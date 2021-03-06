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
import pe.gob.mef.gescon.hibernate.dao.ParametroDao;
import pe.gob.mef.gescon.hibernate.domain.Mtparametro;

/**
 *
 * @author JJacobo
 */
@Repository(value = "ParametroDao")
public class ParametroDaoImpl extends HibernateDaoSupport implements ParametroDao{

    /**
     * Crea una nueva instancia de MaestroDaoImpl
     *
     * @param sessionFactory
     */
    @Autowired
    public ParametroDaoImpl(SessionFactory sessionFactory) {
        this.setSessionFactory(sessionFactory);
    }
    
    @Override
    public BigDecimal getNextPK() throws Exception {
        return (BigDecimal) getHibernateTemplate().execute(
            new HibernateCallback() {
                @Override
                public Object doInHibernate(Session session) throws HibernateException {
                    Query query = session.createSQLQuery("SELECT SEQ_MTPARAMETRO.NEXTVAL FROM DUAL");
                    return query.uniqueResult();
                }
            });
    }
    
    @Override
    public List<Mtparametro> getMtparametros() throws Exception {
        DetachedCriteria criteria = DetachedCriteria.forClass(Mtparametro.class);
        return (List<Mtparametro>) getHibernateTemplate().findByCriteria(criteria);
    }
    
    @Override
    public List<Mtparametro> getMtparametrosActived() throws Exception {
        DetachedCriteria criteria = DetachedCriteria.forClass(Mtparametro.class);
        criteria.add(Restrictions.eq("nactivo", BigDecimal.ONE));
        return (List<Mtparametro>) getHibernateTemplate().findByCriteria(criteria);
    }
    
    @Override
    public Mtparametro getMtparametroById(BigDecimal id) throws Exception {
        DetachedCriteria criteria = DetachedCriteria.forClass(Mtparametro.class);
        criteria.add(Restrictions.eq("nparametroid", id));
        return (Mtparametro) DataAccessUtils.uniqueResult(getHibernateTemplate().findByCriteria(criteria));
    }

    @Override
    @Transactional(readOnly = false)
    public void saveOrUpdate(Mtparametro mtparametro) throws Exception {
        getHibernateTemplate().saveOrUpdate(mtparametro);
    }
    
}
