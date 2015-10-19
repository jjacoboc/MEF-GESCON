/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.gob.mef.gescon.web.bean;

import java.io.Serializable;
import java.util.Date;
import pe.gob.mef.gescon.hibernate.domain.Mtuser;
import pe.gob.mef.gescon.hibernate.domain.TpassId;

/**
 *
 * @author JJacobo
 */
public class Pass implements Serializable {

    private TpassId id;
    private Mtuser mtuser;
    private String vclave;
    private String vusuariocreacion;
    private Date dfechacreacion;
    private String vusuariomodificacion;
    private Date dfechamodificacion;

    public Pass() {

    }

    /**
     * @return the id
     */
    public TpassId getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(TpassId id) {
        this.id = id;
    }

    /**
     * @return the mtuser
     */
    public Mtuser getMtuser() {
        return mtuser;
    }

    /**
     * @param mtuser the mtuser to set
     */
    public void setMtuser(Mtuser mtuser) {
        this.mtuser = mtuser;
    }

    /**
     * @return the vclave
     */
    public String getVclave() {
        return vclave;
    }

    /**
     * @param vclave the vclave to set
     */
    public void setVclave(String vclave) {
        this.vclave = vclave;
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
