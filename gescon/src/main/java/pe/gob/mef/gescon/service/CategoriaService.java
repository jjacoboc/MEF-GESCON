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
    
    public BigDecimal getNextPK() throws Exception;
    public List<Categoria> getCategorias() throws Exception;
    public List<Categoria> getCategoriasPrimerNivel() throws Exception;
    public List<Categoria> getCategoriaHijos(Categoria categoria) throws Exception;
    public Categoria getCategoriaById(BigDecimal id) throws Exception;
    public void saveOrUpdate(Categoria categoria) throws Exception;
}
