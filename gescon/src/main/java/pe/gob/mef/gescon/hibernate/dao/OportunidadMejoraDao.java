/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.gob.mef.gescon.hibernate.dao;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import pe.gob.mef.gescon.hibernate.domain.Toportunidadmejora;

/**
 *
 * @author CNishimura
 */
public interface OportunidadMejoraDao {
    
    public BigDecimal getNextPK() throws Exception;
    public List<Toportunidadmejora> getToportunidadmejoras() throws Exception;
    public Toportunidadmejora getToportunidadmejoraById(BigDecimal id) throws Exception;
    public List<HashMap> getToportunidadmejorasLinkedById(BigDecimal id) throws Exception;
    public List<HashMap> getToportunidadmejorasNotLinkedById(BigDecimal id) throws Exception;
    public void saveOrUpdate(Toportunidadmejora toportunidadmejora) throws Exception;
}
