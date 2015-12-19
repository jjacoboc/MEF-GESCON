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
import pe.gob.mef.gescon.hibernate.dao.AsignacionDao;
import pe.gob.mef.gescon.hibernate.dao.RangoDao;
import pe.gob.mef.gescon.hibernate.domain.Mtrango;
import pe.gob.mef.gescon.hibernate.domain.Mtuser;
import pe.gob.mef.gescon.service.RangoService;
import pe.gob.mef.gescon.util.ServiceFinder;
import pe.gob.mef.gescon.web.bean.Consulta;
import pe.gob.mef.gescon.web.bean.MaestroDetalle;
import pe.gob.mef.gescon.web.bean.Rango;
import pe.gob.mef.gescon.web.bean.User;

/**
 *
 * @author JJacobo
 */
@Repository(value = "RangoService")
public class RangoServiceImpl implements RangoService{

    @Override
    public BigDecimal getNextPK() throws Exception {
        RangoDao rangoDao = (RangoDao) ServiceFinder.findBean("RangoDao");
        return rangoDao.getNextPK();
    }
    
    @Override
    public List<Rango> getRangos() throws Exception {
        List<Rango> rangos = new ArrayList<Rango>();
        RangoDao rangoDao = (RangoDao) ServiceFinder.findBean("RangoDao");
        List<Mtrango> lista = rangoDao.getMtrangos();
        for(Mtrango mtrango : lista) {
            Rango rango = new Rango();
            BeanUtils.copyProperties(rango, mtrango);
            rangos.add(rango);
        }
        return rangos;
    }
    
    @Override
    public List<MaestroDetalle> getTipoRangoByMaestro(BigDecimal maestroid) {
        List<MaestroDetalle> lista = new ArrayList<MaestroDetalle>();
        try {
            RangoDao rangoDao = (RangoDao) ServiceFinder.findBean("RangoDao");
            List<HashMap> maestrodetalle = rangoDao.getTipoRangoByMaestro(maestroid);
            if (!CollectionUtils.isEmpty(maestrodetalle)) {
                for (HashMap map : maestrodetalle) {
                    MaestroDetalle md = new MaestroDetalle();
                    md.setNdetalleid((BigDecimal) map.get("ID"));
                    md.setVnombre((String) map.get("NOMBRE"));
                    lista.add(md);
                }
            }
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
        return lista;
    }
    
    @Override
    public List<Rango> getRangosByTipo(BigDecimal id) throws Exception {
        List<Rango> rangos = new ArrayList<Rango>();
        RangoDao rangoDao = (RangoDao) ServiceFinder.findBean("RangoDao");
        List<Mtrango> lista = rangoDao.getMtrangosByTipo(id);
        for(Mtrango mtrango : lista) {
            Rango rango = new Rango();
            BeanUtils.copyProperties(rango, mtrango);
            rangos.add(rango);
        }
        return rangos;
    }

    @Override
    public List<Rango> getRangosActivosByTipo(BigDecimal id) throws Exception {
        List<Rango> rangos = new ArrayList<Rango>();
        RangoDao rangoDao = (RangoDao) ServiceFinder.findBean("RangoDao");
        List<Mtrango> lista = rangoDao.getMtrangosActivosByTipo(id);
        for(Mtrango mtrango : lista) {
            Rango rango = new Rango();
            BeanUtils.copyProperties(rango, mtrango);
            rangos.add(rango);
        }
        return rangos;
    }
    
    @Override
    public void saveOrUpdate(Rango rango) throws Exception {
        Mtrango mtrango = new Mtrango();
        BeanUtils.copyProperties(mtrango, rango);
        RangoDao rangoDao = (RangoDao) ServiceFinder.findBean("RangoDao");
        rangoDao.saveOrUpdate(mtrango);
    }
}
