package pe.gob.mef.gescon.hibernate.domain;
// Generated 06/11/2015 11:03:52 AM by Hibernate Tools 4.3.1

import java.math.BigDecimal;
import java.util.Date;

/**
 * Tarchivo generated by hbm2java
 */
public class TarchivoConocimiento implements java.io.Serializable {

    private BigDecimal narchivoid;
    private BigDecimal nconocimientoid;
    private BigDecimal ntipoconocimientoid;
    private String vnombre;
    private String vruta;
    private BigDecimal nversion;
    private String vusuariocreacion;
    private Date dfechacreacion;
    private String vusuariomodificacion;
    private Date dfechamodificacion;
    private BigDecimal ntipoarchivo;
    private String vcontenttype;

    public TarchivoConocimiento() {
    }

    public TarchivoConocimiento(BigDecimal narchivoid) {
        this.narchivoid = narchivoid;
    }

    public TarchivoConocimiento(BigDecimal narchivoid, BigDecimal nconocimientoid, BigDecimal ntipoconocimientoid, String vnombre, String vruta, BigDecimal nversion, String vusuariocreacion, Date dfechacreacion, String vusuariomodificacion, Date dfechamodificacion) {
        this.narchivoid = narchivoid;
        this.nconocimientoid = nconocimientoid;
        this.ntipoconocimientoid = ntipoconocimientoid;
        this.vnombre = vnombre;
        this.vruta = vruta;
        this.nversion = nversion;
        this.vusuariocreacion = vusuariocreacion;
        this.dfechacreacion = dfechacreacion;
        this.vusuariomodificacion = vusuariomodificacion;
        this.dfechamodificacion = dfechamodificacion;
    }

    public BigDecimal getNarchivoid() {
        return this.narchivoid;
    }

    public void setNarchivoid(BigDecimal narchivoid) {
        this.narchivoid = narchivoid;
    }

    /**
     * @return the nconocimientoid
     */
    public BigDecimal getNconocimientoid() {
        return nconocimientoid;
    }

    /**
     * @param nconocimientoid the nconocimientoid to set
     */
    public void setNconocimientoid(BigDecimal nconocimientoid) {
        this.nconocimientoid = nconocimientoid;
    }



    /**
     * @return the ntipoconocimientoid
     */
    public BigDecimal getNtipoconocimientoid() {
        return ntipoconocimientoid;
    }

    /**
     * @param ntipoconocimientoid the ntipoconocimientoid to set
     */
    public void setNtipoconocimientoid(BigDecimal ntipoconocimientoid) {
        this.ntipoconocimientoid = ntipoconocimientoid;
    }



    public String getVnombre() {
        return this.vnombre;
    }

    public void setVnombre(String vnombre) {
        this.vnombre = vnombre;
    }

    public String getVruta() {
        return this.vruta;
    }

    public void setVruta(String vruta) {
        this.vruta = vruta;
    }

    public BigDecimal getNversion() {
        return this.nversion;
    }

    public void setNversion(BigDecimal nversion) {
        this.nversion = nversion;
    }

    public String getVusuariocreacion() {
        return this.vusuariocreacion;
    }

    public void setVusuariocreacion(String vusuariocreacion) {
        this.vusuariocreacion = vusuariocreacion;
    }

    public Date getDfechacreacion() {
        return this.dfechacreacion;
    }

    public void setDfechacreacion(Date dfechacreacion) {
        this.dfechacreacion = dfechacreacion;
    }

    public String getVusuariomodificacion() {
        return this.vusuariomodificacion;
    }

    public void setVusuariomodificacion(String vusuariomodificacion) {
        this.vusuariomodificacion = vusuariomodificacion;
    }

    public Date getDfechamodificacion() {
        return this.dfechamodificacion;
    }

    public void setDfechamodificacion(Date dfechamodificacion) {
        this.dfechamodificacion = dfechamodificacion;
    }

    public BigDecimal getNtipoarchivo() {
        return ntipoarchivo;
    }

    public void setNtipoarchivo(BigDecimal ntipoarchivo) {
        this.ntipoarchivo = ntipoarchivo;
    }

    public String getVcontenttype() {
        return vcontenttype;
    }

    public void setVcontenttype(String vcontenttype) {
        this.vcontenttype = vcontenttype;
    }

}
