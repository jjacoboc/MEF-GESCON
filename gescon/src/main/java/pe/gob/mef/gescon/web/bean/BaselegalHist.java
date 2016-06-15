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

/**
 *
 * @author JJacobo
 */
public class BaselegalHist implements Serializable {
    
    private BigDecimal nhistorialid;
    private BigDecimal nbaselegalid;
    private BigDecimal ncategoriaid;
    private String vnombre;
    private String vnumero;
    private BigDecimal ntiporangoid;
    private BigDecimal nrangoid;
    private BigDecimal ngobnacional;
    private BigDecimal ngobregional;
    private BigDecimal ngoblocal;
    private BigDecimal nmancomunidades;
    private String vsumilla;
    private Date dfechavigencia;
    private Date dfechapublicacion;
    private String vtema;
    private BigDecimal nactivo;
    private BigDecimal nestadoid;
    private String vusuariocreacion;
    private Date dfechacreacion;
    private String vusuariomodificacion;
    private Date dfechamodificacion;
    private BigDecimal ndestacado;
    private BigDecimal nversion;
    private String vfechacreacion;
    private String vfechamodificacion;
    private String vnombreusuario;
    private ArchivoHist archivoHist;
    private List<BaseLegal> listaVinculos;
            
    public BaselegalHist(){
        
    }

    public BigDecimal getNhistorialid() {
        return nhistorialid;
    }

    public void setNhistorialid(BigDecimal nhistorialid) {
        this.nhistorialid = nhistorialid;
    }

    public BigDecimal getNbaselegalid() {
        return nbaselegalid;
    }

    public void setNbaselegalid(BigDecimal nbaselegalid) {
        this.nbaselegalid = nbaselegalid;
    }

    public BigDecimal getNcategoriaid() {
        return ncategoriaid;
    }

    public void setNcategoriaid(BigDecimal ncategoriaid) {
        this.ncategoriaid = ncategoriaid;
    }

    public String getVnombre() {
        return vnombre;
    }

    public void setVnombre(String vnombre) {
        this.vnombre = vnombre;
    }

    public String getVnumero() {
        return vnumero;
    }

    public void setVnumero(String vnumero) {
        this.vnumero = vnumero;
    }

    public BigDecimal getNtiporangoid() {
        return ntiporangoid;
    }

    public void setNtiporangoid(BigDecimal ntiporangoid) {
        this.ntiporangoid = ntiporangoid;
    }

    public BigDecimal getNrangoid() {
        return nrangoid;
    }

    public void setNrangoid(BigDecimal nrangoid) {
        this.nrangoid = nrangoid;
    }

    public BigDecimal getNgobnacional() {
        return ngobnacional;
    }

    public void setNgobnacional(BigDecimal ngobnacional) {
        this.ngobnacional = ngobnacional;
    }

    public BigDecimal getNgobregional() {
        return ngobregional;
    }

    public void setNgobregional(BigDecimal ngobregional) {
        this.ngobregional = ngobregional;
    }

    public BigDecimal getNgoblocal() {
        return ngoblocal;
    }

    public void setNgoblocal(BigDecimal ngoblocal) {
        this.ngoblocal = ngoblocal;
    }

    public BigDecimal getNmancomunidades() {
        return nmancomunidades;
    }

    public void setNmancomunidades(BigDecimal nmancomunidades) {
        this.nmancomunidades = nmancomunidades;
    }

    public String getVsumilla() {
        return vsumilla;
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
        return dfechapublicacion;
    }

    public void setDfechapublicacion(Date dfechapublicacion) {
        this.dfechapublicacion = dfechapublicacion;
    }

    public String getVtema() {
        return vtema;
    }

    public void setVtema(String vtema) {
        this.vtema = vtema;
    }

    public BigDecimal getNactivo() {
        return nactivo;
    }

    public void setNactivo(BigDecimal nactivo) {
        this.nactivo = nactivo;
    }

    public BigDecimal getNestadoid() {
        return nestadoid;
    }

    public void setNestadoid(BigDecimal nestadoid) {
        this.nestadoid = nestadoid;
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

    public String getVusuariomodificacion() {
        return vusuariomodificacion;
    }

    public void setVusuariomodificacion(String vusuariomodificacion) {
        this.vusuariomodificacion = vusuariomodificacion;
    }

    public Date getDfechamodificacion() {
        return dfechamodificacion;
    }

    public void setDfechamodificacion(Date dfechamodificacion) {
        this.dfechamodificacion = dfechamodificacion;
    }

    public BigDecimal getNdestacado() {
        return ndestacado;
    }

    public void setNdestacado(BigDecimal ndestacado) {
        this.ndestacado = ndestacado;
    }

    public BigDecimal getNversion() {
        return nversion;
    }

    public void setNversion(BigDecimal nversion) {
        this.nversion = nversion;
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

    public ArchivoHist getArchivoHist() {
        return archivoHist;
    }

    public void setArchivoHist(ArchivoHist archivoHist) {
        this.archivoHist = archivoHist;
    }

    public List<BaseLegal> getListaVinculos() {
        return listaVinculos;
    }

    public void setListaVinculos(List<BaseLegal> listaVinculos) {
        this.listaVinculos = listaVinculos;
    }
}
