package pe.gob.mef.gescon.hibernate.domain;
// Generated 06/11/2015 11:03:52 AM by Hibernate Tools 4.3.1

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Tbaselegal generated by hbm2java
 */
public class Tbaselegal implements java.io.Serializable {

    private BigDecimal nbaselegalid;
    private BigDecimal ncategoriaid;
    private BigDecimal nestadoid;
    private BigDecimal nrangoid;
    private BigDecimal ntiporangoid;
    private String vnombre;
    private String vnumero;
    private String vsumilla;
    private Date dfechavigencia;
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
    private String vmsjusuariocreacion;
    private String vmsjmoderador;
    private BigDecimal ndestacado;
    private BigDecimal ncodigowiki;
    private Set tvinculoBaselegals = new HashSet(0);
    private Set tarchivos = new HashSet(0);

    public Tbaselegal() {
    }

    public Tbaselegal(BigDecimal nbaselegalid) {
        this.nbaselegalid = nbaselegalid;
    }

    public Tbaselegal(BigDecimal nbaselegalid, BigDecimal ncategoriaid, BigDecimal nestadoid, BigDecimal nrangoid, String vnombre, String vnumero, String vsumilla, Date dfechavigencia, Date dfechapublicacion, String vtema, String vusuariocreacion, String vusuariomodificacion, Date dfechacreacion, Date dfechamodificacion, BigDecimal ngobnacional, BigDecimal ngobregional, BigDecimal ngoblocal, BigDecimal nmancomunidades, BigDecimal nactivo, BigDecimal ndestacado, Set tvinculoBaselegals, Set tarchivos) {
        this.nbaselegalid = nbaselegalid;
        this.ncategoriaid = ncategoriaid;
        this.nestadoid = nestadoid;
        this.nrangoid = nrangoid;
        this.vnombre = vnombre;
        this.vnumero = vnumero;
        this.vsumilla = vsumilla;
        this.dfechavigencia = dfechavigencia;
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

    public BigDecimal getNcategoriaid() {
        return this.ncategoriaid;
    }

    public void setNcategoriaid(BigDecimal ncategoriaid) {
        this.ncategoriaid = ncategoriaid;
    }

    public BigDecimal getNestadoid() {
        return this.nestadoid;
    }

    public void setNestadoid(BigDecimal nestadoid) {
        this.nestadoid = nestadoid;
    }

    public BigDecimal getNrangoid() {
        return this.nrangoid;
    }

    public void setNrangoid(BigDecimal nrangoid) {
        this.nrangoid = nrangoid;
    }

    public BigDecimal getNtiporangoid() {
        return ntiporangoid;
    }

    public void setNtiporangoid(BigDecimal ntiporangoid) {
        this.ntiporangoid = ntiporangoid;
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

    public Date getDfechavigencia() {
        return dfechavigencia;
    }

    public void setDfechavigencia(Date dfechavigencia) {
        this.dfechavigencia = dfechavigencia;
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

    public BigDecimal getNactivo() {
        return this.nactivo;
    }

    public void setNactivo(BigDecimal nactivo) {
        this.nactivo = nactivo;
    }

    /**
     * @return the vmsjusuariocreacion
     */
    public String getVmsjusuariocreacion() {
        return vmsjusuariocreacion;
    }

    /**
     * @param vmsjusuariocreacion the vmsjusuariocreacion to set
     */
    public void setVmsjusuariocreacion(String vmsjusuariocreacion) {
        this.vmsjusuariocreacion = vmsjusuariocreacion;
    }

    /**
     * @return the vmsjmoderador
     */
    public String getVmsjmoderador() {
        return vmsjmoderador;
    }

    /**
     * @param vmsjmoderador the vmsjmoderador to set
     */
    public void setVmsjmoderador(String vmsjmoderador) {
        this.vmsjmoderador = vmsjmoderador;
    }

    public BigDecimal getNdestacado() {
        return ndestacado;
    }

    public void setNdestacado(BigDecimal ndestacado) {
        this.ndestacado = ndestacado;
    }

    public BigDecimal getNcodigowiki() {
        return ncodigowiki;
    }

    public void setNcodigowiki(BigDecimal ncodigowiki) {
        this.ncodigowiki = ncodigowiki;
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