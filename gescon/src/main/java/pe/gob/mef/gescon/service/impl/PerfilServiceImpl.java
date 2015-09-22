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
import pe.gob.mef.gescon.hibernate.dao.PerfilDao;

//import pe.gob.mef.gescon.hibernate.dao.PoliticaDao;
import pe.gob.mef.gescon.hibernate.domain.Mtperfil;

//import pe.gob.mef.gescon.hibernate.domain.Mtpolitica;
import pe.gob.mef.gescon.service.PerfilService;

//import pe.gob.mef.gescon.service.PoliticaService;
import pe.gob.mef.gescon.util.ServiceFinder;
import pe.gob.mef.gescon.web.bean.Perfil;

//import pe.gob.mef.gescon.web.bean.Politica;

/**
 *
 * @author JJacobo
 */
@Repository(value = "PerfilService")
public class PerfilServiceImpl implements PerfilService{

    @Override
    public BigDecimal getNextPK() throws Exception {
        PerfilDao perfilDao = (PerfilDao) ServiceFinder.findBean("PerfilDao");
        return perfilDao.getNextPK();
    }
    
    @Override
    public List<Perfil> getPerfils() throws Exception {
        List<Perfil> perfils = new ArrayList<Perfil>();
        PerfilDao perfilDao = (PerfilDao) ServiceFinder.findBean("PerfilDao");
        List<Mtperfil> lista = perfilDao.getMtperfils();
        for(Mtperfil mtperfil : lista) {
            Perfil perfil = new Perfil();
            BeanUtils.copyProperties(perfil, mtperfil);
            perfils.add(perfil);
        }
        return perfils;
    }

    @Override
    public void saveOrUpdate(Perfil perfil) throws Exception {
        Mtperfil mtperfil = new Mtperfil();
        BeanUtils.copyProperties(mtperfil, perfil);
        PerfilDao perfilDao = (PerfilDao) ServiceFinder.findBean("PerfilDao");
        perfilDao.saveOrUpdate(mtperfil);
    }

  
    
}
