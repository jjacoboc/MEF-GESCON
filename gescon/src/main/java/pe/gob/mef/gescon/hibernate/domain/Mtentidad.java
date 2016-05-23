package pe.gob.mef.gescon.hibernate.domain;
// Generated 06/11/2015 11:03:52 AM by Hibernate Tools 4.3.1

import java.math.BigDecimal;
import java.util.Date;

/**
 * Mtrango generated by hbm2java
 */
public class Mtentidad implements java.io.Serializable {

    private BigDecimal nentidadid;
    private String vcodigoentidad;
    private String vnombre;
    private String vdescripcion;
    private String vusuariocreacion;
    private String vusuariomodificacion;
    private Date dfechacreacion;
    private Date dfechamodificacion;
    private BigDecimal nactivo;
    private String vdepartamento;
    private String vprovincia;
    private String vdistrito;
    private BigDecimal ntipoid;

    public Mtentidad() {
    }

    public Mtentidad(BigDecimal nentidadid) {
        this.nentidadid = nentidadid;
    }

    public Mtentidad(BigDecimal nentidadid, String vcodigoentidad,String vnombre, String vdescripcion, String vusuariocreacion, String vusuariomodificacion, Date dfechacreacion, Date dfechamodificacion, BigDecimal nactivo, String vdepartamento, String vprovincia, String vdistrito, BigDecimal ntipoid) {
        this.nentidadid = nentidadid;
        this.vcodigoentidad = vcodigoentidad;
        this.vnombre = vnombre;
        this.vdescripcion = vdescripcion;
        this.vusuariocreacion = vusuariocreacion;
        this.vusuariomodificacion = vusuariomodificacion;
        this.dfechacreacion = dfechacreacion;
        this.dfechamodificacion = dfechamodificacion;
        this.nactivo = nactivo;
        this.vdepartamento = vdepartamento;
        this.vprovincia = vprovincia;
        this.vdistrito = vdistrito;
        this.ntipoid = ntipoid;
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



    public String getVnombre() {
        return this.vnombre;
    }

    public void setVnombre(String vnombre) {
        this.vnombre = vnombre;
    }

    public String getVdescripcion() {
        return this.vdescripcion;
    }

    public void setVdescripcion(String vdescripcion) {
        this.vdescripcion = vdescripcion;
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

    public Date getDfechacreacion() {
        return this.dfechacreacion;
    }

    public void setDfechacreacion(Date dfechacreacion) {
        this.dfechacreacion = dfechacreacion;
    }

    public Date getDfechamodificacion() {
        return this.dfechamodificacion;
    }

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
}
