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
public class User implements Serializable{
    
    private BigDecimal nusuarioid;
    private String vlogin;
    private String vnombres;
    private String vapellidos;
    private String vcorreo;
    private Character nestado;
    private BigDecimal nusuariomod;
    private BigDecimal nusuariocreacion;
    private Date dfechacreacion;
    
    public User() {
        
    }

    /**
     * @return the nusuarioid
     */
    public BigDecimal getNusuarioid() {
        return nusuarioid;
    }

    /**
     * @param nusuarioid the nusuarioid to set
     */
    public void setNusuarioid(BigDecimal nusuarioid) {
        this.nusuarioid = nusuarioid;
    }

    /**
     * @return the vlogin
     */
    public String getVlogin() {
        return vlogin;
    }

    /**
     * @param vlogin the vlogin to set
     */
    public void setVlogin(String vlogin) {
        this.vlogin = vlogin;
    }

    /**
     * @return the vnombres
     */
    public String getVnombres() {
        return vnombres;
    }

    /**
     * @param vnombres the vnombres to set
     */
    public void setVnombres(String vnombres) {
        this.vnombres = vnombres;
    }

    /**
     * @return the vapellidos
     */
    public String getVapellidos() {
        return vapellidos;
    }

    /**
     * @param vapellidos the vapellidos to set
     */
    public void setVapellidos(String vapellidos) {
        this.vapellidos = vapellidos;
    }

    /**
     * @return the vcorreo
     */
    public String getVcorreo() {
        return vcorreo;
    }

    /**
     * @param vcorreo the vcorreo to set
     */
    public void setVcorreo(String vcorreo) {
        this.vcorreo = vcorreo;
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
}
