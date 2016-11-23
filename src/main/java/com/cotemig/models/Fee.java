package com.cotemig.models;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by matheus.elias on 10/23/16.
 */
@Entity
@Table(name = "fees")
@SequenceGenerator(name="FEE_SEQUENCE", sequenceName="FEE_ID_SEQUENCE", allocationSize=1)
public class Fee {
    //region attributes

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="FEE_SEQUENCE")
    private int id;

    @Column(nullable = false)
    private double value;

    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dueDate = new Date();

    @Column(nullable = false)
    private double paid = 0;

    @ManyToOne(targetEntity = Resident.class, optional = false)
    private Resident resident;

    @ManyToOne(targetEntity = Condo.class, optional = false)
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

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public double getPaid() {
        return paid;
    }

    public void setPaid(double paid) {
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
