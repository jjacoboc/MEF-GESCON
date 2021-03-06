package pe.gob.mef.gescon.hibernate.domain;
// Generated 18/12/2015 06:17:48 PM by Hibernate Tools 4.3.1

import java.math.BigDecimal;
import java.util.Date;

/**
 * TdiscusionSeccionHist generated by hbm2java
 */
public class TdiscusionSeccionHist implements java.io.Serializable {

    private BigDecimal ndiscusionseccionhid;
    private BigDecimal ndiscusionhid;
    private String vtitulo;
    private BigDecimal ntipodiscusion;
    private String vruta;
    private String vusuariocreacion;
    private Date dfechacreacion;
    private String vusuariomodificacion;
    private Date dfechamodificacion;

    public TdiscusionSeccionHist() {
    }

    public TdiscusionSeccionHist(BigDecimal ndiscusionseccionhid) {
        this.ndiscusionseccionhid = ndiscusionseccionhid;
    }

    public TdiscusionSeccionHist(BigDecimal ndiscusionseccionhid, BigDecimal ndiscusionhid, String vtitulo, BigDecimal ntipodiscusion, String vruta, String vusuariocreacion, Date dfechacreacion, String vusuariomodificacion, Date dfechamodificacion) {
        this.ndiscusionseccionhid = ndiscusionseccionhid;
        this.ndiscusionhid = ndiscusionhid;
        this.vtitulo = vtitulo;
        this.ntipodiscusion = ntipodiscusion;
        this.vruta = vruta;
        this.vusuariocreacion = vusuariocreacion;
        this.dfechacreacion = dfechacreacion;
        this.vusuariomodificacion = vusuariomodificacion;
        this.dfechamodificacion = dfechamodificacion;
    }

    public BigDecimal getNdiscusionseccionhid() {
        return this.ndiscusionseccionhid;
    }

    public void setNdiscusionseccionhid(BigDecimal ndiscusionseccionhid) {
        this.ndiscusionseccionhid = ndiscusionseccionhid;
    }

    public BigDecimal getNdiscusionhid() {
        return this.ndiscusionhid;
    }

    public void setNdiscusionhid(BigDecimal ndiscusionhid) {
        this.ndiscusionhid = ndiscusionhid;
    }

    public String getVtitulo() {
        return this.vtitulo;
    }

    public void setVtitulo(String vtitulo) {
        this.vtitulo = vtitulo;
    }

    public BigDecimal getNtipodiscusion() {
        return this.ntipodiscusion;
    }

    public void setNtipodiscusion(BigDecimal ntipodiscusion) {
        this.ntipodiscusion = ntipodiscusion;
    }

    public String getVruta() {
        return this.vruta;
    }

    public void setVruta(String vruta) {
        this.vruta = vruta;
    }

    public String getVusuariocreacion() {
        return this.vusuariocreacion;
    }

    public void setVusuariocreacion(String vusuariocreacion) {
        this.vusuariocreacion = vusuariocreacion;
    }

    public Date getDfechacreacion() {
        return this.dfechacreacion;
    }

    public void setDfechacreacion(Date dfechacreacion) {
        this.dfechacreacion = dfechacreacion;
    }

    public String getVusuariomodificacion() {
        return this.vusuariomodificacion;
    }

    public void setVusuariomodificacion(String vusuariomodificacion) {
        this.vusuariomodificacion = vusuariomodificacion;
    }

    public Date getDfechamodificacion() {
        return this.dfechamodificacion;
    }

    public void setDfechamodificacion(Date dfechamodificacion) {
        this.dfechamodificacion = dfechamodificacion;
    }

}
