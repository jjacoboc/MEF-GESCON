/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.gob.mef.gescon.web.ui;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.primefaces.context.RequestContext;
import org.springframework.util.CollectionUtils;
import pe.gob.mef.gescon.common.Constante;
import pe.gob.mef.gescon.hibernate.domain.Mtuser;
import pe.gob.mef.gescon.hibernate.domain.TpassId;
import pe.gob.mef.gescon.service.AsignacionService;
import pe.gob.mef.gescon.service.ParametroService;
import pe.gob.mef.gescon.service.PassService;
import pe.gob.mef.gescon.service.PerfilService;
import pe.gob.mef.gescon.service.PoliticaPerfilService;
import pe.gob.mef.gescon.service.UserService;
import pe.gob.mef.gescon.util.DateUtils;
import pe.gob.mef.gescon.util.JSFUtils;
import pe.gob.mef.gescon.util.ServiceFinder;
import pe.gob.mef.gescon.web.bean.Consulta;
import pe.gob.mef.gescon.web.bean.Parametro;
import pe.gob.mef.gescon.web.bean.Pass;
import pe.gob.mef.gescon.web.bean.Perfil;
import pe.gob.mef.gescon.web.bean.User;

/**
 *
 * @author JJacobo
 */
@ManagedBean
@SessionScoped
public class LoginMB implements Serializable {

    private static final Log log = LogFactory.getLog(LoginMB.class);
    private User user;
    private Perfil perfil;
    private List<Perfil> perfiles;
    private String login;
    private String pass;
    private String newpass;
    private String confirmpass;
    private HashMap politicas;
    private BigDecimal notificaciones;
    private BigDecimal notificacionesAsignadas;
    private BigDecimal notificacionesRecibidas;
    private BigDecimal notificacionesAtendidas;
    private List<Consulta> listaNotificacionesAsignadas;
    private List<Consulta> filteredListaNotificacionesAsignadas;
    private List<Consulta> listaNotificacionesRecibidas;
    private List<Consulta> filteredListaNotificacionesRecibidas;
    private List<Consulta> listaNotificacionesAtendidas;
    private List<Consulta> filteredListaNotificacionesAtendidas;
    private List<Consulta> listaNotificacionesAlerta;
    private List<Consulta> filteredListaNotificacionesAlerta;
    private String alertaFlag;
    private Boolean claveCaducada;
    private String notificacion;
    private Consulta selectedNotification;

    /**
     * Creates a new instance of LoginMB
     */
    public LoginMB() {
        this.alertaFlag = "false";
        this.claveCaducada = false;
    }

    /**
     * @return the user
     */
    public User getUser() {
        return user;
    }

    /**
     * @param user the user to set
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * @return the perfil
     */
    public Perfil getPerfil() {
        return perfil;
    }

    /**
     * @param perfil the perfil to set
     */
    public void setPerfil(Perfil perfil) {
        this.perfil = perfil;
    }

    /**
     * @return the perfiles
     */
    public List<Perfil> getPerfiles() {
        return perfiles;
    }

    /**
     * @param perfiles the perfiles to set
     */
    public void setPerfiles(List<Perfil> perfiles) {
        this.perfiles = perfiles;
    }

    /**
     * @return the login
     */
    public String getLogin() {
        return login;
    }

    /**
     * @param login the login to set
     */
    public void setLogin(String login) {
        this.login = login;
    }

    /**
     * @return the pass
     */
    public String getPass() {
        return pass;
    }

    /**
     * @param pass the pass to set
     */
    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getNewpass() {
        return newpass;
    }

    public void setNewpass(String newpass) {
        this.newpass = newpass;
    }

    public String getConfirmpass() {
        return confirmpass;
    }

    public void setConfirmpass(String confirmpass) {
        this.confirmpass = confirmpass;
    }

    public HashMap getPoliticas() {
        return politicas;
    }

    public void setPoliticas(HashMap politicas) {
        this.politicas = politicas;
    }

    public BigDecimal getNotificaciones() {
        return notificaciones;
    }

    public void setNotificaciones(BigDecimal notificaciones) {
        this.notificaciones = notificaciones;
    }

    public BigDecimal getNotificacionesAsignadas() {
        return notificacionesAsignadas;
    }

    public void setNotificacionesAsignadas(BigDecimal notificacionesAsignadas) {
        this.notificacionesAsignadas = notificacionesAsignadas;
    }

