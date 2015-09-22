/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.gob.mef.gescon.service;

import java.math.BigDecimal;
import java.util.List;
import pe.gob.mef.gescon.web.bean.Perfil;
//import pe.gob.mef.gescon.web.bean.Politica;

/**
 *
 * @author SOPORTE
 */
public interface PerfilService {
    public BigDecimal getNextPK() throws Exception;
    public List<Perfil> getPerfils() throws Exception;
    public void saveOrUpdate(Perfil perfil) throws Exception;
    
}
