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
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.orm.hibernate4.HibernateCallback;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pe.gob.mef.gescon.common.Constante;
import pe.gob.mef.gescon.hibernate.dao.ContenidoDao;
import pe.gob.mef.gescon.hibernate.domain.Tconocimiento;

/**
 *
 * @author JJacobo
 */
@Repository(value = "ContenidoDao")
public class ContenidoDaoImpl extends HibernateDaoSupport implements ContenidoDao {

    /**
     * Crea una nueva instancia de ContenidoDaoImpl
     *
     * @param sessionFactory
     */
    @Autowired
    public ContenidoDaoImpl(SessionFactory sessionFactory) {
        this.setSessionFactory(sessionFactory);
    }

    @Override
    public BigDecimal getNextPK() throws Exception {
        return (BigDecimal) getHibernateTemplate().execute(
                new HibernateCallback() {
                    @Override
                    public Object doInHibernate(Session session) throws HibernateException {
                        Query query = session.createSQLQuery("SELECT SEQ_TCONOCIMIENTO.NEXTVAL FROM DUAL");
                        return query.uniqueResult();
                    }
                });
    }

    @Override
    public List<Tconocimiento> getContenidos() throws Exception {
        DetachedCriteria criteria = DetachedCriteria.forClass(Tconocimiento.class);
        criteria.add(Restrictions.eq("ntipoconocimientoid", BigDecimal.valueOf(Long.parseLong("4"))));
        criteria.addOrder(Order.desc("dfechacreacion"));
        return (List<Tconocimiento>) getHibernateTemplate().findByCriteria(criteria);
    }
    
    @Override
    public Tconocimiento getTcontenidoById(BigDecimal idtipo,BigDecimal id) throws Exception {
        DetachedCriteria criteria = DetachedCriteria.forClass(Tconocimiento.class);
        criteria.add(Restrictions.eq("ntipoconocimientoid",idtipo));
        criteria.add(Restrictions.eq("nconocimientoid",id));
        return (Tconocimiento) DataAccessUtils.uniqueResult(getHibernateTemplate().findByCriteria(criteria));
    }
    
