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
import pe.gob.mef.gescon.hibernate.dao.BaseLegalDao;
import pe.gob.mef.gescon.hibernate.domain.Tbaselegal;

/**
 *
 * @author JJacobo
 */
@Repository(value = "BaseLegalDao")
public class BaseLegalDaoImpl extends HibernateDaoSupport implements BaseLegalDao{

    /**
     * Crea una nueva instancia de BaseLegalDaoImpl
     *
     * @param sessionFactory
     */
    @Autowired
    public BaseLegalDaoImpl(SessionFactory sessionFactory) {
        this.setSessionFactory(sessionFactory);
    }
    
    @Override
    public BigDecimal getNextPK() throws Exception {
        return (BigDecimal) getHibernateTemplate().execute(
            new HibernateCallback() {
                @Override
                public Object doInHibernate(Session session) throws HibernateException {
                    Query query = session.createSQLQuery("SELECT SEQ_TBASELEGAL.NEXTVAL FROM DUAL");
                    return query.uniqueResult();
                }
            });
    }

    @Override
    public List<Tbaselegal> getTbaselegales() throws Exception {
        DetachedCriteria criteria = DetachedCriteria.forClass(Tbaselegal.class);
        criteria.addOrder(Order.desc("dfechacreacion"));
        return (List<Tbaselegal>) getHibernateTemplate().findByCriteria(criteria);
    }
    
    @Override
    public Tbaselegal getTbaselegalById(BigDecimal id) throws Exception {
        DetachedCriteria criteria = DetachedCriteria.forClass(Tbaselegal.class);
        criteria.add(Restrictions.eq("nbaselegalid", id));
        return (Tbaselegal) DataAccessUtils.uniqueResult(getHibernateTemplate().findByCriteria(criteria));
    }

    @Override
    @Transactional(readOnly = false)
    public void saveOrUpdate(Tbaselegal tbaselegal) throws Exception {
        getHibernateTemplate().saveOrUpdate(tbaselegal);
    }
    
}