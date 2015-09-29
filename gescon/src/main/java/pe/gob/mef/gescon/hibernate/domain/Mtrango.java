package pe.gob.mef.gescon.hibernate.domain;
// Generated 22/09/2015 05:59:19 PM by Hibernate Tools 4.3.1

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Mtrango generated by hbm2java
 */
public class Mtrango implements java.io.Serializable {

    private BigDecimal nrangoid;
    private String vnombre;
    private String vdescripcion;
    private String vusuariocreacion;
    private String vusuariomodificacion;
    private Date dfechacreacion;
    private Date dfechamodificacion;
    private Set tbaselegals = new HashSet(0);

    public Mtrango() {
    }

    public Mtrango(BigDecimal nrangoid) {
        this.nrangoid = nrangoid;
    }

    public Mtrango(BigDecimal nrangoid, String vnombre, String vdescripcion, String vusuariocreacion, String vusuariomodificacion, Date dfechacreacion, Date dfechamodificacion, Set tbaselegals) {
        this.nrangoid = nrangoid;
        this.vnombre = vnombre;
        this.vdescripcion = vdescripcion;
        this.vusuariocreacion = vusuariocreacion;
        this.vusuariomodificacion = vusuariomodificacion;
        this.dfechacreacion = dfechacreacion;
        this.dfechamodificacion = dfechamodificacion;
        this.tbaselegals = tbaselegals;
    }

    public BigDecimal getNrangoid() {
        return this.nrangoid;
    }

    public void setNrangoid(BigDecimal nrangoid) {
        this.nrangoid = nrangoid;
    }

    public String getVnombre() {
        return this.vnombre;
    }

    public void setVnombre(String vnombre) {
        this.vnombre = vnombre;
    }

    public String getVdescripcion() {
        return this.vdescripcion;
    }

    public void setVdescripcion(String vdescripcion) {
        this.vdescripcion = vdescripcion;
    }

    public String getVusuariocreacion() {
        return this.vusuariocreacion;
    }

    public void setVusuariocreacion(String vusuariocreacion) {
        this.vusuariocreacion = vusuariocreacion;
    }

    public String getVusuariomodificacion() {
        return this.vusuariomodificacion;
    }

    public void setVusuariomodificacion(String vusuariomodificacion) {
        this.vusuariomodificacion = vusuariomodificacion;
    }

    public Date getDfechacreacion() {
        return this.dfechacreacion;
    }

    public void setDfechacreacion(Date dfechacreacion) {
        this.dfechacreacion = dfechacreacion;
    }

    public Date getDfechamodificacion() {
        return this.dfechamodificacion;
    }

    public void setDfechamodificacion(Date dfechamodificacion) {
        this.dfechamodificacion = dfechamodificacion;
    }

    public Set getTbaselegals() {
        return this.tbaselegals;
    }

    public void setTbaselegals(Set tbaselegals) {
        this.tbaselegals = tbaselegals;
    }

}
