/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.gob.mef.gescon.service;

import java.math.BigDecimal;
import java.util.List;
import pe.gob.mef.gescon.web.bean.ArchivoHist;
import pe.gob.mef.gescon.web.bean.BaselegalHist;

/**
 *
 * @author JJacobo
 */
public interface ArchivoHistorialService {
    
    BigDecimal getNextPK() throws Exception;
    List<ArchivoHist> getArchivosHistByBaseLegalHist(BaselegalHist baseLegal) throws Exception;
    ArchivoHist getLastArchivoHistByBaseLegalHist(BaselegalHist baseLegal) throws Exception;
    void saveOrUpdate(ArchivoHist archivo) throws Exception;
}
