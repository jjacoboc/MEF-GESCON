/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.gob.mef.gescon.web.ui;

import java.io.File;
import java.io.FileOutputStream;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.model.SelectItem;
import javax.servlet.ServletContext;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.primefaces.component.selectonemenu.SelectOneMenu;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.CroppedImage;
import pe.gob.mef.gescon.common.Constante;
import pe.gob.mef.gescon.common.Items;
import pe.gob.mef.gescon.common.Parameters;
import pe.gob.mef.gescon.hibernate.domain.TpassId;
import pe.gob.mef.gescon.hibernate.domain.TuserPerfil;
import pe.gob.mef.gescon.hibernate.domain.TuserPerfilId;
import pe.gob.mef.gescon.service.AsignacionService;
import pe.gob.mef.gescon.service.ParametroService;
import pe.gob.mef.gescon.service.PassService;
import pe.gob.mef.gescon.service.PerfilService;
import pe.gob.mef.gescon.service.UbigeoService;
import pe.gob.mef.gescon.service.UserService;
import pe.gob.mef.gescon.util.JSFUtils;
import pe.gob.mef.gescon.util.MailUtils;
import pe.gob.mef.gescon.util.ServiceFinder;
import pe.gob.mef.gescon.web.bean.Parametro;
import pe.gob.mef.gescon.web.bean.Pass;
import pe.gob.mef.gescon.web.bean.User;

/**
 *
 * @author JJacobo
 */
