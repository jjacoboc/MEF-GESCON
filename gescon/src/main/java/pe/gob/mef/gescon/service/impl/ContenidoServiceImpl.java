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
import pe.gob.mef.gescon.hibernate.dao.ConocimientoDao;
import pe.gob.mef.gescon.hibernate.dao.ContenidoDao;
import pe.gob.mef.gescon.hibernate.dao.PreguntaDao;
import pe.gob.mef.gescon.hibernate.domain.Tconocimiento;
import pe.gob.mef.gescon.hibernate.domain.Tpregunta;
import pe.gob.mef.gescon.service.ContenidoService;
import pe.gob.mef.gescon.service.PreguntaService;
import pe.gob.mef.gescon.util.ServiceFinder;
import pe.gob.mef.gescon.web.bean.Asignacion;
import pe.gob.mef.gescon.web.bean.Conocimiento;
import pe.gob.mef.gescon.web.bean.Pregunta;

/**
 *
 * @author JJacobo
 */
@Repository(value = "ContenidoService")
public class ContenidoServiceImpl implements ContenidoService {

    @Override
    public BigDecimal getNextPK() throws Exception {
        ContenidoDao contenidoDao = (ContenidoDao) ServiceFinder.findBean("ContenidoDao");
        return contenidoDao.getNextPK();
    }

    @Override
    public List<Conocimiento> getContenidos() throws Exception {
        List<Conocimiento> conocimientos = new ArrayList<Conocimiento>();
        ContenidoDao contenidoDao = (ContenidoDao) ServiceFinder.findBean("ContenidoDao");
        List<Tconocimiento> lista = contenidoDao.getContenidos();
        for (Tconocimiento tconocimiento : lista) {
            Conocimiento conocimiento = new Conocimiento();
            BeanUtils.copyProperties(conocimiento, tconocimiento);
            conocimientos.add(conocimiento);
        }
        return conocimientos;
    }
    
    @Override
    public Conocimiento getContenidoById(BigDecimal tipo,BigDecimal id) throws Exception {
        ContenidoDao contenidoDao = (ContenidoDao) ServiceFinder.findBean("ContenidoDao");
        Tconocimiento tconocimiento = contenidoDao.getTcontenidoById(tipo,id);
        Conocimiento conocimiento = new Conocimiento();
        BeanUtils.copyProperties(conocimiento, tconocimiento);
        return conocimiento;
    }
    
    @Override
    public List<Asignacion> obtenerContenidoxAsig(final BigDecimal contenidoid, final BigDecimal usuarioid,BigDecimal tpoconocimientoid) throws Exception {
        List<Asignacion> asignacions = new ArrayList<Asignacion>();
        ContenidoDao contenidoDao = (ContenidoDao) ServiceFinder.findBean("ContenidoDao");
        List<HashMap> lista = contenidoDao.obtenerContenidoxAsig(contenidoid,usuarioid,tpoconocimientoid);
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
    public void saveOrUpdate(Conocimiento conocimiento) throws Exception {
        Tconocimiento tconocimiento = new Tconocimiento();
        BeanUtils.copyProperties(tconocimiento, conocimiento);
        ContenidoDao contenidoDao = (ContenidoDao) ServiceFinder.findBean("ContenidoDao");
        contenidoDao.saveOrUpdate(tconocimiento);
    }


}
