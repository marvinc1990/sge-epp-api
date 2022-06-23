/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.sge.epp.dao;

import com.example.sge.epp.entity.SgeTypeIdentity;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 *
 * @author MARVIN
 */
@Mapper
@Repository
public interface SgeTypeIdentityDao extends SgeGenericDao<SgeTypeIdentity, Integer> {

    List<SgeTypeIdentity> getAllActives();

    List<SgeTypeIdentity> getAllActivesByType(String type);

}
