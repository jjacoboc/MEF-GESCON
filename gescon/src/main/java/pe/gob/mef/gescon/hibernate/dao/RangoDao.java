/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.gob.mef.gescon.hibernate.dao;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import pe.gob.mef.gescon.hibernate.domain.Mtrango;

/**
 *
 * @author JJacobo
 */
public interface RangoDao {
    
    public BigDecimal getNextPK() throws Exception;
    public List<Mtrango> getMtrangos() throws Exception;
    public List<HashMap> getTipoRangoByMaestro(BigDecimal maestroid) throws Exception;
    List<Mtrango> getMtrangosByTipo(BigDecimal id) throws Exception;
    List<Mtrango> getMtrangosActivosByTipo(BigDecimal id) throws Exception;
    public void saveOrUpdate(Mtrango mtrango) throws Exception;
}
