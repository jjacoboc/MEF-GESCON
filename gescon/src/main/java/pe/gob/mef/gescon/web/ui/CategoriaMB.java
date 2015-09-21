/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.gob.mef.gescon.web.ui;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.primefaces.context.RequestContext;
import org.primefaces.event.NodeSelectEvent;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;
import pe.gob.mef.gescon.common.Constante;
import pe.gob.mef.gescon.service.CategoriaService;
import pe.gob.mef.gescon.util.ServiceFinder;
import pe.gob.mef.gescon.web.bean.Categoria;

/**
 *
 * @author JJacobo
 */
@ManagedBean
@ViewScoped
public class CategoriaMB implements Serializable{

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
            createTree(service.getCategoria());
        } catch(Exception e) {
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
        Iterator<FacesMessage> iter = FacesContext.getCurrentInstance().getMessages();
        if (iter.hasNext() == true) {
            iter.remove();
            FacesContext.getCurrentInstance().renderResponse();
        }
    }
    
    public void onNodeSelect(NodeSelectEvent event) {
        try {
            if (event != null) {
                this.setSelectedCategoria((Categoria) event.getTreeNode().getData());
            }
        } catch(Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
    }
    
    public void toSave(ActionEvent event) {
        try{
            this.cleanAttributes();
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
    }
    
    public void save(ActionEvent event) {
        try {
            if (event != null) {
                Categoria categoria = new Categoria();
                categoria.setVnombre(StringUtils.capitalize(this.getNombre().trim()));
                categoria.setVdescripcion(StringUtils.capitalize(this.getDescripcion().trim()));
                categoria.setNestado(BigDecimal.ONE);
                categoria.setNflagbl(this.isFlagbl() ? BigDecimal.ONE : BigDecimal.ZERO);
                categoria.setNflagbp(this.isFlagbp() ? BigDecimal.ONE : BigDecimal.ZERO);
                categoria.setNflagct(this.isFlagct() ? BigDecimal.ONE : BigDecimal.ZERO);
                categoria.setNflagom(this.isFlagom() ? BigDecimal.ONE : BigDecimal.ZERO);
                categoria.setNflagpr(this.isFlagpr() ? BigDecimal.ONE : BigDecimal.ZERO);
                categoria.setNflagwiki(this.isFlagwiki()? BigDecimal.ONE : BigDecimal.ZERO);
                if(this.getSelectedNode() != null) {
                    Categoria parent = (Categoria) this.getSelectedNode().getData();
                    categoria.setNcategoriasup(parent.getNcategoriaid());
                    categoria.setNnivel(BigDecimal.valueOf(parent.getNnivel().intValue() + 1));
                } else {
                    categoria.setNcategoriasup(null);
                    categoria.setNnivel(BigDecimal.ONE);
                }
                categoria.setDfechacreacion(new Date());
                CategoriaService service = (CategoriaService) ServiceFinder.findBean("CategoriaService");
                categoria.setNcategoriaid(service.getNextPK());
                service.saveOrUpdate(categoria);
//                this.getTree().clearParent();
//                createTree(service.getCategoria());
                RequestContext.getCurrentInstance().execute("PF('newDialog').hide();");
            }
        } catch(Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
    }
    
    public void toUpdate(ActionEvent event) {
        try{
            this.cleanAttributes();
            this.setSelectedCategoria((Categoria)this.getSelectedNode().getData());
            this.setSelectedCategoriaPadre((Categoria)this.getSelectedNode().getParent().getData());
            this.setFlagbl(this.getSelectedCategoria().getNflagbl().equals(BigDecimal.ONE));
            this.setFlagbp(this.getSelectedCategoria().getNflagbp().equals(BigDecimal.ONE));
            this.setFlagct(this.getSelectedCategoria().getNflagct().equals(BigDecimal.ONE));
            this.setFlagom(this.getSelectedCategoria().getNflagom().equals(BigDecimal.ONE));
            this.setFlagpr(this.getSelectedCategoria().getNflagpr().equals(BigDecimal.ONE));
            this.setFlagwiki(this.getSelectedCategoria().getNflagwiki().equals(BigDecimal.ONE));
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
    }
    
    public void update(ActionEvent event) {
        try {
            if (event != null) {
                this.getSelectedCategoria().setVnombre(StringUtils.capitalize(this.getSelectedCategoria().getVnombre().trim()));
                this.getSelectedCategoria().setVdescripcion(StringUtils.capitalize(this.getSelectedCategoria().getVdescripcion().trim()));
                this.getSelectedCategoria().setNflagbl(this.isFlagbl() ? BigDecimal.ONE : BigDecimal.ZERO);
                this.getSelectedCategoria().setNflagbp(this.isFlagbp() ? BigDecimal.ONE : BigDecimal.ZERO);
                this.getSelectedCategoria().setNflagct(this.isFlagct() ? BigDecimal.ONE : BigDecimal.ZERO);
                this.getSelectedCategoria().setNflagom(this.isFlagom() ? BigDecimal.ONE : BigDecimal.ZERO);
                this.getSelectedCategoria().setNflagpr(this.isFlagpr() ? BigDecimal.ONE : BigDecimal.ZERO);
                this.getSelectedCategoria().setNflagwiki(this.isFlagwiki()? BigDecimal.ONE : BigDecimal.ZERO);
                this.getSelectedCategoria().setDfechamod(new Date());
                CategoriaService service = (CategoriaService) ServiceFinder.findBean("CategoriaService");
                service.saveOrUpdate(this.getSelectedCategoria());
//                this.getTree().clearParent();
//                createTree(service.getCategoria());
                RequestContext.getCurrentInstance().execute("PF('editDialog').hide();");
            }
        } catch(Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
    }
    
    public void activar(ActionEvent event) {
        try {
            if (event != null) {
                if (this.getSelectedNode()!= null) {
                    Categoria categoria = (Categoria)this.getSelectedNode().getData();
                    CategoriaService service = (CategoriaService) ServiceFinder.findBean("CategoriaService");
                    categoria.setNestado(BigDecimal.ONE);
                    categoria.setDfechamod(new Date());
//                    this.getSelectedMaestro().setVusumod(user.getUsuario());
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
                    Categoria categoria = (Categoria)this.getSelectedNode().getData();
                    CategoriaService service = (CategoriaService) ServiceFinder.findBean("CategoriaService");
                    categoria.setNestado(BigDecimal.ZERO);
                    categoria.setDfechamod(new Date());
//                    this.getSelectedMaestro().setVusumod(user.getUsuario());
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
}
