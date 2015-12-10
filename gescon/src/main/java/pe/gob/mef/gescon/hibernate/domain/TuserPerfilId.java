package pe.gob.mef.gescon.hibernate.domain;
// Generated 06/11/2015 11:03:52 AM by Hibernate Tools 4.3.1

import java.math.BigDecimal;

/**
 * TuserPerfilId generated by hbm2java
 */
public class TuserPerfilId implements java.io.Serializable {

    private BigDecimal nperfilid;
    private BigDecimal nusuarioid;

    public TuserPerfilId() {
    }

    public TuserPerfilId(BigDecimal nperfilid, BigDecimal nusuarioid) {
        this.nperfilid = nperfilid;
        this.nusuarioid = nusuarioid;
    }

    public BigDecimal getNperfilid() {
        return this.nperfilid;
    }

    public void setNperfilid(BigDecimal nperfilid) {
        this.nperfilid = nperfilid;
    }

    public BigDecimal getNusuarioid() {
        return this.nusuarioid;
    }

    public void setNusuarioid(BigDecimal nusuarioid) {
        this.nusuarioid = nusuarioid;
    }

    public boolean equals(Object other) {
        if ((this == other)) {
            return true;
        }
        if ((other == null)) {
            return false;
        }
        if (!(other instanceof TuserPerfilId)) {
            return false;
        }
        TuserPerfilId castOther = (TuserPerfilId) other;

        return ((this.getNperfilid() == castOther.getNperfilid()) || (this.getNperfilid() != null && castOther.getNperfilid() != null && this.getNperfilid().equals(castOther.getNperfilid())))
                && ((this.getNusuarioid() == castOther.getNusuarioid()) || (this.getNusuarioid() != null && castOther.getNusuarioid() != null && this.getNusuarioid().equals(castOther.getNusuarioid())));
    }

    public int hashCode() {
        int result = 17;

        result = 37 * result + (getNperfilid() == null ? 0 : this.getNperfilid().hashCode());
        result = 37 * result + (getNusuarioid() == null ? 0 : this.getNusuarioid().hashCode());
        return result;
    }

}
