/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.gob.mef.gescon.service;

import java.math.BigDecimal;
import pe.gob.mef.gescon.web.bean.Pass;
import pe.gob.mef.gescon.web.bean.User;
//import pe.gob.mef.gescon.web.bean.Politica;
/**
 *
 * @author SOPORTE
 */
public interface PassService {
    public BigDecimal getNextPK() throws Exception;
    public Pass getPassByUser(User user) throws Exception;
    public void saveOrUpdate(Pass pass) throws Exception;
    
}
