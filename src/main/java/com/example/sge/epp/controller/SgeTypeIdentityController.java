/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.sge.epp.controller;

import com.example.sge.epp.response.SgeApiResponse;
import com.example.sge.epp.service.SgeTypeIdentityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author MARVIN
 */
@RestController
@RequestMapping("/typeidentities")
public class SgeTypeIdentityController {

    @Autowired
    private SgeTypeIdentityService sgeTypeIdentityService;

    @GetMapping("/getAllActives")
    public ResponseEntity<SgeApiResponse> getAllActives() {
        return ResponseEntity.ok(SgeApiResponse.success("Get All Actives Types of Identities",
                "Successful result", sgeTypeIdentityService.getAllActives()));
    }

    @GetMapping("/getAllActivesByType/{type}")
    public ResponseEntity<SgeApiResponse> getAllActivesByType(@PathVariable String type) {
        return ResponseEntity.ok(SgeApiResponse.success("Get All Actives Types of Identities By Type",
                "Successful result", sgeTypeIdentityService.getAllActivesByType(type)));
    }

}
