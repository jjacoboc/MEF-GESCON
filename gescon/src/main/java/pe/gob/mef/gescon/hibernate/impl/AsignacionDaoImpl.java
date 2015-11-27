/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.gob.mef.gescon.hibernate.impl;

import java.math.BigDecimal;
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
import org.springframework.transaction.annotation.Transactional;
import pe.gob.mef.gescon.common.Constante;
import pe.gob.mef.gescon.hibernate.dao.AsignacionDao;
import pe.gob.mef.gescon.hibernate.domain.Mtcategoria;
import pe.gob.mef.gescon.hibernate.domain.Mtuser;
import pe.gob.mef.gescon.hibernate.domain.Tasignacion;

/**
 *
 * @author JJacobo
 */
@Repository(value = "AsignacionDao")
public class AsignacionDaoImpl extends HibernateDaoSupport implements AsignacionDao {

    /**
     * Crea una nueva instancia de AsignacionDaoImpl
     *
     * @param sessionFactory
     */
    @Autowired
    public AsignacionDaoImpl(SessionFactory sessionFactory) {
        this.setSessionFactory(sessionFactory);
    }

    @Override
    public BigDecimal getNextPK() throws Exception {
        return (BigDecimal) getHibernateTemplate().execute(
                new HibernateCallback() {
                    @Override
                    public Object doInHibernate(Session session) throws HibernateException {
                        Query query = session.createSQLQuery("SELECT SEQ_TASIGNACION.NEXTVAL FROM DUAL");
                        return query.uniqueResult();
                    }
                });
    }

    @Override
    public BigDecimal getNumberNotificationsByMtuser(Mtuser mtuser) throws Exception {
        final StringBuilder sql = new StringBuilder();
        sql.append("SELECT COUNT(1) FROM TASIGNACION t ");
        sql.append("WHERE t.nusuarioid = ").append(mtuser.getNusuarioid()).append(" ");
        sql.append("AND ((t.dfechaasignacion is not null and t.dfecharecepcion is null and t.dfechaatencion is null) ");
        sql.append("OR (t.dfechaasignacion is not null and t.dfecharecepcion is not null and t.dfechaatencion is null) ");
        sql.append("OR (t.dfechaasignacion is not null and t.dfecharecepcion is not null and t.dfechaatencion is not null)) ");

        return (BigDecimal) getHibernateTemplate().execute(
                new HibernateCallback() {
                    @Override
                    public Object doInHibernate(Session session) throws HibernateException {
                        Query query = session.createSQLQuery(sql.toString());
                        return query.uniqueResult();
                    }
                });
    }

    @Override
    public BigDecimal getNumberNotificationsAssignedByMtuser(Mtuser mtuser) throws Exception {
        final StringBuilder sql = new StringBuilder();
        sql.append("SELECT COUNT(1) FROM TASIGNACION t ");
        sql.append("WHERE t.nusuarioid = ").append(mtuser.getNusuarioid()).append(" ");
        sql.append("AND (t.dfechaasignacion is not null and t.dfecharecepcion is null and t.dfechaatencion is null) ");

        return (BigDecimal) getHibernateTemplate().execute(
                new HibernateCallback() {
                    @Override
                    public Object doInHibernate(Session session) throws HibernateException {
                        Query query = session.createSQLQuery(sql.toString());
                        return query.uniqueResult();
                    }
                });
    }

    @Override
    public BigDecimal getNumberNotificationsReceivedByMtuser(Mtuser mtuser) throws Exception {
        final StringBuilder sql = new StringBuilder();
        sql.append("SELECT COUNT(1) FROM TASIGNACION t ");
        sql.append("WHERE t.nusuarioid = ").append(mtuser.getNusuarioid()).append(" ");
        sql.append("AND (t.dfechaasignacion is not null and t.dfecharecepcion is not null and t.dfechaatencion is null) ");

        return (BigDecimal) getHibernateTemplate().execute(
                new HibernateCallback() {
                    @Override
                    public Object doInHibernate(Session session) throws HibernateException {
                        Query query = session.createSQLQuery(sql.toString());
                        return query.uniqueResult();
                    }
                });
    }

