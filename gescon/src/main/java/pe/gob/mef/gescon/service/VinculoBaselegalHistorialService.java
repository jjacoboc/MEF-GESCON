/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.gob.mef.gescon.service;

import java.math.BigDecimal;
import java.util.List;
import pe.gob.mef.gescon.web.bean.VinculoBaselegalHist;

/**
 *
 * @author JJacobo
 */
public interface VinculoBaselegalHistorialService {
    
    BigDecimal getNextPK() throws Exception;
    VinculoBaselegalHist getVinculoHistById(BigDecimal idvinculoh) throws Exception;
    List<VinculoBaselegalHist> getVinculoHists() throws Exception;
    List<VinculoBaselegalHist> getVinculoHistsByBaselegalHist(BigDecimal idhistorial) throws Exception;
    void saveOrUpdate(VinculoBaselegalHist vinculoHist) throws Exception;
}
