/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.gob.mef.gescon.hibernate.dao;

import java.math.BigDecimal;
import java.util.List;
import pe.gob.mef.gescon.hibernate.domain.Mtperfil;
//import pe.gob.mef.gescon.hibernate.domain.Mtmaestro;
//import pe.gob.mef.gescon.hibernate.domain.Mtpolitica;

/**
 *
 * @author JJacobo
 */
public interface PerfilDao {
    
    public BigDecimal getNextPK() throws Exception;
    public List<Mtperfil> getMtperfils() throws Exception;
    public void saveOrUpdate(Mtperfil mtperfil) throws Exception;
}
