package com.cotemig.dao;

import com.cotemig.model.Resident;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

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
    public Resident getResidentByCpf(String cpf) {
        Session session = this.sessionFactory.getCurrentSession();

        Criteria criteria = session.createCriteria(Resident.class);
        criteria.add(Restrictions.eq("cpf", cpf));

        Resident resident = (Resident) criteria.uniqueResult();

        return resident;
    }

    @Override
    public void removeResident(String cpf) {
        Session session = this.sessionFactory.getCurrentSession();

        Criteria criteria = session.createCriteria(Resident.class);
        criteria.add(Restrictions.eq("cpf", cpf));

        Resident resident = (Resident) criteria.uniqueResult();

        if(resident != null){
            session.delete(resident);
        }
    }
}
