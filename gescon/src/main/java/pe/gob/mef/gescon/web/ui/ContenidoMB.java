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
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.event.PhaseId;
import javax.faces.model.SelectItem;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.io.FileUtils;
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
import pe.gob.mef.gescon.common.Constante;
import pe.gob.mef.gescon.common.Parameters;
import pe.gob.mef.gescon.hibernate.domain.ThistorialId;
import pe.gob.mef.gescon.hibernate.domain.TvinculoHistId;
import pe.gob.mef.gescon.service.ArchivoConocimientoService;
import pe.gob.mef.gescon.service.AsignacionService;
import pe.gob.mef.gescon.service.CalificacionService;
import pe.gob.mef.gescon.service.CategoriaService;
import pe.gob.mef.gescon.service.ConocimientoService;
import pe.gob.mef.gescon.service.ConsultaService;
import pe.gob.mef.gescon.service.ContenidoService;
import pe.gob.mef.gescon.service.DiscusionHistService;
import pe.gob.mef.gescon.service.DiscusionSeccionHistService;
import pe.gob.mef.gescon.service.DiscusionSeccionService;
import pe.gob.mef.gescon.service.DiscusionService;
import pe.gob.mef.gescon.service.HistorialService;
import pe.gob.mef.gescon.service.SeccionHistService;
import pe.gob.mef.gescon.service.UserService;
import pe.gob.mef.gescon.service.VinculoHistService;
import pe.gob.mef.gescon.service.VinculoService;
import pe.gob.mef.gescon.util.GcmFileUtils;
import pe.gob.mef.gescon.util.JSFUtils;
import pe.gob.mef.gescon.util.ServiceFinder;
import pe.gob.mef.gescon.web.bean.ArchivoConocimiento;
import pe.gob.mef.gescon.web.bean.Asignacion;
import pe.gob.mef.gescon.web.bean.Calificacion;
import pe.gob.mef.gescon.web.bean.Categoria;
import pe.gob.mef.gescon.web.bean.Conocimiento;
import pe.gob.mef.gescon.web.bean.Consulta;
import pe.gob.mef.gescon.web.bean.Discusion;
import pe.gob.mef.gescon.web.bean.DiscusionHist;
import pe.gob.mef.gescon.web.bean.DiscusionSeccion;
import pe.gob.mef.gescon.web.bean.DiscusionSeccionHist;
import pe.gob.mef.gescon.web.bean.Historial;
import pe.gob.mef.gescon.web.bean.SeccionHist;
import pe.gob.mef.gescon.web.bean.User;
import pe.gob.mef.gescon.web.bean.Vinculo;
import pe.gob.mef.gescon.web.bean.VinculoHist;

/**
 *
 * @author JJacobo
 */
