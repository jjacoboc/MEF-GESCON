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
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
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
    
}
