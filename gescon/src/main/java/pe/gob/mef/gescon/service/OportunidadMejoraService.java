/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.gob.mef.gescon.service;

import java.math.BigDecimal;
import java.util.List;
import pe.gob.mef.gescon.web.bean.OportunidadMejora;

/**
 *
 * @author CNishimura
 */
public interface OportunidadMejoraService {
    
    public BigDecimal getNextPK() throws Exception;
    public List<OportunidadMejora> getOportunidadmejoras() throws Exception;
    public OportunidadMejora getOportunidadmejoraById(BigDecimal id) throws Exception;
    public List<OportunidadMejora> getToportunidadmejorasLinkedById(final BigDecimal id) throws Exception;
    public List<OportunidadMejora> getToportunidadmejorasNotLinkedById(final BigDecimal id) throws Exception;
    public void saveOrUpdate(OportunidadMejora oportunidadMejora) throws Exception;
}
