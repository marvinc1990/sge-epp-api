/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.sge.epp.dao;

import com.example.sge.epp.entity.SgeEnterprise;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 *
 * @author MARVIN
 */
@Mapper
@Repository
public interface SgeEnterpriseDao extends SgeGenericDao<SgeEnterprise, Integer> {

}
