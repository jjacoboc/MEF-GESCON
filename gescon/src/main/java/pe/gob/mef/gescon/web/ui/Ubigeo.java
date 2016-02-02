/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.gob.mef.gescon.web.ui;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 *
 * @author JJacobo
 */
public class Ubigeo implements Serializable{
    
    private BigDecimal nubigeoid;
    private String vcoddep;
    private String vcodprov;
    private String vcoddist;
    private String vdescdep;
    private String vdescprov;
    private String vdescdist;
    
    public Ubigeo(){
        
    }

    public BigDecimal getNubigeoid() {
        return nubigeoid;
    }

    public void setNubigeoid(BigDecimal nubigeoid) {
        this.nubigeoid = nubigeoid;
    }

    public String getVcoddep() {
        return vcoddep;
    }

    public void setVcoddep(String vcoddep) {
        this.vcoddep = vcoddep;
    }

    public String getVcodprov() {
        return vcodprov;
    }

    public void setVcodprov(String vcodprov) {
        this.vcodprov = vcodprov;
    }

    public String getVcoddist() {
        return vcoddist;
    }

    public void setVcoddist(String vcoddist) {
        this.vcoddist = vcoddist;
    }

    public String getVdescdep() {
        return vdescdep;
    }

    public void setVdescdep(String vdescdep) {
        this.vdescdep = vdescdep;
    }

    public String getVdescprov() {
        return vdescprov;
    }

    public void setVdescprov(String vdescprov) {
        this.vdescprov = vdescprov;
    }

    public String getVdescdist() {
        return vdescdist;
    }

    public void setVdescdist(String vdescdist) {
        this.vdescdist = vdescdist;
    }
}
