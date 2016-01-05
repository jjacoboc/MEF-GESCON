/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.gob.mef.gescon.service;

import java.math.BigDecimal;
import java.util.List;
import pe.gob.mef.gescon.web.bean.Asignacion;
import pe.gob.mef.gescon.web.bean.BaseLegal;

/**
 *
 * @author JJacobo
 */
public interface BaseLegalService {
    
    BigDecimal getNextPK() throws Exception;
    List<BaseLegal> getBaselegales() throws Exception;
    List<BaseLegal> getBaselegalesActivedPosted() throws Exception;
    BaseLegal getBaselegalById(BigDecimal id) throws Exception;
    List<BaseLegal> getTbaselegalesLinkedById(final BigDecimal id) throws Exception;
    List<BaseLegal> getTbaselegalesNotLinkedById(final BigDecimal id) throws Exception;
    List<Asignacion> obtenerBaseLegalxAsig(BigDecimal baselegalid, BigDecimal usuarioid, BigDecimal tpoconocimientoid) throws Exception;
    void saveOrUpdate(BaseLegal baseLegal) throws Exception;
}
