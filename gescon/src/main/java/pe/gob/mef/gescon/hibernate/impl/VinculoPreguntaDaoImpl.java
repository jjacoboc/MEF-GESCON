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
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateCallback;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import pe.gob.mef.gescon.hibernate.dao.VinculoPreguntaDao;

import pe.gob.mef.gescon.hibernate.domain.TvinculoPregunta;

/**
 *
 * @author JJacobo
 */
@Repository(value = "VinculoPreguntaDao")
public class VinculoPreguntaDaoImpl extends HibernateDaoSupport implements VinculoPreguntaDao {

    /**
     * Crea una nueva instancia de VinculoDaoImpl
     *
     * @param sessionFactory
     */
    @Autowired
    public VinculoPreguntaDaoImpl(SessionFactory sessionFactory) {
        this.setSessionFactory(sessionFactory);
    }
    
    @Override
    public BigDecimal getNextPK() throws Exception {
        return (BigDecimal) getHibernateTemplate().execute(
            new HibernateCallback() {
                @Override
                public Object doInHibernate(Session session) throws HibernateException {
                    Query query = session.createSQLQuery("SELECT SEQ_TVINCULO_PREGUNTA.NEXTVAL FROM DUAL");
                    return query.uniqueResult();
                }
            });
    }

    @Override
    public List<TvinculoPregunta> getTvinculos() throws Exception {
        DetachedCriteria criteria = DetachedCriteria.forClass(TvinculoPregunta.class);
        return (List<TvinculoPregunta>) getHibernateTemplate().findByCriteria(criteria);
    }

    @Override
    public List<TvinculoPregunta> getTvinculosByTconocimiento(BigDecimal idconocimiento) throws Exception {
        DetachedCriteria criteria = DetachedCriteria.forClass(TvinculoPregunta.class);
        criteria.add(Restrictions.eq("npreguntaid", idconocimiento));
        return (List<TvinculoPregunta>) getHibernateTemplate().findByCriteria(criteria);
    }

    @Override
    public List<TvinculoPregunta> getTvinculosByTconocimientoAndTtipoconocimiento(BigDecimal idconocimiento, BigDecimal idtipoconocimiento) throws Exception {
        DetachedCriteria criteria = DetachedCriteria.forClass(TvinculoPregunta.class);
        criteria.add(Restrictions.eq("npreguntaid", idconocimiento));
        criteria.add(Restrictions.eq("ntipoconocimientovinc", idtipoconocimiento));
        return (List<TvinculoPregunta>) getHibernateTemplate().findByCriteria(criteria);
    }

    @Override
    public TvinculoPregunta getTvinculoById(BigDecimal idvinculo) throws Exception {
        DetachedCriteria criteria = DetachedCriteria.forClass(TvinculoPregunta.class);
        criteria.add(Restrictions.eq("nvinculoid", idvinculo));
        return (TvinculoPregunta) getHibernateTemplate().findByCriteria(criteria);
    }

    @Override
    @Transactional(readOnly = false)
    public void saveOrUpdate(TvinculoPregunta tvinculopregunta) throws Exception {
        getHibernateTemplate().saveOrUpdate(tvinculopregunta);
    }
    
    @Override
    @Transactional(readOnly = false)
    public void deleteByTconocimiento(final BigDecimal idconocimiento) throws Exception {
        getHibernateTemplate().execute(
                new HibernateCallback() {
                    @Override
                    public Object doInHibernate(Session session) throws HibernateException {
                        StringBuilder sql = new StringBuilder();
                        sql.append("DELETE FROM TVINCULO_PREGUNTA WHERE NCONOCIMIENTOID = ");
                        sql.append(idconocimiento.toString());
                        Query query = session.createSQLQuery(sql.toString());
                        return query.executeUpdate();
                    }
                });
    }
}
