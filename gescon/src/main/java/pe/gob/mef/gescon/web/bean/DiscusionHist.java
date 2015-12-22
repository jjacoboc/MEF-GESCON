/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.gob.mef.gescon.web.bean;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 *
 * @author JJacobo
 */
public class DiscusionHist implements Serializable {

    private BigDecimal ndiscusionhid;
    private BigDecimal nconocimientoid;
    private String vdescripcion;
    private BigDecimal nnumversion;
    private String vusuariomodificacion;
    private String vusuariocreacion;
    private Serializable dfechacreacion;
    private Serializable dfechamodificacion;
    
    public DiscusionHist() {
    }

    public BigDecimal getNdiscusionhid() {
        return ndiscusionhid;
    }

    public void setNdiscusionhid(BigDecimal ndiscusionhid) {
        this.ndiscusionhid = ndiscusionhid;
    }
    
    public BigDecimal getNconocimientoid() {
        return nconocimientoid;
    }

    public void setNconocimientoid(BigDecimal nconocimientoid) {
        this.nconocimientoid = nconocimientoid;
    }

    public String getVdescripcion() {
        return vdescripcion;
    }

    public void setVdescripcion(String vdescripcion) {
        this.vdescripcion = vdescripcion;
    }

    public BigDecimal getNnumversion() {
        return nnumversion;
    }

    public void setNnumversion(BigDecimal nnumversion) {
        this.nnumversion = nnumversion;
    }

    public String getVusuariomodificacion() {
        return vusuariomodificacion;
    }

    public void setVusuariomodificacion(String vusuariomodificacion) {
        this.vusuariomodificacion = vusuariomodificacion;
    }

    public String getVusuariocreacion() {
        return vusuariocreacion;
    }

    public void setVusuariocreacion(String vusuariocreacion) {
        this.vusuariocreacion = vusuariocreacion;
    }

    public Serializable getDfechacreacion() {
        return dfechacreacion;
    }

    public void setDfechacreacion(Serializable dfechacreacion) {
        this.dfechacreacion = dfechacreacion;
    }

    public Serializable getDfechamodificacion() {
        return dfechamodificacion;
    }

    public void setDfechamodificacion(Serializable dfechamodificacion) {
        this.dfechamodificacion = dfechamodificacion;
    }
}
