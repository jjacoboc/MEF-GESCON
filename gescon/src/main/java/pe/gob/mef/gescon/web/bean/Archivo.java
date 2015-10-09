/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.gob.mef.gescon.web.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import pe.gob.mef.gescon.hibernate.domain.TarchivoId;
import pe.gob.mef.gescon.hibernate.domain.Tbaselegal;

/**
 *
 * @author JJacobo
 */
public class Archivo implements Serializable{
    
    private TarchivoId id;
    private Tbaselegal tbaselegal;
    private String vnombre;
    private String vruta;
    private BigDecimal nversion;
    private String vusuariocreacion;
    private Date dfechacreacion;
    private String vusuariomodificacion;
    private Date dfechamodificacion;
    
    public Archivo(){
        
    }

    /**
     * @return the id
     */
    public TarchivoId getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(TarchivoId id) {
        this.id = id;
    }

    /**
     * @return the tbaselegal
     */
    public Tbaselegal getTbaselegal() {
        return tbaselegal;
    }

    /**
     * @param tbaselegal the tbaselegal to set
     */
    public void setTbaselegal(Tbaselegal tbaselegal) {
        this.tbaselegal = tbaselegal;
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
     * @return the vruta
     */
    public String getVruta() {
        return vruta;
    }

    /**
     * @param vruta the vruta to set
     */
    public void setVruta(String vruta) {
        this.vruta = vruta;
    }

    /**
     * @return the nversion
     */
    public BigDecimal getNversion() {
        return nversion;
    }

    /**
     * @param nversion the nversion to set
     */
    public void setNversion(BigDecimal nversion) {
        this.nversion = nversion;
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
}
