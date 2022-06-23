/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.sge.epp.dao;

import com.example.sge.epp.entity.SgeUser;
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
public interface SgeUserDao extends SgeGenericDao<SgeUser, Integer> {

    SgeUser getByNameAndEnterprise(@Param("name") String name, @Param("enterprise") String enterprise);

    List<SgeUser> getAllByIdEnterprise(Integer idEnterprise);

    List<SgeUser> getAllByIdEmployee(Integer idEmployee);

    String getNextCode(SgeUser sgeUser);

}
