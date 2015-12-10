package pe.gob.mef.gescon.hibernate.domain;
// Generated 06/11/2015 11:03:52 AM by Hibernate Tools 4.3.1

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Tconocimiento generated by hbm2java
 */
public class Tconocimiento implements java.io.Serializable {

    private BigDecimal nconocimientoid;
    private BigDecimal ncategoriaid;
    private BigDecimal nsituacionid;
    private BigDecimal ntipoconocimientoid;
    private String vdescripcion;
    private String vtitulo;
    private String vusuariomodificacion;
    private String vusuariocreacion;
    private Date dfechacreacion;
    private String vejecutora;
    private String vruta;
    private String vnumero;
    private String vtema;
    private String vdesextra;
    private String vautor;
    private String vfuente;
    private String vtipo;
    private String vcontenido;
    private BigDecimal ndias;
    private BigDecimal nactivo;
    private String vobservacion;
    private Date dfechamodificacion;
    private BigDecimal ndestacado;
    private Date dfechapublicacion;
    private String vmsjsolicita;
    private String vmsjrespuesta;
    private Set thistorials = new HashSet(0);
    private Set timplementacions = new HashSet(0);

    public Tconocimiento() {
    }

    public Tconocimiento(BigDecimal nconocimientoid) {
        this.nconocimientoid = nconocimientoid;
    }

    public Tconocimiento(BigDecimal nconocimientoid, BigDecimal ncategoriaid, BigDecimal nsituacionid, BigDecimal ntipoconocimientoid, String vdescripcion, String vtitulo, String vusuariomodificacion, String vusuariocreacion, Date dfechacreacion, String vejecutora, String vruta, String vnumero, String vtema, String vdesextra, String vautor, String vfuente, String vtipo, String vcontenido, BigDecimal ndias, String vobservacion, Date dfechamodificacion, BigDecimal ndestacado, Date dfechapublicacion, String vmsjsolicita, String vmsjrespuesta, Set tvinculos, Set tobservacions, Set thistorials, Set timplementacions, Set tdiscusions, Set tseccions, Set tcalificacions) {
        this.nconocimientoid = nconocimientoid;
        this.ncategoriaid = ncategoriaid;
        this.nsituacionid = nsituacionid;
        this.ntipoconocimientoid = ntipoconocimientoid;
        this.vdescripcion = vdescripcion;
        this.vtitulo = vtitulo;
        this.vusuariomodificacion = vusuariomodificacion;
        this.vusuariocreacion = vusuariocreacion;
        this.dfechacreacion = dfechacreacion;
        this.vejecutora = vejecutora;
        this.vruta = vruta;
        this.vnumero = vnumero;
        this.vtema = vtema;
        this.vdesextra = vdesextra;
        this.vautor = vautor;
        this.vfuente = vfuente;
        this.vtipo = vtipo;
        this.vcontenido = vcontenido;
        this.ndias = ndias;
        this.vobservacion = vobservacion;
        this.dfechamodificacion = dfechamodificacion;
        this.ndestacado = ndestacado;
        this.dfechapublicacion = dfechapublicacion;
        this.vmsjsolicita = vmsjsolicita;
        this.vmsjrespuesta = vmsjrespuesta;
        this.thistorials = thistorials;
        this.timplementacions = timplementacions;
    }

    public BigDecimal getNconocimientoid() {
        return this.nconocimientoid;
    }

    public void setNconocimientoid(BigDecimal nconocimientoid) {
        this.nconocimientoid = nconocimientoid;
    }

    public BigDecimal getNcategoriaid() {
        return this.ncategoriaid;
    }

    public void setNcategoriaid(BigDecimal ncategoriaid) {
        this.ncategoriaid = ncategoriaid;
    }

    public BigDecimal getNsituacionid() {
        return this.nsituacionid;
    }

    public void setNsituacionid(BigDecimal nsituacionid) {
        this.nsituacionid = nsituacionid;
    }

