/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.sge.epp.controller;

import com.example.sge.epp.entity.SgeUser;
import com.example.sge.epp.response.SgeApiResponse;
import com.example.sge.epp.service.SgeUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author MARVIN
 */
@RestController
@RequestMapping("/users")
public class SgeUserController {
    
    @Autowired
    private SgeUserService sgeUserService;
    
    @PostMapping
    public ResponseEntity<SgeApiResponse> insert(@RequestBody SgeUser sgeUser) {
        sgeUserService.insert(sgeUser);
        return ResponseEntity.ok(SgeApiResponse.success("Insert User",
                "Successful result", true));
    }
    
    @PutMapping
    public ResponseEntity<SgeApiResponse> update(@RequestBody SgeUser sgeUser) {
        sgeUserService.update(sgeUser);
        return ResponseEntity.ok(SgeApiResponse.success("Update User",
                "Successful result", true));
    }
    
    @DeleteMapping("/{idUser}")
    public ResponseEntity<SgeApiResponse> delete(@PathVariable Integer idUser) {
        SgeUser sgeUser = new SgeUser();
        sgeUser.setIdUser(idUser);
        sgeUserService.delete(sgeUser);
        return ResponseEntity.ok(SgeApiResponse.success("Delete User",
                "Successful result", true));
    }
    
    @GetMapping("/{idUser}")
    public ResponseEntity<SgeApiResponse> get(@PathVariable Integer idUser) {
        return ResponseEntity.ok(SgeApiResponse.success("Get User",
                "Successful result", sgeUserService.get(idUser)));
    }
    
    @GetMapping("/getAllByIdEnterprise/{idEnterprise}")
    public ResponseEntity<SgeApiResponse> getAllByIdEnterprise(@PathVariable Integer idEnterprise) {
        return ResponseEntity.ok(SgeApiResponse.success("Get All Users By IdEnterprise",
                "Successful result", sgeUserService.getAllByIdEnterprise(idEnterprise)));
    }
    
}
