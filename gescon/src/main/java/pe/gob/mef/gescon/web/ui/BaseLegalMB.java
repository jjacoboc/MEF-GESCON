/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template uploadFile, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.gob.mef.gescon.web.ui;

import java.io.File;
import java.io.FileInputStream;
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
import javax.servlet.http.HttpSession;
import org.apache.commons.beanutils.BeanUtils;
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
import org.springframework.util.CollectionUtils;
import pe.gob.mef.gescon.common.Constante;
import pe.gob.mef.gescon.common.Items;
import pe.gob.mef.gescon.common.Parameters;
import pe.gob.mef.gescon.hibernate.domain.Tbaselegal;
import pe.gob.mef.gescon.hibernate.domain.ThistorialId;
import pe.gob.mef.gescon.hibernate.domain.TseccionHistId;
import pe.gob.mef.gescon.hibernate.domain.TvinculoBaselegalId;
import pe.gob.mef.gescon.hibernate.domain.TvinculoHistId;
import pe.gob.mef.gescon.service.ArchivoHistorialService;
import pe.gob.mef.gescon.service.ArchivoService;
import pe.gob.mef.gescon.service.AsignacionService;
import pe.gob.mef.gescon.service.BaseLegalHistorialService;
import pe.gob.mef.gescon.service.BaseLegalService;
import pe.gob.mef.gescon.service.CalificacionBaseLegalService;
import pe.gob.mef.gescon.service.CategoriaService;
import pe.gob.mef.gescon.service.ConocimientoService;
import pe.gob.mef.gescon.service.ConsultaService;
import pe.gob.mef.gescon.service.HistorialService;
import pe.gob.mef.gescon.service.RangoService;
import pe.gob.mef.gescon.service.SeccionHistService;
import pe.gob.mef.gescon.service.SeccionService;
import pe.gob.mef.gescon.service.UserService;
import pe.gob.mef.gescon.service.VinculoBaseLegalService;
import pe.gob.mef.gescon.service.VinculoBaselegalHistorialService;
import pe.gob.mef.gescon.service.VinculoHistService;
import pe.gob.mef.gescon.service.VinculoService;
import pe.gob.mef.gescon.util.GcmFileUtils;
import pe.gob.mef.gescon.util.JSFUtils;
import pe.gob.mef.gescon.util.ServiceFinder;
import pe.gob.mef.gescon.web.bean.Archivo;
import pe.gob.mef.gescon.web.bean.ArchivoHist;
import pe.gob.mef.gescon.web.bean.Asignacion;
import pe.gob.mef.gescon.web.bean.BaseLegal;
import pe.gob.mef.gescon.web.bean.BaselegalHist;
import pe.gob.mef.gescon.web.bean.CalificacionBaselegal;
import pe.gob.mef.gescon.web.bean.Categoria;
import pe.gob.mef.gescon.web.bean.Conocimiento;
import pe.gob.mef.gescon.web.bean.Consulta;
import pe.gob.mef.gescon.web.bean.Historial;
import pe.gob.mef.gescon.web.bean.Seccion;
import pe.gob.mef.gescon.web.bean.SeccionHist;
import pe.gob.mef.gescon.web.bean.User;
import pe.gob.mef.gescon.web.bean.Vinculo;
import pe.gob.mef.gescon.web.bean.VinculoBaselegal;
import pe.gob.mef.gescon.web.bean.VinculoBaselegalHist;
import pe.gob.mef.gescon.web.bean.VinculoHist;

/**
 *
 * @author JJacobo
 */
