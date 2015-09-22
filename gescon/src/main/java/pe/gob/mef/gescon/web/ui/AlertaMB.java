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
    private BigDecimal useraplica;
    private Date fechini;
    private Date fechfin;
    private String condicion1;
    private String condicion2;
    private BigDecimal tipo1;
    private BigDecimal valor1;
    private BigDecimal tipo2;
    private BigDecimal valor2;
            
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
     * @return the useraplica
     */
    public BigDecimal getUseraplica() {
        return useraplica;
    }

    /**
     * @param useraplica the useraplica to set
     */
    public void setUseraplica(BigDecimal useraplica) {
        this.useraplica = useraplica;
    }

    /**
     * @return the fechini
     */
    public Date getFechini() {
        return fechini;
    }

    /**
     * @param fechini the fechini to set
     */
    public void setFechini(Date fechini) {
        this.fechini = fechini;
    }

    /**
     * @return the fechfin
     */
    public Date getFechfin() {
        return fechfin;
    }

    /**
     * @param fechfin the fechfin to set
     */
    public void setFechfin(Date fechfin) {
        this.fechfin = fechfin;
    }

    /**
     * @return the condicion1
     */
    public String getCondicion1() {
        return condicion1;
    }

    /**
     * @param condicion1 the condicion1 to set
     */
    public void setCondicion1(String condicion1) {
        this.condicion1 = condicion1;
    }

    /**
     * @return the condicion2
     */
    public String getCondicion2() {
        return condicion2;
    }

    /**
     * @param condicion2 the condicion2 to set
     */
    public void setCondicion2(String condicion2) {
        this.condicion2 = condicion2;
    }

    /**
     * @return the tipo1
     */
    public BigDecimal getTipo1() {
        return tipo1;
    }

    /**
     * @param tipo1 the tipo1 to set
     */
    public void setTipo1(BigDecimal tipo1) {
        this.tipo1 = tipo1;
    }

    /**
     * @return the valor1
     */
    public BigDecimal getValor1() {
        return valor1;
    }

    /**
     * @param valor1 the valor1 to set
     */
    public void setValor1(BigDecimal valor1) {
        this.valor1 = valor1;
    }

    /**
     * @return the tipo2
     */
    public BigDecimal getTipo2() {
        return tipo2;
    }

    /**
     * @param tipo2 the tipo2 to set
     */
    public void setTipo2(BigDecimal tipo2) {
        this.tipo2 = tipo2;
    }

    /**
     * @return the valor2
     */
    public BigDecimal getValor2() {
        return valor2;
    }

    /**
     * @param valor2 the valor2 to set
     */
    public void setValor2(BigDecimal valor2) {
        this.valor2 = valor2;
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
        this.setSelectedParametro(BigDecimal.ZERO);
        this.setCondicion1(StringUtils.EMPTY);
        
        
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
            alerta.setDfechini(this.getFechfin());
            alerta.setDfechfin(this.getFechfin());
            alerta.setNcondicion1(this.getCondicion1());
            alerta.setNcondicion2(this.getCondicion2());
            alerta.setNtipo1(this.getTipo1());
            alerta.setNtipo2(this.getTipo2());
            alerta.setNuseraplica(this.getUseraplica());
            alerta.setNvalor1(this.getValor1());
            alerta.setNvalor2(this.getValor2());
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
                this.getSelectedAlerta().setDfechini(this.getSelectedAlerta().getDfechini());
                this.getSelectedAlerta().setDfechfin(this.getSelectedAlerta().getDfechfin());
                this.getSelectedAlerta().setNuseraplica(this.getSelectedAlerta().getNuseraplica());
                this.getSelectedAlerta().setNcondicion1(this.getSelectedAlerta().getNcondicion1());
                this.getSelectedAlerta().setNcondicion2(this.getSelectedAlerta().getNcondicion2());
                this.getSelectedAlerta().setNtipo1(this.getSelectedAlerta().getNtipo1());
                this.getSelectedAlerta().setNtipo2(this.getSelectedAlerta().getNtipo2());
                this.getSelectedAlerta().setNvalor1(this.getSelectedAlerta().getNvalor1());
                this.getSelectedAlerta().setNvalor2(this.getSelectedAlerta().getNvalor2());
                
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
