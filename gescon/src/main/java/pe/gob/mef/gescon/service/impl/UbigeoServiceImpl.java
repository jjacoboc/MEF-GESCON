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
import pe.gob.mef.gescon.hibernate.dao.UbigeoDao;
import pe.gob.mef.gescon.service.UbigeoService;
import pe.gob.mef.gescon.util.ServiceFinder;
import pe.gob.mef.gescon.web.bean.Ubigeo;

/**
 *
 * @author JJacobo
 */
@Repository(value = "UbigeoService")
public class UbigeoServiceImpl implements UbigeoService{

    @Override
    public List<Ubigeo> getDepartamentos() throws Exception {
        List<Ubigeo> ubigeos = new ArrayList<Ubigeo>();
        UbigeoDao ubigeoDao = (UbigeoDao) ServiceFinder.findBean("UbigeoDao");
        List<HashMap> lista = ubigeoDao.getDepartamentos();
        for(HashMap m : lista) {
            Ubigeo u = new Ubigeo();
            u.setVcoddep((String) m.get("COD"));
            u.setVdescdep((String) m.get("DES"));
            ubigeos.add(u);
        }
        return ubigeos;
    }

    @Override
    public List<Ubigeo> getProvinciasPorDepartamento(String coddep) throws Exception {
        List<Ubigeo> ubigeos = new ArrayList<Ubigeo>();
        UbigeoDao ubigeoDao = (UbigeoDao) ServiceFinder.findBean("UbigeoDao");
        List<HashMap> lista = ubigeoDao.getProvinciasPorDepartamento(coddep);
        for(HashMap m : lista) {
            Ubigeo u = new Ubigeo();
            u.setVcodprov((String) m.get("COD"));
            u.setVdescprov((String) m.get("DES"));
            ubigeos.add(u);
        }
        return ubigeos;
    }

    @Override
    public List<Ubigeo> getDistritosPorProvincia(String coddep, String codprov) throws Exception {
        List<Ubigeo> ubigeos = new ArrayList<Ubigeo>();
        UbigeoDao ubigeoDao = (UbigeoDao) ServiceFinder.findBean("UbigeoDao");
        List<HashMap> lista = ubigeoDao.getDistritosPorProvincia(coddep, codprov);
        for(HashMap m : lista) {
            Ubigeo u = new Ubigeo();
            u.setVcoddist((String) m.get("COD"));
            u.setVdescdist((String) m.get("DES"));
            ubigeos.add(u);
        }
        return ubigeos;
    }
    
}
