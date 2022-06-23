/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.sge.epp.entity.dto;

import lombok.Data;

/**
 *
 * @author MARVIN
 */
@Data
public class LoginDto {

    private String enterprise;
    private String username;
    private String password;

    public String getUsernameEnterprise() {
        return new StringBuilder(this.username).append("_")
                .append(this.enterprise).toString();
    }

}