    @Override
    public BigDecimal getNumberNotificationsServedByMtuser(Mtuser mtuser) throws Exception {
        final StringBuilder sql = new StringBuilder();
        sql.append("SELECT COUNT(1) FROM TASIGNACION t ");
        sql.append("WHERE t.nusuarioid = ").append(mtuser.getNusuarioid()).append(" ");
        sql.append("AND (t.dfechaasignacion is not null and t.dfecharecepcion is not null and t.dfechaatencion is not null) ");

        return (BigDecimal) getHibernateTemplate().execute(
                new HibernateCallback() {
                    @Override
                    public Object doInHibernate(Session session) throws HibernateException {
                        Query query = session.createSQLQuery(sql.toString());
                        return query.uniqueResult();
                    }
                });
    }

    @Override
    public List<HashMap> getNotificationsAssignedPanelByMtuser(Mtuser mtuser) throws Exception {
        final StringBuilder sql = new StringBuilder();
        sql.append("SELECT a.nbaselegalid AS ID, a.vnumero AS NUMERO, a.vnombre AS NOMBRE, a.vsumilla AS SUMILLA, ");
        sql.append("        a.ncategoriaid AS IDCATEGORIA, b.vnombre AS CATEGORIA, a.dfechapublicacion AS FECHA, ");
        sql.append("        1 AS IDTIPOCONOCIMIENTO, 'Base Legal' AS TIPOCONOCIMIENTO, a.nestadoid AS IDESTADO, c.vnombre AS ESTADO ");
        sql.append("FROM TBASELEGAL a ");
        sql.append("INNER JOIN MTCATEGORIA b ON a.ncategoriaid = b.ncategoriaid ");
        sql.append("INNER JOIN MTESTADO_BASELEGAL c ON a.nestadoid = c.nestadoid ");
        sql.append("INNER JOIN TASIGNACION t ON t.nconocimientoid = a.nbaselegalid AND t.ntipoconocimientoid = 1 ");
        sql.append("WHERE t.nusuarioid = ").append(mtuser.getNusuarioid()).append(" ");
        sql.append("AND (t.dfechaasignacion is not null and t.dfecharecepcion is null and t.dfechaatencion is null) ");
        sql.append("UNION ");
        sql.append("SELECT a.npreguntaid AS ID, '' AS NUMERO, a.vasunto AS NOMBRE, a.vdetalle AS SUMILLA, ");
        sql.append("       a.ncategoriaid AS IDCATEGORIA, b.vnombre AS CATEGORIA, a.dfechacreacion AS FECHA, ");
        sql.append("       2 AS IDTIPOCONOCIMIENTO, 'Preguntas y Respuestas' AS TIPOCONOCIMIENTO, ");
        sql.append("       a.nsituacionid AS IDESTADO, c.vnombre AS ESTADO ");
        sql.append("FROM TPREGUNTA a ");
        sql.append("INNER JOIN MTCATEGORIA b ON a.ncategoriaid = b.ncategoriaid ");
        sql.append("INNER JOIN MTSITUACION c ON a.nsituacionid = c.nsituacionid ");
        sql.append("INNER JOIN TASIGNACION t ON t.nconocimientoid = a.npreguntaid AND t.ntipoconocimientoid = 2 ");
        sql.append("WHERE t.nusuarioid = ").append(mtuser.getNusuarioid()).append(" ");
        sql.append("AND (t.dfechaasignacion is not null and t.dfecharecepcion is null and t.dfechaatencion is null) ");
        sql.append("UNION ");
        sql.append("SELECT a.nconocimientoid AS ID, '' AS NUMERO, a.vtitulo AS NOMBRE, a.vdescripcion AS SUMILLA, ");
        sql.append("       a.ncategoriaid AS IDCATEGORIA, b.vnombre AS CATEGORIA, a.dfechacreacion AS FECHA, ");
        sql.append("       a.ntpoconocimientoid AS IDTIPOCONOCIMIENTO, d.vnombre AS TIPOCONOCIMIENTO, ");
        sql.append("       a.nactivo AS IDESTADO, c.vnombre AS ESTADO ");
        sql.append("FROM TCONOCIMIENTO a ");
        sql.append("INNER JOIN MTCATEGORIA b ON a.ncategoriaid = b.ncategoriaid ");
        sql.append("INNER JOIN MTSITUACION c ON a.nactivo = c.nsituacionid ");
        sql.append("INNER JOIN MTTIPO_CONOCIMIENTO d ON a.ntpoconocimientoid = d.ntpoconocimientoid ");
        sql.append("INNER JOIN TASIGNACION t ON t.nconocimientoid = a.nconocimientoid AND t.ntipoconocimientoid = a.ntpoconocimientoid ");
        sql.append("WHERE t.nusuarioid = ").append(mtuser.getNusuarioid()).append(" ");
        sql.append("AND (t.dfechaasignacion is not null and t.dfecharecepcion is null and t.dfechaatencion is null) ");

        return (List<HashMap>) getHibernateTemplate().execute(
                new HibernateCallback() {
                    @Override
                    public Object doInHibernate(Session session) throws HibernateException {
                        Query query = session.createSQLQuery(sql.toString());
                        query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
                        return query.list();
                    }
                });
    }

