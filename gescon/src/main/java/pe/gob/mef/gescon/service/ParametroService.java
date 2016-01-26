/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.gob.mef.gescon.service;

import java.math.BigDecimal;
import java.util.List;
import pe.gob.mef.gescon.web.bean.Parametro;

/**
 *
 * @author JJacobo
 */
public interface ParametroService {
    
    BigDecimal getNextPK() throws Exception;
    List<Parametro> getParametros() throws Exception;
    List<Parametro> getParametrosActived() throws Exception;
    Parametro getParametroById(BigDecimal id) throws Exception;
    void saveOrUpdate(Parametro parametro) throws Exception;
}
