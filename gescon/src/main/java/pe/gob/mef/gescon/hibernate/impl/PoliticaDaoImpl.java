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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateCallback;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pe.gob.mef.gescon.hibernate.dao.PoliticaDao;

import pe.gob.mef.gescon.hibernate.domain.Mtpolitica;

/**
 *
 * @author SOPORTE
 */



@Repository(value = "PoliticaDao")

public class PoliticaDaoImpl extends HibernateDaoSupport implements PoliticaDao{
    
    
     @Autowired
    public PoliticaDaoImpl(SessionFactory sessionFactory) {
        this.setSessionFactory(sessionFactory);
    }

    @Override
    public BigDecimal getNextPK() throws Exception {
        return (BigDecimal) getHibernateTemplate().execute(
            new HibernateCallback() {
                @Override
                public Object doInHibernate(Session session) throws HibernateException {
                    Query query = session.createSQLQuery("SELECT SEQ_MTPOLITICA.NEXTVAL FROM DUAL");
                    return query.uniqueResult();
                }
            });
    }

    @Override
    public List<Mtpolitica> getMtpoliticas() throws Exception {
        DetachedCriteria criteria = DetachedCriteria.forClass(Mtpolitica.class);
        return (List<Mtpolitica>) getHibernateTemplate().findByCriteria(criteria);
    }

    @Override
    @Transactional(readOnly = false)
    public void saveOrUpdate(Mtpolitica mtpolotica) throws Exception {
        getHibernateTemplate().saveOrUpdate(mtpolotica);
    }
    
}