    @Override
    public List<HashMap> getNotificationsReceivedPanelByMtuser(Mtuser mtuser) throws Exception {
        final StringBuilder sql = new StringBuilder();
        sql.append("SELECT a.nbaselegalid AS ID, a.vnumero AS NUMERO, a.vnombre AS NOMBRE, a.vsumilla AS SUMILLA, ");
        sql.append("        a.ncategoriaid AS IDCATEGORIA, b.vnombre AS CATEGORIA, a.dfechapublicacion AS FECHA, ");
        sql.append("        1 AS IDTIPOCONOCIMIENTO, 'Base Legal' AS TIPOCONOCIMIENTO, a.nestadoid AS IDESTADO, c.vnombre AS ESTADO ");
        sql.append("FROM TBASELEGAL a ");
        sql.append("INNER JOIN MTCATEGORIA b ON a.ncategoriaid = b.ncategoriaid ");
        sql.append("INNER JOIN MTESTADO_BASELEGAL c ON a.nestadoid = c.nestadoid ");
        sql.append("INNER JOIN TASIGNACION t ON t.nconocimientoid = a.nbaselegalid AND t.ntipoconocimientoid = 1 ");
        sql.append("WHERE t.nusuarioid = ").append(mtuser.getNusuarioid()).append(" ");
        sql.append("AND (t.dfechaasignacion is not null and t.dfecharecepcion is not null and t.dfechaatencion is null) ");
        sql.append("UNION ");
        sql.append("SELECT a.npreguntaid AS ID, '' AS NUMERO, a.vasunto AS NOMBRE, a.vdetalle AS SUMILLA, ");
        sql.append("       a.ncategoriaid AS IDCATEGORIA, b.vnombre AS CATEGORIA, a.dfechacreacion AS FECHA, ");
        sql.append("       2 AS IDTIPOCONOCIMIENTO, 'Preguntas y Respuestas' AS TIPOCONOCIMIENTO, ");
        sql.append("       a.nsituacionid AS IDESTADO, c.vnombre AS ESTADO ");
        sql.append("FROM TPREGUNTA a ");
        sql.append("INNER JOIN MTCATEGORIA b ON a.ncategoriaid = b.ncategoriaid ");
        sql.append("INNER JOIN MTSITUACION c ON a.nsituacionid = c.nsituacionid ");
        sql.append("INNER JOIN TASIGNACION t ON t.nconocimientoid = a.npreguntaid AND t.ntipoconocimientoid = 2 ");
        sql.append("WHERE t.nusuarioid = ").append(mtuser.getNusuarioid()).append(" ");
        sql.append("AND (t.dfechaasignacion is not null and t.dfecharecepcion is not null and t.dfechaatencion is null) ");
        sql.append("UNION ");
        sql.append("SELECT a.nconocimientoid AS ID, '' AS NUMERO, a.vtitulo AS NOMBRE, a.vdescripcion AS SUMILLA, ");
        sql.append("       a.ncategoriaid AS IDCATEGORIA, b.vnombre AS CATEGORIA, a.dfechacreacion AS FECHA, ");
        sql.append("       a.ntpoconocimientoid AS IDTIPOCONOCIMIENTO, d.vnombre AS TIPOCONOCIMIENTO, ");
        sql.append("       a.nactivo AS IDESTADO, c.vnombre AS ESTADO ");
        sql.append("FROM TCONOCIMIENTO a ");
        sql.append("INNER JOIN MTCATEGORIA b ON a.ncategoriaid = b.ncategoriaid ");
        sql.append("INNER JOIN MTSITUACION c ON a.nactivo = c.nsituacionid ");
        sql.append("INNER JOIN MTTIPO_CONOCIMIENTO d ON a.ntpoconocimientoid = d.ntpoconocimientoid ");
        sql.append("INNER JOIN TASIGNACION t ON t.nconocimientoid = a.nconocimientoid AND t.ntipoconocimientoid = a.ntpoconocimientoid ");
        sql.append("WHERE t.nusuarioid = ").append(mtuser.getNusuarioid()).append(" ");
        sql.append("AND (t.dfechaasignacion is not null and t.dfecharecepcion is not null and t.dfechaatencion is null) ");

        return (List<HashMap>) getHibernateTemplate().execute(
                new HibernateCallback() {
                    @Override
                    public Object doInHibernate(Session session) throws HibernateException {
                        Query query = session.createSQLQuery(sql.toString());
                        query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
                        return query.list();
                    }
                });
    }

