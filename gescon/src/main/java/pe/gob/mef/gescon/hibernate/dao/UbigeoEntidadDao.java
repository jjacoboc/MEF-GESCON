/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.gob.mef.gescon.hibernate.dao;

import java.util.HashMap;
import java.util.List;

/**
 *
 * @author JJacobo
 */
public interface UbigeoEntidadDao {
    
    List<HashMap> getDepartamentos() throws Exception;
    List<HashMap> getProvinciasPorDepartamento(String coddep) throws Exception;
    List<HashMap> getDistritosPorProvincia(String coddep, String codprov) throws Exception;
}
