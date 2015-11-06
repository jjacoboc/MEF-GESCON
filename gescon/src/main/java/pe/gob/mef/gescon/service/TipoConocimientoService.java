/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.gob.mef.gescon.service;

import java.math.BigDecimal;
import java.util.List;
import pe.gob.mef.gescon.web.bean.TipoConocimiento;

/**
 *
 * @author JJacobo
 */
public interface TipoConocimientoService {
    
    public List<TipoConocimiento> getTipoConocimientos() throws Exception;
    
    
    /**
     * cnishimura
     * @return
     * @throws Exception 
     */
    public TipoConocimiento getTipoConocimientoById(BigDecimal ntpoconocimientoid) throws Exception;
}
