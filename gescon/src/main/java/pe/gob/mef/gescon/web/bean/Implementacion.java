/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.gob.mef.gescon.web.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author JJacobo
 */
public class Implementacion implements Serializable{
    
    private BigDecimal nimplementacionid;
    private BigDecimal nconocimientoid;
    private String vdescripcion;
    private String vtitulo;
    private String vcontenido;
    private String vmsjespecialista;
    private String vmsjmoderador;
    private String vusuariocreacion;
    private String vusuariomodificacion;
    private Date dfechacreacion;
    private Date dfechamodificacion;
    
    public Implementacion() {
        
    }

    public BigDecimal getNimplementacionid() {
        return nimplementacionid;
    }

    public void setNimplementacionid(BigDecimal nimplementacionid) {
        this.nimplementacionid = nimplementacionid;
    }

    public BigDecimal getNconocimientoid() {
        return nconocimientoid;
    }

    public void setNconocimientoid(BigDecimal nconocimientoid) {
        this.nconocimientoid = nconocimientoid;
    }

    public String getVdescripcion() {
        return vdescripcion;
    }

    public void setVdescripcion(String vdescripcion) {
        this.vdescripcion = vdescripcion;
    }

    public String getVtitulo() {
        return vtitulo;
    }

    public void setVtitulo(String vtitulo) {
        this.vtitulo = vtitulo;
    }

    public String getVcontenido() {
        return vcontenido;
    }

    public void setVcontenido(String vcontenido) {
        this.vcontenido = vcontenido;
    }

    public String getVmsjespecialista() {
        return vmsjespecialista;
    }

    public void setVmsjespecialista(String vmsjespecialista) {
        this.vmsjespecialista = vmsjespecialista;
    }

    public String getVmsjmoderador() {
        return vmsjmoderador;
    }

    public void setVmsjmoderador(String vmsjmoderador) {
        this.vmsjmoderador = vmsjmoderador;
    }

    public String getVusuariocreacion() {
        return vusuariocreacion;
    }

    public void setVusuariocreacion(String vusuariocreacion) {
        this.vusuariocreacion = vusuariocreacion;
    }

    public String getVusuariomodificacion() {
        return vusuariomodificacion;
    }

    public void setVusuariomodificacion(String vusuariomodificacion) {
        this.vusuariomodificacion = vusuariomodificacion;
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
}
