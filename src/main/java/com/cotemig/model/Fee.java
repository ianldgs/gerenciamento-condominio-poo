package com.cotemig.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by matheus.elias on 10/23/16.
 */
@Entity
@Table(name = "fees")
@SequenceGenerator(name="FEE_SEQUENCE", sequenceName="FEE_SEQUENCE", allocationSize=1, initialValue=0)
public class Fee {
    //region attributes

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="FEE_SEQUENCE")
    private int id;

    @Column(nullable = false)
    private double value;

    @Temporal(TemporalType.DATE)
    private Date date;

    @Column(nullable = false)
    private String code; //TODO: que isso?

    @Column(nullable = false)
    private boolean paid = false;

    @ManyToOne(targetEntity = Resident.class)
    private Resident resident;

    @ManyToOne(targetEntity = Condo.class)
    private Condo condo;

    //endregion

    //region getters/setters

    public int getId() {
        return id;
    }

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

    public void setPaid(boolean paid) {
        this.paid = paid;
    }

    public Resident getResident() {
        return resident;
    }

    public void setResident(Resident resident) {
        this.resident = resident;
    }

    public Condo getCondo() {
        return condo;
    }

    public void setCondo(Condo condo) {
        this.condo = condo;
    }

    //endregion
}
