package pe.gob.mef.gescon.hibernate.domain;
// Generated 06/11/2015 11:03:52 AM by Hibernate Tools 4.3.1

import java.math.BigDecimal;

/**
 * TimplementacionHistId generated by hbm2java
 */
public class TimplementacionHistId implements java.io.Serializable {

    private BigDecimal nimplementacionhid;
    private BigDecimal nconocimientoid;
    private BigDecimal nhistorialid;

    public TimplementacionHistId() {
    }

    public TimplementacionHistId(BigDecimal nimplementacionhid, BigDecimal nconocimientoid, BigDecimal nhistorialid) {
        this.nimplementacionhid = nimplementacionhid;
        this.nconocimientoid = nconocimientoid;
        this.nhistorialid = nhistorialid;
    }

    public BigDecimal getNimplementacionhid() {
        return this.nimplementacionhid;
    }

    public void setNimplementacionhid(BigDecimal nimplementacionhid) {
        this.nimplementacionhid = nimplementacionhid;
    }

    public BigDecimal getNconocimientoid() {
        return this.nconocimientoid;
    }

    public void setNconocimientoid(BigDecimal nconocimientoid) {
        this.nconocimientoid = nconocimientoid;
    }

    public BigDecimal getNhistorialid() {
        return this.nhistorialid;
    }

    public void setNhistorialid(BigDecimal nhistorialid) {
        this.nhistorialid = nhistorialid;
    }

    public boolean equals(Object other) {
        if ((this == other)) {
            return true;
        }
        if ((other == null)) {
            return false;
        }
        if (!(other instanceof TimplementacionHistId)) {
            return false;
        }
        TimplementacionHistId castOther = (TimplementacionHistId) other;

        return ((this.getNimplementacionhid() == castOther.getNimplementacionhid()) || (this.getNimplementacionhid() != null && castOther.getNimplementacionhid() != null && this.getNimplementacionhid().equals(castOther.getNimplementacionhid())))
                && ((this.getNconocimientoid() == castOther.getNconocimientoid()) || (this.getNconocimientoid() != null && castOther.getNconocimientoid() != null && this.getNconocimientoid().equals(castOther.getNconocimientoid())))
                && ((this.getNhistorialid() == castOther.getNhistorialid()) || (this.getNhistorialid() != null && castOther.getNhistorialid() != null && this.getNhistorialid().equals(castOther.getNhistorialid())));
    }

    public int hashCode() {
        int result = 17;

        result = 37 * result + (getNimplementacionhid() == null ? 0 : this.getNimplementacionhid().hashCode());
        result = 37 * result + (getNconocimientoid() == null ? 0 : this.getNconocimientoid().hashCode());
        result = 37 * result + (getNhistorialid() == null ? 0 : this.getNhistorialid().hashCode());
        return result;
    }

}
