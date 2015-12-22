/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.gob.mef.gescon.service;

import java.math.BigDecimal;
import java.util.List;
import pe.gob.mef.gescon.web.bean.DiscusionSeccion;

/**
 *
 * @author JJacobo
 */
public interface DiscusionSeccionService {
    
    BigDecimal getNextPK() throws Exception;
    DiscusionSeccion getSeccionById(BigDecimal idseccion) throws Exception;
    List<DiscusionSeccion> getSecciones() throws Exception;
    List<DiscusionSeccion> getSeccionesByDiscusion(BigDecimal iddiscusion) throws Exception;
    void saveOrUpdate(DiscusionSeccion discusionSeccion) throws Exception;
}
