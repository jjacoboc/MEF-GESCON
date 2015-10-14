package pe.gob.mef.gescon.hibernate.domain;
// Generated 22/09/2015 05:59:19 PM by Hibernate Tools 4.3.1

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Tbaselegal generated by hbm2java
 */
public class Tbaselegal implements java.io.Serializable {

    private BigDecimal nbaselegalid;
    private Mtrango mtrango;
    private String vnombre;
    private BigDecimal ncategoriaid;
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
    private Set tarchivos = new HashSet(0);

    public Tbaselegal() {
    }

    public Tbaselegal(BigDecimal nbaselegalid) {
        this.nbaselegalid = nbaselegalid;
    }

    public Tbaselegal(BigDecimal nbaselegalid, Mtrango mtrango, String vnombre, BigDecimal ncategoriaid, String vnumero, String vsumilla, Date dfechapublicacion, String vtema, String vusuariocreacion, String vusuariomodificacion, Date dfechacreacion, Date dfechamodificacion, BigDecimal ngobnacional, BigDecimal ngobregional, BigDecimal ngoblocal, BigDecimal nmancomunidades, Set tarchivos) {
        this.nbaselegalid = nbaselegalid;
        this.mtrango = mtrango;
        this.vnombre = vnombre;
        this.ncategoriaid = ncategoriaid;
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
        this.tarchivos = tarchivos;
    }

    public BigDecimal getNbaselegalid() {
        return this.nbaselegalid;
    }

    public void setNbaselegalid(BigDecimal nbaselegalid) {
        this.nbaselegalid = nbaselegalid;
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

    public BigDecimal getNcategoriaid() {
        return this.ncategoriaid;
    }

    public void setNcategoriaid(BigDecimal ncategoriaid) {
        this.ncategoriaid = ncategoriaid;
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

    public Date getDfechapublicacion() {
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

    /**
     * @return the nactivo
     */
    public BigDecimal getNactivo() {
        return nactivo;
    }

    /**
     * @param nactivo the nactivo to set
     */
    public void setNactivo(BigDecimal nactivo) {
        this.nactivo = nactivo;
    }

    public Set getTarchivos() {
        return this.tarchivos;
    }

    public void setTarchivos(Set tarchivos) {
        this.tarchivos = tarchivos;
    }

}
