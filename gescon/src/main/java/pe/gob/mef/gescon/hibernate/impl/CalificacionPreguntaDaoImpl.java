/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.gob.mef.gescon.hibernate.impl;

import java.math.BigDecimal;
import java.util.List;
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
import pe.gob.mef.gescon.hibernate.dao.CalificacionPreguntaDao;
import pe.gob.mef.gescon.hibernate.domain.TcalificacionPregunta;

/**
 *
 * @author JJacobo
 */
@Repository(value = "CalificacionPreguntaDao")
public class CalificacionPreguntaDaoImpl extends HibernateDaoSupport implements CalificacionPreguntaDao{

    /**
     * Crea una nueva instancia de CalificacionPreguntaDaoImpl
     *
     * @param sessionFactory
     */
    @Autowired
    public CalificacionPreguntaDaoImpl(SessionFactory sessionFactory) {
        this.setSessionFactory(sessionFactory);
    }
    
    @Override
    public BigDecimal getNextPK() throws Exception {
        return (BigDecimal) getHibernateTemplate().execute(
            new HibernateCallback() {
                @Override
                public Object doInHibernate(Session session) throws HibernateException {
                    Query query = session.createSQLQuery("SELECT SEQ_TCALIFICACION_PREGUNTA.NEXTVAL FROM DUAL");
                    return query.uniqueResult();
                }
            });
    }

    @Override
    public TcalificacionPregunta getTcalificacionById(BigDecimal idcalificacion) throws Exception {
        DetachedCriteria criteria = DetachedCriteria.forClass(TcalificacionPregunta.class);
        criteria.add(Restrictions.eq("ncalificacionid", idcalificacion));
        return (TcalificacionPregunta) DataAccessUtils.uniqueResult(getHibernateTemplate().findByCriteria(criteria));
    }

    @Override
    public List<TcalificacionPregunta> getTcalificaciones() throws Exception {
        DetachedCriteria criteria = DetachedCriteria.forClass(TcalificacionPregunta.class);
        criteria.addOrder(Order.desc("dfechacreacion"));
        return (List<TcalificacionPregunta>) getHibernateTemplate().findByCriteria(criteria);
    }

    @Override
    public List<TcalificacionPregunta> getTcalificacionesByTconocimiento(BigDecimal idconocimiento) throws Exception {
        DetachedCriteria criteria = DetachedCriteria.forClass(TcalificacionPregunta.class);
        criteria.add(Restrictions.eq("npreguntaid", idconocimiento));
        criteria.addOrder(Order.desc("dfechacreacion"));
        return (List<TcalificacionPregunta>) getHibernateTemplate().findByCriteria(criteria);
    }

    @Override
    @Transactional(readOnly = false)
    public void saveOrUpdate(TcalificacionPregunta tcalificacion) throws Exception {
        getHibernateTemplate().saveOrUpdate(tcalificacion);
    }

    @Override
    @Transactional(readOnly = false)
    public void delete(final BigDecimal idcalificacion) throws Exception {
        getHibernateTemplate().execute(
                new HibernateCallback() {
                    @Override
                    public Object doInHibernate(Session session) throws HibernateException {
                        StringBuilder sql = new StringBuilder();
                        sql.append("DELETE FROM TCALIFICACION_PREGUNTA WHERE NCALIFICACIONID = ");
                        sql.append(idcalificacion.toString());
                        Query query = session.createSQLQuery(sql.toString());
                        return query.executeUpdate();
                    }
                });
    }
    
}
