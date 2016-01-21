/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.gob.mef.gescon.service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import pe.gob.mef.gescon.web.bean.Asignacion;
import pe.gob.mef.gescon.web.bean.Conocimiento;
import pe.gob.mef.gescon.web.bean.Consulta;

/**
 *
 * @author JJacobo
 */
public interface WikiService {
    
    List<Consulta> getConcimientosVinculados(HashMap filters);
    List<Consulta> getConcimientosDisponibles(HashMap filters);
    Conocimiento getWikiById(BigDecimal tipo,BigDecimal id) throws Exception;
    List<Asignacion> obtenerWikixAsig(BigDecimal wikiid, BigDecimal usuarioid, BigDecimal tpoconocimientoid) throws Exception;
}
