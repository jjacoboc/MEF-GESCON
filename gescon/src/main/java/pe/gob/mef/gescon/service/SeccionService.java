/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.gob.mef.gescon.service;

import java.math.BigDecimal;
import java.util.List;
import pe.gob.mef.gescon.web.bean.Seccion;

/**
 *
 * @author JJacobo
 */
public interface SeccionService {
    
    BigDecimal getNextPK() throws Exception;
    Seccion getSeccionById(BigDecimal idseccion) throws Exception;
    List<Seccion> getSecciones() throws Exception;
    List<Seccion> getSeccionesByConocimiento(BigDecimal idconocimiento) throws Exception;
    void saveOrUpdate(Seccion seccion) throws Exception;
}
