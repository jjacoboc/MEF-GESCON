/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pe.gob.mef.gescon.web.ui;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;
import org.primefaces.event.NodeSelectEvent;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;
import pe.gob.mef.gescon.service.CategoriaService;
import pe.gob.mef.gescon.util.ServiceFinder;
import pe.gob.mef.gescon.web.bean.Categoria;
import pe.gob.mef.gescon.web.bean.Wiki;

/**
 *
 * @author JJacobo
 */
@ManagedBean
@SessionScoped
public class WikiMB implements Serializable{

    private List<Wiki> listaWiki;
    private Wiki selectedwiki;
    private TreeNode tree;
    private Categoria selectedCategoria;
    private String nombre;
    private String descripcion;
    
    /**
     * Creates a new instance of WikiMB
     */
    public WikiMB() {
        listaWiki = new ArrayList<Wiki>();
        Wiki bean = new Wiki();
        bean.setTitulo("El Combate de Angamos");
        bean.setEstado("Publicado");
        bean.setFecha("21/10/2015 13:15:25");
        bean.setNactivo(BigDecimal.ONE);
        listaWiki.add(bean);
        
        bean = new Wiki();
        bean.setTitulo("La Crisis Económica China");
        bean.setEstado("Publicado");
        bean.setFecha("20/10/2015 15:42:36");
        bean.setNactivo(BigDecimal.ONE);
        listaWiki.add(bean);
    }

    public List<Wiki> getListaWiki() {
        return listaWiki;
    }

    public void setListaWiki(List<Wiki> listaWiki) {
        this.listaWiki = listaWiki;
    }

    public Wiki getSelectedwiki() {
        return selectedwiki;
    }

    public void setSelectedwiki(Wiki selectedwiki) {
        this.selectedwiki = selectedwiki;
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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    @PostConstruct
    public void init(){
        try {
            
        } catch(Exception e) {
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
}
