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
import pe.gob.mef.gescon.hibernate.dao.SeccionHistDao;
import pe.gob.mef.gescon.hibernate.domain.TseccionHist;

/**
 *
 * @author JJacobo
 */
@Repository(value = "SeccionHistDao")
public class SeccionHistDaoImpl extends HibernateDaoSupport implements SeccionHistDao {
    
    /**
     * Crea una nueva instancia de SeccionHistDaoImpl
     *
     * @param sessionFactory
     */
    @Autowired
    public SeccionHistDaoImpl(SessionFactory sessionFactory) {
        this.setSessionFactory(sessionFactory);
    }
    
    @Override
    public BigDecimal getNextPK() throws Exception {
        return (BigDecimal) getHibernateTemplate().execute(
            new HibernateCallback() {
                @Override
                public Object doInHibernate(Session session) throws HibernateException {
                    Query query = session.createSQLQuery("SELECT SEQ_TSECCION_HIST.NEXTVAL FROM DUAL");
                    return query.uniqueResult();
                }
            });
    }
    
    @Override
    public TseccionHist getTseccionHistById(BigDecimal idseccionh) throws Exception {
        DetachedCriteria criteria = DetachedCriteria.forClass(TseccionHist.class);
        criteria.add(Restrictions.eq("id.nseccionhid", idseccionh));
        return (TseccionHist) DataAccessUtils.uniqueResult(getHibernateTemplate().findByCriteria(criteria));
    }
    
    @Override
    public List<TseccionHist> getTseccionHists() throws Exception {
        DetachedCriteria criteria = DetachedCriteria.forClass(TseccionHist.class);
        criteria.addOrder(Order.asc("norden"));
        return (List<TseccionHist>) getHibernateTemplate().findByCriteria(criteria);
    }
    
    @Override
    public List<TseccionHist> getTseccionHistsByThistorial(BigDecimal idhistorial) throws Exception {
        DetachedCriteria criteria = DetachedCriteria.forClass(TseccionHist.class);
        criteria.add(Restrictions.eq("id.nhistorialid", idhistorial));
        criteria.addOrder(Order.asc("norden"));
        return (List<TseccionHist>) getHibernateTemplate().findByCriteria(criteria);
    }

    @Override
    @Transactional(readOnly = false)
    public void saveOrUpdate(final TseccionHist tseccionHist) throws Exception {
        getHibernateTemplate().saveOrUpdate(tseccionHist);
    }
}
