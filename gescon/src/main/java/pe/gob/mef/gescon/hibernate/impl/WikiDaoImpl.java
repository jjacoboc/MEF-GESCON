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

/**
 *
 * @author JJacobo
 */
@Repository(value = "WikiDao")
public class WikiDaoImpl extends HibernateDaoSupport {
    
    /**
     * Crea una nueva instancia de WikiDaoImpl
     *
     * @param sessionFactory
     */
    @Autowired
    public WikiDaoImpl(SessionFactory sessionFactory) {
        this.setSessionFactory(sessionFactory);
    }
    
    public List<HashMap> getQueryFilter(HashMap filters) {
        final StringBuilder sql = new StringBuilder();
        Object object = null;
        try {
            sql.append("SELECT x.ID, x.NUMERO, x.NOMBRE, x.SUMILLA, x.IDCATEGORIA, x.CATEGORIA, ");
            sql.append("       x.FECHA, x.IDTIPOCONOCIMIENTO, x.TIPOCONOCIMIENTO, x.IDESTADO, x.ESTADO ");
            sql.append("FROM (SELECT ");
            sql.append("            a.nbaselegalid AS ID, a.vnumero AS NUMERO, a.vnombre AS NOMBRE, a.vsumilla AS SUMILLA, ");
            sql.append("            a.ncategoriaid AS IDCATEGORIA, b.vnombre AS CATEGORIA, a.dfechapublicacion AS FECHA, ");
            sql.append("            1 AS IDTIPOCONOCIMIENTO, 'Base Legal' AS TIPOCONOCIMIENTO, a.nestadoid AS IDESTADO, c.vnombre AS ESTADO ");
            sql.append("        FROM TBASELEGAL a ");
            sql.append("        INNER JOIN MTCATEGORIA b ON a.ncategoriaid = b.ncategoriaid ");
            sql.append("        INNER JOIN MTESTADO_BASELEGAL c ON a.nestadoid = c.nestadoid ");
            sql.append("        WHERE a.nactivo = :ACTIVO ");
            sql.append("        AND c.nestadoid IN (3,4,5,6) ");
            sql.append("        ) x ");
            sql.append("UNION ");
            sql.append("SELECT y.ID, y.NUMERO, y.NOMBRE, y.SUMILLA, y.IDCATEGORIA, y.CATEGORIA, ");
            sql.append("       y.FECHA, y.IDTIPOCONOCIMIENTO, y.TIPOCONOCIMIENTO, y.IDESTADO, y.ESTADO ");
            sql.append("FROM (SELECT ");
            sql.append("            a.npreguntaid AS ID, '' AS NUMERO, a.vasunto AS NOMBRE, a.vdetalle AS SUMILLA, ");
            sql.append("            a.ncategoriaid AS IDCATEGORIA, b.vnombre AS CATEGORIA, a.dfechacreacion AS FECHA, ");
            sql.append("            2 AS IDTIPOCONOCIMIENTO, 'Preguntas y Respuestas' AS TIPOCONOCIMIENTO, a.nsituacion AS IDESTADO, c.vnombre AS ESTADO ");
            sql.append("        FROM TPREGUNTA a ");
            sql.append("        INNER JOIN MTCATEGORIA b ON a.ncategoriaid = b.ncategoriaid ");
            sql.append("        INNER JOIN MTSITUACION c ON a.nsituacion = c.nsituacionid ");
            sql.append("        WHERE a.nactivo = :ACTIVO ");
            sql.append("        AND c.nsituacionid = 6 ");
            sql.append("        ) y ");
            sql.append("UNION ");
            sql.append("SELECT z.ID, z.NUMERO, z.NOMBRE, z.SUMILLA, z.IDCATEGORIA, z.CATEGORIA, ");
            sql.append("       z.FECHA, z.IDTIPOCONOCIMIENTO, z.TIPOCONOCIMIENTO, z.IDESTADO, z.ESTADO ");
            sql.append("FROM (SELECT ");
            sql.append("            a.npreguntaid AS ID, '' AS NUMERO, a.vasunto AS NOMBRE, a.vdetalle AS SUMILLA, ");
            sql.append("            a.ncategoriaid AS IDCATEGORIA, b.vnombre AS CATEGORIA, a.dfechacreacion AS FECHA, ");
            sql.append("            2 AS IDTIPOCONOCIMIENTO, 'Preguntas y Respuestas' AS TIPOCONOCIMIENTO, a.nsituacion AS IDESTADO, c.vnombre AS ESTADO ");
            sql.append("        FROM TCONOCIMIENTO a ");
            sql.append("        INNER JOIN MTCATEGORIA b ON a.ncategoriaid = b.ncategoriaid ");
            sql.append("        INNER JOIN MTSITUACION c ON a.nsituacion = c.nsituacionid ");
            sql.append("        WHERE a.nactivo = :ACTIVO ");
            sql.append("        AND c.nsituacionid = 6 ");
            sql.append("        ) z ");
            sql.append("ORDER BY 5, 7 DESC ");

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
}
