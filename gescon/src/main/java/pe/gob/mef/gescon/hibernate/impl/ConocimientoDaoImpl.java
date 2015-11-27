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
import pe.gob.mef.gescon.hibernate.dao.ConocimientoDao;
import pe.gob.mef.gescon.hibernate.domain.Tconocimiento;

/**
 *
 * @author JJacobo
 */
@Repository(value = "ConocimientoDao")
public class ConocimientoDaoImpl extends HibernateDaoSupport implements ConocimientoDao{

    /**
     * Crea una nueva instancia de ConocimientoDaoImpl
     *
     * @param sessionFactory
     */
    @Autowired
    public ConocimientoDaoImpl(SessionFactory sessionFactory) {
        this.setSessionFactory(sessionFactory);
    }
    
    @Override
    public BigDecimal getNextPK() throws Exception {
        return (BigDecimal) getHibernateTemplate().execute(
            new HibernateCallback() {
                @Override
                public Object doInHibernate(Session session) throws HibernateException {
                    Query query = session.createSQLQuery("SELECT SEQ_TCONOCIMIENTO.NEXTVAL FROM DUAL");
                    return query.uniqueResult();
                }
            });
    }

    @Override
    public List<Tconocimiento> getTconocimientos() throws Exception {
        DetachedCriteria criteria = DetachedCriteria.forClass(Tconocimiento.class);
        criteria.addOrder(Order.desc("nconocimientoid"));
        return (List<Tconocimiento>) getHibernateTemplate().findByCriteria(criteria);
    }
    
    @Override
    public Tconocimiento getTconocimientoById(BigDecimal id) throws Exception {
        DetachedCriteria criteria = DetachedCriteria.forClass(Tconocimiento.class);
        criteria.add(Restrictions.eq("nconocimientoid",id));
        return (Tconocimiento) DataAccessUtils.uniqueResult(getHibernateTemplate().findByCriteria(criteria));
    }

    @Override
    @Transactional(readOnly = false)
    public void saveOrUpdate(final Tconocimiento tconocimiento) throws Exception {
        getHibernateTemplate().saveOrUpdate(tconocimiento);
    }

    @Override
    @Transactional(readOnly = false)
    public void delete(Tconocimiento tconocimiento) throws Exception {
        getHibernateTemplate().delete(tconocimiento);
    }
    
}
