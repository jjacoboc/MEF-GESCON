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
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.orm.hibernate4.HibernateCallback;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pe.gob.mef.gescon.hibernate.dao.UserDao;
import pe.gob.mef.gescon.hibernate.domain.Mtuser;

/**
 *
 * @author JJacobo
 */
@Repository(value = "UserDao")
public class UserDaoImpl extends HibernateDaoSupport implements UserDao{

    /**
     * Crea una nueva instancia de MaestroDaoImpl
     *
     * @param sessionFactory
     */
    @Autowired
    public UserDaoImpl(SessionFactory sessionFactory) {
        this.setSessionFactory(sessionFactory);
    }
    
    @Override
    public BigDecimal getNextPK() throws Exception {
        return (BigDecimal) getHibernateTemplate().execute(
            new HibernateCallback() {
                @Override
                public Object doInHibernate(Session session) throws HibernateException {
                    Query query = session.createSQLQuery("SELECT SEQ_MTUSER.NEXTVAL FROM DUAL");
                    return query.uniqueResult();
                }
            });
    }
    
    @Override
    @Transactional(readOnly = false)
    public void saveOrUpdate(Mtuser mtuser) throws Exception {
        getHibernateTemplate().saveOrUpdate(mtuser);
    }

    @Override
    public List<Mtuser> getMtusers() throws Exception {
        DetachedCriteria criteria = DetachedCriteria.forClass(Mtuser.class);
        return (List<Mtuser>) getHibernateTemplate().findByCriteria(criteria);
    }
    
    @Override
    public List<Mtuser> getMtusersInternal() throws Exception {
        DetachedCriteria criteria = DetachedCriteria.forClass(Mtuser.class);
        criteria.add(Restrictions.eq("nuserinterno", BigDecimal.ONE));
        return (List<Mtuser>) getHibernateTemplate().findByCriteria(criteria);
    }
    
    @Override
    public List<Mtuser> getMtusersExternal() throws Exception {
        DetachedCriteria criteria = DetachedCriteria.forClass(Mtuser.class);
        criteria.add(Restrictions.eq("nuserinterno", BigDecimal.ZERO));
        return (List<Mtuser>) getHibernateTemplate().findByCriteria(criteria);
    }
    
    @Override
    public Mtuser getMtuserByDNI(String dni) throws Exception {
        DetachedCriteria criteria = DetachedCriteria.forClass(Mtuser.class);
        criteria.add(Restrictions.eq("vdni", dni));
        return (Mtuser) DataAccessUtils.uniqueResult(getHibernateTemplate().findByCriteria(criteria));
    }
    
    @Override
    public Mtuser getMtuserByLogin(String login) throws Exception {
        DetachedCriteria criteria = DetachedCriteria.forClass(Mtuser.class);
        criteria.add(Restrictions.eq("vlogin", login));
        return (Mtuser) DataAccessUtils.uniqueResult(getHibernateTemplate().findByCriteria(criteria));
    }

    @Override
    public Mtuser getMtuserById(BigDecimal nusuarioid) throws Exception {
        DetachedCriteria criteria = DetachedCriteria.forClass(Mtuser.class);
        criteria.add(Restrictions.eq("nusuarioid", nusuarioid));
        return (Mtuser) DataAccessUtils.uniqueResult(getHibernateTemplate().findByCriteria(criteria));
    }
}
