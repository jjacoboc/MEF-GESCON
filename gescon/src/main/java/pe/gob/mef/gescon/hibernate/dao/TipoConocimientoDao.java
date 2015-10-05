/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.gob.mef.gescon.hibernate.dao;

import java.util.List;
import pe.gob.mef.gescon.hibernate.domain.MttipoConocimiento;

/**
 *
 * @author JJacobo
 */
public interface TipoConocimientoDao {
    
    public List<MttipoConocimiento> getMttipoConocimientos() throws Exception;
}
