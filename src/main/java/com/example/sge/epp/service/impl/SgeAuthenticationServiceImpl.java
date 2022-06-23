/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.sge.epp.service.impl;

import com.example.sge.epp.entity.SgeUser;
import com.example.sge.epp.entity.dto.LoginDto;
import com.example.sge.epp.response.LoginResponse;
import com.example.sge.epp.service.SgeAuthenticationService;
import com.example.sge.epp.service.SgeUserService;
import com.example.sge.epp.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author MARVIN
 */
@Service
@Transactional
public class SgeAuthenticationServiceImpl implements SgeAuthenticationService {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private SgeUserService sgeUserService;

    @Override
    public LoginResponse login(LoginDto loginDto) throws Exception {
        /* Validate user and password */
        validateAuthentication(loginDto);
        /* Get Parameters */
        String token = JwtUtil.generateToken(loginDto.getUsernameEnterprise());
        SgeUser sgeUser = sgeUserService.getByNameAndEnterprise(loginDto.getUsername(), loginDto.getEnterprise());
        /* Login Response */
        LoginResponse response = new LoginResponse();
        response.setAccessToken(token);
        response.setSgeUser(sgeUser);
        return response;
    }

    private void validateAuthentication(LoginDto loginDto) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getUsernameEnterprise(), loginDto.getPassword()));
        } catch (AuthenticationException e) {
            throw new RuntimeException(e);
        }
    }

}
