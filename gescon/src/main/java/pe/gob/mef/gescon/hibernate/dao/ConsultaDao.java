/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.gob.mef.gescon.hibernate.dao;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author JJacobo
 */
public interface ConsultaDao {
    
    List<HashMap> getQueryFilter(HashMap filters);
    List<HashMap> getDestacadosByTipoConocimiento(HashMap filters);
    BigDecimal countDestacadosByTipoConocimiento(HashMap filters);
    List<HashMap<String,Object>> listarReporte(HashMap filters) throws Exception;
}
