/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.gob.mef.gescon.web.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import pe.gob.mef.gescon.common.Constante;

/**
 *
 * @author JJacobo
 */
public class BaseLegal implements Serializable, Comparable<BaseLegal>{
    
    private BigDecimal nbaselegalid;
    private BigDecimal ntiporangoid;
    private BigDecimal nrangoid;
    private String vnombre;
    private BigDecimal ncategoriaid;
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
    private BigDecimal nestadoid;
    private String vmsjusuariocreacion;
    private String vmsjmoderador;
    private BigDecimal ndestacado;
    private Archivo archivo;
    private List<Archivo> listaArchivo;
    private String vfechavigencia;
    private String vfechapublicacion;
    private String vfechacreacion;
    private String vfechamodificacion;
    
    public void BaseLegal(){
        
    }

    /**
     * @return the nbaselegalid
     */
    public BigDecimal getNbaselegalid() {
        return nbaselegalid;
    }

    /**
     * @param nbaselegalid the nbaselegalid to set
     */
    public void setNbaselegalid(BigDecimal nbaselegalid) {
        this.nbaselegalid = nbaselegalid;
    }

    public BigDecimal getNtiporangoid() {
        return ntiporangoid;
    }

    public void setNtiporangoid(BigDecimal ntiporangoid) {
        this.ntiporangoid = ntiporangoid;
    }

    /**
     * @return the nrangoid
     */
    public BigDecimal getNrangoid() {
        return nrangoid;
    }

    /**
     * @param nrangoid the nrangoid to set
     */
    public void setNrangoid(BigDecimal nrangoid) {
        this.nrangoid = nrangoid;
    }

    /**
     * @return the vnombre
     */
    public String getVnombre() {
        return vnombre;
    }

    /**
     * @param vnombre the vnombre to set
     */
    public void setVnombre(String vnombre) {
        this.vnombre = vnombre;
    }

    /**
     * @return the ncategoriaid
     */
    public BigDecimal getNcategoriaid() {
        return ncategoriaid;
    }

    /**
     * @param ncategoriaid the ncategoriaid to set
     */
    public void setNcategoriaid(BigDecimal ncategoriaid) {
        this.ncategoriaid = ncategoriaid;
    }

    /**
     * @return the vnumero
     */
    public String getVnumero() {
        return vnumero;
    }

    /**
     * @param vnumero the vnumero to set
     */
    public void setVnumero(String vnumero) {
        this.vnumero = vnumero;
    }

    /**
     * @return the vsumilla
     */
    public String getVsumilla() {
        return vsumilla;
    }

    /**
     * @param vsumilla the vsumilla to set
     */
    public void setVsumilla(String vsumilla) {
        this.vsumilla = vsumilla;
    }

    public Date getDfechavigencia() {
        return dfechavigencia;
    }

    public void setDfechavigencia(Date dfechavigencia) {
        this.dfechavigencia = dfechavigencia;
    }

    /**
     * @return the dfechapublicacion
     */
    public Date getDfechapublicacion() {
        return dfechapublicacion;
    }

    /**
     * @param dfechapublicacion the dfechapublicacion to set
     */
    public void setDfechapublicacion(Date dfechapublicacion) {
        this.dfechapublicacion = dfechapublicacion;
    }

    /**
     * @return the vtema
     */
    public String getVtema() {
        return vtema;
    }

    /**
     * @param vtema the vtema to set
     */
    public void setVtema(String vtema) {
        this.vtema = vtema;
    }

    /**
     * @return the vusuariocreacion
     */
    public String getVusuariocreacion() {
        return vusuariocreacion;
    }

    /**
     * @param vusuariocreacion the vusuariocreacion to set
     */
    public void setVusuariocreacion(String vusuariocreacion) {
        this.vusuariocreacion = vusuariocreacion;
    }

    /**
     * @return the vusuariomodificacion
     */
    public String getVusuariomodificacion() {
        return vusuariomodificacion;
    }

    /**
     * @param vusuariomodificacion the vusuariomodificacion to set
     */
    public void setVusuariomodificacion(String vusuariomodificacion) {
        this.vusuariomodificacion = vusuariomodificacion;
    }

    /**
     * @return the dfechacreacion
     */
    public Date getDfechacreacion() {
        return dfechacreacion;
    }

    /**
     * @param dfechacreacion the dfechacreacion to set
     */
    public void setDfechacreacion(Date dfechacreacion) {
        this.dfechacreacion = dfechacreacion;
    }

