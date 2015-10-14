/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.gob.mef.gescon.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import pe.gob.mef.gescon.web.bean.Politica;
import pe.gob.mef.gescon.web.bean.Pregunta;

/**
 *
 * @author JJacobo
 */
public interface PreguntaService {
    
    public BigDecimal getNextPK() throws Exception;
    public List<Pregunta> getPreguntas() throws Exception;
    public void saveOrUpdate(Pregunta pregunta) throws Exception;
    public String traerNomCategoria(BigDecimal categoriaid) throws Exception;
    public List<ArrayList> obtenerPreguntas() throws Exception;
    
}
