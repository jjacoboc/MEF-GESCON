/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.gob.mef.gescon.hibernate.dao;

import java.math.BigDecimal;
import java.util.List;
import pe.gob.mef.gescon.hibernate.domain.Mtcategoria;

/**
 *
 * @author JJacobo
 */
public interface CategoriaDao {
    
    public BigDecimal getNextPK() throws Exception;
    public List<Mtcategoria> getMtcategorias() throws Exception;
    public List<Mtcategoria> getMtcategoriasPrimerNivel() throws Exception;
    public List<Mtcategoria> getMtcategoriaHijos(Mtcategoria mtcategoria) throws Exception;
    public Mtcategoria getMtcategoriaById(BigDecimal id) throws Exception;
    public void saveOrUpdate(Mtcategoria mtcategoria) throws Exception;
}
