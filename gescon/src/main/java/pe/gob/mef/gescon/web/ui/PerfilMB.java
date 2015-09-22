/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.gob.mef.gescon.web.ui;

import com.mchange.lang.ByteUtils;
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
import pe.gob.mef.gescon.service.PerfilService;

//import pe.gob.mef.gescon.service.PoliticaService;
import pe.gob.mef.gescon.util.ServiceFinder;
import pe.gob.mef.gescon.web.bean.Perfil;

//import pe.gob.mef.gescon.web.bean.Politica;

/**
 *
 * @author JJacobo
 */
@ManagedBean
@ViewScoped
public class PerfilMB implements Serializable{

    private static final long serialVersionUID = 1L;
    private static final Log log = LogFactory.getLog(PerfilMB.class);
    private BigDecimal id;
    private String nombre;
    private String descripcion;
    private BigDecimal activo;
    private List<Perfil> listaPerfils;
    private Perfil selectedPerfil;
    
    /**
     * Creates a new instance of MaestroMB
     */
    public PerfilMB() {
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
     * @return the listaMaestro
     */
    public List<Perfil> getListaPerfils() {
        return listaPerfils;
    }

    /**
     * @param listaPerfils the listaMaestro to set
     */
    public void setListaPerfils(List<Perfil> listaPerfils) {
        this.listaPerfils = listaPerfils;
    }

    /**
     * @return the selectedMaestro
     */
    public Perfil getSelectedPerfil() {
        return selectedPerfil;
    }

    /**
     * @param selectedPerfil the selectedMaestro to set
     */
    public void setSelectedPerfil(Perfil selectedPerfil) {
        this.selectedPerfil = selectedPerfil;
    }
    
    @PostConstruct
    public void init() {
        try {
            PerfilService service = (PerfilService) ServiceFinder.findBean("PerfilService");
            listaPerfils = service.getPerfils();
        } catch(Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
    }
    
    public void cleanAttributes() {
        this.setId(BigDecimal.ZERO);
        this.setDescripcion(StringUtils.EMPTY);
        this.setNombre(StringUtils.EMPTY);
        this.setActivo(BigDecimal.ONE);
        this.setSelectedPerfil(null);
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
            if (CollectionUtils.isEmpty(this.getListaPerfils())) {
                this.setListaPerfils(Collections.EMPTY_LIST);
            }
            Perfil perfil = new Perfil();
            perfil.setVnombre(this.getNombre().trim().toUpperCase());
            perfil.setVdescripcion(this.getDescripcion().trim());
            if (!errorValidation(perfil)) {
//                UsuarioMB usuarioMB = (UsuarioMB) JSFUtils.getSession().getAttribute("usuarioMB");
//                Usuario usuario = usuarioMB.getUsuario();
                PerfilService service = (PerfilService) ServiceFinder.findBean("PerfilService");
                perfil.setNperfilid(service.getNextPK());
                perfil.setNactivo(BigDecimal.ONE);
                perfil.setDfechacreacion(new Date());
                service.saveOrUpdate(perfil);
                this.setListaPerfils(service.getPerfils());
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
                if(StringUtils.isBlank(this.getSelectedPerfil().getVnombre())) {
                    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, Constante.SEVERETY_ALERTA, "Nombre requerido. Ingrese el nombre de perfil.");
                    FacesContext.getCurrentInstance().addMessage(null, message);
                    return;
                }
                if(StringUtils.isBlank(this.getSelectedPerfil().getVdescripcion())) {
                    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, Constante.SEVERETY_ALERTA, "Descripción requerida. Ingrese la descripción de perfil.");
                    FacesContext.getCurrentInstance().addMessage(null, message);
                    return;
                }
                this.getSelectedPerfil().setVnombre(this.getSelectedPerfil().getVnombre().toUpperCase());
                this.getSelectedPerfil().setVdescripcion(this.getSelectedPerfil().getVdescripcion().toUpperCase());
//                this.getSelectedMestro().setIdUsuaModi(user.getUsuario());
                this.getSelectedPerfil().setDfechacreacion(new Date());
                PerfilService service = (PerfilService) ServiceFinder.findBean("PerfilService");
                service.saveOrUpdate(this.getSelectedPerfil());
                this.setListaPerfils(service.getPerfils());
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
                if(this.getSelectedPerfil()!= null) {
                    PerfilService service = (PerfilService) ServiceFinder.findBean("PerfilService");
                    this.getSelectedPerfil().setNactivo(BigDecimal.ONE);
                    this.getSelectedPerfil().setDfechacreacion(new Date());
//                    this.getSelectedMaestro().setVusumod(user.getUsuario());
                    service.saveOrUpdate(this.getSelectedPerfil());
                    this.setListaPerfils(service.getPerfils());
                } else {
                    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, Constante.SEVERETY_ALERTA, "Debe seleccionar el perfil a activar.");
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
                if(this.getSelectedPerfil()!= null) {
                    PerfilService service = (PerfilService) ServiceFinder.findBean("PerfilService");
                    this.getSelectedPerfil().setNactivo(BigDecimal.ZERO);
                    this.getSelectedPerfil().setDfechacreacion(new Date());
//                    this.getSelectedMaestro().setVusumod(user.getUsuario());
                    service.saveOrUpdate(this.getSelectedPerfil());
                    this.setListaPerfils(service.getPerfils());
                } else {
                    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, Constante.SEVERETY_ALERTA, "Debe seleccionar el perfil a desactivar.");
                    FacesContext.getCurrentInstance().addMessage(null, message);
                }
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
    }
    
    public boolean errorValidation(Perfil perfil) {
        FacesMessage message;
        boolean error = false;
        try {
            if (perfil.getVnombre() == null || perfil.getVnombre().isEmpty()) {
                message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR.", "Nombre requerido. Ingrese el nombre del perfil.");
                FacesContext.getCurrentInstance().addMessage(null, message);
                error = true;
                return error;
            } else if (perfil.getVdescripcion()== null || perfil.getVdescripcion().isEmpty()) {
                message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR.", "Descripción requerida. Ingrese la descripción del perfil.");
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
