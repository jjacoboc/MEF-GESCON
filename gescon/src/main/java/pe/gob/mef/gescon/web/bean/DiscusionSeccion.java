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
public class DiscusionSeccion implements Serializable {
    
    private BigDecimal ndiscusionseccionid;
    private BigDecimal ndiscusionid;
    private String vtitulo;
    private BigDecimal ntipodiscusion;
    private String vruta;
    private String vusuariocreacion;
    private Date dfechacreacion;
    private String vusuariomodificacion;
    private Date dfechamodificacion;
    private String discusionHtml;
    private String discusionPlain;
    private String usuarioNombre;
    private String elapsedTime;
    
    public DiscusionSeccion() {
        
    }

    public BigDecimal getNdiscusionseccionid() {
        return ndiscusionseccionid;
    }

    public void setNdiscusionseccionid(BigDecimal ndiscusionseccionid) {
        this.ndiscusionseccionid = ndiscusionseccionid;
    }

    public BigDecimal getNdiscusionid() {
        return ndiscusionid;
    }

    public void setNdiscusionid(BigDecimal ndiscusionid) {
        this.ndiscusionid = ndiscusionid;
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

    public String getDiscusionHtml() {
        return discusionHtml;
    }

    public void setDiscusionHtml(String discusionHtml) {
        this.discusionHtml = discusionHtml;
    }

    public String getDiscusionPlain() {
        return discusionPlain;
    }

    public void setDiscusionPlain(String discusionPlain) {
        this.discusionPlain = discusionPlain;
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
