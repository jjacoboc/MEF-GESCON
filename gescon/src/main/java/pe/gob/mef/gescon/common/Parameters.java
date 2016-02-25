/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.gob.mef.gescon.common;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author Jonatan Jacobo
 */
public class Parameters implements Serializable {

    /**
     * Devuelve la ubicacion del archivo messages.properties
     *
     * @return
     */
    public static String getMessages() {
        return "pe.gob.mef.gescon.messages";
    }

    /**
     * Devuelve la ubicacion del archivo jdbc.properties
     *
     * @return
     */
    public static String getJDBC() {
        return "pe.gob.mef.gescon.jdbc";
    }

    /**
     * Devuelve la ubicacion del archivo mail.properties
     *
     * @return
     */
    public static String getMail() {
        return "pe.gob.mef.gescon.mail";
    }

    /**
     * Devuelve la ubicacion del archivo parameters.properties
     *
     * @return
     */
    public static String getParameters() {
        return "pe.gob.mef.gescon.parameters";
    }
    
    public static List<GeneralBean> getListaAnoSec() {
        List lista = new ArrayList();
        try {
            int year = Calendar.getInstance().get(Calendar.YEAR);
            for(int i=1;i<=8;i++){
                lista.add(new GeneralBean(Integer.toString(year),Integer.toString(year),StringUtils.EMPTY,StringUtils.EMPTY,StringUtils.EMPTY,StringUtils.EMPTY));
                year--;
            }
        } catch (Exception e) {
            e.getMessage();
        }
        return lista;
    }
    
    /**
     * Devuelve la lista SI/NO. 1 = SI, 0 = NO
     *
     * @return
     */
    public static List<GeneralBean> getListaSiNo() {
        List lista = null;
        GeneralBean bean;
        try {
            lista = new ArrayList();
            
            bean = new GeneralBean();
            bean.setCodigo(BigDecimal.ONE.toString());
            bean.setDescripcion("SI");
            lista.add(bean);
            
            bean = new GeneralBean();
            bean.setCodigo(BigDecimal.ZERO.toString());
            bean.setDescripcion("NO");
            lista.add(bean);
        } catch (Exception e) {
            e.getMessage();
        }
        return lista;
    }
    
    /**
     * Devuelve la lista ACTIVO/INACTIVO.
     *
     * @return
     */
    public static List<GeneralBean> getListaEstado() {
        List lista = null;
        GeneralBean bean;
        try {
            lista = new ArrayList();
            
            bean = new GeneralBean();
            bean.setCodigo(Constante.ESTADO_ACTIVO);
            bean.setDescripcion("ACTIVO");
            lista.add(bean);
            
            bean = new GeneralBean();
            bean.setCodigo(Constante.ESTADO_INACTIVO);
            bean.setDescripcion("INACTIVO");
            lista.add(bean);
        } catch (Exception e) {
            e.getMessage();
        }
        return lista;
    }
    
    /**
     * Devuelve la lista de Rangos de Base Legal.
     *
     * @return
     */
    public static List<GeneralBean> getListaRangoBaseLegal() {
        List lista = null;
        GeneralBean bean;
        try {
            lista = new ArrayList();
            
            bean = new GeneralBean();
            bean.setCodigo("1");
            bean.setDescripcion("RANGO 01");
            lista.add(bean);
            
            bean = new GeneralBean();
            bean.setCodigo("2");
            bean.setDescripcion("RANGO 02");
            lista.add(bean);
            
            bean = new GeneralBean();
            bean.setCodigo("3");
            bean.setDescripcion("RANGO 03");
            lista.add(bean);
        } catch (Exception e) {
            e.getMessage();
        }
        return lista;
    }
    
