/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.gob.mef.gescon.service;

import java.math.BigDecimal;
import java.util.List;
import pe.gob.mef.gescon.web.bean.Perfil;
import pe.gob.mef.gescon.web.bean.User;

/**
 *
 * @author SOPORTE
 */
public interface PerfilService {
    BigDecimal getNextPK() throws Exception;
    List<Perfil> getPerfils() throws Exception;
    List<Perfil> getPerfilsActived() throws Exception;
    List<Perfil> getPerfilesByUser(User user) throws Exception;
    void saveOrUpdate(Perfil perfil) throws Exception;
}
