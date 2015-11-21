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
    public void saveOrUpdate(Conocimiento conocimiento) throws Exception {
        Tconocimiento tconocimiento = new Tconocimiento();
        BeanUtils.copyProperties(tconocimiento, conocimiento);
        ContenidoDao contenidoDao = (ContenidoDao) ServiceFinder.findBean("ContenidoDao");
        contenidoDao.saveOrUpdate(tconocimiento);
    }


}
