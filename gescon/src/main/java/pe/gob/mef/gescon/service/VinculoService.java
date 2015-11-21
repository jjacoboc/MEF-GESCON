/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.gob.mef.gescon.service;

import java.math.BigDecimal;
import java.util.List;
import pe.gob.mef.gescon.web.bean.Vinculo;

/**
 *
 * @author JJacobo
 */
public interface VinculoService {
    
    BigDecimal getNextPK() throws Exception;
    List<Vinculo> getVinculos() throws Exception;
    List<Vinculo> getVinculosByConocimiento(BigDecimal idconocimiento) throws Exception;
    List<Vinculo> getVinculosByConocimientoAndTipoconocimiento(BigDecimal idconocimiento, BigDecimal idtipoconocimiento) throws Exception;
    Vinculo getVinculoById(BigDecimal idvinculo) throws Exception;
    void saveOrUpdate(Vinculo vinculo) throws Exception;
}