    public BigDecimal getNotificacionesRecibidas() {
        return notificacionesRecibidas;
    }

    public void setNotificacionesRecibidas(BigDecimal notificacionesRecibidas) {
        this.notificacionesRecibidas = notificacionesRecibidas;
    }

    public BigDecimal getNotificacionesAtendidas() {
        return notificacionesAtendidas;
    }

    public void setNotificacionesAtendidas(BigDecimal notificacionesAtendidas) {
        this.notificacionesAtendidas = notificacionesAtendidas;
    }

    public List<Consulta> getListaNotificacionesAsignadas() {
        return listaNotificacionesAsignadas;
    }

    public void setListaNotificacionesAsignadas(List<Consulta> listaNotificacionesAsignadas) {
        this.listaNotificacionesAsignadas = listaNotificacionesAsignadas;
    }

    public List<Consulta> getFilteredListaNotificacionesAsignadas() {
        return filteredListaNotificacionesAsignadas;
    }

    public void setFilteredListaNotificacionesAsignadas(List<Consulta> filteredListaNotificacionesAsignadas) {
        this.filteredListaNotificacionesAsignadas = filteredListaNotificacionesAsignadas;
    }

    public List<Consulta> getListaNotificacionesRecibidas() {
        return listaNotificacionesRecibidas;
    }

    public void setListaNotificacionesRecibidas(List<Consulta> listaNotificacionesRecibidas) {
        this.listaNotificacionesRecibidas = listaNotificacionesRecibidas;
    }

    public List<Consulta> getFilteredListaNotificacionesRecibidas() {
        return filteredListaNotificacionesRecibidas;
    }

    public void setFilteredListaNotificacionesRecibidas(List<Consulta> filteredListaNotificacionesRecibidas) {
        this.filteredListaNotificacionesRecibidas = filteredListaNotificacionesRecibidas;
    }

    public List<Consulta> getListaNotificacionesAtendidas() {
        return listaNotificacionesAtendidas;
    }

    public void setListaNotificacionesAtendidas(List<Consulta> listaNotificacionesAtendidas) {
        this.listaNotificacionesAtendidas = listaNotificacionesAtendidas;
    }

    public List<Consulta> getFilteredListaNotificacionesAtendidas() {
        return filteredListaNotificacionesAtendidas;
    }

    public void setFilteredListaNotificacionesAtendidas(List<Consulta> filteredListaNotificacionesAtendidas) {
        this.filteredListaNotificacionesAtendidas = filteredListaNotificacionesAtendidas;
    }

    /**
     * @return the listaNotificacionesAlerta
     */
    public List<Consulta> getListaNotificacionesAlerta() {
        return listaNotificacionesAlerta;
    }

    /**
     * @param listaNotificacionesAlerta the listaNotificacionesAlerta to set
     */
    public void setListaNotificacionesAlerta(List<Consulta> listaNotificacionesAlerta) {
        this.listaNotificacionesAlerta = listaNotificacionesAlerta;
    }

    public List<Consulta> getFilteredListaNotificacionesAlerta() {
        return filteredListaNotificacionesAlerta;
    }

    public void setFilteredListaNotificacionesAlerta(List<Consulta> filteredListaNotificacionesAlerta) {
        this.filteredListaNotificacionesAlerta = filteredListaNotificacionesAlerta;
    }

    /**
     * @return the alertaFlag
     */
    public String getAlertaFlag() {
        return alertaFlag;
    }

    /**
     * @param alertaFlag the alertaFlag to set
     */
    public void setAlertaFlag(String alertaFlag) {
        this.alertaFlag = alertaFlag;
    }

    public Boolean getClaveCaducada() {
        return claveCaducada;
    }

    public void setClaveCaducada(Boolean claveCaducada) {
        this.claveCaducada = claveCaducada;
    }

    public String getNotificacion() {
        return notificacion;
    }

    public void setNotificacion(String notificacion) {
        this.notificacion = notificacion;
    }

    public Consulta getSelectedNotification() {
        return selectedNotification;
    }

    public void setSelectedNotification(Consulta selectedNotification) {
        this.selectedNotification = selectedNotification;
    }

