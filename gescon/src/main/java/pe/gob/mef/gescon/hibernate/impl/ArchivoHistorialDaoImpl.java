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
import pe.gob.mef.gescon.hibernate.dao.ArchivoHistorialDao;
import pe.gob.mef.gescon.hibernate.domain.TarchivoHist;
import pe.gob.mef.gescon.hibernate.domain.TbaselegalHist;

/**
 *
 * @author JJacobo
 */
@Repository(value = "ArchivoHistorialDao")
public class ArchivoHistorialDaoImpl  extends HibernateDaoSupport implements ArchivoHistorialDao{

    /**
     * Crea una nueva instancia de ArchivoHistorialDaoImpl
     *
     * @param sessionFactory
     */
    @Autowired
    public ArchivoHistorialDaoImpl(SessionFactory sessionFactory) {
        this.setSessionFactory(sessionFactory);
    }
    
    @Override
    public BigDecimal getNextPK() throws Exception {
        return (BigDecimal) getHibernateTemplate().execute(
            new HibernateCallback() {
                @Override
                public Object doInHibernate(Session session) throws HibernateException {
                    Query query = session.createSQLQuery("SELECT SEQ_TARCHIVO_HIST.NEXTVAL FROM DUAL");
                    return query.uniqueResult();
                }
            });
    }

    @Override
    public List<TarchivoHist> getTarchivosHistByTbaselegalHist(TbaselegalHist tbaselegal) throws Exception {
        DetachedCriteria criteria = DetachedCriteria.forClass(TarchivoHist.class);
        criteria.add(Restrictions.eq("nhistorialid", tbaselegal.getNhistorialid()));
        criteria.add(Restrictions.eq("nbaselegalid", tbaselegal.getNbaselegalid()));
        criteria.addOrder(Order.asc("nversion"));
        return (List<TarchivoHist>) getHibernateTemplate().findByCriteria(criteria);
    }

    @Override
    public TarchivoHist getLastTarchivoHistByTbaselegalHist(TbaselegalHist tbaselegal) throws Exception {
        DetachedCriteria proj = DetachedCriteria.forClass(TarchivoHist.class);
        proj.setProjection(Projections.max("nversion"));
        DetachedCriteria criteria = DetachedCriteria.forClass(TarchivoHist.class);
        criteria.add(Restrictions.eq("nhistorialid", tbaselegal.getNhistorialid()));
        criteria.add(Restrictions.eq("nbaselegalid", tbaselegal.getNbaselegalid()));
        criteria.add(Property.forName("nversion").eq(proj));
        return (TarchivoHist) DataAccessUtils.uniqueResult(getHibernateTemplate().findByCriteria(criteria));
    }

    @Override
    @Transactional(readOnly = false)
    public void saveOrUpdate(TarchivoHist tarchivo) throws Exception {
        getHibernateTemplate().saveOrUpdate(tarchivo);
    }
    
}
