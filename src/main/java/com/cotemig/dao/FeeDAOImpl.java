package com.cotemig.dao;

import com.cotemig.model.Fee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

/**
 * Created by matheus.elias on 11/2/16.
 */
public class FeeDAOImpl implements FeeDAO {

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sf) {
        this.sessionFactory = sf;
    }

    @Override
    public void addFee(Fee fee) {
        Session session = this.sessionFactory.getCurrentSession();
        session.persist(fee);
    }

    @Override
    public void updateFee(Fee fee) {
        Session session = this.sessionFactory.getCurrentSession();
        session.update(fee);
    }

    @Override
    public List<Fee> listFee() {
        Session session = this.sessionFactory.getCurrentSession();
        List<Fee> feesList = session.createQuery("from Fee").list();

        return feesList;
    }

    @Override
    public Fee getFeeById(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        Fee fee = (Fee) session.load(Fee.class, new Integer(id));

        return fee;
    }

    @Override
    public void removeFee(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        Fee fee = (Fee) session.load(Fee.class, new Integer(id));

        if(fee != null){
            session.delete(fee);
        }
    }
}
