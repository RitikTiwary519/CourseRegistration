package com.ritik.CourseRegistrationSystem.service;

import com.ritik.CourseRegistrationSystem.model.CRS_User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;

public class UserPrincipal implements UserDetails {

    private CRS_User crsu;
    public UserPrincipal(CRS_User crsu)
    {
        this.crsu = crsu;
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority(crsu.getRole()));
    }

    @Override
    public String getPassword() {
        return crsu.getPassword();
    }

    @Override
    public String getUsername() {
        return crsu.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
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
