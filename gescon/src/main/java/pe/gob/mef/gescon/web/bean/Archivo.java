/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.gob.mef.gescon.web.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import pe.gob.mef.gescon.hibernate.domain.Tbaselegal;

/**
 *
 * @author JJacobo
 */
public class Archivo implements Serializable{
    
    private BigDecimal narchivoid;
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

    public BigDecimal getNarchivoid() {
        return narchivoid;
    }

    public void setNarchivoid(BigDecimal narchivoid) {
        this.narchivoid = narchivoid;
    }

    public Tbaselegal getTbaselegal() {
        return tbaselegal;
    }

    public void setTbaselegal(Tbaselegal tbaselegal) {
        this.tbaselegal = tbaselegal;
    }

    public String getVnombre() {
        return vnombre;
    }

    public void setVnombre(String vnombre) {
        this.vnombre = vnombre;
    }

    public String getVruta() {
        return vruta;
    }

    public void setVruta(String vruta) {
        this.vruta = vruta;
    }

    public BigDecimal getNversion() {
        return nversion;
    }

    public void setNversion(BigDecimal nversion) {
        this.nversion = nversion;
    }

    public String getVusuariocreacion() {
        return vusuariocreacion;
    }

    public void setVusuariocreacion(String vusuariocreacion) {
        this.vusuariocreacion = vusuariocreacion;
    }

    public Date getDfechacreacion() {
        return dfechacreacion;
    }

    public void setDfechacreacion(Date dfechacreacion) {
        this.dfechacreacion = dfechacreacion;
    }

    public String getVusuariomodificacion() {
        return vusuariomodificacion;
    }

    public void setVusuariomodificacion(String vusuariomodificacion) {
        this.vusuariomodificacion = vusuariomodificacion;
    }

    public Date getDfechamodificacion() {
        return dfechamodificacion;
    }

    public void setDfechamodificacion(Date dfechamodificacion) {
        this.dfechamodificacion = dfechamodificacion;
    }
}
