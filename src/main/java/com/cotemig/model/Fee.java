package com.cotemig.model;

import java.util.Date;

/**
 * Created by matheus.elias on 10/23/16.
 */
public class Fee {
    private double value;
    private Date date;
    private String code;
    private boolean paid;
    private String cpfDweller;
    private String idCondo;

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public boolean isPaid() {
        return paid;
    }

    public void setPaid(boolean open) {
        this.paid = open;
    }

    public String getCpfDweller() {
        return cpfDweller;
    }

    public void setCpfDweller(String cpfDweller) {
        this.cpfDweller = cpfDweller;
    }

    public String getIdCondo() {
        return idCondo;
    }

    public void setIdCondo(String idCondo) {
        this.idCondo = idCondo;
    }
}
