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
import pe.gob.mef.gescon.hibernate.dao.ArchivoHistorialDao;
import pe.gob.mef.gescon.hibernate.domain.TarchivoHist;
import pe.gob.mef.gescon.hibernate.domain.TbaselegalHist;
import pe.gob.mef.gescon.service.ArchivoHistorialService;
import pe.gob.mef.gescon.util.ServiceFinder;
import pe.gob.mef.gescon.web.bean.ArchivoHist;
import pe.gob.mef.gescon.web.bean.BaselegalHist;

/**
 *
 * @author JJacobo
 */
@Repository(value = "ArchivoHistorialService")
public class ArchivoHistorialServiceImpl implements ArchivoHistorialService{

    @Override
    public BigDecimal getNextPK() throws Exception {
        ArchivoHistorialDao archivoDao = (ArchivoHistorialDao) ServiceFinder.findBean("ArchivoHistorialDao");
        return archivoDao.getNextPK();
    }

    @Override
    public List<ArchivoHist> getArchivosHistByBaseLegalHist(BaselegalHist baseLegal) throws Exception {
        List<ArchivoHist> archivos = new ArrayList<ArchivoHist>();
        TbaselegalHist tbaselegal = new TbaselegalHist();
        BeanUtils.copyProperties(tbaselegal, baseLegal);
        ArchivoHistorialDao archivoDao = (ArchivoHistorialDao) ServiceFinder.findBean("ArchivoHistorialDao");
        List<TarchivoHist> lista = archivoDao.getTarchivosHistByTbaselegalHist(tbaselegal);
        for(TarchivoHist tarchivo : lista) {
            ArchivoHist archivo = new ArchivoHist();
            BeanUtils.copyProperties(archivo, tarchivo);
            archivos.add(archivo);
        }
        return archivos;
    }

    @Override
    public ArchivoHist getLastArchivoHistByBaseLegalHist(BaselegalHist baseLegal) throws Exception {
        ArchivoHist archivo = new ArchivoHist();
        TbaselegalHist tbaselegal = new TbaselegalHist();
        BeanUtils.copyProperties(tbaselegal, baseLegal);
        ArchivoHistorialDao archivoDao = (ArchivoHistorialDao) ServiceFinder.findBean("ArchivoHistorialDao");
        TarchivoHist tarchivo = archivoDao.getLastTarchivoHistByTbaselegalHist(tbaselegal);
        if(tarchivo != null) {
            BeanUtils.copyProperties(archivo, tarchivo);
        } else {
            archivo = null;
        }
        return archivo;
    }

    @Override
    public void saveOrUpdate(ArchivoHist archivo) throws Exception {
        TarchivoHist tarchivo = new TarchivoHist();
        BeanUtils.copyProperties(tarchivo, archivo);
        ArchivoHistorialDao archivoDao = (ArchivoHistorialDao) ServiceFinder.findBean("ArchivoHistorialDao");
        archivoDao.saveOrUpdate(tarchivo);
    }
}
