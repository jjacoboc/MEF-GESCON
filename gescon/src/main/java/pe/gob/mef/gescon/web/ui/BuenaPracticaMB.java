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
import pe.gob.mef.gescon.service.CategoriaService;
import pe.gob.mef.gescon.service.ConocimientoService;
import pe.gob.mef.gescon.service.HistorialService;
import pe.gob.mef.gescon.service.SeccionHistService;
import pe.gob.mef.gescon.service.SeccionService;
import pe.gob.mef.gescon.service.VinculoHistService;
import pe.gob.mef.gescon.service.VinculoService;
import pe.gob.mef.gescon.util.GcmFileUtils;
import pe.gob.mef.gescon.util.JSFUtils;
import pe.gob.mef.gescon.util.ServiceFinder;
import pe.gob.mef.gescon.web.bean.Categoria;
import pe.gob.mef.gescon.web.bean.Conocimiento;
import pe.gob.mef.gescon.web.bean.Consulta;
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
public class BuenaPracticaMB implements Serializable{
    
    private final String path = "bp/";
    private List<Conocimiento> listaBuenaPractica;
    private List<Conocimiento> filteredListaBuenaPractica;
    private Conocimiento selectedBuenaPractica;
    private TreeNode tree;
    private Categoria selectedCategoria;
    private String nombre;
    private String descripcionHtml;
    private String descripcionPlain;
    private List<Seccion> listaSeccion;
    private Seccion selectedSeccion;
    private String titulo;
    private String detalleHtml;
    private String detallePlain;
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
    
    public BuenaPracticaMB() {
        
    }

    public List<Conocimiento> getListaBuenaPractica() {
        return listaBuenaPractica;
    }

    public void setListaBuenaPractica(List<Conocimiento> listaBuenaPractica) {
        this.listaBuenaPractica = listaBuenaPractica;
    }

    public List<Conocimiento> getFilteredListaBuenaPractica() {
        return filteredListaBuenaPractica;
    }

    public void setFilteredListaBuenaPractica(List<Conocimiento> filteredListaBuenaPractica) {
        this.filteredListaBuenaPractica = filteredListaBuenaPractica;
    }

    public Conocimiento getSelectedBuenaPractica() {
        return selectedBuenaPractica;
    }

