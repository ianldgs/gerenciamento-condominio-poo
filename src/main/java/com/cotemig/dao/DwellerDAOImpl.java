package com.cotemig.dao;

import com.cotemig.model.Dweller;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

/**
 * Created by matheus.elias on 11/2/16.
 */
public class DwellerDAOImpl implements DwellerDAO {

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sf) {
        this.sessionFactory = sf;
    }

    @Override
    public void addDweller(Dweller dweller) {
        Session session = this.sessionFactory.getCurrentSession();
        session.persist(dweller);
    }

    @Override
    public void updateDweller(Dweller dweller) {
        Session session = this.sessionFactory.getCurrentSession();
        session.update(dweller);
    }

    @Override
    public List<Dweller> listDweller() {
        Session session = this.sessionFactory.getCurrentSession();
        List<Dweller> dwellersList = session.createQuery("from Dweller").list();

        return dwellersList;
    }

    @Override
    public Dweller getDwellerById(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        Dweller dweller = (Dweller) session.load(Dweller.class, new Integer(id));

        return dweller;
    }

    @Override
    public void removeDweller(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        Dweller dweller = (Dweller) session.load(Dweller.class, new Integer(id));

        if(dweller != null){
            session.delete(dweller);
        }
    }
}
