/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.gob.mef.gescon.hibernate.impl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.orm.hibernate4.HibernateCallback;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pe.gob.mef.gescon.hibernate.dao.ContenidoDao;
import pe.gob.mef.gescon.hibernate.dao.PreguntaDao;
import pe.gob.mef.gescon.hibernate.domain.Tconocimiento;
import pe.gob.mef.gescon.hibernate.domain.Tpregunta;

/**
 *
 * @author JJacobo
 */
@Repository(value = "ContenidoDao")
public class ContenidoDaoImpl extends HibernateDaoSupport implements ContenidoDao {

    /**
     * Crea una nueva instancia de ContenidoDaoImpl
     *
     * @param sessionFactory
     */
    @Autowired
    public ContenidoDaoImpl(SessionFactory sessionFactory) {
        this.setSessionFactory(sessionFactory);
    }

    @Override
    public BigDecimal getNextPK() throws Exception {
        return (BigDecimal) getHibernateTemplate().execute(
                new HibernateCallback() {
                    @Override
                    public Object doInHibernate(Session session) throws HibernateException {
                        Query query = session.createSQLQuery("SELECT SEQ_TCONOCIMIENTO.NEXTVAL FROM DUAL");
                        return query.uniqueResult();
                    }
                });
    }

    @Override
    public List<Tconocimiento> getContenidos() throws Exception {
        DetachedCriteria criteria = DetachedCriteria.forClass(Tconocimiento.class);
        criteria.add(Restrictions.eq("ntipoconocimientoid", BigDecimal.valueOf(Long.parseLong("4"))));
        criteria.addOrder(Order.desc("dfechacreacion"));
        return (List<Tconocimiento>) getHibernateTemplate().findByCriteria(criteria);
    }
    
    @Override
    public Tconocimiento getTcontenidoById(BigDecimal idtipo,BigDecimal id) throws Exception {
        DetachedCriteria criteria = DetachedCriteria.forClass(Tconocimiento.class);
        criteria.add(Restrictions.eq("ntipoconocimientoid",idtipo));
        criteria.add(Restrictions.eq("nconocimientoid",id));
        return (Tconocimiento) DataAccessUtils.uniqueResult(getHibernateTemplate().findByCriteria(criteria));
    }
    
    @Override
    public List<HashMap> obtenerContenidoxAsig(final BigDecimal contenidoid,final BigDecimal usuarioid, final BigDecimal tpoconocimientoid) throws Exception {
        final StringBuilder sql = new StringBuilder();
        Object object = null;
        try {
            sql.append("select nasignacionid AS IDASIGNACION, ntipoconocimientoid AS TPOCONOCIMIENTO , nconocimientoid AS IDPREGUNTA, nusuarioid AS IDUSUARIO, nestadoid AS ESTADO, VUSUARIOCREACION AS USUCREA, VUSUARIOMODIFICACION AS USUMOD, ");
            sql.append(" DFECHACREACION AS FECHACREA, DFECHAMODIFICACION AS FECHAMOD, DFECHAASIGNACION as FECHAASIG, DFECHAATENCION AS FECHAATEN, DFECHARECEPCION AS FECHARECEP ");
            sql.append(" from TASIGNACION ");
            sql.append(" WHERE NCONOCIMIENTOID =:BASE  AND NUSUARIOID=:USUARIO AND NTIPOCONOCIMIENTOID=:TIPOCONOCIMIENTO AND NESTADOID=1");
            
            object = getHibernateTemplate().execute(
                    new HibernateCallback() {
                        @Override
                        public Object doInHibernate(Session session) throws HibernateException {
                            Query query = session.createSQLQuery(sql.toString())
                            .setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP)
                                    .setParameter("BASE", contenidoid)
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
    @Transactional(readOnly = false)
    public void saveOrUpdate(Tconocimiento tconocimiento) throws Exception {
        getHibernateTemplate().saveOrUpdate(tconocimiento);
    }

}
