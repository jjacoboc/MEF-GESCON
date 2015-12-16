/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.gob.mef.gescon.web.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author JJacobo
 */

public class Pregunta implements Serializable {

    private BigDecimal npreguntaid;
    private String vasunto;
    private BigDecimal ncategoriaid;
    private String vdetalle;
    private BigDecimal nentidadid;
    private String vdatoadicional;
    private String vusuariocreacion;
    private String vusuariomodificacion;
    private Date dfechacreacion;
    private Date dfechamodificacion;
    private BigDecimal nactivo;
    private String vrespuesta;
    private String vmsjusuario2;
    private String vmsjespecialista;
    private BigDecimal nsituacionid;
    private String vmsjmoderador;
    private String vmsjusuario1;
    private BigDecimal ndestacado;
    private Date dfechapublicacion;

    public void Pregunta() {

    }

    /**
     * @return the npreguntaid
     */
    public BigDecimal getNpreguntaid() {
        return npreguntaid;
    }

    /**
     * @param npreguntaid the npreguntaid to set
     */
    public void setNpreguntaid(BigDecimal npreguntaid) {
        this.npreguntaid = npreguntaid;
    }

    /**
     * @return the vasunto
     */
    public String getVasunto() {
        return vasunto;
    }

    /**
     * @param vasunto the vasunto to set
     */
    public void setVasunto(String vasunto) {
        this.vasunto = vasunto;
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
     * @return the vdetalle
     */
    public String getVdetalle() {
        return vdetalle;
    }

    /**
     * @param vdetalle the vdetalle to set
     */
    public void setVdetalle(String vdetalle) {
        this.vdetalle = vdetalle;
    }

    /**
     * @return the nentidadid
     */
    public BigDecimal getNentidadid() {
        return nentidadid;
    }

    /**
     * @param nentidadid the nentidadid to set
     */
    public void setNentidadid(BigDecimal nentidadid) {
        this.nentidadid = nentidadid;
    }

    /**
     * @return the vdatoadicional
     */
    public String getVdatoadicional() {
        return vdatoadicional;
    }

    /**
     * @param vdatoadicional the vdatoadicional to set
     */
    public void setVdatoadicional(String vdatoadicional) {
        this.vdatoadicional = vdatoadicional;
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
     * @return the vrespuesta
     */
    public String getVrespuesta() {
        return vrespuesta;
    }

    /**
     * @param vrespuesta the vrespuesta to set
     */
    public void setVrespuesta(String vrespuesta) {
        this.vrespuesta = vrespuesta;
    }

    /**
     * @return the vmsjusuario2
     */
    public String getVmsjusuario2() {
        return vmsjusuario2;
    }

    /**
     * @param vmsjusuario2 the vmsjusuario2 to set
     */
    public void setVmsjusuario2(String vmsjusuario2) {
        this.vmsjusuario2 = vmsjusuario2;
    }

    /**
     * @return the vmsjespecialista
     */
    public String getVmsjespecialista() {
        return vmsjespecialista;
    }

    /**
     * @param vmsjespecialista the vmsjespecialista to set
     */
    public void setVmsjespecialista(String vmsjespecialista) {
        this.vmsjespecialista = vmsjespecialista;
    }

    /**
     * @return the nsituacionid
     */
    public BigDecimal getNsituacionid() {
        return nsituacionid;
    }

    /**
     * @param nsituacionid the nsituacionid to set
     */
    public void setNsituacionid(BigDecimal nsituacionid) {
        this.nsituacionid = nsituacionid;
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

    /**
     * @return the vmsjusuario1
     */
    public String getVmsjusuario1() {
        return vmsjusuario1;
    }

    /**
     * @param vmsjusuario1 the vmsjusuario1 to set
     */
    public void setVmsjusuario1(String vmsjusuario1) {
        this.vmsjusuario1 = vmsjusuario1;
    }

    public BigDecimal getNdestacado() {
        return ndestacado;
    }

    public void setNdestacado(BigDecimal ndestacado) {
        this.ndestacado = ndestacado;
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
}
