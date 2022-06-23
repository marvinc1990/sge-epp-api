/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.sge.epp.service;

import com.example.sge.epp.entity.SgeTypeIdentity;
import java.util.List;

/**
 *
 * @author MARVIN
 */
public interface SgeTypeIdentityService extends SgeGenericService<SgeTypeIdentity, Integer> {

    List<SgeTypeIdentity> getAllActives();

    List<SgeTypeIdentity> getAllActivesByType(String type);

}