    @Override
    public List<HashMap> getNotificationsServedPanelByMtuser(Mtuser mtuser) throws Exception {
        final StringBuilder sql = new StringBuilder();
        sql.append("SELECT a.nbaselegalid AS ID, a.vnumero AS NUMERO, a.vnombre AS NOMBRE, a.vsumilla AS SUMILLA, ");
        sql.append("        a.ncategoriaid AS IDCATEGORIA, b.vnombre AS CATEGORIA, a.dfechapublicacion AS FECHA, ");
        sql.append("        1 AS IDTIPOCONOCIMIENTO, 'Base Legal' AS TIPOCONOCIMIENTO, a.nestadoid AS IDESTADO, c.vnombre AS ESTADO ");
        sql.append("FROM TBASELEGAL a ");
        sql.append("INNER JOIN MTCATEGORIA b ON a.ncategoriaid = b.ncategoriaid ");
        sql.append("INNER JOIN MTESTADO_BASELEGAL c ON a.nestadoid = c.nestadoid ");
        sql.append("INNER JOIN TASIGNACION t ON t.nconocimientoid = a.nbaselegalid AND t.ntipoconocimientoid = 1 ");
        sql.append("WHERE t.nusuarioid = ").append(mtuser.getNusuarioid()).append(" ");
        sql.append("AND (t.dfechaasignacion is not null and t.dfecharecepcion is not null and t.dfechaatencion is not null) ");
        sql.append("UNION ");
        sql.append("SELECT a.npreguntaid AS ID, '' AS NUMERO, a.vasunto AS NOMBRE, a.vdetalle AS SUMILLA, ");
        sql.append("       a.ncategoriaid AS IDCATEGORIA, b.vnombre AS CATEGORIA, a.dfechacreacion AS FECHA, ");
        sql.append("       2 AS IDTIPOCONOCIMIENTO, 'Preguntas y Respuestas' AS TIPOCONOCIMIENTO, ");
        sql.append("       a.nsituacionid AS IDESTADO, c.vnombre AS ESTADO ");
        sql.append("FROM TPREGUNTA a ");
        sql.append("INNER JOIN MTCATEGORIA b ON a.ncategoriaid = b.ncategoriaid ");
        sql.append("INNER JOIN MTSITUACION c ON a.nsituacionid = c.nsituacionid ");
        sql.append("INNER JOIN TASIGNACION t ON t.nconocimientoid = a.npreguntaid AND t.ntipoconocimientoid = 2 ");
        sql.append("WHERE t.nusuarioid = ").append(mtuser.getNusuarioid()).append(" ");
        sql.append("AND (t.dfechaasignacion is not null and t.dfecharecepcion is not null and t.dfechaatencion is not null) ");
        sql.append("UNION ");
        sql.append("SELECT a.nconocimientoid AS ID, '' AS NUMERO, a.vtitulo AS NOMBRE, a.vdescripcion AS SUMILLA, ");
        sql.append("       a.ncategoriaid AS IDCATEGORIA, b.vnombre AS CATEGORIA, a.dfechacreacion AS FECHA, ");
        sql.append("       a.ntpoconocimientoid AS IDTIPOCONOCIMIENTO, d.vnombre AS TIPOCONOCIMIENTO, ");
        sql.append("       a.nactivo AS IDESTADO, c.vnombre AS ESTADO ");
        sql.append("FROM TCONOCIMIENTO a ");
        sql.append("INNER JOIN MTCATEGORIA b ON a.ncategoriaid = b.ncategoriaid ");
        sql.append("INNER JOIN MTSITUACION c ON a.nactivo = c.nsituacionid ");
        sql.append("INNER JOIN MTTIPO_CONOCIMIENTO d ON a.ntpoconocimientoid = d.ntpoconocimientoid ");
        sql.append("INNER JOIN TASIGNACION t ON t.nconocimientoid = a.nconocimientoid AND t.ntipoconocimientoid = a.ntpoconocimientoid ");
        sql.append("WHERE t.nusuarioid = ").append(mtuser.getNusuarioid()).append(" ");
        sql.append("AND (t.dfechaasignacion is not null and t.dfecharecepcion is not null and t.dfechaatencion is not null) ");

        return (List<HashMap>) getHibernateTemplate().execute(
                new HibernateCallback() {
                    @Override
                    public Object doInHibernate(Session session) throws HibernateException {
                        Query query = session.createSQLQuery(sql.toString());
                        query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
                        return query.list();
                    }
                });
    }

