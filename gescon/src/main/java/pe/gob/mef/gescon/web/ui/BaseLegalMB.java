/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template uploadFile, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.gob.mef.gescon.web.ui;

import java.io.File;
import java.io.FileOutputStream;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Blob;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.sql.rowset.serial.SerialBlob;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.NodeSelectEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.TreeNode;
import org.primefaces.model.UploadedFile;
import org.springframework.util.CollectionUtils;
import pe.gob.mef.gescon.common.Constante;
import pe.gob.mef.gescon.hibernate.domain.Mtrango;
import pe.gob.mef.gescon.hibernate.domain.TarchivoId;
import pe.gob.mef.gescon.hibernate.domain.Tbaselegal;
import pe.gob.mef.gescon.service.ArchivoService;
import pe.gob.mef.gescon.service.BaseLegalService;
import pe.gob.mef.gescon.service.CategoriaService;
import pe.gob.mef.gescon.util.JSFUtils;
import pe.gob.mef.gescon.util.ServiceFinder;
import pe.gob.mef.gescon.web.bean.Archivo;
import pe.gob.mef.gescon.web.bean.BaseLegal;
import pe.gob.mef.gescon.web.bean.Categoria;

/**
 *
 * @author JJacobo
 */
@ManagedBean
@SessionScoped
public class BaseLegalMB implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final Log log = LogFactory.getLog(BaseLegalMB.class);
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
    
    @PostConstruct
    public void init() {
        try {
            BaseLegalService service = (BaseLegalService) ServiceFinder.findBean("BaseLegalService");
            this.setListaBaseLegal(service.getBaselegales());
        } catch(Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
    }
    
    public void cleanAttributes() {
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
        Iterator<FacesMessage> iter = FacesContext.getCurrentInstance().getMessages();
        if (iter.hasNext() == true) {
            iter.remove();
            FacesContext.getCurrentInstance().renderResponse();
        }
    }

    public void handleUploadFile(FileUploadEvent event) {
        String temppath = "D:\\gescon\\temp\\";
        try {
            if (event != null) {
                UploadedFile f = event.getFile();
                if (f != null) {
//                    String contentType = f.getContentType();
//                    if(Constante.FILE_CONTENT_TYPE_XLS.equals(contentType)
//                            || Constante.FILE_CONTENT_TYPE_XLSX.equals(contentType)) {
                    this.setUploadFile(f);
                    File direc = new File(temppath);
                    direc.mkdirs();
                    this.setFile(new File(temppath, f.getFileName()));
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
            Mtrango mtrango = new Mtrango();
            mtrango.setNrangoid(this.getRangoId());
            base.setMtrango(mtrango);
            base.setNgobnacional(this.getChkGobNacional() ? BigDecimal.ONE : BigDecimal.ZERO);
            base.setNgobregional(this.getChkGobRegional() ? BigDecimal.ONE : BigDecimal.ZERO);
            base.setNgoblocal(this.getChkGobLocal() ? BigDecimal.ONE : BigDecimal.ZERO);
            base.setNmancomunidades(this.getChkMancomunidades() ? BigDecimal.ONE : BigDecimal.ZERO);
            base.setVsumilla(this.getSumilla().trim());
            base.setDfechapublicacion(this.getFechaPublicacion());
            base.setVtema(this.getTema());
            base.setNactivo(BigDecimal.ONE);
            base.setDfechacreacion(new Date());
            BaseLegalService service = (BaseLegalService) ServiceFinder.findBean("BaseLegalService");
            base.setNbaselegalid(service.getNextPK());
            service.saveOrUpdate(base);
            Tbaselegal tbaselegal = new Tbaselegal();
            BeanUtils.copyProperties(tbaselegal, base);
            Archivo archivo = new Archivo();
            archivo.setNversion(BigDecimal.ONE);
            archivo.setTbaselegal(tbaselegal);
            archivo.setVnombre(this.getUploadFile().getFileName());
            archivo.setVruta(this.getFile().getPath());
            Blob blob = new SerialBlob(this.getUploadFile().getContents());
            archivo.setBbin(blob);
            archivo.setDfechacreacion(new Date());
            ArchivoService aservice = (ArchivoService) ServiceFinder.findBean("ArchivoService");
            TarchivoId archivoId = new TarchivoId();
            archivoId.setNbaselegalid(base.getNbaselegalid());
            archivoId.setNarchivoid(aservice.getNextPK());
            archivo.setId(archivoId);
            aservice.saveOrUpdate(archivo);
            saveFile(archivo);
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
    }

    public void saveFile(Archivo archivo) {
        String path = "D:\\gescon\\files\\bl\\";
        String temppath = "D:\\gescon\\temp\\";
        try {
            if (this.getUploadFile() != null) {
                String id = archivo.getId().getNbaselegalid().toString();
                String version = archivo.getNversion().toString();
                path = path + id + "\\" + version + "\\";
                File direc = new File(path);
                direc.mkdirs();
                this.setFile(new File(path, this.getUploadFile().getFileName()));
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
