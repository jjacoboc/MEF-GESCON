/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.gob.mef.gescon.service;

import java.math.BigDecimal;
import java.util.List;
import pe.gob.mef.gescon.web.bean.RespuestaHist;


/**
 *
 * @author JJacobo
 */
public interface RespuestaHistService {
    
    public BigDecimal getNextPK() throws Exception;
    public List<RespuestaHist> getHistorialByPregunta(BigDecimal npreguntaid) throws Exception;
    public void saveOrUpdate(RespuestaHist respuestahist) throws Exception;
    
    
}
