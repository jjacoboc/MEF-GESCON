/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.gob.mef.gescon.hibernate.dao;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import pe.gob.mef.gescon.hibernate.domain.Tconocimiento;

/**
 *
 * @author JJacobo
 */
public interface WikiDao {
    
    List<HashMap> getConcimientosVinculados(HashMap filters);
    List<HashMap> getConcimientosDisponibles(HashMap filters);
    Tconocimiento getWikiById(BigDecimal tipo,BigDecimal id) throws Exception;
    List<HashMap> obtenerWikixAsig(BigDecimal wikiid,BigDecimal usuarioid, BigDecimal tpoconocimientoid) throws Exception;
}
