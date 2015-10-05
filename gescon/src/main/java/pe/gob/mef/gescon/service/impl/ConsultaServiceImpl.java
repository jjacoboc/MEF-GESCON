/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.gob.mef.gescon.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;
import pe.gob.mef.gescon.hibernate.dao.ConsultaDao;
import pe.gob.mef.gescon.service.ConsultaService;
import pe.gob.mef.gescon.util.ServiceFinder;
import pe.gob.mef.gescon.web.bean.Consulta;

/**
 *
 * @author JJacobo
 */
@Repository(value = "ConsultaService")
public class ConsultaServiceImpl implements ConsultaService{

    @Override
    public List<Consulta> getQueryFilter(HashMap filters) {
        List<Consulta> lista = new ArrayList<Consulta>();
        try {
            ConsultaDao consultaDao = (ConsultaDao) ServiceFinder.findBean("ConsultaDao");
            List<HashMap> consulta = consultaDao.getQueryFilter(filters);
            if(!CollectionUtils.isEmpty(consulta)) {
                for(HashMap map : consulta) {
                    Consulta c = new Consulta();
                    c.setId((BigDecimal) map.get("ID"));
                    c.setCodigo((String) map.get("NUMERO"));
                    c.setNombre((String) map.get("NOMBRE"));
                    c.setSumilla((String) map.get("SUMILLA"));
                    c.setFechaPublicacion((Date) map.get("FECHA"));
                    c.setIdCategoria((BigDecimal) map.get("IDCATEGORIA"));
                    c.setCategoria((String) map.get("CATEGORIA"));
                    c.setIdTipoConocimiento((BigDecimal) map.get("IDTIPOCONOCIMIENTO"));
                    c.setTipoConocimiento((String) map.get("TIPOCONOCIMIENTO"));
                    lista.add(c);
                }
            }
        } catch(Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
        return lista;
    }
    
}
