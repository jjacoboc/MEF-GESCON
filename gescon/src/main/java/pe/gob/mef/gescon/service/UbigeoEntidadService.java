/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.gob.mef.gescon.service;

import java.util.List;
import pe.gob.mef.gescon.web.bean.UbigeoEntidad;

/**
 *
 * @author JJacobo
 */
public interface UbigeoEntidadService {
    
    List<UbigeoEntidad> getDepartamentos() throws Exception;
    List<UbigeoEntidad> getProvinciasPorDepartamento(String coddep) throws Exception;
    List<UbigeoEntidad> getDistritosPorProvincia(String coddep, String codprov) throws Exception;
}
