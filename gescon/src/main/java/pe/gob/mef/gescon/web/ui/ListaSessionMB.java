/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.gob.mef.gescon.web.ui;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;
import pe.gob.mef.gescon.common.Constante;
import pe.gob.mef.gescon.common.Items;
import pe.gob.mef.gescon.common.Parameters;
import pe.gob.mef.gescon.service.CategoriaService;
import pe.gob.mef.gescon.service.EstadoBaseLegalService;
import pe.gob.mef.gescon.service.MaestroDetalleService;
import pe.gob.mef.gescon.service.RangoService;
import pe.gob.mef.gescon.service.SituacionService;
import pe.gob.mef.gescon.service.TipoConocimientoService;
import pe.gob.mef.gescon.util.ServiceFinder;
import pe.gob.mef.gescon.web.bean.Maestro;

/**
 *
 * @author JJacobo
 */
@ManagedBean
@SessionScoped
public class ListaSessionMB implements Serializable{

    private List<SelectItem> listaSiNo;
    private List<SelectItem> listaModulos;
    private List<SelectItem> listaModulosActivos;
    private List<SelectItem> listaTipoRango;
    private List<SelectItem> listaTipoRangoActivo;
    private List<SelectItem> listaCategoria;
    private List<SelectItem> listaCategoriaActiva;
    private List<SelectItem> listaRangoBaseLegal;
    private List<SelectItem> listaEstadoBaseLegal;
    private List<SelectItem> listaEstadoBaseLegalVinculo;
    private List<SelectItem> listaSituacion;
    private List<SelectItem> listaTipoConocimiento;
    private List<SelectItem> listaTipoDiscusion;
    private List<SelectItem> listaTipoDiscusionActivo;
    private List<SelectItem> filterEstado;
    private List<SelectItem> filterModulos;
    
    /**
     * Creates a new instance of ListaSessionMB
     */
    public ListaSessionMB() {
    }

    /**
     * @return the listaSiNo
     */
    public List<SelectItem> getListaSiNo() {
        if(listaSiNo == null){
            listaSiNo =  new Items(Parameters.getListaSiNo(), null, "codigo","descripcion").getItems();
        }
        return listaSiNo;
    }

    /**
     * @param listaSiNo the listaSiNo to set
     */
    public void setListaSiNo(List<SelectItem> listaSiNo) {
        this.listaSiNo = listaSiNo;
    }

    public List<SelectItem> getListaModulos() throws Exception {
        if(listaModulos == null){
            Maestro maestro = new Maestro();
            maestro.setNmaestroid(Constante.MAESTRO_MODULOS);
            MaestroDetalleService service = (MaestroDetalleService) ServiceFinder.findBean("MaestroDetalleService");
            listaModulos =  new Items(service.getDetallesByMaestro(maestro), null, "ndetalleid","vnombre").getItems();
        }
        return listaModulos;
    }

    public void setListaModulos(List<SelectItem> listaModulos) {
        this.listaModulos = listaModulos;
    }

    public List<SelectItem> getListaModulosActivos() throws Exception {
        if(listaModulosActivos == null){
            Maestro maestro = new Maestro();
            maestro.setNmaestroid(Constante.MAESTRO_MODULOS);
            MaestroDetalleService service = (MaestroDetalleService) ServiceFinder.findBean("MaestroDetalleService");
            listaModulosActivos =  new Items(service.getDetallesActivosByMaestro(maestro), null, "ndetalleid","vnombre").getItems();
        }
        return listaModulosActivos;
    }

    public void setListaModulosActivos(List<SelectItem> listaModulosActivos) {
        this.listaModulosActivos = listaModulosActivos;
    }

    public List<SelectItem> getListaTipoRango() throws Exception {
        if(listaTipoRango == null){
            Maestro maestro = new Maestro();
            maestro.setNmaestroid(Constante.MAESTRO_RANGOS);
            MaestroDetalleService service = (MaestroDetalleService) ServiceFinder.findBean("MaestroDetalleService");
            listaTipoRango =  new Items(service.getDetallesByMaestro(maestro), null, "ndetalleid","vnombre").getItems();
        }
        return listaTipoRango;
    }

