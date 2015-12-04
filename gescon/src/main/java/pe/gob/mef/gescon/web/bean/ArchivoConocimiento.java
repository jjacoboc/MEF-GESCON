/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.gob.mef.gescon.web.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import org.primefaces.model.StreamedContent;
import pe.gob.mef.gescon.hibernate.domain.Tbaselegal;
import pe.gob.mef.gescon.hibernate.domain.Tconocimiento;

/**
 *
 * @author JJacobo
 */
public class ArchivoConocimiento implements Serializable {

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
    private StreamedContent content;

    public ArchivoConocimiento() {

    }

    public BigDecimal getNarchivoid() {
        return narchivoid;
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
        return vnombre;
    }

    public void setVnombre(String vnombre) {
        this.vnombre = vnombre;
    }

    public String getVruta() {
        return vruta;
    }

    public void setVruta(String vruta) {
        this.vruta = vruta;
    }

    public BigDecimal getNversion() {
        return nversion;
    }

    public void setNversion(BigDecimal nversion) {
        this.nversion = nversion;
    }

    public String getVusuariocreacion() {
        return vusuariocreacion;
    }

    public void setVusuariocreacion(String vusuariocreacion) {
        this.vusuariocreacion = vusuariocreacion;
    }

    public Date getDfechacreacion() {
        return dfechacreacion;
    }

    public void setDfechacreacion(Date dfechacreacion) {
        this.dfechacreacion = dfechacreacion;
    }

    public String getVusuariomodificacion() {
        return vusuariomodificacion;
    }

    public void setVusuariomodificacion(String vusuariomodificacion) {
        this.vusuariomodificacion = vusuariomodificacion;
    }

    public Date getDfechamodificacion() {
        return dfechamodificacion;
    }

    public void setDfechamodificacion(Date dfechamodificacion) {
        this.dfechamodificacion = dfechamodificacion;
    }

    /**
     * @return the content
     */
    public StreamedContent getContent() {
        return content;
    }

    /**
     * @param content the content to set
     */
    public void setContent(StreamedContent content) {
        this.content = content;
    }
}
