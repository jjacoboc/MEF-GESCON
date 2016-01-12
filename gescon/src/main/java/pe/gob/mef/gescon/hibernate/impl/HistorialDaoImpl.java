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
import pe.gob.mef.gescon.hibernate.dao.HistorialDao;
import pe.gob.mef.gescon.hibernate.domain.Thistorial;

/**
 *
 * @author JJacobo
 */
@Repository(value = "HistorialDao")
public class HistorialDaoImpl extends HibernateDaoSupport implements HistorialDao  {
    
    /**
     * Crea una nueva instancia de HistorialDaoImpl
     *
     * @param sessionFactory
     */
    @Autowired
    public HistorialDaoImpl(SessionFactory sessionFactory) {
        this.setSessionFactory(sessionFactory);
    }
    
    @Override
    public BigDecimal getNextPK() throws Exception {
        return (BigDecimal) getHibernateTemplate().execute(
            new HibernateCallback() {
                @Override
                public Object doInHibernate(Session session) throws HibernateException {
                    Query query = session.createSQLQuery("SELECT SEQ_THISTORIAL.NEXTVAL FROM DUAL");
                    return query.uniqueResult();
                }
            });
    }
    
    @Override
    public Thistorial getThistorialById(BigDecimal idhistorial) throws Exception {
        DetachedCriteria criteria = DetachedCriteria.forClass(Thistorial.class);
        criteria.add(Restrictions.eq("id.nhistorialid", idhistorial));
        return (Thistorial) DataAccessUtils.uniqueResult(getHibernateTemplate().findByCriteria(criteria));
    }
    
    @Override
    public Thistorial getLastThistorialByTconocimiento(BigDecimal idconocimiento) throws Exception {
        DetachedCriteria proj = DetachedCriteria.forClass(Thistorial.class);
        proj.setProjection(Projections.max("nnumversion"));
        proj.add(Restrictions.eq("id.nconocimientoid", idconocimiento));
        DetachedCriteria criteria = DetachedCriteria.forClass(Thistorial.class);
        criteria.add(Restrictions.eq("id.nconocimientoid", idconocimiento));
        criteria.add(Property.forName("nnumversion").eq(proj));
        return (Thistorial) DataAccessUtils.uniqueResult(getHibernateTemplate().findByCriteria(criteria));
    }
    
    @Override
    public List<Thistorial> getThistoriales() throws Exception {
        DetachedCriteria criteria = DetachedCriteria.forClass(Thistorial.class);
        criteria.addOrder(Order.asc("id.nhistorialid"));
        return (List<Thistorial>) getHibernateTemplate().findByCriteria(criteria);
    }
    
    @Override
    public List<Thistorial> getThistorialesByTconocimiento(BigDecimal idconocimiento) throws Exception {
        DetachedCriteria criteria = DetachedCriteria.forClass(Thistorial.class);
        criteria.add(Restrictions.eq("id.nconocimientoid", idconocimiento));
        criteria.addOrder(Order.asc("nnumversion"));
        return (List<Thistorial>) getHibernateTemplate().findByCriteria(criteria);
    }

    @Override
    @Transactional(readOnly = false)
    public void saveOrUpdate(final Thistorial thistorial) throws Exception {
        getHibernateTemplate().saveOrUpdate(thistorial);
    }
}
