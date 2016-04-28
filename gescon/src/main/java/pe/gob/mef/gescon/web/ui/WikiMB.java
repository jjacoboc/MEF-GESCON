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
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFFontFormatting;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.jsoup.Jsoup;
import org.primefaces.component.selectonemenu.SelectOneMenu;
import org.primefaces.context.RequestContext;
import org.primefaces.event.NodeSelectEvent;
import org.primefaces.event.TransferEvent;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.DualListModel;
import org.primefaces.model.TreeNode;
import pe.gob.mef.gescon.common.Constante;
import pe.gob.mef.gescon.hibernate.domain.ThistorialId;
import pe.gob.mef.gescon.hibernate.domain.TseccionHistId;
import pe.gob.mef.gescon.hibernate.domain.TvinculoHistId;
import pe.gob.mef.gescon.service.AsignacionService;
import pe.gob.mef.gescon.service.CalificacionService;
import pe.gob.mef.gescon.service.CategoriaService;
import pe.gob.mef.gescon.service.ConocimientoService;
import pe.gob.mef.gescon.service.ConsultaService;
import pe.gob.mef.gescon.service.DiscusionHistService;
import pe.gob.mef.gescon.service.DiscusionSeccionHistService;
import pe.gob.mef.gescon.service.DiscusionSeccionService;
import pe.gob.mef.gescon.service.DiscusionService;
import pe.gob.mef.gescon.service.HistorialService;
import pe.gob.mef.gescon.service.SeccionHistService;
import pe.gob.mef.gescon.service.SeccionService;
import pe.gob.mef.gescon.service.UserService;
import pe.gob.mef.gescon.service.VinculoHistService;
import pe.gob.mef.gescon.service.VinculoService;
import pe.gob.mef.gescon.util.GcmFileUtils;
import pe.gob.mef.gescon.util.JSFUtils;
import pe.gob.mef.gescon.util.ServiceFinder;
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
import pe.gob.mef.gescon.web.bean.Seccion;
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
public class WikiMB implements Serializable {

    private final String path = "wk/";
    private List<Conocimiento> listaWiki;
    private List<Conocimiento> filteredListaWiki;
    private Conocimiento selectedWiki;
    private TreeNode tree;
    private Categoria selectedCategoria;
    private Boolean chkDestacado;
    private String nombre;
    private String descripcionHtml;
    private String descripcionPlain;
    private List<Seccion> listaSeccion;
    private Seccion selectedSeccion;
    private String titulo;
    private String detalleHtml;
    private String detallePlain;
    private Discusion selectedDiscusion;
    private List<DiscusionSeccion> listaDiscusionSeccion;
    private DiscusionSeccion selectedDiscusionSeccion;
    private String tituloDiscusion;
    private BigDecimal tipoDiscusion;
    private String discusionHtml;
    private String discusionPlain;
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

    /**
     * Creates a new instance of WikiMB
     */
    public WikiMB() {
    }

    public List<Conocimiento> getListaWiki() {
        return listaWiki;
    }

    public void setListaWiki(List<Conocimiento> listaWiki) {
        this.listaWiki = listaWiki;
    }

    public List<Conocimiento> getFilteredListaWiki() {
        return filteredListaWiki;
    }

    public void setFilteredListaWiki(List<Conocimiento> filteredListaWiki) {
        this.filteredListaWiki = filteredListaWiki;
    }

    public Conocimiento getSelectedWiki() {
        return selectedWiki;
    }

    public void setSelectedWiki(Conocimiento selectedWiki) {
        this.selectedWiki = selectedWiki;
    }

    public TreeNode getTree() {
        return tree;
    }

    public void setTree(TreeNode tree) {
        this.tree = tree;
    }

    public Categoria getSelectedCategoria() {
        return selectedCategoria;
    }

    public void setSelectedCategoria(Categoria selectedCategoria) {
        this.selectedCategoria = selectedCategoria;
    }

    public Boolean getChkDestacado() {
        return chkDestacado;
    }

