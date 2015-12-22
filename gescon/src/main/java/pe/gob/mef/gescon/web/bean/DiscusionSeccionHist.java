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
public class DiscusionSeccionHist implements Serializable{
    
    private BigDecimal ndiscusionseccionhid;
    private BigDecimal ndiscusionhid;
    private String vtitulo;
    private BigDecimal ntipodiscusion;
    private String vruta;
    private String vusuariocreacion;
    private Serializable dfechacreacion;
    private String vusuariomodificacion;
    private Serializable dfechamodificacion;
    
    public DiscusionSeccionHist() {
        
    }

    public BigDecimal getNdiscusionseccionhid() {
        return ndiscusionseccionhid;
    }

    public void setNdiscusionseccionhid(BigDecimal ndiscusionseccionhid) {
        this.ndiscusionseccionhid = ndiscusionseccionhid;
    }

    public BigDecimal getNdiscusionhid() {
        return ndiscusionhid;
    }

    public void setNdiscusionhid(BigDecimal ndiscusionhid) {
        this.ndiscusionhid = ndiscusionhid;
    }

    public String getVtitulo() {
        return vtitulo;
    }

    public void setVtitulo(String vtitulo) {
        this.vtitulo = vtitulo;
    }

    public BigDecimal getNtipodiscusion() {
        return ntipodiscusion;
    }

    public void setNtipodiscusion(BigDecimal ntipodiscusion) {
        this.ntipodiscusion = ntipodiscusion;
    }

    public String getVruta() {
        return vruta;
    }

    public void setVruta(String vruta) {
        this.vruta = vruta;
    }

    public String getVusuariocreacion() {
        return vusuariocreacion;
    }

    public void setVusuariocreacion(String vusuariocreacion) {
        this.vusuariocreacion = vusuariocreacion;
    }

    public Serializable getDfechacreacion() {
        return dfechacreacion;
    }

    public void setDfechacreacion(Serializable dfechacreacion) {
        this.dfechacreacion = dfechacreacion;
    }

    public String getVusuariomodificacion() {
        return vusuariomodificacion;
    }

    public void setVusuariomodificacion(String vusuariomodificacion) {
        this.vusuariomodificacion = vusuariomodificacion;
    }

    public Serializable getDfechamodificacion() {
        return dfechamodificacion;
    }

    public void setDfechamodificacion(Serializable dfechamodificacion) {
        this.dfechamodificacion = dfechamodificacion;
    }
}
