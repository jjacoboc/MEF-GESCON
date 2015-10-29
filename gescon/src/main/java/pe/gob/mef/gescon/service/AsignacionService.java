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
    
    public BigDecimal getNextPK() throws Exception;
    public BigDecimal getNumberNotificationsByUser(User user) throws Exception;
    public BigDecimal getNumberNotificationsAssignedByUser(User user) throws Exception;
    public BigDecimal getNumberNotificationsReceivedByUser(User user) throws Exception;
    public BigDecimal getNumberNotificationsServedByUser(User user) throws Exception;
    public List<Consulta> getNotificationsAssignedPanelByUser(User user) throws Exception;
    public List<Consulta> getNotificationsReceivedPanelByUser(User user) throws Exception;
    public List<Consulta> getNotificationsServedPanelByUser(User user) throws Exception;
    public void saveOrUpdate(Asignacion asignacion) throws Exception;
}
