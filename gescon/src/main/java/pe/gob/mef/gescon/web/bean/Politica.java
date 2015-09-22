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
public class Politica implements Serializable{
    
    private BigDecimal npoliticaid;
    private String vnombre;
    private String vdescripcion;
    private BigDecimal nactivo;
    private String vusucrea;
    private Date dfechcrea;
    private String vusumod;
    private Date dfechmod;
    
    public void Politica(){
        
    }

    /**
     * @return the nmaestroid
     */
    public BigDecimal getNpoliticaid() {
        return npoliticaid;
    }

    /**
     * @param nmaestroid the nmaestroid to set
     */
    public void setNpoliticaid(BigDecimal npoliticaid) {
        this.npoliticaid = npoliticaid;
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
     * @return the vusucrea
     */
    public String getVusucrea() {
        return vusucrea;
    }

    /**
     * @param vusucrea the vusucrea to set
     */
    public void setVusucrea(String vusucrea) {
        this.vusucrea = vusucrea;
    }

    /**
     * @return the dfechcrea
     */
    public Date getDfechcrea() {
        return dfechcrea;
    }

    /**
     * @param dfechcrea the dfechcrea to set
     */
    public void setDfechcrea(Date dfechcrea) {
        this.dfechcrea = dfechcrea;
    }

    /**
     * @return the vusumod
     */
    public String getVusumod() {
        return vusumod;
    }

    /**
     * @param vusumod the vusumod to set
     */
    public void setVusumod(String vusumod) {
        this.vusumod = vusumod;
    }

    /**
     * @return the dfechmod
     */
    public Date getDfechmod() {
        return dfechmod;
    }

    /**
     * @param dfechmod the dfechmod to set
     */
    public void setDfechmod(Date dfechmod) {
        this.dfechmod = dfechmod;
    }
}
