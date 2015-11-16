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
public class Seccion implements Serializable{
    
    private BigDecimal nseccionid;
    private BigDecimal nconocimientoid;
    private String vtitulo;
    private String vruta;
    private BigDecimal norden;
    private String vusuariocreacion;
    private Date dfechacreacion;
    private String vusuariomodificacion;
    private Date dfechamodificacion;
    private String detalleHtml;
    private String detallePlain;
    
    public void Seccion(){
        
    }

    public BigDecimal getNseccionid() {
        return nseccionid;
    }

    public void setNseccionid(BigDecimal nseccionid) {
        this.nseccionid = nseccionid;
    }

    public BigDecimal getNconocimientoid() {
        return nconocimientoid;
    }

    public void setNconocimientoid(BigDecimal nconocimientoid) {
        this.nconocimientoid = nconocimientoid;
    }

    public String getVtitulo() {
        return vtitulo;
    }

    public void setVtitulo(String vtitulo) {
        this.vtitulo = vtitulo;
    }

    public String getVruta() {
        return vruta;
    }

    public void setVruta(String vruta) {
        this.vruta = vruta;
    }

    public BigDecimal getNorden() {
        return norden;
    }

    public void setNorden(BigDecimal norden) {
        this.norden = norden;
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
