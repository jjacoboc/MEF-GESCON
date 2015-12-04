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
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.orm.hibernate4.HibernateCallback;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pe.gob.mef.gescon.hibernate.dao.ArchivoConocimientoDao;
import pe.gob.mef.gescon.hibernate.dao.ArchivoDao;
import pe.gob.mef.gescon.hibernate.domain.Tarchivo;
import pe.gob.mef.gescon.hibernate.domain.TarchivoConocimiento;
import pe.gob.mef.gescon.hibernate.domain.Tbaselegal;
import pe.gob.mef.gescon.hibernate.domain.Tconocimiento;

/**
 *
 * @author JJacobo
 */
@Repository(value = "ArchivoConocimientoDao")
public class ArchivoConocimientoDaoImpl extends HibernateDaoSupport implements ArchivoConocimientoDao{

    /**
     * Crea una nueva instancia de ArchivoDaoImpl
     *
     * @param sessionFactory
     */
    @Autowired
    public ArchivoConocimientoDaoImpl(SessionFactory sessionFactory) {
        this.setSessionFactory(sessionFactory);
    }
    
    @Override
    public BigDecimal getNextPK() throws Exception {
        return (BigDecimal) getHibernateTemplate().execute(
            new HibernateCallback() {
                @Override
                public Object doInHibernate(Session session) throws HibernateException {
                    Query query = session.createSQLQuery("SELECT SEQ_TARCHIVO_CONOCIMIENTO.NEXTVAL FROM DUAL");
                    return query.uniqueResult();
                }
            });
    }

    @Override
    public List<TarchivoConocimiento> getTarchivosByTconocimiento(BigDecimal nconocimientoid) throws Exception {
        DetachedCriteria criteria = DetachedCriteria.forClass(TarchivoConocimiento.class);
        criteria.add(Restrictions.eq("nconocimientoid", nconocimientoid));
        criteria.addOrder(Order.asc("nversion"));
        return (List<TarchivoConocimiento>) getHibernateTemplate().findByCriteria(criteria);
    }
    
    @Override
    public TarchivoConocimiento getLastTarchivoByTconocimiento(Tconocimiento tconocimiento) throws Exception {
        DetachedCriteria proj = DetachedCriteria.forClass(TarchivoConocimiento.class);
        proj.setProjection(Projections.max("nversion"));
        DetachedCriteria criteria = DetachedCriteria.forClass(TarchivoConocimiento.class);
        criteria.add(Restrictions.eq("tconocimiento.nconocimientoid", tconocimiento.getNconocimientoid()));
        criteria.add(Property.forName("nversion").eq(proj));
        return (TarchivoConocimiento) DataAccessUtils.uniqueResult(getHibernateTemplate().findByCriteria(criteria));
    }

    @Override
    @Transactional(readOnly = false)
    public void saveOrUpdate(TarchivoConocimiento tarchivoconocimiento) throws Exception {
        getHibernateTemplate().saveOrUpdate(tarchivoconocimiento);
    }
    
}
