/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.gob.mef.gescon.hibernate.dao;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import pe.gob.mef.gescon.hibernate.domain.Tbaselegal;

/**
 *
 * @author JJacobo
 */
public interface BaseLegalDao {
    
    BigDecimal getNextPK() throws Exception;
    List<Tbaselegal> getTbaselegales() throws Exception;
    List<Tbaselegal> getTbaselegalesActivedPosted() throws Exception;
    Tbaselegal getTbaselegalById(BigDecimal id) throws Exception;
    List<HashMap> getTbaselegalesLinkedById(BigDecimal id) throws Exception;
    List<HashMap> getTbaselegalesNotLinkedById(BigDecimal id) throws Exception;
    List<HashMap> obtenerBaseLegalxAsig(BigDecimal baselegalid,BigDecimal usuarioid, BigDecimal tpoconocimientoid) throws Exception;
    void saveOrUpdate(Tbaselegal tbaselegal) throws Exception;
}
