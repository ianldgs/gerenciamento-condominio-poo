package com.cotemig.models;

import javax.persistence.*;
import java.util.List;

/**
 * Created by matheus.elias on 10/23/16.
 */
@Entity
@Table(name = "condos")
@SequenceGenerator(name="CONDO_SEQUENCE", sequenceName="CONDO_SEQUENCE", allocationSize=1, initialValue=0)
public class Condo {
    //region attributes

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="CONDO_SEQUENCE")
    private int id;

    @Column(nullable = false)
    private String cnpj;

    @Column(nullable = false)
    private String name;

    @OneToMany(targetEntity = Resident.class)
    private List<Resident> residents;

    @OneToMany(targetEntity = Fee.class)
    @JoinColumn
    private List<Fee> fees;

    //endregion

    //region getters/setters

    public int getId() {
        return id;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Resident> getResidents() {
        return residents;
    }

    public void setResidents(List<Resident> residents) {
        this.residents = residents;
    }

    public List<Fee> getFees() {
        return fees;
    }

    public void setFees(List<Fee> fees) {
        this.fees = fees;
    }

    //endregion
}
