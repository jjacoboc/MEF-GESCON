package pe.gob.mef.gescon.hibernate.domain;
// Generated 06/11/2015 11:03:52 AM by Hibernate Tools 4.3.1

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * MtestadoBaselegal generated by hbm2java
 */
public class MtestadoBaselegal implements java.io.Serializable {

    private BigDecimal nestadoid;
    private String vnombre;
    private String vdescripcion;
    private String vusuariocreacion;
    private Date dfechacreacion;
    private String vusuariomodificacion;
    private Date dfechamodificacion;
    private BigDecimal nvinculo;
    private Set tbaselegals = new HashSet(0);

    public MtestadoBaselegal() {
    }

    public MtestadoBaselegal(BigDecimal nestadoid) {
        this.nestadoid = nestadoid;
    }

    public MtestadoBaselegal(BigDecimal nestadoid, String vnombre, String vdescripcion, String vusuariocreacion, Date dfechacreacion, String vusuariomodificacion, Date dfechamodificacion, BigDecimal nvinculo, Set tbaselegals) {
        this.nestadoid = nestadoid;
        this.vnombre = vnombre;
        this.vdescripcion = vdescripcion;
        this.vusuariocreacion = vusuariocreacion;
        this.dfechacreacion = dfechacreacion;
        this.vusuariomodificacion = vusuariomodificacion;
        this.dfechamodificacion = dfechamodificacion;
        this.nvinculo = nvinculo;
        this.tbaselegals = tbaselegals;
    }

    public BigDecimal getNestadoid() {
        return this.nestadoid;
    }

    public void setNestadoid(BigDecimal nestadoid) {
        this.nestadoid = nestadoid;
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

    public BigDecimal getNvinculo() {
        return this.nvinculo;
    }

    public void setNvinculo(BigDecimal nvinculo) {
        this.nvinculo = nvinculo;
    }

    public Set getTbaselegals() {
        return this.tbaselegals;
    }

    public void setTbaselegals(Set tbaselegals) {
        this.tbaselegals = tbaselegals;
    }

}
