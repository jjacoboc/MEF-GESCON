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
import pe.gob.mef.gescon.hibernate.dao.CategoriaDao;
import pe.gob.mef.gescon.hibernate.dao.ConocimientoDao;
import pe.gob.mef.gescon.hibernate.domain.Mtcategoria;
import pe.gob.mef.gescon.hibernate.domain.Tconocimiento;
import pe.gob.mef.gescon.service.ConocimientoService;
import pe.gob.mef.gescon.util.ServiceFinder;
import pe.gob.mef.gescon.web.bean.Categoria;
import pe.gob.mef.gescon.web.bean.Conocimiento;

/**
 *
 * @author JJacobo
 */
@Repository(value = "ConocimientoService")
public class ConocimientoServiceImpl implements ConocimientoService{

    @Override
    public BigDecimal getNextPK() throws Exception {
        ConocimientoDao conocimientoDao = (ConocimientoDao) ServiceFinder.findBean("ConocimientoDao");
        return conocimientoDao.getNextPK();
    }

    @Override
    public List<Conocimiento> getConocimientos() throws Exception {
        List<Conocimiento> conocimientos = new ArrayList<Conocimiento>();
        ConocimientoDao conocimientoDao = (ConocimientoDao) ServiceFinder.findBean("ConocimientoDao");
        List<Tconocimiento> lista = conocimientoDao.getTconocimientos();
        for(Tconocimiento tconocimiento : lista) {
            Conocimiento conocimiento = new Conocimiento();
            BeanUtils.copyProperties(conocimiento, tconocimiento);
            conocimientos.add(conocimiento);
        }
        return conocimientos;
    }

    @Override
    public void saveOrUpdate(Conocimiento conocimiento) throws Exception {
        Tconocimiento tconocimiento = new Tconocimiento();
        BeanUtils.copyProperties(tconocimiento, conocimiento);
        ConocimientoDao conocimientoDao = (ConocimientoDao) ServiceFinder.findBean("ConocimientoDao");
        conocimientoDao.saveOrUpdate(tconocimiento);
    }
    
}
