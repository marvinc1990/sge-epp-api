/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.sge.epp.service.impl;

import com.example.sge.epp.dao.SgeUserDao;
import com.example.sge.epp.entity.SgeUser;
import com.example.sge.epp.service.SgeUserService;
import com.example.sge.epp.service.valid.SgeUserValid;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author MARVIN
 */
@Service
@Transactional
public class SgeUserServiceImpl implements SgeUserService {

    @Autowired
    private SgeUserDao sgeUserDao;
    @Autowired
    private SgeUserValid sgeUserValid;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void insert(SgeUser sgeUser) {
        sgeUserValid.validateInsert(sgeUser);
        sgeUser.setCode(sgeUserDao.getNextCode(sgeUser));
        sgeUser.setPass(passwordEncoder.encode(sgeUser.getPass()));
        sgeUserDao.insert(sgeUser);
    }

    @Override
    public void update(SgeUser sgeUser) {
        sgeUserValid.validateUpdate(sgeUser);
        if (sgeUser.getControlPass()) {
            sgeUser.setPass(passwordEncoder.encode(sgeUser.getPass()));
        } else {
            SgeUser sgeUserFinded = sgeUserDao.get(sgeUser.getIdUser());
            sgeUser.setPass(sgeUserFinded.getPass());
        }
        sgeUserDao.update(sgeUser);
    }

    @Override
    public void delete(SgeUser sgeUser) {
        sgeUserValid.validateDelete(sgeUser);
        sgeUserDao.delete(sgeUser);
    }

    @Override
    public SgeUser get(Integer idUser) {
        return sgeUserDao.get(idUser);
    }

    @Override
    public List<SgeUser> getAll() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public SgeUser getByNameAndEnterprise(String name, String enterprise) {
        return sgeUserDao.getByNameAndEnterprise(name, enterprise);
    }

    @Override
    public List<SgeUser> getAllByIdEnterprise(Integer idEnterprise) {
        return sgeUserDao.getAllByIdEnterprise(idEnterprise);
    }

}
