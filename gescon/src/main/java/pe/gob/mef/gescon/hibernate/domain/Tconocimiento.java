package pe.gob.mef.gescon.hibernate.domain;
// Generated 06/11/2015 11:03:52 AM by Hibernate Tools 4.3.1

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Tconocimiento generated by hbm2java
 */
public class Tconocimiento implements java.io.Serializable {

    private BigDecimal nconocimientoid;
    private Mtcategoria mtcategoria;
    private Mtsituacion mtsituacion;
    private MttipoConocimiento mttipoConocimiento;
    private String vdescripcion;
    private String vtitulo;
    private String vusuariomodificacion;
    private String vusuariocreacion;
    private Date dfechacreacion;
    private String vejecutora;
    private String varchivo;
    private String vnumero;
    private String vtema;
    private String vdesextra;
    private String vautor;
    private String vfuente;
    private String vtipo;
    private String vcontenido;
    private BigDecimal nestado;
    private BigDecimal ndias;
    private String vobservacion;
    private Date dfechamodificacion;
    private BigDecimal ndestacado;
    private Set tvinculos = new HashSet(0);
    private Set tobservacions = new HashSet(0);
    private Set thistorials = new HashSet(0);
    private Set timplementacions = new HashSet(0);
    private Set tdiscusions = new HashSet(0);
    private Set tseccions = new HashSet(0);
    private Set tcalificacions = new HashSet(0);

    public Tconocimiento() {
    }

    public Tconocimiento(BigDecimal nconocimientoid, Mtcategoria mtcategoria, Mtsituacion mtsituacion, MttipoConocimiento mttipoConocimiento) {
        this.nconocimientoid = nconocimientoid;
        this.mtcategoria = mtcategoria;
        this.mtsituacion = mtsituacion;
        this.mttipoConocimiento = mttipoConocimiento;
    }

    public Tconocimiento(BigDecimal nconocimientoid, Mtcategoria mtcategoria, Mtsituacion mtsituacion, MttipoConocimiento mttipoConocimiento, String vdescripcion, String vtitulo, String vusuariomodificacion, String vusuariocreacion, Date dfechacreacion, String vejecutora, String varchivo, String vnumero, String vtema, String vdesextra, String vautor, String vfuente, String vtipo, String vcontenido, BigDecimal nestado, BigDecimal ndias, String vobservacion, Date dfechamodificacion, BigDecimal ndestacado, Set tvinculos, Set tobservacions, Set thistorials, Set timplementacions, Set tdiscusions, Set tseccions, Set tcalificacions) {
        this.nconocimientoid = nconocimientoid;
        this.mtcategoria = mtcategoria;
        this.mtsituacion = mtsituacion;
        this.mttipoConocimiento = mttipoConocimiento;
        this.vdescripcion = vdescripcion;
        this.vtitulo = vtitulo;
        this.vusuariomodificacion = vusuariomodificacion;
        this.vusuariocreacion = vusuariocreacion;
        this.dfechacreacion = dfechacreacion;
        this.vejecutora = vejecutora;
        this.varchivo = varchivo;
        this.vnumero = vnumero;
        this.vtema = vtema;
        this.vdesextra = vdesextra;
        this.vautor = vautor;
        this.vfuente = vfuente;
        this.vtipo = vtipo;
        this.vcontenido = vcontenido;
        this.nestado = nestado;
        this.ndias = ndias;
        this.vobservacion = vobservacion;
        this.dfechamodificacion = dfechamodificacion;
        this.ndestacado = ndestacado;
        this.tvinculos = tvinculos;
        this.tobservacions = tobservacions;
        this.thistorials = thistorials;
        this.timplementacions = timplementacions;
        this.tdiscusions = tdiscusions;
        this.tseccions = tseccions;
        this.tcalificacions = tcalificacions;
    }

    public BigDecimal getNconocimientoid() {
        return this.nconocimientoid;
    }

    public void setNconocimientoid(BigDecimal nconocimientoid) {
        this.nconocimientoid = nconocimientoid;
    }

    public Mtcategoria getMtcategoria() {
        return this.mtcategoria;
    }

    public void setMtcategoria(Mtcategoria mtcategoria) {
        this.mtcategoria = mtcategoria;
    }

    public Mtsituacion getMtsituacion() {
        return this.mtsituacion;
    }

    public void setMtsituacion(Mtsituacion mtsituacion) {
        this.mtsituacion = mtsituacion;
    }

    public MttipoConocimiento getMttipoConocimiento() {
        return this.mttipoConocimiento;
    }

    public void setMttipoConocimiento(MttipoConocimiento mttipoConocimiento) {
        this.mttipoConocimiento = mttipoConocimiento;
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

    public Serializable getDfechacreacion() {
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

    public String getVarchivo() {
        return this.varchivo;
    }

    public void setVarchivo(String varchivo) {
        this.varchivo = varchivo;
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

    public BigDecimal getNestado() {
        return this.nestado;
    }

    public void setNestado(BigDecimal nestado) {
        this.nestado = nestado;
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

    public Serializable getDfechamodificacion() {
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

    public Set getTvinculos() {
        return this.tvinculos;
    }

    public void setTvinculos(Set tvinculos) {
        this.tvinculos = tvinculos;
    }

    public Set getTobservacions() {
        return this.tobservacions;
    }

    public void setTobservacions(Set tobservacions) {
        this.tobservacions = tobservacions;
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

    public Set getTdiscusions() {
        return this.tdiscusions;
    }

    public void setTdiscusions(Set tdiscusions) {
        this.tdiscusions = tdiscusions;
    }

    public Set getTseccions() {
        return this.tseccions;
    }

    public void setTseccions(Set tseccions) {
        this.tseccions = tseccions;
    }

    public Set getTcalificacions() {
        return this.tcalificacions;
    }

    public void setTcalificacions(Set tcalificacions) {
        this.tcalificacions = tcalificacions;
    }

}
