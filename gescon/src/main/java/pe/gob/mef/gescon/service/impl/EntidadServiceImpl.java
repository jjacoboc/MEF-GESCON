/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.gob.mef.gescon.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;
import pe.gob.mef.gescon.hibernate.dao.EntidadDao;
import pe.gob.mef.gescon.hibernate.domain.Mtentidad;
import pe.gob.mef.gescon.service.EntidadService;
import pe.gob.mef.gescon.util.ServiceFinder;
import pe.gob.mef.gescon.web.bean.Entidad;
import pe.gob.mef.gescon.web.bean.TipoEntidad;

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
    public List<Entidad> getEntidadesUbigeo() {
        List<Entidad> lista = new ArrayList<Entidad>();
        try {

            EntidadDao entidadDao = (EntidadDao) ServiceFinder.findBean("EntidadDao");
            List<HashMap> entidad = entidadDao.getEntidadesUbigeo();
            if (!CollectionUtils.isEmpty(entidad)) {
                for (HashMap map : entidad) {
                    Entidad c = new Entidad();
                    c.setNentidadid((BigDecimal) map.get("ID"));
                    c.setVcodigoentidad((String) map.get("CODIGO"));
                    c.setVnombre((String) map.get("NOMBRE"));
                    c.setVdescripcion((String) map.get("DES"));
                    c.setVusuariocreacion((String) map.get("USUC"));
                    c.setDfechacreacion((Date) map.get("FECHAC"));
                    c.setVusuariomodificacion((String) map.get("USUM"));
                    c.setDfechamodificacion((Date) map.get("FECHAM"));
                    c.setNactivo((BigDecimal) map.get("ACTIVO"));
                    c.setVdepartamento((String) map.get("DEPARTAMENTO"));
                    c.setVprovincia((String) map.get("PROVINCIA"));
                    c.setVdistrito((String) map.get("DISTRITO"));
                    c.setVtipo((String) map.get("TIPO"));
                    lista.add(c);
                }
            }
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
        return lista;
    }
    
    @Override
    public List<TipoEntidad> getTipos() throws Exception {
        List<TipoEntidad> tipos = new ArrayList<TipoEntidad>();
        EntidadDao entidadDao = (EntidadDao) ServiceFinder.findBean("EntidadDao");
        List<HashMap> lista = entidadDao.getTipos();
        for(HashMap m : lista) {
            TipoEntidad u = new TipoEntidad();
            u.setNtipoentidadid((BigDecimal) m.get("COD"));
            u.setVdescripcion((String) m.get("DES"));
            tipos.add(u);
        }
        return tipos;
    }
    


    @Override
    public void saveOrUpdate(Entidad entidad) throws Exception {
        Mtentidad mtentidad = new Mtentidad();
        BeanUtils.copyProperties(mtentidad, entidad);
        EntidadDao entidadDao = (EntidadDao) ServiceFinder.findBean("EntidadDao");
        entidadDao.saveOrUpdate(mtentidad);
    }
    
}
