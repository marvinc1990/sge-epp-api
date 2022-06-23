/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.sge.epp.entity;

import lombok.Data;

/**
 *
 * @author MARVIN
 */
@Data
public class SgeEmployee {

    private Integer idEmployee;
    private SgeEnterprise sgeEnterprise;
    private String code;
    private String names;
    private String lastNames;
    private String nameCompleted;
    private SgeTypeIdentity sgeTypeIdentity;
    private String documentNumber;
    private String occupation;
    private String area;
    private String email;
    private String phone;
    private String signatureURL;
    private String fingerprint;
    private Boolean state;

}
