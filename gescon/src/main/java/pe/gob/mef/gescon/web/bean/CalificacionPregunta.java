/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.gob.mef.gescon.web.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import pe.gob.mef.gescon.util.DateUtils;

/**
 *
 * @author JJacobo
 */
public class CalificacionPregunta implements Serializable{
    
    private BigDecimal ncalificacionid;
    private BigDecimal ncalificacion;
    private String vcomentario;
    private String vusuariocreacion;
    private Date dfechacreacion;
    private String vusuariomodificacion;
    private Date dfechamodificacion;
    private BigDecimal npreguntaid;
    private String usuarioNombre;
    private String elapsedTime;
    
    public CalificacionPregunta(){
        
    }

    public BigDecimal getNcalificacionid() {
        return ncalificacionid;
    }

    public void setNcalificacionid(BigDecimal ncalificacionid) {
        this.ncalificacionid = ncalificacionid;
    }

    public BigDecimal getNcalificacion() {
        return ncalificacion;
    }

    public void setNcalificacion(BigDecimal ncalificacion) {
        this.ncalificacion = ncalificacion;
    }

    public String getVcomentario() {
        return vcomentario;
    }

    public void setVcomentario(String vcomentario) {
        this.vcomentario = vcomentario;
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

    public BigDecimal getNpreguntaid() {
        return npreguntaid;
    }

    public void setNpreguntaid(BigDecimal npreguntaid) {
        this.npreguntaid = npreguntaid;
    }

    public String getUsuarioNombre() {
        return usuarioNombre;
    }

    public void setUsuarioNombre(String usuarioNombre) {
        this.usuarioNombre = usuarioNombre;
    }

    public String getElapsedTime() {
        long time;
        if (this.dfechacreacion != null) {
            time = DateUtils.getDifferenceYears(this.dfechacreacion, new Date());
            if (time > 0) {
                this.elapsedTime = "hace " + time + " años";
            } else {
                time = DateUtils.getDifferenceMonths(this.dfechacreacion, new Date());
                if (time > 0) {
                    this.elapsedTime = "hace " + time + " meses";
                } else {
                    time = DateUtils.getDifferenceDays(this.dfechacreacion, new Date());
                    if (time > 0) {
                        this.elapsedTime = "hace " + time + " días";
                    } else {
                        time = DateUtils.getDifferenceHours(this.dfechacreacion, new Date());
                        if (time > 0) {
                            this.elapsedTime = "hace " + time + " horas";
                        } else {
                            time = DateUtils.getDifferenceMinutes(this.dfechacreacion, new Date());
                            if (time > 0) {
                                this.elapsedTime = "hace " + time + " minutos";
                            }
                        }
                    }
                }
            }
        } else {
            this.elapsedTime = "";
        }
        return elapsedTime;
    }

    public void setElapsedTime(String elapsedTime) {
        this.elapsedTime = elapsedTime;
    }
}
