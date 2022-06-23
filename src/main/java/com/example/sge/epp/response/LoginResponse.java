/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.sge.epp.response;

import com.example.sge.epp.entity.SgeUser;
import lombok.Data;

/**
 *
 * @author MARVIN
 */
@Data
public class LoginResponse {

    private String accessToken;
    private SgeUser sgeUser;

}
