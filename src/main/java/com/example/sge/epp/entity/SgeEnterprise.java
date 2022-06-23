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
public class SgeEnterprise {

    private Integer idEnterprise;
    private String code;
    private String name;
    /* Type Identity */
    private String documentNumber;
    private String imageURL;
    private String address;
    private String email;
    private String phoneNumber;
    private Boolean state;

}
