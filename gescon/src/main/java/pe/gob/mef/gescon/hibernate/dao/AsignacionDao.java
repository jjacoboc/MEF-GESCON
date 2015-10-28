/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.gob.mef.gescon.hibernate.dao;

import java.math.BigDecimal;
import pe.gob.mef.gescon.hibernate.domain.Mtuser;
import pe.gob.mef.gescon.hibernate.domain.Tasignacion;

/**
 *
 * @author JJacobo
 */
public interface AsignacionDao {
    
    public BigDecimal getNextPK() throws Exception;
    public BigDecimal getNumberNotificationsByMtuser(Mtuser mtuser) throws Exception;
    public void saveOrUpdate(Tasignacion tasignacion) throws Exception;
}
