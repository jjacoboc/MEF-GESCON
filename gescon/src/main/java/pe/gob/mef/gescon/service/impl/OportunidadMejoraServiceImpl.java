/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.gob.mef.gescon.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.stereotype.Repository;
import pe.gob.mef.gescon.hibernate.dao.OportunidadMejoraDao;
import pe.gob.mef.gescon.hibernate.domain.Toportunidadmejora;
import pe.gob.mef.gescon.service.OportunidadMejoraService;
import pe.gob.mef.gescon.util.ServiceFinder;
import pe.gob.mef.gescon.web.bean.OportunidadMejora;

/**
 *
 * @author CNishimura
 */
@Repository(value = "OportunidadMejoraService")
public class OportunidadMejoraServiceImpl implements OportunidadMejoraService{

    @Override
    public BigDecimal getNextPK() throws Exception {
          OportunidadMejoraDao oportunidadMejoraDao = (OportunidadMejoraDao) ServiceFinder.findBean("OportunidadMejoraDao");
        return oportunidadMejoraDao.getNextPK();
    }

    @Override
    public List<OportunidadMejora> getOportunidadmejoras() throws Exception {
        List<OportunidadMejora> oportunidadMejoras = new ArrayList<OportunidadMejora>();
        OportunidadMejoraDao oportunidadMejoraDao = (OportunidadMejoraDao) ServiceFinder.findBean("OportunidadMejoraDao");
        List<Toportunidadmejora> lista = oportunidadMejoraDao.getToportunidadmejoras();
        for(Toportunidadmejora toportunidadmejoras : lista) {
            OportunidadMejora oportunidadMejora = new OportunidadMejora();
            BeanUtils.copyProperties(oportunidadMejora, toportunidadmejoras);
            oportunidadMejoras.add(oportunidadMejora);
        }
        return oportunidadMejoras;
    }

    @Override
    public OportunidadMejora getOportunidadmejoraById(BigDecimal id) throws Exception {
        OportunidadMejoraDao oportunidadMejoraDao = (OportunidadMejoraDao) ServiceFinder.findBean("OportunidadMejoraDao");
        Toportunidadmejora toportunidadmejora = oportunidadMejoraDao.getToportunidadmejoraById(id);
        OportunidadMejora oportunidadMejora = new OportunidadMejora();
        BeanUtils.copyProperties(oportunidadMejora, toportunidadmejora);
        return oportunidadMejora;
    }

    @Override
    public List<OportunidadMejora> getToportunidadmejorasLinkedById(BigDecimal id) throws Exception {
        OportunidadMejoraDao oportunidadMejoraDao = (OportunidadMejoraDao) ServiceFinder.findBean("OportunidadMejoraDao");
        List<HashMap> lista = oportunidadMejoraDao.getToportunidadmejorasNotLinkedById(id);
        List<OportunidadMejora> oportunidadMejoras = new ArrayList<OportunidadMejora>();
        for(HashMap m : lista) {
            OportunidadMejora op = new OportunidadMejora();
            op.setNoportunidadmejoraid((BigDecimal) m.get("ID"));
            op.setNestadoid((BigDecimal) m.get("IDESTADO"));
            op.setVestado((String) m.get("ESTADO"));
            oportunidadMejoras.add(op);
        }
        return oportunidadMejoras;
    }

    @Override
    public List<OportunidadMejora> getToportunidadmejorasNotLinkedById(BigDecimal id) throws Exception {
        OportunidadMejoraDao oportunidadMejoraDao = (OportunidadMejoraDao) ServiceFinder.findBean("OportunidadMejoraDao");
        List<HashMap> lista = oportunidadMejoraDao.getToportunidadmejorasNotLinkedById(id);
        List<OportunidadMejora> oportunidadMejoras = new ArrayList<OportunidadMejora>();
        for(HashMap m : lista) {
            OportunidadMejora op = new OportunidadMejora();
            op.setNoportunidadmejoraid((BigDecimal) m.get("ID"));
            op.setNestadoid((BigDecimal) m.get("IDESTADO"));
            op.setVestado((String) m.get("ESTADO"));
            oportunidadMejoras.add(op);
        }
        return oportunidadMejoras;
    }

    @Override
    public void saveOrUpdate(OportunidadMejora oportunidadMejora) throws Exception {
         Toportunidadmejora toportunidadmejora = new Toportunidadmejora();
        BeanUtils.copyProperties(toportunidadmejora, oportunidadMejora);
        OportunidadMejoraDao oportunidadMejoraDao = (OportunidadMejoraDao) ServiceFinder.findBean("OportunidadMejoraDao");
       oportunidadMejoraDao.saveOrUpdate(toportunidadmejora);
    }
   
}
