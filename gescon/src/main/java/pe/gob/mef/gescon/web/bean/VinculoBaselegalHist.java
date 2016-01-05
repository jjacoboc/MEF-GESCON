/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.gob.mef.gescon.web.bean;

import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author JJacobo
 */
public class VinculoBaselegalHist {
    
    private BigDecimal nvinculohistid;
    private BigDecimal nhistorialid;
    private BigDecimal nbaselegalid;
    private BigDecimal nbaselegalvinculadaid;
    private BigDecimal ntipovinculo;
    private String vusuariocreacion;
    private Date dfechacreacion;
    private String vusuariomodificacion;
    private Date dfechamodificacion;
    
    public VinculoBaselegalHist(){
        
    }

    public BigDecimal getNvinculohistid() {
        return nvinculohistid;
    }

    public void setNvinculohistid(BigDecimal nvinculohistid) {
        this.nvinculohistid = nvinculohistid;
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

    public BigDecimal getNbaselegalvinculadaid() {
        return nbaselegalvinculadaid;
    }

    public void setNbaselegalvinculadaid(BigDecimal nbaselegalvinculadaid) {
        this.nbaselegalvinculadaid = nbaselegalvinculadaid;
    }

    public BigDecimal getNtipovinculo() {
        return ntipovinculo;
    }

    public void setNtipovinculo(BigDecimal ntipovinculo) {
        this.ntipovinculo = ntipovinculo;
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
}
