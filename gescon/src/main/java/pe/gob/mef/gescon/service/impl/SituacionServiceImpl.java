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
import pe.gob.mef.gescon.hibernate.dao.SituacionDao;
import pe.gob.mef.gescon.hibernate.domain.Mtsituacion;
import pe.gob.mef.gescon.service.SituacionService;
import pe.gob.mef.gescon.util.ServiceFinder;
import pe.gob.mef.gescon.web.bean.Situacion;

/**
 *
 * @author JJacobo
 */
@Repository(value = "SituacionService")
public class SituacionServiceImpl implements SituacionService{

    @Override
    public List<Situacion> getSituacions() throws Exception {
        List<Situacion> situacions = new ArrayList<Situacion>();
        SituacionDao situacionDao = (SituacionDao) ServiceFinder.findBean("SituacionDao");
        List<Mtsituacion> lista = situacionDao.getMtsituacions();
        for(Mtsituacion mtsituacion : lista) {
            Situacion situacion = new Situacion();
            BeanUtils.copyProperties(situacion, mtsituacion);
            situacions.add(situacion);
        }
        return situacions;
    }
}