@ManagedBean
@SessionScoped
public class UserMB implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final Log log = LogFactory.getLog(UserMB.class);
    private String path;
    private String temppath;
    private BigDecimal id;
    private String nombres;
    private String apellidos;
    private String login;
    private String clave;
    private String confirmaClave;
    private Date fechaNacimiento;
    private String dni;
    private String sexo;
    private String correo;
    private List<SelectItem> listaPerfil;
    private List<SelectItem> listaDepartamento;
    private List<SelectItem> listaProvincia;
    private List<SelectItem> listaDistrito;
    private String perfil;
    private String departamento;
    private String provincia;
    private String distrito;
    private String profesion;
    private String trabajaMef;
    private String entidad;
    private String cargo;
    private String pliego;
    private String sector;
    private String gobierno;
    private String area;
    private List<User> listaUser;
    private List<User> filteredListaUser;
    private User selectedUser;
    private String imagenTemporal;
    private CroppedImage croppedImage;
    private String photoFileName;
    private String photoImage;

    /**
     * Creates a new instance of MaestroMB
     */
    public UserMB() {
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getTemppath() {
        return temppath;
    }

    public void setTemppath(String temppath) {
        this.temppath = temppath;
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
     * @return the nombres
     */
    public String getNombres() {
        return nombres;
    }

    /**
     * @param nombres the nombres to set
     */
    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    /**
     * @return the apellidos
     */
    public String getApellidos() {
        return apellidos;
    }

    /**
     * @param apellidos the apellidos to set
     */
    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    /**
     * @return the sexo
     */
    public String getSexo() {
        return sexo;
    }

    /**
     * @param sexo the sexo to set
     */
    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public List<SelectItem> getListaPerfil() {
        return listaPerfil;
    }

    public void setListaPerfil(List<SelectItem> listaPerfil) {
        this.listaPerfil = listaPerfil;
    }

    public List<SelectItem> getListaDepartamento() {
        return listaDepartamento;
    }

    public void setListaDepartamento(List<SelectItem> listaDepartamento) {
        this.listaDepartamento = listaDepartamento;
    }

    public List<SelectItem> getListaProvincia() {
        return listaProvincia;
    }

    public void setListaProvincia(List<SelectItem> listaProvincia) {
        this.listaProvincia = listaProvincia;
    }

    public List<SelectItem> getListaDistrito() {
        return listaDistrito;
    }

    public void setListaDistrito(List<SelectItem> listaDistrito) {
        this.listaDistrito = listaDistrito;
    }

    public String getPerfil() {
        return perfil;
    }

    public void setPerfil(String perfil) {
        this.perfil = perfil;
    }

    /**
     * @return the clave
     */
    public String getClave() {
        return clave;
    }

    /**
     * @param clave the clave to set
     */
    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getConfirmaClave() {
        return confirmaClave;
    }

    public void setConfirmaClave(String confirmaClave) {
        this.confirmaClave = confirmaClave;
    }

    /**
     * @return the dni
     */
    public String getDni() {
        return dni;
    }

    /**
     * @param dni the dni to set
     */
    public void setDni(String dni) {
        this.dni = dni;
    }

    /**
     * @return the departamento
     */
    public String getDepartamento() {
        return departamento;
    }

    /**
     * @param departamento the departamento to set
     */
    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    /**
     * @return the provincia
     */
    public String getProvincia() {
        return provincia;
    }

    /**
     * @param provincia the provincia to set
     */
    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    /**
     * @return the distrito
     */
    public String getDistrito() {
        return distrito;
    }

    /**
     * @param distrito the distrito to set
     */
    public void setDistrito(String distrito) {
        this.distrito = distrito;
    }

    /**
     * @return the profesion
     */
    public String getProfesion() {
        return profesion;
    }

    /**
     * @param profesion the profesion to set
     */
    public void setProfesion(String profesion) {
        this.profesion = profesion;
    }

    /**
     * @return the trabajaMef
     */
    public String getTrabajaMef() {
        return trabajaMef;
    }

    /**
     * @param trabajaMef the trabajaMef to set
     */
    public void setTrabajaMef(String trabajaMef) {
        this.trabajaMef = trabajaMef;
    }

    /**
     * @return the entidad
     */
    public String getEntidad() {
        return entidad;
    }

    /**
     * @param entidad the entidad to set
     */
    public void setEntidad(String entidad) {
        this.entidad = entidad;
    }

    /**
     * @return the cargo
     */
    public String getCargo() {
        return cargo;
    }

    /**
     * @param cargo the cargo to set
     */
    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    /**
     * @return the pliego
     */
    public String getPliego() {
        return pliego;
    }

    /**
     * @param pliego the pliego to set
     */
    public void setPliego(String pliego) {
        this.pliego = pliego;
    }

    /**
     * @return the sector
     */
    public String getSector() {
        return sector;
    }

    /**
     * @param sector the sector to set
     */
    public void setSector(String sector) {
        this.sector = sector;
    }

    /**
     * @return the gobierno
     */
    public String getGobierno() {
        return gobierno;
    }

    /**
     * @param gobierno the gobierno to set
     */
    public void setGobierno(String gobierno) {
        this.gobierno = gobierno;
    }

    /**
     * @return the area
     */
    public String getArea() {
        return area;
    }

    /**
     * @param area the area to set
     */
    public void setArea(String area) {
        this.area = area;
    }

    /**
     * @return the listaUser
     */
    public List<User> getListaUser() {
        return listaUser;
    }

    /**
     * @param listaUser the listaUser to set
     */
    public void setListaUser(List<User> listaUser) {
        this.listaUser = listaUser;
    }

    public List<User> getFilteredListaUser() {
        return filteredListaUser;
    }

    public void setFilteredListaUser(List<User> filteredListaUser) {
        this.filteredListaUser = filteredListaUser;
    }

    /**
     * @return the selectedUser
     */
    public User getSelectedUser() {
        return selectedUser;
    }

    /**
     * @param selectedUser the selectedUser to set
     */
    public void setSelectedUser(User selectedUser) {
        this.selectedUser = selectedUser;
    }

    public String getImagenTemporal() {
        return imagenTemporal;
    }

    public void setImagenTemporal(String imagenTemporal) {
        this.imagenTemporal = imagenTemporal;
    }

    public CroppedImage getCroppedImage() {
        return croppedImage;
    }

    public void setCroppedImage(CroppedImage croppedImage) {
        this.croppedImage = croppedImage;
    }

    public String getPhotoFileName() {
        return photoFileName;
    }

    public void setPhotoFileName(String photoFileName) {
        this.photoFileName = photoFileName;
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
            ResourceBundle bundle = ResourceBundle.getBundle(Parameters.getParameters());
            this.setPath(bundle.getString("usrpath"));
            this.setTemppath(bundle.getString("temppath"));
            this.setPhotoFileName("photo.jpg");
            UserService userService = (UserService) ServiceFinder.findBean("UserService");
            this.setListaUser(userService.getUsers());
            UbigeoService ubigeoService = (UbigeoService) ServiceFinder.findBean("UbigeoService");
            listaDepartamento = new Items(ubigeoService.getDepartamentos(), null, "vcoddep", "vdescdep").getItems();
            PerfilService perfilService = (PerfilService) ServiceFinder.findBean("PerfilService");
            listaPerfil = new Items(perfilService.getPerfilsActived(), null, "nperfilid", "vnombre").getItems();
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
    }

    public void handleDepartamentoChangeValue(AjaxBehaviorEvent event) {
        try {
            if (event != null) {
                String coddep = (String) ((SelectOneMenu) event.getSource()).getValue();
                this.setDepartamento(coddep);
                if (StringUtils.isNotBlank(coddep)) {
                    UbigeoService ubigeoService = (UbigeoService) ServiceFinder.findBean("UbigeoService");
                    listaProvincia = new Items(ubigeoService.getProvinciasPorDepartamento(coddep), null, "vcodprov", "vdescprov").getItems();
                } else {
                    this.setListaProvincia(new ArrayList());
                    this.setListaDistrito(new ArrayList());
                    this.setProvincia(StringUtils.EMPTY);
                    this.setDistrito(StringUtils.EMPTY);
                }
            }
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
    }

    public void handleProvinciaChangeValue(AjaxBehaviorEvent event) {
        try {
            if (event != null) {
                String codprov = (String) ((SelectOneMenu) event.getSource()).getValue();
                this.setProvincia(codprov);
                if (StringUtils.isNotBlank(codprov)) {
                    UbigeoService ubigeoService = (UbigeoService) ServiceFinder.findBean("UbigeoService");
                    listaDistrito = new Items(ubigeoService.getDistritosPorProvincia(this.getDepartamento(), codprov), null, "vcoddist", "vdescdist").getItems();
                } else {
                    this.setListaDistrito(new ArrayList());
                    this.setDistrito(StringUtils.EMPTY);
                }
            }
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
    }

    public void setSelectedRow(ActionEvent event) {
        try {
            if (event != null) {
                int index = Integer.parseInt((String) JSFUtils.getRequestParameter("index"));
                if (!CollectionUtils.isEmpty(this.getFilteredListaUser())) {
                    this.setSelectedUser(this.getFilteredListaUser().get(index));
                } else {
                    this.setSelectedUser(this.getListaUser().get(index));
                }
                this.setFilteredListaUser(new ArrayList());
            }
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
    }

    public void cleanAttributes() {
        this.setTrabajaMef(BigDecimal.ONE.toString());
        this.setId(BigDecimal.ZERO);
        this.setDni(StringUtils.EMPTY);
        this.setNombres(StringUtils.EMPTY);
        this.setApellidos(StringUtils.EMPTY);
        this.setLogin(StringUtils.EMPTY);
        this.setClave(StringUtils.EMPTY);
        this.setConfirmaClave(StringUtils.EMPTY);
        this.setFechaNacimiento(null);
        this.setSexo(StringUtils.EMPTY);
        this.setCorreo(StringUtils.EMPTY);
        this.setListaProvincia(new ArrayList());
        this.setListaDistrito(new ArrayList());
        this.setPerfil(StringUtils.EMPTY);
        this.setDepartamento(StringUtils.EMPTY);
        this.setProvincia(StringUtils.EMPTY);
        this.setDistrito(StringUtils.EMPTY);
        this.setProfesion(StringUtils.EMPTY);
        this.setCargo(StringUtils.EMPTY);
        
        Iterator<FacesMessage> iter = FacesContext.getCurrentInstance().getMessages();
        if (iter.hasNext() == true) {
            iter.remove();
            FacesContext.getCurrentInstance().renderResponse();
        }
    }

    public void toSave(ActionEvent event) {
        try {
            this.cleanAttributes();
            this.setImagenTemporal(StringUtils.EMPTY);
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
    }

    public String toSave() {
        try {
            this.cleanAttributes();
            this.setPhotoImage(StringUtils.EMPTY);
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
        return "/pages/usuarioexterno/registro?faces-redirect=true";
    }

    public String toSaveUser() {
        try {
            this.cleanAttributes();
            this.setPhotoImage(StringUtils.EMPTY);
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
        return "/pages/usuarioexterno/registroExterno?faces-redirect=true";
    }

    public void save(ActionEvent event) {
        try {
            if (StringUtils.isBlank(this.getDni())) {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR.", "Ingrese el DNI del usuario a registrar.");
                FacesContext.getCurrentInstance().addMessage(null, message);
                return;
            }
            if (StringUtils.isBlank(this.getNombres())) {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR.", "Ingrese el nombre del usuario a registrar.");
                FacesContext.getCurrentInstance().addMessage(null, message);
                return;
            }
            if (StringUtils.isBlank(this.getApellidos())) {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR.", "Ingrese el apellido del usuario a registrar.");
                FacesContext.getCurrentInstance().addMessage(null, message);
                return;
            }
            if (this.getFechaNacimiento() == null) {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR.", "Ingrese la fecha de nacimiento del usuario a registrar.");
                FacesContext.getCurrentInstance().addMessage(null, message);
                return;
            }
            if (StringUtils.isBlank(this.getCorreo())) {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR.", "Ingrese el correo electrónico del usuario a registrar.");
                FacesContext.getCurrentInstance().addMessage(null, message);
                return;
            }
            if (StringUtils.isBlank(this.getDepartamento())) {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR.", "Seleccione el departamento del lugar de residencia.");
                FacesContext.getCurrentInstance().addMessage(null, message);
                return;
            }
            if (StringUtils.isBlank(this.getProvincia())) {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR.", "Seleccione la provincia del lugar de residencia.");
                FacesContext.getCurrentInstance().addMessage(null, message);
                return;
            }
            if (StringUtils.isBlank(this.getDistrito())) {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR.", "Seleccione el distrito del lugar de residencia.");
                FacesContext.getCurrentInstance().addMessage(null, message);
                return;
            }
            UserService service = (UserService) ServiceFinder.findBean("UserService");
            User user = service.getUserByDNI(this.getDni());
            if (user != null) {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR.", "El DNI ingresado ya se encuentra registrado.");
                FacesContext.getCurrentInstance().addMessage(null, message);
                return;
            }
            user = service.getUserByLogin(this.getLogin());
            if (user != null) {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR.", "El nombre de usuario ingresado ya se encuentra registrado.");
                FacesContext.getCurrentInstance().addMessage(null, message);
                return;
            }
            ResourceBundle bundle = ResourceBundle.getBundle(Parameters.getMessages());
            String usuarioexterno = bundle.getString("usuarioexterno");
            
            user = new User();
            user.setNusuarioid(service.getNextPK());
            user.setVnombres(StringUtils.capitalize(this.getNombres()));
            user.setVapellidos(StringUtils.capitalize(this.getApellidos()));
            user.setVlogin(StringUtils.lowerCase(this.getLogin()));
            user.setDfechanacimiento(this.getFechaNacimiento());
            user.setVsexo(this.getSexo());
            user.setVdni(this.getDni());
            user.setVcorreo(this.getCorreo());
            user.setVdpto(this.getDepartamento());
            user.setVprov(this.getProvincia());
            user.setVdist(this.getDistrito());
            user.setVprofesion(this.getProfesion());
            user.setVentidad(this.getEntidad());
            user.setVpliego(this.getPliego());
            user.setVcargo(this.getCargo());
            user.setVarea(this.getArea());
            user.setVsector(this.getSector());
            user.setVgobierno(this.getGobierno());
            user.setNestado(BigDecimal.ONE);
            user.setNactivo(BigDecimal.ONE);
            user.setNperfilid(BigDecimal.valueOf(Long.parseLong(usuarioexterno)));
            user.setNuserinterno(BigDecimal.ZERO);
            user.setDfechacreacion(new Date());
            user.setVusuariocreacion(this.getLogin());
            service.saveOrUpdate(user);

            ParametroService parametroService = (ParametroService) ServiceFinder.findBean("ParametroService");
            Parametro passDefault = parametroService.getParametroById(BigDecimal.valueOf(Long.parseLong(Constante.CLAVE_DEFAULT)));

            TpassId tpassid = new TpassId();
            PassService passservice = (PassService) ServiceFinder.findBean("PassService");
            tpassid.setNpassid(passservice.getNextPK());
            tpassid.setNusuarioid(user.getNusuarioid());
            Pass pass = new Pass();
            pass.setId(tpassid);
            pass.setVclave(passDefault.getVvalor());
            pass.setDfechacreacion(new Date());
            pass.setVusuariocreacion(this.getLogin());
            passservice.saveOrUpdate(pass);

            if (this.getCroppedImage() != null) {
                String pathImage = this.path + File.separator + user.getNusuarioid() + File.separator;
                this.saveFile(pathImage, this.photoFileName, croppedImage.getBytes());
            }

            TuserPerfilId tuserPerfilId = new TuserPerfilId();
            tuserPerfilId.setNusuarioid(user.getNusuarioid());
            tuserPerfilId.setNperfilid(BigDecimal.valueOf(Long.parseLong(usuarioexterno)));
            TuserPerfil tuserPerfil = new TuserPerfil();
            tuserPerfil.setId(tuserPerfilId);
            tuserPerfil.setNperfilid(tuserPerfilId.getNperfilid());
            tuserPerfil.setNusuarioid(tuserPerfilId.getNusuarioid());
            tuserPerfil.setDfechacreacion(new Date());
            tuserPerfil.setVusuariocreacion(this.getLogin());
            service.asignProfileToUser(tuserPerfil);

            try {
                MailUtils.sendMail(user.getVcorreo(), "GESCON MEF - Usuario Creado", getSaveBodyMail(user.getVlogin(), pass.getVclave()));
                FacesContext.getCurrentInstance().getExternalContext().redirect("/gescon/index.xhtml");
            } catch (Exception e) {
                FacesContext.getCurrentInstance().getExternalContext().redirect("/gescon/index.xhtml");
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
    }

    public void saveadm(ActionEvent event) {

        try {
            if (StringUtils.isBlank(this.getTrabajaMef())) {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR.", "Indique si el usuario a registra labora en el MEF.");
                FacesContext.getCurrentInstance().addMessage(null, message);
                return;
            }
            if (StringUtils.isBlank(this.getDni())) {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR.", "Ingrese el DNI del usuario a registrar.");
                FacesContext.getCurrentInstance().addMessage(null, message);
                return;
            }
            if (StringUtils.isBlank(this.getNombres())) {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR.", "Ingrese el nombre del usuario a registrar.");
                FacesContext.getCurrentInstance().addMessage(null, message);
                return;
            }
            if (StringUtils.isBlank(this.getApellidos())) {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR.", "Ingrese el apellido del usuario a registrar.");
                FacesContext.getCurrentInstance().addMessage(null, message);
                return;
            }
            if (StringUtils.isBlank(this.getPerfil()) && this.getTrabajaMef().equals(BigDecimal.ONE.toString())) {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR.", "Seleccione el perfil del usuario a registrar.");
                FacesContext.getCurrentInstance().addMessage(null, message);
                return;
            }
            if (this.getFechaNacimiento() == null) {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR.", "Ingrese la fecha de nacimiento del usuario a registrar.");
                FacesContext.getCurrentInstance().addMessage(null, message);
                return;
            }
            if (StringUtils.isBlank(this.getCorreo())) {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR.", "Ingrese el correo electrónico del usuario a registrar.");
                FacesContext.getCurrentInstance().addMessage(null, message);
                return;
            }
            if (StringUtils.isBlank(this.getDepartamento())) {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR.", "Seleccione el departamento del lugar de residencia.");
                FacesContext.getCurrentInstance().addMessage(null, message);
                return;
            }
            if (StringUtils.isBlank(this.getProvincia())) {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR.", "Seleccione la provincia del lugar de residencia.");
                FacesContext.getCurrentInstance().addMessage(null, message);
                return;
            }
            if (StringUtils.isBlank(this.getDistrito())) {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR.", "Seleccione el distrito del lugar de residencia.");
                FacesContext.getCurrentInstance().addMessage(null, message);
                return;
            }
            UserService userService = (UserService) ServiceFinder.findBean("UserService");
            User user = userService.getUserByDNI(this.getDni());
            if (user != null) {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR.", "El DNI ingresado ya se encuentra registrado.");
                FacesContext.getCurrentInstance().addMessage(null, message);
                return;
            }
            user = userService.getUserByLogin(this.getLogin());
            if (user != null) {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR.", "El nombre de usuario ingresado ya se encuentra registrado.");
                FacesContext.getCurrentInstance().addMessage(null, message);
                return;
            }
            ResourceBundle bundle = ResourceBundle.getBundle(Parameters.getMessages());
            String usuarioexterno = bundle.getString("usuarioexterno");
            
            LoginMB loginMB = (LoginMB) JSFUtils.getSessionAttribute("loginMB");
            User usuario = loginMB.getUser();
            user = new User();
            user.setNusuarioid(userService.getNextPK());
            user.setVnombres(StringUtils.capitalize(this.getNombres()));
            user.setVapellidos(StringUtils.capitalize(this.getApellidos()));
            user.setVlogin(StringUtils.lowerCase(this.getLogin()));
            user.setDfechanacimiento(this.getFechaNacimiento());
            user.setVsexo(this.getSexo());
            user.setVdni(this.getDni());
            user.setVcorreo(this.getCorreo());
            user.setVdpto(this.getDepartamento());
            user.setVprov(this.getProvincia());
            user.setVdist(this.getDistrito());
            user.setVprofesion(this.getProfesion());
            user.setVentidad(this.getEntidad());
            user.setVpliego(this.getPliego());
            user.setVcargo(this.getCargo());
            user.setVarea(this.getArea());
            user.setVsector(this.getSector());
            user.setVgobierno(this.getGobierno());
            user.setNestado(BigDecimal.ONE);
            user.setNactivo(BigDecimal.ONE);
            user.setNuserinterno(BigDecimal.valueOf(Long.parseLong(this.getTrabajaMef())));
            if(this.getTrabajaMef().equals(BigDecimal.ONE.toString())) {
                user.setNperfilid(BigDecimal.valueOf(Long.parseLong(this.getPerfil())));
            } else {
                user.setNperfilid(BigDecimal.valueOf(Long.parseLong(usuarioexterno)));
            }
            user.setDfechacreacion(new Date());
            user.setVusuariocreacion(usuario.getVlogin());
            userService.saveOrUpdate(user);

            ParametroService parametroService = (ParametroService) ServiceFinder.findBean("ParametroService");
            Parametro passDefault = parametroService.getParametroById(BigDecimal.valueOf(Long.parseLong(Constante.CLAVE_DEFAULT)));

            TpassId tpassid = new TpassId();
            PassService passservice = (PassService) ServiceFinder.findBean("PassService");
            tpassid.setNpassid(passservice.getNextPK());
            tpassid.setNusuarioid(user.getNusuarioid());
            Pass pass = new Pass();
            pass.setId(tpassid);
            pass.setVclave(passDefault.getVvalor());
            pass.setDfechacreacion(new Date());
            pass.setVusuariocreacion(usuario.getVlogin());
            passservice.saveOrUpdate(pass);

            if (this.getCroppedImage() != null) {
                String pathImage = this.path + File.separator + user.getNusuarioid() + File.separator;
                this.saveFile(pathImage, this.photoFileName, croppedImage.getBytes());
            }

            TuserPerfilId tuserPerfilId = new TuserPerfilId();
            tuserPerfilId.setNusuarioid(user.getNusuarioid());
            tuserPerfilId.setNperfilid(BigDecimal.valueOf(Long.parseLong(this.getPerfil())));
            TuserPerfil tuserPerfil = new TuserPerfil();
            tuserPerfil.setId(tuserPerfilId);
            tuserPerfil.setNperfilid(tuserPerfilId.getNperfilid());
            tuserPerfil.setNusuarioid(tuserPerfilId.getNusuarioid());
            tuserPerfil.setDfechacreacion(new Date());
            tuserPerfil.setVusuariocreacion(this.getLogin());
            userService.asignProfileToUser(tuserPerfil);

            this.setListaUser(userService.getUsers());
            try {
                MailUtils.sendMail(user.getVcorreo(), "GESCON MEF - Usuario Creado", getSaveBodyMail(user.getVlogin(), pass.getVclave()));
                FacesContext.getCurrentInstance().getExternalContext().redirect("/gescon/pages/usuarioexterno/lista.xhtml");
            } catch (Exception e) {
                FacesContext.getCurrentInstance().getExternalContext().redirect("/gescon/pages/usuarioexterno/lista.xhtml");
            }

        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
    }

    public String toUpdate() {
        try {
            int index = Integer.parseInt((String) JSFUtils.getRequestParameter("index"));
            if (!CollectionUtils.isEmpty(this.getFilteredListaUser())) {
                this.setSelectedUser(this.getFilteredListaUser().get(index));
            } else {
                this.setSelectedUser(this.getListaUser().get(index));
            }
            UserService userService = (UserService) ServiceFinder.findBean("UserService");
            this.setPerfil(userService.getPerfilByUser(this.getSelectedUser().getNusuarioid()).toString());
            this.setPhotoImage(getPhotoUser());
            UbigeoService ubigeoService = (UbigeoService) ServiceFinder.findBean("UbigeoService");
            listaProvincia = new Items(ubigeoService.getProvinciasPorDepartamento(this.getSelectedUser().getVdpto()), null, "vcodprov", "vdescprov").getItems();
            listaDistrito = new Items(ubigeoService.getDistritosPorProvincia(this.getSelectedUser().getVdpto(), this.getSelectedUser().getVprov()), null, "vcoddist", "vdescdist").getItems();
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
        return "/pages/usuarioexterno/editar?faces-redirect=true";
    }

    public void activar(ActionEvent event) {
        try {
            if (event != null) {
                if (this.getSelectedUser() != null) {
                    LoginMB loginMB = (LoginMB) JSFUtils.getSessionAttribute("loginMB");
                    User user = loginMB.getUser();
                    UserService service = (UserService) ServiceFinder.findBean("UserService");
                    this.getSelectedUser().setNestado(BigDecimal.ONE);
                    this.getSelectedUser().setNactivo(BigDecimal.ONE);
                    this.getSelectedUser().setDfechamodificacion(new Date());
                    this.getSelectedUser().setVusuariomodificacion(user.getVlogin());
                    service.saveOrUpdate(this.getSelectedUser());
                    this.setListaUser(service.getUsers());
                } else {
                    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, Constante.SEVERETY_ALERTA, "Debe seleccionar el usuario a activar.");
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
                if (this.getSelectedUser() != null) {
                    
                    AsignacionService serviceasig = (AsignacionService) ServiceFinder.findBean("AsignacionService");
                    if(serviceasig.getNumberNotificationsByUser(this.getSelectedUser()).toString().equals("0"))
                    {
                        LoginMB loginMB = (LoginMB) JSFUtils.getSessionAttribute("loginMB");
                        User user = loginMB.getUser();
                        UserService service = (UserService) ServiceFinder.findBean("UserService");
                        this.getSelectedUser().setNestado(BigDecimal.ZERO);
                        this.getSelectedUser().setNactivo(BigDecimal.ZERO);
                        this.getSelectedUser().setDfechamodificacion(new Date());
                        this.getSelectedUser().setVusuariomodificacion(user.getVlogin());
                        service.saveOrUpdate(this.getSelectedUser());
                        this.setListaUser(service.getUsers());
                    }
                    else
                    {
                        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, Constante.SEVERETY_ALERTA, "No se puede desactivar el usuario, tiene pendientes por atender.");
                        FacesContext.getCurrentInstance().addMessage(null, message);
                    }
                    
                    
                } else {
                    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, Constante.SEVERETY_ALERTA, "Debe seleccionar el usuario a desactivar.");
                    FacesContext.getCurrentInstance().addMessage(null, message);
                }
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
    }

    public void update(ActionEvent event) {
        try {
            if (StringUtils.isBlank(this.getSelectedUser().getVnombres())) {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR.", "Ingrese el nombre del usuario a registrar.");
                FacesContext.getCurrentInstance().addMessage(null, message);
                return;
            }
            if (StringUtils.isBlank(this.getSelectedUser().getVapellidos())) {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR.", "Ingrese el apellido del usuario a registrar.");
                FacesContext.getCurrentInstance().addMessage(null, message);
                return;
            }
            if (StringUtils.isBlank(this.getPerfil())) {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR.", "Seleccione el perfil del usuario a registrar.");
                FacesContext.getCurrentInstance().addMessage(null, message);
                return;
            }
            if (this.getSelectedUser().getDfechanacimiento() == null) {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR.", "Ingrese la fecha de nacimiento del usuario a registrar.");
                FacesContext.getCurrentInstance().addMessage(null, message);
                return;
            }
            if (StringUtils.isBlank(this.getSelectedUser().getVcorreo())) {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR.", "Ingrese el correo electrónico del usuario a registrar.");
                FacesContext.getCurrentInstance().addMessage(null, message);
                return;
            }
            if (StringUtils.isBlank(this.getSelectedUser().getVdpto())) {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR.", "Seleccione el departamento del lugar de residencia.");
                FacesContext.getCurrentInstance().addMessage(null, message);
                return;
            }
            if (StringUtils.isBlank(this.getSelectedUser().getVprov())) {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR.", "Seleccione la provincia del lugar de residencia.");
                FacesContext.getCurrentInstance().addMessage(null, message);
                return;
            }
            if (StringUtils.isBlank(this.getSelectedUser().getVdist())) {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR.", "Seleccione el distrito del lugar de residencia.");
                FacesContext.getCurrentInstance().addMessage(null, message);
                return;
            }
            LoginMB loginMB = (LoginMB) JSFUtils.getSessionAttribute("loginMB");
            User user = loginMB.getUser();
            this.getSelectedUser().setVnombres(StringUtils.capitalize(this.getSelectedUser().getVnombres()));
            this.getSelectedUser().setVapellidos(StringUtils.capitalize(this.getSelectedUser().getVapellidos()));
            this.getSelectedUser().setNperfilid(BigDecimal.valueOf(Long.parseLong(this.getPerfil())));
            this.getSelectedUser().setDfechamodificacion(new Date());
            this.getSelectedUser().setVusuariomodificacion(user.getVlogin());
            this.getSelectedUser().setVcargo(this.getSelectedUser().getVcargo());
            this.getSelectedUser().setVprofesion(this.getSelectedUser().getVprofesion());
            this.getSelectedUser().setVentidad(this.getSelectedUser().getVentidad());
            this.getSelectedUser().setVsector(this.getSelectedUser().getVsector());
            this.getSelectedUser().setVgobierno(this.getSelectedUser().getVgobierno());
            this.getSelectedUser().setVarea(this.getSelectedUser().getVarea());
            this.getSelectedUser().setVpliego(this.getSelectedUser().getVpliego());
            UserService service = (UserService) ServiceFinder.findBean("UserService");
            service.saveOrUpdate(this.getSelectedUser());

            if (this.getCroppedImage() != null) {
                String pathImage = this.path + File.separator + this.getSelectedUser().getNusuarioid() + File.separator;
                this.saveFile(pathImage, this.photoFileName, croppedImage.getBytes());
            }

            service.deletePerfilByUser(this.getSelectedUser().getNusuarioid());

            TuserPerfilId tuserPerfilId = new TuserPerfilId();
            tuserPerfilId.setNusuarioid(this.getSelectedUser().getNusuarioid());
            tuserPerfilId.setNperfilid(BigDecimal.valueOf(Long.parseLong(this.getPerfil())));
            TuserPerfil tuserPerfil = new TuserPerfil();
            tuserPerfil.setId(tuserPerfilId);
            tuserPerfil.setNperfilid(tuserPerfilId.getNperfilid());
            tuserPerfil.setNusuarioid(tuserPerfilId.getNusuarioid());
            tuserPerfil.setDfechacreacion(new Date());
            tuserPerfil.setVusuariocreacion(user.getVlogin());
            service.asignProfileToUser(tuserPerfil);

            this.setListaUser(service.getUsers());
            FacesContext.getCurrentInstance().getExternalContext().redirect("/gescon/pages/usuarioexterno/lista.xhtml");
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
    }

    public String toView() {
        try {
            int index = Integer.parseInt((String) JSFUtils.getRequestParameter("index"));
            if (!CollectionUtils.isEmpty(this.getFilteredListaUser())) {
                this.setSelectedUser(this.getFilteredListaUser().get(index));
            } else {
                this.setSelectedUser(this.getListaUser().get(index));
            }
            UserService userService = (UserService) ServiceFinder.findBean("UserService");
            this.setPerfil(userService.getPerfilByUser(this.getSelectedUser().getNusuarioid()).toString());
            this.setPhotoImage(getPhotoUser());
            UbigeoService ubigeoService = (UbigeoService) ServiceFinder.findBean("UbigeoService");
            listaProvincia = new Items(ubigeoService.getProvinciasPorDepartamento(this.getSelectedUser().getVdpto()), null, "vcodprov", "vdescprov").getItems();
            listaDistrito = new Items(ubigeoService.getDistritosPorProvincia(this.getSelectedUser().getVdpto(), this.getSelectedUser().getVprov()), null, "vcoddist", "vdescdist").getItems();
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
        return "/pages/usuarioexterno/ver?faces-redirect=true";
    }

    public void toCrop(ActionEvent event) {
        try {
            this.setImagenTemporal(StringUtils.EMPTY);
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
    }

    public void handleFileUpload(FileUploadEvent event) {
        try {
            byte[] img = event.getFile().getContents();
            this.imagenTemporal = File.separator + "resources" + File.separator + "images" + File.separator + event.getFile().getFileName();
            FacesContext facesContext = FacesContext.getCurrentInstance();
            ServletContext scontext = (ServletContext) facesContext.getExternalContext().getContext();
            String archivo = scontext.getRealPath("") + this.imagenTemporal;
            this.imagenTemporal = this.imagenTemporal.replace("\\", "/");
            FileOutputStream fos = new FileOutputStream(archivo);
            fos.write(img);
            fos.flush();
            fos.close();
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
    }

    public void crop() {
        if (croppedImage == null) {
            return;
        }
        String newFileName = this.temppath + this.photoFileName;
        this.setPhotoImage(newFileName);
        this.saveFile(this.temppath, this.photoFileName, croppedImage.getBytes());
    }

    public String getPhotoUser() {
        String pathImage = this.path + File.separator + this.getSelectedUser().getNusuarioid() + File.separator;
        String newFileName = pathImage + this.photoFileName;
        File f = new File(newFileName);
        if (!f.exists()) {
            newFileName = StringUtils.EMPTY;
        }
        return newFileName;
    }

    public void saveFile(String path, String name, byte[] bytes) {
        try {
            File direc = new File(path);
            direc.mkdirs();
            File file = new File(path, name);
            FileOutputStream fileOutStream = new FileOutputStream(file);
            fileOutStream.write(bytes);
            fileOutStream.flush();
            fileOutStream.close();
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
    }

    public void resetPassword(ActionEvent event) {
        try {
            LoginMB loginMB = (LoginMB) JSFUtils.getSessionAttribute("loginMB");
            User user = loginMB.getUser();
            ParametroService parametroService = (ParametroService) ServiceFinder.findBean("ParametroService");
            Parametro passDefault = parametroService.getParametroById(BigDecimal.valueOf(Long.parseLong(Constante.CLAVE_DEFAULT)));
            PassService passService = (PassService) ServiceFinder.findBean("PassService");
            Pass password = passService.getPassByUser(this.getSelectedUser());
            password.setVclave(passDefault.getVvalor());
            password.setDfechamodificacion(new Date());
            password.setVusuariomodificacion(user.getVlogin());
            passService.saveOrUpdate(password);
            MailUtils.sendMail(this.getSelectedUser().getVcorreo(), "GESCON MEF - Reseteo de Contraseña", getResetPasswordBodyMail(passDefault.getVvalor()));
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "INFO.", "Contraseña reseteada. Se notificó al usuario via email.");
            FacesContext.getCurrentInstance().addMessage(null, message);
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
    }

    public String getResetPasswordBodyMail(String clave) {
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
            body.append("Actualiza tu Contraseña");
            body.append("</td>");
            body.append("</tr>");
            body.append("<tr style='font-size: 12px; height: 40px;'>");
            body.append("<td colspan='6' style='text-align: center;'>");
            body.append("Estimado usuario, por motivos de seguridad le solicitamos, por favor, actualice su contraseña.");
            body.append("</td>");
            body.append("</tr>");
            body.append("<tr style='font-size: 14px; height: 40px;'>");
            body.append("<td colspan='6' style='text-align: center;'>");
            body.append("Actual clave de acceso es <span style='font-weight: bold; font-style: italic; color: #2E77D2;'>").append(clave).append("</span>");
            body.append("</td>");
            body.append("</tr>");
            body.append("<tr style='font-size: 12px; height: 40px;'>");
            body.append("<td colspan='6' style='text-align: center;'>");
            body.append("Haz clic en el link de abajo para poder actualizar tu contraseña; en GESCON cuidamos tu privacidad. Gracias. ");
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

    public String getSaveBodyMail(String usuario, String clave) {
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
            body.append("¡ Te damos la bienvenida !");
            body.append("</td>");
            body.append("</tr>");
            body.append("<tr style='font-size: 12px; height: 40px;'>");
            body.append("<td colspan='6' style='text-align: center;'>");
            body.append("GESCON es una plataforma interactiva que te permite trasmitir y generar conocimientos, y habilidades de manera sistemática y eficiente. Te invitamos a participar de esta solución tecnológica; incorpora ideas, pregunta, genera contenidos, crea wikis y buenas prácticas.");
            body.append("</td>");
            body.append("</tr>");
            body.append("<tr style='font-size: 14px; height: 40px;'>");
            body.append("<td colspan='6' style='text-align: center;'>");
            body.append("Tu nuevo usuario es <span style='font-weight: bold; font-style: italic; color: #2E77D2;'>").append(usuario).append("</span> ");
            body.append("y tu clave de acceso es <span style='font-weight: bold; font-style: italic; color: #2E77D2;'>").append(clave).append("</span>");
            body.append("</td>");
            body.append("</tr>");
            body.append("<tr style='font-size: 12px; height: 40px;'>");
            body.append("<td colspan='6' style='text-align: center;'>");
            body.append("Gracias por ser parte de GESCON. Ahora puedes buscar y crear conocimiento que te permitirá obtener una ventaja competitiva en el ámbito laboral.");
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
}
