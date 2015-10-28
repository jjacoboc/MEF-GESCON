package pe.gob.mef.gescon.web.ui;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import org.primefaces.context.RequestContext;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

@ManagedBean
public class ContenidoMB {

    public void handleFileUpload(FileUploadEvent event) {
        FacesMessage message = new FacesMessage("Succesful", event.getFile().getFileName() + " is uploaded.");
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public void toCancel(ActionEvent event) {
        RequestContext.getCurrentInstance().execute("PF('tpoconDialog').hide();");
    }

    public void toCancelSecc(ActionEvent event) {
        RequestContext.getCurrentInstance().execute("PF('newseccDialog').hide();");
    }
}
