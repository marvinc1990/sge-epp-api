/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.sge.epp.service.impl;

import com.example.sge.epp.dao.SgeTypeIdentityDao;
import com.example.sge.epp.entity.SgeTypeIdentity;
import com.example.sge.epp.service.SgeTypeIdentityService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author MARVIN
 */
@Service
@Transactional
public class SgeTypeIdentityServiceImpl implements SgeTypeIdentityService {

    @Autowired
    private SgeTypeIdentityDao sgeTypeIdentityDao;

    @Override
    public void insert(SgeTypeIdentity sgeTypeIdentity) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void update(SgeTypeIdentity sgeTypeIdentity) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void delete(SgeTypeIdentity sgeTypeIdentity) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public SgeTypeIdentity get(Integer idTypeIdentity) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<SgeTypeIdentity> getAll() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<SgeTypeIdentity> getAllActives() {
        return sgeTypeIdentityDao.getAllActives();
    }

    @Override
    public List<SgeTypeIdentity> getAllActivesByType(String type) {
        return sgeTypeIdentityDao.getAllActivesByType(type);
    }

}