    /**
     * @return the dfechamodificacion
     */
    public Date getDfechamodificacion() {
        return dfechamodificacion;
    }

    /**
     * @param dfechamodificacion the dfechamodificacion to set
     */
    public void setDfechamodificacion(Date dfechamodificacion) {
        this.dfechamodificacion = dfechamodificacion;
    }

    /**
     * @return the ngobnacional
     */
    public BigDecimal getNgobnacional() {
        return ngobnacional;
    }

    /**
     * @param ngobnacional the ngobnacional to set
     */
    public void setNgobnacional(BigDecimal ngobnacional) {
        this.ngobnacional = ngobnacional;
    }

    /**
     * @return the ngobregional
     */
    public BigDecimal getNgobregional() {
        return ngobregional;
    }

    /**
     * @param ngobregional the ngobregional to set
     */
    public void setNgobregional(BigDecimal ngobregional) {
        this.ngobregional = ngobregional;
    }

    /**
     * @return the ngoblocal
     */
    public BigDecimal getNgoblocal() {
        return ngoblocal;
    }

    /**
     * @param ngoblocal the ngoblocal to set
     */
    public void setNgoblocal(BigDecimal ngoblocal) {
        this.ngoblocal = ngoblocal;
    }

    /**
     * @return the nmancomunidades
     */
    public BigDecimal getNmancomunidades() {
        return nmancomunidades;
    }

    /**
     * @param nmancomunidades the nmancomunidades to set
     */
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

    /**
     * @return the nestadoid
     */
    public BigDecimal getNestadoid() {
        return nestadoid;
    }

    /**
     * @param nestadoid the nestadoid to set
     */
    public void setNestadoid(BigDecimal nestadoid) {
        this.nestadoid = nestadoid;
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

    /**
     * @return the archivo
     */
    public Archivo getArchivo() {
        return archivo;
    }

    /**
     * @param archivo the archivo to set
     */
    public void setArchivo(Archivo archivo) {
        this.archivo = archivo;
    }

    /**
     * @return the listaArchivo
     */
    public List<Archivo> getListaArchivo() {
        return listaArchivo;
    }

    /**
     * @param listaArchivo the listaArchivo to set
     */
    public void setListaArchivo(List<Archivo> listaArchivo) {
        this.listaArchivo = listaArchivo;
    }

    public String getVfechavigencia() {
        if(dfechavigencia != null) {
            SimpleDateFormat sdf = new SimpleDateFormat(Constante.FORMAT_DATE_SHORT);
            vfechavigencia = sdf.format(dfechavigencia);
        }
        return vfechavigencia;
    }

    public void setVfechavigencia(String vfechavigencia) {
        this.vfechavigencia = vfechavigencia;
    }

    public String getVfechapublicacion() {
        if(dfechapublicacion != null) {
            SimpleDateFormat sdf = new SimpleDateFormat(Constante.FORMAT_DATE_SHORT);
            vfechapublicacion = sdf.format(dfechapublicacion);
        }
        return vfechapublicacion;
    }

    public void setVfechapublicacion(String vfechapublicacion) {
        this.vfechapublicacion = vfechapublicacion;
    }

    public String getVfechacreacion() {
        if(dfechacreacion != null) {
            SimpleDateFormat sdf = new SimpleDateFormat(Constante.FORMAT_DATETIME_SHORT);
            vfechacreacion = sdf.format(dfechacreacion);
        }
        return vfechacreacion;
    }

    public void setVfechacreacion(String vfechacreacion) {
        this.vfechacreacion = vfechacreacion;
    }

    public String getVfechamodificacion() {
        if(dfechamodificacion != null) {
            SimpleDateFormat sdf = new SimpleDateFormat(Constante.FORMAT_DATETIME_SHORT);
            vfechamodificacion = sdf.format(dfechamodificacion);
        }
        return vfechamodificacion;
    }

    public void setVfechamodificacion(String vfechamodificacion) {
        this.vfechamodificacion = vfechamodificacion;
    }
 
    @Override
    public int compareTo(BaseLegal o) {
        return Comparators.ID.compare(this, o);
    }
    
    public static class Comparators {
        public static Comparator<BaseLegal> ID = new Comparator<BaseLegal>() {
            @Override
            public int compare(BaseLegal o1, BaseLegal o2) {
                return o1.getNbaselegalid().intValue() - o2.getNbaselegalid().intValue();
            }
        };
    }
}
