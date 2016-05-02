/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.gob.mef.gescon.web.ui;

import java.io.File;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.primefaces.context.RequestContext;
import org.primefaces.event.TabChangeEvent;
import org.springframework.util.CollectionUtils;
import pe.gob.mef.gescon.common.Constante;
import pe.gob.mef.gescon.common.Items;
import pe.gob.mef.gescon.common.Parameters;
import pe.gob.mef.gescon.hibernate.domain.TpassId;
import pe.gob.mef.gescon.service.AsignacionService;
import pe.gob.mef.gescon.service.ConsultaService;
import pe.gob.mef.gescon.service.ParametroService;
import pe.gob.mef.gescon.service.PassService;
import pe.gob.mef.gescon.service.PerfilService;
import pe.gob.mef.gescon.service.PoliticaPerfilService;
import pe.gob.mef.gescon.service.UbigeoService;
import pe.gob.mef.gescon.service.UserService;
import pe.gob.mef.gescon.util.DateUtils;
import pe.gob.mef.gescon.util.JSFUtils;
import pe.gob.mef.gescon.util.MailUtils;
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
    private String correo;
    private HashMap politicas;
    private BigDecimal notificaciones;
    private BigDecimal notificacionesAsignadas;
    private BigDecimal notificacionesRecibidas;
    private BigDecimal notificacionesAtendidas;
    private BigDecimal notificacionesPublicadas;
    private List<Consulta> listaNotificacionesAsignadas;
    private List<Consulta> filteredListaNotificacionesAsignadas;
    private List<Consulta> listaNotificacionesRecibidas;
    private List<Consulta> filteredListaNotificacionesRecibidas;
    private List<Consulta> listaNotificacionesAtendidas;
    private List<Consulta> filteredListaNotificacionesAtendidas;
    private List<Consulta> listaNotificacionesPublicadas;
    private List<Consulta> filteredListaNotificacionesPublicadas;
    private List<Consulta> listaNotificacionesAlerta;
    private List<Consulta> filteredListaNotificacionesAlerta;
    private String alertaFlag;
    private Boolean claveCaducada;
    private Boolean claveDefault;
    private String notificacion;
    private Consulta selectedNotification;
    private String photoImage;

    /**
     * Creates a new instance of LoginMB
     */
    public LoginMB() {
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

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
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

    public BigDecimal getNotificacionesPublicadas() {
        return notificacionesPublicadas;
    }

    public void setNotificacionesPublicadas(BigDecimal notificacionesPublicadas) {
        this.notificacionesPublicadas = notificacionesPublicadas;
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

    public List<Consulta> getListaNotificacionesPublicadas() {
        return listaNotificacionesPublicadas;
    }

    public void setListaNotificacionesPublicadas(List<Consulta> listaNotificacionesPublicadas) {
        this.listaNotificacionesPublicadas = listaNotificacionesPublicadas;
    }

    public List<Consulta> getFilteredListaNotificacionesPublicadas() {
        return filteredListaNotificacionesPublicadas;
    }

    public void setFilteredListaNotificacionesPublicadas(List<Consulta> filteredListaNotificacionesPublicadas) {
        this.filteredListaNotificacionesPublicadas = filteredListaNotificacionesPublicadas;
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

    public Boolean getClaveDefault() {
        return claveDefault;
    }

    public void setClaveDefault(Boolean claveDefault) {
        this.claveDefault = claveDefault;
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

    public String getPhotoImage() {
        return photoImage;
    }

    public void setPhotoImage(String photoImage) {
        this.photoImage = photoImage;
    }

    @PostConstruct
    public void init() {
        try {
            this.alertaFlag = "false";
            this.claveCaducada = false;
            this.claveDefault = false;
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
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
                        Parametro passDefault = parametroService.getParametroById(BigDecimal.valueOf(Long.parseLong(Constante.CLAVE_DEFAULT)));
                        if (pas.getVclave().equals(passDefault.getVvalor())) {
                            ResourceBundle bundle = ResourceBundle.getBundle(Parameters.getMessages());
                            this.setNotificacion(bundle.getString("notificacion1"));
                            this.setClaveDefault(true);
                            this.setPass(StringUtils.EMPTY);
                            this.setNewpass(StringUtils.EMPTY);
                            this.setConfirmpass(StringUtils.EMPTY);
                            RequestContext.getCurrentInstance().execute("PF('iniDialog').hide();");
                            RequestContext.getCurrentInstance().execute("PF('bar').show();");
                            RequestContext.getCurrentInstance().execute("PF('claDialog').show();");
                            return StringUtils.EMPTY;
                        }
                        Parametro caducidad = parametroService.getParametroById(BigDecimal.valueOf(Long.parseLong(Constante.DIAS_CADUCIDAD_CLAVE)));
                        long dias = DateUtils.getDifferenceDays(pas.getDfechacreacion(), new Date());
                        if (dias > Long.parseLong(caducidad.getVvalor())) {
                            ResourceBundle bundle = ResourceBundle.getBundle(Parameters.getMessages());
                            this.setNotificacion(bundle.getString("notificacion2"));
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
                            this.setNotificacionesPublicadas(asignacionService.getNumberNotificationsPublicByUser(this.getUser()));
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
                this.setFilteredListaNotificacionesAsignadas(new ArrayList());
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
                this.setFilteredListaNotificacionesRecibidas(new ArrayList());
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
                this.setFilteredListaNotificacionesAtendidas(new ArrayList());
            }
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
    }
    
    public void loadPublicPanel(ActionEvent event) {
        try {
            if (event != null) {
                AsignacionService asignacionService = (AsignacionService) ServiceFinder.findBean("AsignacionService");
                this.setListaNotificacionesPublicadas(asignacionService.getNotificationsPublicPanelByUser(this.getUser()));
                this.setFilteredListaNotificacionesPublicadas(new ArrayList());
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
            if (this.getUser() != null) {
                usuario = this.getUser();
            } else {
                UserService service = (UserService) ServiceFinder.findBean("UserService");
                usuario = service.getUserByLogin(this.getLogin());
            }
            pas = passService.getPassByUser(usuario);
            if (StringUtils.isNotBlank(this.getPass())) {
                if (this.getPass().equals(pas.getVclave())) {
                    if (StringUtils.isNotBlank(this.getNewpass())) {
                        if (!this.getNewpass().equals(pas.getVclave())) {
                            if (StringUtils.isNotBlank(this.getConfirmpass())) {
                                if (this.getNewpass().equals(this.getConfirmpass())) {
                                    TpassId id = new TpassId();
                                    id.setNpassid(passService.getNextPK());
                                    id.setNusuarioid(usuario.getNusuarioid());
                                    Pass password = new Pass();
                                    password.setId(id);
                                    password.setVclave(this.getNewpass());
                                    password.setVusuariocreacion(usuario.getVlogin());
                                    password.setDfechacreacion(new Date());
                                    passService.saveOrUpdate(password);
                                    this.logout();
                                    RequestContext.getCurrentInstance().execute("logout();");
                                } else {
                                    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, Constante.SEVERETY_ALERTA, "La nueva contraseña ingresada no coincide con la confirmación.");
                                    FacesContext.getCurrentInstance().addMessage(null, message);
                                }
                            } else {
                                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, Constante.SEVERETY_ALERTA, "Confirme la nueva contraseña.");
                                FacesContext.getCurrentInstance().addMessage(null, message);
                            }
                        } else {
                            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, Constante.SEVERETY_ALERTA, "La nueva contraseña debe ser diferente a la actual.");
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

    public void refreshNotifications() {
        HashMap filter = new HashMap();
        try {
            AsignacionService asignacionService = (AsignacionService) ServiceFinder.findBean("AsignacionService");
            this.setNotificaciones(asignacionService.getNumberNotificationsByUser(this.getUser()));
            this.setNotificacionesAsignadas(asignacionService.getNumberNotificationsAssignedByUser(this.getUser()));
            this.setNotificacionesRecibidas(asignacionService.getNumberNotificationsReceivedByUser(this.getUser()));
            this.setNotificacionesAtendidas(asignacionService.getNumberNotificationsServedByUser(this.getUser()));
            this.setNotificacionesPublicadas(asignacionService.getNumberNotificationsPublicByUser(this.getUser()));
            this.setListaNotificacionesAlerta(asignacionService.getNotificationsAlertPanelByMtuser(this.getUser()));
            
            AdministracionMB admMB = (AdministracionMB) JSFUtils.getSessionAttribute("administracionMB");
            ConsultaService consultaService = (ConsultaService) ServiceFinder.findBean("ConsultaService");
            filter.put("ntipoconocimientoid", Constante.BASELEGAL);
            admMB.setListaDestacadosBL(consultaService.getDestacadosByTipoConocimiento(filter));
            filter.put("ntipoconocimientoid", Constante.PREGUNTAS);
            admMB.setListaDestacadosPR(consultaService.getDestacadosByTipoConocimiento(filter));
            filter.put("ntipoconocimientoid", Constante.WIKI);
            admMB.setListaDestacadosWK(consultaService.getDestacadosByTipoConocimiento(filter));
            filter.put("ntipoconocimientoid", Constante.BUENAPRACTICA);
            admMB.setListaDestacadosBP(consultaService.getDestacadosByTipoConocimiento(filter));
            filter.put("ntipoconocimientoid", Constante.CONTENIDO);
            admMB.setListaDestacadosCT(consultaService.getDestacadosByTipoConocimiento(filter));
            filter.put("ntipoconocimientoid", Constante.OPORTUNIDADMEJORA);
            admMB.setListaDestacadosOM(consultaService.getDestacadosByTipoConocimiento(filter));
            JSFUtils.getSession().setAttribute("administracionMB", admMB);
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
            JSFUtils.getSession().removeAttribute("buenaPracticaMB");
            JSFUtils.getSession().removeAttribute("categoriaMB");
            JSFUtils.getSession().removeAttribute("consultaMB");
            JSFUtils.getSession().removeAttribute("contenidoMB");
            JSFUtils.getSession().removeAttribute("listaSessionMB");
            JSFUtils.getSession().removeAttribute("oportunidadMB");
            JSFUtils.getSession().removeAttribute("pendienteMB");
            JSFUtils.getSession().removeAttribute("preguntaMB");
            JSFUtils.getSession().removeAttribute("userMB");
            JSFUtils.getSession().removeAttribute("wikiMB");
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
        return page.concat("?faces-redirect=true");
    }
    
    public void toMyProfile(ActionEvent event) {
        try {
            FacesContext context = FacesContext.getCurrentInstance();
            System.out.println("External context: " + context.getExternalContext().toString());
            UserMB userMB = (UserMB) JSFUtils.getSessionAttribute("userMB");
            userMB = userMB != null ? userMB : new UserMB();
            this.setPhotoImage(getPhotoUser());
            UbigeoService ubigeoService = (UbigeoService) ServiceFinder.findBean("UbigeoService");
            userMB.setListaDepartamento(new Items(ubigeoService.getDepartamentos(), null, "vcoddep", "vdescdep").getItems());
            userMB.setListaProvincia(new Items(ubigeoService.getProvinciasPorDepartamento(this.getUser().getVdpto()), null, "vcodprov", "vdescprov").getItems());
            userMB.setListaDistrito(new Items(ubigeoService.getDistritosPorProvincia(this.getUser().getVdpto(), this.getUser().getVprov()), null, "vcoddist", "vdescdist").getItems());
            JSFUtils.setSessionAttribute("userMB", userMB);
            FacesContext.getCurrentInstance().getExternalContext().redirect("/gescon/pages/miperfil.xhtml");
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
    }
    
    public String getPhotoUser() {
        ResourceBundle bundle = ResourceBundle.getBundle(Parameters.getParameters());
        String path = bundle.getString("usrpath");
        String pathImage = path + File.separator + this.getUser().getNusuarioid() + File.separator;
        String newFileName = pathImage + "photo.jpg";
        File f = new File(newFileName);
        if (!f.exists()) {
            newFileName = StringUtils.EMPTY;
        }
        return newFileName;
    }
    
    public void toForgotPassword(ActionEvent event) {
        try {
            this.setLogin(StringUtils.EMPTY);
            this.setCorreo(StringUtils.EMPTY);
            Iterator<FacesMessage> iter = FacesContext.getCurrentInstance().getMessages();
            if (iter.hasNext() == true) {
                iter.remove();
                FacesContext.getCurrentInstance().renderResponse();
            }
            RequestContext.getCurrentInstance().execute("PF('iniDialog').hide();");
        } catch(Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
    }
    
    public void forgotPassword(ActionEvent event) {
        User usuario;
        try {
            UserService service = (UserService) ServiceFinder.findBean("UserService");
            if(StringUtils.isNotBlank(this.getLogin())) {
                usuario = service.getUserByLogin(this.getLogin());
            } else {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, Constante.SEVERETY_ALERTA, "Ingrese su usuario de sesión.");
                FacesContext.getCurrentInstance().addMessage(null, message);
                return;
            }
            if(StringUtils.isNotBlank(this.getCorreo())) {
                usuario = service.getUserByEmail(this.getCorreo());
            } else {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, Constante.SEVERETY_ALERTA, "Ingrese su correo electrónico.");
                FacesContext.getCurrentInstance().addMessage(null, message);
                return;
            }
            PassService passService = (PassService) ServiceFinder.findBean("PassService");
            Pass pas = passService.getPassByUser(usuario);
            MailUtils.sendMail(usuario.getVcorreo(), "GESCON MEF - Olvidé mi contraseña.", getForgotPasswordBodyMail(pas.getVclave()));
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "INFO.", "Su contraseña ha sido enviada al correo registrado. Por favor, revise su bandeja de entrada.");
            FacesContext.getCurrentInstance().addMessage(null, message);
        } catch(Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
    }
    
    public void onTabChange(TabChangeEvent event) {
        try {
            this.setLogin(StringUtils.EMPTY);
            this.setCorreo(StringUtils.EMPTY);
            Iterator<FacesMessage> iter = FacesContext.getCurrentInstance().getMessages();
            if (iter.hasNext() == true) {
                iter.remove();
                FacesContext.getCurrentInstance().renderResponse();
            }
        } catch(Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
    }
    
    public String getForgotPasswordBodyMail(String clave) {
        StringBuilder body = new StringBuilder();
        try {
            body.append("<table>");
            body.append("<tr>");
            body.append("<td>");
            body.append("<img src='cid:banner'>");
            body.append("</td>");
            body.append("</tr>");
            body.append("<tr style='font-size: 30px; font-weight: bold; height: 50px;'>");
            body.append("<td colspan='6' style='text-align: center;'>");
            body.append("Olividó su Contraseña?");
            body.append("</td>");
            body.append("</tr>");
            body.append("<tr style='font-size: 12px; height: 40px;'>");
            body.append("<td colspan='6' style='text-align: center;'>");
            body.append("Estimado usuario, por motivos de seguridad le solicitamos, por favor, elimine este correo de su bandeja de entrada.");
            body.append("</td>");
            body.append("</tr>");
            body.append("<tr style='font-size: 14px; height: 40px;'>");
            body.append("<td colspan='6' style='text-align: center;'>");
            body.append("Actual clave de acceso es <span style='font-weight: bold; font-style: italic; color: #2E77D2;'>").append(clave).append("</span>");
            body.append("</td>");
            body.append("</tr>");
            body.append("<tr style='font-size: 12px; height: 40px;'>");
            body.append("<td colspan='6' style='text-align: center;'>");
            body.append("Haz clic en el link de abajo para acceder al portal; en GESCON cuidamos tu privacidad. Gracias. ");
            body.append("</td>");
            body.append("</tr>");
            body.append("<tr>");
            body.append("<td style='text-align:center;vertical-align:top;'>");
            body.append("<a href='http://192.168.1.11:8180/gescon/'><img src='cid:boton'></a>");
            body.append("</td>");
            body.append("</tr>");
            body.append("</table>");
        } catch (Exception e) {
            e.getMessage();
        }

        return body.toString();
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
