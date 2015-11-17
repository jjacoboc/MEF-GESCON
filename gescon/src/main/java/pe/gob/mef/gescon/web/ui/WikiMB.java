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
import java.util.HashMap;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.faces.event.AjaxBehaviorEvent;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.primefaces.component.selectonemenu.SelectOneMenu;
import org.primefaces.event.NodeSelectEvent;
import org.primefaces.event.TransferEvent;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.DualListModel;
import org.primefaces.model.TreeNode;
import pe.gob.mef.gescon.common.Constante;
import pe.gob.mef.gescon.service.CategoriaService;
import pe.gob.mef.gescon.service.WikiService;
import pe.gob.mef.gescon.util.ServiceFinder;
import pe.gob.mef.gescon.web.bean.Categoria;
import pe.gob.mef.gescon.web.bean.Conocimiento;
import pe.gob.mef.gescon.web.bean.Consulta;
import pe.gob.mef.gescon.web.bean.Seccion;
import pe.gob.mef.gescon.web.bean.Wiki;

/**
 *
 * @author JJacobo
 */
@ManagedBean
@ViewScoped
public class WikiMB implements Serializable {

    private final String path = "D:\\gescon\\files\\wk\\";
    private List<Wiki> listaWiki;
    private Wiki selectedWiki;
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

    /**
     * Creates a new instance of WikiMB
     */
    public WikiMB() {
    }

    public List<Wiki> getListaWiki() {
        return listaWiki;
    }

    public void setListaWiki(List<Wiki> listaWiki) {
        this.listaWiki = listaWiki;
    }

    public Wiki getSelectedWiki() {
        return selectedWiki;
    }

    public void setSelectedWiki(Wiki selectedWiki) {
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
            this.setListaSeccion(new ArrayList<Seccion>());
            this.setSelectedSeccion(null);
            this.setTitulo(StringUtils.EMPTY);
            this.setDetalleHtml(StringUtils.EMPTY);
            this.setListaSourceVinculos(new ArrayList());
            this.setListaTargetVinculos(new ArrayList());
            this.setPickList(new DualListModel<Consulta>(this.getListaSourceVinculos(), this.getListaTargetVinculos()));
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
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
    }

    public void loadTree(ActionEvent event) {
        try {
            if (this.getTree() == null) {
                CategoriaService service = (CategoriaService) ServiceFinder.findBean("CategoriaService");
                createTree(service.getCategorias());
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
            Seccion seccion = new Seccion();
            seccion.setVtitulo(this.getTitulo());
            seccion.setDetalleHtml(this.getDetalleHtml());
            seccion.setDetallePlain(this.getDetallePlain());
            if (CollectionUtils.isEmpty(this.getListaSeccion())) {
                this.setListaSeccion(new ArrayList<Seccion>());
                seccion.setNorden(BigDecimal.ONE);
            } else {
                seccion.setNorden(BigDecimal.valueOf(this.getListaSeccion().size() + 1));
            }
            this.getListaSeccion().add(seccion);
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
                    WikiService service = (WikiService) ServiceFinder.findBean("WikiService");
                    if (this.getSelectedWiki() != null) {
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

    public void save(ActionEvent event) {
        try {
            Conocimiento wiki = new Conocimiento();
            wiki.setVtitulo(this.getNombre());
            wiki.setNcategoriaid(this.getSelectedCategoria().getNcategoriaid());
            Seccion seccion = new Seccion();
            seccion.setVtitulo(this.getTitulo());
            seccion.setDetalleHtml(this.getDetalleHtml());
            seccion.setDetallePlain(this.getDetallePlain());
            if (CollectionUtils.isEmpty(this.getListaSeccion())) {
                this.setListaSeccion(new ArrayList<Seccion>());
                seccion.setNorden(BigDecimal.ONE);
            } else {
                seccion.setNorden(BigDecimal.valueOf(this.getListaSeccion().size() + 1));
            }
            this.getListaSeccion().add(seccion);
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
    }
}
