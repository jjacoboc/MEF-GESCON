/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.gob.mef.gescon.web.bean;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 *
 * @author JJacobo
 */
public class UbigeoEntidad implements Serializable{
    
    private BigDecimal nubigeoid;
    private String cdepartamento;
    private String cprovincia;
    private String cdistrito;
    private String vdescripcion;
 
    
    public UbigeoEntidad(){
        
    }

    public BigDecimal getNubigeoid() {
        return nubigeoid;
    }

    public void setNubigeoid(BigDecimal nubigeoid) {
        this.nubigeoid = nubigeoid;
    }

    /**
     * @return the cdepartamento
     */
    public String getCdepartamento() {
        return cdepartamento;
    }

    /**
     * @param cdepartamento the cdepartamento to set
     */
    public void setCdepartamento(String cdepartamento) {
        this.cdepartamento = cdepartamento;
    }

    /**
     * @return the cprovincia
     */
    public String getCprovincia() {
        return cprovincia;
    }

    /**
     * @param cprovincia the cprovincia to set
     */
    public void setCprovincia(String cprovincia) {
        this.cprovincia = cprovincia;
    }

    /**
     * @return the cdistrito
     */
    public String getCdistrito() {
        return cdistrito;
    }

    /**
     * @param cdistrito the cdistrito to set
     */
    public void setCdistrito(String cdistrito) {
        this.cdistrito = cdistrito;
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

    
}
