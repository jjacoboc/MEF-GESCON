package pe.gob.mef.gescon.hibernate.domain;
// Generated 06/11/2015 11:03:52 AM by Hibernate Tools 4.3.1

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Tmensaje generated by hbm2java
 */
public class Tmensaje implements java.io.Serializable {

    private BigDecimal nmensajeid;
    private BigDecimal ntpoconocimientoid;
    private BigDecimal nconocimientoid;
    private String vmensajesolicita;
    private String vmensajerespuesta;
    private BigDecimal nestado;
    private String vusuariocreacion;
    private String vusuariomodificacion;
    private Date dfechacreacion;
    private Date dfechamodificacion;

    public Tmensaje() {
    }

    public Tmensaje(BigDecimal nmensajeid) {
        this.nmensajeid = nmensajeid;
    }

    public Tmensaje(BigDecimal nmensajeid, BigDecimal ntpoconocimientoid, BigDecimal nconocimientoid, String vmensajesolicita, String vmensajerespuesta, BigDecimal nestado, String vusuariocreacion, String vusuariomodificacion, Date dfechacreacion, Date dfechamodificacion) {
        this.nmensajeid = nmensajeid;
        this.ntpoconocimientoid = ntpoconocimientoid;
        this.nconocimientoid = nconocimientoid;
        this.vmensajesolicita = vmensajesolicita;
        this.vmensajerespuesta = vmensajerespuesta;
        this.nestado = nestado;
        this.vusuariocreacion = vusuariocreacion;
        this.vusuariomodificacion = vusuariomodificacion;
        this.dfechacreacion = dfechacreacion;
        this.dfechamodificacion = dfechamodificacion;
    }

    public BigDecimal getNmensajeid() {
        return this.nmensajeid;
    }

    public void setNmensajeid(BigDecimal nmensajeid) {
        this.nmensajeid = nmensajeid;
    }

    public BigDecimal getNtpoconocimientoid() {
        return this.ntpoconocimientoid;
    }

    public void setNtpoconocimientoid(BigDecimal ntpoconocimientoid) {
        this.ntpoconocimientoid = ntpoconocimientoid;
    }

    public BigDecimal getNconocimientoid() {
        return this.nconocimientoid;
    }

    public void setNconocimientoid(BigDecimal nconocimientoid) {
        this.nconocimientoid = nconocimientoid;
    }

    public String getVmensajesolicita() {
        return this.vmensajesolicita;
    }

    public void setVmensajesolicita(String vmensajesolicita) {
        this.vmensajesolicita = vmensajesolicita;
    }

    public String getVmensajerespuesta() {
        return this.vmensajerespuesta;
    }

    public void setVmensajerespuesta(String vmensajerespuesta) {
        this.vmensajerespuesta = vmensajerespuesta;
    }

    public BigDecimal getNestado() {
        return this.nestado;
    }

    public void setNestado(BigDecimal nestado) {
        this.nestado = nestado;
    }

    public String getVusuariocreacion() {
        return this.vusuariocreacion;
    }

    public void setVusuariocreacion(String vusuariocreacion) {
        this.vusuariocreacion = vusuariocreacion;
    }

    public String getVusuariomodificacion() {
        return this.vusuariomodificacion;
    }

    public void setVusuariomodificacion(String vusuariomodificacion) {
        this.vusuariomodificacion = vusuariomodificacion;
    }

    public Serializable getDfechacreacion() {
        return this.dfechacreacion;
    }

    public void setDfechacreacion(Date dfechacreacion) {
        this.dfechacreacion = dfechacreacion;
    }

    public Serializable getDfechamodificacion() {
        return this.dfechamodificacion;
    }

    public void setDfechamodificacion(Date dfechamodificacion) {
        this.dfechamodificacion = dfechamodificacion;
    }

}
