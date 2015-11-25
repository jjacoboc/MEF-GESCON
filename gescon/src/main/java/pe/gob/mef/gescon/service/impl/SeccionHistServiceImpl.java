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
import pe.gob.mef.gescon.hibernate.dao.SeccionHistDao;
import pe.gob.mef.gescon.hibernate.domain.TseccionHist;
import pe.gob.mef.gescon.service.SeccionHistService;
import pe.gob.mef.gescon.util.ServiceFinder;
import pe.gob.mef.gescon.web.bean.SeccionHist;

/**
 *
 * @author JJacobo
 */
@Repository(value = "SeccionHistService")
public class SeccionHistServiceImpl implements SeccionHistService {

    @Override
    public BigDecimal getNextPK() throws Exception {
        SeccionHistDao seccionHistDao = (SeccionHistDao) ServiceFinder.findBean("SeccionHistDao");
        return seccionHistDao.getNextPK();
    }

    @Override
    public SeccionHist getSeccionHistById(BigDecimal idseccionh) throws Exception {
        SeccionHistDao seccionHistDao = (SeccionHistDao) ServiceFinder.findBean("SeccionHistDao");
        TseccionHist tseccionHist = seccionHistDao.getTseccionHistById(idseccionh);
        SeccionHist seccionHist = new SeccionHist();
        BeanUtils.copyProperties(seccionHist, tseccionHist);
        return seccionHist;
    }

    @Override
    public List<SeccionHist> getSeccionHists() throws Exception {
        List<SeccionHist> secciones = new ArrayList<SeccionHist>();
        SeccionHistDao seccionHistDao = (SeccionHistDao) ServiceFinder.findBean("SeccionHistDao");
        List<TseccionHist> lista = seccionHistDao.getTseccionHists();
        for (TseccionHist tseccionHist : lista) {
            SeccionHist seccionHist = new SeccionHist();
            BeanUtils.copyProperties(seccionHist, tseccionHist);
            secciones.add(seccionHist);
        }
        return secciones;
    }

    @Override
    public List<SeccionHist> getSeccionHistsByHistorial(BigDecimal idhistorial) throws Exception {
        List<SeccionHist> secciones = new ArrayList<SeccionHist>();
        SeccionHistDao seccionHistDao = (SeccionHistDao) ServiceFinder.findBean("SeccionHistDao");
        List<TseccionHist> lista = seccionHistDao.getTseccionHistsByThistorial(idhistorial);
        for (TseccionHist tseccionHist : lista) {
            SeccionHist seccionHist = new SeccionHist();
            BeanUtils.copyProperties(seccionHist, tseccionHist);
            secciones.add(seccionHist);
        }
        return secciones;
    }

    @Override
    public void saveOrUpdate(SeccionHist seccionHist) throws Exception {
        TseccionHist tseccionHist = new TseccionHist();
        BeanUtils.copyProperties(tseccionHist, seccionHist);
        SeccionHistDao seccionHistDao = (SeccionHistDao) ServiceFinder.findBean("SeccionHistDao");
        seccionHistDao.saveOrUpdate(tseccionHist);
    }
    
}
