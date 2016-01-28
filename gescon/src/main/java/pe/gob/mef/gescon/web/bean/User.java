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
public class User implements Serializable {

    private BigDecimal nusuarioid;
    private String vlogin;
    private String vnombres;
    private String vapellidos;
    private String vcorreo;
    private Date dfechanacimiento;
    private BigDecimal nestado;
    private String vusuariomodificacion;
    private String vusuariocreacion;
    private Date dfechacreacion;
    private Date dfechamodificacion;
    private String vsexo;
    private String vdni;
    private String vdpto;
    private String vprov;
    private String vdist;
    private String vprofesion;
    private String ventidad;
    private String vpliego;
    private String vcargo;
    private String varea;
    private String vsector;
    private String vgobierno;
    private BigDecimal nuserinterno;
    private BigDecimal nactivo;

    public void User() {

    }

    /**
     * @return the nusuarioid
     */
    public BigDecimal getNusuarioid() {
        return nusuarioid;
    }

    /**
     * @param nusuarioid the nusuarioid to set
     */
    public void setNusuarioid(BigDecimal nusuarioid) {
        this.nusuarioid = nusuarioid;
    }

    /**
     * @return the vlogin
     */
    public String getVlogin() {
        return vlogin;
    }

    /**
     * @param vlogin the vlogin to set
     */
    public void setVlogin(String vlogin) {
        this.vlogin = vlogin;
    }

    /**
     * @return the vnombres
     */
    public String getVnombres() {
        return vnombres;
    }

    /**
     * @param vnombres the vnombres to set
     */
    public void setVnombres(String vnombres) {
        this.vnombres = vnombres;
    }

    /**
     * @return the vapellidos
     */
    public String getVapellidos() {
        return vapellidos;
    }

    /**
     * @param vapellidos the vapellidos to set
     */
    public void setVapellidos(String vapellidos) {
        this.vapellidos = vapellidos;
    }

    public String getVcorreo() {
        return vcorreo;
    }

    public void setVcorreo(String vcorreo) {
        this.vcorreo = vcorreo;
    }

    /**
     * @return the nestado
     */
    public BigDecimal getNestado() {
        return nestado;
    }

    /**
     * @param nestado the nestado to set
     */
    public void setNestado(BigDecimal nestado) {
        this.nestado = nestado;
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

    public Date getDfechamodificacion() {
        return dfechamodificacion;
    }

    public void setDfechamodificacion(Date dfechamodificacion) {
        this.dfechamodificacion = dfechamodificacion;
    }

    /**
     * @return the dfechanacimiento
     */
    public Date getDfechanacimiento() {
        return dfechanacimiento;
    }

    /**
     * @param dfechanacimiento the dfechanacimiento to set
     */
    public void setDfechanacimiento(Date dfechanacimiento) {
        this.dfechanacimiento = dfechanacimiento;
    }

    /**
     * @return the vsexo
     */
    public String getVsexo() {
        return vsexo;
    }

    /**
     * @param vsexo the vsexo to set
     */
    public void setVsexo(String vsexo) {
        this.vsexo = vsexo;
    }

    /**
     * @return the vdni
     */
    public String getVdni() {
        return vdni;
    }

    /**
     * @param vdni the vdni to set
     */
    public void setVdni(String vdni) {
        this.vdni = vdni;
    }

    /**
     * @return the vdpto
     */
    public String getVdpto() {
        return vdpto;
    }

    /**
     * @param vdpto the vdpto to set
     */
    public void setVdpto(String vdpto) {
        this.vdpto = vdpto;
    }

    /**
     * @return the vprov
     */
    public String getVprov() {
        return vprov;
    }

    /**
     * @param vprov the vprov to set
     */
    public void setVprov(String vprov) {
        this.vprov = vprov;
    }

    /**
     * @return the vdist
     */
    public String getVdist() {
        return vdist;
    }

    /**
     * @param vdist the vdist to set
     */
    public void setVdist(String vdist) {
        this.vdist = vdist;
    }

    /**
     * @return the vprofesion
     */
    public String getVprofesion() {
        return vprofesion;
    }

    /**
     * @param vprofesion the vprofesion to set
     */
    public void setVprofesion(String vprofesion) {
        this.vprofesion = vprofesion;
    }

    /**
     * @return the ventidad
     */
    public String getVentidad() {
        return ventidad;
    }

    /**
     * @param ventidad the ventidad to set
     */
    public void setVentidad(String ventidad) {
        this.ventidad = ventidad;
    }

    /**
     * @return the vpliego
     */
    public String getVpliego() {
        return vpliego;
    }

    /**
     * @param vpliego the vpliego to set
     */
    public void setVpliego(String vpliego) {
        this.vpliego = vpliego;
    }

    /**
     * @return the vcargo
     */
    public String getVcargo() {
        return vcargo;
    }

    /**
     * @param vcargo the vcargo to set
     */
    public void setVcargo(String vcargo) {
        this.vcargo = vcargo;
    }

    /**
     * @return the varea
     */
    public String getVarea() {
        return varea;
    }

    /**
     * @param varea the varea to set
     */
    public void setVarea(String varea) {
        this.varea = varea;
    }

    /**
     * @return the vsector
     */
    public String getVsector() {
        return vsector;
    }

    /**
     * @param vsector the vsector to set
     */
    public void setVsector(String vsector) {
        this.vsector = vsector;
    }

    /**
     * @return the vgobierno
     */
    public String getVgobierno() {
        return vgobierno;
    }

    /**
     * @param vgobierno the vgobierno to set
     */
    public void setVgobierno(String vgobierno) {
        this.vgobierno = vgobierno;
    }

    public BigDecimal getNuserinterno() {
        return nuserinterno;
    }

    public void setNuserinterno(BigDecimal nuserinterno) {
        this.nuserinterno = nuserinterno;
    }

    public BigDecimal getNactivo() {
        return nactivo;
    }

    public void setNactivo(BigDecimal nactivo) {
        this.nactivo = nactivo;
    }
}
