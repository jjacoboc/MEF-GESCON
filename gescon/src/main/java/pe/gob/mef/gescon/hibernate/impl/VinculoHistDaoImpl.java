/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.gob.mef.gescon.hibernate.impl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
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
import pe.gob.mef.gescon.hibernate.dao.VinculoHistDao;
import pe.gob.mef.gescon.hibernate.domain.TvinculoHist;

/**
 *
 * @author JJacobo
 */
@Repository(value = "VinculoHistDao")
public class VinculoHistDaoImpl extends HibernateDaoSupport implements VinculoHistDao{

    /**
     * Crea una nueva instancia de VinculoHistDaoImpl
     *
     * @param sessionFactory
     */
    @Autowired
    public VinculoHistDaoImpl(SessionFactory sessionFactory) {
        this.setSessionFactory(sessionFactory);
    }
    
    @Override
    public BigDecimal getNextPK() throws Exception {
        return (BigDecimal) getHibernateTemplate().execute(
            new HibernateCallback() {
                @Override
                public Object doInHibernate(Session session) throws HibernateException {
                    Query query = session.createSQLQuery("SELECT SEQ_TVINCULO_HIST.NEXTVAL FROM DUAL");
                    return query.uniqueResult();
                }
            });
    }

    @Override
    public TvinculoHist getTvinculoHistById(BigDecimal idvinculoh) throws Exception {
        DetachedCriteria criteria = DetachedCriteria.forClass(TvinculoHist.class);
        criteria.add(Restrictions.eq("id.nvinculohid", idvinculoh));
        return (TvinculoHist) DataAccessUtils.uniqueResult(getHibernateTemplate().findByCriteria(criteria));
    }

    @Override
    public List<TvinculoHist> getTvinculoHists() throws Exception {
        DetachedCriteria criteria = DetachedCriteria.forClass(TvinculoHist.class);
        return (List<TvinculoHist>) getHibernateTemplate().findByCriteria(criteria);
    }

    @Override
    public List<TvinculoHist> getTvinculoHistsByThistorial(BigDecimal idhistorial) throws Exception {
        DetachedCriteria criteria = DetachedCriteria.forClass(TvinculoHist.class);
        criteria.add(Restrictions.eq("id.nhistorialid", idhistorial));
        return (List<TvinculoHist>) getHibernateTemplate().findByCriteria(criteria);
    }

    @Override
    @Transactional(readOnly = false)
    public void saveOrUpdate(TvinculoHist tvinculoHist) throws Exception {
        getHibernateTemplate().saveOrUpdate(tvinculoHist);
    }
    
