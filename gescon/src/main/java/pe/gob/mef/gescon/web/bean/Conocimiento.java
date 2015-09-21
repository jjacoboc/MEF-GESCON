/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.gob.mef.gescon.web.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import pe.gob.mef.gescon.hibernate.domain.MttipoConocimiento;
import pe.gob.mef.gescon.hibernate.domain.TconocimientoId;

/**
 *
 * @author JJacobo
 */
public class Conocimiento implements Serializable{

    private TconocimientoId id;
    private MttipoConocimiento mttipoConocimiento;
    private String vdescripcion;
    private String vtitulo;
    private BigDecimal nusuariomod;
    private BigDecimal nusuariocreacion;
    private Date dfechacreacion;
    private BigDecimal nundejecutora;
    private String varchivo;
    private String vrango;
    private String vnumero;
    private String vtema;
    private String vdesextra;
    private String vautor;
    private String vfuente;
    private String vtipo;
    
    public Conocimiento() {
        
    }

    /**
     * @return the id
     */
    public TconocimientoId getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(TconocimientoId id) {
        this.id = id;
    }

    /**
     * @return the mttipoConocimiento
     */
    public MttipoConocimiento getMttipoConocimiento() {
        return mttipoConocimiento;
    }

    /**
     * @param mttipoConocimiento the mttipoConocimiento to set
     */
    public void setMttipoConocimiento(MttipoConocimiento mttipoConocimiento) {
        this.mttipoConocimiento = mttipoConocimiento;
    }

    /**
     * @return the vdescripcion
     */
    public String getVdescripcion() {
        return vdescripcion;
    }

    /**
     * @param vdescripcion the vdescripcion to set
     */
    public void setVdescripcion(String vdescripcion) {
        this.vdescripcion = vdescripcion;
    }

    /**
     * @return the vtitulo
     */
    public String getVtitulo() {
        return vtitulo;
    }

    /**
     * @param vtitulo the vtitulo to set
     */
    public void setVtitulo(String vtitulo) {
        this.vtitulo = vtitulo;
    }

    /**
     * @return the nusuariomod
     */
    public BigDecimal getNusuariomod() {
        return nusuariomod;
    }

    /**
     * @param nusuariomod the nusuariomod to set
     */
    public void setNusuariomod(BigDecimal nusuariomod) {
        this.nusuariomod = nusuariomod;
    }

    /**
     * @return the nusuariocreacion
     */
    public BigDecimal getNusuariocreacion() {
        return nusuariocreacion;
    }

    /**
     * @param nusuariocreacion the nusuariocreacion to set
     */
    public void setNusuariocreacion(BigDecimal nusuariocreacion) {
        this.nusuariocreacion = nusuariocreacion;
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
     * @return the nundejecutora
     */
    public BigDecimal getNundejecutora() {
        return nundejecutora;
    }

    /**
     * @param nundejecutora the nundejecutora to set
     */
    public void setNundejecutora(BigDecimal nundejecutora) {
        this.nundejecutora = nundejecutora;
    }

    /**
     * @return the varchivo
     */
    public String getVarchivo() {
        return varchivo;
    }

    /**
     * @param varchivo the varchivo to set
     */
    public void setVarchivo(String varchivo) {
        this.varchivo = varchivo;
    }

    /**
     * @return the vrango
     */
    public String getVrango() {
        return vrango;
    }

    /**
     * @param vrango the vrango to set
     */
    public void setVrango(String vrango) {
        this.vrango = vrango;
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
     * @return the vdesextra
     */
    public String getVdesextra() {
        return vdesextra;
    }

    /**
     * @param vdesextra the vdesextra to set
     */
    public void setVdesextra(String vdesextra) {
        this.vdesextra = vdesextra;
    }

    /**
     * @return the vautor
     */
    public String getVautor() {
        return vautor;
    }

    /**
     * @param vautor the vautor to set
     */
    public void setVautor(String vautor) {
        this.vautor = vautor;
    }

    /**
     * @return the vfuente
     */
    public String getVfuente() {
        return vfuente;
    }

    /**
     * @param vfuente the vfuente to set
     */
    public void setVfuente(String vfuente) {
        this.vfuente = vfuente;
    }

    /**
     * @return the vtipo
     */
    public String getVtipo() {
        return vtipo;
    }

    /**
     * @param vtipo the vtipo to set
     */
    public void setVtipo(String vtipo) {
        this.vtipo = vtipo;
    }
        
}
