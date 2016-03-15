/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.gob.mef.gescon.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;
import pe.gob.mef.gescon.hibernate.dao.PerfilDao;
import pe.gob.mef.gescon.hibernate.domain.Mtperfil;
import pe.gob.mef.gescon.hibernate.domain.Mtuser;
import pe.gob.mef.gescon.service.PerfilService;
import pe.gob.mef.gescon.util.ServiceFinder;
import pe.gob.mef.gescon.web.bean.Perfil;
import pe.gob.mef.gescon.web.bean.User;

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
    public List<Perfil> getPerfilsActived() throws Exception {
        List<Perfil> perfils = new ArrayList<Perfil>();
        PerfilDao perfilDao = (PerfilDao) ServiceFinder.findBean("PerfilDao");
        List<Mtperfil> lista = perfilDao.getMtperfilsActived();
        for(Mtperfil mtperfil : lista) {
            Perfil perfil = new Perfil();
            BeanUtils.copyProperties(perfil, mtperfil);
            perfils.add(perfil);
        }
        return perfils;
    }
    
    @Override
    public List<Perfil> getPerfilesByUser(User user) throws Exception {
        List<Perfil> perfiles = new ArrayList<Perfil>();
        try {
            Mtuser mtuser = new Mtuser();
            BeanUtils.copyProperties(mtuser, user);
            PerfilDao perfilDao = (PerfilDao) ServiceFinder.findBean("PerfilDao");            
            List<HashMap> mtperfiles = perfilDao.getMtperfilesByMtuser(mtuser);
            if(!CollectionUtils.isEmpty(mtperfiles)) {
                for(HashMap map : mtperfiles) {
                    Perfil p = new Perfil();
                    p.setNperfilid((BigDecimal) map.get("ID"));
                    p.setVnombre((String) map.get("NOMBRE"));
                    p.setVdescripcion((String) map.get("SUMILLA"));
                    p.setNactivo((BigDecimal) map.get("ACTIVO"));
                    p.setVusuariocreacion((String) map.get("USUARIOCREACION"));
                    p.setDfechacreacion((Date) map.get("FECHACREACION"));
                    p.setVusuariomodificacion((String) map.get("USUARIOMODIFICACION"));
                    p.setDfechamodificacion((Date) map.get("FECHAMODIFICACION"));
                    perfiles.add(p);
                }
            }
        } catch(Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
        return perfiles;
    }

    @Override
    public void saveOrUpdate(Perfil perfil) throws Exception {
        Mtperfil mtperfil = new Mtperfil();
        BeanUtils.copyProperties(mtperfil, perfil);
        PerfilDao perfilDao = (PerfilDao) ServiceFinder.findBean("PerfilDao");
        perfilDao.saveOrUpdate(mtperfil);
    }
}
