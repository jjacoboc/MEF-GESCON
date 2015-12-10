/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pe.gob.mef.gescon.web.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author JJacobo
 */
public class Wiki implements Serializable {
    
    private BigDecimal nconocimientoid;
    private BigDecimal ncategoriaid;
    private BigDecimal nsituacionid;
    private BigDecimal ntipoconocimientoid;
    private String vtitulo;
    private BigDecimal ndestacado;
    private String vusuariocreacion;
    private Date dfechacreacion;
    private String vusuariomodificacion;
    private Date dfechamodificacion;
    private String detalleHtml;
    private String detallePlain;
    
    public Wiki(){
        
    }

    public BigDecimal getNconocimientoid() {
        return nconocimientoid;
    }

    public void setNconocimientoid(BigDecimal nconocimientoid) {
        this.nconocimientoid = nconocimientoid;
    }

    public BigDecimal getNcategoriaid() {
        return ncategoriaid;
    }

    public void setNcategoriaid(BigDecimal ncategoriaid) {
        this.ncategoriaid = ncategoriaid;
    }

    public BigDecimal getNsituacionid() {
        return nsituacionid;
    }

    public void setNsituacionid(BigDecimal nsituacionid) {
        this.nsituacionid = nsituacionid;
    }

    public BigDecimal getNtipoconocimientoid() {
        return ntipoconocimientoid;
    }

    public void setNtipoconocimientoid(BigDecimal ntipoconocimientoid) {
        this.ntipoconocimientoid = ntipoconocimientoid;
    }

    public String getVtitulo() {
        return vtitulo;
    }

    public void setVtitulo(String vtitulo) {
        this.vtitulo = vtitulo;
    }

    public BigDecimal getNdestacado() {
        return ndestacado;
    }

    public void setNdestacado(BigDecimal ndestacado) {
        this.ndestacado = ndestacado;
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

    public String getDetalleHtml() {
        return detalleHtml;
    }

    public void setDetalleHtml(String detalleHtml) {
        this.detalleHtml = detalleHtml;
    }

    public String getDetallePlain() {
        return detallePlain;
    }

    public void setDetallePlain(String detallePlain) {
        this.detallePlain = detallePlain;
    }
}
