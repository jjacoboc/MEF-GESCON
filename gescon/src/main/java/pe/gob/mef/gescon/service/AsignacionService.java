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

/**
 *
 * @author JJacobo
 */
public interface AsignacionService {
    
    BigDecimal getNextPK() throws Exception;
    BigDecimal getNumberNotificationsByUser(User user) throws Exception;
    BigDecimal getNumberNotificationsAssignedByUser(User user) throws Exception;
    BigDecimal getNumberNotificationsReceivedByUser(User user) throws Exception;
    BigDecimal getNumberNotificationsServedByUser(User user) throws Exception;
    BigDecimal getNumberNotificationsPublicByUser(User user) throws Exception;
    List<Consulta> getNotificationsAssignedPanelByUser(User user) throws Exception;
    List<Consulta> getNotificationsReceivedPanelByUser(User user) throws Exception;
    List<Consulta> getNotificationsServedPanelByUser(User user) throws Exception;
    List<Consulta> getNotificationsPublicPanelByUser(User user) throws Exception;
    List<Consulta> getNotificationsAlertPanelByMtuser(User user) throws Exception;
    BigDecimal getModeratorByCategoria(BigDecimal ncategoriaid) throws Exception;
    BigDecimal getEspecialistaByCategoria(BigDecimal ncategoriaid) throws Exception;
    BigDecimal getUserCreacionByPregunta(BigDecimal npreguntaid) throws Exception;
    BigDecimal getUserCreacionByBaseLegal(BigDecimal nbaselegalid) throws Exception;
    BigDecimal getUserCreacionByContenido(BigDecimal idtipo ,BigDecimal nconocimientoid) throws Exception;
    void saveOrUpdate(Asignacion asignacion) throws Exception;
}
