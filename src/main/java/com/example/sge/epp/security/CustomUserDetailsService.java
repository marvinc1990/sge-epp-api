/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.sge.epp.security;

import com.example.sge.epp.entity.SgeUser;
import com.example.sge.epp.service.SgeUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 *
 * @author MARVIN
 */
@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private SgeUserService sgeUserService;
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        String[] data = username.split("_");
        if (data.length >= 1) {
            SgeUser sgeUser = sgeUserService.getByNameAndEnterprise(data[0], data[1]);
            if (sgeUser != null) {
                if (sgeUser.getState()) {
                    return new CustomUserSecurity(sgeUser);
                } else {
                    throw new UsernameNotFoundException("User not enabled");
                }
            } else {
                throw new UsernameNotFoundException("User not found");
            }
        } else {
            throw new UsernameNotFoundException("Data username should have two values");
        }
    }

}