@ManagedBean
@SessionScoped
public class BaseLegalMB implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final Log log = LogFactory.getLog(BaseLegalMB.class);
    private String temppath;
    private String path;
    private final String pathBL = "bl/";
    private List<BaseLegal> listaBaseLegal;
    private List<BaseLegal> filteredListaBaseLegal;
    private BaseLegal selectedBaseLegal;
    private String sumilla;
    private String tipoNorma;
    private String numeroNorma;
    private BigDecimal tiporangoId;
    private BigDecimal rangoId;
    private List<SelectItem> listaRangos;
    private Boolean chkGobNacional;
    private Boolean chkGobRegional;
    private Boolean chkGobLocal;
    private Boolean chkMancomunidades;
    private Boolean chkDestacado;
    private String comentario;
    private Date fechaVigencia;
    private String tema;
    private UploadedFile uploadFile;
    private StreamedContent content;
    private File file;
    private TreeNode tree;
    private Categoria selectedCategoria;
    private List<BaseLegal> listaSource;
    private List<BaseLegal> listaTarget;
    private List<BaseLegal> listaLinks;
    private DualListModel<BaseLegal> pickList;
    private List<CalificacionBaselegal> listaCalificacion;
    private CalificacionBaselegal selectedCalificacion;
    private BigDecimal calificacion;
    private String comentarioCalificacion;
    private List<BaselegalHist> listaHistorial;
    private List<BaselegalHist> filteredListaHistorial;
    private List<BaselegalHist> selectedHistoriales;
    private BaselegalHist selectedHistorialLeft;
    private BaselegalHist selectedHistorialRight;
    private List<Consulta> listaDestacados;
    private Consulta selectedDestacado;
    private List<Conocimiento> listaWikis;
    private Conocimiento selectedWiki;
    private BigDecimal codigoWiki;

    /**
     * Creates a new instance of BaseLegalMB
     */
    public BaseLegalMB() {
    }

    /**
     * @return the listaBaseLegal
     */
    public List<BaseLegal> getListaBaseLegal() {
        return listaBaseLegal;
    }

    /**
     * @param listaBaseLegal the listaBaseLegal to set
     */
    public void setListaBaseLegal(List<BaseLegal> listaBaseLegal) {
        this.listaBaseLegal = listaBaseLegal;
    }

    public List<BaseLegal> getFilteredListaBaseLegal() {
        return filteredListaBaseLegal;
    }

    public void setFilteredListaBaseLegal(List<BaseLegal> filteredListaBaseLegal) {
        this.filteredListaBaseLegal = filteredListaBaseLegal;
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
     * @return the sumilla
     */
    public String getSumilla() {
        return sumilla;
    }

    /**
     * @param sumilla the sumilla to set
     */
    public void setSumilla(String sumilla) {
        this.sumilla = sumilla;
    }

    public String getTipoNorma() {
        return tipoNorma;
    }

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

    public BigDecimal getTiporangoId() {
        return tiporangoId;
    }

    public void setTiporangoId(BigDecimal tiporangoId) {
        this.tiporangoId = tiporangoId;
    }

    /**
     * @return the rangoId
     */
    public BigDecimal getRangoId() {
        return rangoId;
    }

    /**
     * @param rangoId the rangoId to set
     */
    public void setRangoId(BigDecimal rangoId) {
        this.rangoId = rangoId;
    }

    public List<SelectItem> getListaRangos() {
        return listaRangos;
    }

    public void setListaRangos(List<SelectItem> listaRangos) {
        this.listaRangos = listaRangos;
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

    public Boolean getChkDestacado() {
        return chkDestacado;
    }

    public void setChkDestacado(Boolean chkDestacado) {
        this.chkDestacado = chkDestacado;
    }

    /**
     * @return the comentario
     */
    public String getComentario() {
        return comentario;
    }

    /**
     * @param comentario the comentario to set
     */
    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    /**
     * @return the fechaVigencia
     */
    public Date getFechaVigencia() {
        return fechaVigencia;
    }

    /**
     * @param fechaVigencia the fechaVigencia to set
     */
    public void setFechaVigencia(Date fechaVigencia) {
        this.fechaVigencia = fechaVigencia;
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

    public List<BaseLegal> getListaLinks() {
        return listaLinks;
    }

    public void setListaLinks(List<BaseLegal> listaLinks) {
        this.listaLinks = listaLinks;
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

    public List<CalificacionBaselegal> getListaCalificacion() {
        return listaCalificacion;
    }

    public void setListaCalificacion(List<CalificacionBaselegal> listaCalificacion) {
        this.listaCalificacion = listaCalificacion;
    }

    public CalificacionBaselegal getSelectedCalificacion() {
        return selectedCalificacion;
    }

    public void setSelectedCalificacion(CalificacionBaselegal selectedCalificacion) {
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

    public List<BaselegalHist> getListaHistorial() {
        return listaHistorial;
    }

    public void setListaHistorial(List<BaselegalHist> listaHistorial) {
        this.listaHistorial = listaHistorial;
    }

    public List<BaselegalHist> getFilteredListaHistorial() {
        return filteredListaHistorial;
    }

    public void setFilteredListaHistorial(List<BaselegalHist> filteredListaHistorial) {
        this.filteredListaHistorial = filteredListaHistorial;
    }

    public List<BaselegalHist> getSelectedHistoriales() {
        return selectedHistoriales;
    }

    public void setSelectedHistoriales(List<BaselegalHist> selectedHistoriales) {
        this.selectedHistoriales = selectedHistoriales;
    }

    public BaselegalHist getSelectedHistorialLeft() {
        return selectedHistorialLeft;
    }

    public void setSelectedHistorialLeft(BaselegalHist selectedHistorialLeft) {
        this.selectedHistorialLeft = selectedHistorialLeft;
    }

    public BaselegalHist getSelectedHistorialRight() {
        return selectedHistorialRight;
    }

    public void setSelectedHistorialRight(BaselegalHist selectedHistorialRight) {
        this.selectedHistorialRight = selectedHistorialRight;
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

    public List<Conocimiento> getListaWikis() {
        return listaWikis;
    }

    public void setListaWikis(List<Conocimiento> listaWikis) {
        this.listaWikis = listaWikis;
    }

    public Conocimiento getSelectedWiki() {
        return selectedWiki;
    }

    public void setSelectedWiki(Conocimiento selectedWiki) {
        this.selectedWiki = selectedWiki;
    }

    public BigDecimal getCodigoWiki() {
        return codigoWiki;
    }

    public void setCodigoWiki(BigDecimal codigoWiki) {
        this.codigoWiki = codigoWiki;
    }

    @PostConstruct
    public void init() {
        try {
            ResourceBundle bundle = ResourceBundle.getBundle(Parameters.getParameters());
            this.temppath = bundle.getString("temppath");
            this.path = bundle.getString("pdfpath");
            BaseLegalService service = (BaseLegalService) ServiceFinder.findBean("BaseLegalService");
            this.setListaBaseLegal(service.getBaselegales());
            ArchivoService aservice = (ArchivoService) ServiceFinder.findBean("ArchivoService");
            for (BaseLegal bl : this.getListaBaseLegal()) {
                bl.setArchivo(aservice.getArchivoByBaseLegal(bl));
            }
            this.setListaSource(new ArrayList<BaseLegal>());
            this.setListaTarget(new ArrayList<BaseLegal>());
            this.setPickList(new DualListModel<BaseLegal>(this.getListaSource(), this.getListaTarget()));
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
    }

    public void cleanAttributes() {
        this.setSelectedBaseLegal(null);
        this.setSelectedCategoria(null);
        this.setUploadFile(null);
        this.setFile(null);
        this.setContent(null);
        this.setSumilla(StringUtils.EMPTY);
        this.setNumeroNorma(StringUtils.EMPTY);
        this.setTiporangoId(BigDecimal.ZERO);
        this.setRangoId(BigDecimal.ZERO);
        this.setChkGobNacional(false);
        this.setChkGobRegional(false);
        this.setChkGobLocal(false);
        this.setChkMancomunidades(false);
        this.setChkDestacado(false);
        this.setComentario(StringUtils.EMPTY);
        this.setFechaVigencia(null);
        this.setTema(StringUtils.EMPTY);
        this.setListaRangos(new ArrayList());
        this.setListaSource(new ArrayList<BaseLegal>());
        this.setListaTarget(new ArrayList<BaseLegal>());
        this.setListaDestacados(new ArrayList<Consulta>());
        this.setSelectedDestacado(null);
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

    public void setSelectedRow(ActionEvent event) {
        try {
            if (event != null) {
                int index = Integer.parseInt((String) JSFUtils.getRequestParameter("index"));
                if (!CollectionUtils.isEmpty(this.getFilteredListaBaseLegal())) {
                    this.setSelectedBaseLegal(this.getFilteredListaBaseLegal().get(index));
                } else {
                    this.setSelectedBaseLegal(this.getListaBaseLegal().get(index));
                }
                this.setFilteredListaBaseLegal(new ArrayList());
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
    }

    public void handleChangeValue(AjaxBehaviorEvent event) {
        try {
            if (event != null) {
                BigDecimal id = (BigDecimal) ((SelectOneMenu) event.getSource()).getValue();
                if (id != null) {
                    HttpSession session = JSFUtils.getSession();
                    ListaSessionMB listasSession = session.getAttribute("listaSessionMB") != null ? (ListaSessionMB) session.getAttribute("listaSessionMB") : new ListaSessionMB();
                    List<SelectItem> lista = listasSession.getListaTipoRango();
                    for (SelectItem item : lista) {
                        if (id.toString().equals(item.getValue().toString())) {
                            this.setTipoNorma(item.getLabel());
                        }
                    }
                    RangoService service = (RangoService) ServiceFinder.findBean("RangoService");
                    this.setListaRangos(new Items(service.getRangosActivosByTipo(id), null, "nrangoid", "vnombre").getItems());
                } else {
                    this.setTipoNorma(StringUtils.EMPTY);
                    this.setListaRangos(new ArrayList());
                }

            }
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
    }

    public void handleUploadFile(FileUploadEvent event) {
        try {
            if (event != null) {
                UploadedFile f = event.getFile();
                if (f != null) {
                    String contentType = f.getContentType();
                    if (Constante.FILE_CONTENT_TYPE_PDF.equals(contentType)) {
                        this.setUploadFile(f);
                        File direc = new File(this.temppath);
                        direc.mkdirs();
                        this.setFile(new File(this.temppath, f.getFileName()));
                        FileOutputStream fileOutStream = new FileOutputStream(this.getFile());
                        fileOutStream.write(f.getContents());
                        fileOutStream.flush();
                        fileOutStream.close();
                        this.setContent(new DefaultStreamedContent(f.getInputstream(), contentType, f.getFileName()));
                    } else {
                        this.setFile(null);
                        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR.", "El archivo debe de ser un PDF.");
                        FacesContext.getCurrentInstance().addMessage(null, message);
                    }
                }
            }
        } catch (Exception e) {
            log.error(e.getMessage());
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
                BaseLegalService service = (BaseLegalService) ServiceFinder.findBean("BaseLegalService");
                BaseLegal baseLegal = service.getBaselegalById(this.getSelectedDestacado().getIdconocimiento());
                if (baseLegal != null) {
                    LoginMB loginMB = (LoginMB) JSFUtils.getSessionAttribute("loginMB");
                    User user = loginMB.getUser();
                    baseLegal.setNdestacado(BigDecimal.ZERO);
                    baseLegal.setVusuariomodificacion(user.getVlogin());
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

    public String toSave() {
        try {
            this.cleanAttributes();
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
        return "/pages/baselegal/nuevo?faces-redirect=true";
    }

    public void save(ActionEvent event) {
        try {
            if (CollectionUtils.isEmpty(this.getListaBaseLegal())) {
                this.setListaBaseLegal(new ArrayList());
            }
            if (this.getSelectedCategoria() == null) {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR.", "Seleccione la categoría de la base legal a registrar.");
                FacesContext.getCurrentInstance().addMessage(null, message);
                return;
            }
            if (this.getTiporangoId() == null) {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR.", "Seleccione el tipo de rango de la base legal a registrar.");
                FacesContext.getCurrentInstance().addMessage(null, message);
                return;
            }
            if (this.getRangoId() == null) {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR.", "Seleccione el tipo de rango de la base legal a registrar.");
                FacesContext.getCurrentInstance().addMessage(null, message);
                return;
            }
            if (StringUtils.isBlank(this.getTipoNorma())) {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR.", "Ingrese el tipo de la base legal a registrar.");
                FacesContext.getCurrentInstance().addMessage(null, message);
                return;
            }
            if (StringUtils.isBlank(this.getNumeroNorma())) {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR.", "Ingrese el número de la base legal a registrar.");
                FacesContext.getCurrentInstance().addMessage(null, message);
                return;
            }
            if (StringUtils.isBlank(this.getSumilla())) {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR.", "Ingrese la sumilla de la base legal a registrar.");
                FacesContext.getCurrentInstance().addMessage(null, message);
                return;
            }
            if (this.getUploadFile() == null) {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR.", "Debe cargar el archivo PDF de la base legal a registrar.");
                FacesContext.getCurrentInstance().addMessage(null, message);
                return;
            }
            if (this.getFechaVigencia() == null) {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR.", "Ingrese la fecha de publicación en el diario \"EL PERUANO\".");
                FacesContext.getCurrentInstance().addMessage(null, message);
                return;
            }
            if (!this.getChkGobNacional() && !this.getChkGobRegional() && !this.getChkGobLocal() && !this.getChkMancomunidades()) {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR.", "Seleccione al menos un ámbito para la base legal a registrar.");
                FacesContext.getCurrentInstance().addMessage(null, message);
                return;
            }

            if (!CollectionUtils.isEmpty(this.getListaTarget())) {
                for (BaseLegal v : this.getListaTarget()) {
                    if (v.getNestadoid().equals(BigDecimal.ZERO)) {
                        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR.", "Debe seleccionar el estado de todos los vínculos agregados.");
                        FacesContext.getCurrentInstance().addMessage(null, message);
                        return;
                    }
                }
            }

            LoginMB loginMB = (LoginMB) JSFUtils.getSessionAttribute("loginMB");
            User user = loginMB.getUser();

            BaseLegalService service = (BaseLegalService) ServiceFinder.findBean("BaseLegalService");
            BaseLegal base = new BaseLegal();
            base.setNbaselegalid(service.getNextPK());
            base.setNcategoriaid(this.getSelectedCategoria().getNcategoriaid());
            base.setVnombre(StringUtils.capitalize(this.getSumilla()));
            base.setVnumero(this.getTipoNorma().concat(" - ").concat(StringUtils.upperCase(this.getNumeroNorma())));
            base.setNtiporangoid(this.getTiporangoId());
            base.setNrangoid(this.getRangoId());
            base.setNgobnacional(this.getChkGobNacional() ? BigDecimal.ONE : BigDecimal.ZERO);
            base.setNgobregional(this.getChkGobRegional() ? BigDecimal.ONE : BigDecimal.ZERO);
            base.setNgoblocal(this.getChkGobLocal() ? BigDecimal.ONE : BigDecimal.ZERO);
            base.setNmancomunidades(this.getChkMancomunidades() ? BigDecimal.ONE : BigDecimal.ZERO);
            base.setNdestacado(BigDecimal.ZERO);
            base.setNcodigowiki(this.getCodigoWiki());
            base.setVsumilla(this.getComentario().trim());
            base.setDfechavigencia(this.getFechaVigencia());
            base.setVtema(this.getTema());
            base.setNactivo(BigDecimal.ONE);
            if (this.getSelectedCategoria().getNflagbl().toString().equals("1")) {
                base.setNestadoid(BigDecimal.valueOf(Long.valueOf(Constante.ESTADO_BASELEGAL_REGISTRADO)));
            } else {
                base.setNestadoid(BigDecimal.valueOf(Long.valueOf(Constante.ESTADO_BASELEGAL_PUBLICADO)));
                base.setDfechapublicacion(new Date());
            }

            base.setVusuariocreacion(user.getVlogin());
            base.setDfechacreacion(new Date());
            service.saveOrUpdate(base);

            String ruta0 = this.pathBL + base.getNbaselegalid().toString() + "\\" + BigDecimal.ZERO.toString() + "\\";
            String txt0 = base.getVnombre();
            GcmFileUtils.writeStringToFileServer(ruta0, "plain.txt", txt0);

            BaseLegalHistorialService serviceHistorial = (BaseLegalHistorialService) ServiceFinder.findBean("BaseLegalHistorialService");
            BaselegalHist baseHist = new BaselegalHist();
            baseHist.setNhistorialid(serviceHistorial.getNextPK());
            baseHist.setNbaselegalid(base.getNbaselegalid());
            baseHist.setNcategoriaid(base.getNcategoriaid());
            baseHist.setVnombre(base.getVnombre());
            baseHist.setVnumero(base.getVnumero());
            baseHist.setNtiporangoid(base.getNtiporangoid());
            baseHist.setNrangoid(base.getNrangoid());
            baseHist.setNgobnacional(base.getNgobnacional());
            baseHist.setNgobregional(base.getNgobregional());
            baseHist.setNgoblocal(base.getNgoblocal());
            baseHist.setNmancomunidades(base.getNmancomunidades());
            baseHist.setNdestacado(base.getNdestacado());
            baseHist.setVsumilla(base.getVsumilla());
            baseHist.setDfechavigencia(base.getDfechavigencia());
            baseHist.setVtema(base.getVtema());
            baseHist.setNactivo(base.getNactivo());
            baseHist.setNestadoid(base.getNestadoid());
            baseHist.setNversion(BigDecimal.ONE);
            baseHist.setVusuariocreacion(base.getVusuariocreacion());
            baseHist.setDfechacreacion(base.getDfechacreacion());
            serviceHistorial.saveOrUpdate(baseHist);

            String ruta1 = this.pathBL + base.getNbaselegalid().toString() + "\\" + BigDecimal.ONE.toString() + "\\";
            String txt1 = baseHist.getVnombre();
            GcmFileUtils.writeStringToFileServer(ruta1, "plain.txt", txt1);

            Tbaselegal tbaselegal = new Tbaselegal();
            BeanUtils.copyProperties(tbaselegal, base);

            ArchivoService aservice = (ArchivoService) ServiceFinder.findBean("ArchivoService");
            if (this.getUploadFile() != null) {
                ruta0 = this.path + base.getNbaselegalid().toString() + "\\" + BigDecimal.ZERO.toString() + "\\";
                Archivo archivo = new Archivo();
                archivo.setNarchivoid(aservice.getNextPK());
                archivo.setTbaselegal(tbaselegal);
                archivo.setVnombre(this.getUploadFile().getFileName());
                archivo.setVruta(ruta0 + archivo.getVnombre());
                archivo.setNversion(BigDecimal.ZERO);
                archivo.setVusuariocreacion(user.getVlogin());
                archivo.setDfechacreacion(new Date());
                aservice.saveOrUpdate(archivo);
                saveFile(ruta0);

                ruta1 = this.path + base.getNbaselegalid().toString() + "\\" + BigDecimal.ONE.toString() + "\\";
                ArchivoHistorialService aserviceHist = (ArchivoHistorialService) ServiceFinder.findBean("ArchivoHistorialService");
                ArchivoHist archivoHist = new ArchivoHist();
                archivoHist.setNarchivohistid(aserviceHist.getNextPK());
                archivoHist.setNhistorialid(baseHist.getNhistorialid());
                archivoHist.setNbaselegalid(baseHist.getNbaselegalid());
                archivoHist.setVnombre(archivo.getVnombre());
                archivoHist.setVruta(ruta1 + archivo.getVnombre());
                archivoHist.setNversion(BigDecimal.ONE);
                archivoHist.setVusuariocreacion(user.getVlogin());
                archivoHist.setDfechacreacion(new Date());
                aserviceHist.saveOrUpdate(archivoHist);
                saveFile(ruta1);
            }

            if (!CollectionUtils.isEmpty(this.getListaTarget())) {
                for (BaseLegal v : this.getListaTarget()) {
                    VinculoBaseLegalService vservice = (VinculoBaseLegalService) ServiceFinder.findBean("VinculoBaseLegalService");
                    TvinculoBaselegalId id = new TvinculoBaselegalId();
                    id.setNbaselegalid(tbaselegal.getNbaselegalid());
                    id.setNvinculoid(vservice.getNextPK());
                    VinculoBaselegal vinculo = new VinculoBaselegal();
                    vinculo.setId(id);
                    vinculo.setTbaselegal(tbaselegal);
                    vinculo.setNbaselegalvinculadaid(v.getNbaselegalid());
                    vinculo.setNtipovinculo(v.getNestadoid());
                    vinculo.setDfechacreacion(new Date());
                    vinculo.setVusuariocreacion(user.getVlogin());
                    vservice.saveOrUpdate(vinculo);

                    BaseLegal blvinculada = service.getBaselegalById(v.getNbaselegalid());
                    blvinculada.setNestadoid(v.getNestadoid());
                    blvinculada.setDfechamodificacion(new Date());
                    blvinculada.setVusuariomodificacion(user.getVlogin());
                    service.saveOrUpdate(blvinculada);

                    if (v.getNestadoid().toString().equals(Constante.ESTADO_BASELEGAL_MODIFICADA)
                            || v.getNestadoid().toString().equals(Constante.ESTADO_BASELEGAL_CONCORDADO)) {

                        ConocimientoService cservice = (ConocimientoService) ServiceFinder.findBean("ConocimientoService");
                        List<Consulta> listaConocimientos = cservice.getConcimientosByVinculoBaseLegalId(blvinculada.getNbaselegalid());
                        if (!CollectionUtils.isEmpty(listaConocimientos)) {
                            for (Consulta c : listaConocimientos) {
                                Conocimiento conocimiento = cservice.getConocimientoById(c.getIdconocimiento());
                                conocimiento.setDfechamodificacion(new Date());
                                conocimiento.setVusuariomodificacion(user.getVlogin());
                                String descripcionHtml = GcmFileUtils.readStringFromFileServer(conocimiento.getVruta(), "html.txt");
                                String descripcionPlain = GcmFileUtils.readStringFromFileServer(conocimiento.getVruta(), "plain.txt");
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
                                historial.setVusuariocreacion(user.getVlogin());
                                historialService.saveOrUpdate(historial);

                                GcmFileUtils.writeStringToFileServer(url, "html.txt", descripcionHtml);
                                GcmFileUtils.writeStringToFileServer(url, "plain.txt", descripcionPlain);

                                SeccionService seccionService = (SeccionService) ServiceFinder.findBean("SeccionService");
                                SeccionHistService seccionHistService = (SeccionHistService) ServiceFinder.findBean("SeccionHistService");
                                List<Seccion> listaSeccion = seccionService.getSeccionesByConocimiento(conocimiento.getNconocimientoid());
                                if (!CollectionUtils.isEmpty(listaSeccion)) {
                                    String url0 = conocimiento.getVruta().concat("s");
                                    String url1 = url.concat("s");
                                    for (Seccion seccion : listaSeccion) {
                                        seccion.setDetalleHtml(GcmFileUtils.readStringFromFileServer(seccion.getVruta(), "html.txt"));
                                        ruta0 = url0.concat(seccion.getNorden().toString()).concat("/");
                                        seccion.setVruta(ruta0);
                                        seccion.setDfechamodificacion(new Date());
                                        seccion.setVusuariomodificacion(user.getVlogin());
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
                                        seccionHist.setVusuariocreacion(user.getVlogin());
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
                                vinculoC.setVusuariocreacion(user.getVlogin());
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
                                    vinculoHist.setVusuariocreacion(user.getVlogin());
                                    vinculoHistService.saveOrUpdate(vinculoHist);
                                }
                            }
                        }
                    } else if (v.getNbaselegalid().toString().equals(Constante.ESTADO_BASELEGAL_DEROGADA)) {
                        ConocimientoService cservice = (ConocimientoService) ServiceFinder.findBean("ConocimientoService");
                        List<Consulta> listaConocimientos = cservice.getConcimientosByVinculoBaseLegalId(blvinculada.getNbaselegalid());
                        if (!CollectionUtils.isEmpty(listaConocimientos)) {
                            for (Consulta c : listaConocimientos) {
                                Conocimiento conocimiento = cservice.getConocimientoById(c.getIdconocimiento());
                                conocimiento.setNflgvinculo(BigDecimal.ONE);
                                conocimiento.setDfechamodificacion(new Date());
                                conocimiento.setVusuariomodificacion(user.getVlogin());
                            }
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
                    vinculoHist.setVusuariocreacion(user.getVlogin());
                    vserviceHist.saveOrUpdate(vinculoHist);
                }
            }

            if (this.getSelectedCategoria().getNflagbl().toString().equals("1")) {
                Asignacion asignacion = new Asignacion();
                AsignacionService serviceasig = (AsignacionService) ServiceFinder.findBean("AsignacionService");
                asignacion.setNasignacionid(serviceasig.getNextPK());
                asignacion.setNtipoconocimientoid(Constante.BASELEGAL);
                asignacion.setNconocimientoid(base.getNbaselegalid());
                asignacion.setNestadoid(BigDecimal.valueOf(Long.parseLong("1")));
                CategoriaService categoriaService = (CategoriaService) ServiceFinder.findBean("CategoriaService");
                asignacion.setNusuarioid(categoriaService.getCategoriaById(this.getSelectedCategoria().getNcategoriaid()).getNmoderador());
                asignacion.setDfechaasignacion(new Date());
                asignacion.setDfechacreacion(new Date());
                serviceasig.saveOrUpdate(asignacion);
            }

            this.setListaBaseLegal(service.getBaselegales());
            for (BaseLegal bl : this.getListaBaseLegal()) {
                bl.setArchivo(aservice.getArchivoByBaseLegal(bl));
            }
            FacesContext.getCurrentInstance().getExternalContext().redirect("/gescon/pages/baselegal/lista.xhtml");
        } catch (Exception e) {
            e.getMessage();
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
                File temp = new File(temppath, this.getUploadFile().getFileName());
                temp.delete();
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
    }

    public String toEdit() {
        try {
            this.cleanAttributes();
            int index = Integer.parseInt((String) JSFUtils.getRequestParameter("index"));
            if (!CollectionUtils.isEmpty(this.getFilteredListaBaseLegal())) {
                this.setSelectedBaseLegal(this.getFilteredListaBaseLegal().get(index));
            } else {
                this.setSelectedBaseLegal(this.getListaBaseLegal().get(index));
            }
            CategoriaService categoriaService = (CategoriaService) ServiceFinder.findBean("CategoriaService");
            this.setSelectedCategoria(categoriaService.getCategoriaById(this.getSelectedBaseLegal().getNcategoriaid()));
            index = this.getSelectedBaseLegal().getVnumero().indexOf("-");
            this.setTiporangoId(this.getSelectedBaseLegal().getNtiporangoid());
            this.setRangoId(this.getSelectedBaseLegal().getNrangoid());
            this.setTipoNorma(this.getSelectedBaseLegal().getVnumero().substring(0, index).trim());
            this.setNumeroNorma(this.getSelectedBaseLegal().getVnumero().substring(index + 1).trim());
            this.setChkGobNacional(this.getSelectedBaseLegal().getNgobnacional().equals(BigDecimal.ONE));
            this.setChkGobRegional(this.getSelectedBaseLegal().getNgobregional().equals(BigDecimal.ONE));
            this.setChkGobLocal(this.getSelectedBaseLegal().getNgoblocal().equals(BigDecimal.ONE));
            this.setChkMancomunidades(this.getSelectedBaseLegal().getNmancomunidades().equals(BigDecimal.ONE));
            this.setChkDestacado(this.getSelectedBaseLegal().getNdestacado().equals(BigDecimal.ONE));
            this.setCodigoWiki(this.getSelectedBaseLegal().getNcodigowiki());
            ConocimientoService conocimientoService = (ConocimientoService) ServiceFinder.findBean("ConocimientoService");
            this.setSelectedWiki(conocimientoService.getConocimientoById(this.getCodigoWiki()));
            RangoService rangoService = (RangoService) ServiceFinder.findBean("RangoService");
            this.setListaRangos(new Items(rangoService.getRangosActivosByTipo(this.getSelectedBaseLegal().getNtiporangoid()), null, "nrangoid", "vnombre").getItems());
            BaseLegalService service = (BaseLegalService) ServiceFinder.findBean("BaseLegalService");
            this.setListaSource(service.getTbaselegalesNotLinkedById(this.getSelectedBaseLegal().getNbaselegalid()));
            this.setListaTarget(service.getTbaselegalesLinkedById(this.getSelectedBaseLegal().getNbaselegalid()));
            this.setPickList(new DualListModel<BaseLegal>(this.getListaSource(), this.getListaTarget()));
            this.setFilteredListaBaseLegal(new ArrayList());
            this.setUploadFile(null);
            this.setFile(null);
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
        return "/pages/baselegal/editar?faces-redirect=true";
    }

    public void edit(ActionEvent event) {
        try {
            if (this.getSelectedCategoria() == null) {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR.", "Seleccione la categoría de la base legal a registrar.");
                FacesContext.getCurrentInstance().addMessage(null, message);
                return;
            }
            if (this.getSelectedBaseLegal().getNtiporangoid() == null) {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR.", "Seleccione el tipo de rango de la base legal a registrar.");
                FacesContext.getCurrentInstance().addMessage(null, message);
                return;
            }
            if (this.getSelectedBaseLegal().getNrangoid() == null) {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR.", "Seleccione el tipo de rango de la base legal a registrar.");
                FacesContext.getCurrentInstance().addMessage(null, message);
                return;
            }
            if (StringUtils.isBlank(this.getTipoNorma())) {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR.", "Ingrese el tipo de la base legal a registrar.");
                FacesContext.getCurrentInstance().addMessage(null, message);
                return;
            }
            if (StringUtils.isBlank(this.getNumeroNorma())) {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR.", "Ingrese el número de la base legal a registrar.");
                FacesContext.getCurrentInstance().addMessage(null, message);
                return;
            }
            if (StringUtils.isBlank(this.getSelectedBaseLegal().getVnombre())) {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR.", "Ingrese la sumilla de la base legal a registrar.");
                FacesContext.getCurrentInstance().addMessage(null, message);
                return;
            }
            if (this.getSelectedBaseLegal().getDfechavigencia() == null) {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR.", "Ingrese la fecha de publicación en el diario \"EL PERUANO\".");
                FacesContext.getCurrentInstance().addMessage(null, message);
                return;
            }
            if (!this.getChkGobNacional() && !this.getChkGobRegional() && !this.getChkGobLocal() && !this.getChkMancomunidades()) {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR.", "Seleccione al menos un ámbito para la base legal a registrar.");
                FacesContext.getCurrentInstance().addMessage(null, message);
                return;
            }
            if (this.getSelectedBaseLegal().getNdestacado().equals(BigDecimal.ZERO) && this.getChkDestacado()) {
                ConsultaService consultaService = (ConsultaService) ServiceFinder.findBean("ConsultaService");
                HashMap filter = new HashMap();
                filter.put("ntipoconocimientoid", Constante.BASELEGAL);
                BigDecimal cant = consultaService.countDestacadosByTipoConocimiento(filter);
                if (cant.intValue() >= 10) {
                    this.setListaDestacados(consultaService.getDestacadosByTipoConocimiento(filter));
                    RequestContext.getCurrentInstance().execute("PF('destDialog').show();");
                    return;
                }
            }
            if (!CollectionUtils.isEmpty(this.getListaTarget())) {
                for (BaseLegal v : this.getListaTarget()) {
                    if (v.getNestadoid().equals(BigDecimal.ZERO)) {
                        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR.", "Debe seleccionar el estado de todos los vínculos agregados.");
                        FacesContext.getCurrentInstance().addMessage(null, message);
                        return;
                    }
                }
            }

            if (CollectionUtils.isEmpty(this.getListaBaseLegal())) {
                this.setListaBaseLegal(new ArrayList());
            }
            LoginMB loginMB = (LoginMB) JSFUtils.getSessionAttribute("loginMB");
            User user = loginMB.getUser();
            if (this.getSelectedCategoria() != null) {
                this.getSelectedBaseLegal().setNcategoriaid(this.getSelectedCategoria().getNcategoriaid());
            }
            BaseLegalService service = (BaseLegalService) ServiceFinder.findBean("BaseLegalService");
            this.getSelectedBaseLegal().setVnombre(StringUtils.capitalize(this.getSelectedBaseLegal().getVnombre()));
            this.getSelectedBaseLegal().setVnumero(this.getTipoNorma().concat(" - ").concat(StringUtils.upperCase(this.getNumeroNorma())));
            this.getSelectedBaseLegal().setNtiporangoid(this.getSelectedBaseLegal().getNtiporangoid());
            this.getSelectedBaseLegal().setNrangoid(this.getSelectedBaseLegal().getNrangoid());
            this.getSelectedBaseLegal().setNgobnacional(this.getChkGobNacional() ? BigDecimal.ONE : BigDecimal.ZERO);
            this.getSelectedBaseLegal().setNgobregional(this.getChkGobRegional() ? BigDecimal.ONE : BigDecimal.ZERO);
            this.getSelectedBaseLegal().setNgoblocal(this.getChkGobLocal() ? BigDecimal.ONE : BigDecimal.ZERO);
            this.getSelectedBaseLegal().setNmancomunidades(this.getChkMancomunidades() ? BigDecimal.ONE : BigDecimal.ZERO);
            this.getSelectedBaseLegal().setNdestacado(this.getChkDestacado() ? BigDecimal.ONE : BigDecimal.ZERO);
            this.getSelectedBaseLegal().setNcodigowiki(this.getCodigoWiki());
            this.getSelectedBaseLegal().setVsumilla(this.getSelectedBaseLegal().getVsumilla().trim());
            this.getSelectedBaseLegal().setDfechavigencia(this.getSelectedBaseLegal().getDfechavigencia());
            this.getSelectedBaseLegal().setVtema(this.getSelectedBaseLegal().getVtema());
            this.getSelectedBaseLegal().setVusuariomodificacion(user.getVlogin());
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
            baseHist.setNtiporangoid(this.getSelectedBaseLegal().getNtiporangoid());
            baseHist.setNrangoid(this.getSelectedBaseLegal().getNrangoid());
            baseHist.setNgobnacional(this.getSelectedBaseLegal().getNgobnacional());
            baseHist.setNgobregional(this.getSelectedBaseLegal().getNgobregional());
            baseHist.setNgoblocal(this.getSelectedBaseLegal().getNgoblocal());
            baseHist.setNmancomunidades(this.getSelectedBaseLegal().getNmancomunidades());
            baseHist.setNdestacado(this.getSelectedBaseLegal().getNdestacado());
            baseHist.setVsumilla(this.getSelectedBaseLegal().getVsumilla());
            baseHist.setDfechavigencia(this.getSelectedBaseLegal().getDfechavigencia());
            baseHist.setVtema(this.getSelectedBaseLegal().getVtema());
            baseHist.setNactivo(this.getSelectedBaseLegal().getNactivo());
            baseHist.setNestadoid(this.getSelectedBaseLegal().getNestadoid());
            baseHist.setNversion(BigDecimal.valueOf(hist.getNversion().intValue() + 1));
            baseHist.setVusuariocreacion(user.getVlogin());
            baseHist.setDfechacreacion(new Date());
            baseHist.setVusuariomodificacion(this.getSelectedBaseLegal().getVusuariomodificacion());
            baseHist.setDfechamodificacion(this.getSelectedBaseLegal().getDfechamodificacion());
            serviceHistorial.saveOrUpdate(baseHist);

            Tbaselegal tbaselegal = new Tbaselegal();
            BeanUtils.copyProperties(tbaselegal, this.getSelectedBaseLegal());

            String ruta0 = this.pathBL + this.getSelectedBaseLegal().getNbaselegalid().toString() + "\\" + BigDecimal.ZERO.toString() + "\\";
            String txt0 = this.getSelectedBaseLegal().getVnombre();
            GcmFileUtils.writeStringToFileServer(ruta0, "plain.txt", txt0);
            String ruta1 = this.pathBL + this.getSelectedBaseLegal().getNbaselegalid().toString() + "\\" + baseHist.getNversion().toString() + "\\";
            String txt1 = baseHist.getVnombre();
            GcmFileUtils.writeStringToFileServer(ruta1, "plain.txt", txt1);

            ArchivoService aservice = (ArchivoService) ServiceFinder.findBean("ArchivoService");
            Archivo archivo = aservice.getArchivoByBaseLegal(this.getSelectedBaseLegal());
            if (this.getUploadFile() != null) {
                ruta0 = this.path + this.getSelectedBaseLegal().getNbaselegalid().toString() + "\\" + BigDecimal.ZERO.toString() + "\\";
                archivo.setVnombre(this.getUploadFile().getFileName());
                archivo.setVruta(ruta0 + archivo.getVnombre());
                archivo.setVusuariomodificacion(user.getVlogin());
                archivo.setDfechamodificacion(new Date());
                aservice.saveOrUpdate(archivo);
                saveFile(ruta0);
            }

            ruta1 = this.path + this.getSelectedBaseLegal().getNbaselegalid().toString() + "\\" + baseHist.getNversion().toString() + "\\";
            ArchivoHistorialService aserviceHist = (ArchivoHistorialService) ServiceFinder.findBean("ArchivoHistorialService");
            ArchivoHist archivoHist = aserviceHist.getLastArchivoHistByBaseLegalHist(baseHist);
            archivoHist = archivoHist != null ? archivoHist : new ArchivoHist();
            archivoHist.setNarchivohistid(aserviceHist.getNextPK());
            archivoHist.setNhistorialid(baseHist.getNhistorialid());
            archivoHist.setNbaselegalid(baseHist.getNbaselegalid());
            archivoHist.setVnombre(archivo.getVnombre());
            archivoHist.setVruta(ruta1 + archivo.getVnombre());
            archivoHist.setVusuariocreacion(user.getVlogin());
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
                vinculo.setVusuariocreacion(user.getVlogin());
                vservice.saveOrUpdate(vinculo);

                BaseLegal blvinculada = service.getBaselegalById(v.getNbaselegalid());
                blvinculada.setNestadoid(v.getNestadoid());
                blvinculada.setDfechamodificacion(new Date());
                blvinculada.setVusuariomodificacion(user.getVlogin());
                service.saveOrUpdate(blvinculada);

                if (v.getNbaselegalid().toString().equals(Constante.ESTADO_BASELEGAL_MODIFICADA)
                        || v.getNbaselegalid().toString().equals(Constante.ESTADO_BASELEGAL_CONCORDADO)) {

                    ConocimientoService cservice = (ConocimientoService) ServiceFinder.findBean("ConocimientoService");
                    List<Consulta> listaConocimientos = cservice.getConcimientosByVinculoBaseLegalId(blvinculada.getNbaselegalid());
                    if (!CollectionUtils.isEmpty(listaConocimientos)) {
                        for (Consulta c : listaConocimientos) {
                            Conocimiento conocimiento = cservice.getConocimientoById(c.getIdconocimiento());
                            conocimiento.setDfechamodificacion(new Date());
                            conocimiento.setVusuariomodificacion(user.getVlogin());
                            String descripcionHtml = GcmFileUtils.readStringFromFileServer(conocimiento.getVruta(), "html.txt");
                            String descripcionPlain = GcmFileUtils.readStringFromFileServer(conocimiento.getVruta(), "plain.txt");
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
                            historial.setVusuariocreacion(user.getVlogin());
                            historialService.saveOrUpdate(historial);

                            GcmFileUtils.writeStringToFileServer(url, "html.txt", descripcionHtml);
                            GcmFileUtils.writeStringToFileServer(url, "plain.txt", descripcionPlain);

                            SeccionService seccionService = (SeccionService) ServiceFinder.findBean("SeccionService");
                            SeccionHistService seccionHistService = (SeccionHistService) ServiceFinder.findBean("SeccionHistService");
                            List<Seccion> listaSeccion = seccionService.getSeccionesByConocimiento(conocimiento.getNconocimientoid());
                            if (!CollectionUtils.isEmpty(listaSeccion)) {
                                String url0 = conocimiento.getVruta().concat("s");
                                String url1 = url.concat("s");
                                for (Seccion seccion : listaSeccion) {
                                    seccion.setDetalleHtml(GcmFileUtils.readStringFromFileServer(seccion.getVruta(), "html.txt"));
                                    ruta0 = url0.concat(seccion.getNorden().toString()).concat("/");
                                    seccion.setVruta(ruta0);
                                    seccion.setDfechamodificacion(new Date());
                                    seccion.setVusuariomodificacion(user.getVlogin());
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
                                    seccionHist.setVusuariocreacion(user.getVlogin());
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
                            vinculoC.setVusuariocreacion(user.getVlogin());
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
                                vinculoHist.setVusuariocreacion(user.getVlogin());
                                vinculoHistService.saveOrUpdate(vinculoHist);
                            }
                        }
                    }
                } else if (v.getNbaselegalid().toString().equals(Constante.ESTADO_BASELEGAL_DEROGADA)) {
                    ConocimientoService cservice = (ConocimientoService) ServiceFinder.findBean("ConocimientoService");
                    List<Consulta> listaConocimientos = cservice.getConcimientosByVinculoBaseLegalId(blvinculada.getNbaselegalid());
                    if (!CollectionUtils.isEmpty(listaConocimientos)) {
                        for (Consulta c : listaConocimientos) {
                            Conocimiento conocimiento = cservice.getConocimientoById(c.getIdconocimiento());
                            conocimiento.setNflgvinculo(BigDecimal.ONE);
                            conocimiento.setDfechamodificacion(new Date());
                            conocimiento.setVusuariomodificacion(user.getVlogin());
                        }
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
                vinculoHist.setVusuariocreacion(user.getVlogin());
                vserviceHist.saveOrUpdate(vinculoHist);
            }

            this.setListaBaseLegal(service.getBaselegales());
            for (BaseLegal bl : this.getListaBaseLegal()) {
                bl.setArchivo(aservice.getArchivoByBaseLegal(bl));
            }
            FacesContext.getCurrentInstance().getExternalContext().redirect("/gescon/pages/baselegal/lista.xhtml");
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
    }

    public String toPost() {
        try {
            this.cleanAttributes();
            int index = Integer.parseInt((String) JSFUtils.getRequestParameter("index"));
            if (!CollectionUtils.isEmpty(this.getFilteredListaBaseLegal())) {
                this.setSelectedBaseLegal(this.getFilteredListaBaseLegal().get(index));
            } else {
                this.setSelectedBaseLegal(this.getListaBaseLegal().get(index));
            }
            CategoriaService categoriaService = (CategoriaService) ServiceFinder.findBean("CategoriaService");
            this.setSelectedCategoria(categoriaService.getCategoriaById(this.getSelectedBaseLegal().getNcategoriaid()));
            index = this.getSelectedBaseLegal().getVnumero().indexOf("-");
            this.setTiporangoId(this.getSelectedBaseLegal().getNtiporangoid());
            this.setRangoId(this.getSelectedBaseLegal().getNrangoid());
            this.setTipoNorma(this.getSelectedBaseLegal().getVnumero().substring(0, index).trim());
            this.setNumeroNorma(this.getSelectedBaseLegal().getVnumero().substring(index + 1).trim());
            this.setChkGobNacional(this.getSelectedBaseLegal().getNgobnacional().equals(BigDecimal.ONE));
            this.setChkGobRegional(this.getSelectedBaseLegal().getNgobregional().equals(BigDecimal.ONE));
            this.setChkGobLocal(this.getSelectedBaseLegal().getNgoblocal().equals(BigDecimal.ONE));
            this.setChkMancomunidades(this.getSelectedBaseLegal().getNmancomunidades().equals(BigDecimal.ONE));
            this.setChkDestacado(this.getSelectedBaseLegal().getNdestacado().equals(BigDecimal.ONE));
            this.setCodigoWiki(this.getSelectedBaseLegal().getNcodigowiki());
            ConocimientoService conocimientoService = (ConocimientoService) ServiceFinder.findBean("ConocimientoService");
            this.setSelectedWiki(conocimientoService.getConocimientoById(this.getCodigoWiki()));
            RangoService rangoService = (RangoService) ServiceFinder.findBean("RangoService");
            this.setListaRangos(new Items(rangoService.getRangosActivosByTipo(this.getSelectedBaseLegal().getNtiporangoid()), null, "nrangoid", "vnombre").getItems());
            BaseLegalService service = (BaseLegalService) ServiceFinder.findBean("BaseLegalService");
            this.setListaSource(service.getTbaselegalesNotLinkedById(this.getSelectedBaseLegal().getNbaselegalid()));
            this.setListaTarget(service.getTbaselegalesLinkedById(this.getSelectedBaseLegal().getNbaselegalid()));
            this.setPickList(new DualListModel<BaseLegal>(this.getListaSource(), this.getListaTarget()));
            this.setFilteredListaBaseLegal(new ArrayList());
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
        return "/pages/baselegal/publicar?faces-redirect=true";
    }

    public void post(ActionEvent event) {
        try {
            if (this.getSelectedCategoria() == null) {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR.", "Seleccione la categoría de la base legal a registrar.");
                FacesContext.getCurrentInstance().addMessage(null, message);
                return;
            }
            if (this.getSelectedBaseLegal().getNtiporangoid() == null) {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR.", "Seleccione el tipo de rango de la base legal a registrar.");
                FacesContext.getCurrentInstance().addMessage(null, message);
                return;
            }
            if (this.getSelectedBaseLegal().getNrangoid() == null) {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR.", "Seleccione el tipo de rango de la base legal a registrar.");
                FacesContext.getCurrentInstance().addMessage(null, message);
                return;
            }
            if (StringUtils.isBlank(this.getTipoNorma())) {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR.", "Ingrese el tipo de la base legal a registrar.");
                FacesContext.getCurrentInstance().addMessage(null, message);
                return;
            }
            if (StringUtils.isBlank(this.getNumeroNorma())) {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR.", "Ingrese el número de la base legal a registrar.");
                FacesContext.getCurrentInstance().addMessage(null, message);
                return;
            }
            if (StringUtils.isBlank(this.getSelectedBaseLegal().getVnombre())) {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR.", "Ingrese la sumilla de la base legal a registrar.");
                FacesContext.getCurrentInstance().addMessage(null, message);
                return;
            }
            if (this.getSelectedBaseLegal().getDfechavigencia() == null) {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR.", "Ingrese la fecha de publicación en el diario \"EL PERUANO\".");
                FacesContext.getCurrentInstance().addMessage(null, message);
                return;
            }
            if (!this.getChkGobNacional() && !this.getChkGobRegional() && !this.getChkGobLocal() && !this.getChkMancomunidades()) {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR.", "Seleccione al menos un ámbito para la base legal a registrar.");
                FacesContext.getCurrentInstance().addMessage(null, message);
                return;
            }
            if (this.getSelectedBaseLegal().getNdestacado().equals(BigDecimal.ZERO) && this.getChkDestacado()) {
                ConsultaService consultaService = (ConsultaService) ServiceFinder.findBean("ConsultaService");
                HashMap filter = new HashMap();
                filter.put("ntipoconocimientoid", Constante.BASELEGAL);
                BigDecimal cant = consultaService.countDestacadosByTipoConocimiento(filter);
                if (cant.intValue() >= 10) {
                    this.setListaDestacados(consultaService.getDestacadosByTipoConocimiento(filter));
                    RequestContext.getCurrentInstance().execute("PF('destDialog').show();");
                    return;
                }
            }
            if (!CollectionUtils.isEmpty(this.getListaTarget())) {
                for (BaseLegal v : this.getListaTarget()) {
                    if (v.getNestadoid().equals(BigDecimal.ZERO)) {
                        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR.", "Debe seleccionar el estado de todos los vínculos agregados.");
                        FacesContext.getCurrentInstance().addMessage(null, message);
                        return;
                    }
                }
            }
            if (CollectionUtils.isEmpty(this.getListaBaseLegal())) {
                this.setListaBaseLegal(new ArrayList());
            }
            LoginMB loginMB = (LoginMB) JSFUtils.getSessionAttribute("loginMB");
            User user = loginMB.getUser();
            if (this.getSelectedCategoria() != null) {
                this.getSelectedBaseLegal().setNcategoriaid(this.getSelectedCategoria().getNcategoriaid());
            }
            BaseLegalService service = (BaseLegalService) ServiceFinder.findBean("BaseLegalService");
            this.getSelectedBaseLegal().setVnombre(StringUtils.capitalize(this.getSelectedBaseLegal().getVnombre()));
            this.getSelectedBaseLegal().setVnumero(this.getTipoNorma().concat(" - ").concat(StringUtils.upperCase(this.getNumeroNorma())));
            this.getSelectedBaseLegal().setNtiporangoid(this.getSelectedBaseLegal().getNtiporangoid());
            this.getSelectedBaseLegal().setNrangoid(this.getSelectedBaseLegal().getNrangoid());
            this.getSelectedBaseLegal().setNgobnacional(this.getChkGobNacional() ? BigDecimal.ONE : BigDecimal.ZERO);
            this.getSelectedBaseLegal().setNgobregional(this.getChkGobRegional() ? BigDecimal.ONE : BigDecimal.ZERO);
            this.getSelectedBaseLegal().setNgoblocal(this.getChkGobLocal() ? BigDecimal.ONE : BigDecimal.ZERO);
            this.getSelectedBaseLegal().setNmancomunidades(this.getChkMancomunidades() ? BigDecimal.ONE : BigDecimal.ZERO);
            this.getSelectedBaseLegal().setNdestacado(this.getChkDestacado() ? BigDecimal.ONE : BigDecimal.ZERO);
            this.getSelectedBaseLegal().setNcodigowiki(this.getCodigoWiki());
            this.getSelectedBaseLegal().setVsumilla(this.getSelectedBaseLegal().getVsumilla().trim());
            this.getSelectedBaseLegal().setDfechavigencia(this.getSelectedBaseLegal().getDfechavigencia());
            this.getSelectedBaseLegal().setVtema(this.getSelectedBaseLegal().getVtema());
            this.getSelectedBaseLegal().setNestadoid(BigDecimal.valueOf(Long.valueOf(Constante.ESTADO_BASELEGAL_PUBLICADO)));
            this.getSelectedBaseLegal().setDfechapublicacion(new Date());
            this.getSelectedBaseLegal().setVusuariomodificacion(user.getVlogin());
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
            baseHist.setNtiporangoid(this.getSelectedBaseLegal().getNtiporangoid());
            baseHist.setNrangoid(this.getSelectedBaseLegal().getNrangoid());
            baseHist.setNgobnacional(this.getSelectedBaseLegal().getNgobnacional());
            baseHist.setNgobregional(this.getSelectedBaseLegal().getNgobregional());
            baseHist.setNgoblocal(this.getSelectedBaseLegal().getNgoblocal());
            baseHist.setNmancomunidades(this.getSelectedBaseLegal().getNmancomunidades());
            baseHist.setNdestacado(this.getSelectedBaseLegal().getNdestacado());
            baseHist.setVsumilla(this.getSelectedBaseLegal().getVsumilla());
            baseHist.setDfechavigencia(this.getSelectedBaseLegal().getDfechavigencia());
            baseHist.setDfechapublicacion(this.getSelectedBaseLegal().getDfechapublicacion());
            baseHist.setVtema(this.getSelectedBaseLegal().getVtema());
            baseHist.setNactivo(this.getSelectedBaseLegal().getNactivo());
            baseHist.setNestadoid(this.getSelectedBaseLegal().getNestadoid());
            baseHist.setNversion(BigDecimal.valueOf(hist.getNversion().intValue() + 1));
            baseHist.setVusuariocreacion(user.getVlogin());
            baseHist.setDfechacreacion(new Date());
            baseHist.setVusuariomodificacion(this.getSelectedBaseLegal().getVusuariomodificacion());
            baseHist.setDfechamodificacion(this.getSelectedBaseLegal().getDfechamodificacion());
            serviceHistorial.saveOrUpdate(baseHist);

            Tbaselegal tbaselegal = new Tbaselegal();
            BeanUtils.copyProperties(tbaselegal, this.getSelectedBaseLegal());

            String ruta0 = this.pathBL + this.getSelectedBaseLegal().getNbaselegalid().toString() + "\\" + BigDecimal.ZERO.toString() + "\\";
            String txt0 = this.getSelectedBaseLegal().getVnombre();
            GcmFileUtils.writeStringToFileServer(ruta0, "plain.txt", txt0);
            String ruta1 = this.pathBL + this.getSelectedBaseLegal().getNbaselegalid().toString() + "\\" + baseHist.getNversion().toString() + "\\";
            String txt1 = baseHist.getVnombre();
            GcmFileUtils.writeStringToFileServer(ruta1, "plain.txt", txt1);

            ArchivoService aservice = (ArchivoService) ServiceFinder.findBean("ArchivoService");
            Archivo archivo = aservice.getArchivoByBaseLegal(this.getSelectedBaseLegal());
            if (this.getUploadFile() != null) {
                ruta0 = this.path + this.getSelectedBaseLegal().getNbaselegalid().toString() + "\\" + BigDecimal.ZERO.toString() + "\\";
                archivo.setVnombre(this.getUploadFile().getFileName());
                archivo.setVruta(ruta0 + archivo.getVnombre());
                archivo.setVusuariomodificacion(user.getVlogin());
                archivo.setDfechamodificacion(new Date());
                aservice.saveOrUpdate(archivo);
                saveFile(ruta0);
            }

            ruta1 = this.path + this.getSelectedBaseLegal().getNbaselegalid().toString() + "\\" + baseHist.getNversion().toString() + "\\";
            ArchivoHistorialService aserviceHist = (ArchivoHistorialService) ServiceFinder.findBean("ArchivoHistorialService");
            ArchivoHist archivoHist = aserviceHist.getLastArchivoHistByBaseLegalHist(baseHist);
            archivoHist = archivoHist != null ? archivoHist : new ArchivoHist();
            archivoHist.setNarchivohistid(aserviceHist.getNextPK());
            archivoHist.setNhistorialid(baseHist.getNhistorialid());
            archivoHist.setNbaselegalid(baseHist.getNbaselegalid());
            archivoHist.setVnombre(archivo.getVnombre());
            archivoHist.setVruta(ruta1 + archivo.getVnombre());
            archivoHist.setVusuariocreacion(user.getVlogin());
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
                vinculo.setVusuariocreacion(user.getVlogin());
                vservice.saveOrUpdate(vinculo);

                BaseLegal blvinculada = service.getBaselegalById(v.getNbaselegalid());
                blvinculada.setNestadoid(v.getNestadoid());
                blvinculada.setDfechamodificacion(new Date());
                blvinculada.setVusuariomodificacion(user.getVlogin());
                service.saveOrUpdate(blvinculada);

                if (v.getNbaselegalid().toString().equals(Constante.ESTADO_BASELEGAL_MODIFICADA)
                        || v.getNbaselegalid().toString().equals(Constante.ESTADO_BASELEGAL_CONCORDADO)) {

                    ConocimientoService cservice = (ConocimientoService) ServiceFinder.findBean("ConocimientoService");
                    List<Consulta> listaConocimientos = cservice.getConcimientosByVinculoBaseLegalId(blvinculada.getNbaselegalid());
                    if (!CollectionUtils.isEmpty(listaConocimientos)) {
                        for (Consulta c : listaConocimientos) {
                            Conocimiento conocimiento = cservice.getConocimientoById(c.getIdconocimiento());
                            conocimiento.setDfechamodificacion(new Date());
                            conocimiento.setVusuariomodificacion(user.getVlogin());
                            String descripcionHtml = GcmFileUtils.readStringFromFileServer(conocimiento.getVruta(), "html.txt");
                            String descripcionPlain = GcmFileUtils.readStringFromFileServer(conocimiento.getVruta(), "plain.txt");
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
                            historial.setVusuariocreacion(user.getVlogin());
                            historialService.saveOrUpdate(historial);

                            GcmFileUtils.writeStringToFileServer(url, "html.txt", descripcionHtml);
                            GcmFileUtils.writeStringToFileServer(url, "plain.txt", descripcionPlain);

                            SeccionService seccionService = (SeccionService) ServiceFinder.findBean("SeccionService");
                            SeccionHistService seccionHistService = (SeccionHistService) ServiceFinder.findBean("SeccionHistService");
                            List<Seccion> listaSeccion = seccionService.getSeccionesByConocimiento(conocimiento.getNconocimientoid());
                            if (!CollectionUtils.isEmpty(listaSeccion)) {
                                String url0 = conocimiento.getVruta().concat("s");
                                String url1 = url.concat("s");
                                for (Seccion seccion : listaSeccion) {
                                    seccion.setDetalleHtml(GcmFileUtils.readStringFromFileServer(seccion.getVruta(), "html.txt"));
                                    ruta0 = url0.concat(seccion.getNorden().toString()).concat("/");
                                    seccion.setVruta(ruta0);
                                    seccion.setDfechamodificacion(new Date());
                                    seccion.setVusuariomodificacion(user.getVlogin());
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
                                    seccionHist.setVusuariocreacion(user.getVlogin());
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
                            vinculoC.setVusuariocreacion(user.getVlogin());
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
                                vinculoHist.setVusuariocreacion(user.getVlogin());
                                vinculoHistService.saveOrUpdate(vinculoHist);
                            }
                        }
                    }
                } else if (v.getNbaselegalid().toString().equals(Constante.ESTADO_BASELEGAL_DEROGADA)) {
                    ConocimientoService cservice = (ConocimientoService) ServiceFinder.findBean("ConocimientoService");
                    List<Consulta> listaConocimientos = cservice.getConcimientosByVinculoBaseLegalId(blvinculada.getNbaselegalid());
                    if (!CollectionUtils.isEmpty(listaConocimientos)) {
                        for (Consulta c : listaConocimientos) {
                            Conocimiento conocimiento = cservice.getConocimientoById(c.getIdconocimiento());
                            conocimiento.setNflgvinculo(BigDecimal.ONE);
                            conocimiento.setDfechamodificacion(new Date());
                            conocimiento.setVusuariomodificacion(user.getVlogin());
                        }
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
                vinculoHist.setVusuariocreacion(user.getVlogin());
                vserviceHist.saveOrUpdate(vinculoHist);
            }

            List<Asignacion> listaAsignacion = service.obtenerBaseLegalxAsig(this.getSelectedBaseLegal().getNbaselegalid(), user.getNusuarioid(), Constante.BASELEGAL);
            if (org.apache.commons.collections.CollectionUtils.isNotEmpty(listaAsignacion)) {
                Asignacion asignacion = listaAsignacion.get(0);
                asignacion.setNestadoid(BigDecimal.valueOf(Long.parseLong("2")));
                if (asignacion.getDfecharecepcion() == null) {
                    asignacion.setDfecharecepcion(new Date());
                }
                asignacion.setDfechaatencion(new Date());
                asignacion.setNaccionid(BigDecimal.valueOf(Long.parseLong("8")));
                AsignacionService asignacionService = (AsignacionService) ServiceFinder.findBean("AsignacionService");
                asignacionService.saveOrUpdate(asignacion);
            }

            this.setListaBaseLegal(service.getBaselegales());
            for (BaseLegal bl : this.getListaBaseLegal()) {
                bl.setArchivo(aservice.getArchivoByBaseLegal(bl));
            }
            FacesContext.getCurrentInstance().getExternalContext().redirect("/gescon/pages/baselegal/lista.xhtml");
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
    }

    public String toView() {
        try {
            this.cleanAttributes();
            int index = Integer.parseInt((String) JSFUtils.getRequestParameter("index"));
            if (!CollectionUtils.isEmpty(this.getFilteredListaBaseLegal())) {
                this.setSelectedBaseLegal(this.getFilteredListaBaseLegal().get(index));
            } else {
                this.setSelectedBaseLegal(this.getListaBaseLegal().get(index));
            }
            CategoriaService categoriaService = (CategoriaService) ServiceFinder.findBean("CategoriaService");
            this.setSelectedCategoria(categoriaService.getCategoriaById(this.getSelectedBaseLegal().getNcategoriaid()));
            index = this.getSelectedBaseLegal().getVnumero().indexOf("-");
            this.setTipoNorma(this.getSelectedBaseLegal().getVnumero().substring(0, index).trim());
            this.setNumeroNorma(this.getSelectedBaseLegal().getVnumero().substring(index + 1).trim());
            this.setChkGobNacional(this.getSelectedBaseLegal().getNgobnacional().equals(BigDecimal.ONE));
            this.setChkGobRegional(this.getSelectedBaseLegal().getNgobregional().equals(BigDecimal.ONE));
            this.setChkGobLocal(this.getSelectedBaseLegal().getNgoblocal().equals(BigDecimal.ONE));
            this.setChkMancomunidades(this.getSelectedBaseLegal().getNmancomunidades().equals(BigDecimal.ONE));
            this.setChkDestacado(this.getSelectedBaseLegal().getNdestacado().equals(BigDecimal.ONE));
            RangoService rangoService = (RangoService) ServiceFinder.findBean("RangoService");
            this.setListaRangos(new Items(rangoService.getRangosActivosByTipo(this.getSelectedBaseLegal().getNtiporangoid()), null, "nrangoid", "vnombre").getItems());
            BaseLegalService service = (BaseLegalService) ServiceFinder.findBean("BaseLegalService");
            this.setListaTarget(service.getTbaselegalesLinkedById(this.getSelectedBaseLegal().getNbaselegalid()));
            this.setFilteredListaBaseLegal(new ArrayList());
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
        return "/pages/baselegal/ver?faces-redirect=true";
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

    public void onTransfer(TransferEvent event) {
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

    public void activar(ActionEvent event) {
        try {
            if (event != null) {
                if (this.getSelectedBaseLegal() != null) {
                    LoginMB loginMB = (LoginMB) JSFUtils.getSessionAttribute("loginMB");
                    User user = loginMB.getUser();
                    BaseLegalService service = (BaseLegalService) ServiceFinder.findBean("BaseLegalService");
                    this.getSelectedBaseLegal().setNactivo(BigDecimal.ONE);
                    this.getSelectedBaseLegal().setDfechamodificacion(new Date());
                    this.getSelectedBaseLegal().setVusuariomodificacion(user.getVlogin());
                    service.saveOrUpdate(this.getSelectedBaseLegal());
                    this.setListaBaseLegal(service.getBaselegales());
                } else {
                    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, Constante.SEVERETY_ALERTA, "Debe seleccionar la base legal a activar.");
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
                if (this.getSelectedBaseLegal() != null) {
                    LoginMB loginMB = (LoginMB) JSFUtils.getSessionAttribute("loginMB");
                    User user = loginMB.getUser();
                    BaseLegalService service = (BaseLegalService) ServiceFinder.findBean("BaseLegalService");
                    this.getSelectedBaseLegal().setNactivo(BigDecimal.ZERO);
                    this.getSelectedBaseLegal().setNdestacado(BigDecimal.ZERO);
                    this.getSelectedBaseLegal().setDfechamodificacion(new Date());
                    this.getSelectedBaseLegal().setVusuariomodificacion(user.getVlogin());
                    service.saveOrUpdate(this.getSelectedBaseLegal());
                    this.setListaBaseLegal(service.getBaselegales());
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
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR.", "Ingrese la calificacion a la base legal.");
                FacesContext.getCurrentInstance().addMessage(null, message);
                return;
            }
            if (StringUtils.isBlank(this.getComentarioCalificacion())) {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR.", "Ingrese un comentario a la base legal.");
                FacesContext.getCurrentInstance().addMessage(null, message);
                return;
            }
            LoginMB loginMB = (LoginMB) JSFUtils.getSessionAttribute("loginMB");
            User user = loginMB.getUser();
            CalificacionBaseLegalService calificacionService = (CalificacionBaseLegalService) ServiceFinder.findBean("CalificacionBaseLegalService");
            CalificacionBaselegal cal = new CalificacionBaselegal();
            cal.setNcalificacionid(calificacionService.getNextPK());
            cal.setNbaselegalid(this.getSelectedBaseLegal().getNbaselegalid());
            cal.setNcalificacion(this.getCalificacion());
            cal.setVcomentario(StringUtils.capitalize(this.getComentarioCalificacion().trim()));
            cal.setDfechacreacion(new Date());
            cal.setVusuariocreacion(user.getVlogin());
            calificacionService.saveOrUpdate(cal);
            this.setListaCalificacion(calificacionService.getCalificacionesByConocimiento(this.getSelectedBaseLegal().getNbaselegalid()));
            if (org.apache.commons.collections.CollectionUtils.isNotEmpty(this.getListaCalificacion())) {
                UserService userService = (UserService) ServiceFinder.findBean("UserService");
                for (CalificacionBaselegal c : this.getListaCalificacion()) {
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
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR.", "Ingrese la calificacion a la base legal.");
                FacesContext.getCurrentInstance().addMessage(null, message);
                return;
            }
            if (StringUtils.isBlank(this.getSelectedCalificacion().getVcomentario())) {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR.", "Ingrese un comentario a la base legal.");
                FacesContext.getCurrentInstance().addMessage(null, message);
                return;
            }
            LoginMB loginMB = (LoginMB) JSFUtils.getSessionAttribute("loginMB");
            User user = loginMB.getUser();
            CalificacionBaseLegalService calificacionService = (CalificacionBaseLegalService) ServiceFinder.findBean("CalificacionBaseLegalService");
            this.getSelectedCalificacion().setNcalificacion(this.getSelectedCalificacion().getNcalificacion());
            this.getSelectedCalificacion().setVcomentario(StringUtils.capitalize(this.getSelectedCalificacion().getVcomentario().trim()));
            this.getSelectedCalificacion().setDfechamodificacion(new Date());
            this.getSelectedCalificacion().setVusuariomodificacion(user.getVlogin());
            calificacionService.saveOrUpdate(this.getSelectedCalificacion());
            this.setListaCalificacion(calificacionService.getCalificacionesByConocimiento(this.getSelectedBaseLegal().getNbaselegalid()));
            if (org.apache.commons.collections.CollectionUtils.isNotEmpty(this.getListaCalificacion())) {
                UserService userService = (UserService) ServiceFinder.findBean("UserService");
                for (CalificacionBaselegal c : this.getListaCalificacion()) {
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
            CalificacionBaseLegalService calificacionService = (CalificacionBaseLegalService) ServiceFinder.findBean("CalificacionBaseLegalService");
            calificacionService.delete(this.getSelectedCalificacion().getNcalificacionid());
            this.setListaCalificacion(calificacionService.getCalificacionesByConocimiento(this.getSelectedBaseLegal().getNbaselegalid()));
            if (org.apache.commons.collections.CollectionUtils.isNotEmpty(this.getListaCalificacion())) {
                UserService userService = (UserService) ServiceFinder.findBean("UserService");
                for (CalificacionBaselegal c : this.getListaCalificacion()) {
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
        for (BaseLegal b : this.getListaBaseLegal()) {
            HSSFRow row = sheet.getRow(i);
            for (int j = 0; j < row.getPhysicalNumberOfCells(); j++) {
                HSSFCell cell = row.getCell(j);
                if (i % 2 == 0) {
                    if (j > 0) {
                        cell.setCellStyle(centerGrayStyle);
                    } else {
                        cell.setCellStyle(grayBG);
                        cell.setCellValue(b.getVnumero());
                    }
                } else {
                    if (j > 0) {
                        cell.setCellStyle(centerStyle);
                    } else {
                        cell.setCellValue(b.getVnumero());
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

    public void listarWikisPublicados(ActionEvent event) {
        try {
            ConocimientoService conocimientoService = (ConocimientoService) ServiceFinder.findBean("ConocimientoService");
            this.setListaWikis(conocimientoService.getConocimientosActivedPublicByType(Constante.WIKI));
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
    }

    public void onRowSelect(ActionEvent event) {
        try {
            if (this.getSelectedWiki() != null) {
                this.setCodigoWiki(this.getSelectedWiki().getNconocimientoid());
            }
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
    }
}
