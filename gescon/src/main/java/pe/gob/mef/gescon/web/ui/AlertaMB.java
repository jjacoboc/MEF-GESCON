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
import javax.faces.model.SelectItem;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.primefaces.context.RequestContext;
import org.springframework.util.CollectionUtils;
import pe.gob.mef.gescon.common.Constante;
import pe.gob.mef.gescon.common.Items;
import pe.gob.mef.gescon.service.AlertaService;
import pe.gob.mef.gescon.service.ParametroService;
import pe.gob.mef.gescon.util.ServiceFinder;
import pe.gob.mef.gescon.web.bean.Alerta;

/**
 *
 * @author JJacobo
 */
@ManagedBean
@ViewScoped
public class AlertaMB implements Serializable{

    private static final long serialVersionUID = 1L;
    private static final Log log = LogFactory.getLog(AlertaMB.class);
    private BigDecimal id;
    private String nombre;
    private String descripcion;
    private BigDecimal activo;
    private List<Alerta> listaAlerta;
    private Alerta selectedAlerta;
    private BigDecimal selectedParametro;
    private List<SelectItem> listaParametro;
    
    /**
     * Creates a new instance of MaestroMB
     */
    public AlertaMB() {
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
    public List<Alerta> getListaAlerta() {
        return listaAlerta;
    }

    /**
     * @param listaMaestro the listaMaestro to set
     */
    public void setListaAlerta(List<Alerta> listaAlerta) {
        this.listaAlerta = listaAlerta;
    }

    /**
     * @return the selectedMaestro
     */
    public Alerta getSelectedAlerta() {
        return selectedAlerta;
    }

    /**
     * @param selectedMaestro the selectedMaestro to set
     */
    public void setSelectedAlerta(Alerta selectedAlerta) {
        this.selectedAlerta = selectedAlerta;
    }

    /**
     * @return the selectedParametro
     */
    public BigDecimal getSelectedParametro() {
        return selectedParametro;
    }

    /**
     * @param selectedParametro the selectedParametro to set
     */
    public void setSelectedParametro(BigDecimal selectedParametro) {
        this.selectedParametro = selectedParametro;
    }

    /**
     * @return the listaParametro
     */
    public List<SelectItem> getListaParametro() throws Exception {
        if(listaParametro == null){
            ParametroService service = (ParametroService) ServiceFinder.findBean("ParametroService");
            listaParametro =  new Items(service.getParametros(), null, "nparametroid","vnombre").getItems();
        }
        return listaParametro;
    }

    /**
     * @param listaParametro the listaParametro to set
     */
    public void setListaParametro(List<SelectItem> listaParametro) {
        this.listaParametro = listaParametro;
    }
    
    @PostConstruct
    public void init() {
        try {
            AlertaService service = (AlertaService) ServiceFinder.findBean("AlertaService");
            listaAlerta = service.getAlertas();
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
            if (CollectionUtils.isEmpty(this.getListaAlerta())) {
                this.setListaAlerta(Collections.EMPTY_LIST);
            }
            Alerta alerta = new Alerta();
            alerta.setVnombre(this.getNombre().trim().toUpperCase());
            alerta.setVdescripcion(this.getDescripcion().trim());
            alerta.setNparametroid(this.getSelectedParametro());
            if (!errorValidation(alerta)) {
//                UsuarioMB usuarioMB = (UsuarioMB) JSFUtils.getSession().getAttribute("usuarioMB");
//                Usuario usuario = usuarioMB.getUsuario();
                AlertaService service = (AlertaService) ServiceFinder.findBean("AlertaService");
                alerta.setNalertaid(service.getNextPK());
                alerta.setNactivo(BigDecimal.ONE);
                alerta.setDfechcrea(new Date());
                service.saveOrUpdate(alerta);
                this.setListaAlerta(service.getAlertas());
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
                if(StringUtils.isBlank(this.getSelectedAlerta().getVnombre())) {
                    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, Constante.SEVERETY_ALERTA, "Nombre requerido. Ingrese el nombre de la alerta.");
                    FacesContext.getCurrentInstance().addMessage(null, message);
                    return;
                }
                if(StringUtils.isBlank(this.getSelectedAlerta().getVdescripcion())) {
                    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, Constante.SEVERETY_ALERTA, "Descripción requerida. Ingrese la descripción de la alerta.");
                    FacesContext.getCurrentInstance().addMessage(null, message);
                    return;
                }
                this.getSelectedAlerta().setVnombre(this.getSelectedAlerta().getVnombre().toUpperCase());
                this.getSelectedAlerta().setVdescripcion(this.getSelectedAlerta().getVdescripcion().toUpperCase());
//                this.getSelectedMestro().setIdUsuaModi(user.getUsuario());
                this.getSelectedAlerta().setDfechmod(new Date());
                AlertaService service = (AlertaService) ServiceFinder.findBean("AlertaService");
                service.saveOrUpdate(this.getSelectedAlerta());
                this.setListaAlerta(service.getAlertas());
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
                if(this.getSelectedAlerta() != null) {
                    AlertaService service = (AlertaService) ServiceFinder.findBean("AlertaService");
                    this.getSelectedAlerta().setNactivo(BigDecimal.ONE);
                    this.getSelectedAlerta().setDfechmod(new Date());
//                    this.getSelectedMaestro().setVusumod(user.getUsuario());
                    service.saveOrUpdate(this.getSelectedAlerta());
                    this.setListaAlerta(service.getAlertas());
                } else {
                    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, Constante.SEVERETY_ALERTA, "Debe seleccionar la alerta a activar.");
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
                if(this.getSelectedAlerta() != null) {
                    AlertaService service = (AlertaService) ServiceFinder.findBean("AlertaService");
                    this.getSelectedAlerta().setNactivo(BigDecimal.ZERO);
                    this.getSelectedAlerta().setDfechmod(new Date());
//                    this.getSelectedMaestro().setVusumod(user.getUsuario());
                    service.saveOrUpdate(this.getSelectedAlerta());
                    this.setListaAlerta(service.getAlertas());
                } else {
                    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, Constante.SEVERETY_ALERTA, "Debe seleccionar la alerta a desactivar.");
                    FacesContext.getCurrentInstance().addMessage(null, message);
                }
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
    }
    
    public boolean errorValidation(Alerta alerta) {
        FacesMessage message;
        boolean error = false;
        try {
            if (alerta.getVnombre() == null || alerta.getVnombre().isEmpty()) {
                message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR.", "Nombre requerido. Ingrese el nombre de la alerta.");
                FacesContext.getCurrentInstance().addMessage(null, message);
                error = true;
                return error;
            } else if (alerta.getVdescripcion()== null || alerta.getVdescripcion().isEmpty()) {
                message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR.", "Descripción requerida. Ingrese la descripción de la alerta.");
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