@ManagedBean
@SessionScoped
public class ContenidoMB implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final Log log = LogFactory.getLog(ContenidoMB.class);
    private final String path = "ct/";
    private List<Conocimiento> listaContenido;
    private List<Asignacion> listaAsignacion;
    private Conocimiento selectedContenido;
    private Asignacion selectedAsignacion;
    private String titulo;
    private String detalle;
    private String descripcion;
    private String contenidoHtml;
    private String contenidoPlain;
    private TreeNode tree;
    private Categoria selectedCategoria;
    private Boolean chkDestacado;
    private UploadedFile uploadFile;
    private StreamedContent content;
    private File file;
    private BigDecimal idTipoConocimiento;
    private List<Consulta> listaSourceVinculos;
    private List<Consulta> listaTargetVinculos;
    private DualListModel<Consulta> pickList;
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
    private List<ArchivoConocimiento> listaArchivos;
    private ArchivoConocimiento selectedArchivo;
    private Discusion selectedDiscusion;
    private List<DiscusionSeccion> listaDiscusionSeccion;
    private DiscusionSeccion selectedDiscusionSeccion;
    private String tituloDiscusion;
    private BigDecimal tipoDiscusion;
    private String discusionHtml;
    private String discusionPlain;
    private List<Historial> listaHistorial;
    private List<Historial> filteredListaHistorial;
    private List<Historial> selectedHistoriales;
    private Historial selectedHistorialLeft;
    private Historial selectedHistorialRight;
    private List<Calificacion> listaCalificacion;
    private Calificacion selectedCalificacion;
    private BigDecimal calificacion;
    private String comentario;
    private String selectedSwitch;
    private List<Consulta> listaDestacados;
    private Consulta selectedDestacado;
    private String tipoContenido;
    private String tipoDocumentos;
    private String tipoVideos;
    private String tipoAudios;
    private String tipoImagenes;
    private String tipoArchivos;
    private String tipoLinks;
    private String tipoOtros;

    /**
     * Creates a new instance of ContenidoMB
     */
    public ContenidoMB() {
    }

    /**
     * @return the listaContenido
     */
    public List<Conocimiento> getListaContenido() {
        return listaContenido;
    }

    /**
     * @param listaContenido the listaContenido to set
     */
    public void setListaContenido(List<Conocimiento> listaContenido) {
        this.listaContenido = listaContenido;
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
     * @return the pickList
     */
    public DualListModel<Consulta> getPickList() {
        return pickList;
    }

    /**
     * @param pickList the pickList to set
     */
    public void setPickList(DualListModel<Consulta> pickList) {
        this.pickList = pickList;
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

    public Discusion getSelectedDiscusion() {
        return selectedDiscusion;
    }

    public void setSelectedDiscusion(Discusion selectedDiscusion) {
        this.selectedDiscusion = selectedDiscusion;
    }

    public List<DiscusionSeccion> getListaDiscusionSeccion() {
        return listaDiscusionSeccion;
    }

    public void setListaDiscusionSeccion(List<DiscusionSeccion> listaDiscusionSeccion) {
        this.listaDiscusionSeccion = listaDiscusionSeccion;
    }

    public DiscusionSeccion getSelectedDiscusionSeccion() {
        return selectedDiscusionSeccion;
    }

    public void setSelectedDiscusionSeccion(DiscusionSeccion selectedDiscusionSeccion) {
        this.selectedDiscusionSeccion = selectedDiscusionSeccion;
    }

    public String getTituloDiscusion() {
        return tituloDiscusion;
    }

    public void setTituloDiscusion(String tituloDiscusion) {
        this.tituloDiscusion = tituloDiscusion;
    }

    public BigDecimal getTipoDiscusion() {
        return tipoDiscusion;
    }

    public void setTipoDiscusion(BigDecimal tipoDiscusion) {
        this.tipoDiscusion = tipoDiscusion;
    }

    public String getDiscusionHtml() {
        return discusionHtml;
    }

    public void setDiscusionHtml(String discusionHtml) {
        this.discusionHtml = discusionHtml;
    }

    public String getDiscusionPlain() {
        return discusionPlain;
    }

    public void setDiscusionPlain(String discusionPlain) {
        this.discusionPlain = discusionPlain;
    }

    public List<Historial> getListaHistorial() {
        return listaHistorial;
    }

    public void setListaHistorial(List<Historial> listaHistorial) {
        this.listaHistorial = listaHistorial;
    }

    public List<Historial> getFilteredListaHistorial() {
        return filteredListaHistorial;
    }

    public void setFilteredListaHistorial(List<Historial> filteredListaHistorial) {
        this.filteredListaHistorial = filteredListaHistorial;
    }

    public List<Historial> getSelectedHistoriales() {
        return selectedHistoriales;
    }

    public void setSelectedHistoriales(List<Historial> selectedHistoriales) {
        this.selectedHistoriales = selectedHistoriales;
    }

    public Historial getSelectedHistorialLeft() {
        return selectedHistorialLeft;
    }

    public void setSelectedHistorialLeft(Historial selectedHistorialLeft) {
        this.selectedHistorialLeft = selectedHistorialLeft;
    }

    public Historial getSelectedHistorialRight() {
        return selectedHistorialRight;
    }

    public void setSelectedHistorialRight(Historial selectedHistorialRight) {
        this.selectedHistorialRight = selectedHistorialRight;
    }

    public List<Calificacion> getListaCalificacion() {
        return listaCalificacion;
    }

    public void setListaCalificacion(List<Calificacion> listaCalificacion) {
        this.listaCalificacion = listaCalificacion;
    }

    public Calificacion getSelectedCalificacion() {
        return selectedCalificacion;
    }

    public void setSelectedCalificacion(Calificacion selectedCalificacion) {
        this.selectedCalificacion = selectedCalificacion;
    }

    public BigDecimal getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(BigDecimal calificacion) {
        this.calificacion = calificacion;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    /**
     * @return the selectedSwitch
     */
    public String getSelectedSwitch() {
        return selectedSwitch;
    }

    /**
     * @param selectedSwitch the selectedSwitch to set
     */
    public void setSelectedSwitch(String selectedSwitch) {
        this.selectedSwitch = selectedSwitch;
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

    public String getTipoContenido() {
        return tipoContenido;
    }

    public void setTipoContenido(String tipoContenido) {
        this.tipoContenido = tipoContenido;
    }

    public String getTipoDocumentos() {
        return tipoDocumentos;
    }

    public void setTipoDocumentos(String tipoDocumentos) {
        this.tipoDocumentos = tipoDocumentos;
    }

    public String getTipoVideos() {
        return tipoVideos;
    }

    public void setTipoVideos(String tipoVideos) {
        this.tipoVideos = tipoVideos;
    }

    public String getTipoAudios() {
        return tipoAudios;
    }

    public void setTipoAudios(String tipoAudios) {
        this.tipoAudios = tipoAudios;
    }

    public String getTipoImagenes() {
        return tipoImagenes;
    }

    public void setTipoImagenes(String tipoImagenes) {
        this.tipoImagenes = tipoImagenes;
    }

    public String getTipoArchivos() {
        return tipoArchivos;
    }

    public void setTipoArchivos(String tipoArchivos) {
        this.tipoArchivos = tipoArchivos;
    }

    public String getTipoLinks() {
        return tipoLinks;
    }

    public void setTipoLinks(String tipoLinks) {
        this.tipoLinks = tipoLinks;
    }

    public String getTipoOtros() {
        return tipoOtros;
    }

    public void setTipoOtros(String tipoOtros) {
        this.tipoOtros = tipoOtros;
    }

    @PostConstruct
    public void init() {
        try {
            ContenidoService service = (ContenidoService) ServiceFinder.findBean("ContenidoService");
            this.setListaContenido(service.getContenidos());
            this.setTipoDocumentos(StringUtils.EMPTY);
            this.setTipoVideos(StringUtils.EMPTY);
            this.setTipoAudios(StringUtils.EMPTY);
            this.setTipoImagenes(StringUtils.EMPTY);
            this.setTipoArchivos(StringUtils.EMPTY);
            this.setTipoLinks(StringUtils.EMPTY);
            this.setTipoOtros(StringUtils.EMPTY);
            this.setListaArchivos(new ArrayList<ArchivoConocimiento>());
            this.setListaSourceVinculos(new ArrayList<Consulta>());
            this.setListaTargetVinculos(new ArrayList<Consulta>());
            this.setPickList(new DualListModel<>(this.getListaSourceVinculos(), this.getListaTargetVinculos()));
            ListaSessionMB listaSessionMB = (ListaSessionMB) JSFUtils.getSessionAttribute("listaSessionMB");
            for(SelectItem s : listaSessionMB.getListaTipoDocumentosActivos()) {
                this.setTipoDocumentos(this.getTipoDocumentos().concat(StringUtils.capitalize(StringUtils.lowerCase(s.getLabel())).concat(", ")));
            }
            for(SelectItem s : listaSessionMB.getListaTipoVideosActivos()) {
                this.setTipoVideos(this.getTipoVideos().concat(StringUtils.capitalize(StringUtils.lowerCase(s.getLabel())).concat(", ")));
            }
            for(SelectItem s : listaSessionMB.getListaTipoAudiosActivos()) {
                this.setTipoAudios(this.getTipoAudios().concat(StringUtils.capitalize(StringUtils.lowerCase(s.getLabel())).concat(", ")));
            }
            for(SelectItem s : listaSessionMB.getListaTipoImagenesActivas()) {
                this.setTipoImagenes(this.getTipoImagenes().concat(StringUtils.capitalize(StringUtils.lowerCase(s.getLabel())).concat(", ")));
            }
            for(SelectItem s : listaSessionMB.getListaTipoArchivosTextoActivos()) {
                this.setTipoArchivos(this.getTipoArchivos().concat(StringUtils.capitalize(StringUtils.lowerCase(s.getLabel())).concat(", ")));
            }
            for(SelectItem s : listaSessionMB.getListaTipoLinksActivos()) {
                this.setTipoLinks(this.getTipoLinks().concat(StringUtils.capitalize(StringUtils.lowerCase(s.getLabel())).concat(", ")));
            }
            for(SelectItem s : listaSessionMB.getListaTipoOtrosArchivosActivos()) {
                this.setTipoOtros(this.getTipoOtros().concat(StringUtils.capitalize(StringUtils.lowerCase(s.getLabel())).concat(", ")));
            }
            this.setTipoDocumentos(this.getTipoDocumentos().substring(0, this.getTipoDocumentos().lastIndexOf(",")));
            this.setTipoVideos(this.getTipoVideos().substring(0, this.getTipoVideos().lastIndexOf(",")));
            this.setTipoAudios(this.getTipoAudios().substring(0, this.getTipoAudios().lastIndexOf(",")));
            this.setTipoImagenes(this.getTipoImagenes().substring(0, this.getTipoImagenes().lastIndexOf(",")));
            this.setTipoArchivos(this.getTipoArchivos().substring(0, this.getTipoArchivos().lastIndexOf(",")));
            this.setTipoLinks(this.getTipoLinks().substring(0, this.getTipoLinks().lastIndexOf(",")));
            this.setTipoOtros(this.getTipoOtros().substring(0, this.getTipoOtros().lastIndexOf(",")));
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
    }

    public void clearAll() {
        try {
            this.setSelectedCategoria(null);
            this.setSelectedContenido(null);
            this.setTitulo(StringUtils.EMPTY);
            this.setDescripcion(StringUtils.EMPTY);
            this.setContenidoHtml(StringUtils.EMPTY);
            this.setDiscusionPlain(StringUtils.EMPTY);
            this.setListaSourceVinculos(new ArrayList());
            this.setListaTargetVinculos(new ArrayList());
            this.setListaTargetVinculosBL(new ArrayList());
            this.setListaTargetVinculosBP(new ArrayList());
            this.setListaTargetVinculosCT(new ArrayList());
            this.setListaTargetVinculosOM(new ArrayList());
            this.setListaTargetVinculosPR(new ArrayList());
            this.setListaTargetVinculosWK(new ArrayList());
            this.setChkDestacado(false);
            this.setListaArchivos(new ArrayList());
            this.setListaDestacados(new ArrayList<Consulta>());
            this.setSelectedDestacado(null);
            this.setPickList(new DualListModel<Consulta>(this.getListaSourceVinculos(), this.getListaTargetVinculos()));
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

    public void clearDiscusion() {
        try {
            this.setSelectedDiscusionSeccion(null);
            this.setTituloDiscusion(StringUtils.EMPTY);
            this.setTipoDiscusion(null);
            this.setDiscusionHtml(StringUtils.EMPTY);
            this.setDiscusionPlain(StringUtils.EMPTY);
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

    public void clearCalificacion() {
        try {
            this.setSelectedCalificacion(null);
            this.setComentario(StringUtils.EMPTY);
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
            ResourceBundle bundle = ResourceBundle.getBundle(Parameters.getParameters());
            String fileName = JSFUtils.getRequestParameter("fileName");
            FileInputStream fis = new FileInputStream(new File(fileName));
            return new DefaultStreamedContent(fis, "application/pdf");
        }
    }
    
    public void toUploadFile(ActionEvent event) {
        try {
            if (event != null) {
                this.setUploadFile(null);
                this.setTipoContenido(StringUtils.EMPTY);
            }
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
                }
            }
        } catch (Exception e) {
            log.error(e.getMessage());
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
                
                ResourceBundle bundle = ResourceBundle.getBundle(Parameters.getMessages());
                String tipoDocumento = bundle.getString("tipoDocumento");
                String tipoVideo = bundle.getString("tipoVideo");
                String tipoAudio = bundle.getString("tipoAudio");
                String tipoImagen = bundle.getString("tipoImagen");
                String tipoArchivo = bundle.getString("tipoArchivo");
                String tipoLink = bundle.getString("tipoLink");
                String tipoOtro = bundle.getString("tipoOtro");
                
                String temppath = bundle.getString("temppath");
                String filename = this.getUploadFile().getFileName();
                String contentType = this.getUploadFile().getContentType();
                
                if(this.getTipoContenido().equals(tipoDocumento)) {
                    
                }
                
//                if(this.getTipoContenido().equals(tipoVideo)){
//                    String ffmpeg = bundle.getString("ffmpeg");
//                    String filenameFLV = filename.substring(0, filename.lastIndexOf(".")).concat(".flv");
//                    FLVConverter FLVConverter = new FLVConverter(ffmpeg);
//                    FLVConverter.convert(temppath + filename, temppath + filenameFLV, 420, 315, 5);
//                    filename = filenameFLV;
//                    contentType = bundle.getString("contentTypeFlash");
//                    inputStream = new FileInputStream(new File(temppath + filenameFLV));
//                }
                
                ArchivoConocimiento archivoconocimiento = new ArchivoConocimiento();
                archivoconocimiento.setUploadedFile(this.getUploadFile());
                archivoconocimiento.setVnombre(filename);
                archivoconocimiento.setNtipoarchivo(BigDecimal.valueOf(Long.parseLong(this.getTipoContenido())));
                archivoconocimiento.setVcontenttype(contentType);
                archivoconocimiento.setVruta(temppath + filename);
                archivoconocimiento.setFile(new File(archivoconocimiento.getVruta()));
                archivoconocimiento.setContent(new DefaultStreamedContent(new FileInputStream(archivoconocimiento.getFile()), contentType, filename));
                this.getListaArchivos().add(archivoconocimiento);
            }
        } catch(NumberFormatException | FileNotFoundException e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
    }
    
    public void toDeleteOutstanding(ActionEvent event) {
        try {
            if(event != null) {
                int index = Integer.parseInt((String) JSFUtils.getRequestParameter("index"));
                this.setSelectedDestacado(this.getListaDestacados().get(index));
            }
        } catch(Exception e) {
            e.getMessage();
        }
    }
    
    public void deleteOutstanding(ActionEvent event) {
        try {
            if(event != null) {
                ConocimientoService service = (ConocimientoService) ServiceFinder.findBean("ConocimientoService");
                Conocimiento conocimiento = service.getConocimientoById(this.getSelectedDestacado().getIdconocimiento());
                if(conocimiento != null) {
                    LoginMB loginMB = (LoginMB) JSFUtils.getSessionAttribute("loginMB");
                    User user = loginMB.getUser();
                    conocimiento.setNdestacado(BigDecimal.ZERO);
                    conocimiento.setVusuariomodificacion(user.getVlogin());
                    conocimiento.setDfechamodificacion(new Date());
                    service.saveOrUpdate(conocimiento);
                    ConsultaService consultaService = (ConsultaService) ServiceFinder.findBean("ConsultaService");
                    HashMap filter = new HashMap();
                    filter.put("ntipoconocimientoid", Constante.CONTENIDO);                
                    this.setListaDestacados(consultaService.getDestacadosByTipoConocimiento(filter));
                }
            }
        } catch(Exception e) {
            e.getMessage();
        }
    }

    public String toSave() {
        try {
            this.clearAll();
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
        return "/pages/contenido/registro?faces-redirect=true";
    }

    public void save(ActionEvent event) {
        try {
            if(this.getChkDestacado()) {
                ConsultaService consultaService = (ConsultaService) ServiceFinder.findBean("ConsultaService");
                HashMap filter = new HashMap();
                filter.put("ntipoconocimientoid", Constante.CONTENIDO);
                BigDecimal cant = consultaService.countDestacadosByTipoConocimiento(filter);
                if(cant.intValue() >= 10) {
                    this.setListaDestacados(consultaService.getDestacadosByTipoConocimiento(filter));
                    RequestContext.getCurrentInstance().execute("PF('destDialog').show();");
                    return;
                }
            }
            /* Validando si exiten vínculos de bases legales derogadas */
            int contador = 0;
            if(CollectionUtils.isNotEmpty(this.getListaTargetVinculosBL())) {
                for(Consulta c : this.getListaTargetVinculosBL()) {
                    if(c.getIdEstado().toString().equals(Constante.ESTADO_BASELEGAL_DEROGADA)) {
                        contador++;
                    }
                }
            }
            
            if (CollectionUtils.isEmpty(this.getListaContenido())) {
                this.setListaContenido(Collections.EMPTY_LIST);
            }

            LoginMB loginMB = (LoginMB) JSFUtils.getSessionAttribute("loginMB");
            User user = loginMB.getUser();
            
            this.setContenidoPlain(Jsoup.parse(this.getContenidoHtml()).text());
            ContenidoService service = (ContenidoService) ServiceFinder.findBean("ContenidoService");
            Conocimiento conocimiento = new Conocimiento();
            conocimiento.setNconocimientoid(service.getNextPK());
            conocimiento.setNcategoriaid(this.getSelectedCategoria().getNcategoriaid());
            conocimiento.setNtipoconocimientoid(Constante.CONTENIDO);
            conocimiento.setVtitulo(this.getTitulo().trim());
            conocimiento.setNdestacado(this.getChkDestacado() ? BigDecimal.ONE : BigDecimal.ZERO);
            conocimiento.setVdescripcion(this.getDescripcion().trim());
            if (this.getContenidoPlain().length() < 400) {
                conocimiento.setVcontenido(StringUtils.capitalize(this.getContenidoPlain()));
            } else {
                conocimiento.setVcontenido(StringUtils.capitalize(this.getContenidoPlain().substring(0, 400)));
            }
            if (this.getSelectedCategoria().getNflagbp().equals(BigDecimal.ONE)) {
                conocimiento.setNsituacionid(BigDecimal.valueOf(Long.parseLong(Constante.SITUACION_POR_VERIFICAR)));
            } else {
                conocimiento.setNsituacionid(BigDecimal.valueOf(Long.parseLong(Constante.SITUACION_PUBLICADO)));
                conocimiento.setDfechapublicacion(new Date());
            }
            if(contador > 0) {
                conocimiento.setNflgvinculo(BigDecimal.ONE);
            } else {
                conocimiento.setNflgvinculo(BigDecimal.ZERO);
            }
            String np0 = this.path.concat(conocimiento.getNconocimientoid().toString()).concat("/0/");
            conocimiento.setVruta(np0);
            conocimiento.setDfechacreacion(new Date());
            conocimiento.setVusuariocreacion(user.getVlogin());
            conocimiento.setNactivo(BigDecimal.ONE);
            service.saveOrUpdate(conocimiento);

            GcmFileUtils.writeStringToFileServer(np0, "html.txt", this.getContenidoHtml());
            GcmFileUtils.writeStringToFileServer(np0, "plain.txt", this.getContenidoPlain());

            String np1 = this.path.concat(conocimiento.getNconocimientoid().toString()).concat("/1/");
            HistorialService historialService = (HistorialService) ServiceFinder.findBean("HistorialService");
            ThistorialId thistorialId = new ThistorialId();
            thistorialId.setNconocimientoid(conocimiento.getNconocimientoid());
            thistorialId.setNhistorialid(historialService.getNextPK());
            Historial historial = new Historial();
            historial.setId(thistorialId);
            historial.setNtipoconocimientoid(Constante.CONTENIDO);
            historial.setNcategoriaid(this.getSelectedCategoria().getNcategoriaid());
            historial.setVtitulo(this.getTitulo());
            historial.setVdescripcion(this.getDescripcion().trim());
            if (this.getContenidoPlain().length() < 400) {
                historial.setVcontenido(StringUtils.capitalize(this.getContenidoPlain()));
            } else {
                historial.setVcontenido(StringUtils.capitalize(this.getContenidoPlain().substring(0, 400)));
            }
            historial.setNversionactual(BigDecimal.ONE);
            historial.setNactivo(BigDecimal.ONE);
            historial.setNsituacionid(conocimiento.getNsituacionid());
            historial.setVruta(np1);
            historial.setNnumversion(BigDecimal.ONE);
            historial.setDfechacreacion(new Date());
            historial.setVusuariocreacion(user.getVlogin());
            historialService.saveOrUpdate(historial);

            GcmFileUtils.writeStringToFileServer(np1, "html.txt", this.getContenidoHtml());
            GcmFileUtils.writeStringToFileServer(np1, "plain.txt", this.getContenidoPlain());

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
                service.delete(conocimiento.getNconocimientoid());
                for (Consulta consulta : this.getListaTargetVinculos()) {
                    Vinculo vinculo = new Vinculo();
                    vinculo.setNvinculoid(vinculoService.getNextPK());
                    vinculo.setNconocimientoid(conocimiento.getNconocimientoid());
                    vinculo.setNconocimientovinc(consulta.getIdconocimiento());
                    vinculo.setNtipoconocimientovinc(consulta.getIdTipoConocimiento());
                    vinculo.setDfechacreacion(new Date());
                    vinculo.setVusuariocreacion(user.getVlogin());
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
                    vinculoHist.setVusuariocreacion(user.getVlogin());
                    vinculoHistService.saveOrUpdate(vinculoHist);
                }
            }

            ArchivoConocimientoService aservice = (ArchivoConocimientoService) ServiceFinder.findBean("ArchivoConocimientoService");
            for (ArchivoConocimiento v : this.getListaArchivos()) {
                ArchivoConocimiento archivoconocimiento = new ArchivoConocimiento();
                archivoconocimiento.setNarchivoid(aservice.getNextPK());
                archivoconocimiento.setNtipoconocimientoid(Constante.CONTENIDO);
                archivoconocimiento.setNconocimientoid(conocimiento.getNconocimientoid());
                archivoconocimiento.setVnombre(v.getVnombre());
                archivoconocimiento.setNversion(BigDecimal.ONE);
                archivoconocimiento.setVruta(path + conocimiento.getNconocimientoid().toString() + "/" + archivoconocimiento.getNversion().toString() + "/" + archivoconocimiento.getVnombre());
                archivoconocimiento.setVusuariocreacion(user.getVlogin());
                archivoconocimiento.setDfechacreacion(new Date());
                aservice.saveOrUpdate(archivoconocimiento);
                saveFile(archivoconocimiento);
            }

            Asignacion asignacion = new Asignacion();
            AsignacionService serviceasig = (AsignacionService) ServiceFinder.findBean("AsignacionService");
            asignacion.setNasignacionid(serviceasig.getNextPK());
            asignacion.setNtipoconocimientoid(Constante.CONTENIDO);
            asignacion.setNconocimientoid(conocimiento.getNconocimientoid());
            asignacion.setNestadoid(BigDecimal.valueOf(Long.parseLong("1")));
            CategoriaService categoriaService = (CategoriaService) ServiceFinder.findBean("CategoriaService");
            asignacion.setNusuarioid(categoriaService.getCategoriaById(conocimiento.getNcategoriaid()).getNmoderador());
            asignacion.setDfechaasignacion(new Date());
            asignacion.setDfechacreacion(new Date());
            serviceasig.saveOrUpdate(asignacion);

            listaContenido = service.getContenidos();
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
    }

    public void toUpdate(ActionEvent event) {

    }

    public void toCancelSecc(ActionEvent event) {
        RequestContext.getCurrentInstance().execute("PF('newseccDialog').hide();");
    }

    public void toAddLink(ActionEvent event) {
        try {
            this.setIdTipoConocimiento(null);
            this.setListaSourceVinculos(new ArrayList());
            this.setListaTargetVinculos(new ArrayList());
            this.setPickList(new DualListModel<Consulta>(this.getListaSourceVinculos(), this.getListaTargetVinculos()));
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
                    ContenidoService service = (ContenidoService) ServiceFinder.findBean("ContenidoService");
                    if (this.getSelectedContenido() != null) {
                        filters.put("nconocimientoid", this.getSelectedContenido().getNconocimientoid());
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
                    this.setPickList(new DualListModel<Consulta>(this.getListaSourceVinculos(), this.getListaTargetVinculos()));
                }
            }
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
    }

    public void saveFile(ArchivoConocimiento archivoconocimiento) {
        try {

            ResourceBundle bundle = ResourceBundle.getBundle(Parameters.getParameters());
            String id = archivoconocimiento.getNconocimientoid().toString();
            String version = archivoconocimiento.getNversion().toString();
            Integer version_ant_s = Integer.parseInt(archivoconocimiento.getNversion().toString()) - 1;
            String version_ant_i = version_ant_s.toString();
            File direc = new File(bundle.getString("path") + "ct" + '/' + id + '/' + version);
            File direct = new File(bundle.getString("path") + "ct" + '/' + id + '/' + version_ant_i +'/'+ archivoconocimiento.getVnombre());
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
        } catch (NumberFormatException e) {
            log.error(e.getMessage());
            e.printStackTrace();
        } catch (IOException e) {
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

    public String toView() {
        try {
            int index = Integer.parseInt((String) JSFUtils.getRequestParameter("index"));
            this.setSelectedContenido(this.getListaContenido().get(index));
            CategoriaService categoriaService = (CategoriaService) ServiceFinder.findBean("CategoriaService");
            this.setSelectedCategoria(categoriaService.getCategoriaById(this.getSelectedContenido().getNcategoriaid()));
            ArchivoConocimientoService archivoservice = (ArchivoConocimientoService) ServiceFinder.findBean("ArchivoConocimientoService");
            this.setListaArchivos(archivoservice.getArchivosByConocimiento(this.getSelectedContenido().getNconocimientoid()));
            this.setChkDestacado(this.getSelectedContenido().getNdestacado().equals(BigDecimal.ONE));
            ContenidoService contenidoService = (ContenidoService) ServiceFinder.findBean("ContenidoService");
            HashMap map = new HashMap();
            map.put("nconocimientoid", this.getSelectedContenido().getNconocimientoid().toString());
            map.put("flag", true);
            map.put("ntipoconocimientoid", Constante.BASELEGAL.toString());
            this.setListaTargetVinculosBL(contenidoService.getConcimientosVinculados(map));
            map.put("ntipoconocimientoid", Constante.PREGUNTAS.toString());
            this.setListaTargetVinculosPR(contenidoService.getConcimientosVinculados(map));
            map.put("ntipoconocimientoid", Constante.BUENAPRACTICA.toString());
            this.setListaTargetVinculosBP(contenidoService.getConcimientosVinculados(map));
            map.put("ntipoconocimientoid", Constante.CONTENIDO.toString());
            this.setListaTargetVinculosCT(contenidoService.getConcimientosVinculados(map));
            map.put("ntipoconocimientoid", Constante.OPORTUNIDADMEJORA.toString());
            this.setListaTargetVinculosOM(contenidoService.getConcimientosVinculados(map));
            map.put("ntipoconocimientoid", Constante.WIKI.toString());
            this.setListaTargetVinculosWK(contenidoService.getConcimientosVinculados(map));
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
        return "/pages/contenido/ver?faces-redirect=true";
    }

    public String toEdit() {
        try {
            int index = Integer.parseInt((String) JSFUtils.getRequestParameter("index"));
            this.setSelectedContenido(this.getListaContenido().get(index));
            CategoriaService categoriaService = (CategoriaService) ServiceFinder.findBean("CategoriaService");
            this.setSelectedCategoria(categoriaService.getCategoriaById(this.getSelectedContenido().getNcategoriaid()));
            this.setContenidoHtml(GcmFileUtils.readStringFromFileServer(this.getSelectedContenido().getVruta(), "html.txt"));
            ArchivoConocimientoService archivoservice = (ArchivoConocimientoService) ServiceFinder.findBean("ArchivoConocimientoService");
            this.setListaArchivos(archivoservice.getArchivosByConocimiento(this.getSelectedContenido().getNconocimientoid()));
            this.setChkDestacado(this.getSelectedContenido().getNdestacado().equals(BigDecimal.ONE));
            ContenidoService contenidoService = (ContenidoService) ServiceFinder.findBean("ContenidoService");
            HashMap map = new HashMap();
            map.put("nconocimientoid", this.getSelectedContenido().getNconocimientoid().toString());
            map.put("flag", true);
            map.put("ntipoconocimientoid", Constante.BASELEGAL.toString());
            this.setListaTargetVinculosBL(contenidoService.getConcimientosVinculados(map));
            map.put("ntipoconocimientoid", Constante.PREGUNTAS.toString());
            this.setListaTargetVinculosPR(contenidoService.getConcimientosVinculados(map));
            map.put("ntipoconocimientoid", Constante.BUENAPRACTICA.toString());
            this.setListaTargetVinculosBP(contenidoService.getConcimientosVinculados(map));
            map.put("ntipoconocimientoid", Constante.CONTENIDO.toString());
            this.setListaTargetVinculosCT(contenidoService.getConcimientosVinculados(map));
            map.put("ntipoconocimientoid", Constante.OPORTUNIDADMEJORA.toString());
            this.setListaTargetVinculosOM(contenidoService.getConcimientosVinculados(map));
            map.put("ntipoconocimientoid", Constante.WIKI.toString());
            this.setListaTargetVinculosWK(contenidoService.getConcimientosVinculados(map));
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
        return "/pages/contenido/editar?faces-redirect=true";
    }

    public void edit(ActionEvent event) {
        try {
            if(this.getSelectedContenido().getNdestacado().equals(BigDecimal.ZERO) && this.getChkDestacado()) {
                ConsultaService consultaService = (ConsultaService) ServiceFinder.findBean("ConsultaService");
                HashMap filter = new HashMap();
                filter.put("ntipoconocimientoid", Constante.CONTENIDO);
                BigDecimal cant = consultaService.countDestacadosByTipoConocimiento(filter);
                if(cant.intValue() >= 10) {
                    this.setListaDestacados(consultaService.getDestacadosByTipoConocimiento(filter));
                    RequestContext.getCurrentInstance().execute("PF('destDialog').show();");
                    return;
                }
            }
            /* Validando si exiten vínculos de bases legales derogadas */
            int contador = 0;
            if(CollectionUtils.isNotEmpty(this.getListaTargetVinculosBL())) {
                for(Consulta c : this.getListaTargetVinculosBL()) {
                    if(c.getIdEstado().toString().equals(Constante.ESTADO_BASELEGAL_DEROGADA)) {
                        contador++;
                    }
                }
            }
            
            if (CollectionUtils.isEmpty(this.getListaContenido())) {
                this.setListaContenido(Collections.EMPTY_LIST);
            }

            LoginMB loginMB = (LoginMB) JSFUtils.getSessionAttribute("loginMB");
            User user = loginMB.getUser();

            this.setContenidoPlain(Jsoup.parse(this.getContenidoHtml()).text());
            ContenidoService service = (ContenidoService) ServiceFinder.findBean("ContenidoService");            
            this.getSelectedContenido().setVtitulo(this.getSelectedContenido().getVtitulo().trim());
            this.getSelectedContenido().setVdescripcion(this.getSelectedContenido().getVdescripcion().trim());
            if (this.getContenidoPlain().length() < 400) {
                this.getSelectedContenido().setVcontenido(StringUtils.capitalize(this.getContenidoPlain()));
            } else {
                this.getSelectedContenido().setVcontenido(StringUtils.capitalize(this.getContenidoPlain().substring(0, 400)));
            }
            if(contador > 0) {
                this.getSelectedContenido().setNflgvinculo(BigDecimal.ONE);
            } else {
                this.getSelectedContenido().setNflgvinculo(BigDecimal.ZERO);
            }
            this.getSelectedContenido().setNcategoriaid(this.getSelectedCategoria().getNcategoriaid());
            this.getSelectedContenido().setNdestacado(this.getChkDestacado() ? BigDecimal.ONE : BigDecimal.ZERO);
            this.getSelectedContenido().setDfechamodificacion(new Date());
            this.getSelectedContenido().setVusuariomodificacion(user.getVlogin());
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
            historial.setVdescripcion(this.getSelectedContenido().getVdescripcion());
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
            historial.setVusuariocreacion(user.getVlogin());
            historialService.saveOrUpdate(historial);

            GcmFileUtils.writeStringToFileServer(url, "html.txt", this.getContenidoHtml());
            GcmFileUtils.writeStringToFileServer(url, "plain.txt", this.getContenidoPlain());

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
                service.delete(this.getSelectedContenido().getNconocimientoid());
                for (Consulta consulta : this.getListaTargetVinculos()) {
                    Vinculo vinculo = new Vinculo();
                    vinculo.setNvinculoid(vinculoService.getNextPK());
                    vinculo.setNconocimientoid(this.getSelectedContenido().getNconocimientoid());
                    vinculo.setNconocimientovinc(consulta.getIdconocimiento());
                    vinculo.setNtipoconocimientovinc(consulta.getIdTipoConocimiento());
                    vinculo.setDfechacreacion(new Date());
                    vinculo.setVusuariocreacion(user.getVlogin());
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
                    vinculoHist.setVusuariocreacion(user.getVlogin());
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
                archivoconocimiento.setVruta(path + this.getSelectedContenido().getNconocimientoid().toString() + "/" + archivoconocimiento.getNversion().toString() + "/" + archivoconocimiento.getVnombre());
                archivoconocimiento.setVusuariocreacion(user.getVlogin());
                archivoconocimiento.setDfechacreacion(new Date());
                aservice.saveOrUpdate(archivoconocimiento);
                saveFile(archivoconocimiento);

            }

            listaContenido = service.getContenidos();

        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
    }

    public String toPost() {
        try {
            int index = Integer.parseInt((String) JSFUtils.getRequestParameter("index"));
            this.setSelectedContenido(this.getListaContenido().get(index));
            CategoriaService categoriaService = (CategoriaService) ServiceFinder.findBean("CategoriaService");
            this.setSelectedCategoria(categoriaService.getCategoriaById(this.getSelectedContenido().getNcategoriaid()));
            this.setContenidoHtml(GcmFileUtils.readStringFromFileServer(this.getSelectedContenido().getVruta(), "html.txt"));
            ArchivoConocimientoService archivoservice = (ArchivoConocimientoService) ServiceFinder.findBean("ArchivoConocimientoService");
            this.setListaArchivos(archivoservice.getArchivosByConocimiento(this.getSelectedContenido().getNconocimientoid()));
            this.setChkDestacado(this.getSelectedContenido().getNdestacado().equals(BigDecimal.ONE));
            ContenidoService contenidoService = (ContenidoService) ServiceFinder.findBean("ContenidoService");
            HashMap map = new HashMap();
            map.put("nconocimientoid", this.getSelectedContenido().getNconocimientoid().toString());
            map.put("flag", true);
            map.put("ntipoconocimientoid", Constante.BASELEGAL.toString());
            this.setListaTargetVinculosBL(contenidoService.getConcimientosVinculados(map));
            map.put("ntipoconocimientoid", Constante.PREGUNTAS.toString());
            this.setListaTargetVinculosPR(contenidoService.getConcimientosVinculados(map));
            map.put("ntipoconocimientoid", Constante.BUENAPRACTICA.toString());
            this.setListaTargetVinculosBP(contenidoService.getConcimientosVinculados(map));
            map.put("ntipoconocimientoid", Constante.CONTENIDO.toString());
            this.setListaTargetVinculosCT(contenidoService.getConcimientosVinculados(map));
            map.put("ntipoconocimientoid", Constante.OPORTUNIDADMEJORA.toString());
            this.setListaTargetVinculosOM(contenidoService.getConcimientosVinculados(map));
            map.put("ntipoconocimientoid", Constante.WIKI.toString());
            this.setListaTargetVinculosWK(contenidoService.getConcimientosVinculados(map));
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
        return "/pages/contenido/publicar?faces-redirect=true";
    }

    public void post(ActionEvent event) {
        try {
            if(this.getSelectedContenido().getNdestacado().equals(BigDecimal.ZERO) && this.getChkDestacado()) {
                ConsultaService consultaService = (ConsultaService) ServiceFinder.findBean("ConsultaService");
                HashMap filter = new HashMap();
                filter.put("ntipoconocimientoid", Constante.CONTENIDO);
                BigDecimal cant = consultaService.countDestacadosByTipoConocimiento(filter);
                if(cant.intValue() >= 10) {
                    this.setListaDestacados(consultaService.getDestacadosByTipoConocimiento(filter));
                    RequestContext.getCurrentInstance().execute("PF('destDialog').show();");
                    return;
                }
            }
            /* Validando si exiten vínculos de bases legales derogadas */
            int contador = 0;
            if(CollectionUtils.isNotEmpty(this.getListaTargetVinculosBL())) {
                for(Consulta c : this.getListaTargetVinculosBL()) {
                    if(c.getIdEstado().toString().equals(Constante.ESTADO_BASELEGAL_DEROGADA)) {
                        contador++;
                    }
                }
            }
            if (CollectionUtils.isEmpty(this.getListaContenido())) {
                this.setListaContenido(Collections.EMPTY_LIST);
            }

            LoginMB loginMB = (LoginMB) JSFUtils.getSessionAttribute("loginMB");
            User user = loginMB.getUser();

            ContenidoService service = (ContenidoService) ServiceFinder.findBean("ContenidoService");
            this.setContenidoPlain(Jsoup.parse(this.getContenidoHtml()).text());
            this.getSelectedContenido().setNcategoriaid(this.getSelectedCategoria().getNcategoriaid());
            this.getSelectedContenido().setVtitulo(this.getSelectedContenido().getVtitulo().trim());
            this.getSelectedContenido().setVdescripcion(this.getSelectedContenido().getVdescripcion().trim());
            if (this.getContenidoPlain().length() < 400) {
                this.getSelectedContenido().setVcontenido(StringUtils.capitalize(this.getContenidoPlain()));
            } else {
                this.getSelectedContenido().setVcontenido(StringUtils.capitalize(this.getContenidoPlain().substring(0, 400)));
            }
            if(contador > 0) {
                this.getSelectedContenido().setNflgvinculo(BigDecimal.ONE);
            } else {
                this.getSelectedContenido().setNflgvinculo(BigDecimal.ZERO);
            }
            this.getSelectedContenido().setNdestacado(this.getChkDestacado() ? BigDecimal.ONE : BigDecimal.ZERO);
            this.getSelectedContenido().setDfechamodificacion(new Date());
            this.getSelectedContenido().setDfechapublicacion(new Date());
            this.getSelectedContenido().setNsituacionid(BigDecimal.valueOf(Long.parseLong(Constante.SITUACION_PUBLICADO)));
            this.getSelectedContenido().setVusuariomodificacion(user.getVlogin());
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
            historial.setVdescripcion(this.getSelectedContenido().getVdescripcion());
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
            historial.setVusuariocreacion(user.getVlogin());
            historialService.saveOrUpdate(historial);

            GcmFileUtils.writeStringToFileServer(url, "html.txt", this.getContenidoHtml());
            GcmFileUtils.writeStringToFileServer(url, "plain.txt", this.getContenidoPlain());
            
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
                service.delete(this.getSelectedContenido().getNconocimientoid());
                for (Consulta consulta : this.getListaTargetVinculos()) {
                    Vinculo vinculo = new Vinculo();
                    vinculo.setNvinculoid(vinculoService.getNextPK());
                    vinculo.setNconocimientoid(this.getSelectedContenido().getNconocimientoid());
                    vinculo.setNconocimientovinc(consulta.getIdconocimiento());
                    vinculo.setNtipoconocimientovinc(consulta.getIdTipoConocimiento());
                    vinculo.setDfechacreacion(new Date());
                    vinculo.setVusuariocreacion(user.getVlogin());
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
                    vinculoHist.setVusuariocreacion(user.getVlogin());
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
                archivoconocimiento.setVruta(path + this.getSelectedContenido().getNconocimientoid().toString() + "/" + archivoconocimiento.getNversion().toString() + "/" + archivoconocimiento.getVnombre());
                archivoconocimiento.setVusuariocreacion(user.getVlogin());
                archivoconocimiento.setDfechacreacion(new Date());
                aservice.saveOrUpdate(archivoconocimiento);
                saveFile(archivoconocimiento);

            }

            listaContenido = service.getContenidos();

        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
    }

    public void activar(ActionEvent event) {
        try {
            if (event != null) {
                if (this.getSelectedContenido() != null) {
                    LoginMB loginMB = (LoginMB) JSFUtils.getSessionAttribute("loginMB");
                    User user = loginMB.getUser();
                    ConocimientoService service = (ConocimientoService) ServiceFinder.findBean("ConocimientoService");
                    this.getSelectedContenido().setNactivo(BigDecimal.ONE);
                    this.getSelectedContenido().setDfechamodificacion(new Date());
                    this.getSelectedContenido().setVusuariomodificacion(user.getVlogin());
                    service.saveOrUpdate(this.getSelectedContenido());
                    this.setListaContenido(service.getConocimientosByType(Constante.CONTENIDO));
                } else {
                    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, Constante.SEVERETY_ALERTA, "Debe seleccionar el contenido a activar.");
                    FacesContext.getCurrentInstance().addMessage(null, message);
                }
            }
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
    }

    public void desactivar(ActionEvent event) {
        try {
            if (event != null) {
                if (this.getSelectedContenido() != null) {
                    LoginMB loginMB = (LoginMB) JSFUtils.getSessionAttribute("loginMB");
                    User user = loginMB.getUser();
                    ConocimientoService service = (ConocimientoService) ServiceFinder.findBean("ConocimientoService");
                    this.getSelectedContenido().setNactivo(BigDecimal.ZERO);
                    this.getSelectedContenido().setNdestacado(BigDecimal.ZERO);
                    this.getSelectedContenido().setDfechamodificacion(new Date());
                    this.getSelectedContenido().setVusuariomodificacion(user.getVlogin());
                    service.saveOrUpdate(this.getSelectedContenido());
                    this.setListaContenido(service.getConocimientosByType(Constante.CONTENIDO));
                } else {
                    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, Constante.SEVERETY_ALERTA, "Debe seleccionar el contenido a desactivar.");
                    FacesContext.getCurrentInstance().addMessage(null, message);
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

                this.setSelectedContenido(this.getListaContenido().get(index));

            }
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
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

    public void toAddDiscusion(ActionEvent event) {
        try {
            this.clearDiscusion();
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
    }

    public void addDiscusion(ActionEvent event) {
        try {
            if (this.getTipoDiscusion() == null) {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR.", "Seleccione el tipo de discusión a agregar.");
                FacesContext.getCurrentInstance().addMessage(null, message);
                return;
            }
            if (StringUtils.isBlank(this.getTituloDiscusion())) {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR.", "Ingrese el título de la discusión a agregar.");
                FacesContext.getCurrentInstance().addMessage(null, message);
                return;
            }
            if (StringUtils.isBlank(this.getDiscusionHtml())) {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR.", "Ingrese el detalle de la discusión a agregar.");
                FacesContext.getCurrentInstance().addMessage(null, message);
                return;
            }
            DiscusionSeccion discusionSeccion = new DiscusionSeccion();
            discusionSeccion.setNtipodiscusion(this.getTipoDiscusion());
            discusionSeccion.setVtitulo(StringUtils.upperCase(this.getTituloDiscusion().trim()));
            discusionSeccion.setDiscusionHtml(this.getDiscusionHtml());
            discusionSeccion.setDiscusionPlain(Jsoup.parse(discusionSeccion.getDiscusionHtml()).text());
            discusionSeccion.setDfechacreacion(new Date());
            if (this.getListaDiscusionSeccion() == null) {
                this.setListaDiscusionSeccion(new ArrayList());
            }
            this.getListaDiscusionSeccion().add(discusionSeccion);
            RequestContext.getCurrentInstance().execute("PF('disDialog').hide();");
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
    }

    public void toEditDiscusion(ActionEvent event) {
        try {
            this.clearDiscusion();
            int index = Integer.parseInt((String) JSFUtils.getRequestParameter("index"));
            this.setSelectedDiscusionSeccion(this.getListaDiscusionSeccion().get(index));
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
    }

    public void editDiscusion(ActionEvent event) {
        try {
            if (this.getSelectedDiscusionSeccion().getNtipodiscusion() == null) {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR.", "Seleccione el tipo de discusión a editar.");
                FacesContext.getCurrentInstance().addMessage(null, message);
                return;
            }
            if (StringUtils.isBlank(this.getSelectedDiscusionSeccion().getVtitulo())) {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR.", "Ingrese el título de la discusión a editar.");
                FacesContext.getCurrentInstance().addMessage(null, message);
                return;
            }
            if (StringUtils.isBlank(this.getSelectedDiscusionSeccion().getDiscusionHtml())) {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR.", "Ingrese el detalle de la discusión a editar.");
                FacesContext.getCurrentInstance().addMessage(null, message);
                return;
            }
            this.getSelectedDiscusionSeccion().setVtitulo(StringUtils.upperCase(this.getSelectedDiscusionSeccion().getVtitulo().trim()));
            RequestContext.getCurrentInstance().execute("PF('edisDialog').hide();");
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
    }

    public void saveOrUpdateDiscusion(ActionEvent event) {
        Discusion discusion = null;
        DiscusionHist discusionHist;
        try {
            LoginMB loginMB = (LoginMB) JSFUtils.getSessionAttribute("loginMB");
            User user = loginMB.getUser();
            DiscusionService discusionService = (DiscusionService) ServiceFinder.findBean("DiscusionService");
            DiscusionHistService discusionHistService = (DiscusionHistService) ServiceFinder.findBean("DiscusionHistService");
            if (this.getSelectedDiscusion() == null) {
                discusion = new Discusion();
                discusion.setNdiscusionid(discusionService.getNextPK());
                discusion.setNconocimientoid(this.getSelectedContenido().getNconocimientoid());
                discusion.setDfechacreacion(new Date());
                discusion.setVusuariocreacion(user.getVlogin());
                discusionService.saveOrUpdate(discusion);

                discusionHist = new DiscusionHist();
                discusionHist.setNnumversion(BigDecimal.ONE);
            } else {
                discusionHist = discusionHistService.getDiscusionHistByConocimiento(this.getSelectedContenido().getNconocimientoid());
                int version = discusionHist.getNnumversion().intValue() + 1;
                discusionHist.setNnumversion(BigDecimal.valueOf(version));
            }
            discusionHist.setNdiscusionhid(discusionHistService.getNextPK());
            discusionHist.setNconocimientoid(this.getSelectedContenido().getNconocimientoid());
            discusionHist.setDfechacreacion(new Date());
            discusionHist.setVusuariocreacion(user.getVlogin());
            discusionHistService.saveOrUpdate(discusionHist);

            if (CollectionUtils.isNotEmpty(this.getListaDiscusionSeccion())) {
                String url0 = this.path.concat(this.getSelectedContenido().getNconocimientoid().toString()).concat("/0/d/").concat(BigDecimal.ZERO.toString()).concat("/s");
                String url1 = this.path.concat(this.getSelectedContenido().getNconocimientoid().toString()).concat("/0/d/").concat(discusionHist.getNnumversion().toString()).concat("/s");
                DiscusionSeccionService discusionSeccionService = (DiscusionSeccionService) ServiceFinder.findBean("DiscusionSeccionService");
                DiscusionSeccionHistService discusionSeccionHistService = (DiscusionSeccionHistService) ServiceFinder.findBean("DiscusionSeccionHistService");
                for (DiscusionSeccion seccion : this.getListaDiscusionSeccion()) {
                    if (seccion.getNdiscusionseccionid() == null) {
                        seccion.setNdiscusionseccionid(discusionSeccionService.getNextPK());
                        seccion.setNdiscusionid(discusion.getNdiscusionid());
                        seccion.setDfechacreacion(new Date());
                        seccion.setVusuariocreacion(user.getVlogin());
                    } else {
                        seccion.setDfechamodificacion(new Date());
                        seccion.setVusuariomodificacion(user.getVlogin());
                    }
                    String ruta0 = url0.concat(seccion.getNdiscusionseccionid().toString()).concat("/");
                    seccion.setNtipodiscusion(seccion.getNtipodiscusion());
                    seccion.setVtitulo(StringUtils.upperCase(seccion.getVtitulo()));
                    seccion.setVruta(ruta0);
                    seccion.setDiscusionPlain(Jsoup.parse(seccion.getDiscusionHtml()).text());
                    discusionSeccionService.saveOrUpdate(seccion);

                    GcmFileUtils.writeStringToFileServer(ruta0, "html.txt", seccion.getDiscusionHtml());
                    GcmFileUtils.writeStringToFileServer(ruta0, "plain.txt", seccion.getDiscusionPlain());

                    String ruta1 = url1.concat(seccion.getNdiscusionseccionid().toString()).concat("/");
                    DiscusionSeccionHist seccionHist = new DiscusionSeccionHist();
                    seccionHist.setNdiscusionseccionhid(discusionSeccionHistService.getNextPK());
                    seccionHist.setNdiscusionhid(discusionHist.getNdiscusionhid());
                    seccionHist.setNtipodiscusion(seccion.getNtipodiscusion());
                    seccionHist.setVtitulo(StringUtils.upperCase(seccion.getVtitulo()));
                    seccionHist.setVruta(ruta1);
                    seccionHist.setDfechacreacion(new Date());
                    seccionHist.setVusuariocreacion(user.getVlogin());
                    discusionSeccionHistService.saveOrUpdate(seccionHist);

                    GcmFileUtils.writeStringToFileServer(ruta1, "html.txt", seccion.getDiscusionHtml());
                    GcmFileUtils.writeStringToFileServer(ruta1, "plain.txt", seccion.getDiscusionPlain());
                }
            }
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage("Información", "Discusión registrada con éxito."));
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
    }

    public void toCompare(ActionEvent event) {
        try {
            if (event != null) {
                if (CollectionUtils.isEmpty(this.getSelectedHistoriales())) {
                    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR.", "Debe seleccionar dos versiones para comparar.");
                    FacesContext.getCurrentInstance().addMessage(null, message);
                    return;
                }
                if (CollectionUtils.isNotEmpty(this.getSelectedHistoriales()) && this.getSelectedHistoriales().size() != 2) {
                    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR.", "Sólo se puede comparar dos versiones a la vez.");
                    FacesContext.getCurrentInstance().addMessage(null, message);
                    return;
                }
                SeccionHistService seccionHistService = (SeccionHistService) ServiceFinder.findBean("SeccionHistService");
                VinculoHistService vinculoHistService = (VinculoHistService) ServiceFinder.findBean("VinculoHistService");
                for (Historial historial : this.getSelectedHistoriales()) {
                    historial.setDescripcionHtml(GcmFileUtils.readStringFromFileServer(historial.getVruta(), "html.txt"));
                    historial.setListaSeccionHist(seccionHistService.getSeccionHistsByHistorial(historial.getId().getNhistorialid()));
                    if (CollectionUtils.isNotEmpty(historial.getListaSeccionHist())) {
                        for (SeccionHist seccionHist : historial.getListaSeccionHist()) {
                            seccionHist.setDetalleHtml(GcmFileUtils.readStringFromFileServer(seccionHist.getVruta(), "html.txt"));
                        }
                    }
                }
                this.setSelectedHistorialLeft(this.getSelectedHistoriales().get(0));
                this.setSelectedHistorialRight(this.getSelectedHistoriales().get(1));
                FacesContext.getCurrentInstance().getExternalContext().redirect("/gescon/pages/contenido/historialCompare.xhtml");
            }
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
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
            if (StringUtils.isBlank(this.getComentario())) {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR.", "Ingrese un comentario al wiki.");
                FacesContext.getCurrentInstance().addMessage(null, message);
                return;
            }
            LoginMB loginMB = (LoginMB) JSFUtils.getSessionAttribute("loginMB");
            User user = loginMB.getUser();
            CalificacionService calificacionService = (CalificacionService) ServiceFinder.findBean("CalificacionService");
            Calificacion cal = new Calificacion();
            cal.setNcalificacionid(calificacionService.getNextPK());
            cal.setNconocimientoid(this.getSelectedContenido().getNconocimientoid());
            cal.setNcalificacion(this.getCalificacion());
            cal.setVcomentario(StringUtils.capitalize(this.getComentario().trim()));
            cal.setDfechacreacion(new Date());
            cal.setVusuariocreacion(user.getVlogin());
            calificacionService.saveOrUpdate(cal);
            this.setListaCalificacion(calificacionService.getCalificacionesByConocimiento(this.getSelectedContenido().getNconocimientoid()));
            if (CollectionUtils.isNotEmpty(this.getListaCalificacion())) {
                UserService userService = (UserService) ServiceFinder.findBean("UserService");
                for (Calificacion c : this.getListaCalificacion()) {
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
            CalificacionService calificacionService = (CalificacionService) ServiceFinder.findBean("CalificacionService");
            this.getSelectedCalificacion().setNcalificacion(this.getSelectedCalificacion().getNcalificacion());
            this.getSelectedCalificacion().setVcomentario(StringUtils.capitalize(this.getSelectedCalificacion().getVcomentario().trim()));
            this.getSelectedCalificacion().setDfechamodificacion(new Date());
            this.getSelectedCalificacion().setVusuariomodificacion(user.getVlogin());
            calificacionService.saveOrUpdate(this.getSelectedCalificacion());
            this.setListaCalificacion(calificacionService.getCalificacionesByConocimiento(this.getSelectedContenido().getNconocimientoid()));
            if (CollectionUtils.isNotEmpty(this.getListaCalificacion())) {
                UserService userService = (UserService) ServiceFinder.findBean("UserService");
                for (Calificacion c : this.getListaCalificacion()) {
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
            CalificacionService calificacionService = (CalificacionService) ServiceFinder.findBean("CalificacionService");
            calificacionService.delete(this.getSelectedCalificacion().getNcalificacionid());
            this.setListaCalificacion(calificacionService.getCalificacionesByConocimiento(this.getSelectedContenido().getNconocimientoid()));
            if (CollectionUtils.isNotEmpty(this.getListaCalificacion())) {
                UserService userService = (UserService) ServiceFinder.findBean("UserService");
                for (Calificacion c : this.getListaCalificacion()) {
                    User u = userService.getUserByLogin(c.getVusuariocreacion());
                    c.setUsuarioNombre(u.getVnombres() + " " + u.getVapellidos());
                }
            }
        } catch (Exception e) {
            e.getMessage();
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
        for(Conocimiento c : this.getListaContenido()) {
            HSSFRow row = sheet.getRow(i);
            for (int j = 0; j < row.getPhysicalNumberOfCells(); j++) {
                HSSFCell cell = row.getCell(j);
                if(i % 2 == 0 ) {
                    if(j > 0) {
                        cell.setCellStyle(centerGrayStyle);
                    } else {
                        cell.setCellStyle(grayBG);
                        cell.setCellValue(c.getVtitulo());
                    }
                } else {
                    if(j > 0) {
                        cell.setCellStyle(centerStyle);
                    } else {
                        cell.setCellValue(c.getVtitulo());
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
