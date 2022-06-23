/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.sge.epp.service.valid;

import com.example.sge.epp.dao.SgeEmployeeDao;
import com.example.sge.epp.dao.SgeEnterpriseDao;
import com.example.sge.epp.dao.SgeTypeIdentityDao;
import com.example.sge.epp.dao.SgeUserDao;
import com.example.sge.epp.entity.SgeEmployee;
import com.example.sge.epp.entity.SgeEnterprise;
import com.example.sge.epp.entity.SgeTypeIdentity;
import com.example.sge.epp.util.ValidateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author MARVIN
 */
@Service
public class SgeEmployeeValid {
    
    @Autowired
    private SgeEnterpriseDao sgeEnterpriseDao;
    @Autowired
    private SgeTypeIdentityDao sgeTypeIdentityDao;
    @Autowired
    private SgeEmployeeDao sgeEmployeeDao;
    @Autowired
    private SgeUserDao sgeUserDao;
    
    public void validate(SgeEmployee sgeEmployee) {
        /* Main validation */
        ValidateUtil.isRequired(sgeEmployee.getSgeEnterprise(), "Enterprise");
        ValidateUtil.isRequired(sgeEmployee.getSgeEnterprise().getIdEnterprise(), "IdEnterprise");
        ValidateUtil.isRequired(sgeEmployee.getNames(), "Names");
        ValidateUtil.isRequired(sgeEmployee.getLastNames(), "LastNames");
        ValidateUtil.isRequired(sgeEmployee.getSgeTypeIdentity(), "TypeIdentity");
        ValidateUtil.isRequired(sgeEmployee.getSgeTypeIdentity().getIdTypeIdentity(), "IdTypeIdentity");
        ValidateUtil.isRequired(sgeEmployee.getDocumentNumber(), "DocumentNumber");
        ValidateUtil.isRequired(sgeEmployee.getState(), "State");
        /* Relationship validation Enterprise */
        SgeEnterprise sgeEnterprise = sgeEnterpriseDao.get(sgeEmployee.getSgeEnterprise().getIdEnterprise());
        ValidateUtil.comply(sgeEnterprise != null, "Enterprise selected does not exist");
        ValidateUtil.comply(sgeEnterprise.getState(), "Enterprise selected is inactive");
        /* Relationship validation Type Identity */
        SgeTypeIdentity sgeTypeIdentity = sgeTypeIdentityDao.get(sgeEmployee.getSgeTypeIdentity().getIdTypeIdentity());
        ValidateUtil.comply(sgeTypeIdentity != null, "Type Identity selected does not exist");
        ValidateUtil.comply(sgeTypeIdentity.getState(), "Type Identity selected is inactive");
        /* Add objects */
        sgeEmployee.setSgeEnterprise(sgeEnterprise);
        sgeEmployee.setSgeTypeIdentity(sgeTypeIdentity);
    }
    
    public void validateInsert(SgeEmployee sgeEmployee) {
        validate(sgeEmployee);
        ValidateUtil.comply(sgeEmployeeDao.getByDocumentNumber(sgeEmployee.getSgeEnterprise().getIdEnterprise(),
                sgeEmployee.getDocumentNumber()) == null, "Document number is already registered");
    }
    
    public void validateUpdate(SgeEmployee sgeEmployee) {
        validate(sgeEmployee);
        ValidateUtil.isRequired(sgeEmployee.getIdEmployee(), "IdEmployee");
        SgeEmployee employeeFinded = sgeEmployeeDao.get(sgeEmployee.getIdEmployee());
        ValidateUtil.comply(employeeFinded != null, "Employee selected does not exist");
        if (!sgeEmployee.getDocumentNumber().equals(employeeFinded.getDocumentNumber())) {
            ValidateUtil.comply(sgeEmployeeDao.getByDocumentNumber(sgeEmployee.getSgeEnterprise().getIdEnterprise(),
                    sgeEmployee.getDocumentNumber()) == null, "Document number is already registered");
        }
    }
    
    public void validateDelete(SgeEmployee sgeEmployee) {
        ValidateUtil.comply(sgeEmployeeDao.get(sgeEmployee.getIdEmployee()) != null,
                "Employee selected does not exist");
        ValidateUtil.comply(sgeUserDao.getAllByIdEmployee(sgeEmployee.getIdEmployee()).isEmpty(),
                "There are users related to the selected employee");
    }
    
}
