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
import pe.gob.mef.gescon.hibernate.dao.CalificacionPreguntaDao;
import pe.gob.mef.gescon.hibernate.domain.TcalificacionPregunta;
import pe.gob.mef.gescon.service.CalificacionPreguntaService;
import pe.gob.mef.gescon.util.ServiceFinder;
import pe.gob.mef.gescon.web.bean.CalificacionPregunta;

/**
 *
 * @author JJacobo
 */
@Repository(value = "CalificacionPreguntaService")
public class CalificacionPreguntaServiceImpl implements CalificacionPreguntaService{

    @Override
    public BigDecimal getNextPK() throws Exception {
        CalificacionPreguntaDao calificacionPreguntaDao = (CalificacionPreguntaDao) ServiceFinder.findBean("CalificacionPreguntaDao");
        return calificacionPreguntaDao.getNextPK();
    }

    @Override
    public CalificacionPregunta getCalificacionById(BigDecimal idcalificacion) throws Exception {
        CalificacionPreguntaDao calificacionPreguntaDao = (CalificacionPreguntaDao) ServiceFinder.findBean("CalificacionPreguntaDao");
        TcalificacionPregunta tcalificacion = calificacionPreguntaDao.getTcalificacionById(idcalificacion);
        CalificacionPregunta calificacion = new CalificacionPregunta();
        BeanUtils.copyProperties(calificacion, tcalificacion);
        return calificacion;
    }

    @Override
    public List<CalificacionPregunta> getCalificaciones() throws Exception {
        List<CalificacionPregunta> calificaciones = new ArrayList<CalificacionPregunta>();
        CalificacionPreguntaDao calificacionPreguntaDao = (CalificacionPreguntaDao) ServiceFinder.findBean("CalificacionPreguntaDao");
        List<TcalificacionPregunta> lista = calificacionPreguntaDao.getTcalificaciones();
        for (TcalificacionPregunta tcalificacion : lista) {
            CalificacionPregunta calificacion = new CalificacionPregunta();
            BeanUtils.copyProperties(calificacion, tcalificacion);
            calificaciones.add(calificacion);
        }
        return calificaciones;
    }

    @Override
    public List<CalificacionPregunta> getCalificacionesByConocimiento(BigDecimal idconocimiento) throws Exception {
        List<CalificacionPregunta> calificaciones = new ArrayList<CalificacionPregunta>();
        CalificacionPreguntaDao calificacionPreguntaDao = (CalificacionPreguntaDao) ServiceFinder.findBean("CalificacionPreguntaDao");
        List<TcalificacionPregunta> lista = calificacionPreguntaDao.getTcalificacionesByTconocimiento(idconocimiento);
        for (TcalificacionPregunta tcalificacion : lista) {
            CalificacionPregunta calificacion = new CalificacionPregunta();
            BeanUtils.copyProperties(calificacion, tcalificacion);
            calificaciones.add(calificacion);
        }
        return calificaciones;
    }

    @Override
    public void saveOrUpdate(CalificacionPregunta calificacion) throws Exception {
        TcalificacionPregunta tcalificacion = new TcalificacionPregunta();
        BeanUtils.copyProperties(tcalificacion, calificacion);
        CalificacionPreguntaDao calificacionPreguntaDao = (CalificacionPreguntaDao) ServiceFinder.findBean("CalificacionPreguntaDao");
        calificacionPreguntaDao.saveOrUpdate(tcalificacion);
    }

    @Override
    public void delete(BigDecimal idcalificacion) throws Exception {
        CalificacionPreguntaDao calificacionPreguntaDao = (CalificacionPreguntaDao) ServiceFinder.findBean("CalificacionPreguntaDao");
        calificacionPreguntaDao.delete(idcalificacion);
    }
    
}
