/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.gob.mef.gescon.service;

import java.math.BigDecimal;
import java.util.List;
import pe.gob.mef.gescon.web.bean.Categoria;

/**
 *
 * @author JJacobo
 */
public interface CategoriaService {
    
    BigDecimal getNextPK() throws Exception;
    List<Categoria> getCategorias() throws Exception;
    List<Categoria> getCategoriasActived() throws Exception;
    List<Categoria> getCategoriasPrimerNivel() throws Exception;
    List<Categoria> getCategoriaHijos(Categoria categoria) throws Exception;
    Categoria getCategoriaById(BigDecimal id) throws Exception;
    void saveOrUpdate(Categoria categoria) throws Exception;
}
