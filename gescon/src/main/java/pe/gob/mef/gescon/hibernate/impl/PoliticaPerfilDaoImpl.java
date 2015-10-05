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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateCallback;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pe.gob.mef.gescon.hibernate.dao.PoliticaPerfilDao;
//import pe.gob.mef.gescon.hibernate.dao.PoliticaDao;
import pe.gob.mef.gescon.hibernate.domain.TpoliticaPerfil;
import pe.gob.mef.gescon.hibernate.domain.Mtperfil;

//import pe.gob.mef.gescon.hibernate.domain.Mtpolitica;
/**
 *
 * @author SOPORTE
 */
@Repository(value = "PoliticaPerfilDao")

public class PoliticaPerfilDaoImpl extends HibernateDaoSupport implements PoliticaPerfilDao {

    @Autowired
    public PoliticaPerfilDaoImpl(SessionFactory sessionFactory) {
        this.setSessionFactory(sessionFactory);
    }

    @Override
    public List<TpoliticaPerfil> getTpoliticaperfil() throws Exception {
        DetachedCriteria criteria = DetachedCriteria.forClass(TpoliticaPerfil.class);
        return (List<TpoliticaPerfil>) getHibernateTemplate().findByCriteria(criteria);
    }

    @Override
    @Transactional(readOnly = false)
    public void saveOrUpdate(TpoliticaPerfil tpoliticaperfil) throws Exception {
        getHibernateTemplate().saveOrUpdate(tpoliticaperfil);
    }

    @Override
    public List<HashMap> obtenerListaPoliticas(final BigDecimal perfilid) throws Exception {
        final StringBuilder sql = new StringBuilder();
        Object object = null;
        try {
            sql.append("select TP.NPOLITICAID as POLITICA, TP.NPERFILID AS PERFIL, P.VDESCRIPCION AS DES ");
            sql.append(" from TPOLITICA_PERFIL TP ");
            sql.append(" inner join MTPOLITICA P on P.NPoliticaID=TP.Npoliticaid ");
            sql.append("WHERE TP.NPERFILID = :PERFIL ");

            object = getHibernateTemplate().execute(
                    new HibernateCallback() {
                        @Override
                        public Object doInHibernate(Session session) throws HibernateException {
                            Query query = session.createSQLQuery(sql.toString())
                            .setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP)
                            .setParameter("PERFIL", perfilid);
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
    public List<HashMap> obtenerListaPoliticasDisp(final BigDecimal perfilid) throws Exception {
        final StringBuilder sql = new StringBuilder();
        Object object = null;
        try {
            sql.append("select npoliticaid as POLITICA, vdescripcion as DES from MTPOLITICA P ");
            sql.append(" where NPOLITICAID not in (select NPOLITICAID from TPOLITICA_PERFIL TP where TP.NPERFILID=:PERFIL ) ");

            object = getHibernateTemplate().execute(
                    new HibernateCallback() {
                        @Override
                        public Object doInHibernate(Session session) throws HibernateException {
                            Query query = session.createSQLQuery(sql.toString())
                            .setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP)
                            .setParameter("PERFIL", perfilid);
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
    public void delete(final BigDecimal perfilid) throws Exception {
        final StringBuilder sql = new StringBuilder();
        Object object = null;
        try {
            sql.append("delete from Tpolitica_perfil where NPERFILID=:PERFIL ");

            object = getHibernateTemplate().execute(
                    new HibernateCallback() {
                        @Override
                        public Object doInHibernate(Session session) throws HibernateException {
                            Query query = session.createSQLQuery(sql.toString())
                            .setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP)
                            .setParameter("PERFIL", perfilid);
                            return query.list();
                        }
                    });
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
    }

}
