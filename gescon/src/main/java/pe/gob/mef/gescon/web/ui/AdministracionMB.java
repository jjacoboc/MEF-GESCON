/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.gob.mef.gescon.web.ui;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import pe.gob.mef.gescon.common.GeneralBean;
import pe.gob.mef.gescon.common.Parameters;
import pe.gob.mef.gescon.web.bean.Admin;
import pe.gob.mef.gescon.web.bean.Categoria;
import pe.gob.mef.gescon.web.bean.Perfil;

/**
 *
 * @author JJacobo
 */
@ManagedBean
@ViewScoped
public class AdministracionMB implements Serializable{

    private List<Admin> listaAdministracion;
    private List<Categoria> listaCategoria;
    private List<Perfil> listaPerfil;
    private List<GeneralBean> themes;
    private String theme;
    
    /**
     * Creates a new instance of AdministracionMB
     */
    public AdministracionMB() {
    }

    /**
     * @return the listaAdministracion
     */
    public List<Admin> getListaAdministracion() {
        return listaAdministracion;
    }

    /**
     * @param listaAdministracion the listaAdministracion to set
     */
    public void setListaAdministracion(List<Admin> listaAdministracion) {
        this.listaAdministracion = listaAdministracion;
    }

    /**
     * @return the listaCategoria
     */
    public List<Categoria> getListaCategoria() {
        return listaCategoria;
    }

    /**
     * @param listaCategoria the listaCategoria to set
     */
    public void setListaCategoria(List<Categoria> listaCategoria) {
        this.listaCategoria = listaCategoria;
    }

    /**
     * @return the listaPerfil
     */
    public List<Perfil> getListaPerfil() {
        return listaPerfil;
    }

    /**
     * @param listaPerfil the listaPerfil to set
     */
    public void setListaPerfil(List<Perfil> listaPerfil) {
        this.listaPerfil = listaPerfil;
    }

    /**
     * @return the themes
     */
    public List<GeneralBean> getThemes() {
        if (themes == null) {
            themes = Parameters.getListaThemes();
        }
        return themes;
    }

    /**
     * @param themes the themes to set
     */
    public void setThemes(List<GeneralBean> themes) {
        this.themes = themes;
    }

    /**
     * @return the theme
     */
    public String getTheme() {
        return theme;
    }

    /**
     * @param theme the theme to set
     */
    public void setTheme(String theme) {
        this.theme = theme;
    }
    
    @PostConstruct
    public void init(){
        try{
            listaAdministracion = new ArrayList<Admin>();
            Admin admin = new Admin();
            admin.setNombre("Alertas");
            admin.setImagen("Alarm-Bell.png");
            listaAdministracion.add(admin);
            admin = new Admin();
            admin.setNombre("Parámetros");
            admin.setImagen("Widgets.png");
            listaAdministracion.add(admin);
            admin = new Admin();
            admin.setNombre("Tablas Maestras");
            admin.setImagen("Table-Multiple.png");
            listaAdministracion.add(admin);
            
            listaCategoria = new ArrayList<Categoria>();
            Categoria categoria = new Categoria();
            categoria.setVnombre("Aplicativos Informaticos");
            categoria.setImagen("Installer-Box.png");
            listaCategoria.add(categoria);
            categoria = new Categoria();
            categoria.setVnombre("Normatividad");
            categoria.setImagen("Auction-Hammer-Gavel.png");
            listaCategoria.add(categoria);
            categoria = new Categoria();
            categoria.setVnombre("Procedimientos");
            categoria.setImagen("To-Do-List-Cheked-All.png");
            listaCategoria.add(categoria);
            categoria = new Categoria();
            categoria.setVnombre("Capacitacion");
            categoria.setImagen("Blackboard-Drawing.png");
            listaCategoria.add(categoria);
            
            listaPerfil = new ArrayList<Perfil>();
            Perfil perfil = new Perfil();
            perfil.setVdescripcion("Usuario Interno");
            perfil.setImagen("User-Red.png");
            listaPerfil.add(perfil);
            perfil = new Perfil();
            perfil.setVdescripcion("Usuario Externo");
            perfil.setImagen("User-Green.png");
            listaPerfil.add(perfil);
            perfil = new Perfil();
            perfil.setVdescripcion("Especialista");
            perfil.setImagen("Reseller-Account.png");
            listaPerfil.add(perfil);
            perfil = new Perfil();
            perfil.setVdescripcion("Moderador");
            perfil.setImagen("User-Policeman.png");
            listaPerfil.add(perfil);
            perfil = new Perfil();
            perfil.setVdescripcion("Administrador");
            perfil.setImagen("Administrator.png");
            listaPerfil.add(perfil);
            perfil = new Perfil();
            perfil.setVdescripcion("Políticas de Acceso");
            perfil.setImagen("Book-Spelling.png");
            listaPerfil.add(perfil);
            
//            CategoriaService service = (CategoriaService) ServiceFinder.findBean("CategoriaService");
//            listaCategoria = service.getCategoriaPrimerNivel();
        } catch(Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
    }
}