    public void setListaTipoRango(List<SelectItem> listaTipoRango) {
        this.listaTipoRango = listaTipoRango;
    }

    public List<SelectItem> getListaTipoRangoActivo() throws Exception {
        if(listaTipoRangoActivo == null){
            Maestro maestro = new Maestro();
            maestro.setNmaestroid(Constante.MAESTRO_RANGOS);
            MaestroDetalleService service = (MaestroDetalleService) ServiceFinder.findBean("MaestroDetalleService");
            listaTipoRangoActivo =  new Items(service.getDetallesActivosByMaestro(maestro), null, "ndetalleid","vnombre").getItems();
        }
        return listaTipoRangoActivo;
    }

    public void setListaTipoRangoActivo(List<SelectItem> listaTipoRangoActivo) {
        this.listaTipoRangoActivo = listaTipoRangoActivo;
    }

    /**
     * @return the listaCategoria
     * @throws java.lang.Exception
     */
    public List<SelectItem> getListaCategoria() throws Exception {
        if(listaCategoria == null){
            CategoriaService service = (CategoriaService) ServiceFinder.findBean("CategoriaService");
            listaCategoria =  new Items(service.getCategorias(), null, "ncategoriaid","vnombre").getItems();
        }
        return listaCategoria;
    }

    /**
     * @param listaCategoria the listaCategoria to set
     */
    public void setListaCategoria(List<SelectItem> listaCategoria) {
        this.listaCategoria = listaCategoria;
    }

    public List<SelectItem> getListaCategoriaActiva() throws Exception {
        if(listaCategoriaActiva == null){
            CategoriaService service = (CategoriaService) ServiceFinder.findBean("CategoriaService");
            listaCategoriaActiva =  new Items(service.getCategoriasActived(), null, "ncategoriaid","vnombre").getItems();
        }
        return listaCategoriaActiva;
    }

    public void setListaCategoriaActiva(List<SelectItem> listaCategoriaActiva) {
        this.listaCategoriaActiva = listaCategoriaActiva;
    }

    /**
     * @return the listaRangoBaseLegal
     * @throws java.lang.Exception
     */
    public List<SelectItem> getListaRangoBaseLegal() throws Exception {
        if(listaRangoBaseLegal == null){
            RangoService service = (RangoService) ServiceFinder.findBean("RangoService");
            listaRangoBaseLegal =  new Items(service.getRangos(), null, "nrangoid","vnombre").getItems();
        }
        return listaRangoBaseLegal;
    }

    /**
     * @param listaRangoBaseLegal the listaRangoBaseLegal to set
     */
    public void setListaRangoBaseLegal(List<SelectItem> listaRangoBaseLegal) {
        this.listaRangoBaseLegal = listaRangoBaseLegal;
    }

    /**
     * @return the listaEstadoBaseLegal
     * @throws java.lang.Exception
     */
    public List<SelectItem> getListaEstadoBaseLegal() throws Exception {
        if(listaEstadoBaseLegal == null){
            EstadoBaseLegalService service = (EstadoBaseLegalService) ServiceFinder.findBean("EstadoBaseLegalService");
            listaEstadoBaseLegal =  new Items(service.getEstadosBaselegal(), null, "nestadoid","vnombre").getItems();
        }
        return listaEstadoBaseLegal;
    }

    /**
     * @param listaEstadoBaseLegal the listaEstadoBaseLegal to set
     */
    public void setListaEstadoBaseLegal(List<SelectItem> listaEstadoBaseLegal) {
        this.listaEstadoBaseLegal = listaEstadoBaseLegal;
    }

    /**
     * @return the listaEstadoBaseLegalVinculo
     * @throws java.lang.Exception
     */
    public List<SelectItem> getListaEstadoBaseLegalVinculo() throws Exception {
        if(listaEstadoBaseLegalVinculo == null){
            EstadoBaseLegalService service = (EstadoBaseLegalService) ServiceFinder.findBean("EstadoBaseLegalService");
            listaEstadoBaseLegalVinculo =  new Items(service.getEstadosBaselegalToLink(), null, "nestadoid","vnombre").getItems();
        }
        return listaEstadoBaseLegalVinculo;
    }

