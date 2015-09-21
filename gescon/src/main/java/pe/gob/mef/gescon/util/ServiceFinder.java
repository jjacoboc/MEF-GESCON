package pe.gob.mef.gescon.util;

import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

public class ServiceFinder {

    /**
     * Devuelve el objeto bean del contexto correspondiente al nombre solicitado.
     * @param beanName Nombre del bean.
     * @return o Objeto bean encontrado.
     */
    public static Object findBean(String beanName) {
        FacesContext context = FacesContext.getCurrentInstance();
        ServletContext servletContext = (ServletContext) context.getExternalContext().getContext();
        ApplicationContext appContext = WebApplicationContextUtils.getWebApplicationContext(servletContext);
        Object o = appContext.getBean(beanName);
        return o;
    }
} 
