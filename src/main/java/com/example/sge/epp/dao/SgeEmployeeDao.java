/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.sge.epp.dao;

import com.example.sge.epp.entity.SgeEmployee;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author MARVIN
 */
@Mapper
@Repository
public interface SgeEmployeeDao extends SgeGenericDao<SgeEmployee, Integer> {

    List<SgeEmployee> getAllByIdEnterprise(Integer idEnterprise);

    List<SgeEmployee> getAllActivesByIdEnterprise(Integer idEnterprise);

    SgeEmployee getByDocumentNumber(@Param("idEnterprise") Integer idEnterprise, @Param("documentNumber") String documentNumber);

    String getNextCode(SgeEmployee sgeEmployee);

}
