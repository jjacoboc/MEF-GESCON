/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.gob.mef.gescon.service.impl;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.stereotype.Repository;
import pe.gob.mef.gescon.hibernate.dao.EstadoBaseLegalDao;
import pe.gob.mef.gescon.hibernate.domain.MtestadoBaselegal;
import pe.gob.mef.gescon.service.EstadoBaseLegalService;
import pe.gob.mef.gescon.util.ServiceFinder;
import pe.gob.mef.gescon.web.bean.EstadoBaselegal;

/**
 *
 * @author JJacobo
 */
@Repository(value = "EstadoBaseLegalService")
public class EstadoBaseLegalServiceImpl implements EstadoBaseLegalService{

    @Override
    public List<EstadoBaselegal> getEstadosBaselegal() throws Exception {
        List<EstadoBaselegal> estadosBaselegal = new ArrayList<EstadoBaselegal>();
        EstadoBaseLegalDao estadoBaseLegalDao = (EstadoBaseLegalDao) ServiceFinder.findBean("EstadoBaseLegalDao");
        List<MtestadoBaselegal> lista = estadoBaseLegalDao.getMtestadosBaselegal();
        for(MtestadoBaselegal mtestadoBaselegal : lista) {
            EstadoBaselegal estadoBaselegal = new EstadoBaselegal();
            BeanUtils.copyProperties(estadoBaselegal, mtestadoBaselegal);
            estadosBaselegal.add(estadoBaselegal);
        }
        return estadosBaselegal;
    }

    @Override
    public List<EstadoBaselegal> getEstadosBaselegalToLink() throws Exception {
        List<EstadoBaselegal> estadosBaselegal = new ArrayList<EstadoBaselegal>();
        EstadoBaseLegalDao estadoBaseLegalDao = (EstadoBaseLegalDao) ServiceFinder.findBean("EstadoBaseLegalDao");
        List<MtestadoBaselegal> lista = estadoBaseLegalDao.getMtestadosBaselegalToLink();
        for(MtestadoBaselegal mtestadoBaselegal : lista) {
            EstadoBaselegal estadoBaselegal = new EstadoBaselegal();
            BeanUtils.copyProperties(estadoBaselegal, mtestadoBaselegal);
            estadosBaselegal.add(estadoBaselegal);
        }
        return estadosBaselegal;
    }
    
}
