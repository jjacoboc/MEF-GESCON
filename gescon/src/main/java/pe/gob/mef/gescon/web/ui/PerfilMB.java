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
import org.primefaces.model.DualListModel;
import org.springframework.util.CollectionUtils;
import pe.gob.mef.gescon.common.Constante;
import pe.gob.mef.gescon.common.Items;
import pe.gob.mef.gescon.hibernate.domain.TpoliticaPerfilId;
import pe.gob.mef.gescon.service.PerfilService;
import pe.gob.mef.gescon.service.PoliticaPerfilService;
import pe.gob.mef.gescon.service.PoliticaService;
import pe.gob.mef.gescon.util.JSFUtils;
import pe.gob.mef.gescon.util.ServiceFinder;
import pe.gob.mef.gescon.web.bean.Perfil;
import pe.gob.mef.gescon.web.bean.PoliticaPerfil;
import pe.gob.mef.gescon.web.bean.User;

/**
 *
 * @author JJacobo
 */
@ManagedBean
@ViewScoped
public class PerfilMB implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final Log log = LogFactory.getLog(PerfilMB.class);
    private BigDecimal id;
    private String nombre;
    private String descripcion;
    private BigDecimal activo;
    private List<Perfil> listaPerfils;
    private List<Perfil> filteredListaPerfils;
    private Perfil selectedPerfil;
    private DualListModel<String> listaPoliticas;

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

    public List<Perfil> getFilteredListaPerfils() {
        return filteredListaPerfils;
    }

    public void setFilteredListaPerfils(List<Perfil> filteredListaPerfils) {
        this.filteredListaPerfils = filteredListaPerfils;
    }

    /**
     * @return the listaPoliticas
     * @throws java.lang.Exception
     */
    public DualListModel<String> getListaPoliticas() throws Exception {
        return listaPoliticas;
    }

    /**
     * @param listaPoliticas the listaPoliticas to set
     */
    public void setListaPoliticas(DualListModel<String> listaPoliticas) {
        this.listaPoliticas = listaPoliticas;
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

            List<String> politicasSource = new ArrayList<String>();
            List<String> politicasTarget = new ArrayList<String>();

            if (this.listaPoliticas == null) {
                PoliticaService politicaservice = (PoliticaService) ServiceFinder.findBean("PoliticaService");
                politicasSource = new Items(politicaservice.getPoliticas(), null, "npoliticaid", "vnombre").getItems();
                this.listaPoliticas = new DualListModel<String>(politicasSource, politicasTarget);
            }
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
        this.setSelectedPerfil(null);
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
                if(!CollectionUtils.isEmpty(this.getFilteredListaPerfils())) {
                    this.setSelectedPerfil(this.getFilteredListaPerfils().get(index));
                } else {
                    this.setSelectedPerfil(this.getListaPerfils().get(index));
                }
                this.setFilteredListaPerfils(new ArrayList());
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
            if (CollectionUtils.isEmpty(this.getListaPerfils())) {
                this.setListaPerfils(Collections.EMPTY_LIST);
            }
            Perfil perfil = new Perfil();
            perfil.setVnombre(this.getNombre());
            perfil.setVdescripcion(this.getDescripcion());
            if (!errorValidation(perfil)) {
                LoginMB loginMB = (LoginMB) JSFUtils.getSessionAttribute("loginMB");
                User user = loginMB.getUser();
                PerfilService service = (PerfilService) ServiceFinder.findBean("PerfilService");
                perfil.setNperfilid(service.getNextPK());
                perfil.setVnombre(StringUtils.upperCase(this.getNombre().trim()));
                perfil.setVdescripcion(StringUtils.capitalize(this.getDescripcion().trim()));
                perfil.setNactivo(BigDecimal.ONE);
                perfil.setDfechacreacion(new Date());
                perfil.setVusuariocreacion(user.getVlogin());
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
            if (event != null) {
                this.setSelectedRow(event);
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
    }

    public void toAsig(ActionEvent event) {
        try {
            if (event != null) {
                List<String> politicasSource = new ArrayList<String>();
                List<String> politicasTarget = new ArrayList<String>();
                this.setSelectedRow(event);
                PoliticaPerfilService ppservice = (PoliticaPerfilService) ServiceFinder.findBean("PoliticaPerfilService");
                politicasSource = new Items(ppservice.obtenerListaPoliticasDisp(this.getSelectedPerfil().getNperfilid()), null, "npoliticaid", "vnombre").getItems();
                politicasTarget = new Items(ppservice.obtenerListaPoliticas(this.getSelectedPerfil().getNperfilid()), null, "npoliticaid", "vnombre").getItems();
                this.listaPoliticas = new DualListModel<String>(politicasSource, politicasTarget);
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
    }

    public void update(ActionEvent event) {
        try {
            if (event != null) {
                if (StringUtils.isBlank(this.getSelectedPerfil().getVnombre())) {
                    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, Constante.SEVERETY_ALERTA, "Nombre requerido. Ingrese el nombre de perfil.");
                    FacesContext.getCurrentInstance().addMessage(null, message);
                    return;
                }
                if (StringUtils.isBlank(this.getSelectedPerfil().getVdescripcion())) {
                    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, Constante.SEVERETY_ALERTA, "DescripciÃ³n requerida. Ingrese la descripciÃ³n de perfil.");
                    FacesContext.getCurrentInstance().addMessage(null, message);
                    return;
                }
                LoginMB loginMB = (LoginMB) JSFUtils.getSessionAttribute("loginMB");
                User user = loginMB.getUser();
                this.getSelectedPerfil().setVnombre(StringUtils.upperCase(this.getSelectedPerfil().getVnombre().trim()));
                this.getSelectedPerfil().setVdescripcion(StringUtils.capitalize(this.getSelectedPerfil().getVdescripcion().trim()));
                this.getSelectedPerfil().setVusuariomodificacion(user.getVlogin());
                this.getSelectedPerfil().setDfechamodificacion(new Date());
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
            if (event != null) {
                if (this.getSelectedPerfil() != null) {
                    LoginMB loginMB = (LoginMB) JSFUtils.getSessionAttribute("loginMB");
                    User user = loginMB.getUser();
                    PerfilService service = (PerfilService) ServiceFinder.findBean("PerfilService");
                    this.getSelectedPerfil().setNactivo(BigDecimal.ONE);
                    this.getSelectedPerfil().setDfechamodificacion(new Date());
                    this.getSelectedPerfil().setVusuariomodificacion(user.getVlogin());
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
            if (event != null) {
                if (this.getSelectedPerfil() != null) {
                    LoginMB loginMB = (LoginMB) JSFUtils.getSessionAttribute("loginMB");
                    User user = loginMB.getUser();
                    PerfilService service = (PerfilService) ServiceFinder.findBean("PerfilService");
                    this.getSelectedPerfil().setNactivo(BigDecimal.ZERO);
                    this.getSelectedPerfil().setDfechamodificacion(new Date());
                    this.getSelectedPerfil().setVusuariomodificacion(user.getVlogin());
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
            } else if (perfil.getVdescripcion() == null || perfil.getVdescripcion().isEmpty()) {
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

    public void asignar(ActionEvent event) {
        try {
            PoliticaPerfilService service = (PoliticaPerfilService) ServiceFinder.findBean("PoliticaPerfilService");
            service.delete(this.getSelectedPerfil().getNperfilid());
            LoginMB loginMB = (LoginMB) JSFUtils.getSessionAttribute("loginMB");
            User user = loginMB.getUser();
            for (int x = 0; x < this.listaPoliticas.getTarget().size(); x++) {
                TpoliticaPerfilId tpoliticaperfilid = new TpoliticaPerfilId();
                tpoliticaperfilid.setNperfilid(this.getSelectedPerfil().getNperfilid());
                tpoliticaperfilid.setNpoliticaid(BigDecimal.valueOf(Long.parseLong(this.listaPoliticas.getTarget().get(x))));
                PoliticaPerfil politicaperfil = new PoliticaPerfil();
                politicaperfil.setId(tpoliticaperfilid);
                politicaperfil.setDfechacreacion(new Date());
                politicaperfil.setVusuariocreacion(user.getVlogin());
                service.saveOrUpdate(politicaperfil);
                RequestContext.getCurrentInstance().execute("PF('asigDialog').hide();");
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
    }
}