    public BigDecimal getNtipoconocimientoid() {
        return this.ntipoconocimientoid;
    }

    public void setNtipoconocimientoid(BigDecimal ntipoconocimientoid) {
        this.ntipoconocimientoid = ntipoconocimientoid;
    }

    public String getVdescripcion() {
        return this.vdescripcion;
    }

    public void setVdescripcion(String vdescripcion) {
        this.vdescripcion = vdescripcion;
    }

    public String getVtitulo() {
        return this.vtitulo;
    }

    public void setVtitulo(String vtitulo) {
        this.vtitulo = vtitulo;
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

    public String getVejecutora() {
        return this.vejecutora;
    }

    public void setVejecutora(String vejecutora) {
        this.vejecutora = vejecutora;
    }

    public String getVruta() {
        return this.vruta;
    }

    public void setVruta(String vruta) {
        this.vruta = vruta;
    }

    public String getVnumero() {
        return this.vnumero;
    }

    public void setVnumero(String vnumero) {
        this.vnumero = vnumero;
    }

    public String getVtema() {
        return this.vtema;
    }

    public void setVtema(String vtema) {
        this.vtema = vtema;
    }

    public String getVdesextra() {
        return this.vdesextra;
    }

    public void setVdesextra(String vdesextra) {
        this.vdesextra = vdesextra;
    }

    public String getVautor() {
        return this.vautor;
    }

    public void setVautor(String vautor) {
        this.vautor = vautor;
    }

    public String getVfuente() {
        return this.vfuente;
    }

    public void setVfuente(String vfuente) {
        this.vfuente = vfuente;
    }

    public String getVtipo() {
        return this.vtipo;
    }

    public void setVtipo(String vtipo) {
        this.vtipo = vtipo;
    }

    public String getVcontenido() {
        return this.vcontenido;
    }

    public void setVcontenido(String vcontenido) {
        this.vcontenido = vcontenido;
    }

    public BigDecimal getNdias() {
        return this.ndias;
    }

    public void setNdias(BigDecimal ndias) {
        this.ndias = ndias;
    }

    public BigDecimal getNactivo() {
        return nactivo;
    }

    public void setNactivo(BigDecimal nactivo) {
        this.nactivo = nactivo;
    }

    public String getVobservacion() {
        return this.vobservacion;
    }

    public void setVobservacion(String vobservacion) {
        this.vobservacion = vobservacion;
    }

    public Date getDfechamodificacion() {
        return this.dfechamodificacion;
    }

    public void setDfechamodificacion(Date dfechamodificacion) {
        this.dfechamodificacion = dfechamodificacion;
    }

    public BigDecimal getNdestacado() {
        return this.ndestacado;
    }

    public void setNdestacado(BigDecimal ndestacado) {
        this.ndestacado = ndestacado;
    }

    public Date getDfechapublicacion() {
        return dfechapublicacion;
    }

    public void setDfechapublicacion(Date dfechapublicacion) {
        this.dfechapublicacion = dfechapublicacion;
    }

    /**
     * @return the vmsjsolicita
     */
    public String getVmsjsolicita() {
        return vmsjsolicita;
    }

    /**
     * @param vmsjsolicita the vmsjsolicita to set
     */
    public void setVmsjsolicita(String vmsjsolicita) {
        this.vmsjsolicita = vmsjsolicita;
    }

    /**
     * @return the vmsjrespuesta
     */
    public String getVmsjrespuesta() {
        return vmsjrespuesta;
    }

    /**
     * @param vmsjrespuesta the vmsjrespuesta to set
     */
    public void setVmsjrespuesta(String vmsjrespuesta) {
        this.vmsjrespuesta = vmsjrespuesta;
    }

    public Set getThistorials() {
        return this.thistorials;
    }

    public void setThistorials(Set thistorials) {
        this.thistorials = thistorials;
    }

    public Set getTimplementacions() {
        return this.timplementacions;
    }

    public void setTimplementacions(Set timplementacions) {
        this.timplementacions = timplementacions;
    }

}
