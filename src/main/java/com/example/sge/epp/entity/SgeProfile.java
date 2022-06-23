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
public class SgeProfile {

    private Integer idProfile;
    private String code;
    private String name;
    private String description;
    private Boolean state;

}
