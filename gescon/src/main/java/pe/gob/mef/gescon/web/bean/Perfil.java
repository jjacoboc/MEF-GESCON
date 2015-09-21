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
public class Perfil implements Serializable{
    
    private BigDecimal nperfilid;
    private String vdescripcion;
    private Character nestado;
    private BigDecimal nusuariomod;
    private BigDecimal nusuariocreacion;
    private Date dfechacreacion;
    private String imagen;
    
    public Perfil(){
        
    }

    /**
     * @return the nperfilid
     */
    public BigDecimal getNperfilid() {
        return nperfilid;
    }

    /**
     * @param nperfilid the nperfilid to set
     */
    public void setNperfilid(BigDecimal nperfilid) {
        this.nperfilid = nperfilid;
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
     * @return the nestado
     */
    public Character getNestado() {
        return nestado;
    }

    /**
     * @param nestado the nestado to set
     */
    public void setNestado(Character nestado) {
        this.nestado = nestado;
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
     * @return the imagen
     */
    public String getImagen() {
        return imagen;
    }

    /**
     * @param imagen the imagen to set
     */
    public void setImagen(String imagen) {
        this.imagen = imagen;
    }
}
