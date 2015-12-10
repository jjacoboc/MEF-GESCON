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
public class Asignacion implements Serializable{
    
    private BigDecimal nasignacionid;
    private BigDecimal ntipoconocimientoid;
    private BigDecimal nconocimientoid;
    private BigDecimal nusuarioid;
    private BigDecimal nestadoid;
    private String vusuariocreacion;
    private String vusuariomodificacion;
    private Date dfechacreacion;
    private Date dfechamodificacion;
    private Date dfechaasignacion;
    private Date dfechaatencion;
    private Date dfecharecepcion;
    
    public void Asignacion(){
        
    }

    /**
     * @return the nasignacionid
     */
    public BigDecimal getNasignacionid() {
        return nasignacionid;
    }

    /**
     * @param nasignacionid the nasignacionid to set
     */
    public void setNasignacionid(BigDecimal nasignacionid) {
        this.nasignacionid = nasignacionid;
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
     * @return the nusuarioid
     */
    public BigDecimal getNusuarioid() {
        return nusuarioid;
    }

    /**
     * @param nusuarioid the nusuarioid to set
     */
    public void setNusuarioid(BigDecimal nusuarioid) {
        this.nusuarioid = nusuarioid;
    }

    /**
     * @return the nestadoid
     */
    public BigDecimal getNestadoid() {
        return nestadoid;
    }

    /**
     * @param nestadoid the nestadoid to set
     */
    public void setNestadoid(BigDecimal nestadoid) {
        this.nestadoid = nestadoid;
    }

    /**
     * @return the vusuariocreacion
     */
    public String getVusuariocreacion() {
        return vusuariocreacion;
    }

    /**
     * @param vusuariocreacion the vusuariocreacion to set
     */
    public void setVusuariocreacion(String vusuariocreacion) {
        this.vusuariocreacion = vusuariocreacion;
    }

    /**
     * @return the vusuariomodificacion
     */
    public String getVusuariomodificacion() {
        return vusuariomodificacion;
    }

    /**
     * @param vusuariomodificacion the vusuariomodificacion to set
     */
    public void setVusuariomodificacion(String vusuariomodificacion) {
        this.vusuariomodificacion = vusuariomodificacion;
    }

    /**
     * @return the dfechacreacion
     */
    public Date getDfechacreacion() {
        return dfechacreacion;
    }

    /**
     * @param dfechacreacion the dfechacreacion to set
     */
    public void setDfechacreacion(Date dfechacreacion) {
        this.dfechacreacion = dfechacreacion;
    }

    /**
     * @return the dfechamodificacion
     */
    public Date getDfechamodificacion() {
        return dfechamodificacion;
    }

    /**
     * @param dfechamodificacion the dfechamodificacion to set
     */
    public void setDfechamodificacion(Date dfechamodificacion) {
        this.dfechamodificacion = dfechamodificacion;
    }

    /**
     * @return the dfechaasignacion
     */
    public Date getDfechaasignacion() {
        return dfechaasignacion;
    }

    /**
     * @param dfechaasignacion the dfechaasignacion to set
     */
    public void setDfechaasignacion(Date dfechaasignacion) {
        this.dfechaasignacion = dfechaasignacion;
    }

    /**
     * @return the dfechaatencion
     */
    public Date getDfechaatencion() {
        return dfechaatencion;
    }

    /**
     * @param dfechaatencion the dfechaatencion to set
     */
    public void setDfechaatencion(Date dfechaatencion) {
        this.dfechaatencion = dfechaatencion;
    }

    /**
     * @return the dfecharecepcion
     */
    public Date getDfecharecepcion() {
        return dfecharecepcion;
    }

    /**
     * @param dfecharecepcion the dfecharecepcion to set
     */
    public void setDfecharecepcion(Date dfecharecepcion) {
        this.dfecharecepcion = dfecharecepcion;
    }

    
   
}
