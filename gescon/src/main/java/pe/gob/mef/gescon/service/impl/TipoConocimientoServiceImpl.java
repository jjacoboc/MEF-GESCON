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
import pe.gob.mef.gescon.hibernate.dao.TipoConocimientoDao;
import pe.gob.mef.gescon.hibernate.domain.MttipoConocimiento;
import pe.gob.mef.gescon.service.TipoConocimientoService;
import pe.gob.mef.gescon.util.ServiceFinder;
import pe.gob.mef.gescon.web.bean.TipoConocimiento;

/**
 *
 * @author JJacobo
 */
@Repository(value = "TipoConocimientoService")
public class TipoConocimientoServiceImpl implements TipoConocimientoService{

    @Override
    public List<TipoConocimiento> getTipoConocimientos() throws Exception {
        List<TipoConocimiento> tipoConocimientos = new ArrayList<TipoConocimiento>();
        TipoConocimientoDao tipoConocimientoDao = (TipoConocimientoDao) ServiceFinder.findBean("TipoConocimientoDao");
        List<MttipoConocimiento> lista = tipoConocimientoDao.getMttipoConocimientos();
        for(MttipoConocimiento mttipoConocimiento : lista) {
            TipoConocimiento tipoConocimiento = new TipoConocimiento();
            BeanUtils.copyProperties(tipoConocimiento, mttipoConocimiento);
            tipoConocimientos.add(tipoConocimiento);
        }
        return tipoConocimientos;
    }
    
}
