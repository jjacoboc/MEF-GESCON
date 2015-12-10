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
    
    BigDecimal getNextPK() throws Exception;
    List<Mtcategoria> getMtcategorias() throws Exception;
    List<Mtcategoria> getMtcategoriasActived() throws Exception;
    List<Mtcategoria> getMtcategoriasPrimerNivel() throws Exception;
    List<Mtcategoria> getMtcategoriaHijos(Mtcategoria mtcategoria) throws Exception;
    Mtcategoria getMtcategoriaById(BigDecimal id) throws Exception;
    void saveOrUpdate(Mtcategoria mtcategoria) throws Exception;
}
