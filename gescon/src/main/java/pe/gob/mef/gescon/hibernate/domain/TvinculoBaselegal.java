package pe.gob.mef.gescon.hibernate.domain;
// Generated 12/10/2015 03:16:20 PM by Hibernate Tools 4.3.1

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * TvinculoBaselegal generated by hbm2java
 */
public class TvinculoBaselegal implements Serializable {

    private TvinculoBaselegalId id;
    private Tbaselegal tbaselegal;
    private BigDecimal nbaselegalvinculadaid;
    private String vusuariocreacion;
    private Date dfechacreacion;
    private String vusuariomodificacion;
    private Date dfechamodificacion;
    private BigDecimal ntipovinculo;

    public TvinculoBaselegal() {
    }

    public TvinculoBaselegal(TvinculoBaselegalId id, Tbaselegal tbaselegal) {
        this.id = id;
        this.tbaselegal = tbaselegal;
    }

    public TvinculoBaselegal(TvinculoBaselegalId id, Tbaselegal tbaselegal, BigDecimal nbaselegalvinculadaid, String vusuariocreacion, Date dfechacreacion, String vusuariomodificacion, Date dfechamodificacion, BigDecimal ntipovinculo) {
        this.id = id;
        this.tbaselegal = tbaselegal;
        this.nbaselegalvinculadaid = nbaselegalvinculadaid;
        this.vusuariocreacion = vusuariocreacion;
        this.dfechacreacion = dfechacreacion;
        this.vusuariomodificacion = vusuariomodificacion;
        this.dfechamodificacion = dfechamodificacion;
        this.ntipovinculo = ntipovinculo;
    }

    public TvinculoBaselegalId getId() {
        return this.id;
    }

    public void setId(TvinculoBaselegalId id) {
        this.id = id;
    }

    public Tbaselegal getTbaselegal() {
        return this.tbaselegal;
    }

    public void setTbaselegal(Tbaselegal tbaselegal) {
        this.tbaselegal = tbaselegal;
    }

    public BigDecimal getNbaselegalvinculadaid() {
        return this.nbaselegalvinculadaid;
    }

    public void setNbaselegalvinculadaid(BigDecimal nbaselegalvinculadaid) {
        this.nbaselegalvinculadaid = nbaselegalvinculadaid;
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

    public BigDecimal getNtipovinculo() {
        return this.ntipovinculo;
    }

    public void setNtipovinculo(BigDecimal ntipovinculo) {
        this.ntipovinculo = ntipovinculo;
    }

}
