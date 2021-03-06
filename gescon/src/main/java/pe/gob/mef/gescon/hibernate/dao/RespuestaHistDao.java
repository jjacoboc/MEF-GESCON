/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.gob.mef.gescon.hibernate.dao;

import java.math.BigDecimal;
import java.util.List;
import pe.gob.mef.gescon.hibernate.domain.TrespuestaHist;

/**
 *
 * @author JJacobo
 */
public interface RespuestaHistDao {
    
    public BigDecimal getNextPK() throws Exception;
    public List<TrespuestaHist> getThistorialByTpregunta(BigDecimal npreguntaid) throws Exception;
    public void saveOrUpdate(TrespuestaHist trespuestahist) throws Exception;
}
