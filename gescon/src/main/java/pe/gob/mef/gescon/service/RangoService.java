/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.gob.mef.gescon.service;

import java.math.BigDecimal;
import java.util.List;
import pe.gob.mef.gescon.web.bean.MaestroDetalle;
import pe.gob.mef.gescon.web.bean.Rango;


/**
 *
 * @author JJacobo
 */
public interface RangoService {
    
    public BigDecimal getNextPK() throws Exception;
    public List<Rango> getRangos() throws Exception;
    public List<MaestroDetalle> getTipoRangoByMaestro(BigDecimal maestroid) throws Exception;
    List<Rango> getRangosByTipo(BigDecimal id) throws Exception;
    List<Rango> getRangosActivosByTipo(BigDecimal id) throws Exception;
    public void saveOrUpdate(Rango rango) throws Exception;
}
