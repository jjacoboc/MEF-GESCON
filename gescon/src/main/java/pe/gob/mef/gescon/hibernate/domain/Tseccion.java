package pe.gob.mef.gescon.hibernate.domain;
// Generated 06/11/2015 11:03:52 AM by Hibernate Tools 4.3.1

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Tseccion generated by hbm2java
 */
public class Tseccion implements java.io.Serializable {

    private BigDecimal nseccionid;
    private Tconocimiento tconocimiento;
    private String vtitulo;
    private String vruta;
    private BigDecimal norden;
    private String vusuariocreacion;
    private Date dfechacreacion;
    private String vusuariomodificacion;
    private Date dfechamodificacion;

    public Tseccion() {
    }

    public Tseccion(BigDecimal nseccionid) {
        this.nseccionid = nseccionid;
    }

    public Tseccion(BigDecimal nseccionid, Tconocimiento tconocimiento, String vtitulo, String vruta, BigDecimal norden, String vusuariocreacion, Date dfechacreacion, String vusuariomodificacion, Date dfechamodificacion) {
        this.nseccionid = nseccionid;
        this.tconocimiento = tconocimiento;
        this.vtitulo = vtitulo;
        this.vruta = vruta;
        this.norden = norden;
        this.vusuariocreacion = vusuariocreacion;
        this.dfechacreacion = dfechacreacion;
        this.vusuariomodificacion = vusuariomodificacion;
        this.dfechamodificacion = dfechamodificacion;
    }

    public BigDecimal getNseccionid() {
        return this.nseccionid;
    }

    public void setNseccionid(BigDecimal nseccionid) {
        this.nseccionid = nseccionid;
    }

    public Tconocimiento getTconocimiento() {
        return this.tconocimiento;
    }

    public void setTconocimiento(Tconocimiento tconocimiento) {
        this.tconocimiento = tconocimiento;
    }

    public String getVtitulo() {
        return this.vtitulo;
    }

    public void setVtitulo(String vtitulo) {
        this.vtitulo = vtitulo;
    }

    public String getVruta() {
        return this.vruta;
    }

    public void setVruta(String vruta) {
        this.vruta = vruta;
    }

    public BigDecimal getNorden() {
        return this.norden;
    }

    public void setNorden(BigDecimal norden) {
        this.norden = norden;
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
