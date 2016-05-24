/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.gob.mef.gescon.web.ui;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.event.PhaseId;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jsoup.Jsoup;
import org.primefaces.component.selectonemenu.SelectOneMenu;
import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.NodeSelectEvent;
import org.primefaces.event.TransferEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.DualListModel;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.TreeNode;
import org.primefaces.model.UploadedFile;
import org.springframework.util.CollectionUtils;
import pe.gob.mef.gescon.common.Constante;
import pe.gob.mef.gescon.common.Parameters;
import pe.gob.mef.gescon.hibernate.domain.Tbaselegal;
import pe.gob.mef.gescon.hibernate.domain.ThistorialId;
import pe.gob.mef.gescon.hibernate.domain.TseccionHistId;
import pe.gob.mef.gescon.hibernate.domain.TvinculoBaselegalId;
import pe.gob.mef.gescon.hibernate.domain.TvinculoHistId;
import pe.gob.mef.gescon.service.ArchivoConocimientoService;
import pe.gob.mef.gescon.service.ArchivoHistorialService;
import pe.gob.mef.gescon.service.ArchivoService;
import pe.gob.mef.gescon.service.AsignacionService;
import pe.gob.mef.gescon.service.BaseLegalHistorialService;
import pe.gob.mef.gescon.service.BaseLegalService;
import pe.gob.mef.gescon.service.CategoriaService;
import pe.gob.mef.gescon.service.ConocimientoService;
import pe.gob.mef.gescon.service.ConsultaService;
import pe.gob.mef.gescon.service.ContenidoService;
import pe.gob.mef.gescon.service.EntidadService;
import pe.gob.mef.gescon.service.HistorialService;
import pe.gob.mef.gescon.service.ImplementacionService;
import pe.gob.mef.gescon.service.PreguntaService;
import pe.gob.mef.gescon.service.RespuestaHistService;
import pe.gob.mef.gescon.service.SeccionHistService;
import pe.gob.mef.gescon.service.SeccionService;
import pe.gob.mef.gescon.service.VinculoBaseLegalService;
import pe.gob.mef.gescon.service.VinculoBaselegalHistorialService;
import pe.gob.mef.gescon.service.VinculoHistService;
import pe.gob.mef.gescon.service.VinculoImplementacionService;
import pe.gob.mef.gescon.service.VinculoPreguntaService;
import pe.gob.mef.gescon.service.VinculoService;
import pe.gob.mef.gescon.service.WikiService;
import pe.gob.mef.gescon.util.GcmFileUtils;
import pe.gob.mef.gescon.util.JSFUtils;
import pe.gob.mef.gescon.util.ServiceFinder;
import pe.gob.mef.gescon.web.bean.Archivo;
import pe.gob.mef.gescon.web.bean.ArchivoConocimiento;
import pe.gob.mef.gescon.web.bean.ArchivoHist;
import pe.gob.mef.gescon.web.bean.Asignacion;
import pe.gob.mef.gescon.web.bean.BaseLegal;
import pe.gob.mef.gescon.web.bean.BaselegalHist;
import pe.gob.mef.gescon.web.bean.Categoria;
import pe.gob.mef.gescon.web.bean.Conocimiento;
import pe.gob.mef.gescon.web.bean.Consulta;
import pe.gob.mef.gescon.web.bean.Entidad;
import pe.gob.mef.gescon.web.bean.Historial;
import pe.gob.mef.gescon.web.bean.Implementacion;
import pe.gob.mef.gescon.web.bean.Pregunta;
import pe.gob.mef.gescon.web.bean.RespuestaHist;
import pe.gob.mef.gescon.web.bean.Seccion;
import pe.gob.mef.gescon.web.bean.SeccionHist;
import pe.gob.mef.gescon.web.bean.User;
import pe.gob.mef.gescon.web.bean.Vinculo;
import pe.gob.mef.gescon.web.bean.VinculoBaselegal;
import pe.gob.mef.gescon.web.bean.VinculoBaselegalHist;
import pe.gob.mef.gescon.web.bean.VinculoHist;
import pe.gob.mef.gescon.web.bean.VinculoImplementacion;
import pe.gob.mef.gescon.web.bean.VinculoPregunta;

/**
 *
 * @author JJacobo
 */
@ManagedBean
@SessionScoped
public class PendienteMB implements Serializable {

    private static final Log log = LogFactory.getLog(PendienteMB.class);
    private final String temppath = "\\\\OSC2018\\gescon\\temp\\";
    private final String path = "ct/";
    private final String pathbl = "bl/";
    private final String pathwk = "wk/";
    private final String pathbp = "bp/";
    private final String pathom = "om/";
    private BigDecimal cat_antigua, cat_nueva;
    private User user;
    private List<Consulta> listaNotificacionesAlerta;
    private String alertaFlag = "false";
    private Asignacion selectedAsignacion;
    private BaseLegal selectedBaseLegal;
    private Boolean chkGobNacional;
    private Boolean chkGobRegional;
    private Boolean chkGobLocal;
    private Boolean chkMancomunidades;
    private DualListModel<BaseLegal> pickList;
    private List<BaseLegal> listaSource;
    private List<BaseLegal> listaTarget;
    private UploadedFile uploadFile;
    private File file;
    private String fMsjModBase; //Mensaje Moderador Base Legal
    private String fMsjUsuCreaBase; //Mensaje Usuario creacion Base Legal
    private Pregunta selectedPregunta;
    private RespuestaHist respuestaHistorial;
    private List<Pregunta> flistaPregunta;
    private List<Asignacion> listaAsignacion;
    private String fSInfEspe; //SI Especialista
    private String fButtonEspe; //Botones Especialista
    private String fButtonMod;// Botones Moderador
    private String fButtonModPub;//Botones Moderador Publicar
    private String fMsjUsu1; //Mensaje 1 Usuario
    private String fMsjUsu2; //Mensaje 2 Usuario
    private String fSInfMod; //SI Moderador
    private String fButton; //Botones UE - ESPE
    private String fButtonUM; //Botones UE - MOD
    private TreeNode tree;
    private Categoria selectedCategoria;
    private BigDecimal entidadId;
    private String entidad;
    private Conocimiento selectedContenido;
    private DualListModel<Consulta> pickListContenido;
    private String tipoContenido;
    private List<ArchivoConocimiento> listaArchivos;
    private ArchivoConocimiento selectedArchivo;
    private BigDecimal idTipoConocimiento;
    private List<Consulta> listaSourceVinculos;
    private List<Consulta> listaTargetVinculos;
    private DualListModel<Consulta> pickListPregunta;
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
    private Conocimiento selectedWiki;
    private Seccion selectedSeccion;
    private String descripcionHtml;
    private String descripcionPlain;
    private String titulo;
    private String detalleHtml;
    private String detallePlain;
    private List<Seccion> listaSeccion;
    private DualListModel<Consulta> pickListWiki;
    private Conocimiento selectedBpractica;
    private DualListModel<Consulta> pickListBpractica;
    private Conocimiento selectedOmejora;
    private String contenidoHtml;
    private String contenidoPlain;
    private StreamedContent content;
    private DualListModel<Consulta> pickListOmejora;
    private String tipoNorma;
    private String numeroNorma;
    private Boolean chkDestacado;
    private List<Consulta> listaDestacados;
    private Consulta selectedDestacado;
    private List<Entidad> listaEntidad;
    private List<Entidad> filteredListaEntidad;
    private Entidad selectedEntidad;
    private BigDecimal analisis;
    private int dias;
    private String motivo;

    /**
     * Creates a new instance of LoginMB
     */
    public PendienteMB() {
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
     * @return the selectedBaseLegal
     */
    public BaseLegal getSelectedBaseLegal() {
        return selectedBaseLegal;
    }

    /**
     * @param selectedBaseLegal the selectedBaseLegal to set
     */
    public void setSelectedBaseLegal(BaseLegal selectedBaseLegal) {
        this.selectedBaseLegal = selectedBaseLegal;
    }

    /**
     * @return the listaTarget
     */
    public List<BaseLegal> getListaTarget() {
        return listaTarget;
    }

    /**
     * @param listaTarget the listaTarget to set
     */
    public void setListaTarget(List<BaseLegal> listaTarget) {
        this.listaTarget = listaTarget;
    }

    /**
     * @return the chkGobNacional
     */
    public Boolean getChkGobNacional() {
        return chkGobNacional;
    }

    /**
     * @param chkGobNacional the chkGobNacional to set
     */
    public void setChkGobNacional(Boolean chkGobNacional) {
        this.chkGobNacional = chkGobNacional;
    }

    /**
     * @return the chkGobRegional
     */
    public Boolean getChkGobRegional() {
        return chkGobRegional;
    }

    /**
     * @param chkGobRegional the chkGobRegional to set
     */
    public void setChkGobRegional(Boolean chkGobRegional) {
        this.chkGobRegional = chkGobRegional;
    }

    /**
     * @return the chkGobLocal
     */
    public Boolean getChkGobLocal() {
        return chkGobLocal;
    }

    /**
     * @param chkGobLocal the chkGobLocal to set
     */
    public void setChkGobLocal(Boolean chkGobLocal) {
        this.chkGobLocal = chkGobLocal;
    }

    /**
     * @return the chkMancomunidades
     */
    public Boolean getChkMancomunidades() {
        return chkMancomunidades;
    }

    /**
     * @param chkMancomunidades the chkMancomunidades to set
     */
    public void setChkMancomunidades(Boolean chkMancomunidades) {
        this.chkMancomunidades = chkMancomunidades;
    }

    /**
     * @return the pickList
     */
    public DualListModel<BaseLegal> getPickList() {
        return pickList;
    }

    /**
     * @param pickList the pickList to set
     */
    public void setPickList(DualListModel<BaseLegal> pickList) {
        this.pickList = pickList;
    }

    /**
     * @return the listaSource
     */
    public List<BaseLegal> getListaSource() {
        return listaSource;
    }

    /**
     * @param listaSource the listaSource to set
     */
    public void setListaSource(List<BaseLegal> listaSource) {
        this.listaSource = listaSource;
    }

    /**
     * @return the uploadFile
     */
    public UploadedFile getUploadFile() {
        return uploadFile;
    }

    /**
     * @param uploadFile the uploadFile to set
     */
    public void setUploadFile(UploadedFile uploadFile) {
        this.uploadFile = uploadFile;
    }

    /**
     * @return the file
     */
    public File getFile() {
        return file;
    }

    /**
     * @param file the file to set
     */
    public void setFile(File file) {
        this.file = file;
    }

    /**
     * @return the fMsjModBase
     */
    public String getfMsjModBase() {
        return fMsjModBase;
    }

    /**
     * @param fMsjModBase the fMsjModBase to set
     */
    public void setfMsjModBase(String fMsjModBase) {
        this.fMsjModBase = fMsjModBase;
    }

    /**
     * @return the fMsjUsuCreaBase
     */
    public String getfMsjUsuCreaBase() {
        return fMsjUsuCreaBase;
    }

    /**
     * @param fMsjUsuCreaBase the fMsjUsuCreaBase to set
     */
    public void setfMsjUsuCreaBase(String fMsjUsuCreaBase) {
        this.fMsjUsuCreaBase = fMsjUsuCreaBase;
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
     * @return the respuestaHistorial
     */
    public RespuestaHist getRespuestaHistorial() {
        return respuestaHistorial;
    }

    /**
     * @param respuestaHistorial the respuestaHistorial to set
     */
    public void setRespuestaHistorial(RespuestaHist respuestaHistorial) {
        this.respuestaHistorial = respuestaHistorial;
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
     * @return the selectedContenido
     */
    public Conocimiento getSelectedContenido() {
        return selectedContenido;
    }

    /**
     * @param selectedContenido the selectedContenido to set
     */
    public void setSelectedContenido(Conocimiento selectedContenido) {
        this.selectedContenido = selectedContenido;
    }

    /**
     * @return the pickListContenido
     */
    public DualListModel<Consulta> getPickListContenido() {
        return pickListContenido;
    }

    /**
     * @param pickListContenido the pickListContenido to set
     */
    public void setPickListContenido(DualListModel<Consulta> pickListContenido) {
        this.pickListContenido = pickListContenido;
    }

    public String getTipoContenido() {
        return tipoContenido;
    }

    public void setTipoContenido(String tipoContenido) {
        this.tipoContenido = tipoContenido;
    }

    /**
     * @return the listaArchivos
     */
    public List<ArchivoConocimiento> getListaArchivos() {
        return listaArchivos;
    }

    /**
     * @param listaArchivos the listaArchivos to set
     */
    public void setListaArchivos(List<ArchivoConocimiento> listaArchivos) {
        this.listaArchivos = listaArchivos;
    }

    public ArchivoConocimiento getSelectedArchivo() {
        return selectedArchivo;
    }

    public void setSelectedArchivo(ArchivoConocimiento selectedArchivo) {
        this.selectedArchivo = selectedArchivo;
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
     * @return the selectedWiki
     */
    public Conocimiento getSelectedWiki() {
        return selectedWiki;
    }

    /**
     * @param selectedWiki the selectedWiki to set
     */
    public void setSelectedWiki(Conocimiento selectedWiki) {
        this.selectedWiki = selectedWiki;
    }

    /**
     * @return the pickListWiki
     */
    public DualListModel<Consulta> getPickListWiki() {
        return pickListWiki;
    }

    /**
     * @return the selectedSeccion
     */
    public Seccion getSelectedSeccion() {
        return selectedSeccion;
    }

    /**
     * @param selectedSeccion the selectedSeccion to set
     */
    public void setSelectedSeccion(Seccion selectedSeccion) {
        this.selectedSeccion = selectedSeccion;
    }

    /**
     * @return the descripcionHtml
     */
    public String getDescripcionHtml() {
        return descripcionHtml;
    }

    /**
     * @param descripcionHtml the descripcionHtml to set
     */
    public void setDescripcionHtml(String descripcionHtml) {
        this.descripcionHtml = descripcionHtml;
    }

    /**
     * @return the descripcionPlain
     */
    public String getDescripcionPlain() {
        return descripcionPlain;
    }

    /**
     * @param descripcionPlain the descripcionPlain to set
     */
    public void setDescripcionPlain(String descripcionPlain) {
        this.descripcionPlain = descripcionPlain;
    }

    /**
     * @return the titulo
     */
    public String getTitulo() {
        return titulo;
    }

    /**
     * @param titulo the titulo to set
     */
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    /**
     * @return the detalleHtml
     */
    public String getDetalleHtml() {
        return detalleHtml;
    }

    /**
     * @param detalleHtml the detalleHtml to set
     */
    public void setDetalleHtml(String detalleHtml) {
        this.detalleHtml = detalleHtml;
    }

    /**
     * @return the detallePlain
     */
    public String getDetallePlain() {
        return detallePlain;
    }

    /**
     * @param detallePlain the detallePlain to set
     */
    public void setDetallePlain(String detallePlain) {
        this.detallePlain = detallePlain;
    }

    /**
     * @return the listaSeccion
     */
    public List<Seccion> getListaSeccion() {
        return listaSeccion;
    }

    /**
     * @param listaSeccion the listaSeccion to set
     */
    public void setListaSeccion(List<Seccion> listaSeccion) {
        this.listaSeccion = listaSeccion;
    }

    /**
     * @param pickListWiki the pickListWiki to set
     */
    public void setPickListWiki(DualListModel<Consulta> pickListWiki) {
        this.pickListWiki = pickListWiki;
    }

    /**
     * @return the selectedBpractica
     */
    public Conocimiento getSelectedBpractica() {
        return selectedBpractica;
    }

    /**
     * @param selectedBpractica the selectedBpractica to set
     */
    public void setSelectedBpractica(Conocimiento selectedBpractica) {
        this.selectedBpractica = selectedBpractica;
    }

    /**
     * @return the pickListBpractica
     */
    public DualListModel<Consulta> getPickListBpractica() {
        return pickListBpractica;
    }

    /**
     * @param pickListBpractica the pickListBpractica to set
     */
    public void setPickListBpractica(DualListModel<Consulta> pickListBpractica) {
        this.pickListBpractica = pickListBpractica;
    }

    /**
     * @return the selectedOmejora
     */
    public Conocimiento getSelectedOmejora() {
        return selectedOmejora;
    }

    /**
     * @param selectedOmejora the selectedOmejora to set
     */
    public void setSelectedOmejora(Conocimiento selectedOmejora) {
        this.selectedOmejora = selectedOmejora;
    }

    /**
     * @return the contenidoHtml
     */
    public String getContenidoHtml() {
        return contenidoHtml;
    }

    /**
     * @param contenidoHtml the contenidoHtml to set
     */
    public void setContenidoHtml(String contenidoHtml) {
        this.contenidoHtml = contenidoHtml;
    }

    /**
     * @return the contenidoPlain
     */
    public String getContenidoPlain() {
        return contenidoPlain;
    }

    /**
     * @param contenidoPlain the contenidoPlain to set
     */
    public void setContenidoPlain(String contenidoPlain) {
        this.contenidoPlain = contenidoPlain;
    }

    /**
     * @return the content
     */
    public StreamedContent getContent() {
        return content;
    }

    /**
     * @param content the content to set
     */
    public void setContent(StreamedContent content) {
        this.content = content;
    }

    /**
     * @return the pickListOmejora
     */
    public DualListModel<Consulta> getPickListOmejora() {
        return pickListOmejora;
    }

    /**
     * @param pickListOmejora the pickListOmejora to set
     */
    public void setPickListOmejora(DualListModel<Consulta> pickListOmejora) {
        this.pickListOmejora = pickListOmejora;
    }

    /**
     * @return the tipoNorma
     */
    public String getTipoNorma() {
        return tipoNorma;
    }

    /**
     * @param tipoNorma the tipoNorma to set
     */
    public void setTipoNorma(String tipoNorma) {
        this.tipoNorma = tipoNorma;
    }

    /**
     * @return the numeroNorma
     */
    public String getNumeroNorma() {
        return numeroNorma;
    }

    /**
     * @param numeroNorma the numeroNorma to set
     */
    public void setNumeroNorma(String numeroNorma) {
        this.numeroNorma = numeroNorma;
    }

    /**
     * @return the chkDestacado
     */
    public Boolean getChkDestacado() {
        return chkDestacado;
    }

    /**
     * @param chkDestacado the chkDestacado to set
     */
    public void setChkDestacado(Boolean chkDestacado) {
        this.chkDestacado = chkDestacado;
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

    /**
     * @return the listaEntidad
     */
    public List<Entidad> getListaEntidad() {
        return listaEntidad;
    }

    /**
     * @param listaEntidad the listaEntidad to set
     */
    public void setListaEntidad(List<Entidad> listaEntidad) {
        this.listaEntidad = listaEntidad;
    }

    /**
     * @return the filteredListaEntidad
     */
    public List<Entidad> getFilteredListaEntidad() {
        return filteredListaEntidad;
    }

    /**
     * @param filteredListaEntidad the filteredListaEntidad to set
     */
    public void setFilteredListaEntidad(List<Entidad> filteredListaEntidad) {
        this.filteredListaEntidad = filteredListaEntidad;
    }

    /**
     * @return the selectedEntidad
     */
    public Entidad getSelectedEntidad() {
        return selectedEntidad;
    }

    /**
     * @param selectedEntidad the selectedEntidad to set
     */
    public void setSelectedEntidad(Entidad selectedEntidad) {
        this.selectedEntidad = selectedEntidad;
        
    }
    
    public BigDecimal getAnalisis() {
        return analisis;
    }

    public void setAnalisis(BigDecimal analisis) {
        this.analisis = analisis;
    }

    public int getDias() {
        return dias;
    }

    public void setDias(int dias) {
        this.dias = dias;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }
    
    public void init() {
        try {
            this.setListaSource(new ArrayList<BaseLegal>());
            this.setListaTarget(new ArrayList<BaseLegal>());
            this.setListaSourceVinculos(new ArrayList<Consulta>());
            this.setListaTargetVinculos(new ArrayList<Consulta>());
        } catch(Exception e) {
            e.getMessage();
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

    public void onNodeSelectPreg(NodeSelectEvent event) {
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

    public void onNodeSelectBl(NodeSelectEvent event) {
        try {
            if (event != null) {
                this.setSelectedCategoria((Categoria) event.getTreeNode().getData());
                this.selectedBaseLegal.setNcategoriaid(this.selectedCategoria.getNcategoriaid());
            }
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
    }

    public void onNodeSelectWk(NodeSelectEvent event) {
        try {
            if (event != null) {
                this.setSelectedCategoria((Categoria) event.getTreeNode().getData());
                this.selectedWiki.setNcategoriaid(this.selectedCategoria.getNcategoriaid());
            }
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
    }

    public void onNodeSelectBp(NodeSelectEvent event) {
        try {
            if (event != null) {
                this.setSelectedCategoria((Categoria) event.getTreeNode().getData());
                this.selectedBpractica.setNcategoriaid(this.selectedCategoria.getNcategoriaid());
            }
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
    }

    public void onNodeSelectCt(NodeSelectEvent event) {
        try {
            if (event != null) {
                this.setSelectedCategoria((Categoria) event.getTreeNode().getData());
                this.selectedContenido.setNcategoriaid(this.selectedCategoria.getNcategoriaid());
            }
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
    }

    public void onNodeSelectOm(NodeSelectEvent event) {
        try {
            if (event != null) {
                this.setSelectedCategoria((Categoria) event.getTreeNode().getData());
                this.selectedOmejora.setNcategoriaid(this.selectedCategoria.getNcategoriaid());
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

    public void deleteOutstandingBL(ActionEvent event) {
        try {
            if (event != null) {
                BaseLegalService service = (BaseLegalService) ServiceFinder.findBean("BaseLegalService");
                BaseLegal baseLegal = service.getBaselegalById(this.getSelectedDestacado().getIdconocimiento());
                if (baseLegal != null) {
                    LoginMB loginMB = (LoginMB) JSFUtils.getSessionAttribute("loginMB");
                    User usuario = loginMB.getUser();
                    baseLegal.setNdestacado(BigDecimal.ZERO);
                    baseLegal.setVusuariomodificacion(usuario.getVlogin());
                    baseLegal.setDfechamodificacion(new Date());
                    service.saveOrUpdate(baseLegal);
                    ConsultaService consultaService = (ConsultaService) ServiceFinder.findBean("ConsultaService");
                    HashMap filter = new HashMap();
                    filter.put("ntipoconocimientoid", Constante.BASELEGAL);
                    this.setListaDestacados(consultaService.getDestacadosByTipoConocimiento(filter));
                }
            }
        } catch (Exception e) {
            e.getMessage();
        }
    }

    public void deleteOutstandingCT(ActionEvent event) {
        try {
            if (event != null) {
                ConocimientoService service = (ConocimientoService) ServiceFinder.findBean("ConocimientoService");
                Conocimiento conocimiento = service.getConocimientoById(this.getSelectedDestacado().getIdconocimiento());
                if (conocimiento != null) {
                    LoginMB loginMB = (LoginMB) JSFUtils.getSessionAttribute("loginMB");
                    User usuario = loginMB.getUser();
                    conocimiento.setNdestacado(BigDecimal.ZERO);
                    conocimiento.setVusuariomodificacion(usuario.getVlogin());
                    conocimiento.setDfechamodificacion(new Date());
                    service.saveOrUpdate(conocimiento);
                    ConsultaService consultaService = (ConsultaService) ServiceFinder.findBean("ConsultaService");
                    HashMap filter = new HashMap();
                    filter.put("ntipoconocimientoid", Constante.CONTENIDO);
                    this.setListaDestacados(consultaService.getDestacadosByTipoConocimiento(filter));
                }
            }
        } catch (Exception e) {
            e.getMessage();
        }
    }

    public void deleteOutstandingBP(ActionEvent event) {
        try {
            if (event != null) {
                ConocimientoService service = (ConocimientoService) ServiceFinder.findBean("BaseLegalService");
                Conocimiento conocimiento = service.getConocimientoById(this.getSelectedDestacado().getIdconocimiento());
                if (conocimiento != null) {
                    LoginMB loginMB = (LoginMB) JSFUtils.getSessionAttribute("loginMB");
                    User user = loginMB.getUser();
                    conocimiento.setNdestacado(BigDecimal.ZERO);
                    conocimiento.setVusuariomodificacion(user.getVlogin());
                    conocimiento.setDfechamodificacion(new Date());
                    service.saveOrUpdate(conocimiento);
                    ConsultaService consultaService = (ConsultaService) ServiceFinder.findBean("ConsultaService");
                    HashMap filter = new HashMap();
                    filter.put("ntipoconocimientoid", Constante.BUENAPRACTICA);
                    this.setListaDestacados(consultaService.getDestacadosByTipoConocimiento(filter));
                }
            }
        } catch (Exception e) {
            e.getMessage();
        }
    }

    public void deleteOutstandingOM(ActionEvent event) {
        try {
            if (event != null) {
                ConocimientoService service = (ConocimientoService) ServiceFinder.findBean("ConocimientoService");
                Conocimiento conocimiento = service.getConocimientoById(this.getSelectedDestacado().getIdconocimiento());
                if (conocimiento != null) {
                    LoginMB loginMB = (LoginMB) JSFUtils.getSessionAttribute("loginMB");
                    User user = loginMB.getUser();
                    conocimiento.setNdestacado(BigDecimal.ZERO);
                    conocimiento.setVusuariomodificacion(user.getVlogin());
                    conocimiento.setDfechamodificacion(new Date());
                    service.saveOrUpdate(conocimiento);
                    ConsultaService consultaService = (ConsultaService) ServiceFinder.findBean("ConsultaService");
                    HashMap filter = new HashMap();
                    filter.put("ntipoconocimientoid", Constante.OPORTUNIDADMEJORA);
                    this.setListaDestacados(consultaService.getDestacadosByTipoConocimiento(filter));
                }
            }
        } catch (Exception e) {
            e.getMessage();
        }
    }

    public void deleteOutstandingWK(ActionEvent event) {
        try {
            if (event != null) {
                ConocimientoService service = (ConocimientoService) ServiceFinder.findBean("ConocimientoService");
                Conocimiento conocimiento = service.getConocimientoById(this.getSelectedDestacado().getIdconocimiento());
                if (conocimiento != null) {
                    LoginMB loginMB = (LoginMB) JSFUtils.getSessionAttribute("loginMB");
                    User user = loginMB.getUser();
                    conocimiento.setNdestacado(BigDecimal.ZERO);
                    conocimiento.setVusuariomodificacion(user.getVlogin());
                    conocimiento.setDfechamodificacion(new Date());
                    service.saveOrUpdate(conocimiento);
                    ConsultaService consultaService = (ConsultaService) ServiceFinder.findBean("ConsultaService");
                    HashMap filter = new HashMap();
                    filter.put("ntipoconocimientoid", Constante.WIKI);
                    this.setListaDestacados(consultaService.getDestacadosByTipoConocimiento(filter));
                }
            }
        } catch (Exception e) {
            e.getMessage();
        }
    }

    public void deleteOutstandingPR(ActionEvent event) {
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

    public String toEditPendiente(int caso) {
        String pagina = null;
        try {
            this.setAlertaFlag("false");

            LoginMB mb = (LoginMB) JSFUtils.getSessionAttribute("loginMB");
            PreguntaService service = (PreguntaService) ServiceFinder.findBean("PreguntaService");
            AsignacionService serviceasig = (AsignacionService) ServiceFinder.findBean("AsignacionService");

            int perfil_actual = Integer.parseInt(service.obtenerPerfilxUsuario(mb.getUser().getNusuarioid()).toString());
            int user_actual = Integer.parseInt(mb.getUser().getNusuarioid().toString());
            int user_creacion;
            int index = Integer.parseInt((String) JSFUtils.getRequestParameter("index"));

            if (caso == 1) {
                if (CollectionUtils.isEmpty(mb.getFilteredListaNotificacionesAsignadas())) {
                    mb.setSelectedNotification(mb.getListaNotificacionesAsignadas().get(index));
                } else {
                    mb.setSelectedNotification(mb.getFilteredListaNotificacionesAsignadas().get(index));
                }
            } else {
                if (caso == 2) {
                    if (CollectionUtils.isEmpty(mb.getFilteredListaNotificacionesRecibidas())) {
                        mb.setSelectedNotification(mb.getListaNotificacionesRecibidas().get(index));
                    } else {
                        mb.setSelectedNotification(mb.getFilteredListaNotificacionesRecibidas().get(index));
                    }
                } else {
                    if (CollectionUtils.isEmpty(mb.getFilteredListaNotificacionesAlerta())) {
                        mb.setSelectedNotification(mb.getListaNotificacionesAlerta().get(index));
                    } else {
                        mb.setSelectedNotification(mb.getFilteredListaNotificacionesAlerta().get(index));
                    }
                }
            }

            Integer tipo = mb.getSelectedNotification().getIdTipoConocimiento().intValue();
            Integer id = mb.getSelectedNotification().getIdconocimiento().intValue();
            switch (tipo) {
                case 1: {
                    int situacion;
                    BaseLegalService servicebl = (BaseLegalService) ServiceFinder.findBean("BaseLegalService");
                    this.setSelectedBaseLegal(servicebl.getBaselegalById(BigDecimal.valueOf(id)));
                    cat_antigua = this.getSelectedBaseLegal().getNcategoriaid();
                    ArchivoService aservice = (ArchivoService) ServiceFinder.findBean("ArchivoService");
                    this.selectedBaseLegal.setArchivo(aservice.getArchivoByBaseLegal(this.getSelectedBaseLegal()));
                    index = this.getSelectedBaseLegal().getVnumero().indexOf("-");
                    this.setTipoNorma(this.getSelectedBaseLegal().getVnumero().substring(0, index).trim());
                    this.setNumeroNorma(this.getSelectedBaseLegal().getVnumero().substring(index + 1).trim());
                    this.setChkGobNacional(this.getSelectedBaseLegal().getNgobnacional().equals(BigDecimal.ONE));
                    this.setChkGobRegional(this.getSelectedBaseLegal().getNgobregional().equals(BigDecimal.ONE));
                    this.setChkGobLocal(this.getSelectedBaseLegal().getNgoblocal().equals(BigDecimal.ONE));
                    this.setChkMancomunidades(this.getSelectedBaseLegal().getNmancomunidades().equals(BigDecimal.ONE));
                    this.setChkDestacado(BigDecimal.ONE.equals(this.getSelectedBaseLegal().getNdestacado()));
                    CategoriaService categoriaService = (CategoriaService) ServiceFinder.findBean("CategoriaService");
                    this.setSelectedCategoria(categoriaService.getCategoriaById(this.getSelectedBaseLegal().getNcategoriaid()));
                    this.setListaSource(servicebl.getTbaselegalesNotLinkedById(this.getSelectedBaseLegal().getNbaselegalid()));
                    this.setListaTarget(servicebl.getTbaselegalesLinkedById(this.getSelectedBaseLegal().getNbaselegalid()));
                    this.setPickList(new DualListModel<>(this.getListaSource(), this.getListaTarget()));
                    setListaAsignacion(servicebl.obtenerBaseLegalxAsig(this.getSelectedBaseLegal().getNbaselegalid(), mb.getUser().getNusuarioid(), Constante.BASELEGAL));
                    setSelectedAsignacion(getListaAsignacion().get(0));

                    user_creacion = Integer.parseInt(serviceasig.getUserCreacionByBaseLegal(this.getSelectedBaseLegal().getNbaselegalid()).toString());

                    if (user_creacion == user_actual) {
                        this.setfButtonEspe("true");
                        this.setfButton("false");
                        this.setfButtonUM("false");
                        this.setfButtonMod("false");
                        this.setfButtonModPub("false");
                    } else {
                        if (perfil_actual == Constante.MODERADOR) {
                            this.setfButtonEspe("false");
                            this.setfButton("false");
                            this.setfButtonUM("false");
                            this.setfButtonMod("true");
                            this.setfButtonModPub("false");
                        }
                    }

                    if (StringUtils.isBlank(this.getSelectedBaseLegal().getVmsjmoderador())) {
                        this.fSInfMod = "false";
                    } else {
                        this.fSInfMod = "true";
                    }
                    if (StringUtils.isBlank(this.getSelectedBaseLegal().getVmsjusuariocreacion())) {
                        this.fMsjUsu1 = "false";
                    } else {
                        this.fMsjUsu1 = "true";
                    }

                    this.getSelectedAsignacion().setDfecharecepcion(new Date());
                    serviceasig.saveOrUpdate(this.getSelectedAsignacion());

                    pagina = "/pages/Pendientes/moderarBaseLegal?faces-redirect=true";

                    break;
                }
                case 2: {
                    int situacion;
                    this.setSelectedPregunta(service.getPreguntaById(BigDecimal.valueOf(id)));
                    cat_antigua = this.getSelectedPregunta().getNcategoriaid();
                    this.setEntidad(service.getNomEntidadbyIdEntidad(this.getSelectedPregunta().getNentidadid()));
                    this.setChkDestacado(BigDecimal.ONE.equals(this.getSelectedPregunta().getNdestacado()));
                    setListaAsignacion(service.obtenerPreguntaxAsig(this.getSelectedPregunta().getNpreguntaid(), mb.getUser().getNusuarioid(), Constante.PREGUNTAS));
                    setFlistaPregunta(service.obtenerPreguntas(this.getSelectedPregunta().getNpreguntaid(), mb.getUser().getNusuarioid(), Constante.PREGUNTAS));

                    this.setListaSourceVinculos(new ArrayList<Consulta>());
                    this.setListaTargetVinculos(new ArrayList<Consulta>());
                    this.setPickListPregunta(new DualListModel<>(this.getListaSourceVinculos(), this.getListaTargetVinculos()));

                    HashMap filters = new HashMap();
                    filters.put("ntipoconocimientoid", Constante.BASELEGAL);
                    filters.put("npreguntaid", this.getSelectedPregunta().getNpreguntaid());
                    this.setListaTargetVinculosBL(service.getConcimientosVinculados(filters));

                    filters.put("ntipoconocimientoid", Constante.PREGUNTAS);
                    filters.put("npreguntaid", this.getSelectedPregunta().getNpreguntaid());
                    this.setListaTargetVinculosPR(service.getConcimientosVinculados(filters));

                    filters.put("ntipoconocimientoid", Constante.WIKI);
                    filters.put("npreguntaid", this.getSelectedPregunta().getNpreguntaid());
                    this.setListaTargetVinculosWK(service.getConcimientosVinculados(filters));

                    filters.put("ntipoconocimientoid", Constante.CONTENIDO);
                    filters.put("npreguntaid", this.getSelectedPregunta().getNpreguntaid());
                    this.setListaTargetVinculosCT(service.getConcimientosVinculados(filters));

                    filters.put("ntipoconocimientoid", Constante.BUENAPRACTICA);
                    filters.put("npreguntaid", this.getSelectedPregunta().getNpreguntaid());
                    this.setListaTargetVinculosBP(service.getConcimientosVinculados(filters));

                    filters.put("ntipoconocimientoid", Constante.OPORTUNIDADMEJORA);
                    filters.put("npreguntaid", this.getSelectedPregunta().getNpreguntaid());
                    this.setListaTargetVinculosOM(service.getConcimientosVinculados(filters));

                    this.setListaTargetVinculosConocimiento(new ArrayList<Consulta>());
                    
                    if (!CollectionUtils.isEmpty(this.getListaTargetVinculosBL())) {
                        this.getListaTargetVinculosConocimiento().addAll(this.getListaTargetVinculosBL());
                    }
                    if (!CollectionUtils.isEmpty(this.getListaTargetVinculosBP())) {
                        this.getListaTargetVinculosConocimiento().addAll(this.getListaTargetVinculosBP());
                    }
                    if (!CollectionUtils.isEmpty(this.getListaTargetVinculosCT())) {
                        this.getListaTargetVinculosConocimiento().addAll(this.getListaTargetVinculosCT());
                    }
                    if (!CollectionUtils.isEmpty(this.getListaTargetVinculosOM())) {
                        this.getListaTargetVinculosConocimiento().addAll(this.getListaTargetVinculosOM());
                    }
                    if (!CollectionUtils.isEmpty(this.getListaTargetVinculosWK())) {
                        this.getListaTargetVinculosConocimiento().addAll(this.getListaTargetVinculosWK());
                    }

                    situacion = Integer.parseInt(this.getSelectedPregunta().getNsituacionid().toString());

                    if (getListaAsignacion().isEmpty() || getFlistaPregunta().isEmpty()) {
                        this.setfButton("false");
                        this.setfButtonUM("false");
                        this.setfButtonEspe("false");
                        this.setfButtonMod("false");
                        this.setfButtonModPub("false");
                    } else {
                        setSelectedAsignacion(getListaAsignacion().get(0));

                        getSelectedAsignacion().setDfecharecepcion(new Date());
                        serviceasig.saveOrUpdate(getSelectedAsignacion());
                        perfil_actual = Integer.parseInt(service.obtenerPerfilxUsuario(mb.getUser().getNusuarioid()).toString());
                        if (perfil_actual == Constante.ESPECIALISTA) {
                            this.setfButtonEspe("true");
                            this.setfButton("false");
                            this.setfButtonUM("false");
                            this.setfButtonMod("false");
                            this.setfButtonModPub("false");
                        } else {
                            if (perfil_actual == Constante.MODERADOR) {
                                this.setfButtonEspe("false");
                                this.setfButton("false");
                                this.setfButtonUM("false");
                                if (StringUtils.isBlank(this.getSelectedPregunta().getVrespuesta())) {
                                    this.setfButtonMod("true");
                                    this.setfButtonModPub("false");
                                } else {
                                    this.setfButtonMod("false");
                                    this.setfButtonModPub("true");
                                }
                            } else {
                                if (situacion == 1) {
                                    if (StringUtils.isBlank(this.getSelectedPregunta().getVmsjmoderador())) {
                                        this.setfButtonUM("false");
                                    } else {
                                        this.setfButtonUM("true");
                                    }
                                } else {
                                    if (StringUtils.isBlank(this.getSelectedPregunta().getVmsjespecialista())) {
                                        this.setfButton("false");
                                    } else {
                                        this.setfButton("true");
                                    }
                                }
                                this.setfButtonEspe("false");
                                this.setfButtonMod("false");
                                this.setfButtonModPub("false");
                            }
                        }
                    }
                    if (situacion == 1) {
                        if (StringUtils.isBlank(this.getSelectedPregunta().getVmsjmoderador())) {
                            this.fSInfMod = "false";
                        } else {
                            this.fSInfMod = "true";
                        }
                        if (StringUtils.isBlank(this.getSelectedPregunta().getVmsjusuario1())) {
                            this.fMsjUsu1 = "false";
                        } else {
                            this.fMsjUsu1 = "true";
                        }
                        this.fSInfEspe = "false";
                        this.fMsjUsu2 = "false";
                    }
                    if (situacion == 2 || situacion == 3 || situacion == 4) {
                        if (StringUtils.isBlank(this.getSelectedPregunta().getVmsjespecialista())) {
                            this.fSInfEspe = "false";
                        } else {
                            this.fSInfEspe = "true";
                        }
                        if (StringUtils.isBlank(this.getSelectedPregunta().getVmsjusuario2())) {
                            this.fMsjUsu2 = "false";
                        } else {
                            this.fMsjUsu2 = "true";
                        }
                        this.fSInfMod = "false";
                        this.fMsjUsu1 = "false";
                    }

                    this.getSelectedAsignacion().setDfecharecepcion(new Date());
                    serviceasig.saveOrUpdate(this.getSelectedAsignacion());

                    if (situacion == 1) {
                        pagina = "/pages/Pendientes/moderarPregunta?faces-redirect=true";
                    }

                    if (situacion == 2 || situacion == 3) {
                        pagina = "/pages/Pendientes/responderPregunta?faces-redirect=true";
                    }

                    if (situacion == 5) {
                        pagina = "/pages/Pendientes/publicarPregunta?faces-redirect=true";
                    }

                    break;
                }
                case 3: {
                    int situacion;
                    WikiService servicewk = (WikiService) ServiceFinder.findBean("WikiService");
                    this.setSelectedWiki(servicewk.getWikiById(BigDecimal.valueOf(tipo), BigDecimal.valueOf(id)));
                    cat_antigua = this.getSelectedWiki().getNcategoriaid();
                    CategoriaService categoriaService = (CategoriaService) ServiceFinder.findBean("CategoriaService");
                    this.setSelectedCategoria(categoriaService.getCategoriaById(this.getSelectedWiki().getNcategoriaid()));
                    this.setChkDestacado(BigDecimal.ONE.equals(this.getSelectedWiki().getNdestacado()));
                    this.setDescripcionHtml(GcmFileUtils.readStringFromFileServer(this.getSelectedWiki().getVruta(), "html.txt"));
                    SeccionService seccionService = (SeccionService) ServiceFinder.findBean("SeccionService");
                    this.setListaSeccion(seccionService.getSeccionesByConocimiento(this.getSelectedWiki().getNconocimientoid()));
                    if (org.apache.commons.collections.CollectionUtils.isNotEmpty(this.getListaSeccion())) {
                        for (Seccion seccion : this.getListaSeccion()) {
                            seccion.setDetalleHtml(GcmFileUtils.readStringFromFileServer(seccion.getVruta(), "html.txt"));
                        }
                    }
                    setListaAsignacion(servicewk.obtenerWikixAsig(this.getSelectedWiki().getNconocimientoid(), mb.getUser().getNusuarioid(), Constante.WIKI));
                    setSelectedAsignacion(getListaAsignacion().get(0));

                    this.setListaSourceVinculos(new ArrayList<Consulta>());
                    this.setListaTargetVinculos(new ArrayList<Consulta>());
                    this.setPickListWiki(new DualListModel<>(this.getListaSourceVinculos(), this.getListaTargetVinculos()));

                    this.listaTargetVinculosBL = new ArrayList<>();
                    this.listaTargetVinculosPR = new ArrayList<>();
                    this.listaTargetVinculosWK = new ArrayList<>();
                    this.listaTargetVinculosCT = new ArrayList<>();
                    this.listaTargetVinculosBP = new ArrayList<>();
                    this.listaTargetVinculosOM = new ArrayList<>();

                    ConocimientoService conocimientoService = (ConocimientoService) ServiceFinder.findBean("ConocimientoService");
                    HashMap map = new HashMap();
                    map.put("nconocimientoid", this.getSelectedWiki().getNconocimientoid().toString());
                    map.put("flag", true);
                    map.put("ntipoconocimientoid", Constante.BASELEGAL.toString());
                    this.setListaTargetVinculosBL(conocimientoService.getConcimientosVinculados(map));
                    map.put("ntipoconocimientoid", Constante.PREGUNTAS.toString());
                    this.setListaTargetVinculosPR(conocimientoService.getConcimientosVinculados(map));
                    map.put("ntipoconocimientoid", Constante.BUENAPRACTICA.toString());
                    this.setListaTargetVinculosBP(conocimientoService.getConcimientosVinculados(map));
                    map.put("ntipoconocimientoid", Constante.CONTENIDO.toString());
                    this.setListaTargetVinculosCT(conocimientoService.getConcimientosVinculados(map));
                    map.put("ntipoconocimientoid", Constante.OPORTUNIDADMEJORA.toString());
                    this.setListaTargetVinculosOM(conocimientoService.getConcimientosVinculados(map));
                    map.put("ntipoconocimientoid", Constante.WIKI.toString());
                    situacion = Integer.parseInt(this.getSelectedWiki().getNsituacionid().toString());

                    user_creacion = Integer.parseInt(serviceasig.getUserCreacionByContenido(this.getSelectedWiki().getNtipoconocimientoid(), this.getSelectedWiki().getNconocimientoid()).toString());

                    if (user_creacion == user_actual) {
                        this.setfButtonEspe("true");
                        this.setfButton("false");
                        this.setfButtonUM("false");
                        this.setfButtonMod("false");
                        this.setfButtonModPub("false");
                    } else {
                        if (perfil_actual == Constante.MODERADOR) {
                            this.setfButtonEspe("false");
                            this.setfButton("false");
                            this.setfButtonUM("false");
                            this.setfButtonMod("true");
                            this.setfButtonModPub("false");
                        }
                    }

                    if (StringUtils.isBlank(this.getSelectedWiki().getVmsjsolicita())) {
                        this.fSInfMod = "false";
                    } else {
                        this.fSInfMod = "true";
                    }
                    if (StringUtils.isBlank(this.getSelectedWiki().getVmsjrespuesta())) {
                        this.fMsjUsu1 = "false";
                    } else {
                        this.fMsjUsu1 = "true";
                    }

                    this.getSelectedAsignacion().setDfecharecepcion(new Date());
                    serviceasig.saveOrUpdate(this.getSelectedAsignacion());

                    if (situacion == 1) {
                        pagina = "/pages/Pendientes/moderarWiki?faces-redirect=true";
                    }
                    break;
                }
                case 4: {
                    int situacion;
                    ContenidoService servicect = (ContenidoService) ServiceFinder.findBean("ContenidoService");
                    this.setSelectedContenido(servicect.getContenidoById(BigDecimal.valueOf(tipo), BigDecimal.valueOf(id)));
                    cat_antigua = this.getSelectedContenido().getNcategoriaid();
                    CategoriaService categoriaService = (CategoriaService) ServiceFinder.findBean("CategoriaService");
                    this.setSelectedCategoria(categoriaService.getCategoriaById(this.getSelectedContenido().getNcategoriaid()));
                    this.setChkDestacado(BigDecimal.ONE.equals(this.getSelectedContenido().getNdestacado()));
                    this.setContenidoHtml(GcmFileUtils.readStringFromFileServer(this.getSelectedContenido().getVruta(), "html.txt"));
                    ArchivoConocimientoService archivoservice = (ArchivoConocimientoService) ServiceFinder.findBean("ArchivoConocimientoService");
                    this.setListaArchivos(archivoservice.getArchivosByConocimiento(this.getSelectedContenido().getNconocimientoid()));
                    setListaAsignacion(servicect.obtenerContenidoxAsig(this.getSelectedContenido().getNconocimientoid(), mb.getUser().getNusuarioid(), Constante.CONTENIDO));
                    setSelectedAsignacion(getListaAsignacion().get(0));

                    this.setListaSourceVinculos(new ArrayList<Consulta>());
                    this.setListaTargetVinculos(new ArrayList<Consulta>());
                    this.setPickListContenido(new DualListModel<>(this.getListaSourceVinculos(), this.getListaTargetVinculos()));

                    this.listaTargetVinculosBL = new ArrayList<>();
                    this.listaTargetVinculosPR = new ArrayList<>();
                    this.listaTargetVinculosWK = new ArrayList<>();
                    this.listaTargetVinculosCT = new ArrayList<>();
                    this.listaTargetVinculosBP = new ArrayList<>();
                    this.listaTargetVinculosOM = new ArrayList<>();

                    HashMap filters = new HashMap();
                    filters.put("ntipoconocimientoid", BigDecimal.valueOf(Long.parseLong("1")));
                    filters.put("nconocimientoid", this.getSelectedContenido().getNconocimientoid());
                    this.getListaTargetVinculosBL().addAll(servicect.getConcimientosVinculados(filters));

                    filters.put("ntipoconocimientoid", BigDecimal.valueOf(Long.parseLong("2")));
                    filters.put("nconocimientoid", this.getSelectedContenido().getNconocimientoid());
                    this.getListaTargetVinculosPR().addAll(servicect.getConcimientosVinculados(filters));

                    filters.put("ntipoconocimientoid", BigDecimal.valueOf(Long.parseLong("3")));
                    filters.put("nconocimientoid", this.getSelectedContenido().getNconocimientoid());
                    this.getListaTargetVinculosWK().addAll(servicect.getConcimientosVinculados(filters));

                    filters.put("ntipoconocimientoid", BigDecimal.valueOf(Long.parseLong("4")));
                    filters.put("nconocimientoid", this.getSelectedContenido().getNconocimientoid());
                    this.getListaTargetVinculosCT().addAll(servicect.getConcimientosVinculados(filters));

                    filters.put("ntipoconocimientoid", BigDecimal.valueOf(Long.parseLong("5")));
                    filters.put("nconocimientoid", this.getSelectedContenido().getNconocimientoid());
                    this.getListaTargetVinculosBP().addAll(servicect.getConcimientosVinculados(filters));

                    filters.put("ntipoconocimientoid", BigDecimal.valueOf(Long.parseLong("6")));
                    filters.put("nconocimientoid", this.getSelectedContenido().getNconocimientoid());
                    this.getListaTargetVinculosOM().addAll(servicect.getConcimientosVinculados(filters));
                    situacion = Integer.parseInt(this.getSelectedContenido().getNsituacionid().toString());
                    user_creacion = Integer.parseInt(serviceasig.getUserCreacionByContenido(this.getSelectedContenido().getNtipoconocimientoid(), this.getSelectedContenido().getNconocimientoid()).toString());

                    if (user_creacion == user_actual) {
                        this.setfButtonEspe("true");
                        this.setfButton("false");
                        this.setfButtonUM("false");
                        this.setfButtonMod("false");
                        this.setfButtonModPub("false");
                    } else {
                        if (perfil_actual == Constante.MODERADOR) {
                            this.setfButtonEspe("false");
                            this.setfButton("false");
                            this.setfButtonUM("false");
                            this.setfButtonMod("true");
                            this.setfButtonModPub("false");
                        }
                    }

                    if (StringUtils.isBlank(this.getSelectedContenido().getVmsjsolicita())) {
                        this.fSInfMod = "false";
                    } else {
                        this.fSInfMod = "true";
                    }
                    if (StringUtils.isBlank(this.getSelectedContenido().getVmsjrespuesta())) {
                        this.fMsjUsu1 = "false";
                    } else {
                        this.fMsjUsu1 = "true";
                    }

                    this.getSelectedAsignacion().setDfecharecepcion(new Date());
                    serviceasig.saveOrUpdate(this.getSelectedAsignacion());

                    if (situacion == 1) {
                        pagina = "/pages/Pendientes/moderarContenido?faces-redirect=true";
                    }
                    break;
                }
                case 5: {
                    int situacion;
                    ConocimientoService servicebp = (ConocimientoService) ServiceFinder.findBean("ConocimientoService");
                    this.setSelectedBpractica(servicebp.getBpracticaById(BigDecimal.valueOf(tipo), BigDecimal.valueOf(id)));
                    cat_antigua = this.getSelectedBpractica().getNcategoriaid();
                    CategoriaService categoriaService = (CategoriaService) ServiceFinder.findBean("CategoriaService");
                    this.setSelectedCategoria(categoriaService.getCategoriaById(this.getSelectedBpractica().getNcategoriaid()));
                    this.setChkDestacado(BigDecimal.ONE.equals(this.getSelectedBpractica().getNdestacado()));
                    this.setDescripcionHtml(GcmFileUtils.readStringFromFileServer(this.getSelectedBpractica().getVruta(), "html.txt"));
                    SeccionService seccionService = (SeccionService) ServiceFinder.findBean("SeccionService");
                    this.setListaSeccion(seccionService.getSeccionesByConocimiento(this.getSelectedBpractica().getNconocimientoid()));
                    if (org.apache.commons.collections.CollectionUtils.isNotEmpty(this.getListaSeccion())) {
                        for (Seccion seccion : this.getListaSeccion()) {
                            seccion.setDetalleHtml(GcmFileUtils.readStringFromFileServer(seccion.getVruta(), "html.txt"));
                        }
                    }
                    setListaAsignacion(servicebp.obtenerBpracticaxAsig(this.getSelectedBpractica().getNconocimientoid(), mb.getUser().getNusuarioid(), Constante.BUENAPRACTICA));
                    setSelectedAsignacion(getListaAsignacion().get(0));

                    this.setListaSourceVinculos(new ArrayList<Consulta>());
                    this.setListaTargetVinculos(new ArrayList<Consulta>());
                    this.setPickListBpractica(new DualListModel<>(this.getListaSourceVinculos(), this.getListaTargetVinculos()));

                    this.listaTargetVinculosBL = new ArrayList<>();
                    this.listaTargetVinculosPR = new ArrayList<>();
                    this.listaTargetVinculosWK = new ArrayList<>();
                    this.listaTargetVinculosCT = new ArrayList<>();
                    this.listaTargetVinculosBP = new ArrayList<>();
                    this.listaTargetVinculosOM = new ArrayList<>();

                    ConocimientoService conocimientoService = (ConocimientoService) ServiceFinder.findBean("ConocimientoService");
                    HashMap map = new HashMap();
                    map.put("nconocimientoid", this.getSelectedBpractica().getNconocimientoid().toString());
                    map.put("flag", true);
                    map.put("ntipoconocimientoid", Constante.BASELEGAL.toString());
                    this.setListaTargetVinculosBL(conocimientoService.getConcimientosVinculados(map));
                    map.put("ntipoconocimientoid", Constante.PREGUNTAS.toString());
                    this.setListaTargetVinculosPR(conocimientoService.getConcimientosVinculados(map));
                    map.put("ntipoconocimientoid", Constante.BUENAPRACTICA.toString());
                    this.setListaTargetVinculosBP(conocimientoService.getConcimientosVinculados(map));
                    map.put("ntipoconocimientoid", Constante.CONTENIDO.toString());
                    this.setListaTargetVinculosCT(conocimientoService.getConcimientosVinculados(map));
                    map.put("ntipoconocimientoid", Constante.OPORTUNIDADMEJORA.toString());
                    this.setListaTargetVinculosOM(conocimientoService.getConcimientosVinculados(map));
                    map.put("ntipoconocimientoid", Constante.WIKI.toString());
                    situacion = Integer.parseInt(this.getSelectedBpractica().getNsituacionid().toString());

                    user_creacion = Integer.parseInt(serviceasig.getUserCreacionByContenido(this.getSelectedBpractica().getNtipoconocimientoid(), this.getSelectedBpractica().getNconocimientoid()).toString());

                    if (user_creacion == user_actual) {
                        this.setfButtonEspe("true");
                        this.setfButton("false");
                        this.setfButtonUM("false");
                        this.setfButtonMod("false");
                        this.setfButtonModPub("false");
                    } else {
                        if (perfil_actual == Constante.MODERADOR) {
                            this.setfButtonEspe("false");
                            this.setfButton("false");
                            this.setfButtonUM("false");
                            this.setfButtonMod("true");
                            this.setfButtonModPub("false");
                        }
                    }

                    if (StringUtils.isBlank(this.getSelectedBpractica().getVmsjsolicita())) {
                        this.fSInfMod = "false";
                    } else {
                        this.fSInfMod = "true";
                    }
                    if (StringUtils.isBlank(this.getSelectedBpractica().getVmsjrespuesta())) {
                        this.fMsjUsu1 = "false";
                    } else {
                        this.fMsjUsu1 = "true";
                    }

                    this.getSelectedAsignacion().setDfecharecepcion(new Date());
                    serviceasig.saveOrUpdate(this.getSelectedAsignacion());

                    if (situacion == 1) {
                        pagina = "/pages/Pendientes/moderarBpracticas?faces-redirect=true";
                    }
                    break;
                }
                case 6: {
                    int situacion;
                    ConocimientoService servicebp = (ConocimientoService) ServiceFinder.findBean("ConocimientoService");
                    this.setSelectedOmejora(servicebp.getOmejoraById(BigDecimal.valueOf(tipo), BigDecimal.valueOf(id)));
                    cat_antigua = this.getSelectedOmejora().getNcategoriaid();
                    CategoriaService categoriaService = (CategoriaService) ServiceFinder.findBean("CategoriaService");
                    this.setSelectedCategoria(categoriaService.getCategoriaById(this.getSelectedOmejora().getNcategoriaid()));
                    this.setChkDestacado(BigDecimal.ONE.equals(this.getSelectedOmejora().getNdestacado()));
                    this.setContenidoHtml(GcmFileUtils.readStringFromFileServer(this.getSelectedOmejora().getVruta(), "html.txt"));
                    SeccionService seccionService = (SeccionService) ServiceFinder.findBean("SeccionService");
                    this.setListaSeccion(seccionService.getSeccionesByConocimiento(this.getSelectedOmejora().getNconocimientoid()));
                    if (org.apache.commons.collections.CollectionUtils.isNotEmpty(this.getListaSeccion())) {
                        for (Seccion seccion : this.getListaSeccion()) {
                            seccion.setDetalleHtml(GcmFileUtils.readStringFromFileServer(seccion.getVruta(), "html.txt"));
                        }
                    }
                    setListaAsignacion(servicebp.obtenerOmejoraxAsig(this.getSelectedOmejora().getNconocimientoid(), mb.getUser().getNusuarioid(), Constante.OPORTUNIDADMEJORA));
                    setSelectedAsignacion(getListaAsignacion().get(0));

                    this.setListaSourceVinculos(new ArrayList<Consulta>());
                    this.setListaTargetVinculos(new ArrayList<Consulta>());
                    this.setPickListOmejora(new DualListModel<>(this.getListaSourceVinculos(), this.getListaTargetVinculos()));

                    this.listaTargetVinculosBL = new ArrayList<>();
                    this.listaTargetVinculosPR = new ArrayList<>();
                    this.listaTargetVinculosWK = new ArrayList<>();
                    this.listaTargetVinculosCT = new ArrayList<>();
                    this.listaTargetVinculosBP = new ArrayList<>();
                    this.listaTargetVinculosOM = new ArrayList<>();

                    ConocimientoService conocimientoService = (ConocimientoService) ServiceFinder.findBean("ConocimientoService");
                    HashMap map = new HashMap();
                    map.put("nconocimientoid", this.getSelectedOmejora().getNconocimientoid().toString());
                    map.put("flag", true);
                    map.put("ntipoconocimientoid", Constante.BASELEGAL.toString());
                    this.setListaTargetVinculosBL(conocimientoService.getConcimientosVinculados(map));
                    map.put("ntipoconocimientoid", Constante.PREGUNTAS.toString());
                    this.setListaTargetVinculosPR(conocimientoService.getConcimientosVinculados(map));
                    map.put("ntipoconocimientoid", Constante.BUENAPRACTICA.toString());
                    this.setListaTargetVinculosBP(conocimientoService.getConcimientosVinculados(map));
                    map.put("ntipoconocimientoid", Constante.CONTENIDO.toString());
                    this.setListaTargetVinculosCT(conocimientoService.getConcimientosVinculados(map));
                    map.put("ntipoconocimientoid", Constante.OPORTUNIDADMEJORA.toString());
                    this.setListaTargetVinculosOM(conocimientoService.getConcimientosVinculados(map));
                    map.put("ntipoconocimientoid", Constante.WIKI.toString());
                    situacion = Integer.parseInt(this.getSelectedOmejora().getNsituacionid().toString());
                    user_creacion = Integer.parseInt(serviceasig.getUserCreacionByContenido(this.getSelectedOmejora().getNtipoconocimientoid(), this.getSelectedOmejora().getNconocimientoid()).toString());

                    if (user_creacion == user_actual) {
                        this.setfButtonEspe("true");
                        this.setfButton("false");
                        this.setfButtonUM("false");
                        this.setfButtonMod("false");
                        this.setfButtonModPub("false");
                    } else {
                        if (perfil_actual == Constante.MODERADOR) {
                            this.setfButtonEspe("false");
                            this.setfButton("false");
                            this.setfButtonUM("false");
                            this.setfButtonMod("true");
                            this.setfButtonModPub("false");
                        }
                    }

                    if (StringUtils.isBlank(this.getSelectedOmejora().getVmsjsolicita())) {
                        this.fSInfMod = "false";
                    } else {
                        this.fSInfMod = "true";
                    }
                    if (StringUtils.isBlank(this.getSelectedOmejora().getVmsjrespuesta())) {
                        this.fMsjUsu1 = "false";
                    } else {
                        this.fMsjUsu1 = "true";
                    }

                    this.getSelectedAsignacion().setDfecharecepcion(new Date());
                    serviceasig.saveOrUpdate(this.getSelectedAsignacion());

                    if (situacion == Integer.parseInt(Constante.SITUACION_POR_VERIFICAR)) { 
                        pagina = "/pages/Pendientes/moderarOmejora?faces-redirect=true";
                    } else if (situacion == Integer.parseInt(Constante.SITUACION_VERIFICADO)) { 
                        pagina = "/pages/Pendientes/analizarOmejora?faces-redirect=true";
                    } else if (situacion == Integer.parseInt(Constante.SITUACION_APROBADO)) {
                        pagina = "/pages/Pendientes/implementarOmejora?faces-redirect=true";
                    }
                    break;
                }
            }
            mb.refreshNotifications();
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
        return pagina;
    }

    public void toMod(ActionEvent event) {
        try {
            RequestContext.getCurrentInstance().execute("PF('seePregDialog').hide();");
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
    }

    public void toEdit(ActionEvent event) {
        try {
            RequestContext.getCurrentInstance().execute("PF('respPregDialog').hide();");
            RequestContext.getCurrentInstance().execute("PF('modPregDialog').hide();");
            RequestContext.getCurrentInstance().execute("PF('modPubPregDialog').hide();");

        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
    }

    public String DevEsp() {
        String pagina = null;
        try {
            /* Validando si la cantidad de pregutnas destacados llegÃ³ al lÃ­mite (10 max.).*/
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
            PreguntaService service = (PreguntaService) ServiceFinder.findBean("PreguntaService");
            this.getSelectedPregunta().setNdestacado(this.getChkDestacado() ? BigDecimal.ONE : BigDecimal.ZERO);
            this.getSelectedPregunta().setNsituacionid(BigDecimal.valueOf((long) 2));
            service.saveOrUpdate(this.getSelectedPregunta());

            AsignacionService serviceasig = (AsignacionService) ServiceFinder.findBean("AsignacionService");
            this.getSelectedAsignacion().setNestadoid(BigDecimal.valueOf(Long.parseLong("2")));
            this.getSelectedAsignacion().setNaccionid(BigDecimal.valueOf(Long.parseLong("12")));
            this.getSelectedAsignacion().setDfechaatencion(new Date());
            
            serviceasig.saveOrUpdate(this.getSelectedAsignacion());

            Asignacion asignacion = new Asignacion();
            asignacion.setNasignacionid(serviceasig.getNextPK());
            asignacion.setNtipoconocimientoid(Constante.PREGUNTAS);
            asignacion.setNconocimientoid(this.getSelectedPregunta().getNpreguntaid());
            asignacion.setNestadoid(BigDecimal.valueOf(Long.parseLong("1")));
            CategoriaService categoriaService = (CategoriaService) ServiceFinder.findBean("CategoriaService");
            asignacion.setNusuarioid(categoriaService.getCategoriaById(this.getSelectedPregunta().getNcategoriaid()).getNespecialista());
            asignacion.setDfechacreacion(new Date());
            asignacion.setDfechaasignacion(new Date());
            serviceasig.saveOrUpdate(asignacion);

            LoginMB loginMB = (LoginMB) JSFUtils.getSessionAttribute("loginMB");
            loginMB.refreshNotifications();

            pagina = "/index.xhtml";

        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
        return pagina;
    }

    public void toSi(ActionEvent event) {
        try {

        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
    }

    public String Rechazar() {
        String pagina = null;
        try {

            PreguntaService service = (PreguntaService) ServiceFinder.findBean("PreguntaService");
            this.getSelectedPregunta().setNsituacionid(BigDecimal.valueOf((long) 7));
            service.saveOrUpdate(this.getSelectedPregunta());

            AsignacionService serviceasig = (AsignacionService) ServiceFinder.findBean("AsignacionService");
            this.getSelectedAsignacion().setNestadoid(BigDecimal.valueOf(Long.parseLong("2")));
            this.getSelectedAsignacion().setDfechaatencion(new Date());
            this.getSelectedAsignacion().setNaccionid(BigDecimal.valueOf(Long.parseLong("13")));
            serviceasig.saveOrUpdate(this.getSelectedAsignacion());
            LoginMB loginMB = (LoginMB) JSFUtils.getSessionAttribute("loginMB");
            loginMB.refreshNotifications();
            pagina = "/index.xhtml";

        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
        return pagina;
    }

    public void toCancel(ActionEvent event) {
        try {
            RequestContext.getCurrentInstance().execute("PF('respPregDialog').hide();");
            RequestContext.getCurrentInstance().execute("PF('modPregDialog').hide();");
            RequestContext.getCurrentInstance().execute("PF('modPubPregDialog').hide();");
            RequestContext.getCurrentInstance().execute("PF('tpoconDialog').hide();");
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
    }

    public void toEnt(ActionEvent event) {
//        try {
//            PreguntaService service = (PreguntaService) ServiceFinder.findBean("PreguntaService");
//            this.setEntidad(service.getNomEntidadbyIdEntidad(this.getEntidadId()));
//        } catch (Exception e) {
//            log.error(e.getMessage());
//            e.printStackTrace();
//        }
        try {
            if (event != null) {
                EntidadService service = (EntidadService) ServiceFinder.findBean("EntidadService");
                this.setListaEntidad(service.getEntidadesUbigeo());
            }
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
    }
    
    public void SeleccionarEP(ActionEvent event) {

        try {
            if (event != null) {
                int index = Integer.parseInt((String) JSFUtils.getRequestParameter("index"));

                if (!CollectionUtils.isEmpty(this.getFilteredListaEntidad())) {
                    this.setSelectedEntidad(this.getFilteredListaEntidad().get(index));
                    this.setEntidad(this.getSelectedEntidad().getVnombre());
                    this.getSelectedPregunta().setNentidadid(BigDecimal.valueOf(Long.parseLong(this.getSelectedEntidad().getVcodigoentidad())));
                } else {
                    this.setSelectedEntidad(this.getListaEntidad().get(index));
                    this.setEntidad(this.getSelectedEntidad().getVnombre());
                    this.getSelectedPregunta().setNentidadid(BigDecimal.valueOf(Long.parseLong(this.getSelectedEntidad().getVcodigoentidad())));
                }
                this.setFilteredListaEntidad(new ArrayList());

            }
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
    }

    public void savePregEdit(ActionEvent event) {
        String pagina = "/gescon/index.xhtml";
        try {
            //this.getSelectedPregunta().setVrespuesta(JSFUtils.getRequestParameter("descHtml"));
            /* Validando si la cantidad de pregutnas destacados llegÃ³ al lÃ­mite (10 max.).*/
            if (this.getChkDestacado()) {
                ConsultaService consultaService = (ConsultaService) ServiceFinder.findBean("ConsultaService");
                HashMap filter = new HashMap();
                filter.put("ntipoconocimientoid", Constante.PREGUNTAS);
                BigDecimal cant = consultaService.countDestacadosByTipoConocimiento(filter);
                if (cant.intValue() >= 10) {
                    this.setListaDestacados(consultaService.getDestacadosByTipoConocimiento(filter));
                    RequestContext.getCurrentInstance().execute("PF('destDialog').show();");
                    return;
                }
            }
            LoginMB loginMB = (LoginMB) JSFUtils.getSessionAttribute("loginMB");
            User user_savepreg = loginMB.getUser();

            PreguntaService service = (PreguntaService) ServiceFinder.findBean("PreguntaService");
            if (this.getSelectedCategoria() == null) {
                this.getSelectedPregunta().setNcategoriaid(this.getSelectedPregunta().getNcategoriaid());
                cat_nueva = this.getSelectedPregunta().getNcategoriaid();
            } else {
                this.getSelectedPregunta().setNcategoriaid(this.getSelectedCategoria().getNcategoriaid());
                cat_nueva = this.getSelectedPregunta().getNcategoriaid();
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

            this.setListaTargetVinculos(new ArrayList<Consulta>());

            if (!CollectionUtils.isEmpty(this.getListaTargetVinculosBL())) {
                this.getListaTargetVinculos().addAll(this.getListaTargetVinculosBL());
            }
            if (!CollectionUtils.isEmpty(this.getListaTargetVinculosBP())) {
                this.getListaTargetVinculos().addAll(this.getListaTargetVinculosBP());
            }
            if (!CollectionUtils.isEmpty(this.getListaTargetVinculosCT())) {
                this.getListaTargetVinculos().addAll(this.getListaTargetVinculosCT());
            }
            if (!CollectionUtils.isEmpty(this.getListaTargetVinculosOM())) {
                this.getListaTargetVinculos().addAll(this.getListaTargetVinculosOM());
            }
            if (!CollectionUtils.isEmpty(this.getListaTargetVinculosPR())) {
                this.getListaTargetVinculos().addAll(this.getListaTargetVinculosPR());
            }
            if (!CollectionUtils.isEmpty(this.getListaTargetVinculosWK())) {
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

            if (cat_antigua != cat_nueva) {
                AsignacionService serviceasig = (AsignacionService) ServiceFinder.findBean("AsignacionService");
                this.getSelectedAsignacion().setNestadoid(BigDecimal.valueOf(Long.parseLong("2")));
                this.getSelectedAsignacion().setDfechaatencion(new Date());
                this.getSelectedAsignacion().setNaccionid(BigDecimal.valueOf(Long.parseLong("12")));
                serviceasig.saveOrUpdate(this.getSelectedAsignacion());

                Asignacion asignacion = new Asignacion();
                asignacion.setNasignacionid(serviceasig.getNextPK());
                asignacion.setNtipoconocimientoid(Constante.PREGUNTAS);
                asignacion.setNconocimientoid(this.getSelectedPregunta().getNpreguntaid());
                asignacion.setNestadoid(BigDecimal.valueOf(Long.parseLong("1")));
                CategoriaService categoriaService = (CategoriaService) ServiceFinder.findBean("CategoriaService");
                asignacion.setNusuarioid(categoriaService.getCategoriaById(this.getSelectedPregunta().getNcategoriaid()).getNmoderador());
                asignacion.setDfechaasignacion(new Date());
                asignacion.setDfechacreacion(new Date());
                serviceasig.saveOrUpdate(asignacion);
            }
            loginMB.refreshNotifications();
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "INFO.", "Actualización exitosa!.");
            FacesContext.getCurrentInstance().addMessage(null, message);
            FacesContext.getCurrentInstance().getExternalContext().redirect(pagina);
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }

    }

    public String savePregEditE() {
        String pagina = null;
        try {

            LoginMB loginMB = (LoginMB) JSFUtils.getSessionAttribute("loginMB");
            User user_savepreg = loginMB.getUser();

            PreguntaService service = (PreguntaService) ServiceFinder.findBean("PreguntaService");
            if (this.getSelectedCategoria() == null) {
                this.getSelectedPregunta().setNcategoriaid(this.getSelectedPregunta().getNcategoriaid());
                cat_nueva = this.getSelectedPregunta().getNcategoriaid();
            } else {
                this.getSelectedPregunta().setNcategoriaid(this.getSelectedCategoria().getNcategoriaid());
                cat_nueva = this.getSelectedPregunta().getNcategoriaid();
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

            this.setListaTargetVinculos(new ArrayList<Consulta>());

            if (!CollectionUtils.isEmpty(this.getListaTargetVinculosBL())) {
                this.getListaTargetVinculos().addAll(this.getListaTargetVinculosBL());
            }
            if (!CollectionUtils.isEmpty(this.getListaTargetVinculosBP())) {
                this.getListaTargetVinculos().addAll(this.getListaTargetVinculosBP());
            }
            if (!CollectionUtils.isEmpty(this.getListaTargetVinculosCT())) {
                this.getListaTargetVinculos().addAll(this.getListaTargetVinculosCT());
            }
            if (!CollectionUtils.isEmpty(this.getListaTargetVinculosOM())) {
                this.getListaTargetVinculos().addAll(this.getListaTargetVinculosOM());
            }
            if (!CollectionUtils.isEmpty(this.getListaTargetVinculosPR())) {
                this.getListaTargetVinculos().addAll(this.getListaTargetVinculosPR());
            }
            if (!CollectionUtils.isEmpty(this.getListaTargetVinculosWK())) {
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

            if (cat_antigua != cat_nueva) {
                AsignacionService serviceasig = (AsignacionService) ServiceFinder.findBean("AsignacionService");
                this.getSelectedAsignacion().setNestadoid(BigDecimal.valueOf(Long.parseLong("2")));
                this.getSelectedAsignacion().setDfechaatencion(new Date());
                this.getSelectedAsignacion().setNaccionid(BigDecimal.valueOf(Long.parseLong("12")));
                serviceasig.saveOrUpdate(this.getSelectedAsignacion());

                Asignacion asignacion = new Asignacion();
                asignacion.setNasignacionid(serviceasig.getNextPK());
                asignacion.setNtipoconocimientoid(Constante.PREGUNTAS);
                asignacion.setNconocimientoid(this.getSelectedPregunta().getNpreguntaid());
                asignacion.setNestadoid(BigDecimal.valueOf(Long.parseLong("1")));
                CategoriaService categoriaService = (CategoriaService) ServiceFinder.findBean("CategoriaService");
                asignacion.setNusuarioid(categoriaService.getCategoriaById(this.getSelectedPregunta().getNcategoriaid()).getNespecialista());
                asignacion.setDfechaasignacion(new Date());
                asignacion.setDfechacreacion(new Date());
                serviceasig.saveOrUpdate(asignacion);

                pagina = "/index.xhtml";
            } else {
                pagina = "";
            }
            loginMB.refreshNotifications();
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
        return pagina;

    }

    public void toCancelRespEdit(ActionEvent event) {
        try {
            RequestContext.getCurrentInstance().execute("PF('editRespPregDialog').hide();");
            RequestContext.getCurrentInstance().execute("PF('editPregDialog').hide();");
            RequestContext.getCurrentInstance().execute("PF('editPubPregDialog').hide();");
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
    }

    public String sendSiMod() {
        String pagina = null;
        try {
            if (StringUtils.isBlank(this.getSelectedPregunta().getVmsjmoderador())) {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, Constante.SEVERETY_ALERTA, "Campo requerido. Ingrese el mensaje a enviar.");
                FacesContext.getCurrentInstance().addMessage(null, message);
                pagina = null;
            } else {
                /* Validando si la cantidad de pregutnas destacados llegÃ³ al lÃ­mite (10 max.).*/
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
                PreguntaService service = (PreguntaService) ServiceFinder.findBean("PreguntaService");
                this.getSelectedPregunta().setNdestacado(this.getChkDestacado() ? BigDecimal.ONE : BigDecimal.ZERO);
                this.getSelectedPregunta().setVmsjmoderador(this.getSelectedPregunta().getVmsjmoderador().toUpperCase());
                service.saveOrUpdate(this.getSelectedPregunta());

                AsignacionService serviceasig = (AsignacionService) ServiceFinder.findBean("AsignacionService");
                this.getSelectedAsignacion().setNestadoid(BigDecimal.valueOf(Long.parseLong("2")));
                this.getSelectedAsignacion().setDfechaatencion(new Date());
                this.getSelectedAsignacion().setNaccionid(BigDecimal.valueOf(Long.parseLong("9")));
                serviceasig.saveOrUpdate(this.getSelectedAsignacion());

                Asignacion asignacion = new Asignacion();
                asignacion.setNasignacionid(serviceasig.getNextPK());
                asignacion.setNtipoconocimientoid(Constante.PREGUNTAS);
                asignacion.setNconocimientoid(this.getSelectedPregunta().getNpreguntaid());
                asignacion.setNestadoid(BigDecimal.valueOf(Long.parseLong("1")));
                asignacion.setNusuarioid(serviceasig.getUserCreacionByPregunta(this.getSelectedPregunta().getNpreguntaid()));
                asignacion.setDfechaasignacion(new Date());
                asignacion.setDfechacreacion(new Date());
                serviceasig.saveOrUpdate(asignacion);

                loginMB.refreshNotifications();

                this.fSInfMod = "true";
                pagina = "/index.xhtml";
            }

        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
        return pagina;
    }

    public void toCancelSiMod(ActionEvent event) {
        try {
            RequestContext.getCurrentInstance().execute("PF('siModPregDialog').hide();");
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
    }

    public void toModPub(ActionEvent event) {
        try {
            RequestContext.getCurrentInstance().execute("PF('seePregDialog').hide();");
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
    }

    public String Publicar() {
        String pagina = "/gescon/index.xhtml";
        try {
            //this.getSelectedPregunta().setVrespuesta(JSFUtils.getRequestParameter("descHtml"));
            PreguntaService service = (PreguntaService) ServiceFinder.findBean("PreguntaService");
            this.getSelectedPregunta().setNdestacado(this.getChkDestacado() ? BigDecimal.ONE : BigDecimal.ZERO);
            this.getSelectedPregunta().setNsituacionid(BigDecimal.valueOf((long) 6));
            this.getSelectedPregunta().setDfechapublicacion(new Date());
            service.saveOrUpdate(this.getSelectedPregunta());

            AsignacionService serviceasig = (AsignacionService) ServiceFinder.findBean("AsignacionService");
            this.getSelectedAsignacion().setNestadoid(BigDecimal.valueOf(Long.parseLong("2")));
            this.getSelectedAsignacion().setDfechaatencion(new Date());
            this.getSelectedAsignacion().setNaccionid(BigDecimal.valueOf(Long.parseLong("8")));
            serviceasig.saveOrUpdate(this.getSelectedAsignacion());

            LoginMB loginMB = (LoginMB) JSFUtils.getSessionAttribute("loginMB");
            loginMB.refreshNotifications();
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "INFO.", "Se publicó la pregunta.");
            FacesContext.getCurrentInstance().addMessage(null, message);
            FacesContext.getCurrentInstance().getExternalContext().redirect(pagina);
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
        return pagina;
    }

    public void savePubEdit(ActionEvent event) throws Exception {
        try {
            PreguntaService service = (PreguntaService) ServiceFinder.findBean("PreguntaService");
            this.getSelectedPregunta().setVrespuesta(this.getSelectedPregunta().getVrespuesta());
            this.getSelectedPregunta().setVasunto(this.getSelectedPregunta().getVasunto().trim());
            this.getSelectedPregunta().setVdetalle(this.getSelectedPregunta().getVdetalle().trim());
            this.getSelectedPregunta().setNentidadid(this.getSelectedPregunta().getNentidadid());
            this.getSelectedPregunta().setVdatoadicional(this.getSelectedPregunta().getVdatoadicional().trim());
            this.getSelectedPregunta().setDfechamodificacion(new Date());
            service.saveOrUpdate(this.getSelectedPregunta());

            LoginMB loginMB = (LoginMB) JSFUtils.getSessionAttribute("loginMB");
            loginMB.refreshNotifications();

            RequestContext.getCurrentInstance().execute("PF('editRespPregDialog').hide();");
            RequestContext.getCurrentInstance().execute("PF('editPregDialog').hide();");
            RequestContext.getCurrentInstance().execute("PF('editPubPregDialog').hide();");

        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
    }

    public void toResp(ActionEvent event) {
        try {
            RequestContext.getCurrentInstance().execute("PF('seePregDialog').hide();");
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
    }

    public void saveRespEdit(ActionEvent event) throws Exception {
        try {
            PreguntaService service = (PreguntaService) ServiceFinder.findBean("PreguntaService");
            this.getSelectedPregunta().setVrespuesta(this.getSelectedPregunta().getVrespuesta().toUpperCase());
            this.getSelectedPregunta().setVasunto(this.getSelectedPregunta().getVasunto().trim());
            this.getSelectedPregunta().setVdetalle(this.getSelectedPregunta().getVdetalle().trim());
            this.getSelectedPregunta().setNentidadid(this.getSelectedPregunta().getNentidadid());
            this.getSelectedPregunta().setVdatoadicional(this.getSelectedPregunta().getVdatoadicional().trim());
            this.getSelectedPregunta().setDfechamodificacion(new Date());
            service.saveOrUpdate(this.getSelectedPregunta());

            LoginMB loginMB = (LoginMB) JSFUtils.getSessionAttribute("loginMB");
            loginMB.refreshNotifications();

            RequestContext.getCurrentInstance().execute("PF('editRespPregDialog').hide();");
            RequestContext.getCurrentInstance().execute("PF('editPregDialog').hide();");

        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }

    }

    public void Responder(ActionEvent event) {
        try {
            if (StringUtils.isBlank(this.getSelectedPregunta().getVrespuesta())) {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, Constante.SEVERETY_ALERTA, "Campo requerido. Ingrese la respuesta.");
                FacesContext.getCurrentInstance().addMessage(null, message);
            } else {
                PreguntaService service = (PreguntaService) ServiceFinder.findBean("PreguntaService");
                //this.getSelectedPregunta().setVrespuesta(JSFUtils.getRequestParameter("descHtml"));
                this.getSelectedPregunta().setNdestacado(this.getChkDestacado() ? BigDecimal.ONE : BigDecimal.ZERO);
                this.getSelectedPregunta().setNsituacionid(BigDecimal.valueOf((long) 5));
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
                CategoriaService categoriaService = (CategoriaService) ServiceFinder.findBean("CategoriaService");
                asignacion.setNusuarioid(categoriaService.getCategoriaById(this.getSelectedPregunta().getNcategoriaid()).getNmoderador());
                asignacion.setDfechacreacion(new Date());
                asignacion.setDfechaasignacion(new Date());
                serviceasig.saveOrUpdate(asignacion);

                LoginMB loginMB = (LoginMB) JSFUtils.getSessionAttribute("loginMB");
                loginMB.refreshNotifications();

                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "INFO.", "Se respondió la pregunta.");
                FacesContext.getCurrentInstance().addMessage(null, message);
                FacesContext.getCurrentInstance().getExternalContext().redirect("/gescon/index.xhtml");
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
    }

    public void sendSi(ActionEvent event) {
        try {
            if (StringUtils.isBlank(this.getSelectedPregunta().getVmsjespecialista())) {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, Constante.SEVERETY_ALERTA, "Campo requerido. Ingrese el mensaje a enviar.");
                FacesContext.getCurrentInstance().addMessage(null, message);
            } else {
                PreguntaService service = (PreguntaService) ServiceFinder.findBean("PreguntaService");
                //this.getSelectedPregunta().setVrespuesta(JSFUtils.getRequestParameter("descHtml"));
                this.getSelectedPregunta().setVmsjespecialista(this.getSelectedPregunta().getVmsjespecialista().toUpperCase());
                service.saveOrUpdate(this.getSelectedPregunta());

                AsignacionService serviceasig = (AsignacionService) ServiceFinder.findBean("AsignacionService");
                this.getSelectedAsignacion().setNestadoid(BigDecimal.valueOf(Long.parseLong("2")));
                this.getSelectedAsignacion().setDfechaatencion(new Date());
                this.getSelectedAsignacion().setNaccionid(BigDecimal.valueOf(Long.parseLong("10")));
                serviceasig.saveOrUpdate(this.getSelectedAsignacion());

                Asignacion asignacion = new Asignacion();
                asignacion.setNasignacionid(serviceasig.getNextPK());
                asignacion.setNtipoconocimientoid(Constante.PREGUNTAS);
                asignacion.setNconocimientoid(this.getSelectedPregunta().getNpreguntaid());
                asignacion.setNestadoid(BigDecimal.valueOf(Long.parseLong("1")));
                asignacion.setNusuarioid(serviceasig.getUserCreacionByPregunta(this.getSelectedPregunta().getNpreguntaid()));
                asignacion.setDfechacreacion(new Date());
                asignacion.setDfechaasignacion(new Date());
                serviceasig.saveOrUpdate(asignacion);

                LoginMB loginMB = (LoginMB) JSFUtils.getSessionAttribute("loginMB");
                loginMB.refreshNotifications();

                this.fSInfEspe = "true";

                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "INFO.", "Se respondió la pregunta.");
                FacesContext.getCurrentInstance().addMessage(null, message);
                FacesContext.getCurrentInstance().getExternalContext().redirect("/gescon/index.xhtml");
            }
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

    public String sendUsu() {
        String pagina = null;
        try {

            if (StringUtils.isBlank(this.getSelectedPregunta().getVmsjusuario2())) {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, Constante.SEVERETY_ALERTA, "Campo requerido. Ingrese el mensaje a enviar");
                FacesContext.getCurrentInstance().addMessage(null, message);
                pagina = null;
            } else {
                PreguntaService service = (PreguntaService) ServiceFinder.findBean("PreguntaService");
                //this.getSelectedPregunta().setVrespuesta(JSFUtils.getRequestParameter("descHtml"));
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
                CategoriaService categoriaService = (CategoriaService) ServiceFinder.findBean("CategoriaService");
                asignacion.setNusuarioid(categoriaService.getCategoriaById(this.getSelectedPregunta().getNcategoriaid()).getNespecialista());
                asignacion.setDfechacreacion(new Date());
                asignacion.setDfechaasignacion(new Date());
                serviceasig.saveOrUpdate(asignacion);

                LoginMB loginMB = (LoginMB) JSFUtils.getSessionAttribute("loginMB");
                loginMB.refreshNotifications();

                this.fMsjUsu2 = "true";

                pagina = "/index.xhtml";
            }

        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
        return pagina;
    }

    public void toCancelRespUsu(ActionEvent event) {
        try {
            RequestContext.getCurrentInstance().execute("PF('respUsuPregDialog').hide();");
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
    }

    public String sendUsuMod() {
        String pagina = null;
        try {
            if (StringUtils.isBlank(this.getSelectedPregunta().getVmsjusuario1())) {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, Constante.SEVERETY_ALERTA, "Campo requerido. Ingrese el mensaje a enviar");
                FacesContext.getCurrentInstance().addMessage(null, message);
                pagina = null;
            } else {
                /* Validando si la cantidad de pregutnas destacados llegÃ³ al lÃ­mite (10 max.).*/
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
                PreguntaService service = (PreguntaService) ServiceFinder.findBean("PreguntaService");
                this.getSelectedPregunta().setNdestacado(this.getChkDestacado() ? BigDecimal.ONE : BigDecimal.ZERO);
                this.getSelectedPregunta().setVmsjusuario1(this.getSelectedPregunta().getVmsjusuario1().toUpperCase());
                service.saveOrUpdate(this.getSelectedPregunta());

                AsignacionService serviceasig = (AsignacionService) ServiceFinder.findBean("AsignacionService");
                this.getSelectedAsignacion().setNestadoid(BigDecimal.valueOf(Long.parseLong("2")));
                this.getSelectedAsignacion().setDfechaatencion(new Date());
                this.getSelectedAsignacion().setNaccionid(BigDecimal.valueOf(Long.parseLong("11")));
                serviceasig.saveOrUpdate(this.getSelectedAsignacion());

                Asignacion asignacion = new Asignacion();
                asignacion.setNasignacionid(serviceasig.getNextPK());
                asignacion.setNtipoconocimientoid(Constante.PREGUNTAS);
                asignacion.setNconocimientoid(this.getSelectedPregunta().getNpreguntaid());
                asignacion.setNestadoid(BigDecimal.valueOf(Long.parseLong("1")));
                CategoriaService categoriaService = (CategoriaService) ServiceFinder.findBean("CategoriaService");
                asignacion.setNusuarioid(categoriaService.getCategoriaById(this.getSelectedPregunta().getNcategoriaid()).getNmoderador());
                asignacion.setDfechacreacion(new Date());
                asignacion.setDfechaasignacion(new Date());
                serviceasig.saveOrUpdate(asignacion);

                LoginMB loginMB = (LoginMB) JSFUtils.getSessionAttribute("loginMB");
                loginMB.refreshNotifications();

                this.fMsjUsu1 = "true";
                pagina = "/index.xhtml";
            }

        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
        return pagina;
    }

    public void toCancelRespUsuMod(ActionEvent event) {
        try {
            RequestContext.getCurrentInstance().execute("PF('respModPregDialog').hide();");
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
    }

    public void toRespUsu(ActionEvent event) {
        try {

        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
    }

    public void toRespUsuMod(ActionEvent event) {
        try {

        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
    }

    public void loadPickList(ActionEvent event) {
        try {
            BaseLegalService service = (BaseLegalService) ServiceFinder.findBean("BaseLegalService");
            if (this.getSelectedBaseLegal() != null) {
                if (CollectionUtils.isEmpty(this.getListaSource())) {
                    this.setListaSource(service.getTbaselegalesNotLinkedById(this.getSelectedBaseLegal().getNbaselegalid()));
                }
                if (CollectionUtils.isEmpty(this.getListaTarget())) {
                    this.setListaTarget(service.getTbaselegalesLinkedById(this.getSelectedBaseLegal().getNbaselegalid()));
                }
            } else {
                if (CollectionUtils.isEmpty(this.getListaSource())) {
                    this.setListaSource(service.getTbaselegalesNotLinkedById(null));
                }
                if (CollectionUtils.isEmpty(this.getListaTarget())) {
                    this.setListaTarget(new ArrayList<BaseLegal>());
                }
            }
            this.setPickList(new DualListModel<BaseLegal>(this.getListaSource(), this.getListaTarget()));
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
    }

    public String edit() {
        String pagina = null;
        try {
            LoginMB loginMB = (LoginMB) JSFUtils.getSessionAttribute("loginMB");
            User usuario = loginMB.getUser();
            if (this.getChkDestacado()) {
                ConsultaService consultaService = (ConsultaService) ServiceFinder.findBean("ConsultaService");
                HashMap filter = new HashMap();
                filter.put("ntipoconocimientoid", Constante.BASELEGAL);
                BigDecimal cant = consultaService.countDestacadosByTipoConocimiento(filter);
                if (cant.intValue() >= 10) {
                    this.setListaDestacados(consultaService.getDestacadosByTipoConocimiento(filter));
                    RequestContext.getCurrentInstance().execute("PF('destDialog').show();");
                    return "";
                }
            }
            if (!CollectionUtils.isEmpty(this.getListaTarget())) {
                for (BaseLegal v : this.getListaTarget()) {
                    if(v.getNestadoid().equals(BigDecimal.ZERO)) {
                        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR.", "Debe seleccionar el estado de todos los vínculos agregados.");
                        FacesContext.getCurrentInstance().addMessage(null, message);
                        return "";
                    }
                }
            }
            if (this.getSelectedCategoria() != null) {
                this.getSelectedBaseLegal().setNcategoriaid(this.getSelectedBaseLegal().getNcategoriaid());
                cat_nueva = this.getSelectedBaseLegal().getNcategoriaid();
            } else {
                this.getSelectedBaseLegal().setNcategoriaid(this.getSelectedBaseLegal().getNcategoriaid());
                cat_nueva = this.getSelectedBaseLegal().getNcategoriaid();
            }
            BaseLegalService service = (BaseLegalService) ServiceFinder.findBean("BaseLegalService");
            this.getSelectedBaseLegal().setVnombre(StringUtils.capitalize(this.getSelectedBaseLegal().getVnombre()));
            this.getSelectedBaseLegal().setVnumero(this.getTipoNorma().concat(" - ").concat(StringUtils.upperCase(this.getNumeroNorma())));
            this.getSelectedBaseLegal().setNrangoid(this.getSelectedBaseLegal().getNrangoid());
            this.getSelectedBaseLegal().setNgobnacional(this.getChkGobNacional() ? BigDecimal.ONE : BigDecimal.ZERO);
            this.getSelectedBaseLegal().setNgobregional(this.getChkGobRegional() ? BigDecimal.ONE : BigDecimal.ZERO);
            this.getSelectedBaseLegal().setNgoblocal(this.getChkGobLocal() ? BigDecimal.ONE : BigDecimal.ZERO);
            this.getSelectedBaseLegal().setNmancomunidades(this.getChkMancomunidades() ? BigDecimal.ONE : BigDecimal.ZERO);
            this.getSelectedBaseLegal().setNdestacado(this.getChkDestacado() ? BigDecimal.ONE : BigDecimal.ZERO);
            this.getSelectedBaseLegal().setVsumilla(this.getSelectedBaseLegal().getVsumilla().trim());
            this.getSelectedBaseLegal().setDfechapublicacion(this.getSelectedBaseLegal().getDfechapublicacion());
            this.getSelectedBaseLegal().setVtema(this.getSelectedBaseLegal().getVtema());
            this.getSelectedBaseLegal().setVusuariomodificacion(usuario.getVlogin());
            this.getSelectedBaseLegal().setDfechamodificacion(new Date());
            service.saveOrUpdate(this.getSelectedBaseLegal());

            BaseLegalHistorialService serviceHistorial = (BaseLegalHistorialService) ServiceFinder.findBean("BaseLegalHistorialService");
            BaselegalHist hist = serviceHistorial.getLastHistorialByBaselegal(this.getSelectedBaseLegal().getNbaselegalid());

            BaselegalHist baseHist = new BaselegalHist();
            baseHist.setNhistorialid(serviceHistorial.getNextPK());
            baseHist.setNbaselegalid(this.getSelectedBaseLegal().getNbaselegalid());
            baseHist.setNcategoriaid(this.getSelectedBaseLegal().getNcategoriaid());
            baseHist.setVnombre(this.getSelectedBaseLegal().getVnombre());
            baseHist.setVnumero(this.getSelectedBaseLegal().getVnumero());
            baseHist.setNrangoid(this.getSelectedBaseLegal().getNrangoid());
            baseHist.setNgobnacional(this.getSelectedBaseLegal().getNgobnacional());
            baseHist.setNgobregional(this.getSelectedBaseLegal().getNgobregional());
            baseHist.setNgoblocal(this.getSelectedBaseLegal().getNgoblocal());
            baseHist.setNmancomunidades(this.getSelectedBaseLegal().getNmancomunidades());
            baseHist.setNdestacado(this.getSelectedBaseLegal().getNdestacado());
            baseHist.setVsumilla(this.getSelectedBaseLegal().getVsumilla());
            baseHist.setDfechapublicacion(this.getSelectedBaseLegal().getDfechapublicacion());
            baseHist.setVtema(this.getSelectedBaseLegal().getVtema());
            baseHist.setNactivo(this.getSelectedBaseLegal().getNactivo());
            baseHist.setNestadoid(this.getSelectedBaseLegal().getNestadoid());
            baseHist.setNversion(BigDecimal.valueOf(hist.getNversion().intValue() + 1));
            baseHist.setVusuariocreacion(usuario.getVlogin());
            baseHist.setDfechacreacion(new Date());
            baseHist.setVusuariomodificacion(this.getSelectedBaseLegal().getVusuariomodificacion());
            baseHist.setDfechamodificacion(this.getSelectedBaseLegal().getDfechamodificacion());
            serviceHistorial.saveOrUpdate(baseHist);

            Tbaselegal tbaselegal = new Tbaselegal();
            BeanUtils.copyProperties(tbaselegal, this.getSelectedBaseLegal());

            String ruta0 = path + this.getSelectedBaseLegal().getNbaselegalid().toString() + "\\" + BigDecimal.ZERO.toString() + "\\";
            String ruta1 = path + this.getSelectedBaseLegal().getNbaselegalid().toString() + "\\" + baseHist.getNversion().toString() + "\\";

            ArchivoService aservice = (ArchivoService) ServiceFinder.findBean("ArchivoService");
            Archivo archivo = aservice.getArchivoByBaseLegal(this.getSelectedBaseLegal());
            if (this.getUploadFile() != null) {
                archivo.setVnombre(this.getUploadFile().getFileName());
                archivo.setVruta(ruta0 + archivo.getVnombre());
                archivo.setVusuariomodificacion(usuario.getVlogin());
                archivo.setDfechamodificacion(new Date());
                aservice.saveOrUpdate(archivo);
                saveFile(ruta0);
            }

            ArchivoHistorialService aserviceHist = (ArchivoHistorialService) ServiceFinder.findBean("ArchivoHistorialService");
            ArchivoHist aHist = aserviceHist.getLastArchivoHistByBaseLegalHist(baseHist);

            ArchivoHist archivoHist = new ArchivoHist();
            archivoHist.setNarchivohistid(aserviceHist.getNextPK());
            archivoHist.setNhistorialid(baseHist.getNhistorialid());
            archivoHist.setNbaselegalid(baseHist.getNbaselegalid());
            archivoHist.setVnombre(archivo.getVnombre());
            archivoHist.setVruta(ruta1 + archivo.getVnombre());
            archivoHist.setVusuariocreacion(usuario.getVlogin());
            archivoHist.setDfechacreacion(new Date());
            aserviceHist.saveOrUpdate(archivoHist);
            saveFile(ruta1);

            VinculoBaseLegalService vservice = (VinculoBaseLegalService) ServiceFinder.findBean("VinculoBaseLegalService");
            vservice.deleteByBaseLegal(this.getSelectedBaseLegal());
            for (BaseLegal v : this.getListaTarget()) {
                TvinculoBaselegalId id = new TvinculoBaselegalId();
                id.setNbaselegalid(tbaselegal.getNbaselegalid());
                id.setNvinculoid(vservice.getNextPK());
                VinculoBaselegal vinculo = new VinculoBaselegal();
                vinculo.setId(id);
                vinculo.setTbaselegal(tbaselegal);
                vinculo.setNbaselegalvinculadaid(v.getNbaselegalid());
                vinculo.setNtipovinculo(v.getNestadoid());
                vinculo.setDfechacreacion(new Date());
                vinculo.setVusuariocreacion(usuario.getVlogin());
                vservice.saveOrUpdate(vinculo);

                BaseLegal blvinculada = service.getBaselegalById(v.getNbaselegalid());
                blvinculada.setNestadoid(v.getNestadoid());
                blvinculada.setDfechamodificacion(new Date());
                blvinculada.setVusuariomodificacion(usuario.getVlogin());
                service.saveOrUpdate(blvinculada);

                if (v.getNbaselegalid().toString().equals(Constante.ESTADO_BASELEGAL_MODIFICADA)
                        || v.getNbaselegalid().toString().equals(Constante.ESTADO_BASELEGAL_CONCORDADO)) {

                    ConocimientoService cservice = (ConocimientoService) ServiceFinder.findBean("ConocimientoService");
                    List<Consulta> listaConocimientos = cservice.getConcimientosByVinculoBaseLegalId(blvinculada.getNbaselegalid());
                    for (Consulta c : listaConocimientos) {
                        Conocimiento conocimiento = cservice.getConocimientoById(c.getId());
                        conocimiento.setDfechamodificacion(new Date());
                        conocimiento.setVusuariomodificacion(usuario.getVlogin());
                        String descHtml = GcmFileUtils.readStringFromFileServer(conocimiento.getVruta(), "html.txt");
                        String descPlain = GcmFileUtils.readStringFromFileServer(conocimiento.getVruta(), "plain.txt");
                        cservice.saveOrUpdate(conocimiento);

                        HistorialService historialService = (HistorialService) ServiceFinder.findBean("HistorialService");
                        Historial lastHistorial = historialService.getLastHistorialByConocimiento(conocimiento.getNconocimientoid());
                        int lastversion;
                        if (lastHistorial != null) {
                            lastversion = lastHistorial.getNnumversion().intValue();
                        } else {
                            lastversion = 0;
                        }
                        String newpath = "";
                        if (conocimiento.getNtipoconocimientoid().equals(Constante.BASELEGAL)) {
                            newpath = "bl/";
                        } else if (conocimiento.getNtipoconocimientoid().equals(Constante.BUENAPRACTICA)) {
                            newpath = "bp/";
                        } else if (conocimiento.getNtipoconocimientoid().equals(Constante.CONTENIDO)) {
                            newpath = "ct/";
                        } else if (conocimiento.getNtipoconocimientoid().equals(Constante.OPORTUNIDADMEJORA)) {
                            newpath = "om/";
                        } else if (conocimiento.getNtipoconocimientoid().equals(Constante.PREGUNTAS)) {
                            newpath = "pr/";
                        } else if (conocimiento.getNtipoconocimientoid().equals(Constante.WIKI)) {
                            newpath = "wk/";
                        }

                        String url = newpath.concat(conocimiento.getNconocimientoid().toString()).concat("/").concat(Integer.toString(lastversion + 1)).concat("/");

                        ThistorialId thistorialId = new ThistorialId();
                        thistorialId.setNconocimientoid(conocimiento.getNconocimientoid());
                        thistorialId.setNhistorialid(historialService.getNextPK());
                        Historial historial = new Historial();
                        historial.setId(thistorialId);
                        historial.setNtipoconocimientoid(conocimiento.getNtipoconocimientoid());
                        historial.setNcategoriaid(conocimiento.getNcategoriaid());
                        historial.setVtitulo(conocimiento.getVtitulo());
                        historial.setNactivo(BigDecimal.ONE);
                        historial.setNsituacionid(conocimiento.getNsituacionid());
                        historial.setVruta(url);
                        historial.setNnumversion(BigDecimal.valueOf(lastversion + 1));
                        historial.setDfechacreacion(new Date());
                        historial.setVusuariocreacion(usuario.getVlogin());
                        historialService.saveOrUpdate(historial);

                        GcmFileUtils.writeStringToFileServer(url, "html.txt", descHtml);
                        GcmFileUtils.writeStringToFileServer(url, "plain.txt", descPlain);

                        SeccionService seccionService = (SeccionService) ServiceFinder.findBean("SeccionService");
                        SeccionHistService seccionHistService = (SeccionHistService) ServiceFinder.findBean("SeccionHistService");
                        List<Seccion> listaSecc = seccionService.getSeccionesByConocimiento(conocimiento.getNconocimientoid());
                        if (!CollectionUtils.isEmpty(listaSecc)) {
                            String url0 = conocimiento.getVruta().concat("s");
                            String url1 = url.concat("s");
                            for (Seccion seccion : listaSecc) {
                                seccion.setDetalleHtml(GcmFileUtils.readStringFromFileServer(seccion.getVruta(), "html.txt"));
                                ruta0 = url0.concat(seccion.getNorden().toString()).concat("/");
                                seccion.setVruta(ruta0);
                                seccion.setDfechamodificacion(new Date());
                                seccion.setVusuariomodificacion(usuario.getVlogin());
                                seccionService.saveOrUpdate(seccion);

                                seccion.setDetallePlain(Jsoup.parse(seccion.getDetalleHtml()).text());

                                ruta1 = url1.concat(seccion.getNorden().toString()).concat("/");
                                TseccionHistId tseccionHistId = new TseccionHistId();
                                tseccionHistId.setNconocimientoid(thistorialId.getNconocimientoid());
                                tseccionHistId.setNhistorialid(thistorialId.getNhistorialid());
                                tseccionHistId.setNseccionhid(seccionHistService.getNextPK());
                                SeccionHist seccionHist = new SeccionHist();
                                seccionHist.setId(tseccionHistId);
                                seccionHist.setNorden(seccion.getNorden());
                                seccionHist.setVruta(ruta1);
                                seccionHist.setVtitulo(seccion.getVtitulo());
                                seccionHist.setVusuariocreacion(usuario.getVlogin());
                                seccionHist.setDfechacreacion(new Date());
                                seccionHistService.saveOrUpdate(seccionHist);

                                GcmFileUtils.writeStringToFileServer(ruta1, "html.txt", seccion.getDetalleHtml());
                                GcmFileUtils.writeStringToFileServer(ruta1, "plain.txt", seccion.getDetallePlain());
                            }
                        }

                        VinculoService vinculoService = (VinculoService) ServiceFinder.findBean("VinculoService");
                        Vinculo vinculoC = new Vinculo();
                        vinculoC.setNvinculoid(vinculoService.getNextPK());
                        vinculoC.setNconocimientoid(conocimiento.getNconocimientoid());
                        vinculoC.setNconocimientovinc(tbaselegal.getNbaselegalid());
                        vinculoC.setNtipoconocimientovinc(Constante.BASELEGAL);
                        vinculoC.setDfechacreacion(new Date());
                        vinculoC.setVusuariocreacion(usuario.getVlogin());
                        vinculoService.saveOrUpdate(vinculoC);

                        List<Vinculo> vinculos = vinculoService.getVinculosByConocimiento(conocimiento.getNtipoconocimientoid());
                        VinculoHistService vinculoHistService = (VinculoHistService) ServiceFinder.findBean("VinculoHistService");
                        for (Vinculo vinc : vinculos) {
                            TvinculoHistId vinculoHistId = new TvinculoHistId();
                            vinculoHistId.setNvinculohid(vinculoHistService.getNextPK());
                            vinculoHistId.setNconocimientoid(thistorialId.getNconocimientoid());
                            vinculoHistId.setNhistorialid(thistorialId.getNhistorialid());
                            VinculoHist vinculoHist = new VinculoHist();
                            vinculoHist.setId(vinculoHistId);
                            vinculoHist.setNconocimientovinc(vinc.getNconocimientovinc());
                            vinculoHist.setDfechacreacion(new Date());
                            vinculoHist.setVusuariocreacion(usuario.getVlogin());
                            vinculoHistService.saveOrUpdate(vinculoHist);
                        }
                    }
                } else if (v.getNbaselegalid().toString().equals(Constante.ESTADO_BASELEGAL_DEROGADA)) {
                    ConocimientoService cservice = (ConocimientoService) ServiceFinder.findBean("ConocimientoService");
                    List<Consulta> listaConocimientos = cservice.getConcimientosByVinculoBaseLegalId(blvinculada.getNbaselegalid());
                    for (Consulta c : listaConocimientos) {
                        Conocimiento conocimiento = cservice.getConocimientoById(c.getId());
                        conocimiento.setNflgvinculo(BigDecimal.ONE);
                        conocimiento.setDfechamodificacion(new Date());
                        conocimiento.setVusuariomodificacion(usuario.getVlogin());
                    }
                }

                VinculoBaselegalHistorialService vserviceHist = (VinculoBaselegalHistorialService) ServiceFinder.findBean("VinculoBaselegalHistorialService");
                VinculoBaselegalHist vinculoHist = new VinculoBaselegalHist();
                vinculoHist.setNvinculohistid(vserviceHist.getNextPK());
                vinculoHist.setNhistorialid(baseHist.getNhistorialid());
                vinculoHist.setNbaselegalid(baseHist.getNbaselegalid());
                vinculoHist.setNbaselegalvinculadaid(v.getNbaselegalid());
                vinculoHist.setNtipovinculo(v.getNestadoid());
                vinculoHist.setDfechacreacion(new Date());
                vinculoHist.setVusuariocreacion(usuario.getVlogin());
                vserviceHist.saveOrUpdate(vinculoHist);
            }

            if (cat_antigua != cat_nueva) {
                AsignacionService serviceasig = (AsignacionService) ServiceFinder.findBean("AsignacionService");
                this.getSelectedAsignacion().setNestadoid(BigDecimal.valueOf(Long.parseLong("2")));
                this.getSelectedAsignacion().setDfechaatencion(new Date());
                this.getSelectedAsignacion().setNaccionid(BigDecimal.valueOf(Long.parseLong("12")));
                serviceasig.saveOrUpdate(this.getSelectedAsignacion());

                Asignacion asignacion = new Asignacion();
                asignacion.setNasignacionid(serviceasig.getNextPK());
                asignacion.setNtipoconocimientoid(Constante.BASELEGAL);
                asignacion.setNconocimientoid(this.getSelectedBaseLegal().getNbaselegalid());
                asignacion.setNestadoid(BigDecimal.valueOf(Long.parseLong("1")));
                CategoriaService categoriaService = (CategoriaService) ServiceFinder.findBean("CategoriaService");
                asignacion.setNusuarioid(categoriaService.getCategoriaById(this.getSelectedBaseLegal().getNcategoriaid()).getNmoderador());
                asignacion.setDfechaasignacion(new Date());
                asignacion.setDfechacreacion(new Date());
                serviceasig.saveOrUpdate(asignacion);

                pagina = "/index.xhtml";
            } else {
                pagina = "";
            }
            loginMB.refreshNotifications();
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
        return pagina;
    }

    public String PubBaseLegal() throws Exception {
        String pagina = "/index.xhtml";
        try {

            LoginMB loginMB = (LoginMB) JSFUtils.getSessionAttribute("loginMB");
            User usuario = loginMB.getUser();
            if (this.getChkDestacado()) {
                ConsultaService consultaService = (ConsultaService) ServiceFinder.findBean("ConsultaService");
                HashMap filter = new HashMap();
                filter.put("ntipoconocimientoid", Constante.BASELEGAL);
                BigDecimal cant = consultaService.countDestacadosByTipoConocimiento(filter);
                if (cant.intValue() >= 10) {
                    this.setListaDestacados(consultaService.getDestacadosByTipoConocimiento(filter));
                    RequestContext.getCurrentInstance().execute("PF('destDialog').show();");
                    return "";
                }
            }
            if (!CollectionUtils.isEmpty(this.getListaTarget())) {
                for (BaseLegal v : this.getListaTarget()) {
                    if(v.getNestadoid().equals(BigDecimal.ZERO)) {
                        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR.", "Debe seleccionar el estado de todos los vínculos agregados.");
                        FacesContext.getCurrentInstance().addMessage(null, message);
                        return "";
                    }
                }
            }
            if (this.getSelectedCategoria() != null) {
                this.getSelectedBaseLegal().setNcategoriaid(this.getSelectedCategoria().getNcategoriaid());
            }
            BaseLegalService service = (BaseLegalService) ServiceFinder.findBean("BaseLegalService");
            this.getSelectedBaseLegal().setVnombre(StringUtils.capitalize(this.getSelectedBaseLegal().getVnombre()));
            this.getSelectedBaseLegal().setVnumero(this.getTipoNorma().concat(" - ").concat(StringUtils.upperCase(this.getNumeroNorma())));
            this.getSelectedBaseLegal().setNrangoid(this.getSelectedBaseLegal().getNrangoid());
            this.getSelectedBaseLegal().setNgobnacional(this.getChkGobNacional() ? BigDecimal.ONE : BigDecimal.ZERO);
            this.getSelectedBaseLegal().setNgobregional(this.getChkGobRegional() ? BigDecimal.ONE : BigDecimal.ZERO);
            this.getSelectedBaseLegal().setNgoblocal(this.getChkGobLocal() ? BigDecimal.ONE : BigDecimal.ZERO);
            this.getSelectedBaseLegal().setNmancomunidades(this.getChkMancomunidades() ? BigDecimal.ONE : BigDecimal.ZERO);
            this.getSelectedBaseLegal().setNdestacado(this.getChkDestacado() ? BigDecimal.ONE : BigDecimal.ZERO);
            this.getSelectedBaseLegal().setVsumilla(this.getSelectedBaseLegal().getVsumilla().trim());
            this.getSelectedBaseLegal().setDfechapublicacion(new Date());
            this.getSelectedBaseLegal().setVtema(this.getSelectedBaseLegal().getVtema());
            this.getSelectedBaseLegal().setNestadoid(BigDecimal.valueOf(Long.valueOf(Constante.ESTADO_BASELEGAL_PUBLICADO)));
            this.getSelectedBaseLegal().setVusuariomodificacion(usuario.getVlogin());
            this.getSelectedBaseLegal().setDfechamodificacion(new Date());
            service.saveOrUpdate(this.getSelectedBaseLegal());

            BaseLegalHistorialService serviceHistorial = (BaseLegalHistorialService) ServiceFinder.findBean("BaseLegalHistorialService");
            BaselegalHist hist = serviceHistorial.getLastHistorialByBaselegal(this.getSelectedBaseLegal().getNbaselegalid());

            BaselegalHist baseHist = new BaselegalHist();
            baseHist.setNhistorialid(serviceHistorial.getNextPK());
            baseHist.setNbaselegalid(this.getSelectedBaseLegal().getNbaselegalid());
            baseHist.setNcategoriaid(this.getSelectedBaseLegal().getNcategoriaid());
            baseHist.setVnombre(this.getSelectedBaseLegal().getVnombre());
            baseHist.setVnumero(this.getSelectedBaseLegal().getVnumero());
            baseHist.setNrangoid(this.getSelectedBaseLegal().getNrangoid());
            baseHist.setNgobnacional(this.getSelectedBaseLegal().getNgobnacional());
            baseHist.setNgobregional(this.getSelectedBaseLegal().getNgobregional());
            baseHist.setNgoblocal(this.getSelectedBaseLegal().getNgoblocal());
            baseHist.setNmancomunidades(this.getSelectedBaseLegal().getNmancomunidades());
            baseHist.setNdestacado(this.getSelectedBaseLegal().getNdestacado());
            baseHist.setVsumilla(this.getSelectedBaseLegal().getVsumilla());
            baseHist.setDfechapublicacion(this.getSelectedBaseLegal().getDfechapublicacion());
            baseHist.setVtema(this.getSelectedBaseLegal().getVtema());
            baseHist.setNactivo(this.getSelectedBaseLegal().getNactivo());
            baseHist.setNestadoid(this.getSelectedBaseLegal().getNestadoid());
            baseHist.setNversion(BigDecimal.valueOf(hist.getNversion().intValue() + 1));
            baseHist.setVusuariocreacion(usuario.getVlogin());
            baseHist.setDfechacreacion(new Date());
            baseHist.setVusuariomodificacion(this.getSelectedBaseLegal().getVusuariomodificacion());
            baseHist.setDfechamodificacion(this.getSelectedBaseLegal().getDfechamodificacion());
            serviceHistorial.saveOrUpdate(baseHist);

            Tbaselegal tbaselegal = new Tbaselegal();
            BeanUtils.copyProperties(tbaselegal, this.getSelectedBaseLegal());

            String ruta0 = path + this.getSelectedBaseLegal().getNbaselegalid().toString() + "\\" + BigDecimal.ZERO.toString() + "\\";
            String ruta1 = path + this.getSelectedBaseLegal().getNbaselegalid().toString() + "\\" + baseHist.getNversion().toString() + "\\";

            ArchivoService aservice = (ArchivoService) ServiceFinder.findBean("ArchivoService");
            Archivo archivo = aservice.getArchivoByBaseLegal(this.getSelectedBaseLegal());
            if (this.getUploadFile() != null) {
                archivo.setVnombre(this.getUploadFile().getFileName());
                archivo.setVruta(ruta0 + archivo.getVnombre());
                archivo.setVusuariomodificacion(usuario.getVlogin());
                archivo.setDfechamodificacion(new Date());
                aservice.saveOrUpdate(archivo);
                saveFile(ruta0);
            }

            ArchivoHistorialService aserviceHist = (ArchivoHistorialService) ServiceFinder.findBean("ArchivoHistorialService");
            ArchivoHist aHist = aserviceHist.getLastArchivoHistByBaseLegalHist(baseHist);

            ArchivoHist archivoHist = new ArchivoHist();
            archivoHist.setNarchivohistid(aserviceHist.getNextPK());
            archivoHist.setNhistorialid(baseHist.getNhistorialid());
            archivoHist.setNbaselegalid(baseHist.getNbaselegalid());
            archivoHist.setVnombre(archivo.getVnombre());
            archivoHist.setVruta(ruta1 + archivo.getVnombre());
            archivoHist.setVusuariocreacion(usuario.getVlogin());
            archivoHist.setDfechacreacion(new Date());
            aserviceHist.saveOrUpdate(archivoHist);
            saveFile(ruta1);

            VinculoBaseLegalService vservice = (VinculoBaseLegalService) ServiceFinder.findBean("VinculoBaseLegalService");
            vservice.deleteByBaseLegal(this.getSelectedBaseLegal());
            for (BaseLegal v : this.getListaTarget()) {
                TvinculoBaselegalId id = new TvinculoBaselegalId();
                id.setNbaselegalid(tbaselegal.getNbaselegalid());
                id.setNvinculoid(vservice.getNextPK());
                VinculoBaselegal vinculo = new VinculoBaselegal();
                vinculo.setId(id);
                vinculo.setTbaselegal(tbaselegal);
                vinculo.setNbaselegalvinculadaid(v.getNbaselegalid());
                vinculo.setNtipovinculo(v.getNestadoid());
                vinculo.setDfechacreacion(new Date());
                vinculo.setVusuariocreacion(usuario.getVlogin());
                vservice.saveOrUpdate(vinculo);

                BaseLegal blvinculada = service.getBaselegalById(v.getNbaselegalid());
                blvinculada.setNestadoid(v.getNestadoid());
                blvinculada.setDfechamodificacion(new Date());
                blvinculada.setVusuariomodificacion(usuario.getVlogin());
                service.saveOrUpdate(blvinculada);

                if (v.getNbaselegalid().toString().equals(Constante.ESTADO_BASELEGAL_MODIFICADA)
                        || v.getNbaselegalid().toString().equals(Constante.ESTADO_BASELEGAL_CONCORDADO)) {

                    ConocimientoService cservice = (ConocimientoService) ServiceFinder.findBean("ConocimientoService");
                    List<Consulta> listaConocimientos = cservice.getConcimientosByVinculoBaseLegalId(blvinculada.getNbaselegalid());
                    for (Consulta c : listaConocimientos) {
                        Conocimiento conocimiento = cservice.getConocimientoById(c.getId());
                        conocimiento.setDfechamodificacion(new Date());
                        conocimiento.setVusuariomodificacion(usuario.getVlogin());
                        String descHtml = GcmFileUtils.readStringFromFileServer(conocimiento.getVruta(), "html.txt");
                        String descPlain = GcmFileUtils.readStringFromFileServer(conocimiento.getVruta(), "plain.txt");
                        cservice.saveOrUpdate(conocimiento);

                        HistorialService historialService = (HistorialService) ServiceFinder.findBean("HistorialService");
                        Historial lastHistorial = historialService.getLastHistorialByConocimiento(conocimiento.getNconocimientoid());
                        int lastversion;
                        if (lastHistorial != null) {
                            lastversion = lastHistorial.getNnumversion().intValue();
                        } else {
                            lastversion = 0;
                        }
                        String newpath = "";
                        if (conocimiento.getNtipoconocimientoid().equals(Constante.BASELEGAL)) {
                            newpath = "bl/";
                        } else if (conocimiento.getNtipoconocimientoid().equals(Constante.BUENAPRACTICA)) {
                            newpath = "bp/";
                        } else if (conocimiento.getNtipoconocimientoid().equals(Constante.CONTENIDO)) {
                            newpath = "ct/";
                        } else if (conocimiento.getNtipoconocimientoid().equals(Constante.OPORTUNIDADMEJORA)) {
                            newpath = "om/";
                        } else if (conocimiento.getNtipoconocimientoid().equals(Constante.PREGUNTAS)) {
                            newpath = "pr/";
                        } else if (conocimiento.getNtipoconocimientoid().equals(Constante.WIKI)) {
                            newpath = "wk/";
                        }

                        String url = newpath.concat(conocimiento.getNconocimientoid().toString()).concat("/").concat(Integer.toString(lastversion + 1)).concat("/");

                        ThistorialId thistorialId = new ThistorialId();
                        thistorialId.setNconocimientoid(conocimiento.getNconocimientoid());
                        thistorialId.setNhistorialid(historialService.getNextPK());
                        Historial historial = new Historial();
                        historial.setId(thistorialId);
                        historial.setNtipoconocimientoid(conocimiento.getNtipoconocimientoid());
                        historial.setNcategoriaid(conocimiento.getNcategoriaid());
                        historial.setVtitulo(conocimiento.getVtitulo());
                        historial.setNactivo(BigDecimal.ONE);
                        historial.setNsituacionid(conocimiento.getNsituacionid());
                        historial.setVruta(url);
                        historial.setNnumversion(BigDecimal.valueOf(lastversion + 1));
                        historial.setDfechacreacion(new Date());
                        historial.setVusuariocreacion(usuario.getVlogin());
                        historialService.saveOrUpdate(historial);

                        GcmFileUtils.writeStringToFileServer(url, "html.txt", descHtml);
                        GcmFileUtils.writeStringToFileServer(url, "plain.txt", descPlain);

                        SeccionService seccionService = (SeccionService) ServiceFinder.findBean("SeccionService");
                        SeccionHistService seccionHistService = (SeccionHistService) ServiceFinder.findBean("SeccionHistService");
                        List<Seccion> listaSecc = seccionService.getSeccionesByConocimiento(conocimiento.getNconocimientoid());
                        if (!CollectionUtils.isEmpty(listaSecc)) {
                            String url0 = conocimiento.getVruta().concat("s");
                            String url1 = url.concat("s");
                            for (Seccion seccion : listaSecc) {
                                seccion.setDetalleHtml(GcmFileUtils.readStringFromFileServer(seccion.getVruta(), "html.txt"));
                                ruta0 = url0.concat(seccion.getNorden().toString()).concat("/");
                                seccion.setVruta(ruta0);
                                seccion.setDfechamodificacion(new Date());
                                seccion.setVusuariomodificacion(usuario.getVlogin());
                                seccionService.saveOrUpdate(seccion);

                                seccion.setDetallePlain(Jsoup.parse(seccion.getDetalleHtml()).text());

                                ruta1 = url1.concat(seccion.getNorden().toString()).concat("/");
                                TseccionHistId tseccionHistId = new TseccionHistId();
                                tseccionHistId.setNconocimientoid(thistorialId.getNconocimientoid());
                                tseccionHistId.setNhistorialid(thistorialId.getNhistorialid());
                                tseccionHistId.setNseccionhid(seccionHistService.getNextPK());
                                SeccionHist seccionHist = new SeccionHist();
                                seccionHist.setId(tseccionHistId);
                                seccionHist.setNorden(seccion.getNorden());
                                seccionHist.setVruta(ruta1);
                                seccionHist.setVtitulo(seccion.getVtitulo());
                                seccionHist.setVusuariocreacion(usuario.getVlogin());
                                seccionHist.setDfechacreacion(new Date());
                                seccionHistService.saveOrUpdate(seccionHist);

                                GcmFileUtils.writeStringToFileServer(ruta1, "html.txt", seccion.getDetalleHtml());
                                GcmFileUtils.writeStringToFileServer(ruta1, "plain.txt", seccion.getDetallePlain());
                            }
                        }

                        VinculoService vinculoService = (VinculoService) ServiceFinder.findBean("VinculoService");
                        Vinculo vinculoC = new Vinculo();
                        vinculoC.setNvinculoid(vinculoService.getNextPK());
                        vinculoC.setNconocimientoid(conocimiento.getNconocimientoid());
                        vinculoC.setNconocimientovinc(tbaselegal.getNbaselegalid());
                        vinculoC.setNtipoconocimientovinc(Constante.BASELEGAL);
                        vinculoC.setDfechacreacion(new Date());
                        vinculoC.setVusuariocreacion(usuario.getVlogin());
                        vinculoService.saveOrUpdate(vinculoC);

                        List<Vinculo> vinculos = vinculoService.getVinculosByConocimiento(conocimiento.getNtipoconocimientoid());
                        VinculoHistService vinculoHistService = (VinculoHistService) ServiceFinder.findBean("VinculoHistService");
                        for (Vinculo vinc : vinculos) {
                            TvinculoHistId vinculoHistId = new TvinculoHistId();
                            vinculoHistId.setNvinculohid(vinculoHistService.getNextPK());
                            vinculoHistId.setNconocimientoid(thistorialId.getNconocimientoid());
                            vinculoHistId.setNhistorialid(thistorialId.getNhistorialid());
                            VinculoHist vinculoHist = new VinculoHist();
                            vinculoHist.setId(vinculoHistId);
                            vinculoHist.setNconocimientovinc(vinc.getNconocimientovinc());
                            vinculoHist.setDfechacreacion(new Date());
                            vinculoHist.setVusuariocreacion(usuario.getVlogin());
                            vinculoHistService.saveOrUpdate(vinculoHist);
                        }
                    }
                } else if (v.getNbaselegalid().toString().equals(Constante.ESTADO_BASELEGAL_DEROGADA)) {
                    ConocimientoService cservice = (ConocimientoService) ServiceFinder.findBean("ConocimientoService");
                    List<Consulta> listaConocimientos = cservice.getConcimientosByVinculoBaseLegalId(blvinculada.getNbaselegalid());
                    for (Consulta c : listaConocimientos) {
                        Conocimiento conocimiento = cservice.getConocimientoById(c.getId());
                        conocimiento.setNflgvinculo(BigDecimal.ONE);
                        conocimiento.setDfechamodificacion(new Date());
                        conocimiento.setVusuariomodificacion(usuario.getVlogin());
                    }
                }

                VinculoBaselegalHistorialService vserviceHist = (VinculoBaselegalHistorialService) ServiceFinder.findBean("VinculoBaselegalHistorialService");
                VinculoBaselegalHist vinculoHist = new VinculoBaselegalHist();
                vinculoHist.setNvinculohistid(vserviceHist.getNextPK());
                vinculoHist.setNhistorialid(baseHist.getNhistorialid());
                vinculoHist.setNbaselegalid(baseHist.getNbaselegalid());
                vinculoHist.setNbaselegalvinculadaid(v.getNbaselegalid());
                vinculoHist.setNtipovinculo(v.getNestadoid());
                vinculoHist.setDfechacreacion(new Date());
                vinculoHist.setVusuariocreacion(usuario.getVlogin());
                vserviceHist.saveOrUpdate(vinculoHist);
            }

            AsignacionService serviceasig = (AsignacionService) ServiceFinder.findBean("AsignacionService");
            this.getSelectedAsignacion().setNestadoid(BigDecimal.valueOf(Long.parseLong("2")));
            this.getSelectedAsignacion().setDfechaatencion(new Date());
            this.getSelectedAsignacion().setNaccionid(BigDecimal.valueOf(Long.parseLong("8")));
            serviceasig.saveOrUpdate(this.getSelectedAsignacion());
            loginMB.refreshNotifications();
            pagina = "/index.xhtml";
            return pagina;

        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
        return pagina;
    }

    public void toSolInfoBase(ActionEvent event) {
        try {
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
    }

    public String sendSiModBase() {
        String pagina = null;
        try {

            if (StringUtils.isBlank(this.getSelectedBaseLegal().getVmsjmoderador())) {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, Constante.SEVERETY_ALERTA, "Campo requerido. Ingrese el mensaje a enviar.");
                FacesContext.getCurrentInstance().addMessage(null, message);
                pagina = null;
            } else {
                LoginMB loginMB = (LoginMB) JSFUtils.getSessionAttribute("loginMB");
                User usuario = loginMB.getUser();
                if (this.getChkDestacado()) {
                    ConsultaService consultaService = (ConsultaService) ServiceFinder.findBean("ConsultaService");
                    HashMap filter = new HashMap();
                    filter.put("ntipoconocimientoid", Constante.BASELEGAL);
                    BigDecimal cant = consultaService.countDestacadosByTipoConocimiento(filter);
                    if (cant.intValue() >= 10) {
                        this.setListaDestacados(consultaService.getDestacadosByTipoConocimiento(filter));
                        RequestContext.getCurrentInstance().execute("PF('destDialog').show();");
                        return "";
                    }
                }
                if (this.getSelectedCategoria() != null) {
                    this.getSelectedBaseLegal().setNcategoriaid(this.getSelectedCategoria().getNcategoriaid());
                }
                BaseLegalService service = (BaseLegalService) ServiceFinder.findBean("BaseLegalService");
                this.getSelectedBaseLegal().setVnombre(StringUtils.capitalize(this.getSelectedBaseLegal().getVnombre()));
                this.getSelectedBaseLegal().setVnumero(this.getTipoNorma().concat(" - ").concat(StringUtils.upperCase(this.getNumeroNorma())));
                this.getSelectedBaseLegal().setNrangoid(this.getSelectedBaseLegal().getNrangoid());
                this.getSelectedBaseLegal().setNgobnacional(this.getChkGobNacional() ? BigDecimal.ONE : BigDecimal.ZERO);
                this.getSelectedBaseLegal().setNgobregional(this.getChkGobRegional() ? BigDecimal.ONE : BigDecimal.ZERO);
                this.getSelectedBaseLegal().setNgoblocal(this.getChkGobLocal() ? BigDecimal.ONE : BigDecimal.ZERO);
                this.getSelectedBaseLegal().setNmancomunidades(this.getChkMancomunidades() ? BigDecimal.ONE : BigDecimal.ZERO);
                this.getSelectedBaseLegal().setNdestacado(this.getChkDestacado() ? BigDecimal.ONE : BigDecimal.ZERO);
                this.getSelectedBaseLegal().setVsumilla(this.getSelectedBaseLegal().getVsumilla().trim());
                this.getSelectedBaseLegal().setDfechapublicacion(this.getSelectedBaseLegal().getDfechapublicacion());
                this.getSelectedBaseLegal().setVtema(this.getSelectedBaseLegal().getVtema());
                this.getSelectedBaseLegal().setVusuariomodificacion(usuario.getVlogin());
                this.getSelectedBaseLegal().setDfechamodificacion(new Date());
                this.getSelectedBaseLegal().setVmsjmoderador(this.getSelectedBaseLegal().getVmsjmoderador().toUpperCase());
                service.saveOrUpdate(this.getSelectedBaseLegal());

                BaseLegalHistorialService serviceHistorial = (BaseLegalHistorialService) ServiceFinder.findBean("BaseLegalHistorialService");
                BaselegalHist hist = serviceHistorial.getLastHistorialByBaselegal(this.getSelectedBaseLegal().getNbaselegalid());

                BaselegalHist baseHist = new BaselegalHist();
                baseHist.setNhistorialid(serviceHistorial.getNextPK());
                baseHist.setNbaselegalid(this.getSelectedBaseLegal().getNbaselegalid());
                baseHist.setNcategoriaid(this.getSelectedBaseLegal().getNcategoriaid());
                baseHist.setVnombre(this.getSelectedBaseLegal().getVnombre());
                baseHist.setVnumero(this.getSelectedBaseLegal().getVnumero());
                baseHist.setNrangoid(this.getSelectedBaseLegal().getNrangoid());
                baseHist.setNgobnacional(this.getSelectedBaseLegal().getNgobnacional());
                baseHist.setNgobregional(this.getSelectedBaseLegal().getNgobregional());
                baseHist.setNgoblocal(this.getSelectedBaseLegal().getNgoblocal());
                baseHist.setNmancomunidades(this.getSelectedBaseLegal().getNmancomunidades());
                baseHist.setNdestacado(this.getSelectedBaseLegal().getNdestacado());
                baseHist.setVsumilla(this.getSelectedBaseLegal().getVsumilla());
                baseHist.setDfechapublicacion(this.getSelectedBaseLegal().getDfechapublicacion());
                baseHist.setVtema(this.getSelectedBaseLegal().getVtema());
                baseHist.setNactivo(this.getSelectedBaseLegal().getNactivo());
                baseHist.setNestadoid(this.getSelectedBaseLegal().getNestadoid());
                baseHist.setNversion(BigDecimal.valueOf(hist.getNversion().intValue() + 1));
                baseHist.setVusuariocreacion(usuario.getVlogin());
                baseHist.setDfechacreacion(new Date());
                baseHist.setVusuariomodificacion(this.getSelectedBaseLegal().getVusuariomodificacion());
                baseHist.setDfechamodificacion(this.getSelectedBaseLegal().getDfechamodificacion());
                serviceHistorial.saveOrUpdate(baseHist);

                Tbaselegal tbaselegal = new Tbaselegal();
                BeanUtils.copyProperties(tbaselegal, this.getSelectedBaseLegal());

                String ruta0 = path + this.getSelectedBaseLegal().getNbaselegalid().toString() + "\\" + BigDecimal.ZERO.toString() + "\\";
                String ruta1 = path + this.getSelectedBaseLegal().getNbaselegalid().toString() + "\\" + baseHist.getNversion().toString() + "\\";

                ArchivoService aservice = (ArchivoService) ServiceFinder.findBean("ArchivoService");
                Archivo archivo = aservice.getArchivoByBaseLegal(this.getSelectedBaseLegal());
                if (this.getUploadFile() != null) {
                    archivo.setVnombre(this.getUploadFile().getFileName());
                    archivo.setVruta(ruta0 + archivo.getVnombre());
                    archivo.setVusuariomodificacion(usuario.getVlogin());
                    archivo.setDfechamodificacion(new Date());
                    aservice.saveOrUpdate(archivo);
                    saveFile(ruta0);
                }

                ArchivoHistorialService aserviceHist = (ArchivoHistorialService) ServiceFinder.findBean("ArchivoHistorialService");
                ArchivoHist aHist = aserviceHist.getLastArchivoHistByBaseLegalHist(baseHist);

                ArchivoHist archivoHist = new ArchivoHist();
                archivoHist.setNarchivohistid(aserviceHist.getNextPK());
                archivoHist.setNhistorialid(baseHist.getNhistorialid());
                archivoHist.setNbaselegalid(baseHist.getNbaselegalid());
                archivoHist.setVnombre(archivo.getVnombre());
                archivoHist.setVruta(ruta1 + archivo.getVnombre());
                archivoHist.setVusuariocreacion(usuario.getVlogin());
                archivoHist.setDfechacreacion(new Date());
                aserviceHist.saveOrUpdate(archivoHist);
                saveFile(ruta1);

                VinculoBaseLegalService vservice = (VinculoBaseLegalService) ServiceFinder.findBean("VinculoBaseLegalService");
                vservice.deleteByBaseLegal(this.getSelectedBaseLegal());
                for (BaseLegal v : this.getListaTarget()) {
                    TvinculoBaselegalId id = new TvinculoBaselegalId();
                    id.setNbaselegalid(tbaselegal.getNbaselegalid());
                    id.setNvinculoid(vservice.getNextPK());
                    VinculoBaselegal vinculo = new VinculoBaselegal();
                    vinculo.setId(id);
                    vinculo.setTbaselegal(tbaselegal);
                    vinculo.setNbaselegalvinculadaid(v.getNbaselegalid());
                    vinculo.setNtipovinculo(v.getNestadoid());
                    vinculo.setDfechacreacion(new Date());
                    vinculo.setVusuariocreacion(usuario.getVlogin());
                    vservice.saveOrUpdate(vinculo);

                    BaseLegal blvinculada = service.getBaselegalById(v.getNbaselegalid());
                    blvinculada.setNestadoid(v.getNestadoid());
                    blvinculada.setDfechamodificacion(new Date());
                    blvinculada.setVusuariomodificacion(usuario.getVlogin());
                    service.saveOrUpdate(blvinculada);

                    if (v.getNbaselegalid().toString().equals(Constante.ESTADO_BASELEGAL_MODIFICADA)
                            || v.getNbaselegalid().toString().equals(Constante.ESTADO_BASELEGAL_CONCORDADO)) {

                        ConocimientoService cservice = (ConocimientoService) ServiceFinder.findBean("ConocimientoService");
                        List<Consulta> listaConocimientos = cservice.getConcimientosByVinculoBaseLegalId(blvinculada.getNbaselegalid());
                        for (Consulta c : listaConocimientos) {
                            Conocimiento conocimiento = cservice.getConocimientoById(c.getId());
                            conocimiento.setDfechamodificacion(new Date());
                            conocimiento.setVusuariomodificacion(usuario.getVlogin());
                            String descHtml = GcmFileUtils.readStringFromFileServer(conocimiento.getVruta(), "html.txt");
                            String descPlain = GcmFileUtils.readStringFromFileServer(conocimiento.getVruta(), "plain.txt");
                            cservice.saveOrUpdate(conocimiento);

                            HistorialService historialService = (HistorialService) ServiceFinder.findBean("HistorialService");
                            Historial lastHistorial = historialService.getLastHistorialByConocimiento(conocimiento.getNconocimientoid());
                            int lastversion;
                            if (lastHistorial != null) {
                                lastversion = lastHistorial.getNnumversion().intValue();
                            } else {
                                lastversion = 0;
                            }
                            String newpath = "";
                            if (conocimiento.getNtipoconocimientoid().equals(Constante.BASELEGAL)) {
                                newpath = "bl/";
                            } else if (conocimiento.getNtipoconocimientoid().equals(Constante.BUENAPRACTICA)) {
                                newpath = "bp/";
                            } else if (conocimiento.getNtipoconocimientoid().equals(Constante.CONTENIDO)) {
                                newpath = "ct/";
                            } else if (conocimiento.getNtipoconocimientoid().equals(Constante.OPORTUNIDADMEJORA)) {
                                newpath = "om/";
                            } else if (conocimiento.getNtipoconocimientoid().equals(Constante.PREGUNTAS)) {
                                newpath = "pr/";
                            } else if (conocimiento.getNtipoconocimientoid().equals(Constante.WIKI)) {
                                newpath = "wk/";
                            }

                            String url = newpath.concat(conocimiento.getNconocimientoid().toString()).concat("/").concat(Integer.toString(lastversion + 1)).concat("/");

                            ThistorialId thistorialId = new ThistorialId();
                            thistorialId.setNconocimientoid(conocimiento.getNconocimientoid());
                            thistorialId.setNhistorialid(historialService.getNextPK());
                            Historial historial = new Historial();
                            historial.setId(thistorialId);
                            historial.setNtipoconocimientoid(conocimiento.getNtipoconocimientoid());
                            historial.setNcategoriaid(conocimiento.getNcategoriaid());
                            historial.setVtitulo(conocimiento.getVtitulo());
                            historial.setNactivo(BigDecimal.ONE);
                            historial.setNsituacionid(conocimiento.getNsituacionid());
                            historial.setVruta(url);
                            historial.setNnumversion(BigDecimal.valueOf(lastversion + 1));
                            historial.setDfechacreacion(new Date());
                            historial.setVusuariocreacion(usuario.getVlogin());
                            historialService.saveOrUpdate(historial);

                            GcmFileUtils.writeStringToFileServer(url, "html.txt", descHtml);
                            GcmFileUtils.writeStringToFileServer(url, "plain.txt", descPlain);

                            SeccionService seccionService = (SeccionService) ServiceFinder.findBean("SeccionService");
                            SeccionHistService seccionHistService = (SeccionHistService) ServiceFinder.findBean("SeccionHistService");
                            List<Seccion> listaSecc = seccionService.getSeccionesByConocimiento(conocimiento.getNconocimientoid());
                            if (!CollectionUtils.isEmpty(listaSecc)) {
                                String url0 = conocimiento.getVruta().concat("s");
                                String url1 = url.concat("s");
                                for (Seccion seccion : listaSecc) {
                                    seccion.setDetalleHtml(GcmFileUtils.readStringFromFileServer(seccion.getVruta(), "html.txt"));
                                    ruta0 = url0.concat(seccion.getNorden().toString()).concat("/");
                                    seccion.setVruta(ruta0);
                                    seccion.setDfechamodificacion(new Date());
                                    seccion.setVusuariomodificacion(usuario.getVlogin());
                                    seccionService.saveOrUpdate(seccion);

                                    seccion.setDetallePlain(Jsoup.parse(seccion.getDetalleHtml()).text());

                                    ruta1 = url1.concat(seccion.getNorden().toString()).concat("/");
                                    TseccionHistId tseccionHistId = new TseccionHistId();
                                    tseccionHistId.setNconocimientoid(thistorialId.getNconocimientoid());
                                    tseccionHistId.setNhistorialid(thistorialId.getNhistorialid());
                                    tseccionHistId.setNseccionhid(seccionHistService.getNextPK());
                                    SeccionHist seccionHist = new SeccionHist();
                                    seccionHist.setId(tseccionHistId);
                                    seccionHist.setNorden(seccion.getNorden());
                                    seccionHist.setVruta(ruta1);
                                    seccionHist.setVtitulo(seccion.getVtitulo());
                                    seccionHist.setVusuariocreacion(usuario.getVlogin());
                                    seccionHist.setDfechacreacion(new Date());
                                    seccionHistService.saveOrUpdate(seccionHist);

                                    GcmFileUtils.writeStringToFileServer(ruta1, "html.txt", seccion.getDetalleHtml());
                                    GcmFileUtils.writeStringToFileServer(ruta1, "plain.txt", seccion.getDetallePlain());
                                }
                            }

                            VinculoService vinculoService = (VinculoService) ServiceFinder.findBean("VinculoService");
                            Vinculo vinculoC = new Vinculo();
                            vinculoC.setNvinculoid(vinculoService.getNextPK());
                            vinculoC.setNconocimientoid(conocimiento.getNconocimientoid());
                            vinculoC.setNconocimientovinc(tbaselegal.getNbaselegalid());
                            vinculoC.setNtipoconocimientovinc(Constante.BASELEGAL);
                            vinculoC.setDfechacreacion(new Date());
                            vinculoC.setVusuariocreacion(usuario.getVlogin());
                            vinculoService.saveOrUpdate(vinculoC);

                            List<Vinculo> vinculos = vinculoService.getVinculosByConocimiento(conocimiento.getNtipoconocimientoid());
                            VinculoHistService vinculoHistService = (VinculoHistService) ServiceFinder.findBean("VinculoHistService");
                            for (Vinculo vinc : vinculos) {
                                TvinculoHistId vinculoHistId = new TvinculoHistId();
                                vinculoHistId.setNvinculohid(vinculoHistService.getNextPK());
                                vinculoHistId.setNconocimientoid(thistorialId.getNconocimientoid());
                                vinculoHistId.setNhistorialid(thistorialId.getNhistorialid());
                                VinculoHist vinculoHist = new VinculoHist();
                                vinculoHist.setId(vinculoHistId);
                                vinculoHist.setNconocimientovinc(vinc.getNconocimientovinc());
                                vinculoHist.setDfechacreacion(new Date());
                                vinculoHist.setVusuariocreacion(usuario.getVlogin());
                                vinculoHistService.saveOrUpdate(vinculoHist);
                            }
                        }
                    } else if (v.getNbaselegalid().toString().equals(Constante.ESTADO_BASELEGAL_DEROGADA)) {
                        ConocimientoService cservice = (ConocimientoService) ServiceFinder.findBean("ConocimientoService");
                        List<Consulta> listaConocimientos = cservice.getConcimientosByVinculoBaseLegalId(blvinculada.getNbaselegalid());
                        for (Consulta c : listaConocimientos) {
                            Conocimiento conocimiento = cservice.getConocimientoById(c.getId());
                            conocimiento.setNflgvinculo(BigDecimal.ONE);
                            conocimiento.setDfechamodificacion(new Date());
                            conocimiento.setVusuariomodificacion(usuario.getVlogin());
                        }
                    }

                    VinculoBaselegalHistorialService vserviceHist = (VinculoBaselegalHistorialService) ServiceFinder.findBean("VinculoBaselegalHistorialService");
                    VinculoBaselegalHist vinculoHist = new VinculoBaselegalHist();
                    vinculoHist.setNvinculohistid(vserviceHist.getNextPK());
                    vinculoHist.setNhistorialid(baseHist.getNhistorialid());
                    vinculoHist.setNbaselegalid(baseHist.getNbaselegalid());
                    vinculoHist.setNbaselegalvinculadaid(v.getNbaselegalid());
                    vinculoHist.setNtipovinculo(v.getNestadoid());
                    vinculoHist.setDfechacreacion(new Date());
                    vinculoHist.setVusuariocreacion(usuario.getVlogin());
                    vserviceHist.saveOrUpdate(vinculoHist);
                }

                AsignacionService serviceasig = (AsignacionService) ServiceFinder.findBean("AsignacionService");
                this.getSelectedAsignacion().setNestadoid(BigDecimal.valueOf(Long.parseLong("2")));
                this.getSelectedAsignacion().setDfechaatencion(new Date());
                this.getSelectedAsignacion().setNaccionid(BigDecimal.valueOf(Long.parseLong("9")));
                serviceasig.saveOrUpdate(this.getSelectedAsignacion());

                Asignacion asignacion = new Asignacion();
                asignacion.setNasignacionid(serviceasig.getNextPK());
                asignacion.setNtipoconocimientoid(Constante.BASELEGAL);
                asignacion.setNconocimientoid(this.getSelectedBaseLegal().getNbaselegalid());
                asignacion.setNestadoid(BigDecimal.valueOf(Long.parseLong("1")));
                asignacion.setNusuarioid(serviceasig.getUserCreacionByBaseLegal(this.getSelectedBaseLegal().getNbaselegalid()));                
                asignacion.setDfechaasignacion(new Date());
                asignacion.setDfechacreacion(new Date());
                serviceasig.saveOrUpdate(asignacion);

                loginMB.refreshNotifications();

                pagina = "/index.xhtml";
            }

        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
        return pagina;
    }

    public void toCancelSiModBase(ActionEvent event) {
        try {
            RequestContext.getCurrentInstance().execute("PF('siModBaseDialog').hide();");
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
    }

    public void toRespSolInfoBase(ActionEvent event) {
        try {
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
    }

    public String sendSiRespBase() {
        String pagina = null;
        try {

            if (StringUtils.isBlank(this.getSelectedBaseLegal().getVmsjusuariocreacion())) {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, Constante.SEVERETY_ALERTA, "Campo requerido. Ingrese el mensaje a enviar.");
                FacesContext.getCurrentInstance().addMessage(null, message);
                pagina = null;
            } else {
                LoginMB loginMB = (LoginMB) JSFUtils.getSessionAttribute("loginMB");
                User usuario = loginMB.getUser();
                if (this.getChkDestacado()) {
                    ConsultaService consultaService = (ConsultaService) ServiceFinder.findBean("ConsultaService");
                    HashMap filter = new HashMap();
                    filter.put("ntipoconocimientoid", Constante.BASELEGAL);
                    BigDecimal cant = consultaService.countDestacadosByTipoConocimiento(filter);
                    if (cant.intValue() >= 10) {
                        this.setListaDestacados(consultaService.getDestacadosByTipoConocimiento(filter));
                        RequestContext.getCurrentInstance().execute("PF('destDialog').show();");
                        return "";
                    }
                }
                if (!CollectionUtils.isEmpty(this.getListaTarget())) {
                    for (BaseLegal v : this.getListaTarget()) {
                        if(v.getNestadoid().equals(BigDecimal.ZERO)) {
                            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR.", "Debe seleccionar el estado de todos los vínculos agregados.");
                            FacesContext.getCurrentInstance().addMessage(null, message);
                            return "";
                        }
                    }
                }
                if (this.getSelectedCategoria() != null) {
                    this.getSelectedBaseLegal().setNcategoriaid(this.getSelectedCategoria().getNcategoriaid());
                }
                BaseLegalService service = (BaseLegalService) ServiceFinder.findBean("BaseLegalService");
                this.getSelectedBaseLegal().setVnombre(StringUtils.capitalize(this.getSelectedBaseLegal().getVnombre()));
                this.getSelectedBaseLegal().setVnumero(this.getTipoNorma().concat(" - ").concat(StringUtils.upperCase(this.getNumeroNorma())));
                this.getSelectedBaseLegal().setNrangoid(this.getSelectedBaseLegal().getNrangoid());
                this.getSelectedBaseLegal().setNgobnacional(this.getChkGobNacional() ? BigDecimal.ONE : BigDecimal.ZERO);
                this.getSelectedBaseLegal().setNgobregional(this.getChkGobRegional() ? BigDecimal.ONE : BigDecimal.ZERO);
                this.getSelectedBaseLegal().setNgoblocal(this.getChkGobLocal() ? BigDecimal.ONE : BigDecimal.ZERO);
                this.getSelectedBaseLegal().setNmancomunidades(this.getChkMancomunidades() ? BigDecimal.ONE : BigDecimal.ZERO);
                this.getSelectedBaseLegal().setNdestacado(this.getChkDestacado() ? BigDecimal.ONE : BigDecimal.ZERO);
                this.getSelectedBaseLegal().setVsumilla(this.getSelectedBaseLegal().getVsumilla().trim());
                this.getSelectedBaseLegal().setDfechapublicacion(this.getSelectedBaseLegal().getDfechapublicacion());
                this.getSelectedBaseLegal().setVtema(this.getSelectedBaseLegal().getVtema());
                this.getSelectedBaseLegal().setVusuariomodificacion(usuario.getVlogin());
                this.getSelectedBaseLegal().setDfechamodificacion(new Date());
                this.getSelectedBaseLegal().setVmsjusuariocreacion(this.getSelectedBaseLegal().getVmsjusuariocreacion().toUpperCase());
                service.saveOrUpdate(this.getSelectedBaseLegal());

                BaseLegalHistorialService serviceHistorial = (BaseLegalHistorialService) ServiceFinder.findBean("BaseLegalHistorialService");
                BaselegalHist hist = serviceHistorial.getLastHistorialByBaselegal(this.getSelectedBaseLegal().getNbaselegalid());

                BaselegalHist baseHist = new BaselegalHist();
                baseHist.setNhistorialid(serviceHistorial.getNextPK());
                baseHist.setNbaselegalid(this.getSelectedBaseLegal().getNbaselegalid());
                baseHist.setNcategoriaid(this.getSelectedBaseLegal().getNcategoriaid());
                baseHist.setVnombre(this.getSelectedBaseLegal().getVnombre());
                baseHist.setVnumero(this.getSelectedBaseLegal().getVnumero());
                baseHist.setNrangoid(this.getSelectedBaseLegal().getNrangoid());
                baseHist.setNgobnacional(this.getSelectedBaseLegal().getNgobnacional());
                baseHist.setNgobregional(this.getSelectedBaseLegal().getNgobregional());
                baseHist.setNgoblocal(this.getSelectedBaseLegal().getNgoblocal());
                baseHist.setNmancomunidades(this.getSelectedBaseLegal().getNmancomunidades());
                baseHist.setNdestacado(this.getSelectedBaseLegal().getNdestacado());
                baseHist.setVsumilla(this.getSelectedBaseLegal().getVsumilla());
                baseHist.setDfechapublicacion(this.getSelectedBaseLegal().getDfechapublicacion());
                baseHist.setVtema(this.getSelectedBaseLegal().getVtema());
                baseHist.setNactivo(this.getSelectedBaseLegal().getNactivo());
                baseHist.setNestadoid(this.getSelectedBaseLegal().getNestadoid());
                baseHist.setNversion(BigDecimal.valueOf(hist.getNversion().intValue() + 1));
                baseHist.setVusuariocreacion(usuario.getVlogin());
                baseHist.setDfechacreacion(new Date());
                baseHist.setVusuariomodificacion(this.getSelectedBaseLegal().getVusuariomodificacion());
                baseHist.setDfechamodificacion(this.getSelectedBaseLegal().getDfechamodificacion());
                serviceHistorial.saveOrUpdate(baseHist);

                Tbaselegal tbaselegal = new Tbaselegal();
                BeanUtils.copyProperties(tbaselegal, this.getSelectedBaseLegal());

                String ruta0 = path + this.getSelectedBaseLegal().getNbaselegalid().toString() + "\\" + BigDecimal.ZERO.toString() + "\\";
                String ruta1 = path + this.getSelectedBaseLegal().getNbaselegalid().toString() + "\\" + baseHist.getNversion().toString() + "\\";

                ArchivoService aservice = (ArchivoService) ServiceFinder.findBean("ArchivoService");
                Archivo archivo = aservice.getArchivoByBaseLegal(this.getSelectedBaseLegal());
                if (this.getUploadFile() != null) {
                    archivo.setVnombre(this.getUploadFile().getFileName());
                    archivo.setVruta(ruta0 + archivo.getVnombre());
                    archivo.setVusuariomodificacion(usuario.getVlogin());
                    archivo.setDfechamodificacion(new Date());
                    aservice.saveOrUpdate(archivo);
                    saveFile(ruta0);
                }

                ArchivoHistorialService aserviceHist = (ArchivoHistorialService) ServiceFinder.findBean("ArchivoHistorialService");
                ArchivoHist aHist = aserviceHist.getLastArchivoHistByBaseLegalHist(baseHist);

                ArchivoHist archivoHist = new ArchivoHist();
                archivoHist.setNarchivohistid(aserviceHist.getNextPK());
                archivoHist.setNhistorialid(baseHist.getNhistorialid());
                archivoHist.setNbaselegalid(baseHist.getNbaselegalid());
                archivoHist.setVnombre(archivo.getVnombre());
                archivoHist.setVruta(ruta1 + archivo.getVnombre());
                archivoHist.setVusuariocreacion(usuario.getVlogin());
                archivoHist.setDfechacreacion(new Date());
                aserviceHist.saveOrUpdate(archivoHist);
                saveFile(ruta1);

                VinculoBaseLegalService vservice = (VinculoBaseLegalService) ServiceFinder.findBean("VinculoBaseLegalService");
                vservice.deleteByBaseLegal(this.getSelectedBaseLegal());
                for (BaseLegal v : this.getListaTarget()) {
                    TvinculoBaselegalId id = new TvinculoBaselegalId();
                    id.setNbaselegalid(tbaselegal.getNbaselegalid());
                    id.setNvinculoid(vservice.getNextPK());
                    VinculoBaselegal vinculo = new VinculoBaselegal();
                    vinculo.setId(id);
                    vinculo.setTbaselegal(tbaselegal);
                    vinculo.setNbaselegalvinculadaid(v.getNbaselegalid());
                    vinculo.setNtipovinculo(v.getNestadoid());
                    vinculo.setDfechacreacion(new Date());
                    vinculo.setVusuariocreacion(usuario.getVlogin());
                    vservice.saveOrUpdate(vinculo);

                    BaseLegal blvinculada = service.getBaselegalById(v.getNbaselegalid());
                    blvinculada.setNestadoid(v.getNestadoid());
                    blvinculada.setDfechamodificacion(new Date());
                    blvinculada.setVusuariomodificacion(usuario.getVlogin());
                    service.saveOrUpdate(blvinculada);

                    if (v.getNbaselegalid().toString().equals(Constante.ESTADO_BASELEGAL_MODIFICADA)
                            || v.getNbaselegalid().toString().equals(Constante.ESTADO_BASELEGAL_CONCORDADO)) {

                        ConocimientoService cservice = (ConocimientoService) ServiceFinder.findBean("ConocimientoService");
                        List<Consulta> listaConocimientos = cservice.getConcimientosByVinculoBaseLegalId(blvinculada.getNbaselegalid());
                        for (Consulta c : listaConocimientos) {
                            Conocimiento conocimiento = cservice.getConocimientoById(c.getId());
                            conocimiento.setDfechamodificacion(new Date());
                            conocimiento.setVusuariomodificacion(usuario.getVlogin());
                            String descHtml = GcmFileUtils.readStringFromFileServer(conocimiento.getVruta(), "html.txt");
                            String descPlain = GcmFileUtils.readStringFromFileServer(conocimiento.getVruta(), "plain.txt");
                            cservice.saveOrUpdate(conocimiento);

                            HistorialService historialService = (HistorialService) ServiceFinder.findBean("HistorialService");
                            Historial lastHistorial = historialService.getLastHistorialByConocimiento(conocimiento.getNconocimientoid());
                            int lastversion;
                            if (lastHistorial != null) {
                                lastversion = lastHistorial.getNnumversion().intValue();
                            } else {
                                lastversion = 0;
                            }
                            String newpath = "";
                            if (conocimiento.getNtipoconocimientoid().equals(Constante.BASELEGAL)) {
                                newpath = "bl/";
                            } else if (conocimiento.getNtipoconocimientoid().equals(Constante.BUENAPRACTICA)) {
                                newpath = "bp/";
                            } else if (conocimiento.getNtipoconocimientoid().equals(Constante.CONTENIDO)) {
                                newpath = "ct/";
                            } else if (conocimiento.getNtipoconocimientoid().equals(Constante.OPORTUNIDADMEJORA)) {
                                newpath = "om/";
                            } else if (conocimiento.getNtipoconocimientoid().equals(Constante.PREGUNTAS)) {
                                newpath = "pr/";
                            } else if (conocimiento.getNtipoconocimientoid().equals(Constante.WIKI)) {
                                newpath = "wk/";
                            }

                            String url = newpath.concat(conocimiento.getNconocimientoid().toString()).concat("/").concat(Integer.toString(lastversion + 1)).concat("/");

                            ThistorialId thistorialId = new ThistorialId();
                            thistorialId.setNconocimientoid(conocimiento.getNconocimientoid());
                            thistorialId.setNhistorialid(historialService.getNextPK());
                            Historial historial = new Historial();
                            historial.setId(thistorialId);
                            historial.setNtipoconocimientoid(conocimiento.getNtipoconocimientoid());
                            historial.setNcategoriaid(conocimiento.getNcategoriaid());
                            historial.setVtitulo(conocimiento.getVtitulo());
                            historial.setNactivo(BigDecimal.ONE);
                            historial.setNsituacionid(conocimiento.getNsituacionid());
                            historial.setVruta(url);
                            historial.setNnumversion(BigDecimal.valueOf(lastversion + 1));
                            historial.setDfechacreacion(new Date());
                            historial.setVusuariocreacion(usuario.getVlogin());
                            historialService.saveOrUpdate(historial);

                            GcmFileUtils.writeStringToFileServer(url, "html.txt", descHtml);
                            GcmFileUtils.writeStringToFileServer(url, "plain.txt", descPlain);

                            SeccionService seccionService = (SeccionService) ServiceFinder.findBean("SeccionService");
                            SeccionHistService seccionHistService = (SeccionHistService) ServiceFinder.findBean("SeccionHistService");
                            List<Seccion> listaSecc = seccionService.getSeccionesByConocimiento(conocimiento.getNconocimientoid());
                            if (!CollectionUtils.isEmpty(listaSecc)) {
                                String url0 = conocimiento.getVruta().concat("s");
                                String url1 = url.concat("s");
                                for (Seccion seccion : listaSecc) {
                                    seccion.setDetalleHtml(GcmFileUtils.readStringFromFileServer(seccion.getVruta(), "html.txt"));
                                    ruta0 = url0.concat(seccion.getNorden().toString()).concat("/");
                                    seccion.setVruta(ruta0);
                                    seccion.setDfechamodificacion(new Date());
                                    seccion.setVusuariomodificacion(usuario.getVlogin());
                                    seccionService.saveOrUpdate(seccion);

                                    seccion.setDetallePlain(Jsoup.parse(seccion.getDetalleHtml()).text());

                                    ruta1 = url1.concat(seccion.getNorden().toString()).concat("/");
                                    TseccionHistId tseccionHistId = new TseccionHistId();
                                    tseccionHistId.setNconocimientoid(thistorialId.getNconocimientoid());
                                    tseccionHistId.setNhistorialid(thistorialId.getNhistorialid());
                                    tseccionHistId.setNseccionhid(seccionHistService.getNextPK());
                                    SeccionHist seccionHist = new SeccionHist();
                                    seccionHist.setId(tseccionHistId);
                                    seccionHist.setNorden(seccion.getNorden());
                                    seccionHist.setVruta(ruta1);
                                    seccionHist.setVtitulo(seccion.getVtitulo());
                                    seccionHist.setVusuariocreacion(usuario.getVlogin());
                                    seccionHist.setDfechacreacion(new Date());
                                    seccionHistService.saveOrUpdate(seccionHist);

                                    GcmFileUtils.writeStringToFileServer(ruta1, "html.txt", seccion.getDetalleHtml());
                                    GcmFileUtils.writeStringToFileServer(ruta1, "plain.txt", seccion.getDetallePlain());
                                }
                            }

                            VinculoService vinculoService = (VinculoService) ServiceFinder.findBean("VinculoService");
                            Vinculo vinculoC = new Vinculo();
                            vinculoC.setNvinculoid(vinculoService.getNextPK());
                            vinculoC.setNconocimientoid(conocimiento.getNconocimientoid());
                            vinculoC.setNconocimientovinc(tbaselegal.getNbaselegalid());
                            vinculoC.setNtipoconocimientovinc(Constante.BASELEGAL);
                            vinculoC.setDfechacreacion(new Date());
                            vinculoC.setVusuariocreacion(usuario.getVlogin());
                            vinculoService.saveOrUpdate(vinculoC);

                            List<Vinculo> vinculos = vinculoService.getVinculosByConocimiento(conocimiento.getNtipoconocimientoid());
                            VinculoHistService vinculoHistService = (VinculoHistService) ServiceFinder.findBean("VinculoHistService");
                            for (Vinculo vinc : vinculos) {
                                TvinculoHistId vinculoHistId = new TvinculoHistId();
                                vinculoHistId.setNvinculohid(vinculoHistService.getNextPK());
                                vinculoHistId.setNconocimientoid(thistorialId.getNconocimientoid());
                                vinculoHistId.setNhistorialid(thistorialId.getNhistorialid());
                                VinculoHist vinculoHist = new VinculoHist();
                                vinculoHist.setId(vinculoHistId);
                                vinculoHist.setNconocimientovinc(vinc.getNconocimientovinc());
                                vinculoHist.setDfechacreacion(new Date());
                                vinculoHist.setVusuariocreacion(usuario.getVlogin());
                                vinculoHistService.saveOrUpdate(vinculoHist);
                            }
                        }
                    } else if (v.getNbaselegalid().toString().equals(Constante.ESTADO_BASELEGAL_DEROGADA)) {
                        ConocimientoService cservice = (ConocimientoService) ServiceFinder.findBean("ConocimientoService");
                        List<Consulta> listaConocimientos = cservice.getConcimientosByVinculoBaseLegalId(blvinculada.getNbaselegalid());
                        for (Consulta c : listaConocimientos) {
                            Conocimiento conocimiento = cservice.getConocimientoById(c.getId());
                            conocimiento.setNflgvinculo(BigDecimal.ONE);
                            conocimiento.setDfechamodificacion(new Date());
                            conocimiento.setVusuariomodificacion(usuario.getVlogin());
                        }
                    }

                    VinculoBaselegalHistorialService vserviceHist = (VinculoBaselegalHistorialService) ServiceFinder.findBean("VinculoBaselegalHistorialService");
                    VinculoBaselegalHist vinculoHist = new VinculoBaselegalHist();
                    vinculoHist.setNvinculohistid(vserviceHist.getNextPK());
                    vinculoHist.setNhistorialid(baseHist.getNhistorialid());
                    vinculoHist.setNbaselegalid(baseHist.getNbaselegalid());
                    vinculoHist.setNbaselegalvinculadaid(v.getNbaselegalid());
                    vinculoHist.setNtipovinculo(v.getNestadoid());
                    vinculoHist.setDfechacreacion(new Date());
                    vinculoHist.setVusuariocreacion(usuario.getVlogin());
                    vserviceHist.saveOrUpdate(vinculoHist);
                }

                AsignacionService serviceasig = (AsignacionService) ServiceFinder.findBean("AsignacionService");
                this.getSelectedAsignacion().setNestadoid(BigDecimal.valueOf(Long.parseLong("2")));
                this.getSelectedAsignacion().setDfechaatencion(new Date());
                this.getSelectedAsignacion().setNaccionid(BigDecimal.valueOf(Long.parseLong("10")));
                serviceasig.saveOrUpdate(this.getSelectedAsignacion());

                Asignacion asignacion = new Asignacion();
                asignacion.setNasignacionid(serviceasig.getNextPK());
                asignacion.setNtipoconocimientoid(Constante.BASELEGAL);
                asignacion.setNconocimientoid(this.getSelectedBaseLegal().getNbaselegalid());
                asignacion.setNestadoid(BigDecimal.valueOf(Long.parseLong("1")));
                CategoriaService categoriaService = (CategoriaService) ServiceFinder.findBean("CategoriaService");
                asignacion.setNusuarioid(categoriaService.getCategoriaById(this.getSelectedBaseLegal().getNcategoriaid()).getNmoderador());
                asignacion.setDfechaasignacion(new Date());
                asignacion.setDfechacreacion(new Date());
                serviceasig.saveOrUpdate(asignacion);

                loginMB.refreshNotifications();

                pagina = "/index.xhtml";
            }

        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
        return pagina;
    }

    public void toCancelSiRespBase(ActionEvent event) {
        try {
            RequestContext.getCurrentInstance().execute("PF('siRespBaseDialog').show();");
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
    }

    public void handleUploadFile(FileUploadEvent event) {
        try {
            if (event != null) {
                UploadedFile f = event.getFile();
                if (f != null) {

                    this.setUploadFile(f);
                    ResourceBundle bundle = ResourceBundle.getBundle(Parameters.getParameters());
                    File direc = new File(bundle.getString("temppath"));
                    direc.mkdirs();
                    this.setFile(new File(bundle.getString("temppath"), f.getFileName()));
                    FileOutputStream fileOutStream = new FileOutputStream(this.getFile());
                    fileOutStream.write(f.getContents());
                    fileOutStream.flush();
                    fileOutStream.close();
                    this.setContent(new DefaultStreamedContent(f.getInputstream(), f.getContentType(), f.getFileName()));
                }
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
    }

    public void saveFileCT(ArchivoConocimiento archivoconocimiento) {
        try {

            ResourceBundle bundle = ResourceBundle.getBundle(Parameters.getParameters());
            String id = archivoconocimiento.getNconocimientoid().toString();
            String version = archivoconocimiento.getNversion().toString();
            Integer version_ant_s = Integer.parseInt(archivoconocimiento.getNversion().toString()) - 1;
            String version_ant_i = version_ant_s.toString();
            File direc = new File(bundle.getString("path") + "ct" + '/' + id + '/' + version);
            File direct = new File(bundle.getString("path") + "ct" + '/' + id + '/' + version_ant_i + '/' + archivoconocimiento.getVnombre());
            direc.mkdirs();

            this.setFile(new File(bundle.getString("path") + "ct" + '/' + id + '/' + version, archivoconocimiento.getVnombre()));

            FileOutputStream fileOutStream = new FileOutputStream(this.getFile());
            if (direct.exists()) {
                fileOutStream.write(FileUtils.readFileToByteArray(new File(bundle.getString("path") + "ct" + '/' + id + '/' + version_ant_i, archivoconocimiento.getVnombre())));
            } else {
                fileOutStream.write(FileUtils.readFileToByteArray(new File(bundle.getString("temppath"), archivoconocimiento.getVnombre())));
            }
            fileOutStream.flush();
            fileOutStream.close();
            File temp = new File(bundle.getString("temppath"), archivoconocimiento.getVnombre());
            temp.delete();
            //}

        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
    }

    public void saveFile(String newPath) {
        try {
            if (this.getUploadFile() != null) {
                File direc = new File(newPath);
                direc.mkdirs();
                this.setFile(new File(newPath, this.getUploadFile().getFileName()));
                FileOutputStream fileOutStream = new FileOutputStream(this.getFile());
                fileOutStream.write(this.getUploadFile().getContents());
                fileOutStream.flush();
                fileOutStream.close();
                File temp = new File(temppath, this.getUploadFile().getFileName());
                temp.delete();
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
    }
    
    public void onTransferBL(TransferEvent event) {
        int index;
        try {
            if (event != null) {
                if (event.isAdd()) {
                    Collections.sort(this.getListaSource(), BaseLegal.Comparators.ID);
                    for (BaseLegal ele : (List<BaseLegal>) event.getItems()) {
                        index = Collections.binarySearch(this.getListaSource(), ele, BaseLegal.Comparators.ID);
                        if (this.getListaTarget() == null) {
                            this.setListaTarget(new ArrayList<BaseLegal>());
                        }
                        this.getListaTarget().add(this.getListaSource().get(index));
                        this.getListaSource().remove(index);
                    }
                }
                if (event.isRemove()) {
                    Collections.sort(this.getListaTarget(), BaseLegal.Comparators.ID);
                    for (BaseLegal ele : (List<BaseLegal>) event.getItems()) {
                        index = Collections.binarySearch(this.getListaTarget(), ele, BaseLegal.Comparators.ID);
                        if (this.getListaSource() == null) {
                            this.setListaSource(new ArrayList<BaseLegal>());
                        }
                        this.getListaSource().add(this.getListaTarget().get(index));
                        this.getListaTarget().remove(index);
                    }
                }
            }
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
    }

    public void onTransfer(TransferEvent event) {
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
            }
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
    }

    public void saveContenidoEdit(ActionEvent event) {
        String pagina = null;
        try {
            LoginMB loginMB = (LoginMB) JSFUtils.getSessionAttribute("loginMB");
            User user_savecontenido = loginMB.getUser();
            //this.setContenidoHtml(JSFUtils.getRequestParameter("descHtml"));
            if (this.getChkDestacado()) {
                ConsultaService consultaService = (ConsultaService) ServiceFinder.findBean("ConsultaService");
                HashMap filter = new HashMap();
                filter.put("ntipoconocimientoid", Constante.CONTENIDO);
                BigDecimal cant = consultaService.countDestacadosByTipoConocimiento(filter);
                if (cant.intValue() >= 10) {
                    this.setListaDestacados(consultaService.getDestacadosByTipoConocimiento(filter));
                    RequestContext.getCurrentInstance().execute("PF('destDialog').show();");
                    return;
                }
            }
            ContenidoService service = (ContenidoService) ServiceFinder.findBean("ContenidoService");
            this.setContenidoPlain(Jsoup.parse(this.getContenidoHtml()).text());
            this.getSelectedContenido().setVtitulo(this.getSelectedContenido().getVtitulo().trim());
            this.getSelectedContenido().setVdescripcion(this.getSelectedContenido().getVdescripcion().trim());
            if (this.getContenidoPlain().length() < 400) {
                this.getSelectedContenido().setVcontenido(StringUtils.capitalize(this.getContenidoPlain()));
            } else {
                this.getSelectedContenido().setVcontenido(StringUtils.capitalize(this.getContenidoPlain().substring(0, 400)));
            }

            if (this.getSelectedCategoria() == null) {
                this.getSelectedContenido().setNcategoriaid(this.getSelectedContenido().getNcategoriaid());
                cat_nueva = this.getSelectedContenido().getNcategoriaid();
            } else {
                this.getSelectedContenido().setNcategoriaid(this.getSelectedContenido().getNcategoriaid());
                cat_nueva = this.getSelectedContenido().getNcategoriaid();
            }

            this.getSelectedContenido().setDfechamodificacion(new Date());
            this.getSelectedContenido().setVusuariomodificacion(user_savecontenido.getVlogin());
            service.saveOrUpdate(this.getSelectedContenido());

            GcmFileUtils.writeStringToFileServer(this.getSelectedContenido().getVruta(), "html.txt", this.getContenidoHtml());
            GcmFileUtils.writeStringToFileServer(this.getSelectedContenido().getVruta(), "plain.txt", this.getContenidoPlain());

            HistorialService historialService = (HistorialService) ServiceFinder.findBean("HistorialService");
            Historial lastHistorial = historialService.getLastHistorialByConocimiento(this.getSelectedContenido().getNconocimientoid());
            int lastversion;
            if (lastHistorial != null) {
                lastversion = lastHistorial.getNnumversion().intValue();
            } else {
                lastversion = 0;
            }

            String url = this.path.concat(this.getSelectedContenido().getNconocimientoid().toString()).concat("/").concat(Integer.toString(lastversion + 1)).concat("/");

            ThistorialId thistorialId = new ThistorialId();
            thistorialId.setNconocimientoid(this.getSelectedContenido().getNconocimientoid());
            thistorialId.setNhistorialid(historialService.getNextPK());
            Historial historial = new Historial();
            historial.setId(thistorialId);
            historial.setNtipoconocimientoid(Constante.CONTENIDO);
            historial.setNcategoriaid(this.getSelectedCategoria().getNcategoriaid());
            historial.setVdescripcion(this.getSelectedContenido().getVdescripcion().trim());
            historial.setVtitulo(this.getSelectedContenido().getVtitulo());
            if (this.getContenidoPlain().length() < 400) {
                historial.setVcontenido(StringUtils.capitalize(this.getContenidoPlain()));
            } else {
                historial.setVcontenido(StringUtils.capitalize(this.getContenidoPlain().substring(0, 400)));
            }
            historial.setNactivo(BigDecimal.ONE);
            historial.setNsituacionid(this.getSelectedContenido().getNsituacionid());
            historial.setVruta(url);
            historial.setNnumversion(BigDecimal.valueOf(lastversion + 1));
            historial.setDfechacreacion(new Date());
            historial.setVmsjsolicita(this.getSelectedContenido().getVmsjsolicita());
            historial.setVmsjrespuesta(this.getSelectedContenido().getVmsjrespuesta());
            historial.setVusuariocreacion(user_savecontenido.getVlogin());
            historialService.saveOrUpdate(historial);

            GcmFileUtils.writeStringToFileServer(url, "html.txt", this.getContenidoHtml());
            GcmFileUtils.writeStringToFileServer(url, "plain.txt", this.getContenidoPlain());

            this.setListaTargetVinculos(new ArrayList<Consulta>());

            if (!CollectionUtils.isEmpty(this.getListaTargetVinculosBL())) {
                this.getListaTargetVinculos().addAll(this.getListaTargetVinculosBL());
            }
            if (!CollectionUtils.isEmpty(this.getListaTargetVinculosBP())) {
                this.getListaTargetVinculos().addAll(this.getListaTargetVinculosBP());
            }
            if (!CollectionUtils.isEmpty(this.getListaTargetVinculosCT())) {
                this.getListaTargetVinculos().addAll(this.getListaTargetVinculosCT());
            }
            if (!CollectionUtils.isEmpty(this.getListaTargetVinculosOM())) {
                this.getListaTargetVinculos().addAll(this.getListaTargetVinculosOM());
            }
            if (!CollectionUtils.isEmpty(this.getListaTargetVinculosPR())) {
                this.getListaTargetVinculos().addAll(this.getListaTargetVinculosPR());
            }
            if (!CollectionUtils.isEmpty(this.getListaTargetVinculosWK())) {
                this.getListaTargetVinculos().addAll(this.getListaTargetVinculosWK());
            }

            if (org.apache.commons.collections.CollectionUtils.isNotEmpty(this.getListaTargetVinculos())) {
                VinculoService vinculoService = (VinculoService) ServiceFinder.findBean("VinculoService");
                VinculoHistService vinculoHistService = (VinculoHistService) ServiceFinder.findBean("VinculoHistService");
                service.delete(this.getSelectedContenido().getNconocimientoid());
                for (Consulta consulta : this.getListaTargetVinculos()) {
                    Vinculo vinculo = new Vinculo();
                    vinculo.setNvinculoid(vinculoService.getNextPK());
                    vinculo.setNconocimientoid(this.getSelectedContenido().getNconocimientoid());
                    vinculo.setNconocimientovinc(consulta.getIdconocimiento());
                    vinculo.setNtipoconocimientovinc(consulta.getIdTipoConocimiento());
                    vinculo.setDfechacreacion(new Date());
                    vinculo.setVusuariocreacion(user_savecontenido.getVlogin());
                    vinculoService.saveOrUpdate(vinculo);

                    TvinculoHistId vinculoHistId = new TvinculoHistId();
                    vinculoHistId.setNvinculohid(vinculoHistService.getNextPK());
                    vinculoHistId.setNconocimientoid(thistorialId.getNconocimientoid());
                    vinculoHistId.setNhistorialid(thistorialId.getNhistorialid());
                    VinculoHist vinculoHist = new VinculoHist();
                    vinculoHist.setId(vinculoHistId);
                    vinculoHist.setNconocimientovinc(vinculo.getNconocimientovinc());
                    vinculoHist.setNtipoconocimientovinc(vinculo.getNtipoconocimientovinc());
                    vinculoHist.setDfechacreacion(new Date());
                    vinculoHist.setVusuariocreacion(user_savecontenido.getVlogin());
                    vinculoHistService.saveOrUpdate(vinculoHist);
                }
            }

            ArchivoConocimientoService aservice = (ArchivoConocimientoService) ServiceFinder.findBean("ArchivoConocimientoService");
            service.deleteArchivos(this.getSelectedContenido().getNconocimientoid());
            for (ArchivoConocimiento v : this.getListaArchivos()) {

                ArchivoConocimiento archivoconocimiento = new ArchivoConocimiento();
                archivoconocimiento.setNarchivoid(aservice.getNextPK());
                archivoconocimiento.setNtipoconocimientoid(Constante.CONTENIDO);
                archivoconocimiento.setNconocimientoid(this.getSelectedContenido().getNconocimientoid());
                archivoconocimiento.setVnombre(v.getVnombre());
                archivoconocimiento.setNversion(historial.getNnumversion());
                archivoconocimiento.setNtipoarchivo(v.getNtipoarchivo());
                archivoconocimiento.setVcontenttype(v.getVcontenttype());
                archivoconocimiento.setVruta(path + this.getSelectedContenido().getNconocimientoid().toString() + "/" + archivoconocimiento.getNversion().toString() + "/" + archivoconocimiento.getVnombre());
                archivoconocimiento.setVusuariocreacion(user_savecontenido.getVlogin());
                archivoconocimiento.setDfechacreacion(new Date());
                aservice.saveOrUpdate(archivoconocimiento);
                saveFileCT(archivoconocimiento);

            }

            if (cat_antigua != cat_nueva) {
                AsignacionService serviceasig = (AsignacionService) ServiceFinder.findBean("AsignacionService");
                this.getSelectedAsignacion().setNestadoid(BigDecimal.valueOf(Long.parseLong("2")));
                this.getSelectedAsignacion().setDfechaatencion(new Date());
                this.getSelectedAsignacion().setNaccionid(BigDecimal.valueOf(Long.parseLong("12")));
                serviceasig.saveOrUpdate(this.getSelectedAsignacion());

                Asignacion asignacion = new Asignacion();
                asignacion.setNasignacionid(serviceasig.getNextPK());
                asignacion.setNtipoconocimientoid(Constante.CONTENIDO);
                asignacion.setNconocimientoid(this.getSelectedContenido().getNconocimientoid());
                asignacion.setNestadoid(BigDecimal.valueOf(Long.parseLong("1")));
                CategoriaService categoriaService = (CategoriaService) ServiceFinder.findBean("CategoriaService");
                asignacion.setNusuarioid(categoriaService.getCategoriaById(this.getSelectedContenido().getNcategoriaid()).getNmoderador());
                asignacion.setDfechaasignacion(new Date());
                asignacion.setDfechacreacion(new Date());
                serviceasig.saveOrUpdate(asignacion);

                pagina = "/gescon/index.xhtml";
            } else {
                pagina = "";
            }
            loginMB.refreshNotifications();
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "INFO.", "Actualización exitosa!.");
            FacesContext.getCurrentInstance().addMessage(null, message);
            FacesContext.getCurrentInstance().getExternalContext().redirect(pagina);
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
    }

    public void PublicarContenido(ActionEvent event) {
        try {
            LoginMB loginMB = (LoginMB) JSFUtils.getSessionAttribute("loginMB");
            User user_savecontenido = loginMB.getUser();
            //this.setContenidoHtml(JSFUtils.getRequestParameter("descHtml"));
            if (this.getChkDestacado()) {
                ConsultaService consultaService = (ConsultaService) ServiceFinder.findBean("ConsultaService");
                HashMap filter = new HashMap();
                filter.put("ntipoconocimientoid", Constante.CONTENIDO);
                BigDecimal cant = consultaService.countDestacadosByTipoConocimiento(filter);
                if (cant.intValue() >= 10) {
                    this.setListaDestacados(consultaService.getDestacadosByTipoConocimiento(filter));
                    RequestContext.getCurrentInstance().execute("PF('destDialog').show();");
                    return;
                }
            }
            ContenidoService service = (ContenidoService) ServiceFinder.findBean("ContenidoService");
            this.setContenidoPlain(Jsoup.parse(this.getContenidoHtml()).text());
            this.getSelectedContenido().setVtitulo(this.getSelectedContenido().getVtitulo().trim());
            this.getSelectedContenido().setVdescripcion(this.getSelectedContenido().getVdescripcion().trim());
            if (this.getContenidoPlain().length() < 400) {
                this.getSelectedContenido().setVcontenido(StringUtils.capitalize(this.getContenidoPlain()));
            } else {
                this.getSelectedContenido().setVcontenido(StringUtils.capitalize(this.getContenidoPlain().substring(0, 400)));
            }
            this.getSelectedContenido().setNcategoriaid(this.getSelectedCategoria().getNcategoriaid());
            this.getSelectedContenido().setDfechamodificacion(new Date());
            this.getSelectedContenido().setVusuariomodificacion(user_savecontenido.getVlogin());
            this.getSelectedContenido().setNsituacionid(BigDecimal.valueOf((long) 6));
            this.getSelectedContenido().setDfechapublicacion(new Date());
            service.saveOrUpdate(this.getSelectedContenido());

            GcmFileUtils.writeStringToFileServer(this.getSelectedContenido().getVruta(), "html.txt", this.getContenidoHtml());
            GcmFileUtils.writeStringToFileServer(this.getSelectedContenido().getVruta(), "plain.txt", this.getContenidoPlain());

            HistorialService historialService = (HistorialService) ServiceFinder.findBean("HistorialService");
            Historial lastHistorial = historialService.getLastHistorialByConocimiento(this.getSelectedContenido().getNconocimientoid());
            int lastversion;
            if (lastHistorial != null) {
                lastversion = lastHistorial.getNnumversion().intValue();
            } else {
                lastversion = 0;
            }

            String url = this.path.concat(this.getSelectedContenido().getNconocimientoid().toString()).concat("/").concat(Integer.toString(lastversion + 1)).concat("/");

            ThistorialId thistorialId = new ThistorialId();
            thistorialId.setNconocimientoid(this.getSelectedContenido().getNconocimientoid());
            thistorialId.setNhistorialid(historialService.getNextPK());
            Historial historial = new Historial();
            historial.setId(thistorialId);
            historial.setNtipoconocimientoid(Constante.CONTENIDO);
            historial.setNcategoriaid(this.getSelectedCategoria().getNcategoriaid());
            historial.setVdescripcion(this.getSelectedContenido().getVdescripcion().trim());
            historial.setVtitulo(this.getSelectedContenido().getVtitulo());
            if (this.getContenidoPlain().length() < 400) {
                historial.setVcontenido(StringUtils.capitalize(this.getContenidoPlain()));
            } else {
                historial.setVcontenido(StringUtils.capitalize(this.getContenidoPlain().substring(0, 400)));
            }
            historial.setNactivo(BigDecimal.ONE);
            historial.setNsituacionid(this.getSelectedContenido().getNsituacionid());
            historial.setVruta(url);
            historial.setNnumversion(BigDecimal.valueOf(lastversion + 1));
            historial.setDfechacreacion(new Date());
            historial.setVmsjsolicita(this.getSelectedContenido().getVmsjsolicita());
            historial.setVmsjrespuesta(this.getSelectedContenido().getVmsjrespuesta());
            historial.setVusuariocreacion(user_savecontenido.getVlogin());
            historialService.saveOrUpdate(historial);

            GcmFileUtils.writeStringToFileServer(url, "html.txt", this.getContenidoHtml());
            GcmFileUtils.writeStringToFileServer(url, "plain.txt", this.getContenidoPlain());

            AsignacionService serviceasig = (AsignacionService) ServiceFinder.findBean("AsignacionService");
            this.getSelectedAsignacion().setNestadoid(BigDecimal.valueOf(Long.parseLong("2")));
            this.getSelectedAsignacion().setDfechaatencion(new Date());
            this.getSelectedAsignacion().setNaccionid(BigDecimal.valueOf(Long.parseLong("8")));
            serviceasig.saveOrUpdate(this.getSelectedAsignacion());

            this.setListaTargetVinculos(new ArrayList<Consulta>());

            if (!CollectionUtils.isEmpty(this.getListaTargetVinculosBL())) {
                this.getListaTargetVinculos().addAll(this.getListaTargetVinculosBL());
            }
            if (!CollectionUtils.isEmpty(this.getListaTargetVinculosBP())) {
                this.getListaTargetVinculos().addAll(this.getListaTargetVinculosBP());
            }
            if (!CollectionUtils.isEmpty(this.getListaTargetVinculosCT())) {
                this.getListaTargetVinculos().addAll(this.getListaTargetVinculosCT());
            }
            if (!CollectionUtils.isEmpty(this.getListaTargetVinculosOM())) {
                this.getListaTargetVinculos().addAll(this.getListaTargetVinculosOM());
            }
            if (!CollectionUtils.isEmpty(this.getListaTargetVinculosPR())) {
                this.getListaTargetVinculos().addAll(this.getListaTargetVinculosPR());
            }
            if (!CollectionUtils.isEmpty(this.getListaTargetVinculosWK())) {
                this.getListaTargetVinculos().addAll(this.getListaTargetVinculosWK());
            }

            if (org.apache.commons.collections.CollectionUtils.isNotEmpty(this.getListaTargetVinculos())) {
                VinculoService vinculoService = (VinculoService) ServiceFinder.findBean("VinculoService");
                VinculoHistService vinculoHistService = (VinculoHistService) ServiceFinder.findBean("VinculoHistService");
                service.delete(this.getSelectedContenido().getNconocimientoid());
                for (Consulta consulta : this.getListaTargetVinculos()) {
                    Vinculo vinculo = new Vinculo();
                    vinculo.setNvinculoid(vinculoService.getNextPK());
                    vinculo.setNconocimientoid(this.getSelectedContenido().getNconocimientoid());
                    vinculo.setNconocimientovinc(consulta.getIdconocimiento());
                    vinculo.setNtipoconocimientovinc(consulta.getIdTipoConocimiento());
                    vinculo.setDfechacreacion(new Date());
                    vinculo.setVusuariocreacion(user_savecontenido.getVlogin());
                    vinculoService.saveOrUpdate(vinculo);

                    TvinculoHistId vinculoHistId = new TvinculoHistId();
                    vinculoHistId.setNvinculohid(vinculoHistService.getNextPK());
                    vinculoHistId.setNconocimientoid(thistorialId.getNconocimientoid());
                    vinculoHistId.setNhistorialid(thistorialId.getNhistorialid());
                    VinculoHist vinculoHist = new VinculoHist();
                    vinculoHist.setId(vinculoHistId);
                    vinculoHist.setNconocimientovinc(vinculo.getNconocimientovinc());
                    vinculoHist.setNtipoconocimientovinc(vinculo.getNtipoconocimientovinc());
                    vinculoHist.setDfechacreacion(new Date());
                    vinculoHist.setVusuariocreacion(user_savecontenido.getVlogin());
                    vinculoHistService.saveOrUpdate(vinculoHist);

                }
            }

            ArchivoConocimientoService aservice = (ArchivoConocimientoService) ServiceFinder.findBean("ArchivoConocimientoService");
            service.deleteArchivos(this.getSelectedContenido().getNconocimientoid());
            for (ArchivoConocimiento v : this.getListaArchivos()) {

                ArchivoConocimiento archivoconocimiento = new ArchivoConocimiento();
                archivoconocimiento.setNarchivoid(aservice.getNextPK());
                archivoconocimiento.setNtipoconocimientoid(Constante.CONTENIDO);
                archivoconocimiento.setNconocimientoid(this.getSelectedContenido().getNconocimientoid());
                archivoconocimiento.setVnombre(v.getVnombre());
                archivoconocimiento.setNtipoarchivo(v.getNtipoarchivo());
                archivoconocimiento.setVcontenttype(v.getVcontenttype());
                archivoconocimiento.setNversion(historial.getNnumversion());
                archivoconocimiento.setVruta(path + this.getSelectedContenido().getNconocimientoid().toString() + "/" + archivoconocimiento.getNversion().toString() + "/" + archivoconocimiento.getVnombre());
                archivoconocimiento.setVusuariocreacion(user_savecontenido.getVlogin());
                archivoconocimiento.setDfechacreacion(new Date());
                aservice.saveOrUpdate(archivoconocimiento);
                saveFileCT(archivoconocimiento);

            }
            loginMB.refreshNotifications();
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "INFO.", "Se publicó el contenido exitosamente.");
            FacesContext.getCurrentInstance().addMessage(null, message);
            FacesContext.getCurrentInstance().getExternalContext().redirect("/gescon/index.xhtml");
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
    }

    public void RechazarContenido(ActionEvent event) {
        try {
            ContenidoService service = (ContenidoService) ServiceFinder.findBean("ContenidoService");
            this.getSelectedContenido().setNsituacionid(BigDecimal.valueOf((long) 7));
            service.saveOrUpdate(this.getSelectedContenido());

            AsignacionService serviceasig = (AsignacionService) ServiceFinder.findBean("AsignacionService");
            this.getSelectedAsignacion().setNestadoid(BigDecimal.valueOf(Long.parseLong("2")));
            this.getSelectedAsignacion().setDfechaatencion(new Date());
            this.getSelectedAsignacion().setNaccionid(BigDecimal.valueOf(Long.parseLong("13")));
            serviceasig.saveOrUpdate(this.getSelectedAsignacion());

            LoginMB loginMB = (LoginMB) JSFUtils.getSessionAttribute("loginMB");
            loginMB.refreshNotifications();

            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "INFO.", "Se rechazó el contenido.");
            FacesContext.getCurrentInstance().addMessage(null, message);
            FacesContext.getCurrentInstance().getExternalContext().redirect("/gescon/index.xhtml");

        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
    }

    public void sendContenidoSolicita(ActionEvent event) {
        try {
            if (StringUtils.isBlank(this.getSelectedContenido().getVmsjsolicita())) {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, Constante.SEVERETY_ALERTA, "Campo requerido. Ingrese el mensaje a enviar.");
                FacesContext.getCurrentInstance().addMessage(null, message);
            } else {
                LoginMB loginMB = (LoginMB) JSFUtils.getSessionAttribute("loginMB");
                User user_savecontenido = loginMB.getUser();
                //this.setContenidoHtml(JSFUtils.getRequestParameter("descHtml"));
                if (this.getChkDestacado()) {
                    ConsultaService consultaService = (ConsultaService) ServiceFinder.findBean("ConsultaService");
                    HashMap filter = new HashMap();
                    filter.put("ntipoconocimientoid", Constante.CONTENIDO);
                    BigDecimal cant = consultaService.countDestacadosByTipoConocimiento(filter);
                    if (cant.intValue() >= 10) {
                        this.setListaDestacados(consultaService.getDestacadosByTipoConocimiento(filter));
                        RequestContext.getCurrentInstance().execute("PF('destDialog').show();");
                        return;
                    }
                }
                ContenidoService service = (ContenidoService) ServiceFinder.findBean("ContenidoService");
                this.setContenidoPlain(Jsoup.parse(this.getContenidoHtml()).text());
                this.getSelectedContenido().setVmsjsolicita(this.getSelectedContenido().getVmsjsolicita().toUpperCase());
                this.getSelectedContenido().setVtitulo(this.getSelectedContenido().getVtitulo().trim());
                this.getSelectedContenido().setVdescripcion(this.getSelectedContenido().getVdescripcion().trim());
                if (this.getContenidoPlain().length() < 400) {
                    this.getSelectedContenido().setVcontenido(StringUtils.capitalize(this.getContenidoPlain()));
                } else {
                    this.getSelectedContenido().setVcontenido(StringUtils.capitalize(this.getContenidoPlain().substring(0, 400)));
                }
                this.getSelectedContenido().setNcategoriaid(this.getSelectedCategoria().getNcategoriaid());
                this.getSelectedContenido().setDfechamodificacion(new Date());
                this.getSelectedContenido().setVusuariomodificacion(user_savecontenido.getVlogin());
                service.saveOrUpdate(this.getSelectedContenido());

                GcmFileUtils.writeStringToFileServer(this.getSelectedContenido().getVruta(), "html.txt", this.getContenidoHtml());
                GcmFileUtils.writeStringToFileServer(this.getSelectedContenido().getVruta(), "plain.txt", this.getContenidoPlain());

                HistorialService historialService = (HistorialService) ServiceFinder.findBean("HistorialService");
                Historial lastHistorial = historialService.getLastHistorialByConocimiento(this.getSelectedContenido().getNconocimientoid());
                int lastversion;
                if (lastHistorial != null) {
                    lastversion = lastHistorial.getNnumversion().intValue();
                } else {
                    lastversion = 0;
                }

                String url = this.path.concat(this.getSelectedContenido().getNconocimientoid().toString()).concat("/").concat(Integer.toString(lastversion + 1)).concat("/");

                ThistorialId thistorialId = new ThistorialId();
                thistorialId.setNconocimientoid(this.getSelectedContenido().getNconocimientoid());
                thistorialId.setNhistorialid(historialService.getNextPK());
                Historial historial = new Historial();
                historial.setId(thistorialId);
                historial.setNtipoconocimientoid(Constante.CONTENIDO);
                historial.setNcategoriaid(this.getSelectedCategoria().getNcategoriaid());
                historial.setVdescripcion(this.getSelectedContenido().getVdescripcion().trim());
                historial.setVtitulo(this.getSelectedContenido().getVtitulo());
                if (this.getContenidoPlain().length() < 400) {
                    historial.setVcontenido(StringUtils.capitalize(this.getContenidoPlain()));
                } else {
                    historial.setVcontenido(StringUtils.capitalize(this.getContenidoPlain().substring(0, 400)));
                }
                historial.setNactivo(BigDecimal.ONE);
                historial.setNsituacionid(this.getSelectedContenido().getNsituacionid());
                historial.setVruta(url);
                historial.setNnumversion(BigDecimal.valueOf(lastversion + 1));
                historial.setDfechacreacion(new Date());
                historial.setVmsjsolicita(this.getSelectedContenido().getVmsjsolicita());
                historial.setVusuariocreacion(user_savecontenido.getVlogin());
                historialService.saveOrUpdate(historial);

                GcmFileUtils.writeStringToFileServer(url, "html.txt", this.getContenidoHtml());
                GcmFileUtils.writeStringToFileServer(url, "plain.txt", this.getContenidoPlain());

                this.setListaTargetVinculos(new ArrayList<Consulta>());

                if (!CollectionUtils.isEmpty(this.getListaTargetVinculosBL())) {
                    this.getListaTargetVinculos().addAll(this.getListaTargetVinculosBL());
                }
                if (!CollectionUtils.isEmpty(this.getListaTargetVinculosBP())) {
                    this.getListaTargetVinculos().addAll(this.getListaTargetVinculosBP());
                }
                if (!CollectionUtils.isEmpty(this.getListaTargetVinculosCT())) {
                    this.getListaTargetVinculos().addAll(this.getListaTargetVinculosCT());
                }
                if (!CollectionUtils.isEmpty(this.getListaTargetVinculosOM())) {
                    this.getListaTargetVinculos().addAll(this.getListaTargetVinculosOM());
                }
                if (!CollectionUtils.isEmpty(this.getListaTargetVinculosPR())) {
                    this.getListaTargetVinculos().addAll(this.getListaTargetVinculosPR());
                }
                if (!CollectionUtils.isEmpty(this.getListaTargetVinculosWK())) {
                    this.getListaTargetVinculos().addAll(this.getListaTargetVinculosWK());
                }

                if (org.apache.commons.collections.CollectionUtils.isNotEmpty(this.getListaTargetVinculos())) {
                    VinculoService vinculoService = (VinculoService) ServiceFinder.findBean("VinculoService");
                    VinculoHistService vinculoHistService = (VinculoHistService) ServiceFinder.findBean("VinculoHistService");
                    service.delete(this.getSelectedContenido().getNconocimientoid());
                    for (Consulta consulta : this.getListaTargetVinculos()) {
                        Vinculo vinculo = new Vinculo();
                        vinculo.setNvinculoid(vinculoService.getNextPK());
                        vinculo.setNconocimientoid(this.getSelectedContenido().getNconocimientoid());
                        vinculo.setNconocimientovinc(consulta.getIdconocimiento());
                        vinculo.setNtipoconocimientovinc(consulta.getIdTipoConocimiento());
                        vinculo.setDfechacreacion(new Date());
                        vinculo.setVusuariocreacion(user_savecontenido.getVlogin());
                        vinculoService.saveOrUpdate(vinculo);

                        TvinculoHistId vinculoHistId = new TvinculoHistId();
                        vinculoHistId.setNvinculohid(vinculoHistService.getNextPK());
                        vinculoHistId.setNconocimientoid(thistorialId.getNconocimientoid());
                        vinculoHistId.setNhistorialid(thistorialId.getNhistorialid());
                        VinculoHist vinculoHist = new VinculoHist();
                        vinculoHist.setId(vinculoHistId);
                        vinculoHist.setNconocimientovinc(vinculo.getNconocimientovinc());
                        vinculoHist.setNtipoconocimientovinc(vinculo.getNtipoconocimientovinc());
                        vinculoHist.setDfechacreacion(new Date());
                        vinculoHist.setVusuariocreacion(user_savecontenido.getVlogin());
                        vinculoHistService.saveOrUpdate(vinculoHist);

                    }
                }

                ArchivoConocimientoService aservice = (ArchivoConocimientoService) ServiceFinder.findBean("ArchivoConocimientoService");
                service.deleteArchivos(this.getSelectedContenido().getNconocimientoid());
                for (ArchivoConocimiento v : this.getListaArchivos()) {

                    ArchivoConocimiento archivoconocimiento = new ArchivoConocimiento();
                    archivoconocimiento.setNarchivoid(aservice.getNextPK());
                    archivoconocimiento.setNtipoconocimientoid(Constante.CONTENIDO);
                    archivoconocimiento.setNconocimientoid(this.getSelectedContenido().getNconocimientoid());
                    archivoconocimiento.setVnombre(v.getVnombre());
                    archivoconocimiento.setNtipoarchivo(v.getNtipoarchivo());
                    archivoconocimiento.setVcontenttype(v.getVcontenttype());
                    archivoconocimiento.setNversion(historial.getNnumversion());
                    archivoconocimiento.setVruta(path + this.getSelectedContenido().getNconocimientoid().toString() + "/" + archivoconocimiento.getNversion().toString() + "/" + archivoconocimiento.getVnombre());
                    archivoconocimiento.setVusuariocreacion(user_savecontenido.getVlogin());
                    archivoconocimiento.setDfechacreacion(new Date());
                    aservice.saveOrUpdate(archivoconocimiento);
                    saveFileCT(archivoconocimiento);

                }

                AsignacionService serviceasig = (AsignacionService) ServiceFinder.findBean("AsignacionService");
                this.getSelectedAsignacion().setNestadoid(BigDecimal.valueOf(Long.parseLong("2")));
                this.getSelectedAsignacion().setDfechaatencion(new Date());
                this.getSelectedAsignacion().setNaccionid(BigDecimal.valueOf(Long.parseLong("9")));
                serviceasig.saveOrUpdate(this.getSelectedAsignacion());

                Asignacion asignacion = new Asignacion();
                asignacion.setNasignacionid(serviceasig.getNextPK());
                asignacion.setNtipoconocimientoid(Constante.CONTENIDO);
                asignacion.setNconocimientoid(this.getSelectedContenido().getNconocimientoid());
                asignacion.setNestadoid(BigDecimal.valueOf(Long.parseLong("1")));
                asignacion.setNusuarioid(serviceasig.getUserCreacionByContenido(this.getSelectedContenido().getNtipoconocimientoid(), this.getSelectedContenido().getNconocimientoid()));
                asignacion.setDfechaasignacion(new Date());
                asignacion.setDfechacreacion(new Date());
                serviceasig.saveOrUpdate(asignacion);

                loginMB.refreshNotifications();

                this.fSInfMod = "true";
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "INFO.", "Se envió la solicitud de información.");
                FacesContext.getCurrentInstance().addMessage(null, message);
                FacesContext.getCurrentInstance().getExternalContext().redirect("/gescon/index.xhtml");
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
    }

    public void sendContenidoRespuesta(ActionEvent event) {
        try {
            if (StringUtils.isBlank(this.getSelectedContenido().getVmsjrespuesta())) {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, Constante.SEVERETY_ALERTA, "Campo requerido. Ingrese el mensaje a enviar.");
                FacesContext.getCurrentInstance().addMessage(null, message);
            } else {
                LoginMB loginMB = (LoginMB) JSFUtils.getSessionAttribute("loginMB");
                User user_savecontenido = loginMB.getUser();
                //this.setContenidoHtml(JSFUtils.getRequestParameter("descHtml"));
                if (this.getChkDestacado()) {
                    ConsultaService consultaService = (ConsultaService) ServiceFinder.findBean("ConsultaService");
                    HashMap filter = new HashMap();
                    filter.put("ntipoconocimientoid", Constante.CONTENIDO);
                    BigDecimal cant = consultaService.countDestacadosByTipoConocimiento(filter);
                    if (cant.intValue() >= 10) {
                        this.setListaDestacados(consultaService.getDestacadosByTipoConocimiento(filter));
                        RequestContext.getCurrentInstance().execute("PF('destDialog').show();");
                        return;
                    }
                }
                ContenidoService service = (ContenidoService) ServiceFinder.findBean("ContenidoService");
                this.setContenidoPlain(Jsoup.parse(this.getContenidoHtml()).text());
                this.getSelectedContenido().setVmsjrespuesta(this.getSelectedContenido().getVmsjrespuesta().toUpperCase());
                this.getSelectedContenido().setVmsjsolicita(this.getSelectedContenido().getVmsjsolicita().toUpperCase());
                this.getSelectedContenido().setVtitulo(this.getSelectedContenido().getVtitulo().trim());
                this.getSelectedContenido().setVdescripcion(this.getSelectedContenido().getVdescripcion().trim());
                if (this.getContenidoPlain().length() < 400) {
                    this.getSelectedContenido().setVcontenido(StringUtils.capitalize(this.getContenidoPlain()));
                } else {
                    this.getSelectedContenido().setVcontenido(StringUtils.capitalize(this.getContenidoPlain().substring(0, 400)));
                }
                this.getSelectedContenido().setNcategoriaid(this.getSelectedCategoria().getNcategoriaid());
                this.getSelectedContenido().setDfechamodificacion(new Date());
                this.getSelectedContenido().setVusuariomodificacion(user_savecontenido.getVlogin());
                service.saveOrUpdate(this.getSelectedContenido());

                GcmFileUtils.writeStringToFileServer(this.getSelectedContenido().getVruta(), "html.txt", this.getContenidoHtml());
                GcmFileUtils.writeStringToFileServer(this.getSelectedContenido().getVruta(), "plain.txt", this.getContenidoPlain());

                HistorialService historialService = (HistorialService) ServiceFinder.findBean("HistorialService");
                Historial lastHistorial = historialService.getLastHistorialByConocimiento(this.getSelectedContenido().getNconocimientoid());
                int lastversion;
                if (lastHistorial != null) {
                    lastversion = lastHistorial.getNnumversion().intValue();
                } else {
                    lastversion = 0;
                }

                String url = this.path.concat(this.getSelectedContenido().getNconocimientoid().toString()).concat("/").concat(Integer.toString(lastversion + 1)).concat("/");

                ThistorialId thistorialId = new ThistorialId();
                thistorialId.setNconocimientoid(this.getSelectedContenido().getNconocimientoid());
                thistorialId.setNhistorialid(historialService.getNextPK());
                Historial historial = new Historial();
                historial.setId(thistorialId);
                historial.setNtipoconocimientoid(Constante.CONTENIDO);
                historial.setNcategoriaid(this.getSelectedCategoria().getNcategoriaid());
                historial.setVdescripcion(this.getSelectedContenido().getVdescripcion().trim());
                historial.setVtitulo(this.getSelectedContenido().getVtitulo());
                if (this.getContenidoPlain().length() < 400) {
                    historial.setVcontenido(StringUtils.capitalize(this.getContenidoPlain()));
                } else {
                    historial.setVcontenido(StringUtils.capitalize(this.getContenidoPlain().substring(0, 400)));
                }
                historial.setNactivo(BigDecimal.ONE);
                historial.setNsituacionid(this.getSelectedContenido().getNsituacionid());
                historial.setVruta(url);
                historial.setNnumversion(BigDecimal.valueOf(lastversion + 1));
                historial.setDfechacreacion(new Date());
                historial.setVmsjsolicita(this.getSelectedContenido().getVmsjsolicita());
                historial.setVmsjrespuesta(this.getSelectedContenido().getVmsjrespuesta());
                historial.setVusuariocreacion(user_savecontenido.getVlogin());
                historialService.saveOrUpdate(historial);

                GcmFileUtils.writeStringToFileServer(url, "html.txt", this.getContenidoHtml());
                GcmFileUtils.writeStringToFileServer(url, "plain.txt", this.getContenidoPlain());

                this.setListaTargetVinculos(new ArrayList<Consulta>());

                if (!CollectionUtils.isEmpty(this.getListaTargetVinculosBL())) {
                    this.getListaTargetVinculos().addAll(this.getListaTargetVinculosBL());
                }
                if (!CollectionUtils.isEmpty(this.getListaTargetVinculosBP())) {
                    this.getListaTargetVinculos().addAll(this.getListaTargetVinculosBP());
                }
                if (!CollectionUtils.isEmpty(this.getListaTargetVinculosCT())) {
                    this.getListaTargetVinculos().addAll(this.getListaTargetVinculosCT());
                }
                if (!CollectionUtils.isEmpty(this.getListaTargetVinculosOM())) {
                    this.getListaTargetVinculos().addAll(this.getListaTargetVinculosOM());
                }
                if (!CollectionUtils.isEmpty(this.getListaTargetVinculosPR())) {
                    this.getListaTargetVinculos().addAll(this.getListaTargetVinculosPR());
                }
                if (!CollectionUtils.isEmpty(this.getListaTargetVinculosWK())) {
                    this.getListaTargetVinculos().addAll(this.getListaTargetVinculosWK());
                }

                if (org.apache.commons.collections.CollectionUtils.isNotEmpty(this.getListaTargetVinculos())) {
                    VinculoService vinculoService = (VinculoService) ServiceFinder.findBean("VinculoService");
                    VinculoHistService vinculoHistService = (VinculoHistService) ServiceFinder.findBean("VinculoHistService");
                    service.delete(this.getSelectedContenido().getNconocimientoid());
                    for (Consulta consulta : this.getListaTargetVinculos()) {
                        Vinculo vinculo = new Vinculo();
                        vinculo.setNvinculoid(vinculoService.getNextPK());
                        vinculo.setNconocimientoid(this.getSelectedContenido().getNconocimientoid());
                        vinculo.setNconocimientovinc(consulta.getIdconocimiento());
                        vinculo.setNtipoconocimientovinc(consulta.getIdTipoConocimiento());
                        vinculo.setDfechacreacion(new Date());
                        vinculo.setVusuariocreacion(user_savecontenido.getVlogin());
                        vinculoService.saveOrUpdate(vinculo);

                        TvinculoHistId vinculoHistId = new TvinculoHistId();
                        vinculoHistId.setNvinculohid(vinculoHistService.getNextPK());
                        vinculoHistId.setNconocimientoid(thistorialId.getNconocimientoid());
                        vinculoHistId.setNhistorialid(thistorialId.getNhistorialid());
                        VinculoHist vinculoHist = new VinculoHist();
                        vinculoHist.setId(vinculoHistId);
                        vinculoHist.setNconocimientovinc(vinculo.getNconocimientovinc());
                        vinculoHist.setNtipoconocimientovinc(vinculo.getNtipoconocimientovinc());
                        vinculoHist.setDfechacreacion(new Date());
                        vinculoHist.setVusuariocreacion(user_savecontenido.getVlogin());
                        vinculoHistService.saveOrUpdate(vinculoHist);

                    }
                }

                ArchivoConocimientoService aservice = (ArchivoConocimientoService) ServiceFinder.findBean("ArchivoConocimientoService");
                service.deleteArchivos(this.getSelectedContenido().getNconocimientoid());
                for (ArchivoConocimiento v : this.getListaArchivos()) {

                    ArchivoConocimiento archivoconocimiento = new ArchivoConocimiento();
                    archivoconocimiento.setNarchivoid(aservice.getNextPK());
                    archivoconocimiento.setNtipoconocimientoid(Constante.CONTENIDO);
                    archivoconocimiento.setNconocimientoid(this.getSelectedContenido().getNconocimientoid());
                    archivoconocimiento.setVnombre(v.getVnombre());
                    archivoconocimiento.setNtipoarchivo(v.getNtipoarchivo());
                    archivoconocimiento.setVcontenttype(v.getVcontenttype());
                    archivoconocimiento.setNversion(historial.getNnumversion());
                    archivoconocimiento.setVruta(path + this.getSelectedContenido().getNconocimientoid().toString() + "/" + archivoconocimiento.getNversion().toString() + "/" + archivoconocimiento.getVnombre());
                    archivoconocimiento.setVusuariocreacion(user_savecontenido.getVlogin());
                    archivoconocimiento.setDfechacreacion(new Date());
                    aservice.saveOrUpdate(archivoconocimiento);
                    saveFileCT(archivoconocimiento);

                }

                AsignacionService serviceasig = (AsignacionService) ServiceFinder.findBean("AsignacionService");
                this.getSelectedAsignacion().setNestadoid(BigDecimal.valueOf(Long.parseLong("2")));
                this.getSelectedAsignacion().setDfechaatencion(new Date());
                this.getSelectedAsignacion().setNaccionid(BigDecimal.valueOf(Long.parseLong("10")));
                serviceasig.saveOrUpdate(this.getSelectedAsignacion());

                Asignacion asignacion = new Asignacion();
                asignacion.setNasignacionid(serviceasig.getNextPK());
                asignacion.setNtipoconocimientoid(Constante.CONTENIDO);
                asignacion.setNconocimientoid(this.getSelectedContenido().getNconocimientoid());
                asignacion.setNestadoid(BigDecimal.valueOf(Long.parseLong("1")));
                CategoriaService categoriaService = (CategoriaService) ServiceFinder.findBean("CategoriaService");
                asignacion.setNusuarioid(categoriaService.getCategoriaById(this.getSelectedContenido().getNcategoriaid()).getNmoderador());
                asignacion.setDfechaasignacion(new Date());
                asignacion.setDfechacreacion(new Date());
                serviceasig.saveOrUpdate(asignacion);

                loginMB.refreshNotifications();

                this.fSInfMod = "true";
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "INFO.", "Se envió la respuesta a la solicitud de información.");
                FacesContext.getCurrentInstance().addMessage(null, message);
                FacesContext.getCurrentInstance().getExternalContext().redirect("/gescon/index.xhtml");
            }

        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
    }

    public void toCancelRespContenidoMod(ActionEvent event) {
        try {
            RequestContext.getCurrentInstance().execute("PF('respModContenidoDialog').hide();");
            RequestContext.getCurrentInstance().execute("PF('siModContenidoDialog').hide();");
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
    }

    public void sendWikiSolicita(ActionEvent event) {
        try {
            if (StringUtils.isBlank(this.getSelectedWiki().getVmsjsolicita())) {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, Constante.SEVERETY_ALERTA, "Campo requerido. Ingrese el mensaje a enviar.");
                FacesContext.getCurrentInstance().addMessage(null, message);
            } else {
                //this.setDescripcionHtml(JSFUtils.getRequestParameter("descHtml"));
                if (this.getChkDestacado()) {
                    ConsultaService consultaService = (ConsultaService) ServiceFinder.findBean("ConsultaService");
                    HashMap filter = new HashMap();
                    filter.put("ntipoconocimientoid", Constante.WIKI);
                    BigDecimal cant = consultaService.countDestacadosByTipoConocimiento(filter);
                    if (cant.intValue() >= 10) {
                        this.setListaDestacados(consultaService.getDestacadosByTipoConocimiento(filter));
                        RequestContext.getCurrentInstance().execute("PF('destDialog').show();");
                        return;
                    }
                }
                LoginMB loginMB = (LoginMB) JSFUtils.getSessionAttribute("loginMB");
                User usuario = loginMB.getUser();
                ConocimientoService conocimientoService = (ConocimientoService) ServiceFinder.findBean("ConocimientoService");
                this.setDescripcionPlain(Jsoup.parse(this.getDescripcionHtml()).text());
                this.getSelectedWiki().setNcategoriaid(this.getSelectedCategoria().getNcategoriaid());
                this.getSelectedWiki().setVtitulo(StringUtils.upperCase(this.getSelectedWiki().getVtitulo()));
                if (this.getDescripcionPlain().length() < 400) {
                    this.getSelectedWiki().setVdescripcion(StringUtils.capitalize(this.getDescripcionPlain()));
                } else {
                    this.getSelectedWiki().setVdescripcion(StringUtils.capitalize(this.getDescripcionPlain().substring(0, 400)));
                }
                this.getSelectedWiki().setNdestacado(this.getChkDestacado() ? BigDecimal.ONE : BigDecimal.ZERO);
                this.getSelectedWiki().setDfechamodificacion(new Date());
                this.getSelectedWiki().setVusuariomodificacion(usuario.getVlogin());
                this.getSelectedWiki().setVmsjsolicita(this.getSelectedWiki().getVmsjsolicita().toUpperCase());
                conocimientoService.saveOrUpdate(this.getSelectedWiki());

                GcmFileUtils.writeStringToFileServer(this.getSelectedWiki().getVruta(), "html.txt", this.getDescripcionHtml());
                GcmFileUtils.writeStringToFileServer(this.getSelectedWiki().getVruta(), "plain.txt", this.getDescripcionPlain());

                HistorialService historialService = (HistorialService) ServiceFinder.findBean("HistorialService");
                Historial lastHistorial = historialService.getLastHistorialByConocimiento(this.getSelectedWiki().getNconocimientoid());
                int lastversion;
                if (lastHistorial != null) {
                    lastversion = lastHistorial.getNnumversion().intValue();
                } else {
                    lastversion = 0;
                }
                String url = this.pathwk.concat(this.getSelectedWiki().getNconocimientoid().toString()).concat("/").concat(Integer.toString(lastversion + 1)).concat("/");

                ThistorialId thistorialId = new ThistorialId();
                thistorialId.setNconocimientoid(this.getSelectedWiki().getNconocimientoid());
                thistorialId.setNhistorialid(historialService.getNextPK());
                Historial historial = new Historial();
                historial.setId(thistorialId);
                historial.setNtipoconocimientoid(Constante.WIKI);
                historial.setNcategoriaid(this.getSelectedCategoria().getNcategoriaid());
                historial.setVtitulo(this.getSelectedWiki().getVtitulo());
                historial.setNactivo(BigDecimal.ONE);
                historial.setNsituacionid(this.getSelectedWiki().getNsituacionid());
                historial.setVruta(url);
                historial.setNnumversion(BigDecimal.valueOf(lastversion + 1));
                historial.setDfechacreacion(new Date());
                historial.setVusuariocreacion(usuario.getVlogin());
                historial.setVmsjsolicita(this.getSelectedWiki().getVmsjsolicita());
                historial.setVmsjrespuesta(this.getSelectedWiki().getVmsjrespuesta());
                historialService.saveOrUpdate(historial);

                GcmFileUtils.writeStringToFileServer(url, "html.txt", this.getDescripcionHtml());
                GcmFileUtils.writeStringToFileServer(url, "plain.txt", this.getDescripcionPlain());

                if (org.apache.commons.collections.CollectionUtils.isNotEmpty(this.getListaSeccion())) {
                    String url0 = this.getSelectedWiki().getVruta().concat("s");
                    String url1 = url.concat("s");
                    SeccionService seccionService = (SeccionService) ServiceFinder.findBean("SeccionService");
                    SeccionHistService seccionHistService = (SeccionHistService) ServiceFinder.findBean("SeccionHistService");
                    for (Seccion seccion : this.getListaSeccion()) {
                        String ruta0 = url0.concat(seccion.getNorden().toString()).concat("/");
                        seccion.setVruta(ruta0);
                        if (seccion.getNseccionid() != null) {
                            seccion.setDfechamodificacion(new Date());
                            seccion.setVusuariomodificacion(usuario.getVlogin());
                        } else {
                            seccion.setNseccionid(seccionService.getNextPK());
                            seccion.setNconocimientoid(this.getSelectedWiki().getNconocimientoid());
                            seccion.setDfechacreacion(new Date());
                            seccion.setVusuariocreacion(usuario.getVlogin());
                        }
                        seccionService.saveOrUpdate(seccion);

                        seccion.setDetallePlain(Jsoup.parse(seccion.getDetalleHtml()).text());
                        GcmFileUtils.writeStringToFileServer(ruta0, "html.txt", seccion.getDetalleHtml());
                        GcmFileUtils.writeStringToFileServer(ruta0, "plain.txt", seccion.getDetallePlain());

                        String ruta1 = url1.concat(seccion.getNorden().toString()).concat("/");
                        TseccionHistId tseccionHistId = new TseccionHistId();
                        tseccionHistId.setNconocimientoid(thistorialId.getNconocimientoid());
                        tseccionHistId.setNhistorialid(thistorialId.getNhistorialid());
                        tseccionHistId.setNseccionhid(seccionHistService.getNextPK());
                        SeccionHist seccionHist = new SeccionHist();
                        seccionHist.setId(tseccionHistId);
                        seccionHist.setNorden(seccion.getNorden());
                        seccionHist.setVruta(ruta1);
                        seccionHist.setVtitulo(seccion.getVtitulo());
                        seccionHist.setVusuariocreacion(usuario.getVlogin());
                        seccionHist.setDfechacreacion(new Date());
                        seccionHistService.saveOrUpdate(seccionHist);

                        GcmFileUtils.writeStringToFileServer(ruta1, "html.txt", seccion.getDetalleHtml());
                        GcmFileUtils.writeStringToFileServer(ruta1, "plain.txt", seccion.getDetallePlain());
                    }
                }

                this.setListaTargetVinculos(new ArrayList());
                this.getListaTargetVinculos().addAll(this.getListaTargetVinculosBL());
                this.getListaTargetVinculos().addAll(this.getListaTargetVinculosBP());
                this.getListaTargetVinculos().addAll(this.getListaTargetVinculosCT());
                this.getListaTargetVinculos().addAll(this.getListaTargetVinculosOM());
                this.getListaTargetVinculos().addAll(this.getListaTargetVinculosPR());
                this.getListaTargetVinculos().addAll(this.getListaTargetVinculosWK());

                if (org.apache.commons.collections.CollectionUtils.isNotEmpty(this.getListaTargetVinculos())) {
                    VinculoService vinculoService = (VinculoService) ServiceFinder.findBean("VinculoService");
                    VinculoHistService vinculoHistService = (VinculoHistService) ServiceFinder.findBean("VinculoHistService");
                    vinculoService.deleteByConocimiento(this.getSelectedWiki().getNconocimientoid());
                    for (Consulta consulta : this.getListaTargetVinculos()) {
                        Vinculo vinculo = new Vinculo();
                        vinculo.setNvinculoid(vinculoService.getNextPK());
                        vinculo.setNconocimientoid(this.getSelectedWiki().getNconocimientoid());
                        vinculo.setNconocimientovinc(consulta.getIdconocimiento());
                        vinculo.setNtipoconocimientovinc(consulta.getIdTipoConocimiento());
                        vinculo.setDfechacreacion(new Date());
                        vinculo.setVusuariocreacion(usuario.getVlogin());
                        vinculoService.saveOrUpdate(vinculo);

                        TvinculoHistId vinculoHistId = new TvinculoHistId();
                        vinculoHistId.setNvinculohid(vinculoHistService.getNextPK());
                        vinculoHistId.setNconocimientoid(thistorialId.getNconocimientoid());
                        vinculoHistId.setNhistorialid(thistorialId.getNhistorialid());
                        VinculoHist vinculoHist = new VinculoHist();
                        vinculoHist.setId(vinculoHistId);
                        vinculoHist.setNconocimientovinc(vinculo.getNconocimientovinc());
                        vinculoHist.setDfechacreacion(new Date());
                        vinculoHist.setVusuariocreacion(usuario.getVlogin());
                        vinculoHistService.saveOrUpdate(vinculoHist);
                    }
                }

                AsignacionService serviceasig = (AsignacionService) ServiceFinder.findBean("AsignacionService");
                this.getSelectedAsignacion().setNestadoid(BigDecimal.valueOf(Long.parseLong("2")));
                this.getSelectedAsignacion().setDfechaatencion(new Date());
                this.getSelectedAsignacion().setNaccionid(BigDecimal.valueOf(Long.parseLong("9")));
                serviceasig.saveOrUpdate(this.getSelectedAsignacion());

                Asignacion asignacion = new Asignacion();
                asignacion.setNasignacionid(serviceasig.getNextPK());
                asignacion.setNtipoconocimientoid(Constante.WIKI);
                asignacion.setNconocimientoid(this.getSelectedWiki().getNconocimientoid());
                asignacion.setNestadoid(BigDecimal.valueOf(Long.parseLong("1")));
                asignacion.setNusuarioid(serviceasig.getUserCreacionByContenido(this.getSelectedWiki().getNtipoconocimientoid(), this.getSelectedWiki().getNconocimientoid()));
                asignacion.setDfechaasignacion(new Date());
                asignacion.setDfechacreacion(new Date());
                serviceasig.saveOrUpdate(asignacion);

                loginMB.refreshNotifications();

                this.fSInfMod = "true";
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "INFO.", "Solicitud de información enviada.");
                FacesContext.getCurrentInstance().addMessage(null, message);
                FacesContext.getCurrentInstance().getExternalContext().redirect("/gescon/index.xhtml");
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
    }

    public void sendBpracticaSolicita(ActionEvent event) {
        try {
            if (StringUtils.isBlank(this.getSelectedBpractica().getVmsjsolicita())) {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, Constante.SEVERETY_ALERTA, "Campo requerido. Ingrese el mensaje a enviar.");
                FacesContext.getCurrentInstance().addMessage(null, message);
            } else {
                //this.setDescripcionHtml(JSFUtils.getRequestParameter("descHtml"));
                if (this.getChkDestacado()) {
                    ConsultaService consultaService = (ConsultaService) ServiceFinder.findBean("ConsultaService");
                    HashMap filter = new HashMap();
                    filter.put("ntipoconocimientoid", Constante.BUENAPRACTICA);
                    BigDecimal cant = consultaService.countDestacadosByTipoConocimiento(filter);
                    if (cant.intValue() >= 10) {
                        this.setListaDestacados(consultaService.getDestacadosByTipoConocimiento(filter));
                        RequestContext.getCurrentInstance().execute("PF('destDialog').show();");
                        return;
                    }
                }
                this.setDescripcionPlain(Jsoup.parse(this.getDescripcionHtml()).text());
                LoginMB loginMB = (LoginMB) JSFUtils.getSessionAttribute("loginMB");
                User usuario = loginMB.getUser();
                ConocimientoService conocimientoService = (ConocimientoService) ServiceFinder.findBean("ConocimientoService");
                this.getSelectedBpractica().setNcategoriaid(this.getSelectedCategoria().getNcategoriaid());
                this.getSelectedBpractica().setVtitulo(StringUtils.upperCase(this.getSelectedBpractica().getVtitulo().trim()));
                if (this.getDescripcionPlain().length() < 400) {
                    this.getSelectedBpractica().setVdescripcion(StringUtils.capitalize(this.getDescripcionPlain()));
                } else {
                    this.getSelectedBpractica().setVdescripcion(StringUtils.capitalize(this.getDescripcionPlain().substring(0, 400)));
                }
                this.getSelectedBpractica().setNdestacado(this.getChkDestacado() ? BigDecimal.ONE : BigDecimal.ZERO);
                this.getSelectedBpractica().setDfechamodificacion(new Date());
                this.getSelectedBpractica().setVusuariomodificacion(usuario.getVlogin());
                this.getSelectedBpractica().setVmsjsolicita(this.getSelectedBpractica().getVmsjsolicita().toUpperCase());
                conocimientoService.saveOrUpdate(this.getSelectedBpractica());

                GcmFileUtils.writeStringToFileServer(this.getSelectedBpractica().getVruta(), "html.txt", this.getDescripcionHtml());
                GcmFileUtils.writeStringToFileServer(this.getSelectedBpractica().getVruta(), "plain.txt", this.getDescripcionPlain());

                HistorialService historialService = (HistorialService) ServiceFinder.findBean("HistorialService");
                Historial lastHistorial = historialService.getLastHistorialByConocimiento(this.getSelectedBpractica().getNconocimientoid());
                int lastversion;
                if (lastHistorial != null) {
                    lastversion = lastHistorial.getNnumversion().intValue();
                } else {
                    lastversion = 0;
                }
                String url = this.pathbp.concat(this.getSelectedBpractica().getNconocimientoid().toString()).concat("/").concat(Integer.toString(lastversion + 1)).concat("/");

                ThistorialId thistorialId = new ThistorialId();
                thistorialId.setNconocimientoid(this.getSelectedBpractica().getNconocimientoid());
                thistorialId.setNhistorialid(historialService.getNextPK());
                Historial historial = new Historial();
                historial.setId(thistorialId);
                historial.setNtipoconocimientoid(Constante.BUENAPRACTICA);
                historial.setNcategoriaid(this.getSelectedCategoria().getNcategoriaid());
                historial.setVtitulo(this.getSelectedBpractica().getVtitulo());
                historial.setNactivo(BigDecimal.ONE);
                historial.setNsituacionid(this.getSelectedBpractica().getNsituacionid());
                historial.setVruta(url);
                historial.setNnumversion(BigDecimal.valueOf(lastversion + 1));
                historial.setDfechacreacion(new Date());
                historial.setVusuariocreacion(usuario.getVlogin());
                historial.setVmsjsolicita(this.getSelectedBpractica().getVmsjsolicita());
                historial.setVmsjrespuesta(this.getSelectedBpractica().getVmsjrespuesta());
                historialService.saveOrUpdate(historial);

                GcmFileUtils.writeStringToFileServer(url, "html.txt", this.getDescripcionHtml());
                GcmFileUtils.writeStringToFileServer(url, "plain.txt", this.getDescripcionPlain());

                if (org.apache.commons.collections.CollectionUtils.isNotEmpty(this.getListaSeccion())) {
                    String url0 = this.getSelectedBpractica().getVruta().concat("s");
                    String url1 = url.concat("s");
                    SeccionService seccionService = (SeccionService) ServiceFinder.findBean("SeccionService");
                    SeccionHistService seccionHistService = (SeccionHistService) ServiceFinder.findBean("SeccionHistService");
                    for (Seccion seccion : this.getListaSeccion()) {
                        String ruta0 = url0.concat(seccion.getNorden().toString()).concat("/");
                        seccion.setVruta(ruta0);
                        if (seccion.getNseccionid() != null) {
                            seccion.setDfechamodificacion(new Date());
                            seccion.setVusuariomodificacion(usuario.getVlogin());
                        } else {
                            seccion.setNseccionid(seccionService.getNextPK());
                            seccion.setNconocimientoid(this.getSelectedBpractica().getNconocimientoid());
                            seccion.setDfechacreacion(new Date());
                            seccion.setVusuariocreacion(usuario.getVlogin());
                        }
                        seccionService.saveOrUpdate(seccion);

                        seccion.setDetallePlain(Jsoup.parse(seccion.getDetalleHtml()).text());
                        GcmFileUtils.writeStringToFileServer(ruta0, "html.txt", seccion.getDetalleHtml());
                        GcmFileUtils.writeStringToFileServer(ruta0, "plain.txt", seccion.getDetallePlain());

                        String ruta1 = url1.concat(seccion.getNorden().toString()).concat("/");
                        TseccionHistId tseccionHistId = new TseccionHistId();
                        tseccionHistId.setNconocimientoid(thistorialId.getNconocimientoid());
                        tseccionHistId.setNhistorialid(thistorialId.getNhistorialid());
                        tseccionHistId.setNseccionhid(seccionHistService.getNextPK());
                        SeccionHist seccionHist = new SeccionHist();
                        seccionHist.setId(tseccionHistId);
                        seccionHist.setNorden(seccion.getNorden());
                        seccionHist.setVruta(ruta1);
                        seccionHist.setVtitulo(seccion.getVtitulo());
                        seccionHist.setVusuariocreacion(usuario.getVlogin());
                        seccionHist.setDfechacreacion(new Date());
                        seccionHistService.saveOrUpdate(seccionHist);

                        GcmFileUtils.writeStringToFileServer(ruta1, "html.txt", seccion.getDetalleHtml());
                        GcmFileUtils.writeStringToFileServer(ruta1, "plain.txt", seccion.getDetallePlain());
                    }
                }

                this.setListaTargetVinculos(new ArrayList());
                this.getListaTargetVinculos().addAll(this.getListaTargetVinculosBL());
                this.getListaTargetVinculos().addAll(this.getListaTargetVinculosBP());
                this.getListaTargetVinculos().addAll(this.getListaTargetVinculosCT());
                this.getListaTargetVinculos().addAll(this.getListaTargetVinculosOM());
                this.getListaTargetVinculos().addAll(this.getListaTargetVinculosPR());
                this.getListaTargetVinculos().addAll(this.getListaTargetVinculosWK());

                if (org.apache.commons.collections.CollectionUtils.isNotEmpty(this.getListaTargetVinculos())) {
                    VinculoService vinculoService = (VinculoService) ServiceFinder.findBean("VinculoService");
                    VinculoHistService vinculoHistService = (VinculoHistService) ServiceFinder.findBean("VinculoHistService");
                    vinculoService.deleteByConocimiento(this.getSelectedBpractica().getNconocimientoid());
                    for (Consulta consulta : this.getListaTargetVinculos()) {
                        Vinculo vinculo = new Vinculo();
                        vinculo.setNvinculoid(vinculoService.getNextPK());
                        vinculo.setNconocimientoid(this.getSelectedBpractica().getNconocimientoid());
                        vinculo.setNconocimientovinc(consulta.getIdconocimiento());
                        vinculo.setNtipoconocimientovinc(consulta.getIdTipoConocimiento());
                        vinculo.setDfechacreacion(new Date());
                        vinculo.setVusuariocreacion(usuario.getVlogin());
                        vinculoService.saveOrUpdate(vinculo);

                        TvinculoHistId vinculoHistId = new TvinculoHistId();
                        vinculoHistId.setNvinculohid(vinculoHistService.getNextPK());
                        vinculoHistId.setNconocimientoid(thistorialId.getNconocimientoid());
                        vinculoHistId.setNhistorialid(thistorialId.getNhistorialid());
                        VinculoHist vinculoHist = new VinculoHist();
                        vinculoHist.setId(vinculoHistId);
                        vinculoHist.setNconocimientovinc(vinculo.getNconocimientovinc());
                        vinculoHist.setDfechacreacion(new Date());
                        vinculoHist.setVusuariocreacion(usuario.getVlogin());
                        vinculoHistService.saveOrUpdate(vinculoHist);
                    }
                }

                AsignacionService serviceasig = (AsignacionService) ServiceFinder.findBean("AsignacionService");
                this.getSelectedAsignacion().setNestadoid(BigDecimal.valueOf(Long.parseLong("2")));
                this.getSelectedAsignacion().setDfechaatencion(new Date());
                this.getSelectedAsignacion().setNaccionid(BigDecimal.valueOf(Long.parseLong("9")));
                serviceasig.saveOrUpdate(this.getSelectedAsignacion());

                Asignacion asignacion = new Asignacion();
                asignacion.setNasignacionid(serviceasig.getNextPK());
                asignacion.setNtipoconocimientoid(Constante.BUENAPRACTICA);
                asignacion.setNconocimientoid(this.getSelectedBpractica().getNconocimientoid());
                asignacion.setNestadoid(BigDecimal.valueOf(Long.parseLong("1")));
                asignacion.setNusuarioid(serviceasig.getUserCreacionByContenido(this.getSelectedBpractica().getNtipoconocimientoid(), this.getSelectedBpractica().getNconocimientoid()));
                asignacion.setDfechaasignacion(new Date());
                asignacion.setDfechacreacion(new Date());
                serviceasig.saveOrUpdate(asignacion);

                loginMB.refreshNotifications();

                this.fSInfMod = "true";
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "INFO.", "Solicitud de información adicional enviada.");
                FacesContext.getCurrentInstance().addMessage(null, message);
                FacesContext.getCurrentInstance().getExternalContext().redirect("/gescon/index.xhtml");
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
    }

    public void sendOmejoraSolicita(ActionEvent event) {
        try {
            if (StringUtils.isBlank(this.getSelectedOmejora().getVmsjsolicita())) {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, Constante.SEVERETY_ALERTA, "Campo requerido. Ingrese el mensaje a enviar.");
                FacesContext.getCurrentInstance().addMessage(null, message);
            } else {
                //this.setContenidoHtml(JSFUtils.getRequestParameter("descHtml"));
                if (this.getChkDestacado()) {
                    ConsultaService consultaService = (ConsultaService) ServiceFinder.findBean("ConsultaService");
                    HashMap filter = new HashMap();
                    filter.put("ntipoconocimientoid", Constante.OPORTUNIDADMEJORA);
                    BigDecimal cant = consultaService.countDestacadosByTipoConocimiento(filter);
                    if (cant.intValue() >= 10) {
                        this.setListaDestacados(consultaService.getDestacadosByTipoConocimiento(filter));
                        RequestContext.getCurrentInstance().execute("PF('destDialog').show();");
                        return;
                    }
                }
                this.setContenidoPlain(Jsoup.parse(this.getContenidoHtml()).text());
                LoginMB loginMB = (LoginMB) JSFUtils.getSessionAttribute("loginMB");
                User usuario = loginMB.getUser();
                ConocimientoService conocimientoService = (ConocimientoService) ServiceFinder.findBean("ConocimientoService");
                this.getSelectedOmejora().setNcategoriaid(this.getSelectedCategoria().getNcategoriaid());
                this.getSelectedOmejora().setVtitulo(StringUtils.upperCase(this.getSelectedOmejora().getVtitulo()));
                this.getSelectedOmejora().setVdescripcion(StringUtils.upperCase(this.getSelectedOmejora().getVdescripcion()));
                if (this.getContenidoPlain().length() < 400) {
                    this.getSelectedOmejora().setVcontenido(StringUtils.capitalize(this.getContenidoPlain()));
                } else {
                    this.getSelectedOmejora().setVcontenido(StringUtils.capitalize(this.getContenidoPlain().substring(0, 400)));
                }
                this.getSelectedOmejora().setNdestacado(this.getChkDestacado() ? BigDecimal.ONE : BigDecimal.ZERO);
                this.getSelectedOmejora().setDfechamodificacion(new Date());
                this.getSelectedOmejora().setVusuariomodificacion(usuario.getVlogin());
                this.getSelectedOmejora().setVmsjsolicita(this.getSelectedOmejora().getVmsjsolicita().toUpperCase());
                conocimientoService.saveOrUpdate(this.getSelectedOmejora());

                this.setContenidoPlain(Jsoup.parse(this.getContenidoHtml()).text());
                GcmFileUtils.writeStringToFileServer(this.getSelectedOmejora().getVruta(), "html.txt", this.getContenidoHtml());
                GcmFileUtils.writeStringToFileServer(this.getSelectedOmejora().getVruta(), "plain.txt", this.getContenidoPlain());

                HistorialService historialService = (HistorialService) ServiceFinder.findBean("HistorialService");
                Historial lastHistorial = historialService.getLastHistorialByConocimiento(this.getSelectedOmejora().getNconocimientoid());
                int lastversion;
                if (lastHistorial != null) {
                    lastversion = lastHistorial.getNnumversion().intValue();
                } else {
                    lastversion = 0;
                }
                String url = this.pathom.concat(this.getSelectedOmejora().getNconocimientoid().toString()).concat("/").concat(Integer.toString(lastversion + 1)).concat("/");

                ThistorialId thistorialId = new ThistorialId();
                thistorialId.setNconocimientoid(this.getSelectedOmejora().getNconocimientoid());
                thistorialId.setNhistorialid(historialService.getNextPK());
                Historial historial = new Historial();
                historial.setId(thistorialId);
                historial.setNtipoconocimientoid(Constante.OPORTUNIDADMEJORA);
                historial.setNcategoriaid(this.getSelectedCategoria().getNcategoriaid());
                historial.setVtitulo(this.getSelectedOmejora().getVtitulo());
                historial.setVdescripcion(this.getSelectedOmejora().getVdescripcion());
                historial.setNactivo(BigDecimal.ONE);
                historial.setNsituacionid(this.getSelectedOmejora().getNsituacionid());
                historial.setVruta(url);
                historial.setNnumversion(BigDecimal.valueOf(lastversion + 1));
                historial.setDfechacreacion(new Date());
                historial.setVusuariocreacion(usuario.getVlogin());
                historial.setVmsjsolicita(this.getSelectedOmejora().getVmsjsolicita());
                historial.setVmsjrespuesta(this.getSelectedOmejora().getVmsjrespuesta());
                historialService.saveOrUpdate(historial);

                GcmFileUtils.writeStringToFileServer(url, "html.txt", this.getContenidoHtml());
                GcmFileUtils.writeStringToFileServer(url, "plain.txt", this.getContenidoPlain());

                if (org.apache.commons.collections.CollectionUtils.isNotEmpty(this.getListaSeccion())) {
                    String url0 = this.getSelectedOmejora().getVruta().concat("s");
                    String url1 = url.concat("s");
                    SeccionService seccionService = (SeccionService) ServiceFinder.findBean("SeccionService");
                    SeccionHistService seccionHistService = (SeccionHistService) ServiceFinder.findBean("SeccionHistService");
                    for (Seccion seccion : this.getListaSeccion()) {
                        String ruta0 = url0.concat(seccion.getNorden().toString()).concat("/");
                        seccion.setVruta(ruta0);
                        if (seccion.getNseccionid() != null) {
                            seccion.setDfechamodificacion(new Date());
                            seccion.setVusuariomodificacion(usuario.getVlogin());
                        } else {
                            seccion.setNseccionid(seccionService.getNextPK());
                            seccion.setNconocimientoid(this.getSelectedOmejora().getNconocimientoid());
                            seccion.setDfechacreacion(new Date());
                            seccion.setVusuariocreacion(usuario.getVlogin());
                        }
                        seccionService.saveOrUpdate(seccion);

                        seccion.setDetallePlain(Jsoup.parse(seccion.getDetalleHtml()).text());
                        GcmFileUtils.writeStringToFileServer(ruta0, "html.txt", seccion.getDetalleHtml());
                        GcmFileUtils.writeStringToFileServer(ruta0, "plain.txt", seccion.getDetallePlain());

                        String ruta1 = url1.concat(seccion.getNorden().toString()).concat("/");
                        TseccionHistId tseccionHistId = new TseccionHistId();
                        tseccionHistId.setNconocimientoid(thistorialId.getNconocimientoid());
                        tseccionHistId.setNhistorialid(thistorialId.getNhistorialid());
                        tseccionHistId.setNseccionhid(seccionHistService.getNextPK());
                        SeccionHist seccionHist = new SeccionHist();
                        seccionHist.setId(tseccionHistId);
                        seccionHist.setNorden(seccion.getNorden());
                        seccionHist.setVruta(ruta1);
                        seccionHist.setVtitulo(seccion.getVtitulo());
                        seccionHist.setVusuariocreacion(usuario.getVlogin());
                        seccionHist.setDfechacreacion(new Date());
                        seccionHistService.saveOrUpdate(seccionHist);

                        GcmFileUtils.writeStringToFileServer(ruta1, "html.txt", seccion.getDetalleHtml());
                        GcmFileUtils.writeStringToFileServer(ruta1, "plain.txt", seccion.getDetallePlain());
                    }
                }

                this.setListaTargetVinculos(new ArrayList());
                this.getListaTargetVinculos().addAll(this.getListaTargetVinculosBL());
                this.getListaTargetVinculos().addAll(this.getListaTargetVinculosBP());
                this.getListaTargetVinculos().addAll(this.getListaTargetVinculosCT());
                this.getListaTargetVinculos().addAll(this.getListaTargetVinculosOM());
                this.getListaTargetVinculos().addAll(this.getListaTargetVinculosPR());
                this.getListaTargetVinculos().addAll(this.getListaTargetVinculosWK());

                if (org.apache.commons.collections.CollectionUtils.isNotEmpty(this.getListaTargetVinculos())) {
                    VinculoService vinculoService = (VinculoService) ServiceFinder.findBean("VinculoService");
                    VinculoHistService vinculoHistService = (VinculoHistService) ServiceFinder.findBean("VinculoHistService");
                    vinculoService.deleteByConocimiento(this.getSelectedOmejora().getNconocimientoid());
                    for (Consulta consulta : this.getListaTargetVinculos()) {
                        Vinculo vinculo = new Vinculo();
                        vinculo.setNvinculoid(vinculoService.getNextPK());
                        vinculo.setNconocimientoid(this.getSelectedOmejora().getNconocimientoid());
                        vinculo.setNconocimientovinc(consulta.getIdconocimiento());
                        vinculo.setNtipoconocimientovinc(consulta.getIdTipoConocimiento());
                        vinculo.setDfechacreacion(new Date());
                        vinculo.setVusuariocreacion(usuario.getVlogin());
                        vinculoService.saveOrUpdate(vinculo);

                        TvinculoHistId vinculoHistId = new TvinculoHistId();
                        vinculoHistId.setNvinculohid(vinculoHistService.getNextPK());
                        vinculoHistId.setNconocimientoid(thistorialId.getNconocimientoid());
                        vinculoHistId.setNhistorialid(thistorialId.getNhistorialid());
                        VinculoHist vinculoHist = new VinculoHist();
                        vinculoHist.setId(vinculoHistId);
                        vinculoHist.setNconocimientovinc(vinculo.getNconocimientovinc());
                        vinculoHist.setDfechacreacion(new Date());
                        vinculoHist.setVusuariocreacion(usuario.getVlogin());
                        vinculoHistService.saveOrUpdate(vinculoHist);
                    }
                }

                AsignacionService serviceasig = (AsignacionService) ServiceFinder.findBean("AsignacionService");
                this.getSelectedAsignacion().setNestadoid(BigDecimal.valueOf(Long.parseLong("2")));
                this.getSelectedAsignacion().setDfechaatencion(new Date());
                this.getSelectedAsignacion().setNaccionid(BigDecimal.valueOf(Long.parseLong("9")));
                serviceasig.saveOrUpdate(this.getSelectedAsignacion());

                Asignacion asignacion = new Asignacion();
                asignacion.setNasignacionid(serviceasig.getNextPK());
                asignacion.setNtipoconocimientoid(Constante.OPORTUNIDADMEJORA);
                asignacion.setNconocimientoid(this.getSelectedOmejora().getNconocimientoid());
                asignacion.setNestadoid(BigDecimal.valueOf(Long.parseLong("1")));
                asignacion.setNusuarioid(serviceasig.getUserCreacionByContenido(this.getSelectedOmejora().getNtipoconocimientoid(), this.getSelectedOmejora().getNconocimientoid()));
                asignacion.setDfechaasignacion(new Date());
                asignacion.setDfechacreacion(new Date());
                serviceasig.saveOrUpdate(asignacion);

                loginMB.refreshNotifications();

                this.fSInfMod = "true";
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "INFO.", "Se envió la solicitud de información adicional.");
                FacesContext.getCurrentInstance().addMessage(null, message);
                FacesContext.getCurrentInstance().getExternalContext().redirect("/gescon/index.xhtml");
            }

        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
    }

    public void sendWikiRespuesta(ActionEvent event) {
        try {
            if (StringUtils.isBlank(this.getSelectedWiki().getVmsjrespuesta())) {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, Constante.SEVERETY_ALERTA, "Campo requerido. Ingrese el mensaje a enviar.");
                FacesContext.getCurrentInstance().addMessage(null, message);
            } else {
                //this.setDescripcionHtml(JSFUtils.getRequestParameter("descHtml"));
                if (this.getChkDestacado()) {
                    ConsultaService consultaService = (ConsultaService) ServiceFinder.findBean("ConsultaService");
                    HashMap filter = new HashMap();
                    filter.put("ntipoconocimientoid", Constante.WIKI);
                    BigDecimal cant = consultaService.countDestacadosByTipoConocimiento(filter);
                    if (cant.intValue() >= 10) {
                        this.setListaDestacados(consultaService.getDestacadosByTipoConocimiento(filter));
                        RequestContext.getCurrentInstance().execute("PF('destDialog').show();");
                        return;
                    }
                }
                LoginMB loginMB = (LoginMB) JSFUtils.getSessionAttribute("loginMB");
                User usuario = loginMB.getUser();
                ConocimientoService conocimientoService = (ConocimientoService) ServiceFinder.findBean("ConocimientoService");
                this.setDescripcionPlain(Jsoup.parse(this.getDescripcionHtml()).text());
                this.getSelectedWiki().setNcategoriaid(this.getSelectedCategoria().getNcategoriaid());
                this.getSelectedWiki().setVtitulo(StringUtils.upperCase(this.getSelectedWiki().getVtitulo()));
                if (this.getDescripcionPlain().length() < 400) {
                    this.getSelectedWiki().setVdescripcion(StringUtils.capitalize(this.getDescripcionPlain()));
                } else {
                    this.getSelectedWiki().setVdescripcion(StringUtils.capitalize(this.getDescripcionPlain().substring(0, 400)));
                }
                this.getSelectedWiki().setNdestacado(this.getChkDestacado() ? BigDecimal.ONE : BigDecimal.ZERO);
                this.getSelectedWiki().setDfechamodificacion(new Date());
                this.getSelectedWiki().setVusuariomodificacion(usuario.getVlogin());
                this.getSelectedWiki().setVmsjrespuesta(this.getSelectedWiki().getVmsjrespuesta().toUpperCase());
                conocimientoService.saveOrUpdate(this.getSelectedWiki());

                GcmFileUtils.writeStringToFileServer(this.getSelectedWiki().getVruta(), "html.txt", this.getDescripcionHtml());
                GcmFileUtils.writeStringToFileServer(this.getSelectedWiki().getVruta(), "plain.txt", this.getDescripcionPlain());

                HistorialService historialService = (HistorialService) ServiceFinder.findBean("HistorialService");
                Historial lastHistorial = historialService.getLastHistorialByConocimiento(this.getSelectedWiki().getNconocimientoid());
                int lastversion;
                if (lastHistorial != null) {
                    lastversion = lastHistorial.getNnumversion().intValue();
                } else {
                    lastversion = 0;
                }
                String url = this.pathwk.concat(this.getSelectedWiki().getNconocimientoid().toString()).concat("/").concat(Integer.toString(lastversion + 1)).concat("/");

                ThistorialId thistorialId = new ThistorialId();
                thistorialId.setNconocimientoid(this.getSelectedWiki().getNconocimientoid());
                thistorialId.setNhistorialid(historialService.getNextPK());
                Historial historial = new Historial();
                historial.setId(thistorialId);
                historial.setNtipoconocimientoid(Constante.WIKI);
                historial.setNcategoriaid(this.getSelectedCategoria().getNcategoriaid());
                historial.setVtitulo(this.getSelectedWiki().getVtitulo());
                historial.setNactivo(BigDecimal.ONE);
                historial.setNsituacionid(this.getSelectedWiki().getNsituacionid());
                historial.setVruta(url);
                historial.setNnumversion(BigDecimal.valueOf(lastversion + 1));
                historial.setDfechacreacion(new Date());
                historial.setVusuariocreacion(usuario.getVlogin());
                historial.setVmsjsolicita(this.getSelectedWiki().getVmsjsolicita());
                historial.setVmsjrespuesta(this.getSelectedWiki().getVmsjrespuesta());
                historialService.saveOrUpdate(historial);

                GcmFileUtils.writeStringToFileServer(url, "html.txt", this.getDescripcionHtml());
                GcmFileUtils.writeStringToFileServer(url, "plain.txt", this.getDescripcionPlain());

                if (org.apache.commons.collections.CollectionUtils.isNotEmpty(this.getListaSeccion())) {
                    String url0 = this.getSelectedWiki().getVruta().concat("s");
                    String url1 = url.concat("s");
                    SeccionService seccionService = (SeccionService) ServiceFinder.findBean("SeccionService");
                    SeccionHistService seccionHistService = (SeccionHistService) ServiceFinder.findBean("SeccionHistService");
                    for (Seccion seccion : this.getListaSeccion()) {
                        String ruta0 = url0.concat(seccion.getNorden().toString()).concat("/");
                        seccion.setVruta(ruta0);
                        if (seccion.getNseccionid() != null) {
                            seccion.setDfechamodificacion(new Date());
                            seccion.setVusuariomodificacion(usuario.getVlogin());
                        } else {
                            seccion.setNseccionid(seccionService.getNextPK());
                            seccion.setNconocimientoid(this.getSelectedWiki().getNconocimientoid());
                            seccion.setDfechacreacion(new Date());
                            seccion.setVusuariocreacion(usuario.getVlogin());
                        }
                        seccionService.saveOrUpdate(seccion);

                        seccion.setDetallePlain(Jsoup.parse(seccion.getDetalleHtml()).text());
                        GcmFileUtils.writeStringToFileServer(ruta0, "html.txt", seccion.getDetalleHtml());
                        GcmFileUtils.writeStringToFileServer(ruta0, "plain.txt", seccion.getDetallePlain());

                        String ruta1 = url1.concat(seccion.getNorden().toString()).concat("/");
                        TseccionHistId tseccionHistId = new TseccionHistId();
                        tseccionHistId.setNconocimientoid(thistorialId.getNconocimientoid());
                        tseccionHistId.setNhistorialid(thistorialId.getNhistorialid());
                        tseccionHistId.setNseccionhid(seccionHistService.getNextPK());
                        SeccionHist seccionHist = new SeccionHist();
                        seccionHist.setId(tseccionHistId);
                        seccionHist.setNorden(seccion.getNorden());
                        seccionHist.setVruta(ruta1);
                        seccionHist.setVtitulo(seccion.getVtitulo());
                        seccionHist.setVusuariocreacion(usuario.getVlogin());
                        seccionHist.setDfechacreacion(new Date());
                        seccionHistService.saveOrUpdate(seccionHist);

                        GcmFileUtils.writeStringToFileServer(ruta1, "html.txt", seccion.getDetalleHtml());
                        GcmFileUtils.writeStringToFileServer(ruta1, "plain.txt", seccion.getDetallePlain());
                    }
                }

                this.setListaTargetVinculos(new ArrayList());
                this.getListaTargetVinculos().addAll(this.getListaTargetVinculosBL());
                this.getListaTargetVinculos().addAll(this.getListaTargetVinculosBP());
                this.getListaTargetVinculos().addAll(this.getListaTargetVinculosCT());
                this.getListaTargetVinculos().addAll(this.getListaTargetVinculosOM());
                this.getListaTargetVinculos().addAll(this.getListaTargetVinculosPR());
                this.getListaTargetVinculos().addAll(this.getListaTargetVinculosWK());

                if (org.apache.commons.collections.CollectionUtils.isNotEmpty(this.getListaTargetVinculos())) {
                    VinculoService vinculoService = (VinculoService) ServiceFinder.findBean("VinculoService");
                    VinculoHistService vinculoHistService = (VinculoHistService) ServiceFinder.findBean("VinculoHistService");
                    vinculoService.deleteByConocimiento(this.getSelectedWiki().getNconocimientoid());
                    for (Consulta consulta : this.getListaTargetVinculos()) {
                        Vinculo vinculo = new Vinculo();
                        vinculo.setNvinculoid(vinculoService.getNextPK());
                        vinculo.setNconocimientoid(this.getSelectedWiki().getNconocimientoid());
                        vinculo.setNconocimientovinc(consulta.getIdconocimiento());
                        vinculo.setNtipoconocimientovinc(consulta.getIdTipoConocimiento());
                        vinculo.setDfechacreacion(new Date());
                        vinculo.setVusuariocreacion(usuario.getVlogin());
                        vinculoService.saveOrUpdate(vinculo);

                        TvinculoHistId vinculoHistId = new TvinculoHistId();
                        vinculoHistId.setNvinculohid(vinculoHistService.getNextPK());
                        vinculoHistId.setNconocimientoid(thistorialId.getNconocimientoid());
                        vinculoHistId.setNhistorialid(thistorialId.getNhistorialid());
                        VinculoHist vinculoHist = new VinculoHist();
                        vinculoHist.setId(vinculoHistId);
                        vinculoHist.setNconocimientovinc(vinculo.getNconocimientovinc());
                        vinculoHist.setDfechacreacion(new Date());
                        vinculoHist.setVusuariocreacion(usuario.getVlogin());
                        vinculoHistService.saveOrUpdate(vinculoHist);
                    }
                }

                AsignacionService serviceasig = (AsignacionService) ServiceFinder.findBean("AsignacionService");
                this.getSelectedAsignacion().setNestadoid(BigDecimal.valueOf(Long.parseLong("2")));
                this.getSelectedAsignacion().setDfechaatencion(new Date());
                this.getSelectedAsignacion().setNaccionid(BigDecimal.valueOf(Long.parseLong("10")));
                serviceasig.saveOrUpdate(this.getSelectedAsignacion());

                Asignacion asignacion = new Asignacion();
                asignacion.setNasignacionid(serviceasig.getNextPK());
                asignacion.setNtipoconocimientoid(Constante.WIKI);
                asignacion.setNconocimientoid(this.getSelectedWiki().getNconocimientoid());
                asignacion.setNestadoid(BigDecimal.valueOf(Long.parseLong("1")));
                CategoriaService categoriaService = (CategoriaService) ServiceFinder.findBean("CategoriaService");
                asignacion.setNusuarioid(categoriaService.getCategoriaById(this.getSelectedWiki().getNcategoriaid()).getNmoderador());
                asignacion.setDfechaasignacion(new Date());
                asignacion.setDfechacreacion(new Date());
                serviceasig.saveOrUpdate(asignacion);

                loginMB.refreshNotifications();

                this.fSInfMod = "true";
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "INFO.", "Solicitud de información respondida.");
                FacesContext.getCurrentInstance().addMessage(null, message);
                FacesContext.getCurrentInstance().getExternalContext().redirect("/gescon/index.xhtml");
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
    }

    public void sendBpracticaRespuesta(ActionEvent event) {
        try {
            if (StringUtils.isBlank(this.getSelectedBpractica().getVmsjrespuesta())) {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, Constante.SEVERETY_ALERTA, "Campo requerido. Ingrese el mensaje a enviar.");
                FacesContext.getCurrentInstance().addMessage(null, message);
            } else {
                //this.setDescripcionHtml(JSFUtils.getRequestParameter("descHtml"));
                if (this.getChkDestacado()) {
                    ConsultaService consultaService = (ConsultaService) ServiceFinder.findBean("ConsultaService");
                    HashMap filter = new HashMap();
                    filter.put("ntipoconocimientoid", Constante.BUENAPRACTICA);
                    BigDecimal cant = consultaService.countDestacadosByTipoConocimiento(filter);
                    if (cant.intValue() >= 10) {
                        this.setListaDestacados(consultaService.getDestacadosByTipoConocimiento(filter));
                        RequestContext.getCurrentInstance().execute("PF('destDialog').show();");
                        return;
                    }
                }
                this.setDescripcionPlain(Jsoup.parse(this.getDescripcionHtml()).text());
                LoginMB loginMB = (LoginMB) JSFUtils.getSessionAttribute("loginMB");
                User usuario = loginMB.getUser();
                ConocimientoService conocimientoService = (ConocimientoService) ServiceFinder.findBean("ConocimientoService");
                this.getSelectedBpractica().setNcategoriaid(this.getSelectedCategoria().getNcategoriaid());
                this.getSelectedBpractica().setVtitulo(StringUtils.upperCase(this.getSelectedBpractica().getVtitulo().trim()));
                if (this.getDescripcionPlain().length() < 400) {
                    this.getSelectedBpractica().setVdescripcion(StringUtils.capitalize(this.getDescripcionPlain()));
                } else {
                    this.getSelectedBpractica().setVdescripcion(StringUtils.capitalize(this.getDescripcionPlain().substring(0, 400)));
                }
                this.getSelectedBpractica().setNdestacado(this.getChkDestacado() ? BigDecimal.ONE : BigDecimal.ZERO);
                this.getSelectedBpractica().setDfechamodificacion(new Date());
                this.getSelectedBpractica().setVusuariomodificacion(usuario.getVlogin());
                this.getSelectedBpractica().setVmsjrespuesta(this.getSelectedBpractica().getVmsjrespuesta().toUpperCase());
                conocimientoService.saveOrUpdate(this.getSelectedBpractica());

                GcmFileUtils.writeStringToFileServer(this.getSelectedBpractica().getVruta(), "html.txt", this.getDescripcionHtml());
                GcmFileUtils.writeStringToFileServer(this.getSelectedBpractica().getVruta(), "plain.txt", this.getDescripcionPlain());

                HistorialService historialService = (HistorialService) ServiceFinder.findBean("HistorialService");
                Historial lastHistorial = historialService.getLastHistorialByConocimiento(this.getSelectedBpractica().getNconocimientoid());
                int lastversion;
                if (lastHistorial != null) {
                    lastversion = lastHistorial.getNnumversion().intValue();
                } else {
                    lastversion = 0;
                }
                String url = this.pathbp.concat(this.getSelectedBpractica().getNconocimientoid().toString()).concat("/").concat(Integer.toString(lastversion + 1)).concat("/");

                ThistorialId thistorialId = new ThistorialId();
                thistorialId.setNconocimientoid(this.getSelectedBpractica().getNconocimientoid());
                thistorialId.setNhistorialid(historialService.getNextPK());
                Historial historial = new Historial();
                historial.setId(thistorialId);
                historial.setNtipoconocimientoid(Constante.BUENAPRACTICA);
                historial.setNcategoriaid(this.getSelectedCategoria().getNcategoriaid());
                historial.setVtitulo(this.getSelectedBpractica().getVtitulo());
                historial.setNactivo(BigDecimal.ONE);
                historial.setNsituacionid(this.getSelectedBpractica().getNsituacionid());
                historial.setVruta(url);
                historial.setNnumversion(BigDecimal.valueOf(lastversion + 1));
                historial.setDfechacreacion(new Date());
                historial.setVusuariocreacion(usuario.getVlogin());
                historial.setVmsjsolicita(this.getSelectedBpractica().getVmsjsolicita());
                historial.setVmsjrespuesta(this.getSelectedBpractica().getVmsjrespuesta());
                historialService.saveOrUpdate(historial);

                GcmFileUtils.writeStringToFileServer(url, "html.txt", this.getDescripcionHtml());
                GcmFileUtils.writeStringToFileServer(url, "plain.txt", this.getDescripcionPlain());

                if (org.apache.commons.collections.CollectionUtils.isNotEmpty(this.getListaSeccion())) {
                    String url0 = this.getSelectedBpractica().getVruta().concat("s");
                    String url1 = url.concat("s");
                    SeccionService seccionService = (SeccionService) ServiceFinder.findBean("SeccionService");
                    SeccionHistService seccionHistService = (SeccionHistService) ServiceFinder.findBean("SeccionHistService");
                    for (Seccion seccion : this.getListaSeccion()) {
                        String ruta0 = url0.concat(seccion.getNorden().toString()).concat("/");
                        seccion.setVruta(ruta0);
                        if (seccion.getNseccionid() != null) {
                            seccion.setDfechamodificacion(new Date());
                            seccion.setVusuariomodificacion(usuario.getVlogin());
                        } else {
                            seccion.setNseccionid(seccionService.getNextPK());
                            seccion.setNconocimientoid(this.getSelectedBpractica().getNconocimientoid());
                            seccion.setDfechacreacion(new Date());
                            seccion.setVusuariocreacion(usuario.getVlogin());
                        }
                        seccionService.saveOrUpdate(seccion);

                        seccion.setDetallePlain(Jsoup.parse(seccion.getDetalleHtml()).text());
                        GcmFileUtils.writeStringToFileServer(ruta0, "html.txt", seccion.getDetalleHtml());
                        GcmFileUtils.writeStringToFileServer(ruta0, "plain.txt", seccion.getDetallePlain());

                        String ruta1 = url1.concat(seccion.getNorden().toString()).concat("/");
                        TseccionHistId tseccionHistId = new TseccionHistId();
                        tseccionHistId.setNconocimientoid(thistorialId.getNconocimientoid());
                        tseccionHistId.setNhistorialid(thistorialId.getNhistorialid());
                        tseccionHistId.setNseccionhid(seccionHistService.getNextPK());
                        SeccionHist seccionHist = new SeccionHist();
                        seccionHist.setId(tseccionHistId);
                        seccionHist.setNorden(seccion.getNorden());
                        seccionHist.setVruta(ruta1);
                        seccionHist.setVtitulo(seccion.getVtitulo());
                        seccionHist.setVusuariocreacion(usuario.getVlogin());
                        seccionHist.setDfechacreacion(new Date());
                        seccionHistService.saveOrUpdate(seccionHist);

                        GcmFileUtils.writeStringToFileServer(ruta1, "html.txt", seccion.getDetalleHtml());
                        GcmFileUtils.writeStringToFileServer(ruta1, "plain.txt", seccion.getDetallePlain());
                    }
                }

                this.setListaTargetVinculos(new ArrayList());
                this.getListaTargetVinculos().addAll(this.getListaTargetVinculosBL());
                this.getListaTargetVinculos().addAll(this.getListaTargetVinculosBP());
                this.getListaTargetVinculos().addAll(this.getListaTargetVinculosCT());
                this.getListaTargetVinculos().addAll(this.getListaTargetVinculosOM());
                this.getListaTargetVinculos().addAll(this.getListaTargetVinculosPR());
                this.getListaTargetVinculos().addAll(this.getListaTargetVinculosWK());

                if (org.apache.commons.collections.CollectionUtils.isNotEmpty(this.getListaTargetVinculos())) {
                    VinculoService vinculoService = (VinculoService) ServiceFinder.findBean("VinculoService");
                    VinculoHistService vinculoHistService = (VinculoHistService) ServiceFinder.findBean("VinculoHistService");
                    vinculoService.deleteByConocimiento(this.getSelectedBpractica().getNconocimientoid());
                    for (Consulta consulta : this.getListaTargetVinculos()) {
                        Vinculo vinculo = new Vinculo();
                        vinculo.setNvinculoid(vinculoService.getNextPK());
                        vinculo.setNconocimientoid(this.getSelectedBpractica().getNconocimientoid());
                        vinculo.setNconocimientovinc(consulta.getIdconocimiento());
                        vinculo.setNtipoconocimientovinc(consulta.getIdTipoConocimiento());
                        vinculo.setDfechacreacion(new Date());
                        vinculo.setVusuariocreacion(usuario.getVlogin());
                        vinculoService.saveOrUpdate(vinculo);

                        TvinculoHistId vinculoHistId = new TvinculoHistId();
                        vinculoHistId.setNvinculohid(vinculoHistService.getNextPK());
                        vinculoHistId.setNconocimientoid(thistorialId.getNconocimientoid());
                        vinculoHistId.setNhistorialid(thistorialId.getNhistorialid());
                        VinculoHist vinculoHist = new VinculoHist();
                        vinculoHist.setId(vinculoHistId);
                        vinculoHist.setNconocimientovinc(vinculo.getNconocimientovinc());
                        vinculoHist.setDfechacreacion(new Date());
                        vinculoHist.setVusuariocreacion(usuario.getVlogin());
                        vinculoHistService.saveOrUpdate(vinculoHist);
                    }
                }

                AsignacionService serviceasig = (AsignacionService) ServiceFinder.findBean("AsignacionService");
                this.getSelectedAsignacion().setNestadoid(BigDecimal.valueOf(Long.parseLong("2")));
                this.getSelectedAsignacion().setDfechaatencion(new Date());
                this.getSelectedAsignacion().setNaccionid(BigDecimal.valueOf(Long.parseLong("10")));
                serviceasig.saveOrUpdate(this.getSelectedAsignacion());

                Asignacion asignacion = new Asignacion();
                asignacion.setNasignacionid(serviceasig.getNextPK());
                asignacion.setNtipoconocimientoid(Constante.BUENAPRACTICA);
                asignacion.setNconocimientoid(this.getSelectedBpractica().getNconocimientoid());
                asignacion.setNestadoid(BigDecimal.valueOf(Long.parseLong("1")));
                CategoriaService categoriaService = (CategoriaService) ServiceFinder.findBean("CategoriaService");
                asignacion.setNusuarioid(categoriaService.getCategoriaById(this.getSelectedBpractica().getNcategoriaid()).getNmoderador());
                asignacion.setDfechaasignacion(new Date());
                asignacion.setDfechacreacion(new Date());
                serviceasig.saveOrUpdate(asignacion);

                loginMB.refreshNotifications();

                this.fSInfMod = "true";
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "INFO.", "Se respondió la solicitud de información.");
                FacesContext.getCurrentInstance().addMessage(null, message);
                FacesContext.getCurrentInstance().getExternalContext().redirect("/gescon/index.xhtml");
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
    }

    public void sendOmejoraRespuesta(ActionEvent event) {
        try {
            if (StringUtils.isBlank(this.getSelectedOmejora().getVmsjrespuesta())) {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, Constante.SEVERETY_ALERTA, "Campo requerido. Ingrese el mensaje a enviar.");
                FacesContext.getCurrentInstance().addMessage(null, message);
            } else {
                //this.setContenidoHtml(JSFUtils.getRequestParameter("descHtml"));
                if (this.getChkDestacado()) {
                    ConsultaService consultaService = (ConsultaService) ServiceFinder.findBean("ConsultaService");
                    HashMap filter = new HashMap();
                    filter.put("ntipoconocimientoid", Constante.OPORTUNIDADMEJORA);
                    BigDecimal cant = consultaService.countDestacadosByTipoConocimiento(filter);
                    if (cant.intValue() >= 10) {
                        this.setListaDestacados(consultaService.getDestacadosByTipoConocimiento(filter));
                        RequestContext.getCurrentInstance().execute("PF('destDialog').show();");
                        return;
                    }
                }
                this.setContenidoPlain(Jsoup.parse(this.getContenidoHtml()).text());
                LoginMB loginMB = (LoginMB) JSFUtils.getSessionAttribute("loginMB");
                User usuario = loginMB.getUser();
                ConocimientoService conocimientoService = (ConocimientoService) ServiceFinder.findBean("ConocimientoService");
                this.getSelectedOmejora().setNcategoriaid(this.getSelectedCategoria().getNcategoriaid());
                this.getSelectedOmejora().setVtitulo(StringUtils.upperCase(this.getSelectedOmejora().getVtitulo()));
                this.getSelectedOmejora().setVdescripcion(StringUtils.upperCase(this.getSelectedOmejora().getVdescripcion()));
                if (this.getContenidoPlain().length() < 400) {
                    this.getSelectedOmejora().setVcontenido(StringUtils.capitalize(this.getContenidoPlain()));
                } else {
                    this.getSelectedOmejora().setVcontenido(StringUtils.capitalize(this.getContenidoPlain().substring(0, 400)));
                }
                this.getSelectedOmejora().setNdestacado(this.getChkDestacado() ? BigDecimal.ONE : BigDecimal.ZERO);
                this.getSelectedOmejora().setDfechamodificacion(new Date());
                this.getSelectedOmejora().setVusuariomodificacion(usuario.getVlogin());
                this.getSelectedOmejora().setVmsjrespuesta(this.getSelectedOmejora().getVmsjrespuesta().toUpperCase());
                conocimientoService.saveOrUpdate(this.getSelectedOmejora());

                this.setContenidoPlain(Jsoup.parse(this.getContenidoHtml()).text());
                GcmFileUtils.writeStringToFileServer(this.getSelectedOmejora().getVruta(), "html.txt", this.getContenidoHtml());
                GcmFileUtils.writeStringToFileServer(this.getSelectedOmejora().getVruta(), "plain.txt", this.getContenidoPlain());

                HistorialService historialService = (HistorialService) ServiceFinder.findBean("HistorialService");
                Historial lastHistorial = historialService.getLastHistorialByConocimiento(this.getSelectedOmejora().getNconocimientoid());
                int lastversion;
                if (lastHistorial != null) {
                    lastversion = lastHistorial.getNnumversion().intValue();
                } else {
                    lastversion = 0;
                }
                String url = this.pathom.concat(this.getSelectedOmejora().getNconocimientoid().toString()).concat("/").concat(Integer.toString(lastversion + 1)).concat("/");

                ThistorialId thistorialId = new ThistorialId();
                thistorialId.setNconocimientoid(this.getSelectedOmejora().getNconocimientoid());
                thistorialId.setNhistorialid(historialService.getNextPK());
                Historial historial = new Historial();
                historial.setId(thistorialId);
                historial.setNtipoconocimientoid(Constante.OPORTUNIDADMEJORA);
                historial.setNcategoriaid(this.getSelectedCategoria().getNcategoriaid());
                historial.setVtitulo(this.getSelectedOmejora().getVtitulo());
                historial.setVdescripcion(this.getSelectedOmejora().getVdescripcion());
                historial.setNactivo(BigDecimal.ONE);
                historial.setNsituacionid(this.getSelectedOmejora().getNsituacionid());
                historial.setVruta(url);
                historial.setNnumversion(BigDecimal.valueOf(lastversion + 1));
                historial.setDfechacreacion(new Date());
                historial.setVusuariocreacion(usuario.getVlogin());
                historial.setVmsjsolicita(this.getSelectedOmejora().getVmsjsolicita());
                historial.setVmsjrespuesta(this.getSelectedOmejora().getVmsjrespuesta());
                historialService.saveOrUpdate(historial);

                GcmFileUtils.writeStringToFileServer(url, "html.txt", this.getContenidoHtml());
                GcmFileUtils.writeStringToFileServer(url, "plain.txt", this.getContenidoPlain());

                if (org.apache.commons.collections.CollectionUtils.isNotEmpty(this.getListaSeccion())) {
                    String url0 = this.getSelectedOmejora().getVruta().concat("s");
                    String url1 = url.concat("s");
                    SeccionService seccionService = (SeccionService) ServiceFinder.findBean("SeccionService");
                    SeccionHistService seccionHistService = (SeccionHistService) ServiceFinder.findBean("SeccionHistService");
                    for (Seccion seccion : this.getListaSeccion()) {
                        String ruta0 = url0.concat(seccion.getNorden().toString()).concat("/");
                        seccion.setVruta(ruta0);
                        if (seccion.getNseccionid() != null) {
                            seccion.setDfechamodificacion(new Date());
                            seccion.setVusuariomodificacion(usuario.getVlogin());
                        } else {
                            seccion.setNseccionid(seccionService.getNextPK());
                            seccion.setNconocimientoid(this.getSelectedOmejora().getNconocimientoid());
                            seccion.setDfechacreacion(new Date());
                            seccion.setVusuariocreacion(usuario.getVlogin());
                        }
                        seccionService.saveOrUpdate(seccion);

                        seccion.setDetallePlain(Jsoup.parse(seccion.getDetalleHtml()).text());
                        GcmFileUtils.writeStringToFileServer(ruta0, "html.txt", seccion.getDetalleHtml());
                        GcmFileUtils.writeStringToFileServer(ruta0, "plain.txt", seccion.getDetallePlain());

                        String ruta1 = url1.concat(seccion.getNorden().toString()).concat("/");
                        TseccionHistId tseccionHistId = new TseccionHistId();
                        tseccionHistId.setNconocimientoid(thistorialId.getNconocimientoid());
                        tseccionHistId.setNhistorialid(thistorialId.getNhistorialid());
                        tseccionHistId.setNseccionhid(seccionHistService.getNextPK());
                        SeccionHist seccionHist = new SeccionHist();
                        seccionHist.setId(tseccionHistId);
                        seccionHist.setNorden(seccion.getNorden());
                        seccionHist.setVruta(ruta1);
                        seccionHist.setVtitulo(seccion.getVtitulo());
                        seccionHist.setVusuariocreacion(usuario.getVlogin());
                        seccionHist.setDfechacreacion(new Date());
                        seccionHistService.saveOrUpdate(seccionHist);

                        GcmFileUtils.writeStringToFileServer(ruta1, "html.txt", seccion.getDetalleHtml());
                        GcmFileUtils.writeStringToFileServer(ruta1, "plain.txt", seccion.getDetallePlain());
                    }
                }

                this.setListaTargetVinculos(new ArrayList());
                this.getListaTargetVinculos().addAll(this.getListaTargetVinculosBL());
                this.getListaTargetVinculos().addAll(this.getListaTargetVinculosBP());
                this.getListaTargetVinculos().addAll(this.getListaTargetVinculosCT());
                this.getListaTargetVinculos().addAll(this.getListaTargetVinculosOM());
                this.getListaTargetVinculos().addAll(this.getListaTargetVinculosPR());
                this.getListaTargetVinculos().addAll(this.getListaTargetVinculosWK());

                if (org.apache.commons.collections.CollectionUtils.isNotEmpty(this.getListaTargetVinculos())) {
                    VinculoService vinculoService = (VinculoService) ServiceFinder.findBean("VinculoService");
                    VinculoHistService vinculoHistService = (VinculoHistService) ServiceFinder.findBean("VinculoHistService");
                    vinculoService.deleteByConocimiento(this.getSelectedOmejora().getNconocimientoid());
                    for (Consulta consulta : this.getListaTargetVinculos()) {
                        Vinculo vinculo = new Vinculo();
                        vinculo.setNvinculoid(vinculoService.getNextPK());
                        vinculo.setNconocimientoid(this.getSelectedOmejora().getNconocimientoid());
                        vinculo.setNconocimientovinc(consulta.getIdconocimiento());
                        vinculo.setNtipoconocimientovinc(consulta.getIdTipoConocimiento());
                        vinculo.setDfechacreacion(new Date());
                        vinculo.setVusuariocreacion(usuario.getVlogin());
                        vinculoService.saveOrUpdate(vinculo);

                        TvinculoHistId vinculoHistId = new TvinculoHistId();
                        vinculoHistId.setNvinculohid(vinculoHistService.getNextPK());
                        vinculoHistId.setNconocimientoid(thistorialId.getNconocimientoid());
                        vinculoHistId.setNhistorialid(thistorialId.getNhistorialid());
                        VinculoHist vinculoHist = new VinculoHist();
                        vinculoHist.setId(vinculoHistId);
                        vinculoHist.setNconocimientovinc(vinculo.getNconocimientovinc());
                        vinculoHist.setDfechacreacion(new Date());
                        vinculoHist.setVusuariocreacion(usuario.getVlogin());
                        vinculoHistService.saveOrUpdate(vinculoHist);
                    }
                }

                AsignacionService serviceasig = (AsignacionService) ServiceFinder.findBean("AsignacionService");
                this.getSelectedAsignacion().setNestadoid(BigDecimal.valueOf(Long.parseLong("2")));
                this.getSelectedAsignacion().setDfechaatencion(new Date());
                this.getSelectedAsignacion().setNaccionid(BigDecimal.valueOf(Long.parseLong("10")));
                serviceasig.saveOrUpdate(this.getSelectedAsignacion());

                Asignacion asignacion = new Asignacion();
                asignacion.setNasignacionid(serviceasig.getNextPK());
                asignacion.setNtipoconocimientoid(Constante.OPORTUNIDADMEJORA);
                asignacion.setNconocimientoid(this.getSelectedOmejora().getNconocimientoid());
                asignacion.setNestadoid(BigDecimal.valueOf(Long.parseLong("1")));
                CategoriaService categoriaService = (CategoriaService) ServiceFinder.findBean("CategoriaService");
                asignacion.setNusuarioid(categoriaService.getCategoriaById(this.getSelectedOmejora().getNcategoriaid()).getNmoderador());
                asignacion.setDfechaasignacion(new Date());
                asignacion.setDfechacreacion(new Date());
                serviceasig.saveOrUpdate(asignacion);

                loginMB.refreshNotifications();

                this.fSInfMod = "true";
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "INFO.", "Respuesta de la solicitud de información enviada.");
                FacesContext.getCurrentInstance().addMessage(null, message);
                FacesContext.getCurrentInstance().getExternalContext().redirect("/gescon/index.xhtml");
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
    }

    public void toCancelRespWikiMod(ActionEvent event) {
        try {
            RequestContext.getCurrentInstance().execute("PF('respModDialog').hide();");
            RequestContext.getCurrentInstance().execute("PF('siModDialog').hide();");
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
    }

    public void toCancelRespBpracticaMod(ActionEvent event) {
        try {
            RequestContext.getCurrentInstance().execute("PF('respModDialog').hide();");
            RequestContext.getCurrentInstance().execute("PF('siModDialog').hide();");
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
    }

    public void toCancelRespOmejoraMod(ActionEvent event) {
        try {
            RequestContext.getCurrentInstance().execute("PF('respModDialog').hide();");
            RequestContext.getCurrentInstance().execute("PF('siModDialog').hide();");
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
    }

    public void rechazarWiki(ActionEvent event) {
        try {
            ConocimientoService conocimientoService = (ConocimientoService) ServiceFinder.findBean("ConocimientoService");
            this.getSelectedWiki().setNsituacionid(BigDecimal.valueOf((long) 7));
            conocimientoService.saveOrUpdate(this.getSelectedWiki());

            AsignacionService serviceasig = (AsignacionService) ServiceFinder.findBean("AsignacionService");
            this.getSelectedAsignacion().setNaccionid(BigDecimal.valueOf(Long.parseLong("13")));
            this.getSelectedAsignacion().setNestadoid(BigDecimal.valueOf(Long.parseLong("2")));
            this.getSelectedAsignacion().setDfechaatencion(new Date());
            serviceasig.saveOrUpdate(this.getSelectedAsignacion());

            LoginMB loginMB = (LoginMB) JSFUtils.getSessionAttribute("loginMB");
            loginMB.refreshNotifications();

            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "INFO.", "Se rechazó el wiki.");
            FacesContext.getCurrentInstance().addMessage(null, message);
            FacesContext.getCurrentInstance().getExternalContext().redirect("/gescon/index.xhtml");
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
    }

    public String rechazarOmejora() {
        String pagina = null;
        try {
            ConocimientoService conocimientoService = (ConocimientoService) ServiceFinder.findBean("ConocimientoService");
            this.getSelectedOmejora().setNsituacionid(BigDecimal.valueOf((long) 7));
            conocimientoService.saveOrUpdate(this.getSelectedOmejora());

            AsignacionService serviceasig = (AsignacionService) ServiceFinder.findBean("AsignacionService");
            this.getSelectedAsignacion().setNaccionid(BigDecimal.valueOf(Long.parseLong("13")));
            this.getSelectedAsignacion().setNestadoid(BigDecimal.valueOf(Long.parseLong("2")));
            this.getSelectedAsignacion().setDfechaatencion(new Date());
            serviceasig.saveOrUpdate(this.getSelectedAsignacion());

            LoginMB loginMB = (LoginMB) JSFUtils.getSessionAttribute("loginMB");
            loginMB.refreshNotifications();

            pagina = "/index.xhtml";

        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
        return pagina;
    }

    public String rechazarBpractica() {
        String pagina = null;
        try {
            ConocimientoService conocimientoService = (ConocimientoService) ServiceFinder.findBean("ConocimientoService");
            this.getSelectedBpractica().setNsituacionid(BigDecimal.valueOf((long) 7));
            conocimientoService.saveOrUpdate(this.getSelectedBpractica());

            AsignacionService serviceasig = (AsignacionService) ServiceFinder.findBean("AsignacionService");
            this.getSelectedAsignacion().setNaccionid(BigDecimal.valueOf(Long.parseLong("13")));
            this.getSelectedAsignacion().setNestadoid(BigDecimal.valueOf(Long.parseLong("2")));
            this.getSelectedAsignacion().setDfechaatencion(new Date());
            serviceasig.saveOrUpdate(this.getSelectedAsignacion());

            LoginMB loginMB = (LoginMB) JSFUtils.getSessionAttribute("loginMB");
            loginMB.refreshNotifications();

            pagina = "/index.xhtml";

        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
        return pagina;
    }

    public String publicarWiki() {
        String pagina = null;
        try {
            //this.setDescripcionHtml(JSFUtils.getRequestParameter("descHtml"));
            if (this.getChkDestacado()) {
                ConsultaService consultaService = (ConsultaService) ServiceFinder.findBean("ConsultaService");
                HashMap filter = new HashMap();
                filter.put("ntipoconocimientoid", Constante.WIKI);
                BigDecimal cant = consultaService.countDestacadosByTipoConocimiento(filter);
                if (cant.intValue() >= 10) {
                    this.setListaDestacados(consultaService.getDestacadosByTipoConocimiento(filter));
                    RequestContext.getCurrentInstance().execute("PF('destDialog').show();");
                    return "";
                }
            }
            LoginMB loginMB = (LoginMB) JSFUtils.getSessionAttribute("loginMB");
            User usuario = loginMB.getUser();
            ConocimientoService conocimientoService = (ConocimientoService) ServiceFinder.findBean("ConocimientoService");
            this.setDescripcionPlain(Jsoup.parse(this.getDescripcionHtml()).text());
            this.getSelectedWiki().setNcategoriaid(this.getSelectedCategoria().getNcategoriaid());
            this.getSelectedWiki().setVtitulo(StringUtils.upperCase(this.getSelectedWiki().getVtitulo()));
            if (this.getDescripcionPlain().length() < 400) {
                this.getSelectedWiki().setVdescripcion(StringUtils.capitalize(this.getDescripcionPlain()));
            } else {
                this.getSelectedWiki().setVdescripcion(StringUtils.capitalize(this.getDescripcionPlain().substring(0, 400)));
            }
            this.getSelectedWiki().setNdestacado(this.getChkDestacado() ? BigDecimal.ONE : BigDecimal.ZERO);
            this.getSelectedWiki().setDfechamodificacion(new Date());
            this.getSelectedWiki().setVusuariomodificacion(usuario.getVlogin());
            this.getSelectedWiki().setNsituacionid(BigDecimal.valueOf((long) 6));
            this.getSelectedWiki().setDfechapublicacion(new Date());
            conocimientoService.saveOrUpdate(this.getSelectedWiki());

            GcmFileUtils.writeStringToFileServer(this.getSelectedWiki().getVruta(), "html.txt", this.getDescripcionHtml());
            GcmFileUtils.writeStringToFileServer(this.getSelectedWiki().getVruta(), "plain.txt", this.getDescripcionPlain());

            HistorialService historialService = (HistorialService) ServiceFinder.findBean("HistorialService");
            Historial lastHistorial = historialService.getLastHistorialByConocimiento(this.getSelectedWiki().getNconocimientoid());
            int lastversion;
            if (lastHistorial != null) {
                lastversion = lastHistorial.getNnumversion().intValue();
            } else {
                lastversion = 0;
            }
            String url = this.pathwk.concat(this.getSelectedWiki().getNconocimientoid().toString()).concat("/").concat(Integer.toString(lastversion + 1)).concat("/");

            ThistorialId thistorialId = new ThistorialId();
            thistorialId.setNconocimientoid(this.getSelectedWiki().getNconocimientoid());
            thistorialId.setNhistorialid(historialService.getNextPK());
            Historial historial = new Historial();
            historial.setId(thistorialId);
            historial.setNtipoconocimientoid(Constante.WIKI);
            historial.setNcategoriaid(this.getSelectedCategoria().getNcategoriaid());
            historial.setVtitulo(this.getSelectedWiki().getVtitulo());
            historial.setNactivo(BigDecimal.ONE);
            historial.setNsituacionid(this.getSelectedWiki().getNsituacionid());
            historial.setVruta(url);
            historial.setNnumversion(BigDecimal.valueOf(lastversion + 1));
            historial.setDfechacreacion(new Date());
            historial.setVusuariocreacion(usuario.getVlogin());
            historial.setVmsjsolicita(this.getSelectedWiki().getVmsjsolicita());
            historial.setVmsjrespuesta(this.getSelectedWiki().getVmsjrespuesta());
            historialService.saveOrUpdate(historial);

            GcmFileUtils.writeStringToFileServer(url, "html.txt", this.getDescripcionHtml());
            GcmFileUtils.writeStringToFileServer(url, "plain.txt", this.getDescripcionPlain());

            if (org.apache.commons.collections.CollectionUtils.isNotEmpty(this.getListaSeccion())) {
                String url0 = this.getSelectedWiki().getVruta().concat("s");
                String url1 = url.concat("s");
                SeccionService seccionService = (SeccionService) ServiceFinder.findBean("SeccionService");
                SeccionHistService seccionHistService = (SeccionHistService) ServiceFinder.findBean("SeccionHistService");
                for (Seccion seccion : this.getListaSeccion()) {
                    String ruta0 = url0.concat(seccion.getNorden().toString()).concat("/");
                    seccion.setVruta(ruta0);
                    if (seccion.getNseccionid() != null) {
                        seccion.setDfechamodificacion(new Date());
                        seccion.setVusuariomodificacion(usuario.getVlogin());
                    } else {
                        seccion.setNseccionid(seccionService.getNextPK());
                        seccion.setNconocimientoid(this.getSelectedWiki().getNconocimientoid());
                        seccion.setDfechacreacion(new Date());
                        seccion.setVusuariocreacion(usuario.getVlogin());
                    }
                    seccionService.saveOrUpdate(seccion);

                    seccion.setDetallePlain(Jsoup.parse(seccion.getDetalleHtml()).text());
                    GcmFileUtils.writeStringToFileServer(ruta0, "html.txt", seccion.getDetalleHtml());
                    GcmFileUtils.writeStringToFileServer(ruta0, "plain.txt", seccion.getDetallePlain());

                    String ruta1 = url1.concat(seccion.getNorden().toString()).concat("/");
                    TseccionHistId tseccionHistId = new TseccionHistId();
                    tseccionHistId.setNconocimientoid(thistorialId.getNconocimientoid());
                    tseccionHistId.setNhistorialid(thistorialId.getNhistorialid());
                    tseccionHistId.setNseccionhid(seccionHistService.getNextPK());
                    SeccionHist seccionHist = new SeccionHist();
                    seccionHist.setId(tseccionHistId);
                    seccionHist.setNorden(seccion.getNorden());
                    seccionHist.setVruta(ruta1);
                    seccionHist.setVtitulo(seccion.getVtitulo());
                    seccionHist.setVusuariocreacion(usuario.getVlogin());
                    seccionHist.setDfechacreacion(new Date());
                    seccionHistService.saveOrUpdate(seccionHist);

                    GcmFileUtils.writeStringToFileServer(ruta1, "html.txt", seccion.getDetalleHtml());
                    GcmFileUtils.writeStringToFileServer(ruta1, "plain.txt", seccion.getDetallePlain());
                }
            }

            this.setListaTargetVinculos(new ArrayList());
            this.getListaTargetVinculos().addAll(this.getListaTargetVinculosBL());
            this.getListaTargetVinculos().addAll(this.getListaTargetVinculosBP());
            this.getListaTargetVinculos().addAll(this.getListaTargetVinculosCT());
            this.getListaTargetVinculos().addAll(this.getListaTargetVinculosOM());
            this.getListaTargetVinculos().addAll(this.getListaTargetVinculosPR());
            this.getListaTargetVinculos().addAll(this.getListaTargetVinculosWK());

            if (org.apache.commons.collections.CollectionUtils.isNotEmpty(this.getListaTargetVinculos())) {
                VinculoService vinculoService = (VinculoService) ServiceFinder.findBean("VinculoService");
                VinculoHistService vinculoHistService = (VinculoHistService) ServiceFinder.findBean("VinculoHistService");
                vinculoService.deleteByConocimiento(this.getSelectedWiki().getNconocimientoid());
                for (Consulta consulta : this.getListaTargetVinculos()) {
                    Vinculo vinculo = new Vinculo();
                    vinculo.setNvinculoid(vinculoService.getNextPK());
                    vinculo.setNconocimientoid(this.getSelectedWiki().getNconocimientoid());
                    vinculo.setNconocimientovinc(consulta.getIdconocimiento());
                    vinculo.setNtipoconocimientovinc(consulta.getIdTipoConocimiento());
                    vinculo.setDfechacreacion(new Date());
                    vinculo.setVusuariocreacion(usuario.getVlogin());
                    vinculoService.saveOrUpdate(vinculo);

                    TvinculoHistId vinculoHistId = new TvinculoHistId();
                    vinculoHistId.setNvinculohid(vinculoHistService.getNextPK());
                    vinculoHistId.setNconocimientoid(thistorialId.getNconocimientoid());
                    vinculoHistId.setNhistorialid(thistorialId.getNhistorialid());
                    VinculoHist vinculoHist = new VinculoHist();
                    vinculoHist.setId(vinculoHistId);
                    vinculoHist.setNconocimientovinc(vinculo.getNconocimientovinc());
                    vinculoHist.setDfechacreacion(new Date());
                    vinculoHist.setVusuariocreacion(usuario.getVlogin());
                    vinculoHistService.saveOrUpdate(vinculoHist);
                }
            }

            AsignacionService serviceasig = (AsignacionService) ServiceFinder.findBean("AsignacionService");
            this.getSelectedAsignacion().setNestadoid(BigDecimal.valueOf(Long.parseLong("2")));
            this.getSelectedAsignacion().setDfechaatencion(new Date());
            this.getSelectedAsignacion().setNaccionid(BigDecimal.valueOf(Long.parseLong("8")));
            serviceasig.saveOrUpdate(this.getSelectedAsignacion());

            loginMB.refreshNotifications();
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "INFO.", "Se publicó el wiki.");
            FacesContext.getCurrentInstance().addMessage(null, message);
            FacesContext.getCurrentInstance().getExternalContext().redirect("/gescon/index.xhtml");
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
        return pagina;
    }

    public void publicarBpractica(ActionEvent event) {
        try {
            //this.setDescripcionHtml(JSFUtils.getRequestParameter("descHtml"));
            if (this.getChkDestacado()) {
                ConsultaService consultaService = (ConsultaService) ServiceFinder.findBean("ConsultaService");
                HashMap filter = new HashMap();
                filter.put("ntipoconocimientoid", Constante.BUENAPRACTICA);
                BigDecimal cant = consultaService.countDestacadosByTipoConocimiento(filter);
                if (cant.intValue() >= 10) {
                    this.setListaDestacados(consultaService.getDestacadosByTipoConocimiento(filter));
                    RequestContext.getCurrentInstance().execute("PF('destDialog').show();");
                    return;
                }
            }
            this.setDescripcionPlain(Jsoup.parse(this.getDescripcionHtml()).text());
            LoginMB loginMB = (LoginMB) JSFUtils.getSessionAttribute("loginMB");
            User usuario = loginMB.getUser();
            ConocimientoService conocimientoService = (ConocimientoService) ServiceFinder.findBean("ConocimientoService");
            this.getSelectedBpractica().setNcategoriaid(this.getSelectedCategoria().getNcategoriaid());
            this.getSelectedBpractica().setVtitulo(StringUtils.upperCase(this.getSelectedBpractica().getVtitulo().trim()));
            if (this.getDescripcionPlain().length() < 400) {
                this.getSelectedBpractica().setVdescripcion(StringUtils.capitalize(this.getDescripcionPlain()));
            } else {
                this.getSelectedBpractica().setVdescripcion(StringUtils.capitalize(this.getDescripcionPlain().substring(0, 400)));
            }
            this.getSelectedBpractica().setNdestacado(this.getChkDestacado() ? BigDecimal.ONE : BigDecimal.ZERO);
            this.getSelectedBpractica().setDfechamodificacion(new Date());
            this.getSelectedBpractica().setVusuariomodificacion(usuario.getVlogin());
            this.getSelectedBpractica().setNsituacionid(BigDecimal.valueOf((long) 6));
            this.getSelectedBpractica().setDfechapublicacion(new Date());
            conocimientoService.saveOrUpdate(this.getSelectedBpractica());

            GcmFileUtils.writeStringToFileServer(this.getSelectedBpractica().getVruta(), "html.txt", this.getDescripcionHtml());
            GcmFileUtils.writeStringToFileServer(this.getSelectedBpractica().getVruta(), "plain.txt", this.getDescripcionPlain());

            HistorialService historialService = (HistorialService) ServiceFinder.findBean("HistorialService");
            Historial lastHistorial = historialService.getLastHistorialByConocimiento(this.getSelectedBpractica().getNconocimientoid());
            int lastversion;
            if (lastHistorial != null) {
                lastversion = lastHistorial.getNnumversion().intValue();
            } else {
                lastversion = 0;
            }
            String url = this.pathbp.concat(this.getSelectedBpractica().getNconocimientoid().toString()).concat("/").concat(Integer.toString(lastversion + 1)).concat("/");

            ThistorialId thistorialId = new ThistorialId();
            thistorialId.setNconocimientoid(this.getSelectedBpractica().getNconocimientoid());
            thistorialId.setNhistorialid(historialService.getNextPK());
            Historial historial = new Historial();
            historial.setId(thistorialId);
            historial.setNtipoconocimientoid(Constante.BUENAPRACTICA);
            historial.setNcategoriaid(this.getSelectedCategoria().getNcategoriaid());
            historial.setVtitulo(this.getSelectedBpractica().getVtitulo());
            historial.setNactivo(BigDecimal.ONE);
            historial.setNsituacionid(this.getSelectedBpractica().getNsituacionid());
            historial.setVruta(url);
            historial.setNnumversion(BigDecimal.valueOf(lastversion + 1));
            historial.setDfechacreacion(new Date());
            historial.setVusuariocreacion(usuario.getVlogin());
            historial.setVmsjsolicita(this.getSelectedBpractica().getVmsjsolicita());
            historial.setVmsjrespuesta(this.getSelectedBpractica().getVmsjrespuesta());
            historialService.saveOrUpdate(historial);

            GcmFileUtils.writeStringToFileServer(url, "html.txt", this.getDescripcionHtml());
            GcmFileUtils.writeStringToFileServer(url, "plain.txt", this.getDescripcionPlain());

            if (org.apache.commons.collections.CollectionUtils.isNotEmpty(this.getListaSeccion())) {
                String url0 = this.getSelectedBpractica().getVruta().concat("s");
                String url1 = url.concat("s");
                SeccionService seccionService = (SeccionService) ServiceFinder.findBean("SeccionService");
                SeccionHistService seccionHistService = (SeccionHistService) ServiceFinder.findBean("SeccionHistService");
                for (Seccion seccion : this.getListaSeccion()) {
                    String ruta0 = url0.concat(seccion.getNorden().toString()).concat("/");
                    seccion.setVruta(ruta0);
                    if (seccion.getNseccionid() != null) {
                        seccion.setDfechamodificacion(new Date());
                        seccion.setVusuariomodificacion(usuario.getVlogin());
                    } else {
                        seccion.setNseccionid(seccionService.getNextPK());
                        seccion.setNconocimientoid(this.getSelectedBpractica().getNconocimientoid());
                        seccion.setDfechacreacion(new Date());
                        seccion.setVusuariocreacion(usuario.getVlogin());
                    }
                    seccionService.saveOrUpdate(seccion);

                    seccion.setDetallePlain(Jsoup.parse(seccion.getDetalleHtml()).text());
                    GcmFileUtils.writeStringToFileServer(ruta0, "html.txt", seccion.getDetalleHtml());
                    GcmFileUtils.writeStringToFileServer(ruta0, "plain.txt", seccion.getDetallePlain());

                    String ruta1 = url1.concat(seccion.getNorden().toString()).concat("/");
                    TseccionHistId tseccionHistId = new TseccionHistId();
                    tseccionHistId.setNconocimientoid(thistorialId.getNconocimientoid());
                    tseccionHistId.setNhistorialid(thistorialId.getNhistorialid());
                    tseccionHistId.setNseccionhid(seccionHistService.getNextPK());
                    SeccionHist seccionHist = new SeccionHist();
                    seccionHist.setId(tseccionHistId);
                    seccionHist.setNorden(seccion.getNorden());
                    seccionHist.setVruta(ruta1);
                    seccionHist.setVtitulo(seccion.getVtitulo());
                    seccionHist.setVusuariocreacion(usuario.getVlogin());
                    seccionHist.setDfechacreacion(new Date());
                    seccionHistService.saveOrUpdate(seccionHist);

                    GcmFileUtils.writeStringToFileServer(ruta1, "html.txt", seccion.getDetalleHtml());
                    GcmFileUtils.writeStringToFileServer(ruta1, "plain.txt", seccion.getDetallePlain());
                }
            }

            this.setListaTargetVinculos(new ArrayList());
            this.getListaTargetVinculos().addAll(this.getListaTargetVinculosBL());
            this.getListaTargetVinculos().addAll(this.getListaTargetVinculosBP());
            this.getListaTargetVinculos().addAll(this.getListaTargetVinculosCT());
            this.getListaTargetVinculos().addAll(this.getListaTargetVinculosOM());
            this.getListaTargetVinculos().addAll(this.getListaTargetVinculosPR());
            this.getListaTargetVinculos().addAll(this.getListaTargetVinculosWK());

            if (org.apache.commons.collections.CollectionUtils.isNotEmpty(this.getListaTargetVinculos())) {
                VinculoService vinculoService = (VinculoService) ServiceFinder.findBean("VinculoService");
                VinculoHistService vinculoHistService = (VinculoHistService) ServiceFinder.findBean("VinculoHistService");
                vinculoService.deleteByConocimiento(this.getSelectedBpractica().getNconocimientoid());
                for (Consulta consulta : this.getListaTargetVinculos()) {
                    Vinculo vinculo = new Vinculo();
                    vinculo.setNvinculoid(vinculoService.getNextPK());
                    vinculo.setNconocimientoid(this.getSelectedBpractica().getNconocimientoid());
                    vinculo.setNconocimientovinc(consulta.getIdconocimiento());
                    vinculo.setNtipoconocimientovinc(consulta.getIdTipoConocimiento());
                    vinculo.setDfechacreacion(new Date());
                    vinculo.setVusuariocreacion(usuario.getVlogin());
                    vinculoService.saveOrUpdate(vinculo);

                    TvinculoHistId vinculoHistId = new TvinculoHistId();
                    vinculoHistId.setNvinculohid(vinculoHistService.getNextPK());
                    vinculoHistId.setNconocimientoid(thistorialId.getNconocimientoid());
                    vinculoHistId.setNhistorialid(thistorialId.getNhistorialid());
                    VinculoHist vinculoHist = new VinculoHist();
                    vinculoHist.setId(vinculoHistId);
                    vinculoHist.setNconocimientovinc(vinculo.getNconocimientovinc());
                    vinculoHist.setDfechacreacion(new Date());
                    vinculoHist.setVusuariocreacion(usuario.getVlogin());
                    vinculoHistService.saveOrUpdate(vinculoHist);
                }
            }

            AsignacionService serviceasig = (AsignacionService) ServiceFinder.findBean("AsignacionService");
            this.getSelectedAsignacion().setNestadoid(BigDecimal.valueOf(Long.parseLong("2")));
            this.getSelectedAsignacion().setDfechaatencion(new Date());
            this.getSelectedAsignacion().setNaccionid(BigDecimal.valueOf(Long.parseLong("8")));
            serviceasig.saveOrUpdate(this.getSelectedAsignacion());

            loginMB.refreshNotifications();
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "INFO.", "Se publicó la buena práctica.");
            FacesContext.getCurrentInstance().addMessage(null, message);
            FacesContext.getCurrentInstance().getExternalContext().redirect("/gescon/index.xhtml");
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
    }

    public void publicarOmejora(ActionEvent event) {
        try {
            //this.setContenidoHtml(JSFUtils.getRequestParameter("descHtml"));
            if (this.getChkDestacado()) {
                ConsultaService consultaService = (ConsultaService) ServiceFinder.findBean("ConsultaService");
                HashMap filter = new HashMap();
                filter.put("ntipoconocimientoid", Constante.OPORTUNIDADMEJORA);
                BigDecimal cant = consultaService.countDestacadosByTipoConocimiento(filter);
                if (cant.intValue() >= 10) {
                    this.setListaDestacados(consultaService.getDestacadosByTipoConocimiento(filter));
                    RequestContext.getCurrentInstance().execute("PF('destDialog').show();");
                    return;
                }
            }
            this.setContenidoPlain(Jsoup.parse(this.getContenidoHtml()).text());
            LoginMB loginMB = (LoginMB) JSFUtils.getSessionAttribute("loginMB");
            User usuario = loginMB.getUser();
            ConocimientoService conocimientoService = (ConocimientoService) ServiceFinder.findBean("ConocimientoService");
            this.getSelectedOmejora().setNcategoriaid(this.getSelectedCategoria().getNcategoriaid());
            this.getSelectedOmejora().setVtitulo(StringUtils.upperCase(this.getSelectedOmejora().getVtitulo()));
            this.getSelectedOmejora().setVdescripcion(StringUtils.upperCase(this.getSelectedOmejora().getVdescripcion()));
            if (this.getContenidoPlain().length() < 400) {
                this.getSelectedOmejora().setVcontenido(StringUtils.capitalize(this.getContenidoPlain()));
            } else {
                this.getSelectedOmejora().setVcontenido(StringUtils.capitalize(this.getContenidoPlain().substring(0, 400)));
            }
            this.getSelectedOmejora().setNdestacado(this.getChkDestacado() ? BigDecimal.ONE : BigDecimal.ZERO);
            this.getSelectedOmejora().setDfechamodificacion(new Date());
            this.getSelectedOmejora().setVusuariomodificacion(usuario.getVlogin());
            this.getSelectedOmejora().setNsituacionid(BigDecimal.valueOf(Long.parseLong(Constante.SITUACION_VERIFICADO)));
            this.getSelectedOmejora().setDfechapublicacion(new Date());
            conocimientoService.saveOrUpdate(this.getSelectedOmejora());

            this.setContenidoPlain(Jsoup.parse(this.getContenidoHtml()).text());
            GcmFileUtils.writeStringToFileServer(this.getSelectedOmejora().getVruta(), "html.txt", this.getContenidoHtml());
            GcmFileUtils.writeStringToFileServer(this.getSelectedOmejora().getVruta(), "plain.txt", this.getContenidoPlain());

            HistorialService historialService = (HistorialService) ServiceFinder.findBean("HistorialService");
            Historial lastHistorial = historialService.getLastHistorialByConocimiento(this.getSelectedOmejora().getNconocimientoid());
            int lastversion;
            if (lastHistorial != null) {
                lastversion = lastHistorial.getNnumversion().intValue();
            } else {
                lastversion = 0;
            }
            String url = this.pathom.concat(this.getSelectedOmejora().getNconocimientoid().toString()).concat("/").concat(Integer.toString(lastversion + 1)).concat("/");

            ThistorialId thistorialId = new ThistorialId();
            thistorialId.setNconocimientoid(this.getSelectedOmejora().getNconocimientoid());
            thistorialId.setNhistorialid(historialService.getNextPK());
            Historial historial = new Historial();
            historial.setId(thistorialId);
            historial.setNtipoconocimientoid(Constante.OPORTUNIDADMEJORA);
            historial.setNcategoriaid(this.getSelectedCategoria().getNcategoriaid());
            historial.setVtitulo(this.getSelectedOmejora().getVtitulo());
            historial.setVdescripcion(this.getSelectedOmejora().getVdescripcion());
            historial.setNactivo(BigDecimal.ONE);
            historial.setNsituacionid(this.getSelectedOmejora().getNsituacionid());
            historial.setVruta(url);
            historial.setNnumversion(BigDecimal.valueOf(lastversion + 1));
            historial.setDfechacreacion(new Date());
            historial.setVusuariocreacion(usuario.getVlogin());
            historial.setVmsjsolicita(this.getSelectedOmejora().getVmsjsolicita());
            historial.setVmsjrespuesta(this.getSelectedOmejora().getVmsjrespuesta());
            historialService.saveOrUpdate(historial);

            GcmFileUtils.writeStringToFileServer(url, "html.txt", this.getContenidoHtml());
            GcmFileUtils.writeStringToFileServer(url, "plain.txt", this.getContenidoPlain());

            if (org.apache.commons.collections.CollectionUtils.isNotEmpty(this.getListaSeccion())) {
                String url0 = this.getSelectedOmejora().getVruta().concat("s");
                String url1 = url.concat("s");
                SeccionService seccionService = (SeccionService) ServiceFinder.findBean("SeccionService");
                SeccionHistService seccionHistService = (SeccionHistService) ServiceFinder.findBean("SeccionHistService");
                for (Seccion seccion : this.getListaSeccion()) {
                    String ruta0 = url0.concat(seccion.getNorden().toString()).concat("/");
                    seccion.setVruta(ruta0);
                    if (seccion.getNseccionid() != null) {
                        seccion.setDfechamodificacion(new Date());
                        seccion.setVusuariomodificacion(usuario.getVlogin());
                    } else {
                        seccion.setNseccionid(seccionService.getNextPK());
                        seccion.setNconocimientoid(this.getSelectedOmejora().getNconocimientoid());
                        seccion.setDfechacreacion(new Date());
                        seccion.setVusuariocreacion(usuario.getVlogin());
                    }
                    seccionService.saveOrUpdate(seccion);

                    seccion.setDetallePlain(Jsoup.parse(seccion.getDetalleHtml()).text());
                    GcmFileUtils.writeStringToFileServer(ruta0, "html.txt", seccion.getDetalleHtml());
                    GcmFileUtils.writeStringToFileServer(ruta0, "plain.txt", seccion.getDetallePlain());

                    String ruta1 = url1.concat(seccion.getNorden().toString()).concat("/");
                    TseccionHistId tseccionHistId = new TseccionHistId();
                    tseccionHistId.setNconocimientoid(thistorialId.getNconocimientoid());
                    tseccionHistId.setNhistorialid(thistorialId.getNhistorialid());
                    tseccionHistId.setNseccionhid(seccionHistService.getNextPK());
                    SeccionHist seccionHist = new SeccionHist();
                    seccionHist.setId(tseccionHistId);
                    seccionHist.setNorden(seccion.getNorden());
                    seccionHist.setVruta(ruta1);
                    seccionHist.setVtitulo(seccion.getVtitulo());
                    seccionHist.setVusuariocreacion(usuario.getVlogin());
                    seccionHist.setDfechacreacion(new Date());
                    seccionHistService.saveOrUpdate(seccionHist);

                    GcmFileUtils.writeStringToFileServer(ruta1, "html.txt", seccion.getDetalleHtml());
                    GcmFileUtils.writeStringToFileServer(ruta1, "plain.txt", seccion.getDetallePlain());
                }
            }

            this.setListaTargetVinculos(new ArrayList());
            this.getListaTargetVinculos().addAll(this.getListaTargetVinculosBL());
            this.getListaTargetVinculos().addAll(this.getListaTargetVinculosBP());
            this.getListaTargetVinculos().addAll(this.getListaTargetVinculosCT());
            this.getListaTargetVinculos().addAll(this.getListaTargetVinculosOM());
            this.getListaTargetVinculos().addAll(this.getListaTargetVinculosPR());
            this.getListaTargetVinculos().addAll(this.getListaTargetVinculosWK());

            if (org.apache.commons.collections.CollectionUtils.isNotEmpty(this.getListaTargetVinculos())) {
                VinculoService vinculoService = (VinculoService) ServiceFinder.findBean("VinculoService");
                VinculoHistService vinculoHistService = (VinculoHistService) ServiceFinder.findBean("VinculoHistService");
                vinculoService.deleteByConocimiento(this.getSelectedOmejora().getNconocimientoid());
                for (Consulta consulta : this.getListaTargetVinculos()) {
                    Vinculo vinculo = new Vinculo();
                    vinculo.setNvinculoid(vinculoService.getNextPK());
                    vinculo.setNconocimientoid(this.getSelectedOmejora().getNconocimientoid());
                    vinculo.setNconocimientovinc(consulta.getIdconocimiento());
                    vinculo.setNtipoconocimientovinc(consulta.getIdTipoConocimiento());
                    vinculo.setDfechacreacion(new Date());
                    vinculo.setVusuariocreacion(usuario.getVlogin());
                    vinculoService.saveOrUpdate(vinculo);

                    TvinculoHistId vinculoHistId = new TvinculoHistId();
                    vinculoHistId.setNvinculohid(vinculoHistService.getNextPK());
                    vinculoHistId.setNconocimientoid(thistorialId.getNconocimientoid());
                    vinculoHistId.setNhistorialid(thistorialId.getNhistorialid());
                    VinculoHist vinculoHist = new VinculoHist();
                    vinculoHist.setId(vinculoHistId);
                    vinculoHist.setNconocimientovinc(vinculo.getNconocimientovinc());
                    vinculoHist.setDfechacreacion(new Date());
                    vinculoHist.setVusuariocreacion(usuario.getVlogin());
                    vinculoHistService.saveOrUpdate(vinculoHist);
                }
            }

            AsignacionService serviceasig = (AsignacionService) ServiceFinder.findBean("AsignacionService");
            this.getSelectedAsignacion().setNestadoid(BigDecimal.valueOf(Long.parseLong("2")));
            this.getSelectedAsignacion().setDfechaatencion(new Date());
            this.getSelectedAsignacion().setNaccionid(BigDecimal.valueOf(Long.parseLong("8")));
            serviceasig.saveOrUpdate(this.getSelectedAsignacion());
            
            Asignacion asignacion = new Asignacion();
            asignacion.setNasignacionid(serviceasig.getNextPK());
            asignacion.setNtipoconocimientoid(Constante.OPORTUNIDADMEJORA);
            asignacion.setNconocimientoid(this.getSelectedOmejora().getNconocimientoid());
            asignacion.setNestadoid(BigDecimal.valueOf(Long.parseLong("1")));
            CategoriaService categoriaService = (CategoriaService) ServiceFinder.findBean("CategoriaService");
            asignacion.setNusuarioid(categoriaService.getCategoriaById(this.getSelectedOmejora().getNcategoriaid()).getNespecialista());
            asignacion.setDfechaasignacion(new Date());
            asignacion.setDfechacreacion(new Date());
            serviceasig.saveOrUpdate(asignacion);

            loginMB.refreshNotifications();
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "INFO.", "Se publicó la oportunidad de mejora.");
            FacesContext.getCurrentInstance().addMessage(null, message);
            FacesContext.getCurrentInstance().getExternalContext().redirect("/gescon/index.xhtml");

        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
    }
    
    public void analizarOmejora(ActionEvent event) {
        try {
            if(this.getAnalisis() == null) {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR.", "Seleccione el análisis correspondiente a la oportunidad de mejora.");
                FacesContext.getCurrentInstance().addMessage(null, message);
                return;
            } else if(this.getAnalisis().equals(BigDecimal.ONE)) {
                if(this.getDias() < 1){
                    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR.", "Ingrese los días de implementación.");
                    FacesContext.getCurrentInstance().addMessage(null, message);
                    return;
                }
            } else {
                if(StringUtils.isBlank(this.getMotivo())){
                    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR.", "Ingrese el motivo del rechazo.");
                    FacesContext.getCurrentInstance().addMessage(null, message);
                    return;
                }
            }
            LoginMB loginMB = (LoginMB) JSFUtils.getSessionAttribute("loginMB");
            User user = loginMB.getUser();
            this.getSelectedOmejora().setNanalisis(this.getAnalisis());
            this.getSelectedOmejora().setNdias(BigDecimal.valueOf(this.getDias()));
            this.getSelectedOmejora().setVobservacion(this.getMotivo());
            this.getSelectedOmejora().setDfechamodificacion(new Date());
            this.getSelectedOmejora().setVusuariomodificacion(user.getVlogin());
            this.getSelectedOmejora().setNsituacionid(BigDecimal.valueOf(Long.parseLong(Constante.SITUACION_APROBADO)));
            ConocimientoService conocimientoService = (ConocimientoService) ServiceFinder.findBean("ConocimientoService");
            conocimientoService.saveOrUpdate(this.getSelectedOmejora());
            
            String mensaje = "";
            AsignacionService serviceasig = (AsignacionService) ServiceFinder.findBean("AsignacionService");
            this.getSelectedAsignacion().setNestadoid(BigDecimal.valueOf(Long.parseLong("2")));
            this.getSelectedAsignacion().setDfechaatencion(new Date());
            if(this.getAnalisis().equals(BigDecimal.ONE)) {
                this.getSelectedAsignacion().setNaccionid(BigDecimal.valueOf(Long.parseLong("14")));
                mensaje = "Se aprobó la iportunidad de mejora.";
            } else {
                this.getSelectedAsignacion().setNaccionid(BigDecimal.valueOf(Long.parseLong("13")));
                mensaje = "Se rechazó la iportunidad de mejora.";
            }
            
            serviceasig.saveOrUpdate(this.getSelectedAsignacion());

            Asignacion asignacion = new Asignacion();
            asignacion.setNasignacionid(serviceasig.getNextPK());
            asignacion.setNtipoconocimientoid(Constante.OPORTUNIDADMEJORA);
            asignacion.setNconocimientoid(this.getSelectedOmejora().getNconocimientoid());
            asignacion.setNestadoid(BigDecimal.valueOf(Long.parseLong("1")));
            CategoriaService categoriaService = (CategoriaService) ServiceFinder.findBean("CategoriaService");
            asignacion.setNusuarioid(categoriaService.getCategoriaById(this.getSelectedOmejora().getNcategoriaid()).getNespecialista());
            asignacion.setDfechaasignacion(new Date());
            asignacion.setDfechacreacion(new Date());
            serviceasig.saveOrUpdate(asignacion);

            loginMB.refreshNotifications();

            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "INFO.", mensaje);
            FacesContext.getCurrentInstance().addMessage(null, message);
            FacesContext.getCurrentInstance().getExternalContext().redirect("/gescon/index.xhtml");
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
    }
    
    public void implementarOmejora(ActionEvent event) {
        try {
            //this.setContenidoHtml(JSFUtils.getRequestParameter("descHtml"));
            if (this.getChkDestacado()) {
                ConsultaService consultaService = (ConsultaService) ServiceFinder.findBean("ConsultaService");
                HashMap filter = new HashMap();
                filter.put("ntipoconocimientoid", Constante.OPORTUNIDADMEJORA);
                BigDecimal cant = consultaService.countDestacadosByTipoConocimiento(filter);
                if (cant.intValue() >= 10) {
                    this.setListaDestacados(consultaService.getDestacadosByTipoConocimiento(filter));
                    RequestContext.getCurrentInstance().execute("PF('destDialog').show();");
                    return;
                }
            }
            this.setContenidoPlain(Jsoup.parse(this.getContenidoHtml()).text());
            LoginMB loginMB = (LoginMB) JSFUtils.getSessionAttribute("loginMB");
            User usuario = loginMB.getUser();
            ImplementacionService implementacionService = (ImplementacionService) ServiceFinder.findBean("ImplementacionService");
            Implementacion implementacion = new Implementacion();
            implementacion.setNimplementacionid(implementacionService.getNextPK());
            implementacion.setNconocimientoid(this.getSelectedOmejora().getNconocimientoid());
            implementacion.setNtipoconocimientoid(this.getSelectedOmejora().getNtipoconocimientoid());
            implementacion.setNcategoriaid(this.getSelectedOmejora().getNcategoriaid());
            implementacion.setVtitulo(StringUtils.upperCase(this.getSelectedOmejora().getVtitulo()));
            implementacion.setVdescripcion(StringUtils.upperCase(this.getSelectedOmejora().getVdescripcion()));
            if (this.getContenidoPlain().length() < 400) {
                implementacion.setVcontenido(StringUtils.capitalize(this.getContenidoPlain()));
            } else {
                implementacion.setVcontenido(StringUtils.capitalize(this.getContenidoPlain().substring(0, 400)));
            }
            implementacion.setNdestacado(this.getChkDestacado() ? BigDecimal.ONE : BigDecimal.ZERO);
            
            implementacion.setDfechacreacion(new Date());
            implementacion.setVusuariocreacion(usuario.getVlogin());
            implementacion.setNsituacionid(BigDecimal.valueOf((long) 6));
            implementacionService.saveOrUpdate(implementacion);
            
            String np = this.pathom.concat(this.getSelectedOmejora().getNconocimientoid().toString()).concat("/impl/");
            this.setContenidoPlain(Jsoup.parse(this.getContenidoHtml()).text());
            GcmFileUtils.writeStringToFileServer(np, "html.txt", this.getContenidoHtml());
            GcmFileUtils.writeStringToFileServer(np, "plain.txt", this.getContenidoPlain());
            
            this.setListaTargetVinculos(new ArrayList());
            this.getListaTargetVinculos().addAll(this.getListaTargetVinculosBL());
            this.getListaTargetVinculos().addAll(this.getListaTargetVinculosBP());
            this.getListaTargetVinculos().addAll(this.getListaTargetVinculosCT());
            this.getListaTargetVinculos().addAll(this.getListaTargetVinculosOM());
            this.getListaTargetVinculos().addAll(this.getListaTargetVinculosPR());
            this.getListaTargetVinculos().addAll(this.getListaTargetVinculosWK());

            if (org.apache.commons.collections.CollectionUtils.isNotEmpty(this.getListaTargetVinculos())) {
                VinculoImplementacionService vinculoService = (VinculoImplementacionService) ServiceFinder.findBean("VinculoImplementacionService");
                vinculoService.deleteByConocimiento(implementacion.getNimplementacionid());
                for (Consulta consulta : this.getListaTargetVinculos()) {
                    VinculoImplementacion vinculo = new VinculoImplementacion();
                    vinculo.setNimplvinculoid(vinculoService.getNextPK());
                    vinculo.setNimplementacionid(implementacion.getNconocimientoid());
                    vinculo.setNconocimientovinc(consulta.getIdconocimiento());
                    vinculo.setNtipoconocimientovinc(consulta.getIdTipoConocimiento());
                    vinculo.setDfechacreacion(new Date());
                    vinculo.setVusuariocreacion(usuario.getVlogin());
                    vinculoService.saveOrUpdate(vinculo);
                }
            }

            AsignacionService serviceasig = (AsignacionService) ServiceFinder.findBean("AsignacionService");
            this.getSelectedAsignacion().setNestadoid(BigDecimal.valueOf(Long.parseLong("2")));
            this.getSelectedAsignacion().setDfechaatencion(new Date());
            this.getSelectedAsignacion().setNaccionid(BigDecimal.valueOf(Long.parseLong("15")));
            serviceasig.saveOrUpdate(this.getSelectedAsignacion());
            
            Asignacion asignacion = new Asignacion();
            asignacion.setNasignacionid(serviceasig.getNextPK());
            asignacion.setNtipoconocimientoid(Constante.OPORTUNIDADMEJORA);
            asignacion.setNconocimientoid(this.getSelectedOmejora().getNconocimientoid());
            asignacion.setNestadoid(BigDecimal.valueOf(Long.parseLong("1")));
            CategoriaService categoriaService = (CategoriaService) ServiceFinder.findBean("CategoriaService");
            asignacion.setNusuarioid(categoriaService.getCategoriaById(this.getSelectedOmejora().getNcategoriaid()).getNmoderador());
            asignacion.setDfechaasignacion(new Date());
            asignacion.setDfechacreacion(new Date());
            serviceasig.saveOrUpdate(asignacion);

            loginMB.refreshNotifications();
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "INFO.", "Se implementó la oportunidad de mejora.");
            FacesContext.getCurrentInstance().addMessage(null, message);
            FacesContext.getCurrentInstance().getExternalContext().redirect("/gescon/index.xhtml");

        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
    }
    
    public void saveResumen(ActionEvent event) {
        try {
            if(StringUtils.isNotBlank(this.getContenidoPlain())) {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR.", "Seleccione el análisis correspondiente a la oportunidad de mejora.");
                FacesContext.getCurrentInstance().addMessage(null, message);
                return;
            }
            
            LoginMB loginMB = (LoginMB) JSFUtils.getSessionAttribute("loginMB");
            User user = loginMB.getUser();
            ImplementacionService implementacionService = (ImplementacionService) ServiceFinder.findBean("ImplementacionService");
            Implementacion implementacion = implementacionService.getImplementacionByConocimiento(this.getSelectedOmejora().getNconocimientoid());
            implementacion.setVresumen(this.getContenidoPlain());
            implementacion.setDfechamodificacion(new Date());
            implementacion.setVusuariomodificacion(user.getVlogin());
            
            implementacionService.saveOrUpdate(implementacion);
            
            ConocimientoService conocimientoService = (ConocimientoService) ServiceFinder.findBean("ConocimientoService");
            this.getSelectedOmejora().setNsituacionid(BigDecimal.valueOf((long) 6));
            conocimientoService.saveOrUpdate(this.getSelectedOmejora());
            
            AsignacionService serviceasig = (AsignacionService) ServiceFinder.findBean("AsignacionService");
            this.getSelectedAsignacion().setNestadoid(BigDecimal.valueOf(Long.parseLong("2")));
            this.getSelectedAsignacion().setDfechaatencion(new Date());
            this.getSelectedAsignacion().setNaccionid(BigDecimal.valueOf(Long.parseLong("8")));
            serviceasig.saveOrUpdate(this.getSelectedAsignacion());
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
    }

    public void saveBpracticaEdit(ActionEvent event) {
        String pagina = null;
        try {
            //this.setDescripcionHtml(JSFUtils.getRequestParameter("descHtml"));
            if (this.getChkDestacado()) {
                ConsultaService consultaService = (ConsultaService) ServiceFinder.findBean("ConsultaService");
                HashMap filter = new HashMap();
                filter.put("ntipoconocimientoid", Constante.BUENAPRACTICA);
                BigDecimal cant = consultaService.countDestacadosByTipoConocimiento(filter);
                if (cant.intValue() >= 10) {
                    this.setListaDestacados(consultaService.getDestacadosByTipoConocimiento(filter));
                    RequestContext.getCurrentInstance().execute("PF('destDialog').show();");
                    return;
                }
            }
            this.setDescripcionPlain(Jsoup.parse(this.getDescripcionHtml()).text());
            LoginMB loginMB = (LoginMB) JSFUtils.getSessionAttribute("loginMB");
            User usuario = loginMB.getUser();
            ConocimientoService conocimientoService = (ConocimientoService) ServiceFinder.findBean("ConocimientoService");

            if (this.getSelectedCategoria() == null) {
                this.getSelectedBpractica().setNcategoriaid(this.getSelectedBpractica().getNcategoriaid());
                cat_nueva = this.getSelectedBpractica().getNcategoriaid();
            } else {
                this.getSelectedBpractica().setNcategoriaid(this.getSelectedBpractica().getNcategoriaid());
                cat_nueva = this.getSelectedBpractica().getNcategoriaid();
            }

            this.getSelectedBpractica().setVtitulo(StringUtils.upperCase(this.getSelectedBpractica().getVtitulo().trim()));
            if (this.getDescripcionPlain().length() < 400) {
                this.getSelectedBpractica().setVdescripcion(StringUtils.capitalize(this.getDescripcionPlain()));
            } else {
                this.getSelectedBpractica().setVdescripcion(StringUtils.capitalize(this.getDescripcionPlain().substring(0, 400)));
            }
            this.getSelectedBpractica().setNdestacado(this.getChkDestacado() ? BigDecimal.ONE : BigDecimal.ZERO);
            this.getSelectedBpractica().setDfechamodificacion(new Date());
            this.getSelectedBpractica().setVusuariomodificacion(usuario.getVlogin());
            conocimientoService.saveOrUpdate(this.getSelectedBpractica());

            GcmFileUtils.writeStringToFileServer(this.getSelectedBpractica().getVruta(), "html.txt", this.getDescripcionHtml());
            GcmFileUtils.writeStringToFileServer(this.getSelectedBpractica().getVruta(), "plain.txt", this.getDescripcionPlain());

            HistorialService historialService = (HistorialService) ServiceFinder.findBean("HistorialService");
            Historial lastHistorial = historialService.getLastHistorialByConocimiento(this.getSelectedBpractica().getNconocimientoid());
            int lastversion;
            if (lastHistorial != null) {
                lastversion = lastHistorial.getNnumversion().intValue();
            } else {
                lastversion = 0;
            }
            String url = this.pathbp.concat(this.getSelectedBpractica().getNconocimientoid().toString()).concat("/").concat(Integer.toString(lastversion + 1)).concat("/");

            ThistorialId thistorialId = new ThistorialId();
            thistorialId.setNconocimientoid(this.getSelectedBpractica().getNconocimientoid());
            thistorialId.setNhistorialid(historialService.getNextPK());
            Historial historial = new Historial();
            historial.setId(thistorialId);
            historial.setNtipoconocimientoid(Constante.BUENAPRACTICA);
            historial.setNcategoriaid(this.getSelectedCategoria().getNcategoriaid());
            historial.setVtitulo(this.getSelectedBpractica().getVtitulo());
            historial.setNactivo(BigDecimal.ONE);
            historial.setNsituacionid(this.getSelectedBpractica().getNsituacionid());
            historial.setVruta(url);
            historial.setNnumversion(BigDecimal.valueOf(lastversion + 1));
            historial.setDfechacreacion(new Date());
            historial.setVusuariocreacion(usuario.getVlogin());
            historial.setVmsjsolicita(this.getSelectedBpractica().getVmsjsolicita());
            historial.setVmsjrespuesta(this.getSelectedBpractica().getVmsjrespuesta());
            historialService.saveOrUpdate(historial);

            GcmFileUtils.writeStringToFileServer(url, "html.txt", this.getDescripcionHtml());
            GcmFileUtils.writeStringToFileServer(url, "plain.txt", this.getDescripcionPlain());

            if (org.apache.commons.collections.CollectionUtils.isNotEmpty(this.getListaSeccion())) {
                String url0 = this.getSelectedBpractica().getVruta().concat("s");
                String url1 = url.concat("s");
                SeccionService seccionService = (SeccionService) ServiceFinder.findBean("SeccionService");
                SeccionHistService seccionHistService = (SeccionHistService) ServiceFinder.findBean("SeccionHistService");
                for (Seccion seccion : this.getListaSeccion()) {
                    String ruta0 = url0.concat(seccion.getNorden().toString()).concat("/");
                    seccion.setVruta(ruta0);
                    if (seccion.getNseccionid() != null) {
                        seccion.setDfechamodificacion(new Date());
                        seccion.setVusuariomodificacion(usuario.getVlogin());
                    } else {
                        seccion.setNseccionid(seccionService.getNextPK());
                        seccion.setNconocimientoid(this.getSelectedBpractica().getNconocimientoid());
                        seccion.setDfechacreacion(new Date());
                        seccion.setVusuariocreacion(usuario.getVlogin());
                    }
                    seccionService.saveOrUpdate(seccion);

                    seccion.setDetallePlain(Jsoup.parse(seccion.getDetalleHtml()).text());
                    GcmFileUtils.writeStringToFileServer(ruta0, "html.txt", seccion.getDetalleHtml());
                    GcmFileUtils.writeStringToFileServer(ruta0, "plain.txt", seccion.getDetallePlain());

                    String ruta1 = url1.concat(seccion.getNorden().toString()).concat("/");
                    TseccionHistId tseccionHistId = new TseccionHistId();
                    tseccionHistId.setNconocimientoid(thistorialId.getNconocimientoid());
                    tseccionHistId.setNhistorialid(thistorialId.getNhistorialid());
                    tseccionHistId.setNseccionhid(seccionHistService.getNextPK());
                    SeccionHist seccionHist = new SeccionHist();
                    seccionHist.setId(tseccionHistId);
                    seccionHist.setNorden(seccion.getNorden());
                    seccionHist.setVruta(ruta1);
                    seccionHist.setVtitulo(seccion.getVtitulo());
                    seccionHist.setVusuariocreacion(usuario.getVlogin());
                    seccionHist.setDfechacreacion(new Date());
                    seccionHistService.saveOrUpdate(seccionHist);

                    GcmFileUtils.writeStringToFileServer(ruta1, "html.txt", seccion.getDetalleHtml());
                    GcmFileUtils.writeStringToFileServer(ruta1, "plain.txt", seccion.getDetallePlain());
                }
            }

            this.setListaTargetVinculos(new ArrayList());
            this.getListaTargetVinculos().addAll(this.getListaTargetVinculosBL());
            this.getListaTargetVinculos().addAll(this.getListaTargetVinculosBP());
            this.getListaTargetVinculos().addAll(this.getListaTargetVinculosCT());
            this.getListaTargetVinculos().addAll(this.getListaTargetVinculosOM());
            this.getListaTargetVinculos().addAll(this.getListaTargetVinculosPR());
            this.getListaTargetVinculos().addAll(this.getListaTargetVinculosWK());

            if (org.apache.commons.collections.CollectionUtils.isNotEmpty(this.getListaTargetVinculos())) {
                VinculoService vinculoService = (VinculoService) ServiceFinder.findBean("VinculoService");
                VinculoHistService vinculoHistService = (VinculoHistService) ServiceFinder.findBean("VinculoHistService");
                vinculoService.deleteByConocimiento(this.getSelectedBpractica().getNconocimientoid());
                for (Consulta consulta : this.getListaTargetVinculos()) {
                    Vinculo vinculo = new Vinculo();
                    vinculo.setNvinculoid(vinculoService.getNextPK());
                    vinculo.setNconocimientoid(this.getSelectedBpractica().getNconocimientoid());
                    vinculo.setNconocimientovinc(consulta.getIdconocimiento());
                    vinculo.setNtipoconocimientovinc(consulta.getIdTipoConocimiento());
                    vinculo.setDfechacreacion(new Date());
                    vinculo.setVusuariocreacion(usuario.getVlogin());
                    vinculoService.saveOrUpdate(vinculo);

                    TvinculoHistId vinculoHistId = new TvinculoHistId();
                    vinculoHistId.setNvinculohid(vinculoHistService.getNextPK());
                    vinculoHistId.setNconocimientoid(thistorialId.getNconocimientoid());
                    vinculoHistId.setNhistorialid(thistorialId.getNhistorialid());
                    VinculoHist vinculoHist = new VinculoHist();
                    vinculoHist.setId(vinculoHistId);
                    vinculoHist.setNconocimientovinc(vinculo.getNconocimientovinc());
                    vinculoHist.setDfechacreacion(new Date());
                    vinculoHist.setVusuariocreacion(usuario.getVlogin());
                    vinculoHistService.saveOrUpdate(vinculoHist);
                }
            }

            if (cat_antigua != cat_nueva) {
                AsignacionService serviceasig = (AsignacionService) ServiceFinder.findBean("AsignacionService");
                this.getSelectedAsignacion().setNestadoid(BigDecimal.valueOf(Long.parseLong("2")));
                this.getSelectedAsignacion().setDfechaatencion(new Date());
                this.getSelectedAsignacion().setNaccionid(BigDecimal.valueOf(Long.parseLong("12")));
                serviceasig.saveOrUpdate(this.getSelectedAsignacion());

                Asignacion asignacion = new Asignacion();
                asignacion.setNasignacionid(serviceasig.getNextPK());
                asignacion.setNtipoconocimientoid(Constante.BUENAPRACTICA);
                asignacion.setNconocimientoid(this.getSelectedBpractica().getNconocimientoid());
                asignacion.setNestadoid(BigDecimal.valueOf(Long.parseLong("1")));
                CategoriaService categoriaService = (CategoriaService) ServiceFinder.findBean("CategoriaService");
                asignacion.setNusuarioid(categoriaService.getCategoriaById(this.getSelectedBpractica().getNcategoriaid()).getNmoderador());
                asignacion.setDfechaasignacion(new Date());
                asignacion.setDfechacreacion(new Date());
                serviceasig.saveOrUpdate(asignacion);

                pagina = "/gescon/index.xhtml";
            } else {
                pagina = "";
            }
            loginMB.refreshNotifications();
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "INFO.", "Actualización exitosa!.");
            FacesContext.getCurrentInstance().addMessage(null, message);
            FacesContext.getCurrentInstance().getExternalContext().redirect(pagina);
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
    }

    public void saveOmejoraEdit(ActionEvent event) {
        String pagina = null;
        try {
            //this.setContenidoHtml(JSFUtils.getRequestParameter("descHtml"));
            if (this.getChkDestacado()) {
                ConsultaService consultaService = (ConsultaService) ServiceFinder.findBean("ConsultaService");
                HashMap filter = new HashMap();
                filter.put("ntipoconocimientoid", Constante.OPORTUNIDADMEJORA);
                BigDecimal cant = consultaService.countDestacadosByTipoConocimiento(filter);
                if (cant.intValue() >= 10) {
                    this.setListaDestacados(consultaService.getDestacadosByTipoConocimiento(filter));
                    RequestContext.getCurrentInstance().execute("PF('destDialog').show();");
                    return;
                }
            }
            this.setContenidoPlain(Jsoup.parse(this.getContenidoHtml()).text());
            LoginMB loginMB = (LoginMB) JSFUtils.getSessionAttribute("loginMB");
            User usuario = loginMB.getUser();
            ConocimientoService conocimientoService = (ConocimientoService) ServiceFinder.findBean("ConocimientoService");

            if (this.getSelectedCategoria() == null) {
                this.getSelectedOmejora().setNcategoriaid(this.getSelectedOmejora().getNcategoriaid());
                cat_nueva = this.getSelectedOmejora().getNcategoriaid();
            } else {
                this.getSelectedOmejora().setNcategoriaid(this.getSelectedOmejora().getNcategoriaid());
                cat_nueva = this.getSelectedOmejora().getNcategoriaid();
            }

            this.getSelectedOmejora().setVtitulo(StringUtils.upperCase(this.getSelectedOmejora().getVtitulo()));
            this.getSelectedOmejora().setVdescripcion(StringUtils.upperCase(this.getSelectedOmejora().getVdescripcion()));
            if (this.getContenidoPlain().length() < 400) {
                this.getSelectedOmejora().setVcontenido(StringUtils.capitalize(this.getContenidoPlain()));
            } else {
                this.getSelectedOmejora().setVcontenido(StringUtils.capitalize(this.getContenidoPlain().substring(0, 400)));
            }
            this.getSelectedOmejora().setNdestacado(this.getChkDestacado() ? BigDecimal.ONE : BigDecimal.ZERO);
            this.getSelectedOmejora().setDfechamodificacion(new Date());
            this.getSelectedOmejora().setVusuariomodificacion(usuario.getVlogin());
            conocimientoService.saveOrUpdate(this.getSelectedOmejora());

            this.setContenidoPlain(Jsoup.parse(this.getContenidoHtml()).text());
            GcmFileUtils.writeStringToFileServer(this.getSelectedOmejora().getVruta(), "html.txt", this.getContenidoHtml());
            GcmFileUtils.writeStringToFileServer(this.getSelectedOmejora().getVruta(), "plain.txt", this.getContenidoPlain());

            HistorialService historialService = (HistorialService) ServiceFinder.findBean("HistorialService");
            Historial lastHistorial = historialService.getLastHistorialByConocimiento(this.getSelectedOmejora().getNconocimientoid());
            int lastversion;
            if (lastHistorial != null) {
                lastversion = lastHistorial.getNnumversion().intValue();
            } else {
                lastversion = 0;
            }
            String url = this.pathom.concat(this.getSelectedOmejora().getNconocimientoid().toString()).concat("/").concat(Integer.toString(lastversion + 1)).concat("/");

            ThistorialId thistorialId = new ThistorialId();
            thistorialId.setNconocimientoid(this.getSelectedOmejora().getNconocimientoid());
            thistorialId.setNhistorialid(historialService.getNextPK());
            Historial historial = new Historial();
            historial.setId(thistorialId);
            historial.setNtipoconocimientoid(Constante.OPORTUNIDADMEJORA);
            historial.setNcategoriaid(this.getSelectedCategoria().getNcategoriaid());
            historial.setVtitulo(this.getSelectedOmejora().getVtitulo());
            historial.setVdescripcion(this.getSelectedOmejora().getVdescripcion());
            historial.setNactivo(BigDecimal.ONE);
            historial.setNsituacionid(this.getSelectedOmejora().getNsituacionid());
            historial.setVruta(url);
            historial.setNnumversion(BigDecimal.valueOf(lastversion + 1));
            historial.setDfechacreacion(new Date());
            historial.setVusuariocreacion(usuario.getVlogin());
            historial.setVmsjsolicita(this.getSelectedOmejora().getVmsjsolicita());
            historial.setVmsjrespuesta(this.getSelectedOmejora().getVmsjrespuesta());
            historialService.saveOrUpdate(historial);

            GcmFileUtils.writeStringToFileServer(url, "html.txt", this.getContenidoHtml());
            GcmFileUtils.writeStringToFileServer(url, "plain.txt", this.getContenidoPlain());

            if (org.apache.commons.collections.CollectionUtils.isNotEmpty(this.getListaSeccion())) {
                String url0 = this.getSelectedOmejora().getVruta().concat("s");
                String url1 = url.concat("s");
                SeccionService seccionService = (SeccionService) ServiceFinder.findBean("SeccionService");
                SeccionHistService seccionHistService = (SeccionHistService) ServiceFinder.findBean("SeccionHistService");
                for (Seccion seccion : this.getListaSeccion()) {
                    String ruta0 = url0.concat(seccion.getNorden().toString()).concat("/");
                    seccion.setVruta(ruta0);
                    if (seccion.getNseccionid() != null) {
                        seccion.setDfechamodificacion(new Date());
                        seccion.setVusuariomodificacion(usuario.getVlogin());
                    } else {
                        seccion.setNseccionid(seccionService.getNextPK());
                        seccion.setNconocimientoid(this.getSelectedOmejora().getNconocimientoid());
                        seccion.setDfechacreacion(new Date());
                        seccion.setVusuariocreacion(usuario.getVlogin());
                    }
                    seccionService.saveOrUpdate(seccion);

                    seccion.setDetallePlain(Jsoup.parse(seccion.getDetalleHtml()).text());
                    GcmFileUtils.writeStringToFileServer(ruta0, "html.txt", seccion.getDetalleHtml());
                    GcmFileUtils.writeStringToFileServer(ruta0, "plain.txt", seccion.getDetallePlain());

                    String ruta1 = url1.concat(seccion.getNorden().toString()).concat("/");
                    TseccionHistId tseccionHistId = new TseccionHistId();
                    tseccionHistId.setNconocimientoid(thistorialId.getNconocimientoid());
                    tseccionHistId.setNhistorialid(thistorialId.getNhistorialid());
                    tseccionHistId.setNseccionhid(seccionHistService.getNextPK());
                    SeccionHist seccionHist = new SeccionHist();
                    seccionHist.setId(tseccionHistId);
                    seccionHist.setNorden(seccion.getNorden());
                    seccionHist.setVruta(ruta1);
                    seccionHist.setVtitulo(seccion.getVtitulo());
                    seccionHist.setVusuariocreacion(usuario.getVlogin());
                    seccionHist.setDfechacreacion(new Date());
                    seccionHistService.saveOrUpdate(seccionHist);

                    GcmFileUtils.writeStringToFileServer(ruta1, "html.txt", seccion.getDetalleHtml());
                    GcmFileUtils.writeStringToFileServer(ruta1, "plain.txt", seccion.getDetallePlain());
                }
            }

            this.setListaTargetVinculos(new ArrayList());
            this.getListaTargetVinculos().addAll(this.getListaTargetVinculosBL());
            this.getListaTargetVinculos().addAll(this.getListaTargetVinculosBP());
            this.getListaTargetVinculos().addAll(this.getListaTargetVinculosCT());
            this.getListaTargetVinculos().addAll(this.getListaTargetVinculosOM());
            this.getListaTargetVinculos().addAll(this.getListaTargetVinculosPR());
            this.getListaTargetVinculos().addAll(this.getListaTargetVinculosWK());

            if (org.apache.commons.collections.CollectionUtils.isNotEmpty(this.getListaTargetVinculos())) {
                VinculoService vinculoService = (VinculoService) ServiceFinder.findBean("VinculoService");
                VinculoHistService vinculoHistService = (VinculoHistService) ServiceFinder.findBean("VinculoHistService");
                vinculoService.deleteByConocimiento(this.getSelectedOmejora().getNconocimientoid());
                for (Consulta consulta : this.getListaTargetVinculos()) {
                    Vinculo vinculo = new Vinculo();
                    vinculo.setNvinculoid(vinculoService.getNextPK());
                    vinculo.setNconocimientoid(this.getSelectedOmejora().getNconocimientoid());
                    vinculo.setNconocimientovinc(consulta.getIdconocimiento());
                    vinculo.setNtipoconocimientovinc(consulta.getIdTipoConocimiento());
                    vinculo.setDfechacreacion(new Date());
                    vinculo.setVusuariocreacion(usuario.getVlogin());
                    vinculoService.saveOrUpdate(vinculo);

                    TvinculoHistId vinculoHistId = new TvinculoHistId();
                    vinculoHistId.setNvinculohid(vinculoHistService.getNextPK());
                    vinculoHistId.setNconocimientoid(thistorialId.getNconocimientoid());
                    vinculoHistId.setNhistorialid(thistorialId.getNhistorialid());
                    VinculoHist vinculoHist = new VinculoHist();
                    vinculoHist.setId(vinculoHistId);
                    vinculoHist.setNconocimientovinc(vinculo.getNconocimientovinc());
                    vinculoHist.setDfechacreacion(new Date());
                    vinculoHist.setVusuariocreacion(usuario.getVlogin());
                    vinculoHistService.saveOrUpdate(vinculoHist);
                }
            }

            if (cat_antigua != cat_nueva) {
                AsignacionService serviceasig = (AsignacionService) ServiceFinder.findBean("AsignacionService");
                this.getSelectedAsignacion().setNestadoid(BigDecimal.valueOf(Long.parseLong("2")));
                this.getSelectedAsignacion().setDfechaatencion(new Date());
                this.getSelectedAsignacion().setNaccionid(BigDecimal.valueOf(Long.parseLong("12")));
                serviceasig.saveOrUpdate(this.getSelectedAsignacion());

                Asignacion asignacion = new Asignacion();
                asignacion.setNasignacionid(serviceasig.getNextPK());
                asignacion.setNtipoconocimientoid(Constante.OPORTUNIDADMEJORA);
                asignacion.setNconocimientoid(this.getSelectedOmejora().getNconocimientoid());
                asignacion.setNestadoid(BigDecimal.valueOf(Long.parseLong("1")));
                CategoriaService categoriaService = (CategoriaService) ServiceFinder.findBean("CategoriaService");
                asignacion.setNusuarioid(categoriaService.getCategoriaById(this.getSelectedOmejora().getNcategoriaid()).getNmoderador());
                asignacion.setDfechaasignacion(new Date());
                asignacion.setDfechacreacion(new Date());
                serviceasig.saveOrUpdate(asignacion);

                pagina = "/gescon/index.xhtml";
            } else {
                pagina = "";
            }
            loginMB.refreshNotifications();
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "INFO.", "Actualización exitosa!.");
            FacesContext.getCurrentInstance().addMessage(null, message);
            FacesContext.getCurrentInstance().getExternalContext().redirect(pagina);
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
    }

    public void saveWikiEdit(ActionEvent event) {
        String pagina = null;
        try {
            //this.setDescripcionHtml(JSFUtils.getRequestParameter("descHtml"));
            if (this.getChkDestacado()) {
                ConsultaService consultaService = (ConsultaService) ServiceFinder.findBean("ConsultaService");
                HashMap filter = new HashMap();
                filter.put("ntipoconocimientoid", Constante.WIKI);
                BigDecimal cant = consultaService.countDestacadosByTipoConocimiento(filter);
                if (cant.intValue() >= 10) {
                    this.setListaDestacados(consultaService.getDestacadosByTipoConocimiento(filter));
                    RequestContext.getCurrentInstance().execute("PF('destDialog').show();");
                    return;
                }
            }
            LoginMB loginMB = (LoginMB) JSFUtils.getSessionAttribute("loginMB");
            User usuario = loginMB.getUser();
            ConocimientoService conocimientoService = (ConocimientoService) ServiceFinder.findBean("ConocimientoService");
            this.setDescripcionPlain(Jsoup.parse(this.getDescripcionHtml()).text());

            if (this.getSelectedCategoria() == null) {
                this.getSelectedWiki().setNcategoriaid(this.getSelectedWiki().getNcategoriaid());
                cat_nueva = this.getSelectedWiki().getNcategoriaid();
            } else {
                this.getSelectedWiki().setNcategoriaid(this.getSelectedWiki().getNcategoriaid());
                cat_nueva = this.getSelectedWiki().getNcategoriaid();
            }

            this.getSelectedWiki().setVtitulo(StringUtils.upperCase(this.getSelectedWiki().getVtitulo()));
            if (this.getDescripcionPlain().length() < 400) {
                this.getSelectedWiki().setVdescripcion(StringUtils.capitalize(this.getDescripcionPlain()));
            } else {
                this.getSelectedWiki().setVdescripcion(StringUtils.capitalize(this.getDescripcionPlain().substring(0, 400)));
            }
            this.getSelectedWiki().setNdestacado(this.getChkDestacado() ? BigDecimal.ONE : BigDecimal.ZERO);
            this.getSelectedWiki().setDfechamodificacion(new Date());
            this.getSelectedWiki().setVusuariomodificacion(usuario.getVlogin());
            conocimientoService.saveOrUpdate(this.getSelectedWiki());

            GcmFileUtils.writeStringToFileServer(this.getSelectedWiki().getVruta(), "html.txt", this.getDescripcionHtml());
            GcmFileUtils.writeStringToFileServer(this.getSelectedWiki().getVruta(), "plain.txt", this.getDescripcionPlain());

            HistorialService historialService = (HistorialService) ServiceFinder.findBean("HistorialService");
            Historial lastHistorial = historialService.getLastHistorialByConocimiento(this.getSelectedWiki().getNconocimientoid());
            int lastversion;
            if (lastHistorial != null) {
                lastversion = lastHistorial.getNnumversion().intValue();
            } else {
                lastversion = 0;
            }
            String url = this.pathwk.concat(this.getSelectedWiki().getNconocimientoid().toString()).concat("/").concat(Integer.toString(lastversion + 1)).concat("/");

            ThistorialId thistorialId = new ThistorialId();
            thistorialId.setNconocimientoid(this.getSelectedWiki().getNconocimientoid());
            thistorialId.setNhistorialid(historialService.getNextPK());
            Historial historial = new Historial();
            historial.setId(thistorialId);
            historial.setNtipoconocimientoid(Constante.WIKI);
            historial.setNcategoriaid(this.getSelectedCategoria().getNcategoriaid());
            historial.setVtitulo(this.getSelectedWiki().getVtitulo());
            historial.setNactivo(BigDecimal.ONE);
            historial.setNsituacionid(this.getSelectedWiki().getNsituacionid());
            historial.setVruta(url);
            historial.setNnumversion(BigDecimal.valueOf(lastversion + 1));
            historial.setDfechacreacion(new Date());
            historial.setVusuariocreacion(usuario.getVlogin());
            historial.setVmsjsolicita(this.getSelectedWiki().getVmsjsolicita());
            historial.setVmsjrespuesta(this.getSelectedWiki().getVmsjrespuesta());
            historialService.saveOrUpdate(historial);

            GcmFileUtils.writeStringToFileServer(url, "html.txt", this.getDescripcionHtml());
            GcmFileUtils.writeStringToFileServer(url, "plain.txt", this.getDescripcionPlain());

            if (org.apache.commons.collections.CollectionUtils.isNotEmpty(this.getListaSeccion())) {
                String url0 = this.getSelectedWiki().getVruta().concat("s");
                String url1 = url.concat("s");
                SeccionService seccionService = (SeccionService) ServiceFinder.findBean("SeccionService");
                SeccionHistService seccionHistService = (SeccionHistService) ServiceFinder.findBean("SeccionHistService");
                for (Seccion seccion : this.getListaSeccion()) {
                    String ruta0 = url0.concat(seccion.getNorden().toString()).concat("/");
                    seccion.setVruta(ruta0);
                    if (seccion.getNseccionid() != null) {
                        seccion.setDfechamodificacion(new Date());
                        seccion.setVusuariomodificacion(usuario.getVlogin());
                    } else {
                        seccion.setNseccionid(seccionService.getNextPK());
                        seccion.setNconocimientoid(this.getSelectedWiki().getNconocimientoid());
                        seccion.setDfechacreacion(new Date());
                        seccion.setVusuariocreacion(usuario.getVlogin());
                    }
                    seccionService.saveOrUpdate(seccion);

                    seccion.setDetallePlain(Jsoup.parse(seccion.getDetalleHtml()).text());
                    GcmFileUtils.writeStringToFileServer(ruta0, "html.txt", seccion.getDetalleHtml());
                    GcmFileUtils.writeStringToFileServer(ruta0, "plain.txt", seccion.getDetallePlain());

                    String ruta1 = url1.concat(seccion.getNorden().toString()).concat("/");
                    TseccionHistId tseccionHistId = new TseccionHistId();
                    tseccionHistId.setNconocimientoid(thistorialId.getNconocimientoid());
                    tseccionHistId.setNhistorialid(thistorialId.getNhistorialid());
                    tseccionHistId.setNseccionhid(seccionHistService.getNextPK());
                    SeccionHist seccionHist = new SeccionHist();
                    seccionHist.setId(tseccionHistId);
                    seccionHist.setNorden(seccion.getNorden());
                    seccionHist.setVruta(ruta1);
                    seccionHist.setVtitulo(seccion.getVtitulo());
                    seccionHist.setVusuariocreacion(usuario.getVlogin());
                    seccionHist.setDfechacreacion(new Date());
                    seccionHistService.saveOrUpdate(seccionHist);

                    GcmFileUtils.writeStringToFileServer(ruta1, "html.txt", seccion.getDetalleHtml());
                    GcmFileUtils.writeStringToFileServer(ruta1, "plain.txt", seccion.getDetallePlain());
                }
            }

            this.setListaTargetVinculos(new ArrayList());
            this.getListaTargetVinculos().addAll(this.getListaTargetVinculosBL());
            this.getListaTargetVinculos().addAll(this.getListaTargetVinculosBP());
            this.getListaTargetVinculos().addAll(this.getListaTargetVinculosCT());
            this.getListaTargetVinculos().addAll(this.getListaTargetVinculosOM());
            this.getListaTargetVinculos().addAll(this.getListaTargetVinculosPR());
            this.getListaTargetVinculos().addAll(this.getListaTargetVinculosWK());

            if (org.apache.commons.collections.CollectionUtils.isNotEmpty(this.getListaTargetVinculos())) {
                VinculoService vinculoService = (VinculoService) ServiceFinder.findBean("VinculoService");
                VinculoHistService vinculoHistService = (VinculoHistService) ServiceFinder.findBean("VinculoHistService");
                vinculoService.deleteByConocimiento(this.getSelectedWiki().getNconocimientoid());
                for (Consulta consulta : this.getListaTargetVinculos()) {
                    Vinculo vinculo = new Vinculo();
                    vinculo.setNvinculoid(vinculoService.getNextPK());
                    vinculo.setNconocimientoid(this.getSelectedWiki().getNconocimientoid());
                    vinculo.setNconocimientovinc(consulta.getIdconocimiento());
                    vinculo.setNtipoconocimientovinc(consulta.getIdTipoConocimiento());
                    vinculo.setDfechacreacion(new Date());
                    vinculo.setVusuariocreacion(usuario.getVlogin());
                    vinculoService.saveOrUpdate(vinculo);

                    TvinculoHistId vinculoHistId = new TvinculoHistId();
                    vinculoHistId.setNvinculohid(vinculoHistService.getNextPK());
                    vinculoHistId.setNconocimientoid(thistorialId.getNconocimientoid());
                    vinculoHistId.setNhistorialid(thistorialId.getNhistorialid());
                    VinculoHist vinculoHist = new VinculoHist();
                    vinculoHist.setId(vinculoHistId);
                    vinculoHist.setNconocimientovinc(vinculo.getNconocimientovinc());
                    vinculoHist.setDfechacreacion(new Date());
                    vinculoHist.setVusuariocreacion(usuario.getVlogin());
                    vinculoHistService.saveOrUpdate(vinculoHist);
                }
            }

            if (cat_antigua != cat_nueva) {
                AsignacionService serviceasig = (AsignacionService) ServiceFinder.findBean("AsignacionService");
                this.getSelectedAsignacion().setNestadoid(BigDecimal.valueOf(Long.parseLong("2")));
                this.getSelectedAsignacion().setDfechaatencion(new Date());
                this.getSelectedAsignacion().setNaccionid(BigDecimal.valueOf(Long.parseLong("12")));
                serviceasig.saveOrUpdate(this.getSelectedAsignacion());

                Asignacion asignacion = new Asignacion();
                asignacion.setNasignacionid(serviceasig.getNextPK());
                asignacion.setNtipoconocimientoid(Constante.WIKI);
                asignacion.setNconocimientoid(this.getSelectedWiki().getNconocimientoid());
                asignacion.setNestadoid(BigDecimal.valueOf(Long.parseLong("1")));
                CategoriaService categoriaService = (CategoriaService) ServiceFinder.findBean("CategoriaService");
                asignacion.setNusuarioid(categoriaService.getCategoriaById(this.getSelectedWiki().getNcategoriaid()).getNmoderador());
                asignacion.setDfechaasignacion(new Date());
                asignacion.setDfechacreacion(new Date());
                serviceasig.saveOrUpdate(asignacion);

                pagina = "/gescon/index.xhtml";
            } else {
                pagina = "";
            }
            loginMB.refreshNotifications();
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "INFO.", "Actualización exitosa!.");
            FacesContext.getCurrentInstance().addMessage(null, message);
            FacesContext.getCurrentInstance().getExternalContext().redirect(pagina);
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
    }

    public void logout() {
        try {
            JSFUtils.getSession().invalidate();
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
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

    public void toAddLinkWiki(ActionEvent event) {
        try {
            this.setIdTipoConocimiento(null);
            this.setListaSourceVinculos(new ArrayList());
            this.setListaTargetVinculos(new ArrayList());
            this.setPickListWiki(new DualListModel<Consulta>(this.getListaSourceVinculos(), this.getListaTargetVinculos()));
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
    }

    public void toAddLinkBpractica(ActionEvent event) {
        try {
            this.setIdTipoConocimiento(null);
            this.setListaSourceVinculos(new ArrayList());
            this.setListaTargetVinculos(new ArrayList());
            this.setPickListBpractica(new DualListModel<Consulta>(this.getListaSourceVinculos(), this.getListaTargetVinculos()));
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
    }

    public void toAddLinkOmejora(ActionEvent event) {
        try {
            this.setIdTipoConocimiento(null);
            this.setListaSourceVinculos(new ArrayList());
            this.setListaTargetVinculos(new ArrayList());
            this.setPickListOmejora(new DualListModel<>(this.getListaSourceVinculos(), this.getListaTargetVinculos()));
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
                        List<String> ids = new ArrayList<>();
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
                    this.setPickListPregunta(new DualListModel<>(this.getListaSourceVinculos(), this.getListaTargetVinculos()));
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

                this.listaTargetVinculosConocimiento = new ArrayList<>();

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

    public void clearSection() {
        try {
            this.setSelectedSeccion(null);
            this.setTitulo(StringUtils.EMPTY);
            this.setDetalleHtml(StringUtils.EMPTY);
            this.setDetallePlain(StringUtils.EMPTY);
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

    public void toAddSection(ActionEvent event) {
        try {
            this.clearSection();
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
    }

    public void addSection(ActionEvent event) {
        try {
            if (StringUtils.isBlank(this.getTitulo())) {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR.", "TÃ­tulo de la secciÃ³n requerido. Ingrese el tÃ­tulo de la secciÃ³n a agregar.");
                FacesContext.getCurrentInstance().addMessage(null, message);
                return;
            }
            if (StringUtils.isBlank(this.getDetalleHtml())) {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR.", "Detalle de la secciÃ³n requerido. Ingrese el detalle de la secciÃ³n a agregar.");
                FacesContext.getCurrentInstance().addMessage(null, message);
                return;
            }
            Seccion seccion = new Seccion();
            seccion.setVtitulo(this.getTitulo());
            seccion.setDetalleHtml(this.getDetalleHtml());
            seccion.setDetallePlain(Jsoup.parse(seccion.getDetalleHtml()).text());
            if (org.apache.commons.collections.CollectionUtils.isEmpty(this.getListaSeccion())) {
                this.setListaSeccion(new ArrayList<Seccion>());
                seccion.setNorden(BigDecimal.ONE);
            } else {
                seccion.setNorden(BigDecimal.valueOf(this.getListaSeccion().size() + 1));
            }
            this.getListaSeccion().add(seccion);
            RequestContext.getCurrentInstance().execute("PF('secDialog').hide();");
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
    }

    public void toEditSection(ActionEvent event) {
        try {
            this.clearSection();
            int index = Integer.parseInt((String) JSFUtils.getRequestParameter("index"));
            this.setSelectedSeccion(this.getListaSeccion().get(index));
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
    }

    public void editSection(ActionEvent event) {
        try {
            if (StringUtils.isBlank(this.getSelectedSeccion().getVtitulo())) {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR.", "TÃ­tulo de la secciÃ³n requerido. Ingrese el tÃ­tulo de la secciÃ³n a agregar.");
                FacesContext.getCurrentInstance().addMessage(null, message);
                return;
            }
            if (StringUtils.isBlank(this.getSelectedSeccion().getDetalleHtml())) {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR.", "Detalle de la secciÃ³n requerido. Ingrese el detalle de la secciÃ³n a agregar.");
                FacesContext.getCurrentInstance().addMessage(null, message);
                return;
            }
            RequestContext.getCurrentInstance().execute("PF('esecDialog').hide();");
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
    }

    public void onListTipoConocimientoChangeWiki(AjaxBehaviorEvent event) {
        try {
            if (event != null) {
                final BigDecimal id = (BigDecimal) ((SelectOneMenu) event.getSource()).getValue();
                this.setIdTipoConocimiento(id);
                if (id != null) {
                    HashMap filters = new HashMap();
                    filters.put("ntipoconocimientoid", id.toString());
                    ConocimientoService service = (ConocimientoService) ServiceFinder.findBean("ConocimientoService");
                    if (this.getSelectedWiki() != null) {
                        filters.put("nconocimientoid", this.getSelectedWiki().getNconocimientoid().toString());
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
                        if (filter.isEmpty()) {
                            if (id.equals(Constante.WIKI)) {
                                filter = filter.concat(this.getSelectedWiki().getNconocimientoid().toString());
                            }
                        } else {
                            if (id.equals(Constante.WIKI)) {
                                filter = filter.concat(",").concat(this.getSelectedWiki().getNconocimientoid().toString());
                            }
                        }

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
                    this.setPickListWiki(new DualListModel<>(this.getListaSourceVinculos(), this.getListaTargetVinculos()));
                }
            }
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
    }

    public void onListTipoConocimientoChangeBpractica(AjaxBehaviorEvent event) {
        try {
            if (event != null) {
                final BigDecimal id = (BigDecimal) ((SelectOneMenu) event.getSource()).getValue();
                this.setIdTipoConocimiento(id);
                if (id != null) {
                    HashMap filters = new HashMap();
                    filters.put("ntipoconocimientoid", id.toString());
                    ConocimientoService service = (ConocimientoService) ServiceFinder.findBean("ConocimientoService");
                    if (this.getSelectedBpractica() != null) {
                        filters.put("nconocimientoid", this.getSelectedBpractica().getNconocimientoid().toString());
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
                        List<String> ids = new ArrayList<>();
                        for (Consulta c : this.getListaTargetVinculos()) {
                            ids.add(c.getIdconocimiento().toString());
                        }
                        String filter = StringUtils.join(ids, ',');
                        if (filter.isEmpty()) {
                            if (id.equals(Constante.BUENAPRACTICA)) {
                                filter = filter.concat(this.getSelectedBpractica().getNconocimientoid().toString());
                            }
                        } else {
                            if (id.equals(Constante.BUENAPRACTICA)) {
                                filter = filter.concat(",").concat(this.getSelectedBpractica().getNconocimientoid().toString());
                            }
                        }

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
                    this.setPickListBpractica(new DualListModel<>(this.getListaSourceVinculos(), this.getListaTargetVinculos()));
                }
            }
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
    }

    public void onListTipoConocimientoChangeOmejora(AjaxBehaviorEvent event) {
        try {
            if (event != null) {
                final BigDecimal id = (BigDecimal) ((SelectOneMenu) event.getSource()).getValue();
                this.setIdTipoConocimiento(id);
                if (id != null) {
                    HashMap filters = new HashMap();
                    filters.put("ntipoconocimientoid", id.toString());
                    ConocimientoService service = (ConocimientoService) ServiceFinder.findBean("ConocimientoService");
                    if (this.getSelectedOmejora() != null) {
                        filters.put("nconocimientoid", this.getSelectedOmejora().getNconocimientoid().toString());
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
                        List<String> ids = new ArrayList<>();
                        for (Consulta c : this.getListaTargetVinculos()) {
                            ids.add(c.getIdconocimiento().toString());
                        }
                        String filter = StringUtils.join(ids, ',');
                        if (filter.isEmpty()) {
                            if (id.equals(Constante.OPORTUNIDADMEJORA)) {
                                filter = filter.concat(this.getSelectedOmejora().getNconocimientoid().toString());
                            }
                        } else {
                            if (id.equals(Constante.BUENAPRACTICA)) {
                                filter = filter.concat(",").concat(this.getSelectedOmejora().getNconocimientoid().toString());
                            }
                        }

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
                    this.setPickListOmejora(new DualListModel<>(this.getListaSourceVinculos(), this.getListaTargetVinculos()));
                }
            }
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
    }

    public void onTransferWiki(TransferEvent event) {
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
            }
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
    }

    public void onTransferBpractica(TransferEvent event) {
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
            }
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
    }

    public void onTransferOmejora(TransferEvent event) {
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
            }
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
    }

    public void onListTipoConocimientoChangeCT(AjaxBehaviorEvent event) {
        try {
            if (event != null) {
                final BigDecimal id = (BigDecimal) ((SelectOneMenu) event.getSource()).getValue();
                this.setIdTipoConocimiento(id);
                if (id != null) {
                    HashMap filters = new HashMap();
                    filters.put("ntipoconocimientoid", id);
                    filters.put("nconocimientoid", this.getSelectedContenido().getNconocimientoid());
                    ContenidoService service = (ContenidoService) ServiceFinder.findBean("ContenidoService");
                    if (this.getSelectedContenido() != null) {
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
                        List<String> ids = new ArrayList<>();
                        for (Consulta c : this.getListaTargetVinculos()) {
                            ids.add(c.getIdconocimiento().toString());
                        }
                        String filter = StringUtils.join(ids, ',');
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
                    this.setPickListContenido(new DualListModel<>(this.getListaSourceVinculos(), this.getListaTargetVinculos()));
                }
            }
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
    }

    public StreamedContent getPdf() throws IOException, Exception {
        FacesContext context = FacesContext.getCurrentInstance();

        if (context.getCurrentPhaseId() == PhaseId.RENDER_RESPONSE) {
            // So, we're rendering the HTML. Return a stub StreamedContent so that it will generate right URL.
            return new DefaultStreamedContent();
        } else {
            // So, browser is requesting the image. Return a real StreamedContent with the image bytes.
            String fileName = JSFUtils.getRequestParameter("fileName");
            FileInputStream fis = new FileInputStream(new File(fileName));
            return new DefaultStreamedContent(fis, "application/pdf");
        }
    }

    public StreamedContent getPdfCT() throws IOException, Exception {
        FacesContext context = FacesContext.getCurrentInstance();

        if (context.getCurrentPhaseId() == PhaseId.RENDER_RESPONSE) {
            // So, we're rendering the HTML. Return a stub StreamedContent so that it will generate right URL.
            return new DefaultStreamedContent();
        } else {
            // So, browser is requesting the image. Return a real StreamedContent with the image bytes.
            ResourceBundle bundle = ResourceBundle.getBundle(Parameters.getParameters());
            String fileName = JSFUtils.getRequestParameter("fileName");
            if (fileName.isEmpty()) {
                String fileNameS = (bundle.getString("temppath")) + this.getUploadFile().getFileName();
                FileInputStream fis = new FileInputStream(new File(fileNameS));
                return new DefaultStreamedContent(fis, "application/pdf");
            } else {
                String fileNameS = (bundle.getString("path")) + fileName;
                FileInputStream fis = new FileInputStream(new File(fileNameS));
                return new DefaultStreamedContent(fis, "application/pdf");
            }
        }
    }
    
    public void setSelectedFile(ActionEvent event) {
        try {
            if (event != null) {
                int index = Integer.parseInt((String) JSFUtils.getRequestParameter("index"));
                this.setSelectedArchivo(this.getListaArchivos().get(index));
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
    }

    public void deleteFile() {
        try {
            ResourceBundle bundle = ResourceBundle.getBundle(Parameters.getParameters());
            File temp = new File(bundle.getString("temppath"), this.getSelectedArchivo().getVnombre());
            if(temp.exists())   temp.delete();
            if(this.getSelectedArchivo().getNconocimientoid() != null) {
                String id = this.getSelectedArchivo().getNconocimientoid().toString();
                File dir = new File(bundle.getString("path") + "ct" + '/' + id + "/0/" + this.getSelectedArchivo().getVnombre());
                if(dir.exists())    dir.delete();
            }
            if(this.getSelectedArchivo().getNarchivoid() != null) {
                ArchivoConocimientoService aservice = (ArchivoConocimientoService) ServiceFinder.findBean("ArchivoConocimientoService");
                aservice.delete(this.getSelectedArchivo().getNarchivoid());
            }
            this.getListaArchivos().remove(this.getSelectedArchivo());
        } catch(Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
    }
    
    public void adjuntar(ActionEvent event) {
        try {
            if (event != null) {
                if(StringUtils.isBlank(this.getTipoContenido())) {
                    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR.", "Seleccione el tipo de contenido a adjuntar.");
                    FacesContext.getCurrentInstance().addMessage(null, message);
                    return;
                }
                
                if(this.getUploadFile() == null) {
                    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR.", "Debe Cargar el archivo antes de adjuntar.");
                    FacesContext.getCurrentInstance().addMessage(null, message);
                    return;
                }
                
                ResourceBundle bundle = ResourceBundle.getBundle(Parameters.getMessages());
                String tipoDocumento = bundle.getString("tipoDocumento");
                String tipoVideo = bundle.getString("tipoVideo");
                String tipoAudio = bundle.getString("tipoAudio");
                String tipoImagen = bundle.getString("tipoImagen");
                String tipoArchivo = bundle.getString("tipoArchivo");
                String tipoLink = bundle.getString("tipoLink");
                String tipoOtro = bundle.getString("tipoOtro");
                
                String tmppath = bundle.getString("temppath");
                String filename = this.getUploadFile().getFileName();
                String contentType = this.getUploadFile().getContentType();
                
                String contentTypePdf = bundle.getString("contentTypePdf");
                String contentTypeWord = bundle.getString("contentTypeWord");
                String contentTypeExcel = bundle.getString("contentTypeExcel");
                String contentTypePowerPoint = bundle.getString("contentTypePowerPoint");
                String contentTypeWordx = bundle.getString("contentTypeWordx");
                String contentTypeExcelx = bundle.getString("contentTypeExcelx");
                String contentTypePowerPointx = bundle.getString("contentTypePowerPointx");
                String contentTypeMpg = bundle.getString("contentTypeMpg");
                String contentTypeAvi = bundle.getString("contentTypeAvi");
                String contentTypeMp4 = bundle.getString("contentTypeMp4");
                String contentTypeQuickTime = bundle.getString("contentTypeQuickTime");
                String contentTypeMp3 = bundle.getString("contentTypeMp3");
                String contentTypeMp3_ = bundle.getString("contentTypeMp3_");
                String contentTypeWav = bundle.getString("contentTypeWav");
                String contentTypeGif = bundle.getString("contentTypeGif");
                String contentTypeJpg = bundle.getString("contentTypeJpg");
                String contentTypePng = bundle.getString("contentTypePng");
                String contentTypeTiff = bundle.getString("contentTypeTiff");
                String contentTypePlain = bundle.getString("contentTypePlain");
                String contentTypeRichtext = bundle.getString("contentTypeRichtext");
                String contentTypeXml = bundle.getString("contentTypeXml");
                String contentTypeHtml = bundle.getString("contentTypeHtml");
                String contentTypeLink = bundle.getString("contentTypeLink");
                
                if(this.getTipoContenido().equals(tipoDocumento)) {
                    if(!contentType.equals(contentTypePdf) && !contentType.equals(contentTypeWord) && !contentType.equals(contentTypeExcel)
                            && !contentType.equals(contentTypePowerPoint) && !contentType.equals(contentTypeWordx)
                            && !contentType.equals(contentTypeExcelx) && !contentType.equals(contentTypePowerPointx)) {
                        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR.", "Formato de archivo incorrecto!.");
                        FacesContext.getCurrentInstance().addMessage(null, message);
                        return;
                    }
                }
                if(this.getTipoContenido().equals(tipoVideo)) {
                    if(!contentType.equals(contentTypeMpg) && !contentType.equals(contentTypeAvi) 
                            && !contentType.equals(contentTypeMp4) && !contentType.equals(contentTypeQuickTime)) {
                        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR.", "Formato de archivo incorrecto!.");
                        FacesContext.getCurrentInstance().addMessage(null, message);
                        return;
                    }
                }
                if(this.getTipoContenido().equals(tipoAudio)) {
                    if(!contentType.equals(contentTypeMp3) && !contentType.equals(contentTypeMp3_) && !contentType.equals(contentTypeWav)) {
                        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR.", "Formato de archivo incorrecto!.");
                        FacesContext.getCurrentInstance().addMessage(null, message);
                        return;
                    }
                }
                if(this.getTipoContenido().equals(tipoImagen)) {
                    if(!contentType.equals(contentTypeGif) && !contentType.equals(contentTypeJpg) 
                            && !contentType.equals(contentTypePng) && !contentType.equals(contentTypeTiff)) {
                        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR.", "Formato de archivo incorrecto!.");
                        FacesContext.getCurrentInstance().addMessage(null, message);
                        return;
                    }
                }
                if(this.getTipoContenido().equals(tipoArchivo)) {
                    if(!contentType.equals(contentTypePlain) && !contentType.equals(contentTypeRichtext) 
                            && !contentType.equals(contentTypeXml) && !contentType.equals(contentTypeHtml)) {
                        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR.", "Formato de archivo incorrecto!.");
                        FacesContext.getCurrentInstance().addMessage(null, message);
                        return;
                    }
                }
                if(this.getTipoContenido().equals(tipoLink)) {
                    if(!contentType.equals(contentTypeLink)) {
                        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR.", "Formato de archivo incorrecto!.");
                        FacesContext.getCurrentInstance().addMessage(null, message);
                        return;
                    }
                }
                
                ArchivoConocimiento archivoconocimiento = new ArchivoConocimiento();
                archivoconocimiento.setUploadedFile(this.getUploadFile());
                archivoconocimiento.setVnombre(filename);
                archivoconocimiento.setNtipoarchivo(BigDecimal.valueOf(Long.parseLong(this.getTipoContenido())));
                archivoconocimiento.setVcontenttype(contentType);
                archivoconocimiento.setVruta(tmppath + filename);
                archivoconocimiento.setFile(new File(archivoconocimiento.getVruta()));
                archivoconocimiento.setContent(new DefaultStreamedContent(new FileInputStream(archivoconocimiento.getFile()), contentType, filename));
                this.getListaArchivos().add(archivoconocimiento);
                System.out.println("filename: "+filename);
            }
        } catch(NumberFormatException | FileNotFoundException e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
    }
}
