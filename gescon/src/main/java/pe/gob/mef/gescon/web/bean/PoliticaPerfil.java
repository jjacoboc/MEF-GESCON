/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.gob.mef.gescon.web.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import pe.gob.mef.gescon.hibernate.domain.Mtperfil;
import pe.gob.mef.gescon.hibernate.domain.Mtpolitica;
import pe.gob.mef.gescon.hibernate.domain.TpoliticaPerfilId;

/**
 *
 * @author JJacobo
 */
public class PoliticaPerfil implements Serializable{
    
     private TpoliticaPerfilId id;
     private Mtperfil mtperfil;
     private Mtpolitica mtpolitica;
     private BigDecimal nusuariomod;
     private BigDecimal nusuariocreacion;
     private Date dfechacreacion;

    
    public PoliticaPerfil(){
        
    }

    /**
     * @return the id
     */
    public TpoliticaPerfilId getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(TpoliticaPerfilId id) {
        this.id = id;
    }

    /**
     * @return the mtperfil
     */
    public Mtperfil getMtperfil() {
        return mtperfil;
    }

    /**
     * @param mtperfil the mtperfil to set
     */
    public void setMtperfil(Mtperfil mtperfil) {
        this.mtperfil = mtperfil;
    }

    /**
     * @return the mtpolitica
     */
    public Mtpolitica getMtpolitica() {
        return mtpolitica;
    }

    /**
     * @param mtpolitica the mtpolitica to set
     */
    public void setMtpolitica(Mtpolitica mtpolitica) {
        this.mtpolitica = mtpolitica;
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
