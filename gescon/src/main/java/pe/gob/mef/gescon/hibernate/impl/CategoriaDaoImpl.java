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
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.orm.hibernate4.HibernateCallback;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pe.gob.mef.gescon.hibernate.dao.CategoriaDao;
import pe.gob.mef.gescon.hibernate.domain.Mtcategoria;

/**
 *
 * @author JJacobo
 */
@Repository(value = "CategoriaDao")
public class CategoriaDaoImpl extends HibernateDaoSupport implements CategoriaDao{

    /**
     * Crea una nueva instancia de CategoriaDaoImpl
     *
     * @param sessionFactory
     */
    @Autowired
    public CategoriaDaoImpl(SessionFactory sessionFactory) {
        this.setSessionFactory(sessionFactory);
    }
    
    @Override
    public BigDecimal getNextPK() throws Exception {
        return (BigDecimal) getHibernateTemplate().execute(
            new HibernateCallback() {
                @Override
                public Object doInHibernate(Session session) throws HibernateException {
                    Query query = session.createSQLQuery("SELECT SEQ_MTCATEGORIA.NEXTVAL FROM DUAL");
                    return query.uniqueResult();
                }
            });
    }
    
    @Override
    public List<Mtcategoria> getMtcategorias() throws Exception {
        DetachedCriteria criteria = DetachedCriteria.forClass(Mtcategoria.class);
        criteria.addOrder(Order.asc("nnivel"));
        criteria.addOrder(Order.asc("ncategoriaid"));
        return (List<Mtcategoria>) getHibernateTemplate().findByCriteria(criteria);
    }
    
    @Override
    public List<Mtcategoria> getMtcategoriasPrimerNivel() throws Exception {
        DetachedCriteria criteria = DetachedCriteria.forClass(Mtcategoria.class);
        criteria.add(Restrictions.eq("nnivel", BigDecimal.ONE));
        criteria.add(Restrictions.eq("nestado", BigDecimal.ONE));
        criteria.addOrder(Order.asc("ncategoriaid"));
        return (List<Mtcategoria>) getHibernateTemplate().findByCriteria(criteria);
    }
    
    @Override
    public List<Mtcategoria> getMtcategoriaHijos(Mtcategoria mtcategoria) throws Exception {
        DetachedCriteria criteria = DetachedCriteria.forClass(Mtcategoria.class);
        criteria.add(Restrictions.eq("ncategoriasup", mtcategoria.getNcategoriaid()));
        criteria.add(Restrictions.eq("nestado", BigDecimal.ONE));
        criteria.addOrder(Order.asc("nnivel"));
        criteria.addOrder(Order.asc("ncategoriaid"));
        return (List<Mtcategoria>) getHibernateTemplate().findByCriteria(criteria);
    }
    
    @Override
    public Mtcategoria getMtcategoriaById(BigDecimal id) throws Exception {
        DetachedCriteria criteria = DetachedCriteria.forClass(Mtcategoria.class);
        criteria.add(Restrictions.eq("ncategoriaid", id));
        return (Mtcategoria) DataAccessUtils.uniqueResult(getHibernateTemplate().findByCriteria(criteria));
    }
    
    @Override
    @Transactional(readOnly = false)
    public void saveOrUpdate(Mtcategoria mtcategoria) throws Exception {
        getHibernateTemplate().saveOrUpdate(mtcategoria);
    }
    
}
