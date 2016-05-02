/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.gob.mef.gescon.web.ui;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ApplicationScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseId;
import org.apache.commons.lang3.StringUtils;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import pe.gob.mef.gescon.util.JSFUtils;

/**
 *
 * @author JJacobo
 */
@ManagedBean
@ApplicationScoped
public class UsuarioImage {

    /**
     * Creates a new instance of UsuairoImage
     */
    public UsuarioImage() {
    }

    public StreamedContent getImage() throws IOException, Exception {
        FacesContext context = FacesContext.getCurrentInstance();
        FileInputStream fis = null;
        String url;
        String filename = null;
        String contentType = null;
        
        if (context.getCurrentPhaseId() == PhaseId.RENDER_RESPONSE) {
            // So, we're rendering the HTML. Return a stub StreamedContent so that it will generate right URL.
            return new DefaultStreamedContent();
        } else {
            System.out.println("External context: " + context.getExternalContext());
            // So, browser is requesting the image. Return a real StreamedContent with the image bytes.
            String ruta = JSFUtils.getRequestParameter("ruta");

            if (StringUtils.isNotBlank(ruta)) {
                int index = ruta.lastIndexOf("\\");
                url = ruta.substring(0, index + 1);
                filename = ruta.substring(index + 1);

                File file = new File(url, filename);
                
                if(file.exists()) {
                    fis = new FileInputStream(file);
                    contentType = "image/jpg";
                } else {
                    System.out.println("External context: " + context.getExternalContext());
                    file = new File(context.getExternalContext() + "/resources/images/blank-avatar.png");
                    fis = new FileInputStream(file);
                    contentType = "image/png";
                    filename = "blank-avatar.png";
                }
            }
        }
        return new DefaultStreamedContent(fis, contentType, filename);
    }
}
