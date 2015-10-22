/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.gob.mef.gescon.hibernate.impl;

import java.math.BigDecimal;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateCallback;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pe.gob.mef.gescon.hibernate.dao.VinculoBaseLegalDao;
import pe.gob.mef.gescon.hibernate.domain.Tbaselegal;
import pe.gob.mef.gescon.hibernate.domain.TvinculoBaselegal;

/**
 *
 * @author JJacobo
 */
@Repository(value = "VinculoBaseLegalDao")
public class VinculoBaseLegalDaoImpl extends HibernateDaoSupport implements VinculoBaseLegalDao {

    /**
     * Crea una nueva instancia de VinculoBaseLegalDaoImpl
     *
     * @param sessionFactory
     */
    @Autowired
    public VinculoBaseLegalDaoImpl(SessionFactory sessionFactory) {
        this.setSessionFactory(sessionFactory);
    }

    @Override
    public BigDecimal getNextPK() throws Exception {
        return (BigDecimal) getHibernateTemplate().execute(
                new HibernateCallback() {
                    @Override
                    public Object doInHibernate(Session session) throws HibernateException {
                        Query query = session.createSQLQuery("SELECT SEQ_TVINCULO_BASELEGAL.NEXTVAL FROM DUAL");
                        return query.uniqueResult();
                    }
                });
    }

    @Override
    @Transactional(readOnly = false)
    public void saveOrUpdate(TvinculoBaselegal tvinculoBaselegal) throws Exception {
        getHibernateTemplate().saveOrUpdate(tvinculoBaselegal);
    }
    
    @Override
    @Transactional(readOnly = false)
    public void deleteByBaseLegal(final Tbaselegal tbaselegal) throws Exception {
        getHibernateTemplate().execute(
                new HibernateCallback() {
                    @Override
                    public Object doInHibernate(Session session) throws HibernateException {
                        StringBuilder sql = new StringBuilder();
                        sql.append("DELETE FROM TVINCULO_BASELEGAL WHERE NBASELEGALID = ");
                        sql.append(tbaselegal.getNbaselegalid().toString());
                        Query query = session.createSQLQuery(sql.toString());
                        return query.executeUpdate();
                    }
                });
    }
}
