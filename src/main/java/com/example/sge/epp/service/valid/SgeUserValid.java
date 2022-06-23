/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.sge.epp.service.valid;

import com.example.sge.epp.dao.SgeEnterpriseDao;
import com.example.sge.epp.dao.SgeProfileDao;
import com.example.sge.epp.dao.SgeUserDao;
import com.example.sge.epp.entity.SgeEnterprise;
import com.example.sge.epp.entity.SgeProfile;
import com.example.sge.epp.entity.SgeUser;
import com.example.sge.epp.util.ValidateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author MARVIN
 */
@Service
public class SgeUserValid {

    @Autowired
    private SgeEnterpriseDao sgeEnterpriseDao;
    @Autowired
    private SgeProfileDao sgeProfileDao;
    @Autowired
    private SgeUserDao sgeUserDao;

    private void validate(SgeUser sgeUser) {
        /* Main validation */
        ValidateUtil.isRequired(sgeUser.getSgeEnterprise(), "Enterprise");
        ValidateUtil.isRequired(sgeUser.getSgeEnterprise().getIdEnterprise(), "IdEnterprise");
        ValidateUtil.isRequired(sgeUser.getSgeProfile(), "Profile");
        ValidateUtil.isRequired(sgeUser.getSgeProfile().getIdProfile(), "IdProfile");
        ValidateUtil.isRequired(sgeUser.getName(), "Name");
        ValidateUtil.isRequired(sgeUser.getState(), "State");
        /* Relationship validation Enterprise */
        SgeEnterprise sgeEnterprise = sgeEnterpriseDao.get(sgeUser.getSgeEnterprise().getIdEnterprise());
        ValidateUtil.comply(sgeEnterprise != null, "Enterprise selected does not exist");
        ValidateUtil.comply(sgeEnterprise.getState(), "Enterprise selected is inactive");
        sgeUser.setSgeEnterprise(sgeEnterprise);
        /* Relationship validation Profile */
        SgeProfile sgeProfile = sgeProfileDao.get(sgeUser.getSgeProfile().getIdProfile());
        ValidateUtil.comply(sgeProfile != null, "Profile selected does not exist");
        ValidateUtil.comply(sgeProfile.getState(), "Profile selected is inactive");
        sgeUser.setSgeProfile(sgeProfile);
    }

    public void validateInsert(SgeUser sgeUser) {
        /* Main validation */
        validate(sgeUser);
        ValidateUtil.isRequired(sgeUser.getPass(), "Password");
        /* Aditional validation */
        SgeUser sgeUserFinded = sgeUserDao.getByNameAndEnterprise(sgeUser.getName(),
                sgeUser.getSgeEnterprise().getCode());
        ValidateUtil.comply(sgeUserFinded == null, 
                "User selected is already registered. Please, modify username.");
    }
    
    public void validateUpdate(SgeUser sgeUser) {
        /* Main validation */
        validate(sgeUser);
        ValidateUtil.isRequired(sgeUser.getIdUser(), "IdUser");
        ValidateUtil.isRequired(sgeUser.getControlPass(), "ControlPass");
        if (sgeUser.getControlPass()) {
            ValidateUtil.isRequired(sgeUser.getPass(), "Password");
        }
        /* Aditional validation */
        ValidateUtil.comply(sgeUserDao.get(sgeUser.getIdUser()) != null, "User selected does not exist");
    }
    
    public void validateDelete(SgeUser sgeUser) {
        ValidateUtil.comply(sgeUserDao.get(sgeUser.getIdUser()) != null,
                "User selected does not exist");
    }

}
