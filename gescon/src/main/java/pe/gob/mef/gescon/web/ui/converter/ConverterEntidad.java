/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.gob.mef.gescon.web.ui.converter;

import java.math.BigDecimal;
import java.util.List;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpSession;
import pe.gob.mef.gescon.util.JSFUtils;
import pe.gob.mef.gescon.web.ui.ListaSessionMB;


/**
 *
 * @author JJacobo
 */
@FacesConverter("converterEntidad")
public class ConverterEntidad implements Converter{

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
        try {
            return BigDecimal.valueOf(Long.parseLong(value));
        } catch (NumberFormatException exception) {
            throw new ConverterException(exception);
        }
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) {
        List<SelectItem> lista;
        String label = "";
        try {
            HttpSession session = JSFUtils.getSession();
            ListaSessionMB listasSession = session.getAttribute("listaSessionMB") != null ? (ListaSessionMB) session.getAttribute("listaSessionMB") : new ListaSessionMB();
            lista = listasSession.getListaEntidad();
            for (SelectItem item : lista) {
                if (o.toString().equals(item.getValue().toString())) {
                    label = item.getLabel();
                }
            }
            return label;
        } catch (Exception exception) {
            throw new ConverterException(exception);
        }
    }
    
}

