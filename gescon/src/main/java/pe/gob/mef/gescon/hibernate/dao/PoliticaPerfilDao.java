/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.gob.mef.gescon.hibernate.dao;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import pe.gob.mef.gescon.hibernate.domain.TpoliticaPerfil;

/**
 *
 * @author JJacobo
 */
public interface PoliticaPerfilDao {

    public List<TpoliticaPerfil> getTpoliticaperfil() throws Exception;
    public void saveOrUpdate(TpoliticaPerfil tpoliticaperfil) throws Exception;
    public List<HashMap> obtenerListaPoliticasDisp(BigDecimal perfilid) throws Exception;
    public List<HashMap> obtenerListaPoliticas(BigDecimal perfilid) throws Exception;
    public void delete(BigDecimal perfilid) throws Exception;
    
}
