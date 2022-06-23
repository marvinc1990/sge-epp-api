/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.sge.epp.controller;

import com.example.sge.epp.entity.SgeClassification;
import com.example.sge.epp.response.SgeApiResponse;
import com.example.sge.epp.service.SgeClassificationService;
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
@RequestMapping("/classifications")
public class SgeClassificationController {

    @Autowired
    private SgeClassificationService sgeClassificationService;

    @PostMapping
    public ResponseEntity<SgeApiResponse> insert(@RequestBody SgeClassification sgeClassification) {
        sgeClassificationService.insert(sgeClassification);
        return ResponseEntity.ok(SgeApiResponse.success("Insert Classification",
                "Successful result", true));
    }

    @PutMapping
    public ResponseEntity<SgeApiResponse> update(@RequestBody SgeClassification sgeClassification) {
        sgeClassificationService.update(sgeClassification);
        return ResponseEntity.ok(SgeApiResponse.success("Update Classification",
                "Successful result", true));
    }

    @DeleteMapping("/{idClassification}")
    public ResponseEntity<SgeApiResponse> delete(@PathVariable Integer idClassification) {
        SgeClassification sgeClassification = new SgeClassification();
        sgeClassification.setIdClassification(idClassification);
        sgeClassificationService.delete(sgeClassification);
        return ResponseEntity.ok(SgeApiResponse.success("Delete Classification",
                "Successful result", true));
    }

    @GetMapping("/{idClassification}")
    public ResponseEntity<SgeApiResponse> get(@PathVariable Integer idClassification) {
        return ResponseEntity.ok(SgeApiResponse.success("Get Classification",
                "Successful result", sgeClassificationService.get(idClassification)));
    }

    @GetMapping("/getAllByIdEnterprise/{idEnterprise}")
    public ResponseEntity<SgeApiResponse> getAllByIdEnterprise(@PathVariable Integer idEnterprise) {
        return ResponseEntity.ok(SgeApiResponse.success("Get All Classifications By IdEnterprise",
                "Successful result", sgeClassificationService.getAllByIdEnterprise(idEnterprise)));
    }

    @GetMapping("/getAllActivesByIdEnterprise/{idEnterprise}")
    public ResponseEntity<SgeApiResponse> getAllActivesByIdEnterprise(@PathVariable Integer idEnterprise) {
        return ResponseEntity.ok(SgeApiResponse.success("Get All Active Classifications By IdEnterprise",
                "Successful result", sgeClassificationService.getAllActivesByIdEnterprise(idEnterprise)));
    }

}