    public String ingresar() {
        String page = StringUtils.EMPTY;
        try {
            if (StringUtils.isNotBlank(this.getLogin()) && StringUtils.isNotBlank(this.getPass())) {
                UserService service = (UserService) ServiceFinder.findBean("UserService");
                User usuario = service.getUserByLogin(this.getLogin());
                if (usuario != null) {
                    PassService passService = (PassService) ServiceFinder.findBean("PassService");
                    Pass pas = passService.getPassByUser(usuario);
                    if (pas != null && this.getPass().equals(pas.getVclave())) {
                        ParametroService parametroService = (ParametroService) ServiceFinder.findBean("ParametroService");
                        Parametro parametro = parametroService.getParametroById(BigDecimal.valueOf(Long.parseLong(Constante.DIAS_CADUCIDAD_CLAVE)));
                        long dias = DateUtils.getDifferenceDays(pas.getDfechacreacion(), new Date());
                        if(dias > Long.parseLong(parametro.getVvalor())) {
                            this.setClaveCaducada(true);
                            this.setPass(StringUtils.EMPTY);
                            this.setNewpass(StringUtils.EMPTY);
                            this.setConfirmpass(StringUtils.EMPTY);
                            RequestContext.getCurrentInstance().execute("PF('iniDialog').hide();");
                            RequestContext.getCurrentInstance().execute("PF('bar').show();");
                            RequestContext.getCurrentInstance().execute("PF('claDialog').show();");
                            return StringUtils.EMPTY;
                        }
                        PerfilService perfilService = (PerfilService) ServiceFinder.findBean("PerfilService");
                        List<Perfil> listaperfiles = perfilService.getPerfilesByUser(usuario);
                        if (!CollectionUtils.isEmpty(listaperfiles)) {
                            this.setUser(usuario);
                            this.setPerfil(listaperfiles.get(0));
                            PoliticaPerfilService politicaPerfilService = (PoliticaPerfilService) ServiceFinder.findBean("PoliticaPerfilService");
                            this.setPoliticas(politicaPerfilService.obtenerPoliticasByPerfil(this.getPerfil().getNperfilid()));
                            AsignacionService asignacionService = (AsignacionService) ServiceFinder.findBean("AsignacionService");
                            this.setNotificaciones(asignacionService.getNumberNotificationsByUser(this.getUser()));
                            this.setNotificacionesAsignadas(asignacionService.getNumberNotificationsAssignedByUser(this.getUser()));
                            this.setNotificacionesRecibidas(asignacionService.getNumberNotificationsReceivedByUser(this.getUser()));
                            this.setNotificacionesAtendidas(asignacionService.getNumberNotificationsServedByUser(this.getUser()));
                            this.setListaNotificacionesAlerta(asignacionService.getNotificationsAlertPanelByMtuser(this.getUser()));
                            if (this.getListaNotificacionesAlerta().isEmpty()) {
                                this.setAlertaFlag("false");
                            } else {
                                this.setAlertaFlag("true");
                            }
                            if (this.getPerfil().getNperfilid().toString().equals(Constante.ROL_ADMINISTRADOR)) {
                                page = "/pages/indexAdmin?faces-redirect=true";
                            } else {
                                page = "/index?faces-redirect=true";
                            }
                        } else {
                            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, Constante.SEVERETY_ALERTA, "El usuario ingresado no cuenta con un rol asignado. \nComuníquese con el administrador del servicio.");
                            FacesContext.getCurrentInstance().addMessage(null, message);
                            return StringUtils.EMPTY;
                        }
                    } else {
                        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, Constante.SEVERETY_ALERTA, "La contraseña ingresada es incorrecta.");
                        FacesContext.getCurrentInstance().addMessage(null, message);
                        return StringUtils.EMPTY;
                    }
                } else {
                    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, Constante.SEVERETY_ALERTA, "El usuario ingresado no se encuentra registrado.");
                    FacesContext.getCurrentInstance().addMessage(null, message);
                    return StringUtils.EMPTY;
                }
            } else {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, Constante.SEVERETY_ALERTA, "Ingrese su usuario y contraseña para ingresar.");
                FacesContext.getCurrentInstance().addMessage(null, message);
                return StringUtils.EMPTY;
            }
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
        return page;
    }

    public void loadAssignedPanel(ActionEvent event) {
        try {
            if (event != null) {
                AsignacionService asignacionService = (AsignacionService) ServiceFinder.findBean("AsignacionService");
                this.setListaNotificacionesAsignadas(asignacionService.getNotificationsAssignedPanelByUser(this.getUser()));
            }
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
    }

    public void loadReceivedPanel(ActionEvent event) {
        try {
            if (event != null) {
                AsignacionService asignacionService = (AsignacionService) ServiceFinder.findBean("AsignacionService");
                this.setListaNotificacionesRecibidas(asignacionService.getNotificationsReceivedPanelByUser(this.getUser()));
            }
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
    }

    public void loadServedPanel(ActionEvent event) {
        try {
            if (event != null) {
                AsignacionService asignacionService = (AsignacionService) ServiceFinder.findBean("AsignacionService");
                this.setListaNotificacionesAtendidas(asignacionService.getNotificationsServedPanelByUser(this.getUser()));
            }
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
    }
    
    public void toUpdatePassword(ActionEvent event) {
        try {
            this.setPass(StringUtils.EMPTY);
            this.setNewpass(StringUtils.EMPTY);
            this.setConfirmpass(StringUtils.EMPTY);
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
    }

    public void updatePassword(ActionEvent event) {
        User usuario;
        Pass pas;
        try {
            PassService passService = (PassService) ServiceFinder.findBean("PassService");
            if(this.getUser() != null) {
                usuario = this.getUser();
            } else {
                UserService service = (UserService) ServiceFinder.findBean("UserService");
                usuario = service.getUserByLogin(this.getLogin());
            }
            pas = passService.getPassByUser(usuario);
            if (StringUtils.isNotBlank(this.getPass())) {
                if (this.getPass().equals(pas.getVclave())) {
                    if (StringUtils.isNotBlank(this.getNewpass())) {
                        if (StringUtils.isNotBlank(this.getConfirmpass())) {
                            if (this.getNewpass().equals(this.getConfirmpass())) {
                                TpassId id = new TpassId();
                                id.setNpassid(passService.getNextPK());
                                id.setNusuarioid(usuario.getNusuarioid());
                                Mtuser mtuser = new Mtuser();
                                BeanUtils.copyProperties(usuario, mtuser);
                                Pass password = new Pass();
                                password.setId(id);
                                password.setVclave(this.getNewpass());
                                password.setVusuariocreacion(usuario.getVlogin());
                                password.setDfechacreacion(new Date());
                                passService.saveOrUpdate(password);
                                this.logout();
                            } else {
                                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, Constante.SEVERETY_ALERTA, "La nueva contraseña ingresada no coincide con la confirmación.");
                                FacesContext.getCurrentInstance().addMessage(null, message);
                            }
                        } else {
                            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, Constante.SEVERETY_ALERTA, "Confirme la nueva contraseña.");
                            FacesContext.getCurrentInstance().addMessage(null, message);
                        }
                    } else {
                        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, Constante.SEVERETY_ALERTA, "Ingrese la nueva contraseña.");
                        FacesContext.getCurrentInstance().addMessage(null, message);
                    }
                } else {
                    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, Constante.SEVERETY_ALERTA, "La contraseña ingresada es incorrecta.");
                    FacesContext.getCurrentInstance().addMessage(null, message);
                }
            } else {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, Constante.SEVERETY_ALERTA, "Ingrese la contraseña ingresada actual.");
                FacesContext.getCurrentInstance().addMessage(null, message);
            }

        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
    }
    
    public String redirect() {
        String page = null;
        try {
            page = (String) JSFUtils.getRequestParameter("page");
            JSFUtils.getSession().removeAttribute("administracionMB");
            JSFUtils.getSession().removeAttribute("baseLegalMB");
            JSFUtils.getSession().removeAttribute("categoriaMB");
            JSFUtils.getSession().removeAttribute("consultaMB");
            JSFUtils.getSession().removeAttribute("listaSessionMB");
            JSFUtils.getSession().removeAttribute("wikiMB");
            JSFUtils.getSession().removeAttribute("buenaPracticaMB");
            JSFUtils.getSession().removeAttribute("oportunidadMB");
            JSFUtils.getSession().removeAttribute("preguntaMB");
            JSFUtils.getSession().removeAttribute("contenidoMB");
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
        return page.concat("?faces-redirect=true");
    }

    public void logout() {
        try {
            JSFUtils.getSession().invalidate();
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
    }
}
