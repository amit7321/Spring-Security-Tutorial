package com.example.Spring_Security_Tutoiral.auth;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class ApplicationUser implements UserDetails {

    private final List<? extends GrantedAuthority> grandtedAuthorities;
    private final String password;
    private final String username;
    private final boolean isAccountNonExpired;
    private final boolean isCredentialsNonExpired;
    private final boolean isEnabled;
    private final boolean isAccountNonLocked;

    public ApplicationUser(List<? extends GrantedAuthority> grandtedAuthorities, String password, String username,
                           boolean isAccountNonExpired, boolean isCredentialsNonExpired, boolean isEnabled, boolean isAccountNonLocked)
    {
        this.grandtedAuthorities = grandtedAuthorities;
        this.password = password;
        this.username = username;
        this.isAccountNonExpired = isAccountNonExpired;
        this.isCredentialsNonExpired = isCredentialsNonExpired;
        this.isEnabled = isEnabled;
        this.isAccountNonLocked = isAccountNonLocked;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return grandtedAuthorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return isAccountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return isAccountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return isCredentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return isEnabled;
    }
}
