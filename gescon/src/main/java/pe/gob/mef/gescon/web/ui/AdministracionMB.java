/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.gob.mef.gescon.web.ui;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;
import org.primefaces.component.commandbutton.CommandButton;
import pe.gob.mef.gescon.common.Constante;
import pe.gob.mef.gescon.common.GeneralBean;
import pe.gob.mef.gescon.common.Parameters;
import pe.gob.mef.gescon.service.CategoriaService;
import pe.gob.mef.gescon.service.ConsultaService;
import pe.gob.mef.gescon.util.JSFUtils;
import pe.gob.mef.gescon.util.ServiceFinder;
import pe.gob.mef.gescon.web.bean.Admin;
import pe.gob.mef.gescon.web.bean.Categoria;
import pe.gob.mef.gescon.web.bean.Consulta;
import pe.gob.mef.gescon.web.bean.Perfil;

/**
 *
 * @author JJacobo
 */
@ManagedBean
@SessionScoped
public class AdministracionMB implements Serializable{

    private List<Admin> listaAdministracion;
    private List<Categoria> listaAllCategorias;
    private List<Categoria> listaCategoria;
    private List<Perfil> listaPerfil;
    private List<Consulta> listaDestacadosBL;
    private List<Consulta> listaDestacadosPR;
    private List<Consulta> listaDestacadosWK;
    private List<Consulta> listaDestacadosBP;
    private List<Consulta> listaDestacadosCT;
    private List<Consulta> listaDestacadosOM;
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

    public List<Categoria> getListaAllCategorias() throws Exception {
        if(listaAllCategorias == null) {
            CategoriaService catservice = (CategoriaService) ServiceFinder.findBean("CategoriaService");
            listaAllCategorias = catservice.getCategorias();
        }
        return listaAllCategorias;
    }

