/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.gob.mef.gescon.service;

import java.math.BigDecimal;
import java.util.List;
import pe.gob.mef.gescon.web.bean.Entidad;
import pe.gob.mef.gescon.web.bean.TipoEntidad;

/**
 *
 * @author JJacobo
 */
public interface EntidadService {
    
    public BigDecimal getNextPK() throws Exception;
    public List<Entidad> getEntidades() throws Exception;
    public List<Entidad> getEntidadesUbigeo() throws Exception;
    public List<TipoEntidad> getTipos() throws Exception;
    public void saveOrUpdate(Entidad entidad) throws Exception;
}
