package com.bahubba.bahubbabookclubreactive.model.document;

import com.bahubba.bahubbabookclubreactive.model.document.validator.RoleConstraint;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Document(collection = "readers")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Reader implements UserDetails {
    @Id
    private UUID id;
    private String username;
    private String email;
    @RoleConstraint
    private String role;
    private String password;
    private String givenName;
    private String middleName;
    private String surname;
    private String suffix;
    private String title;
    @CreatedDate
    private Date joined;
    private Date departed;

    // TODO - Add Role "enum" (MongoDB validation, really) with admin and reader
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(getRole()));
    }

    @Override
    public boolean isAccountNonExpired() {
        return departed == null;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
