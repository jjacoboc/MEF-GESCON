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
import pe.gob.mef.gescon.hibernate.dao.TipoConocimientoDao;
import pe.gob.mef.gescon.hibernate.domain.MttipoConocimiento;

/**
 *
 * @author JJacobo
 */
@Repository(value = "TipoConocimientoDao")
public class TipoConocimientoDaoImpl extends HibernateDaoSupport implements TipoConocimientoDao{

    /**
     * Crea una nueva instancia de TipoConocimientoDaoImpl
     *
     * @param sessionFactory
     */
    @Autowired
    public TipoConocimientoDaoImpl(SessionFactory sessionFactory) {
        this.setSessionFactory(sessionFactory);
    }

    @Override
    public List<MttipoConocimiento> getMttipoConocimientos() throws Exception {
        DetachedCriteria criteria = DetachedCriteria.forClass(MttipoConocimiento.class);
        criteria.addOrder(Order.asc("vnombre"));
        return (List<MttipoConocimiento>) getHibernateTemplate().findByCriteria(criteria);
    }
}
