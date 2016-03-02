/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.gob.mef.gescon.web.ui;

import java.io.File;
import java.io.IOException;
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
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.util.JRLoader;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.primefaces.context.RequestContext;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.TreeNode;
import pe.gob.mef.gescon.common.Constante;
import pe.gob.mef.gescon.lucene.Indexador;
import pe.gob.mef.gescon.service.ArchivoConocimientoService;
import pe.gob.mef.gescon.service.ArchivoService;
import pe.gob.mef.gescon.service.BaseLegalHistorialService;
import pe.gob.mef.gescon.service.BaseLegalService;
import pe.gob.mef.gescon.service.CalificacionBaseLegalService;
import pe.gob.mef.gescon.service.CalificacionPreguntaService;
import pe.gob.mef.gescon.service.CalificacionService;
import pe.gob.mef.gescon.service.CategoriaService;
import pe.gob.mef.gescon.service.ConocimientoService;
import pe.gob.mef.gescon.service.ConsultaService;
import pe.gob.mef.gescon.service.DiscusionSeccionService;
import pe.gob.mef.gescon.service.DiscusionService;
import pe.gob.mef.gescon.service.HistorialService;
import pe.gob.mef.gescon.service.ParametroService;
import pe.gob.mef.gescon.service.PreguntaService;
import pe.gob.mef.gescon.service.SeccionService;
import pe.gob.mef.gescon.service.TipoConocimientoService;
import pe.gob.mef.gescon.service.UserService;
import pe.gob.mef.gescon.util.GcmFileUtils;
import pe.gob.mef.gescon.util.JSFUtils;
import pe.gob.mef.gescon.util.ServiceFinder;
import pe.gob.mef.gescon.web.bean.BaseLegal;
import pe.gob.mef.gescon.web.bean.BaselegalHist;
import pe.gob.mef.gescon.web.bean.Calificacion;
import pe.gob.mef.gescon.web.bean.CalificacionBaselegal;
import pe.gob.mef.gescon.web.bean.CalificacionPregunta;
import pe.gob.mef.gescon.web.bean.Categoria;
import pe.gob.mef.gescon.web.bean.Consulta;
import pe.gob.mef.gescon.web.bean.Discusion;
import pe.gob.mef.gescon.web.bean.DiscusionSeccion;
import pe.gob.mef.gescon.web.bean.Historial;
import pe.gob.mef.gescon.web.bean.Pregunta;
import pe.gob.mef.gescon.web.bean.Seccion;
import pe.gob.mef.gescon.web.bean.TipoConocimiento;
import pe.gob.mef.gescon.web.bean.User;

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
    private String searchText;
    private String tipoConocimiento;
    private String categoria;
    private String ordenpor;
    private String calificacion1;
    private String calificacion2;
    private String calificacion3;
    private String calificacion4;
    private String calificacion5;
    private String tempPopup;

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

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public List<TipoConocimiento> getListaTipoConocimientoFiltro() {
        return listaTipoConocimientoFiltro;
    }

    public void setListaTipoConocimientoFiltro(List<TipoConocimiento> listaTipoConocimientoFiltro) {
        this.listaTipoConocimientoFiltro = listaTipoConocimientoFiltro;
    }

    public List<String> getSelectedTipoConocimiento() {
        return selectedTipoConocimiento;
    }

    public void setSelectedTipoConocimiento(List<String> selectedTipoConocimiento) {
        this.selectedTipoConocimiento = selectedTipoConocimiento;
    }

    public List<BaseLegal> getListaBaseLegal() {
        return listaBaseLegal;
    }

    public void setListaBaseLegal(List<BaseLegal> listaBaseLegal) {
        this.listaBaseLegal = listaBaseLegal;
    }

    public List<Consulta> getListaConsulta() {
        return listaConsulta;
    }

    public void setListaConsulta(List<Consulta> listaConsulta) {
        this.listaConsulta = listaConsulta;
    }

    public BaseLegal getSelectedBaseLegal() {
        return selectedBaseLegal;
    }

    public void setSelectedBaseLegal(BaseLegal selectedBaseLegal) {
        this.selectedBaseLegal = selectedBaseLegal;
    }

    public StreamedContent getContent() {
        return content;
    }

    public void setContent(StreamedContent content) {
        this.content = content;
    }

    public Pregunta getSelectedPregunta() {
        return selectedPregunta;
    }

    public void setSelectedPregunta(Pregunta selectedPregunta) {
        this.selectedPregunta = selectedPregunta;
    }

    public String getSearchText() {
        return searchText;
    }

    public void setSearchText(String searchText) {
        this.searchText = searchText;
    }

    public String getTipoConocimiento() {
        return tipoConocimiento;
    }

    public void setTipoConocimiento(String tipoConocimiento) {
        this.tipoConocimiento = tipoConocimiento;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getOrdenpor() {
        return ordenpor;
    }

    public void setOrdenpor(String ordenpor) {
        this.ordenpor = ordenpor;
    }

    public String getCalificacion1() {
        return calificacion1;
    }

    public void setCalificacion1(String calificacion1) {
        this.calificacion1 = calificacion1;
    }

    public String getCalificacion2() {
        return calificacion2;
    }

    public void setCalificacion2(String calificacion2) {
        this.calificacion2 = calificacion2;
    }

    public String getCalificacion3() {
        return calificacion3;
    }

    public void setCalificacion3(String calificacion3) {
        this.calificacion3 = calificacion3;
    }

    public String getCalificacion4() {
        return calificacion4;
    }

    public void setCalificacion4(String calificacion4) {
        this.calificacion4 = calificacion4;
    }

    public String getCalificacion5() {
        return calificacion5;
    }

    public void setCalificacion5(String calificacion5) {
        this.calificacion5 = calificacion5;
    }

    public String getTempPopup() {
        return tempPopup;
    }

    public void setTempPopup(String tempPopup) {
        this.tempPopup = tempPopup;
    }

    public void init() {
        try {
            ParametroService parametroService = (ParametroService) ServiceFinder.findBean("ParametroService");
            this.setCalificacion1(parametroService.getParametroById(BigDecimal.valueOf(Long.valueOf(Constante.CALIFICACION1))).getVvalor());
            this.setCalificacion2(parametroService.getParametroById(BigDecimal.valueOf(Long.valueOf(Constante.CALIFICACION2))).getVvalor());
            this.setCalificacion3(parametroService.getParametroById(BigDecimal.valueOf(Long.valueOf(Constante.CALIFICACION3))).getVvalor());
            this.setCalificacion4(parametroService.getParametroById(BigDecimal.valueOf(Long.valueOf(Constante.CALIFICACION4))).getVvalor());
            this.setCalificacion5(parametroService.getParametroById(BigDecimal.valueOf(Long.valueOf(Constante.CALIFICACION5))).getVvalor());
            CategoriaService catservice = (CategoriaService) ServiceFinder.findBean("CategoriaService");
            this.setListaCategoriaFiltro(catservice.getCategoriasActived());
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
            if(this.getSelectedCategoriaFiltro() != null) {
                id = this.getSelectedCategoriaFiltro().getNcategoriaid().toString();
                ids.add(id);
                node = this.getNodeByIdCategoria(this.getTree(), id);
                ids.addAll(getIdsSubTreeByNode(node, ids));
            }
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
            if(StringUtils.isNotBlank(this.getSearchText())) {
                HashMap map = Indexador.search(this.getSearchText());
                filter.put("fCodesBL", (String) map.get("codesBL"));
                filter.put("fCodesPR", (String) map.get("codesPR"));
                filter.put("fCodesC", (String) map.get("codesC"));
            } else {
                filter.remove("fCodesBL");
                filter.remove("fCodesPR");
                filter.remove("fCodesC");
            }
            filter.put("order", this.getOrdenpor());
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
            if(StringUtils.isNotBlank(this.getSearchText())) {
                HashMap map = Indexador.search(this.getSearchText());
                filter.put("fCodesBL", (String) map.get("codesBL"));
                filter.put("fCodesPR", (String) map.get("codesPR"));
                filter.put("fCodesC", (String) map.get("codesC"));
            } else {
                filter.remove("fCodesBL");
                filter.remove("fCodesPR");
                filter.remove("fCodesC");
            }
            filter.put("order", this.getOrdenpor());
            ConsultaService service = (ConsultaService) ServiceFinder.findBean("ConsultaService");
            this.setListaConsulta(service.getQueryFilter(filter));
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
    }
    
    public String search() {
        HashMap filter = new HashMap();
        try {
            filter.put("fCategoria", this.getCategoriesFilter());
            filter.put("fFromDate", this.getFechaInicio());
            filter.put("fToDate", this.getFechaFin());
            filter.put("fType", this.getTypesFilter());
            if(StringUtils.isNotBlank(this.getSearchText())) {
                HashMap map = Indexador.search(this.getSearchText());
                filter.put("fCodesBL", (String) map.get("codesBL"));
                filter.put("fCodesPR", (String) map.get("codesPR"));
                filter.put("fCodesC", (String) map.get("codesC"));
            } else {
                filter.remove("fCodesBL");
                filter.remove("fCodesPR");
                filter.remove("fCodesC");
            }
            filter.put("order", this.getOrdenpor());
            ConsultaService service = (ConsultaService) ServiceFinder.findBean("ConsultaService");
            this.setListaConsulta(service.getQueryFilter(filter));
            if(CollectionUtils.isEmpty(this.getListaCategoriaFiltro())) {
                CategoriaService catservice = (CategoriaService) ServiceFinder.findBean("CategoriaService");
                this.setListaCategoriaFiltro(catservice.getCategoriasActived());
                createTree(this.getListaCategoriaFiltro());
                this.setListaBreadCrumb(new ArrayList<Categoria>());
            }
            if(CollectionUtils.isEmpty(this.getSelectedTipoConocimiento())) {
                TipoConocimientoService tcservice = (TipoConocimientoService) ServiceFinder.findBean("TipoConocimientoService");
                this.setListaTipoConocimientoFiltro(tcservice.getTipoConocimientos());
            }
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
        return "/pages/consulta?faces-redirect=true";
    }
    
    public String advanceSearch() {
        HashMap filter = new HashMap();
        try {
            if(CollectionUtils.isEmpty(this.getListaCategoriaFiltro())) {
                CategoriaService catservice = (CategoriaService) ServiceFinder.findBean("CategoriaService");
                this.setListaCategoriaFiltro(catservice.getCategoriasActived());
            }
            for(Categoria c : this.getListaCategoriaFiltro()) {
                if(c.getNcategoriaid().toString().equals(this.getCategoria())) {
                    this.setSelectedCategoriaFiltro(c);
                    break;
                }
            }
            this.setSelectedTipoConocimiento(new ArrayList<String>());
            if(StringUtils.isNotBlank(this.getTipoConocimiento())) {
                this.getSelectedTipoConocimiento().add(this.getTipoConocimiento());
            }
            filter.put("fCategoria", this.getCategoriesFilter());
            filter.put("fFromDate", this.getFechaInicio());
            filter.put("fToDate", this.getFechaFin());
            filter.put("fType", this.getTypesFilter());
            if(StringUtils.isNotBlank(this.getSearchText())) {
                HashMap map = Indexador.search(this.getSearchText());
                filter.put("fCodesBL", (String) map.get("codesBL"));
                filter.put("fCodesPR", (String) map.get("codesPR"));
                filter.put("fCodesC", (String) map.get("codesC"));
            } else {
                filter.remove("fCodesBL");
                filter.remove("fCodesPR");
                filter.remove("fCodesC");
            }
            filter.put("order", this.getOrdenpor());
            ConsultaService service = (ConsultaService) ServiceFinder.findBean("ConsultaService");
            this.setListaConsulta(service.getQueryFilter(filter));
            if(CollectionUtils.isEmpty(this.getListaCategoriaFiltro())) {
                CategoriaService catservice = (CategoriaService) ServiceFinder.findBean("CategoriaService");
                this.setListaCategoriaFiltro(catservice.getCategoriasActived());
                createTree(this.getListaCategoriaFiltro());
                this.setListaBreadCrumb(new ArrayList<Categoria>());
            }
            if(CollectionUtils.isEmpty(this.getSelectedTipoConocimiento())) {
                TipoConocimientoService tcservice = (TipoConocimientoService) ServiceFinder.findBean("TipoConocimientoService");
                this.setListaTipoConocimientoFiltro(tcservice.getTipoConocimientos());
            }
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
        return "/pages/consulta?faces-redirect=true";
    }
    
    public void view(ActionEvent event) {
        try {
            int id = Integer.parseInt((String) JSFUtils.getRequestParameter("id"));
            int idTipo = Integer.parseInt((String) JSFUtils.getRequestParameter("idTipo"));
            boolean popup = Boolean.valueOf((String) JSFUtils.getRequestParameter("popup"));
            boolean tempPopup = Boolean.valueOf((String) JSFUtils.getRequestParameter("tempPopup"));
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
                    ArchivoService aservice = (ArchivoService) ServiceFinder.findBean("ArchivoService");
                    bl.getSelectedBaseLegal().setArchivo(aservice.getArchivoByBaseLegal(bl.getSelectedBaseLegal()));
                    bl.setListaTarget(service.getTbaselegalesLinkedById(bl.getSelectedBaseLegal().getNbaselegalid()));
                    
                    BaseLegalHistorialService historialService = (BaseLegalHistorialService) ServiceFinder.findBean("BaseLegalHistorialService");
                    bl.setListaHistorial(historialService.getHistorialesByBaselegal(bl.getSelectedBaseLegal().getNbaselegalid()));
                    if(CollectionUtils.isNotEmpty(bl.getListaHistorial())) {
                        UserService userService = (UserService) ServiceFinder.findBean("UserService");
                        for (BaselegalHist historial : bl.getListaHistorial()) {
                            User user = userService.getUserByLogin(historial.getVusuariocreacion());
                            historial.setVnombreusuario(user.getVnombres()+" "+user.getVapellidos());
                        }
                    }
                    CalificacionBaseLegalService calificacionService = (CalificacionBaseLegalService) ServiceFinder.findBean("CalificacionBaseLegalService");
                    bl.setListaCalificacion(calificacionService.getCalificacionesByConocimiento(bl.getSelectedBaseLegal().getNbaselegalid()));
                    if(CollectionUtils.isNotEmpty(bl.getListaCalificacion())) {
                        UserService userService = (UserService) ServiceFinder.findBean("UserService");
                        for (CalificacionBaselegal calificacion : bl.getListaCalificacion()) {
                            User user = userService.getUserByLogin(calificacion.getVusuariocreacion());
                            calificacion.setUsuarioNombre(user.getVnombres()+" "+user.getVapellidos());
                        }
                    }
                    JSFUtils.getSession().setAttribute("baseLegalMB", bl);
                    if(popup) {
                        if(tempPopup) {
                            RequestContext.getCurrentInstance().execute("popupCenter('/gescon/pages/baselegal/vistaPopup.xhtml','','1000','650');");
                        } else {
                            RequestContext.getCurrentInstance().execute("popupCenter('/gescon/pages/baselegal/vistaConsulta.xhtml','','1000','650');");
                        }
                        this.setTempPopup("true");
                    } else {
                        if(tempPopup) {
                            FacesContext.getCurrentInstance().getExternalContext().redirect("/gescon/pages/baselegal/vistaPopup.xhtml");
                            this.setTempPopup("true");
                        } else {
                            FacesContext.getCurrentInstance().getExternalContext().redirect("/gescon/pages/baselegal/vistaConsulta.xhtml");
                            this.setTempPopup("false");
                        }
                    }
                    break;
                }
                case 2: { //Preguntas y respuestas
                    PreguntaMB pr = new PreguntaMB();
                    PreguntaService service = (PreguntaService) ServiceFinder.findBean("PreguntaService");
                    pr.setSelectedPregunta(service.getPreguntaById(BigDecimal.valueOf(id)));
                    CategoriaService categoriaService = (CategoriaService) ServiceFinder.findBean("CategoriaService");
                    pr.setSelectedCategoria(categoriaService.getCategoriaById(pr.getSelectedPregunta().getNcategoriaid()));
                    pr.setEntidad(service.getNomEntidadbyIdEntidad(pr.getSelectedPregunta().getNentidadid()));
                    pr.setListaSourceVinculos(new ArrayList<Consulta>());
                    pr.setListaTargetVinculos(new ArrayList<Consulta>());
                    pr.setListaTargetVinculosConocimiento(new ArrayList<Consulta>());
                    pr.setListaTargetVinculosBL(new ArrayList<Consulta>());
                    pr.setListaTargetVinculosPR(new ArrayList<Consulta>());
                    pr.setListaTargetVinculosWK(new ArrayList<Consulta>());
                    pr.setListaTargetVinculosCT(new ArrayList<Consulta>());
                    pr.setListaTargetVinculosBP(new ArrayList<Consulta>());
                    pr.setListaTargetVinculosOM(new ArrayList<Consulta>());

                    HashMap filters = new HashMap();
                    filters.put("npreguntaid", pr.getSelectedPregunta().getNpreguntaid());
                    filters.put("ntipoconocimientoid", Constante.BASELEGAL);
                    pr.getListaTargetVinculosBL().addAll(service.getConcimientosVinculados(filters));
                    filters.put("ntipoconocimientoid", Constante.PREGUNTAS);
                    pr.getListaTargetVinculosPR().addAll(service.getConcimientosVinculados(filters));
                    filters.put("ntipoconocimientoid", Constante.WIKI);
                    pr.getListaTargetVinculosWK().addAll(service.getConcimientosVinculados(filters));
                    filters.put("ntipoconocimientoid", Constante.CONTENIDO);
                    pr.getListaTargetVinculosCT().addAll(service.getConcimientosVinculados(filters));
                    filters.put("ntipoconocimientoid", Constante.BUENAPRACTICA);
                    pr.getListaTargetVinculosBP().addAll(service.getConcimientosVinculados(filters));
                    filters.put("ntipoconocimientoid", Constante.OPORTUNIDADMEJORA);
                    pr.getListaTargetVinculosOM().addAll(service.getConcimientosVinculados(filters));
                    if (pr.getListaTargetVinculosBL() != null) {
                        pr.getListaTargetVinculosConocimiento().addAll(pr.getListaTargetVinculosBL());
                    }
                    if (pr.getListaTargetVinculosBP() != null) {
                        pr.getListaTargetVinculosConocimiento().addAll(pr.getListaTargetVinculosBP());
                    }
                    if (pr.getListaTargetVinculosCT() != null) {
                        pr.getListaTargetVinculosConocimiento().addAll(pr.getListaTargetVinculosCT());
                    }
                    if (pr.getListaTargetVinculosOM() != null) {
                        pr.getListaTargetVinculosConocimiento().addAll(pr.getListaTargetVinculosOM());
                    }
                    if (pr.getListaTargetVinculosWK() != null) {
                        pr.getListaTargetVinculosConocimiento().addAll(pr.getListaTargetVinculosWK());
                    }
                    CalificacionPreguntaService calificacionService = (CalificacionPreguntaService) ServiceFinder.findBean("CalificacionPreguntaService");
                    pr.setListaCalificacion(calificacionService.getCalificacionesByConocimiento(pr.getSelectedPregunta().getNpreguntaid()));
                    if(CollectionUtils.isNotEmpty(pr.getListaCalificacion())) {
                        UserService userService = (UserService) ServiceFinder.findBean("UserService");
                        for (CalificacionPregunta calificacion : pr.getListaCalificacion()) {
                            User user = userService.getUserByLogin(calificacion.getVusuariocreacion());
                            calificacion.setUsuarioNombre(user.getVnombres()+" "+user.getVapellidos());
                        }
                    }
                    JSFUtils.getSession().setAttribute("preguntaMB", pr);
                    if(popup) {
                        if(tempPopup) {
                            RequestContext.getCurrentInstance().execute("popupCenter('/gescon/pages/pregunta/vistaPopup.xhtml','','1000','650');");
                        } else {
                            RequestContext.getCurrentInstance().execute("popupCenter('/gescon/pages/pregunta/vistaConsulta.xhtml','','1000','650');");
                        }
                        this.setTempPopup("true");
                    } else {
                        if(tempPopup) {
                            FacesContext.getCurrentInstance().getExternalContext().redirect("/gescon/pages/pregunta/vistaPopup.xhtml");
                            this.setTempPopup("true");
                        } else {
                            FacesContext.getCurrentInstance().getExternalContext().redirect("/gescon/pages/pregunta/vistaConsulta.xhtml");
                            this.setTempPopup("false");
                        }
                    }
                    break;
                }
                case 3: { //Wiki
                    WikiMB mb = new WikiMB();
                    ConocimientoService conocimientoService = (ConocimientoService) ServiceFinder.findBean("ConocimientoService");
                    mb.setSelectedWiki(conocimientoService.getConocimientoById(BigDecimal.valueOf(id)));
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
                    DiscusionService discusionService = (DiscusionService) ServiceFinder.findBean("DiscusionService");
                    List listaDiscusion = discusionService.getDiscusionesByConocimiento(mb.getSelectedWiki().getNconocimientoid());
                    if(CollectionUtils.isNotEmpty(listaDiscusion)) {
                        Discusion discusion = (Discusion)listaDiscusion.get(0);
                        mb.setSelectedDiscusion(discusion);
                        DiscusionSeccionService discusionSeccionService = (DiscusionSeccionService) ServiceFinder.findBean("DiscusionSeccionService");
                        mb.setListaDiscusionSeccion(discusionSeccionService.getSeccionesByDiscusion(discusion.getNdiscusionid()));
                        UserService userService = (UserService) ServiceFinder.findBean("UserService");
                        if (CollectionUtils.isNotEmpty(mb.getListaDiscusionSeccion())) {
                            for (DiscusionSeccion discusionSeccion : mb.getListaDiscusionSeccion()) {
                                discusionSeccion.setDiscusionHtml(GcmFileUtils.readStringFromFileServer(discusionSeccion.getVruta(), "html.txt"));
                                User user = userService.getUserByLogin(discusionSeccion.getVusuariocreacion());
                                discusionSeccion.setUsuarioNombre(user.getVnombres()+" "+user.getVapellidos());
                            }
                        }
                    }
                    HistorialService historialService = (HistorialService) ServiceFinder.findBean("HistorialService");
                    mb.setListaHistorial(historialService.getHistorialesByConocimiento(mb.getSelectedWiki().getNconocimientoid()));
                    if(CollectionUtils.isNotEmpty(mb.getListaHistorial())) {
                        UserService userService = (UserService) ServiceFinder.findBean("UserService");
                        for (Historial historial : mb.getListaHistorial()) {
                            User user = userService.getUserByLogin(historial.getVusuariocreacion());
                            historial.setVnombreusuario(user.getVnombres()+" "+user.getVapellidos());
                        }
                    }
                    CalificacionService calificacionService = (CalificacionService) ServiceFinder.findBean("CalificacionService");
                    mb.setListaCalificacion(calificacionService.getCalificacionesByConocimiento(mb.getSelectedWiki().getNconocimientoid()));
                    if(CollectionUtils.isNotEmpty(mb.getListaCalificacion())) {
                        UserService userService = (UserService) ServiceFinder.findBean("UserService");
                        for (Calificacion calificacion : mb.getListaCalificacion()) {
                            User user = userService.getUserByLogin(calificacion.getVusuariocreacion());
                            calificacion.setUsuarioNombre(user.getVnombres()+" "+user.getVapellidos());
                        }
                    }
                    JSFUtils.getSession().setAttribute("wikiMB", mb);
                    if(popup) {
                        if(tempPopup) {
                            RequestContext.getCurrentInstance().execute("popupCenter('/gescon/pages/wiki/vistaPopup.xhtml','','1000','650');");
                        } else {
                            RequestContext.getCurrentInstance().execute("popupCenter('/gescon/pages/wiki/vistaConsulta.xhtml','','1000','650');");
                        }
                        this.setTempPopup("true");
                    } else {
                        if(tempPopup) {
                            FacesContext.getCurrentInstance().getExternalContext().redirect("/gescon/pages/wiki/vistaPopup.xhtml");
                            this.setTempPopup("true");
                        } else {
                            FacesContext.getCurrentInstance().getExternalContext().redirect("/gescon/pages/wiki/vistaConsulta.xhtml");
                            this.setTempPopup("false");
                        }
                    }
                    break;
                }
                case 4: { //Contenido
                    ContenidoMB ct = new ContenidoMB();
                    ConocimientoService service = (ConocimientoService) ServiceFinder.findBean("ConocimientoService");
                    ct.setSelectedContenido(service.getConocimientoById(BigDecimal.valueOf(id)));
                    CategoriaService categoriaService = (CategoriaService) ServiceFinder.findBean("CategoriaService");
                    ct.setSelectedCategoria(categoriaService.getCategoriaById(ct.getSelectedContenido().getNcategoriaid()));
                    ArchivoConocimientoService archivoservice = (ArchivoConocimientoService) ServiceFinder.findBean("ArchivoConocimientoService");
                    ct.setListaArchivos(archivoservice.getArchivosByConocimiento(ct.getSelectedContenido().getNconocimientoid()));
                    HashMap filters = new HashMap();
                    filters.put("nconocimientoid", ct.getSelectedContenido().getNconocimientoid());
                    filters.put("flag", true);
                    filters.put("ntipoconocimientoid", Constante.BASELEGAL.toString());
                    ct.setListaTargetVinculosBL(service.getConcimientosVinculados(filters));
                    filters.put("ntipoconocimientoid", Constante.PREGUNTAS.toString());
                    ct.setListaTargetVinculosPR(service.getConcimientosVinculados(filters));
                    filters.put("ntipoconocimientoid", Constante.WIKI.toString());
                    ct.setListaTargetVinculosWK(service.getConcimientosVinculados(filters));
                    filters.put("ntipoconocimientoid", Constante.CONTENIDO.toString());
                    ct.setListaTargetVinculosCT(service.getConcimientosVinculados(filters));
                    filters.put("ntipoconocimientoid", Constante.BUENAPRACTICA.toString());
                    ct.setListaTargetVinculosBP(service.getConcimientosVinculados(filters));
                    filters.put("ntipoconocimientoid", Constante.OPORTUNIDADMEJORA.toString());
                    ct.setListaTargetVinculosOM(service.getConcimientosVinculados(filters));
                    DiscusionService discusionService = (DiscusionService) ServiceFinder.findBean("DiscusionService");
                    List listaDiscusion = discusionService.getDiscusionesByConocimiento(ct.getSelectedContenido().getNconocimientoid());
                    if(CollectionUtils.isNotEmpty(listaDiscusion)) {
                        Discusion discusion = (Discusion)listaDiscusion.get(0);
                        ct.setSelectedDiscusion(discusion);
                        DiscusionSeccionService discusionSeccionService = (DiscusionSeccionService) ServiceFinder.findBean("DiscusionSeccionService");
                        ct.setListaDiscusionSeccion(discusionSeccionService.getSeccionesByDiscusion(discusion.getNdiscusionid()));
                        UserService userService = (UserService) ServiceFinder.findBean("UserService");
                        if (CollectionUtils.isNotEmpty(ct.getListaDiscusionSeccion())) {
                            for (DiscusionSeccion discusionSeccion : ct.getListaDiscusionSeccion()) {
                                discusionSeccion.setDiscusionHtml(GcmFileUtils.readStringFromFileServer(discusionSeccion.getVruta(), "html.txt"));
                                User user = userService.getUserByLogin(discusionSeccion.getVusuariocreacion());
                                discusionSeccion.setUsuarioNombre(user.getVnombres()+" "+user.getVapellidos());
                            }
                        }
                    }
                    HistorialService historialService = (HistorialService) ServiceFinder.findBean("HistorialService");
                    ct.setListaHistorial(historialService.getHistorialesByConocimiento(ct.getSelectedContenido().getNconocimientoid()));
                    if(CollectionUtils.isNotEmpty(ct.getListaHistorial())) {
                        UserService userService = (UserService) ServiceFinder.findBean("UserService");
                        for (Historial historial : ct.getListaHistorial()) {
                            User user = userService.getUserByLogin(historial.getVusuariocreacion());
                            historial.setVnombreusuario(user.getVnombres()+" "+user.getVapellidos());
                        }
                    }
                    CalificacionService calificacionService = (CalificacionService) ServiceFinder.findBean("CalificacionService");
                    ct.setListaCalificacion(calificacionService.getCalificacionesByConocimiento(ct.getSelectedContenido().getNconocimientoid()));
                    if(CollectionUtils.isNotEmpty(ct.getListaCalificacion())) {
                        UserService userService = (UserService) ServiceFinder.findBean("UserService");
                        for (Calificacion calificacion : ct.getListaCalificacion()) {
                            User user = userService.getUserByLogin(calificacion.getVusuariocreacion());
                            calificacion.setUsuarioNombre(user.getVnombres()+" "+user.getVapellidos());
                        }
                    }
                    JSFUtils.getSession().setAttribute("contenidoMB", ct);
                    if(popup) {
                        if(tempPopup) {
                            RequestContext.getCurrentInstance().execute("popupCenter('/gescon/pages/contenido/vistaPopup.xhtml','','1000','650');");
                        } else {
                            RequestContext.getCurrentInstance().execute("popupCenter('/gescon/pages/contenido/vistaConsulta.xhtml','','1000','650');");
                        }
                        this.setTempPopup("true");
                    } else {
                        if(tempPopup) {
                            FacesContext.getCurrentInstance().getExternalContext().redirect("/gescon/pages/contenido/vistaPopup.xhtml");
                            this.setTempPopup("true");
                        } else {
                            FacesContext.getCurrentInstance().getExternalContext().redirect("/gescon/pages/contenido/vistaConsulta.xhtml");
                            this.setTempPopup("false");
                        }
                    }
                    break;
                }
                case 5: { //Buena Practica
                    BuenaPracticaMB bp = new BuenaPracticaMB();
                    ConocimientoService conocimientoService = (ConocimientoService) ServiceFinder.findBean("ConocimientoService");
                    bp.setSelectedBuenaPractica(conocimientoService.getConocimientoById(BigDecimal.valueOf(id)));
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
                    DiscusionService discusionService = (DiscusionService) ServiceFinder.findBean("DiscusionService");
                    List listaDiscusion = discusionService.getDiscusionesByConocimiento(bp.getSelectedBuenaPractica().getNconocimientoid());
                    if(CollectionUtils.isNotEmpty(listaDiscusion)) {
                        Discusion discusion = (Discusion)listaDiscusion.get(0);
                        bp.setSelectedDiscusion(discusion);
                        DiscusionSeccionService discusionSeccionService = (DiscusionSeccionService) ServiceFinder.findBean("DiscusionSeccionService");
                        bp.setListaDiscusionSeccion(discusionSeccionService.getSeccionesByDiscusion(discusion.getNdiscusionid()));
                        UserService userService = (UserService) ServiceFinder.findBean("UserService");
                        if (CollectionUtils.isNotEmpty(bp.getListaDiscusionSeccion())) {
                            for (DiscusionSeccion discusionSeccion : bp.getListaDiscusionSeccion()) {
                                discusionSeccion.setDiscusionHtml(GcmFileUtils.readStringFromFileServer(discusionSeccion.getVruta(), "html.txt"));
                                User user = userService.getUserByLogin(discusionSeccion.getVusuariocreacion());
                                discusionSeccion.setUsuarioNombre(user.getVnombres()+" "+user.getVapellidos());
                            }
                        }
                    }
                    HistorialService historialService = (HistorialService) ServiceFinder.findBean("HistorialService");
                    bp.setListaHistorial(historialService.getHistorialesByConocimiento(bp.getSelectedBuenaPractica().getNconocimientoid()));
                    if(CollectionUtils.isNotEmpty(bp.getListaHistorial())) {
                        UserService userService = (UserService) ServiceFinder.findBean("UserService");
                        for (Historial historial : bp.getListaHistorial()) {
                            User user = userService.getUserByLogin(historial.getVusuariocreacion());
                            historial.setVnombreusuario(user.getVnombres()+" "+user.getVapellidos());
                        }
                    }
                    CalificacionService calificacionService = (CalificacionService) ServiceFinder.findBean("CalificacionService");
                    bp.setListaCalificacion(calificacionService.getCalificacionesByConocimiento(bp.getSelectedBuenaPractica().getNconocimientoid()));
                    if(CollectionUtils.isNotEmpty(bp.getListaCalificacion())) {
                        UserService userService = (UserService) ServiceFinder.findBean("UserService");
                        for (Calificacion calificacion : bp.getListaCalificacion()) {
                            User user = userService.getUserByLogin(calificacion.getVusuariocreacion());
                            calificacion.setUsuarioNombre(user.getVnombres()+" "+user.getVapellidos());
                        }
                    }
                    JSFUtils.getSession().setAttribute("buenaPracticaMB", bp);
                    if(popup) {
                        if(tempPopup) {
                            RequestContext.getCurrentInstance().execute("popupCenter('/gescon/pages/buenapractica/vistaPopup.xhtml','','1000','650');");
                        } else {
                            RequestContext.getCurrentInstance().execute("popupCenter('/gescon/pages/buenapractica/vistaConsulta.xhtml','','1000','650');");
                        }
                        this.setTempPopup("true");
                    } else {
                        if(tempPopup) {
                            FacesContext.getCurrentInstance().getExternalContext().redirect("/gescon/pages/buenapractica/vistaPopup.xhtml");
                            this.setTempPopup("true");
                        } else {
                            FacesContext.getCurrentInstance().getExternalContext().redirect("/gescon/pages/buenapractica/vistaConsulta.xhtml");
                            this.setTempPopup("false");
                        }
                    }
                    break;
                }
                case 6: { //Oportunidad de Mejora
                    OportunidadMB om = new OportunidadMB();
                    ConocimientoService conocimientoService = (ConocimientoService) ServiceFinder.findBean("ConocimientoService");
                    om.setSelectedOportunidad(conocimientoService.getConocimientoById(BigDecimal.valueOf(id)));
                    CategoriaService categoriaService = (CategoriaService) ServiceFinder.findBean("CategoriaService");
                    om.setSelectedCategoria(categoriaService.getCategoriaById(om.getSelectedOportunidad().getNcategoriaid()));
                    om.setContenidoHtml(GcmFileUtils.readStringFromFileServer(om.getSelectedOportunidad().getVruta(), "html.txt"));
                    SeccionService seccionService = (SeccionService) ServiceFinder.findBean("SeccionService");
                    om.setListaSeccion(seccionService.getSeccionesByConocimiento(om.getSelectedOportunidad().getNconocimientoid()));
                    if (CollectionUtils.isNotEmpty(om.getListaSeccion())) {
                        for (Seccion seccion : om.getListaSeccion()) {
                            seccion.setDetalleHtml(GcmFileUtils.readStringFromFileServer(seccion.getVruta(), "html.txt"));
                        }
                    }
                    HashMap map = new HashMap();
                    map.put("nconocimientoid", om.getSelectedOportunidad().getNconocimientoid().toString());
                    map.put("flag", true);
                    map.put("ntipoconocimientoid", Constante.BASELEGAL.toString());
                    om.setListaTargetVinculosBL(conocimientoService.getConcimientosVinculados(map));
                    map.put("ntipoconocimientoid", Constante.PREGUNTAS.toString());
                    om.setListaTargetVinculosPR(conocimientoService.getConcimientosVinculados(map));
                    map.put("ntipoconocimientoid", Constante.BUENAPRACTICA.toString());
                    om.setListaTargetVinculosBP(conocimientoService.getConcimientosVinculados(map));
                    map.put("ntipoconocimientoid", Constante.CONTENIDO.toString());
                    om.setListaTargetVinculosCT(conocimientoService.getConcimientosVinculados(map));
                    map.put("ntipoconocimientoid", Constante.OPORTUNIDADMEJORA.toString());
                    om.setListaTargetVinculosOM(conocimientoService.getConcimientosVinculados(map));
                    map.put("ntipoconocimientoid", Constante.WIKI.toString());
                    om.setListaTargetVinculosWK(conocimientoService.getConcimientosVinculados(map));
                    DiscusionService discusionService = (DiscusionService) ServiceFinder.findBean("DiscusionService");
                    List listaDiscusion = discusionService.getDiscusionesByConocimiento(om.getSelectedOportunidad().getNconocimientoid());
                    if(CollectionUtils.isNotEmpty(listaDiscusion)) {
                        Discusion discusion = (Discusion)listaDiscusion.get(0);
                        om.setSelectedDiscusion(discusion);
                        DiscusionSeccionService discusionSeccionService = (DiscusionSeccionService) ServiceFinder.findBean("DiscusionSeccionService");
                        om.setListaDiscusionSeccion(discusionSeccionService.getSeccionesByDiscusion(discusion.getNdiscusionid()));
                        UserService userService = (UserService) ServiceFinder.findBean("UserService");
                        if (CollectionUtils.isNotEmpty(om.getListaDiscusionSeccion())) {
                            for (DiscusionSeccion discusionSeccion : om.getListaDiscusionSeccion()) {
                                discusionSeccion.setDiscusionHtml(GcmFileUtils.readStringFromFileServer(discusionSeccion.getVruta(), "html.txt"));
                                User user = userService.getUserByLogin(discusionSeccion.getVusuariocreacion());
                                discusionSeccion.setUsuarioNombre(user.getVnombres()+" "+user.getVapellidos());
                            }
                        }
                    }
                    HistorialService historialService = (HistorialService) ServiceFinder.findBean("HistorialService");
                    om.setListaHistorial(historialService.getHistorialesByConocimiento(om.getSelectedOportunidad().getNconocimientoid()));
                    if(CollectionUtils.isNotEmpty(om.getListaHistorial())) {
                        UserService userService = (UserService) ServiceFinder.findBean("UserService");
                        for (Historial historial : om.getListaHistorial()) {
                            User user = userService.getUserByLogin(historial.getVusuariocreacion());
                            historial.setVnombreusuario(user.getVnombres()+" "+user.getVapellidos());
                        }
                    }
                    CalificacionService calificacionService = (CalificacionService) ServiceFinder.findBean("CalificacionService");
                    om.setListaCalificacion(calificacionService.getCalificacionesByConocimiento(om.getSelectedOportunidad().getNconocimientoid()));
                    if(CollectionUtils.isNotEmpty(om.getListaCalificacion())) {
                        UserService userService = (UserService) ServiceFinder.findBean("UserService");
                        for (Calificacion calificacion : om.getListaCalificacion()) {
                            User user = userService.getUserByLogin(calificacion.getVusuariocreacion());
                            calificacion.setUsuarioNombre(user.getVnombres()+" "+user.getVapellidos());
                        }
                    }
                    JSFUtils.getSession().setAttribute("oportunidadMB", om);
                    if(popup) {
                        if(tempPopup) {
                            RequestContext.getCurrentInstance().execute("popupCenter('/gescon/pages/oportunidad/vistaPopup.xhtml','','1000','650');");
                        } else {
                            RequestContext.getCurrentInstance().execute("popupCenter('/gescon/pages/oportunidad/vistaConsulta.xhtml','','1000','650');");
                        }
                        this.setTempPopup("true");
                    } else {
                        if(tempPopup) {
                            FacesContext.getCurrentInstance().getExternalContext().redirect("/gescon/pages/oportunidad/vistaPopup.xhtml");
                            this.setTempPopup("true");
                        } else {
                            FacesContext.getCurrentInstance().getExternalContext().redirect("/gescon/pages/oportunidad/vistaConsulta.xhtml");
                            this.setTempPopup("false");
                        }
                    }
                    break;
                }
            }
            RequestContext.getCurrentInstance().execute("PF('alertDialog').hide();");
        } catch(Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
    }
    
    public String back() {
        try {
            JSFUtils.getSession().removeAttribute("administracionMB");
            JSFUtils.getSession().removeAttribute("baseLegalMB");
            JSFUtils.getSession().removeAttribute("buenaPracticaMB");
            JSFUtils.getSession().removeAttribute("categoriaMB");
            JSFUtils.getSession().removeAttribute("contenidoMB");
            JSFUtils.getSession().removeAttribute("listaSessionMB");
            JSFUtils.getSession().removeAttribute("oportunidadMB");
            JSFUtils.getSession().removeAttribute("wikiMB");            
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
        return "/pages/consulta?faces-redirect=true";
    }
    
    public void reporte() {
        HashMap parametrosReporte = new HashMap();
        HashMap parametrosJasper = new HashMap();
        File archivo;
        List<HashMap<String, Object>> listaDatosReporte;
        
        HashMap filter = new HashMap();
        try {
            filter.put("fCategoria", this.getCategoriesFilter());
            filter.put("fFromDate", this.getFechaInicio());
            filter.put("fToDate", this.getFechaFin());
            filter.put("fType", this.getTypesFilter());
            if(StringUtils.isNotBlank(this.getSearchText())) {
                HashMap map = Indexador.search(this.getSearchText());
                filter.put("fCodesBL", (String) map.get("codesBL"));
                filter.put("fCodesPR", (String) map.get("codesPR"));
                filter.put("fCodesC", (String) map.get("codesC"));
            } else {
                filter.remove("fCodesBL");
                filter.remove("fCodesPR");
                filter.remove("fCodesC");
            }
            filter.put("order", this.getOrdenpor());
            ConsultaService service = (ConsultaService) ServiceFinder.findBean("ConsultaService");
            listaDatosReporte = service.listarReporte(filter);

            
            archivo = new File(JSFUtils.getServletContext().getRealPath("/pages/reportes/reporteConsulta.jasper"));
            String rutaImagen = JSFUtils.getServletContext().getRealPath("/resources/images/logo-minef.jpg");
            parametrosJasper.put("P_IMAGEN", rutaImagen);
            //archivo = new File(JSFUtils.getServletContext().getRealPath("/reportes/reportePAP.jrxml"));
            List<HashMap<String, Object>> listaDatos = listaDatosReporte;
            JRDataSource fuenteDatos = new JRBeanCollectionDataSource(listaDatos);
            JasperReport reporteJasper = (JasperReport) JRLoader.loadObjectFromFile(archivo.getPath());
            //JasperReport reporteJasper = JasperCompileManager.compileReport(archivo.getPath());
            JasperPrint documentoPdf = JasperFillManager.fillReport(reporteJasper, parametrosJasper, fuenteDatos);
            HttpServletResponse response = JSFUtils.getResponse();
            if (documentoPdf != null) {
                response.setContentType("application/pdf");
                response.setHeader("Content-Disposition", "attachment;filename=" + 
                        "ReporteConsulta" + 
                        ".pdf");
                ServletOutputStream ouputStream = response.getOutputStream();
                JRPdfExporter pdfExporter = new JRPdfExporter();
                pdfExporter.setParameter(JRExporterParameter.JASPER_PRINT, documentoPdf);
                pdfExporter.setParameter(JRExporterParameter.OUTPUT_STREAM, ouputStream);
                pdfExporter.exportReport();
                FacesContext.getCurrentInstance().responseComplete();
            }
        } catch (JRException e) {
            e.getMessage();
            e.printStackTrace();
        } catch (IOException e) {
            e.getMessage();
            e.printStackTrace();
        }
    }
}
