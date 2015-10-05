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
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateCallback;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pe.gob.mef.gescon.hibernate.dao.PassDao;
import pe.gob.mef.gescon.hibernate.dao.PoliticaPerfilDao;
//import pe.gob.mef.gescon.hibernate.dao.PoliticaDao;
import pe.gob.mef.gescon.hibernate.domain.TpoliticaPerfil;
import pe.gob.mef.gescon.hibernate.domain.Mtperfil;
import pe.gob.mef.gescon.hibernate.domain.Tpass;

//import pe.gob.mef.gescon.hibernate.domain.Mtpolitica;
/**
 *
 * @author SOPORTE
 */
@Repository(value = "PassDao")

public class PassDaoImpl extends HibernateDaoSupport implements PassDao {

    @Autowired
    public PassDaoImpl(SessionFactory sessionFactory) {
        this.setSessionFactory(sessionFactory);
    }

    @Override
    public BigDecimal getNextPK() throws Exception {
        return (BigDecimal) getHibernateTemplate().execute(
                new HibernateCallback() {
                    @Override
                    public Object doInHibernate(Session session) throws HibernateException {
                        Query query = session.createSQLQuery("SELECT SEQ_TPASS.NEXTVAL FROM DUAL");
                        return query.uniqueResult();
                    }
                });
    }

    @Override
    @Transactional(readOnly = false)
    public void saveOrUpdate(Tpass tpass) throws Exception {
        getHibernateTemplate().saveOrUpdate(tpass);
    }

}
