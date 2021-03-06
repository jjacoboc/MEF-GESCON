package pe.gob.mef.gescon.hibernate.domain;
// Generated 06/11/2015 11:03:52 AM by Hibernate Tools 4.3.1

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Mtperfil generated by hbm2java
 */
public class Mtperfil implements java.io.Serializable {

    private BigDecimal nperfilid;
    private BigDecimal nactivo;
    private String vusuariomodificacion;
    private String vusuariocreacion;
    private Date dfechacreacion;
    private Date dfechamodificacion;
    private String vdescripcion;
    private String vnombre;
    private Set tpoliticaPerfils = new HashSet(0);
    private Set tuserPerfils = new HashSet(0);

    public Mtperfil() {
    }

    public Mtperfil(BigDecimal nperfilid) {
        this.nperfilid = nperfilid;
    }

    public Mtperfil(BigDecimal nperfilid, BigDecimal nactivo, String vusuariomodificacion, String vusuariocreacion, Date dfechacreacion, Date dfechamodificacion, String vdescripcion, String vnombre, Set tpoliticaPerfils, Set tuserPerfils) {
        this.nperfilid = nperfilid;
        this.nactivo = nactivo;
        this.vusuariomodificacion = vusuariomodificacion;
        this.vusuariocreacion = vusuariocreacion;
        this.dfechacreacion = dfechacreacion;
        this.dfechamodificacion = dfechamodificacion;
        this.vdescripcion = vdescripcion;
        this.vnombre = vnombre;
        this.tpoliticaPerfils = tpoliticaPerfils;
        this.tuserPerfils = tuserPerfils;
    }

    public BigDecimal getNperfilid() {
        return this.nperfilid;
    }

    public void setNperfilid(BigDecimal nperfilid) {
        this.nperfilid = nperfilid;
    }

    public BigDecimal getNactivo() {
        return this.nactivo;
    }

    public void setNactivo(BigDecimal nactivo) {
        this.nactivo = nactivo;
    }

    public String getVusuariomodificacion() {
        return this.vusuariomodificacion;
    }

    public void setVusuariomodificacion(String vusuariomodificacion) {
        this.vusuariomodificacion = vusuariomodificacion;
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
    
    public Date getDfechamodificacion() {
        return this.dfechamodificacion;
    }

    public void setDfechamodificacion(Date dfechamodificacion) {
        this.dfechamodificacion = dfechamodificacion;
    }

    public String getVdescripcion() {
        return this.vdescripcion;
    }

    public void setVdescripcion(String vdescripcion) {
        this.vdescripcion = vdescripcion;
    }

    public String getVnombre() {
        return this.vnombre;
    }

    public void setVnombre(String vnombre) {
        this.vnombre = vnombre;
    }

    public Set getTpoliticaPerfils() {
        return this.tpoliticaPerfils;
    }

    public void setTpoliticaPerfils(Set tpoliticaPerfils) {
        this.tpoliticaPerfils = tpoliticaPerfils;
    }

    public Set getTuserPerfils() {
        return this.tuserPerfils;
    }

    public void setTuserPerfils(Set tuserPerfils) {
        this.tuserPerfils = tuserPerfils;
    }

}
