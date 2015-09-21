package pe.gob.mef.gescon.hibernate.domain;
// Generated 11/09/2015 05:20:07 PM by Hibernate Tools 4.3.1

import java.math.BigDecimal;
import java.util.Date;

/**
 * Tmaestrodetalle generated by hbm2java
 */
public class Tmaestrodetalle implements java.io.Serializable {

    private TmaestrodetalleId id;
    private Mtmaestro mtmaestro;
    private BigDecimal nmaestroid;
    private BigDecimal ndetalleid;
    private String vnombre;
    private String vdescripcion;
    private String vusucrea;
    private Date dfechcrea;
    private String vusumod;
    private Date dfechmod;
    private BigDecimal nactivo;

    public Tmaestrodetalle() {
    }

    public Tmaestrodetalle(TmaestrodetalleId id, Mtmaestro mtmaestro) {
        this.id = id;
        this.mtmaestro = mtmaestro;
    }

    public Tmaestrodetalle(TmaestrodetalleId id, Mtmaestro mtmaestro, BigDecimal nmaestroid, BigDecimal ndetalleid, String vnombre, String vdescripcion, String vusucrea, Date dfechcrea, String vusumod, Date dfechmod, BigDecimal nactivo) {
        this.id = id;
        this.mtmaestro = mtmaestro;
        this.nmaestroid = nmaestroid;
        this.ndetalleid = ndetalleid;
        this.vnombre = vnombre;
        this.vdescripcion = vdescripcion;
        this.vusucrea = vusucrea;
        this.dfechcrea = dfechcrea;
        this.vusumod = vusumod;
        this.dfechmod = dfechmod;
        this.nactivo = nactivo;
    }

    public TmaestrodetalleId getId() {
        return this.id;
    }

    public void setId(TmaestrodetalleId id) {
        this.id = id;
    }

    public Mtmaestro getMtmaestro() {
        return this.mtmaestro;
    }

    public void setMtmaestro(Mtmaestro mtmaestro) {
        this.mtmaestro = mtmaestro;
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
