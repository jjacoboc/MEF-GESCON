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
import pe.gob.mef.gescon.hibernate.dao.DiscusionSeccionDao;
import pe.gob.mef.gescon.hibernate.domain.TdiscusionSeccion;

/**
 *
 * @author JJacobo
 */
@Repository(value = "DiscusionSeccionDao")
public class DiscusionSeccionDaoImpl extends HibernateDaoSupport implements DiscusionSeccionDao {

    /**
     * Crea una nueva instancia de DiscusionSeccionDaoImpl
     *
     * @param sessionFactory
     */
    @Autowired
    public DiscusionSeccionDaoImpl(SessionFactory sessionFactory) {
        this.setSessionFactory(sessionFactory);
    }
    
    @Override
    public BigDecimal getNextPK() throws Exception {
        return (BigDecimal) getHibernateTemplate().execute(
            new HibernateCallback() {
                @Override
                public Object doInHibernate(Session session) throws HibernateException {
                    Query query = session.createSQLQuery("SELECT SEQ_TDISCUSION_SECCION.NEXTVAL FROM DUAL");
                    return query.uniqueResult();
                }
            });
    }

    @Override
    public TdiscusionSeccion getTseccionById(BigDecimal idseccion) throws Exception {
        DetachedCriteria criteria = DetachedCriteria.forClass(TdiscusionSeccion.class);
        criteria.add(Restrictions.eq("ndiscusionseccionid", idseccion));
        return (TdiscusionSeccion) DataAccessUtils.uniqueResult(getHibernateTemplate().findByCriteria(criteria));
    }

    @Override
    public List<TdiscusionSeccion> getTsecciones() throws Exception {
        DetachedCriteria criteria = DetachedCriteria.forClass(TdiscusionSeccion.class);
        criteria.addOrder(Order.desc("dfechacreacion"));
        return (List<TdiscusionSeccion>) getHibernateTemplate().findByCriteria(criteria);
    }

    @Override
    public List<TdiscusionSeccion> getTseccionesByTdiscusion(BigDecimal iddiscusion) throws Exception {
        DetachedCriteria criteria = DetachedCriteria.forClass(TdiscusionSeccion.class);
        criteria.add(Restrictions.eq("ndiscusionid", iddiscusion));
        criteria.addOrder(Order.desc("dfechacreacion"));
        return (List<TdiscusionSeccion>) getHibernateTemplate().findByCriteria(criteria);
    }

    @Override
    @Transactional(readOnly = false)
    public void saveOrUpdate(TdiscusionSeccion tdiscusionSeccion) throws Exception {
        getHibernateTemplate().saveOrUpdate(tdiscusionSeccion);
    }
    
}
