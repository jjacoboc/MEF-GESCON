/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.gob.mef.gescon.web.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import pe.gob.mef.gescon.hibernate.domain.Thistorial;
import pe.gob.mef.gescon.hibernate.domain.TseccionHistId;

/**
 *
 * @author JJacobo
 */
public class SeccionHist implements Serializable{
    
    private TseccionHistId id;
    private Thistorial thistorial;
    private String vtitulo;
    private String vruta;
    private BigDecimal norden;
    private String vusuariocreacion;
    private Date dfechacreacion;
    private String vusuariomodificacion;
    private Date dfechamodificacion;
    
    public SeccionHist() {
        
    }

    public TseccionHistId getId() {
        return id;
    }

    public void setId(TseccionHistId id) {
        this.id = id;
    }

    public Thistorial getThistorial() {
        return thistorial;
    }

    public void setThistorial(Thistorial thistorial) {
        this.thistorial = thistorial;
    }

    public String getVtitulo() {
        return vtitulo;
    }

    public void setVtitulo(String vtitulo) {
        this.vtitulo = vtitulo;
    }

    public String getVruta() {
        return vruta;
    }

    public void setVruta(String vruta) {
        this.vruta = vruta;
    }

    public BigDecimal getNorden() {
        return norden;
    }

    public void setNorden(BigDecimal norden) {
        this.norden = norden;
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
