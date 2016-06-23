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
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;
import pe.gob.mef.gescon.hibernate.dao.ConsultaDao;
import pe.gob.mef.gescon.service.ConsultaService;
import pe.gob.mef.gescon.util.ServiceFinder;
import pe.gob.mef.gescon.web.bean.Consulta;

/**
 *
 * @author JJacobo
 */
@Repository(value = "ConsultaService")
public class ConsultaServiceImpl implements ConsultaService{

    @Override
    public List<Consulta> getQueryFilter(HashMap filters) {
        List<Consulta> lista = new ArrayList<Consulta>();
        try {
            ConsultaDao consultaDao = (ConsultaDao) ServiceFinder.findBean("ConsultaDao");
            List<HashMap> consulta = consultaDao.getQueryFilter(filters);
            if(!CollectionUtils.isEmpty(consulta)) {
                for(HashMap map : consulta) {
                    Consulta c = new Consulta();
                    c.setIdconocimiento((BigDecimal) map.get("ID"));
                    c.setNombre((String) map.get("NOMBRE"));
                    c.setSumilla((String) map.get("SUMILLA"));
                    c.setFechaPublicacion((Date) map.get("FECHA"));
                    c.setIdCategoria((BigDecimal) map.get("IDCATEGORIA"));
                    c.setCategoria((String) map.get("CATEGORIA"));
                    c.setIdTipoConocimiento((BigDecimal) map.get("IDTIPOCONOCIMIENTO"));
                    c.setTipoConocimiento((String) map.get("TIPOCONOCIMIENTO"));
                    c.setIdEstado((BigDecimal) map.get("IDESTADO"));
                    c.setEstado((String) map.get("ESTADO"));
                    c.setFlgVinculo((BigDecimal) map.get("FLG"));
                    BigDecimal contador = (BigDecimal) map.get("CONTADOR");
                    BigDecimal suma = (BigDecimal) map.get("SUMA");
                    if(BigDecimal.ZERO.equals(contador)) {
                        c.setCalificacion(BigDecimal.ZERO.intValue());
                    } else {
                        int calificacion = Math.round(Float.parseFloat(suma.toString()) / Integer.parseInt(contador.toString()));
                        c.setCalificacion(calificacion);
                    }
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
    public List<Consulta> getDestacadosByTipoConocimiento(HashMap filters) {
        List<Consulta> lista = new ArrayList<Consulta>();
        try {
            ConsultaDao consultaDao = (ConsultaDao) ServiceFinder.findBean("ConsultaDao");
            List<HashMap> consulta = consultaDao.getDestacadosByTipoConocimiento(filters);
            if(!CollectionUtils.isEmpty(consulta)) {
                for(HashMap map : consulta) {
                    Consulta c = new Consulta();
                    c.setIdconocimiento((BigDecimal) map.get("ID"));
                    c.setNombre((String) map.get("NOMBRE"));
                    String sumilla = (String) map.get("SUMILLA");
                    sumilla = sumilla != null ? sumilla : StringUtils.EMPTY;
                    if(sumilla.length() > 160) {
                        sumilla = sumilla.substring(0, 160);
                        sumilla = sumilla.substring(0, sumilla.lastIndexOf(" ")).concat("...");
                    }
                    c.setSumilla(sumilla);
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
    public BigDecimal countDestacadosByTipoConocimiento(HashMap filters) {
        ConsultaDao consultaDao = (ConsultaDao) ServiceFinder.findBean("ConsultaDao");
        return consultaDao.countDestacadosByTipoConocimiento(filters);
    }
    
    @Override
    public List<HashMap<String,Object>> listarReporte(HashMap filters) {
        List<HashMap<String,Object>> lista = new ArrayList<HashMap<String,Object>>();
        try {
            ConsultaDao consultaDao = (ConsultaDao) ServiceFinder.findBean("ConsultaDao");
            List<HashMap<String,Object>> consulta = consultaDao.listarReporte(filters);
            if(!CollectionUtils.isEmpty(consulta)) {
                for(HashMap<String,Object> r : consulta) {
                    HashMap<String,Object> map = new HashMap<String,Object>();
                    map.put("ID", r.get("ID"));
                    map.put("NOMBRE", r.get("NOMBRE"));
                    map.put("SUMILLA", r.get("SUMILLA"));
                    map.put("FECHA", r.get("FECHA"));
                    map.put("IDCATEGORIA", r.get("IDCATEGORIA"));
                    map.put("CATEGORIA", r.get("CATEGORIA"));
                    map.put("IDTIPOCONOCIMIENTO", r.get("IDTIPOCONOCIMIENTO"));
                    map.put("TIPOCONOCIMIENTO", r.get("TIPOCONOCIMIENTO"));
                    map.put("IDESTADO", r.get("IDESTADO"));
                    map.put("ESTADO", r.get("ESTADO"));
                    map.put("PROMEDIO", r.get("PROMEDIO"));
                    map.put("USUARIOCREA", r.get("USUARIOCREA"));
                    map.put("FECHACREA", r.get("FECHACREA"));
                    BigDecimal contador = (BigDecimal) r.get("CONTADOR");
                    BigDecimal suma = (BigDecimal) r.get("SUMA");

                    if(BigDecimal.ZERO.equals(contador)) {
                        map.put("CONTADOR", 0);
                    } else {
                        int calificacion = Math.round(Float.parseFloat(suma.toString()) / Integer.parseInt(contador.toString()));
                        map.put("CONTADOR", calificacion);
                    }
                    lista.add(map);
                }
            }
        } catch(Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
        return lista;
    }
    
}
