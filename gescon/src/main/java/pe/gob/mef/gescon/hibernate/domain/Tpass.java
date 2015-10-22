package pe.gob.mef.gescon.hibernate.domain;
// Generated 11/09/2015 05:20:07 PM by Hibernate Tools 4.3.1

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

    public Tpass(TpassId id, Mtuser mtuser, String vclave, String vusuariocreacion, Date dfechacreacion) {
        this.id = id;
        this.mtuser = mtuser;
        this.vclave = vclave;
        this.vusuariocreacion = vusuariocreacion;
        this.dfechacreacion = dfechacreacion;
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

    public Date getDfechacreacion() {
        return this.dfechacreacion;
    }

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
