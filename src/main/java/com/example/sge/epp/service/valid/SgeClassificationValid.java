/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.sge.epp.service.valid;

import com.example.sge.epp.dao.SgeClassificationDao;
import com.example.sge.epp.dao.SgeEnterpriseDao;
import com.example.sge.epp.dao.SgeEquipmentDao;
import com.example.sge.epp.entity.SgeClassification;
import com.example.sge.epp.entity.SgeEnterprise;
import com.example.sge.epp.util.ValidateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author MARVIN
 */
@Service
public class SgeClassificationValid {

    @Autowired
    private SgeEnterpriseDao sgeEnterpriseDao;
    @Autowired
    private SgeClassificationDao sgeClassificationDao;
    @Autowired
    private SgeEquipmentDao sgeEquipmentDao;

    public void validate(SgeClassification sgeClassification) {
        /* Main validation */
        ValidateUtil.isRequired(sgeClassification.getSgeEnterprise(), "Enterprise");
        ValidateUtil.isRequired(sgeClassification.getSgeEnterprise().getIdEnterprise(), "IdEnterprise");
        ValidateUtil.isRequired(sgeClassification.getName(), "Name");
        ValidateUtil.isRequired(sgeClassification.getState(), "State");
        /* Relationship validation */
        SgeEnterprise sgeEnterprise = sgeEnterpriseDao.get(sgeClassification.getSgeEnterprise().getIdEnterprise());
        ValidateUtil.comply(sgeEnterprise != null, "Enterprise selected does not exist");
        ValidateUtil.comply(sgeEnterprise.getState(), "Enterprise selected is inactive");
        /* Add objects */
        sgeClassification.setSgeEnterprise(sgeEnterprise);
    }

    public void validateUpdate(SgeClassification sgeClassification) {
        validate(sgeClassification);
        ValidateUtil.isRequired(sgeClassification.getIdClassification(), "IdClassification");
    }

    public void validateDelete(SgeClassification sgeClassification) {
        ValidateUtil.comply(sgeClassificationDao.get(sgeClassification.getIdClassification()) != null, "Classification selected does not exist");
        ValidateUtil.comply(sgeEquipmentDao.getAllByIdClassification(sgeClassification.getIdClassification()).isEmpty(), "There are EPPs that use this classification");
    }

}