    public void setChkDestacado(Boolean chkDestacado) {
        this.chkDestacado = chkDestacado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcionHtml() {
        return descripcionHtml;
    }

    public void setDescripcionHtml(String descripcionHtml) {
        this.descripcionHtml = descripcionHtml;
    }

    public String getDescripcionPlain() {
        return descripcionPlain;
    }

    public void setDescripcionPlain(String descripcionPlain) {
        this.descripcionPlain = descripcionPlain;
    }

    public List<Seccion> getListaSeccion() {
        return listaSeccion;
    }

    public void setListaSeccion(List<Seccion> listaSeccion) {
        this.listaSeccion = listaSeccion;
    }

    public Seccion getSelectedSeccion() {
        return selectedSeccion;
    }

    public void setSelectedSeccion(Seccion selectedSeccion) {
        this.selectedSeccion = selectedSeccion;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDetalleHtml() {
        return detalleHtml;
    }

    public void setDetalleHtml(String detalleHtml) {
        this.detalleHtml = detalleHtml;
    }

    public String getDetallePlain() {
        return detallePlain;
    }

    public void setDetallePlain(String detallePlain) {
        this.detallePlain = detallePlain;
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

    public BigDecimal getIdTipoConocimiento() {
        return idTipoConocimiento;
    }

    public void setIdTipoConocimiento(BigDecimal idTipoConocimiento) {
        this.idTipoConocimiento = idTipoConocimiento;
    }

    public List<Consulta> getListaSourceVinculos() {
        return listaSourceVinculos;
    }

    public void setListaSourceVinculos(List<Consulta> listaSourceVinculos) {
        this.listaSourceVinculos = listaSourceVinculos;
    }

    public List<Consulta> getListaTargetVinculos() {
        return listaTargetVinculos;
    }

    public void setListaTargetVinculos(List<Consulta> listaTargetVinculos) {
        this.listaTargetVinculos = listaTargetVinculos;
    }

    public DualListModel<Consulta> getPickList() {
        return pickList;
    }

    public void setPickList(DualListModel<Consulta> pickList) {
        this.pickList = pickList;
    }

    public List<Consulta> getListaSourceVinculosBL() {
        return listaSourceVinculosBL;
    }

    public void setListaSourceVinculosBL(List<Consulta> listaSourceVinculosBL) {
        this.listaSourceVinculosBL = listaSourceVinculosBL;
    }

    public List<Consulta> getListaTargetVinculosBL() {
        return listaTargetVinculosBL;
    }

    public void setListaTargetVinculosBL(List<Consulta> listaTargetVinculosBL) {
        this.listaTargetVinculosBL = listaTargetVinculosBL;
    }

    public List<Consulta> getListaSourceVinculosPR() {
        return listaSourceVinculosPR;
    }

    public void setListaSourceVinculosPR(List<Consulta> listaSourceVinculosPR) {
        this.listaSourceVinculosPR = listaSourceVinculosPR;
    }

    public List<Consulta> getListaTargetVinculosPR() {
        return listaTargetVinculosPR;
    }

    public void setListaTargetVinculosPR(List<Consulta> listaTargetVinculosPR) {
        this.listaTargetVinculosPR = listaTargetVinculosPR;
    }

    public List<Consulta> getListaSourceVinculosWK() {
        return listaSourceVinculosWK;
    }

    public void setListaSourceVinculosWK(List<Consulta> listaSourceVinculosWK) {
        this.listaSourceVinculosWK = listaSourceVinculosWK;
    }

    public List<Consulta> getListaTargetVinculosWK() {
        return listaTargetVinculosWK;
    }

    public void setListaTargetVinculosWK(List<Consulta> listaTargetVinculosWK) {
        this.listaTargetVinculosWK = listaTargetVinculosWK;
    }

    public List<Consulta> getListaSourceVinculosOM() {
        return listaSourceVinculosOM;
    }

    public void setListaSourceVinculosOM(List<Consulta> listaSourceVinculosOM) {
        this.listaSourceVinculosOM = listaSourceVinculosOM;
    }

    public List<Consulta> getListaTargetVinculosOM() {
        return listaTargetVinculosOM;
    }

    public void setListaTargetVinculosOM(List<Consulta> listaTargetVinculosOM) {
        this.listaTargetVinculosOM = listaTargetVinculosOM;
    }

    public List<Consulta> getListaSourceVinculosBP() {
        return listaSourceVinculosBP;
    }

    public void setListaSourceVinculosBP(List<Consulta> listaSourceVinculosBP) {
        this.listaSourceVinculosBP = listaSourceVinculosBP;
    }

    public List<Consulta> getListaTargetVinculosBP() {
        return listaTargetVinculosBP;
    }

    public void setListaTargetVinculosBP(List<Consulta> listaTargetVinculosBP) {
        this.listaTargetVinculosBP = listaTargetVinculosBP;
    }

    public List<Consulta> getListaSourceVinculosCT() {
        return listaSourceVinculosCT;
    }

    public void setListaSourceVinculosCT(List<Consulta> listaSourceVinculosCT) {
        this.listaSourceVinculosCT = listaSourceVinculosCT;
    }

    public List<Consulta> getListaTargetVinculosCT() {
        return listaTargetVinculosCT;
    }

    public void setListaTargetVinculosCT(List<Consulta> listaTargetVinculosCT) {
        this.listaTargetVinculosCT = listaTargetVinculosCT;
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

    public String getSelectedSwitch() {
        return selectedSwitch;
    }

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

    @PostConstruct
    public void init() {
        try {
            ConocimientoService conocimientoService = (ConocimientoService) ServiceFinder.findBean("ConocimientoService");
            this.setListaWiki(conocimientoService.getConocimientosByType(Constante.WIKI));
            this.setListaSourceVinculos(new ArrayList<Consulta>());
            this.setListaTargetVinculos(new ArrayList<Consulta>());
            this.setPickList(new DualListModel<Consulta>(this.getListaSourceVinculos(), this.getListaTargetVinculos()));
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
    }

    public void setSelectedRow(ActionEvent event) {
        try {
            if (event != null) {
                int index = Integer.parseInt((String) JSFUtils.getRequestParameter("index"));
                if (!CollectionUtils.isEmpty(this.getFilteredListaWiki())) {
                    this.setSelectedWiki(this.getFilteredListaWiki().get(index));
                } else {
                    this.setSelectedWiki(this.getListaWiki().get(index));
                }
                this.setFilteredListaWiki(new ArrayList());
            }
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
    }

    public void clearAll() {
        try {
            this.setSelectedCategoria(null);
            this.setNombre(StringUtils.EMPTY);
            this.setDescripcionHtml(StringUtils.EMPTY);
            this.setSelectedSeccion(null);
            this.setTitulo(StringUtils.EMPTY);
            this.setDetalleHtml(StringUtils.EMPTY);
            this.setChkDestacado(false);
            this.setListaSeccion(new ArrayList());
            this.setListaSourceVinculos(new ArrayList());
            this.setListaTargetVinculos(new ArrayList());
            this.setListaTargetVinculosBL(new ArrayList());
            this.setListaTargetVinculosBP(new ArrayList());
            this.setListaTargetVinculosCT(new ArrayList());
            this.setListaTargetVinculosOM(new ArrayList());
            this.setListaTargetVinculosPR(new ArrayList());
            this.setListaTargetVinculosWK(new ArrayList());
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

    public void clearSection() {
        try {
            this.setSelectedSeccion(new Seccion());
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
            this.setDetalleHtml(JSFUtils.getRequestParameter("txtHtml"));
            if (StringUtils.isBlank(this.getTitulo())) {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR.", "Título de la sección requerido. Ingrese el título de la sección a agregar.");
                FacesContext.getCurrentInstance().addMessage(null, message);
                return;
            }
            if (StringUtils.isBlank(this.getDetalleHtml())) {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR.", "Detalle de la sección requerido. Ingrese el detalle de la sección a agregar.");
                FacesContext.getCurrentInstance().addMessage(null, message);
                return;
            }
            Seccion seccion = new Seccion();
            seccion.setVtitulo(this.getTitulo());
            seccion.setDetalleHtml(this.getDetalleHtml());
            seccion.setDetallePlain(Jsoup.parse(seccion.getDetalleHtml()).text());
            if (CollectionUtils.isEmpty(this.getListaSeccion())) {
                this.setListaSeccion(new ArrayList<Seccion>());
                seccion.setNorden(BigDecimal.ONE);
            } else {
                seccion.setNorden(BigDecimal.valueOf(this.getListaSeccion().size() + 1));
            }
            this.getListaSeccion().add(seccion);
            RequestContext.getCurrentInstance().execute("PF('secPanel').hide();");
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
            this.getSelectedSeccion().setDetalleHtml(JSFUtils.getRequestParameter("etxtHtml"));
            if (StringUtils.isBlank(this.getSelectedSeccion().getVtitulo())) {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR.", "Título de la sección requerido. Ingrese el título de la sección a agregar.");
                FacesContext.getCurrentInstance().addMessage(null, message);
                return;
            }
            if (StringUtils.isBlank(this.getSelectedSeccion().getDetalleHtml())) {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR.", "Detalle de la sección requerido. Ingrese el detalle de la sección a agregar.");
                FacesContext.getCurrentInstance().addMessage(null, message);
                return;
            }
            RequestContext.getCurrentInstance().execute("PF('esecPanel').hide();");
        } catch (Exception e) {
            e.getMessage();
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
                discusion.setNconocimientoid(this.getSelectedWiki().getNconocimientoid());
                discusion.setDfechacreacion(new Date());
                discusion.setVusuariocreacion(user.getVlogin());
                discusionService.saveOrUpdate(discusion);

                discusionHist = new DiscusionHist();
                discusionHist.setNnumversion(BigDecimal.ONE);
            } else {
                discusionHist = discusionHistService.getDiscusionHistByConocimiento(this.getSelectedWiki().getNconocimientoid());
                int version = discusionHist.getNnumversion().intValue() + 1;
                discusionHist.setNnumversion(BigDecimal.valueOf(version));
            }
            discusionHist.setNdiscusionhid(discusionHistService.getNextPK());
            discusionHist.setNconocimientoid(this.getSelectedWiki().getNconocimientoid());
            discusionHist.setDfechacreacion(new Date());
            discusionHist.setVusuariocreacion(user.getVlogin());
            discusionHistService.saveOrUpdate(discusionHist);

            if (CollectionUtils.isNotEmpty(this.getListaDiscusionSeccion())) {
                String url0 = this.path.concat(this.getSelectedWiki().getNconocimientoid().toString()).concat("/0/d/").concat(BigDecimal.ZERO.toString()).concat("/s");
                String url1 = this.path.concat(this.getSelectedWiki().getNconocimientoid().toString()).concat("/0/d/").concat(discusionHist.getNnumversion().toString()).concat("/s");
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
                        if (id.equals(Constante.WIKI)) {
                            filter = filter.concat(",").concat(this.getSelectedWiki().getNconocimientoid().toString());
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
                    this.setPickList(new DualListModel<Consulta>(this.getListaSourceVinculos(), this.getListaTargetVinculos()));
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

    public String toSave() {
        try {
            this.clearAll();
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
        return "/pages/wiki/nuevo?faces-redirect=true";
    }

    public void save(ActionEvent event) {
        try {
            this.setDescripcionHtml(JSFUtils.getRequestParameter("descHtml"));
            if (this.getSelectedCategoria() == null) {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR.", "Categoría del wiki requerida. Seleccione la categoría del wiki a registrar.");
                FacesContext.getCurrentInstance().addMessage(null, message);
                return;
            }
            if (StringUtils.isBlank(this.getNombre())) {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR.", "Título del wiki requerido. Ingrese el título del wiki a registrar.");
                FacesContext.getCurrentInstance().addMessage(null, message);
                return;
            }
            if (StringUtils.isBlank(this.getDescripcionHtml())) {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR.", "Descripción del wiki requerido. Ingrese la descripción del wiki a registrar.");
                FacesContext.getCurrentInstance().addMessage(null, message);
                return;
            }
            /* Validando si la cantidad de wikis destacados llegó al límite (10 max.).*/
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
            /* Validando si exiten vínculos de bases legales derogadas */
            int contador = 0;
            if (CollectionUtils.isNotEmpty(this.getListaTargetVinculosBL())) {
                for (Consulta c : this.getListaTargetVinculosBL()) {
                    if (c.getIdEstado().toString().equals(Constante.ESTADO_BASELEGAL_DEROGADA)) {
                        contador++;
                    }
                }
            }

            LoginMB loginMB = (LoginMB) JSFUtils.getSessionAttribute("loginMB");
            User user = loginMB.getUser();
            ConocimientoService conocimientoService = (ConocimientoService) ServiceFinder.findBean("ConocimientoService");
            Conocimiento wiki = new Conocimiento();
            wiki.setNtipoconocimientoid(Constante.WIKI);
            wiki.setNconocimientoid(conocimientoService.getNextPK());
            wiki.setNcategoriaid(this.getSelectedCategoria().getNcategoriaid());
            wiki.setVtitulo(StringUtils.upperCase(this.getNombre()));
            this.setDescripcionPlain(Jsoup.parse(this.getDescripcionHtml()).text());
            if (this.getDescripcionPlain().length() < 400) {
                wiki.setVdescripcion(StringUtils.capitalize(this.getDescripcionPlain()));
            } else {
                wiki.setVdescripcion(StringUtils.capitalize(this.getDescripcionPlain().substring(0, 300)));
            }
            wiki.setNactivo(BigDecimal.ONE);
            wiki.setNdestacado(this.getChkDestacado() ? BigDecimal.ONE : BigDecimal.ZERO);
            if (contador > 0) {
                wiki.setNflgvinculo(BigDecimal.ONE);
            } else {
                wiki.setNflgvinculo(BigDecimal.ZERO);
            }
            if (this.getSelectedCategoria().getNflagwiki().equals(BigDecimal.ONE)) {
                wiki.setNsituacionid(BigDecimal.valueOf(Long.parseLong(Constante.SITUACION_POR_VERIFICAR)));
            } else {
                wiki.setNsituacionid(BigDecimal.valueOf(Long.parseLong(Constante.SITUACION_PUBLICADO)));
                wiki.setDfechapublicacion(new Date());
            }
            String np0 = this.path.concat(wiki.getNconocimientoid().toString()).concat("/0/");
            wiki.setVruta(np0);
            wiki.setDfechacreacion(new Date());
            wiki.setVusuariocreacion(user.getVlogin());
            conocimientoService.saveOrUpdate(wiki);

            GcmFileUtils.writeStringToFileServer(np0, "html.txt", this.getDescripcionHtml());
            GcmFileUtils.writeStringToFileServer(np0, "plain.txt", this.getDescripcionPlain());

            String np1 = this.path.concat(wiki.getNconocimientoid().toString()).concat("/1/");
            HistorialService historialService = (HistorialService) ServiceFinder.findBean("HistorialService");
            ThistorialId thistorialId = new ThistorialId();
            thistorialId.setNconocimientoid(wiki.getNconocimientoid());
            thistorialId.setNhistorialid(historialService.getNextPK());
            Historial historial = new Historial();
            historial.setId(thistorialId);
            historial.setNtipoconocimientoid(Constante.WIKI);
            historial.setNcategoriaid(this.getSelectedCategoria().getNcategoriaid());
            historial.setVtitulo(this.getNombre());
            historial.setNactivo(BigDecimal.ONE);
            historial.setNsituacionid(wiki.getNsituacionid());
            historial.setVruta(np1);
            historial.setNnumversion(BigDecimal.ONE);
            historial.setDfechacreacion(new Date());
            historial.setVusuariocreacion(user.getVlogin());
            historialService.saveOrUpdate(historial);

            GcmFileUtils.writeStringToFileServer(np1, "html.txt", this.getDescripcionHtml());
            GcmFileUtils.writeStringToFileServer(np1, "plain.txt", this.getDescripcionPlain());

            if (CollectionUtils.isNotEmpty(this.getListaSeccion())) {
                String url0 = this.path.concat(wiki.getNconocimientoid().toString()).concat("/0/s");
                String url1 = this.path.concat(wiki.getNconocimientoid().toString()).concat("/1/s");
                SeccionService seccionService = (SeccionService) ServiceFinder.findBean("SeccionService");
                SeccionHistService seccionHistService = (SeccionHistService) ServiceFinder.findBean("SeccionHistService");
                for (Seccion seccion : this.getListaSeccion()) {
                    String ruta0 = url0.concat(seccion.getNorden().toString()).concat("/");
                    seccion.setVruta(ruta0);
                    seccion.setNseccionid(seccionService.getNextPK());
                    seccion.setNconocimientoid(wiki.getNconocimientoid());
                    seccion.setDfechacreacion(new Date());
                    seccion.setVusuariocreacion(user.getVlogin());
                    seccionService.saveOrUpdate(seccion);

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
                    seccionHist.setVusuariocreacion(user.getVlogin());
                    seccionHist.setDfechacreacion(new Date());
                    seccionHistService.saveOrUpdate(seccionHist);

                    GcmFileUtils.writeStringToFileServer(ruta1, "html.txt", this.getDetalleHtml());
                    GcmFileUtils.writeStringToFileServer(ruta1, "plain.txt", this.getDetallePlain());
                }
            }

            this.setListaTargetVinculos(new ArrayList());
            this.getListaTargetVinculos().addAll(this.getListaTargetVinculosBL());
            this.getListaTargetVinculos().addAll(this.getListaTargetVinculosBP());
            this.getListaTargetVinculos().addAll(this.getListaTargetVinculosCT());
            this.getListaTargetVinculos().addAll(this.getListaTargetVinculosOM());
            this.getListaTargetVinculos().addAll(this.getListaTargetVinculosPR());
            this.getListaTargetVinculos().addAll(this.getListaTargetVinculosWK());

            if (CollectionUtils.isNotEmpty(this.getListaTargetVinculos())) {
                VinculoService vinculoService = (VinculoService) ServiceFinder.findBean("VinculoService");
                VinculoHistService vinculoHistService = (VinculoHistService) ServiceFinder.findBean("VinculoHistService");
                for (Consulta consulta : this.getListaTargetVinculos()) {
                    Vinculo vinculo = new Vinculo();
                    vinculo.setNvinculoid(vinculoService.getNextPK());
                    vinculo.setNconocimientoid(wiki.getNconocimientoid());
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
                    vinculoHist.setDfechacreacion(new Date());
                    vinculoHist.setVusuariocreacion(user.getVlogin());
                    vinculoHistService.saveOrUpdate(vinculoHist);
                }
            }
            Asignacion asignacion = new Asignacion();
            AsignacionService serviceasig = (AsignacionService) ServiceFinder.findBean("AsignacionService");
            asignacion.setNasignacionid(serviceasig.getNextPK());
            asignacion.setNtipoconocimientoid(Constante.WIKI);
            asignacion.setNconocimientoid(wiki.getNconocimientoid());
            asignacion.setNestadoid(BigDecimal.valueOf(Long.parseLong("1")));
            CategoriaService categoriaService = (CategoriaService) ServiceFinder.findBean("CategoriaService");
            asignacion.setNusuarioid(categoriaService.getCategoriaById(wiki.getNcategoriaid()).getNmoderador());
            asignacion.setDfechaasignacion(new Date());
            asignacion.setDfechacreacion(new Date());
            serviceasig.saveOrUpdate(asignacion);

            this.setListaWiki(conocimientoService.getConocimientosByType(Constante.WIKI));
            FacesContext.getCurrentInstance().getExternalContext().redirect("/gescon/pages/wiki/lista.xhtml");
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
    }

    public String toView() {
        try {
            this.clearAll();
            int index = Integer.parseInt((String) JSFUtils.getRequestParameter("index"));
            if (!CollectionUtils.isEmpty(this.getFilteredListaWiki())) {
                this.setSelectedWiki(this.getFilteredListaWiki().get(index));
            } else {
                this.setSelectedWiki(this.getListaWiki().get(index));
            }
            CategoriaService categoriaService = (CategoriaService) ServiceFinder.findBean("CategoriaService");
            this.setSelectedCategoria(categoriaService.getCategoriaById(this.getSelectedWiki().getNcategoriaid()));
            this.setDescripcionHtml(GcmFileUtils.readStringFromFileServer(this.getSelectedWiki().getVruta(), "html.txt"));
            SeccionService seccionService = (SeccionService) ServiceFinder.findBean("SeccionService");
            this.setListaSeccion(seccionService.getSeccionesByConocimiento(this.getSelectedWiki().getNconocimientoid()));
            if (CollectionUtils.isNotEmpty(this.getListaSeccion())) {
                for (Seccion seccion : this.getListaSeccion()) {
                    seccion.setDetalleHtml(GcmFileUtils.readStringFromFileServer(seccion.getVruta(), "html.txt"));
                }
            }
            this.setChkDestacado(this.getSelectedWiki().getNdestacado().equals(BigDecimal.ONE));
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
            this.setListaTargetVinculosWK(conocimientoService.getConcimientosVinculados(map));
            this.setFilteredListaWiki(new ArrayList());
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
        return "/pages/wiki/ver?faces-redirect=true";
    }

    public String toEdit() {
        try {
            this.clearAll();
            int index = Integer.parseInt((String) JSFUtils.getRequestParameter("index"));
            if (!CollectionUtils.isEmpty(this.getFilteredListaWiki())) {
                this.setSelectedWiki(this.getFilteredListaWiki().get(index));
            } else {
                this.setSelectedWiki(this.getListaWiki().get(index));
            }
            CategoriaService categoriaService = (CategoriaService) ServiceFinder.findBean("CategoriaService");
            this.setSelectedCategoria(categoriaService.getCategoriaById(this.getSelectedWiki().getNcategoriaid()));
            this.setDescripcionHtml(GcmFileUtils.readStringFromFileServer(this.getSelectedWiki().getVruta(), "html.txt"));
            SeccionService seccionService = (SeccionService) ServiceFinder.findBean("SeccionService");
            this.setListaSeccion(seccionService.getSeccionesByConocimiento(this.getSelectedWiki().getNconocimientoid()));
            if (CollectionUtils.isNotEmpty(this.getListaSeccion())) {
                for (Seccion seccion : this.getListaSeccion()) {
                    seccion.setDetalleHtml(GcmFileUtils.readStringFromFileServer(seccion.getVruta(), "html.txt"));
                }
            }
            this.setChkDestacado(this.getSelectedWiki().getNdestacado().equals(BigDecimal.ONE));
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
            this.setListaTargetVinculosWK(conocimientoService.getConcimientosVinculados(map));
            this.setFilteredListaWiki(new ArrayList());
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
        return "/pages/wiki/editar?faces-redirect=true";
    }

    public void edit(ActionEvent event) {
        try {
            this.setDescripcionHtml(JSFUtils.getRequestParameter("descHtml"));
            if (this.getSelectedCategoria() == null) {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR.", "Categoría del wiki requerida. Seleccione la categoría del wiki a registrar.");
                FacesContext.getCurrentInstance().addMessage(null, message);
                return;
            }
            if (StringUtils.isBlank(this.getSelectedWiki().getVtitulo())) {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR.", "Título del wiki requerido. Ingrese el título del wiki a registrar.");
                FacesContext.getCurrentInstance().addMessage(null, message);
                return;
            }
            if (StringUtils.isBlank(this.getDescripcionHtml())) {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR.", "Descripción del wiki requerido. Ingrese la descripción del wiki a registrar.");
                FacesContext.getCurrentInstance().addMessage(null, message);
                return;
            }
            if (this.getSelectedWiki().getNdestacado().equals(BigDecimal.ZERO) && this.getChkDestacado()) {
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
            /* Validando si exiten vínculos de bases legales derogadas */
            int contador = 0;
            if (CollectionUtils.isNotEmpty(this.getListaTargetVinculosBL())) {
                for (Consulta c : this.getListaTargetVinculosBL()) {
                    if (c.getIdEstado().toString().equals(Constante.ESTADO_BASELEGAL_DEROGADA)) {
                        contador++;
                    }
                }
            }
            LoginMB loginMB = (LoginMB) JSFUtils.getSessionAttribute("loginMB");
            User user = loginMB.getUser();
            ConocimientoService conocimientoService = (ConocimientoService) ServiceFinder.findBean("ConocimientoService");
            this.setDescripcionPlain(Jsoup.parse(this.getDescripcionHtml()).text());
            this.getSelectedWiki().setNcategoriaid(this.getSelectedCategoria().getNcategoriaid());
            this.getSelectedWiki().setVtitulo(StringUtils.upperCase(this.getSelectedWiki().getVtitulo()));
            if (this.getDescripcionPlain().length() < 400) {
                this.getSelectedWiki().setVdescripcion(StringUtils.capitalize(this.getDescripcionPlain()));
            } else {
                this.getSelectedWiki().setVdescripcion(StringUtils.capitalize(this.getDescripcionPlain().substring(0, 300)));
            }
            if (contador > 0) {
                this.getSelectedWiki().setNflgvinculo(BigDecimal.ONE);
            } else {
                this.getSelectedWiki().setNflgvinculo(BigDecimal.ZERO);
            }
            this.getSelectedWiki().setNdestacado(this.getChkDestacado() ? BigDecimal.ONE : BigDecimal.ZERO);
            this.getSelectedWiki().setDfechamodificacion(new Date());
            this.getSelectedWiki().setVusuariomodificacion(user.getVlogin());
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
            String url = this.path.concat(this.getSelectedWiki().getNconocimientoid().toString()).concat("/").concat(Integer.toString(lastversion + 1)).concat("/");

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
            historial.setVusuariocreacion(user.getVlogin());
            historialService.saveOrUpdate(historial);

            GcmFileUtils.writeStringToFileServer(url, "html.txt", this.getDescripcionHtml());
            GcmFileUtils.writeStringToFileServer(url, "plain.txt", this.getDescripcionPlain());

            if (CollectionUtils.isNotEmpty(this.getListaSeccion())) {
                String url0 = this.getSelectedWiki().getVruta().concat("s");
                String url1 = url.concat("s");
                SeccionService seccionService = (SeccionService) ServiceFinder.findBean("SeccionService");
                SeccionHistService seccionHistService = (SeccionHistService) ServiceFinder.findBean("SeccionHistService");
                for (Seccion seccion : this.getListaSeccion()) {
                    String ruta0 = url0.concat(seccion.getNorden().toString()).concat("/");
                    seccion.setVruta(ruta0);
                    if (seccion.getNseccionid() != null) {
                        seccion.setDfechamodificacion(new Date());
                        seccion.setVusuariomodificacion(user.getVlogin());
                    } else {
                        seccion.setNseccionid(seccionService.getNextPK());
                        seccion.setNconocimientoid(this.getSelectedWiki().getNconocimientoid());
                        seccion.setDfechacreacion(new Date());
                        seccion.setVusuariocreacion(user.getVlogin());
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
                    seccionHist.setVusuariocreacion(user.getVlogin());
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

            if (CollectionUtils.isNotEmpty(this.getListaTargetVinculos())) {
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
                    vinculo.setVusuariocreacion(user.getVlogin());
                    vinculoService.saveOrUpdate(vinculo);

                    TvinculoHistId vinculoHistId = new TvinculoHistId();
                    vinculoHistId.setNvinculohid(vinculoHistService.getNextPK());
                    vinculoHistId.setNconocimientoid(thistorialId.getNconocimientoid());
                    vinculoHistId.setNhistorialid(thistorialId.getNhistorialid());
                    VinculoHist vinculoHist = new VinculoHist();
                    vinculoHist.setId(vinculoHistId);
                    vinculoHist.setNconocimientovinc(vinculo.getNconocimientovinc());
                    vinculoHist.setDfechacreacion(new Date());
                    vinculoHist.setVusuariocreacion(user.getVlogin());
                    vinculoHistService.saveOrUpdate(vinculoHist);
                }
            }
            this.setListaWiki(conocimientoService.getConocimientosByType(Constante.WIKI));
            FacesContext.getCurrentInstance().getExternalContext().redirect("/gescon/pages/wiki/lista.xhtml");
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
    }

    public String toPost() {
        try {
            this.clearAll();
            int index = Integer.parseInt((String) JSFUtils.getRequestParameter("index"));
            if (!CollectionUtils.isEmpty(this.getFilteredListaWiki())) {
                this.setSelectedWiki(this.getFilteredListaWiki().get(index));
            } else {
                this.setSelectedWiki(this.getListaWiki().get(index));
            }
            CategoriaService categoriaService = (CategoriaService) ServiceFinder.findBean("CategoriaService");
            this.setSelectedCategoria(categoriaService.getCategoriaById(this.getSelectedWiki().getNcategoriaid()));
            this.setDescripcionHtml(GcmFileUtils.readStringFromFileServer(this.getSelectedWiki().getVruta(), "html.txt"));
            SeccionService seccionService = (SeccionService) ServiceFinder.findBean("SeccionService");
            this.setListaSeccion(seccionService.getSeccionesByConocimiento(this.getSelectedWiki().getNconocimientoid()));
            if (CollectionUtils.isNotEmpty(this.getListaSeccion())) {
                for (Seccion seccion : this.getListaSeccion()) {
                    seccion.setDetalleHtml(GcmFileUtils.readStringFromFileServer(seccion.getVruta(), "html.txt"));
                }
            }
            this.setChkDestacado(this.getSelectedWiki().getNdestacado().equals(BigDecimal.ONE));
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
            this.setListaTargetVinculosWK(conocimientoService.getConcimientosVinculados(map));
            this.setFilteredListaWiki(new ArrayList());
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
        return "/pages/wiki/publicar?faces-redirect=true";
    }

    public void post(ActionEvent event) {
        try {
            this.setDescripcionHtml(JSFUtils.getRequestParameter("descHtml"));
            if (this.getSelectedCategoria() == null) {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR.", "Categoría del wiki requerida. Seleccione la categoría del wiki a registrar.");
                FacesContext.getCurrentInstance().addMessage(null, message);
                return;
            }
            if (StringUtils.isBlank(this.getSelectedWiki().getVtitulo())) {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR.", "Título del wiki requerido. Ingrese el título del wiki a registrar.");
                FacesContext.getCurrentInstance().addMessage(null, message);
                return;
            }
            if (StringUtils.isBlank(this.getDescripcionHtml())) {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR.", "Descripción del wiki requerido. Ingrese la descripción del wiki a registrar.");
                FacesContext.getCurrentInstance().addMessage(null, message);
                return;
            }
            if (this.getSelectedWiki().getNdestacado().equals(BigDecimal.ZERO) && this.getChkDestacado()) {
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
            /* Validando si exiten vínculos de bases legales derogadas */
            int contador = 0;
            if (CollectionUtils.isNotEmpty(this.getListaTargetVinculosBL())) {
                for (Consulta c : this.getListaTargetVinculosBL()) {
                    if (c.getIdEstado().toString().equals(Constante.ESTADO_BASELEGAL_DEROGADA)) {
                        contador++;
                    }
                }
            }
            LoginMB loginMB = (LoginMB) JSFUtils.getSessionAttribute("loginMB");
            User user = loginMB.getUser();
            ConocimientoService conocimientoService = (ConocimientoService) ServiceFinder.findBean("ConocimientoService");
            this.setDescripcionPlain(Jsoup.parse(this.getDescripcionHtml()).text());
            this.getSelectedWiki().setNcategoriaid(this.getSelectedCategoria().getNcategoriaid());
            this.getSelectedWiki().setVtitulo(StringUtils.upperCase(this.getSelectedWiki().getVtitulo()));
            if (this.getDescripcionPlain().length() < 400) {
                this.getSelectedWiki().setVdescripcion(StringUtils.capitalize(this.getDescripcionPlain()));
            } else {
                this.getSelectedWiki().setVdescripcion(StringUtils.capitalize(this.getDescripcionPlain().substring(0, 300)));
            }
            if (contador > 0) {
                this.getSelectedWiki().setNflgvinculo(BigDecimal.ONE);
            } else {
                this.getSelectedWiki().setNflgvinculo(BigDecimal.ZERO);
            }
            this.getSelectedWiki().setNdestacado(this.getChkDestacado() ? BigDecimal.ONE : BigDecimal.ZERO);
            this.getSelectedWiki().setNsituacionid(BigDecimal.valueOf(Long.parseLong(Constante.SITUACION_PUBLICADO)));
            this.getSelectedWiki().setDfechapublicacion(new Date());
            this.getSelectedWiki().setDfechamodificacion(new Date());
            this.getSelectedWiki().setVusuariomodificacion(user.getVlogin());
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
            String url = this.path.concat(this.getSelectedWiki().getNconocimientoid().toString()).concat("/").concat(Integer.toString(lastversion + 1)).concat("/");

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
            historial.setVusuariocreacion(user.getVlogin());
            historialService.saveOrUpdate(historial);

            GcmFileUtils.writeStringToFileServer(url, "html.txt", this.getDescripcionHtml());
            GcmFileUtils.writeStringToFileServer(url, "plain.txt", this.getDescripcionPlain());

            if (CollectionUtils.isNotEmpty(this.getListaSeccion())) {
                String url0 = this.getSelectedWiki().getVruta().concat("s");
                String url1 = url.concat("s");
                SeccionService seccionService = (SeccionService) ServiceFinder.findBean("SeccionService");
                SeccionHistService seccionHistService = (SeccionHistService) ServiceFinder.findBean("SeccionHistService");
                for (Seccion seccion : this.getListaSeccion()) {
                    String ruta0 = url0.concat(seccion.getNorden().toString()).concat("/");
                    seccion.setVruta(ruta0);
                    if (seccion.getNseccionid() != null) {
                        seccion.setDfechamodificacion(new Date());
                        seccion.setVusuariomodificacion(user.getVlogin());
                    } else {
                        seccion.setNseccionid(seccionService.getNextPK());
                        seccion.setNconocimientoid(this.getSelectedWiki().getNconocimientoid());
                        seccion.setDfechacreacion(new Date());
                        seccion.setVusuariocreacion(user.getVlogin());
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
                    seccionHist.setVusuariocreacion(user.getVlogin());
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

            if (CollectionUtils.isNotEmpty(this.getListaTargetVinculos())) {
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
                    vinculo.setVusuariocreacion(user.getVlogin());
                    vinculoService.saveOrUpdate(vinculo);

                    TvinculoHistId vinculoHistId = new TvinculoHistId();
                    vinculoHistId.setNvinculohid(vinculoHistService.getNextPK());
                    vinculoHistId.setNconocimientoid(thistorialId.getNconocimientoid());
                    vinculoHistId.setNhistorialid(thistorialId.getNhistorialid());
                    VinculoHist vinculoHist = new VinculoHist();
                    vinculoHist.setId(vinculoHistId);
                    vinculoHist.setNconocimientovinc(vinculo.getNconocimientovinc());
                    vinculoHist.setDfechacreacion(new Date());
                    vinculoHist.setVusuariocreacion(user.getVlogin());
                    vinculoHistService.saveOrUpdate(vinculoHist);
                }
            }
            this.setListaWiki(conocimientoService.getConocimientosByType(Constante.WIKI));
            FacesContext.getCurrentInstance().getExternalContext().redirect("/gescon/pages/wiki/lista.xhtml");
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
    }

    public void activar(ActionEvent event) {
        try {
            if (event != null) {
                if (this.getSelectedWiki() != null) {
                    LoginMB loginMB = (LoginMB) JSFUtils.getSessionAttribute("loginMB");
                    User user = loginMB.getUser();
                    ConocimientoService service = (ConocimientoService) ServiceFinder.findBean("ConocimientoService");
                    this.getSelectedWiki().setNactivo(BigDecimal.ONE);
                    this.getSelectedWiki().setDfechamodificacion(new Date());
                    this.getSelectedWiki().setVusuariomodificacion(user.getVlogin());
                    service.saveOrUpdate(this.getSelectedWiki());
                    this.setListaWiki(service.getConocimientosByType(Constante.WIKI));
                } else {
                    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, Constante.SEVERETY_ALERTA, "Debe seleccionar el wiki a activar.");
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
                if (this.getSelectedWiki() != null) {
                    LoginMB loginMB = (LoginMB) JSFUtils.getSessionAttribute("loginMB");
                    User user = loginMB.getUser();
                    ConocimientoService service = (ConocimientoService) ServiceFinder.findBean("ConocimientoService");
                    this.getSelectedWiki().setNactivo(BigDecimal.ZERO);
                    this.getSelectedWiki().setNdestacado(BigDecimal.ZERO);
                    this.getSelectedWiki().setDfechamodificacion(new Date());
                    this.getSelectedWiki().setVusuariomodificacion(user.getVlogin());
                    service.saveOrUpdate(this.getSelectedWiki());
                    this.setListaWiki(service.getConocimientosByType(Constante.WIKI));
                } else {
                    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, Constante.SEVERETY_ALERTA, "Debe seleccionar el wiki a desactivar.");
                    FacesContext.getCurrentInstance().addMessage(null, message);
                }
            }
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
                FacesContext.getCurrentInstance().getExternalContext().redirect("/gescon/pages/wiki/historialCompare.xhtml");
            }
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
    }

    public void switchHistory(ActionEvent event) {
        try {
            LoginMB loginMB = (LoginMB) JSFUtils.getSessionAttribute("loginMB");
            User user = loginMB.getUser();
            HistorialService historialService = (HistorialService) ServiceFinder.findBean("HistorialService");
            if (CollectionUtils.isNotEmpty(this.getListaHistorial())) {
                for (Historial h : this.getListaHistorial()) {
                    h.setNversionactual(BigDecimal.ZERO);
                    historialService.saveOrUpdate(h);
                }
            }
            Historial hist = historialService.getHistorialById(BigDecimal.valueOf(Long.parseLong(this.getSelectedSwitch())));
            hist.setNversionactual(BigDecimal.ONE);
            historialService.saveOrUpdate(hist);
            String html = GcmFileUtils.readStringFromFileServer(hist.getVruta(), "html.txt");
            String plain = GcmFileUtils.readStringFromFileServer(hist.getVruta(), "plain.txt");
            SeccionHistService seccionHistService = (SeccionHistService) ServiceFinder.findBean("SeccionHistService");
            hist.setListaSeccionHist(seccionHistService.getSeccionHistsByHistorial(hist.getId().getNhistorialid()));
            if (CollectionUtils.isNotEmpty(hist.getListaSeccionHist())) {
                for (SeccionHist seccionHist : hist.getListaSeccionHist()) {
                    seccionHist.setDetalleHtml(GcmFileUtils.readStringFromFileServer(seccionHist.getVruta(), "html.txt"));
                    seccionHist.setDetallePlain(GcmFileUtils.readStringFromFileServer(seccionHist.getVruta(), "plain.txt"));
                }
            }

            this.getSelectedWiki().setNcategoriaid(hist.getNcategoriaid());
            this.getSelectedWiki().setNdias(hist.getNdias());
            this.getSelectedWiki().setVcontenido(hist.getVcontenido());
            this.getSelectedWiki().setVdescripcion(hist.getVdescripcion());
            this.getSelectedWiki().setVmsjrespuesta(hist.getVmsjrespuesta());
            this.getSelectedWiki().setVmsjsolicita(hist.getVmsjsolicita());
            this.getSelectedWiki().setVnumero(hist.getVnumero());
            this.getSelectedWiki().setVobservacion(hist.getVobservacion());
            this.getSelectedWiki().setVtema(hist.getVtema());
            this.getSelectedWiki().setVtitulo(hist.getVtitulo());
            this.getSelectedWiki().setDfechamodificacion(new Date());
            this.getSelectedWiki().setVusuariomodificacion(user.getVlogin());

            ConocimientoService conocimientoService = (ConocimientoService) ServiceFinder.findBean("ConocimientoService");
            conocimientoService.saveOrUpdate(this.getSelectedWiki());
            GcmFileUtils.writeStringToFileServer(this.getSelectedWiki().getVruta(), "html.txt", html);
            GcmFileUtils.writeStringToFileServer(this.getSelectedWiki().getVruta(), "plain.txt", plain);

            SeccionService seccionService = (SeccionService) ServiceFinder.findBean("SeccionService");
            seccionService.deleteSeccionesByConocimiento(this.getSelectedWiki().getNconocimientoid());
            if (CollectionUtils.isNotEmpty(hist.getListaSeccionHist())) {
                for (SeccionHist seccionHist : hist.getListaSeccionHist()) {
                    Seccion seccion = new Seccion();
                    seccion.setNseccionid(seccionService.getNextPK());
                    seccion.setNconocimientoid(seccionHist.getId().getNconocimientoid());
                    seccion.setNorden(seccionHist.getNorden());
                    seccion.setVruta(seccionHist.getVruta());
                    seccion.setVtitulo(seccionHist.getVtitulo());
                    seccion.setVusuariocreacion(user.getVlogin());
                    seccion.setDfechacreacion(new Date());
                    seccion.setDetalleHtml(GcmFileUtils.readStringFromFileServer(seccionHist.getVruta(), "html.txt"));
                    seccion.setDetallePlain(GcmFileUtils.readStringFromFileServer(seccionHist.getVruta(), "plain.txt"));
                    seccionService.saveOrUpdate(seccion);

                    String ruta1 = this.getSelectedWiki().getVruta().concat(seccion.getNorden().toString()).concat("/");
                    GcmFileUtils.writeStringToFileServer(ruta1, "html.txt", seccion.getDetalleHtml());
                    GcmFileUtils.writeStringToFileServer(ruta1, "plain.txt", seccion.getDetallePlain());
                }
            }

            VinculoService vinculoService = (VinculoService) ServiceFinder.findBean("VinculoService");
            vinculoService.deleteByConocimiento(this.getSelectedWiki().getNconocimientoid());
            VinculoHistService vinculoHistService = (VinculoHistService) ServiceFinder.findBean("VinculoHistService");
            List<VinculoHist> listaVinculoHist = vinculoHistService.getVinculoHistsByHistorial(hist.getId().getNhistorialid());
            if (CollectionUtils.isNotEmpty(listaVinculoHist)) {
                for (VinculoHist vinculoHist : listaVinculoHist) {
                    Vinculo vinculo = new Vinculo();
                    vinculo.setNvinculoid(vinculoService.getNextPK());
                    vinculo.setNconocimientoid(this.getSelectedWiki().getNconocimientoid());
                    vinculo.setNconocimientovinc(vinculoHist.getNconocimientovinc());
                    vinculo.setNtipoconocimientovinc(vinculoHist.getNtipoconocimientovinc());
                    vinculo.setVusuariocreacion(user.getVlogin());
                    vinculo.setDfechacreacion(new Date());
                    vinculoService.saveOrUpdate(vinculo);
                }
            }
            FacesContext.getCurrentInstance().getExternalContext().redirect("/gescon/pages/wiki/vistaConsulta.xhtml");
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
            cal.setNconocimientoid(this.getSelectedWiki().getNconocimientoid());
            cal.setNcalificacion(this.getCalificacion());
            cal.setVcomentario(StringUtils.capitalize(this.getComentario().trim()));
            cal.setDfechacreacion(new Date());
            cal.setVusuariocreacion(user.getVlogin());
            calificacionService.saveOrUpdate(cal);
            this.setListaCalificacion(calificacionService.getCalificacionesByConocimiento(this.getSelectedWiki().getNconocimientoid()));
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
            this.setListaCalificacion(calificacionService.getCalificacionesByConocimiento(this.getSelectedWiki().getNconocimientoid()));
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
            this.setListaCalificacion(calificacionService.getCalificacionesByConocimiento(this.getSelectedWiki().getNconocimientoid()));
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
        
        for (int i = 0; i < header.getPhysicalNumberOfCells(); i++) {
            HSSFCell cell = header.getCell(i);
            cell.setCellStyle(headerStyle);
            sheet.autoSizeColumn(i);
        }
        
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
        for(Conocimiento c : this.getListaWiki()) {
            HSSFRow row = sheet.getRow(i);
            for (int j = 0; j < row.getPhysicalNumberOfCells(); j++) {
                HSSFCell cell = row.getCell(j);
                if(i % 2 == 0 ) {
                    if(j > 0) {
                        cell.setCellStyle(centerGrayStyle);
                    } else {
                        cell.setCellStyle(grayBG);
                    }
                } else {
                    if(j > 0) {
                        cell.setCellStyle(centerStyle);
                    }
                }
            }
            i++;
        }
    }
}
