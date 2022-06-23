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
import com.example.sge.epp.entity.SgeEquipment;
import com.example.sge.epp.util.ValidateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author MARVIN
 */
@Service
public class SgeEquipmentValid {

    @Autowired
    private SgeEnterpriseDao sgeEnterpriseDao;
    @Autowired
    private SgeClassificationDao sgeClassificationDao;
    @Autowired
    private SgeEquipmentDao sgeEquipmentDao;

    public void validate(SgeEquipment sgeEquipment) {
        /* Main validation */
        ValidateUtil.isRequired(sgeEquipment.getSgeClassification(), "Classification");
        ValidateUtil.isRequired(sgeEquipment.getSgeClassification().getIdClassification(), "IdClassification");
        ValidateUtil.isRequired(sgeEquipment.getName(), "Name");
        ValidateUtil.isRequired(sgeEquipment.getCost(), "Cost");
        ValidateUtil.isRequired(sgeEquipment.getMinimumStock(), "MinimumStock");
        ValidateUtil.isRequired(sgeEquipment.getState(), "State");
        /* Relationship validation Classification */
        SgeClassification sgeClassification = sgeClassificationDao.get(sgeEquipment.getSgeClassification().getIdClassification());
        ValidateUtil.comply(sgeClassification != null, "Classification selected does not exist");
        ValidateUtil.comply(sgeClassification.getState(), "Classification selected is inactive");
        /* Relationship validation Enterprise */
        SgeEnterprise sgeEnterprise = sgeEnterpriseDao.get(sgeClassification.getSgeEnterprise().getIdEnterprise());
        ValidateUtil.comply(sgeEnterprise != null, "Enterprise selected does not exist");
        ValidateUtil.comply(sgeEnterprise.getState(), "Enterprise selected is inactiva");
        /* Add Clasificacion */
        sgeClassification.setSgeEnterprise(sgeEnterprise);
        sgeEquipment.setSgeClassification(sgeClassification);
    }

    public void validateUpdate(SgeEquipment sgeEquipment) {
        validate(sgeEquipment);
        ValidateUtil.isRequired(sgeEquipment.getIdEquipment(), "IdEquipment");
        /* Validation */
        SgeEquipment equipmentFinded = sgeEquipmentDao.get(sgeEquipment.getIdEquipment());
        ValidateUtil.comply(equipmentFinded != null, "Equipment selected does not exist");
    }

    public void validateDelete(SgeEquipment sgeEquipment) {
        SgeEquipment sgeEquipmentFinded = sgeEquipmentDao.get(sgeEquipment.getIdEquipment());
        ValidateUtil.comply(sgeEquipmentFinded != null, "Equiptment selected does not exist");
        sgeEquipment.setImageURL(sgeEquipmentFinded.getImageURL());
    }

}
