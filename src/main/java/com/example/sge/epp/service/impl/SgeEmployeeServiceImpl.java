/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.sge.epp.service.impl;

import com.example.sge.epp.dao.SgeEmployeeDao;
import com.example.sge.epp.entity.SgeEmployee;
import com.example.sge.epp.service.SgeEmployeeService;
import com.example.sge.epp.service.valid.SgeEmployeeValid;
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
public class SgeEmployeeServiceImpl implements SgeEmployeeService {

    @Autowired
    private SgeEmployeeDao sgeEmployeeDao;
    @Autowired
    private SgeEmployeeValid sgeEmployeeValid;

    @Override
    public void insert(SgeEmployee sgeEmployee) {
        sgeEmployeeValid.validateInsert(sgeEmployee);
        sgeEmployee.setCode(sgeEmployeeDao.getNextCode(sgeEmployee));
        sgeEmployeeDao.insert(sgeEmployee);
    }

    @Override
    public void update(SgeEmployee sgeEmployee) {
        sgeEmployeeValid.validateUpdate(sgeEmployee);
        sgeEmployeeDao.update(sgeEmployee);
    }

    @Override
    public void delete(SgeEmployee sgeEmployee) {
        sgeEmployeeValid.validateDelete(sgeEmployee);
        sgeEmployeeDao.delete(sgeEmployee);
    }

    @Override
    public SgeEmployee get(Integer idEmployee) {
        return sgeEmployeeDao.get(idEmployee);
    }

    @Override
    public List<SgeEmployee> getAll() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<SgeEmployee> getAllByIdEnterprise(Integer idEnterprise) {
        return sgeEmployeeDao.getAllByIdEnterprise(idEnterprise);
    }

    @Override
    public List<SgeEmployee> getAllActivesByIdEnterprise(Integer idEnterprise) {
        return sgeEmployeeDao.getAllActivesByIdEnterprise(idEnterprise);
    }

}
