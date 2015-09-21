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
import pe.gob.mef.gescon.hibernate.dao.MaestroDao;
import pe.gob.mef.gescon.hibernate.domain.Mtmaestro;
import pe.gob.mef.gescon.service.MaestroService;
import pe.gob.mef.gescon.util.ServiceFinder;
import pe.gob.mef.gescon.web.bean.Maestro;

/**
 *
 * @author JJacobo
 */
@Repository(value = "MaestroService")
public class MaestroServiceImpl implements MaestroService{

    @Override
    public BigDecimal getNextPK() throws Exception {
        MaestroDao maestroDao = (MaestroDao) ServiceFinder.findBean("MaestroDao");
        return maestroDao.getNextPK();
    }
    
    @Override
    public List<Maestro> getMaestros() throws Exception {
        List<Maestro> maestros = new ArrayList<Maestro>();
        MaestroDao maestroDao = (MaestroDao) ServiceFinder.findBean("MaestroDao");
        List<Mtmaestro> lista = maestroDao.getMtmaestros();
        for(Mtmaestro mtmaestro : lista) {
            Maestro maestro = new Maestro();
            BeanUtils.copyProperties(maestro, mtmaestro);
            maestros.add(maestro);
        }
        return maestros;
    }

    @Override
    public void saveOrUpdate(Maestro maestro) throws Exception {
        Mtmaestro mtmaestro = new Mtmaestro();
        BeanUtils.copyProperties(mtmaestro, maestro);
        MaestroDao maestroDao = (MaestroDao) ServiceFinder.findBean("MaestroDao");
        maestroDao.saveOrUpdate(mtmaestro);
    }
    
}