    @Override
    public List<HashMap> obtenerContenidoxAsig(final BigDecimal contenidoid,final BigDecimal usuarioid, final BigDecimal tpoconocimientoid) throws Exception {
        final StringBuilder sql = new StringBuilder();
        Object object = null;
        try {
            sql.append("select nasignacionid AS IDASIGNACION, ntipoconocimientoid AS TPOCONOCIMIENTO , nconocimientoid AS IDPREGUNTA, nusuarioid AS IDUSUARIO, nestadoid AS ESTADO, VUSUARIOCREACION AS USUCREA, VUSUARIOMODIFICACION AS USUMOD, ");
            sql.append(" DFECHACREACION AS FECHACREA, DFECHAMODIFICACION AS FECHAMOD, DFECHAASIGNACION as FECHAASIG, DFECHAATENCION AS FECHAATEN, DFECHARECEPCION AS FECHARECEP ");
            sql.append(" from TASIGNACION ");
            sql.append(" WHERE NCONOCIMIENTOID =:BASE  AND NUSUARIOID=:USUARIO AND NTIPOCONOCIMIENTOID=:TIPOCONOCIMIENTO AND NESTADOID=1");
            
            object = getHibernateTemplate().execute(
                    new HibernateCallback() {
                        @Override
                        public Object doInHibernate(Session session) throws HibernateException {
                            Query query = session.createSQLQuery(sql.toString())
                            .setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP)
                                    .setParameter("BASE", contenidoid)
                                    .setParameter("USUARIO", usuarioid)
                                    .setParameter("TIPOCONOCIMIENTO", tpoconocimientoid);
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
    public void saveOrUpdate(Tconocimiento tconocimiento) throws Exception {
        getHibernateTemplate().saveOrUpdate(tconocimiento);
    }
    
    @Override
    public List<HashMap> getConcimientosVinculados(HashMap filters) {
        String nconocimientoid = filters.get("nconocimientoid").toString();
        String ntipoconocimientoid =filters.get("ntipoconocimientoid").toString();
        Boolean flag = (Boolean) filters.get("flag");
        Object object = null;
        final StringBuilder sql = new StringBuilder();
        if(StringUtils.isNotBlank(ntipoconocimientoid) && 
                (ntipoconocimientoid.equals("3") || ntipoconocimientoid.equals("4") || 
                ntipoconocimientoid.equals("5") || ntipoconocimientoid.equals("6"))) {
            sql.append("SELECT ");
            sql.append("    a.nvinculoid as ID, a.nconocimientovinc as IDCONOCIMIENTO, '' AS NUMERO, b.vtitulo AS NOMBRE, b.vdescripcion AS SUMILLA, ");
            sql.append("    b.ncategoriaid AS IDCATEGORIA, c.vnombre AS CATEGORIA, b.dfechacreacion AS FECHA, ");
            sql.append("    b.ntpoconocimientoid AS IDTIPOCONOCIMIENTO, e.vnombre AS TIPOCONOCIMIENTO, ");
            sql.append("    b.nsituacionid AS IDESTADO, d.vnombre AS ESTADO ");
            sql.append("FROM TVINCULO a ");
            sql.append("INNER JOIN TCONOCIMIENTO b ");
            sql.append("    INNER JOIN MTCATEGORIA c ON b.ncategoriaid = c.ncategoriaid ");
            sql.append("    INNER JOIN MTSITUACION d ON b.nsituacionid = d.nsituacionid ");
            sql.append("    INNER JOIN MTTIPO_CONOCIMIENTO e ON b.ntpoconocimientoid = e.ntpoconocimientoid ");
            sql.append("ON a.nconocimientovinc = b.nconocimientoid ");
            sql.append("AND a.ntipoconocimientovinc = b.ntpoconocimientoid ");
            sql.append("AND b.nactivo = :ACTIVO ");
            sql.append("WHERE a.nconocimientoid = ").append(nconocimientoid).append(" ");
            sql.append("AND a.ntipoconocimientovinc = ").append(ntipoconocimientoid).append(" ");
            sql.append("ORDER BY 7 DESC ");
        }
        if(StringUtils.isNotBlank(ntipoconocimientoid) && ntipoconocimientoid.equals("2")) {
            sql.append("SELECT ");
            sql.append("    a.nvinculoid as ID, a.nconocimientovinc as IDCONOCIMIENTO, '' AS NUMERO, b.vasunto AS NOMBRE , b.vrespuesta AS SUMILLA, ");
            sql.append("    b.ncategoriaid AS IDCATEGORIA, c.vnombre AS CATEGORIA, b.dfechacreacion AS FECHA, ");
            sql.append("    2 AS IDTIPOCONOCIMIENTO, 'Preguntas y Respuestas' AS TIPOCONOCIMIENTO, ");
            sql.append("    b.nsituacionid AS IDESTADO, d.vnombre AS ESTADO ");
            sql.append("FROM TVINCULO a ");
            sql.append("INNER JOIN TPREGUNTA b ");
            sql.append("    INNER JOIN MTCATEGORIA c ON b.ncategoriaid = c.ncategoriaid ");
            sql.append("    INNER JOIN MTSITUACION d ON b.nsituacionid = d.nsituacionid ");
            sql.append("ON a.nconocimientovinc = b.npreguntaid ");
            sql.append("AND a.ntipoconocimientovinc = 2 ");
            sql.append("AND b.nactivo = :ACTIVO ");
            sql.append("WHERE a.nconocimientoid = ").append(nconocimientoid).append(" ");
            sql.append("ORDER BY 7 DESC ");
        }
        if(StringUtils.isNotBlank(ntipoconocimientoid) && ntipoconocimientoid.equals("1")) {
            sql.append("SELECT ");
            sql.append("    a.nvinculoid as ID, a.nconocimientovinc as IDCONOCIMIENTO, b.vnumero AS NUMERO, b.vnombre AS NOMBRE , b.vsumilla AS SUMILLA, ");
            sql.append("    b.ncategoriaid AS IDCATEGORIA, c.vnombre AS CATEGORIA, b.dfechacreacion AS FECHA, ");
            sql.append("    1 AS IDTIPOCONOCIMIENTO, 'Base Legal' AS TIPOCONOCIMIENTO, ");
            sql.append("    b.nestadoid AS IDESTADO, d.vnombre AS ESTADO ");
            sql.append("FROM TVINCULO a ");
            sql.append("INNER JOIN TBASELEGAL b ");
            sql.append("    INNER JOIN MTCATEGORIA c ON b.ncategoriaid = c.ncategoriaid ");
            sql.append("    INNER JOIN MTESTADO_BASELEGAL d ON b.nestadoid = d.nestadoid ");
            sql.append("ON a.nconocimientovinc = b.nbaselegalid ");
            sql.append("AND a.ntipoconocimientovinc = 1 ");
            sql.append("AND b.nactivo = :ACTIVO ");
            sql.append("WHERE a.nconocimientoid = ").append(nconocimientoid).append(" ");
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
    
    @Override
    public List<HashMap> getConcimientosDisponibles(HashMap filters) {
        String ntipoconocimientoid = ((BigDecimal) filters.get("ntipoconocimientoid")).toString();
        String nconocimientovinc = (String) filters.get("nconocimientovinc");
        final StringBuilder sql = new StringBuilder();
        Object object = null;
        try {
            if(StringUtils.isNotBlank(ntipoconocimientoid) && ntipoconocimientoid.equals("1")) {
                sql.append("SELECT ");
                sql.append("    a.nbaselegalid AS ID, a.vnumero AS NUMERO, a.vnombre AS NOMBRE, a.vsumilla AS SUMILLA, ");
                sql.append("    a.ncategoriaid AS IDCATEGORIA, b.vnombre AS CATEGORIA, a.dfechapublicacion AS FECHA, ");
                sql.append("    1 AS IDTIPOCONOCIMIENTO, 'Base Legal' AS TIPOCONOCIMIENTO, a.nestadoid AS IDESTADO, c.vnombre AS ESTADO ");
                sql.append("FROM TBASELEGAL a ");
                sql.append("    INNER JOIN MTCATEGORIA b ON a.ncategoriaid = b.ncategoriaid ");
                sql.append("    INNER JOIN MTESTADO_BASELEGAL c ON a.nestadoid = c.nestadoid ");
                sql.append("WHERE a.nactivo = :ACTIVO ");
                sql.append("AND a.nestadoid IN (3,5,6) "); // Publicada, Concordada y Modificada.
                if (StringUtils.isNotBlank(nconocimientovinc)) {
                    sql.append("AND a.nbaselegalid NOT IN (").append(nconocimientovinc).append(") ");
                }
            }
            if(StringUtils.isNotBlank(ntipoconocimientoid) && ntipoconocimientoid.equals("2")) {
                sql.append("SELECT ");
                sql.append("    a.npreguntaid AS ID, '' AS NUMERO, a.vasunto AS NOMBRE, a.vrespuesta AS SUMILLA, ");
                sql.append("    a.ncategoriaid AS IDCATEGORIA, b.vnombre AS CATEGORIA, a.dfechacreacion AS FECHA, ");
                sql.append("    2 AS IDTIPOCONOCIMIENTO, 'Preguntas y Respuestas' AS TIPOCONOCIMIENTO, ");
                sql.append("    a.nsituacionid AS IDESTADO, c.vnombre AS ESTADO ");
                sql.append("FROM TPREGUNTA a ");
                sql.append("    INNER JOIN MTCATEGORIA b ON a.ncategoriaid = b.ncategoriaid ");
                sql.append("    INNER JOIN MTSITUACION c ON a.nsituacionid = c.nsituacionid ");
                sql.append("WHERE a.nactivo = :ACTIVO ");
                sql.append("AND a.nsituacionid = 6 "); // Publicado
                if (StringUtils.isNotBlank(nconocimientovinc)) {
                    sql.append("AND a.npreguntaid NOT IN (").append(nconocimientovinc).append(") ");
                }
            }
            if(StringUtils.isNotBlank(ntipoconocimientoid) && 
                (ntipoconocimientoid.equals("3") || ntipoconocimientoid.equals("4") || 
                ntipoconocimientoid.equals("5") || ntipoconocimientoid.equals("6"))) {
                sql.append("SELECT ");
                sql.append("    a.nconocimientoid AS ID, '' AS NUMERO, a.vtitulo AS NOMBRE, a.vdescripcion AS SUMILLA, ");
                sql.append("    a.ncategoriaid AS IDCATEGORIA, b.vnombre AS CATEGORIA, a.dfechacreacion AS FECHA, ");
                sql.append("    a.ntpoconocimientoid AS IDTIPOCONOCIMIENTO, d.vnombre AS TIPOCONOCIMIENTO, ");
                sql.append("    a.nsituacionid AS IDESTADO, c.vnombre AS ESTADO ");
                sql.append("FROM TCONOCIMIENTO a ");
                sql.append("    INNER JOIN MTCATEGORIA b ON a.ncategoriaid = b.ncategoriaid ");
                sql.append("    INNER JOIN MTSITUACION c ON a.nsituacionid = c.nsituacionid ");
                sql.append("    INNER JOIN MTTIPO_CONOCIMIENTO d ON a.ntpoconocimientoid = d.ntpoconocimientoid ");
                sql.append("WHERE a.nactivo = :ACTIVO ");
                sql.append("AND a.nsituacionid = 6 AND a.NTPOCONOCIMIENTOID= ").append(ntipoconocimientoid).append(" "); // Publicado
                if (StringUtils.isNotBlank(nconocimientovinc)) {
                    sql.append(" AND a.nconocimientoid NOT IN (").append(nconocimientovinc).append(") ");
                }
            }
            sql.append("ORDER BY 5, 7 DESC ");

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
        } catch (DataAccessException e) {
            e.getMessage();
            e.printStackTrace();
        }
        return (List<HashMap>) object;
    }
    
    @Override
    public void delete(final BigDecimal conocimientoid) throws Exception {
        final StringBuilder sql = new StringBuilder();
        Object object = null;
        try {
            sql.append("delete from TVINCULO where NCONOCIMIENTOID=:CONOCIMIENTO");

            object = getHibernateTemplate().execute(
                    new HibernateCallback() {
                        @Override
                        public Object doInHibernate(Session session) throws HibernateException {
                            Query query = session.createSQLQuery(sql.toString())
                            .setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP)
                            .setParameter("CONOCIMIENTO", conocimientoid);
                            return query.list();
                        }
                    });
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
    }
    
    @Override
    public void deleteArchivos(final BigDecimal conocimientoid) throws Exception {
        final StringBuilder sql = new StringBuilder();
        Object object = null;
        try {
            sql.append("delete from TARCHIVO_CONOCIMIENTO where NCONOCIMIENTOID=:CONOCIMIENTO AND NTIPOCONOCIMIENTOID=:TIPO");

            object = getHibernateTemplate().execute(
                    new HibernateCallback() {
                        @Override
                        public Object doInHibernate(Session session) throws HibernateException {
                            Query query = session.createSQLQuery(sql.toString())
                            .setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP)
                            .setParameter("CONOCIMIENTO", conocimientoid)
                            .setParameter("TIPO", Constante.CONTENIDO);
                            return query.list();
                        }
                    });
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
    }

}
