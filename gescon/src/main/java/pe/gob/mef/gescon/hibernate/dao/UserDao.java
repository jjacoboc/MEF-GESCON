/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.gob.mef.gescon.hibernate.dao;

import java.math.BigDecimal;
import java.util.List;
import pe.gob.mef.gescon.hibernate.domain.Mtuser;
import pe.gob.mef.gescon.hibernate.domain.TuserPerfil;

/**
 *
 * @author JJacobo
 */
public interface UserDao {
    
    BigDecimal getNextPK() throws Exception;
    void saveOrUpdate(Mtuser mtuser) throws Exception;
    List<Mtuser> getMtusers() throws Exception;
    List<Mtuser> getMtusersInternal() throws Exception;
    List<Mtuser> getMtusersExternal() throws Exception;
    List<Mtuser> getMtuserByPerfil(BigDecimal perfil) throws Exception;
    Mtuser getMtuserByDNI(String dni) throws Exception;
    Mtuser getMtuserByLogin(String login) throws Exception;
    Mtuser getMtuserById(BigDecimal nusuarioid) throws Exception;
    void asignProfileToUser(TuserPerfil tuserPerfil) throws Exception;
    TuserPerfil getPerfilByUser(BigDecimal idusuario) throws Exception;
    void deletePerfilByUser(final BigDecimal idusuario) throws Exception;
}
