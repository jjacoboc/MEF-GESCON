/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.gob.mef.gescon.hibernate.dao;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import pe.gob.mef.gescon.hibernate.domain.Mtalerta;

/**
 *
 * @author JJacobo
 */
public interface ReporteDao {
    
    public List<HashMap<String,Object>> listarUsuarios(final HashMap parametros) throws Exception;
    public List<HashMap<String,Object>> listarPerfiles(final HashMap parametros) throws Exception;
    public List<HashMap<String,Object>> listarConocimientos(final HashMap parametros) throws Exception;
    public List<HashMap<String,Object>> listarCalificaciones(final HashMap parametros) throws Exception;
}
