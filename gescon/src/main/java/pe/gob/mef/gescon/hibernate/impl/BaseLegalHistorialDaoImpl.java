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
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.orm.hibernate4.HibernateCallback;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pe.gob.mef.gescon.hibernate.dao.BaseLegalHistorialDao;
import pe.gob.mef.gescon.hibernate.domain.TbaselegalHist;

/**
 *
 * @author JJacobo
 */
@Repository(value = "BaseLegalHistorialDao")
public class BaseLegalHistorialDaoImpl extends HibernateDaoSupport implements BaseLegalHistorialDao{

    /**
     * Crea una nueva instancia de BaseLegalHistorialDaoImpl
     *
     * @param sessionFactory
     */
    @Autowired
    public BaseLegalHistorialDaoImpl(SessionFactory sessionFactory) {
        this.setSessionFactory(sessionFactory);
    }
    
    @Override
    public BigDecimal getNextPK() throws Exception {
        return (BigDecimal) getHibernateTemplate().execute(
            new HibernateCallback() {
                @Override
                public Object doInHibernate(Session session) throws HibernateException {
                    Query query = session.createSQLQuery("SELECT SEQ_TBASELEGAL_HIST.NEXTVAL FROM DUAL");
                    return query.uniqueResult();
                }
            });
    }

    @Override
    public TbaselegalHist getThistorialById(BigDecimal idhistorial) throws Exception {
        DetachedCriteria criteria = DetachedCriteria.forClass(TbaselegalHist.class);
        criteria.add(Restrictions.eq("nhistorialid", idhistorial));
        return (TbaselegalHist) DataAccessUtils.uniqueResult(getHibernateTemplate().findByCriteria(criteria));
    }

    @Override
    public TbaselegalHist getLastThistorialByTbaselegal(BigDecimal idbaselegal) throws Exception {
        DetachedCriteria proj = DetachedCriteria.forClass(TbaselegalHist.class);
        proj.setProjection(Projections.max("nnumversion"));
        DetachedCriteria criteria = DetachedCriteria.forClass(TbaselegalHist.class);
        criteria.add(Restrictions.eq("nbaselegalid", idbaselegal));
        criteria.add(Property.forName("nnumversion").eq(proj));
        return (TbaselegalHist) DataAccessUtils.uniqueResult(getHibernateTemplate().findByCriteria(criteria));
    }

    @Override
    public List<TbaselegalHist> getThistoriales() throws Exception {
        DetachedCriteria criteria = DetachedCriteria.forClass(TbaselegalHist.class);
        criteria.addOrder(Order.asc("nhistorialid"));
        return (List<TbaselegalHist>) getHibernateTemplate().findByCriteria(criteria);
    }

    @Override
    public List<TbaselegalHist> getThistorialesByTbaselegal(BigDecimal idbaselegal) throws Exception {
        DetachedCriteria criteria = DetachedCriteria.forClass(TbaselegalHist.class);
        criteria.add(Restrictions.eq("nbaselegalid", idbaselegal));
        criteria.addOrder(Order.asc("nnumversion"));
        return (List<TbaselegalHist>) getHibernateTemplate().findByCriteria(criteria);
    }

    @Override
    @Transactional(readOnly = false)
    public void saveOrUpdate(TbaselegalHist thistorial) throws Exception {
        getHibernateTemplate().saveOrUpdate(thistorial);
    }
    
}
