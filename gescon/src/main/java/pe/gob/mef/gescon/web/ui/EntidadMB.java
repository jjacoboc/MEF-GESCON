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
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.primefaces.context.RequestContext;
import org.springframework.util.CollectionUtils;
import pe.gob.mef.gescon.common.Constante;
import pe.gob.mef.gescon.service.EntidadService;
import pe.gob.mef.gescon.util.JSFUtils;
import pe.gob.mef.gescon.util.ServiceFinder;
import pe.gob.mef.gescon.web.bean.Entidad;
import pe.gob.mef.gescon.web.bean.User;

/**
 *
 * @author JJacobo
 */
@ManagedBean
@ViewScoped
public class EntidadMB implements Serializable{

    private static final long serialVersionUID = 1L;
    private static final Log log = LogFactory.getLog(EntidadMB.class);
    private BigDecimal id;
    private String nombre;
    private BigDecimal codigo;
    private String descripcion;
    private BigDecimal activo;
    private List<Entidad> listaEntidad;
    private List<Entidad> filteredListaEntidad;
    private Entidad selectedEntidad;
    
    /**
     * Creates a new instance of EntidadMB
     */
    public EntidadMB() {
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
     * @return the codigo
     */
    public BigDecimal getCodigo() {
        return codigo;
    }

    /**
     * @param codigo the codigo to set
     */
    public void setCodigo(BigDecimal codigo) {
        this.codigo = codigo;
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
     * @return the listaEntidad
     */
    public List<Entidad> getListaEntidad() {
        return listaEntidad;
    }

    /**
     * @param listaEntidad the listaEntidad to set
     */
    public void setListaEntidad(List<Entidad> listaEntidad) {
        this.listaEntidad = listaEntidad;
    }

    /**
     * @return the filteredListaEntidad
     */
    public List<Entidad> getFilteredListaEntidad() {
        return filteredListaEntidad;
    }

    /**
     * @param filteredListaEntidad the filteredListaEntidad to set
     */
    public void setFilteredListaEntidad(List<Entidad> filteredListaEntidad) {
        this.filteredListaEntidad = filteredListaEntidad;
    }

    /**
     * @return the selectedEntidad
     */
    public Entidad getSelectedEntidad() {
        return selectedEntidad;
    }

    /**
     * @param selectedEntidad the selectedEntidad to set
     */
    public void setSelectedEntidad(Entidad selectedEntidad) {
        this.selectedEntidad = selectedEntidad;
    }

    
    
    @PostConstruct
    public void init() {
        try {
            EntidadService service = (EntidadService) ServiceFinder.findBean("EntidadService");
            listaEntidad = service.getEntidades();
        } catch(Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
    }
    
    public void cleanAttributes() {
        this.setId(BigDecimal.ZERO);
        this.setDescripcion(StringUtils.EMPTY);
        this.setNombre(StringUtils.EMPTY);
        this.setCodigo(null);
        this.setActivo(BigDecimal.ONE);
        this.setSelectedEntidad(null);
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
                if(!CollectionUtils.isEmpty(this.getFilteredListaEntidad())) {
                    this.setSelectedEntidad(this.getFilteredListaEntidad().get(index));
                } else {
                    this.setSelectedEntidad(this.getListaEntidad().get(index));
                }
                this.setFilteredListaEntidad(new ArrayList());
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
            if (CollectionUtils.isEmpty(this.getListaEntidad())) {
                this.setListaEntidad(Collections.EMPTY_LIST);
            }
            Entidad entidad = new Entidad();
            entidad.setVnombre(this.getNombre());
            entidad.setNcodigoentidad(this.getCodigo());
            entidad.setVdescripcion(this.getDescripcion());
            if (!errorValidation(entidad)) {
                LoginMB loginMB = (LoginMB) JSFUtils.getSessionAttribute("loginMB");
                User user = loginMB.getUser();
                EntidadService service = (EntidadService) ServiceFinder.findBean("EntidadService");
                entidad.setNentidadid(service.getNextPK());
                entidad.setVnombre(StringUtils.upperCase(this.getNombre().trim()));
                entidad.setNcodigoentidad(this.getCodigo());
                entidad.setVdescripcion(StringUtils.capitalize(this.getDescripcion().trim()));
                entidad.setNactivo(BigDecimal.ONE);
                entidad.setDfechacreacion(new Date());
                entidad.setVusuariocreacion(user.getVlogin());
                service.saveOrUpdate(entidad);
                this.setListaEntidad(service.getEntidades());
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
            if(event != null) {
                this.setSelectedRow(event);
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
    }
    
    public void update(ActionEvent event) {
        try {
            if(event != null) {
                if(StringUtils.isBlank(this.getSelectedEntidad().getVnombre())) {
                    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, Constante.SEVERETY_ALERTA, "Nombre requerido. Ingrese el nombre de la Entidad.");
                    FacesContext.getCurrentInstance().addMessage(null, message);
                    return;
                }
                if (this.getSelectedEntidad().getNcodigoentidad()== null) {
                    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, Constante.SEVERETY_ALERTA, "Codigo requerido. Ingrese el codigo de la entidad.");
                    FacesContext.getCurrentInstance().addMessage(null, message);
                    return;
                }
                if(StringUtils.isBlank(this.getSelectedEntidad().getVdescripcion())) {
                    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, Constante.SEVERETY_ALERTA, "Descripción requerida. Ingrese la descripción de la Entidad.");
                    FacesContext.getCurrentInstance().addMessage(null, message);
                    return;
                }
                LoginMB loginMB = (LoginMB) JSFUtils.getSessionAttribute("loginMB");
                User user = loginMB.getUser();
                this.getSelectedEntidad().setVnombre(StringUtils.upperCase(this.getSelectedEntidad().getVnombre().trim()));
                this.getSelectedEntidad().setNcodigoentidad(this.getSelectedEntidad().getNcodigoentidad());
                this.getSelectedEntidad().setVdescripcion(StringUtils.capitalize(this.getSelectedEntidad().getVdescripcion().trim()));
                this.getSelectedEntidad().setVusuariomodificacion(user.getVlogin());
                this.getSelectedEntidad().setDfechamodificacion(new Date());
                EntidadService service = (EntidadService) ServiceFinder.findBean("EntidadService");
                service.saveOrUpdate(this.getSelectedEntidad());
                this.setListaEntidad(service.getEntidades());
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
            if(event != null) {
                if(this.getSelectedEntidad() != null) {
                    LoginMB loginMB = (LoginMB) JSFUtils.getSessionAttribute("loginMB");
                    User user = loginMB.getUser();
                    EntidadService service = (EntidadService) ServiceFinder.findBean("EntidadService");
                    this.getSelectedEntidad().setNactivo(BigDecimal.ONE);
                    this.getSelectedEntidad().setDfechamodificacion(new Date());
                    this.getSelectedEntidad().setVusuariomodificacion(user.getVlogin());
                    service.saveOrUpdate(this.getSelectedEntidad());
                    this.setListaEntidad(service.getEntidades());
                } else {
                    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, Constante.SEVERETY_ALERTA, "Debe seleccionar la entidad a activar.");
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
            if(event != null) {
                if(this.getSelectedEntidad() != null) {
                    LoginMB loginMB = (LoginMB) JSFUtils.getSessionAttribute("loginMB");
                    User user = loginMB.getUser();
                    EntidadService service = (EntidadService) ServiceFinder.findBean("EntidadService");
                    this.getSelectedEntidad().setNactivo(BigDecimal.ZERO);
                    this.getSelectedEntidad().setDfechamodificacion(new Date());
                    this.getSelectedEntidad().setVusuariomodificacion(user.getVlogin());
                    service.saveOrUpdate(this.getSelectedEntidad());
                    this.setListaEntidad(service.getEntidades());
                } else {
                    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, Constante.SEVERETY_ALERTA, "Debe seleccionar la entidad a desactivar.");
                    FacesContext.getCurrentInstance().addMessage(null, message);
                }
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
    }
    
    public boolean errorValidation(Entidad entidad) {
        FacesMessage message;
        boolean error = false;
        try {
            if (entidad.getVnombre() == null || entidad.getVnombre().isEmpty()) {
                message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR.", "Nombre requerido. Ingrese el nombre de la entidad.");
                FacesContext.getCurrentInstance().addMessage(null, message);
                error = true;
                return error;
            } 
            else if (entidad.getNcodigoentidad()== null) {
                message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR.", "Codigo requerido. Ingrese el codigo de la entidad.");
                FacesContext.getCurrentInstance().addMessage(null, message);
                error = true;
                return error;
            }
            else if (entidad.getVdescripcion()== null || entidad.getVdescripcion().isEmpty()) {
                message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR.", "Descripción requerida. Ingrese la descripción de la entidad.");
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
