package pe.gob.mef.gescon.hibernate.domain;
// Generated 11/09/2015 05:20:07 PM by Hibernate Tools 4.3.1


import java.math.BigDecimal;

/**
 * TpassId generated by hbm2java
 */
public class TpassId  implements java.io.Serializable {


     private BigDecimal npassid;
     private BigDecimal nusuarioid;

    public TpassId() {
    }

    public TpassId(BigDecimal npassid, BigDecimal nusuarioid) {
       this.npassid = npassid;
       this.nusuarioid = nusuarioid;
    }
   
    public BigDecimal getNpassid() {
        return this.npassid;
    }
    
    public void setNpassid(BigDecimal npassid) {
        this.npassid = npassid;
    }
    public BigDecimal getNusuarioid() {
        return this.nusuarioid;
    }
    
    public void setNusuarioid(BigDecimal nusuarioid) {
        this.nusuarioid = nusuarioid;
    }




}


