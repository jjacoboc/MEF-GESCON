/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.gob.mef.gescon.web.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import pe.gob.mef.gescon.hibernate.domain.Mtuser;
import pe.gob.mef.gescon.hibernate.domain.TpassId;

/**
 *
 * @author JJacobo
 */
public class Pass implements Serializable{
    
     private TpassId id;
     private Mtuser mtuser;
     private String vclave;
     private BigDecimal nusuariocreacion;
     private Date dfechacreacion;

    
    public Pass(){
        
    }

    /**
     * @return the id
     */
    public TpassId getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(TpassId id) {
        this.id = id;
    }

    /**
     * @return the mtuser
     */
    public Mtuser getMtuser() {
        return mtuser;
    }

    /**
     * @param mtuser the mtuser to set
     */
    public void setMtuser(Mtuser mtuser) {
        this.mtuser = mtuser;
    }

    /**
     * @return the vclave
     */
    public String getVclave() {
        return vclave;
    }

    /**
     * @param vclave the vclave to set
     */
    public void setVclave(String vclave) {
        this.vclave = vclave;
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
