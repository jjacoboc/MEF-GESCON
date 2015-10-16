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
import pe.gob.mef.gescon.hibernate.dao.SituacionDao;
import pe.gob.mef.gescon.hibernate.domain.Mtsituacion;

/**
 *
 * @author JJacobo
 */
@Repository(value = "SituacionDao")
public class SituacionDaoImpl extends HibernateDaoSupport implements SituacionDao{
    
    /**
     * Crea una nueva instancia de SituacionDaoImpl
     *
     * @param sessionFactory
     */
    @Autowired
    public SituacionDaoImpl(SessionFactory sessionFactory) {
        this.setSessionFactory(sessionFactory);
    }

    @Override
    public List<Mtsituacion> getMtsituacions() throws Exception {
        DetachedCriteria criteria = DetachedCriteria.forClass(Mtsituacion.class);
        return (List<Mtsituacion>) getHibernateTemplate().findByCriteria(criteria);
    }
}
