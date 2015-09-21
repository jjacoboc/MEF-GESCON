package pe.gob.mef.gescon.hibernate.domain;
// Generated 10/09/2015 10:47:32 AM by Hibernate Tools 4.3.1

import java.math.BigDecimal;
import java.util.Date;


/**
 * Mtmaestro generated by hbm2java
 */
public class Mtalerta implements java.io.Serializable {

    private BigDecimal nalertaid;
    private String vnombre;
    private String vdescripcion;
    private String vusucrea;
    private Date dfechcrea;
    private String vusumod;
    private Date dfechmod;
    private BigDecimal nactivo;

    public Mtalerta() {
    }

    public Mtalerta(BigDecimal nalertaid) {
        this.nalertaid = nalertaid;
    }

    public Mtalerta(BigDecimal nalertaid, String vnombre, String vdescripcion, String vusucrea, Date dfechcrea, String vusumod, Date dfechmod, BigDecimal nactivo) {
        this.nalertaid = nalertaid;
        this.vnombre = vnombre;
        this.vdescripcion = vdescripcion;
        this.vusucrea = vusucrea;
        this.dfechcrea = dfechcrea;
        this.vusumod = vusumod;
        this.dfechmod = dfechmod;
        this.nactivo = nactivo;
    }

    public BigDecimal getNalertaid() {
        return this.nalertaid;
    }

    public void setNalertaid(BigDecimal nalertaid) {
        this.nalertaid = nalertaid;
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

    public String getVusucrea() {
        return this.vusucrea;
    }

    public void setVusucrea(String vusucrea) {
        this.vusucrea = vusucrea;
    }

    public Date getDfechcrea() {
        return this.dfechcrea;
    }

    public void setDfechcrea(Date dfechcrea) {
        this.dfechcrea = dfechcrea;
    }

    public String getVusumod() {
        return this.vusumod;
    }

    public void setVusumod(String vusumod) {
        this.vusumod = vusumod;
    }

    public Date getDfechmod() {
        return this.dfechmod;
    }

    public void setDfechmod(Date dfechmod) {
        this.dfechmod = dfechmod;
    }
    
    public BigDecimal getNactivo() {
        return this.nactivo;
    }
    
    public void setNactivo(BigDecimal nactivo) {
        this.nactivo = nactivo;
    }
}
