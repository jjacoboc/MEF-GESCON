package pe.gob.mef.gescon.hibernate.domain;
// Generated 06/11/2015 11:03:52 AM by Hibernate Tools 4.3.1

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Tbaselegal generated by hbm2java
 */
public class Tbaselegal implements java.io.Serializable {

    private BigDecimal nbaselegalid;
    private Mtcategoria mtcategoria;
    private MtestadoBaselegal mtestadoBaselegal;
    private Mtrango mtrango;
    private String vnombre;
    private String vnumero;
    private String vsumilla;
    private Date dfechapublicacion;
    private String vtema;
    private String vusuariocreacion;
    private String vusuariomodificacion;
    private Date dfechacreacion;
    private Date dfechamodificacion;
    private BigDecimal ngobnacional;
    private BigDecimal ngobregional;
    private BigDecimal ngoblocal;
    private BigDecimal nmancomunidades;
    private BigDecimal nactivo;
    private Set tvinculoBaselegals = new HashSet(0);
    private Set tarchivos = new HashSet(0);

    public Tbaselegal() {
    }

    public Tbaselegal(BigDecimal nbaselegalid) {
        this.nbaselegalid = nbaselegalid;
    }

    public Tbaselegal(BigDecimal nbaselegalid, Mtcategoria mtcategoria, MtestadoBaselegal mtestadoBaselegal, Mtrango mtrango, String vnombre, String vnumero, String vsumilla, Date dfechapublicacion, String vtema, String vusuariocreacion, String vusuariomodificacion, Date dfechacreacion, Date dfechamodificacion, BigDecimal ngobnacional, BigDecimal ngobregional, BigDecimal ngoblocal, BigDecimal nmancomunidades, BigDecimal nactivo, Set tvinculoBaselegals, Set tarchivos) {
        this.nbaselegalid = nbaselegalid;
        this.mtcategoria = mtcategoria;
        this.mtestadoBaselegal = mtestadoBaselegal;
        this.mtrango = mtrango;
        this.vnombre = vnombre;
        this.vnumero = vnumero;
        this.vsumilla = vsumilla;
        this.dfechapublicacion = dfechapublicacion;
        this.vtema = vtema;
        this.vusuariocreacion = vusuariocreacion;
        this.vusuariomodificacion = vusuariomodificacion;
        this.dfechacreacion = dfechacreacion;
        this.dfechamodificacion = dfechamodificacion;
        this.ngobnacional = ngobnacional;
        this.ngobregional = ngobregional;
        this.ngoblocal = ngoblocal;
        this.nmancomunidades = nmancomunidades;
        this.nactivo = nactivo;
        this.tvinculoBaselegals = tvinculoBaselegals;
        this.tarchivos = tarchivos;
    }

    public BigDecimal getNbaselegalid() {
        return this.nbaselegalid;
    }

    public void setNbaselegalid(BigDecimal nbaselegalid) {
        this.nbaselegalid = nbaselegalid;
    }

    public Mtcategoria getMtcategoria() {
        return this.mtcategoria;
    }

    public void setMtcategoria(Mtcategoria mtcategoria) {
        this.mtcategoria = mtcategoria;
    }

    public MtestadoBaselegal getMtestadoBaselegal() {
        return this.mtestadoBaselegal;
    }

    public void setMtestadoBaselegal(MtestadoBaselegal mtestadoBaselegal) {
        this.mtestadoBaselegal = mtestadoBaselegal;
    }

    public Mtrango getMtrango() {
        return this.mtrango;
    }

    public void setMtrango(Mtrango mtrango) {
        this.mtrango = mtrango;
    }

    public String getVnombre() {
        return this.vnombre;
    }

    public void setVnombre(String vnombre) {
        this.vnombre = vnombre;
    }

    public String getVnumero() {
        return this.vnumero;
    }

    public void setVnumero(String vnumero) {
        this.vnumero = vnumero;
    }

    public String getVsumilla() {
        return this.vsumilla;
    }

    public void setVsumilla(String vsumilla) {
        this.vsumilla = vsumilla;
    }

    public Serializable getDfechapublicacion() {
        return this.dfechapublicacion;
    }

    public void setDfechapublicacion(Date dfechapublicacion) {
        this.dfechapublicacion = dfechapublicacion;
    }

    public String getVtema() {
        return this.vtema;
    }

    public void setVtema(String vtema) {
        this.vtema = vtema;
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

    public BigDecimal getNgobnacional() {
        return this.ngobnacional;
    }

    public void setNgobnacional(BigDecimal ngobnacional) {
        this.ngobnacional = ngobnacional;
    }

    public BigDecimal getNgobregional() {
        return this.ngobregional;
    }

    public void setNgobregional(BigDecimal ngobregional) {
        this.ngobregional = ngobregional;
    }

    public BigDecimal getNgoblocal() {
        return this.ngoblocal;
    }

    public void setNgoblocal(BigDecimal ngoblocal) {
        this.ngoblocal = ngoblocal;
    }

    public BigDecimal getNmancomunidades() {
        return this.nmancomunidades;
    }

    public void setNmancomunidades(BigDecimal nmancomunidades) {
        this.nmancomunidades = nmancomunidades;
    }

    public BigDecimal getNactivo() {
        return this.nactivo;
    }

    public void setNactivo(BigDecimal nactivo) {
        this.nactivo = nactivo;
    }

    public Set getTvinculoBaselegals() {
        return this.tvinculoBaselegals;
    }

    public void setTvinculoBaselegals(Set tvinculoBaselegals) {
        this.tvinculoBaselegals = tvinculoBaselegals;
    }

    public Set getTarchivos() {
        return this.tarchivos;
    }

    public void setTarchivos(Set tarchivos) {
        this.tarchivos = tarchivos;
    }

}
