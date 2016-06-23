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
import pe.gob.mef.gescon.hibernate.dao.DiscusionHistDao;
import pe.gob.mef.gescon.hibernate.domain.TdiscusionHist;

/**
 *
 * @author JJacobo
 */
@Repository(value = "DiscusionHistDao")
public class DiscusionHistDaoImpl extends HibernateDaoSupport implements DiscusionHistDao {

    /**
     * Crea una nueva instancia de DiscusionHistDaoImpl
     *
     * @param sessionFactory
     */
    @Autowired
    public DiscusionHistDaoImpl(SessionFactory sessionFactory) {
        this.setSessionFactory(sessionFactory);
    }
    
    @Override
    public BigDecimal getNextPK() throws Exception {
        return (BigDecimal) getHibernateTemplate().execute(
            new HibernateCallback() {
                @Override
                public Object doInHibernate(Session session) throws HibernateException {
                    Query query = session.createSQLQuery("SELECT SEQ_TDISCUSION_HIST.NEXTVAL FROM DUAL");
                    return query.uniqueResult();
                }
            });
    }

    @Override
    public TdiscusionHist getTdiscusionHistById(BigDecimal ndiscusionhid) throws Exception {
        DetachedCriteria criteria = DetachedCriteria.forClass(TdiscusionHist.class);
        criteria.add(Restrictions.eq("ndiscusionhid", ndiscusionhid));
        return (TdiscusionHist) DataAccessUtils.uniqueResult(getHibernateTemplate().findByCriteria(criteria));
    }

    @Override
    public TdiscusionHist getTdiscusionHistByTconocimiento(BigDecimal idconocimiento) throws Exception {
        DetachedCriteria proj = DetachedCriteria.forClass(TdiscusionHist.class);
        proj.setProjection(Projections.max("nnumversion"));
        proj.add(Restrictions.eq("nconocimientoid", idconocimiento));
        DetachedCriteria criteria = DetachedCriteria.forClass(TdiscusionHist.class);
        criteria.add(Restrictions.eq("nconocimientoid", idconocimiento));
        criteria.add(Property.forName("nnumversion").eq(proj));
        return (TdiscusionHist) DataAccessUtils.uniqueResult(getHibernateTemplate().findByCriteria(criteria));
    }

    @Override
    @Transactional(readOnly = false)
    public void saveOrUpdate(TdiscusionHist tdiscusionHist) throws Exception {
        getHibernateTemplate().saveOrUpdate(tdiscusionHist);
    }
    
}
