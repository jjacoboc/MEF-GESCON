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
import pe.gob.mef.gescon.hibernate.dao.AlertaDao;
import pe.gob.mef.gescon.hibernate.domain.Mtalerta;
import pe.gob.mef.gescon.service.AlertaService;
import pe.gob.mef.gescon.util.ServiceFinder;
import pe.gob.mef.gescon.web.bean.Alerta;

/**
 *
 * @author JJacobo
 */
@Repository(value = "AlertaService")
public class AlertaServiceImpl implements AlertaService{

    @Override
    public BigDecimal getNextPK() throws Exception {
        AlertaDao alertaDao = (AlertaDao) ServiceFinder.findBean("AlertaDao");
        return alertaDao.getNextPK();
    }
    
    @Override
    public List<Alerta> getAlertas() throws Exception {
        List<Alerta> alertas = new ArrayList<Alerta>();
        AlertaDao alertaDao = (AlertaDao) ServiceFinder.findBean("AlertaDao");
        List<Mtalerta> lista = alertaDao.getMtalertas();
        for(Mtalerta mtalerta : lista) {
            Alerta alerta = new Alerta();
            BeanUtils.copyProperties(alerta, mtalerta);
            alertas.add(alerta);
        }
        return alertas;
    }

    @Override
    public void saveOrUpdate(Alerta alerta) throws Exception {
        Mtalerta mtalerta = new Mtalerta();
        BeanUtils.copyProperties(mtalerta, alerta);
        AlertaDao alertaDao = (AlertaDao) ServiceFinder.findBean("AlertaDao");
        alertaDao.saveOrUpdate(mtalerta);
    }
    
}
