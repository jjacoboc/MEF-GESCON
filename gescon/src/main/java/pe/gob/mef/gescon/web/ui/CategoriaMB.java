/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.gob.mef.gescon.web.ui;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.Serializable;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.sql.Blob;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.imageio.ImageIO;
import javax.sql.rowset.serial.SerialBlob;
import jcifs.smb.NtlmPasswordAuthentication;
import jcifs.smb.SmbFile;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.NodeSelectEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.TreeNode;
import org.primefaces.model.UploadedFile;
import pe.gob.mef.gescon.common.Constante;
import pe.gob.mef.gescon.common.Parameters;
import pe.gob.mef.gescon.service.CategoriaService;
import pe.gob.mef.gescon.util.JSFUtils;
import pe.gob.mef.gescon.util.ServiceFinder;
import pe.gob.mef.gescon.web.bean.Categoria;
import pe.gob.mef.gescon.web.bean.User;

/**
 *
 * @author JJacobo
 */
@ManagedBean
@SessionScoped
public class CategoriaMB implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final Log log = LogFactory.getLog(CategoriaMB.class);
    private String nombre;
    private String descripcion;
    private BigDecimal activo;
    private boolean flagbl;
    private boolean flagpr;
    private boolean flagwiki;
    private boolean flagct;
    private boolean flagbp;
    private boolean flagom;
    private UploadedFile uploadFile;
    private StreamedContent content;
    private TreeNode tree;
    private TreeNode selectedNode;
    private TreeNode selectedNodeParent;
    private Categoria selectedCategoria;
    private Categoria selectedCategoriaPadre;

    /**
     * Creates a new instance of CategoriaMB
     */
    public CategoriaMB() {
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
     * @return the activo
     */
    public BigDecimal getActivo() {
        return activo;
    }

    /**
     * @param activo the activo to set
     */
    public void setActivo(BigDecimal activo) {
        this.activo = activo;
    }

    /**
     * @return the flagbl
     */
    public boolean isFlagbl() {
        return flagbl;
    }

    /**
     * @param flagbl the flagbl to set
     */
    public void setFlagbl(boolean flagbl) {
        this.flagbl = flagbl;
    }

    /**
     * @return the flagpr
     */
    public boolean isFlagpr() {
        return flagpr;
    }

    /**
     * @param flagpr the flagpr to set
     */
    public void setFlagpr(boolean flagpr) {
        this.flagpr = flagpr;
    }

    /**
     * @return the flagwiki
     */
    public boolean isFlagwiki() {
        return flagwiki;
    }

    /**
     * @param flagwiki the flagwiki to set
     */
    public void setFlagwiki(boolean flagwiki) {
        this.flagwiki = flagwiki;
    }

    /**
     * @return the flagct
     */
    public boolean isFlagct() {
        return flagct;
    }

    /**
     * @param flagct the flagct to set
     */
    public void setFlagct(boolean flagct) {
        this.flagct = flagct;
    }

    /**
     * @return the flagbp
     */
    public boolean isFlagbp() {
        return flagbp;
    }

    /**
     * @param flagbp the flagbp to set
     */
    public void setFlagbp(boolean flagbp) {
        this.flagbp = flagbp;
    }

    /**
     * @return the flagom
     */
    public boolean isFlagom() {
        return flagom;
    }

    /**
     * @param flagom the flagom to set
     */
    public void setFlagom(boolean flagom) {
        this.flagom = flagom;
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
     * @return the selectedNode
     */
    public TreeNode getSelectedNode() {
        return selectedNode;
    }

    /**
     * @param selectedNode the selectedNode to set
     */
    public void setSelectedNode(TreeNode selectedNode) {
        this.selectedNode = selectedNode;
    }

    /**
     * @return the selectedNodeParent
     */
    public TreeNode getSelectedNodeParent() {
        return selectedNodeParent;
    }

    /**
     * @param selectedNodeParent the selectedNodeParent to set
     */
    public void setSelectedNodeParent(TreeNode selectedNodeParent) {
        this.selectedNodeParent = selectedNodeParent;
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
     * @return the selectedCategoriaPadre
     */
    public Categoria getSelectedCategoriaPadre() {
        return selectedCategoriaPadre;
    }

    /**
     * @param selectedCategoriaPadre the selectedCategoriaPadre to set
     */
    public void setSelectedCategoriaPadre(Categoria selectedCategoriaPadre) {
        this.selectedCategoriaPadre = selectedCategoriaPadre;
    }

    @PostConstruct
    public void init() {
        try {
            CategoriaService service = (CategoriaService) ServiceFinder.findBean("CategoriaService");
            createTree(service.getCategorias());
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

    public void cleanAttributes() {
        this.setDescripcion(StringUtils.EMPTY);
        this.setNombre(StringUtils.EMPTY);
        this.setActivo(BigDecimal.ONE);
        this.setFlagbl(false);
        this.setFlagbp(false);
        this.setFlagct(false);
        this.setFlagom(false);
        this.setFlagpr(false);
        this.setFlagwiki(false);
        this.setUploadFile(null);
        this.setContent(null);
        Iterator<FacesMessage> iter = FacesContext.getCurrentInstance().getMessages();
        if (iter.hasNext() == true) {
            iter.remove();
            FacesContext.getCurrentInstance().renderResponse();
        }
    }

    public void handleUploadFile(FileUploadEvent event) {
        try {
            if (event != null) {
                UploadedFile f = event.getFile();
                if (f != null) {
                    if (!f.getContentType().equalsIgnoreCase("image/png")
                            && !f.getContentType().equalsIgnoreCase("image/jpg")
                            && !f.getContentType().equalsIgnoreCase("image/jpeg")
                            && !f.getContentType().equalsIgnoreCase("image/gif")) {
                        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, Constante.SEVERETY_ALERTA, "La imagen debe ser de tipo png, jpg o gif.");
                        FacesContext.getCurrentInstance().addMessage(null, message);
                        return;
                    }
                    if (f.getSize() > 102400) {
                        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, Constante.SEVERETY_ALERTA, "El tamaño de la imagen no debe ser mayor a 100KB.");
                        FacesContext.getCurrentInstance().addMessage(null, message);
                        return;
                    }
                    BufferedImage bimg = ImageIO.read(f.getInputstream());
                    int width = bimg.getWidth();
                    int height = bimg.getHeight();
                    if (width > 128 || height > 128) {
                        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, Constante.SEVERETY_ALERTA, "La dimensión de la imagen no debe ser mayor a 128x128.");
                        FacesContext.getCurrentInstance().addMessage(null, message);
                        return;
                    }
                    this.setUploadFile(f);
                    this.content = new DefaultStreamedContent(f.getInputstream(), f.getContentType(), f.getFileName());
                }
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
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
            this.readImage(this.getSelectedCategoria());
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
    }

    public void save(ActionEvent event) {
        try {
            if (event != null) {
                if(StringUtils.isBlank(this.getNombre())) {
                    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR.", "Ingrese el nombre de la categoría a registrar.");
                    FacesContext.getCurrentInstance().addMessage(null, message);
                    return;
                }
                if(StringUtils.isBlank(this.getDescripcion())) {
                    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR.", "Ingrese la descripción de la categoría a registrar.");
                    FacesContext.getCurrentInstance().addMessage(null, message);
                    return;
                }
                if(this.getContent() == null) {
                    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR.", "Seleccione la imagen de la categoría a registrar.");
                    FacesContext.getCurrentInstance().addMessage(null, message);
                    return;
                }
                LoginMB loginMB = (LoginMB) JSFUtils.getSessionAttribute("loginMB");
                User user = loginMB.getUser();
                Categoria categoria = new Categoria();
                categoria.setVnombre(StringUtils.capitalize(this.getNombre().trim()));
                categoria.setVdescripcion(StringUtils.capitalize(this.getDescripcion().trim()));
                categoria.setNestado(BigDecimal.ONE);
                categoria.setNflagbl(this.isFlagbl() ? BigDecimal.ONE : BigDecimal.ZERO);
                categoria.setNflagbp(this.isFlagbp() ? BigDecimal.ONE : BigDecimal.ZERO);
                categoria.setNflagct(this.isFlagct() ? BigDecimal.ONE : BigDecimal.ZERO);
                categoria.setNflagom(this.isFlagom() ? BigDecimal.ONE : BigDecimal.ZERO);
                categoria.setNflagpr(this.isFlagpr() ? BigDecimal.ONE : BigDecimal.ZERO);
                categoria.setNflagwiki(this.isFlagwiki() ? BigDecimal.ONE : BigDecimal.ZERO);
                if (this.getSelectedNode() != null) {
                    Categoria parent = (Categoria) this.getSelectedNode().getData();
                    categoria.setNcategoriasup(parent.getNcategoriaid());
                    categoria.setNnivel(BigDecimal.valueOf(parent.getNnivel().intValue() + 1));
                } else {
                    categoria.setNcategoriasup(null);
                    categoria.setNnivel(BigDecimal.ONE);
                }
                if (this.getContent()!= null) {
                    Blob blob = new SerialBlob(this.getUploadFile().getContents());
                    categoria.setBimagen(blob);
                    categoria.setVimagennombre(this.getContent().getName());
                    categoria.setVimagentype(this.getContent().getContentType());
                }
                categoria.setDfechacreacion(new Date());
                categoria.setVusuariocreacion(user.getVlogin());
                CategoriaService service = (CategoriaService) ServiceFinder.findBean("CategoriaService");
                categoria.setNcategoriaid(service.getNextPK());
                service.saveOrUpdate(categoria);
                this.writeImage(categoria);
                this.setTree(null);
                createTree(service.getCategorias());
                RequestContext.getCurrentInstance().execute("PF('newDialog').hide();");
            }
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
    }

    public void toUpdate(ActionEvent event) {
        try {
            this.cleanAttributes();
            this.setSelectedCategoria((Categoria) this.getSelectedNode().getData());
            this.setSelectedCategoriaPadre((Categoria) this.getSelectedNode().getParent().getData());
            this.setFlagbl(this.getSelectedCategoria().getNflagbl().equals(BigDecimal.ONE));
            this.setFlagbp(this.getSelectedCategoria().getNflagbp().equals(BigDecimal.ONE));
            this.setFlagct(this.getSelectedCategoria().getNflagct().equals(BigDecimal.ONE));
            this.setFlagom(this.getSelectedCategoria().getNflagom().equals(BigDecimal.ONE));
            this.setFlagpr(this.getSelectedCategoria().getNflagpr().equals(BigDecimal.ONE));
            this.setFlagwiki(this.getSelectedCategoria().getNflagwiki().equals(BigDecimal.ONE));
            this.readImage(this.getSelectedCategoria());
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
    }

    public void update(ActionEvent event) {
        try {
            if (event != null) {
                if(StringUtils.isBlank(this.getSelectedCategoria().getVnombre())) {
                    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR.", "Ingrese el nombre de la categoría a registrar.");
                    FacesContext.getCurrentInstance().addMessage(null, message);
                    return;
                }
                if(StringUtils.isBlank(this.getSelectedCategoria().getVdescripcion())) {
                    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR.", "Ingrese la descripción de la categoría a registrar.");
                    FacesContext.getCurrentInstance().addMessage(null, message);
                    return;
                }
                if(this.getContent() == null) {
                    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR.", "Seleccione la imagen de la categoría a registrar.");
                    FacesContext.getCurrentInstance().addMessage(null, message);
                    return;
                }
                LoginMB loginMB = (LoginMB) JSFUtils.getSessionAttribute("loginMB");
                User user = loginMB.getUser();
                this.getSelectedCategoria().setVnombre(StringUtils.capitalize(this.getSelectedCategoria().getVnombre().trim()));
                this.getSelectedCategoria().setVdescripcion(StringUtils.capitalize(this.getSelectedCategoria().getVdescripcion().trim()));
                this.getSelectedCategoria().setNflagbl(this.isFlagbl() ? BigDecimal.ONE : BigDecimal.ZERO);
                this.getSelectedCategoria().setNflagbp(this.isFlagbp() ? BigDecimal.ONE : BigDecimal.ZERO);
                this.getSelectedCategoria().setNflagct(this.isFlagct() ? BigDecimal.ONE : BigDecimal.ZERO);
                this.getSelectedCategoria().setNflagom(this.isFlagom() ? BigDecimal.ONE : BigDecimal.ZERO);
                this.getSelectedCategoria().setNflagpr(this.isFlagpr() ? BigDecimal.ONE : BigDecimal.ZERO);
                this.getSelectedCategoria().setNflagwiki(this.isFlagwiki() ? BigDecimal.ONE : BigDecimal.ZERO);
                this.getSelectedCategoria().setDfechamodificacion(new Date());
                this.getSelectedCategoria().setVusuariomodificacion(user.getVlogin());
                if (this.getContent() != null) {
                    Blob blob = new SerialBlob(this.getUploadFile().getContents());
                    this.getSelectedCategoria().setBimagen(blob);
                    this.getSelectedCategoria().setVimagennombre(this.getContent().getName());
                    this.getSelectedCategoria().setVimagentype(this.getContent().getContentType());
                }
                CategoriaService service = (CategoriaService) ServiceFinder.findBean("CategoriaService");
                service.saveOrUpdate(this.getSelectedCategoria());
                this.writeImage(this.getSelectedCategoria());
                this.setTree(null);
                createTree(service.getCategorias());
                RequestContext.getCurrentInstance().execute("PF('editDialog').hide();");
            }
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
    }

    public void activar(ActionEvent event) {
        try {
            if (event != null) {
                if (this.getSelectedNode() != null) {
                    LoginMB loginMB = (LoginMB) JSFUtils.getSessionAttribute("loginMB");
                    User user = loginMB.getUser();
                    Categoria categoria = (Categoria) this.getSelectedNode().getData();
                    CategoriaService service = (CategoriaService) ServiceFinder.findBean("CategoriaService");
                    categoria.setNestado(BigDecimal.ONE);
                    categoria.setDfechamodificacion(new Date());
                    categoria.setVusuariomodificacion(user.getVlogin());
                    service.saveOrUpdate(categoria);
                } else {
                    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, Constante.SEVERETY_ALERTA, "Debe seleccionar la categoría a activar.");
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
                if (this.getSelectedNode() != null) {
                    LoginMB loginMB = (LoginMB) JSFUtils.getSessionAttribute("loginMB");
                    User user = loginMB.getUser();
                    Categoria categoria = (Categoria) this.getSelectedNode().getData();
                    CategoriaService service = (CategoriaService) ServiceFinder.findBean("CategoriaService");
                    categoria.setNestado(BigDecimal.ZERO);
                    categoria.setDfechamodificacion(new Date());
                    categoria.setVusuariomodificacion(user.getVlogin());
                    service.saveOrUpdate(categoria);
                } else {
                    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, Constante.SEVERETY_ALERTA, "Debe seleccionar la categoría a desactivar.");
                    FacesContext.getCurrentInstance().addMessage(null, message);
                }
            }
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
    }
    
    public void writeImage(Categoria categoria) {
        String filepath;
        String user;
        String password;
        String url;
        try {
            if (this.getUploadFile() != null) {
                ResourceBundle bundle = ResourceBundle.getBundle(Parameters.getParameters());
                filepath = bundle.getString("filepath");
                user = bundle.getString("user");
                password = bundle.getString("password");
                url = filepath + "category/" + categoria.getNcategoriaid().toString() + "/";

                NtlmPasswordAuthentication auth = new NtlmPasswordAuthentication(null, user, password);
                SmbFile dir = new SmbFile(url, auth);
                if(!dir.exists()) {
                    dir.mkdirs();
                }
                
                File file = new File(dir.getUncPath(), this.getUploadFile().getFileName());
                FileOutputStream fileOutStream = new FileOutputStream(file);
                fileOutStream.write(this.getUploadFile().getContents());
                fileOutStream.flush();
                fileOutStream.close();
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
    }
    
    public void readImage(Categoria categoria) {
        String filepath;
        String user;
        String password;
        String url;
        try {
            if (categoria != null) {
                ResourceBundle bundle = ResourceBundle.getBundle(Parameters.getParameters());
                filepath = bundle.getString("filepath");
                user = bundle.getString("user");
                password = bundle.getString("password");
                url = filepath + "category/" + categoria.getNcategoriaid().toString() + "/";

                NtlmPasswordAuthentication auth = new NtlmPasswordAuthentication(null, user, password);
                SmbFile dir = new SmbFile(url, auth);
                
                File file = new File(dir.getUncPath(), categoria.getVimagennombre());
                FileInputStream fis = new FileInputStream(file);
                this.content = new DefaultStreamedContent(fis, categoria.getVimagentype(), categoria.getVimagennombre());
            }
        } catch (MalformedURLException e) {
            log.error(e.getMessage());
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
    }
}
