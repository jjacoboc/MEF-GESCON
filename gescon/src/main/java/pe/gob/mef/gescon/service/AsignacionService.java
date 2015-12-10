/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.gob.mef.gescon.service;

import java.math.BigDecimal;
import java.util.List;
import pe.gob.mef.gescon.web.bean.Asignacion;
import pe.gob.mef.gescon.web.bean.Consulta;
import pe.gob.mef.gescon.web.bean.User;
import pe.gob.mef.gescon.web.bean.Categoria;

/**
 *
 * @author JJacobo
 */
public interface AsignacionService {
    
    public BigDecimal getNextPK() throws Exception;
    public BigDecimal getNumberNotificationsByUser(User user) throws Exception;
    public BigDecimal getNumberNotificationsAssignedByUser(User user) throws Exception;
    public BigDecimal getNumberNotificationsReceivedByUser(User user) throws Exception;
    public BigDecimal getNumberNotificationsServedByUser(User user) throws Exception;
    public List<Consulta> getNotificationsAssignedPanelByUser(User user) throws Exception;
    public List<Consulta> getNotificationsReceivedPanelByUser(User user) throws Exception;
    public List<Consulta> getNotificationsServedPanelByUser(User user) throws Exception;
    public BigDecimal getModeratorByCategoria(BigDecimal ncategoriaid) throws Exception;
    public BigDecimal getEspecialistaByCategoria(BigDecimal ncategoriaid) throws Exception;
    public BigDecimal getUserCreacionByPregunta(BigDecimal npreguntaid) throws Exception;
    public BigDecimal getUserCreacionByBaseLegal(BigDecimal nbaselegalid) throws Exception;
    public BigDecimal getUserCreacionByContenido(BigDecimal idtipo ,BigDecimal nconocimientoid) throws Exception;
    public void saveOrUpdate(Asignacion asignacion) throws Exception;
}
