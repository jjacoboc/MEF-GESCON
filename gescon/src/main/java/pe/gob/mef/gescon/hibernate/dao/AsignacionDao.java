/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.gob.mef.gescon.hibernate.dao;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import pe.gob.mef.gescon.hibernate.domain.Mtuser;
import pe.gob.mef.gescon.hibernate.domain.Tasignacion;

/**
 *
 * @author JJacobo
 */
public interface AsignacionDao {
    
    BigDecimal getNextPK() throws Exception;
    BigDecimal getNumberNotificationsByMtuser(Mtuser mtuser) throws Exception;
    BigDecimal getNumberNotificationsAssignedByMtuser(Mtuser mtuser) throws Exception;
    BigDecimal getNumberNotificationsReceivedByMtuser(Mtuser mtuser) throws Exception;
    BigDecimal getNumberNotificationsServedByMtuser(Mtuser mtuser) throws Exception;
    BigDecimal getNumberNotificationsPublicByMtuser(Mtuser mtuser) throws Exception;
    List<HashMap> getNotificationsAssignedPanelByMtuser(Mtuser mtuser) throws Exception;
    List<HashMap> getNotificationsReceivedPanelByMtuser(Mtuser mtuser) throws Exception;
    List<HashMap> getNotificationsServedPanelByMtuser(Mtuser mtuser) throws Exception;
    List<HashMap> getNotificationsPublicPanelByMtuser(Mtuser mtuser) throws Exception;
    List<HashMap> getNotificationsAlertPanelByMtuser(Mtuser mtuser) throws Exception;
    BigDecimal getModeratorByMtcategoria(BigDecimal ncategoriaid) throws Exception;
    BigDecimal getEspecialistaByMtcategoria(BigDecimal ncategoriaid) throws Exception;
    BigDecimal getUserCreacionByPregunta(BigDecimal npreguntaid) throws Exception;
    BigDecimal getUserCreacionByBaseLegal(BigDecimal nbaselegalid) throws Exception;
    BigDecimal getUserCreacionByContenido(BigDecimal idtipo,BigDecimal nconocimientoid) throws Exception;
    void saveOrUpdate(Tasignacion tasignacion) throws Exception;
}
