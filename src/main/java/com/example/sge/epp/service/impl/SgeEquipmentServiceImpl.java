/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.sge.epp.service.impl;

import com.example.sge.epp.dao.SgeEquipmentDao;
import com.example.sge.epp.entity.SgeEquipment;
import com.example.sge.epp.service.SgeEquipmentService;
import com.example.sge.epp.service.valid.SgeEquipmentValid;
import com.example.sge.epp.util.AwsS3Util;
import com.example.sge.epp.util.Base64Util;
import java.io.File;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author MARVIN
 */
@Service
@Transactional
public class SgeEquipmentServiceImpl implements SgeEquipmentService {

    @Autowired
    private SgeEquipmentDao sgeEquipmentDao;
    @Autowired
    private SgeEquipmentValid sgeEquipmentValid;

    @Value("${aws.s3.host}")
    private String HOST_S3;
    @Value("${server.ec2.dir-files}")
    private String DIR_FILES;

    @Override
    public void insert(SgeEquipment sgeEquipment) {
        sgeEquipmentValid.validate(sgeEquipment);
        sgeEquipment.setCode(sgeEquipmentDao.getNextCode(sgeEquipment));
        sgeEquipment.setCurrentStock(0.00);
        sgeEquipment.setStateStock(obtainStateStock(sgeEquipment));
        if (sgeEquipment.getImageBase64() != null) {
            sgeEquipment.setImageURL(HOST_S3.concat(sgeEquipment.getRemotePath()));
            sgeEquipmentDao.insert(sgeEquipment);
            /* Proccess S3 */
            File file = Base64Util.parseBase64toFile(sgeEquipment.getImageBase64(),
                    DIR_FILES.concat("/").concat(sgeEquipment.getFileName()));
            AwsS3Util.uploadFile(file, sgeEquipment.getRemotePath());
            file.delete();
        } else {
            sgeEquipmentDao.insert(sgeEquipment);
        }
    }

    @Override
    public void update(SgeEquipment sgeEquipment) {
        sgeEquipmentValid.validateUpdate(sgeEquipment);
        SgeEquipment sgeEquipmentFinded = sgeEquipmentDao.get(sgeEquipment.getIdEquipment());
        sgeEquipment.setCode(sgeEquipmentFinded.getCode());
        sgeEquipment.setCurrentStock(sgeEquipmentFinded.getCurrentStock());
        sgeEquipment.setStateStock(obtainStateStock(sgeEquipment));
        if (sgeEquipment.getImageURL() == null) {
            if (sgeEquipment.getImageBase64() != null) {
                sgeEquipment.setImageURL(HOST_S3.concat(sgeEquipment.getRemotePath()));
                sgeEquipmentDao.update(sgeEquipment);
                /* Proccess S3 */
                if (sgeEquipmentFinded.getImageURL() != null) {
                    AwsS3Util.deleteFile(sgeEquipmentFinded.getRemotePath());
                }
                File file = Base64Util.parseBase64toFile(sgeEquipment.getImageBase64(),
                        DIR_FILES.concat("/").concat(sgeEquipment.getFileName()));
                AwsS3Util.uploadFile(file, sgeEquipment.getRemotePath());
                file.delete();
            } else {
                sgeEquipment.setImageURL(null);
                sgeEquipmentDao.update(sgeEquipment);
                if (sgeEquipmentFinded.getImageURL() != null) {
                    AwsS3Util.deleteFile(sgeEquipmentFinded.getRemotePath());
                }
            }
        } else {
            sgeEquipmentDao.update(sgeEquipment);
        }
    }

    @Override
    public void delete(SgeEquipment sgeEquipment) {
        sgeEquipmentValid.validateDelete(sgeEquipment);
        sgeEquipmentDao.delete(sgeEquipment);
        /* Proccess S3 */
        if (sgeEquipment.getImageURL() != null) {
            AwsS3Util.deleteFile(sgeEquipment.getImageURL().replace(HOST_S3, ""));
        }
    }

    @Override
    public SgeEquipment get(Integer idEquipment) {
        return sgeEquipmentDao.get(idEquipment);
    }

    @Override
    public List<SgeEquipment> getAll() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<SgeEquipment> getAllByIdEnterprise(Integer idEnterprise) {
        return sgeEquipmentDao.getAllByIdEnterprise(idEnterprise);
    }

    @Override
    public List<SgeEquipment> getAllActivesByIdEnterprise(Integer idEnterprise) {
        return sgeEquipmentDao.getAllActivesByIdEnterprise(idEnterprise);
    }

    @Override
    public String obtainStateStock(SgeEquipment sgeEquipment) {
        switch (sgeEquipment.getCurrentStock().compareTo(sgeEquipment.getMinimumStock())) {
            case -1:
                return "A";
            case 0:
                return sgeEquipment.getCurrentStock() > 0 ? "M" : "A";
            case 1:
                return "S";
            default:
                throw new RuntimeException("Error al comparar stock");
        }
    }

}
