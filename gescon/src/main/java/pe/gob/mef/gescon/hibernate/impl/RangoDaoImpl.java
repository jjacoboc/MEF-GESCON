/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.gob.mef.gescon.hibernate.impl;

import java.util.List;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;
import pe.gob.mef.gescon.hibernate.dao.RangoDao;
import pe.gob.mef.gescon.hibernate.domain.Mtrango;

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
    public List<Mtrango> getMtrangos() throws Exception {
        DetachedCriteria criteria = DetachedCriteria.forClass(Mtrango.class);
        criteria.addOrder(Order.asc("vnombre"));
        return (List<Mtrango>) getHibernateTemplate().findByCriteria(criteria);
    }
}