    public void setSelectedBuenaPractica(Conocimiento selectedBuenaPractica) {
        this.selectedBuenaPractica = selectedBuenaPractica;
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
    
    @PostConstruct
    public void init() {
        try {
            ConocimientoService conocimientoService = (ConocimientoService) ServiceFinder.findBean("ConocimientoService");
            this.setListaBuenaPractica(conocimientoService.getConocimientosByType(Constante.BUENAPRACTICA));
            this.setListaSourceVinculos(new ArrayList<Consulta>());
            this.setListaTargetVinculos(new ArrayList<Consulta>());
            this.setPickList(new DualListModel<Consulta>(this.getListaSourceVinculos(), this.getListaTargetVinculos()));
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
            this.setListaSeccion(new ArrayList());
            this.setListaSourceVinculos(new ArrayList());
            this.setListaTargetVinculos(new ArrayList());
            this.setListaTargetVinculosBL(new ArrayList());
            this.setListaTargetVinculosBP(new ArrayList());
            this.setListaTargetVinculosCT(new ArrayList());
            this.setListaTargetVinculosOM(new ArrayList());
            this.setListaTargetVinculosPR(new ArrayList());
            this.setListaTargetVinculosWK(new ArrayList());
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
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR.", "Título de la sección requerido. Ingrese el título de la sección a agregar.");
                FacesContext.getCurrentInstance().addMessage(null, message);
                return;
            }
            if(StringUtils.isBlank(this.getDetalleHtml())) {
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
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR.", "Título de la sección requerido. Ingrese el título de la sección a agregar.");
                FacesContext.getCurrentInstance().addMessage(null, message);
                return;
            }
            if(StringUtils.isBlank(this.getSelectedSeccion().getDetalleHtml())) {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR.", "Detalle de la sección requerido. Ingrese el detalle de la sección a agregar.");
                FacesContext.getCurrentInstance().addMessage(null, message);
                return;
            }
            RequestContext.getCurrentInstance().execute("PF('esecDialog').hide();");
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
                    if (this.getSelectedBuenaPractica()!= null) {
                        filters.put("nconocimientoid", this.getSelectedBuenaPractica().getNconocimientoid().toString());
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
                            filter = filter.concat(",").concat(this.getSelectedBuenaPractica().getNconocimientoid().toString());
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
    
    public String toSave() {
        try {
            this.clearAll();
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
        return "/pages/buenapractica/nuevo?faces-redirect=true";
    }

    public void save(ActionEvent event) {
        try {
            if(this.getSelectedCategoria() == null) {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR.", "Seleccione la categoría de la buena práctica a registrar.");
                FacesContext.getCurrentInstance().addMessage(null, message);
                return;
            }
            if(StringUtils.isBlank(this.getNombre())) {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR.", "Ingrese el título de la buena práctica a registrar.");
                FacesContext.getCurrentInstance().addMessage(null, message);
                return;
            }
            if(StringUtils.isBlank(this.getDescripcionHtml())) {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR.", "Ingrese la descripción de la buena práctica a registrar.");
                FacesContext.getCurrentInstance().addMessage(null, message);
                return;
            }
            if(CollectionUtils.isEmpty(this.getListaSeccion())) {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR.", "Ingrese al menos un (01) paso a seguir.");
                FacesContext.getCurrentInstance().addMessage(null, message);
                return;
            }
            this.setDescripcionPlain(Jsoup.parse(this.getDescripcionHtml()).text());
            LoginMB loginMB = (LoginMB) JSFUtils.getSessionAttribute("loginMB");
            User user = loginMB.getUser();
            ConocimientoService conocimientoService = (ConocimientoService) ServiceFinder.findBean("ConocimientoService");
            Conocimiento conocimiento = new Conocimiento();
            conocimiento.setNtipoconocimientoid(Constante.BUENAPRACTICA);
            conocimiento.setNconocimientoid(conocimientoService.getNextPK());
            conocimiento.setNcategoriaid(this.getSelectedCategoria().getNcategoriaid());
            conocimiento.setVtitulo(StringUtils.upperCase(this.getNombre()));
            if(this.getDescripcionPlain().length() < 400) {
                conocimiento.setVdescripcion(StringUtils.capitalize(this.getDescripcionPlain()));
            } else {
                conocimiento.setVdescripcion(StringUtils.capitalize(this.getDescripcionPlain().substring(0, 400)));
            }
            conocimiento.setNactivo(BigDecimal.ONE);
            if (this.getSelectedCategoria().getNflagbp().equals(BigDecimal.ONE)) {
                conocimiento.setNsituacionid(BigDecimal.valueOf(Long.parseLong(Constante.SITUACION_POR_VERIFICAR)));
            } else {
                conocimiento.setNsituacionid(BigDecimal.valueOf(Long.parseLong(Constante.SITUACION_PUBLICADO)));
            }
            String np0 = this.path.concat(conocimiento.getNconocimientoid().toString()).concat("/0/");
            conocimiento.setVruta(np0);
            conocimiento.setDfechacreacion(new Date());
            conocimiento.setVusuariocreacion(user.getVlogin());
            conocimientoService.saveOrUpdate(conocimiento);

            this.setDescripcionPlain(Jsoup.parse(this.getDescripcionHtml()).text());
            GcmFileUtils.writeStringToFileServer(np0, "html.txt", this.getDescripcionHtml());
            GcmFileUtils.writeStringToFileServer(np0, "plain.txt", this.getDescripcionPlain());

            String np1 = this.path.concat(conocimiento.getNconocimientoid().toString()).concat("/1/");
            HistorialService historialService = (HistorialService) ServiceFinder.findBean("HistorialService");
            ThistorialId thistorialId = new ThistorialId();
            thistorialId.setNconocimientoid(conocimiento.getNconocimientoid());
            thistorialId.setNhistorialid(historialService.getNextPK());
            Historial historial = new Historial();
            historial.setId(thistorialId);
            historial.setNtipoconocimientoid(Constante.BUENAPRACTICA);
            historial.setNcategoriaid(this.getSelectedCategoria().getNcategoriaid());
            historial.setVtitulo(this.getNombre());
            historial.setNactivo(BigDecimal.ONE);
            historial.setNsituacionid(conocimiento.getNsituacionid());
            historial.setVruta(np1);
            historial.setNnumversion(BigDecimal.ONE);
            historial.setDfechacreacion(new Date());
            historial.setVusuariocreacion(user.getVlogin());
            historialService.saveOrUpdate(historial);

            GcmFileUtils.writeStringToFileServer(np1, "html.txt", this.getDescripcionHtml());
            GcmFileUtils.writeStringToFileServer(np1, "plain.txt", this.getDescripcionPlain());
            
            if (CollectionUtils.isNotEmpty(this.getListaSeccion())) {
                String url0 = this.path.concat(conocimiento.getNconocimientoid().toString()).concat("/0/s");
                String url1 = this.path.concat(conocimiento.getNconocimientoid().toString()).concat("/1/s");
                SeccionService seccionService = (SeccionService) ServiceFinder.findBean("SeccionService");
                SeccionHistService seccionHistService = (SeccionHistService) ServiceFinder.findBean("SeccionHistService");
                for (Seccion seccion : this.getListaSeccion()) {
                    String ruta0 = url0.concat(seccion.getNorden().toString()).concat("/");
                    seccion.setVruta(ruta0);
                    seccion.setNseccionid(seccionService.getNextPK());
                    seccion.setNconocimientoid(conocimiento.getNconocimientoid());
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
            this.setListaBuenaPractica(conocimientoService.getConocimientosByType(Constante.BUENAPRACTICA));
            FacesContext.getCurrentInstance().getExternalContext().redirect("/gescon/pages/buenapractica/lista.xhtml");
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
    }
    
    public String toView() {
        try {
            this.clearAll();
            int index = Integer.parseInt((String) JSFUtils.getRequestParameter("index"));
            if(!CollectionUtils.isEmpty(this.getFilteredListaBuenaPractica())) {
                this.setSelectedBuenaPractica(this.getFilteredListaBuenaPractica().get(index));
            } else {
                this.setSelectedBuenaPractica(this.getListaBuenaPractica().get(index));
            }
            CategoriaService categoriaService = (CategoriaService) ServiceFinder.findBean("CategoriaService");
            this.setSelectedCategoria(categoriaService.getCategoriaById(this.getSelectedBuenaPractica().getNcategoriaid()));
            this.setDescripcionHtml(GcmFileUtils.readStringFromFileServer(this.getSelectedBuenaPractica().getVruta(), "html.txt"));
            SeccionService seccionService = (SeccionService) ServiceFinder.findBean("SeccionService");
            this.setListaSeccion(seccionService.getSeccionesByConocimiento(this.getSelectedBuenaPractica().getNconocimientoid()));
            if (CollectionUtils.isNotEmpty(this.getListaSeccion())) {
                for (Seccion seccion : this.getListaSeccion()) {
                    seccion.setDetalleHtml(GcmFileUtils.readStringFromFileServer(seccion.getVruta(), "html.txt"));
                }
            }
            ConocimientoService conocimientoService = (ConocimientoService) ServiceFinder.findBean("ConocimientoService");
            HashMap map = new HashMap();
            map.put("nconocimientoid", this.getSelectedBuenaPractica().getNconocimientoid().toString());
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
            this.setFilteredListaBuenaPractica(new ArrayList());
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
        return "/pages/buenapractica/ver?faces-redirect=true";
    }

    public String toEdit() {
        try {
            this.clearAll();
            int index = Integer.parseInt((String) JSFUtils.getRequestParameter("index"));
            if(!CollectionUtils.isEmpty(this.getFilteredListaBuenaPractica())) {
                this.setSelectedBuenaPractica(this.getFilteredListaBuenaPractica().get(index));
            } else {
                this.setSelectedBuenaPractica(this.getListaBuenaPractica().get(index));
            }
            CategoriaService categoriaService = (CategoriaService) ServiceFinder.findBean("CategoriaService");
            this.setSelectedCategoria(categoriaService.getCategoriaById(this.getSelectedBuenaPractica().getNcategoriaid()));
            this.setDescripcionHtml(GcmFileUtils.readStringFromFileServer(this.getSelectedBuenaPractica().getVruta(), "html.txt"));
            SeccionService seccionService = (SeccionService) ServiceFinder.findBean("SeccionService");
            this.setListaSeccion(seccionService.getSeccionesByConocimiento(this.getSelectedBuenaPractica().getNconocimientoid()));
            if (CollectionUtils.isNotEmpty(this.getListaSeccion())) {
                for (Seccion seccion : this.getListaSeccion()) {
                    seccion.setDetalleHtml(GcmFileUtils.readStringFromFileServer(seccion.getVruta(), "html.txt"));
                }
            }
            ConocimientoService conocimientoService = (ConocimientoService) ServiceFinder.findBean("ConocimientoService");
            HashMap map = new HashMap();
            map.put("nconocimientoid", this.getSelectedBuenaPractica().getNconocimientoid().toString());
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
            this.setFilteredListaBuenaPractica(new ArrayList());
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
        return "/pages/buenapractica/editar?faces-redirect=true";
    }

    public void edit(ActionEvent event) {
        try {
            if(this.getSelectedCategoria() == null) {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR.", "Categoría del wiki requerida. Seleccione la categoría del wiki a registrar.");
                FacesContext.getCurrentInstance().addMessage(null, message);
                return;
            }
            if(StringUtils.isBlank(this.getSelectedBuenaPractica().getVtitulo())) {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR.", "Título del wiki requerido. Ingrese el título del wiki a registrar.");
                FacesContext.getCurrentInstance().addMessage(null, message);
                return;
            }
            if(StringUtils.isBlank(this.getDescripcionHtml())) {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR.", "Descripción del wiki requerido. Ingrese la descripción del wiki a registrar.");
                FacesContext.getCurrentInstance().addMessage(null, message);
                return;
            }
            if(CollectionUtils.isEmpty(this.getListaSeccion())) {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR.", "Ingrese al menos un (01) paso a seguir.");
                FacesContext.getCurrentInstance().addMessage(null, message);
                return;
            }
            this.setDescripcionPlain(Jsoup.parse(this.getDescripcionHtml()).text());
            LoginMB loginMB = (LoginMB) JSFUtils.getSessionAttribute("loginMB");
            User user = loginMB.getUser();
            ConocimientoService conocimientoService = (ConocimientoService) ServiceFinder.findBean("ConocimientoService");
            this.getSelectedBuenaPractica().setNcategoriaid(this.getSelectedCategoria().getNcategoriaid());
            this.getSelectedBuenaPractica().setVtitulo(StringUtils.upperCase(this.getSelectedBuenaPractica().getVtitulo().trim()));
            if(this.getDescripcionPlain().length() < 400) {
                this.getSelectedBuenaPractica().setVdescripcion(StringUtils.capitalize(this.getDescripcionPlain()));
            } else {
                this.getSelectedBuenaPractica().setVdescripcion(StringUtils.capitalize(this.getDescripcionPlain().substring(0, 400)));
            }
            this.getSelectedBuenaPractica().setDfechamodificacion(new Date());
            this.getSelectedBuenaPractica().setVusuariomodificacion(user.getVlogin());
            conocimientoService.saveOrUpdate(this.getSelectedBuenaPractica());

            
            GcmFileUtils.writeStringToFileServer(this.getSelectedBuenaPractica().getVruta(), "html.txt", this.getDescripcionHtml());
            GcmFileUtils.writeStringToFileServer(this.getSelectedBuenaPractica().getVruta(), "plain.txt", this.getDescripcionPlain());

            HistorialService historialService = (HistorialService) ServiceFinder.findBean("HistorialService");
            Historial lastHistorial = historialService.getLastHistorialByConocimiento(this.getSelectedBuenaPractica().getNconocimientoid());
            int lastversion;
            if(lastHistorial != null) {
                lastversion = lastHistorial.getNnumversion().intValue();
            } else {
                lastversion = 0;
            }
            String url = this.path.concat(this.getSelectedBuenaPractica().getNconocimientoid().toString()).concat("/").concat(Integer.toString(lastversion + 1)).concat("/");

            ThistorialId thistorialId = new ThistorialId();
            thistorialId.setNconocimientoid(this.getSelectedBuenaPractica().getNconocimientoid());
            thistorialId.setNhistorialid(historialService.getNextPK());
            Historial historial = new Historial();
            historial.setId(thistorialId);
            historial.setNtipoconocimientoid(Constante.WIKI);
            historial.setNcategoriaid(this.getSelectedCategoria().getNcategoriaid());
            historial.setVtitulo(this.getSelectedBuenaPractica().getVtitulo());
            historial.setNactivo(BigDecimal.ONE);
            historial.setNsituacionid(this.getSelectedBuenaPractica().getNsituacionid());
            historial.setVruta(url);
            historial.setNnumversion(BigDecimal.valueOf(lastversion + 1));
            historial.setDfechacreacion(new Date());
            historial.setVusuariocreacion(user.getVlogin());
            historialService.saveOrUpdate(historial);

            GcmFileUtils.writeStringToFileServer(url, "html.txt", this.getDescripcionHtml());
            GcmFileUtils.writeStringToFileServer(url, "plain.txt", this.getDescripcionPlain());
            
            if (CollectionUtils.isNotEmpty(this.getListaSeccion())) {
                String url0 = this.getSelectedBuenaPractica().getVruta().concat("s");
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
                        seccion.setNconocimientoid(this.getSelectedBuenaPractica().getNconocimientoid());
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
                vinculoService.deleteByConocimiento(this.getSelectedBuenaPractica().getNconocimientoid());
                for (Consulta consulta : this.getListaTargetVinculos()) {
                    Vinculo vinculo = new Vinculo();
                    vinculo.setNvinculoid(vinculoService.getNextPK());
                    vinculo.setNconocimientoid(this.getSelectedBuenaPractica().getNconocimientoid());
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
            this.setListaBuenaPractica(conocimientoService.getConocimientosByType(Constante.BUENAPRACTICA));
            FacesContext.getCurrentInstance().getExternalContext().redirect("/gescon/pages/buenapractica/lista.xhtml");
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
    }
    
    public String toPost() {
        try {
            this.clearAll();
            int index = Integer.parseInt((String) JSFUtils.getRequestParameter("index"));
            if(!CollectionUtils.isEmpty(this.getFilteredListaBuenaPractica())) {
                this.setSelectedBuenaPractica(this.getFilteredListaBuenaPractica().get(index));
            } else {
                this.setSelectedBuenaPractica(this.getListaBuenaPractica().get(index));
            }
            CategoriaService categoriaService = (CategoriaService) ServiceFinder.findBean("CategoriaService");
            this.setSelectedCategoria(categoriaService.getCategoriaById(this.getSelectedBuenaPractica().getNcategoriaid()));
            this.setDescripcionHtml(GcmFileUtils.readStringFromFileServer(this.getSelectedBuenaPractica().getVruta(), "html.txt"));
            SeccionService seccionService = (SeccionService) ServiceFinder.findBean("SeccionService");
            this.setListaSeccion(seccionService.getSeccionesByConocimiento(this.getSelectedBuenaPractica().getNconocimientoid()));
            if (CollectionUtils.isNotEmpty(this.getListaSeccion())) {
                for (Seccion seccion : this.getListaSeccion()) {
                    seccion.setDetalleHtml(GcmFileUtils.readStringFromFileServer(seccion.getVruta(), "html.txt"));
                }
            }
            ConocimientoService conocimientoService = (ConocimientoService) ServiceFinder.findBean("ConocimientoService");
            HashMap map = new HashMap();
            map.put("nconocimientoid", this.getSelectedBuenaPractica().getNconocimientoid().toString());
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
            this.setFilteredListaBuenaPractica(new ArrayList());
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
        return "/pages/buenapractica/publicar?faces-redirect=true";
    }
    
    public void post(ActionEvent event) {
        try {
            if(this.getSelectedCategoria() == null) {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR.", "Categoría del wiki requerida. Seleccione la categoría del wiki a registrar.");
                FacesContext.getCurrentInstance().addMessage(null, message);
                return;
            }
            if(StringUtils.isBlank(this.getSelectedBuenaPractica().getVtitulo())) {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR.", "Título del wiki requerido. Ingrese el título del wiki a registrar.");
                FacesContext.getCurrentInstance().addMessage(null, message);
                return;
            }
            if(StringUtils.isBlank(this.getDescripcionHtml())) {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR.", "Descripción del wiki requerido. Ingrese la descripción del wiki a registrar.");
                FacesContext.getCurrentInstance().addMessage(null, message);
                return;
            }
            if(CollectionUtils.isEmpty(this.getListaSeccion())) {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR.", "Ingrese al menos un (01) paso a seguir.");
                FacesContext.getCurrentInstance().addMessage(null, message);
                return;
            }
            LoginMB loginMB = (LoginMB) JSFUtils.getSessionAttribute("loginMB");
            User user = loginMB.getUser();
            ConocimientoService conocimientoService = (ConocimientoService) ServiceFinder.findBean("ConocimientoService");
            this.setDescripcionPlain(Jsoup.parse(this.getDescripcionHtml()).text());
            this.getSelectedBuenaPractica().setNcategoriaid(this.getSelectedCategoria().getNcategoriaid());
            this.getSelectedBuenaPractica().setNsituacionid(BigDecimal.valueOf(Long.parseLong(Constante.SITUACION_PUBLICADO)));
            this.getSelectedBuenaPractica().setVtitulo(StringUtils.upperCase(this.getSelectedBuenaPractica().getVtitulo().trim()));
            if(this.getDescripcionPlain().length() < 400) {
                this.getSelectedBuenaPractica().setVdescripcion(StringUtils.capitalize(this.getDescripcionPlain()));
            } else {
                this.getSelectedBuenaPractica().setVdescripcion(StringUtils.capitalize(this.getDescripcionPlain().substring(0, 400)));
            }
            this.getSelectedBuenaPractica().setDfechapublicacion(new Date());
            this.getSelectedBuenaPractica().setDfechamodificacion(new Date());
            this.getSelectedBuenaPractica().setVusuariomodificacion(user.getVlogin());
            conocimientoService.saveOrUpdate(this.getSelectedBuenaPractica());

            
            GcmFileUtils.writeStringToFileServer(this.getSelectedBuenaPractica().getVruta(), "html.txt", this.getDescripcionHtml());
            GcmFileUtils.writeStringToFileServer(this.getSelectedBuenaPractica().getVruta(), "plain.txt", this.getDescripcionPlain());

            HistorialService historialService = (HistorialService) ServiceFinder.findBean("HistorialService");
            Historial lastHistorial = historialService.getLastHistorialByConocimiento(this.getSelectedBuenaPractica().getNconocimientoid());
            int lastversion;
            if(lastHistorial != null) {
                lastversion = lastHistorial.getNnumversion().intValue();
            } else {
                lastversion = 0;
            }
            String url = this.path.concat(this.getSelectedBuenaPractica().getNconocimientoid().toString()).concat("/").concat(Integer.toString(lastversion + 1)).concat("/");

            ThistorialId thistorialId = new ThistorialId();
            thistorialId.setNconocimientoid(this.getSelectedBuenaPractica().getNconocimientoid());
            thistorialId.setNhistorialid(historialService.getNextPK());
            Historial historial = new Historial();
            historial.setId(thistorialId);
            historial.setNtipoconocimientoid(Constante.WIKI);
            historial.setNcategoriaid(this.getSelectedCategoria().getNcategoriaid());
            historial.setVtitulo(this.getSelectedBuenaPractica().getVtitulo());
            historial.setNactivo(BigDecimal.ONE);
            historial.setNsituacionid(this.getSelectedBuenaPractica().getNsituacionid());
            historial.setVruta(url);
            historial.setNnumversion(BigDecimal.valueOf(lastversion + 1));
            historial.setDfechacreacion(new Date());
            historial.setVusuariocreacion(user.getVlogin());
            historialService.saveOrUpdate(historial);

            GcmFileUtils.writeStringToFileServer(url, "html.txt", this.getDescripcionHtml());
            GcmFileUtils.writeStringToFileServer(url, "plain.txt", this.getDescripcionPlain());
            
            if (CollectionUtils.isNotEmpty(this.getListaSeccion())) {
                String url0 = this.getSelectedBuenaPractica().getVruta().concat("s");
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
                        seccion.setNconocimientoid(this.getSelectedBuenaPractica().getNconocimientoid());
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
                vinculoService.deleteByConocimiento(this.getSelectedBuenaPractica().getNconocimientoid());
                for (Consulta consulta : this.getListaTargetVinculos()) {
                    Vinculo vinculo = new Vinculo();
                    vinculo.setNvinculoid(vinculoService.getNextPK());
                    vinculo.setNconocimientoid(this.getSelectedBuenaPractica().getNconocimientoid());
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
            this.setListaBuenaPractica(conocimientoService.getConocimientosByType(Constante.BUENAPRACTICA));
            FacesContext.getCurrentInstance().getExternalContext().redirect("/gescon/pages/buenapractica/lista.xhtml");
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
    }

    public void activar(ActionEvent event) {
        try {
            if (event != null) {
                if (this.getSelectedBuenaPractica() != null) {
                    ConocimientoService service = (ConocimientoService) ServiceFinder.findBean("ConocimientoService");
                    this.getSelectedBuenaPractica().setNactivo(BigDecimal.ONE);
                    this.getSelectedBuenaPractica().setDfechamodificacion(new Date());
                    service.saveOrUpdate(this.getSelectedBuenaPractica());
                    this.setListaBuenaPractica(service.getConocimientosByType(Constante.BUENAPRACTICA));
                } else {
                    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, Constante.SEVERETY_ALERTA, "Debe seleccionar la buena practica a activar.");
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
                if (this.getSelectedBuenaPractica() != null) {
                    ConocimientoService service = (ConocimientoService) ServiceFinder.findBean("ConocimientoService");
                    this.getSelectedBuenaPractica().setNactivo(BigDecimal.ZERO);
                    this.getSelectedBuenaPractica().setDfechamodificacion(new Date());
                    service.saveOrUpdate(this.getSelectedBuenaPractica());
                    this.setListaBuenaPractica(service.getConocimientosByType(Constante.BUENAPRACTICA));
                } else {
                    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, Constante.SEVERETY_ALERTA, "Debe seleccionar la buena practica a desactivar.");
                    FacesContext.getCurrentInstance().addMessage(null, message);
                }
            }
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
    }
}
