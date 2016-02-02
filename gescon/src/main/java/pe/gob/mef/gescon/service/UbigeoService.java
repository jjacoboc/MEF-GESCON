/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.gob.mef.gescon.service;

import java.util.List;
import pe.gob.mef.gescon.web.ui.Ubigeo;

/**
 *
 * @author JJacobo
 */
public interface UbigeoService {
    
    List<Ubigeo> getDepartamentos() throws Exception;
    List<Ubigeo> getProvinciasPorDepartamento(String coddep) throws Exception;
    List<Ubigeo> getDistritosPorProvincia(String coddep, String codprov) throws Exception;
}
