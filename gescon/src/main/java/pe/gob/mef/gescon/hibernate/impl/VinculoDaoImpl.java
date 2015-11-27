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
import org.springframework.orm.hibernate4.HibernateCallback;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pe.gob.mef.gescon.hibernate.dao.VinculoDao;
import pe.gob.mef.gescon.hibernate.domain.Tvinculo;

/**
 *
 * @author JJacobo
 */
@Repository(value = "VinculoDao")
public class VinculoDaoImpl extends HibernateDaoSupport implements VinculoDao {

    /**
     * Crea una nueva instancia de VinculoDaoImpl
     *
     * @param sessionFactory
     */
    @Autowired
    public VinculoDaoImpl(SessionFactory sessionFactory) {
        this.setSessionFactory(sessionFactory);
    }
    
    @Override
    public BigDecimal getNextPK() throws Exception {
        return (BigDecimal) getHibernateTemplate().execute(
            new HibernateCallback() {
                @Override
                public Object doInHibernate(Session session) throws HibernateException {
                    Query query = session.createSQLQuery("SELECT SEQ_TVINCULO.NEXTVAL FROM DUAL");
                    return query.uniqueResult();
                }
            });
    }

    @Override
    public List<Tvinculo> getTvinculos() throws Exception {
        DetachedCriteria criteria = DetachedCriteria.forClass(Tvinculo.class);
        return (List<Tvinculo>) getHibernateTemplate().findByCriteria(criteria);
    }

    @Override
    public List<Tvinculo> getTvinculosByTconocimiento(BigDecimal idconocimiento) throws Exception {
        DetachedCriteria criteria = DetachedCriteria.forClass(Tvinculo.class);
        criteria.add(Restrictions.eq("nconocimientoid", idconocimiento));
        return (List<Tvinculo>) getHibernateTemplate().findByCriteria(criteria);
    }

    @Override
    public List<Tvinculo> getTvinculosByTconocimientoAndTtipoconocimiento(BigDecimal idconocimiento, BigDecimal idtipoconocimiento) throws Exception {
        DetachedCriteria criteria = DetachedCriteria.forClass(Tvinculo.class);
        criteria.add(Restrictions.eq("nconocimientoid", idconocimiento));
        criteria.add(Restrictions.eq("ntipoconocimientovinc", idtipoconocimiento));
        return (List<Tvinculo>) getHibernateTemplate().findByCriteria(criteria);
    }

    @Override
    public Tvinculo getTvinculoById(BigDecimal idvinculo) throws Exception {
        DetachedCriteria criteria = DetachedCriteria.forClass(Tvinculo.class);
        criteria.add(Restrictions.eq("nvinculoid", idvinculo));
        return (Tvinculo) getHibernateTemplate().findByCriteria(criteria);
    }

    @Override
    @Transactional(readOnly = false)
    public void saveOrUpdate(Tvinculo tvinculo) throws Exception {
        getHibernateTemplate().saveOrUpdate(tvinculo);
    }
    
    @Override
    @Transactional(readOnly = false)
    public void deleteByTconocimiento(final BigDecimal idconocimiento) throws Exception {
        getHibernateTemplate().execute(
                new HibernateCallback() {
                    @Override
                    public Object doInHibernate(Session session) throws HibernateException {
                        StringBuilder sql = new StringBuilder();
                        sql.append("DELETE FROM TVINCULO WHERE NCONOCIMIENTOID = ");
                        sql.append(idconocimiento.toString());
                        Query query = session.createSQLQuery(sql.toString());
                        return query.executeUpdate();
                    }
                });
    }
}
