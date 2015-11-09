package pe.gob.mef.gescon.hibernate.domain;
// Generated 06/11/2015 11:03:52 AM by Hibernate Tools 4.3.1

import java.io.Serializable;
import java.util.Date;

/**
 * Tpass generated by hbm2java
 */
public class Tpass implements java.io.Serializable {

    private TpassId id;
    private Mtuser mtuser;
    private String vclave;
    private String vusuariocreacion;
    private Date dfechacreacion;
    private String vusuariomodificacion;
    private Date dfechamodificacion;

    public Tpass() {
    }

    public Tpass(TpassId id, Mtuser mtuser) {
        this.id = id;
        this.mtuser = mtuser;
    }

    public Tpass(TpassId id, Mtuser mtuser, String vclave, String vusuariocreacion, Date dfechacreacion, String vusuariomodificacion, Date dfechamodificacion) {
        this.id = id;
        this.mtuser = mtuser;
        this.vclave = vclave;
        this.vusuariocreacion = vusuariocreacion;
        this.dfechacreacion = dfechacreacion;
        this.vusuariomodificacion = vusuariomodificacion;
        this.dfechamodificacion = dfechamodificacion;
    }

    public TpassId getId() {
        return this.id;
    }

    public void setId(TpassId id) {
        this.id = id;
    }

    public Mtuser getMtuser() {
        return this.mtuser;
    }

    public void setMtuser(Mtuser mtuser) {
        this.mtuser = mtuser;
    }

    public String getVclave() {
        return this.vclave;
    }

    public void setVclave(String vclave) {
        this.vclave = vclave;
    }

    public String getVusuariocreacion() {
        return this.vusuariocreacion;
    }

    public void setVusuariocreacion(String vusuariocreacion) {
        this.vusuariocreacion = vusuariocreacion;
    }

    public Serializable getDfechacreacion() {
        return this.dfechacreacion;
    }

    public void setDfechacreacion(Date dfechacreacion) {
        this.dfechacreacion = dfechacreacion;
    }

    public String getVusuariomodificacion() {
        return this.vusuariomodificacion;
    }

    public void setVusuariomodificacion(String vusuariomodificacion) {
        this.vusuariomodificacion = vusuariomodificacion;
    }

    public Serializable getDfechamodificacion() {
        return this.dfechamodificacion;
    }

    public void setDfechamodificacion(Date dfechamodificacion) {
        this.dfechamodificacion = dfechamodificacion;
    }

}
