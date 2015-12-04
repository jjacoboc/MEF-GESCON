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
import pe.gob.mef.gescon.hibernate.dao.ArchivoConocimientoDao;
import pe.gob.mef.gescon.hibernate.dao.ArchivoDao;
import pe.gob.mef.gescon.hibernate.domain.Tarchivo;
import pe.gob.mef.gescon.hibernate.domain.TarchivoConocimiento;
import pe.gob.mef.gescon.hibernate.domain.Tbaselegal;
import pe.gob.mef.gescon.hibernate.domain.Tconocimiento;
import pe.gob.mef.gescon.service.ArchivoConocimientoService;
import pe.gob.mef.gescon.service.ArchivoService;
import pe.gob.mef.gescon.util.ServiceFinder;
import pe.gob.mef.gescon.web.bean.Archivo;
import pe.gob.mef.gescon.web.bean.ArchivoConocimiento;
import pe.gob.mef.gescon.web.bean.BaseLegal;
import pe.gob.mef.gescon.web.bean.Conocimiento;

/**
 *
 * @author JJacobo
 */
@Repository(value = "ArchivoConocimientoService")
public class ArchivoConocimientoServiceImpl implements ArchivoConocimientoService{

    @Override
    public BigDecimal getNextPK() throws Exception {
        ArchivoConocimientoDao archivoconocimientoDao = (ArchivoConocimientoDao) ServiceFinder.findBean("ArchivoConocimientoDao");
        return archivoconocimientoDao.getNextPK();
    }

    @Override
    public List<ArchivoConocimiento> getArchivosByConocimiento(BigDecimal nconocimientoid) throws Exception {
        List<ArchivoConocimiento> archivos = new ArrayList<ArchivoConocimiento>();
        ArchivoConocimientoDao archivoconocimientoDao = (ArchivoConocimientoDao) ServiceFinder.findBean("ArchivoConocimientoDao");
        List<TarchivoConocimiento> lista = archivoconocimientoDao.getTarchivosByTconocimiento(nconocimientoid);
        for(TarchivoConocimiento tarchivoconocimiento : lista) {
            ArchivoConocimiento archivoconocimiento = new ArchivoConocimiento();
            BeanUtils.copyProperties(archivoconocimiento, tarchivoconocimiento);
            archivos.add(archivoconocimiento);
        }
        return archivos;
    }
    
    @Override
    public ArchivoConocimiento getLastArchivoByConocimiento(Conocimiento conocimiento) throws Exception {
        ArchivoConocimiento archivoconocimiento = new ArchivoConocimiento();
        Tconocimiento tconocimiento = new Tconocimiento();
        BeanUtils.copyProperties(tconocimiento, conocimiento);
        ArchivoConocimientoDao archivoconocimientoDao = (ArchivoConocimientoDao) ServiceFinder.findBean("ArchivoConocimientoDao");
        TarchivoConocimiento tarchivoconocimiento = archivoconocimientoDao.getLastTarchivoByTconocimiento(tconocimiento);
        if(tarchivoconocimiento != null) {
            BeanUtils.copyProperties(archivoconocimiento, tarchivoconocimiento);
        } else {
            archivoconocimiento = null;
        }
        return archivoconocimiento;
    }

    @Override
    public void saveOrUpdate(ArchivoConocimiento archivoconocimiento) throws Exception {
        TarchivoConocimiento tarchivoconocimiento = new TarchivoConocimiento();
        BeanUtils.copyProperties(tarchivoconocimiento, archivoconocimiento);
        ArchivoConocimientoDao archivoconocimientoDao = (ArchivoConocimientoDao) ServiceFinder.findBean("ArchivoConocimientoDao");
        archivoconocimientoDao.saveOrUpdate(tarchivoconocimiento);
    }

    
    
}
