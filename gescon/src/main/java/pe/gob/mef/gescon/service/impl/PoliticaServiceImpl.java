/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.gob.mef.gescon.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.stereotype.Repository;

import pe.gob.mef.gescon.hibernate.dao.PoliticaDao;

import pe.gob.mef.gescon.hibernate.domain.Mtpolitica;

import pe.gob.mef.gescon.service.PoliticaService;
import pe.gob.mef.gescon.util.ServiceFinder;
import pe.gob.mef.gescon.web.bean.Perfil;

import pe.gob.mef.gescon.web.bean.Politica;

/**
 *
 * @author JJacobo
 */
@Repository(value = "PoliticaService")
public class PoliticaServiceImpl implements PoliticaService{

    @Override
    public BigDecimal getNextPK() throws Exception {
        PoliticaDao politicaDao = (PoliticaDao) ServiceFinder.findBean("PoliticaDao");
        return politicaDao.getNextPK();
    }
    
    @Override
    public List<Politica> getPoliticas() throws Exception {
        List<Politica> politicas = new ArrayList<Politica>();
        PoliticaDao politicaDao = (PoliticaDao) ServiceFinder.findBean("PoliticaDao");
        List<Mtpolitica> lista = politicaDao.getMtpoliticas();
        for(Mtpolitica mtpolitica : lista) {
            Politica politica = new Politica();
            BeanUtils.copyProperties(politica, mtpolitica);
            politicas.add(politica);
        }
        return politicas;
    }

    @Override
    public void saveOrUpdate(Politica politica) throws Exception {
        Mtpolitica mtpolitica = new Mtpolitica();
        BeanUtils.copyProperties(mtpolitica, politica);
        PoliticaDao politicaDao = (PoliticaDao) ServiceFinder.findBean("PoliticaDao");
        politicaDao.saveOrUpdate(mtpolitica);
    }

  
    
}
