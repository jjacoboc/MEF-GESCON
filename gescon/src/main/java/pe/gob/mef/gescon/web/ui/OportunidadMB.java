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
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;
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
public class OportunidadMB implements Serializable {

    private final String path = "om/";
    private List<Conocimiento> listaOportunidad;
    private List<Conocimiento> filteredListaOportunidad;
    private Conocimiento selectedOportunidad;
    private TreeNode tree;
    private Categoria selectedCategoria;
    private Boolean chkDestacado;
    private String nombre;
    private String descripcion;
    private String contenidoHtml;
    private String contenidoPlain;
    private String analisisHtml;
    private String analisisPlain;
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
    private BigDecimal analisis;
    private int dias;
    private String motivo;
    
    /**
     * Creates a new instance of OportunidadMB
     */
    public OportunidadMB() {
    }

    public List<Conocimiento> getListaOportunidad() {
        return listaOportunidad;
    }

    public void setListaOportunidad(List<Conocimiento> listaOportunidad) {
        this.listaOportunidad = listaOportunidad;
    }

    public List<Conocimiento> getFilteredListaOportunidad() {
        return filteredListaOportunidad;
    }

    public void setFilteredListaOportunidad(List<Conocimiento> filteredListaOportunidad) {
        this.filteredListaOportunidad = filteredListaOportunidad;
    }

    public Conocimiento getSelectedOportunidad() {
        return selectedOportunidad;
    }

