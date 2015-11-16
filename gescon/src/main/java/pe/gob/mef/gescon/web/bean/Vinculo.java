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
public class Vinculo implements Serializable{
    
    private BigDecimal nvinculoid;
    private BigDecimal nconocimientoid;
    private BigDecimal nconocimientovinc;
    private BigDecimal ntipoconocimientovinc;
    private String vusuariomodificacion;
    private Date dfechacreacion;
    private Date dfechamodificacion;
    private String vusuariocreacion;
    
    public Vinculo() {
        
    }

    public BigDecimal getNvinculoid() {
        return nvinculoid;
    }

    public void setNvinculoid(BigDecimal nvinculoid) {
        this.nvinculoid = nvinculoid;
    }

    public BigDecimal getNconocimientoid() {
        return nconocimientoid;
    }

    public void setNconocimientoid(BigDecimal nconocimientoid) {
        this.nconocimientoid = nconocimientoid;
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

    public String getVusuariomodificacion() {
        return vusuariomodificacion;
    }

    public void setVusuariomodificacion(String vusuariomodificacion) {
        this.vusuariomodificacion = vusuariomodificacion;
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

    public String getVusuariocreacion() {
        return vusuariocreacion;
    }

    public void setVusuariocreacion(String vusuariocreacion) {
        this.vusuariocreacion = vusuariocreacion;
    }
}
