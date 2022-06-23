/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.sge.epp.controller;

import com.example.sge.epp.entity.dto.LoginDto;
import com.example.sge.epp.response.SgeApiResponse;
import com.example.sge.epp.service.SgeAuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author MARVIN
 */
@RestController
@RequestMapping("/auth")
public class SgeAuthenticationController {
    
    @Autowired
    private SgeAuthenticationService sgeAuthenticationService;
    
    @PostMapping(path = "/login")
    public ResponseEntity<SgeApiResponse> login(@RequestBody LoginDto loginDto) throws Exception {
        return ResponseEntity.ok(SgeApiResponse.success("Login", "Successful authentication", sgeAuthenticationService.login(loginDto)));
    }
}
