package com.cotemig.dao;

import com.cotemig.model.Condo;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

import java.util.List;

/**
 * Created by matheus.elias on 11/2/16.
 */
public class CondoDAOImpl implements CondoDAO {

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sf) {
        this.sessionFactory = sf;
    }

    @Override
    public void addCondo(Condo condo) {
        Session session = this.sessionFactory.getCurrentSession();
        session.persist(condo);
    }

    @Override
    public void updateCondo(Condo condo) {
        Session session = this.sessionFactory.getCurrentSession();
        session.update(condo);
    }

    @Override
    public List<Condo> listCondos() {
        Session session = this.sessionFactory.getCurrentSession();
        List<Condo> condosList = session.createQuery("from Condo").list();

        return condosList;
    }

    @Override
    public Condo getCondoByCnpj(String cnpj) {
        Session session = this.sessionFactory.getCurrentSession();

        Criteria criteria = session.createCriteria(Condo.class);
        criteria.add(Restrictions.eq("cnpj", cnpj));

        Condo condo = (Condo) criteria.uniqueResult();

        return condo;
    }

    @Override
    public void removeCondo(String cnpj) {
        Session session = this.sessionFactory.getCurrentSession();

        Criteria criteria = session.createCriteria(Condo.class);
        criteria.add(Restrictions.eq("cnpj", cnpj));

        Condo condo = (Condo) criteria.uniqueResult();

        if(condo != null){
            session.delete(condo);
        }
    }
}
