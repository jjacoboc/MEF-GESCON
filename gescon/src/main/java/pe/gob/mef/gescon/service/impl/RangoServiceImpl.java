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
import pe.gob.mef.gescon.hibernate.dao.RangoDao;
import pe.gob.mef.gescon.hibernate.domain.Mtrango;
import pe.gob.mef.gescon.service.RangoService;
import pe.gob.mef.gescon.util.ServiceFinder;
import pe.gob.mef.gescon.web.bean.Rango;

/**
 *
 * @author JJacobo
 */
@Repository(value = "RangoService")
public class RangoServiceImpl implements RangoService{

    @Override
    public List<Rango> getRangos() throws Exception {
        List<Rango> rangos = new ArrayList<Rango>();
        RangoDao rangoDao = (RangoDao) ServiceFinder.findBean("RangoDao");
        List<Mtrango> lista = rangoDao.getMtrangos();
        for(Mtrango mtrango : lista) {
            Rango rango = new Rango();
            BeanUtils.copyProperties(rango, mtrango);
            rangos.add(rango);
        }
        return rangos;
    }
}
