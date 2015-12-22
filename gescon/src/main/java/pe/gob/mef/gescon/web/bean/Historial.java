/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.gob.mef.gescon.web.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import pe.gob.mef.gescon.common.Constante;
import pe.gob.mef.gescon.hibernate.domain.Tconocimiento;
import pe.gob.mef.gescon.hibernate.domain.ThistorialId;

/**
 *
 * @author JJacobo
 */
public class Historial implements Serializable {
    
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
    private String vfechacreacion;
    private String vfechamodificacion;
    private String vnombreusuario;
    private String descripcionHtml;
    private List<SeccionHist> listaSeccionHist;
    private List<Consulta> listaVinculosBL;
    private List<Consulta> listaVinculosPR;
    private List<Consulta> listaVinculosWK;
    private List<Consulta> listaVinculosOM;
    private List<Consulta> listaVinculosBP;
    private List<Consulta> listaVinculosCT;
    
    public Historial(){
        
    }

    public ThistorialId getId() {
        return id;
    }

    public void setId(ThistorialId id) {
        this.id = id;
    }

    public Tconocimiento getTconocimiento() {
        return tconocimiento;
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
        return vdescripcion;
    }

    public void setVdescripcion(String vdescripcion) {
        this.vdescripcion = vdescripcion;
    }

    public String getVtitulo() {
        return vtitulo;
    }

    public void setVtitulo(String vtitulo) {
        this.vtitulo = vtitulo;
    }

    public String getVusuariomodificacion() {
        return vusuariomodificacion;
    }

    public void setVusuariomodificacion(String vusuariomodificacion) {
        this.vusuariomodificacion = vusuariomodificacion;
    }

    public String getVusuariocreacion() {
        return vusuariocreacion;
    }

    public void setVusuariocreacion(String vusuariocreacion) {
        this.vusuariocreacion = vusuariocreacion;
    }

    public Date getDfechacreacion() {
        return dfechacreacion;
    }

    public void setDfechacreacion(Date dfechacreacion) {
        this.dfechacreacion = dfechacreacion;
    }

    public BigDecimal getNundejecutora() {
        return nundejecutora;
    }

    public void setNundejecutora(BigDecimal nundejecutora) {
        this.nundejecutora = nundejecutora;
    }

    public String getVruta() {
        return vruta;
    }

    public void setVruta(String vruta) {
        this.vruta = vruta;
    }

    public String getVrango() {
        return vrango;
    }

    public void setVrango(String vrango) {
        this.vrango = vrango;
    }

    public String getVnumero() {
        return vnumero;
    }

    public void setVnumero(String vnumero) {
        this.vnumero = vnumero;
    }

    public String getVtema() {
        return vtema;
    }

    public void setVtema(String vtema) {
        this.vtema = vtema;
    }

    public String getVdesextra() {
        return vdesextra;
    }

    public void setVdesextra(String vdesextra) {
        this.vdesextra = vdesextra;
    }

    public String getVautor() {
        return vautor;
    }

    public void setVautor(String vautor) {
        this.vautor = vautor;
    }

    public String getVfuente() {
        return vfuente;
    }

    public void setVfuente(String vfuente) {
        this.vfuente = vfuente;
    }

    public String getVtipo() {
        return vtipo;
    }

    public void setVtipo(String vtipo) {
        this.vtipo = vtipo;
    }

    public BigDecimal getNnumversion() {
        return nnumversion;
    }

    public void setNnumversion(BigDecimal nnumversion) {
        this.nnumversion = nnumversion;
    }

    public String getVcontenido() {
        return vcontenido;
    }

    public void setVcontenido(String vcontenido) {
        this.vcontenido = vcontenido;
    }

    public BigDecimal getNactivo() {
        return nactivo;
    }

    public void setNactivo(BigDecimal nactivo) {
        this.nactivo = nactivo;
    }

    public BigDecimal getNsituacionid() {
        return nsituacionid;
    }

    public void setNsituacionid(BigDecimal nsituacionid) {
        this.nsituacionid = nsituacionid;
    }

    public BigDecimal getNdias() {
        return ndias;
    }

    public void setNdias(BigDecimal ndias) {
        this.ndias = ndias;
    }

    public String getVobservacion() {
        return vobservacion;
    }

    public void setVobservacion(String vobservacion) {
        this.vobservacion = vobservacion;
    }

    public Date getDfechamodificacion() {
        return dfechamodificacion;
    }

    public void setDfechamodificacion(Date dfechamodificacion) {
        this.dfechamodificacion = dfechamodificacion;
    }

    public BigDecimal getNcategoriaid() {
        return ncategoriaid;
    }

    public void setNcategoriaid(BigDecimal ncategoriaid) {
        this.ncategoriaid = ncategoriaid;
    }

    public String getVfechacreacion() {
        if(dfechacreacion != null) {
            SimpleDateFormat sdf = new SimpleDateFormat(Constante.FORMAT_DATETIME_MEDIUM);
            vfechacreacion = sdf.format(dfechacreacion);
        }
        return vfechacreacion;
    }

    public void setVfechacreacion(String vfechacreacion) {
        this.vfechacreacion = vfechacreacion;
    }

    public String getVfechamodificacion() {
        if(dfechamodificacion != null) {
            SimpleDateFormat sdf = new SimpleDateFormat(Constante.FORMAT_DATETIME_MEDIUM);
            vfechamodificacion = sdf.format(dfechamodificacion);
        }
        return vfechamodificacion;
    }

    public void setVfechamodificacion(String vfechamodificacion) {
        this.vfechamodificacion = vfechamodificacion;
    }

    public String getVnombreusuario() {
        return vnombreusuario;
    }

    public void setVnombreusuario(String vnombreusuario) {
        this.vnombreusuario = vnombreusuario;
    }

    public String getDescripcionHtml() {
        return descripcionHtml;
    }

    public void setDescripcionHtml(String descripcionHtml) {
        this.descripcionHtml = descripcionHtml;
    }

    public List<SeccionHist> getListaSeccionHist() {
        return listaSeccionHist;
    }

    public void setListaSeccionHist(List<SeccionHist> listaSeccionHist) {
        this.listaSeccionHist = listaSeccionHist;
    }

    public List<Consulta> getListaVinculosBL() {
        return listaVinculosBL;
    }

    public void setListaVinculosBL(List<Consulta> listaVinculosBL) {
        this.listaVinculosBL = listaVinculosBL;
    }

    public List<Consulta> getListaVinculosPR() {
        return listaVinculosPR;
    }

    public void setListaVinculosPR(List<Consulta> listaVinculosPR) {
        this.listaVinculosPR = listaVinculosPR;
    }

    public List<Consulta> getListaVinculosWK() {
        return listaVinculosWK;
    }

    public void setListaVinculosWK(List<Consulta> listaVinculosWK) {
        this.listaVinculosWK = listaVinculosWK;
    }

    public List<Consulta> getListaVinculosOM() {
        return listaVinculosOM;
    }

    public void setListaVinculosOM(List<Consulta> listaVinculosOM) {
        this.listaVinculosOM = listaVinculosOM;
    }

    public List<Consulta> getListaVinculosBP() {
        return listaVinculosBP;
    }

    public void setListaVinculosBP(List<Consulta> listaVinculosBP) {
        this.listaVinculosBP = listaVinculosBP;
    }

    public List<Consulta> getListaVinculosCT() {
        return listaVinculosCT;
    }

    public void setListaVinculosCT(List<Consulta> listaVinculosCT) {
        this.listaVinculosCT = listaVinculosCT;
    }
}
