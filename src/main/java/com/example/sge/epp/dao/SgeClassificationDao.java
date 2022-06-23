/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.sge.epp.dao;

import com.example.sge.epp.entity.SgeClassification;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 *
 * @author MARVIN
 */
@Mapper
@Repository
public interface SgeClassificationDao extends SgeGenericDao<SgeClassification, Integer> {

    List<SgeClassification> getAllByIdEnterprise(Integer idEnterprise);

    List<SgeClassification> getAllActivesByIdEnterprise(Integer idEnterprise);

    String getNextCode(SgeClassification sgeClassification);

}
