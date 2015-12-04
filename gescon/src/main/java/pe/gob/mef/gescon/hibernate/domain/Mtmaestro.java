package pe.gob.mef.gescon.hibernate.domain;
// Generated 06/11/2015 11:03:52 AM by Hibernate Tools 4.3.1

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Mtmaestro generated by hbm2java
 */
public class Mtmaestro implements java.io.Serializable {

    private BigDecimal nmaestroid;
    private String vnombre;
    private String vdescripcion;
    private String vusuariocreacion;
    private Date dfechacreacion;
    private String vusuariomodificacion;
    private Date dfechamodificacion;
    private BigDecimal nactivo;
    private Set tmaestrodetalles = new HashSet(0);

    public Mtmaestro() {
    }

    public Mtmaestro(BigDecimal nmaestroid) {
        this.nmaestroid = nmaestroid;
    }

    public Mtmaestro(BigDecimal nmaestroid, String vnombre, String vdescripcion, String vusuariocreacion, Date dfechacreacion, String vusuariomodificacion, Date dfechamodificacion, BigDecimal nactivo, Set tmaestrodetalles) {
        this.nmaestroid = nmaestroid;
        this.vnombre = vnombre;
        this.vdescripcion = vdescripcion;
        this.vusuariocreacion = vusuariocreacion;
        this.dfechacreacion = dfechacreacion;
        this.vusuariomodificacion = vusuariomodificacion;
        this.dfechamodificacion = dfechamodificacion;
        this.nactivo = nactivo;
        this.tmaestrodetalles = tmaestrodetalles;
    }

    public BigDecimal getNmaestroid() {
        return this.nmaestroid;
    }

    public void setNmaestroid(BigDecimal nmaestroid) {
        this.nmaestroid = nmaestroid;
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

    public Date getDfechacreacion() {
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

    public Date getDfechamodificacion() {
        return this.dfechamodificacion;
    }

    public void setDfechamodificacion(Date dfechamodificacion) {
        this.dfechamodificacion = dfechamodificacion;
    }

    public BigDecimal getNactivo() {
        return this.nactivo;
    }

    public void setNactivo(BigDecimal nactivo) {
        this.nactivo = nactivo;
    }

    public Set getTmaestrodetalles() {
        return this.tmaestrodetalles;
    }

    public void setTmaestrodetalles(Set tmaestrodetalles) {
        this.tmaestrodetalles = tmaestrodetalles;
    }

}