    public void setSelectedOportunidad(Conocimiento selectedOportunidad) {
        this.selectedOportunidad = selectedOportunidad;
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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getContenidoHtml() {
        return contenidoHtml;
    }

    public void setContenidoHtml(String contenidoHtml) {
        this.contenidoHtml = contenidoHtml;
    }

    public String getContenidoPlain() {
        return contenidoPlain;
    }

    public void setContenidoPlain(String contenidoPlain) {
        this.contenidoPlain = contenidoPlain;
    }

    public String getAnalisisHtml() {
        return analisisHtml;
    }

    public void setAnalisisHtml(String analisisHtml) {
        this.analisisHtml = analisisHtml;
    }

    public String getAnalisisPlain() {
        return analisisPlain;
    }

    public void setAnalisisPlain(String analisisPlain) {
        this.analisisPlain = analisisPlain;
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
    
    @PostConstruct
    public void init() {
        try {
            ConocimientoService conocimientoService = (ConocimientoService) ServiceFinder.findBean("ConocimientoService");
            this.setListaOportunidad(conocimientoService.getConocimientosByType(Constante.OPORTUNIDADMEJORA));
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
                if (!CollectionUtils.isEmpty(this.getFilteredListaOportunidad())) {
                    this.setSelectedOportunidad(this.getFilteredListaOportunidad().get(index));
                } else {
                    this.setSelectedOportunidad(this.getListaOportunidad().get(index));
                }
                this.setFilteredListaOportunidad(new ArrayList());
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
            this.setDescripcion(StringUtils.EMPTY);
            this.setContenidoHtml(StringUtils.EMPTY);
            this.setContenidoPlain(StringUtils.EMPTY);
            this.setAnalisisHtml(StringUtils.EMPTY);
            this.setAnalisisPlain(StringUtils.EMPTY);
            this.setListaSeccion(new ArrayList());
            this.setSelectedSeccion(null);
            this.setTitulo(StringUtils.EMPTY);
            this.setDetalleHtml(StringUtils.EMPTY);
            this.setDetallePlain(StringUtils.EMPTY);
            this.setChkDestacado(false);
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
            if(StringUtils.isBlank(this.getTitulo())) {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR.", "Ingrese el título de la sección a agregar.");
                FacesContext.getCurrentInstance().addMessage(null, message);
                return;
            }
            if(StringUtils.isBlank(this.getDetalleHtml())) {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR.", "Ingrese el detalle de la sección a agregar.");
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
            if(StringUtils.isBlank(this.getSelectedSeccion().getVtitulo())) {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR.", "Ingrese el título de la sección a agregar.");
                FacesContext.getCurrentInstance().addMessage(null, message);
                return;
            }
            if(StringUtils.isBlank(this.getSelectedSeccion().getDetalleHtml())) {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR.", "Ingrese el detalle de la sección a agregar.");
                FacesContext.getCurrentInstance().addMessage(null, message);
                return;
            }
            RequestContext.getCurrentInstance().execute("PF('esecDialog').hide();");
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
            if(this.getTipoDiscusion() == null) {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR.", "Seleccione el tipo de discusión a agregar.");
                FacesContext.getCurrentInstance().addMessage(null, message);
                return;
            }
            if(StringUtils.isBlank(this.getTituloDiscusion())) {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR.", "Ingrese el título de la discusión a agregar.");
                FacesContext.getCurrentInstance().addMessage(null, message);
                return;
            }
            if(StringUtils.isBlank(this.getDiscusionHtml())) {
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
            if(this.getListaDiscusionSeccion() == null) {
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
            if(this.getSelectedDiscusionSeccion().getNtipodiscusion() == null) {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR.", "Seleccione el tipo de discusión a editar.");
                FacesContext.getCurrentInstance().addMessage(null, message);
                return;
            }
            if(StringUtils.isBlank(this.getSelectedDiscusionSeccion().getVtitulo())) {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR.", "Ingrese el título de la discusión a editar.");
                FacesContext.getCurrentInstance().addMessage(null, message);
                return;
            }
            if(StringUtils.isBlank(this.getSelectedDiscusionSeccion().getDiscusionHtml())) {
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
            if(this.getSelectedDiscusion() == null) {
                discusion = new Discusion();
                discusion.setNdiscusionid(discusionService.getNextPK());
                discusion.setNconocimientoid(this.getSelectedOportunidad().getNconocimientoid());
                discusion.setDfechacreacion(new Date());
                discusion.setVusuariocreacion(user.getVlogin());
                discusionService.saveOrUpdate(discusion);
                
                discusionHist = new DiscusionHist();
                discusionHist.setNnumversion(BigDecimal.ONE);
            } else {
                discusionHist = discusionHistService.getDiscusionHistByConocimiento(this.getSelectedOportunidad().getNconocimientoid());
                int version = discusionHist.getNnumversion().intValue() + 1;
                discusionHist.setNnumversion(BigDecimal.valueOf(version));
            }
            discusionHist.setNdiscusionhid(discusionHistService.getNextPK());
            discusionHist.setNconocimientoid(this.getSelectedOportunidad().getNconocimientoid());
            discusionHist.setDfechacreacion(new Date());
            discusionHist.setVusuariocreacion(user.getVlogin());
            discusionHistService.saveOrUpdate(discusionHist);
            
            if(CollectionUtils.isNotEmpty(this.getListaDiscusionSeccion())) {
                String url0 = this.path.concat(this.getSelectedOportunidad().getNconocimientoid().toString()).concat("/0/d/").concat(BigDecimal.ZERO.toString()).concat("/s");
                String url1 = this.path.concat(this.getSelectedOportunidad().getNconocimientoid().toString()).concat("/0/d/").concat(discusionHist.getNnumversion().toString()).concat("/s");
                DiscusionSeccionService discusionSeccionService = (DiscusionSeccionService) ServiceFinder.findBean("DiscusionSeccionService");
                DiscusionSeccionHistService discusionSeccionHistService = (DiscusionSeccionHistService) ServiceFinder.findBean("DiscusionSeccionHistService");
                for (DiscusionSeccion seccion : this.getListaDiscusionSeccion()) {
                    if(seccion.getNdiscusionseccionid() == null) {
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
            if(CollectionUtils.isEmpty(this.getListaSourceVinculosBL())) { this.setListaSourceVinculosBL(new ArrayList()); }
            if(CollectionUtils.isEmpty(this.getListaSourceVinculosBP())) { this.setListaSourceVinculosBP(new ArrayList()); }
            if(CollectionUtils.isEmpty(this.getListaSourceVinculosCT())) { this.setListaSourceVinculosCT(new ArrayList()); } 
            if(CollectionUtils.isEmpty(this.getListaSourceVinculosOM())) { this.setListaSourceVinculosOM(new ArrayList()); }
            if(CollectionUtils.isEmpty(this.getListaSourceVinculosPR())) { this.setListaSourceVinculosPR(new ArrayList()); }
            if(CollectionUtils.isEmpty(this.getListaSourceVinculosWK())) { this.setListaSourceVinculosWK(new ArrayList()); }
            this.setListaTargetVinculos(new ArrayList());
            if(CollectionUtils.isEmpty(this.getListaTargetVinculosBL())) { this.setListaTargetVinculosBL(new ArrayList()); }
            if(CollectionUtils.isEmpty(this.getListaTargetVinculosBP())) { this.setListaTargetVinculosBP(new ArrayList()); }
            if(CollectionUtils.isEmpty(this.getListaTargetVinculosCT())) { this.setListaTargetVinculosCT(new ArrayList()); }
            if(CollectionUtils.isEmpty(this.getListaTargetVinculosOM())) { this.setListaTargetVinculosOM(new ArrayList()); }
            if(CollectionUtils.isEmpty(this.getListaTargetVinculosPR())) { this.setListaTargetVinculosPR(new ArrayList()); }
            if(CollectionUtils.isEmpty(this.getListaTargetVinculosWK())) { this.setListaTargetVinculosWK(new ArrayList()); }
            this.setPickList(new DualListModel<>(this.getListaSourceVinculos(), this.getListaTargetVinculos()));
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
                    if (this.getSelectedOportunidad()!= null) {
                        filters.put("nconocimientoid", this.getSelectedOportunidad().getNconocimientoid().toString());
                        this.setListaTargetVinculos(new ArrayList());
                        List<Consulta> lista = service.getConcimientosVinculados(filters);
                        Collections.sort(lista, Consulta.Comparators.ID);
                        if (id.equals(Constante.BASELEGAL)) {
                            for(Consulta ele : lista){
                                int pos = Collections.binarySearch(this.getListaTargetVinculosBL(), ele, Consulta.Comparators.ID);
                                if(pos < 0) {
                                    this.getListaTargetVinculosBL().add(ele);
                                }
                            }
                            this.getListaTargetVinculos().addAll(this.getListaTargetVinculosBL());
                        } else if (id.equals(Constante.PREGUNTAS)) {
                            for(Consulta ele : lista){
                                int pos = Collections.binarySearch(this.getListaTargetVinculosPR(), ele, Consulta.Comparators.ID);
                                if(pos < 0) {
                                    this.getListaTargetVinculosPR().add(ele);
                                }
                            }
                            this.getListaTargetVinculos().addAll(this.getListaTargetVinculosPR());
                        } else if (id.equals(Constante.WIKI)) {
                            for(Consulta ele : lista){
                                int pos = Collections.binarySearch(this.getListaTargetVinculosWK(), ele, Consulta.Comparators.ID);
                                if(pos < 0) {
                                    this.getListaTargetVinculosWK().add(ele);
                                }
                            }
                            this.getListaTargetVinculos().addAll(this.getListaTargetVinculosWK());
                        } else if (id.equals(Constante.CONTENIDO)) {
                            for(Consulta ele : lista){
                                int pos = Collections.binarySearch(this.getListaTargetVinculosCT(), ele, Consulta.Comparators.ID);
                                if(pos < 0) {
                                    this.getListaTargetVinculosCT().add(ele);
                                }
                            }
                            this.getListaTargetVinculos().addAll(this.getListaTargetVinculosCT());
                        } else if (id.equals(Constante.BUENAPRACTICA)) {
                            for(Consulta ele : lista){
                                int pos = Collections.binarySearch(this.getListaTargetVinculosBP(), ele, Consulta.Comparators.ID);
                                if(pos < 0) {
                                    this.getListaTargetVinculosBP().add(ele);
                                }
                            }
                            this.getListaTargetVinculos().addAll(this.getListaTargetVinculosBP());
                        } else if (id.equals(Constante.OPORTUNIDADMEJORA)) {
                            for(Consulta ele : lista){
                                int pos = Collections.binarySearch(this.getListaTargetVinculosOM(), ele, Consulta.Comparators.ID);
                                if(pos < 0) {
                                    this.getListaTargetVinculosOM().add(ele);
                                }
                            }
                            this.getListaTargetVinculos().addAll(this.getListaTargetVinculosOM());
                        }
                    } else {
                        if (id.equals(Constante.BASELEGAL)) {
                            this.setListaTargetVinculos(this.getListaTargetVinculosBL());
                        } else if (id.equals(Constante.PREGUNTAS)) {
                            this.setListaTargetVinculos(this.getListaTargetVinculosPR());
                        } else if (id.equals(Constante.WIKI)) {
                            this.setListaTargetVinculos(this.getListaTargetVinculosWK());
                        } else if (id.equals(Constante.CONTENIDO)) {
                            this.setListaTargetVinculos(this.getListaTargetVinculosCT());
                        } else if (id.equals(Constante.BUENAPRACTICA)) {
                            this.setListaTargetVinculos(this.getListaTargetVinculosBP());
                        } else if (id.equals(Constante.OPORTUNIDADMEJORA)) {
                            this.setListaTargetVinculos(this.getListaTargetVinculosOM());
                        }
                    }
                    if(CollectionUtils.isNotEmpty(this.getListaTargetVinculos())) {
                        List<String> ids = new ArrayList<>();
                        for (Consulta c : this.getListaTargetVinculos()) {
                            ids.add(c.getIdconocimiento().toString());
                        }
                        String filter = StringUtils.join(ids, ',');
                        if (id.equals(Constante.WIKI)) {
                            filter = filter.concat(",").concat(this.getSelectedOportunidad().getNconocimientoid().toString());
                        }
                        filters.put("nconocimientovinc", filter);
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
                    this.setPickList(new DualListModel<>(this.getListaSourceVinculos(), this.getListaTargetVinculos()));
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
                    filter.put("ntipoconocimientoid", Constante.OPORTUNIDADMEJORA);                
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
        return "/pages/oportunidad/nuevo?faces-redirect=true";
    }

    public void save(ActionEvent event) {
        try {
            this.setContenidoHtml(JSFUtils.getRequestParameter("descHtml"));
            if(this.getSelectedCategoria() == null) {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR.", "Seleccione la categoría de la oportunidad de mejora a registrar.");
                FacesContext.getCurrentInstance().addMessage(null, message);
                return;
            }
            if(StringUtils.isBlank(this.getNombre())) {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR.", "Ingrese el título de la oportunidad de mejora a registrar.");
                FacesContext.getCurrentInstance().addMessage(null, message);
                return;
            }
            if(StringUtils.isBlank(this.getDescripcion())) {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR.", "Ingrese la descripción de la oportunidad de mejora a registrar.");
                FacesContext.getCurrentInstance().addMessage(null, message);
                return;
            }
            if(StringUtils.isBlank(this.getContenidoHtml())) {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR.", "Ingrese la descripción del contenido de la oportunidad de mejora a registrar.");
                FacesContext.getCurrentInstance().addMessage(null, message);
                return;
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
            LoginMB loginMB = (LoginMB) JSFUtils.getSessionAttribute("loginMB");
            User user = loginMB.getUser();
            ConocimientoService conocimientoService = (ConocimientoService) ServiceFinder.findBean("ConocimientoService");
            Conocimiento conocimiento = new Conocimiento();
            conocimiento.setNtipoconocimientoid(Constante.OPORTUNIDADMEJORA);
            conocimiento.setNconocimientoid(conocimientoService.getNextPK());
            conocimiento.setNcategoriaid(this.getSelectedCategoria().getNcategoriaid());
            conocimiento.setVtitulo(StringUtils.upperCase(this.getNombre()));
            conocimiento.setVdescripcion(StringUtils.capitalize(this.getDescripcion()));
            conocimiento.setNactivo(BigDecimal.ONE);
            if (this.getSelectedCategoria().getNflagom().equals(BigDecimal.ONE)) {
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
            conocimiento.setNdestacado(BigDecimal.ZERO);
            conocimiento.setDfechacreacion(new Date());
            conocimiento.setVusuariocreacion(user.getVlogin());
            conocimientoService.saveOrUpdate(conocimiento);

            this.setContenidoPlain(Jsoup.parse(this.getContenidoHtml()).text());
            GcmFileUtils.writeStringToFileServer(np0, "html.txt", this.getContenidoHtml());
            GcmFileUtils.writeStringToFileServer(np0, "plain.txt", this.getContenidoPlain());

            String np1 = this.path.concat(conocimiento.getNconocimientoid().toString()).concat("/1/");
            HistorialService historialService = (HistorialService) ServiceFinder.findBean("HistorialService");
            ThistorialId thistorialId = new ThistorialId();
            thistorialId.setNconocimientoid(conocimiento.getNconocimientoid());
            thistorialId.setNhistorialid(historialService.getNextPK());
            Historial historial = new Historial();
            historial.setId(thistorialId);
            historial.setNtipoconocimientoid(Constante.OPORTUNIDADMEJORA);
            historial.setNcategoriaid(this.getSelectedCategoria().getNcategoriaid());
            historial.setVtitulo(this.getNombre());
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

            if (CollectionUtils.isNotEmpty(this.getListaTargetVinculos())) {
                VinculoService vinculoService = (VinculoService) ServiceFinder.findBean("VinculoService");
                VinculoHistService vinculoHistService = (VinculoHistService) ServiceFinder.findBean("VinculoHistService");
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
                    vinculoHist.setDfechacreacion(new Date());
                    vinculoHist.setVusuariocreacion(user.getVlogin());
                    vinculoHistService.saveOrUpdate(vinculoHist);
                }
            }
            
            if(this.getSelectedCategoria().getNflagom().toString().equals("1"))
            {
            Asignacion asignacion = new Asignacion();
            AsignacionService serviceasig = (AsignacionService) ServiceFinder.findBean("AsignacionService");
            asignacion.setNasignacionid(serviceasig.getNextPK());
            asignacion.setNtipoconocimientoid(Constante.OPORTUNIDADMEJORA);
            asignacion.setNconocimientoid(conocimiento.getNconocimientoid());
            asignacion.setNestadoid(BigDecimal.valueOf(Long.parseLong(Constante.SITUACION_POR_VERIFICAR)));
            CategoriaService categoriaService = (CategoriaService) ServiceFinder.findBean("CategoriaService");
            asignacion.setNusuarioid(categoriaService.getCategoriaById(conocimiento.getNcategoriaid()).getNmoderador());
            asignacion.setDfechaasignacion(new Date());
            asignacion.setDfechacreacion(new Date());
            serviceasig.saveOrUpdate(asignacion);
            }
            
            this.setListaOportunidad(conocimientoService.getConocimientosByType(Constante.OPORTUNIDADMEJORA));
            FacesContext.getCurrentInstance().getExternalContext().redirect("/gescon/pages/oportunidad/lista.xhtml");
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
    }
    
    public String toView() {
        try {
            this.clearAll();
            int index = Integer.parseInt((String) JSFUtils.getRequestParameter("index"));
            if(!CollectionUtils.isEmpty(this.getFilteredListaOportunidad())) {
                this.setSelectedOportunidad(this.getFilteredListaOportunidad().get(index));
            } else {
                this.setSelectedOportunidad(this.getListaOportunidad().get(index));
            }
            CategoriaService categoriaService = (CategoriaService) ServiceFinder.findBean("CategoriaService");
            this.setSelectedCategoria(categoriaService.getCategoriaById(this.getSelectedOportunidad().getNcategoriaid()));
            this.setChkDestacado(this.getSelectedOportunidad().getNdestacado().equals(BigDecimal.ONE));
            this.setContenidoHtml(GcmFileUtils.readStringFromFileServer(this.getSelectedOportunidad().getVruta(), "html.txt"));
            SeccionService seccionService = (SeccionService) ServiceFinder.findBean("SeccionService");
            this.setListaSeccion(seccionService.getSeccionesByConocimiento(this.getSelectedOportunidad().getNconocimientoid()));
            if (CollectionUtils.isNotEmpty(this.getListaSeccion())) {
                for (Seccion seccion : this.getListaSeccion()) {
                    seccion.setDetalleHtml(GcmFileUtils.readStringFromFileServer(seccion.getVruta(), "html.txt"));
                }
            }
            ConocimientoService conocimientoService = (ConocimientoService) ServiceFinder.findBean("ConocimientoService");
            HashMap map = new HashMap();
            map.put("nconocimientoid", this.getSelectedOportunidad().getNconocimientoid().toString());
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
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
        return "/pages/oportunidad/ver?faces-redirect=true";
    }

    public String toEdit() {
        try {
            this.clearAll();
            int index = Integer.parseInt((String) JSFUtils.getRequestParameter("index"));
            if(!CollectionUtils.isEmpty(this.getFilteredListaOportunidad())) {
                this.setSelectedOportunidad(this.getFilteredListaOportunidad().get(index));
            } else {
                this.setSelectedOportunidad(this.getListaOportunidad().get(index));
            }
            CategoriaService categoriaService = (CategoriaService) ServiceFinder.findBean("CategoriaService");
            this.setSelectedCategoria(categoriaService.getCategoriaById(this.getSelectedOportunidad().getNcategoriaid()));
            this.setChkDestacado(this.getSelectedOportunidad().getNdestacado().equals(BigDecimal.ONE));
            this.setContenidoHtml(GcmFileUtils.readStringFromFileServer(this.getSelectedOportunidad().getVruta(), "html.txt"));
            SeccionService seccionService = (SeccionService) ServiceFinder.findBean("SeccionService");
            this.setListaSeccion(seccionService.getSeccionesByConocimiento(this.getSelectedOportunidad().getNconocimientoid()));
            if (CollectionUtils.isNotEmpty(this.getListaSeccion())) {
                for (Seccion seccion : this.getListaSeccion()) {
                    seccion.setDetalleHtml(GcmFileUtils.readStringFromFileServer(seccion.getVruta(), "html.txt"));
                }
            }
            ConocimientoService conocimientoService = (ConocimientoService) ServiceFinder.findBean("ConocimientoService");
            HashMap map = new HashMap();
            map.put("nconocimientoid", this.getSelectedOportunidad().getNconocimientoid().toString());
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
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
        return "/pages/oportunidad/editar?faces-redirect=true";
    }

    public void edit(ActionEvent event) {
        try {
            this.setContenidoHtml(JSFUtils.getRequestParameter("descHtml"));
            if(this.getSelectedCategoria() == null) {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR.", "Seleccione la categoría de la oportunidad de mejora a registrar.");
                FacesContext.getCurrentInstance().addMessage(null, message);
                return;
            }
            if(StringUtils.isBlank(this.getSelectedOportunidad().getVtitulo())) {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR.", "Ingrese el título de la oportunidad de mejora a registrar.");
                FacesContext.getCurrentInstance().addMessage(null, message);
                return;
            }
            if(StringUtils.isBlank(this.getSelectedOportunidad().getVdescripcion())) {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR.", "Ingrese la descripción de la oportunidad de mejora a registrar.");
                FacesContext.getCurrentInstance().addMessage(null, message);
                return;
            }
            if(StringUtils.isBlank(this.getContenidoHtml())) {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR.", "Ingrese el contenido de la oportunidad de mejora a registrar.");
                FacesContext.getCurrentInstance().addMessage(null, message);
                return;
            }
            if(this.getSelectedOportunidad().getNdestacado().equals(BigDecimal.ZERO) && this.getChkDestacado()) {
                ConsultaService consultaService = (ConsultaService) ServiceFinder.findBean("ConsultaService");
                HashMap filter = new HashMap();
                filter.put("ntipoconocimientoid", Constante.OPORTUNIDADMEJORA);
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
            LoginMB loginMB = (LoginMB) JSFUtils.getSessionAttribute("loginMB");
            User user = loginMB.getUser();
            ConocimientoService conocimientoService = (ConocimientoService) ServiceFinder.findBean("ConocimientoService");
            this.getSelectedOportunidad().setNcategoriaid(this.getSelectedCategoria().getNcategoriaid());
            this.getSelectedOportunidad().setVtitulo(StringUtils.upperCase(this.getSelectedOportunidad().getVtitulo()));
            this.getSelectedOportunidad().setVdescripcion(StringUtils.capitalize(this.getSelectedOportunidad().getVdescripcion()));
            this.getSelectedOportunidad().setNdestacado(this.getChkDestacado() ? BigDecimal.ONE : BigDecimal.ZERO);
            this.getSelectedOportunidad().setDfechamodificacion(new Date());
            this.getSelectedOportunidad().setVusuariomodificacion(user.getVlogin());
            if(contador > 0) {
                this.getSelectedOportunidad().setNflgvinculo(BigDecimal.ONE);
            } else {
                this.getSelectedOportunidad().setNflgvinculo(BigDecimal.ZERO);
            }
            conocimientoService.saveOrUpdate(this.getSelectedOportunidad());

            this.setContenidoPlain(Jsoup.parse(this.getContenidoHtml()).text());
            GcmFileUtils.writeStringToFileServer(this.getSelectedOportunidad().getVruta(), "html.txt", this.getContenidoHtml());
            GcmFileUtils.writeStringToFileServer(this.getSelectedOportunidad().getVruta(), "plain.txt", this.getContenidoHtml());

            HistorialService historialService = (HistorialService) ServiceFinder.findBean("HistorialService");
            Historial lastHistorial = historialService.getLastHistorialByConocimiento(this.getSelectedOportunidad().getNconocimientoid());
            int lastversion;
            if(lastHistorial != null) {
                lastversion = lastHistorial.getNnumversion().intValue();
            } else {
                lastversion = 0;
            }
            String url = this.path.concat(this.getSelectedOportunidad().getNconocimientoid().toString()).concat("/").concat(Integer.toString(lastversion + 1)).concat("/");

            ThistorialId thistorialId = new ThistorialId();
            thistorialId.setNconocimientoid(this.getSelectedOportunidad().getNconocimientoid());
            thistorialId.setNhistorialid(historialService.getNextPK());
            Historial historial = new Historial();
            historial.setId(thistorialId);
            historial.setNtipoconocimientoid(Constante.OPORTUNIDADMEJORA);
            historial.setNcategoriaid(this.getSelectedCategoria().getNcategoriaid());
            historial.setVtitulo(this.getSelectedOportunidad().getVtitulo());
            historial.setVdescripcion(this.getSelectedOportunidad().getVdescripcion());
            historial.setNactivo(BigDecimal.ONE);
            historial.setNsituacionid(this.getSelectedOportunidad().getNsituacionid());
            historial.setVruta(url);
            historial.setNnumversion(BigDecimal.valueOf(lastversion + 1));
            historial.setDfechacreacion(new Date());
            historial.setVusuariocreacion(user.getVlogin());
            historialService.saveOrUpdate(historial);

            GcmFileUtils.writeStringToFileServer(url, "html.txt", this.getContenidoHtml());
            GcmFileUtils.writeStringToFileServer(url, "plain.txt", this.getContenidoPlain());
            
            if (CollectionUtils.isNotEmpty(this.getListaSeccion())) {
                String url0 = this.getSelectedOportunidad().getVruta().concat("s");
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
                        seccion.setNconocimientoid(this.getSelectedOportunidad().getNconocimientoid());
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
                vinculoService.deleteByConocimiento(this.getSelectedOportunidad().getNconocimientoid());
                for (Consulta consulta : this.getListaTargetVinculos()) {
                    Vinculo vinculo = new Vinculo();
                    vinculo.setNvinculoid(vinculoService.getNextPK());
                    vinculo.setNconocimientoid(this.getSelectedOportunidad().getNconocimientoid());
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
            this.setListaOportunidad(conocimientoService.getConocimientosByType(Constante.OPORTUNIDADMEJORA));
            FacesContext.getCurrentInstance().getExternalContext().redirect("/gescon/pages/oportunidad/lista.xhtml");
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
    }
    
    public String toPost() {
        try {
            this.clearAll();
            int index = Integer.parseInt((String) JSFUtils.getRequestParameter("index"));
            if(!CollectionUtils.isEmpty(this.getFilteredListaOportunidad())) {
                this.setSelectedOportunidad(this.getFilteredListaOportunidad().get(index));
            } else {
                this.setSelectedOportunidad(this.getListaOportunidad().get(index));
            }
            CategoriaService categoriaService = (CategoriaService) ServiceFinder.findBean("CategoriaService");
            this.setSelectedCategoria(categoriaService.getCategoriaById(this.getSelectedOportunidad().getNcategoriaid()));
            this.setChkDestacado(this.getSelectedOportunidad().getNdestacado().equals(BigDecimal.ONE));
            this.setContenidoHtml(GcmFileUtils.readStringFromFileServer(this.getSelectedOportunidad().getVruta(), "html.txt"));
            SeccionService seccionService = (SeccionService) ServiceFinder.findBean("SeccionService");
            this.setListaSeccion(seccionService.getSeccionesByConocimiento(this.getSelectedOportunidad().getNconocimientoid()));
            if (CollectionUtils.isNotEmpty(this.getListaSeccion())) {
                for (Seccion seccion : this.getListaSeccion()) {
                    seccion.setDetalleHtml(GcmFileUtils.readStringFromFileServer(seccion.getVruta(), "html.txt"));
                }
            }
            ConocimientoService conocimientoService = (ConocimientoService) ServiceFinder.findBean("ConocimientoService");
            HashMap map = new HashMap();
            map.put("nconocimientoid", this.getSelectedOportunidad().getNconocimientoid().toString());
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
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
        return "/pages/oportunidad/publicar?faces-redirect=true";
    }
    
    public void post(ActionEvent event) {
        try {
            this.setContenidoHtml(JSFUtils.getRequestParameter("descHtml"));
            if(this.getSelectedCategoria() == null) {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR.", "Seleccione la categoría de la oportunidad de mejora a registrar.");
                FacesContext.getCurrentInstance().addMessage(null, message);
                return;
            }
            if(StringUtils.isBlank(this.getSelectedOportunidad().getVtitulo())) {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR.", "Ingrese el título de la oportunidad de mejora a registrar.");
                FacesContext.getCurrentInstance().addMessage(null, message);
                return;
            }
            if(StringUtils.isBlank(this.getSelectedOportunidad().getVdescripcion())) {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR.", "Ingrese la descripción de la oportunidad de mejora a registrar.");
                FacesContext.getCurrentInstance().addMessage(null, message);
                return;
            }
            if(StringUtils.isBlank(this.getContenidoHtml())) {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR.", "Ingrese el contenido de la oportunidad de mejora a registrar.");
                FacesContext.getCurrentInstance().addMessage(null, message);
                return;
            }
            if(this.getSelectedOportunidad().getNdestacado().equals(BigDecimal.ZERO) && this.getChkDestacado()) {
                ConsultaService consultaService = (ConsultaService) ServiceFinder.findBean("ConsultaService");
                HashMap filter = new HashMap();
                filter.put("ntipoconocimientoid", Constante.OPORTUNIDADMEJORA);
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
            LoginMB loginMB = (LoginMB) JSFUtils.getSessionAttribute("loginMB");
            User user = loginMB.getUser();
            ConocimientoService conocimientoService = (ConocimientoService) ServiceFinder.findBean("ConocimientoService");
            this.getSelectedOportunidad().setNcategoriaid(this.getSelectedCategoria().getNcategoriaid());
            this.getSelectedOportunidad().setVtitulo(StringUtils.upperCase(this.getSelectedOportunidad().getVtitulo()));
            this.getSelectedOportunidad().setVdescripcion(StringUtils.capitalize(this.getSelectedOportunidad().getVdescripcion()));
            this.getSelectedOportunidad().setDfechapublicacion(new Date());
            this.getSelectedOportunidad().setNsituacionid(BigDecimal.valueOf(Long.parseLong(Constante.SITUACION_PUBLICADO)));
            this.getSelectedOportunidad().setNdestacado(this.getChkDestacado() ? BigDecimal.ONE : BigDecimal.ZERO);
            this.getSelectedOportunidad().setDfechamodificacion(new Date());
            this.getSelectedOportunidad().setVusuariomodificacion(user.getVlogin());
            if(contador > 0) {
                this.getSelectedOportunidad().setNflgvinculo(BigDecimal.ONE);
            } else {
                this.getSelectedOportunidad().setNflgvinculo(BigDecimal.ZERO);
            }
            conocimientoService.saveOrUpdate(this.getSelectedOportunidad());

            this.setContenidoPlain(Jsoup.parse(this.getContenidoHtml()).text());
            GcmFileUtils.writeStringToFileServer(this.getSelectedOportunidad().getVruta(), "html.txt", this.getContenidoHtml());
            GcmFileUtils.writeStringToFileServer(this.getSelectedOportunidad().getVruta(), "plain.txt", this.getContenidoHtml());

            HistorialService historialService = (HistorialService) ServiceFinder.findBean("HistorialService");
            Historial lastHistorial = historialService.getLastHistorialByConocimiento(this.getSelectedOportunidad().getNconocimientoid());
            int lastversion;
            if(lastHistorial != null) {
                lastversion = lastHistorial.getNnumversion().intValue();
            } else {
                lastversion = 0;
            }
            String url = this.path.concat(this.getSelectedOportunidad().getNconocimientoid().toString()).concat("/").concat(Integer.toString(lastversion + 1)).concat("/");

            ThistorialId thistorialId = new ThistorialId();
            thistorialId.setNconocimientoid(this.getSelectedOportunidad().getNconocimientoid());
            thistorialId.setNhistorialid(historialService.getNextPK());
            Historial historial = new Historial();
            historial.setId(thistorialId);
            historial.setNtipoconocimientoid(Constante.OPORTUNIDADMEJORA);
            historial.setNcategoriaid(this.getSelectedCategoria().getNcategoriaid());
            historial.setVtitulo(this.getSelectedOportunidad().getVtitulo());
            historial.setVdescripcion(this.getSelectedOportunidad().getVdescripcion());
            historial.setNactivo(BigDecimal.ONE);
            historial.setNsituacionid(this.getSelectedOportunidad().getNsituacionid());
            historial.setVruta(url);
            historial.setNnumversion(BigDecimal.valueOf(lastversion + 1));
            historial.setDfechacreacion(new Date());
            historial.setVusuariocreacion(user.getVlogin());
            historialService.saveOrUpdate(historial);

            GcmFileUtils.writeStringToFileServer(url, "html.txt", this.getContenidoHtml());
            GcmFileUtils.writeStringToFileServer(url, "plain.txt", this.getContenidoPlain());
            
            if (CollectionUtils.isNotEmpty(this.getListaSeccion())) {
                String url0 = this.getSelectedOportunidad().getVruta().concat("s");
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
                        seccion.setNconocimientoid(this.getSelectedOportunidad().getNconocimientoid());
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
                vinculoService.deleteByConocimiento(this.getSelectedOportunidad().getNconocimientoid());
                for (Consulta consulta : this.getListaTargetVinculos()) {
                    Vinculo vinculo = new Vinculo();
                    vinculo.setNvinculoid(vinculoService.getNextPK());
                    vinculo.setNconocimientoid(this.getSelectedOportunidad().getNconocimientoid());
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
            
            
            AsignacionService asignacionService = (AsignacionService) ServiceFinder.findBean("AsignacionService");
            List<Asignacion> listaAsignacion = conocimientoService.obtenerOmejoraxAsig(this.getSelectedOportunidad().getNconocimientoid(), user.getNusuarioid(), Constante.OPORTUNIDADMEJORA);
            if(CollectionUtils.isNotEmpty(listaAsignacion)) {
                Asignacion asignacion = listaAsignacion.get(0);
                asignacion.setNestadoid(BigDecimal.valueOf(Long.parseLong("2")));
                if(asignacion.getDfecharecepcion() == null) {
                    asignacion.setDfecharecepcion(new Date());
                }
                asignacion.setDfechaatencion(new Date());
                asignacion.setNaccionid(BigDecimal.valueOf(Long.parseLong("8")));
                asignacionService.saveOrUpdate(asignacion);
            }
            
            Asignacion nueva_asignacion = new Asignacion();
            AsignacionService serviceasig = (AsignacionService) ServiceFinder.findBean("AsignacionService");
            nueva_asignacion.setNasignacionid(serviceasig.getNextPK());
            nueva_asignacion.setNtipoconocimientoid(Constante.OPORTUNIDADMEJORA);
            nueva_asignacion.setNconocimientoid(this.getSelectedOportunidad().getNconocimientoid());
            nueva_asignacion.setNestadoid(BigDecimal.valueOf(Long.parseLong(Constante.SITUACION_POR_ANALIZAR)));
            CategoriaService categoriaService = (CategoriaService) ServiceFinder.findBean("CategoriaService");
            nueva_asignacion.setNusuarioid(categoriaService.getCategoriaById(this.getSelectedOportunidad().getNcategoriaid()).getNespecialista());
            nueva_asignacion.setDfechaasignacion(new Date());
            nueva_asignacion.setDfechacreacion(new Date());
            serviceasig.saveOrUpdate(nueva_asignacion);
            
            this.setListaOportunidad(conocimientoService.getConocimientosByType(Constante.OPORTUNIDADMEJORA));
            FacesContext.getCurrentInstance().getExternalContext().redirect("/gescon/pages/oportunidad/lista.xhtml");
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
    }
    
    public void activar(ActionEvent event) {
        try {
            if (event != null) {
                if (this.getSelectedOportunidad()!= null) {
                    LoginMB loginMB = (LoginMB) JSFUtils.getSessionAttribute("loginMB");
                    User user = loginMB.getUser();
                    ConocimientoService service = (ConocimientoService) ServiceFinder.findBean("ConocimientoService");
                    this.getSelectedOportunidad().setNactivo(BigDecimal.ONE);
                    this.getSelectedOportunidad().setDfechamodificacion(new Date());
                    this.getSelectedOportunidad().setVusuariomodificacion(user.getVlogin());
                    service.saveOrUpdate(this.getSelectedOportunidad());
                    this.setListaOportunidad(service.getConocimientosByType(Constante.OPORTUNIDADMEJORA));
                } else {
                    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, Constante.SEVERETY_ALERTA, "Debe seleccionar la oportunidad de mejora a activar.");
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
                if (this.getSelectedOportunidad() != null) {
                    LoginMB loginMB = (LoginMB) JSFUtils.getSessionAttribute("loginMB");
                    User user = loginMB.getUser();
                    ConocimientoService service = (ConocimientoService) ServiceFinder.findBean("ConocimientoService");
                    this.getSelectedOportunidad().setNactivo(BigDecimal.ZERO);
                    this.getSelectedOportunidad().setNdestacado(BigDecimal.ZERO);
                    this.getSelectedOportunidad().setDfechamodificacion(new Date());
                    this.getSelectedOportunidad().setVusuariomodificacion(user.getVlogin());
                    service.saveOrUpdate(this.getSelectedOportunidad());
                    this.setListaOportunidad(service.getConocimientosByType(Constante.OPORTUNIDADMEJORA));
                } else {
                    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, Constante.SEVERETY_ALERTA, "Debe seleccionar la oportunidad de mejora a desactivar.");
                    FacesContext.getCurrentInstance().addMessage(null, message);
                }
            }
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
    }
    
    public void toCompare(ActionEvent event){
        try {
            if (event != null) {
                if(CollectionUtils.isEmpty(this.getSelectedHistoriales())){
                    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR.", "Debe seleccionar dos versiones para comparar.");
                    FacesContext.getCurrentInstance().addMessage(null, message);
                    return;
                }
                if(CollectionUtils.isNotEmpty(this.getSelectedHistoriales()) && this.getSelectedHistoriales().size() != 2){
                    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR.", "Sólo se puede comparar dos versiones a la vez.");
                    FacesContext.getCurrentInstance().addMessage(null, message);
                    return;
                }
                SeccionHistService seccionHistService = (SeccionHistService) ServiceFinder.findBean("SeccionHistService");
                VinculoHistService vinculoHistService = (VinculoHistService) ServiceFinder.findBean("VinculoHistService");
                for(Historial historial : this.getSelectedHistoriales()) {
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
                FacesContext.getCurrentInstance().getExternalContext().redirect("/gescon/pages/oportunidad/historialCompare.xhtml");
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
            if(CollectionUtils.isNotEmpty(this.getListaHistorial())) {
                for(Historial h : this.getListaHistorial()) {
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
            
            this.getSelectedOportunidad().setNcategoriaid(hist.getNcategoriaid());
            this.getSelectedOportunidad().setNdias(hist.getNdias());
            this.getSelectedOportunidad().setVcontenido(hist.getVcontenido());
            this.getSelectedOportunidad().setVdescripcion(hist.getVdescripcion());
            this.getSelectedOportunidad().setVmsjrespuesta(hist.getVmsjrespuesta());
            this.getSelectedOportunidad().setVmsjsolicita(hist.getVmsjsolicita());
            this.getSelectedOportunidad().setVnumero(hist.getVnumero());
            this.getSelectedOportunidad().setVobservacion(hist.getVobservacion());
            this.getSelectedOportunidad().setVtema(hist.getVtema());
            this.getSelectedOportunidad().setVtitulo(hist.getVtitulo());
            this.getSelectedOportunidad().setDfechamodificacion(new Date());
            this.getSelectedOportunidad().setVusuariomodificacion(user.getVlogin());
            
            ConocimientoService conocimientoService = (ConocimientoService) ServiceFinder.findBean("ConocimientoService");
            conocimientoService.saveOrUpdate(this.getSelectedOportunidad());
            GcmFileUtils.writeStringToFileServer(this.getSelectedOportunidad().getVruta(), "html.txt", html);
            GcmFileUtils.writeStringToFileServer(this.getSelectedOportunidad().getVruta(), "plain.txt", plain);
            
            SeccionService seccionService = (SeccionService) ServiceFinder.findBean("SeccionService");
            seccionService.deleteSeccionesByConocimiento(this.getSelectedOportunidad().getNconocimientoid());
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
                    
                    String ruta1 = this.getSelectedOportunidad().getVruta().concat(seccion.getNorden().toString()).concat("/");
                    GcmFileUtils.writeStringToFileServer(ruta1, "html.txt", seccion.getDetalleHtml());
                    GcmFileUtils.writeStringToFileServer(ruta1, "plain.txt", seccion.getDetallePlain());
                }
            }
            
            VinculoService vinculoService = (VinculoService) ServiceFinder.findBean("VinculoService");
            vinculoService.deleteByConocimiento(this.getSelectedOportunidad().getNconocimientoid());
            VinculoHistService vinculoHistService = (VinculoHistService) ServiceFinder.findBean("VinculoHistService");
            List<VinculoHist> listaVinculoHist = vinculoHistService.getVinculoHistsByHistorial(hist.getId().getNhistorialid());
            if (CollectionUtils.isNotEmpty(listaVinculoHist)) {
                for (VinculoHist vinculoHist : listaVinculoHist) {
                    Vinculo vinculo = new Vinculo();
                    vinculo.setNvinculoid(vinculoService.getNextPK());
                    vinculo.setNconocimientoid(this.getSelectedOportunidad().getNconocimientoid());
                    vinculo.setNconocimientovinc(vinculoHist.getNconocimientovinc());
                    vinculo.setNtipoconocimientovinc(vinculoHist.getNtipoconocimientovinc());
                    vinculo.setVusuariocreacion(user.getVlogin());
                    vinculo.setDfechacreacion(new Date());
                    vinculoService.saveOrUpdate(vinculo);
                }
            }
            FacesContext.getCurrentInstance().getExternalContext().redirect("/gescon/pages/oportunidad/vistaConsulta.xhtml");
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
            cal.setNconocimientoid(this.getSelectedOportunidad().getNconocimientoid());
            cal.setNcalificacion(this.getCalificacion());
            cal.setVcomentario(StringUtils.capitalize(this.getComentario().trim()));
            cal.setDfechacreacion(new Date());
            cal.setVusuariocreacion(user.getVlogin());
            calificacionService.saveOrUpdate(cal);
            this.setListaCalificacion(calificacionService.getCalificacionesByConocimiento(this.getSelectedOportunidad().getNconocimientoid()));
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
            this.setListaCalificacion(calificacionService.getCalificacionesByConocimiento(this.getSelectedOportunidad().getNconocimientoid()));
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
            this.setListaCalificacion(calificacionService.getCalificacionesByConocimiento(this.getSelectedOportunidad().getNconocimientoid()));
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
    
    public String toAnalysis() {
        try {
            this.clearAll();
            int index = Integer.parseInt((String) JSFUtils.getRequestParameter("index"));
            if(!CollectionUtils.isEmpty(this.getFilteredListaOportunidad())) {
                this.setSelectedOportunidad(this.getFilteredListaOportunidad().get(index));
            } else {
                this.setSelectedOportunidad(this.getListaOportunidad().get(index));
            }
            CategoriaService categoriaService = (CategoriaService) ServiceFinder.findBean("CategoriaService");
            this.setSelectedCategoria(categoriaService.getCategoriaById(this.getSelectedOportunidad().getNcategoriaid()));
            this.setChkDestacado(this.getSelectedOportunidad().getNdestacado().equals(BigDecimal.ONE));
            this.setContenidoHtml(GcmFileUtils.readStringFromFileServer(this.getSelectedOportunidad().getVruta(), "html.txt"));
            SeccionService seccionService = (SeccionService) ServiceFinder.findBean("SeccionService");
            this.setListaSeccion(seccionService.getSeccionesByConocimiento(this.getSelectedOportunidad().getNconocimientoid()));
            if (CollectionUtils.isNotEmpty(this.getListaSeccion())) {
                for (Seccion seccion : this.getListaSeccion()) {
                    seccion.setDetalleHtml(GcmFileUtils.readStringFromFileServer(seccion.getVruta(), "html.txt"));
                }
            }
            ConocimientoService conocimientoService = (ConocimientoService) ServiceFinder.findBean("ConocimientoService");
            HashMap map = new HashMap();
            map.put("nconocimientoid", this.getSelectedOportunidad().getNconocimientoid().toString());
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
            RequestContext.getCurrentInstance().execute("PF('anaDialog').show();");
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
        return "/pages/oportunidad/analisis?faces-redirect=true";
    }
    
    public void saveAnalysis(ActionEvent event) {
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
            this.getSelectedOportunidad().setNanalisis(this.getAnalisis());
            this.getSelectedOportunidad().setNdias(BigDecimal.valueOf(this.getDias()));
            this.getSelectedOportunidad().setVobservacion(this.getMotivo());
            this.getSelectedOportunidad().setDfechamodificacion(new Date());
            this.getSelectedOportunidad().setVusuariomodificacion(user.getVlogin());
            ConocimientoService conocimientoService = (ConocimientoService) ServiceFinder.findBean("ConocimientoService");
            conocimientoService.saveOrUpdate(this.getSelectedOportunidad());
            this.setListaOportunidad(conocimientoService.getConocimientosByType(Constante.OPORTUNIDADMEJORA));
            FacesContext.getCurrentInstance().getExternalContext().redirect("/gescon/pages/oportunidad/lista.xhtml");
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
        for(Conocimiento c : this.getListaOportunidad()) {
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
