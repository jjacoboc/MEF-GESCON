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
import pe.gob.mef.gescon.hibernate.dao.MaestroDetalleDao;
import pe.gob.mef.gescon.hibernate.domain.Mtmaestro;
import pe.gob.mef.gescon.hibernate.domain.Tmaestrodetalle;
import pe.gob.mef.gescon.service.MaestroDetalleService;
import pe.gob.mef.gescon.util.ServiceFinder;
import pe.gob.mef.gescon.web.bean.Maestro;
import pe.gob.mef.gescon.web.bean.MaestroDetalle;

/**
 *
 * @author JJacobo
 */
@Repository(value = "MaestroDetalleService")
public class MaestroDetalleServiceImpl implements MaestroDetalleService{

    @Override
    public BigDecimal getNextPK() throws Exception {
        MaestroDetalleDao maestroDetalleDao = (MaestroDetalleDao) ServiceFinder.findBean("MaestroDetalleDao");
        return maestroDetalleDao.getNextPK();
    }

    @Override
    public List<MaestroDetalle> getDetallesByMaestro(Maestro maestro) throws Exception {
        List<MaestroDetalle> maestros = new ArrayList<MaestroDetalle>();
        MaestroDetalleDao maestroDetalleDao = (MaestroDetalleDao) ServiceFinder.findBean("MaestroDetalleDao");
        Mtmaestro mtmaestro = new Mtmaestro();
        BeanUtils.copyProperties(mtmaestro, maestro);
        List<Tmaestrodetalle> lista = maestroDetalleDao.getDetalleByMaestro(mtmaestro);
        for(Tmaestrodetalle tdetalle : lista) {
            MaestroDetalle detalle = new MaestroDetalle();
            BeanUtils.copyProperties(detalle, tdetalle);
            maestros.add(detalle);
        }
        return maestros;
    }
    
    @Override
    public List<MaestroDetalle> getDetallesActivosByMaestro(Maestro maestro) throws Exception {
        List<MaestroDetalle> maestros = new ArrayList<MaestroDetalle>();
        MaestroDetalleDao maestroDetalleDao = (MaestroDetalleDao) ServiceFinder.findBean("MaestroDetalleDao");
        Mtmaestro mtmaestro = new Mtmaestro();
        BeanUtils.copyProperties(mtmaestro, maestro);
        List<Tmaestrodetalle> lista = maestroDetalleDao.getDetallesActivosByMaestro(mtmaestro);
        for(Tmaestrodetalle tdetalle : lista) {
            MaestroDetalle detalle = new MaestroDetalle();
            BeanUtils.copyProperties(detalle, tdetalle);
            maestros.add(detalle);
        }
        return maestros;
    }

    @Override
    public void saveOrUpdate(MaestroDetalle maestroDetalle) throws Exception {
        Tmaestrodetalle tmaestrodetalle = new Tmaestrodetalle();
        BeanUtils.copyProperties(tmaestrodetalle, maestroDetalle);
        MaestroDetalleDao maestroDetalleDao = (MaestroDetalleDao) ServiceFinder.findBean("MaestroDetalleDao");
        maestroDetalleDao.saveOrUpdate(tmaestrodetalle);
    }
    
}
