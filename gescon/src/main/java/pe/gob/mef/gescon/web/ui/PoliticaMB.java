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
import pe.gob.mef.gescon.service.PoliticaService;
import pe.gob.mef.gescon.util.JSFUtils;
import pe.gob.mef.gescon.util.ServiceFinder;
import pe.gob.mef.gescon.web.bean.Politica;
import pe.gob.mef.gescon.web.bean.User;

/**
 *
 * @author JJacobo
 */
@ManagedBean
@ViewScoped
public class PoliticaMB implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final Log log = LogFactory.getLog(PoliticaMB.class);
    private BigDecimal id;
    private BigDecimal moduloid;
    private String nombre;
    private String descripcion;
    private BigDecimal activo;
    private List<Politica> listaPolitica;
    private List<Politica> filteredListaPolitica;
    private Politica selectedPolitica;

    /**
     * Creates a new instance of MaestroMB
     */
    public PoliticaMB() {
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

    public BigDecimal getModuloid() {
        return moduloid;
    }

    public void setModuloid(BigDecimal moduloid) {
        this.moduloid = moduloid;
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
     * @return the listaMaestro
     */
    public List<Politica> getListaPolitica() {
        return listaPolitica;
    }

    /**
     * @param listaPolitica the listaMaestro to set
     */
    public void setListaPolitica(List<Politica> listaPolitica) {
        this.listaPolitica = listaPolitica;
    }

    public List<Politica> getFilteredListaPolitica() {
        return filteredListaPolitica;
    }

    public void setFilteredListaPolitica(List<Politica> filteredListaPolitica) {
        this.filteredListaPolitica = filteredListaPolitica;
    }

    /**
     * @return the selectedMaestro
     */
    public Politica getSelectedPolitica() {
        return selectedPolitica;
    }

    /**
     * @param selectedPolitica
     */
    public void setSelectedPolitica(Politica selectedPolitica) {
        this.selectedPolitica = selectedPolitica;
    }

    @PostConstruct
    public void init() {
        try {
            PoliticaService service = (PoliticaService) ServiceFinder.findBean("PoliticaService");
            this.setListaPolitica(service.getPoliticas());
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
    }

    public void cleanAttributes() {
        this.setId(BigDecimal.ZERO);
        this.setModuloid(null);
        this.setDescripcion(StringUtils.EMPTY);
        this.setNombre(StringUtils.EMPTY);
        this.setActivo(BigDecimal.ONE);
        this.setSelectedPolitica(null);
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
                if(!CollectionUtils.isEmpty(this.getFilteredListaPolitica())) {
                    this.setSelectedPolitica(this.getFilteredListaPolitica().get(index));
                } else {
                    this.setSelectedPolitica(this.getListaPolitica().get(index));
                }
                this.setFilteredListaPolitica(new ArrayList());
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
            if (CollectionUtils.isEmpty(this.getListaPolitica())) {
                this.setListaPolitica(Collections.EMPTY_LIST);
            }
            Politica politica = new Politica();
            politica.setNmoduloid(this.getModuloid());
            politica.setVnombre(this.getNombre());
            politica.setVdescripcion(this.getDescripcion());
            if (!errorValidation(politica)) {
                politica.setVnombre(StringUtils.upperCase(this.getNombre().trim()));
                politica.setVdescripcion(StringUtils.capitalize(this.getDescripcion().trim()));
                LoginMB loginMB = (LoginMB) JSFUtils.getSessionAttribute("loginMB");
                User user = loginMB.getUser();
                PoliticaService service = (PoliticaService) ServiceFinder.findBean("PoliticaService");
                politica.setNpoliticaid(service.getNextPK());
                politica.setNactivo(BigDecimal.ONE);
                politica.setDfechacreacion(new Date());
                politica.setVusuariocreacion(user.getVlogin());
                service.saveOrUpdate(politica);
                this.setListaPolitica(service.getPoliticas());
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
                if (this.getSelectedPolitica().getNmoduloid() == null) {
                    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, Constante.SEVERETY_ALERTA, "Módulo requerido. Ingrese el nombre de la política acceso.");
                    FacesContext.getCurrentInstance().addMessage(null, message);
                    return;
                }
                if (StringUtils.isBlank(this.getSelectedPolitica().getVnombre())) {
                    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, Constante.SEVERETY_ALERTA, "Nombre requerido. Ingrese el nombre de la política acceso.");
                    FacesContext.getCurrentInstance().addMessage(null, message);
                    return;
                }
                if (StringUtils.isBlank(this.getSelectedPolitica().getVdescripcion())) {
                    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, Constante.SEVERETY_ALERTA, "Descripción requerida. Ingrese la descripción de la política acceso.");
                    FacesContext.getCurrentInstance().addMessage(null, message);
                    return;
                }
                LoginMB loginMB = (LoginMB) JSFUtils.getSessionAttribute("loginMB");
                User user = loginMB.getUser();
                this.getSelectedPolitica().setVnombre(StringUtils.upperCase(this.getSelectedPolitica().getVnombre().trim()));
                this.getSelectedPolitica().setVdescripcion(StringUtils.capitalize(this.getSelectedPolitica().getVdescripcion().trim()));
                this.getSelectedPolitica().setVusuariomodificacion(user.getVlogin());
                this.getSelectedPolitica().setDfechamodificacion(new Date());
                PoliticaService service = (PoliticaService) ServiceFinder.findBean("PoliticaService");
                service.saveOrUpdate(this.getSelectedPolitica());
                this.setListaPolitica(service.getPoliticas());
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
                if (this.getSelectedPolitica() != null) {
                    LoginMB loginMB = (LoginMB) JSFUtils.getSessionAttribute("loginMB");
                    User user = loginMB.getUser();
                    PoliticaService service = (PoliticaService) ServiceFinder.findBean("PoliticaService");
                    this.getSelectedPolitica().setNactivo(BigDecimal.ONE);
                    this.getSelectedPolitica().setDfechamodificacion(new Date());
                    this.getSelectedPolitica().setVusuariomodificacion(user.getVlogin());
                    service.saveOrUpdate(this.getSelectedPolitica());
                    this.setListaPolitica(service.getPoliticas());
                } else {
                    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, Constante.SEVERETY_ALERTA, "Debe seleccionar politica a activar.");
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
                if (this.getSelectedPolitica() != null) {
                    LoginMB loginMB = (LoginMB) JSFUtils.getSessionAttribute("loginMB");
                    User user = loginMB.getUser();
                    PoliticaService service = (PoliticaService) ServiceFinder.findBean("PoliticaService");
                    this.getSelectedPolitica().setNactivo(BigDecimal.ZERO);
                    this.getSelectedPolitica().setDfechamodificacion(new Date());
                    this.getSelectedPolitica().setVusuariomodificacion(user.getVlogin());
                    service.saveOrUpdate(this.getSelectedPolitica());
                    this.setListaPolitica(service.getPoliticas());
                } else {
                    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, Constante.SEVERETY_ALERTA, "Debe seleccionar politica a desactivar.");
                    FacesContext.getCurrentInstance().addMessage(null, message);
                }
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
    }

    public boolean errorValidation(Politica politica) {
        FacesMessage message;
        boolean error = false;
        try {
            if (politica.getNmoduloid() == null) {
                message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR.", "Módulo requerido. Seleccione el módulo de la política de acceso.");
                FacesContext.getCurrentInstance().addMessage(null, message);
                error = true;
                return error;
            } if (politica.getVnombre() == null || politica.getVnombre().isEmpty()) {
                message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR.", "Nombre requerido. Ingrese el nombre de la política de acceso.");
                FacesContext.getCurrentInstance().addMessage(null, message);
                error = true;
                return error;
            } else if (politica.getVdescripcion() == null || politica.getVdescripcion().isEmpty()) {
                message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR.", "Descripción requerida. Ingrese la descripción de la política de acceso.");
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
