/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.gob.mef.gescon.service;

import java.math.BigDecimal;
import java.util.List;
import pe.gob.mef.gescon.web.bean.VinculoPregunta;

/**
 *
 * @author JJacobo
 */
public interface VinculoPreguntaService {
    
    BigDecimal getNextPK() throws Exception;
    List<VinculoPregunta> getVinculos() throws Exception;
    List<VinculoPregunta> getVinculosByConocimiento(BigDecimal idconocimiento) throws Exception;
    List<VinculoPregunta> getVinculosByConocimientoAndTipoconocimiento(BigDecimal idconocimiento, BigDecimal idtipoconocimiento) throws Exception;
    VinculoPregunta getVinculoById(BigDecimal idvinculo) throws Exception;
    void saveOrUpdate(VinculoPregunta vinculopregunta) throws Exception;
    void deleteByConocimiento(BigDecimal idconocimiento) throws Exception;
}
