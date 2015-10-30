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
import pe.gob.mef.gescon.hibernate.dao.PreguntaDao;
import pe.gob.mef.gescon.hibernate.domain.Tpregunta;
import pe.gob.mef.gescon.service.PreguntaService;
import pe.gob.mef.gescon.util.ServiceFinder;
import pe.gob.mef.gescon.web.bean.Asignacion;
import pe.gob.mef.gescon.web.bean.Pregunta;

/**
 *
 * @author JJacobo
 */
@Repository(value = "PreguntaService")
public class PreguntaServiceImpl implements PreguntaService {

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
        for (Tpregunta tpregunta : lista) {
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
        String nombre = "";
        try {
            PreguntaDao preguntaDao = (PreguntaDao) ServiceFinder.findBean("PreguntaDao");
            List<HashMap> lista = preguntaDao.traerNomCategoria(categoriaid);
            for (HashMap bean : lista) {
                nombre = ((String) bean.get("NOMBRE"));
            }
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
        return nombre;

    }

    @Override
    public List<Pregunta> obtenerPreguntas(final BigDecimal preguntaid, final BigDecimal usuarioid, final BigDecimal tpoconocimientoid) throws Exception {
        List<Pregunta> preguntas = new ArrayList<Pregunta>();
        PreguntaDao preguntaDao = (PreguntaDao) ServiceFinder.findBean("PreguntaDao");
        List<HashMap> lista = preguntaDao.obtenerPreguntas(preguntaid,usuarioid,tpoconocimientoid);
        String asd;
        for (HashMap bean : lista) {
            Pregunta pregunta = new Pregunta();
            pregunta.setNpreguntaid((BigDecimal) bean.get("IDPREGUNTA"));
            asd= (String) bean.get("ASUNTO");
            pregunta.setVasunto((String) bean.get("ASUNTO"));
            pregunta.setNcategoriaid((BigDecimal) bean.get("IDCATEGORIA"));
            pregunta.setVdetalle((String) bean.get("DETALLE"));
            pregunta.setNentidadid((BigDecimal) bean.get("IDENTIDAD"));
            pregunta.setVdatoadicional((String) bean.get("DATOADICIONAL"));
            pregunta.setVusuariocreacion((String) bean.get("USUCREA"));
            pregunta.setVusuariomodificacion((String) bean.get("USUMOD"));
            pregunta.setDfechacreacion((Date) bean.get("FECHACREA"));
            pregunta.setDfechamodificacion((Date) bean.get("FECHAMOD"));
            pregunta.setNactivo((BigDecimal) bean.get("ESTADO"));
            pregunta.setVrespuesta((String) bean.get("RESPUESTA"));
            pregunta.setVmsjusuario2((String) bean.get("MSJUSU2"));
            pregunta.setVmsjespecialista((String) bean.get("MSJESP"));
            pregunta.setNsituacion((BigDecimal) bean.get("SITUACION"));
            pregunta.setVmsjmoderador((String) bean.get("MSJMOD"));
            pregunta.setVmsjusuario1((String) bean.get("MSJUSU1"));
            preguntas.add(pregunta);

        }
        return preguntas;
    }
    
    @Override
    public List<Asignacion> obtenerPreguntaxAsig(final BigDecimal preguntaid, final BigDecimal usuarioid,BigDecimal tpoconocimientoid) throws Exception {
        List<Asignacion> asignacions = new ArrayList<Asignacion>();
        PreguntaDao preguntaDao = (PreguntaDao) ServiceFinder.findBean("PreguntaDao");
        List<HashMap> lista = preguntaDao.obtenerPreguntaxAsig(preguntaid,usuarioid,tpoconocimientoid);
        for (HashMap bean : lista) {
            Asignacion asignacion = new Asignacion();
            asignacion.setNasignacionid((BigDecimal) bean.get("IDASIGNACION"));
            asignacion.setNtipoconocimientoid((BigDecimal) bean.get("TPOCONOCIMIENTO"));
            asignacion.setNconocimientoid((BigDecimal) bean.get("IDPREGUNTA"));
            asignacion.setNusuarioid((BigDecimal) bean.get("IDUSUARIO"));
            asignacion.setNestadoid((BigDecimal) bean.get("ESTADO"));
            asignacion.setVusuariocreacion((String) bean.get("USUCREA"));
            asignacion.setVusuariomodificacion((String) bean.get("USUMOD"));
            asignacion.setDfechacreacion((Date) bean.get("FECHACREA"));
            asignacion.setDfechamodificacion((Date) bean.get("FECHAMOD"));     
            asignacion.setDfechaasignacion((Date) bean.get("FECHAASIG"));  
            asignacion.setDfechaatencion((Date) bean.get("FECHAATEN"));  
            asignacion.setDfecharecepcion((Date) bean.get("FECHARECEP"));  
            asignacions.add(asignacion);
        }
        return asignacions;
    }

    @Override
    public BigDecimal obtenerPerfilxUsuario(final BigDecimal usuarioid) throws Exception {
        BigDecimal perfil = new BigDecimal(0);
        PreguntaDao preguntaDao = (PreguntaDao) ServiceFinder.findBean("PreguntaDao");
        List<HashMap> lista = preguntaDao.obtenerPerfilxUsuario(usuarioid);
        for (HashMap bean : lista) {
            perfil = (BigDecimal) bean.get("PERFIL");
        }
        return perfil;
    }
}
