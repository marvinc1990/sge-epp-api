/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.sge.epp.service.impl;

import com.example.sge.epp.dao.SgeClassificationDao;
import com.example.sge.epp.entity.SgeClassification;
import com.example.sge.epp.service.SgeClassificationService;
import com.example.sge.epp.service.valid.SgeClassificationValid;
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
public class SgeClassificationServiceImpl implements SgeClassificationService {

    @Autowired
    private SgeClassificationDao sgeClassificationDao;
    @Autowired
    private SgeClassificationValid sgeClassificationValid;

    @Override
    public void insert(SgeClassification sgeClassification) {
        sgeClassificationValid.validate(sgeClassification);
        sgeClassification.setCode(sgeClassificationDao.getNextCode(sgeClassification));
        sgeClassificationDao.insert(sgeClassification);
    }

    @Override
    public void update(SgeClassification sgeClassification) {
        sgeClassificationValid.validateUpdate(sgeClassification);
        sgeClassificationDao.update(sgeClassification);
    }

    @Override
    public void delete(SgeClassification sgeClassification) {
        sgeClassificationValid.validateDelete(sgeClassification);
        sgeClassificationDao.delete(sgeClassification);
    }

    @Override
    public SgeClassification get(Integer idClassification) {
        return sgeClassificationDao.get(idClassification);
    }

    @Override
    public List<SgeClassification> getAll() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<SgeClassification> getAllByIdEnterprise(Integer idEnterprise) {
        return sgeClassificationDao.getAllByIdEnterprise(idEnterprise);
    }

    @Override
    public List<SgeClassification> getAllActivesByIdEnterprise(Integer idEnterprise) {
        return sgeClassificationDao.getAllActivesByIdEnterprise(idEnterprise);
    }

}
