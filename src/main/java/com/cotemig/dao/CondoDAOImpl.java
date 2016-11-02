package com.cotemig.dao;

import com.cotemig.model.Condo;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

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
    public Condo getCondoById(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        Condo condo = (Condo) session.load(Condo.class, new Integer(id));

        return condo;
    }

    @Override
    public void removeCondo(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        Condo condo = (Condo) session.load(Condo.class, new Integer(id));

        if(condo != null){
            session.delete(condo);
        }
    }
}
