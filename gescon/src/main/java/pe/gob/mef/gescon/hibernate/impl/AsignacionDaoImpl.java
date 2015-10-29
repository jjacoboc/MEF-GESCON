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
import pe.gob.mef.gescon.hibernate.dao.AsignacionDao;
import pe.gob.mef.gescon.hibernate.domain.Mtuser;
import pe.gob.mef.gescon.hibernate.domain.Tasignacion;

/**
 *
 * @author JJacobo
 */
@Repository(value = "AsignacionDao")
public class AsignacionDaoImpl extends HibernateDaoSupport implements AsignacionDao{

    /**
     * Crea una nueva instancia de AsignacionDaoImpl
     *
     * @param sessionFactory
     */
    @Autowired
    public AsignacionDaoImpl(SessionFactory sessionFactory) {
        this.setSessionFactory(sessionFactory);
    }
    
    @Override
    public BigDecimal getNextPK() throws Exception {
        return (BigDecimal) getHibernateTemplate().execute(
            new HibernateCallback() {
                @Override
                public Object doInHibernate(Session session) throws HibernateException {
                    Query query = session.createSQLQuery("SELECT SEQ_TASIGNACION.NEXTVAL FROM DUAL");
                    return query.uniqueResult();
                }
            });
    }
    
    @Override
    public BigDecimal getNumberNotificationsByMtuser(Mtuser mtuser) throws Exception {
        final StringBuilder sql = new StringBuilder();
        sql.append("SELECT COUNT(1) FROM TASIGNACION t ");
        sql.append("WHERE t.nusuarioid = ").append(mtuser.getNusuarioid()).append(" ");
        sql.append("AND (t.dfecharecepcion is null and t.dfechaatencion is null) ");
        sql.append("OR (t.dfecharecepcion is not null and t.dfechaatencion is null) ");
        
        return (BigDecimal) getHibernateTemplate().execute(
            new HibernateCallback() {
                @Override
                public Object doInHibernate(Session session) throws HibernateException {
                    Query query = session.createSQLQuery(sql.toString());
                    return query.uniqueResult();
                }
            });
    }
    
    @Override
    public BigDecimal getNumberNotificationsAssignedByMtuser(Mtuser mtuser) throws Exception {
        final StringBuilder sql = new StringBuilder();
        sql.append("SELECT COUNT(1) FROM TASIGNACION t ");
        sql.append("WHERE t.nusuarioid = ").append(mtuser.getNusuarioid()).append(" ");
        sql.append("AND (t.dfechaasignacion is not null and t.dfecharecepcion is null and t.dfechaatencion is null) ");
        
        return (BigDecimal) getHibernateTemplate().execute(
            new HibernateCallback() {
                @Override
                public Object doInHibernate(Session session) throws HibernateException {
                    Query query = session.createSQLQuery(sql.toString());
                    return query.uniqueResult();
                }
            });
    }
    
    @Override
    public BigDecimal getNumberNotificationsReceivedByMtuser(Mtuser mtuser) throws Exception {
        final StringBuilder sql = new StringBuilder();
        sql.append("SELECT COUNT(1) FROM TASIGNACION t ");
        sql.append("WHERE t.nusuarioid = ").append(mtuser.getNusuarioid()).append(" ");
        sql.append("AND (t.dfechaasignacion is not null and t.dfecharecepcion is not null and t.dfechaatencion is null) ");
        
        return (BigDecimal) getHibernateTemplate().execute(
            new HibernateCallback() {
                @Override
                public Object doInHibernate(Session session) throws HibernateException {
                    Query query = session.createSQLQuery(sql.toString());
                    return query.uniqueResult();
                }
            });
    }
    
    @Override
    public BigDecimal getNumberNotificationsServedByMtuser(Mtuser mtuser) throws Exception {
        final StringBuilder sql = new StringBuilder();
        sql.append("SELECT COUNT(1) FROM TASIGNACION t ");
        sql.append("WHERE t.nusuarioid = ").append(mtuser.getNusuarioid()).append(" ");
        sql.append("AND (t.dfechaasignacion is not null and t.dfecharecepcion is not null and t.dfechaatencion is not null) ");
        
        return (BigDecimal) getHibernateTemplate().execute(
            new HibernateCallback() {
                @Override
                public Object doInHibernate(Session session) throws HibernateException {
                    Query query = session.createSQLQuery(sql.toString());
                    return query.uniqueResult();
                }
            });
    }
    
    @Override
    @Transactional(readOnly = false)
    public void saveOrUpdate(Tasignacion tasignacion) throws Exception {
        getHibernateTemplate().saveOrUpdate(tasignacion);
    }
    
}
