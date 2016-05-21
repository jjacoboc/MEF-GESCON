/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.gob.mef.gescon.web.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import pe.gob.mef.gescon.hibernate.domain.Tbaselegal;
import pe.gob.mef.gescon.hibernate.domain.TvinculoBaselegalId;

/**
 *
 * @author JJacobo
 */
public class VinculoBaselegal implements Serializable{
    
    private TvinculoBaselegalId id;
    private Tbaselegal tbaselegal;
    private BigDecimal nbaselegalvinculadaid;
    private BigDecimal ntipovinculo;
    private BigDecimal nestadoid;
    private String vusuariocreacion;
    private Date dfechacreacion;
    private String vusuariomodificacion;
    private Date dfechamodificacion;
    
    public VinculoBaselegal(){
        
    }

    /**
     * @return the id
     */
    public TvinculoBaselegalId getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(TvinculoBaselegalId id) {
        this.id = id;
    }

    /**
     * @return the tbaselegal
     */
    public Tbaselegal getTbaselegal() {
        return tbaselegal;
    }

    /**
     * @param tbaselegal the tbaselegal to set
     */
    public void setTbaselegal(Tbaselegal tbaselegal) {
        this.tbaselegal = tbaselegal;
    }

    /**
     * @return the nbaselegalvinculadaid
     */
    public BigDecimal getNbaselegalvinculadaid() {
        return nbaselegalvinculadaid;
    }

    /**
     * @param nbaselegalvinculadaid the nbaselegalvinculadaid to set
     */
    public void setNbaselegalvinculadaid(BigDecimal nbaselegalvinculadaid) {
        this.nbaselegalvinculadaid = nbaselegalvinculadaid;
    }

    /**
     * @return the ntipovinculo
     */
    public BigDecimal getNtipovinculo() {
        return ntipovinculo;
    }

    /**
     * @param ntipovinculo the ntipovinculo to set
     */
    public void setNtipovinculo(BigDecimal ntipovinculo) {
        this.ntipovinculo = ntipovinculo;
    }

    public BigDecimal getNestadoid() {
        return nestadoid;
    }

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
}
