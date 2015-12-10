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

/**
 *
 * @author JJacobo
 */
public class Alerta implements Serializable{
    
    private BigDecimal nalertaid;
    private String vnombre;
    private String vdescripcion;
    private BigDecimal nactivo;
    private String vusuariocreacion;
    private Date dfechacreacion;
    private String vusuariomodificacion;
    private Date dfechamodificacion;
    private BigDecimal nparametroid;
    private BigDecimal nuseraplica;
    private Date dfechini;
    private Date dfechfin;
    private String ncondicion1;
    private String ncondicion2;
    private BigDecimal nvalor1;
    private BigDecimal ntipo1;
    private BigDecimal nvalor2;
    private BigDecimal ntipo2;
    private String vfechacreacion;
    private String vfechamodificacion;
    private String vfechini;
    private String vfechfin;
    
    public void Alerta(){
        
    }

    /**
     * @return the nalertaid
     */
    public BigDecimal getNalertaid() {
        return nalertaid;
    }

    /**
     * @param nalertaid the nalertaid to set
     */
    public void setNalertaid(BigDecimal nalertaid) {
        this.nalertaid = nalertaid;
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
     * @return the nparametroid
     */
    public BigDecimal getNparametroid() {
        return nparametroid;
    }

    /**
     * @param nparametroid the nparametroid to set
     */
    public void setNparametroid(BigDecimal nparametroid) {
        this.nparametroid = nparametroid;
    }

    /**
     * @return the nuseraplica
     */
    public BigDecimal getNuseraplica() {
        return nuseraplica;
    }

    /**
     * @param nuseraplica the nuseraplica to set
     */
    public void setNuseraplica(BigDecimal nuseraplica) {
        this.nuseraplica = nuseraplica;
    }

    /**
     * @return the dfechini
     */
    public Date getDfechini() {
        return dfechini;
    }

    /**
     * @param dfechini the dfechini to set
     */
    public void setDfechini(Date dfechini) {
        this.dfechini = dfechini;
    }

    /**
     * @return the dfechfin
     */
    public Date getDfechfin() {
        return dfechfin;
    }

    /**
     * @param dfechfin the dfechfin to set
     */
    public void setDfechfin(Date dfechfin) {
        this.dfechfin = dfechfin;
    }

    /**
     * @return the ncondicion1
     */
    public String getNcondicion1() {
        return ncondicion1;
    }

    /**
     * @param ncondicion1 the ncondicion1 to set
     */
    public void setNcondicion1(String ncondicion1) {
        this.ncondicion1 = ncondicion1;
    }

    /**
     * @return the ncondicion2
     */
    public String getNcondicion2() {
        return ncondicion2;
    }

    /**
     * @param ncondicion2 the ncondicion2 to set
     */
    public void setNcondicion2(String ncondicion2) {
        this.ncondicion2 = ncondicion2;
    }

    /**
     * @return the nvalor1
     */
    public BigDecimal getNvalor1() {
        return nvalor1;
    }

    /**
     * @param nvalor1 the nvalor1 to set
     */
    public void setNvalor1(BigDecimal nvalor1) {
        this.nvalor1 = nvalor1;
    }

    /**
     * @return the ntipo1
     */
    public BigDecimal getNtipo1() {
        return ntipo1;
    }

    /**
     * @param ntipo1 the ntipo1 to set
     */
    public void setNtipo1(BigDecimal ntipo1) {
        this.ntipo1 = ntipo1;
    }

    /**
     * @return the nvalor2
     */
    public BigDecimal getNvalor2() {
        return nvalor2;
    }

    /**
     * @param nvalor2 the nvalor2 to set
     */
    public void setNvalor2(BigDecimal nvalor2) {
        this.nvalor2 = nvalor2;
    }

    /**
     * @return the ntipo2
     */
    public BigDecimal getNtipo2() {
        return ntipo2;
    }

    /**
     * @param ntipo2 the ntipo2 to set
     */
    public void setNtipo2(BigDecimal ntipo2) {
        this.ntipo2 = ntipo2;
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

    public String getVfechini() {
        if(dfechini != null) {
            SimpleDateFormat sdf = new SimpleDateFormat(Constante.FORMAT_DATE_SHORT);
            vfechini = sdf.format(dfechini);
        }
        return vfechini;
    }

    public void setVfechini(String vfechini) {
        this.vfechini = vfechini;
    }

    public String getVfechfin() {
        if(dfechfin != null) {
            SimpleDateFormat sdf = new SimpleDateFormat(Constante.FORMAT_DATE_SHORT);
            vfechfin = sdf.format(dfechfin);
        }
        return vfechfin;
    }

    public void setVfechfin(String vfechfin) {
        this.vfechfin = vfechfin;
    }
}
