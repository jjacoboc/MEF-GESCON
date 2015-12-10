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
import pe.gob.mef.gescon.hibernate.domain.TvinculoHistId;

/**
 *
 * @author JJacobo
 */
public class VinculoHist implements Serializable{
    
    private TvinculoHistId id;
    private Thistorial thistorial;
    private BigDecimal nconocimientovinc;
    private String vusuariomodificacion;
    private String vusuariocreacion;
    private Date dfechacreacion;
    private Date dfechamodificacion;
    private BigDecimal ntipoconocimientovinc;
    
    public VinculoHist() {
        
    }

    public TvinculoHistId getId() {
        return id;
    }

    public void setId(TvinculoHistId id) {
        this.id = id;
    }

    public Thistorial getThistorial() {
        return thistorial;
    }

    public void setThistorial(Thistorial thistorial) {
        this.thistorial = thistorial;
    }

    public BigDecimal getNconocimientovinc() {
        return nconocimientovinc;
    }

    public void setNconocimientovinc(BigDecimal nconocimientovinc) {
        this.nconocimientovinc = nconocimientovinc;
    }

    public String getVusuariomodificacion() {
        return vusuariomodificacion;
    }

    public void setVusuariomodificacion(String vusuariomodificacion) {
        this.vusuariomodificacion = vusuariomodificacion;
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

    public Date getDfechamodificacion() {
        return dfechamodificacion;
    }

    public void setDfechamodificacion(Date dfechamodificacion) {
        this.dfechamodificacion = dfechamodificacion;
    }

    public BigDecimal getNtipoconocimientovinc() {
        return ntipoconocimientovinc;
    }

    public void setNtipoconocimientovinc(BigDecimal ntipoconocimientovinc) {
        this.ntipoconocimientovinc = ntipoconocimientovinc;
    }
}
