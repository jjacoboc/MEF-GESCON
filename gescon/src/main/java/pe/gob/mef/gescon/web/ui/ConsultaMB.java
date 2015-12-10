/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.gob.mef.gescon.web.ui;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.AjaxBehaviorEvent;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.primefaces.context.RequestContext;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.TreeNode;
import pe.gob.mef.gescon.common.Constante;
import pe.gob.mef.gescon.service.BaseLegalService;
import pe.gob.mef.gescon.service.CategoriaService;
import pe.gob.mef.gescon.service.ConocimientoService;
import pe.gob.mef.gescon.service.ConsultaService;
import pe.gob.mef.gescon.service.PreguntaService;
import pe.gob.mef.gescon.service.SeccionService;
import pe.gob.mef.gescon.service.TipoConocimientoService;
import pe.gob.mef.gescon.util.GcmFileUtils;
import pe.gob.mef.gescon.util.JSFUtils;
import pe.gob.mef.gescon.util.ServiceFinder;
import pe.gob.mef.gescon.web.bean.BaseLegal;
import pe.gob.mef.gescon.web.bean.Categoria;
import pe.gob.mef.gescon.web.bean.Consulta;
import pe.gob.mef.gescon.web.bean.Pregunta;
import pe.gob.mef.gescon.web.bean.Seccion;
import pe.gob.mef.gescon.web.bean.TipoConocimiento;

/**
 *
 * @author JJacobo
 */
@ManagedBean
@SessionScoped
public class ConsultaMB implements Serializable {

    private TreeNode tree;
    private List<Categoria> listaCategoriaFiltro;
    private Categoria selectedCategoriaFiltro;
    private List<Categoria> listaBreadCrumb;
    private Date fechaInicio;
    private Date fechaFin;
    private List<TipoConocimiento> listaTipoConocimientoFiltro;
    private List<String> selectedTipoConocimiento;
    private List<BaseLegal> listaBaseLegal;
    private List<Consulta> listaConsulta;
    private BaseLegal selectedBaseLegal;
    private StreamedContent content;
    private Pregunta selectedPregunta;

