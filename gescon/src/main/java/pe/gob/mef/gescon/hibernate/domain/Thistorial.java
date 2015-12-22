package pe.gob.mef.gescon.hibernate.domain;
// Generated 06/11/2015 11:03:52 AM by Hibernate Tools 4.3.1

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Thistorial generated by hbm2java
 */
public class Thistorial implements java.io.Serializable {

    private ThistorialId id;
    private Tconocimiento tconocimiento;
    private BigDecimal ntipoconocimientoid;
    private String vdescripcion;
    private String vtitulo;
    private String vusuariomodificacion;
    private String vusuariocreacion;
    private Date dfechacreacion;
    private BigDecimal nundejecutora;
    private String vruta;
    private String vrango;
    private String vnumero;
    private String vtema;
    private String vdesextra;
    private String vautor;
    private String vfuente;
    private String vtipo;
    private BigDecimal nnumversion;
    private String vcontenido;
    private BigDecimal nactivo;
    private BigDecimal nsituacionid;
    private BigDecimal ndias;
    private String vobservacion;
    private Date dfechamodificacion;
    private BigDecimal ncategoriaid;
    private Set timplementacionHists = new HashSet(0);
    private Set tdiscusionHists = new HashSet(0);
    private Set tvinculoHists = new HashSet(0);
    private Set tcalificacionHists = new HashSet(0);
    private Set tseccionHists = new HashSet(0);
    private Set tobservacionHists = new HashSet(0);

    public Thistorial() {
    }

    public Thistorial(ThistorialId id, Tconocimiento tconocimiento) {
        this.id = id;
        this.tconocimiento = tconocimiento;
    }

    public Thistorial(ThistorialId id, Tconocimiento tconocimiento, String vdescripcion, String vtitulo, String vusuariomodificacion, String vusuariocreacion, Date dfechacreacion, BigDecimal nundejecutora, String vruta, String vrango, String vnumero, String vtema, String vdesextra, String vautor, String vfuente, String vtipo, BigDecimal nnumversion, String vcontenido, BigDecimal nactivo, BigDecimal nsituacionid, BigDecimal ndias, String vobservacion, Date dfechamodificacion, BigDecimal ncategoriaid, Set timplementacionHists, Set tdiscusionHists, Set tvinculoHists, Set tcalificacionHists, Set tseccionHists, Set tobservacionHists) {
        this.id = id;
        this.tconocimiento = tconocimiento;
        this.vdescripcion = vdescripcion;
        this.vtitulo = vtitulo;
        this.vusuariomodificacion = vusuariomodificacion;
        this.vusuariocreacion = vusuariocreacion;
        this.dfechacreacion = dfechacreacion;
        this.nundejecutora = nundejecutora;
        this.vruta = vruta;
        this.vrango = vrango;
        this.vnumero = vnumero;
        this.vtema = vtema;
        this.vdesextra = vdesextra;
        this.vautor = vautor;
        this.vfuente = vfuente;
        this.vtipo = vtipo;
        this.nnumversion = nnumversion;
        this.vcontenido = vcontenido;
        this.nactivo = nactivo;
        this.nsituacionid = nsituacionid;
        this.ndias = ndias;
        this.vobservacion = vobservacion;
        this.dfechamodificacion = dfechamodificacion;
        this.ncategoriaid = ncategoriaid;
        this.timplementacionHists = timplementacionHists;
        this.tdiscusionHists = tdiscusionHists;
        this.tvinculoHists = tvinculoHists;
        this.tcalificacionHists = tcalificacionHists;
        this.tseccionHists = tseccionHists;
        this.tobservacionHists = tobservacionHists;
    }

    public ThistorialId getId() {
        return this.id;
    }

    public void setId(ThistorialId id) {
        this.id = id;
    }

    public Tconocimiento getTconocimiento() {
        return this.tconocimiento;
    }

    public void setTconocimiento(Tconocimiento tconocimiento) {
        this.tconocimiento = tconocimiento;
    }

    public BigDecimal getNtipoconocimientoid() {
        return ntipoconocimientoid;
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

    public BigDecimal getNundejecutora() {
        return this.nundejecutora;
    }

    public void setNundejecutora(BigDecimal nundejecutora) {
        this.nundejecutora = nundejecutora;
    }

    public String getVruta() {
        return this.vruta;
    }

    public void setVruta(String vruta) {
        this.vruta = vruta;
    }

    public String getVrango() {
        return this.vrango;
    }

    public void setVrango(String vrango) {
        this.vrango = vrango;
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

    public BigDecimal getNnumversion() {
        return this.nnumversion;
    }

    public void setNnumversion(BigDecimal nnumversion) {
        this.nnumversion = nnumversion;
    }

    public String getVcontenido() {
        return this.vcontenido;
    }

    public void setVcontenido(String vcontenido) {
        this.vcontenido = vcontenido;
    }

    public BigDecimal getNactivo() {
        return this.nactivo;
    }

    public void setNactivo(BigDecimal nactivo) {
        this.nactivo = nactivo;
    }

    public BigDecimal getNsituacionid() {
        return this.nsituacionid;
    }

    public void setNsituacionid(BigDecimal nsituacionid) {
        this.nsituacionid = nsituacionid;
    }

    public BigDecimal getNdias() {
        return this.ndias;
    }

    public void setNdias(BigDecimal ndias) {
        this.ndias = ndias;
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

    public BigDecimal getNcategoriaid() {
        return this.ncategoriaid;
    }

    public void setNcategoriaid(BigDecimal ncategoriaid) {
        this.ncategoriaid = ncategoriaid;
    }

    public Set getTimplementacionHists() {
        return this.timplementacionHists;
    }

    public void setTimplementacionHists(Set timplementacionHists) {
        this.timplementacionHists = timplementacionHists;
    }

    public Set getTdiscusionHists() {
        return this.tdiscusionHists;
    }

    public void setTdiscusionHists(Set tdiscusionHists) {
        this.tdiscusionHists = tdiscusionHists;
    }

    public Set getTvinculoHists() {
        return this.tvinculoHists;
    }

    public void setTvinculoHists(Set tvinculoHists) {
        this.tvinculoHists = tvinculoHists;
    }

    public Set getTcalificacionHists() {
        return this.tcalificacionHists;
    }

    public void setTcalificacionHists(Set tcalificacionHists) {
        this.tcalificacionHists = tcalificacionHists;
    }

    public Set getTseccionHists() {
        return this.tseccionHists;
    }

    public void setTseccionHists(Set tseccionHists) {
        this.tseccionHists = tseccionHists;
    }

    public Set getTobservacionHists() {
        return this.tobservacionHists;
    }

    public void setTobservacionHists(Set tobservacionHists) {
        this.tobservacionHists = tobservacionHists;
    }

}