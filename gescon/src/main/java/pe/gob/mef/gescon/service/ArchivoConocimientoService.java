/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.gob.mef.gescon.service;

import java.math.BigDecimal;
import java.util.List;
import pe.gob.mef.gescon.web.bean.ArchivoConocimiento;
import pe.gob.mef.gescon.web.bean.Conocimiento;


/**
 *
 * @author JJacobo
 */
public interface ArchivoConocimientoService {
    
    public BigDecimal getNextPK() throws Exception;
    public List<ArchivoConocimiento> getArchivosByConocimiento(BigDecimal nconocimientoid) throws Exception;
    public ArchivoConocimiento getLastArchivoByConocimiento(Conocimiento conocimiento) throws Exception;
    public void saveOrUpdate(ArchivoConocimiento archivoconocimiento) throws Exception;
    
    
}
