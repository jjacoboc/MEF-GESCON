/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.gob.mef.gescon.web.ui;

import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseId;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import pe.gob.mef.gescon.service.CategoriaService;
import pe.gob.mef.gescon.util.JSFUtils;
import pe.gob.mef.gescon.util.ServiceFinder;
import pe.gob.mef.gescon.web.bean.Categoria;

/**
 *
 * @author JJacobo
 */
@ManagedBean
@SessionScoped
public class CategoriaImages implements Serializable{

    /**
     * Creates a new instance of CategoriaImages
     */
    public CategoriaImages() {
    }

    public StreamedContent getImage() throws IOException, Exception {
        FacesContext context = FacesContext.getCurrentInstance();

        if (context.getCurrentPhaseId() == PhaseId.RENDER_RESPONSE) {
            // So, we're rendering the HTML. Return a stub StreamedContent so that it will generate right URL.
            return new DefaultStreamedContent();
        }
        else {
            // So, browser is requesting the image. Return a real StreamedContent with the image bytes.
            String id = JSFUtils.getRequestParameter("id");
            CategoriaService service = (CategoriaService) ServiceFinder.findBean("CategoriaService");
            Categoria categoria = service.getCategoriaById(BigDecimal.valueOf(Long.valueOf(id)));
            return new DefaultStreamedContent(categoria.getBimagen().getBinaryStream());
        }
    }
    
}
