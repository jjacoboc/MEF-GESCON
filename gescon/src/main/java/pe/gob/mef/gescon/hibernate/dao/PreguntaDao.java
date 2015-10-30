/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.gob.mef.gescon.hibernate.dao;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import pe.gob.mef.gescon.hibernate.domain.Tpregunta;

/**
 *
 * @author JJacobo
 */
public interface PreguntaDao {
    
    public BigDecimal getNextPK() throws Exception;
    public List<Tpregunta> getTpreguntas() throws Exception;
    public void saveOrUpdate(Tpregunta tpregunta) throws Exception;
    public List<HashMap>  traerNomCategoria(BigDecimal categoriaid) throws Exception;
    public List<HashMap> obtenerPreguntas(BigDecimal preguntaid,BigDecimal usuarioid, BigDecimal tpoconocimientoid) throws Exception;
    public List<HashMap> obtenerPreguntaxAsig(BigDecimal preguntaid,BigDecimal usuarioid, BigDecimal tpoconocimientoid) throws Exception;
    public List<HashMap> obtenerPerfilxUsuario(BigDecimal usuarioid) throws Exception;
}
