package com.bahubba.bahubbabookclubreactive.service.impl;

import com.bahubba.bahubbabookclubreactive.model.mapper.ReaderMapper;
import com.bahubba.bahubbabookclubreactive.repository.ReaderRepo;
import com.bahubba.bahubbabookclubreactive.service.JwtService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpCookie;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.MultiValueMap;

import java.security.Key;
import java.util.*;
import java.util.function.Function;

@Service
@Transactional
public class JwtServiceImpl implements JwtService {
    @Value("${app.properties.secret_key}")
    private String secretKey;

    @Value("${app.properties.auth_cookie_name}")
    private String authCookieName;

    @Value("${app.properties.refresh_cookie_name}")
    private String refreshCookieName;

    @Autowired
    private ReaderRepo readerRepo;

    @Autowired
    private ReaderMapper readerMapper;

    @Override
    public String getJwtFromCookies(ServerHttpRequest req) {
        return getCookieValueByName(req, authCookieName);
    }

    @Override
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    // TODO - Update validity checks with error handling (see https://www.bezkoder.com/spring-security-refresh-token/)
    @Override
    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
    }

    @Override
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private String getCookieValueByName(ServerHttpRequest req, String name) {
        MultiValueMap<String, HttpCookie> cookieMap = req.getCookies();
        if (cookieMap.containsKey(name)) {
            HttpCookie cookie = cookieMap.getFirst(name);
            if (cookie != null) {
                return cookie.getValue();
            }
        }

        return null;
    }

    // FIXME - Gracefully handle io.jsonwebtoken.ExpiredJwtException
    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder().setSigningKey(getSigningKey()).build().parseClaimsJws(token).getBody();
    }

    private Key getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }
}
