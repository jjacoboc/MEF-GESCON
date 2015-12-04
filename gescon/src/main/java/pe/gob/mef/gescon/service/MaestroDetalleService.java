/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.gob.mef.gescon.service;

import java.math.BigDecimal;
import java.util.List;
import pe.gob.mef.gescon.web.bean.Maestro;
import pe.gob.mef.gescon.web.bean.MaestroDetalle;

/**
 *
 * @author JJacobo
 */
public interface MaestroDetalleService {
    
    BigDecimal getNextPK() throws Exception;
    List<MaestroDetalle> getDetallesByMaestro(Maestro maestro) throws Exception;
    List<MaestroDetalle> getDetallesActivosByMaestro(Maestro maestro) throws Exception;
    void saveOrUpdate(MaestroDetalle maestroDetalle) throws Exception;
}
