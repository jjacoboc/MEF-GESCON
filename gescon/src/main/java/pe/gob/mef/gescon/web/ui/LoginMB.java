/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.gob.mef.gescon.web.ui;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;
import pe.gob.mef.gescon.common.Constante;
import pe.gob.mef.gescon.service.AsignacionService;
import pe.gob.mef.gescon.service.PassService;
import pe.gob.mef.gescon.service.PerfilService;
import pe.gob.mef.gescon.service.UserService;
import pe.gob.mef.gescon.util.JSFUtils;
import pe.gob.mef.gescon.util.ServiceFinder;
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

    private User user;
    private Perfil perfil;
    private List<Perfil> perfiles;
    private String login;
    private String pass;
    private BigDecimal notificaciones;

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

    public BigDecimal getNotificaciones() {
        return notificaciones;
    }

    public void setNotificaciones(BigDecimal notificaciones) {
        this.notificaciones = notificaciones;
    }

    public String ingresar() {
        String page = StringUtils.EMPTY;
        try {
            if (StringUtils.isNotBlank(this.getLogin()) && StringUtils.isNotBlank(this.getPass())) {
                UserService service = (UserService) ServiceFinder.findBean("UserService");
                this.setUser(service.getUserByLogin(this.getLogin()));
                if (this.getUser() != null) {
                    PassService passService = (PassService) ServiceFinder.findBean("PassService");
                    Pass pas = passService.getPassByUser(this.getUser());
                    if(pas != null) {
                        PerfilService perfilService = (PerfilService) ServiceFinder.findBean("PerfilService");
                        List<Perfil> listaperfiles = perfilService.getPerfilesByUser(this.getUser());
                        if(!CollectionUtils.isEmpty(listaperfiles)) {
                            this.setPerfil(listaperfiles.get(0));
                            AsignacionService asignacionService = (AsignacionService) ServiceFinder.findBean("AsignacionService");
                            this.setNotificaciones(asignacionService.getNumberNotificationsByUser(this.getUser()));
                            if(this.getPerfil().getNperfilid().toString().equals(Constante.ROL_ADMINISTRADOR)) {
                                page = "/pages/indexAdmin?faces-redirect=true";
                            } else if(this.getPerfil().getNperfilid().toString().equals(Constante.ROL_MODERADOR)) {
                                page = "/index2?faces-redirect=true";
                            } else if(this.getPerfil().getNperfilid().toString().equals(Constante.ROL_ESPECIALISTA)) {
                                page = "/index2?faces-redirect=true";
                            } else if(this.getPerfil().getNperfilid().toString().equals(Constante.ROL_USUARIOEXTERNO)) {
                                page = "/index2?faces-redirect=true";
                            } else if(this.getPerfil().getNperfilid().toString().equals(Constante.ROL_USUARIOINTERNO)) {
                                page = "/index2?faces-redirect=true";
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
    
    public void logout() {
        try {
            JSFUtils.getSession().invalidate();
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
    }
}
