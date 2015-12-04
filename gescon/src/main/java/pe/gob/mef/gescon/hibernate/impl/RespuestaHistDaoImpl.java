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
import pe.gob.mef.gescon.hibernate.dao.RespuestaHistDao;
import pe.gob.mef.gescon.hibernate.domain.Tarchivo;
import pe.gob.mef.gescon.hibernate.domain.TarchivoConocimiento;
import pe.gob.mef.gescon.hibernate.domain.Tbaselegal;
import pe.gob.mef.gescon.hibernate.domain.Tconocimiento;
import pe.gob.mef.gescon.hibernate.domain.TrespuestaHist;

/**
 *
 * @author JJacobo
 */
@Repository(value = "RespuestaHistDao")
public class RespuestaHistDaoImpl extends HibernateDaoSupport implements RespuestaHistDao{

    /**
     * Crea una nueva instancia de ArchivoDaoImpl
     *
     * @param sessionFactory
     */
    @Autowired
    public RespuestaHistDaoImpl(SessionFactory sessionFactory) {
        this.setSessionFactory(sessionFactory);
    }
    
    @Override
    public BigDecimal getNextPK() throws Exception {
        return (BigDecimal) getHibernateTemplate().execute(
            new HibernateCallback() {
                @Override
                public Object doInHibernate(Session session) throws HibernateException {
                    Query query = session.createSQLQuery("SELECT SEQ_TRESPUESTA_HIST.NEXTVAL FROM DUAL");
                    return query.uniqueResult();
                }
            });
    }

    @Override
    public List<TrespuestaHist> getThistorialByTpregunta(BigDecimal npreguntaid) throws Exception {
        DetachedCriteria criteria = DetachedCriteria.forClass(TrespuestaHist.class);
        criteria.add(Restrictions.eq("npreguntaid", npreguntaid));
        return (List<TrespuestaHist>) getHibernateTemplate().findByCriteria(criteria);
    }
    

    @Override
    @Transactional(readOnly = false)
    public void saveOrUpdate(TrespuestaHist trespuestahist) throws Exception {
        getHibernateTemplate().saveOrUpdate(trespuestahist);
    }
    
}
