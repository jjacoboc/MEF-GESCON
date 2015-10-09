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
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.orm.hibernate4.HibernateCallback;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pe.gob.mef.gescon.hibernate.dao.PassDao;
import pe.gob.mef.gescon.hibernate.domain.Mtuser;
import pe.gob.mef.gescon.hibernate.domain.Tpass;

//import pe.gob.mef.gescon.hibernate.domain.Mtpolitica;
/**
 *
 * @author SOPORTE
 */
@Repository(value = "PassDao")

public class PassDaoImpl extends HibernateDaoSupport implements PassDao {

    @Autowired
    public PassDaoImpl(SessionFactory sessionFactory) {
        this.setSessionFactory(sessionFactory);
    }

    @Override
    public BigDecimal getNextPK() throws Exception {
        return (BigDecimal) getHibernateTemplate().execute(
                new HibernateCallback() {
                    @Override
                    public Object doInHibernate(Session session) throws HibernateException {
                        Query query = session.createSQLQuery("SELECT SEQ_TPASS.NEXTVAL FROM DUAL");
                        return query.uniqueResult();
                    }
                });
    }
    
    @Override
    public Tpass getTpassByMtuser(Mtuser mtuser) throws Exception {
        DetachedCriteria proj = DetachedCriteria.forClass(Tpass.class);
        proj.setProjection(Projections.max("dfechacreacion"));
        DetachedCriteria criteria = DetachedCriteria.forClass(Tpass.class);
        criteria.add(Restrictions.eq("id.nusuarioid", mtuser.getNusuarioid()));
        criteria.add(Property.forName("dfechacreacion").eq(proj));
        return (Tpass) DataAccessUtils.uniqueResult(getHibernateTemplate().findByCriteria(criteria));
    }

    @Override
    @Transactional(readOnly = false)
    public void saveOrUpdate(Tpass tpass) throws Exception {
        getHibernateTemplate().saveOrUpdate(tpass);
    }

}
