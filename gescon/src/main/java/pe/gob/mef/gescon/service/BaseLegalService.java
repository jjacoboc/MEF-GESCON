/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.gob.mef.gescon.service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import pe.gob.mef.gescon.web.bean.BaseLegal;

/**
 *
 * @author JJacobo
 */
public interface BaseLegalService {
    
    public BigDecimal getNextPK() throws Exception;
    public List<BaseLegal> getBaselegales() throws Exception;
    public BaseLegal getBaselegalById(BigDecimal id) throws Exception;
    public List<BaseLegal> getTbaselegalesLinkedById(final BigDecimal id) throws Exception;
    public List<BaseLegal> getTbaselegalesNotLinkedById(final BigDecimal id) throws Exception;
    public void saveOrUpdate(BaseLegal baseLegal) throws Exception;
}
