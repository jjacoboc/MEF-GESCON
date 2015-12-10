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
import pe.gob.mef.gescon.hibernate.dao.ConocimientoDao;
import pe.gob.mef.gescon.hibernate.domain.Tconocimiento;
import pe.gob.mef.gescon.service.ConocimientoService;
import pe.gob.mef.gescon.util.ServiceFinder;
import pe.gob.mef.gescon.web.bean.Conocimiento;
import pe.gob.mef.gescon.web.bean.Consulta;

/**
 *
 * @author JJacobo
 */
@Repository(value = "ConocimientoService")
public class ConocimientoServiceImpl implements ConocimientoService {

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
        Conocimiento conocimiento = new Conocimiento();
        for (Tconocimiento tconocimiento : lista) {
            BeanUtils.copyProperties(conocimiento, tconocimiento);
            conocimientos.add(conocimiento);
            conocimiento = new Conocimiento();
        }
        return conocimientos;
    }
    
    @Override
    public List<Conocimiento> getConocimientosByType(BigDecimal type) throws Exception {
        List<Conocimiento> conocimientos = new ArrayList<Conocimiento>();
        ConocimientoDao conocimientoDao = (ConocimientoDao) ServiceFinder.findBean("ConocimientoDao");
        List<Tconocimiento> lista = conocimientoDao.getTconocimientosByType(type);
        Conocimiento conocimiento = new Conocimiento();
        for (Tconocimiento tconocimiento : lista) {
            BeanUtils.copyProperties(conocimiento, tconocimiento);
            conocimientos.add(conocimiento);
            conocimiento = new Conocimiento();
        }
        return conocimientos;
    }
    
    @Override
    public Conocimiento getConocimientoById(BigDecimal id) throws Exception {
        ConocimientoDao conocimientoDao = (ConocimientoDao) ServiceFinder.findBean("ConocimientoDao");
        Tconocimiento tconocimiento = conocimientoDao.getTconocimientoById(id);
        Conocimiento conocimiento = new Conocimiento();        
        BeanUtils.copyProperties(conocimiento, tconocimiento);        
        return conocimiento;
    }

    @Override
    public void saveOrUpdate(Conocimiento conocimiento) throws Exception {
        Tconocimiento tconocimiento = new Tconocimiento();
        BeanUtils.copyProperties(tconocimiento, conocimiento);
        ConocimientoDao conocimientoDao = (ConocimientoDao) ServiceFinder.findBean("ConocimientoDao");
        conocimientoDao.saveOrUpdate(tconocimiento);
    }

    @Override
    public void delete(Conocimiento conocimiento) throws Exception {
        Tconocimiento tconocimiento = new Tconocimiento();
        BeanUtils.copyProperties(tconocimiento, conocimiento);
        ConocimientoDao conocimientoDao = (ConocimientoDao) ServiceFinder.findBean("ConocimientoDao");
        conocimientoDao.delete(tconocimiento);

    }

    @Override
    public List<Consulta> getConcimientosVinculados(HashMap filters) {
        List<Consulta> lista = new ArrayList<Consulta>();
        try {
            ConocimientoDao conocimientoDao = (ConocimientoDao) ServiceFinder.findBean("ConocimientoDao");
            List<HashMap> consulta = conocimientoDao.getConcimientosVinculados(filters);
            if(!CollectionUtils.isEmpty(consulta)) {
                for(HashMap map : consulta) {
                    Consulta c = new Consulta();
                    c.setId((BigDecimal) map.get("ID"));
                    c.setIdconocimiento((BigDecimal) map.get("IDCONOCIMIENTO"));
                    c.setCodigo((String) map.get("NUMERO"));
                    c.setNombre((String) map.get("NOMBRE"));
                    c.setSumilla((String) map.get("SUMILLA"));
                    c.setFechaPublicacion((Date) map.get("FECHA"));
                    c.setIdCategoria((BigDecimal) map.get("IDCATEGORIA"));
                    c.setCategoria((String) map.get("CATEGORIA"));
                    c.setIdTipoConocimiento((BigDecimal) map.get("IDTIPOCONOCIMIENTO"));
                    c.setTipoConocimiento((String) map.get("TIPOCONOCIMIENTO"));
                    c.setIdEstado((BigDecimal) map.get("IDESTADO"));
                    c.setEstado((String) map.get("ESTADO"));
                    lista.add(c);
                }
            }
        } catch(Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
        return lista;
    }
    
    @Override
    public List<Consulta> getConcimientosDisponibles(HashMap filters) {
        List<Consulta> lista = new ArrayList<Consulta>();
        try {
            ConocimientoDao conocimientoDao = (ConocimientoDao) ServiceFinder.findBean("ConocimientoDao");
            List<HashMap> consulta = conocimientoDao.getConcimientosDisponibles(filters);
            if(!CollectionUtils.isEmpty(consulta)) {
                for(HashMap map : consulta) {
                    Consulta c = new Consulta();
                    c.setIdconocimiento((BigDecimal) map.get("ID"));
                    c.setCodigo((String) map.get("NUMERO"));
                    c.setNombre((String) map.get("NOMBRE"));
                    c.setSumilla((String) map.get("SUMILLA"));
                    c.setFechaPublicacion((Date) map.get("FECHA"));
                    c.setIdCategoria((BigDecimal) map.get("IDCATEGORIA"));
                    c.setCategoria((String) map.get("CATEGORIA"));
                    c.setIdTipoConocimiento((BigDecimal) map.get("IDTIPOCONOCIMIENTO"));
                    c.setTipoConocimiento((String) map.get("TIPOCONOCIMIENTO"));
                    c.setIdEstado((BigDecimal) map.get("IDESTADO"));
                    c.setEstado((String) map.get("ESTADO"));
                    lista.add(c);
                }
            }
        } catch(Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
        return lista;
    }
}
