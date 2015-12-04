/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.gob.mef.gescon.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.stereotype.Repository;
import pe.gob.mef.gescon.hibernate.dao.ArchivoConocimientoDao;
import pe.gob.mef.gescon.hibernate.dao.ArchivoDao;
import pe.gob.mef.gescon.hibernate.dao.RespuestaHistDao;
import pe.gob.mef.gescon.hibernate.domain.Tarchivo;
import pe.gob.mef.gescon.hibernate.domain.TarchivoConocimiento;
import pe.gob.mef.gescon.hibernate.domain.Tbaselegal;
import pe.gob.mef.gescon.hibernate.domain.Tconocimiento;
import pe.gob.mef.gescon.hibernate.domain.TrespuestaHist;
import pe.gob.mef.gescon.service.ArchivoConocimientoService;
import pe.gob.mef.gescon.service.ArchivoService;
import pe.gob.mef.gescon.service.RespuestaHistService;
import pe.gob.mef.gescon.util.ServiceFinder;
import pe.gob.mef.gescon.web.bean.Archivo;
import pe.gob.mef.gescon.web.bean.ArchivoConocimiento;
import pe.gob.mef.gescon.web.bean.BaseLegal;
import pe.gob.mef.gescon.web.bean.Conocimiento;
import pe.gob.mef.gescon.web.bean.RespuestaHist;

/**
 *
 * @author JJacobo
 */
@Repository(value = "RespuestaHistService")
public class RespuestaHistServiceImpl implements RespuestaHistService{

    @Override
    public BigDecimal getNextPK() throws Exception {
        RespuestaHistDao respuestahistDao = (RespuestaHistDao) ServiceFinder.findBean("RespuestaHistDao");
        return respuestahistDao.getNextPK();
    }

    @Override
    public List<RespuestaHist> getHistorialByPregunta(BigDecimal npreguntaid) throws Exception {
        List<RespuestaHist> historial = new ArrayList<RespuestaHist>();
        RespuestaHistDao respuestahistDao = (RespuestaHistDao) ServiceFinder.findBean("RespuestaHistDao");
        List<TrespuestaHist> lista = respuestahistDao.getThistorialByTpregunta(npreguntaid);
        for(TrespuestaHist trespuestahist : lista) {
            RespuestaHist respuestahist = new RespuestaHist();
            BeanUtils.copyProperties(respuestahist, trespuestahist);
            historial.add(respuestahist);
        }
        return historial;
    }

    @Override
    public void saveOrUpdate(RespuestaHist respuestahist) throws Exception {
        TrespuestaHist trespuestahist = new TrespuestaHist();
        BeanUtils.copyProperties(trespuestahist, respuestahist);
        RespuestaHistDao respuestahistDao = (RespuestaHistDao) ServiceFinder.findBean("RespuestaHistDao");
        respuestahistDao.saveOrUpdate(trespuestahist);
    }

    
    
}
