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
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.primefaces.context.RequestContext;
import org.springframework.util.CollectionUtils;
import pe.gob.mef.gescon.common.Constante;
import pe.gob.mef.gescon.common.Items;
import pe.gob.mef.gescon.service.RangoService;
import pe.gob.mef.gescon.util.JSFUtils;
import pe.gob.mef.gescon.util.ServiceFinder;
import pe.gob.mef.gescon.web.bean.Rango;
import pe.gob.mef.gescon.web.bean.User;

/**
 *
 * @author JJacobo
 */
@ManagedBean
@ViewScoped
public class RangoMB implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final Log log = LogFactory.getLog(RangoMB.class);
    private BigDecimal id;
    private String nombre;
    private String descripcion;
    private BigDecimal activo;
    private List<Rango> listaRango;
    private List<Rango> filteredListaRango;
    private Rango selectedRango;
    private List<SelectItem> listaTipoNorma;
    private BigDecimal selectedTipoNorma;

    /**
     * Creates a new instance of RangoMB
     */
    public RangoMB() {
    }

    /**
     * @return the id
     */
    public BigDecimal getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(BigDecimal id) {
        this.id = id;
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * @param descripcion the descripcion to set
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * @return the activo
     */
    public BigDecimal getActivo() {
        return activo;
    }

    /**
     * @param activo the activo to set
     */
    public void setActivo(BigDecimal activo) {
        this.activo = activo;
    }

    /**
     * @return the listaRango
     */
    public List<Rango> getListaRango() {
        return listaRango;
    }

    /**
     * @param listaRango the listaRango to set
     */
    public void setListaRango(List<Rango> listaRango) {
        this.listaRango = listaRango;
    }

    /**
     * @return the filteredListaRango
     */
    public List<Rango> getFilteredListaRango() {
        return filteredListaRango;
    }

    /**
     * @param filteredListaRango the filteredListaRango to set
     */
    public void setFilteredListaRango(List<Rango> filteredListaRango) {
        this.filteredListaRango = filteredListaRango;
    }

    /**
     * @return the selectedRango
     */
    public Rango getSelectedRango() {
        return selectedRango;
    }

    /**
     * @param selectedRango the selectedRango to set
     */
    public void setSelectedRango(Rango selectedRango) {
        this.selectedRango = selectedRango;
    }

    /**
     * @return the listaTipoNorma
     * @throws java.lang.Exception
     */
    public List<SelectItem> getListaTipoNorma() throws Exception  {
        if (listaTipoNorma == null) {
            RangoService service = (RangoService) ServiceFinder.findBean("RangoService");
            listaTipoNorma = new Items(service.getTipoRangoByMaestro(Constante.MAESTRO_RANGOS), null, "ndetalleid", "vnombre").getItems();
        }
        return listaTipoNorma;
    }

    /**
     * @param listaTipoNorma the listaTipoNorma to set
     */
    public void setListaTipoNorma(List<SelectItem> listaTipoNorma) {
        this.listaTipoNorma = listaTipoNorma;
    }

    /**
     * @return the selectedTipoNorma
     */
    public BigDecimal getSelectedTipoNorma() {
        return selectedTipoNorma;
    }

    /**
     * @param selectedTipoNorma the selectedTipoNorma to set
     */
    public void setSelectedTipoNorma(BigDecimal selectedTipoNorma) {
        this.selectedTipoNorma = selectedTipoNorma;
    }

    @PostConstruct
    public void init() {
        try {
            RangoService service = (RangoService) ServiceFinder.findBean("RangoService");
            listaRango = service.getRangos();
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
    }

    public void cleanAttributes() {
        this.setId(BigDecimal.ZERO);
        this.setDescripcion(StringUtils.EMPTY);
        this.setNombre(StringUtils.EMPTY);
        this.setActivo(BigDecimal.ONE);
        this.setSelectedRango(null);
        Iterator<FacesMessage> iter = FacesContext.getCurrentInstance().getMessages();
        if (iter.hasNext() == true) {
            iter.remove();
            FacesContext.getCurrentInstance().renderResponse();
        }
    }

    public void setSelectedRow(ActionEvent event) {
        try {
            if (event != null) {
                int index = Integer.parseInt((String) JSFUtils.getRequestParameter("index"));
                if (!CollectionUtils.isEmpty(this.getFilteredListaRango())) {
                    this.setSelectedRango(this.getFilteredListaRango().get(index));
                } else {
                    this.setSelectedRango(this.getListaRango().get(index));
                }
                this.setFilteredListaRango(new ArrayList());
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
    }

    public void toSave(ActionEvent event) {
        try {
            this.cleanAttributes();
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
    }

    public void save(ActionEvent event) {
        try {
            if (CollectionUtils.isEmpty(this.getListaRango())) {
                this.setListaRango(Collections.EMPTY_LIST);
            }
            Rango rango = new Rango();
            rango.setVnombre(this.getNombre());
            rango.setVdescripcion(this.getDescripcion());
            if (!errorValidation(rango)) {
                LoginMB loginMB = (LoginMB) JSFUtils.getSessionAttribute("loginMB");
                User user = loginMB.getUser();
                RangoService service = (RangoService) ServiceFinder.findBean("RangoService");
                rango.setNrangoid(service.getNextPK());
                rango.setVnombre(StringUtils.upperCase(this.getNombre().trim()));
                rango.setVdescripcion(StringUtils.capitalize(this.getDescripcion().trim()));
                rango.setNactivo(BigDecimal.ONE);
                rango.setNtiponormaid(this.getSelectedTipoNorma());
                rango.setDfechacreacion(new Date());
                rango.setVusuariocreacion(user.getVlogin());
                service.saveOrUpdate(rango);
                this.setListaRango(service.getRangos());
                this.cleanAttributes();
                RequestContext.getCurrentInstance().execute("PF('newDialog').hide();");
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
    }

    public void toUpdate(ActionEvent event) {
        try {
            if (event != null) {
                this.setSelectedRow(event);
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
    }

    public void update(ActionEvent event) {
        try {
            if (event != null) {
                if (StringUtils.isBlank(this.getSelectedRango().getVnombre())) {
                    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, Constante.SEVERETY_ALERTA, "Nombre requerido. Ingrese el nombre del Rango.");
                    FacesContext.getCurrentInstance().addMessage(null, message);
                    return;
                }
                if (StringUtils.isBlank(this.getSelectedRango().getVdescripcion())) {
                    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, Constante.SEVERETY_ALERTA, "Descripción requerida. Ingrese la descripción del Rango.");
                    FacesContext.getCurrentInstance().addMessage(null, message);
                    return;
                }
                LoginMB loginMB = (LoginMB) JSFUtils.getSessionAttribute("loginMB");
                User user = loginMB.getUser();
                this.getSelectedRango().setVnombre(StringUtils.upperCase(this.getSelectedRango().getVnombre().trim()));
                this.getSelectedRango().setVdescripcion(StringUtils.capitalize(this.getSelectedRango().getVdescripcion().trim()));
                this.getSelectedRango().setNtiponormaid(this.getSelectedRango().getNtiponormaid());
                this.getSelectedRango().setVusuariomodificacion(user.getVlogin());
                this.getSelectedRango().setDfechamodificacion(new Date());
                RangoService service = (RangoService) ServiceFinder.findBean("RangoService");
                service.saveOrUpdate(this.getSelectedRango());
                this.setListaRango(service.getRangos());
                this.cleanAttributes();
                RequestContext.getCurrentInstance().execute("PF('editDialog').hide();");
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
    }

    public void activar(ActionEvent event) {
        try {
            if (event != null) {
                if (this.getSelectedRango() != null) {
                    LoginMB loginMB = (LoginMB) JSFUtils.getSessionAttribute("loginMB");
                    User user = loginMB.getUser();
                    RangoService service = (RangoService) ServiceFinder.findBean("RangoService");
                    this.getSelectedRango().setNactivo(BigDecimal.ONE);
                    this.getSelectedRango().setDfechamodificacion(new Date());
                    this.getSelectedRango().setVusuariomodificacion(user.getVlogin());
                    service.saveOrUpdate(this.getSelectedRango());
                    this.setListaRango(service.getRangos());
                } else {
                    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, Constante.SEVERETY_ALERTA, "Debe seleccionar el rango a activar.");
                    FacesContext.getCurrentInstance().addMessage(null, message);
                }
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
    }

    public void desactivar(ActionEvent event) {
        try {
            if (event != null) {
                if (this.getSelectedRango() != null) {
                    LoginMB loginMB = (LoginMB) JSFUtils.getSessionAttribute("loginMB");
                    User user = loginMB.getUser();
                    RangoService service = (RangoService) ServiceFinder.findBean("RangoService");
                    this.getSelectedRango().setNactivo(BigDecimal.ZERO);
                    this.getSelectedRango().setDfechamodificacion(new Date());
                    this.getSelectedRango().setVusuariomodificacion(user.getVlogin());
                    service.saveOrUpdate(this.getSelectedRango());
                    this.setListaRango(service.getRangos());
                } else {
                    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, Constante.SEVERETY_ALERTA, "Debe seleccionar el rango a desactivar.");
                    FacesContext.getCurrentInstance().addMessage(null, message);
                }
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
    }

    public boolean errorValidation(Rango rango) {
        FacesMessage message;
        boolean error = false;
        try {
            if (rango.getVnombre() == null || rango.getVnombre().isEmpty()) {
                message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR.", "Nombre requerido. Ingrese el nombre del rango.");
                FacesContext.getCurrentInstance().addMessage(null, message);
                error = true;
                return error;
            } else if (rango.getVdescripcion() == null || rango.getVdescripcion().isEmpty()) {
                message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR.", "Descripción requerida. Ingrese la descripción del rango.");
                FacesContext.getCurrentInstance().addMessage(null, message);
                error = true;
                return error;
            }
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return error;
    }
}
