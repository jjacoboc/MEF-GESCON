/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.gob.mef.gescon.web.ui;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.primefaces.context.RequestContext;
import pe.gob.mef.gescon.common.Constante;
import pe.gob.mef.gescon.hibernate.domain.TpassId;
import pe.gob.mef.gescon.service.PassService;
import pe.gob.mef.gescon.service.UserService;
import pe.gob.mef.gescon.util.JSFUtils;
import pe.gob.mef.gescon.util.ServiceFinder;
import pe.gob.mef.gescon.web.bean.Pass;
import pe.gob.mef.gescon.web.bean.User;

/**
 *
 * @author JJacobo
 */
@ManagedBean
@ViewScoped
public class UserMB implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final Log log = LogFactory.getLog(UserMB.class);
    private BigDecimal id;
    private String login;
    private String nombres;
    private String apellidos;
    private Date fechaNacimiento;
    private String dia;
    private List<SelectItem> dias = new ArrayList<SelectItem>();
    private String mes;
    private String año;
    private List<SelectItem> años = new ArrayList<SelectItem>();
    private String sexo;
    private String dni;
    private String pass;
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

    /**
     * Creates a new instance of MaestroMB
     */
    public UserMB() {
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
     * @return the dia
     */
    public String getDia() {
        return dia;
    }

    /**
     * @param dia the dia to set
     */
    public void setDia(String dia) {
        this.dia = dia;
    }

    /**
     * @return the dias
     */
    public List<SelectItem> getDias() {
        return dias;
    }

    /**
     * @param dias the dias to set
     */
    public void setDias(List<SelectItem> dias) {
        this.dias = dias;
    }

    /**
     * @return the mes
     */
    public String getMes() {
        return mes;
    }

    /**
     * @param mes the mes to set
     */
    public void setMes(String mes) {
        this.mes = mes;
    }

    /**
     * @return the año
     */
    public String getAño() {
        return año;
    }

    /**
     * @param año the año to set
     */
    public void setAño(String año) {
        this.año = año;
    }

    /**
     * @return the años
     */
    public List<SelectItem> getAños() {
        return años;
    }

    /**
     * @param años the años to set
     */
    public void setAños(List<SelectItem> años) {
        this.años = años;
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
    
    @PostConstruct
    public void init() {
        try {

            UserService service = (UserService) ServiceFinder.findBean("UserService");
            listaUser = service.getUsers();

            for (int x = 1; x < 32; x++) {
                SelectItem dias = new SelectItem();
                dias.setLabel(String.valueOf(x));
                dias.setValue(String.valueOf(x));
                getDias().add(dias);
            }

            for (int j = 1997; j > 1935; j--) {
                SelectItem años = new SelectItem();
                años.setLabel(String.valueOf(j));
                años.setValue(String.valueOf(j));
                getAños().add(años);
            }

        } catch (Exception e) {
            log.error(e.getMessage());
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
        this.setId(BigDecimal.ZERO);
        this.setNombres(StringUtils.EMPTY);
        this.setApellidos(StringUtils.EMPTY);
        this.setLogin(StringUtils.EMPTY);
        this.setDia("1");
        Iterator<FacesMessage> iter = FacesContext.getCurrentInstance().getMessages();
        if (iter.hasNext() == true) {
            iter.remove();
            FacesContext.getCurrentInstance().renderResponse();
        }
    }

    public void save() throws ParseException {

        BigDecimal iduser;
        String fecha;
        Date date1;

        fecha = this.dia + "/" + this.mes + "/" + this.año;
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        Date date = formato.parse(fecha);

        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Correct", "Correct");
        FacesContext.getCurrentInstance().addMessage(null, msg);

        try {

            User user = new User();
            user.setVnombres(this.getNombres());
            user.setVapellidos(this.getApellidos());
            user.setVlogin(this.getLogin());
            user.setDfechacreacion(new Date());
            user.setDfechanacimiento(date);
            user.setVsexo(this.getSexo());
            user.setVdni(this.getDni());
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
            UserService service = (UserService) ServiceFinder.findBean("UserService");
            iduser = service.getNextPK();
            user.setNusuarioid(iduser);
            service.saveOrUpdate(user);

            TpassId tpassid = new TpassId();
            PassService passservice = (PassService) ServiceFinder.findBean("PassService");
            tpassid.setNpassid(passservice.getNextPK());
            tpassid.setNusuarioid(iduser);
            Pass pass = new Pass();
            pass.setId(tpassid);
            pass.setVclave(this.getPass());
            pass.setDfechacreacion(new Date());
            passservice.saveOrUpdate(pass);
            RequestContext.getCurrentInstance().execute("PF('iniDialog').hide();");
            RequestContext.getCurrentInstance().execute("PF('regDialog').hide();");
            this.cleanAttributes();

        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
    }

    public void saveadm() throws ParseException {

        BigDecimal iduser;
        String fecha;
        Date date1;

        fecha = this.dia + "/" + this.mes + "/" + this.año;
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        Date date = formato.parse(fecha);

        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Correct", "Correct");
        FacesContext.getCurrentInstance().addMessage(null, msg);

        try {

            User user = new User();
            user.setVnombres(this.getNombres());
            user.setVapellidos(this.getApellidos());
            user.setVlogin(this.getLogin());
            user.setDfechacreacion(new Date());
            user.setDfechanacimiento(date);
            user.setVsexo(this.getSexo());
            user.setVdni(this.getDni());
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
            UserService service = (UserService) ServiceFinder.findBean("UserService");
            iduser = service.getNextPK();
            user.setNusuarioid(iduser);
            service.saveOrUpdate(user);

            TpassId tpassid = new TpassId();
            PassService passservice = (PassService) ServiceFinder.findBean("PassService");
            tpassid.setNpassid(passservice.getNextPK());
            tpassid.setNusuarioid(iduser);
            Pass pass = new Pass();
            pass.setId(tpassid);
            pass.setVclave(this.getPass());
            pass.setDfechacreacion(new Date());
            passservice.saveOrUpdate(pass);
            this.setListaUser(service.getUsers());
            RequestContext.getCurrentInstance().execute("PF('newDialog').hide();");
            this.cleanAttributes();
            

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

    public void toUpdate(ActionEvent event) {
        try {
            if (event != null) {
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
    
    public void activar(ActionEvent event) {
        try {
            if(event != null) {
                if(this.getSelectedUser() != null) {
                    UserService service = (UserService) ServiceFinder.findBean("UserService");
                    this.getSelectedUser().setNestado(BigDecimal.ONE);
                    //this.getSelectedUser().setd(new Date());
//                    this.getSelectedMaestro().setVusumod(user.getUsuario());
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
            if(event != null) {
                if(this.getSelectedUser() != null) {
                    UserService service = (UserService) ServiceFinder.findBean("UserService");
                    this.getSelectedUser().setNestado(BigDecimal.ZERO);
                    //this.getSelectedParametro().setDfechmod(new Date());
//                    this.getSelectedMaestro().setVusumod(user.getUsuario());
                    service.saveOrUpdate(this.getSelectedUser());
                    this.setListaUser(service.getUsers());
                } else {
                    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, Constante.SEVERETY_ALERTA, "Debe seleccionar el parametro a desactivar.");
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
            if(event != null) {
                //if(StringUtils.isBlank(this.getSelectedParametro().getVnombre())) {
                //    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, Constante.SEVERETY_ALERTA, "Nombre requerido. Ingrese el nombre del parametro.");
                //    FacesContext.getCurrentInstance().addMessage(null, message);
                //    return;
                //}
                //if(StringUtils.isBlank(this.getSelectedParametro().getVvalor())) {
                //    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, Constante.SEVERETY_ALERTA, "Nombre requerido. Ingrese el valor del parametro.");
                //    FacesContext.getCurrentInstance().addMessage(null, message);
                //    return;
                //}
                //if(StringUtils.isBlank(this.getSelectedParametro().getVdescripcion())) {
                //    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, Constante.SEVERETY_ALERTA, "Descripción requerida. Ingrese la descripción del parametro.");
                //    FacesContext.getCurrentInstance().addMessage(null, message);
                //   return;
                //}
                this.getSelectedUser().setVnombres(this.getSelectedUser().getVnombres());
                this.getSelectedUser().setVapellidos(this.getSelectedUser().getVapellidos());
                this.getSelectedUser().setVlogin(this.getSelectedUser().getVlogin());
                this.getSelectedUser().setVdpto(this.getSelectedUser().getVdpto());
                this.getSelectedUser().setVprov(this.getSelectedUser().getVprov());
                this.getSelectedUser().setVdist(this.getSelectedUser().getVdist());
                this.getSelectedUser().setVdni(this.getSelectedUser().getVdni());
                this.getSelectedUser().setVsexo(this.getSelectedUser().getVsexo());
                this.getSelectedUser().setVentidad(this.getSelectedUser().getVentidad());
                this.getSelectedUser().setVpliego(this.getSelectedUser().getVpliego());
                this.getSelectedUser().setVcargo(this.getSelectedUser().getVcargo());
                this.getSelectedUser().setVarea(this.getSelectedUser().getVarea());
                this.getSelectedUser().setVsector(this.getSelectedUser().getVsector());
                this.getSelectedUser().setVgobierno(this.getSelectedUser().getVgobierno());
                //this.getSelectedMestro().setIdUsuaModi(user.getUsuario());
                //this.getSelectedParametro().setDfechmod(new Date());
                UserService service = (UserService) ServiceFinder.findBean("UserService");
                service.saveOrUpdate(this.getSelectedUser());
                this.setListaUser(service.getUsers());
                RequestContext.getCurrentInstance().execute("PF('editDialog').hide();");
                this.cleanAttributes();
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
    }

}
