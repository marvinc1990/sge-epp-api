/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.sge.epp.controller;

import com.example.sge.epp.entity.SgeEmployee;
import com.example.sge.epp.response.SgeApiResponse;
import com.example.sge.epp.service.SgeEmployeeService;
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
@RequestMapping("/employees")
public class SgeEmployeeController {

    @Autowired
    private SgeEmployeeService sgeEmployeeService;

    @PostMapping
    public ResponseEntity<SgeApiResponse> insert(@RequestBody SgeEmployee sgeEmployee) {
        sgeEmployeeService.insert(sgeEmployee);
        return ResponseEntity.ok(SgeApiResponse.success("Insert Employee",
                "Successful result", true));
    }

    @PutMapping
    public ResponseEntity<SgeApiResponse> update(@RequestBody SgeEmployee sgeEmployee) {
        sgeEmployeeService.update(sgeEmployee);
        return ResponseEntity.ok(SgeApiResponse.success("Update Employee",
                "Successful result", true));
    }

    @DeleteMapping("/{idEmployee}")
    public ResponseEntity<SgeApiResponse> delete(@PathVariable Integer idEmployee) {
        SgeEmployee sgeEmployee = new SgeEmployee();
        sgeEmployee.setIdEmployee(idEmployee);
        sgeEmployeeService.delete(sgeEmployee);
        return ResponseEntity.ok(SgeApiResponse.success("Delete Employee",
                "Successful result", true));
    }

    @GetMapping("/{idEmployee}")
    public ResponseEntity<SgeApiResponse> get(@PathVariable Integer idEmployee) {
        return ResponseEntity.ok(SgeApiResponse.success("Get Employee",
                "Successful result", sgeEmployeeService.get(idEmployee)));
    }

    @GetMapping("/getAllByIdEnterprise/{idEnterprise}")
    public ResponseEntity<SgeApiResponse> getAllByIdEnterprise(@PathVariable Integer idEnterprise) {
        return ResponseEntity.ok(SgeApiResponse.success("Get All Employees By IdEnterprise",
                "Successful result", sgeEmployeeService.getAllByIdEnterprise(idEnterprise)));
    }

    @GetMapping("/getAllActivesByIdEnterprise/{idEnterprise}")
    public ResponseEntity<SgeApiResponse> getAllActivesByIdEnterprise(@PathVariable Integer idEnterprise) {
        return ResponseEntity.ok(SgeApiResponse.success("Get All Actives Employees By IdEnterprise",
                "Successful result", sgeEmployeeService.getAllActivesByIdEnterprise(idEnterprise)));
    }

}
