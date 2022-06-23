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
public class SgeEquipment {

    private Integer idEquipment;
    private SgeClassification sgeClassification;
    private String code;
    private String name;
    private String features;
    private String imageURL;
    private Double cost;
    private Double minimumStock;
    private Double currentStock;
    private String stateStock;
    private Boolean state;
    /* Utils */
    private String imageBase64;
    private String imageExtension;

    public String getFileName() {
        if (this.code != null && this.imageExtension != null) {
            return this.code.concat(".").concat(this.imageExtension);
        }
        return null;
    }

    public String getRemotePath() {
        if (getFileName() != null) {
            return new StringBuilder()
                    .append("enterprises").append("/")
                    .append(getSgeClassification().getSgeEnterprise().getCode()).append("/")
                    .append("equipments").append("/")
                    .append(getFileName()).toString();
        }
        return null;
    }

}
