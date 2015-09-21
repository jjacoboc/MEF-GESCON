package pe.gob.mef.gescon.hibernate.domain;
// Generated 11/09/2015 05:20:07 PM by Hibernate Tools 4.3.1


import java.math.BigDecimal;
import java.util.Date;

/**
 * TcategoriaConocimiento generated by hbm2java
 */
public class TcategoriaConocimiento  implements java.io.Serializable {


     private TcategoriaConocimientoId id;
     private Tconocimiento tconocimiento;
     private Mtcategoria mtcategoria;
     private BigDecimal nusuariomod;
     private BigDecimal nusuariocreacion;
     private Date dfechacreacion;

    public TcategoriaConocimiento() {
    }

	
    public TcategoriaConocimiento(TcategoriaConocimientoId id, Tconocimiento tconocimiento, Mtcategoria mtcategoria) {
        this.id = id;
        this.tconocimiento = tconocimiento;
        this.mtcategoria = mtcategoria;
    }
    public TcategoriaConocimiento(TcategoriaConocimientoId id, Tconocimiento tconocimiento, Mtcategoria mtcategoria, BigDecimal nusuariomod, BigDecimal nusuariocreacion, Date dfechacreacion) {
       this.id = id;
       this.tconocimiento = tconocimiento;
       this.mtcategoria = mtcategoria;
       this.nusuariomod = nusuariomod;
       this.nusuariocreacion = nusuariocreacion;
       this.dfechacreacion = dfechacreacion;
    }
   
    public TcategoriaConocimientoId getId() {
        return this.id;
    }
    
    public void setId(TcategoriaConocimientoId id) {
        this.id = id;
    }
    public Tconocimiento getTconocimiento() {
        return this.tconocimiento;
    }
    
    public void setTconocimiento(Tconocimiento tconocimiento) {
        this.tconocimiento = tconocimiento;
    }
    public Mtcategoria getMtcategoria() {
        return this.mtcategoria;
    }
    
    public void setMtcategoria(Mtcategoria mtcategoria) {
        this.mtcategoria = mtcategoria;
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




}


