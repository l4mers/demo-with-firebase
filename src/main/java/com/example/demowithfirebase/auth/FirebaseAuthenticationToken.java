package com.example.demowithfirebase.auth;

import com.example.demowithfirebase.model.CustomerExample;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import javax.security.auth.Subject;
import java.util.Collection;

//Token klass
public class FirebaseAuthenticationToken extends AbstractAuthenticationToken {
    private final CustomerExample principal;
    private final String credentials;

    public FirebaseAuthenticationToken(CustomerExample principal, String credentials, Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        this.principal = principal;
        this.credentials = credentials;
        setAuthenticated(true);
    }

    @Override
    public String getCredentials() {
        return credentials;
    }

    @Override
    public CustomerExample getPrincipal() {
        return principal;
    }
}
