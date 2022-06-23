/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.sge.epp.service.impl;

import com.example.sge.epp.dao.SgeProfileDao;
import com.example.sge.epp.entity.SgeProfile;
import com.example.sge.epp.service.SgeProfileService;
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
public class SgeProfileServiceImpl implements SgeProfileService {

    @Autowired
    private SgeProfileDao sgeProfileDao;

    @Override
    public void insert(SgeProfile sgeProfile) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void update(SgeProfile sgeProfile) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void delete(SgeProfile sgeProfile) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public SgeProfile get(Integer idProfile) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<SgeProfile> getAll() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<SgeProfile> getAllActives() {
        return sgeProfileDao.getAllActives();
    }

}
