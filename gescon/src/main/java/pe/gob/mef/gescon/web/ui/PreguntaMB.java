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
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.AjaxBehaviorEvent;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;
import org.primefaces.component.selectonemenu.SelectOneMenu;
import org.primefaces.context.RequestContext;
import org.primefaces.event.NodeSelectEvent;
import org.primefaces.event.TransferEvent;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.DualListModel;
import org.primefaces.model.TreeNode;
import org.springframework.util.CollectionUtils;
import pe.gob.mef.gescon.common.Constante;
import pe.gob.mef.gescon.service.AsignacionService;
import pe.gob.mef.gescon.service.CalificacionPreguntaService;
import pe.gob.mef.gescon.service.CategoriaService;
import pe.gob.mef.gescon.service.ConsultaService;
import pe.gob.mef.gescon.service.PreguntaService;
import pe.gob.mef.gescon.service.RespuestaHistService;
import pe.gob.mef.gescon.service.UserService;
import pe.gob.mef.gescon.service.VinculoPreguntaService;
import pe.gob.mef.gescon.util.GcmFileUtils;
import pe.gob.mef.gescon.util.JSFUtils;
import pe.gob.mef.gescon.util.ServiceFinder;
import pe.gob.mef.gescon.web.bean.Asignacion;
import pe.gob.mef.gescon.web.bean.CalificacionPregunta;
import pe.gob.mef.gescon.web.bean.Categoria;
import pe.gob.mef.gescon.web.bean.Consulta;
import pe.gob.mef.gescon.web.bean.Pregunta;
import pe.gob.mef.gescon.web.bean.RespuestaHist;
import pe.gob.mef.gescon.web.bean.User;
import pe.gob.mef.gescon.web.bean.VinculoPregunta;

/**
 *
 * @author JJacobo
 */
