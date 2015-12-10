/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.gob.mef.gescon.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.stereotype.Repository;
import pe.gob.mef.gescon.hibernate.dao.CategoriaDao;
import pe.gob.mef.gescon.hibernate.domain.Mtcategoria;
import pe.gob.mef.gescon.service.CategoriaService;
import pe.gob.mef.gescon.util.ServiceFinder;
import pe.gob.mef.gescon.web.bean.Categoria;

/**
 *
 * @author JJacobo
 */
@Repository(value = "CategoriaService")
public class CategoriaServiceImpl implements CategoriaService {

    @Override
    public BigDecimal getNextPK() throws Exception {
        CategoriaDao categoriaDao = (CategoriaDao) ServiceFinder.findBean("CategoriaDao");
        return categoriaDao.getNextPK();
    }
    
    @Override
    public List<Categoria> getCategorias() throws Exception {
        List<Categoria> categorias = new ArrayList<Categoria>();
        CategoriaDao categoriaDao = (CategoriaDao) ServiceFinder.findBean("CategoriaDao");
        List<Mtcategoria> lista = categoriaDao.getMtcategorias();
        for(Mtcategoria mtcategoria : lista) {
            Categoria categoria = new Categoria();
            BeanUtils.copyProperties(categoria, mtcategoria);
            categorias.add(categoria);
        }
        return categorias;
    }
    
    @Override
    public List<Categoria> getCategoriasActived() throws Exception {
        List<Categoria> categorias = new ArrayList<Categoria>();
        CategoriaDao categoriaDao = (CategoriaDao) ServiceFinder.findBean("CategoriaDao");
        List<Mtcategoria> lista = categoriaDao.getMtcategoriasActived();
        for(Mtcategoria mtcategoria : lista) {
            Categoria categoria = new Categoria();
            BeanUtils.copyProperties(categoria, mtcategoria);
            categorias.add(categoria);
        }
        return categorias;
    }
    
    @Override
    public List<Categoria> getCategoriasPrimerNivel() throws Exception {
        List<Categoria> categorias = new ArrayList<Categoria>();
        CategoriaDao categoriaDao = (CategoriaDao) ServiceFinder.findBean("CategoriaDao");
        List<Mtcategoria> lista = categoriaDao.getMtcategoriasPrimerNivel();
        for(Mtcategoria mtcategoria : lista) {
            Categoria categoria = new Categoria();
            BeanUtils.copyProperties(categoria, mtcategoria);
            categorias.add(categoria);
        }
        return categorias;
    }
    
    @Override
    public List<Categoria> getCategoriaHijos(Categoria categoria) throws Exception {
        List<Categoria> categorias = new ArrayList<Categoria>();
        CategoriaDao categoriaDao = (CategoriaDao) ServiceFinder.findBean("CategoriaDao");
        Mtcategoria mtcategoria = new Mtcategoria();
        BeanUtils.copyProperties(mtcategoria, categoria);
        List<Mtcategoria> lista = categoriaDao.getMtcategoriaHijos(mtcategoria);
        for(Mtcategoria ele : lista) {
            Categoria bean = new Categoria();
            BeanUtils.copyProperties(bean, ele);
            categorias.add(bean);
        }
        return categorias;
    }
    
    @Override
    public Categoria getCategoriaById(BigDecimal id) throws Exception {
        Categoria categoria = new Categoria();
        CategoriaDao categoriaDao = (CategoriaDao) ServiceFinder.findBean("CategoriaDao");
        Mtcategoria mtcategoria = categoriaDao.getMtcategoriaById(id);
        if(mtcategoria != null) {
            BeanUtils.copyProperties(categoria, mtcategoria);
        } else {
            categoria = null;
        }
        return categoria;
    }

    @Override
    public void saveOrUpdate(Categoria categoria) throws Exception {
        Mtcategoria mtcategoria = new Mtcategoria();
        BeanUtils.copyProperties(mtcategoria, categoria);
        CategoriaDao categoriaDao = (CategoriaDao) ServiceFinder.findBean("CategoriaDao");
        categoriaDao.saveOrUpdate(mtcategoria);
    }
    
}
