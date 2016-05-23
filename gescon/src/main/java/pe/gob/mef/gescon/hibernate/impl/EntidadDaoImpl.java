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
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateCallback;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pe.gob.mef.gescon.hibernate.dao.EntidadDao;
import pe.gob.mef.gescon.hibernate.domain.Mtentidad;
import pe.gob.mef.gescon.hibernate.domain.Mtuser;

/**
 *
 * @author JJacobo
 */
@Repository(value = "EntidadDao")
public class EntidadDaoImpl extends HibernateDaoSupport implements EntidadDao{

    /**
     * Crea una nueva instancia de EntidadDaoImpl
     *
     * @param sessionFactory
     */
    @Autowired
    public EntidadDaoImpl(SessionFactory sessionFactory) {
        this.setSessionFactory(sessionFactory);
    }
    
    @Override
    public BigDecimal getNextPK() throws Exception {
        return (BigDecimal) getHibernateTemplate().execute(
            new HibernateCallback() {
                @Override
                public Object doInHibernate(Session session) throws HibernateException {
                    Query query = session.createSQLQuery("SELECT SEQ_MTENTIDAD.NEXTVAL FROM DUAL");
                    return query.uniqueResult();
                }
            });
    }
    
    @Override
    public List<Mtentidad> getMtentidades() throws Exception {
        DetachedCriteria criteria = DetachedCriteria.forClass(Mtentidad.class);
        return (List<Mtentidad>) getHibernateTemplate().findByCriteria(criteria);
    }
    
    @Override
    public List<HashMap>  getEntidadesUbigeo() throws Exception {
        final StringBuilder sql = new StringBuilder();
        sql.append("select nentidadid AS ID, vcodigoentidad AS CODIGO, vnombre AS NOMBRE, vdescripcion AS DES, vusuariocreacion AS USUC, dfechacreacion AS FECHAC, vusuariomodificacion AS USUM, dfechamodificacion AS FECHAM, ");
        sql.append("nactivo AS ACTIVO,  (select vdescripcion from MTUBIGEO_ENTIDAD where cdepartamento=E.VDEPARTAMENTO and cprovincia='00' and cdistrito='00') AS DEPARTAMENTO, ");
        sql.append("(select vdescripcion from MTUBIGEO_ENTIDAD where cdepartamento=E.VDEPARTAMENTO and cprovincia=E.VPROVINCIA and cdistrito='00') AS PROVINCIA, ");
        sql.append("(select vdescripcion from MTUBIGEO_ENTIDAD where cdepartamento=E.VDEPARTAMENTO and cprovincia=E.VPROVINCIA and cdistrito=E.VDISTRITO) AS DISTRITO, ");
        sql.append("(select vdescripcion from mttipo_entidad where ntipoentidadid=E.NTIPOID) AS TIPO from mtentidad E ");

        return (List<HashMap>) getHibernateTemplate().execute(
                new HibernateCallback() {
                    @Override
                    public Object doInHibernate(Session session) throws HibernateException {
                        Query query = session.createSQLQuery(sql.toString());
                        query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
                        return query.list();
                    }
                });
    }
    
    public List<HashMap> getTipos() throws Exception {
        final StringBuilder sql = new StringBuilder();
        Object object = null;
        try {
            sql.append("SELECT ntipoentidadid AS COD, vdescripcion AS DES ");
            sql.append("FROM MTTIPO_ENTIDAD ");

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
    @Transactional(readOnly = false)
    public void saveOrUpdate(Mtentidad mtentidad) throws Exception {
        getHibernateTemplate().saveOrUpdate(mtentidad);
    }
    
}
