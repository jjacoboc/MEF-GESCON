/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.gob.mef.gescon.web.ui;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;
import pe.gob.mef.gescon.service.BaseLegalService;
import pe.gob.mef.gescon.service.CategoriaService;
import pe.gob.mef.gescon.util.JSFUtils;
import pe.gob.mef.gescon.util.ServiceFinder;
import pe.gob.mef.gescon.web.bean.BaseLegal;
import pe.gob.mef.gescon.web.bean.Categoria;

/**
 *
 * @author JJacobo
 */
@ManagedBean
@ViewScoped
public class ConsultaMB implements Serializable{

    private TreeNode tree;
    private List<Categoria> listaCategoriaFiltro;
    private Categoria selectedCategoriaFiltro;
    private List<Categoria> listaBreadCrumb;
    private List<BaseLegal> listaBaseLegal;
    
    
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
    
    @PostConstruct
    public void init() {
        try {
            CategoriaService catservice = (CategoriaService) ServiceFinder.findBean("CategoriaService");
            this.setListaCategoriaFiltro(catservice.getCategoria());
            createTree(this.getListaCategoriaFiltro());
            this.setListaBreadCrumb(new ArrayList<Categoria>());
            BaseLegalService blservice = (BaseLegalService) ServiceFinder.findBean("BaseLegalService");
            this.setListaBaseLegal(blservice.getBaselegales());
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
    
    public String getSubTreeByNode(TreeNode treeNode, Categoria categoria) {
        StringBuilder str = new StringBuilder();
        try {
            TreeNode node = this.getNodeByIdCategoria(treeNode, categoria.getNcategoriaid().toString());
            if(node != null) {
                List<TreeNode> lista = node.getChildren();
                if (lista != null && !lista.isEmpty()) {
                    for (TreeNode n : lista) {
                        Categoria c = (Categoria) n.getData();
                        str.append(c.getNcategoriaid().toString()).append(",");
                        str.append(getSubTreeByNode(n, categoria)).append(",");
                    }
                }
            }
        } catch(Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
        return str.toString();
    }
    
    public void onClickSubCategoriesFilter(ActionEvent event) {
        try {
            int index = Integer.parseInt((String) JSFUtils.getRequestParameter("rowIndex"));
            this.setSelectedCategoriaFiltro(this.getListaCategoriaFiltro().get(index));
            this.getListaBreadCrumb().add(this.getSelectedCategoriaFiltro());
            this.filtrar(event);
        } catch(Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
    }
    
    public void onClickBreadCrumb(ActionEvent event) {
        int pos = 0;
        try {
            String id = (String) JSFUtils.getRequestParameter("ncategoriaid");
            for(int i=0;i<this.getListaBreadCrumb().size();i++) {
                Categoria c = this.getListaBreadCrumb().get(i);
                if(c.getNcategoriaid().toString().equals(id)) {
                    pos = i;
                    break;
                }
            }
            this.setSelectedCategoriaFiltro(this.getListaBreadCrumb().get(pos));
            this.setListaBreadCrumb(this.getListaBreadCrumb().subList(0, pos + 1));
            this.filtrar(event);
        } catch(Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
    }
    
    public void filtrar(ActionEvent event) {
        try {
            String str = this.getSubTreeByNode(this.getTree(), this.getSelectedCategoriaFiltro());
            if(str.endsWith(",")) {
                str = str.substring(0, str.length()-1);
            }
        } catch(Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
    }
}
