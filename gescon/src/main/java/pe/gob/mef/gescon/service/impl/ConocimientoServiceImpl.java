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
import pe.gob.mef.gescon.common.Constante;
import pe.gob.mef.gescon.hibernate.dao.CategoriaDao;
import pe.gob.mef.gescon.hibernate.dao.ConocimientoDao;
import pe.gob.mef.gescon.hibernate.dao.TipoConocimientoDao;
import pe.gob.mef.gescon.hibernate.domain.Mtcategoria;
import pe.gob.mef.gescon.hibernate.domain.MttipoConocimiento;
import pe.gob.mef.gescon.hibernate.domain.Tconocimiento;
import pe.gob.mef.gescon.service.ConocimientoService;
import pe.gob.mef.gescon.util.ServiceFinder;
import pe.gob.mef.gescon.web.bean.Categoria;
import pe.gob.mef.gescon.web.bean.Conocimiento;
import pe.gob.mef.gescon.web.bean.TipoConocimiento;

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
        CategoriaDao categoriaDao = (CategoriaDao) ServiceFinder.findBean("CategoriaDao");
        TipoConocimientoDao tipoConocimientoDao = (TipoConocimientoDao) ServiceFinder.findBean("TipoConocimientoDao");
        Conocimiento conocimiento = new Conocimiento();
        Mtcategoria mtcategoria = new Mtcategoria();
        MttipoConocimiento mttipoConocimiento = new MttipoConocimiento();
        for (Tconocimiento tconocimiento : lista) {
            mttipoConocimiento = tipoConocimientoDao.getMttipoConocimientoById(tconocimiento.getMttipoConocimiento().getNtpoconocimientoid());
            mtcategoria = categoriaDao.getMtcategoriaById(tconocimiento.getMtcategoria().getNcategoriaid() );
            BeanUtils.copyProperties(conocimiento, tconocimiento);
            
            conocimiento.setMtcategoria(mtcategoria);
            conocimiento.setMttipoConocimiento(mttipoConocimiento);
            conocimientos.add(conocimiento);
            
            conocimiento     = new Conocimiento();
            mtcategoria        = new Mtcategoria();
            mttipoConocimiento = new MttipoConocimiento();
            
        }
        return conocimientos;
    }

    @Override
    public void saveOrUpdate(Conocimiento conocimiento) throws Exception {
        Tconocimiento tconocimiento = new Tconocimiento();
        BeanUtils.copyProperties(tconocimiento, conocimiento);
        ConocimientoDao conocimientoDao         = (ConocimientoDao) ServiceFinder.findBean("ConocimientoDao");
        TipoConocimientoDao tipoConocimientoDao = (TipoConocimientoDao) ServiceFinder.findBean("TipoConocimientoDao");
        CategoriaDao categoriaDao               = (CategoriaDao) ServiceFinder.findBean("CategoriaDao");
        final MttipoConocimiento mttipoConocimiento = tipoConocimientoDao.getMttipoConocimientoById(BigDecimal.valueOf(Constante.ID_OPORTUNIDAD_MEJORAS));
        final Mtcategoria mtcategoria = categoriaDao.getMtcategoriaById(conocimiento.getMtcategoria().getNcategoriaid());
        tconocimiento.setNconocimientoid( conocimientoDao.getNextPK() );
        tconocimiento.setMttipoConocimiento( mttipoConocimiento );
        tconocimiento.setMtcategoria( mtcategoria );
                        
        tconocimiento.setNestado(BigDecimal.ONE);
        
        conocimientoDao.saveOrUpdate(tconocimiento);
    }

    @Override
    public void delete(Conocimiento conocimiento) throws Exception {
        Tconocimiento tconocimiento = new Tconocimiento();
        BeanUtils.copyProperties(tconocimiento, conocimiento);
        ConocimientoDao conocimientoDao = (ConocimientoDao) ServiceFinder.findBean("ConocimientoDao");
        conocimientoDao.delete(tconocimiento);
        
    }
    
    
    
}
