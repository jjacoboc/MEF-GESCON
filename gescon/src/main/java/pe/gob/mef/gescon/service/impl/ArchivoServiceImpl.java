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
import pe.gob.mef.gescon.hibernate.dao.ArchivoDao;
import pe.gob.mef.gescon.hibernate.domain.Tarchivo;
import pe.gob.mef.gescon.hibernate.domain.Tbaselegal;
import pe.gob.mef.gescon.service.ArchivoService;
import pe.gob.mef.gescon.util.ServiceFinder;
import pe.gob.mef.gescon.web.bean.Archivo;
import pe.gob.mef.gescon.web.bean.BaseLegal;
import pe.gob.mef.gescon.web.bean.OportunidadMejora;

/**
 *
 * @author JJacobo
 */
@Repository(value = "ArchivoService")
public class ArchivoServiceImpl implements ArchivoService{

    @Override
    public BigDecimal getNextPK() throws Exception {
        ArchivoDao archivoDao = (ArchivoDao) ServiceFinder.findBean("ArchivoDao");
        return archivoDao.getNextPK();
    }

    @Override
    public List<Archivo> getArchivosByBaseLegal(BaseLegal baseLegal) throws Exception {
        List<Archivo> archivos = new ArrayList<Archivo>();
        Tbaselegal tbaselegal = new Tbaselegal();
        BeanUtils.copyProperties(tbaselegal, baseLegal);
        ArchivoDao archivoDao = (ArchivoDao) ServiceFinder.findBean("ArchivoDao");
        List<Tarchivo> lista = archivoDao.getTarchivosByTbaselegal(tbaselegal);
        for(Tarchivo tarchivo : lista) {
            Archivo archivo = new Archivo();
            BeanUtils.copyProperties(archivo, tarchivo);
            archivos.add(archivo);
        }
        return archivos;
    }
    
    @Override
    public Archivo getLastArchivoByBaseLegal(BaseLegal baseLegal) throws Exception {
        Archivo archivo = new Archivo();
        Tbaselegal tbaselegal = new Tbaselegal();
        BeanUtils.copyProperties(tbaselegal, baseLegal);
        ArchivoDao archivoDao = (ArchivoDao) ServiceFinder.findBean("ArchivoDao");
        Tarchivo tarchivo = archivoDao.getLastTarchivoByTbaselegal(tbaselegal);
        if(tarchivo != null) {
            BeanUtils.copyProperties(archivo, tarchivo);
        } else {
            archivo = null;
        }
        return archivo;
    }

    @Override
    public void saveOrUpdate(Archivo archivo) throws Exception {
        Tarchivo tarchivo = new Tarchivo();
        BeanUtils.copyProperties(tarchivo, archivo);
        ArchivoDao archivoDao = (ArchivoDao) ServiceFinder.findBean("ArchivoDao");
        archivoDao.saveOrUpdate(tarchivo);
    }

    
    
}
