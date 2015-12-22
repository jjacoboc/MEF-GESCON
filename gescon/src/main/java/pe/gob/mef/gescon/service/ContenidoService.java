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
public interface ContenidoService {
    
    BigDecimal getNextPK() throws Exception;
    public List<Conocimiento> getContenidos() throws Exception;
    public Conocimiento getContenidoById(BigDecimal tipo,BigDecimal id) throws Exception;
    List<Asignacion> obtenerContenidoxAsig(BigDecimal contenidoid, BigDecimal usuarioid, BigDecimal tpoconocimientoid) throws Exception;
    void saveOrUpdate(Conocimiento conocimiento) throws Exception;
    List<Consulta> getConcimientosVinculados(HashMap filters);
    List<Consulta> getConcimientosDisponibles(HashMap filters);
    void delete(BigDecimal conocimientoid) throws Exception;
    void deleteArchivos(BigDecimal conocimientoid) throws Exception;
}
