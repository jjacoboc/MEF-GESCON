/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.gob.mef.gescon.hibernate.impl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.orm.hibernate4.HibernateCallback;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pe.gob.mef.gescon.common.Constante;
import pe.gob.mef.gescon.hibernate.dao.BaseLegalDao;
import pe.gob.mef.gescon.hibernate.domain.Tbaselegal;

/**
 *
 * @author JJacobo
 */
@Repository(value = "BaseLegalDao")
public class BaseLegalDaoImpl extends HibernateDaoSupport implements BaseLegalDao {

    /**
     * Crea una nueva instancia de BaseLegalDaoImpl
     *
     * @param sessionFactory
     */
    @Autowired
    public BaseLegalDaoImpl(SessionFactory sessionFactory) {
        this.setSessionFactory(sessionFactory);
    }

    @Override
    public BigDecimal getNextPK() throws Exception {
        return (BigDecimal) getHibernateTemplate().execute(
                new HibernateCallback() {
                    @Override
                    public Object doInHibernate(Session session) throws HibernateException {
                        Query query = session.createSQLQuery("SELECT SEQ_TBASELEGAL.NEXTVAL FROM DUAL");
                        return query.uniqueResult();
                    }
                });
    }

    @Override
    public List<Tbaselegal> getTbaselegales() throws Exception {
        DetachedCriteria criteria = DetachedCriteria.forClass(Tbaselegal.class);
        criteria.addOrder(Order.desc("dfechacreacion"));
        return (List<Tbaselegal>) getHibernateTemplate().findByCriteria(criteria);
    }

    @Override
    public Tbaselegal getTbaselegalById(BigDecimal id) throws Exception {
        DetachedCriteria criteria = DetachedCriteria.forClass(Tbaselegal.class);
        criteria.add(Restrictions.eq("nbaselegalid", id));
        return (Tbaselegal) DataAccessUtils.uniqueResult(getHibernateTemplate().findByCriteria(criteria));
    }

    @Override
    public List<HashMap> getTbaselegalesLinkedById(final BigDecimal id) throws Exception {
        final StringBuilder sql = new StringBuilder();
        Object object = null;
        try {
            sql.append("SELECT a.nbaselegalid AS ID, a.vnumero AS NUMERO, a.vnombre AS NOMBRE, a.vsumilla AS SUMILLA, ");
            sql.append("       a.ncategoriaid AS IDCATEGORIA, b.vnombre AS CATEGORIA, a.dfechapublicacion AS FECHA, ");
            sql.append("       a.nestadoid AS IDESTADO, c.vnombre AS ESTADO ");
            sql.append("FROM TBASELEGAL a ");
            sql.append("INNER JOIN MTCATEGORIA b ON a.ncategoriaid = b.ncategoriaid ");
            sql.append("INNER JOIN MTESTADO_BASELEGAL c ON a.nestadoid = c.nestadoid ");
            sql.append("WHERE a.nactivo = :ACTIVO ");
            //sql.append("AND a.nestadoid IN (:ESTADO_REGISTRADO,:ESTADO_PUBLICADO,:ESTADO_CONCORDADO) ");
            sql.append("AND a.nbaselegalid IN(SELECT d.nbaselegalvinculadaid FROM TVINCULO_BASELEGAL d WHERE d.nbaselegalid = :ID)");
            sql.append("ORDER BY a.vnumero ");

            object = getHibernateTemplate().execute(
                    new HibernateCallback() {
                        @Override
                        public Object doInHibernate(Session session) throws HibernateException {
                            Query query = session.createSQLQuery(sql.toString());
                            query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
                            query.setParameter("ACTIVO", BigDecimal.ONE);
//                            query.setParameter("ESTADO_REGISTRADO", Constante.ESTADO_BASELEGAL_REGISTRADO);
//                            query.setParameter("ESTADO_PUBLICADO", Constante.ESTADO_BASELEGAL_PUBLICADO);
//                            query.setParameter("ESTADO_CONCORDADO", Constante.ESTADO_BASELEGAL_CONCORDADO);
                            query.setParameter("ID", id);
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
    public List<HashMap> getTbaselegalesNotLinkedById(final BigDecimal id) throws Exception {
        final StringBuilder sql = new StringBuilder();
        Object object = null;
        try {
            sql.append("SELECT a.nbaselegalid AS ID, a.vnumero AS NUMERO, a.vnombre AS NOMBRE, a.vsumilla AS SUMILLA, ");
            sql.append("       a.ncategoriaid AS IDCATEGORIA, b.vnombre AS CATEGORIA, a.dfechapublicacion AS FECHA, ");
            sql.append("       a.nestadoid AS IDESTADO, c.vnombre AS ESTADO ");
            sql.append("FROM TBASELEGAL a ");
            sql.append("INNER JOIN MTCATEGORIA b ON a.ncategoriaid = b.ncategoriaid ");
            sql.append("INNER JOIN MTESTADO_BASELEGAL c ON a.nestadoid = c.nestadoid ");
            sql.append("WHERE a.nactivo = :ACTIVO ");
            sql.append("AND a.nestadoid IN (:ESTADO_PUBLICADO, :ESTADO_CONCORDADO, :ESTADO_MODIFICADA) ");
            if(id != null) {
                sql.append("AND a.nbaselegalid <> :ID ");
                sql.append("AND a.nbaselegalid NOT IN(SELECT d.nbaselegalvinculadaid FROM TVINCULO_BASELEGAL d WHERE d.nbaselegalid = :ID) ");
            }
            sql.append("ORDER BY a.vnumero ");

            object = getHibernateTemplate().execute(
                    new HibernateCallback() {
                        @Override
                        public Object doInHibernate(Session session) throws HibernateException {
                            Query query = session.createSQLQuery(sql.toString());
                            query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
                            query.setParameter("ACTIVO", BigDecimal.ONE);
                            query.setParameter("ESTADO_PUBLICADO", Constante.ESTADO_BASELEGAL_PUBLICADO);
                            query.setParameter("ESTADO_CONCORDADO", Constante.ESTADO_BASELEGAL_CONCORDADO);
                            query.setParameter("ESTADO_MODIFICADA", Constante.ESTADO_BASELEGAL_MODIFICADA);
                            if(id != null) {
                                query.setParameter("ID", id);
                            }
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
    @Transactional(readOnly = false)
    public void saveOrUpdate(Tbaselegal tbaselegal) throws Exception {
        getHibernateTemplate().saveOrUpdate(tbaselegal);
    }

}
