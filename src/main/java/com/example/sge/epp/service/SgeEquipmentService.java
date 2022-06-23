/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.sge.epp.service;

import com.example.sge.epp.entity.SgeEquipment;
import java.util.List;

/**
 *
 * @author MARVIN
 */
public interface SgeEquipmentService extends SgeGenericService<SgeEquipment, Integer> {

    List<SgeEquipment> getAllByIdEnterprise(Integer idEnterprise);

    List<SgeEquipment> getAllActivesByIdEnterprise(Integer idEnterprise);

    String obtainStateStock(SgeEquipment sgeEquipment);

}