@ManagedBean
@SessionScoped
public class PreguntaMB implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final Log log = LogFactory.getLog(PreguntaMB.class);
    private final String path = "pr/";
    private List<Pregunta> listaPregunta;
    private List<Pregunta> flistaPregunta;
    private List<RespuestaHist> listaRespuesta;
    private List<Asignacion> listaAsignacion;
    private Pregunta selectedPregunta;
    private Asignacion selectedAsignacion;
    private String asunto;
    private String detalle;
    private BigDecimal entidadId;
    private String datoAdicional;
    private String respuesta;
    private String msjusuario2;
    private String msjespecialista;
    private BigDecimal nsituacion;
    private String msjmoderador;
    private String msjusuario1;
    private String fSInfEspe; //SI Especialista
    private String fButtonEspe; //Botones Especialista
    private String fButtonMod;// Botones Moderador
    private String fButtonModPub;//Botones Moderador Publicar
    private String fMsjUsu1; //Mensaje 1 Usuario
    private String fMsjUsu2; //Mensaje 2 Usuario
    private String fSInfMod; //SI Moderador
    private String fButton; //Botones UE - ESPE
    private String fButtonUM; //Botones UE - MOD
    private String entidad;
    private String tema;
    private TreeNode tree;
    private Categoria selectedCategoria;
    private Boolean chkDestacado;
    private BigDecimal idTipoConocimiento;
    private List<Consulta> listaSourceVinculos;
    private List<Consulta> listaTargetVinculos;
    private List<Consulta> listaSourceVinculosBL;
    private List<Consulta> listaTargetVinculosBL;
    private List<Consulta> listaSourceVinculosPR;
    private List<Consulta> listaTargetVinculosPR;
    private List<Consulta> listaSourceVinculosWK;
    private List<Consulta> listaTargetVinculosWK;
    private List<Consulta> listaSourceVinculosOM;
    private List<Consulta> listaTargetVinculosOM;
    private List<Consulta> listaSourceVinculosBP;
    private List<Consulta> listaTargetVinculosBP;
    private List<Consulta> listaSourceVinculosCT;
    private List<Consulta> listaTargetVinculosCT;
    private List<Consulta> listaTargetVinculosConocimiento;
    private DualListModel<Consulta> pickListPregunta;
    private BigDecimal cat1;
    private List<CalificacionPregunta> listaCalificacion;
    private CalificacionPregunta selectedCalificacion;
    private BigDecimal calificacion;
    private String comentarioCalificacion;
    private List<Consulta> listaDestacados;
    private Consulta selectedDestacado;

    /**
     * Creates a new instance of MaestroMB
     */
    public PreguntaMB() {
    }

    /**
     * @return the listaPregunta
     */
    public List<Pregunta> getListaPregunta() {
        return listaPregunta;
    }

    /**
     * @param listaPregunta the listaPregunta to set
     */
    public void setListaPregunta(List<Pregunta> listaPregunta) {
        this.listaPregunta = listaPregunta;
    }

    /**
     * @return the flistaPregunta
     */
    public List<Pregunta> getFlistaPregunta() {
        return flistaPregunta;
    }

    /**
     * @param flistaPregunta the flistaPregunta to set
     */
    public void setFlistaPregunta(List<Pregunta> flistaPregunta) {
        this.flistaPregunta = flistaPregunta;
    }

    /**
     * @return the listaRespuesta
     */
    public List<RespuestaHist> getListaRespuesta() {
        return listaRespuesta;
    }

    /**
     * @param listaRespuesta the listaRespuesta to set
     */
    public void setListaRespuesta(List<RespuestaHist> listaRespuesta) {
        this.listaRespuesta = listaRespuesta;
    }

    /**
     * @return the listaAsignacion
     */
    public List<Asignacion> getListaAsignacion() {
        return listaAsignacion;
    }

    /**
     * @param listaAsignacion the listaAsignacion to set
     */
    public void setListaAsignacion(List<Asignacion> listaAsignacion) {
        this.listaAsignacion = listaAsignacion;
    }

    /**
     * @return the selectedPregunta
     */
    public Pregunta getSelectedPregunta() {
        return selectedPregunta;
    }

    /**
     * @param selectedPregunta the selectedPregunta to set
     */
    public void setSelectedPregunta(Pregunta selectedPregunta) {
        this.selectedPregunta = selectedPregunta;
    }

    /**
     * @return the selectedAsignacion
     */
    public Asignacion getSelectedAsignacion() {
        return selectedAsignacion;
    }

    /**
     * @param selectedAsignacion the selectedAsignacion to set
     */
    public void setSelectedAsignacion(Asignacion selectedAsignacion) {
        this.selectedAsignacion = selectedAsignacion;
    }

    /**
     * @return the asunto
     */
    public String getAsunto() {
        return asunto;
    }

    /**
     * @param asunto the asunto to set
     */
    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }

    /**
     * @return the detalle
     */
    public String getDetalle() {
        return detalle;
    }

    /**
     * @param detalle the detalle to set
     */
    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

    /**
     * @return the entidadId
     */
    public BigDecimal getEntidadId() {
        return entidadId;
    }

    /**
     * @param entidadId the entidadId to set
     */
    public void setEntidadId(BigDecimal entidadId) {
        this.entidadId = entidadId;
    }

    /**
     * @return the datoAdicional
     */
    public String getDatoAdicional() {
        return datoAdicional;
    }

    /**
     * @param datoAdicional the datoAdicional to set
     */
    public void setDatoAdicional(String datoAdicional) {
        this.datoAdicional = datoAdicional;
    }

    /**
     * @return the respuesta
     */
    public String getRespuesta() {
        return respuesta;
    }

    /**
     * @param respuesta the respuesta to set
     */
    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }

    /**
     * @return the msjusuario2
     */
    public String getMsjusuario2() {
        return msjusuario2;
    }

    /**
     * @param msjusuario2 the msjusuario2 to set
     */
    public void setMsjusuario2(String msjusuario2) {
        this.msjusuario2 = msjusuario2;
    }

    /**
     * @return the msjespecialista
     */
    public String getMsjespecialista() {
        return msjespecialista;
    }

    /**
     * @param msjespecialista the msjespecialista to set
     */
    public void setMsjespecialista(String msjespecialista) {
        this.msjespecialista = msjespecialista;
    }

    /**
     * @return the nsituacion
     */
    public BigDecimal getNsituacion() {
        return nsituacion;
    }

    /**
     * @param nsituacion the nsituacion to set
     */
    public void setNsituacion(BigDecimal nsituacion) {
        this.nsituacion = nsituacion;
    }

    /**
     * @return the msjmoderador
     */
    public String getMsjmoderador() {
        return msjmoderador;
    }

    /**
     * @param msjmoderador the msjmoderador to set
     */
    public void setMsjmoderador(String msjmoderador) {
        this.msjmoderador = msjmoderador;
    }

    /**
     * @return the msjusuario1
     */
    public String getMsjusuario1() {
        return msjusuario1;
    }

    /**
     * @param msjusuario1 the msjusuario1 to set
     */
    public void setMsjusuario1(String msjusuario1) {
        this.msjusuario1 = msjusuario1;
    }

    /**
     * @return the fSInfEspe
     */
    public String getfSInfEspe() {
        return fSInfEspe;
    }

    /**
     * @param fSInfEspe the fSInfEspe to set
     */
    public void setfSInfEspe(String fSInfEspe) {
        this.fSInfEspe = fSInfEspe;
    }

    /**
     * @return the fButtonEspe
     */
    public String getfButtonEspe() {
        return fButtonEspe;
    }

    /**
     * @param fButtonEspe the fButtonEspe to set
     */
    public void setfButtonEspe(String fButtonEspe) {
        this.fButtonEspe = fButtonEspe;
    }

    /**
     * @return the fButtonMod
     */
    public String getfButtonMod() {
        return fButtonMod;
    }

    /**
     * @param fButtonMod the fButtonMod to set
     */
    public void setfButtonMod(String fButtonMod) {
        this.fButtonMod = fButtonMod;
    }

    /**
     * @return the fButtonModPub
     */
    public String getfButtonModPub() {
        return fButtonModPub;
    }

    /**
     * @param fButtonModPub the fButtonModPub to set
     */
    public void setfButtonModPub(String fButtonModPub) {
        this.fButtonModPub = fButtonModPub;
    }

    /**
     * @return the fMsjUsu1
     */
    public String getfMsjUsu1() {
        return fMsjUsu1;
    }

    /**
     * @param fMsjUsu1 the fMsjUsu1 to set
     */
    public void setfMsjUsu1(String fMsjUsu1) {
        this.fMsjUsu1 = fMsjUsu1;
    }

    /**
     * @return the fMsjUsu2
     */
    public String getfMsjUsu2() {
        return fMsjUsu2;
    }

    /**
     * @param fMsjUsu2 the fMsjUsu2 to set
     */
    public void setfMsjUsu2(String fMsjUsu2) {
        this.fMsjUsu2 = fMsjUsu2;
    }

    /**
     * @return the fSInfMod
     */
    public String getfSInfMod() {
        return fSInfMod;
    }

    /**
     * @param fSInfMod the fSInfMod to set
     */
    public void setfSInfMod(String fSInfMod) {
        this.fSInfMod = fSInfMod;
    }

    /**
     * @return the fButton
     */
    public String getfButton() {
        return fButton;
    }

    /**
     * @param fButton the fButton to set
     */
    public void setfButton(String fButton) {
        this.fButton = fButton;
    }

    /**
     * @return the fButtonUM
     */
    public String getfButtonUM() {
        return fButtonUM;
    }

    /**
     * @param fButtonUM the fButtonUM to set
     */
    public void setfButtonUM(String fButtonUM) {
        this.fButtonUM = fButtonUM;
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
     * @return the tema
     */
    public String getTema() {
        return tema;
    }

    /**
     * @param tema the tema to set
     */
    public void setTema(String tema) {
        this.tema = tema;
    }

    /**
     * @return the tree
     */
    public TreeNode getTree() {
        return tree;
    }

    /**
     * @param tree the tree to set
     */
    public void setTree(TreeNode tree) {
        this.tree = tree;
    }

    /**
     * @return the selectedCategoria
     */
    public Categoria getSelectedCategoria() {
        return selectedCategoria;
    }

    /**
     * @param selectedCategoria the selectedCategoria to set
     */
    public void setSelectedCategoria(Categoria selectedCategoria) {
        this.selectedCategoria = selectedCategoria;
    }

    public Boolean getChkDestacado() {
        return chkDestacado;
    }

    public void setChkDestacado(Boolean chkDestacado) {
        this.chkDestacado = chkDestacado;
    }

    /**
     * @return the idTipoConocimiento
     */
    public BigDecimal getIdTipoConocimiento() {
        return idTipoConocimiento;
    }

    /**
     * @param idTipoConocimiento the idTipoConocimiento to set
     */
    public void setIdTipoConocimiento(BigDecimal idTipoConocimiento) {
        this.idTipoConocimiento = idTipoConocimiento;
    }

    /**
     * @return the listaSourceVinculos
     */
    public List<Consulta> getListaSourceVinculos() {
        return listaSourceVinculos;
    }

    /**
     * @param listaSourceVinculos the listaSourceVinculos to set
     */
    public void setListaSourceVinculos(List<Consulta> listaSourceVinculos) {
        this.listaSourceVinculos = listaSourceVinculos;
    }

    /**
     * @return the listaTargetVinculos
     */
    public List<Consulta> getListaTargetVinculos() {
        return listaTargetVinculos;
    }

    /**
     * @param listaTargetVinculos the listaTargetVinculos to set
     */
    public void setListaTargetVinculos(List<Consulta> listaTargetVinculos) {
        this.listaTargetVinculos = listaTargetVinculos;
    }

    /**
     * @return the listaSourceVinculosBL
     */
    public List<Consulta> getListaSourceVinculosBL() {
        return listaSourceVinculosBL;
    }

    /**
     * @param listaSourceVinculosBL the listaSourceVinculosBL to set
     */
    public void setListaSourceVinculosBL(List<Consulta> listaSourceVinculosBL) {
        this.listaSourceVinculosBL = listaSourceVinculosBL;
    }

    /**
     * @return the listaTargetVinculosBL
     */
    public List<Consulta> getListaTargetVinculosBL() {
        return listaTargetVinculosBL;
    }

    /**
     * @param listaTargetVinculosBL the listaTargetVinculosBL to set
     */
    public void setListaTargetVinculosBL(List<Consulta> listaTargetVinculosBL) {
        this.listaTargetVinculosBL = listaTargetVinculosBL;
    }

    /**
     * @return the listaSourceVinculosPR
     */
    public List<Consulta> getListaSourceVinculosPR() {
        return listaSourceVinculosPR;
    }

    /**
     * @param listaSourceVinculosPR the listaSourceVinculosPR to set
     */
    public void setListaSourceVinculosPR(List<Consulta> listaSourceVinculosPR) {
        this.listaSourceVinculosPR = listaSourceVinculosPR;
    }

    /**
     * @return the listaTargetVinculosPR
     */
    public List<Consulta> getListaTargetVinculosPR() {
        return listaTargetVinculosPR;
    }

    /**
     * @param listaTargetVinculosPR the listaTargetVinculosPR to set
     */
    public void setListaTargetVinculosPR(List<Consulta> listaTargetVinculosPR) {
        this.listaTargetVinculosPR = listaTargetVinculosPR;
    }

    /**
     * @return the listaSourceVinculosWK
     */
    public List<Consulta> getListaSourceVinculosWK() {
        return listaSourceVinculosWK;
    }

    /**
     * @param listaSourceVinculosWK the listaSourceVinculosWK to set
     */
    public void setListaSourceVinculosWK(List<Consulta> listaSourceVinculosWK) {
        this.listaSourceVinculosWK = listaSourceVinculosWK;
    }

    /**
     * @return the listaTargetVinculosWK
     */
    public List<Consulta> getListaTargetVinculosWK() {
        return listaTargetVinculosWK;
    }

    /**
     * @param listaTargetVinculosWK the listaTargetVinculosWK to set
     */
    public void setListaTargetVinculosWK(List<Consulta> listaTargetVinculosWK) {
        this.listaTargetVinculosWK = listaTargetVinculosWK;
    }

    /**
     * @return the listaSourceVinculosOM
     */
    public List<Consulta> getListaSourceVinculosOM() {
        return listaSourceVinculosOM;
    }

    /**
     * @param listaSourceVinculosOM the listaSourceVinculosOM to set
     */
    public void setListaSourceVinculosOM(List<Consulta> listaSourceVinculosOM) {
        this.listaSourceVinculosOM = listaSourceVinculosOM;
    }

    /**
     * @return the listaTargetVinculosOM
     */
    public List<Consulta> getListaTargetVinculosOM() {
        return listaTargetVinculosOM;
    }

    /**
     * @param listaTargetVinculosOM the listaTargetVinculosOM to set
     */
    public void setListaTargetVinculosOM(List<Consulta> listaTargetVinculosOM) {
        this.listaTargetVinculosOM = listaTargetVinculosOM;
    }

    /**
     * @return the listaSourceVinculosBP
     */
    public List<Consulta> getListaSourceVinculosBP() {
        return listaSourceVinculosBP;
    }

    /**
     * @param listaSourceVinculosBP the listaSourceVinculosBP to set
     */
    public void setListaSourceVinculosBP(List<Consulta> listaSourceVinculosBP) {
        this.listaSourceVinculosBP = listaSourceVinculosBP;
    }

    /**
     * @return the listaTargetVinculosBP
     */
    public List<Consulta> getListaTargetVinculosBP() {
        return listaTargetVinculosBP;
    }

    /**
     * @param listaTargetVinculosBP the listaTargetVinculosBP to set
     */
    public void setListaTargetVinculosBP(List<Consulta> listaTargetVinculosBP) {
        this.listaTargetVinculosBP = listaTargetVinculosBP;
    }

    /**
     * @return the listaSourceVinculosCT
     */
    public List<Consulta> getListaSourceVinculosCT() {
        return listaSourceVinculosCT;
    }

    /**
     * @param listaSourceVinculosCT the listaSourceVinculosCT to set
     */
    public void setListaSourceVinculosCT(List<Consulta> listaSourceVinculosCT) {
        this.listaSourceVinculosCT = listaSourceVinculosCT;
    }

    /**
     * @return the listaTargetVinculosCT
     */
    public List<Consulta> getListaTargetVinculosCT() {
        return listaTargetVinculosCT;
    }

    /**
     * @param listaTargetVinculosCT the listaTargetVinculosCT to set
     */
    public void setListaTargetVinculosCT(List<Consulta> listaTargetVinculosCT) {
        this.listaTargetVinculosCT = listaTargetVinculosCT;
    }

    /**
     * @return the listaTargetVinculosConocimiento
     */
    public List<Consulta> getListaTargetVinculosConocimiento() {
        return listaTargetVinculosConocimiento;
    }

    /**
     * @param listaTargetVinculosConocimiento the
     * listaTargetVinculosConocimiento to set
     */
    public void setListaTargetVinculosConocimiento(List<Consulta> listaTargetVinculosConocimiento) {
        this.listaTargetVinculosConocimiento = listaTargetVinculosConocimiento;
    }

    /**
     * @return the pickListPregunta
     */
    public DualListModel<Consulta> getPickListPregunta() {
        return pickListPregunta;
    }

    /**
     * @param pickListPregunta the pickListPregunta to set
     */
    public void setPickListPregunta(DualListModel<Consulta> pickListPregunta) {
        this.pickListPregunta = pickListPregunta;
    }

    public BigDecimal getCat1() {
        return cat1;
    }

    public void setCat1(BigDecimal cat1) {
        this.cat1 = cat1;
    }

    public List<CalificacionPregunta> getListaCalificacion() {
        return listaCalificacion;
    }

    public void setListaCalificacion(List<CalificacionPregunta> listaCalificacion) {
        this.listaCalificacion = listaCalificacion;
    }

    public CalificacionPregunta getSelectedCalificacion() {
        return selectedCalificacion;
    }

    public void setSelectedCalificacion(CalificacionPregunta selectedCalificacion) {
        this.selectedCalificacion = selectedCalificacion;
    }

    public BigDecimal getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(BigDecimal calificacion) {
        this.calificacion = calificacion;
    }

    public String getComentarioCalificacion() {
        return comentarioCalificacion;
    }

    public void setComentarioCalificacion(String comentarioCalificacion) {
        this.comentarioCalificacion = comentarioCalificacion;
    }

    public List<Consulta> getListaDestacados() {
        return listaDestacados;
    }

    public void setListaDestacados(List<Consulta> listaDestacados) {
        this.listaDestacados = listaDestacados;
    }

    public Consulta getSelectedDestacado() {
        return selectedDestacado;
    }

    public void setSelectedDestacado(Consulta selectedDestacado) {
        this.selectedDestacado = selectedDestacado;
    }

    @PostConstruct
    public void init() {
        try {
            PreguntaService service = (PreguntaService) ServiceFinder.findBean("PreguntaService");
            listaPregunta = service.getPreguntas();
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
    }

    public void cleanAttributes() {
        this.setSelectedCategoria(null);
        this.setEntidad(StringUtils.EMPTY);
        this.setEntidadId(null);
        this.setAsunto(StringUtils.EMPTY);
        this.setDetalle(StringUtils.EMPTY);
        this.setDatoAdicional(StringUtils.EMPTY);
        Iterator<FacesMessage> iter = FacesContext.getCurrentInstance().getMessages();

        if (iter.hasNext() == true) {
            iter.remove();
            FacesContext.getCurrentInstance().renderResponse();
        }
    }

    public void clearCalificacion() {
        try {
            this.setSelectedCalificacion(null);
            this.setComentarioCalificacion(StringUtils.EMPTY);
            this.setCalificacion(null);
            Iterator<FacesMessage> iter = FacesContext.getCurrentInstance().getMessages();
            if (iter.hasNext() == true) {
                iter.remove();
                FacesContext.getCurrentInstance().renderResponse();
            }
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
    }

    public void loadTree(ActionEvent event) {
        try {
            if (this.getTree() == null) {
                CategoriaService service = (CategoriaService) ServiceFinder.findBean("CategoriaService");
                createTree(service.getCategoriasActived());
            }
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
    }

    public void createTree(List<Categoria> lista) {
        try {
            for (Categoria ele : lista) {
                String id = ele.getNcategoriasup() != null ? ele.getNcategoriasup().toString() : null;
                if (id != null) {
                    TreeNode parent = this.getNodeByIdCategoria(this.getTree(), id);
                    TreeNode node = new DefaultTreeNode(ele, parent);
                    node.setParent(parent);
                } else {
                    this.setTree(new DefaultTreeNode(ele, this.getTree()));
                }
            }
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
    }

    public TreeNode getNodeByIdCategoria(TreeNode treeNode, String idCategoria) {
        try {
            if (treeNode != null) {
                Categoria categoria = (Categoria) treeNode.getData();
                if (categoria.getNcategoriaid().toString().equals(idCategoria)) {
                    return treeNode;
                }
                List<TreeNode> lista = treeNode.getChildren();
                if (lista != null && !lista.isEmpty()) {
                    for (TreeNode node : lista) {
                        if (node != null) {
                            TreeNode tn = this.getNodeByIdCategoria(node, idCategoria);
                            if (tn != null) {
                                return tn;
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
        return null;
    }

    public void onNodeSelect(NodeSelectEvent event) {
        try {
            if (event != null) {
                this.setSelectedCategoria((Categoria) event.getTreeNode().getData());
                this.selectedPregunta.setNcategoriaid(this.selectedCategoria.getNcategoriaid());
            }
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
    }
    
    public void toDeleteOutstanding(ActionEvent event) {
        try {
            if (event != null) {
                int index = Integer.parseInt((String) JSFUtils.getRequestParameter("index"));
                this.setSelectedDestacado(this.getListaDestacados().get(index));
            }
        } catch (Exception e) {
            e.getMessage();
        }
    }

    public void deleteOutstanding(ActionEvent event) {
        try {
            if (event != null) {
                PreguntaService service = (PreguntaService) ServiceFinder.findBean("PreguntaService");
                Pregunta pregunta = service.getPreguntaById(this.getSelectedDestacado().getIdconocimiento());
                if (pregunta != null) {
                    LoginMB loginMB = (LoginMB) JSFUtils.getSessionAttribute("loginMB");
                    User user = loginMB.getUser();
                    pregunta.setNdestacado(BigDecimal.ZERO);
                    pregunta.setVusuariomodificacion(user.getVlogin());
                    pregunta.setDfechamodificacion(new Date());
                    service.saveOrUpdate(pregunta);
                    ConsultaService consultaService = (ConsultaService) ServiceFinder.findBean("ConsultaService");
                    HashMap filter = new HashMap();
                    filter.put("ntipoconocimientoid", Constante.PREGUNTAS);
                    this.setListaDestacados(consultaService.getDestacadosByTipoConocimiento(filter));
                }
            }
        } catch (Exception e) {
            e.getMessage();
        }
    }

    public String toSave() {
        try {
            this.cleanAttributes();
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
        return "/pages/pregunta/nuevo?faces-redirect=true";
    }

    public void toEnt(ActionEvent event) {
        try {
            PreguntaService service = (PreguntaService) ServiceFinder.findBean("PreguntaService");
            this.setEntidad(service.getNomEntidadbyIdEntidad(this.getEntidadId()));
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
    }

    public void toEntEdit(ActionEvent event) {
        try {
            PreguntaService service = (PreguntaService) ServiceFinder.findBean("PreguntaService");
            this.setEntidad(service.getNomEntidadbyIdEntidad(this.getSelectedPregunta().getNentidadid()));
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
    }

    public String save() throws Exception {
        String pagina="";
        try {
            /* Validando si la cantidad de pregutnas destacados llegó al límite (10 max.).*/
            if (this.getChkDestacado()) {
                ConsultaService consultaService = (ConsultaService) ServiceFinder.findBean("ConsultaService");
                HashMap filter = new HashMap();
                filter.put("ntipoconocimientoid", Constante.PREGUNTAS);
                BigDecimal cant = consultaService.countDestacadosByTipoConocimiento(filter);
                if (cant.intValue() >= 10) {
                    this.setListaDestacados(consultaService.getDestacadosByTipoConocimiento(filter));
                    RequestContext.getCurrentInstance().execute("PF('destDialog').show();");
                    return "";
                }
            }
            if (CollectionUtils.isEmpty(this.getListaPregunta())) {
                this.setListaPregunta(Collections.EMPTY_LIST);
            }
            BigDecimal idperfil;

            LoginMB loginMB = (LoginMB) JSFUtils.getSessionAttribute("loginMB");
            User user = loginMB.getUser();

            Pregunta pregunta = new Pregunta();
            PreguntaService service = (PreguntaService) ServiceFinder.findBean("PreguntaService");
            pregunta.setNpreguntaid(service.getNextPK());
            pregunta.setNcategoriaid(this.getSelectedCategoria().getNcategoriaid());
            pregunta.setVasunto(this.getAsunto().trim());
            pregunta.setVdetalle(this.getDetalle().trim());
            pregunta.setNentidadid(this.getEntidadId());
            pregunta.setVdatoadicional(this.getDatoAdicional().trim());
            pregunta.setNdestacado(this.getChkDestacado() ? BigDecimal.ONE : BigDecimal.ZERO);
            pregunta.setNactivo(BigDecimal.ONE);
            pregunta.setDfechacreacion(new Date());
            pregunta.setVusuariocreacion(user.getVlogin());
            if (this.getSelectedCategoria().getNflagbp().equals(BigDecimal.ONE)) {
                pregunta.setNsituacionid(BigDecimal.valueOf(Long.parseLong(Constante.SITUACION_POR_VERIFICAR)));
            } else {
                pregunta.setNsituacionid(BigDecimal.valueOf(Long.parseLong(Constante.SITUACION_PUBLICADO)));
                pregunta.setDfechapublicacion(new Date());
            }
            service.saveOrUpdate(pregunta);
            
            String ruta0 = this.path + pregunta.getNpreguntaid().toString() + "\\" + BigDecimal.ZERO.toString() + "\\";
            String texto = pregunta.getVasunto() + " \n " + pregunta.getVdetalle() + " \n " + pregunta.getVrespuesta();
            GcmFileUtils.writeStringToFileServer(ruta0, "plain.txt", texto);
            
            if(this.getSelectedCategoria().getNflagpr().toString().equals("1"))
            {
            Asignacion asignacion = new Asignacion();
            AsignacionService serviceasig = (AsignacionService) ServiceFinder.findBean("AsignacionService");
            asignacion.setNasignacionid(serviceasig.getNextPK());
            asignacion.setNtipoconocimientoid(Constante.PREGUNTAS);
            asignacion.setNconocimientoid(pregunta.getNpreguntaid());
            asignacion.setNestadoid(BigDecimal.valueOf(Long.parseLong("1")));
            CategoriaService categoriaService = (CategoriaService) ServiceFinder.findBean("CategoriaService");
            asignacion.setNusuarioid(categoriaService.getCategoriaById(pregunta.getNcategoriaid()).getNmoderador());
            asignacion.setDfechaasignacion(new Date());
            asignacion.setDfechacreacion(new Date());
            serviceasig.saveOrUpdate(asignacion);
            }
            
            idperfil = service.obtenerPerfilxUsuario(user.getNusuarioid());
            
            if(Integer.parseInt(idperfil.toString()) != Constante.USUARIOEXTERNO){
                pagina="/pages/pregunta/lista?faces-redirect=true";
            }else{
                pagina="/index?faces-redirect=true";
            }
            listaPregunta = service.getPreguntas();
            RequestContext.getCurrentInstance().execute("PF('newDialog').hide();");
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
        return pagina;
    }

    public void activar(ActionEvent event) {
        try {
            if (event != null) {
                if (this.getSelectedPregunta() != null) {
                    PreguntaService service = (PreguntaService) ServiceFinder.findBean("PreguntaService");
                    this.getSelectedPregunta().setNactivo(BigDecimal.ONE);
                    this.getSelectedPregunta().setDfechamodificacion(new Date());
//                    this.getSelectedMaestro().setVusumod(user.getUsuario());
                    service.saveOrUpdate(this.getSelectedPregunta());
                    this.setListaPregunta(service.getPreguntas());
                } else {
                    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, Constante.SEVERETY_ALERTA, "Debe seleccionar la pregunta a activar.");
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
                if (this.getSelectedPregunta() != null) {
                    PreguntaService service = (PreguntaService) ServiceFinder.findBean("PreguntaService");
                    this.getSelectedPregunta().setNactivo(BigDecimal.ZERO);
                    this.getSelectedPregunta().setDfechamodificacion(new Date());
//                    this.getSelectedMaestro().setVusumod(user.getUsuario());
                    service.saveOrUpdate(this.getSelectedPregunta());
                    this.setListaPregunta(service.getPreguntas());
                } else {
                    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, Constante.SEVERETY_ALERTA, "Debe seleccionar la base legal a desactivar.");
                    FacesContext.getCurrentInstance().addMessage(null, message);
                }
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
    }

    public String toSee() {
        String pagina = null;
        try {
            int situacion;

            PreguntaService service = (PreguntaService) ServiceFinder.findBean("PreguntaService");
            int index = Integer.parseInt((String) JSFUtils.getRequestParameter("index"));
            this.setSelectedPregunta(this.getListaPregunta().get(index));
            this.setEntidad(service.getNomEntidadbyIdEntidad(this.getSelectedPregunta().getNentidadid()));

            this.setListaSourceVinculos(new ArrayList<Consulta>());
            this.setListaTargetVinculos(new ArrayList<Consulta>());
            this.setPickListPregunta(new DualListModel<Consulta>(this.getListaSourceVinculos(), this.getListaTargetVinculos()));

            this.listaTargetVinculosConocimiento = new ArrayList<Consulta>();
            this.listaTargetVinculosBL = new ArrayList<Consulta>();
            this.listaTargetVinculosPR = new ArrayList<Consulta>();
            this.listaTargetVinculosWK = new ArrayList<Consulta>();
            this.listaTargetVinculosCT = new ArrayList<Consulta>();
            this.listaTargetVinculosBP = new ArrayList<Consulta>();
            this.listaTargetVinculosOM = new ArrayList<Consulta>();

            HashMap filters = new HashMap();
            filters.put("ntipoconocimientoid", BigDecimal.valueOf(Long.parseLong("1")));
            filters.put("npreguntaid", this.getSelectedPregunta().getNpreguntaid());
            this.getListaTargetVinculosBL().addAll(service.getConcimientosVinculados(filters));

            filters.put("ntipoconocimientoid", BigDecimal.valueOf(Long.parseLong("2")));
            filters.put("npreguntaid", this.getSelectedPregunta().getNpreguntaid());
            this.getListaTargetVinculosPR().addAll(service.getConcimientosVinculados(filters));

            filters.put("ntipoconocimientoid", BigDecimal.valueOf(Long.parseLong("3")));
            filters.put("npreguntaid", this.getSelectedPregunta().getNpreguntaid());
            this.getListaTargetVinculosWK().addAll(service.getConcimientosVinculados(filters));

            filters.put("ntipoconocimientoid", BigDecimal.valueOf(Long.parseLong("4")));
            filters.put("npreguntaid", this.getSelectedPregunta().getNpreguntaid());
            this.getListaTargetVinculosCT().addAll(service.getConcimientosVinculados(filters));

            filters.put("ntipoconocimientoid", BigDecimal.valueOf(Long.parseLong("5")));
            filters.put("npreguntaid", this.getSelectedPregunta().getNpreguntaid());
            this.getListaTargetVinculosBP().addAll(service.getConcimientosVinculados(filters));

            filters.put("ntipoconocimientoid", BigDecimal.valueOf(Long.parseLong("6")));
            filters.put("npreguntaid", this.getSelectedPregunta().getNpreguntaid());
            this.getListaTargetVinculosOM().addAll(service.getConcimientosVinculados(filters));

            if (this.getListaTargetVinculosBL() == null) {
            } else {
                this.getListaTargetVinculosConocimiento().addAll(this.getListaTargetVinculosBL());
            }
            if (this.getListaTargetVinculosBP() == null) {
            } else {
                this.getListaTargetVinculosConocimiento().addAll(this.getListaTargetVinculosBP());
            }
            if (this.getListaTargetVinculosCT() == null) {
            } else {
                this.getListaTargetVinculosConocimiento().addAll(this.getListaTargetVinculosCT());
            }
            if (this.getListaTargetVinculosOM() == null) {
            } else {
                this.getListaTargetVinculosConocimiento().addAll(this.getListaTargetVinculosOM());
            }
            if (this.getListaTargetVinculosWK() == null) {
            } else {
                this.getListaTargetVinculosConocimiento().addAll(this.getListaTargetVinculosWK());
            }

            situacion = Integer.parseInt(this.getSelectedPregunta().getNsituacionid().toString());

            pagina = "/pages/pregunta/ver?faces-redirect=true";

        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
        return pagina;
    }

    public void toCancel(ActionEvent event) {
        try {
            PreguntaService service = (PreguntaService) ServiceFinder.findBean("PreguntaService");
            listaPregunta = service.getPreguntas();
            RequestContext.getCurrentInstance().execute("PF('respDialog').hide();");
            RequestContext.getCurrentInstance().execute("PF('modDialog').hide();");
            RequestContext.getCurrentInstance().execute("PF('modpubDialog').hide();");
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
    }

    public void Rechazar(ActionEvent event) {
        try {
            PreguntaService service = (PreguntaService) ServiceFinder.findBean("PreguntaService");
            this.getSelectedPregunta().setNsituacionid(BigDecimal.valueOf(Long.parseLong("7")));
            service.saveOrUpdate(this.getSelectedPregunta());

            AsignacionService serviceasig = (AsignacionService) ServiceFinder.findBean("AsignacionService");
            this.getSelectedAsignacion().setNestadoid(BigDecimal.valueOf(Long.parseLong("2")));
            serviceasig.saveOrUpdate(this.getSelectedAsignacion());

            this.setListaPregunta(service.getPreguntas());
            RequestContext.getCurrentInstance().execute("PF('modDialog').hide();");
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
    }

    public void toCancelSi(ActionEvent event) {
        try {
            RequestContext.getCurrentInstance().execute("PF('siDialog').hide();");
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
    }

    public void toCancelSiMod(ActionEvent event) {
        try {
            RequestContext.getCurrentInstance().execute("PF('simodDialog').hide();");
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
    }

    public void toCancelRespUsu(ActionEvent event) {
        try {
            RequestContext.getCurrentInstance().execute("PF('respusuDialog').hide();");
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
    }

    public void toCancelRespUsuMod(ActionEvent event) {
        try {
            RequestContext.getCurrentInstance().execute("PF('respmodDialog').hide();");
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
    }

    public void toSi(ActionEvent event) {
        try {

        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
    }

    public void toMod(ActionEvent event) {
        try {
            RequestContext.getCurrentInstance().execute("PF('seeDialog').hide();");
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
    }

    public void toModPub(ActionEvent event) {
        try {
            RequestContext.getCurrentInstance().execute("PF('seeDialog').hide();");
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
    }

    public void toResp(ActionEvent event) {
        try {
            RequestContext.getCurrentInstance().execute("PF('seeDialog').hide();");
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
    }

    public void toCancelRespEdit(ActionEvent event) {
        try {
            RequestContext.getCurrentInstance().execute("PF('editrespDialog').hide();");
            RequestContext.getCurrentInstance().execute("PF('editpregDialog').hide();");
            RequestContext.getCurrentInstance().execute("PF('editpubDialog').hide();");
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
    }

    public void toRespUsu(ActionEvent event) {
        try {

            this.cleanAttributes();
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
    }

    public void toRespUsuMod(ActionEvent event) {
        try {

            this.cleanAttributes();
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
    }

    public void saveResp(ActionEvent event) throws Exception {
        try {
            if (CollectionUtils.isEmpty(this.getListaPregunta())) {
                this.setListaPregunta(Collections.EMPTY_LIST);
            }
            BigDecimal cat2;

            PreguntaService service = (PreguntaService) ServiceFinder.findBean("PreguntaService");
            this.getSelectedPregunta().setVrespuesta(this.getSelectedPregunta().getVrespuesta().toUpperCase());
            this.getSelectedPregunta().setNcategoriaid(this.getSelectedPregunta().getNcategoriaid());

            cat2 = this.getSelectedPregunta().getNcategoriaid();

            if (Integer.parseInt(getCat1().toString()) != Integer.parseInt(cat2.toString())) {
                this.getSelectedPregunta().setNsituacionid(BigDecimal.valueOf(Long.parseLong("3")));
            } else {
                this.getSelectedPregunta().setNsituacionid(BigDecimal.valueOf(Long.parseLong("6")));
            }

            service.saveOrUpdate(this.getSelectedPregunta());
            this.setListaPregunta(service.getPreguntas());
            RequestContext.getCurrentInstance().execute("PF('respDialog').hide();");

        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
    }

    public void sendUsu(ActionEvent event) throws Exception {
        try {
            if (CollectionUtils.isEmpty(this.getListaPregunta())) {
                this.setListaPregunta(Collections.EMPTY_LIST);
            }
            if (StringUtils.isBlank(this.getSelectedPregunta().getVmsjusuario2())) {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, Constante.SEVERETY_ALERTA, "Nombre requerido. Ingrese el nombre de perfil.");
                FacesContext.getCurrentInstance().addMessage(null, message);
                return;
            }
            PreguntaService service = (PreguntaService) ServiceFinder.findBean("PreguntaService");
            this.getSelectedPregunta().setVmsjusuario2(this.getSelectedPregunta().getVmsjusuario2().toUpperCase());
            service.saveOrUpdate(this.getSelectedPregunta());

            AsignacionService serviceasig = (AsignacionService) ServiceFinder.findBean("AsignacionService");
            this.getSelectedAsignacion().setNestadoid(BigDecimal.valueOf(Long.parseLong("2")));
            this.getSelectedAsignacion().setDfechaatencion(new Date());
            serviceasig.saveOrUpdate(this.getSelectedAsignacion());

            Asignacion asignacion = new Asignacion();
            asignacion.setNasignacionid(serviceasig.getNextPK());
            asignacion.setNtipoconocimientoid(Constante.PREGUNTAS);
            asignacion.setNconocimientoid(this.getSelectedPregunta().getNpreguntaid());
            asignacion.setNestadoid(BigDecimal.valueOf(Long.parseLong("1")));
            asignacion.setNusuarioid(BigDecimal.valueOf(Long.parseLong("3")));
            asignacion.setDfechacreacion(new Date());
            asignacion.setDfechaasignacion(new Date());
            serviceasig.saveOrUpdate(asignacion);

            this.fMsjUsu2 = "true";

            this.setListaPregunta(service.getPreguntas());

            RequestContext.getCurrentInstance().execute("PF('respusuDialog').hide();");
            RequestContext.getCurrentInstance().execute("PF('seeDialog').hide();");

        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
    }

    public void sendUsuMod(ActionEvent event) throws Exception {
        try {
            if (CollectionUtils.isEmpty(this.getListaPregunta())) {
                this.setListaPregunta(Collections.EMPTY_LIST);
            }
            if (StringUtils.isBlank(this.getSelectedPregunta().getVmsjusuario1())) {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, Constante.SEVERETY_ALERTA, "Nombre requerido. Ingrese el nombre de perfil.");
                FacesContext.getCurrentInstance().addMessage(null, message);
                return;
            }
            PreguntaService service = (PreguntaService) ServiceFinder.findBean("PreguntaService");
            this.getSelectedPregunta().setVmsjusuario1(this.getSelectedPregunta().getVmsjusuario1().toUpperCase());
            service.saveOrUpdate(this.getSelectedPregunta());

            AsignacionService serviceasig = (AsignacionService) ServiceFinder.findBean("AsignacionService");
            this.getSelectedAsignacion().setNestadoid(BigDecimal.valueOf(Long.parseLong("2")));
            this.getSelectedAsignacion().setDfechaatencion(new Date());
            serviceasig.saveOrUpdate(this.getSelectedAsignacion());

            Asignacion asignacion = new Asignacion();
            asignacion.setNasignacionid(serviceasig.getNextPK());
            asignacion.setNtipoconocimientoid(Constante.PREGUNTAS);
            asignacion.setNconocimientoid(this.getSelectedPregunta().getNpreguntaid());
            asignacion.setNestadoid(BigDecimal.valueOf(Long.parseLong("1")));
            asignacion.setNusuarioid(BigDecimal.valueOf(Long.parseLong("2")));
            asignacion.setDfechacreacion(new Date());
            asignacion.setDfechaasignacion(new Date());
            serviceasig.saveOrUpdate(asignacion);

            this.fMsjUsu1 = "true";

            this.setListaPregunta(service.getPreguntas());

            RequestContext.getCurrentInstance().execute("PF('respmodDialog').hide();");
            RequestContext.getCurrentInstance().execute("PF('seeDialog').hide();");

        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
    }

    public void sendSi(ActionEvent event) throws Exception {
        try {
            if (CollectionUtils.isEmpty(this.getListaPregunta())) {
                this.setListaPregunta(Collections.EMPTY_LIST);
            }
            if (StringUtils.isBlank(this.getSelectedPregunta().getVmsjespecialista())) {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, Constante.SEVERETY_ALERTA, "Nombre requerido. Ingrese el nombre de perfil.");
                FacesContext.getCurrentInstance().addMessage(null, message);
                return;
            }
            PreguntaService service = (PreguntaService) ServiceFinder.findBean("PreguntaService");
            this.getSelectedPregunta().setVmsjespecialista(this.getSelectedPregunta().getVmsjespecialista().toUpperCase());
            service.saveOrUpdate(this.getSelectedPregunta());

            AsignacionService serviceasig = (AsignacionService) ServiceFinder.findBean("AsignacionService");
            this.getSelectedAsignacion().setNestadoid(BigDecimal.valueOf(Long.parseLong("2")));
            this.getSelectedAsignacion().setDfechaatencion(new Date());
            serviceasig.saveOrUpdate(this.getSelectedAsignacion());

            Asignacion asignacion = new Asignacion();
            asignacion.setNasignacionid(serviceasig.getNextPK());
            asignacion.setNtipoconocimientoid(Constante.PREGUNTAS);
            asignacion.setNconocimientoid(this.getSelectedPregunta().getNpreguntaid());
            asignacion.setNestadoid(BigDecimal.valueOf(Long.parseLong("1")));
            asignacion.setNusuarioid(BigDecimal.valueOf(Long.parseLong("4")));
            asignacion.setDfechacreacion(new Date());
            asignacion.setDfechaasignacion(new Date());
            serviceasig.saveOrUpdate(asignacion);

            this.fSInfEspe = "true";

            this.setListaPregunta(service.getPreguntas());

            RequestContext.getCurrentInstance().execute("PF('siDialog').hide();");
            RequestContext.getCurrentInstance().execute("PF('respDialog').hide();");

        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
    }

    public void sendSiMod(ActionEvent event) throws Exception {
        try {
            if (CollectionUtils.isEmpty(this.getListaPregunta())) {
                this.setListaPregunta(Collections.EMPTY_LIST);
            }
            if (StringUtils.isBlank(this.getSelectedPregunta().getVmsjmoderador())) {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, Constante.SEVERETY_ALERTA, "Nombre requerido. Ingrese el nombre de perfil.");
                FacesContext.getCurrentInstance().addMessage(null, message);
                return;
            }
            PreguntaService service = (PreguntaService) ServiceFinder.findBean("PreguntaService");
            this.getSelectedPregunta().setVmsjmoderador(this.getSelectedPregunta().getVmsjmoderador().toUpperCase());
            service.saveOrUpdate(this.getSelectedPregunta());

            AsignacionService serviceasig = (AsignacionService) ServiceFinder.findBean("AsignacionService");
            this.getSelectedAsignacion().setNestadoid(BigDecimal.valueOf(Long.parseLong("2")));
            this.getSelectedAsignacion().setDfechaatencion(new Date());
            serviceasig.saveOrUpdate(this.getSelectedAsignacion());

            Asignacion asignacion = new Asignacion();
            asignacion.setNasignacionid(serviceasig.getNextPK());
            asignacion.setNtipoconocimientoid(Constante.PREGUNTAS);
            asignacion.setNconocimientoid(this.getSelectedPregunta().getNpreguntaid());
            asignacion.setNestadoid(BigDecimal.valueOf(Long.parseLong("1")));
            asignacion.setNusuarioid(BigDecimal.valueOf(Long.parseLong("4")));
            asignacion.setDfechaasignacion(new Date());
            asignacion.setDfechacreacion(new Date());
            serviceasig.saveOrUpdate(asignacion);

            this.fSInfMod = "true";

            this.setListaPregunta(service.getPreguntas());

            RequestContext.getCurrentInstance().execute("PF('simodDialog').hide();");
            RequestContext.getCurrentInstance().execute("PF('modDialog').hide();");

        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
    }

    public String Publicar() throws Exception {
        String pagina = null;
        try {
             /* Validando si la cantidad de pregutnas destacados llegó al límite (10 max.).*/
            if (this.getChkDestacado()) {
                ConsultaService consultaService = (ConsultaService) ServiceFinder.findBean("ConsultaService");
                HashMap filter = new HashMap();
                filter.put("ntipoconocimientoid", Constante.PREGUNTAS);
                BigDecimal cant = consultaService.countDestacadosByTipoConocimiento(filter);
                if (cant.intValue() >= 10) {
                    this.setListaDestacados(consultaService.getDestacadosByTipoConocimiento(filter));
                    RequestContext.getCurrentInstance().execute("PF('destDialog').show();");
                    return "";
                }
            }
            LoginMB loginMB = (LoginMB) JSFUtils.getSessionAttribute("loginMB");
            User user_savepreg = loginMB.getUser();

            PreguntaService service = (PreguntaService) ServiceFinder.findBean("PreguntaService");
            if (this.getSelectedCategoria() == null) {
                this.getSelectedPregunta().setNcategoriaid(this.getSelectedPregunta().getNcategoriaid());
            } else {
                this.getSelectedPregunta().setNcategoriaid(this.getSelectedCategoria().getNcategoriaid());
            }
            this.getSelectedPregunta().setVasunto(this.getSelectedPregunta().getVasunto().trim());
            this.getSelectedPregunta().setVdetalle(this.getSelectedPregunta().getVdetalle().trim());
            this.getSelectedPregunta().setNentidadid(this.getSelectedPregunta().getNentidadid());
            this.getSelectedPregunta().setVrespuesta(this.getSelectedPregunta().getVrespuesta());
            this.getSelectedPregunta().setVdatoadicional(this.getSelectedPregunta().getVdatoadicional().trim());
            this.getSelectedPregunta().setNdestacado(this.getChkDestacado() ? BigDecimal.ONE : BigDecimal.ZERO);
            this.getSelectedPregunta().setDfechamodificacion(new Date());
            this.getSelectedPregunta().setVusuariomodificacion(user_savepreg.getVlogin());
            this.getSelectedPregunta().setNsituacionid(BigDecimal.valueOf((long) 6));
            this.getSelectedPregunta().setDfechapublicacion(new Date());
            service.saveOrUpdate(this.getSelectedPregunta());

            RespuestaHistService serviceresp = (RespuestaHistService) ServiceFinder.findBean("RespuestaHistService");
            RespuestaHist respuestahist = new RespuestaHist();
            respuestahist.setNhistorialid(serviceresp.getNextPK());
            respuestahist.setNpreguntaid(this.getSelectedPregunta().getNpreguntaid());
            respuestahist.setVrespuesta(this.getSelectedPregunta().getVrespuesta());
            respuestahist.setVusuariocreacion(user_savepreg.getVlogin());
            respuestahist.setDfechacreacion(new Date());
            serviceresp.saveOrUpdate(respuestahist);
            
            String ruta0 = this.path + this.getSelectedPregunta().getNpreguntaid().toString() + "\\" + BigDecimal.ZERO.toString() + "\\";
            String texto = this.getSelectedPregunta().getVasunto() + " \n " + this.getSelectedPregunta().getVdetalle() + " \n " + this.getSelectedPregunta().getVrespuesta();
            GcmFileUtils.writeStringToFileServer(ruta0, "plain.txt", texto);

            listaTargetVinculos = new ArrayList<Consulta>();

            if (this.getListaTargetVinculosBL() == null) {
            } else {
                this.getListaTargetVinculos().addAll(this.getListaTargetVinculosBL());
            }
            if (this.getListaTargetVinculosBP() == null) {
            } else {
                this.getListaTargetVinculos().addAll(this.getListaTargetVinculosBP());
            }
            if (this.getListaTargetVinculosCT() == null) {
            } else {
                this.getListaTargetVinculos().addAll(this.getListaTargetVinculosCT());
            }
            if (this.getListaTargetVinculosOM() == null) {
            } else {
                this.getListaTargetVinculos().addAll(this.getListaTargetVinculosOM());
            }
            if (this.getListaTargetVinculosPR() == null) {
            } else {
                this.getListaTargetVinculos().addAll(this.getListaTargetVinculosPR());
            }
            if (this.getListaTargetVinculosWK() == null) {
            } else {
                this.getListaTargetVinculos().addAll(this.getListaTargetVinculosWK());
            }

            if (org.apache.commons.collections.CollectionUtils.isNotEmpty(this.getListaTargetVinculos())) {
                VinculoPreguntaService vinculopreguntaService = (VinculoPreguntaService) ServiceFinder.findBean("VinculoPreguntaService");
                service.delete(this.getSelectedPregunta().getNpreguntaid());
                for (Consulta consulta : this.getListaTargetVinculos()) {
                    VinculoPregunta vinculopregunta = new VinculoPregunta();
                    vinculopregunta.setNvinculoid(vinculopreguntaService.getNextPK());
                    vinculopregunta.setNpreguntaid(this.getSelectedPregunta().getNpreguntaid());
                    vinculopregunta.setNconocimientovinc(consulta.getIdconocimiento());
                    vinculopregunta.setNtipoconocimientovinc(consulta.getIdTipoConocimiento());
                    vinculopregunta.setDfechacreacion(new Date());
                    vinculopregunta.setVusuariocreacion(user_savepreg.getVlogin());
                    vinculopreguntaService.saveOrUpdate(vinculopregunta);

                }
            }
            pagina = "/pages/pregunta/lista?faces-redirect=true";

        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
        return pagina;
    }

    public void Responder(ActionEvent event) throws Exception {
        try {
            if (CollectionUtils.isEmpty(this.getListaPregunta())) {
                this.setListaPregunta(Collections.EMPTY_LIST);
            }

            if (StringUtils.isBlank(this.getSelectedPregunta().getVrespuesta())) {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, Constante.SEVERETY_ALERTA, "Campo requerido. Ingrese la respuesta.");
                FacesContext.getCurrentInstance().addMessage(null, message);
                return;
            }

            PreguntaService service = (PreguntaService) ServiceFinder.findBean("PreguntaService");
            this.getSelectedPregunta().setNsituacionid(BigDecimal.valueOf(Long.parseLong("5")));
            service.saveOrUpdate(this.getSelectedPregunta());
            
            String ruta0 = this.path + this.getSelectedPregunta().getNpreguntaid().toString() + "\\" + BigDecimal.ZERO.toString() + "\\";
            String texto = this.getSelectedPregunta().getVasunto() + " \n " + this.getSelectedPregunta().getVdetalle() + " \n " + this.getSelectedPregunta().getVrespuesta();
            GcmFileUtils.writeStringToFileServer(ruta0, "plain.txt", texto);

            AsignacionService serviceasig = (AsignacionService) ServiceFinder.findBean("AsignacionService");
            this.getSelectedAsignacion().setNestadoid(BigDecimal.valueOf(Long.parseLong("2")));
            this.getSelectedAsignacion().setDfechaatencion(new Date());
            serviceasig.saveOrUpdate(this.getSelectedAsignacion());

            Asignacion asignacion = new Asignacion();
            asignacion.setNasignacionid(serviceasig.getNextPK());
            asignacion.setNtipoconocimientoid(Constante.PREGUNTAS);
            asignacion.setNconocimientoid(this.getSelectedPregunta().getNpreguntaid());
            asignacion.setNestadoid(BigDecimal.valueOf(Long.parseLong("1")));
            asignacion.setNusuarioid(BigDecimal.valueOf(Long.parseLong("2")));
            asignacion.setDfechacreacion(new Date());
            asignacion.setDfechaasignacion(new Date());
            serviceasig.saveOrUpdate(asignacion);
            this.setListaPregunta(service.getPreguntas());
            RequestContext.getCurrentInstance().execute("PF('respDialog').hide();");

        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
    }

    public void DevEsp(ActionEvent event) throws Exception {
        try {
            if (CollectionUtils.isEmpty(this.getListaPregunta())) {
                this.setListaPregunta(Collections.EMPTY_LIST);
            }

            PreguntaService service = (PreguntaService) ServiceFinder.findBean("PreguntaService");
            this.getSelectedPregunta().setNsituacionid(BigDecimal.valueOf(Long.parseLong("2")));
            service.saveOrUpdate(this.getSelectedPregunta());

            AsignacionService serviceasig = (AsignacionService) ServiceFinder.findBean("AsignacionService");
            this.getSelectedAsignacion().setNestadoid(BigDecimal.valueOf(Long.parseLong("2")));
            this.getSelectedAsignacion().setDfechaatencion(new Date());
            serviceasig.saveOrUpdate(this.getSelectedAsignacion());

            Asignacion asignacion = new Asignacion();
            asignacion.setNasignacionid(serviceasig.getNextPK());
            asignacion.setNtipoconocimientoid(Constante.PREGUNTAS);
            asignacion.setNconocimientoid(this.getSelectedPregunta().getNpreguntaid());
            asignacion.setNestadoid(BigDecimal.valueOf(Long.parseLong("1")));
            asignacion.setNusuarioid(BigDecimal.valueOf(Long.parseLong("3")));
            asignacion.setDfechacreacion(new Date());
            asignacion.setDfechaasignacion(new Date());
            serviceasig.saveOrUpdate(asignacion);
            this.setListaPregunta(service.getPreguntas());
            RequestContext.getCurrentInstance().execute("PF('modDialog').hide();");

        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
    }

    public void saveRespEdit(ActionEvent event) throws Exception {
        try {
            if (CollectionUtils.isEmpty(this.getListaPregunta())) {
                this.setListaPregunta(Collections.EMPTY_LIST);
            }

            PreguntaService service = (PreguntaService) ServiceFinder.findBean("PreguntaService");

            this.getSelectedPregunta().setVrespuesta(this.getSelectedPregunta().getVrespuesta().toUpperCase());
            this.getSelectedPregunta().setNcategoriaid(this.getSelectedPregunta().getNcategoriaid());
            this.getSelectedPregunta().setVasunto(this.getSelectedPregunta().getVasunto().trim());
            this.getSelectedPregunta().setVdetalle(this.getSelectedPregunta().getVdetalle().trim());
            this.getSelectedPregunta().setNentidadid(this.getSelectedPregunta().getNentidadid());
            this.getSelectedPregunta().setVdatoadicional(this.getSelectedPregunta().getVdatoadicional().trim());
            this.getSelectedPregunta().setDfechamodificacion(new Date());
            service.saveOrUpdate(this.getSelectedPregunta());

            this.setListaPregunta(service.getPreguntas());
            RequestContext.getCurrentInstance().execute("PF('editrespDialog').hide();");
            RequestContext.getCurrentInstance().execute("PF('editpregDialog').hide();");

        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }

    }

    public void savePregEdit(ActionEvent event) throws Exception {
        try {
            if (CollectionUtils.isEmpty(this.getListaPregunta())) {
                this.setListaPregunta(Collections.EMPTY_LIST);
            }

            PreguntaService service = (PreguntaService) ServiceFinder.findBean("PreguntaService");

            this.getSelectedPregunta().setNcategoriaid(this.getSelectedPregunta().getNcategoriaid());
            this.getSelectedPregunta().setVasunto(this.getSelectedPregunta().getVasunto().trim());
            this.getSelectedPregunta().setVdetalle(this.getSelectedPregunta().getVdetalle().trim());
            this.getSelectedPregunta().setNentidadid(this.getSelectedPregunta().getNentidadid());
            this.getSelectedPregunta().setVdatoadicional(this.getSelectedPregunta().getVdatoadicional().trim());
            this.getSelectedPregunta().setDfechamodificacion(new Date());
            service.saveOrUpdate(this.getSelectedPregunta());

            this.setListaPregunta(service.getPreguntas());
            RequestContext.getCurrentInstance().execute("PF('editrespDialog').hide();");
            RequestContext.getCurrentInstance().execute("PF('editpregDialog').hide();");

        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }

    }

    public void savePubEdit(ActionEvent event) throws Exception {
        try {
            if (CollectionUtils.isEmpty(this.getListaPregunta())) {
                this.setListaPregunta(Collections.EMPTY_LIST);
            }

            PreguntaService service = (PreguntaService) ServiceFinder.findBean("PreguntaService");

            this.getSelectedPregunta().setVrespuesta(this.getSelectedPregunta().getVrespuesta());
            this.getSelectedPregunta().setVasunto(this.getSelectedPregunta().getVasunto().trim());
            this.getSelectedPregunta().setVdetalle(this.getSelectedPregunta().getVdetalle().trim());
            this.getSelectedPregunta().setNentidadid(this.getSelectedPregunta().getNentidadid());
            this.getSelectedPregunta().setVdatoadicional(this.getSelectedPregunta().getVdatoadicional().trim());
            this.getSelectedPregunta().setDfechamodificacion(new Date());
            service.saveOrUpdate(this.getSelectedPregunta());

            this.setListaPregunta(service.getPreguntas());
            RequestContext.getCurrentInstance().execute("PF('editrespDialog').hide();");
            RequestContext.getCurrentInstance().execute("PF('editpregDialog').hide();");
            RequestContext.getCurrentInstance().execute("PF('editpubDialog').hide();");

        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }

    }

    public String toHistorial() {
        String pagina = null;
        try {
            int index = Integer.parseInt((String) JSFUtils.getRequestParameter("index"));
            this.setSelectedPregunta(this.getListaPregunta().get(index));
            RespuestaHistService serviceresp = (RespuestaHistService) ServiceFinder.findBean("RespuestaHistService");
            this.setListaRespuesta(serviceresp.getHistorialByPregunta(this.getSelectedPregunta().getNpreguntaid()));
            pagina = "/pages/respuestaHistorial?faces-redirect=true";
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
        return pagina;
    }

    public void toAddCalificacion(ActionEvent event) {
        try {
            this.clearCalificacion();
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
    }

    public void addCalificacion(ActionEvent event) {
        try {
            if (this.getCalificacion() == null) {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR.", "Ingrese la calificacion al wiki.");
                FacesContext.getCurrentInstance().addMessage(null, message);
                return;
            }
            if (StringUtils.isBlank(this.getComentarioCalificacion())) {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR.", "Ingrese un comentario al wiki.");
                FacesContext.getCurrentInstance().addMessage(null, message);
                return;
            }
            LoginMB loginMB = (LoginMB) JSFUtils.getSessionAttribute("loginMB");
            User user = loginMB.getUser();
            CalificacionPreguntaService calificacionService = (CalificacionPreguntaService) ServiceFinder.findBean("CalificacionPreguntaService");
            CalificacionPregunta cal = new CalificacionPregunta();
            cal.setNcalificacionid(calificacionService.getNextPK());
            cal.setNpreguntaid(this.getSelectedPregunta().getNpreguntaid());
            cal.setNcalificacion(this.getCalificacion());
            cal.setVcomentario(StringUtils.capitalize(this.getComentarioCalificacion().trim()));
            cal.setDfechacreacion(new Date());
            cal.setVusuariocreacion(user.getVlogin());
            calificacionService.saveOrUpdate(cal);
            this.setListaCalificacion(calificacionService.getCalificacionesByConocimiento(this.getSelectedPregunta().getNpreguntaid()));
            if (org.apache.commons.collections.CollectionUtils.isNotEmpty(this.getListaCalificacion())) {
                UserService userService = (UserService) ServiceFinder.findBean("UserService");
                for (CalificacionPregunta c : this.getListaCalificacion()) {
                    User u = userService.getUserByLogin(c.getVusuariocreacion());
                    c.setUsuarioNombre(u.getVnombres() + " " + u.getVapellidos());
                }
            }
            RequestContext.getCurrentInstance().execute("PF('calDialog').hide();");
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
    }

    public void toEditCalificacion(ActionEvent event) {
        try {
            this.clearCalificacion();
            int index = Integer.parseInt((String) JSFUtils.getRequestParameter("index"));
            this.setSelectedCalificacion(this.getListaCalificacion().get(index));
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
    }

    public void editCalificacion(ActionEvent event) {
        try {
            if (this.getSelectedCalificacion().getNcalificacion() == null) {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR.", "Ingrese la calificacion al wiki.");
                FacesContext.getCurrentInstance().addMessage(null, message);
                return;
            }
            if (StringUtils.isBlank(this.getSelectedCalificacion().getVcomentario())) {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR.", "Ingrese un comentario al wiki.");
                FacesContext.getCurrentInstance().addMessage(null, message);
                return;
            }
            LoginMB loginMB = (LoginMB) JSFUtils.getSessionAttribute("loginMB");
            User user = loginMB.getUser();
            CalificacionPreguntaService calificacionService = (CalificacionPreguntaService) ServiceFinder.findBean("CalificacionPreguntaService");
            this.getSelectedCalificacion().setNcalificacion(this.getSelectedCalificacion().getNcalificacion());
            this.getSelectedCalificacion().setVcomentario(StringUtils.capitalize(this.getSelectedCalificacion().getVcomentario().trim()));
            this.getSelectedCalificacion().setDfechamodificacion(new Date());
            this.getSelectedCalificacion().setVusuariomodificacion(user.getVlogin());
            calificacionService.saveOrUpdate(this.getSelectedCalificacion());
            this.setListaCalificacion(calificacionService.getCalificacionesByConocimiento(this.getSelectedPregunta().getNpreguntaid()));
            if (org.apache.commons.collections.CollectionUtils.isNotEmpty(this.getListaCalificacion())) {
                UserService userService = (UserService) ServiceFinder.findBean("UserService");
                for (CalificacionPregunta c : this.getListaCalificacion()) {
                    User u = userService.getUserByLogin(c.getVusuariocreacion());
                    c.setUsuarioNombre(u.getVnombres() + " " + u.getVapellidos());
                }
            }
            RequestContext.getCurrentInstance().execute("PF('ecalDialog').hide();");
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
    }

    public void toDeleteCalificacion(ActionEvent event) {
        try {
            this.clearCalificacion();
            int index = Integer.parseInt((String) JSFUtils.getRequestParameter("index"));
            this.setSelectedCalificacion(this.getListaCalificacion().get(index));
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
    }

    public void deleteCalificacion(ActionEvent event) {
        try {
            CalificacionPreguntaService calificacionService = (CalificacionPreguntaService) ServiceFinder.findBean("CalificacionPreguntaService");
            calificacionService.delete(this.getSelectedCalificacion().getNcalificacionid());
            this.setListaCalificacion(calificacionService.getCalificacionesByConocimiento(this.getSelectedPregunta().getNpreguntaid()));
            if (org.apache.commons.collections.CollectionUtils.isNotEmpty(this.getListaCalificacion())) {
                UserService userService = (UserService) ServiceFinder.findBean("UserService");
                for (CalificacionPregunta c : this.getListaCalificacion()) {
                    User u = userService.getUserByLogin(c.getVusuariocreacion());
                    c.setUsuarioNombre(u.getVnombres() + " " + u.getVapellidos());
                }
            }
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
    }

    public String update() throws Exception {
        String pagina = "";
        try {
            /* Validando si la cantidad de pregutnas destacados llegó al límite (10 max.).*/
            if (this.getChkDestacado()) {
                ConsultaService consultaService = (ConsultaService) ServiceFinder.findBean("ConsultaService");
                HashMap filter = new HashMap();
                filter.put("ntipoconocimientoid", Constante.PREGUNTAS);
                BigDecimal cant = consultaService.countDestacadosByTipoConocimiento(filter);
                if (cant.intValue() >= 10) {
                    this.setListaDestacados(consultaService.getDestacadosByTipoConocimiento(filter));
                    RequestContext.getCurrentInstance().execute("PF('destDialog').show();");
                    return "";
                }
            }
            LoginMB loginMB = (LoginMB) JSFUtils.getSessionAttribute("loginMB");
            User user_savepreg = loginMB.getUser();

            PreguntaService service = (PreguntaService) ServiceFinder.findBean("PreguntaService");
            if (this.getSelectedCategoria() == null) {
                this.getSelectedPregunta().setNcategoriaid(this.getSelectedPregunta().getNcategoriaid());
            } else {
                this.getSelectedPregunta().setNcategoriaid(this.getSelectedCategoria().getNcategoriaid());
            }
            this.getSelectedPregunta().setVasunto(this.getSelectedPregunta().getVasunto().trim());
            this.getSelectedPregunta().setVdetalle(this.getSelectedPregunta().getVdetalle().trim());
            this.getSelectedPregunta().setNentidadid(this.getSelectedPregunta().getNentidadid());
            this.getSelectedPregunta().setVrespuesta(this.getSelectedPregunta().getVrespuesta());
            this.getSelectedPregunta().setVdatoadicional(this.getSelectedPregunta().getVdatoadicional().trim());
            this.getSelectedPregunta().setNdestacado(this.getChkDestacado() ? BigDecimal.ONE : BigDecimal.ZERO);
            this.getSelectedPregunta().setDfechamodificacion(new Date());
            this.getSelectedPregunta().setVusuariomodificacion(user_savepreg.getVlogin());
            service.saveOrUpdate(this.getSelectedPregunta());

            RespuestaHistService serviceresp = (RespuestaHistService) ServiceFinder.findBean("RespuestaHistService");
            RespuestaHist respuestahist = new RespuestaHist();
            respuestahist.setNhistorialid(serviceresp.getNextPK());
            respuestahist.setNpreguntaid(this.getSelectedPregunta().getNpreguntaid());
            respuestahist.setVrespuesta(this.getSelectedPregunta().getVrespuesta());
            respuestahist.setVusuariocreacion(user_savepreg.getVlogin());
            respuestahist.setDfechacreacion(new Date());
            serviceresp.saveOrUpdate(respuestahist);
            
            String ruta0 = this.path + this.getSelectedPregunta().getNpreguntaid().toString() + "\\" + BigDecimal.ZERO.toString() + "\\";
            String texto = this.getSelectedPregunta().getVasunto() + " \n " + this.getSelectedPregunta().getVdetalle() + " \n " + this.getSelectedPregunta().getVrespuesta();
            GcmFileUtils.writeStringToFileServer(ruta0, "plain.txt", texto);

            listaTargetVinculos = new ArrayList<Consulta>();

            if (this.getListaTargetVinculosBL() == null) {
            } else {
                this.getListaTargetVinculos().addAll(this.getListaTargetVinculosBL());
            }
            if (this.getListaTargetVinculosBP() == null) {
            } else {
                this.getListaTargetVinculos().addAll(this.getListaTargetVinculosBP());
            }
            if (this.getListaTargetVinculosCT() == null) {
            } else {
                this.getListaTargetVinculos().addAll(this.getListaTargetVinculosCT());
            }
            if (this.getListaTargetVinculosOM() == null) {
            } else {
                this.getListaTargetVinculos().addAll(this.getListaTargetVinculosOM());
            }
            if (this.getListaTargetVinculosPR() == null) {
            } else {
                this.getListaTargetVinculos().addAll(this.getListaTargetVinculosPR());
            }
            if (this.getListaTargetVinculosWK() == null) {
            } else {
                this.getListaTargetVinculos().addAll(this.getListaTargetVinculosWK());
            }

            if (org.apache.commons.collections.CollectionUtils.isNotEmpty(this.getListaTargetVinculos())) {
                VinculoPreguntaService vinculopreguntaService = (VinculoPreguntaService) ServiceFinder.findBean("VinculoPreguntaService");
                service.delete(this.getSelectedPregunta().getNpreguntaid());
                for (Consulta consulta : this.getListaTargetVinculos()) {
                    VinculoPregunta vinculopregunta = new VinculoPregunta();
                    vinculopregunta.setNvinculoid(vinculopreguntaService.getNextPK());
                    vinculopregunta.setNpreguntaid(this.getSelectedPregunta().getNpreguntaid());
                    vinculopregunta.setNconocimientovinc(consulta.getIdconocimiento());
                    vinculopregunta.setNtipoconocimientovinc(consulta.getIdTipoConocimiento());
                    vinculopregunta.setDfechacreacion(new Date());
                    vinculopregunta.setVusuariocreacion(user_savepreg.getVlogin());
                    vinculopreguntaService.saveOrUpdate(vinculopregunta);

                }
            }
            pagina = "/pages/pregunta/lista?faces-redirect=true";

        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
        return pagina;
    }

    public String toEdit() {
        String pagina = null;
        try {
            int situacion;
            this.cleanAttributes();
            PreguntaService service = (PreguntaService) ServiceFinder.findBean("PreguntaService");
            int index = Integer.parseInt((String) JSFUtils.getRequestParameter("index"));
            this.setSelectedPregunta(this.getListaPregunta().get(index));
            this.setEntidad(service.getNomEntidadbyIdEntidad(this.getSelectedPregunta().getNentidadid()));

            this.setListaSourceVinculos(new ArrayList<Consulta>());
            this.setListaTargetVinculos(new ArrayList<Consulta>());
            this.setPickListPregunta(new DualListModel<Consulta>(this.getListaSourceVinculos(), this.getListaTargetVinculos()));

            this.listaTargetVinculosConocimiento = new ArrayList<Consulta>();
            this.listaTargetVinculosBL = new ArrayList<Consulta>();
            this.listaTargetVinculosPR = new ArrayList<Consulta>();
            this.listaTargetVinculosWK = new ArrayList<Consulta>();
            this.listaTargetVinculosCT = new ArrayList<Consulta>();
            this.listaTargetVinculosBP = new ArrayList<Consulta>();
            this.listaTargetVinculosOM = new ArrayList<Consulta>();

            HashMap filters = new HashMap();
            filters.put("ntipoconocimientoid", BigDecimal.valueOf(Long.parseLong("1")));
            filters.put("npreguntaid", this.getSelectedPregunta().getNpreguntaid());
            this.getListaTargetVinculosBL().addAll(service.getConcimientosVinculados(filters));

            filters.put("ntipoconocimientoid", BigDecimal.valueOf(Long.parseLong("2")));
            filters.put("npreguntaid", this.getSelectedPregunta().getNpreguntaid());
            this.getListaTargetVinculosPR().addAll(service.getConcimientosVinculados(filters));

            filters.put("ntipoconocimientoid", BigDecimal.valueOf(Long.parseLong("3")));
            filters.put("npreguntaid", this.getSelectedPregunta().getNpreguntaid());
            this.getListaTargetVinculosWK().addAll(service.getConcimientosVinculados(filters));

            filters.put("ntipoconocimientoid", BigDecimal.valueOf(Long.parseLong("4")));
            filters.put("npreguntaid", this.getSelectedPregunta().getNpreguntaid());
            this.getListaTargetVinculosCT().addAll(service.getConcimientosVinculados(filters));

            filters.put("ntipoconocimientoid", BigDecimal.valueOf(Long.parseLong("5")));
            filters.put("npreguntaid", this.getSelectedPregunta().getNpreguntaid());
            this.getListaTargetVinculosBP().addAll(service.getConcimientosVinculados(filters));

            filters.put("ntipoconocimientoid", BigDecimal.valueOf(Long.parseLong("6")));
            filters.put("npreguntaid", this.getSelectedPregunta().getNpreguntaid());
            this.getListaTargetVinculosOM().addAll(service.getConcimientosVinculados(filters));

            if (this.getListaTargetVinculosBL() == null) {
            } else {
                this.getListaTargetVinculosConocimiento().addAll(this.getListaTargetVinculosBL());
            }
            if (this.getListaTargetVinculosBP() == null) {
            } else {
                this.getListaTargetVinculosConocimiento().addAll(this.getListaTargetVinculosBP());
            }
            if (this.getListaTargetVinculosCT() == null) {
            } else {
                this.getListaTargetVinculosConocimiento().addAll(this.getListaTargetVinculosCT());
            }
            if (this.getListaTargetVinculosOM() == null) {
            } else {
                this.getListaTargetVinculosConocimiento().addAll(this.getListaTargetVinculosOM());
            }
            if (this.getListaTargetVinculosWK() == null) {
            } else {
                this.getListaTargetVinculosConocimiento().addAll(this.getListaTargetVinculosWK());
            }

            situacion = Integer.parseInt(this.getSelectedPregunta().getNsituacionid().toString());

            pagina = "/pages/pregunta/editar?faces-redirect=true";

        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
        return pagina;
    }

    public void toAddLink(ActionEvent event) {
        try {
            this.setIdTipoConocimiento(null);
            this.setListaSourceVinculos(new ArrayList());
            this.setListaTargetVinculos(new ArrayList());
            this.setPickListPregunta(new DualListModel<Consulta>(this.getListaSourceVinculos(), this.getListaTargetVinculos()));
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
    }

    public void onListTipoConocimientoChange(AjaxBehaviorEvent event) {
        try {
            if (event != null) {
                final BigDecimal id = (BigDecimal) ((SelectOneMenu) event.getSource()).getValue();
                this.setIdTipoConocimiento(id);
                if (id != null) {
                    HashMap filters = new HashMap();
                    filters.put("ntipoconocimientoid", id);
                    filters.put("npreguntaid", this.getSelectedPregunta().getNpreguntaid());
                    PreguntaService service = (PreguntaService) ServiceFinder.findBean("PreguntaService");
                    if (this.getSelectedPregunta() != null) {
                        if (id.equals(Constante.BASELEGAL)) {
                            this.setListaTargetVinculosBL(service.getConcimientosVinculados(filters));
                            this.setListaTargetVinculos(this.getListaTargetVinculosBL());
                        } else if (id.equals(Constante.PREGUNTAS)) {
                            this.setListaTargetVinculosPR(service.getConcimientosVinculados(filters));
                            this.setListaTargetVinculos(this.getListaTargetVinculosPR());
                        } else if (id.equals(Constante.WIKI)) {
                            this.setListaTargetVinculosWK(service.getConcimientosVinculados(filters));
                            this.setListaTargetVinculos(this.getListaTargetVinculosWK());
                        } else if (id.equals(Constante.CONTENIDO)) {
                            this.setListaTargetVinculosCT(service.getConcimientosVinculados(filters));
                            this.setListaTargetVinculos(this.getListaTargetVinculosCT());
                        } else if (id.equals(Constante.BUENAPRACTICA)) {
                            this.setListaTargetVinculosBP(service.getConcimientosVinculados(filters));
                            this.setListaTargetVinculos(this.getListaTargetVinculosBP());
                        } else if (id.equals(Constante.OPORTUNIDADMEJORA)) {
                            this.setListaTargetVinculosOM(service.getConcimientosVinculados(filters));
                            this.setListaTargetVinculos(this.getListaTargetVinculosOM());
                        }
                        List<String> ids = new ArrayList<String>();
                        for (Consulta c : this.getListaTargetVinculos()) {
                            ids.add(c.getIdconocimiento().toString());
                        }
                        String filter = StringUtils.join(ids, ',');
//                        if (id.equals(Constante.PREGUNTAS)) {
//                            filter = filter.concat(",").concat(this.getSelectedPregunta().getNpreguntaid().toString());
//                        }
                        filters.put("nconocimientovinc", filter);
                    } else {
                        this.setListaTargetVinculos(new ArrayList<Consulta>());
                    }
                    if (id.equals(Constante.BASELEGAL)) {
                        this.setListaSourceVinculosBL(service.getConcimientosDisponibles(filters));
                        this.setListaSourceVinculos(this.getListaSourceVinculosBL());
                    } else if (id.equals(Constante.PREGUNTAS)) {
                        this.setListaSourceVinculosPR(service.getConcimientosDisponibles(filters));
                        this.setListaSourceVinculos(this.getListaSourceVinculosPR());
                    } else if (id.equals(Constante.WIKI)) {
                        this.setListaSourceVinculosWK(service.getConcimientosDisponibles(filters));
                        this.setListaSourceVinculos(this.getListaSourceVinculosWK());
                    } else if (id.equals(Constante.CONTENIDO)) {
                        this.setListaSourceVinculosCT(service.getConcimientosDisponibles(filters));
                        this.setListaSourceVinculos(this.getListaSourceVinculosCT());
                    } else if (id.equals(Constante.BUENAPRACTICA)) {
                        this.setListaSourceVinculosBP(service.getConcimientosDisponibles(filters));
                        this.setListaSourceVinculos(this.getListaSourceVinculosBP());
                    } else if (id.equals(Constante.OPORTUNIDADMEJORA)) {
                        this.setListaSourceVinculosOM(service.getConcimientosDisponibles(filters));
                        this.setListaSourceVinculos(this.getListaSourceVinculosOM());
                    }
                    this.setPickListPregunta(new DualListModel<Consulta>(this.getListaSourceVinculos(), this.getListaTargetVinculos()));
                }
            }
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
    }
    
    public void onTransferPreguntas(TransferEvent event) {
        int index;
        try {
            if (event != null) {
                BigDecimal id = this.getIdTipoConocimiento();
                if (event.isAdd()) {
                    Collections.sort(this.getListaSourceVinculos(), Consulta.Comparators.ID);
                    for (Consulta ele : (List<Consulta>) event.getItems()) {
                        index = Collections.binarySearch(this.getListaSourceVinculos(), ele, Consulta.Comparators.ID);
                        if (this.getListaTargetVinculos() == null) {
                            this.setListaTargetVinculos(new ArrayList<Consulta>());
                        }
                        this.getListaTargetVinculos().add(this.getListaSourceVinculos().get(index));
                        this.getListaSourceVinculos().remove(index);
                    }
                }
                if (event.isRemove()) {
                    Collections.sort(this.getListaTargetVinculos(), Consulta.Comparators.ID);
                    for (Consulta ele : (List<Consulta>) event.getItems()) {
                        index = Collections.binarySearch(this.getListaTargetVinculos(), ele, Consulta.Comparators.ID);
                        if (this.getListaSourceVinculos() == null) {
                            this.setListaSourceVinculos(new ArrayList<Consulta>());
                        }
                        this.getListaSourceVinculos().add(this.getListaTargetVinculos().get(index));
                        this.getListaTargetVinculos().remove(index);
                    }
                }
                if (id.equals(Constante.BASELEGAL)) {
                    this.setListaSourceVinculosBL(this.getListaSourceVinculos());
                    this.setListaTargetVinculosBL(this.getListaTargetVinculos());
                } else if (id.equals(Constante.PREGUNTAS)) {
                    this.setListaSourceVinculosPR(this.getListaSourceVinculos());
                    this.setListaTargetVinculosPR(this.getListaTargetVinculos());
                } else if (id.equals(Constante.WIKI)) {
                    this.setListaSourceVinculosWK(this.getListaSourceVinculos());
                    this.setListaTargetVinculosWK(this.getListaTargetVinculos());
                } else if (id.equals(Constante.CONTENIDO)) {
                    this.setListaSourceVinculosCT(this.getListaSourceVinculos());
                    this.setListaTargetVinculosCT(this.getListaTargetVinculos());
                } else if (id.equals(Constante.BUENAPRACTICA)) {
                    this.setListaSourceVinculosBP(this.getListaSourceVinculos());
                    this.setListaTargetVinculosBP(this.getListaTargetVinculos());
                } else if (id.equals(Constante.OPORTUNIDADMEJORA)) {
                    this.setListaSourceVinculosOM(this.getListaSourceVinculos());
                    this.setListaTargetVinculosOM(this.getListaTargetVinculos());
                }

                this.listaTargetVinculosConocimiento = new ArrayList<Consulta>();

                if (this.getListaTargetVinculosBL() == null) {
                } else {
                    this.getListaTargetVinculosConocimiento().addAll(this.getListaTargetVinculosBL());
                }
                if (this.getListaTargetVinculosBP() == null) {
                } else {
                    this.getListaTargetVinculosConocimiento().addAll(this.getListaTargetVinculosBP());
                }
                if (this.getListaTargetVinculosCT() == null) {
                } else {
                    this.getListaTargetVinculosConocimiento().addAll(this.getListaTargetVinculosCT());
                }
                if (this.getListaTargetVinculosOM() == null) {
                } else {
                    this.getListaTargetVinculosConocimiento().addAll(this.getListaTargetVinculosOM());
                }
//                if (this.getListaTargetVinculosPR() == null) {
//                } else {
//                    this.getListaTargetVinculosPR().addAll(this.getListaTargetVinculosPR());
//                }
                if (this.getListaTargetVinculosWK() == null) {
                } else {
                    this.getListaTargetVinculosConocimiento().addAll(this.getListaTargetVinculosWK());
                }

            }
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
    }
    
    public String toPost() {
        String pagina = null;
        try {
            int situacion;
            this.cleanAttributes();
            PreguntaService service = (PreguntaService) ServiceFinder.findBean("PreguntaService");
            int index = Integer.parseInt((String) JSFUtils.getRequestParameter("index"));
            this.setSelectedPregunta(this.getListaPregunta().get(index));
            this.setEntidad(service.getNomEntidadbyIdEntidad(this.getSelectedPregunta().getNentidadid()));

            this.setListaSourceVinculos(new ArrayList<Consulta>());
            this.setListaTargetVinculos(new ArrayList<Consulta>());
            this.setPickListPregunta(new DualListModel<Consulta>(this.getListaSourceVinculos(), this.getListaTargetVinculos()));

            this.listaTargetVinculosConocimiento = new ArrayList<Consulta>();
            this.listaTargetVinculosBL = new ArrayList<Consulta>();
            this.listaTargetVinculosPR = new ArrayList<Consulta>();
            this.listaTargetVinculosWK = new ArrayList<Consulta>();
            this.listaTargetVinculosCT = new ArrayList<Consulta>();
            this.listaTargetVinculosBP = new ArrayList<Consulta>();
            this.listaTargetVinculosOM = new ArrayList<Consulta>();

            HashMap filters = new HashMap();
            filters.put("ntipoconocimientoid", BigDecimal.valueOf(Long.parseLong("1")));
            filters.put("npreguntaid", this.getSelectedPregunta().getNpreguntaid());
            this.getListaTargetVinculosBL().addAll(service.getConcimientosVinculados(filters));

            filters.put("ntipoconocimientoid", BigDecimal.valueOf(Long.parseLong("2")));
            filters.put("npreguntaid", this.getSelectedPregunta().getNpreguntaid());
            this.getListaTargetVinculosPR().addAll(service.getConcimientosVinculados(filters));

            filters.put("ntipoconocimientoid", BigDecimal.valueOf(Long.parseLong("3")));
            filters.put("npreguntaid", this.getSelectedPregunta().getNpreguntaid());
            this.getListaTargetVinculosWK().addAll(service.getConcimientosVinculados(filters));

            filters.put("ntipoconocimientoid", BigDecimal.valueOf(Long.parseLong("4")));
            filters.put("npreguntaid", this.getSelectedPregunta().getNpreguntaid());
            this.getListaTargetVinculosCT().addAll(service.getConcimientosVinculados(filters));

            filters.put("ntipoconocimientoid", BigDecimal.valueOf(Long.parseLong("5")));
            filters.put("npreguntaid", this.getSelectedPregunta().getNpreguntaid());
            this.getListaTargetVinculosBP().addAll(service.getConcimientosVinculados(filters));

            filters.put("ntipoconocimientoid", BigDecimal.valueOf(Long.parseLong("6")));
            filters.put("npreguntaid", this.getSelectedPregunta().getNpreguntaid());
            this.getListaTargetVinculosOM().addAll(service.getConcimientosVinculados(filters));

            if (this.getListaTargetVinculosBL() == null) {
            } else {
                this.getListaTargetVinculosConocimiento().addAll(this.getListaTargetVinculosBL());
            }
            if (this.getListaTargetVinculosBP() == null) {
            } else {
                this.getListaTargetVinculosConocimiento().addAll(this.getListaTargetVinculosBP());
            }
            if (this.getListaTargetVinculosCT() == null) {
            } else {
                this.getListaTargetVinculosConocimiento().addAll(this.getListaTargetVinculosCT());
            }
            if (this.getListaTargetVinculosOM() == null) {
            } else {
                this.getListaTargetVinculosConocimiento().addAll(this.getListaTargetVinculosOM());
            }
            if (this.getListaTargetVinculosWK() == null) {
            } else {
                this.getListaTargetVinculosConocimiento().addAll(this.getListaTargetVinculosWK());
            }

            situacion = Integer.parseInt(this.getSelectedPregunta().getNsituacionid().toString());

            pagina = "/pages/pregunta/publicar?faces-redirect=true";

        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
        return pagina;
    }
    
    public void setSelectedRow(ActionEvent event) {
        try {
            if (event != null) {
                int index = Integer.parseInt((String) JSFUtils.getRequestParameter("index"));

                this.setSelectedPregunta(this.getListaPregunta().get(index));

            }
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
    }
    
    public void postProcessXLS(Object document) {
        HSSFWorkbook wb = (HSSFWorkbook) document;
        HSSFSheet sheet = wb.getSheetAt(0);

        //Para los datos
        HSSFCellStyle centerStyle = wb.createCellStyle();
        centerStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        
        HSSFCellStyle centerGrayStyle = wb.createCellStyle();
        centerGrayStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        centerGrayStyle.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
        centerGrayStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
        
        HSSFCellStyle grayBG = wb.createCellStyle();
        grayBG.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
        grayBG.setFillPattern(CellStyle.SOLID_FOREGROUND);
        int i = 1;
        for(Pregunta p : this.getListaPregunta()) {
            HSSFRow row = sheet.getRow(i);
            for (int j = 0; j < row.getPhysicalNumberOfCells(); j++) {
                HSSFCell cell = row.getCell(j);
                if(i % 2 == 0 ) {
                    if(j > 0) {
                        cell.setCellStyle(centerGrayStyle);
                    } else {
                        cell.setCellStyle(grayBG);
                        cell.setCellValue(p.getVasunto());
                    }
                } else {
                    if(j > 0) {
                        cell.setCellStyle(centerStyle);
                    } else {
                        cell.setCellValue(p.getVasunto());
                    }
                }
            }
            i++;
        }
        
        // Para la cabecera
        HSSFRow header = sheet.getRow(0);
        HSSFCellStyle headerStyle = wb.createCellStyle();
        HSSFFont font = wb.createFont();
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        headerStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        headerStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        headerStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
        headerStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        headerStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
        headerStyle.setFont(font);
        
        for (int j = 0; j < header.getPhysicalNumberOfCells(); j++) {
            HSSFCell cell = header.getCell(j);
            cell.setCellStyle(headerStyle);
            sheet.autoSizeColumn(j);
        }
    }
}
