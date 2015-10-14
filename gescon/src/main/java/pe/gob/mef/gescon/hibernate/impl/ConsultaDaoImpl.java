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
import org.springframework.orm.hibernate4.HibernateCallback;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;
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
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        final StringBuilder sql = new StringBuilder();
        Object object = null;
        try {
            if(StringUtils.isBlank(fType) || fType.contains("1")) {
                sql.append("SELECT a.nbaselegalid AS ID, a.vnumero AS NUMERO, a.vnombre AS NOMBRE, a.vsumilla AS SUMILLA, ");
                sql.append("       a.ncategoriaid AS IDCATEGORIA, b.vnombre AS CATEGORIA, a.dfechapublicacion AS FECHA, ");
                sql.append("       1 AS IDTIPOCONOCIMIENTO, 'Base Legal' AS TIPOCONOCIMIENTO, a.nestadoid AS IDESTADO, c.vnombre AS ESTADO ");
                sql.append("FROM TBASELEGAL a ");
                sql.append("INNER JOIN MTCATEGORIA b ON a.ncategoriaid = b.ncategoriaid ");
                sql.append("INNER JOIN MTESTADO_BASELEGAL c ON a.nestadoid = c.nestadoid ");
                sql.append("WHERE a.nactivo = :ACTIVO ");
                if(StringUtils.isNotBlank(fCategoria)) {
                    sql.append("AND a.ncategoriaid IN (").append(fCategoria).append(")");
                }
                if(fFromDate != null) {
                    sql.append(" AND a.dfechapublicacion >= TO_DATE('").append(fFromDate).append("','dd/mm/yyyy')");
                }
                if(fToDate != null) {
                    sql.append(" AND a.dfechapublicacion <= TO_DATE('").append(fToDate).append("','dd/mm/yyyy')");
                }
                sql.append(" ORDER BY a.dfechapublicacion DESC ");
            }

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
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
        return (List<HashMap>) object;
    }
}
