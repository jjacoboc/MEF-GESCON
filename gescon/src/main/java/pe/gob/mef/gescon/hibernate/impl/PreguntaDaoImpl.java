/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.gob.mef.gescon.hibernate.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateCallback;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pe.gob.mef.gescon.hibernate.dao.PreguntaDao;
import pe.gob.mef.gescon.hibernate.domain.Tpregunta;

/**
 *
 * @author JJacobo
 */
@Repository(value = "PreguntaDao")
public class PreguntaDaoImpl extends HibernateDaoSupport implements PreguntaDao{

    /**
     * Crea una nueva instancia de PreguntaDaoImpl
     *
     * @param sessionFactory
     */
    @Autowired
    public PreguntaDaoImpl(SessionFactory sessionFactory) {
        this.setSessionFactory(sessionFactory);
    }
    
    @Override
    public BigDecimal getNextPK() throws Exception {
        return (BigDecimal) getHibernateTemplate().execute(
            new HibernateCallback() {
                @Override
                public Object doInHibernate(Session session) throws HibernateException {
                    Query query = session.createSQLQuery("SELECT SEQ_TPREGUNTA.NEXTVAL FROM DUAL");
                    return query.uniqueResult();
                }
            });
    }

    @Override
    public List<Tpregunta> getTpreguntas() throws Exception {
        DetachedCriteria criteria = DetachedCriteria.forClass(Tpregunta.class);
        criteria.addOrder(Order.desc("dfechacreacion"));
        return (List<Tpregunta>) getHibernateTemplate().findByCriteria(criteria);
    }

    @Override
    @Transactional(readOnly = false)
    public void saveOrUpdate(Tpregunta tpregunta) throws Exception {
        getHibernateTemplate().saveOrUpdate(tpregunta);
    }
    
    @Override
    public List<HashMap> traerNomCategoria(final BigDecimal categoriaid) throws Exception {
        final StringBuilder sql = new StringBuilder();
        Object object = null;
        try {
            sql.append("select vnombre AS NOMBRE ");
            sql.append(" from MTCATEGORIA ");
            sql.append(" WHERE NCATEGORIAID = :CATEGORIA ");

            object = getHibernateTemplate().execute(
                    new HibernateCallback() {
                        @Override
                        public Object doInHibernate(Session session) throws HibernateException {
                            Query query = session.createSQLQuery(sql.toString())
                            .setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP)
                            .setParameter("CATEGORIA", categoriaid);
                            return query.list();
                        }
                    });
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
        return (List<HashMap>) object;
    }
    
    @Override
    public List<HashMap> obtenerPreguntas(final BigDecimal preguntaid,final BigDecimal usuarioid, final BigDecimal tpoconocimientoid) throws Exception {
        final StringBuilder sql = new StringBuilder();
        Object object = null;
        try {
            sql.append("select P.NPREGUNTAID AS IDPREGUNTA, VASUNTO AS ASUNTO, NCATEGORIAID AS IDCATEGORIA, VDETALLE AS DETALLE, ");
            sql.append(" NENTIDADID AS IDENTIDAD, VDATOADICIONAL AS DATOADICIONAL, P.VUSUARIOCREACION AS USUCREA, P.VUSUARIOMODIFICACION AS USUMOD, ");
            sql.append(" P.DFECHACREACION AS FECHACREA, P.DFECHAMODIFICACION AS FECHAMOD, NACTIVO AS ESTADO, VRESPUESTA AS RESPUESTA, VMSJUSUARIO2 AS MSJUSU2, ");
            sql.append(" VMSJESPECIALISTA AS MSJESP, NSITUACION AS SITUACION, VMSJMODERADOR AS MSJMOD, VMSJUSUARIO1 AS MSJUSU1  ");
            sql.append(" from TPREGUNTA P ");
            sql.append(" inner join TASIGNACION A on P.NPREGUNTAID=A.NCONOCIMIENTOID  ");
            sql.append(" WHERE A.NCONOCIMIENTOID =:PREGUNTA  AND A.NUSUARIOID=:USUARIO  AND A.NTIPOCONOCIMIENTOID=:TIPOCONOCIMIENTO AND NESTADOID=1");
            
            object = getHibernateTemplate().execute(
                    new HibernateCallback() {
                        @Override
                        public Object doInHibernate(Session session) throws HibernateException {
                            Query query = session.createSQLQuery(sql.toString())
                            .setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP)
                                    .setParameter("PREGUNTA", preguntaid)
                                    .setParameter("USUARIO", usuarioid)
                                    .setParameter("TIPOCONOCIMIENTO", tpoconocimientoid);
                            return query.list();
                        }
                    });
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
        return (List<HashMap>) object;

    }
    
    @Override
    public List<HashMap> obtenerPreguntaxAsig(final BigDecimal preguntaid,final BigDecimal usuarioid, final BigDecimal tpoconocimientoid) throws Exception {
        final StringBuilder sql = new StringBuilder();
        Object object = null;
        try {
            sql.append("select nasignacionid AS IDASIGNACION, ntipoconocimientoid AS TPOCONOCIMIENTO , nconocimientoid AS IDPREGUNTA, nusuarioid AS IDUSUARIO, nestadoid AS ESTADO, VUSUARIOCREACION AS USUCREA, VUSUARIOMODIFICACION AS USUMOD, ");
            sql.append(" DFECHACREACION AS FECHACREA, DFECHAMODIFICACION AS FECHAMOD ");
            sql.append(" from TASIGNACION ");
            sql.append(" WHERE NCONOCIMIENTOID =:PREGUNTA  AND NUSUARIOID=:USUARIO AND NTIPOCONOCIMIENTOID=:TIPOCONOCIMIENTO AND NESTADOID=1");
            
            object = getHibernateTemplate().execute(
                    new HibernateCallback() {
                        @Override
                        public Object doInHibernate(Session session) throws HibernateException {
                            Query query = session.createSQLQuery(sql.toString())
                            .setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP)
                                    .setParameter("PREGUNTA", preguntaid)
                                    .setParameter("USUARIO", usuarioid)
                                    .setParameter("TIPOCONOCIMIENTO", tpoconocimientoid);
                            return query.list();
                        }
                    });
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
        return (List<HashMap>) object;

    }

    @Override
    public List<HashMap>obtenerPerfilxUsuario(final BigDecimal usuarioid) throws Exception {
        final StringBuilder sql = new StringBuilder();
        Object object = null;
        try {
            sql.append("select nperfilid AS PERFIL ");
            sql.append(" from TUSER_PERFIL ");
            sql.append(" WHERE NUSUARIOID =:USUARIO");
            
            object = getHibernateTemplate().execute(
                    new HibernateCallback() {
                        @Override
                        public Object doInHibernate(Session session) throws HibernateException {
                            Query query = session.createSQLQuery(sql.toString())
                            .setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP)
                                    .setParameter("USUARIO", usuarioid);
                            return query.list();
                        }
                    });
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
        return (List<HashMap>) object;
    }
    
    
    
}
