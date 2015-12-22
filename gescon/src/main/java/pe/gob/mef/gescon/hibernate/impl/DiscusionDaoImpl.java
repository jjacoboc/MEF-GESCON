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
import pe.gob.mef.gescon.hibernate.dao.DiscusionDao;
import pe.gob.mef.gescon.hibernate.domain.Tdiscusion;

/**
 *
 * @author JJacobo
 */
@Repository(value = "DiscusionDao")
public class DiscusionDaoImpl extends HibernateDaoSupport implements DiscusionDao {
    
    /**
     * Crea una nueva instancia de SeccionDaoImpl
     *
     * @param sessionFactory
     */
    @Autowired
    public DiscusionDaoImpl(SessionFactory sessionFactory) {
        this.setSessionFactory(sessionFactory);
    }
    
    @Override
    public BigDecimal getNextPK() throws Exception {
        return (BigDecimal) getHibernateTemplate().execute(
            new HibernateCallback() {
                @Override
                public Object doInHibernate(Session session) throws HibernateException {
                    Query query = session.createSQLQuery("SELECT SEQ_TDISCUSION.NEXTVAL FROM DUAL");
                    return query.uniqueResult();
                }
            });
    }
    
    @Override
    public Tdiscusion getTdiscusionById(BigDecimal iddiscusion) throws Exception {
        DetachedCriteria criteria = DetachedCriteria.forClass(Tdiscusion.class);
        criteria.add(Restrictions.eq("ndiscusionid", iddiscusion));
        return (Tdiscusion) DataAccessUtils.uniqueResult(getHibernateTemplate().findByCriteria(criteria));
    }
    
    @Override
    public List<Tdiscusion> getTdiscusiones() throws Exception {
        DetachedCriteria criteria = DetachedCriteria.forClass(Tdiscusion.class);
        criteria.addOrder(Order.desc("dfechacreacion"));
        return (List<Tdiscusion>) getHibernateTemplate().findByCriteria(criteria);
    }
    
    @Override
    public List<Tdiscusion> getTdiscusionesByTconocimiento(BigDecimal idconocimiento) throws Exception {
        DetachedCriteria criteria = DetachedCriteria.forClass(Tdiscusion.class);
        criteria.add(Restrictions.eq("nconocimientoid", idconocimiento));
        criteria.addOrder(Order.desc("dfechacreacion"));
        return (List<Tdiscusion>) getHibernateTemplate().findByCriteria(criteria);
    }

    @Override
    @Transactional(readOnly = false)
    public void saveOrUpdate(final Tdiscusion tdiscusion) throws Exception {
        getHibernateTemplate().saveOrUpdate(tdiscusion);
    }
}
