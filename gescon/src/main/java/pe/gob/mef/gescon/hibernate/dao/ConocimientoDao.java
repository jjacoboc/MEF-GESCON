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
public interface ConocimientoDao {
    BigDecimal getNextPK() throws Exception;
    List<Tconocimiento> getTconocimientos() throws Exception;
    List<Tconocimiento> getTconocimientosActivedPublic() throws Exception;
    List<Tconocimiento> getTconocimientosByType(BigDecimal type) throws Exception;
    List<Tconocimiento> getTconocimientosActivedPublicByType(BigDecimal type) throws Exception;
    Tconocimiento getTconocimientoById(BigDecimal id) throws Exception;
    void saveOrUpdate(Tconocimiento tconocimiento) throws Exception;
    void delete(Tconocimiento tconocimiento) throws Exception;
    List<HashMap> getConcimientosVinculados(HashMap filters);
    List<HashMap> getConcimientosDisponibles(HashMap filters);
    List<HashMap> getConcimientosByVinculoBaseLegalId(BigDecimal id);
    Tconocimiento getBpracticaById(BigDecimal tipo,BigDecimal id) throws Exception;
    List<HashMap> obtenerBpracticaxAsig(BigDecimal bpracticaid,BigDecimal usuarioid, BigDecimal tpoconocimientoid) throws Exception;
    Tconocimiento getOmejoraById(BigDecimal tipo,BigDecimal id) throws Exception;
    List<HashMap> obtenerOmejoraxAsig(BigDecimal omejoraid,BigDecimal usuarioid, BigDecimal tpoconocimientoid) throws Exception;
}
