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
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.orm.hibernate4.HibernateCallback;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;
import pe.gob.mef.gescon.hibernate.dao.VinculoHistDao;
import pe.gob.mef.gescon.hibernate.domain.TvinculoHist;

/**
 *
 * @author JJacobo
 */
@Repository(value = "VinculoHistDao")
public class VinculoHistDaoImpl extends HibernateDaoSupport implements VinculoHistDao{

    /**
     * Crea una nueva instancia de SeccionHistDaoImpl
     *
     * @param sessionFactory
     */
    @Autowired
    public VinculoHistDaoImpl(SessionFactory sessionFactory) {
        this.setSessionFactory(sessionFactory);
    }
    
    @Override
    public BigDecimal getNextPK() throws Exception {
        return (BigDecimal) getHibernateTemplate().execute(
            new HibernateCallback() {
                @Override
                public Object doInHibernate(Session session) throws HibernateException {
                    Query query = session.createSQLQuery("SELECT SEQ_TVINCULO_HIST.NEXTVAL FROM DUAL");
                    return query.uniqueResult();
                }
            });
    }

    @Override
    public TvinculoHist getTvinculoHistById(BigDecimal idvinculoh) throws Exception {
        DetachedCriteria criteria = DetachedCriteria.forClass(TvinculoHist.class);
        criteria.add(Restrictions.eq("id.nvinculohid", idvinculoh));
        return (TvinculoHist) DataAccessUtils.uniqueResult(getHibernateTemplate().findByCriteria(criteria));
    }

    @Override
    public List<TvinculoHist> getTvinculoHists() throws Exception {
        DetachedCriteria criteria = DetachedCriteria.forClass(TvinculoHist.class);
        return (List<TvinculoHist>) getHibernateTemplate().findByCriteria(criteria);
    }

    @Override
    public List<TvinculoHist> getTvinculoHistsByThistorial(BigDecimal idhistorial) throws Exception {
        DetachedCriteria criteria = DetachedCriteria.forClass(TvinculoHist.class);
        criteria.add(Restrictions.eq("id.nhistorialid", idhistorial));
        return (List<TvinculoHist>) getHibernateTemplate().findByCriteria(criteria);
    }

    @Override
    public void saveOrUpdate(TvinculoHist tvinculoHist) throws Exception {
        getHibernateTemplate().saveOrUpdate(tvinculoHist);
    }
    
}
