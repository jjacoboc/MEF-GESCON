/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.gob.mef.gescon.hibernate.impl;

import java.util.HashMap;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateCallback;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;
import pe.gob.mef.gescon.hibernate.dao.ReporteDao;

/**
 *
 * @author JJacobo
 */
@Repository(value = "ReporteDao")
public class ReporteDaoImpl extends HibernateDaoSupport implements ReporteDao {

    /**
     * Crea una nueva instancia de MaestroDaoImpl
     *
     * @param sessionFactory
     */
    @Autowired
    public ReporteDaoImpl(SessionFactory sessionFactory) {
        this.setSessionFactory(sessionFactory);
    }

    @Override
    public List<HashMap<String, Object>> listarUsuarios(final HashMap parametros) throws Exception {
        final StringBuilder sql = new StringBuilder();
        Object object = null;

        try {
            sql.append("SELECT VNOMBRES ||' '|| VAPELLIDOS AS NOMBRE ,VLOGIN AS LOGIN,VUSUARIOCREACION AS USUARIO_CREACION , ");
            sql.append(" DFECHACREACION AS FECHA_CREACION, VUSUARIOMODIFICACION AS USUARIO_MODIFICACION, ");
            sql.append(" DFECHAMODIFICACION AS FECHA_MODIFICACION, CASE when NACTIVO =1 THEN 'ACTIVO' ELSE 'DESACIVADO' END AS ESTADO ");
            sql.append(" FROM mtuser WHERE 1=1 ");
            if (parametros.get("FECHAINI") != null && parametros.get("FECHAFIN") != null) {
                sql.append(" AND TRUNC(dfechacreacion) >= :FECHAINI AND TRUNC(dfechacreacion) <= :FECHAFIN ");
            }
            object = getHibernateTemplate().execute(
                    new HibernateCallback() {
                        @Override
                        public Object doInHibernate(Session session) throws HibernateException {
                            Query query = session.createSQLQuery(sql.toString());
                            if (parametros.get("FECHAINI") != null && parametros.get("FECHAFIN") != null) {
                                query.setParameter("FECHAINI", parametros.get("FECHAINI"));
                                query.setParameter("FECHAFIN", parametros.get("FECHAFIN"));
                            }
                            query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
                            return query.list();
                        }
                    });
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
        return (List<HashMap<String, Object>>) object;
    }

    @Override
    public List<HashMap<String, Object>> listarPerfiles(final HashMap parametros) throws Exception {
        final StringBuilder sql = new StringBuilder();
        Object object = null;
        try {
            sql.append("SELECT PF.VNOMBRE AS NOMBRE_PERFIL, PL.VNOMBRE AS NOMBRE_POLITICA, PL.VDESCRIPCION AS DESCRIPCION, CASE WHEN PL.NACTIVO =1 THEN 'ACTIVO' ELSE 'DESACIVADO' END AS ESTADO, ");
            sql.append("PP.VUSUARIOCREACION AS USUARIO_CREACION, PP.DFECHACREACION AS FECHA_CREACION, PP.VUSUARIOMODIFICACION AS USUARIO_MODIFICACION, PP.Dfechamodificacion AS FECHA_MODIFICACION ");
            sql.append("FROM TPOLITICA_PERFIL PP ");
            sql.append("INNER JOIN MTPERFIL PF ON PP.NPERFILID = PF.NPERFILID ");
            sql.append("INNER JOIN MTPOLITICA PL ON PP.NPOLITICAID = PL.NPOLITICAID ");
            sql.append("WHERE 1 = 1 ");
            if (parametros.get("FECHAINI") != null && parametros.get("FECHAFIN") != null) {
                sql.append(" AND TRUNC(PP.dfechacreacion) >= :FECHAINI AND TRUNC(PP.dfechacreacion) <= :FECHAFIN ");
            }
            sql.append("ORDER BY PF.VNOMBRE, PL.VNOMBRE ");

            object = getHibernateTemplate().execute(
                    new HibernateCallback() {
                        @Override
                        public Object doInHibernate(Session session) throws HibernateException {
                            Query query = session.createSQLQuery(sql.toString());
                            if (parametros.get("FECHAINI") != null && parametros.get("FECHAFIN") != null) {
                                query.setParameter("FECHAINI", parametros.get("FECHAINI"));
                                query.setParameter("FECHAFIN", parametros.get("FECHAFIN"));
                            }
                            query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
                            return query.list();
                        }
                    });
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
        return (List<HashMap<String, Object>>) object;
    }

    @Override
    public List<HashMap<String, Object>> listarConocimientos(final HashMap parametros) throws Exception {
        final StringBuilder sql = new StringBuilder();
        Object object = null;
        try {
            sql.append("SELECT a.vnumero AS NOMBRE, b.vnombre AS CATEGORIA,a.vusuariocreacion AS USUARIO_CREACION, ");
            sql.append("  a.dfechacreacion AS FECHA_CREACION, a.vusuariomodificacion AS USUARIO_MODIFICACION, ");
            sql.append("  a.dfechamodificacion AS FECHA_MODIFICACION, 'Base Legal' AS TIPOCONOCIMIENTO,  c.vnombre AS ESTADO  ");
            sql.append(" FROM TBASELEGAL a ");
            sql.append(" INNER JOIN MTCATEGORIA b ON a.ncategoriaid = b.ncategoriaid ");
            sql.append(" INNER JOIN MTESTADO_BASELEGAL c ON a.nestadoid = c.nestadoid ");
            sql.append(" WHERE 1=1 ");
            if (parametros.get("FECHAINI") != null && parametros.get("FECHAFIN") != null) {
                sql.append(" AND TRUNC(a.dfechacreacion) >= :FECHAINI AND TRUNC(a.dfechacreacion) <= :FECHAFIN ");
            }
            sql.append(" UNION ");
            sql.append("SELECT a.vasunto AS NOMBRE, b.vnombre AS CATEGORIA, a.vusuariocreacion AS USUARIO_CREACION, ");
            sql.append("  a.dfechacreacion AS FECHA_CREACION, a.vusuariomodificacion AS USUARIO_MODIFICACION, ");
            sql.append("  a.dfechamodificacion AS FECHA_MODIFICACION, 'Preguntas y Respuestas' AS TIPOCONOCIMIENTO, c.vnombre AS ESTADO ");
            sql.append(" FROM TPREGUNTA a ");
            sql.append(" INNER JOIN MTCATEGORIA b ON a.ncategoriaid = b.ncategoriaid ");
            sql.append(" INNER JOIN MTSITUACION c ON a.nsituacionid = c.nsituacionid ");
            sql.append(" WHERE 1=1 ");
            if (parametros.get("FECHAINI") != null && parametros.get("FECHAFIN") != null) {
                sql.append(" AND TRUNC(a.dfechacreacion) >= :FECHAINI AND TRUNC(a.dfechacreacion) <= :FECHAFIN ");
            }
            sql.append(" UNION ");
            sql.append("SELECT a.vtitulo AS NOMBRE, b.vnombre AS CATEGORIA, a.vusuariocreacion AS USUARIO_CREACION, ");
            sql.append("  a.dfechacreacion AS FECHA_CREACION, a.vusuariomodificacion AS USUARIO_MODIFICACION, ");
            sql.append("  a.dfechamodificacion AS FECHA_MODIFICACION, d.vnombre AS TIPOCONOCIMIENTO,  c.vnombre AS ESTADO ");
            sql.append(" FROM TCONOCIMIENTO a ");
            sql.append(" INNER JOIN MTCATEGORIA b ON a.ncategoriaid = b.ncategoriaid ");
            sql.append(" INNER JOIN MTSITUACION c ON a.nsituacionid = c.nsituacionid ");
            sql.append(" INNER JOIN MTTIPO_CONOCIMIENTO d ON a.ntpoconocimientoid = d.ntpoconocimientoid ");
            sql.append(" WHERE 1=1 ");
            if (parametros.get("FECHAINI") != null && parametros.get("FECHAFIN") != null) {
                sql.append(" AND TRUNC(a.dfechacreacion) >= :FECHAINI AND TRUNC(a.dfechacreacion) <= :FECHAFIN ");
            }

            if (parametros.get("FECHAINI") != null && parametros.get("FECHAFIN") != null) {

                object = getHibernateTemplate().execute(
                        new HibernateCallback() {
                            @Override
                            public Object doInHibernate(Session session) throws HibernateException {
                                Query query = session.createSQLQuery(sql.toString())
                                .setParameter("FECHAINI", parametros.get("FECHAINI"))
                                .setParameter("FECHAFIN", parametros.get("FECHAFIN"))
                                .setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
                                return query.list();
                            }
                        });
            } else {
                object = getHibernateTemplate().execute(
                        new HibernateCallback() {
                            @Override
                            public Object doInHibernate(Session session) throws HibernateException {
                                Query query = session.createSQLQuery(sql.toString())
                                .setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
                                return query.list();
                            }
                        });
            }

        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
        return (List<HashMap<String, Object>>) object;
    }

    @Override
    public List<HashMap<String, Object>> listarCalificaciones(final HashMap parametros) throws Exception {
        final StringBuilder sql = new StringBuilder();
        Object object = null;
        try {
            sql.append("SELECT a.vnumero AS NOMBRE, b.vnombre AS CATEGORIA,a.vusuariocreacion AS USUARIO_CREACION, ");
            sql.append("  a.dfechacreacion AS FECHA_CREACION, a.vusuariomodificacion AS USUARIO_MODIFICACION, ");
            sql.append("  a.dfechamodificacion AS FECHA_MODIFICACION, 'Base Legal' AS TIPOCONOCIMIENTO,  c.vnombre AS ESTADO , TO_NUMBER(cl.ncalificacion) AS CALIFICACION  ");
            sql.append(" FROM TBASELEGAL a ");
            sql.append(" INNER JOIN MTCATEGORIA b ON a.ncategoriaid = b.ncategoriaid ");
            sql.append(" INNER JOIN MTESTADO_BASELEGAL c ON a.nestadoid = c.nestadoid ");
            sql.append(" INNER JOIN tcalificacion_baselegal cl ON cl.NBASELEGALID = a.nbaselegalid ");
            sql.append(" WHERE 1=1 ");
            if (parametros.get("FECHAINI") != null && parametros.get("FECHAFIN") != null) {
                sql.append(" AND TRUNC(a.dfechacreacion) >= :FECHAINI AND TRUNC(a.dfechacreacion) <= :FECHAFIN ");
            }
            sql.append(" UNION ");
            sql.append("SELECT a.vasunto AS NOMBRE, b.vnombre AS CATEGORIA, a.vusuariocreacion AS USUARIO_CREACION, ");
            sql.append("  a.dfechacreacion AS FECHA_CREACION, a.vusuariomodificacion AS USUARIO_MODIFICACION, ");
            sql.append("  a.dfechamodificacion AS FECHA_MODIFICACION, 'Preguntas y Respuestas' AS TIPOCONOCIMIENTO, c.vnombre AS ESTADO, TO_NUMBER(cl.ncalificacion) AS CALIFICACION  ");
            sql.append(" FROM TPREGUNTA a ");
            sql.append(" INNER JOIN MTCATEGORIA b ON a.ncategoriaid = b.ncategoriaid ");
            sql.append(" INNER JOIN MTSITUACION c ON a.nsituacionid = c.nsituacionid ");
            sql.append(" INNER JOIN TCALIFICACION_PREGUNTA cl ON cl.npreguntaid = a.npreguntaid ");
            sql.append(" WHERE 1=1 ");
            if (parametros.get("FECHAINI") != null && parametros.get("FECHAFIN") != null) {
                sql.append(" AND TRUNC(a.dfechacreacion) >= :FECHAINI AND TRUNC(a.dfechacreacion) <= :FECHAFIN ");
            }
            sql.append(" UNION ");
            sql.append("SELECT a.vtitulo AS NOMBRE, b.vnombre AS CATEGORIA, a.vusuariocreacion AS USUARIO_CREACION, ");
            sql.append("  a.dfechacreacion AS FECHA_CREACION, a.vusuariomodificacion AS USUARIO_MODIFICACION, ");
            sql.append("  a.dfechamodificacion AS FECHA_MODIFICACION, d.vnombre AS TIPOCONOCIMIENTO,  c.vnombre AS ESTADO, TO_NUMBER(cl.ncalificacion) as CALIFICACION  ");
            sql.append(" FROM TCONOCIMIENTO a ");
            sql.append(" INNER JOIN MTCATEGORIA b ON a.ncategoriaid = b.ncategoriaid ");
            sql.append(" INNER JOIN MTSITUACION c ON a.nsituacionid = c.nsituacionid ");
            sql.append(" INNER JOIN MTTIPO_CONOCIMIENTO d ON a.ntpoconocimientoid = d.ntpoconocimientoid ");
            sql.append(" INNER JOIN TCALIFICACION cl ON cl.NCONOCIMIENTOID = a.NCONOCIMIENTOID ");
            sql.append(" WHERE 1=1 ");
            if (parametros.get("FECHAINI") != null && parametros.get("FECHAFIN") != null) {
                sql.append(" AND TRUNC(a.dfechacreacion) >= :FECHAINI AND TRUNC(a.dfechacreacion) <= :FECHAFIN ");
            }

            if (parametros.get("FECHAINI") != null && parametros.get("FECHAFIN") != null) {

                object = getHibernateTemplate().execute(
                        new HibernateCallback() {
                            @Override
                            public Object doInHibernate(Session session) throws HibernateException {
                                Query query = session.createSQLQuery(sql.toString())
                                .setParameter("FECHAINI", parametros.get("FECHAINI"))
                                .setParameter("FECHAFIN", parametros.get("FECHAFIN"))
                                .setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
                                return query.list();
                            }
                        });
            } else {
                object = getHibernateTemplate().execute(
                        new HibernateCallback() {
                            @Override
                            public Object doInHibernate(Session session) throws HibernateException {
                                Query query = session.createSQLQuery(sql.toString())
                                .setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
                                return query.list();
                            }
                        });
            }

        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
        return (List<HashMap<String, Object>>) object;
    }

}
