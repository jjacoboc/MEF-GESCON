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
import pe.gob.mef.gescon.hibernate.dao.BaseLegalDao;
import pe.gob.mef.gescon.hibernate.domain.Tbaselegal;
import pe.gob.mef.gescon.service.BaseLegalService;
import pe.gob.mef.gescon.util.ServiceFinder;
import pe.gob.mef.gescon.web.bean.Asignacion;
import pe.gob.mef.gescon.web.bean.BaseLegal;

/**
 *
 * @author JJacobo
 */
@Repository(value = "BaseLegalService")
public class BaseLegalServiceImpl implements BaseLegalService{

    @Override
    public BigDecimal getNextPK() throws Exception {
        BaseLegalDao baseLegalDao = (BaseLegalDao) ServiceFinder.findBean("BaseLegalDao");
        return baseLegalDao.getNextPK();
    }

    @Override
    public List<BaseLegal> getBaselegales() throws Exception {
        List<BaseLegal> baseLegales = new ArrayList<BaseLegal>();
        BaseLegalDao baseLegalDao = (BaseLegalDao) ServiceFinder.findBean("BaseLegalDao");
        List<Tbaselegal> lista = baseLegalDao.getTbaselegales();
        for(Tbaselegal tbaselegal : lista) {
            BaseLegal baseLegal = new BaseLegal();
            BeanUtils.copyProperties(baseLegal, tbaselegal);
            baseLegales.add(baseLegal);
        }
        return baseLegales;
    }
    
    @Override
    public List<BaseLegal> getBaselegalesActivedPosted() throws Exception {
        List<BaseLegal> baseLegales = new ArrayList<BaseLegal>();
        BaseLegalDao baseLegalDao = (BaseLegalDao) ServiceFinder.findBean("BaseLegalDao");
        List<Tbaselegal> lista = baseLegalDao.getTbaselegalesActivedPosted();
        for(Tbaselegal tbaselegal : lista) {
            BaseLegal baseLegal = new BaseLegal();
            BeanUtils.copyProperties(baseLegal, tbaselegal);
            baseLegales.add(baseLegal);
        }
        return baseLegales;
    }
    
    @Override
    public BaseLegal getBaselegalById(BigDecimal id) throws Exception {
        BaseLegalDao baseLegalDao = (BaseLegalDao) ServiceFinder.findBean("BaseLegalDao");
        Tbaselegal tbaselegal = baseLegalDao.getTbaselegalById(id);
        BaseLegal baseLegal = new BaseLegal();
        BeanUtils.copyProperties(baseLegal, tbaselegal);
        return baseLegal;
    }
    
    @Override
    public List<BaseLegal> getTbaselegalesLinkedById(final BigDecimal id) throws Exception {
        BaseLegalDao baseLegalDao = (BaseLegalDao) ServiceFinder.findBean("BaseLegalDao");
        List<HashMap> lista = baseLegalDao.getTbaselegalesLinkedById(id);
        List<BaseLegal> baseLegales = new ArrayList<BaseLegal>();
        for(HashMap m : lista) {
            BaseLegal bl = new BaseLegal();
            bl.setNbaselegalid((BigDecimal) m.get("ID"));
            bl.setVnumero((String) m.get("NUMERO"));
            bl.setVnombre((String) m.get("NOMBRE"));
            bl.setVsumilla((String) m.get("SUMILLA"));
            bl.setNcategoriaid((BigDecimal) m.get("IDCATEGORIA"));  
            bl.setNestadoid((BigDecimal) m.get("IDESTADO"));
            bl.setDfechapublicacion((Date) m.get("FECHA"));
            baseLegales.add(bl);
        }
        return baseLegales;
    }
    
    @Override
    public List<BaseLegal> getTbaselegalesNotLinkedById(final BigDecimal id) throws Exception {
        BaseLegalDao baseLegalDao = (BaseLegalDao) ServiceFinder.findBean("BaseLegalDao");
        List<HashMap> lista = baseLegalDao.getTbaselegalesNotLinkedById(id);
        List<BaseLegal> baseLegales = new ArrayList<BaseLegal>();
        for(HashMap m : lista) {
            BaseLegal bl = new BaseLegal();
            bl.setNbaselegalid((BigDecimal) m.get("ID"));
            bl.setVnumero((String) m.get("NUMERO"));
            bl.setVnombre((String) m.get("NOMBRE"));
            bl.setVsumilla((String) m.get("SUMILLA"));
            bl.setNcategoriaid((BigDecimal) m.get("IDCATEGORIA"));
            bl.setNestadoid((BigDecimal) m.get("IDESTADO"));
            bl.setDfechapublicacion((Date) m.get("FECHA"));
            baseLegales.add(bl);
        }
        return baseLegales;
    }
    
    @Override
    public List<Asignacion> obtenerBaseLegalxAsig(final BigDecimal baselegalid, final BigDecimal usuarioid,BigDecimal tpoconocimientoid) throws Exception {
        List<Asignacion> asignacions = new ArrayList<Asignacion>();
        BaseLegalDao baselegalDao = (BaseLegalDao) ServiceFinder.findBean("BaseLegalDao");
        List<HashMap> lista = baselegalDao.obtenerBaseLegalxAsig(baselegalid,usuarioid,tpoconocimientoid);
        for (HashMap bean : lista) {
            Asignacion asignacion = new Asignacion();
            asignacion.setNasignacionid((BigDecimal) bean.get("IDASIGNACION"));
            asignacion.setNtipoconocimientoid((BigDecimal) bean.get("TPOCONOCIMIENTO"));
            asignacion.setNconocimientoid((BigDecimal) bean.get("IDPREGUNTA"));
            asignacion.setNusuarioid((BigDecimal) bean.get("IDUSUARIO"));
            asignacion.setNestadoid((BigDecimal) bean.get("ESTADO"));
            asignacion.setVusuariocreacion((String) bean.get("USUCREA"));
            asignacion.setVusuariomodificacion((String) bean.get("USUMOD"));
            asignacion.setDfechacreacion((Date) bean.get("FECHACREA"));
            asignacion.setDfechamodificacion((Date) bean.get("FECHAMOD"));     
            asignacion.setDfechaasignacion((Date) bean.get("FECHAASIG"));  
            asignacion.setDfechaatencion((Date) bean.get("FECHAATEN"));  
            asignacion.setDfecharecepcion((Date) bean.get("FECHARECEP"));  
            asignacions.add(asignacion);
        }
        return asignacions;
    }

    @Override
    public void saveOrUpdate(BaseLegal baseLegal) throws Exception {
        Tbaselegal tbaselegal = new Tbaselegal();
        BeanUtils.copyProperties(tbaselegal, baseLegal);
        BaseLegalDao baseLegalDao = (BaseLegalDao) ServiceFinder.findBean("BaseLegalDao");
        baseLegalDao.saveOrUpdate(tbaselegal);
    }
    
}
