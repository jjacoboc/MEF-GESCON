/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.gob.mef.gescon.web.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import pe.gob.mef.gescon.common.Constante;
import pe.gob.mef.gescon.hibernate.domain.TmaestrodetalleId;

/**
 *
 * @author JJacobo
 */
public class MaestroDetalle implements Serializable {

    private TmaestrodetalleId id;
    private BigDecimal nmaestroid;
    private BigDecimal ndetalleid;
    private String vnombre;
    private String vdescripcion;
    private String vusuariocreacion;
    private Date dfechacreacion;
    private String vusuariomodificacion;
    private Date dfechamodificacion;
    private BigDecimal nactivo;
    private String vfechacreacion;
    private String vfechamodificacion;
    
    public void MaestroDetalle(){
        
    }

    /**
     * @return the id
     */
    public TmaestrodetalleId getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(TmaestrodetalleId id) {
        this.id = id;
    }

    /**
     * @return the nmaestroid
     */
    public BigDecimal getNmaestroid() {
        return nmaestroid;
    }

    /**
     * @param nmaestroid the nmaestroid to set
     */
    public void setNmaestroid(BigDecimal nmaestroid) {
        this.nmaestroid = nmaestroid;
    }

    /**
     * @return the ndetalleid
     */
    public BigDecimal getNdetalleid() {
        return ndetalleid;
    }

    /**
     * @param ndetalleid the ndetalleid to set
     */
    public void setNdetalleid(BigDecimal ndetalleid) {
        this.ndetalleid = ndetalleid;
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
     * @return the vdescripcion
     */
    public String getVdescripcion() {
        return vdescripcion;
    }

    /**
     * @param vdescripcion the vdescripcion to set
     */
    public void setVdescripcion(String vdescripcion) {
        this.vdescripcion = vdescripcion;
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

    /**
     * @return the nactivo
     */
    public BigDecimal getNactivo() {
        return nactivo;
    }

    /**
     * @param nactivo the nactivo to set
     */
    public void setNactivo(BigDecimal nactivo) {
        this.nactivo = nactivo;
    }

    public String getVfechacreacion() {
        if(dfechacreacion != null) {
            SimpleDateFormat sdf = new SimpleDateFormat(Constante.FORMAT_DATETIME_MEDIUM);
            vfechacreacion = sdf.format(dfechacreacion);
        }
        return vfechacreacion;
    }

    public void setVfechacreacion(String vfechacreacion) {
        this.vfechacreacion = vfechacreacion;
    }

    public String getVfechamodificacion() {
        if(dfechamodificacion != null) {
            SimpleDateFormat sdf = new SimpleDateFormat(Constante.FORMAT_DATETIME_MEDIUM);
            vfechamodificacion = sdf.format(dfechamodificacion);
        }
        return vfechamodificacion;
    }

    public void setVfechamodificacion(String vfechamodificacion) {
        this.vfechamodificacion = vfechamodificacion;
    }
}
