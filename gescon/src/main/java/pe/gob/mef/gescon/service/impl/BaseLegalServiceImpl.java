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
import pe.gob.mef.gescon.hibernate.dao.BaseLegalDao;
import pe.gob.mef.gescon.hibernate.domain.Tbaselegal;
import pe.gob.mef.gescon.service.BaseLegalService;
import pe.gob.mef.gescon.util.ServiceFinder;
import pe.gob.mef.gescon.web.bean.BaseLegal;

/**
 *
 * @author JJacobo
 */
@Repository(value = "BaseLegalService")
public class BaseLegalServiceImpl implements BaseLegalService{

    @Override
    public BigDecimal getNextPK() throws Exception {
        BaseLegalDao baseLegalDao = (BaseLegalDao) ServiceFinder.findBean("BaseLegalDao");
        return baseLegalDao.getNextPK();
    }

    @Override
    public List<BaseLegal> getBaselegales() throws Exception {
        List<BaseLegal> baseLegales = new ArrayList<BaseLegal>();
        BaseLegalDao baseLegalDao = (BaseLegalDao) ServiceFinder.findBean("BaseLegalDao");
        List<Tbaselegal> lista = baseLegalDao.getTbaselegales();
        for(Tbaselegal tbaselegal : lista) {
            BaseLegal baseLegal = new BaseLegal();
            BeanUtils.copyProperties(baseLegal, tbaselegal);
            baseLegales.add(baseLegal);
        }
        return baseLegales;
    }
    
    @Override
    public BaseLegal getBaselegalById(BigDecimal id) throws Exception {
        BaseLegalDao baseLegalDao = (BaseLegalDao) ServiceFinder.findBean("BaseLegalDao");
        Tbaselegal tbaselegal = baseLegalDao.getTbaselegalById(id);
        BaseLegal baseLegal = new BaseLegal();
        BeanUtils.copyProperties(baseLegal, tbaselegal);
        return baseLegal;
    }
    
    @Override
    public List<BaseLegal> getTbaselegalesLinkedById(final BigDecimal id) throws Exception {
        BaseLegalDao baseLegalDao = (BaseLegalDao) ServiceFinder.findBean("BaseLegalDao");
        List<HashMap> lista = baseLegalDao.getTbaselegalesLinkedById(id);
        List<BaseLegal> baseLegales = new ArrayList<BaseLegal>();
        for(HashMap m : lista) {
            BaseLegal bl = new BaseLegal();
            bl.setNbaselegalid((BigDecimal) m.get("ID"));
            bl.setVnumero((String) m.get("NUMERO"));
            bl.setVnombre((String) m.get("NOMBRE"));
            bl.setVsumilla((String) m.get("SUMILLA"));
            bl.setNcategoriaid((BigDecimal) m.get("IDCATEGORIA"));
            bl.setVcategoria((String) m.get("CATEGORIA"));
            bl.setNestadoid((BigDecimal) m.get("IDESTADO"));
            bl.setVestado((String) m.get("ESTADO"));
            bl.setDfechapublicacion((Date) m.get("FECHA"));
            baseLegales.add(bl);
        }
        return baseLegales;
    }
    
    @Override
    public List<BaseLegal> getTbaselegalesNotLinkedById(final BigDecimal id) throws Exception {
        BaseLegalDao baseLegalDao = (BaseLegalDao) ServiceFinder.findBean("BaseLegalDao");
        List<HashMap> lista = baseLegalDao.getTbaselegalesNotLinkedById(id);
        List<BaseLegal> baseLegales = new ArrayList<BaseLegal>();
        for(HashMap m : lista) {
            BaseLegal bl = new BaseLegal();
            bl.setNbaselegalid((BigDecimal) m.get("ID"));
            bl.setVnumero((String) m.get("NUMERO"));
            bl.setVnombre((String) m.get("NOMBRE"));
            bl.setVsumilla((String) m.get("SUMILLA"));
            bl.setNcategoriaid((BigDecimal) m.get("IDCATEGORIA"));
            bl.setVcategoria((String) m.get("CATEGORIA"));
            bl.setNestadoid((BigDecimal) m.get("IDESTADO"));
            bl.setVestado((String) m.get("ESTADO"));
            bl.setDfechapublicacion((Date) m.get("FECHA"));
            baseLegales.add(bl);
        }
        return baseLegales;
    }

    @Override
    public void saveOrUpdate(BaseLegal baseLegal) throws Exception {
        Tbaselegal tbaselegal = new Tbaselegal();
        BeanUtils.copyProperties(tbaselegal, baseLegal);
        BaseLegalDao baseLegalDao = (BaseLegalDao) ServiceFinder.findBean("BaseLegalDao");
        baseLegalDao.saveOrUpdate(tbaselegal);
    }
    
}
