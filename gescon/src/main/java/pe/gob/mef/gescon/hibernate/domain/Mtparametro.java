package pe.gob.mef.gescon.hibernate.domain;
// Generated 06/11/2015 11:03:52 AM by Hibernate Tools 4.3.1

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Mtparametro generated by hbm2java
 */
public class Mtparametro implements java.io.Serializable {

    private BigDecimal nparametroid;
    private String vnombre;
    private BigDecimal nvalor;
    private String vdescripcion;
    private String vusuariocreacion;
    private String vusuariomodificacion;
    private Date dfechacreacion;
    private Date dfechamodificacion;
    private BigDecimal nactivo;
    private Set mtalertas = new HashSet(0);

    public Mtparametro() {
    }

    public Mtparametro(BigDecimal nparametroid) {
        this.nparametroid = nparametroid;
    }

    public Mtparametro(BigDecimal nparametroid, String vnombre, BigDecimal nvalor, String vdescripcion, String vusuariocreacion, String vusuariomodificacion, Date dfechacreacion, Date dfechamodificacion, BigDecimal nactivo, Set mtalertas) {
        this.nparametroid = nparametroid;
        this.vnombre = vnombre;
        this.nvalor = nvalor;
        this.vdescripcion = vdescripcion;
        this.vusuariocreacion = vusuariocreacion;
        this.vusuariomodificacion = vusuariomodificacion;
        this.dfechacreacion = dfechacreacion;
        this.dfechamodificacion = dfechamodificacion;
        this.nactivo = nactivo;
        this.mtalertas = mtalertas;
    }

    public BigDecimal getNparametroid() {
        return this.nparametroid;
    }

    public void setNparametroid(BigDecimal nparametroid) {
        this.nparametroid = nparametroid;
    }

    public String getVnombre() {
        return this.vnombre;
    }

    public void setVnombre(String vnombre) {
        this.vnombre = vnombre;
    }

    public BigDecimal getNvalor() {
        return this.nvalor;
    }

    public void setNvalor(BigDecimal nvalor) {
        this.nvalor = nvalor;
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

    public Serializable getDfechacreacion() {
        return this.dfechacreacion;
    }

    public void setDfechacreacion(Date dfechacreacion) {
        this.dfechacreacion = dfechacreacion;
    }

    public Serializable getDfechamodificacion() {
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

    public Set getMtalertas() {
        return this.mtalertas;
    }

    public void setMtalertas(Set mtalertas) {
        this.mtalertas = mtalertas;
    }

}
