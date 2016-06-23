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
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;
import pe.gob.mef.gescon.hibernate.dao.VinculoHistDao;
import pe.gob.mef.gescon.hibernate.domain.TvinculoHist;
import pe.gob.mef.gescon.service.VinculoHistService;
import pe.gob.mef.gescon.util.ServiceFinder;
import pe.gob.mef.gescon.web.bean.Consulta;
import pe.gob.mef.gescon.web.bean.VinculoHist;

/**
 *
 * @author JJacobo
 */
@Repository(value = "VinculoHistService")
public class VinculoHistServiceImpl implements VinculoHistService{

    @Override
    public BigDecimal getNextPK() throws Exception {
        VinculoHistDao vinculoHistDao = (VinculoHistDao) ServiceFinder.findBean("VinculoHistDao");
        return vinculoHistDao.getNextPK();
    }

    @Override
    public VinculoHist getVinculoHistById(BigDecimal idvinculoh) throws Exception {
        VinculoHistDao vinculoHistDao = (VinculoHistDao) ServiceFinder.findBean("VinculoHistDao");
        TvinculoHist tvinculoHist = vinculoHistDao.getTvinculoHistById(idvinculoh);
        VinculoHist vinculoHist = new VinculoHist();
        BeanUtils.copyProperties(vinculoHist, tvinculoHist);
        return vinculoHist;
    }

    @Override
    public List<VinculoHist> getVinculoHists() throws Exception {
        List<VinculoHist> vinculosHists = new ArrayList<VinculoHist>();
        VinculoHistDao vinculoHistDao = (VinculoHistDao) ServiceFinder.findBean("VinculoHistDao");
        List<TvinculoHist> lista = vinculoHistDao.getTvinculoHists();
        for (TvinculoHist tvinculoHist : lista) {
            VinculoHist vinculoHist = new VinculoHist();
            BeanUtils.copyProperties(vinculoHist, tvinculoHist);
            vinculosHists.add(vinculoHist);
        }
        return vinculosHists;
    }

    @Override
    public List<VinculoHist> getVinculoHistsByHistorial(BigDecimal idhistorial) throws Exception {
        List<VinculoHist> vinculosHists = new ArrayList<VinculoHist>();
        VinculoHistDao vinculoHistDao = (VinculoHistDao) ServiceFinder.findBean("VinculoHistDao");
        List<TvinculoHist> lista = vinculoHistDao.getTvinculoHistsByThistorial(idhistorial);
        for (TvinculoHist tvinculoHist : lista) {
            VinculoHist vinculoHist = new VinculoHist();
            BeanUtils.copyProperties(vinculoHist, tvinculoHist);
            vinculosHists.add(vinculoHist);
        }
        return vinculosHists;
    }

    @Override
    public void saveOrUpdate(VinculoHist vinculoHist) throws Exception {
        VinculoHistDao vinculoHistDao = (VinculoHistDao) ServiceFinder.findBean("VinculoHistDao");
        TvinculoHist tvinculoHist = new TvinculoHist();
        BeanUtils.copyProperties(tvinculoHist, vinculoHist);
        vinculoHistDao.saveOrUpdate(tvinculoHist);
    }
    
    @Override
    public List<Consulta> getConcimientosVinculadosByHistorial(HashMap filters) throws Exception {
        List<Consulta> lista = new ArrayList<Consulta>();
        try {
            VinculoHistDao vinculoHistDao = (VinculoHistDao) ServiceFinder.findBean("VinculoHistDao");
            List<HashMap> consulta = vinculoHistDao.getConcimientosVinculadosByHistorial(filters);
            if(!CollectionUtils.isEmpty(consulta)) {
                for(HashMap map : consulta) {
                    Consulta c = new Consulta();
                    c.setId((BigDecimal) map.get("ID"));
                    c.setIdconocimiento((BigDecimal) map.get("IDCONOCIMIENTO"));
                    c.setCodigo((String) map.get("NUMERO"));
                    c.setNombre((String) map.get("NOMBRE"));
                    c.setSumilla((String) map.get("SUMILLA"));
                    c.setFechaPublicacion((Date) map.get("FECHA"));
                    c.setIdCategoria((BigDecimal) map.get("IDCATEGORIA"));
                    c.setCategoria((String) map.get("CATEGORIA"));
                    c.setIdTipoConocimiento((BigDecimal) map.get("IDTIPOCONOCIMIENTO"));
                    c.setTipoConocimiento((String) map.get("TIPOCONOCIMIENTO"));
                    c.setIdEstado((BigDecimal) map.get("IDESTADO"));
                    c.setEstado((String) map.get("ESTADO"));
                    lista.add(c);
                }
            }
        } catch(Exception e) {
            e.getMessage();
        }
        return lista;
    }
}
