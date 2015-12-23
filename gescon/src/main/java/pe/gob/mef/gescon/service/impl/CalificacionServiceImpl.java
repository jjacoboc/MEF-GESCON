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
import pe.gob.mef.gescon.hibernate.dao.CalificacionDao;
import pe.gob.mef.gescon.hibernate.domain.Tcalificacion;
import pe.gob.mef.gescon.service.CalificacionService;
import pe.gob.mef.gescon.util.ServiceFinder;
import pe.gob.mef.gescon.web.bean.Calificacion;

/**
 *
 * @author JJacobo
 */
@Repository(value = "CalificacionService")
public class CalificacionServiceImpl implements CalificacionService{

    @Override
    public BigDecimal getNextPK() throws Exception {
        CalificacionDao calificacionDao = (CalificacionDao) ServiceFinder.findBean("CalificacionDao");
        return calificacionDao.getNextPK();
    }

    @Override
    public Calificacion getCalificacionById(BigDecimal idcalificacion) throws Exception {
        CalificacionDao calificacionDao = (CalificacionDao) ServiceFinder.findBean("CalificacionDao");
        Tcalificacion tcalificacion = calificacionDao.getTcalificacionById(idcalificacion);
        Calificacion calificacion = new Calificacion();
        BeanUtils.copyProperties(calificacion, tcalificacion);
        return calificacion;
    }

    @Override
    public List<Calificacion> getCalificaciones() throws Exception {
        List<Calificacion> calificaciones = new ArrayList<Calificacion>();
        CalificacionDao calificacionDao = (CalificacionDao) ServiceFinder.findBean("CalificacionDao");
        List<Tcalificacion> lista = calificacionDao.getTcalificaciones();
        for (Tcalificacion tcalificacion : lista) {
            Calificacion calificacion = new Calificacion();
            BeanUtils.copyProperties(calificacion, tcalificacion);
            calificaciones.add(calificacion);
        }
        return calificaciones;
    }

    @Override
    public List<Calificacion> getCalificacionesByConocimiento(BigDecimal idconocimiento) throws Exception {
        List<Calificacion> calificaciones = new ArrayList<Calificacion>();
        CalificacionDao calificacionDao = (CalificacionDao) ServiceFinder.findBean("CalificacionDao");
        List<Tcalificacion> lista = calificacionDao.getTcalificacionesByTconocimiento(idconocimiento);
        for (Tcalificacion tcalificacion : lista) {
            Calificacion calificacion = new Calificacion();
            BeanUtils.copyProperties(calificacion, tcalificacion);
            calificaciones.add(calificacion);
        }
        return calificaciones;
    }

    @Override
    public void saveOrUpdate(Calificacion calificacion) throws Exception {
        Tcalificacion tcalificacion = new Tcalificacion();
        BeanUtils.copyProperties(tcalificacion, calificacion);
        CalificacionDao calificacionDao = (CalificacionDao) ServiceFinder.findBean("CalificacionDao");
        calificacionDao.saveOrUpdate(tcalificacion);
    }
    
    @Override
    public void delete(BigDecimal idcalificacion) throws Exception {
        CalificacionDao calificacionDao = (CalificacionDao) ServiceFinder.findBean("CalificacionDao");
        calificacionDao.delete(idcalificacion);
    }
}
