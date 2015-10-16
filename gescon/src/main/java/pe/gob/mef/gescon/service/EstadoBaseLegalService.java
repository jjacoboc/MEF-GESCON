/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.gob.mef.gescon.service;

import java.util.List;
import pe.gob.mef.gescon.web.bean.EstadoBaselegal;

/**
 *
 * @author JJacobo
 */
public interface EstadoBaseLegalService {
    
    public List<EstadoBaselegal> getEstadosBaselegal() throws Exception;
    public List<EstadoBaselegal> getEstadosBaselegalToLink() throws Exception;
}
