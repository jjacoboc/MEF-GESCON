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
public class Discusion implements Serializable{
    
    private BigDecimal ndiscusionid;
    private BigDecimal nconocimientoid;
    private String vdiscusion;
    private String vusuariomodificacion;
    private String vusuariocreacion;
    private Date dfechacreacion;
    private Date dfechamodificacion;
    
    public Discusion() {
        
    }

    public BigDecimal getNdiscusionid() {
        return ndiscusionid;
    }

    public void setNdiscusionid(BigDecimal ndiscusionid) {
        this.ndiscusionid = ndiscusionid;
    }

    public BigDecimal getNconocimientoid() {
        return nconocimientoid;
    }

    public void setNconocimientoid(BigDecimal nconocimientoid) {
        this.nconocimientoid = nconocimientoid;
    }

    public String getVdiscusion() {
        return vdiscusion;
    }

    public void setVdiscusion(String vdiscusion) {
        this.vdiscusion = vdiscusion;
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

    public Date getDfechacreacion() {
        return dfechacreacion;
    }

    public void setDfechacreacion(Date dfechacreacion) {
        this.dfechacreacion = dfechacreacion;
    }

    public Date getDfechamodificacion() {
        return dfechamodificacion;
    }

    public void setDfechamodificacion(Date dfechamodificacion) {
        this.dfechamodificacion = dfechamodificacion;
    }
}