    /**
     * Creates a new instance of ConsultaMB
     */
    public ConsultaMB() {
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
     * @return the listaCategoriaFiltro
     */
    public List<Categoria> getListaCategoriaFiltro() {
        return listaCategoriaFiltro;
    }

    /**
     * @param listaCategoriaFiltro the listaCategoriaFiltro to set
     */
    public void setListaCategoriaFiltro(List<Categoria> listaCategoriaFiltro) {
        this.listaCategoriaFiltro = listaCategoriaFiltro;
    }

    /**
     * @return the selectedCategoriaFiltro
     */
    public Categoria getSelectedCategoriaFiltro() {
        return selectedCategoriaFiltro;
    }

    /**
     * @param selectedCategoriaFiltro the selectedCategoriaFiltro to set
     */
    public void setSelectedCategoriaFiltro(Categoria selectedCategoriaFiltro) {
        this.selectedCategoriaFiltro = selectedCategoriaFiltro;
    }

    /**
     * @return the listaBreadCrumb
     */
    public List<Categoria> getListaBreadCrumb() {
        return listaBreadCrumb;
    }

    /**
     * @param listaBreadCrumb the listaBreadCrumb to set
     */
    public void setListaBreadCrumb(List<Categoria> listaBreadCrumb) {
        this.listaBreadCrumb = listaBreadCrumb;
    }

    /**
     * @return the fechaInicio
     */
    public Date getFechaInicio() {
        return fechaInicio;
    }

    /**
     * @param fechaInicio the fechaInicio to set
     */
    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    /**
     * @return the fechaFin
     */
    public Date getFechaFin() {
        return fechaFin;
    }

    /**
     * @param fechaFin the fechaFin to set
     */
    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    /**
     * @return the listaTipoConocimientoFiltro
     */
    public List<TipoConocimiento> getListaTipoConocimientoFiltro() {
        return listaTipoConocimientoFiltro;
    }

    /**
     * @param listaTipoConocimientoFiltro the listaTipoConocimientoFiltro to set
     */
    public void setListaTipoConocimientoFiltro(List<TipoConocimiento> listaTipoConocimientoFiltro) {
        this.listaTipoConocimientoFiltro = listaTipoConocimientoFiltro;
    }

    /**
     * @return the selectedTipoConocimiento
     */
    public List<String> getSelectedTipoConocimiento() {
        return selectedTipoConocimiento;
    }

    /**
     * @param selectedTipoConocimiento the selectedTipoConocimiento to set
     */
    public void setSelectedTipoConocimiento(List<String> selectedTipoConocimiento) {
        this.selectedTipoConocimiento = selectedTipoConocimiento;
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

    /**
     * @return the listaConsulta
     */
    public List<Consulta> getListaConsulta() {
        return listaConsulta;
    }

    /**
     * @param listaConsulta the listaConsulta to set
     */
    public void setListaConsulta(List<Consulta> listaConsulta) {
        this.listaConsulta = listaConsulta;
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

    public Pregunta getSelectedPregunta() {
        return selectedPregunta;
    }

    public void setSelectedPregunta(Pregunta selectedPregunta) {
        this.selectedPregunta = selectedPregunta;
    }

    public void init() {
        try {
            CategoriaService catservice = (CategoriaService) ServiceFinder.findBean("CategoriaService");
            this.setListaCategoriaFiltro(catservice.getCategorias());
            createTree(this.getListaCategoriaFiltro());
            this.setListaBreadCrumb(new ArrayList<Categoria>());
            TipoConocimientoService tcservice = (TipoConocimientoService) ServiceFinder.findBean("TipoConocimientoService");
            this.setListaTipoConocimientoFiltro(tcservice.getTipoConocimientos());
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

    public List<String> getIdsSubTreeByNode(TreeNode node, List<String> ids) {
        try {
            if (node != null) {
                List<TreeNode> lista = node.getChildren();
                if (lista != null && !lista.isEmpty()) {
                    for (TreeNode n : lista) {
                        if (n != null) {
                            Categoria c = (Categoria) n.getData();
                            String id = c.getNcategoriaid().toString();
                            if (StringUtils.isNotBlank(id)) {
                                ids.add(id);
                                getIdsSubTreeByNode(n, ids);
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
        return ids;
    }

    public void onClickSubCategoriesFilter(ActionEvent event) {
        try {
            int index = Integer.parseInt((String) JSFUtils.getRequestParameter("rowIndex"));
            this.setSelectedCategoriaFiltro(this.getListaCategoriaFiltro().get(index));
            this.getListaBreadCrumb().add(this.getSelectedCategoriaFiltro());
            this.filtrar(event);
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
    }

    public void onClickBreadCrumb(ActionEvent event) {
        int pos = 0;
        try {
            String id = (String) JSFUtils.getRequestParameter("ncategoriaid");
            for (int i = 0; i < this.getListaBreadCrumb().size(); i++) {
                Categoria c = this.getListaBreadCrumb().get(i);
                if (c.getNcategoriaid().toString().equals(id)) {
                    pos = i;
                    break;
                }
            }
            this.setSelectedCategoriaFiltro(this.getListaBreadCrumb().get(pos));
            this.setListaBreadCrumb(this.getListaBreadCrumb().subList(0, pos + 1));
            this.filtrar(event);
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
    }
    
    public String getCategoriesFilter(){
        String id;
        TreeNode node;
        List<String> ids = new ArrayList<String>();
        try {
            id = this.getSelectedCategoriaFiltro().getNcategoriaid().toString();
            ids.add(id);
            node = this.getNodeByIdCategoria(this.getTree(), id);
            ids.addAll(getIdsSubTreeByNode(node, ids));
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
        return StringUtils.join(ids, ',');
    }
    
    public String getTypesFilter(){
        String filter = StringUtils.join(this.getSelectedTipoConocimiento(), ',');
        if(StringUtils.isBlank(filter)) {
            filter = "1,2,3,4,5,6";
        }
        return filter;
    }

    public void filtrar(ActionEvent event) {
        HashMap filter = new HashMap();
        try {
            filter.put("fCategoria", this.getCategoriesFilter());
            filter.put("fFromDate", this.getFechaInicio());
            filter.put("fToDate", this.getFechaFin());
            filter.put("fType", this.getTypesFilter());
            ConsultaService service = (ConsultaService) ServiceFinder.findBean("ConsultaService");
            this.setListaConsulta(service.getQueryFilter(filter));
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
    }
    
    public void filtrar(AjaxBehaviorEvent event) {
        HashMap filter = new HashMap();
        try {
            filter.put("fCategoria", this.getCategoriesFilter());
            filter.put("fFromDate", this.getFechaInicio());
            filter.put("fToDate", this.getFechaFin());
            filter.put("fType", this.getTypesFilter());
            ConsultaService service = (ConsultaService) ServiceFinder.findBean("ConsultaService");
            this.setListaConsulta(service.getQueryFilter(filter));
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
    }
    
    public void view(ActionEvent event) {
        try {
            int id = Integer.parseInt((String) JSFUtils.getRequestParameter("id"));
            int idTipo = Integer.parseInt((String) JSFUtils.getRequestParameter("idTipo"));
            switch(idTipo) {
                case 1: { //Base Legal
                    BaseLegalMB bl = new BaseLegalMB();
                    BaseLegalService service = (BaseLegalService) ServiceFinder.findBean("BaseLegalService");
                    bl.setSelectedBaseLegal(service.getBaselegalById(BigDecimal.valueOf(id)));
                    bl.setChkGobNacional(bl.getSelectedBaseLegal().getNgobnacional().equals(BigDecimal.ONE));
                    bl.setChkGobRegional(bl.getSelectedBaseLegal().getNgobregional().equals(BigDecimal.ONE));
                    bl.setChkGobLocal(bl.getSelectedBaseLegal().getNgoblocal().equals(BigDecimal.ONE));
                    bl.setChkMancomunidades(bl.getSelectedBaseLegal().getNmancomunidades().equals(BigDecimal.ONE));
                    CategoriaService categoriaService = (CategoriaService) ServiceFinder.findBean("CategoriaService");
                    bl.setSelectedCategoria(categoriaService.getCategoriaById(bl.getSelectedBaseLegal().getNcategoriaid()));
                    bl.setListaTarget(service.getTbaselegalesLinkedById(bl.getSelectedBaseLegal().getNbaselegalid()));
                    JSFUtils.getSession().setAttribute("baseLegalMB", bl);
                    FacesContext.getCurrentInstance().getExternalContext().redirect("/gescon/pages/baselegal/ver.xhtml");
                    break;
                }
                case 2: { //Preguntas y respuestas
                    PreguntaService service = (PreguntaService) ServiceFinder.findBean("PreguntaService");
                    this.setSelectedPregunta(service.getPreguntaById(BigDecimal.valueOf(id)));
                    RequestContext.getCurrentInstance().execute("PF('viewprDialog').show();");
                    break;
                }
                case 3: { //Wiki
                    WikiMB mb = new WikiMB();
                    ConocimientoService service = (ConocimientoService) ServiceFinder.findBean("ConocimientoService");
                    mb.setSelectedWiki(service.getConocimientoById(BigDecimal.valueOf(id)));
                    CategoriaService categoriaService = (CategoriaService) ServiceFinder.findBean("CategoriaService");
                    mb.setSelectedCategoria(categoriaService.getCategoriaById(mb.getSelectedWiki().getNcategoriaid()));
                    mb.setDescripcionHtml(GcmFileUtils.readStringFromFileServer(mb.getSelectedWiki().getVruta(), "html.txt"));
                    SeccionService seccionService = (SeccionService) ServiceFinder.findBean("SeccionService");
                    mb.setListaSeccion(seccionService.getSeccionesByConocimiento(mb.getSelectedWiki().getNconocimientoid()));
                    if (CollectionUtils.isNotEmpty(mb.getListaSeccion())) {
                        for (Seccion seccion : mb.getListaSeccion()) {
                            seccion.setDetalleHtml(GcmFileUtils.readStringFromFileServer(seccion.getVruta(), "html.txt"));
                        }
                    }
                    ConocimientoService conocimientoService = (ConocimientoService) ServiceFinder.findBean("ConocimientoService");
                    HashMap map = new HashMap();
                    map.put("nconocimientoid", mb.getSelectedWiki().getNconocimientoid().toString());
                    map.put("flag", true);
                    map.put("ntipoconocimientoid", Constante.BASELEGAL.toString());
                    mb.setListaTargetVinculosBL(conocimientoService.getConcimientosVinculados(map));
                    map.put("ntipoconocimientoid", Constante.PREGUNTAS.toString());
                    mb.setListaTargetVinculosPR(conocimientoService.getConcimientosVinculados(map));
                    map.put("ntipoconocimientoid", Constante.BUENAPRACTICA.toString());
                    mb.setListaTargetVinculosBP(conocimientoService.getConcimientosVinculados(map));
                    map.put("ntipoconocimientoid", Constante.CONTENIDO.toString());
                    mb.setListaTargetVinculosCT(conocimientoService.getConcimientosVinculados(map));
                    map.put("ntipoconocimientoid", Constante.OPORTUNIDADMEJORA.toString());
                    mb.setListaTargetVinculosOM(conocimientoService.getConcimientosVinculados(map));
                    map.put("ntipoconocimientoid", Constante.WIKI.toString());
                    mb.setListaTargetVinculosWK(conocimientoService.getConcimientosVinculados(map));
                    JSFUtils.getSession().setAttribute("wikiMB", mb);
                    FacesContext.getCurrentInstance().getExternalContext().redirect("/gescon/pages/wiki/ver.xhtml");
                    break;
                }
                case 5: { //Buen Practica
                    BuenaPracticaMB bp = new BuenaPracticaMB();
                    ConocimientoService service = (ConocimientoService) ServiceFinder.findBean("ConocimientoService");
                    bp.setSelectedBuenaPractica(service.getConocimientoById(BigDecimal.valueOf(id)));
                    CategoriaService categoriaService = (CategoriaService) ServiceFinder.findBean("CategoriaService");
                    bp.setSelectedCategoria(categoriaService.getCategoriaById(bp.getSelectedBuenaPractica().getNcategoriaid()));
                    bp.setDescripcionHtml(GcmFileUtils.readStringFromFileServer(bp.getSelectedBuenaPractica().getVruta(), "html.txt"));
                    SeccionService seccionService = (SeccionService) ServiceFinder.findBean("SeccionService");
                    bp.setListaSeccion(seccionService.getSeccionesByConocimiento(bp.getSelectedBuenaPractica().getNconocimientoid()));
                    if (CollectionUtils.isNotEmpty(bp.getListaSeccion())) {
                        for (Seccion seccion : bp.getListaSeccion()) {
                            seccion.setDetalleHtml(GcmFileUtils.readStringFromFileServer(seccion.getVruta(), "html.txt"));
                        }
                    }
                    ConocimientoService conocimientoService = (ConocimientoService) ServiceFinder.findBean("ConocimientoService");
                    HashMap map = new HashMap();
                    map.put("nconocimientoid", bp.getSelectedBuenaPractica().getNconocimientoid().toString());
                    map.put("flag", true);
                    map.put("ntipoconocimientoid", Constante.BASELEGAL.toString());
                    bp.setListaTargetVinculosBL(conocimientoService.getConcimientosVinculados(map));
                    map.put("ntipoconocimientoid", Constante.PREGUNTAS.toString());
                    bp.setListaTargetVinculosPR(conocimientoService.getConcimientosVinculados(map));
                    map.put("ntipoconocimientoid", Constante.BUENAPRACTICA.toString());
                    bp.setListaTargetVinculosBP(conocimientoService.getConcimientosVinculados(map));
                    map.put("ntipoconocimientoid", Constante.CONTENIDO.toString());
                    bp.setListaTargetVinculosCT(conocimientoService.getConcimientosVinculados(map));
                    map.put("ntipoconocimientoid", Constante.OPORTUNIDADMEJORA.toString());
                    bp.setListaTargetVinculosOM(conocimientoService.getConcimientosVinculados(map));
                    map.put("ntipoconocimientoid", Constante.WIKI.toString());
                    bp.setListaTargetVinculosWK(conocimientoService.getConcimientosVinculados(map));
                    JSFUtils.getSession().setAttribute("buenaPracticaMB", bp);
                    FacesContext.getCurrentInstance().getExternalContext().redirect("/gescon/pages/buenapractica/ver.xhtml");
                    break;
                }
            }
            
        } catch(Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
    }
}
