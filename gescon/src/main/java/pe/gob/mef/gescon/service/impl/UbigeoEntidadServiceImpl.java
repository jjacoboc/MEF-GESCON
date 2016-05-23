/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.gob.mef.gescon.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.springframework.stereotype.Repository;
import pe.gob.mef.gescon.hibernate.dao.UbigeoEntidadDao;
import pe.gob.mef.gescon.service.UbigeoEntidadService;
import pe.gob.mef.gescon.util.ServiceFinder;
import pe.gob.mef.gescon.web.bean.UbigeoEntidad;

/**
 *
 * @author JJacobo
 */
@Repository(value = "UbigeoEntidadService")
public class UbigeoEntidadServiceImpl implements UbigeoEntidadService{

    @Override
    public List<UbigeoEntidad> getDepartamentos() throws Exception {
        List<UbigeoEntidad> ubigeos = new ArrayList<UbigeoEntidad>();
        UbigeoEntidadDao ubigeoDao = (UbigeoEntidadDao) ServiceFinder.findBean("UbigeoEntidadDao");
        List<HashMap> lista = ubigeoDao.getDepartamentos();
        for(HashMap m : lista) {
            UbigeoEntidad u = new UbigeoEntidad();
            u.setCdepartamento((String) m.get("COD"));
            u.setVdescripcion((String) m.get("DES"));
            ubigeos.add(u);
        }
        return ubigeos;
    }

    @Override
    public List<UbigeoEntidad> getProvinciasPorDepartamento(String coddep) throws Exception {
        List<UbigeoEntidad> ubigeos = new ArrayList<UbigeoEntidad>();
        UbigeoEntidadDao ubigeoDao = (UbigeoEntidadDao) ServiceFinder.findBean("UbigeoEntidadDao");
        List<HashMap> lista = ubigeoDao.getProvinciasPorDepartamento(coddep);
        for(HashMap m : lista) {
            UbigeoEntidad u = new UbigeoEntidad();
            u.setCprovincia((String) m.get("COD"));
            u.setVdescripcion((String) m.get("DES"));
            ubigeos.add(u);
        }
        return ubigeos;
    }

    @Override
    public List<UbigeoEntidad> getDistritosPorProvincia(String coddep, String codprov) throws Exception {
        List<UbigeoEntidad> ubigeos = new ArrayList<UbigeoEntidad>();
        UbigeoEntidadDao ubigeoDao = (UbigeoEntidadDao) ServiceFinder.findBean("UbigeoEntidadDao");
        List<HashMap> lista = ubigeoDao.getDistritosPorProvincia(coddep, codprov);
        for(HashMap m : lista) {
            UbigeoEntidad u = new UbigeoEntidad();
            u.setCdistrito((String) m.get("COD"));
            u.setVdescripcion((String) m.get("DES"));
            ubigeos.add(u);
        }
        return ubigeos;
    }
    
}
