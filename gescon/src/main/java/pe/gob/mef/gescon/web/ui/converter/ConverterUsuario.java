/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.gob.mef.gescon.web.ui.converter;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;
import javax.servlet.http.HttpSession;
import pe.gob.mef.gescon.util.JSFUtils;
import pe.gob.mef.gescon.web.bean.User;
import pe.gob.mef.gescon.web.ui.UserMB;

/**
 *
 * @author JJacobo
 */
@FacesConverter("converterUsuario")
public class ConverterUsuario implements Converter{

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
        if(value != null && value.trim().length() > 0) {
            try {
                HttpSession session = JSFUtils.getSession();
                UserMB userMB = session.getAttribute("userMB") != null ? (UserMB) session.getAttribute("userMB") : new UserMB();
                return userMB.getListaUser().get(Integer.parseInt(value));
            } catch(NumberFormatException e) {
                throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Conversion Error", "Not a valid theme."));
            }
        } else {
            return null;
        }
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) {
        if(o != null) {
            return ((User) o).getNusuarioid().toString();
        } else {
            return null;
        }
    }
    
}
