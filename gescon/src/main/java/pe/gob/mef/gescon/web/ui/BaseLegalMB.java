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
import java.util.Iterator;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.event.PhaseId;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
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
import pe.gob.mef.gescon.hibernate.domain.TarchivoId;
import pe.gob.mef.gescon.hibernate.domain.Tbaselegal;
import pe.gob.mef.gescon.hibernate.domain.TvinculoBaselegalId;
import pe.gob.mef.gescon.service.ArchivoService;
import pe.gob.mef.gescon.service.BaseLegalService;
import pe.gob.mef.gescon.service.CategoriaService;
import pe.gob.mef.gescon.service.VinculoBaseLegalService;
import pe.gob.mef.gescon.util.JSFUtils;
import pe.gob.mef.gescon.util.ServiceFinder;
import pe.gob.mef.gescon.web.bean.Archivo;
import pe.gob.mef.gescon.web.bean.BaseLegal;
import pe.gob.mef.gescon.web.bean.Categoria;
import pe.gob.mef.gescon.web.bean.VinculoBaselegal;

/**
 *
 * @author JJacobo
 */
@ManagedBean
@SessionScoped
public class BaseLegalMB implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final Log log = LogFactory.getLog(BaseLegalMB.class);
    private final String temppath = "D:\\gescon\\temp\\";
    private String path = "D:\\gescon\\files\\bl\\";
    private List<BaseLegal> listaBaseLegal;
    private BaseLegal selectedBaseLegal;
    private String nombre;
    private String numeroNorma;
    private BigDecimal rangoId;
    private Boolean chkGobNacional;
    private Boolean chkGobRegional;
    private Boolean chkGobLocal;
    private Boolean chkMancomunidades;
    private String sumilla;
    private Date fechaPublicacion;
    private String tema;
    private UploadedFile uploadFile;
    private StreamedContent content;
    private File file;
    private TreeNode tree;
    private Categoria selectedCategoria;
    private List<BaseLegal> listaSource;
    private List<BaseLegal> listaTarget;
    private DualListModel<BaseLegal> pickList;
    private List<String> listaTipoVinculo;
    private DataModel dataModel;
    

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
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
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

    /**
     * @return the fechaPublicacion
     */
    public Date getFechaPublicacion() {
        return fechaPublicacion;
    }

    /**
     * @param fechaPublicacion the fechaPublicacion to set
     */
    public void setFechaPublicacion(Date fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
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
     * @return the listaTipoVinculo
     */
    public List<String> getListaTipoVinculo() {
        return listaTipoVinculo;
    }

    /**
     * @param listaTipoVinculo the listaTipoVinculo to set
     */
    public void setListaTipoVinculo(List<String> listaTipoVinculo) {
        this.listaTipoVinculo = listaTipoVinculo;
    }

    /**
     * @return the dataModel
     */
    public DataModel getDataModel() {
        if (dataModel == null) {
            dataModel = new ListDataModel(this.getListaTipoVinculo());
        }
        return dataModel;
    }

    /**
     * @param dataModel the dataModel to set
     */
    public void setDataModel(DataModel dataModel) {
        this.dataModel = dataModel;
    }
    
    @PostConstruct
    public void init() {
        try {
            BaseLegalService service = (BaseLegalService) ServiceFinder.findBean("BaseLegalService");
            this.setListaBaseLegal(service.getBaselegales());
            ArchivoService aservice = (ArchivoService) ServiceFinder.findBean("ArchivoService");
            for(BaseLegal bl : this.getListaBaseLegal()) {
                bl.setArchivo(aservice.getLastArchivoByBaseLegal(bl));
            }
            this.setListaSource(new ArrayList<BaseLegal>());
            this.setListaTarget(new ArrayList<BaseLegal>());
            this.setPickList(new DualListModel<BaseLegal>(this.getListaSource(), this.getListaTarget()));
        } catch(Exception e) {
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
        this.setNombre(StringUtils.EMPTY);
        this.setNumeroNorma(StringUtils.EMPTY);
        this.setRangoId(BigDecimal.ZERO);
        this.setChkGobNacional(false);
        this.setChkGobRegional(false);
        this.setChkGobLocal(false);
        this.setChkMancomunidades(false);
        this.setSumilla(StringUtils.EMPTY);
        this.setFechaPublicacion(null);
        this.setTema(StringUtils.EMPTY);
        this.setListaSource(new ArrayList<BaseLegal>());
        this.setListaTarget(new ArrayList<BaseLegal>());
        this.setListaTipoVinculo(new ArrayList<String>());
        Iterator<FacesMessage> iter = FacesContext.getCurrentInstance().getMessages();
        if (iter.hasNext() == true) {
            iter.remove();
            FacesContext.getCurrentInstance().renderResponse();
        }
    }
    
    public void handleChangeValue(AjaxBehaviorEvent event) {
        try {
            if(event != null) {
                String id = (String)((SelectOneMenu) event.getSource()).getValue();
                String index = JSFUtils.getRequestParameter("index");
                this.getListaTipoVinculo().set(Integer.parseInt(index), id);
                this.getListaTarget().get(Integer.parseInt(index)).setNestadoid(BigDecimal.valueOf(Long.parseLong(id)));
            }
        } catch(Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
    }

    public void handleUploadFile(FileUploadEvent event) {
        try {
            if (event != null) {
                UploadedFile f = event.getFile();
                if (f != null) {
//                    String contentType = f.getContentType();
//                    if(Constante.FILE_CONTENT_TYPE_XLS.equals(contentType)
//                            || Constante.FILE_CONTENT_TYPE_XLSX.equals(contentType)) {
                    this.setUploadFile(f);
                    File direc = new File(this.temppath);
                    direc.mkdirs();
                    this.setFile(new File(this.temppath, f.getFileName()));
                    FileOutputStream fileOutStream = new FileOutputStream(this.getFile());
                    fileOutStream.write(f.getContents());
                    fileOutStream.flush();
                    fileOutStream.close();
                    this.content = new DefaultStreamedContent(f.getInputstream(), "application/pdf", f.getFileName());
//                    } else {
//                        this.setFile(null);
//                    }
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
        }
        else {
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
    
    public void toSave(ActionEvent event) {
        try {
            this.cleanAttributes();
        } catch(Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
    }

    public void save(ActionEvent event) {
        try {
            if (CollectionUtils.isEmpty(this.getListaBaseLegal())) {
                this.setListaBaseLegal(Collections.EMPTY_LIST);
            }
            BaseLegal base = new BaseLegal();
            base.setNcategoriaid(this.getSelectedCategoria().getNcategoriaid());
            base.setVnombre(this.getNombre().trim().toUpperCase());
            base.setVnumero(this.getNumeroNorma().trim().toUpperCase());
            base.setNrangoid(this.getRangoId());
            base.setNgobnacional(this.getChkGobNacional() ? BigDecimal.ONE : BigDecimal.ZERO);
            base.setNgobregional(this.getChkGobRegional() ? BigDecimal.ONE : BigDecimal.ZERO);
            base.setNgoblocal(this.getChkGobLocal() ? BigDecimal.ONE : BigDecimal.ZERO);
            base.setNmancomunidades(this.getChkMancomunidades() ? BigDecimal.ONE : BigDecimal.ZERO);
            base.setVsumilla(this.getSumilla().trim());
            base.setDfechapublicacion(this.getFechaPublicacion());
            base.setVtema(this.getTema());
            base.setNactivo(BigDecimal.ONE);
            base.setNestadoid(BigDecimal.valueOf(Long.valueOf(Constante.ESTADO_BASELEGAL_REGISTRADO)));
            base.setDfechacreacion(new Date());
            BaseLegalService service = (BaseLegalService) ServiceFinder.findBean("BaseLegalService");
            base.setNbaselegalid(service.getNextPK());
            service.saveOrUpdate(base);
            Tbaselegal tbaselegal = new Tbaselegal();
            BeanUtils.copyProperties(tbaselegal, base);
            
            ArchivoService aservice = (ArchivoService) ServiceFinder.findBean("ArchivoService");
            TarchivoId archivoId = new TarchivoId();
            archivoId.setNbaselegalid(base.getNbaselegalid());
            archivoId.setNarchivoid(aservice.getNextPK());
            
            Archivo archivo = new Archivo();
            archivo.setId(archivoId);
            archivo.setNversion(BigDecimal.ONE);
            archivo.setTbaselegal(tbaselegal);
            archivo.setVnombre(this.getUploadFile().getFileName());
            archivo.setVruta(path + base.getNbaselegalid().toString() + "\\" + archivo.getNversion().toString() + "\\" + archivo.getVnombre());
            archivo.setDfechacreacion(new Date());
            aservice.saveOrUpdate(archivo);            
            saveFile(archivo);
            
            for(BaseLegal v : this.getListaTarget()) {
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
//                vinculo.setVusuariocreacion(numeroNorma);
                vservice.saveOrUpdate(vinculo);
                
                BaseLegal bl = service.getBaselegalById(v.getNbaselegalid());
                bl.setNestadoid(v.getNestadoid());
                bl.setDfechamodificacion(new Date());
//                bl.setVusuariomodificacion(numeroNorma);
                service.saveOrUpdate(bl);
            }
            
            this.setListaBaseLegal(service.getBaselegales());
            for(BaseLegal bl : this.getListaBaseLegal()) {
                bl.setArchivo(aservice.getLastArchivoByBaseLegal(bl));
            }
            RequestContext.getCurrentInstance().execute("PF('newDialog').hide();");
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
    }

    public void saveFile(Archivo archivo) {
        try {
            if (this.getUploadFile() != null) {
                String id = archivo.getId().getNbaselegalid().toString();
                String version = archivo.getNversion().toString();
                String newPath = path + id + "\\" + version + "\\";
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
    
    public void toEdit(ActionEvent event) {
        try {
            this.cleanAttributes();
            int index = Integer.parseInt((String) JSFUtils.getRequestParameter("index"));
            this.setSelectedBaseLegal(this.getListaBaseLegal().get(index));
            this.setChkGobNacional(this.getSelectedBaseLegal().getNgobnacional().equals(BigDecimal.ONE));
            this.setChkGobRegional(this.getSelectedBaseLegal().getNgobregional().equals(BigDecimal.ONE));
            this.setChkGobLocal(this.getSelectedBaseLegal().getNgoblocal().equals(BigDecimal.ONE));
            this.setChkMancomunidades(this.getSelectedBaseLegal().getNmancomunidades().equals(BigDecimal.ONE));
            loadPickList(event);
        } catch(Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
    }
    
    public void edit(ActionEvent event) {
        try {
            if (CollectionUtils.isEmpty(this.getListaBaseLegal())) {
                this.setListaBaseLegal(Collections.EMPTY_LIST);
            }
            if(this.getSelectedCategoria() != null){
                this.getSelectedBaseLegal().setNcategoriaid(this.getSelectedCategoria().getNcategoriaid());
            }
            this.getSelectedBaseLegal().setVnombre(this.getNombre().trim().toUpperCase());
            this.getSelectedBaseLegal().setVnumero(this.getNumeroNorma().trim().toUpperCase());
            this.getSelectedBaseLegal().setNrangoid(this.getRangoId());
            this.getSelectedBaseLegal().setNgobnacional(this.getChkGobNacional() ? BigDecimal.ONE : BigDecimal.ZERO);
            this.getSelectedBaseLegal().setNgobregional(this.getChkGobRegional() ? BigDecimal.ONE : BigDecimal.ZERO);
            this.getSelectedBaseLegal().setNgoblocal(this.getChkGobLocal() ? BigDecimal.ONE : BigDecimal.ZERO);
            this.getSelectedBaseLegal().setNmancomunidades(this.getChkMancomunidades() ? BigDecimal.ONE : BigDecimal.ZERO);
            this.getSelectedBaseLegal().setVsumilla(this.getSumilla().trim());
            this.getSelectedBaseLegal().setDfechapublicacion(this.getFechaPublicacion());
            this.getSelectedBaseLegal().setVtema(this.getTema());
            this.getSelectedBaseLegal().setNactivo(BigDecimal.ONE);
            this.getSelectedBaseLegal().setNestadoid(BigDecimal.valueOf(Long.valueOf(Constante.ESTADO_BASELEGAL_REGISTRADO)));
            this.getSelectedBaseLegal().setDfechamodificacion(new Date());
            BaseLegalService service = (BaseLegalService) ServiceFinder.findBean("BaseLegalService");
            service.saveOrUpdate(this.getSelectedBaseLegal());
            Tbaselegal tbaselegal = new Tbaselegal();
            BeanUtils.copyProperties(tbaselegal, this.getSelectedBaseLegal());
            
            ArchivoService aservice = (ArchivoService) ServiceFinder.findBean("ArchivoService");
            if(this.getUploadFile() != null) {                
                TarchivoId archivoId = new TarchivoId();
                archivoId.setNbaselegalid(this.getSelectedBaseLegal().getNbaselegalid());
                archivoId.setNarchivoid(aservice.getNextPK());

                Archivo archivo = new Archivo();
                archivo.setId(archivoId);
                int version = this.getSelectedBaseLegal().getArchivo().getNversion().intValue();
                archivo.setNversion(BigDecimal.valueOf(version + 1));
                archivo.setTbaselegal(tbaselegal);
                archivo.setVnombre(this.getUploadFile().getFileName());
                archivo.setVruta(path + this.getSelectedBaseLegal().getNbaselegalid().toString() + "\\" + archivo.getNversion().toString() + "\\" + archivo.getVnombre());
                archivo.setDfechacreacion(new Date());
                aservice.saveOrUpdate(archivo);
                saveFile(archivo);
            }            
            
            VinculoBaseLegalService vservice = (VinculoBaseLegalService) ServiceFinder.findBean("VinculoBaseLegalService");
            vservice.deleteByBaseLegal(this.getSelectedBaseLegal());
            for(BaseLegal v : this.getListaTarget()) {
                TvinculoBaselegalId id = new TvinculoBaselegalId();
                id.setNbaselegalid(tbaselegal.getNbaselegalid());
                id.setNvinculoid(vservice.getNextPK());
                VinculoBaselegal vinculo = new VinculoBaselegal();
                vinculo.setId(id);
                vinculo.setTbaselegal(tbaselegal);
                vinculo.setNbaselegalvinculadaid(v.getNbaselegalid());
                vinculo.setNtipovinculo(v.getNestadoid());
                vinculo.setDfechacreacion(new Date());
//                vinculo.setVusuariocreacion(numeroNorma);
                vservice.saveOrUpdate(vinculo);
                
                BaseLegal bl = service.getBaselegalById(v.getNbaselegalid());
                bl.setNestadoid(v.getNestadoid());
                bl.setDfechamodificacion(new Date());
//                bl.setVusuariomodificacion(numeroNorma);
                service.saveOrUpdate(bl);
            }
            
            this.setListaBaseLegal(service.getBaselegales());
            for(BaseLegal bl : this.getListaBaseLegal()) {
                bl.setArchivo(aservice.getLastArchivoByBaseLegal(bl));
            }
            RequestContext.getCurrentInstance().execute("PF('newDialog').hide();");
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
    }
    
    public void post(ActionEvent event) {
        try {
            if (CollectionUtils.isEmpty(this.getListaBaseLegal())) {
                this.setListaBaseLegal(Collections.EMPTY_LIST);
            }
            if(this.getSelectedCategoria() != null){
                this.getSelectedBaseLegal().setNcategoriaid(this.getSelectedCategoria().getNcategoriaid());
            }
            this.getSelectedBaseLegal().setVnombre(this.getNombre().trim().toUpperCase());
            this.getSelectedBaseLegal().setVnumero(this.getNumeroNorma().trim().toUpperCase());
            this.getSelectedBaseLegal().setNrangoid(this.getRangoId());
            this.getSelectedBaseLegal().setNgobnacional(this.getChkGobNacional() ? BigDecimal.ONE : BigDecimal.ZERO);
            this.getSelectedBaseLegal().setNgobregional(this.getChkGobRegional() ? BigDecimal.ONE : BigDecimal.ZERO);
            this.getSelectedBaseLegal().setNgoblocal(this.getChkGobLocal() ? BigDecimal.ONE : BigDecimal.ZERO);
            this.getSelectedBaseLegal().setNmancomunidades(this.getChkMancomunidades() ? BigDecimal.ONE : BigDecimal.ZERO);
            this.getSelectedBaseLegal().setVsumilla(this.getSumilla().trim());
            this.getSelectedBaseLegal().setDfechapublicacion(this.getFechaPublicacion());
            this.getSelectedBaseLegal().setVtema(this.getTema());
            this.getSelectedBaseLegal().setNactivo(BigDecimal.ONE);
            this.getSelectedBaseLegal().setNestadoid(BigDecimal.valueOf(Long.valueOf(Constante.ESTADO_BASELEGAL_PUBLICADO)));
            this.getSelectedBaseLegal().setDfechamodificacion(new Date());
            BaseLegalService service = (BaseLegalService) ServiceFinder.findBean("BaseLegalService");
            service.saveOrUpdate(this.getSelectedBaseLegal());
            Tbaselegal tbaselegal = new Tbaselegal();
            BeanUtils.copyProperties(tbaselegal, this.getSelectedBaseLegal());
            
            ArchivoService aservice = (ArchivoService) ServiceFinder.findBean("ArchivoService");
            if(this.getUploadFile() != null) {                
                TarchivoId archivoId = new TarchivoId();
                archivoId.setNbaselegalid(this.getSelectedBaseLegal().getNbaselegalid());
                archivoId.setNarchivoid(aservice.getNextPK());

                Archivo archivo = new Archivo();
                archivo.setId(archivoId);
                int version = this.getSelectedBaseLegal().getArchivo().getNversion().intValue();
                archivo.setNversion(BigDecimal.valueOf(version + 1));
                archivo.setTbaselegal(tbaselegal);
                archivo.setVnombre(this.getUploadFile().getFileName());
                archivo.setVruta(path + this.getSelectedBaseLegal().getNbaselegalid().toString() + "\\" + archivo.getNversion().toString() + "\\" + archivo.getVnombre());
                archivo.setDfechacreacion(new Date());
                aservice.saveOrUpdate(archivo);
                saveFile(archivo);
            }            
            
            VinculoBaseLegalService vservice = (VinculoBaseLegalService) ServiceFinder.findBean("VinculoBaseLegalService");
            vservice.deleteByBaseLegal(this.getSelectedBaseLegal());
            for(BaseLegal v : this.getListaTarget()) {
                TvinculoBaselegalId id = new TvinculoBaselegalId();
                id.setNbaselegalid(tbaselegal.getNbaselegalid());
                id.setNvinculoid(vservice.getNextPK());
                VinculoBaselegal vinculo = new VinculoBaselegal();
                vinculo.setId(id);
                vinculo.setTbaselegal(tbaselegal);
                vinculo.setNbaselegalvinculadaid(v.getNbaselegalid());
                vinculo.setNtipovinculo(v.getNestadoid());
                vinculo.setDfechacreacion(new Date());
//                vinculo.setVusuariocreacion(numeroNorma);
                vservice.saveOrUpdate(vinculo);
                
                BaseLegal bl = service.getBaselegalById(v.getNbaselegalid());
                bl.setNestadoid(v.getNestadoid());
                bl.setDfechamodificacion(new Date());
//                bl.setVusuariomodificacion(numeroNorma);
                service.saveOrUpdate(bl);
            }
            
            this.setListaBaseLegal(service.getBaselegales());
            for(BaseLegal bl : this.getListaBaseLegal()) {
                bl.setArchivo(aservice.getLastArchivoByBaseLegal(bl));
            }
            RequestContext.getCurrentInstance().execute("PF('newDialog').hide();");
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
    }
    
    public void toView(ActionEvent event) {
        try {
            int index = Integer.parseInt((String) JSFUtils.getRequestParameter("index"));
            this.setSelectedBaseLegal(this.getListaBaseLegal().get(index));
            loadPickList(event);
        } catch(Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
    }
    
    public void loadPickList(ActionEvent event) {
        try {
            BaseLegalService service = (BaseLegalService) ServiceFinder.findBean("BaseLegalService");
            if(this.getSelectedBaseLegal() != null) {
                if(CollectionUtils.isEmpty(this.getListaSource())){
                    this.setListaSource(service.getTbaselegalesNotLinkedById(this.getSelectedBaseLegal().getNbaselegalid()));
                }
                if(CollectionUtils.isEmpty(this.getListaTarget())){
                    this.setListaTarget(service.getTbaselegalesLinkedById(this.getSelectedBaseLegal().getNbaselegalid()));
                }
            } else {
                if(CollectionUtils.isEmpty(this.getListaSource())){
                    this.setListaSource(service.getTbaselegalesNotLinkedById(null));
                }
                if(CollectionUtils.isEmpty(this.getListaTarget())){
                    this.setListaTarget(new ArrayList<BaseLegal>());
                }
            }
            this.setPickList(new DualListModel<BaseLegal>(this.getListaSource(), this.getListaTarget()));
        } catch(Exception e) {
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
                        if(this.getListaTarget() == null) this.setListaTarget(new ArrayList<BaseLegal>());
                        this.getListaTarget().add(this.getListaSource().get(index));
                        this.getListaTipoVinculo().add(BigDecimal.ZERO.toString());
                        this.getListaSource().remove(index);
                    }
                }
                if (event.isRemove()) {
                    Collections.sort(this.getListaTarget(), BaseLegal.Comparators.ID);
                    for (BaseLegal ele : (List<BaseLegal>) event.getItems()) {
                        index = Collections.binarySearch(this.getListaTarget(), ele, BaseLegal.Comparators.ID);
                        if(this.getListaSource() == null) this.setListaSource(new ArrayList<BaseLegal>());
                        this.getListaSource().add(this.getListaTarget().get(index));
                        this.getListaTarget().remove(index);
                        this.getListaTipoVinculo().remove(index);
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
                if (this.getSelectedBaseLegal()!= null) {
                    BaseLegalService service = (BaseLegalService) ServiceFinder.findBean("BaseLegalService");
                    this.getSelectedBaseLegal().setNactivo(BigDecimal.ONE);
                    this.getSelectedBaseLegal().setDfechamodificacion(new Date());
//                    this.getSelectedMaestro().setVusumod(user.getUsuario());
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
                    BaseLegalService service = (BaseLegalService) ServiceFinder.findBean("BaseLegalService");
                    this.getSelectedBaseLegal().setNactivo(BigDecimal.ZERO);
                    this.getSelectedBaseLegal().setDfechamodificacion(new Date());
//                    this.getSelectedMaestro().setVusumod(user.getUsuario());
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
}
