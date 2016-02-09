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
public class ArchivoHist implements Serializable {
    
    private BigDecimal narchivohistid;
    private BigDecimal nhistorialid;
    private BigDecimal nbaselegalid;
    private String vnombre;
    private String vruta;
    private BigDecimal nversion;
    private String vusuariocreacion;
    private Date dfechacreacion;
    private String vusuariomodificacion;
    private Date dfechamodificacion;
    
    public ArchivoHist(){
        
    }

    public BigDecimal getNarchivohistid() {
        return narchivohistid;
    }

    public void setNarchivohistid(BigDecimal narchivohistid) {
        this.narchivohistid = narchivohistid;
    }

    public BigDecimal getNhistorialid() {
        return nhistorialid;
    }

    public void setNhistorialid(BigDecimal nhistorialid) {
        this.nhistorialid = nhistorialid;
    }

    public BigDecimal getNbaselegalid() {
        return nbaselegalid;
    }

    public void setNbaselegalid(BigDecimal nbaselegalid) {
        this.nbaselegalid = nbaselegalid;
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

    public BigDecimal getNversion() {
        return nversion;
    }

    public void setNversion(BigDecimal nversion) {
        this.nversion = nversion;
    }
}
