/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.sge.epp.security;

import com.example.sge.epp.entity.SgeUser;
import java.util.ArrayList;
import java.util.Collection;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 *
 * @author MARVIN
 */
public class CustomUserSecurity extends SgeUser implements UserDetails {

    public CustomUserSecurity(SgeUser sgeUser) {
        setIdUser(sgeUser.getIdUser());
        setSgeEnterprise(sgeUser.getSgeEnterprise());
        setCode(sgeUser.getCode());
        setSgeProfile(sgeUser.getSgeProfile());
        setName(sgeUser.getName());
        setPass(sgeUser.getPass());
        setSgeEmployee(sgeUser.getSgeEmployee());
        setState(sgeUser.getState());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return new ArrayList<>();
    }

    @Override
    public String getUsername() {
        return getName();
    }

    @Override
    public String getPassword() {
        return getPass();
    }

    @Override
    public boolean isAccountNonExpired() {
        return getState();
    }

    @Override
    public boolean isAccountNonLocked() {
        return getState();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return getState();
    }

    @Override
    public boolean isEnabled() {
        return getState();
    }

}