    /**
     * @param listaEstadoBaseLegalVinculo the listaEstadoBaseLegalVinculo to set
     */
    public void setListaEstadoBaseLegalVinculo(List<SelectItem> listaEstadoBaseLegalVinculo) {
        this.listaEstadoBaseLegalVinculo = listaEstadoBaseLegalVinculo;
    }

    /**
     * @return the listaSituacion
     * @throws java.lang.Exception
     */
    public List<SelectItem> getListaSituacion() throws Exception {
        if(listaSituacion == null){
            SituacionService service = (SituacionService) ServiceFinder.findBean("SituacionService");
            listaSituacion =  new Items(service.getSituacions(), null, "nsituacionid","vnombre").getItems();
        }
        return listaSituacion;
    }

    /**
     * @param listaSituacion the listaSituacion to set
     */
    public void setListaSituacion(List<SelectItem> listaSituacion) {
        this.listaSituacion = listaSituacion;
    }

    public List<SelectItem> getListaTipoConocimiento() throws Exception {
        if(listaTipoConocimiento == null){
            TipoConocimientoService service = (TipoConocimientoService) ServiceFinder.findBean("TipoConocimientoService");
            listaTipoConocimiento =  new Items(service.getTipoConocimientos(), null, "ntpoconocimientoid","vnombre").getItems();
        }
        return listaTipoConocimiento;
    }

    public void setListaTipoConocimiento(List<SelectItem> listaTipoConocimiento) {
        this.listaTipoConocimiento = listaTipoConocimiento;
    }

    public List<SelectItem> getListaTipoDiscusion() throws Exception {
        if(listaTipoDiscusion == null){
            Maestro maestro = new Maestro();
            maestro.setNmaestroid(Constante.MAESTRO_TIPODISCUSION);
            MaestroDetalleService service = (MaestroDetalleService) ServiceFinder.findBean("MaestroDetalleService");
            listaTipoDiscusion =  new Items(service.getDetallesByMaestro(maestro), null, "ndetalleid","vnombre").getItems();
        }
        return listaTipoDiscusion;
    }

    public void setListaTipoDiscusion(List<SelectItem> listaTipoDiscusion) {
        this.listaTipoDiscusion = listaTipoDiscusion;
    }

    public List<SelectItem> getListaTipoDiscusionActivo() throws Exception {
        if(listaTipoDiscusionActivo == null){
            Maestro maestro = new Maestro();
            maestro.setNmaestroid(Constante.MAESTRO_TIPODISCUSION);
            MaestroDetalleService service = (MaestroDetalleService) ServiceFinder.findBean("MaestroDetalleService");
            listaTipoDiscusionActivo =  new Items(service.getDetallesActivosByMaestro(maestro), null, "ndetalleid","vnombre").getItems();
        }
        return listaTipoDiscusionActivo;
    }

    public void setListaTipoDiscusionActivo(List<SelectItem> listaTipoDiscusionActivo) {
        this.listaTipoDiscusionActivo = listaTipoDiscusionActivo;
    }

    /**
     * @return the filterEstado
     */
    public List<SelectItem> getFilterEstado() {
        if(filterEstado == null){
            filterEstado =  new ArrayList<SelectItem>();
            filterEstado.add(new SelectItem("", "Todos"));
            filterEstado.addAll(new Items(Parameters.getListaEstado(), null, "codigo","descripcion").getItems());
        }
        return filterEstado;
    }

    /**
     * @param filterEstado the filterEstado to set
     */
    public void setFilterEstado(List<SelectItem> filterEstado) {
        this.filterEstado = filterEstado;
    }

    public List<SelectItem> getFilterModulos() throws Exception {
        if(filterModulos == null){
            Maestro maestro = new Maestro();
            maestro.setNmaestroid(Constante.MAESTRO_MODULOS);
            MaestroDetalleService service = (MaestroDetalleService) ServiceFinder.findBean("MaestroDetalleService");
            filterModulos =  new ArrayList<SelectItem>();
            filterModulos.add(new SelectItem("", "Todos"));
            filterModulos.addAll(new Items(service.getDetallesByMaestro(maestro), null, "ndetalleid","vnombre").getItems());
        }
        return filterModulos;
    }

    public void setFilterModulos(List<SelectItem> filterModulos) {
        this.filterModulos = filterModulos;
    }
    
}
