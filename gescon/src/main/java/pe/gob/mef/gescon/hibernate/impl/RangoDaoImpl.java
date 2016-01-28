/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.gob.mef.gescon.hibernate.impl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateCallback;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pe.gob.mef.gescon.hibernate.dao.RangoDao;
import pe.gob.mef.gescon.hibernate.domain.Mtrango;
import pe.gob.mef.gescon.hibernate.domain.Mtuser;

/**
 *
 * @author JJacobo
 */
@Repository(value = "RangoDao")
public class RangoDaoImpl extends HibernateDaoSupport implements RangoDao{
    
    /**
     * Crea una nueva instancia de RangoDaoImpl
     *
     * @param sessionFactory
     */
    @Autowired
    public RangoDaoImpl(SessionFactory sessionFactory) {
        this.setSessionFactory(sessionFactory);
    }
    
    @Override
    public BigDecimal getNextPK() throws Exception {
        return (BigDecimal) getHibernateTemplate().execute(
            new HibernateCallback() {
                @Override
                public Object doInHibernate(Session session) throws HibernateException {
                    Query query = session.createSQLQuery("SELECT SEQ_MTRANGO.NEXTVAL FROM DUAL");
                    return query.uniqueResult();
                }
            });
    }

    @Override
    public List<Mtrango> getMtrangos() throws Exception {
        DetachedCriteria criteria = DetachedCriteria.forClass(Mtrango.class);
        criteria.addOrder(Order.asc("vnombre"));
        return (List<Mtrango>) getHibernateTemplate().findByCriteria(criteria);
    }
    
    @Override
    public List<HashMap> getTipoRangoByMaestro(BigDecimal maestroid) throws Exception {
        final StringBuilder sql = new StringBuilder();
        sql.append("SELECT ndetalleid AS ID, vnombre AS NOMBRE ");
        sql.append("FROM TMAESTRODETALLE WHERE nactivo = 1 AND nmaestroid = ").append(maestroid).append(" ");
        return (List<HashMap>) getHibernateTemplate().execute(
                new HibernateCallback() {
                    @Override
                    public Object doInHibernate(Session session) throws HibernateException {
                        Query query = session.createSQLQuery(sql.toString());
                        query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
                        return query.list();
                    }
                });
    }
    
    @Override
    public List<Mtrango> getMtrangosByTipo(BigDecimal id) throws Exception {
        DetachedCriteria criteria = DetachedCriteria.forClass(Mtrango.class);
        criteria.add(Restrictions.eq("ntiponormaid", id));
        criteria.addOrder(Order.asc("vnombre"));
        return (List<Mtrango>) getHibernateTemplate().findByCriteria(criteria);
    }
    
    @Override
    public List<Mtrango> getMtrangosActivosByTipo(BigDecimal id) throws Exception {
        DetachedCriteria criteria = DetachedCriteria.forClass(Mtrango.class);
        criteria.add(Restrictions.eq("ntiponormaid", id));
        criteria.add(Restrictions.eq("nactivo", BigDecimal.ONE));
        criteria.addOrder(Order.asc("vnombre"));
        return (List<Mtrango>) getHibernateTemplate().findByCriteria(criteria);
    }
    
    @Override
    @Transactional(readOnly = false)
    public void saveOrUpdate(Mtrango mtrango) throws Exception {
        getHibernateTemplate().saveOrUpdate(mtrango);
    }
}
