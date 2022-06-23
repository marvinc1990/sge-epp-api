/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.sge.epp.service;

import com.example.sge.epp.entity.SgeClassification;
import java.util.List;

/**
 *
 * @author MARVIN
 */
public interface SgeClassificationService extends SgeGenericService<SgeClassification, Integer> {

    List<SgeClassification> getAllByIdEnterprise(Integer idEnterprise);

    List<SgeClassification> getAllActivesByIdEnterprise(Integer idEnterprise);

}
