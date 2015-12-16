/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.gob.mef.gescon.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.stereotype.Repository;
import pe.gob.mef.gescon.hibernate.dao.PoliticaPerfilDao;
import pe.gob.mef.gescon.hibernate.domain.TpoliticaPerfil;
import pe.gob.mef.gescon.service.PoliticaPerfilService;
import pe.gob.mef.gescon.util.ServiceFinder;
import pe.gob.mef.gescon.web.bean.Politica;
import pe.gob.mef.gescon.web.bean.PoliticaPerfil;

//import pe.gob.mef.gescon.web.bean.Politica;
/**
 *
 * @author JJacobo
 */
@Repository(value = "PoliticaPerfilService")
public class PoliticaPerfilServiceImpl implements PoliticaPerfilService {

    @Override
    public List<PoliticaPerfil> getPoliticaperfil() throws Exception {
        List<PoliticaPerfil> politicaperfiles = new ArrayList<PoliticaPerfil>();
        PoliticaPerfilDao perfilDao = (PoliticaPerfilDao) ServiceFinder.findBean("PoliticaPerfilDao");
        List<TpoliticaPerfil> lista = perfilDao.getTpoliticaperfil();
        for (TpoliticaPerfil tpoliticaperfil : lista) {
            PoliticaPerfil politica_perfil = new PoliticaPerfil();
            BeanUtils.copyProperties(politica_perfil, tpoliticaperfil);
            politicaperfiles.add(politica_perfil);
        }
        return politicaperfiles;
    }

    @Override
    public void saveOrUpdate(PoliticaPerfil politica_perfil) throws Exception {
        TpoliticaPerfil tpoliticaperfil = new TpoliticaPerfil();
        BeanUtils.copyProperties(tpoliticaperfil, politica_perfil);
        PoliticaPerfilDao politicaperfilDao = (PoliticaPerfilDao) ServiceFinder.findBean("PoliticaPerfilDao");
        politicaperfilDao.saveOrUpdate(tpoliticaperfil);
    }

    @Override
    public HashMap obtenerPoliticasByPerfil(final BigDecimal perfilid) throws Exception {
        HashMap politicas = new HashMap();
        try {
            PoliticaPerfilDao politicaperfilDao = (PoliticaPerfilDao) ServiceFinder.findBean("PoliticaPerfilDao");
            List<HashMap> lista = politicaperfilDao.obtenerListaPoliticas(perfilid);
            for(HashMap map : lista) {
                politicas.put(((BigDecimal) map.get("ID")).toString(), true);
            }
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
        return politicas;

    }
    
    @Override
    public List<Politica> obtenerListaPoliticas(final BigDecimal perfilid) throws Exception {
        List<Politica> list = new ArrayList<Politica>();
        try {
            PoliticaPerfilDao politicaperfilDao = (PoliticaPerfilDao) ServiceFinder.findBean("PoliticaPerfilDao");
            List<HashMap> lista = politicaperfilDao.obtenerListaPoliticas(perfilid);
            for (HashMap bean : lista) {
                Politica politica = new Politica();
                politica.setNpoliticaid((BigDecimal) bean.get("ID"));
                politica.setVnombre((String) bean.get("NOMBRE"));
                list.add(politica);
            }
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public List<Politica> obtenerListaPoliticasDisp(final BigDecimal perfilid) throws Exception {
        List<Politica> list = new ArrayList<Politica>();
        try {
            PoliticaPerfilDao politicaperfilDao = (PoliticaPerfilDao) ServiceFinder.findBean("PoliticaPerfilDao");
            List<HashMap> lista = politicaperfilDao.obtenerListaPoliticasDisp(perfilid);
            for (HashMap bean : lista) {
                Politica politica = new Politica();
                politica.setNpoliticaid((BigDecimal) bean.get("POLITICA"));
                politica.setVnombre((String) bean.get("DES"));
                list.add(politica);
            }
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public void delete(BigDecimal perfilid) throws Exception {
        PoliticaPerfilDao politicaperfilDao = (PoliticaPerfilDao) ServiceFinder.findBean("PoliticaPerfilDao");
        politicaperfilDao.delete(perfilid);
    }

}
