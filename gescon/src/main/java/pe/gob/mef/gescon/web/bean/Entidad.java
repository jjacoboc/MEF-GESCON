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
public class Entidad implements Serializable {

    private BigDecimal nentidadid;
    private String vcodigoentidad;
    private String vnombre;
    private String vdescripcion;
    private String vusuariocreacion;
    private String vusuariomodificacion;
    private Date dfechacreacion;
    private Date dfechamodificacion;
    private BigDecimal nactivo;
    private String vfechacreacion;
    private String vfechamodificacion;
    private String vdepartamento;
    private String vprovincia;
    private String vdistrito;
    private BigDecimal ntipoid;
    private String vtipo;
    

    public void Entidad() {

    }

    /**
     * @return the nentidadid
     */
    public BigDecimal getNentidadid() {
        return nentidadid;
    }

    /**
     * @param nentidadid the nentidadid to set
     */
    public void setNentidadid(BigDecimal nentidadid) {
        this.nentidadid = nentidadid;
    }

    /**
     * @return the vcodigoentidad
     */
    public String getVcodigoentidad() {
        return vcodigoentidad;
    }

    /**
     * @param vcodigoentidad the vcodigoentidad to set
     */
    public void setVcodigoentidad(String vcodigoentidad) {
        this.vcodigoentidad = vcodigoentidad;
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

    /**
     * @return the vfechacreacion
     */
    public String getVfechacreacion() {
        if (dfechacreacion != null) {
            SimpleDateFormat sdf = new SimpleDateFormat(Constante.FORMAT_DATETIME_MEDIUM);
            vfechacreacion = sdf.format(dfechacreacion);
        }
        return vfechacreacion;
    }

    /**
     * @param vfechacreacion the vfechacreacion to set
     */
    public void setVfechacreacion(String vfechacreacion) {
        this.vfechacreacion = vfechacreacion;
    }

    /**
     * @return the vfechamodificacion
     */
    public String getVfechamodificacion() {
        if (dfechamodificacion != null) {
            SimpleDateFormat sdf = new SimpleDateFormat(Constante.FORMAT_DATETIME_MEDIUM);
            vfechamodificacion = sdf.format(dfechamodificacion);
        }
        return vfechamodificacion;
    }

    /**
     * @param vfechamodificacion the vfechamodificacion to set
     */
    public void setVfechamodificacion(String vfechamodificacion) {
        this.vfechamodificacion = vfechamodificacion;
    }

    /**
     * @return the vdepartamento
     */
    public String getVdepartamento() {
        return vdepartamento;
    }

    /**
     * @param vdepartamento the vdepartamento to set
     */
    public void setVdepartamento(String vdepartamento) {
        this.vdepartamento = vdepartamento;
    }

    /**
     * @return the vprovincia
     */
    public String getVprovincia() {
        return vprovincia;
    }

    /**
     * @param vprovincia the vprovincia to set
     */
    public void setVprovincia(String vprovincia) {
        this.vprovincia = vprovincia;
    }

    /**
     * @return the vdistrito
     */
    public String getVdistrito() {
        return vdistrito;
    }

    /**
     * @param vdistrito the vdistrito to set
     */
    public void setVdistrito(String vdistrito) {
        this.vdistrito = vdistrito;
    }

    /**
     * @return the ntipoid
     */
    public BigDecimal getNtipoid() {
        return ntipoid;
    }

    /**
     * @param ntipoid the ntipoid to set
     */
    public void setNtipoid(BigDecimal ntipoid) {
        this.ntipoid = ntipoid;
    }

    /**
     * @return the vtipo
     */
    public String getVtipo() {
        return vtipo;
    }

    /**
     * @param vtipo the vtipo to set
     */
    public void setVtipo(String vtipo) {
        this.vtipo = vtipo;
    }
}
