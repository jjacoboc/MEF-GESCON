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
import pe.gob.mef.gescon.web.bean.Consulta;
import pe.gob.mef.gescon.web.bean.Pregunta;

/**
 *
 * @author JJacobo
 */
public interface PreguntaService {
    
    BigDecimal getNextPK() throws Exception;
    List<Pregunta> getPreguntas() throws Exception;
    Pregunta getPreguntaById(BigDecimal id) throws Exception;
    void saveOrUpdate(Pregunta pregunta) throws Exception;
    String traerNomCategoria(BigDecimal categoriaid) throws Exception;
    List<Pregunta> obtenerPreguntas(BigDecimal preguntaid, BigDecimal usuarioid, BigDecimal tpoconocimientoid) throws Exception;
    List<Asignacion> obtenerPreguntaxAsig(BigDecimal preguntaid, BigDecimal usuarioid, BigDecimal tpoconocimientoid) throws Exception;
    BigDecimal obtenerPerfilxUsuario(BigDecimal usuarioid) throws Exception;
    String getNomEntidadbyIdEntidad(BigDecimal entidadid) throws Exception;
    List<Consulta> getConcimientosVinculados(HashMap filters);
    List<Consulta> getConcimientosDisponibles(HashMap filters);
    void delete(BigDecimal preguntaid) throws Exception;    
}
