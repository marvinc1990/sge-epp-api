/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.sge.epp.service;

import com.example.sge.epp.entity.SgeUser;
import java.util.List;

/**
 *
 * @author MARVIN
 */
public interface SgeUserService extends SgeGenericService<SgeUser, Integer> {

    SgeUser getByNameAndEnterprise(String name, String enterprise);

    List<SgeUser> getAllByIdEnterprise(Integer idEnterprise);

}
