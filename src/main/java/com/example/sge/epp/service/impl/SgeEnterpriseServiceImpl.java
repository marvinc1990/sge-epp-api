/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.sge.epp.service.impl;

import com.example.sge.epp.entity.SgeEnterprise;
import com.example.sge.epp.service.SgeEnterpriseService;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author MARVIN
 */
@Service
@Transactional
public class SgeEnterpriseServiceImpl implements SgeEnterpriseService {

    @Override
    public void insert(SgeEnterprise sgeEnterprise) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public void update(SgeEnterprise sgeEnterprise) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public void delete(SgeEnterprise sgeEnterprise) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public SgeEnterprise get(Integer idEnterprise) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public List<SgeEnterprise> getAll() {
        throw new UnsupportedOperationException("Not supported yet."); 
    }
    
}
