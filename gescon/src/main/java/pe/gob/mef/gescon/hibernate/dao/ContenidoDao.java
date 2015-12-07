/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.gob.mef.gescon.hibernate.dao;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import pe.gob.mef.gescon.hibernate.domain.Tconocimiento;

/**
 *
 * @author JJacobo
 */
public interface ContenidoDao {
    
    BigDecimal getNextPK() throws Exception;
    public List<Tconocimiento> getContenidos() throws Exception;
    Tconocimiento getTcontenidoById(BigDecimal tipo,BigDecimal id) throws Exception;
    List<HashMap> obtenerContenidoxAsig(BigDecimal contenidoid,BigDecimal usuarioid, BigDecimal tpoconocimientoid) throws Exception;
    void saveOrUpdate(Tconocimiento tconocimiento) throws Exception;
    List<HashMap> getConcimientosVinculados(HashMap filters);
    List<HashMap> getConcimientosDisponibles(HashMap filters);
    void delete(BigDecimal conocimientoid) throws Exception;

}
