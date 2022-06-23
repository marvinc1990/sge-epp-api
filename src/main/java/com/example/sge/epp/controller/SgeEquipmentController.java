/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.sge.epp.controller;

import com.example.sge.epp.entity.SgeEquipment;
import com.example.sge.epp.response.SgeApiResponse;
import com.example.sge.epp.service.SgeEquipmentService;
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
@RequestMapping("/equipments")
public class SgeEquipmentController {

    @Autowired
    private SgeEquipmentService sgeEquipmentService;

    @PostMapping
    public ResponseEntity<SgeApiResponse> insert(@RequestBody SgeEquipment sgeEquipment) {
        sgeEquipmentService.insert(sgeEquipment);
        return ResponseEntity.ok(SgeApiResponse.success("Insert Equipment",
                "Successful result", true));
    }

    @PutMapping
    public ResponseEntity<SgeApiResponse> update(@RequestBody SgeEquipment sgeEquipment) {
        sgeEquipmentService.update(sgeEquipment);
        return ResponseEntity.ok(SgeApiResponse.success("Update Equipment",
                "Successful result", true));
    }

    @DeleteMapping("/{idEquipment}")
    public ResponseEntity<SgeApiResponse> delete(@PathVariable Integer idEquipment) {
        SgeEquipment sgeEquipment = new SgeEquipment();
        sgeEquipment.setIdEquipment(idEquipment);
        sgeEquipmentService.delete(sgeEquipment);
        return ResponseEntity.ok(SgeApiResponse.success("Delete Equipment",
                "Successful result", true));
    }

    @GetMapping("/getAllByIdEnterprise/{idEnterprise}")
    public ResponseEntity<SgeApiResponse> getAllByIdEnterprise(@PathVariable Integer idEnterprise) {
        return ResponseEntity.ok(SgeApiResponse.success("Get All Equipoments By IdEnterprise",
                "Successful result", sgeEquipmentService.getAllByIdEnterprise(idEnterprise)));
    }

    @GetMapping("/getAllActivesByIdEnterprise/{idEnterprise}")
    public ResponseEntity<SgeApiResponse> getAllActivesByIdEnterprise(@PathVariable Integer idEnterprise) {
        return ResponseEntity.ok(SgeApiResponse.success("Get All Actives Equipments By IdEnterprise",
                "Successful result", sgeEquipmentService.getAllActivesByIdEnterprise(idEnterprise)));
    }

}
