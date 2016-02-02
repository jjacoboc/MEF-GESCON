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
import pe.gob.mef.gescon.hibernate.dao.PerfilDao;
import pe.gob.mef.gescon.hibernate.domain.Mtperfil;
import pe.gob.mef.gescon.hibernate.domain.Mtuser;

/**
 *
 * @author SOPORTE
 */
@Repository(value = "PerfilDao")
public class PerfilDaoImpl extends HibernateDaoSupport implements PerfilDao {

    @Autowired
    public PerfilDaoImpl(SessionFactory sessionFactory) {
        this.setSessionFactory(sessionFactory);
    }

    @Override
    public BigDecimal getNextPK() throws Exception {
        return (BigDecimal) getHibernateTemplate().execute(
                new HibernateCallback() {
                    @Override
                    public Object doInHibernate(Session session) throws HibernateException {
                        Query query = session.createSQLQuery("SELECT SEQ_MTPERFIL.NEXTVAL FROM DUAL");
                        return query.uniqueResult();
                    }
                });
    }

    @Override
    public List<Mtperfil> getMtperfils() throws Exception {
        DetachedCriteria criteria = DetachedCriteria.forClass(Mtperfil.class);
        return (List<Mtperfil>) getHibernateTemplate().findByCriteria(criteria);
    }
    
    @Override
    public List<HashMap> getMtperfilesByMtuser(final Mtuser mtuser) throws Exception {
        final StringBuilder sql = new StringBuilder();
        Object object = null;
        try {
            sql.append("SELECT a.nperfilid AS ID, a.vnombre AS NOMBRE, a.vdescripcion AS SUMILLA, a.nactivo AS ACTIVO, ");
            sql.append("       a.vusuariocreacion AS USUARIOCREACION, a.vusuariomodificacion AS USUARIOMODIFICACION, ");
            sql.append("       a.dfechacreacion AS FECHACREACION, a.dfechamodificacion AS FECHAMODIFICACION ");
            sql.append("FROM MTPERFIL a ");
            sql.append("INNER JOIN TUSER_PERFIL b ON a.nperfilid = b.nperfilid ");
            sql.append("WHERE a.nactivo = :ACTIVO ");
            sql.append("AND b.nusuarioid = :USERID ");
            sql.append("ORDER BY a.nperfilid ");

            object = getHibernateTemplate().execute(
                    new HibernateCallback() {
                        @Override
                        public Object doInHibernate(Session session) throws HibernateException {
                            Query query = session.createSQLQuery(sql.toString());
                            query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
                            query.setParameter("ACTIVO", BigDecimal.ONE);
                            query.setParameter("USERID", mtuser.getNusuarioid());
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
    public void saveOrUpdate(Mtperfil mtperfil) throws Exception {
        getHibernateTemplate().saveOrUpdate(mtperfil);
    }

}
