/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.sge.epp.dao;

import com.example.sge.epp.entity.SgeEquipment;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 *
 * @author MARVIN
 */
@Mapper
@Repository
public interface SgeEquipmentDao extends SgeGenericDao<SgeEquipment, Integer> {

    List<SgeEquipment> getAllByIdEnterprise(Integer idEnterprise);

    List<SgeEquipment> getAllActivesByIdEnterprise(Integer idEnterprise);

    List<SgeEquipment> getAllByIdClassification(Integer idClassification);

    String getNextCode(SgeEquipment sgeEquipment);

}
