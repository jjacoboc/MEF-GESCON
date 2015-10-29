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
import pe.gob.mef.gescon.hibernate.dao.AsignacionDao;
import pe.gob.mef.gescon.hibernate.domain.Mtuser;
import pe.gob.mef.gescon.hibernate.domain.Tasignacion;
import pe.gob.mef.gescon.service.AsignacionService;
import pe.gob.mef.gescon.util.ServiceFinder;
import pe.gob.mef.gescon.web.bean.Asignacion;
import pe.gob.mef.gescon.web.bean.Consulta;
import pe.gob.mef.gescon.web.bean.User;

/**
 *
 * @author JJacobo
 */
@Repository(value = "AsignacionService")
public class AsignacionServiceImpl implements AsignacionService{

    @Override
    public BigDecimal getNextPK() throws Exception {
        AsignacionDao asignacionDao = (AsignacionDao) ServiceFinder.findBean("AsignacionDao");
        return asignacionDao.getNextPK();
    }
    
    @Override
    public BigDecimal getNumberNotificationsByUser(User user) throws Exception {
        Mtuser mtuser = new Mtuser();
        BeanUtils.copyProperties(mtuser, user);
        AsignacionDao asignacionDao = (AsignacionDao) ServiceFinder.findBean("AsignacionDao");
        return asignacionDao.getNumberNotificationsByMtuser(mtuser);
    }
    
    @Override
    public BigDecimal getNumberNotificationsAssignedByUser(User user) throws Exception {
        Mtuser mtuser = new Mtuser();
        BeanUtils.copyProperties(mtuser, user);
        AsignacionDao asignacionDao = (AsignacionDao) ServiceFinder.findBean("AsignacionDao");
        return asignacionDao.getNumberNotificationsAssignedByMtuser(mtuser);
    }
    
    @Override
    public BigDecimal getNumberNotificationsReceivedByUser(User user) throws Exception {
        Mtuser mtuser = new Mtuser();
        BeanUtils.copyProperties(mtuser, user);
        AsignacionDao asignacionDao = (AsignacionDao) ServiceFinder.findBean("AsignacionDao");
        return asignacionDao.getNumberNotificationsReceivedByMtuser(mtuser);
    }
    
    @Override
    public BigDecimal getNumberNotificationsServedByUser(User user) throws Exception {
        Mtuser mtuser = new Mtuser();
        BeanUtils.copyProperties(mtuser, user);
        AsignacionDao asignacionDao = (AsignacionDao) ServiceFinder.findBean("AsignacionDao");
        return asignacionDao.getNumberNotificationsServedByMtuser(mtuser);
    }
    
    @Override
    public List<Consulta> getNotificationsAssignedPanelByUser(User user) {
        List<Consulta> lista = new ArrayList<Consulta>();
        try {
            Mtuser mtuser = new Mtuser();
            BeanUtils.copyProperties(mtuser, user);
            AsignacionDao asignacionDao = (AsignacionDao) ServiceFinder.findBean("AsignacionDao");
            List<HashMap> consulta = asignacionDao.getNotificationsAssignedPanelByMtuser(mtuser);
            if(!CollectionUtils.isEmpty(consulta)) {
                for(HashMap map : consulta) {
                    Consulta c = new Consulta();
                    c.setId((BigDecimal) map.get("ID"));
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
    public List<Consulta> getNotificationsReceivedPanelByUser(User user) {
        List<Consulta> lista = new ArrayList<Consulta>();
        try {
            Mtuser mtuser = new Mtuser();
            BeanUtils.copyProperties(mtuser, user);
            AsignacionDao asignacionDao = (AsignacionDao) ServiceFinder.findBean("AsignacionDao");
            List<HashMap> consulta = asignacionDao.getNotificationsReceivedPanelByMtuser(mtuser);
            if(!CollectionUtils.isEmpty(consulta)) {
                for(HashMap map : consulta) {
                    Consulta c = new Consulta();
                    c.setId((BigDecimal) map.get("ID"));
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
    public List<Consulta> getNotificationsServedPanelByUser(User user) {
        List<Consulta> lista = new ArrayList<Consulta>();
        try {
            Mtuser mtuser = new Mtuser();
            BeanUtils.copyProperties(mtuser, user);
            AsignacionDao asignacionDao = (AsignacionDao) ServiceFinder.findBean("AsignacionDao");
            List<HashMap> consulta = asignacionDao.getNotificationsServedPanelByMtuser(mtuser);
            if(!CollectionUtils.isEmpty(consulta)) {
                for(HashMap map : consulta) {
                    Consulta c = new Consulta();
                    c.setId((BigDecimal) map.get("ID"));
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
    public void saveOrUpdate(Asignacion asignacion) throws Exception {
        Tasignacion tasignacion = new Tasignacion();
        BeanUtils.copyProperties(tasignacion, asignacion);
        AsignacionDao asignacionDao = (AsignacionDao) ServiceFinder.findBean("AsignacionDao");
        asignacionDao.saveOrUpdate(tasignacion);
    }
}
