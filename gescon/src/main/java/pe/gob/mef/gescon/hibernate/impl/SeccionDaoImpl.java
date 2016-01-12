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
import pe.gob.mef.gescon.hibernate.dao.SeccionDao;
import pe.gob.mef.gescon.hibernate.domain.Tseccion;

/**
 *
 * @author JJacobo
 */
@Repository(value = "SeccionDao")
public class SeccionDaoImpl extends HibernateDaoSupport implements SeccionDao {
    
    /**
     * Crea una nueva instancia de SeccionDaoImpl
     *
     * @param sessionFactory
     */
    @Autowired
    public SeccionDaoImpl(SessionFactory sessionFactory) {
        this.setSessionFactory(sessionFactory);
    }
    
    @Override
    public BigDecimal getNextPK() throws Exception {
        return (BigDecimal) getHibernateTemplate().execute(
            new HibernateCallback() {
                @Override
                public Object doInHibernate(Session session) throws HibernateException {
                    Query query = session.createSQLQuery("SELECT SEQ_TSECCION.NEXTVAL FROM DUAL");
                    return query.uniqueResult();
                }
            });
    }
    
    @Override
    public Tseccion getTseccionById(BigDecimal idseccion) throws Exception {
        DetachedCriteria criteria = DetachedCriteria.forClass(Tseccion.class);
        criteria.add(Restrictions.eq("nseccionid", idseccion));
        return (Tseccion) DataAccessUtils.uniqueResult(getHibernateTemplate().findByCriteria(criteria));
    }
    
    @Override
    public List<Tseccion> getTsecciones() throws Exception {
        DetachedCriteria criteria = DetachedCriteria.forClass(Tseccion.class);
        criteria.addOrder(Order.asc("norden"));
        return (List<Tseccion>) getHibernateTemplate().findByCriteria(criteria);
    }
    
    @Override
    public List<Tseccion> getTseccionesByTconocimiento(BigDecimal idconocimiento) throws Exception {
        DetachedCriteria criteria = DetachedCriteria.forClass(Tseccion.class);
        criteria.add(Restrictions.eq("nconocimientoid", idconocimiento));
        criteria.addOrder(Order.asc("norden"));
        return (List<Tseccion>) getHibernateTemplate().findByCriteria(criteria);
    }

    @Override
    @Transactional(readOnly = false)
    public void saveOrUpdate(final Tseccion tseccion) throws Exception {
        getHibernateTemplate().saveOrUpdate(tseccion);
    }
    
    @Override
    @Transactional(readOnly = false)
    public void deleteTseccionesByTconocimiento(final BigDecimal idconocimiento) throws Exception {
        getHibernateTemplate().execute(
                new HibernateCallback() {
                    @Override
                    public Object doInHibernate(Session session) throws HibernateException {
                        StringBuilder sql = new StringBuilder();
                        sql.append("DELETE FROM TSECCION WHERE NCONOCIMIENTOID = ");
                        sql.append(idconocimiento.toString());
                        Query query = session.createSQLQuery(sql.toString());
                        return query.executeUpdate();
                    }
                });
    }
}
