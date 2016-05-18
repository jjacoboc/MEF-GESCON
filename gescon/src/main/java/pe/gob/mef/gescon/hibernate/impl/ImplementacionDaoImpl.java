/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.gob.mef.gescon.hibernate.impl;

import java.math.BigDecimal;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.orm.hibernate4.HibernateCallback;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pe.gob.mef.gescon.hibernate.dao.ImplementacionDao;
import pe.gob.mef.gescon.hibernate.domain.Timplementacion;

/**
 *
 * @author JJacobo
 */
@Repository(value = "ImplementacionDao")
public class ImplementacionDaoImpl extends HibernateDaoSupport implements ImplementacionDao{
    
    /**
     * Crea una nueva instancia de ConocimientoDaoImpl
     *
     * @param sessionFactory
     */
    @Autowired
    public ImplementacionDaoImpl(SessionFactory sessionFactory) {
        this.setSessionFactory(sessionFactory);
    }
    
    @Override
    public BigDecimal getNextPK() throws Exception {
        return (BigDecimal) getHibernateTemplate().execute(
            new HibernateCallback() {
                @Override
                public Object doInHibernate(Session session) throws HibernateException {
                    Query query = session.createSQLQuery("SELECT SEQ_TIMPLEMENTACION.NEXTVAL FROM DUAL");
                    return query.uniqueResult();
                }
            });
    }
    
    @Override
    public Timplementacion getTimplementacionById(BigDecimal id) throws Exception {
        DetachedCriteria criteria = DetachedCriteria.forClass(Timplementacion.class);
        criteria.add(Restrictions.eq("nimplementacionid",id));
        return (Timplementacion) DataAccessUtils.uniqueResult(getHibernateTemplate().findByCriteria(criteria));
    }
    
    @Override
    public Timplementacion getTimplementacionByConocimiento(BigDecimal idconocimiento) throws Exception {
        DetachedCriteria criteria = DetachedCriteria.forClass(Timplementacion.class);
        criteria.add(Restrictions.eq("nconocimientoid",idconocimiento));
        return (Timplementacion) DataAccessUtils.uniqueResult(getHibernateTemplate().findByCriteria(criteria));
    }
    
    @Override
    @Transactional(readOnly = false)
    public void saveOrUpdate(final Timplementacion timplementacion) throws Exception {
        getHibernateTemplate().saveOrUpdate(timplementacion);
    }
}
