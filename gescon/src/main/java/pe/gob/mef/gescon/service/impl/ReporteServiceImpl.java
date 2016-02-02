/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.gob.mef.gescon.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.stereotype.Repository;
import pe.gob.mef.gescon.hibernate.dao.AlertaDao;
import pe.gob.mef.gescon.hibernate.dao.ReporteDao;
import pe.gob.mef.gescon.hibernate.domain.Mtalerta;
import pe.gob.mef.gescon.service.AlertaService;
import pe.gob.mef.gescon.service.ReporteService;
import pe.gob.mef.gescon.util.ServiceFinder;
import pe.gob.mef.gescon.web.bean.Alerta;

/**
 *
 * @author JJacobo
 */
@Repository(value = "ReporteService")
public class ReporteServiceImpl implements ReporteService{

    @Override
    public List<HashMap<String,Object>> listarUsuarios(HashMap parametros) throws Exception {
        List<HashMap<String,Object>> lista = new ArrayList<HashMap<String,Object>>();
        ReporteDao reporteDao = (ReporteDao) ServiceFinder.findBean("ReporteDao");
        List<HashMap<String,Object>> result = reporteDao.listarUsuarios(parametros);
        for (HashMap<String,Object> r : result) {
            HashMap<String,Object> map = new HashMap<String,Object>();
            map.put("NOMBRE", r.get("NOMBRE"));
            map.put("LOGIN", r.get("LOGIN"));
            map.put("USUARIO_CREACION", r.get("USUARIO_CREACION"));
            map.put("FECHA_CREACION", r.get("FECHA_CREACION"));
            map.put("USUARIO_MODIFICACION", r.get("USUARIO_MODIFICACION"));
            map.put("FECHA_MODIFICACION", r.get("FECHA_MODIFICACION"));
            map.put("ESTADO", r.get("ESTADO"));
            lista.add(map);
        }
        return lista;
    }
    
    @Override
    public List<HashMap<String,Object>> listarPerfiles(HashMap parametros) throws Exception {
        List<HashMap<String,Object>> lista = new ArrayList<HashMap<String,Object>>();
        ReporteDao reporteDao = (ReporteDao) ServiceFinder.findBean("ReporteDao");
        List<HashMap<String,Object>> result = reporteDao.listarPerfiles(parametros);
        for (HashMap<String,Object> r : result) {
            HashMap<String,Object> map = new HashMap<String,Object>();
            map.put("NOMBRE_PERFIL", r.get("NOMBRE_PERFIL"));
            map.put("NOMBRE_POLITICA", r.get("NOMBRE_POLITICA"));
            map.put("DESCRIPCION", r.get("DESCRIPCION"));
            map.put("USUARIO_CREACION", r.get("USUARIO_CREACION"));
            map.put("FECHA_CREACION", r.get("FECHA_CREACION"));
            map.put("USUARIO_MODIFICACION", r.get("USUARIO_MODIFICACION"));
            map.put("FECHA_MODIFICACION", r.get("FECHA_MODIFICACION"));
            map.put("ESTADO", r.get("ESTADO"));
            lista.add(map);
        }
        return lista;
    }
    
    @Override
    public List<HashMap<String,Object>> listarConocimientos(HashMap parametros) throws Exception {
        List<HashMap<String,Object>> lista = new ArrayList<HashMap<String,Object>>();
        ReporteDao reporteDao = (ReporteDao) ServiceFinder.findBean("ReporteDao");
        List<HashMap<String,Object>> result = reporteDao.listarConocimientos(parametros);
        for (HashMap<String,Object> r : result) {
            HashMap<String,Object> map = new HashMap<String,Object>();
            map.put("NOMBRE", r.get("NOMBRE"));
            map.put("CATEGORIA", r.get("CATEGORIA"));
            map.put("TIPOCONOCIMIENTO", r.get("TIPOCONOCIMIENTO"));
            map.put("USUARIO_CREACION", r.get("USUARIO_CREACION"));
            map.put("FECHA_CREACION", r.get("FECHA_CREACION"));
            map.put("USUARIO_MODIFICACION", r.get("USUARIO_MODIFICACION"));
            map.put("FECHA_MODIFICACION", r.get("FECHA_MODIFICACION"));
            map.put("ESTADO", r.get("ESTADO"));
            lista.add(map);
        }
        return lista;
    }
    
    @Override
    public List<HashMap<String,Object>> listarCalificaciones(HashMap parametros) throws Exception {
        List<HashMap<String,Object>> lista = new ArrayList<HashMap<String,Object>>();
        ReporteDao reporteDao = (ReporteDao) ServiceFinder.findBean("ReporteDao");
        List<HashMap<String,Object>> result = reporteDao.listarCalificaciones(parametros);
        for (HashMap<String,Object> r : result) {
            HashMap<String,Object> map = new HashMap<String,Object>();
            map.put("NOMBRE", r.get("NOMBRE"));
            map.put("CATEGORIA", r.get("CATEGORIA"));
            map.put("TIPOCONOCIMIENTO", r.get("TIPOCONOCIMIENTO"));
            map.put("USUARIO_CREACION", r.get("USUARIO_CREACION"));
            map.put("FECHA_CREACION", r.get("FECHA_CREACION"));
            map.put("USUARIO_MODIFICACION", r.get("USUARIO_MODIFICACION"));
            map.put("FECHA_MODIFICACION", r.get("FECHA_MODIFICACION"));
            map.put("ESTADO", r.get("ESTADO"));
            map.put("CALIFICACION", r.get("CALIFICACION"));
            lista.add(map);
        }
        return lista;
    }
    
}
