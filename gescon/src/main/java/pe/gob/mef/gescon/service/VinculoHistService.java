/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.gob.mef.gescon.service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import pe.gob.mef.gescon.web.bean.Consulta;
import pe.gob.mef.gescon.web.bean.VinculoHist;

/**
 *
 * @author JJacobo
 */
public interface VinculoHistService {
    
    BigDecimal getNextPK() throws Exception;
    VinculoHist getVinculoHistById(BigDecimal idvinculoh) throws Exception;
    List<VinculoHist> getVinculoHists() throws Exception;
    List<VinculoHist> getVinculoHistsByHistorial(BigDecimal idhistorial) throws Exception;
    void saveOrUpdate(VinculoHist vinculoHist) throws Exception;
    List<Consulta> getConcimientosVinculadosByHistorial(HashMap filters) throws Exception;
}