    /**
     * Devuelve la lista de temas.
     *
     * @return
     */
    public static List<GeneralBean> getListaThemes() {
        List lista = null;
        try {
            lista = new ArrayList();
            //lista.add(new GeneralBean(codigo,descripcion,descripcionCorta,tipo,orden,imagen));
            lista.add(new GeneralBean("afterdark","Afterdark",StringUtils.EMPTY,StringUtils.EMPTY,StringUtils.EMPTY,"afterdark.png"));
            lista.add(new GeneralBean("afternoon","Afternoon",StringUtils.EMPTY,StringUtils.EMPTY,StringUtils.EMPTY,"afternoon.png"));
            lista.add(new GeneralBean("afterwork","Afterwork",StringUtils.EMPTY,StringUtils.EMPTY,StringUtils.EMPTY,"afterwork.png"));
            lista.add(new GeneralBean("aristo","Aristo",StringUtils.EMPTY,StringUtils.EMPTY,StringUtils.EMPTY,"aristo.png"));
            lista.add(new GeneralBean("black-tie","Black-Tie",StringUtils.EMPTY,StringUtils.EMPTY,StringUtils.EMPTY,"black-tie.png"));
            lista.add(new GeneralBean("blitzer","Blitzer",StringUtils.EMPTY,StringUtils.EMPTY,StringUtils.EMPTY,"blitzer.png"));
            lista.add(new GeneralBean("bluesky","Bluesky",StringUtils.EMPTY,StringUtils.EMPTY,StringUtils.EMPTY,"bluesky.png"));
            lista.add(new GeneralBean("bootstrap","Bootstrap",StringUtils.EMPTY,StringUtils.EMPTY,StringUtils.EMPTY,"bootstrap.png"));
            lista.add(new GeneralBean("casablanca","Casablanca",StringUtils.EMPTY,StringUtils.EMPTY,StringUtils.EMPTY,"casablanca.png"));
            lista.add(new GeneralBean("cupertino","Cupertino",StringUtils.EMPTY,StringUtils.EMPTY,StringUtils.EMPTY,"cupertino.png"));
            lista.add(new GeneralBean("cruze","Cruze",StringUtils.EMPTY,StringUtils.EMPTY,StringUtils.EMPTY,"cruze.png"));
            lista.add(new GeneralBean("dark-hive","Dark-Hive",StringUtils.EMPTY,StringUtils.EMPTY,StringUtils.EMPTY,"dark-hive.png"));
            lista.add(new GeneralBean("delta","Delta",StringUtils.EMPTY,StringUtils.EMPTY,StringUtils.EMPTY,"delta.png"));
            lista.add(new GeneralBean("dot-luv","Dot-Luv",StringUtils.EMPTY,StringUtils.EMPTY,StringUtils.EMPTY,"dot-luv.png"));
            lista.add(new GeneralBean("eggplant","Eggplant",StringUtils.EMPTY,StringUtils.EMPTY,StringUtils.EMPTY,"eggplant.png"));
            lista.add(new GeneralBean("excite-bike","Excite-Bike",StringUtils.EMPTY,StringUtils.EMPTY,StringUtils.EMPTY,"excite-bike.png"));
            lista.add(new GeneralBean("flick","Flick",StringUtils.EMPTY,StringUtils.EMPTY,StringUtils.EMPTY,"flick.png"));
            lista.add(new GeneralBean("glass-x","Glass-X",StringUtils.EMPTY,StringUtils.EMPTY,StringUtils.EMPTY,"glass-x.png"));
            lista.add(new GeneralBean("home","Home",StringUtils.EMPTY,StringUtils.EMPTY,StringUtils.EMPTY,"home.png"));
            lista.add(new GeneralBean("hot-sneaks","Hot-Sneaks",StringUtils.EMPTY,StringUtils.EMPTY,StringUtils.EMPTY,"hot-sneaks.png"));
            lista.add(new GeneralBean("humanity","Humanity",StringUtils.EMPTY,StringUtils.EMPTY,StringUtils.EMPTY,"humanity.png"));
            lista.add(new GeneralBean("le-frog","Le-Frog",StringUtils.EMPTY,StringUtils.EMPTY,StringUtils.EMPTY,"le-frog.png"));
            lista.add(new GeneralBean("midnight","Midnight",StringUtils.EMPTY,StringUtils.EMPTY,StringUtils.EMPTY,"midnight.png"));
            lista.add(new GeneralBean("mint-choc","Mint-Choc",StringUtils.EMPTY,StringUtils.EMPTY,StringUtils.EMPTY,"mint-choc.png"));
            lista.add(new GeneralBean("overcast","Overcast",StringUtils.EMPTY,StringUtils.EMPTY,StringUtils.EMPTY,"overcast.png"));
            lista.add(new GeneralBean("pepper-grinder","Pepper-Grinder",StringUtils.EMPTY,StringUtils.EMPTY,StringUtils.EMPTY,"pepper-grinder.png"));
            lista.add(new GeneralBean("redmond","Redmond",StringUtils.EMPTY,StringUtils.EMPTY,StringUtils.EMPTY,"redmond.png"));
            lista.add(new GeneralBean("rocket","Rocket",StringUtils.EMPTY,StringUtils.EMPTY,StringUtils.EMPTY,"rocket.png"));
            lista.add(new GeneralBean("sam","Sam",StringUtils.EMPTY,StringUtils.EMPTY,StringUtils.EMPTY,"sam.png"));
            lista.add(new GeneralBean("smoothness","Smoothness",StringUtils.EMPTY,StringUtils.EMPTY,StringUtils.EMPTY,"smoothness.png"));
            lista.add(new GeneralBean("south-street","South-Street",StringUtils.EMPTY,StringUtils.EMPTY,StringUtils.EMPTY,"south-street.png"));
            lista.add(new GeneralBean("start","Start",StringUtils.EMPTY,StringUtils.EMPTY,StringUtils.EMPTY,"start.png"));
            lista.add(new GeneralBean("sunny","Sunny",StringUtils.EMPTY,StringUtils.EMPTY,StringUtils.EMPTY,"sunny.png"));
            lista.add(new GeneralBean("swanky-purse","Swanky-Purse",StringUtils.EMPTY,StringUtils.EMPTY,StringUtils.EMPTY,"swanky-purse.png"));
            lista.add(new GeneralBean("trontastic","Trontastic",StringUtils.EMPTY,StringUtils.EMPTY,StringUtils.EMPTY,"trontastic.png"));
            lista.add(new GeneralBean("ui-darkness","UI-Darkness",StringUtils.EMPTY,StringUtils.EMPTY,StringUtils.EMPTY,"ui-darkness.png"));
            lista.add(new GeneralBean("ui-lightness","UI-Lightness",StringUtils.EMPTY,StringUtils.EMPTY,StringUtils.EMPTY,"ui-lightness.png"));
            lista.add(new GeneralBean("vader","Vader",StringUtils.EMPTY,StringUtils.EMPTY,StringUtils.EMPTY,"vader.png"));
        } catch (Exception e) {
            e.getMessage();
        }
        return lista;
    }
}
