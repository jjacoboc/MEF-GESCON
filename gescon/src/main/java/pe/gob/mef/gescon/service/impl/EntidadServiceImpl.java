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
import pe.gob.mef.gescon.hibernate.dao.EntidadDao;
import pe.gob.mef.gescon.hibernate.domain.Mtentidad;
import pe.gob.mef.gescon.service.EntidadService;
import pe.gob.mef.gescon.util.ServiceFinder;
import pe.gob.mef.gescon.web.bean.Entidad;

/**
 *
 * @author JJacobo
 */
@Repository(value = "EntidadService")
public class EntidadServiceImpl implements EntidadService{

    @Override
    public BigDecimal getNextPK() throws Exception {
        EntidadDao entidadDao = (EntidadDao) ServiceFinder.findBean("EntidadDao");
        return entidadDao.getNextPK();
    }
    
    @Override
    public List<Entidad> getEntidades() throws Exception {
        List<Entidad> entidades = new ArrayList<Entidad>();
        EntidadDao entidadDao = (EntidadDao) ServiceFinder.findBean("EntidadDao");
        List<Mtentidad> lista = entidadDao.getMtentidades();
        for(Mtentidad mtentidad : lista) {
            Entidad entidad = new Entidad();
            BeanUtils.copyProperties(entidad, mtentidad);
            entidades.add(entidad);
        }
        return entidades;
    }

    @Override
    public void saveOrUpdate(Entidad entidad) throws Exception {
        Mtentidad mtentidad = new Mtentidad();
        BeanUtils.copyProperties(mtentidad, entidad);
        EntidadDao entidadDao = (EntidadDao) ServiceFinder.findBean("EntidadDao");
        entidadDao.saveOrUpdate(mtentidad);
    }
    
}
