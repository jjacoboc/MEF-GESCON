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
import javax.faces.bean.ApplicationScoped;
import javax.faces.model.SelectItem;
import pe.gob.mef.gescon.common.Items;
import pe.gob.mef.gescon.common.Parameters;
import pe.gob.mef.gescon.service.CategoriaService;
import pe.gob.mef.gescon.service.EstadoBaseLegalService;
import pe.gob.mef.gescon.service.RangoService;
import pe.gob.mef.gescon.service.SituacionService;
import pe.gob.mef.gescon.util.ServiceFinder;

/**
 *
 * @author JJacobo
 */
@ManagedBean
@ApplicationScoped
public class ListaSessionMB implements Serializable{

    private List<SelectItem> listaSiNo;
    private List<SelectItem> listaCategoria;
    private List<SelectItem> listaRangoBaseLegal;
    private List<SelectItem> listaEstadoBaseLegal;
    private List<SelectItem> listaEstadoBaseLegalVinculo;
    private List<SelectItem> listaSituacion;
    private List<SelectItem> filterEstado;
    
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
    
}
