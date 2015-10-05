/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.gob.mef.gescon.hibernate.dao;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import pe.gob.mef.gescon.hibernate.domain.Tpass;
import pe.gob.mef.gescon.hibernate.domain.Mtuser;
//import pe.gob.mef.gescon.hibernate.domain.Mtpolitica;

/**
 *
 * @author JJacobo
 */
public interface PassDao {

    public BigDecimal getNextPK() throws Exception;
    public void saveOrUpdate(Tpass tpass) throws Exception;

    
}
