/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.sge.epp.service;

import com.example.sge.epp.entity.dto.LoginDto;
import com.example.sge.epp.response.LoginResponse;

/**
 *
 * @author MARVIN
 */
public interface SgeAuthenticationService {

    LoginResponse login(LoginDto loginDto) throws Exception;

}
