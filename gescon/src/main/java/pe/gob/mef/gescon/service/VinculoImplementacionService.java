/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.gob.mef.gescon.service;

import java.math.BigDecimal;
import java.util.List;
import pe.gob.mef.gescon.web.bean.VinculoImplementacion;

/**
 *
 * @author JJacobo
 */
public interface VinculoImplementacionService {
    
    BigDecimal getNextPK() throws Exception;
    List<VinculoImplementacion> getVinculos() throws Exception;
    List<VinculoImplementacion> getVinculosByConocimiento(BigDecimal idconocimiento) throws Exception;
    VinculoImplementacion getVinculoById(BigDecimal idvinculo) throws Exception;
    void saveOrUpdate(VinculoImplementacion vinculoImplementacion) throws Exception;
    void deleteByConocimiento(BigDecimal idconocimiento) throws Exception;
}
