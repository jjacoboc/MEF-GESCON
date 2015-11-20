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
import org.springframework.orm.hibernate4.HibernateCallback;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pe.gob.mef.gescon.hibernate.dao.MaestroDetalleDao;
import pe.gob.mef.gescon.hibernate.domain.Mtmaestro;
import pe.gob.mef.gescon.hibernate.domain.Tmaestrodetalle;

/**
 *
 * @author JJacobo
 */
@Repository(value = "MaestroDetalleDao")
public class MaestroDetalleDaoImpl extends HibernateDaoSupport implements MaestroDetalleDao{

    /**
     * Crea una nueva instancia de MaestroDetalleDaoImpl
     *
     * @param sessionFactory
     */
    @Autowired
    public MaestroDetalleDaoImpl(SessionFactory sessionFactory) {
        this.setSessionFactory(sessionFactory);
    }
    
    @Override
    public BigDecimal getNextPK() throws Exception {
        return (BigDecimal) getHibernateTemplate().execute(
            new HibernateCallback() {
                @Override
                public Object doInHibernate(Session session) throws HibernateException {
                    Query query = session.createSQLQuery("SELECT SEQ_TMAESTRODETALLE.NEXTVAL FROM DUAL");
                    return query.uniqueResult();
                }
            });
    }
    
    @Override
    public List<Tmaestrodetalle> getDetalleByMaestro(Mtmaestro mtmaestro) throws Exception {
        DetachedCriteria criteria = DetachedCriteria.forClass(Tmaestrodetalle.class);
        criteria.add(Restrictions.eq("mtmaestro.nmaestroid", mtmaestro.getNmaestroid()));
        criteria.addOrder(Order.asc("vnombre"));
        return (List<Tmaestrodetalle>) getHibernateTemplate().findByCriteria(criteria);
    }

    @Override
    @Transactional(readOnly = false)
    public void saveOrUpdate(Tmaestrodetalle tmaestrodetalle) throws Exception {
        getHibernateTemplate().saveOrUpdate(tmaestrodetalle);
    }
    
}
