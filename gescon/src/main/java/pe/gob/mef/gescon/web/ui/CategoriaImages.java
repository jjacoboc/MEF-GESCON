/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.gob.mef.gescon.web.ui;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ResourceBundle;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseId;
import jcifs.smb.NtlmPasswordAuthentication;
import jcifs.smb.SmbFile;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import pe.gob.mef.gescon.common.Parameters;
import pe.gob.mef.gescon.service.CategoriaService;
import pe.gob.mef.gescon.util.JSFUtils;
import pe.gob.mef.gescon.util.ServiceFinder;
import pe.gob.mef.gescon.web.bean.Categoria;

/**
 *
 * @author JJacobo
 */
@ManagedBean
@ApplicationScoped
public class CategoriaImages {

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
        } else {
            // So, browser is requesting the image. Return a real StreamedContent with the image bytes.
            String id = JSFUtils.getRequestParameter("id");
            CategoriaService catservice = (CategoriaService) ServiceFinder.findBean("CategoriaService");
            Categoria categoria = catservice.getCategoriaById(BigDecimal.valueOf(Long.parseLong(id)));

            ResourceBundle bundle = ResourceBundle.getBundle(Parameters.getParameters());
            String filepath = bundle.getString("filepath");
            String user = bundle.getString("user");
            String password = bundle.getString("password");
            String url = filepath + "category/" + id + "/";

            NtlmPasswordAuthentication auth = new NtlmPasswordAuthentication(null, user, password);
            SmbFile dir = new SmbFile(url, auth);

            File file = new File(dir.getUncPath(), categoria.getVimagennombre());
            FileInputStream fis = new FileInputStream(file);
            return new DefaultStreamedContent(fis, categoria.getVimagentype(), categoria.getVimagennombre());
        }
    }

}
