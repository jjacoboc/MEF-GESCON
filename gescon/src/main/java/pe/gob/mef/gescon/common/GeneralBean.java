/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pe.gob.mef.gescon.common;

import java.io.Serializable;

/**
 *
 * @author Jonatan Jacobo
 */
public class GeneralBean implements Serializable {

    private String codigo;
    private String descripcion;
    private String descripcionCorta;
    private String tipo;
    private String orden;
    private String imagen;
    
    public GeneralBean(){
    }
    
    public GeneralBean(String codigo, String descripcion, String descripcionCorta, String tipo, String orden, String imagen){
        this.codigo = codigo;
        this.descripcion = descripcion;
        this.descripcionCorta = descripcionCorta;
        this.tipo = tipo;
        this.orden = orden;
        this.imagen = imagen;
    }

    /**
     * @return the codigo
     */
    public String getCodigo() {
        return codigo;
    }

    /**
     * @param codigo the codigo to set
     */
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    /**
     * @return the descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * @param descripcion the descripcion to set
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * @return the descripcionCorta
     */
    public String getDescripcionCorta() {
        return descripcionCorta;
    }

    /**
     * @param descripcionCorta the descripcionCorta to set
     */
    public void setDescripcionCorta(String descripcionCorta) {
        this.descripcionCorta = descripcionCorta;
    }

    /**
     * @return the tipo
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * @param tipo the tipo to set
     */
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    /**
     * @return the orden
     */
    public String getOrden() {
        return orden;
    }

    /**
     * @param orden the orden to set
     */
    public void setOrden(String orden) {
        this.orden = orden;
    }

    /**
     * @return the imagen
     */
    public String getImagen() {
        return imagen;
    }

    /**
     * @param imagen the imagen to set
     */
    public void setImagen(String imagen) {
        this.imagen = imagen;
    }
}
