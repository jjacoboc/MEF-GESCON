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
public class VinculoImplementacion implements Serializable{
    
    private BigDecimal nimplvinculoid;
    private BigDecimal nimplementacionid;
    private String vusuariocreacion;
    private Date dfechacreacion;
    private String vusuariomodificacion;
    private Date dfechamodificacion;
    private BigDecimal nconocimientovinc;
    private BigDecimal ntipoconocimientovinc;
    
    public VinculoImplementacion() {
        
    }

    public BigDecimal getNimplvinculoid() {
        return nimplvinculoid;
    }

    public void setNimplvinculoid(BigDecimal nimplvinculoid) {
        this.nimplvinculoid = nimplvinculoid;
    }

    public BigDecimal getNimplementacionid() {
        return nimplementacionid;
    }

    public void setNimplementacionid(BigDecimal nimplementacionid) {
        this.nimplementacionid = nimplementacionid;
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

    public BigDecimal getNconocimientovinc() {
        return nconocimientovinc;
    }

    public void setNconocimientovinc(BigDecimal nconocimientovinc) {
        this.nconocimientovinc = nconocimientovinc;
    }

    public BigDecimal getNtipoconocimientovinc() {
        return ntipoconocimientovinc;
    }

    public void setNtipoconocimientovinc(BigDecimal ntipoconocimientovinc) {
        this.ntipoconocimientovinc = ntipoconocimientovinc;
    }
}
