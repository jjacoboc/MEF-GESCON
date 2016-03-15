/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.gob.mef.gescon.hibernate.dao;

import java.math.BigDecimal;
import java.util.List;

import pe.gob.mef.gescon.hibernate.domain.TarchivoConocimiento;
import pe.gob.mef.gescon.hibernate.domain.Tconocimiento;

/**
 *
 * @author JJacobo
 */
public interface ArchivoConocimientoDao {
    
    BigDecimal getNextPK() throws Exception;
    List<TarchivoConocimiento> getTarchivosByTconocimiento(BigDecimal nconocimientoid) throws Exception;
    TarchivoConocimiento getLastTarchivoByTconocimiento(Tconocimiento tconocimiento) throws Exception;
    void saveOrUpdate(TarchivoConocimiento tarchivoconocimiento) throws Exception;
    void delete(final BigDecimal idarchivo) throws Exception;
}
