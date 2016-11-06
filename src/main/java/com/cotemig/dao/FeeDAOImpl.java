package com.cotemig.dao;

import com.cotemig.model.Fee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.Calendar;
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
    public void addFee(String code) {
        String cnpjCondo = code.substring(0, 13);
        String cpfResident = code.substring(14, 25);
        String monthYear = code.substring(26, 32);

        double value = Double.parseDouble(code.substring(33, 41));

        CondoDAO condoDAO = new CondoDAOImpl();
        ResidentDAO residentDAO = new ResidentDAOImpl();

        Fee fee = new Fee();

        fee.setValue(value);
        fee.setCode(code);
        fee.setPaid(true);
        fee.setCondo(condoDAO.getCondoByCnpj(cnpjCondo));
        fee.setResident(residentDAO.getResidentByCpf(cpfResident));

        int month = Integer.parseInt(monthYear.substring(0, 1));
        int year = Integer.parseInt(monthYear.substring(2, 5));

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.YEAR, year);

        fee.setDate(calendar.getTime());

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
