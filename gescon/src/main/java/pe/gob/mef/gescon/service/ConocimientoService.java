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
public interface ConocimientoService {
    
    BigDecimal getNextPK() throws Exception;
    List<Conocimiento> getConocimientos() throws Exception;
    List<Conocimiento> getConocimientosActivedPublic() throws Exception;
    List<Conocimiento> getConocimientosByType(BigDecimal type) throws Exception;
    List<Conocimiento> getConocimientosActivedPublicByType(BigDecimal type) throws Exception;
    Conocimiento getConocimientoById(BigDecimal id) throws Exception;
    void saveOrUpdate(Conocimiento conocimiento) throws Exception;
    void delete(Conocimiento conocimiento) throws Exception;
    List<Consulta> getConcimientosVinculados(HashMap filters);
    List<Consulta> getConcimientosDisponibles(HashMap filters);
    Conocimiento getBpracticaById(BigDecimal tipo,BigDecimal id) throws Exception;
    List<Asignacion> obtenerBpracticaxAsig(BigDecimal bpracticaid, BigDecimal usuarioid, BigDecimal tpoconocimientoid) throws Exception;
    Conocimiento getOmejoraById(BigDecimal tipo,BigDecimal id) throws Exception;
    List<Asignacion> obtenerOmejoraxAsig(BigDecimal omejoraid, BigDecimal usuarioid, BigDecimal tpoconocimientoid) throws Exception;
}
