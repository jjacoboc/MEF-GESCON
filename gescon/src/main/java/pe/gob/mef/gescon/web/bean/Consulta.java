/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.gob.mef.gescon.web.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import pe.gob.mef.gescon.util.DateUtils;

/**
 *
 * @author JJacobo
 */
public class Consulta implements Serializable{
    
    private BigDecimal id;
    private String codigo;
    private String nombre;
    private String sumilla;
    private String descripcion;
    private BigDecimal idCategoria;
    private String categoria;
    private BigDecimal idTipoConocimiento;
    private String tipoConocimiento;
    private BigDecimal idEstado;
    private String estado;
    private Date fechaPublicacion;
    private String usuario;
    private String usuarioNombre;
    private String elapsedTime;
    
    public Consulta() {
        
    }

    /**
     * @return the id
     */
    public BigDecimal getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(BigDecimal id) {
        this.id = id;
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
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the sumilla
     */
    public String getSumilla() {
        return sumilla;
    }

    /**
     * @param sumilla the sumilla to set
     */
    public void setSumilla(String sumilla) {
        this.sumilla = sumilla;
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
     * @return the idCategoria
     */
    public BigDecimal getIdCategoria() {
        return idCategoria;
    }

    /**
     * @param idCategoria the idCategoria to set
     */
    public void setIdCategoria(BigDecimal idCategoria) {
        this.idCategoria = idCategoria;
    }

    /**
     * @return the categoria
     */
    public String getCategoria() {
        return categoria;
    }

    /**
     * @param categoria the categoria to set
     */
    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    /**
     * @return the idTipoConocimiento
     */
    public BigDecimal getIdTipoConocimiento() {
        return idTipoConocimiento;
    }

    /**
     * @param idTipoConocimiento the idTipoConocimiento to set
     */
    public void setIdTipoConocimiento(BigDecimal idTipoConocimiento) {
        this.idTipoConocimiento = idTipoConocimiento;
    }

    /**
     * @return the tipoConocimiento
     */
    public String getTipoConocimiento() {
        return tipoConocimiento;
    }

    /**
     * @param tipoConocimiento the tipoConocimiento to set
     */
    public void setTipoConocimiento(String tipoConocimiento) {
        this.tipoConocimiento = tipoConocimiento;
    }

    /**
     * @return the idEstado
     */
    public BigDecimal getIdEstado() {
        return idEstado;
    }

    /**
     * @param idEstado the idEstado to set
     */
    public void setIdEstado(BigDecimal idEstado) {
        this.idEstado = idEstado;
    }

    /**
     * @return the estado
     */
    public String getEstado() {
        return estado;
    }

    /**
     * @param estado the estado to set
     */
    public void setEstado(String estado) {
        this.estado = estado;
    }

    /**
     * @return the fechaPublicacion
     */
    public Date getFechaPublicacion() {
        return fechaPublicacion;
    }

    /**
     * @param fechaPublicacion the fechaPublicacion to set
     */
    public void setFechaPublicacion(Date fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
    }

    /**
     * @return the usuario
     */
    public String getUsuario() {
        return usuario;
    }

    /**
     * @param usuario the usuario to set
     */
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    /**
     * @return the usuarioNombre
     */
    public String getUsuarioNombre() {
        return usuarioNombre;
    }

    /**
     * @param usuarioNombre the usuarioNombre to set
     */
    public void setUsuarioNombre(String usuarioNombre) {
        this.usuarioNombre = usuarioNombre;
    }
    
    /**
     * @return the elapsedTime
     */
    public String getElapsedTime() {
        long time = DateUtils.getDifferenceYears(this.fechaPublicacion, new Date());
        if(time > 0) {
            this.elapsedTime =  "hace " + time + " años";
        } else {
            time = DateUtils.getDifferenceMonths(this.fechaPublicacion, new Date());
            if(time > 0) {
                this.elapsedTime =  "hace " + time + " meses";
            } else {
                time = DateUtils.getDifferenceDays(this.fechaPublicacion, new Date());
                if(time > 0) {
                    this.elapsedTime =  "hace " + time + " días";
                } else {
                    time = DateUtils.getDifferenceHours(this.fechaPublicacion, new Date());
                    if(time > 0) {
                        this.elapsedTime =  "hace " + time + " horas";
                    } else {
                        time = DateUtils.getDifferenceMinutes(this.fechaPublicacion, new Date());
                        if(time > 0) {
                            this.elapsedTime =  "hace " + time + " minutos";
                        }
                    }
                }
            }
        }
        return this.elapsedTime;
    }
    
    /**
     * @param elapsedTime the elapsedTime to set
     */
    public void setElapsedTime(String elapsedTime) {
        this.elapsedTime = elapsedTime;
    }
}
