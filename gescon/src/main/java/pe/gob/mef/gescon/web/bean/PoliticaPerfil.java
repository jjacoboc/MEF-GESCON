/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.gob.mef.gescon.web.bean;

import java.io.Serializable;
import java.util.Date;
import pe.gob.mef.gescon.hibernate.domain.Mtperfil;
import pe.gob.mef.gescon.hibernate.domain.Mtpolitica;
import pe.gob.mef.gescon.hibernate.domain.TpoliticaPerfilId;

/**
 *
 * @author JJacobo
 */
public class PoliticaPerfil implements Serializable {

    private TpoliticaPerfilId id;
    private Mtperfil mtperfil;
    private Mtpolitica mtpolitica;
    private String vusuariomodificacion;
    private String vusuariocreacion;
    private Date dfechacreacion;
    private Date dfechamodificacion;

    public PoliticaPerfil() {

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

    public Date getDfechamodificacion() {
        return dfechamodificacion;
    }

    public void setDfechamodificacion(Date dfechamodificacion) {
        this.dfechamodificacion = dfechamodificacion;
    }

}
