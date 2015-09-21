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
import pe.gob.mef.gescon.hibernate.dao.ParametroDao;
import pe.gob.mef.gescon.hibernate.domain.Mtparametro;
import pe.gob.mef.gescon.service.ParametroService;
import pe.gob.mef.gescon.util.ServiceFinder;
import pe.gob.mef.gescon.web.bean.Parametro;

/**
 *
 * @author JJacobo
 */
@Repository(value = "ParametroService")
public class ParametroServiceImpl implements ParametroService{

    @Override
    public BigDecimal getNextPK() throws Exception {
        ParametroDao parametroDao = (ParametroDao) ServiceFinder.findBean("ParametroDao");
        return parametroDao.getNextPK();
    }
    
    @Override
    public List<Parametro> getParametros() throws Exception {
        List<Parametro> parametros = new ArrayList<Parametro>();
        ParametroDao parametroDao = (ParametroDao) ServiceFinder.findBean("ParametroDao");
        List<Mtparametro> lista = parametroDao.getMtparametros();
        for(Mtparametro mtparametro : lista) {
            Parametro parametro = new Parametro();
            BeanUtils.copyProperties(parametro, mtparametro);
            parametros.add(parametro);
        }
        return parametros;
    }

    @Override
    public void saveOrUpdate(Parametro parametro) throws Exception {
        Mtparametro mtparametro = new Mtparametro();
        BeanUtils.copyProperties(mtparametro, parametro);
        ParametroDao parametroDao = (ParametroDao) ServiceFinder.findBean("ParametroDao");
        parametroDao.saveOrUpdate(mtparametro);
    }
    
}
