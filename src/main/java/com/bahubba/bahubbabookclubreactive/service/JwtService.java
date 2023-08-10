package com.bahubba.bahubbabookclubreactive.service;

import com.bahubba.bahubbabookclubreactive.model.document.RefreshToken;
import com.bahubba.bahubbabookclubreactive.model.dto.AuthDTO;
import io.jsonwebtoken.Claims;
import org.springframework.http.ResponseCookie;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.security.core.userdetails.UserDetails;
import reactor.core.publisher.Mono;

import java.util.UUID;
import java.util.function.Function;

public interface JwtService {
    ResponseCookie generateJwtCookie(UserDetails userDetails);

    ResponseCookie generateJwtRefreshCookie(String refreshToken);

    Mono<String> getJwtFromCookies(ServerHttpRequest req);

    Mono<String> getJwtRefreshFromCookies(ServerHttpRequest req);

    String extractUsername(String token);

    boolean isTokenValid(String token, UserDetails userDetails);

    <T> T extractClaim(String token, Function<Claims, T> claimsResolver);

    Mono<AuthDTO> refreshToken(ServerHttpRequest req);

    Mono<AuthDTO> refreshToken(String token);

    Mono<RefreshToken> getByToken(String token);

    Mono<RefreshToken> createRefreshToken(UUID readerID);

    RefreshToken verifyExpiration(RefreshToken token);

    Mono<Void> deleteByReaderID(UUID readerID);

    ResponseCookie generateCookie(String name, String value, String path);

    void deleteRefreshToken(ServerHttpRequest req);
}