    @Override
    public List<HashMap> getConcimientosVinculadosByHistorial(HashMap filters) throws Exception {
        String nhistorialid = (String) filters.get("nhistorialid");
        String nconocimientoid = (String) filters.get("nconocimientoid");
        String ntipoconocimientoid = (String) filters.get("ntipoconocimientoid");
        Boolean flag = (Boolean) filters.get("flag");
        Object object = null;
        final StringBuilder sql = new StringBuilder();
        if (StringUtils.isNotBlank(ntipoconocimientoid)
                && (ntipoconocimientoid.equals("3") || ntipoconocimientoid.equals("4")
                || ntipoconocimientoid.equals("5") || ntipoconocimientoid.equals("6"))) {
            sql.append("SELECT ");
            sql.append("    a.nvinculohid as ID, a.nconocimientovinc as IDCONOCIMIENTO, '' AS NUMERO, b.vtitulo AS NOMBRE, b.vdescripcion AS SUMILLA, ");
            sql.append("    b.ncategoriaid AS IDCATEGORIA, c.vnombre AS CATEGORIA, b.dfechapublicacion AS FECHA, ");
            sql.append("    b.ntpoconocimientoid AS IDTIPOCONOCIMIENTO, e.vnombre AS TIPOCONOCIMIENTO, ");
            sql.append("    b.nsituacionid AS IDESTADO, d.vnombre AS ESTADO ");
            sql.append("FROM TVINCULO_HIST a ");
            sql.append("INNER JOIN TCONOCIMIENTO b ");
            sql.append("    INNER JOIN MTCATEGORIA c ON b.ncategoriaid = c.ncategoriaid ");
            sql.append("    INNER JOIN MTSITUACION d ON b.nsituacionid = d.nsituacionid ");
            sql.append("    INNER JOIN MTTIPO_CONOCIMIENTO e ON b.ntpoconocimientoid = e.ntpoconocimientoid ");
            sql.append("ON a.nconocimientovinc = b.nconocimientoid ");
            sql.append("AND a.ntipoconocimientovinc = b.ntpoconocimientoid ");
            sql.append("AND b.nactivo = :ACTIVO ");
            sql.append("WHERE a.nconocimientoid = ").append(nconocimientoid).append(" ");
            sql.append("AND a.nhistorialid = ").append(nhistorialid).append(" ");
            if(flag) {
                sql.append("AND a.ntipoconocimientovinc = ").append(ntipoconocimientoid).append(" ");
            }
            sql.append("ORDER BY 7 DESC ");
        }
        if(StringUtils.isNotBlank(ntipoconocimientoid) && ntipoconocimientoid.equals("2")) {
            sql.append("SELECT ");
            sql.append("    a.nvinculohid as ID, a.nconocimientovinc as IDCONOCIMIENTO, '' AS NUMERO, b.vasunto AS NOMBRE , b.vdetalle AS SUMILLA, ");
            sql.append("    b.ncategoriaid AS IDCATEGORIA, c.vnombre AS CATEGORIA, b.dfechapublicacion AS FECHA, ");
            sql.append("    2 AS IDTIPOCONOCIMIENTO, 'Preguntas y Respuestas' AS TIPOCONOCIMIENTO, ");
            sql.append("    b.nsituacionid AS IDESTADO, d.vnombre AS ESTADO ");
            sql.append("FROM TVINCULO_HIST a ");
            sql.append("INNER JOIN TPREGUNTA b ");
            sql.append("    INNER JOIN MTCATEGORIA c ON b.ncategoriaid = c.ncategoriaid ");
            sql.append("    INNER JOIN MTSITUACION d ON b.nsituacionid = d.nsituacionid ");
            sql.append("ON a.nconocimientovinc = b.npreguntaid ");
            sql.append("AND a.ntipoconocimientovinc = 2 ");
            sql.append("AND b.nactivo = :ACTIVO ");
            sql.append("WHERE a.nconocimientoid = ").append(nconocimientoid).append(" ");
            sql.append("AND a.nhistorialid = ").append(nhistorialid).append(" ");
            sql.append("ORDER BY 7 DESC ");
        }
        if(StringUtils.isNotBlank(ntipoconocimientoid) && ntipoconocimientoid.equals("1")) {
            sql.append("SELECT ");
            sql.append("    a.nvinculohid as ID, a.nconocimientovinc as IDCONOCIMIENTO, b.vnumero AS NUMERO, b.vnombre AS NOMBRE , b.vsumilla AS SUMILLA, ");
            sql.append("    b.ncategoriaid AS IDCATEGORIA, c.vnombre AS CATEGORIA, b.dfechapublicacion AS FECHA, ");
            sql.append("    1 AS IDTIPOCONOCIMIENTO, 'Base Legal' AS TIPOCONOCIMIENTO, ");
            sql.append("    b.nestadoid AS IDESTADO, d.vnombre AS ESTADO ");
            sql.append("FROM TVINCULO_HIST a ");
            sql.append("INNER JOIN TBASELEGAL b ");
            sql.append("    INNER JOIN MTCATEGORIA c ON b.ncategoriaid = c.ncategoriaid ");
            sql.append("    INNER JOIN MTESTADO_BASELEGAL d ON b.nestadoid = d.nestadoid ");
            sql.append("ON a.nconocimientovinc = b.nbaselegalid ");
            sql.append("AND a.ntipoconocimientovinc = 1 ");
            sql.append("AND b.nactivo = :ACTIVO ");
            sql.append("WHERE a.nconocimientoid = ").append(nconocimientoid).append(" ");
            sql.append("AND a.nhistorialid = ").append(nhistorialid).append(" ");
            sql.append("ORDER BY 7 DESC ");
        }
        try {
            object = getHibernateTemplate().execute(
                    new HibernateCallback() {
                        @Override
                        public Object doInHibernate(Session session) throws HibernateException {
                            Query query = session.createSQLQuery(sql.toString());
                            query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
                            if (StringUtils.isNotBlank(sql.toString())) {
                                query.setParameter("ACTIVO", BigDecimal.ONE);
                            }
                            return query.list();
                        }
                    });
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
        return (List<HashMap>) object;
    }
    
}
