/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.gob.mef.gescon.hibernate.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate4.HibernateCallback;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;
import pe.gob.mef.gescon.common.Constante;
import pe.gob.mef.gescon.hibernate.dao.ConsultaDao;

/**
 *
 * @author JJacobo
 */
@Repository(value = "ConsultaDao")
public class ConsultaDaoImpl extends HibernateDaoSupport implements ConsultaDao{
    
    /**
     * Crea una nueva instancia de ConsultaDaoImpl
     *
     * @param sessionFactory
     */
    @Autowired
    public ConsultaDaoImpl(SessionFactory sessionFactory) {
        this.setSessionFactory(sessionFactory);
    }
    
    @Override
    public List<HashMap> getQueryFilter(HashMap filters) {
        final String fCategoria = (String) filters.get("fCategoria");
        final Date fFromDate = (Date) filters.get("fFromDate");
        final Date fToDate = (Date) filters.get("fToDate");
        final String fType = (String) filters.get("fType");
        SimpleDateFormat sdf = new SimpleDateFormat(Constante.FORMAT_DATE_SHORT);
        final StringBuilder sql = new StringBuilder();
        Object object = null;
        try {
            sql.append("SELECT x.ID, x.NOMBRE, x.SUMILLA, x.IDCATEGORIA, x.CATEGORIA, ");
            sql.append("       x.FECHA, x.IDTIPOCONOCIMIENTO, x.TIPOCONOCIMIENTO, x.IDESTADO, x.ESTADO ");
            sql.append("FROM (SELECT ");
            sql.append("            a.nbaselegalid AS ID, a.vnumero AS NOMBRE, a.vnombre AS SUMILLA, ");
            sql.append("            a.ncategoriaid AS IDCATEGORIA, b.vnombre AS CATEGORIA, a.dfechapublicacion AS FECHA, ");
            sql.append("            1 AS IDTIPOCONOCIMIENTO, 'Base Legal' AS TIPOCONOCIMIENTO, a.nestadoid AS IDESTADO, c.vnombre AS ESTADO ");
            sql.append("        FROM TBASELEGAL a ");
            sql.append("        INNER JOIN MTCATEGORIA b ON a.ncategoriaid = b.ncategoriaid ");
            sql.append("        INNER JOIN MTESTADO_BASELEGAL c ON a.nestadoid = c.nestadoid ");
            sql.append("        WHERE a.nactivo = :ACTIVO ");
            sql.append("        AND c.nestadoid IN (3,4,5,6) AND b.NESTADO = 1 ");
            if(StringUtils.isNotBlank(fCategoria)) {
                sql.append("    AND a.ncategoriaid IN (").append(fCategoria).append(") ");
            }
            if(fFromDate != null) {
                sql.append("    AND a.dfechapublicacion >= TO_DATE('").append(sdf.format(fFromDate)).append("','dd/mm/yyyy') ");
            }
            if(fToDate != null) {
                sql.append("    AND a.dfechapublicacion <= TO_DATE('").append(sdf.format(fToDate)).append("','dd/mm/yyyy') ");
            }
            sql.append("        ) x ");
            sql.append("WHERE 1 IN (").append(fType).append(") "); //BASE LEGAL
            sql.append("UNION ");
            sql.append("SELECT y.ID, y.NOMBRE, y.SUMILLA, y.IDCATEGORIA, y.CATEGORIA, ");
            sql.append("       y.FECHA, y.IDTIPOCONOCIMIENTO, y.TIPOCONOCIMIENTO, y.IDESTADO, y.ESTADO ");
            sql.append("FROM (SELECT ");
            sql.append("            a.npreguntaid AS ID, a.vasunto AS NOMBRE, a.vdetalle AS SUMILLA, ");
            sql.append("            a.ncategoriaid AS IDCATEGORIA, b.vnombre AS CATEGORIA, a.dfechacreacion AS FECHA, ");
            sql.append("            2 AS IDTIPOCONOCIMIENTO, 'Preguntas y Respuestas' AS TIPOCONOCIMIENTO, a.nsituacionid AS IDESTADO, c.vnombre AS ESTADO ");
            sql.append("        FROM TPREGUNTA a ");
            sql.append("        INNER JOIN MTCATEGORIA b ON a.ncategoriaid = b.ncategoriaid ");
            sql.append("        INNER JOIN MTSITUACION c ON a.nsituacionid = c.nsituacionid ");
            sql.append("        WHERE a.nactivo = :ACTIVO ");
            sql.append("        AND c.nsituacionid = 6 AND b.NESTADO = 1");
            if(StringUtils.isNotBlank(fCategoria)) {
                sql.append("    AND a.ncategoriaid IN (").append(fCategoria).append(") ");
            }
            if(fFromDate != null) {
                sql.append("    AND a.dfechacreacion >= TO_DATE('").append(sdf.format(fFromDate)).append("','dd/mm/yyyy') ");
            }
            if(fToDate != null) {
                sql.append("    AND a.dfechacreacion <= TO_DATE('").append(sdf.format(fToDate)).append("','dd/mm/yyyy') ");
            }
            sql.append("        ) y ");
            sql.append("WHERE 2 IN (").append(fType).append(") "); //PREGUNTAS Y RESPUESTAS
            sql.append("UNION ");
            sql.append("SELECT z.ID, z.NOMBRE, z.SUMILLA, z.IDCATEGORIA, z.CATEGORIA, ");
            sql.append("       z.FECHA, z.IDTIPOCONOCIMIENTO, z.TIPOCONOCIMIENTO, z.IDESTADO, z.ESTADO ");
            sql.append("FROM (SELECT ");
            sql.append("            a.nconocimientoid AS ID, a.vtitulo AS NOMBRE, a.vdescripcion AS SUMILLA, ");
            sql.append("            a.ncategoriaid AS IDCATEGORIA, b.vnombre AS CATEGORIA, a.dfechapublicacion AS FECHA, ");
            sql.append("            a.ntpoconocimientoid AS IDTIPOCONOCIMIENTO, d.vnombre AS TIPOCONOCIMIENTO, ");
            sql.append("            a.nsituacionid AS IDESTADO, c.vnombre AS ESTADO ");
            sql.append("        FROM TCONOCIMIENTO a ");
            sql.append("        INNER JOIN MTCATEGORIA b ON a.ncategoriaid = b.ncategoriaid ");
            sql.append("        INNER JOIN MTSITUACION c ON a.nsituacionid = c.nsituacionid ");
            sql.append("        INNER JOIN MTTIPO_CONOCIMIENTO d ON a.ntpoconocimientoid = d.ntpoconocimientoid ");
            sql.append("        WHERE a.nactivo = :ACTIVO ");
            sql.append("        AND c.nsituacionid = 6 AND b.nestado = 1");
            if(StringUtils.isNotBlank(fCategoria)) {
                sql.append("    AND a.ncategoriaid IN (").append(fCategoria).append(") ");
            }
            if(fFromDate != null) {
                sql.append("    AND a.dfechacreacion >= TO_DATE('").append(sdf.format(fFromDate)).append("','dd/mm/yyyy') ");
            }
            if(fToDate != null) {
                sql.append("    AND a.dfechacreacion <= TO_DATE('").append(sdf.format(fToDate)).append("','dd/mm/yyyy') ");
            }
            sql.append("        ) z ");
            sql.append("WHERE 3 IN (").append(fType).append(") "); //WIKI
            sql.append("ORDER BY 7 DESC ");

            object = getHibernateTemplate().execute(
                new HibernateCallback() {
                    @Override
                    public Object doInHibernate(Session session) throws HibernateException {
                        Query query = session.createSQLQuery(sql.toString());
                        query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
                        if(StringUtils.isNotBlank(sql.toString())) {
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
    
    public List<HashMap> getDestacadosByTipoConocimiento(HashMap filters) {
        String ntipoconocimientoid = ((BigDecimal) filters.get("ntipoconocimientoid")).toString();
        final StringBuilder sql = new StringBuilder();
        Object object = null;
        try {
            if(StringUtils.isNotBlank(ntipoconocimientoid) && ntipoconocimientoid.equals("1")) {
                sql.append("SELECT ");
                sql.append("    a.nbaselegalid AS ID, a.vnumero AS NOMBRE, a.vnombre AS SUMILLA, ");
                sql.append("    a.ncategoriaid AS IDCATEGORIA, b.vnombre AS CATEGORIA, a.dfechapublicacion AS FECHA, ");
                sql.append("    1 AS IDTIPOCONOCIMIENTO, 'Base Legal' AS TIPOCONOCIMIENTO, a.nestadoid AS IDESTADO, c.vnombre AS ESTADO ");
                sql.append("FROM TBASELEGAL a ");
                sql.append("    INNER JOIN MTCATEGORIA b ON a.ncategoriaid = b.ncategoriaid ");
                sql.append("    INNER JOIN MTESTADO_BASELEGAL c ON a.nestadoid = c.nestadoid ");
                sql.append("WHERE a.nactivo = :ACTIVO ");
                sql.append("AND a.ndestacado = :DESTACADO ");
                sql.append("AND a.nestadoid IN (3,5,6) "); // Publicada, Concordada y Modificada.
            }
            if(StringUtils.isNotBlank(ntipoconocimientoid) && ntipoconocimientoid.equals("2")) {
                sql.append("SELECT ");
                sql.append("    a.npreguntaid AS ID, a.vasunto AS NOMBRE, a.vrespuesta AS SUMILLA, ");
                sql.append("    a.ncategoriaid AS IDCATEGORIA, b.vnombre AS CATEGORIA, a.dfechacreacion AS FECHA, ");
                sql.append("    2 AS IDTIPOCONOCIMIENTO, 'Preguntas y Respuestas' AS TIPOCONOCIMIENTO, ");
                sql.append("    a.nsituacionid AS IDESTADO, c.vnombre AS ESTADO ");
                sql.append("FROM TPREGUNTA a ");
                sql.append("    INNER JOIN MTCATEGORIA b ON a.ncategoriaid = b.ncategoriaid ");
                sql.append("    INNER JOIN MTSITUACION c ON a.nsituacionid = c.nsituacionid ");
                sql.append("WHERE a.nactivo = :ACTIVO ");
                sql.append("AND a.ndestacado = :DESTACADO ");
                sql.append("AND a.nsituacionid = 6 "); // Publicada
            }
            if(StringUtils.isNotBlank(ntipoconocimientoid) && 
                (ntipoconocimientoid.equals("3") || ntipoconocimientoid.equals("4") || 
                ntipoconocimientoid.equals("5") || ntipoconocimientoid.equals("6"))) {
                sql.append("SELECT ");
                sql.append("    a.nconocimientoid AS ID, a.vtitulo AS NOMBRE, a.vdescripcion AS SUMILLA, ");
                sql.append("    a.ncategoriaid AS IDCATEGORIA, b.vnombre AS CATEGORIA, a.dfechacreacion AS FECHA, ");
                sql.append("    a.ntpoconocimientoid AS IDTIPOCONOCIMIENTO, d.vnombre AS TIPOCONOCIMIENTO, ");
                sql.append("    a.nsituacionid AS IDESTADO, c.vnombre AS ESTADO ");
                sql.append("FROM TCONOCIMIENTO a ");
                sql.append("    INNER JOIN MTCATEGORIA b ON a.ncategoriaid = b.ncategoriaid ");
                sql.append("    INNER JOIN MTSITUACION c ON a.nsituacionid = c.nsituacionid ");
                sql.append("    INNER JOIN MTTIPO_CONOCIMIENTO d ON a.ntpoconocimientoid = d.ntpoconocimientoid ");
                sql.append("WHERE a.nactivo = :ACTIVO ");
                sql.append("AND a.ndestacado = :DESTACADO ");
                sql.append("AND a.nsituacionid = 6 AND a.NTPOCONOCIMIENTOID= ").append(ntipoconocimientoid).append(" "); // Publicado
            }
            sql.append("ORDER BY 5, 7 DESC ");

            object = getHibernateTemplate().execute(
                new HibernateCallback() {
                    @Override
                    public Object doInHibernate(Session session) throws HibernateException {
                        Query query = session.createSQLQuery(sql.toString());
                        query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
                        query.setParameter("ACTIVO", BigDecimal.ONE);
                        query.setParameter("DESTACADO", BigDecimal.ONE);
                        return query.list();
                    }
                });
        } catch (DataAccessException e) {
            e.getMessage();
            e.printStackTrace();
        }
        return (List<HashMap>) object;
    }
}