    @Override
    public BigDecimal getModeratorByMtcategoria(BigDecimal ncategoriaid) throws Exception {
        final StringBuilder sql = new StringBuilder();
        sql.append("SELECT NUSUARIOID FROM TCATEGORIA_USER  ");
        sql.append("WHERE ncategoriaid = ").append(ncategoriaid).append(" ");
        sql.append("AND nperfilid = ").append(Constante.MODERADOR).append(" ");

        return (BigDecimal) getHibernateTemplate().execute(
                new HibernateCallback() {
                    @Override
                    public Object doInHibernate(Session session) throws HibernateException {
                        Query query = session.createSQLQuery(sql.toString());
                        return query.uniqueResult();
                    }
                });
    }

    @Override
    public BigDecimal getEspecialistaByMtcategoria(BigDecimal ncategoriaid) throws Exception {
        final StringBuilder sql = new StringBuilder();
        sql.append("SELECT NUSUARIOID FROM TCATEGORIA_USER  ");
        sql.append("WHERE ncategoriaid = ").append(ncategoriaid).append(" ");
        sql.append("AND nperfilid = ").append(Constante.ESPECIALISTA).append(" ");

        return (BigDecimal) getHibernateTemplate().execute(
                new HibernateCallback() {
                    @Override
                    public Object doInHibernate(Session session) throws HibernateException {
                        Query query = session.createSQLQuery(sql.toString());
                        return query.uniqueResult();
                    }
                });
    }

    @Override
    public BigDecimal getUserCreacionByPregunta(BigDecimal npreguntaid) throws Exception {
        final StringBuilder sql = new StringBuilder();
        sql.append("SELECT NUSUARIOID FROM MTUSER WHERE VLOGIN= (SELECT VUSUARIOCREACION FROM TPREGUNTA  ");
        sql.append("WHERE npreguntaid = ").append(npreguntaid).append(" )");

        return (BigDecimal) getHibernateTemplate().execute(
                new HibernateCallback() {
                    @Override
                    public Object doInHibernate(Session session) throws HibernateException {
                        Query query = session.createSQLQuery(sql.toString());
                        return query.uniqueResult();
                    }
                });
    }

    @Override
    public BigDecimal getUserCreacionByBaseLegal(BigDecimal nbaselegalid) throws Exception {
        final StringBuilder sql = new StringBuilder();
        sql.append("SELECT NUSUARIOID FROM MTUSER WHERE VLOGIN= (SELECT VUSUARIOCREACION FROM TBASELEGAL  ");
        sql.append("WHERE nbaselegalid = ").append(nbaselegalid).append(" )");

        return (BigDecimal) getHibernateTemplate().execute(
                new HibernateCallback() {
                    @Override
                    public Object doInHibernate(Session session) throws HibernateException {
                        Query query = session.createSQLQuery(sql.toString());
                        return query.uniqueResult();
                    }
                });
    }

    @Override
    @Transactional(readOnly = false)
    public void saveOrUpdate(Tasignacion tasignacion) throws Exception {
        getHibernateTemplate().saveOrUpdate(tasignacion);
    }

}
