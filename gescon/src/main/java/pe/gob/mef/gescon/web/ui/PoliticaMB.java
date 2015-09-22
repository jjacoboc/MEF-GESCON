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

import pe.gob.mef.gescon.service.PoliticaService;
import pe.gob.mef.gescon.util.ServiceFinder;

import pe.gob.mef.gescon.web.bean.Politica;

/**
 *
 * @author JJacobo
 */
@ManagedBean
@ViewScoped
public class PoliticaMB implements Serializable{

    private static final long serialVersionUID = 1L;
    private static final Log log = LogFactory.getLog(PoliticaMB.class);
    private BigDecimal id;
    private String nombre;
    private String descripcion;
    private BigDecimal activo;
    private List<Politica> listaPolitica;
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

    /**
     * @return the selectedMaestro
     */
    public Politica getSelectedPolitica() {
        return selectedPolitica;
    }

    /**
     * @param selectedMaestro the selectedMaestro to set
     */
    public void setSelectedPolitica(Politica selectedPolitica) {
        this.selectedPolitica = selectedPolitica;
    }
    
    @PostConstruct
    public void init() {
        try {
            PoliticaService service = (PoliticaService) ServiceFinder.findBean("PoliticaService");
            listaPolitica = service.getPoliticas();
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
        this.setSelectedPolitica(null);
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
            if (CollectionUtils.isEmpty(this.getListaPolitica())) {
                this.setListaPolitica(Collections.EMPTY_LIST);
            }
            Politica politica = new Politica();
            politica.setVnombre(this.getNombre().trim().toUpperCase());
            politica.setVdescripcion(this.getDescripcion().trim());
            if (!errorValidation(politica)) {
//                UsuarioMB usuarioMB = (UsuarioMB) JSFUtils.getSession().getAttribute("usuarioMB");
//                Usuario usuario = usuarioMB.getUsuario();
                PoliticaService service = (PoliticaService) ServiceFinder.findBean("PoliticaService");
                politica.setNpoliticaid(service.getNextPK());
                politica.setNactivo(BigDecimal.ONE);
                politica.setDfechcrea(new Date());
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
                if(StringUtils.isBlank(this.getSelectedPolitica().getVnombre())) {
                    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, Constante.SEVERETY_ALERTA, "Nombre requerido. Ingrese el nombre de politica.");
                    FacesContext.getCurrentInstance().addMessage(null, message);
                    return;
                }
                if(StringUtils.isBlank(this.getSelectedPolitica().getVdescripcion())) {
                    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, Constante.SEVERETY_ALERTA, "Descripción requerida. Ingrese la descripción de politica.");
                    FacesContext.getCurrentInstance().addMessage(null, message);
                    return;
                }
                this.getSelectedPolitica().setVnombre(this.getSelectedPolitica().getVnombre().toUpperCase());
                this.getSelectedPolitica().setVdescripcion(this.getSelectedPolitica().getVdescripcion().toUpperCase());
//                this.getSelectedMestro().setIdUsuaModi(user.getUsuario());
                this.getSelectedPolitica().setDfechmod(new Date());
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
            if(event != null) {
                if(this.getSelectedPolitica() != null) {
                    PoliticaService service = (PoliticaService) ServiceFinder.findBean("PoliticaService");
                    this.getSelectedPolitica().setNactivo(BigDecimal.ONE);
                    this.getSelectedPolitica().setDfechmod(new Date());
//                    this.getSelectedMaestro().setVusumod(user.getUsuario());
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
            if(event != null) {
                if(this.getSelectedPolitica() != null) {
                    PoliticaService service = (PoliticaService) ServiceFinder.findBean("PoliticaService");
                    this.getSelectedPolitica().setNactivo(BigDecimal.ZERO);
                    this.getSelectedPolitica().setDfechmod(new Date());
//                    this.getSelectedMaestro().setVusumod(user.getUsuario());
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
            if (politica.getVnombre() == null || politica.getVnombre().isEmpty()) {
                message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR.", "Nombre requerido. Ingrese el nombre del maestro.");
                FacesContext.getCurrentInstance().addMessage(null, message);
                error = true;
                return error;
            } else if (politica.getVdescripcion()== null || politica.getVdescripcion().isEmpty()) {
                message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR.", "Descripción requerida. Ingrese la descripción del maestro.");
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
