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

/**
 *
 * @author JJacobo
 */
@ManagedBean
@ApplicationScoped
public class ListaSessionMB implements Serializable{

    private List<SelectItem> listaSiNo;
    private List<SelectItem> listaRangoBaseLegal;
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
     * @return the listaRangoBaseLegal
     */
    public List<SelectItem> getListaRangoBaseLegal() {
        if(listaRangoBaseLegal == null){
            listaRangoBaseLegal =  new Items(Parameters.getListaRangoBaseLegal(), null, "codigo","descripcion").getItems();
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
