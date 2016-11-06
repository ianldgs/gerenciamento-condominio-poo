package com.cotemig.dao;

import com.cotemig.model.Resident;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

/**
 * Created by matheus.elias on 11/2/16.
 */
public class ResidentDAOImpl implements ResidentDAO {

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sf) {
        this.sessionFactory = sf;
    }

    @Override
    public void addResident(Resident resident) {
        Session session = this.sessionFactory.getCurrentSession();
        session.persist(resident);
    }

    @Override
    public void updateResident(Resident resident) {
        Session session = this.sessionFactory.getCurrentSession();
        session.update(resident);
    }

    @Override
    public List<Resident> listResident() {
        Session session = this.sessionFactory.getCurrentSession();
        List<Resident> residentsList = session.createQuery("from Resident").list();

        return residentsList;
    }

    @Override
    public Resident getResidentById(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        Resident resident = (Resident) session.load(Resident.class, new Integer(id));

        return resident;
    }

    @Override
    public void removeResident(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        Resident resident = (Resident) session.load(Resident.class, new Integer(id));

        if(resident != null){
            session.delete(resident);
        }
    }
}
