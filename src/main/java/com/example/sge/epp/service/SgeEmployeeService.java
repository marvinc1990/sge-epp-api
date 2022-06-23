/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.sge.epp.service;

import com.example.sge.epp.entity.SgeEmployee;
import java.util.List;

/**
 *
 * @author MARVIN
 */
public interface SgeEmployeeService extends SgeGenericService<SgeEmployee, Integer> {

    List<SgeEmployee> getAllByIdEnterprise(Integer idEnterprise);

    List<SgeEmployee> getAllActivesByIdEnterprise(Integer idEnterprise);

}
