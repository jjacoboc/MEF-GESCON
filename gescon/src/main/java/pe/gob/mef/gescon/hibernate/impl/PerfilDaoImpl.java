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
import pe.gob.mef.gescon.hibernate.dao.PerfilDao;
import pe.gob.mef.gescon.hibernate.domain.Mtperfil;

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
    @Transactional(readOnly = false)
    public void saveOrUpdate(Mtperfil mtperfil) throws Exception {
        getHibernateTemplate().saveOrUpdate(mtperfil);
    }

}
