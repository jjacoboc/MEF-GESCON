/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.gob.mef.gescon.service;

import java.util.HashMap;
import java.util.List;
import pe.gob.mef.gescon.web.bean.Consulta;

/**
 *
 * @author JJacobo
 */
public interface ConsultaService {
    
    List<Consulta> getQueryFilter(HashMap filters);
    List<Consulta> getDestacadosByTipoConocimiento(HashMap filters);
}
