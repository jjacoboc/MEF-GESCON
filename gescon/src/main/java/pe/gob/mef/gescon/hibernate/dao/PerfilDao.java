/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.gob.mef.gescon.hibernate.dao;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import pe.gob.mef.gescon.hibernate.domain.Mtperfil;
import pe.gob.mef.gescon.hibernate.domain.Mtuser;

/**
 *
 * @author JJacobo
 */
public interface PerfilDao {
    
    BigDecimal getNextPK() throws Exception;
    List<Mtperfil> getMtperfils() throws Exception;
    List<Mtperfil> getMtperfilsActived() throws Exception;
    List<HashMap> getMtperfilesByMtuser(final Mtuser mtuser) throws Exception;
    void saveOrUpdate(Mtperfil mtperfil) throws Exception;
}
