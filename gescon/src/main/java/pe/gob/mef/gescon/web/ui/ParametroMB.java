/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.gob.mef.gescon.web.ui;

import java.io.Serializable;
import java.math.BigDecimal;
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
import pe.gob.mef.gescon.service.ParametroService;
import pe.gob.mef.gescon.util.JSFUtils;
import pe.gob.mef.gescon.util.ServiceFinder;
import pe.gob.mef.gescon.web.bean.Parametro;
import pe.gob.mef.gescon.web.bean.User;

/**
 *
 * @author JJacobo
 */
@ManagedBean
@ViewScoped
public class ParametroMB implements Serializable{

    private static final long serialVersionUID = 1L;
    private static final Log log = LogFactory.getLog(ParametroMB.class);
    private BigDecimal id;
    private String nombre;
    private BigDecimal valor;
    private String descripcion;
    private BigDecimal activo;
    private List<Parametro> listaParametro;
    private Parametro selectedParametro;
    
    /**
     * Creates a new instance of ParametroMB
     */
    public ParametroMB() {
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
     * @return the valor
     */
    public BigDecimal getValor() {
        return valor;
    }

    /**
     * @param valor the valor to set
     */
    public void setValor(BigDecimal valor) {
        this.valor = valor;
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
     * @return the listaParametro
     */
    public List<Parametro> getListaParametro() {
        return listaParametro;
    }

    /**
     * @param listaParametro the listaParametro to set
     */
    public void setListaParametro(List<Parametro> listaParametro) {
        this.listaParametro = listaParametro;
    }

    /**
     * @return the selectedParametro
     */
    public Parametro getSelectedParametro() {
        return selectedParametro;
    }

    /**
     * @param selectedParametro the selectedParametro to set
     */
    public void setSelectedParametro(Parametro selectedParametro) {
        this.selectedParametro = selectedParametro;
    }
    
    @PostConstruct
    public void init() {
        try {
            ParametroService service = (ParametroService) ServiceFinder.findBean("ParametroService");
            listaParametro = service.getParametros();
        } catch(Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
    }
    
    public void cleanAttributes() {
        this.setId(BigDecimal.ZERO);
        this.setDescripcion(StringUtils.EMPTY);
        this.setNombre(StringUtils.EMPTY);
        this.setValor(null);
        this.setActivo(BigDecimal.ONE);
        this.setSelectedParametro(null);
        Iterator<FacesMessage> iter = FacesContext.getCurrentInstance().getMessages();
        if (iter.hasNext() == true) {
            iter.remove();
            FacesContext.getCurrentInstance().renderResponse();
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
            if (CollectionUtils.isEmpty(this.getListaParametro())) {
                this.setListaParametro(Collections.EMPTY_LIST);
            }
            Parametro parametro = new Parametro();
            parametro.setVnombre(this.getNombre().trim().toUpperCase());
            parametro.setNvalor(this.getValor());
            parametro.setVdescripcion(this.getDescripcion().trim());
            if (!errorValidation(parametro)) {
                LoginMB loginMB = (LoginMB) JSFUtils.getSessionAttribute("loginMB");
                User user = loginMB.getUser();
                ParametroService service = (ParametroService) ServiceFinder.findBean("ParametroService");
                parametro.setNparametroid(service.getNextPK());
                parametro.setNactivo(BigDecimal.ONE);
                parametro.setDfechacreacion(new Date());
                parametro.setVusuariocreacion(user.getVlogin());
                service.saveOrUpdate(parametro);
                this.setListaParametro(service.getParametros());
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
//                if(this.getSelectedMaestro() == null) {
//                    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, Constante.SEVERETY_ALERTA, "Seleccione el maestro que desea editar.");
//                    FacesContext.getCurrentInstance().addMessage(null, message);
//                }
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
    }
    
    public void update(ActionEvent event) {
        try {
            if(event != null) {
                if(StringUtils.isBlank(this.getSelectedParametro().getVnombre())) {
                    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, Constante.SEVERETY_ALERTA, "Nombre requerido. Ingrese el nombre del parametro.");
                    FacesContext.getCurrentInstance().addMessage(null, message);
                    return;
                }
                if(StringUtils.isBlank(this.getSelectedParametro().getVdescripcion())) {
                    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, Constante.SEVERETY_ALERTA, "Descripción requerida. Ingrese la descripción del parametro.");
                    FacesContext.getCurrentInstance().addMessage(null, message);
                    return;
                }
                LoginMB loginMB = (LoginMB) JSFUtils.getSessionAttribute("loginMB");
                User user = loginMB.getUser();
                this.getSelectedParametro().setVnombre(this.getSelectedParametro().getVnombre().trim().toUpperCase());
                this.getSelectedParametro().setNvalor(this.getSelectedParametro().getNvalor());
                this.getSelectedParametro().setVdescripcion(this.getSelectedParametro().getVdescripcion().trim());
                this.getSelectedParametro().setVusuariomodificacion(user.getVlogin());
                this.getSelectedParametro().setDfechamodificacion(new Date());
                ParametroService service = (ParametroService) ServiceFinder.findBean("ParametroService");
                service.saveOrUpdate(this.getSelectedParametro());
                this.setListaParametro(service.getParametros());
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
                if(this.getSelectedParametro() != null) {
                    LoginMB loginMB = (LoginMB) JSFUtils.getSessionAttribute("loginMB");
                    User user = loginMB.getUser();
                    ParametroService service = (ParametroService) ServiceFinder.findBean("ParametroService");
                    this.getSelectedParametro().setNactivo(BigDecimal.ONE);
                    this.getSelectedParametro().setDfechamodificacion(new Date());
                    this.getSelectedParametro().setVusuariomodificacion(user.getVlogin());
                    service.saveOrUpdate(this.getSelectedParametro());
                    this.setListaParametro(service.getParametros());
                } else {
                    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, Constante.SEVERETY_ALERTA, "Debe seleccionar el parametro a activar.");
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
                if(this.getSelectedParametro() != null) {
                    LoginMB loginMB = (LoginMB) JSFUtils.getSessionAttribute("loginMB");
                    User user = loginMB.getUser();
                    ParametroService service = (ParametroService) ServiceFinder.findBean("ParametroService");
                    this.getSelectedParametro().setNactivo(BigDecimal.ZERO);
                    this.getSelectedParametro().setDfechamodificacion(new Date());
                    this.getSelectedParametro().setVusuariomodificacion(user.getVlogin());
                    service.saveOrUpdate(this.getSelectedParametro());
                    this.setListaParametro(service.getParametros());
                } else {
                    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, Constante.SEVERETY_ALERTA, "Debe seleccionar el parametro a desactivar.");
                    FacesContext.getCurrentInstance().addMessage(null, message);
                }
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
    }
    
    public boolean errorValidation(Parametro parametro) {
        FacesMessage message;
        boolean error = false;
        try {
            if (parametro.getVnombre() == null || parametro.getVnombre().isEmpty()) {
                message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR.", "Nombre requerido. Ingrese el nombre del parametro.");
                FacesContext.getCurrentInstance().addMessage(null, message);
                error = true;
                return error;
            } 
            else if (parametro.getNvalor()== null) {
                message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR.", "Valor requerido. Ingrese el valor del parametro.");
                FacesContext.getCurrentInstance().addMessage(null, message);
                error = true;
                return error;
            }            
            else if (parametro.getVdescripcion()== null || parametro.getVdescripcion().isEmpty()) {
                message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR.", "Descripción requerida. Ingrese la descripción del parametro.");
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
