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
import pe.gob.mef.gescon.service.MaestroDetalleService;
import pe.gob.mef.gescon.service.MaestroService;
import pe.gob.mef.gescon.util.JSFUtils;
import pe.gob.mef.gescon.util.ServiceFinder;
import pe.gob.mef.gescon.web.bean.Maestro;
import pe.gob.mef.gescon.web.bean.MaestroDetalle;
import pe.gob.mef.gescon.web.bean.User;

/**
 *
 * @author JJacobo
 */
@ManagedBean
@ViewScoped
public class MaestroMB implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final Log log = LogFactory.getLog(MaestroMB.class);
    private BigDecimal id;
    private String nombre;
    private String descripcion;
    private BigDecimal activo;
    private List<Maestro> listaMaestro;
    private List<Maestro> filteredListaMaestro;
    private Maestro selectedMaestro;
    private List<MaestroDetalle> listaMaestroDetalle;
    private List<MaestroDetalle> filteredListaMaestroDetalle;
    private MaestroDetalle selectedMaestroDetalle;

    /**
     * Creates a new instance of MaestroMB
     */
    public MaestroMB() {
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
    public List<Maestro> getListaMaestro() {
        return listaMaestro;
    }

    /**
     * @param listaMaestro the listaMaestro to set
     */
    public void setListaMaestro(List<Maestro> listaMaestro) {
        this.listaMaestro = listaMaestro;
    }

    public List<Maestro> getFilteredListaMaestro() {
        return filteredListaMaestro;
    }

    public void setFilteredListaMaestro(List<Maestro> filteredListaMaestro) {
        this.filteredListaMaestro = filteredListaMaestro;
    }

    /**
     * @return the selectedMaestro
     */
    public Maestro getSelectedMaestro() {
        return selectedMaestro;
    }

    /**
     * @param selectedMaestro the selectedMaestro to set
     */
    public void setSelectedMaestro(Maestro selectedMaestro) {
        this.selectedMaestro = selectedMaestro;
    }

    /**
     * @return the listaMaestroDetalle
     */
    public List<MaestroDetalle> getListaMaestroDetalle() {
        return listaMaestroDetalle;
    }

    /**
     * @param listaMaestroDetalle the listaMaestroDetalle to set
     */
    public void setListaMaestroDetalle(List<MaestroDetalle> listaMaestroDetalle) {
        this.listaMaestroDetalle = listaMaestroDetalle;
    }

    public List<MaestroDetalle> getFilteredListaMaestroDetalle() {
        return filteredListaMaestroDetalle;
    }

    public void setFilteredListaMaestroDetalle(List<MaestroDetalle> filteredListaMaestroDetalle) {
        this.filteredListaMaestroDetalle = filteredListaMaestroDetalle;
    }

    /**
     * @return the selectedMaestroDetalle
     */
    public MaestroDetalle getSelectedMaestroDetalle() {
        return selectedMaestroDetalle;
    }

    /**
     * @param selectedMaestroDetalle the selectedMaestroDetalle to set
     */
    public void setSelectedMaestroDetalle(MaestroDetalle selectedMaestroDetalle) {
        this.selectedMaestroDetalle = selectedMaestroDetalle;
    }

    @PostConstruct
    public void init() {
        try {
            MaestroService service = (MaestroService) ServiceFinder.findBean("MaestroService");
            listaMaestro = service.getMaestros();
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
//        this.setSelectedMaestro(null);
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
                if(!CollectionUtils.isEmpty(this.getFilteredListaMaestro())) {
                    this.setSelectedMaestro(this.getFilteredListaMaestro().get(index));
                } else {
                    this.setSelectedMaestro(this.getListaMaestro().get(index));
                }
                this.setFilteredListaMaestro(new ArrayList());
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
            if (CollectionUtils.isEmpty(this.getListaMaestro())) {
                this.setListaMaestro(Collections.EMPTY_LIST);
            }
            Maestro maestro = new Maestro();
            maestro.setVnombre(this.getNombre().trim().toUpperCase());
            maestro.setVdescripcion(StringUtils.capitalize(this.getDescripcion().trim()));
            if (!errorValidation(maestro)) {
                LoginMB loginMB = (LoginMB) JSFUtils.getSessionAttribute("loginMB");
                User user = loginMB.getUser();
                MaestroService service = (MaestroService) ServiceFinder.findBean("MaestroService");
                maestro.setNmaestroid(service.getNextPK());
                maestro.setNactivo(BigDecimal.ONE);
                maestro.setVusuariocreacion(user.getVlogin());
                maestro.setDfechacreacion(new Date());
                service.saveOrUpdate(maestro);
                this.setListaMaestro(service.getMaestros());
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
                if (StringUtils.isBlank(this.getSelectedMaestro().getVnombre())) {
                    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, Constante.SEVERETY_ALERTA, "Nombre requerido. Ingrese el nombre del maestro.");
                    FacesContext.getCurrentInstance().addMessage(null, message);
                    return;
                }
                if (StringUtils.isBlank(this.getSelectedMaestro().getVdescripcion())) {
                    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, Constante.SEVERETY_ALERTA, "Descripción requerida. Ingrese la descripción del maestro.");
                    FacesContext.getCurrentInstance().addMessage(null, message);
                    return;
                }
                LoginMB loginMB = (LoginMB) JSFUtils.getSessionAttribute("loginMB");
                User user = loginMB.getUser();
                this.getSelectedMaestro().setVnombre(this.getSelectedMaestro().getVnombre().trim().toUpperCase());
                this.getSelectedMaestro().setVdescripcion(StringUtils.capitalize(this.getSelectedMaestro().getVdescripcion().trim()));
                this.getSelectedMaestro().setVusuariomodificacion(user.getVlogin());
                this.getSelectedMaestro().setDfechamodificacion(new Date());
                MaestroService service = (MaestroService) ServiceFinder.findBean("MaestroService");
                service.saveOrUpdate(this.getSelectedMaestro());
                this.setListaMaestro(service.getMaestros());
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
                if (this.getSelectedMaestro() != null) {
                    LoginMB loginMB = (LoginMB) JSFUtils.getSessionAttribute("loginMB");
                    User user = loginMB.getUser();
                    MaestroService service = (MaestroService) ServiceFinder.findBean("MaestroService");
                    this.getSelectedMaestro().setNactivo(BigDecimal.ONE);
                    this.getSelectedMaestro().setDfechamodificacion(new Date());
                    this.getSelectedMaestro().setVusuariomodificacion(user.getVlogin());
                    service.saveOrUpdate(this.getSelectedMaestro());
                    this.setListaMaestro(service.getMaestros());
                } else {
                    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, Constante.SEVERETY_ALERTA, "Debe seleccionar el maestro a activar.");
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
                if (this.getSelectedMaestro() != null) {
                    LoginMB loginMB = (LoginMB) JSFUtils.getSessionAttribute("loginMB");
                    User user = loginMB.getUser();
                    MaestroService service = (MaestroService) ServiceFinder.findBean("MaestroService");
                    this.getSelectedMaestro().setNactivo(BigDecimal.ZERO);
                    this.getSelectedMaestro().setDfechamodificacion(new Date());
                    this.getSelectedMaestro().setVusuariomodificacion(user.getVlogin());
                    service.saveOrUpdate(this.getSelectedMaestro());
                    this.setListaMaestro(service.getMaestros());
                } else {
                    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, Constante.SEVERETY_ALERTA, "Debe seleccionar el maestro a desactivar.");
                    FacesContext.getCurrentInstance().addMessage(null, message);
                }
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
    }

    public boolean errorValidation(Maestro maestro) {
        FacesMessage message;
        boolean error = false;
        try {
            if (maestro.getVnombre() == null || maestro.getVnombre().isEmpty()) {
                message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR.", "Nombre requerido. Ingrese el nombre del maestro.");
                FacesContext.getCurrentInstance().addMessage(null, message);
                error = true;
                return error;
            } else if (maestro.getVdescripcion() == null || maestro.getVdescripcion().isEmpty()) {
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

    public void getDetalles(ActionEvent event) {
        try {
            this.setSelectedRow(event);
            MaestroDetalleService service = (MaestroDetalleService) ServiceFinder.findBean("MaestroDetalleService");
            this.setListaMaestroDetalle(service.getDetallesByMaestro(this.getSelectedMaestro()));
            this.setFilteredListaMaestro(new ArrayList());
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
    }
    
    public void setSelectedDetailRow(ActionEvent event) {
        try {
            if (event != null) {
                int index = Integer.parseInt((String) JSFUtils.getRequestParameter("index"));
                if(!CollectionUtils.isEmpty(this.getFilteredListaMaestroDetalle())) {
                    this.setSelectedMaestroDetalle(this.getFilteredListaMaestroDetalle().get(index));
                } else {
                    this.setSelectedMaestroDetalle(this.getListaMaestroDetalle().get(index));
                }
                this.setFilteredListaMaestroDetalle(new ArrayList());
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
    }

    public void saveDetail(ActionEvent event) {
        try {
            if (CollectionUtils.isEmpty(this.getListaMaestroDetalle())) {
                this.setListaMaestroDetalle(Collections.EMPTY_LIST);
            }
            MaestroDetalle maestroDetalle = new MaestroDetalle();
            maestroDetalle.setVnombre(this.getNombre().trim().toUpperCase());
            maestroDetalle.setVdescripcion(StringUtils.capitalize(this.getDescripcion().trim()));
            if (!errorValidationDetail(maestroDetalle)) {
                LoginMB loginMB = (LoginMB) JSFUtils.getSessionAttribute("loginMB");
                User user = loginMB.getUser();
                MaestroDetalleService service = (MaestroDetalleService) ServiceFinder.findBean("MaestroDetalleService");
                maestroDetalle.setNdetalleid(service.getNextPK());
                maestroDetalle.setNmaestroid(this.getSelectedMaestro().getNmaestroid());
                maestroDetalle.setNactivo(BigDecimal.ONE);
                maestroDetalle.setDfechacreacion(new Date());
                maestroDetalle.setVusuariocreacion(user.getVlogin());
                service.saveOrUpdate(maestroDetalle);
                this.setListaMaestroDetalle(service.getDetallesByMaestro(this.getSelectedMaestro()));
                this.cleanAttributes();
                RequestContext.getCurrentInstance().execute("PF('newdDialog').hide();");
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
    }
    public void toUpdateDetail(ActionEvent event) {
        try {
            if (event != null) {
                this.setSelectedDetailRow(event);
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
    }
    
    public void updateDetail(ActionEvent event) {
        try {
            if (event != null) {
                if (StringUtils.isBlank(this.getSelectedMaestroDetalle().getVnombre())) {
                    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, Constante.SEVERETY_ALERTA, "Nombre requerido. Ingrese el nombre del detalle.");
                    FacesContext.getCurrentInstance().addMessage(null, message);
                    return;
                }
                if (StringUtils.isBlank(this.getSelectedMaestroDetalle().getVdescripcion())) {
                    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, Constante.SEVERETY_ALERTA, "Descripción requerida. Ingrese la descripción del detalle.");
                    FacesContext.getCurrentInstance().addMessage(null, message);
                    return;
                }
                LoginMB loginMB = (LoginMB) JSFUtils.getSessionAttribute("loginMB");
                User user = loginMB.getUser();
                this.getSelectedMaestroDetalle().setVnombre(this.getSelectedMaestroDetalle().getVnombre().trim().toUpperCase());
                this.getSelectedMaestroDetalle().setVdescripcion(StringUtils.capitalize(this.getSelectedMaestroDetalle().getVdescripcion().trim()));
                this.getSelectedMaestroDetalle().setVusuariomodificacion(user.getVlogin());
                this.getSelectedMaestroDetalle().setDfechamodificacion(new Date());
                MaestroDetalleService service = (MaestroDetalleService) ServiceFinder.findBean("MaestroDetalleService");
                service.saveOrUpdate(this.getSelectedMaestroDetalle());
                this.setListaMaestroDetalle(service.getDetallesByMaestro(this.getSelectedMaestro()));
                this.cleanAttributes();
                RequestContext.getCurrentInstance().execute("PF('editdDialog').hide();");
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
    }

    public void activarDetalle(ActionEvent event) {
        try {
            if (event != null) {
                if (this.getSelectedMaestroDetalle() != null) {
                    LoginMB loginMB = (LoginMB) JSFUtils.getSessionAttribute("loginMB");
                    User user = loginMB.getUser();
                    MaestroDetalleService service = (MaestroDetalleService) ServiceFinder.findBean("MaestroDetalleService");
                    this.getSelectedMaestroDetalle().setNactivo(BigDecimal.ONE);
                    this.getSelectedMaestroDetalle().setDfechamodificacion(new Date());
                    this.getSelectedMaestroDetalle().setVusuariomodificacion(user.getVlogin());
                    service.saveOrUpdate(this.getSelectedMaestroDetalle());
                    this.setListaMaestroDetalle(service.getDetallesByMaestro(this.getSelectedMaestro()));
                } else {
                    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, Constante.SEVERETY_ALERTA, "Debe seleccionar el detalle a activar.");
                    FacesContext.getCurrentInstance().addMessage(null, message);
                }
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
    }

    public void desactivarDetalle(ActionEvent event) {
        try {
            if (event != null) {
                if (this.getSelectedMaestroDetalle() != null) {
                    LoginMB loginMB = (LoginMB) JSFUtils.getSessionAttribute("loginMB");
                    User user = loginMB.getUser();
                    MaestroDetalleService service = (MaestroDetalleService) ServiceFinder.findBean("MaestroDetalleService");
                    this.getSelectedMaestroDetalle().setNactivo(BigDecimal.ZERO);
                    this.getSelectedMaestroDetalle().setDfechamodificacion(new Date());
                    this.getSelectedMaestroDetalle().setVusuariomodificacion(user.getVlogin());
                    service.saveOrUpdate(this.getSelectedMaestroDetalle());
                    this.setListaMaestroDetalle(service.getDetallesByMaestro(this.getSelectedMaestro()));
                } else {
                    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, Constante.SEVERETY_ALERTA, "Debe seleccionar el detalle a desactivar.");
                    FacesContext.getCurrentInstance().addMessage(null, message);
                }
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
    }

    public boolean errorValidationDetail(MaestroDetalle detalle) {
        FacesMessage message;
        boolean error = false;
        try {
            if (detalle.getVnombre() == null || detalle.getVnombre().isEmpty()) {
                message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR.", "Nombre requerido. Ingrese el nombre del detalle.");
                FacesContext.getCurrentInstance().addMessage(null, message);
                error = true;
                return error;
            } else if (detalle.getVdescripcion() == null || detalle.getVdescripcion().isEmpty()) {
                message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR.", "Descripción requerida. Ingrese la descripción del detalle.");
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
