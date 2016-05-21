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
import pe.gob.mef.gescon.hibernate.dao.UbigeoEntidadDao;

/**
 *
 * @author JJacobo
 */
@Repository(value = "UbigeoEntidadDao")
public class UbigeoEntidadDaoImpl extends HibernateDaoSupport implements UbigeoEntidadDao{
    
    /**
     * Crea una nueva instancia de UbigeoEntidadDaoImpl
     *
     * @param sessionFactory
     */
    @Autowired
    public UbigeoEntidadDaoImpl(SessionFactory sessionFactory) {
        this.setSessionFactory(sessionFactory);
    }
    
    @Override
    public List<HashMap> getDepartamentos() throws Exception {
        final StringBuilder sql = new StringBuilder();
        Object object = null;
        try {
            sql.append("SELECT DISTINCT t.CDEPARTAMENTO AS COD, t.VDESCRIPCION AS DES ");
            sql.append("FROM MTUBIGEO_ENTIDAD t  where cProvincia=00 and cDistrito=00");
            sql.append("ORDER BY t.CDEPARTAMENTO ");

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
            sql.append("SELECT DISTINCT t.CPROVINCIA AS COD, t.VDESCRIPCION AS DES ");
            sql.append("FROM MTUBIGEO_ENTIDAD t ");
            sql.append("WHERE t.CDEPARTAMENTO = :CODDEP and t.CPROVINCIA != '00' and t.cDistrito ='00'");
            sql.append("ORDER BY t.CPROVINCIA ");

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
            sql.append("SELECT DISTINCT t.CDISTRITO AS COD, t.VDESCRIPCION AS DES ");
            sql.append("FROM MTUBIGEO_ENTIDAD t ");
            sql.append("WHERE t.CDEPARTAMENTO = :CODDEP ");
            sql.append("AND t.CPROVINCIA = :CODPROV and t.cDistrito!='00'");
            sql.append("ORDER BY t.CDISTRITO ");

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
