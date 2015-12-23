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
import pe.gob.mef.gescon.hibernate.dao.CalificacionDao;
import pe.gob.mef.gescon.hibernate.domain.Tcalificacion;

/**
 *
 * @author JJacobo
 */
@Repository(value = "CalificacionDao")
public class CalificacionDaoImpl extends HibernateDaoSupport implements CalificacionDao{

    /**
     * Crea una nueva instancia de CalificacionDaoImpl
     *
     * @param sessionFactory
     */
    @Autowired
    public CalificacionDaoImpl(SessionFactory sessionFactory) {
        this.setSessionFactory(sessionFactory);
    }
    
    @Override
    public BigDecimal getNextPK() throws Exception {
        return (BigDecimal) getHibernateTemplate().execute(
            new HibernateCallback() {
                @Override
                public Object doInHibernate(Session session) throws HibernateException {
                    Query query = session.createSQLQuery("SELECT SEQ_TCALIFICACION.NEXTVAL FROM DUAL");
                    return query.uniqueResult();
                }
            });
    }

    @Override
    public Tcalificacion getTcalificacionById(BigDecimal idcalificacion) throws Exception {
        DetachedCriteria criteria = DetachedCriteria.forClass(Tcalificacion.class);
        criteria.add(Restrictions.eq("ncalificacionid", idcalificacion));
        return (Tcalificacion) DataAccessUtils.uniqueResult(getHibernateTemplate().findByCriteria(criteria));
    }

    @Override
    public List<Tcalificacion> getTcalificaciones() throws Exception {
        DetachedCriteria criteria = DetachedCriteria.forClass(Tcalificacion.class);
        criteria.addOrder(Order.desc("dfechacreacion"));
        return (List<Tcalificacion>) getHibernateTemplate().findByCriteria(criteria);
    }

    @Override
    public List<Tcalificacion> getTcalificacionesByTconocimiento(BigDecimal idconocimiento) throws Exception {
        DetachedCriteria criteria = DetachedCriteria.forClass(Tcalificacion.class);
        criteria.add(Restrictions.eq("nconocimientoid", idconocimiento));
        criteria.addOrder(Order.desc("dfechacreacion"));
        return (List<Tcalificacion>) getHibernateTemplate().findByCriteria(criteria);
    }

    @Override
    @Transactional(readOnly = false)
    public void saveOrUpdate(Tcalificacion tcalificacion) throws Exception {
        getHibernateTemplate().saveOrUpdate(tcalificacion);
    }
    
    @Override
    @Transactional(readOnly = false)
    public void delete(final BigDecimal idcalificacion) throws Exception {
        getHibernateTemplate().execute(
                new HibernateCallback() {
                    @Override
                    public Object doInHibernate(Session session) throws HibernateException {
                        StringBuilder sql = new StringBuilder();
                        sql.append("DELETE FROM TCALIFICACION WHERE NCALIFICACIONID = ");
                        sql.append(idcalificacion.toString());
                        Query query = session.createSQLQuery(sql.toString());
                        return query.executeUpdate();
                    }
                });
    }
}
