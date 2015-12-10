/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.gob.mef.gescon.web.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import org.primefaces.model.StreamedContent;
import pe.gob.mef.gescon.hibernate.domain.Tbaselegal;
import pe.gob.mef.gescon.hibernate.domain.Tconocimiento;

/**
 *
 * @author JJacobo
 */
public class RespuestaHist implements Serializable {

    private BigDecimal nhistorialid;
    private BigDecimal npreguntaid;
    private String vrespuesta;
    private String vusuariocreacion;
    private Date dfechacreacion;
    private String vusuariomodificacion;
    private Date dfechamodificacion;

    public RespuestaHist() {

    }

    /**
     * @return the nhistorialid
     */
    public BigDecimal getNhistorialid() {
        return nhistorialid;
    }

    /**
     * @param nhistorialid the nhistorialid to set
     */
    public void setNhistorialid(BigDecimal nhistorialid) {
        this.nhistorialid = nhistorialid;
    }

    /**
     * @return the npreguntaid
     */
    public BigDecimal getNpreguntaid() {
        return npreguntaid;
    }

    /**
     * @param npreguntaid the npreguntaid to set
     */
    public void setNpreguntaid(BigDecimal npreguntaid) {
        this.npreguntaid = npreguntaid;
    }

    /**
     * @return the vrespuesta
     */
    public String getVrespuesta() {
        return vrespuesta;
    }

    /**
     * @param vrespuesta the vrespuesta to set
     */
    public void setVrespuesta(String vrespuesta) {
        this.vrespuesta = vrespuesta;
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
