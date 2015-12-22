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
import pe.gob.mef.gescon.hibernate.dao.DiscusionSeccionHistDao;
import pe.gob.mef.gescon.hibernate.domain.TdiscusionSeccionHist;

/**
 *
 * @author JJacobo
 */
@Repository(value = "DiscusionSeccionHistDao")
public class DiscusionSeccionHistDaoImpl extends HibernateDaoSupport implements DiscusionSeccionHistDao{

    /**
     * Crea una nueva instancia de DiscusionSeccionHistDaoImpl
     *
     * @param sessionFactory
     */
    @Autowired
    public DiscusionSeccionHistDaoImpl(SessionFactory sessionFactory) {
        this.setSessionFactory(sessionFactory);
    }
    
    @Override
    public BigDecimal getNextPK() throws Exception {
        return (BigDecimal) getHibernateTemplate().execute(
            new HibernateCallback() {
                @Override
                public Object doInHibernate(Session session) throws HibernateException {
                    Query query = session.createSQLQuery("SELECT SEQ_TDISCUSION_SECCION_HIST.NEXTVAL FROM DUAL");
                    return query.uniqueResult();
                }
            });
    }

    @Override
    public TdiscusionSeccionHist getTseccionHistById(BigDecimal idseccionhist) throws Exception {
        DetachedCriteria criteria = DetachedCriteria.forClass(TdiscusionSeccionHist.class);
        criteria.add(Restrictions.eq("ndiscusionseccionhid", idseccionhist));
        return (TdiscusionSeccionHist) DataAccessUtils.uniqueResult(getHibernateTemplate().findByCriteria(criteria));
    }

    @Override
    public List<TdiscusionSeccionHist> getTseccionesHist() throws Exception {
        DetachedCriteria criteria = DetachedCriteria.forClass(TdiscusionSeccionHist.class);
        criteria.addOrder(Order.desc("dfechacreacion"));
        return (List<TdiscusionSeccionHist>) getHibernateTemplate().findByCriteria(criteria);
    }

    @Override
    public List<TdiscusionSeccionHist> getTseccionesHistByTdiscusionHist(BigDecimal iddiscusionhist) throws Exception {
        DetachedCriteria criteria = DetachedCriteria.forClass(TdiscusionSeccionHist.class);
        criteria.add(Restrictions.eq("ndiscusionhid", iddiscusionhist));
        criteria.addOrder(Order.desc("dfechacreacion"));
        return (List<TdiscusionSeccionHist>) getHibernateTemplate().findByCriteria(criteria);
    }

    @Override
    @Transactional(readOnly = false)
    public void saveOrUpdate(TdiscusionSeccionHist tdiscusionSeccionHist) throws Exception {
        getHibernateTemplate().saveOrUpdate(tdiscusionSeccionHist);
    }
    
}
