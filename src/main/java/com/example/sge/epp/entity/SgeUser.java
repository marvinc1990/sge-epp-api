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
public class SgeUser {

    private Integer idUser;
    private SgeEnterprise sgeEnterprise;
    private String code;
    private SgeProfile sgeProfile;
    private String name;
    private String pass;
    private Boolean controlPass;
    private SgeEmployee sgeEmployee;
    private Boolean state;

}