    public void setListaAllCategorias(List<Categoria> listaAllCategorias) {
        this.listaAllCategorias = listaAllCategorias;
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

    public List<Consulta> getListaDestacadosBL() {
        return listaDestacadosBL;
    }

    public void setListaDestacadosBL(List<Consulta> listaDestacadosBL) {
        this.listaDestacadosBL = listaDestacadosBL;
    }

    public List<Consulta> getListaDestacadosPR() {
        return listaDestacadosPR;
    }

    public void setListaDestacadosPR(List<Consulta> listaDestacadosPR) {
        this.listaDestacadosPR = listaDestacadosPR;
    }

    public List<Consulta> getListaDestacadosWK() {
        return listaDestacadosWK;
    }

    public void setListaDestacadosWK(List<Consulta> listaDestacadosWK) {
        this.listaDestacadosWK = listaDestacadosWK;
    }

    public List<Consulta> getListaDestacadosBP() {
        return listaDestacadosBP;
    }

    public void setListaDestacadosBP(List<Consulta> listaDestacadosBP) {
        this.listaDestacadosBP = listaDestacadosBP;
    }

    public List<Consulta> getListaDestacadosCT() {
        return listaDestacadosCT;
    }

    public void setListaDestacadosCT(List<Consulta> listaDestacadosCT) {
        this.listaDestacadosCT = listaDestacadosCT;
    }

    public List<Consulta> getListaDestacadosOM() {
        return listaDestacadosOM;
    }

    public void setListaDestacadosOM(List<Consulta> listaDestacadosOM) {
        this.listaDestacadosOM = listaDestacadosOM;
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
        HashMap filter = new HashMap();
        try{
            ConsultaService consultaService = (ConsultaService) ServiceFinder.findBean("ConsultaService");
            filter.put("ntipoconocimientoid", Constante.BASELEGAL);
            this.setListaDestacadosBL(consultaService.getDestacadosByTipoConocimiento(filter));
            filter.put("ntipoconocimientoid", Constante.PREGUNTAS);
            this.setListaDestacadosPR(consultaService.getDestacadosByTipoConocimiento(filter));
            filter.put("ntipoconocimientoid", Constante.WIKI);
            this.setListaDestacadosWK(consultaService.getDestacadosByTipoConocimiento(filter));
            filter.put("ntipoconocimientoid", Constante.BUENAPRACTICA);
            this.setListaDestacadosBP(consultaService.getDestacadosByTipoConocimiento(filter));
            filter.put("ntipoconocimientoid", Constante.CONTENIDO);
            this.setListaDestacadosCT(consultaService.getDestacadosByTipoConocimiento(filter));
            filter.put("ntipoconocimientoid", Constante.OPORTUNIDADMEJORA);
            this.setListaDestacadosOM(consultaService.getDestacadosByTipoConocimiento(filter));
            
            listaAdministracion = new ArrayList<Admin>();
            Admin admin = new Admin();
            admin.setNombre("Alertas");
            admin.setImagen("fa fa-bullhorn Fs26 black");
            listaAdministracion.add(admin);
            admin = new Admin();
            admin.setNombre("Parámetros");
            admin.setImagen("fa fa-gears Fs26 black");
            listaAdministracion.add(admin);
            admin = new Admin();
            admin.setNombre("Tablas Maestras");
            admin.setImagen("fa fa-table Fs26 black");
            listaAdministracion.add(admin);
            
            CategoriaService catservice = (CategoriaService) ServiceFinder.findBean("CategoriaService");
            this.setListaCategoria(catservice.getCategoriasPrimerNivel());
            for(Categoria c : this.getListaCategoria()) {
                c.setChildren(catservice.getCategoriaHijos(c));
            }
            
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
        } catch(Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
    }
    
    public String onClickSubCategoriesFilter() {
        int index;
        Categoria parent = new Categoria();
        Categoria child = new Categoria();
        try {
            String idparent = (String) JSFUtils.getRequestParameter("idparent");
            String idchild = (String) JSFUtils.getRequestParameter("idchild");
            
            parent.setNcategoriaid(BigDecimal.valueOf(Long.parseLong(idparent)));
            index = Collections.binarySearch(this.getListaCategoria(), parent, Categoria.Comparators.ID);
            parent = this.getListaCategoria().get(index);
            
            child.setNcategoriaid(BigDecimal.valueOf(Long.parseLong(idchild)));
            index = Collections.binarySearch(parent.getChildren(), child, Categoria.Comparators.ID);
            child = parent.getChildren().get(index);
            
            ConsultaMB consultaMB = new ConsultaMB();
            consultaMB.init();
            index = Collections.binarySearch(consultaMB.getListaCategoriaFiltro(), parent, Categoria.Comparators.ID);
            consultaMB.getListaBreadCrumb().add(consultaMB.getListaCategoriaFiltro().get(index));
            
            index = Collections.binarySearch(consultaMB.getListaCategoriaFiltro(), child, Categoria.Comparators.ID);
            consultaMB.getListaBreadCrumb().add(consultaMB.getListaCategoriaFiltro().get(index));
            
            consultaMB.setSelectedCategoriaFiltro(child);
            consultaMB.filtrar(new ActionEvent(new CommandButton()));
            JSFUtils.getSession().setAttribute("consultaMB", consultaMB);
            
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
        return "/pages/consulta?faces-redirect=true";
    }
    
    public String onClickCategoriesFilter() {
        int index;
        Categoria categoria = new Categoria();
        try {
            String idparent = (String) JSFUtils.getRequestParameter("idparent");
            
            categoria.setNcategoriaid(BigDecimal.valueOf(Long.parseLong(idparent)));
            index = Collections.binarySearch(this.getListaCategoria(), categoria, Categoria.Comparators.ID);
            categoria = this.getListaCategoria().get(index);
            
            ConsultaMB consultaMB = new ConsultaMB();
            consultaMB.init();
            index = Collections.binarySearch(consultaMB.getListaCategoriaFiltro(), categoria, Categoria.Comparators.ID);
            consultaMB.getListaBreadCrumb().add(consultaMB.getListaCategoriaFiltro().get(index));
            
            consultaMB.setSelectedCategoriaFiltro(categoria);
            consultaMB.filtrar(new ActionEvent(new CommandButton()));
            JSFUtils.getSession().setAttribute("consultaMB", consultaMB);
            
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
        return "/pages/consulta?faces-redirect=true";
    }
}
