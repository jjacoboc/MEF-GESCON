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
import pe.gob.mef.gescon.hibernate.dao.CalificacionBaseLegalDao;
import pe.gob.mef.gescon.hibernate.domain.TcalificacionBaselegal;
import pe.gob.mef.gescon.service.CalificacionBaseLegalService;
import pe.gob.mef.gescon.util.ServiceFinder;
import pe.gob.mef.gescon.web.bean.CalificacionBaselegal;

/**
 *
 * @author JJacobo
 */
@Repository(value = "CalificacionBaseLegalService")
public class CalificacionBaseLegalServiceImpl implements CalificacionBaseLegalService{

    @Override
    public BigDecimal getNextPK() throws Exception {
        CalificacionBaseLegalDao calificacionBaseLegalDao = (CalificacionBaseLegalDao) ServiceFinder.findBean("CalificacionBaseLegalDao");
        return calificacionBaseLegalDao.getNextPK();
    }

    @Override
    public CalificacionBaselegal getCalificacionById(BigDecimal idcalificacion) throws Exception {
        CalificacionBaseLegalDao calificacionBaseLegalDao = (CalificacionBaseLegalDao) ServiceFinder.findBean("CalificacionBaseLegalDao");
        TcalificacionBaselegal tcalificacion = calificacionBaseLegalDao.getTcalificacionById(idcalificacion);
        CalificacionBaselegal calificacion = new CalificacionBaselegal();
        BeanUtils.copyProperties(calificacion, tcalificacion);
        return calificacion;
    }

    @Override
    public List<CalificacionBaselegal> getCalificaciones() throws Exception {
        List<CalificacionBaselegal> calificaciones = new ArrayList<CalificacionBaselegal>();
        CalificacionBaseLegalDao calificacionBaseLegalDao = (CalificacionBaseLegalDao) ServiceFinder.findBean("CalificacionBaseLegalDao");
        List<TcalificacionBaselegal> lista = calificacionBaseLegalDao.getTcalificaciones();
        for (TcalificacionBaselegal tcalificacion : lista) {
            CalificacionBaselegal calificacion = new CalificacionBaselegal();
            BeanUtils.copyProperties(calificacion, tcalificacion);
            calificaciones.add(calificacion);
        }
        return calificaciones;
    }

    @Override
    public List<CalificacionBaselegal> getCalificacionesByConocimiento(BigDecimal idconocimiento) throws Exception {
        List<CalificacionBaselegal> calificaciones = new ArrayList<CalificacionBaselegal>();
        CalificacionBaseLegalDao calificacionBaseLegalDao = (CalificacionBaseLegalDao) ServiceFinder.findBean("CalificacionBaseLegalDao");
        List<TcalificacionBaselegal> lista = calificacionBaseLegalDao.getTcalificacionesByTconocimiento(idconocimiento);
        for (TcalificacionBaselegal tcalificacion : lista) {
            CalificacionBaselegal calificacion = new CalificacionBaselegal();
            BeanUtils.copyProperties(calificacion, tcalificacion);
            calificaciones.add(calificacion);
        }
        return calificaciones;
    }

    @Override
    public void saveOrUpdate(CalificacionBaselegal calificacion) throws Exception {
        TcalificacionBaselegal tcalificacion = new TcalificacionBaselegal();
        BeanUtils.copyProperties(tcalificacion, calificacion);
        CalificacionBaseLegalDao calificacionBaseLegalDao = (CalificacionBaseLegalDao) ServiceFinder.findBean("CalificacionBaseLegalDao");
        calificacionBaseLegalDao.saveOrUpdate(tcalificacion);
    }

    @Override
    public void delete(BigDecimal idcalificacion) throws Exception {
        CalificacionBaseLegalDao calificacionBaseLegalDao = (CalificacionBaseLegalDao) ServiceFinder.findBean("CalificacionBaseLegalDao");
        calificacionBaseLegalDao.delete(idcalificacion);
    }    
}
