/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.gob.mef.gescon.web.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import pe.gob.mef.gescon.common.Constante;

/**
 *
 * @author JJacobo
 */
public class TipoEntidad implements Serializable {

    private BigDecimal ntipoentidadid;
    private String vdescripcion;
    

    public void TipoEntidad() {

    }

    /**
     * @return the ntipoentidadid
     */
    public BigDecimal getNtipoentidadid() {
        return ntipoentidadid;
    }

    /**
     * @param ntipoentidadid the ntipoentidadid to set
     */
    public void setNtipoentidadid(BigDecimal ntipoentidadid) {
        this.ntipoentidadid = ntipoentidadid;
    }

    /**
     * @return the vdescripcion
     */
    public String getVdescripcion() {
        return vdescripcion;
    }

    /**
     * @param vdescripcion the vdescripcion to set
     */
    public void setVdescripcion(String vdescripcion) {
        this.vdescripcion = vdescripcion;
    }

    
}
