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
import org.springframework.transaction.annotation.Transactional;
import pe.gob.mef.gescon.hibernate.dao.VinculoBaselegalHistorialDao;
import pe.gob.mef.gescon.hibernate.domain.TvinculoBaselegalHist;

/**
 *
 * @author JJacobo
 */
@Repository(value = "VinculoBaselegalHistorialDao")
public class VinculoBaselegalHistorialDaoImpl extends HibernateDaoSupport implements VinculoBaselegalHistorialDao{

    /**
     * Crea una nueva instancia de VinculoBaselegalHistorialDaoImpl
     *
     * @param sessionFactory
     */
    @Autowired
    public VinculoBaselegalHistorialDaoImpl(SessionFactory sessionFactory) {
        this.setSessionFactory(sessionFactory);
    }
    
    @Override
    public BigDecimal getNextPK() throws Exception {
        return (BigDecimal) getHibernateTemplate().execute(
            new HibernateCallback() {
                @Override
                public Object doInHibernate(Session session) throws HibernateException {
                    Query query = session.createSQLQuery("SELECT SEQ_TVINCULO_BASELELGAL_HIST.NEXTVAL FROM DUAL");
                    return query.uniqueResult();
                }
            });
    }

    @Override
    public TvinculoBaselegalHist getTvinculoHistById(BigDecimal idvinculoh) throws Exception {
        DetachedCriteria criteria = DetachedCriteria.forClass(TvinculoBaselegalHist.class);
        criteria.add(Restrictions.eq("nvinculohistid", idvinculoh));
        return (TvinculoBaselegalHist) DataAccessUtils.uniqueResult(getHibernateTemplate().findByCriteria(criteria));
    }

    @Override
    public List<TvinculoBaselegalHist> getTvinculoHists() throws Exception {
        DetachedCriteria criteria = DetachedCriteria.forClass(TvinculoBaselegalHist.class);
        return (List<TvinculoBaselegalHist>) getHibernateTemplate().findByCriteria(criteria);
    }

    @Override
    public List<TvinculoBaselegalHist> getTvinculoHistsByTbaselegalHist(BigDecimal idhistorial) throws Exception {
        DetachedCriteria criteria = DetachedCriteria.forClass(TvinculoBaselegalHist.class);
        criteria.add(Restrictions.eq("nhistorialid", idhistorial));
        return (List<TvinculoBaselegalHist>) getHibernateTemplate().findByCriteria(criteria);
    }

    @Override
    @Transactional(readOnly = false)
    public void saveOrUpdate(TvinculoBaselegalHist tvinculoHist) throws Exception {
        getHibernateTemplate().saveOrUpdate(tvinculoHist);
    }
}
