/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.gob.mef.gescon.service;

import java.math.BigDecimal;
import java.util.List;
import pe.gob.mef.gescon.web.bean.Archivo;
import pe.gob.mef.gescon.web.bean.BaseLegal;

/**
 *
 * @author JJacobo
 */
public interface ArchivoService {
    
    public BigDecimal getNextPK() throws Exception;
    public List<Archivo> getArchivosByBaseLegal(BaseLegal baseLegal) throws Exception;
    public Archivo getLastArchivoByBaseLegal(BaseLegal baseLegal) throws Exception;
    public void saveOrUpdate(Archivo archivo) throws Exception;
}
