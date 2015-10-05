package pe.gob.mef.gescon.hibernate.domain;
// Generated 11/09/2015 05:20:07 PM by Hibernate Tools 4.3.1

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Mtuser generated by hbm2java
 */
public class Mtuser implements java.io.Serializable {

     private BigDecimal nusuarioid;
     private String vlogin;
     private String vnombres;
     private String vapellidos;
     private Date dfechanacimiento;
     private BigDecimal nestado;
     private BigDecimal nusuariomod;
     private BigDecimal nusuariocreacion;
     private Date dfechacreacion;
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
     private Set<TuserPerfil> tuserPerfils = new HashSet<TuserPerfil>(0);
     private Set<Tpass> tpasses = new HashSet<Tpass>(0);

    public Mtuser() {
    }

    public Mtuser(BigDecimal nusuarioid) {
        this.nusuarioid = nusuarioid;
    }
    
    public Mtuser(BigDecimal nusuarioid, String vlogin, String vnombres, String vapellidos, Date dfechanacimiento, BigDecimal nestado, BigDecimal nusuariomod, BigDecimal nusuariocreacion, Date dfechacreacion, String vsexo, String vdni, String vdpto, String vprov, String vdist, String vprofesion, String ventidad, String vpliego, String vcargo, String varea, String vsector, String vgobierno, Set<TuserPerfil> tuserPerfils, Set<Tpass> tpasses) {
       this.nusuarioid = nusuarioid;
       this.vlogin = vlogin;
       this.vnombres = vnombres;
       this.vapellidos = vapellidos;
       this.dfechanacimiento = dfechanacimiento;
       this.nestado = nestado;
       this.nusuariomod = nusuariomod;
       this.nusuariocreacion = nusuariocreacion;
       this.dfechacreacion = dfechacreacion;
       this.vsexo = vsexo;
       this.vdni = vdni;
       this.vdpto = vdpto;
       this.vprov = vprov;
       this.vdist = vdist;
       this.vprofesion = vprofesion;
       this.ventidad = ventidad;
       this.vpliego = vpliego;
       this.vcargo = vcargo;
       this.varea = varea;
       this.vsector = vsector;
       this.vgobierno = vgobierno;
       this.tuserPerfils = tuserPerfils;
       this.tpasses = tpasses;
    }

    public BigDecimal getNusuarioid() {
        return this.nusuarioid;
    }

    public void setNusuarioid(BigDecimal nusuarioid) {
        this.nusuarioid = nusuarioid;
    }

    public String getVlogin() {
        return this.vlogin;
    }

    public void setVlogin(String vlogin) {
        this.vlogin = vlogin;
    }

    public String getVnombres() {
        return this.vnombres;
    }

    public void setVnombres(String vnombres) {
        this.vnombres = vnombres;
    }

    public String getVapellidos() {
        return this.vapellidos;
    }

    public void setVapellidos(String vapellidos) {
        this.vapellidos = vapellidos;
    }

    public BigDecimal getNestado() {
        return this.nestado;
    }
    
    public void setNestado(BigDecimal nestado) {
        this.nestado = nestado;
    }

    public BigDecimal getNusuariomod() {
        return this.nusuariomod;
    }

    public void setNusuariomod(BigDecimal nusuariomod) {
        this.nusuariomod = nusuariomod;
    }

    public BigDecimal getNusuariocreacion() {
        return this.nusuariocreacion;
    }

    public void setNusuariocreacion(BigDecimal nusuariocreacion) {
        this.nusuariocreacion = nusuariocreacion;
    }

    public Date getDfechacreacion() {
        return this.dfechacreacion;
    }

    public void setDfechacreacion(Date dfechacreacion) {
        this.dfechacreacion = dfechacreacion;
    }

    public Set<TuserPerfil> getTuserPerfils() {
        return this.tuserPerfils;
    }

    public void setTuserPerfils(Set<TuserPerfil> tuserPerfils) {
        this.tuserPerfils = tuserPerfils;
    }

    public Set<Tpass> getTpasses() {
        return this.tpasses;
    }

    public void setTpasses(Set<Tpass> tpasses) {
        this.tpasses = tpasses;
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
}
