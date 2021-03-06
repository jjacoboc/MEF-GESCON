/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.gob.mef.gescon.hibernate.impl;

import java.math.BigDecimal;
import java.util.List;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;
import pe.gob.mef.gescon.hibernate.dao.EstadoBaseLegalDao;
import pe.gob.mef.gescon.hibernate.domain.MtestadoBaselegal;

/**
 *
 * @author JJacobo
 */
@Repository(value = "EstadoBaseLegalDao")
public class EstadoBaseLegalDaoImpl extends HibernateDaoSupport implements EstadoBaseLegalDao{

    /**
     * Crea una nueva instancia de EstadoBaseLegalDaoImpl
     *
     * @param sessionFactory
     */
    @Autowired
    public EstadoBaseLegalDaoImpl(SessionFactory sessionFactory) {
        this.setSessionFactory(sessionFactory);
    }
    
    @Override
    public List<MtestadoBaselegal> getMtestadosBaselegal() throws Exception {
        DetachedCriteria criteria = DetachedCriteria.forClass(MtestadoBaselegal.class);
        criteria.addOrder(Order.asc("vnombre"));
        return (List<MtestadoBaselegal>) getHibernateTemplate().findByCriteria(criteria);
    }
    
    @Override
    public List<MtestadoBaselegal> getMtestadosBaselegalToLink() throws Exception {
        DetachedCriteria criteria = DetachedCriteria.forClass(MtestadoBaselegal.class);
        criteria.add(Restrictions.eq("nvinculo", BigDecimal.ONE));
        criteria.addOrder(Order.asc("vnombre"));
        return (List<MtestadoBaselegal>) getHibernateTemplate().findByCriteria(criteria);
    }
}
