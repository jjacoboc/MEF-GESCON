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
import pe.gob.mef.gescon.hibernate.dao.PreguntaDao;
import pe.gob.mef.gescon.hibernate.domain.Tpregunta;
import pe.gob.mef.gescon.service.PreguntaService;
import pe.gob.mef.gescon.util.ServiceFinder;
import pe.gob.mef.gescon.web.bean.Pregunta;

/**
 *
 * @author JJacobo
 */
@Repository(value = "PreguntaService")
public class PreguntaServiceImpl implements PreguntaService{

    @Override
    public BigDecimal getNextPK() throws Exception {
        PreguntaDao preguntaDao = (PreguntaDao) ServiceFinder.findBean("PreguntaDao");
        return preguntaDao.getNextPK();
    }

    @Override
    public List<Pregunta> getPreguntas() throws Exception {
        List<Pregunta> preguntas = new ArrayList<Pregunta>();
        PreguntaDao preguntaDao = (PreguntaDao) ServiceFinder.findBean("PreguntaDao");
        List<Tpregunta> lista = preguntaDao.getTpreguntas();
        for(Tpregunta tpregunta : lista) {
            Pregunta pregunta = new Pregunta();
            BeanUtils.copyProperties(pregunta, tpregunta);
            preguntas.add(pregunta);
        }
        return preguntas;
    }

    @Override
    public void saveOrUpdate(Pregunta pregunta) throws Exception {
        Tpregunta tpregunta = new Tpregunta();
        BeanUtils.copyProperties(tpregunta, pregunta);
        PreguntaDao preguntaDao = (PreguntaDao) ServiceFinder.findBean("PreguntaDao");
        preguntaDao.saveOrUpdate(tpregunta);
    }
    
    @Override
    public String traerNomCategoria(final BigDecimal categoriaid) throws Exception {
        String nombre="";
        try {
            PreguntaDao preguntaDao = (PreguntaDao) ServiceFinder.findBean("PreguntaDao");
            List<HashMap> lista = preguntaDao.traerNomCategoria(categoriaid);
            for (HashMap bean : lista) {
                nombre=((String) bean.get("NOMBRE"));
            }
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
        return nombre;

    }
    
    @Override
    public List<ArrayList> obtenerPreguntas() throws Exception {
        PreguntaDao preguntaDao = (PreguntaDao) ServiceFinder.findBean("PreguntaDao");
        List<ArrayList> preguntas = preguntaDao.obtenerPreguntas();
        return preguntas;

    }
}
