package com.example.security;

import java.util.Collection;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

/**
 * AuthenticationToken for JWT.
 * 
 * @author char5742
 */
public class JWTAuthenticationToken extends AbstractAuthenticationToken {
    private final AuthenticationUser user;

    public JWTAuthenticationToken(Collection<? extends GrantedAuthority> authorities, AuthenticationUser user) {
        super(authorities);
        this.setAuthenticated(true);
        this.user = user;
    }

    public record AuthenticationUser(String id) {
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public AuthenticationUser getPrincipal() {
        return user;
    }
}
