/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.gob.mef.gescon.web.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Comparator;
import java.util.Date;

/**
 *
 * @author Pruebas
 */

public class OportunidadMejora implements Serializable, Comparable<OportunidadMejora>{
    
    private BigDecimal noportunidadmejoraid;
    private String vusuariocreacion;
    private String vusuariomodificacion;
    private Date dfechacreacion;
    private Date dfechamodificacion;
    private BigDecimal ngobnacional;
    private BigDecimal ngobregional;
    private BigDecimal ngoblocal;
    private BigDecimal nmancomunidades;
    private BigDecimal nactivo;
    private BigDecimal nestadoid;
    private String vestado;

    /**
     * 
     * @return 
     */
    public BigDecimal getNoportunidadmejoraid() {
        return noportunidadmejoraid;
    }

    /**
     * 
     * @param noportunidadmejoraid 
     */
    public void setNoportunidadmejoraid(BigDecimal noportunidadmejoraid) {
        this.noportunidadmejoraid = noportunidadmejoraid;
    }

    public String getVusuariocreacion() {
        return vusuariocreacion;
    }

    public void setVusuariocreacion(String vusuariocreacion) {
        this.vusuariocreacion = vusuariocreacion;
    }

    public String getVusuariomodificacion() {
        return vusuariomodificacion;
    }

    public void setVusuariomodificacion(String vusuariomodificacion) {
        this.vusuariomodificacion = vusuariomodificacion;
    }

    public Date getDfechacreacion() {
        return dfechacreacion;
    }

    public void setDfechacreacion(Date dfechacreacion) {
        this.dfechacreacion = dfechacreacion;
    }

    public Date getDfechamodificacion() {
        return dfechamodificacion;
    }

    public void setDfechamodificacion(Date dfechamodificacion) {
        this.dfechamodificacion = dfechamodificacion;
    }

    public BigDecimal getNgobnacional() {
        return ngobnacional;
    }

    public void setNgobnacional(BigDecimal ngobnacional) {
        this.ngobnacional = ngobnacional;
    }

    public BigDecimal getNgobregional() {
        return ngobregional;
    }

    public void setNgobregional(BigDecimal ngobregional) {
        this.ngobregional = ngobregional;
    }

    public BigDecimal getNgoblocal() {
        return ngoblocal;
    }

    public void setNgoblocal(BigDecimal ngoblocal) {
        this.ngoblocal = ngoblocal;
    }

    public BigDecimal getNmancomunidades() {
        return nmancomunidades;
    }

    public void setNmancomunidades(BigDecimal nmancomunidades) {
        this.nmancomunidades = nmancomunidades;
    }

    public BigDecimal getNactivo() {
        return nactivo;
    }

    public void setNactivo(BigDecimal nactivo) {
        this.nactivo = nactivo;
    }

    public BigDecimal getNestadoid() {
        return nestadoid;
    }

    public void setNestadoid(BigDecimal nestadoid) {
        this.nestadoid = nestadoid;
    }

    public String getVestado() {
        return vestado;
    }

    public void setVestado(String vestado) {
        this.vestado = vestado;
    }
    
    
    
    @Override
    public int compareTo(OportunidadMejora o) {
        return Comparators.ID.compare(this, o);
    }
    
    public static class Comparators {
        public static Comparator<OportunidadMejora> ID = new Comparator<OportunidadMejora>() {
            @Override
            public int compare(OportunidadMejora o1, OportunidadMejora o2) {
                return o1.getNoportunidadmejoraid().intValue() - o2.getNoportunidadmejoraid().intValue();
            }
        };
    }
    
}
