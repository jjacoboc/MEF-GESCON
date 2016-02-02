/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.gob.mef.gescon.hibernate.impl;

import java.util.HashMap;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateCallback;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;
import pe.gob.mef.gescon.hibernate.dao.UbigeoDao;

/**
 *
 * @author JJacobo
 */
@Repository(value = "UbigeoDao")
public class UbigeoDaoImpl extends HibernateDaoSupport implements UbigeoDao{
    
    /**
     * Crea una nueva instancia de UbigeoDaoImpl
     *
     * @param sessionFactory
     */
    @Autowired
    public UbigeoDaoImpl(SessionFactory sessionFactory) {
        this.setSessionFactory(sessionFactory);
    }
    
    @Override
    public List<HashMap> getDepartamentos() throws Exception {
        final StringBuilder sql = new StringBuilder();
        Object object = null;
        try {
            sql.append("SELECT DISTINCT t.vcoddep AS COD, t.vdescdep AS DES ");
            sql.append("FROM MTUBIGEO t ");
            sql.append("ORDER BY t.vcoddep ");

            object = getHibernateTemplate().execute(
                    new HibernateCallback() {
                        @Override
                        public Object doInHibernate(Session session) throws HibernateException {
                            Query query = session.createSQLQuery(sql.toString());
                            query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
                            return query.list();
                        }
                    });
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
        return (List<HashMap>) object;
    }
    
    @Override
    public List<HashMap> getProvinciasPorDepartamento(final String coddep) throws Exception {
        final StringBuilder sql = new StringBuilder();
        Object object = null;
        try {
            sql.append("SELECT DISTINCT t.vcodprov AS COD, t.vdescprov AS DES ");
            sql.append("FROM MTUBIGEO t ");
            sql.append("WHERE t.vcoddep = :CODDEP ");
            sql.append("ORDER BY t.vcodprov ");

            object = getHibernateTemplate().execute(
                    new HibernateCallback() {
                        @Override
                        public Object doInHibernate(Session session) throws HibernateException {
                            Query query = session.createSQLQuery(sql.toString());
                            query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
                            query.setParameter("CODDEP", coddep);
                            return query.list();
                        }
                    });
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
        return (List<HashMap>) object;
    }
    
    @Override
    public List<HashMap> getDistritosPorProvincia(final String coddep, final String codprov) throws Exception {
        final StringBuilder sql = new StringBuilder();
        Object object = null;
        try {
            sql.append("SELECT DISTINCT t.vcoddist AS COD, t.vdescdist AS DES ");
            sql.append("FROM MTUBIGEO t ");
            sql.append("WHERE t.vcoddep = :CODDEP ");
            sql.append("AND t.vcodprov = :CODPROV ");
            sql.append("ORDER BY t.vcoddist ");

            object = getHibernateTemplate().execute(
                    new HibernateCallback() {
                        @Override
                        public Object doInHibernate(Session session) throws HibernateException {
                            Query query = session.createSQLQuery(sql.toString());
                            query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
                            query.setParameter("CODDEP", coddep);
                            query.setParameter("CODPROV", codprov);
                            return query.list();
                        }
                    });
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
        return (List<HashMap>) object;
    }
}
