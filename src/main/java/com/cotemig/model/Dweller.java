package com.cotemig.model;

import java.util.Map;

/**
 * Created by matheus.elias on 10/23/16.
 */
public class Dweller {
    private String cpf;
    private String name;
    private int numberApartment;

    private Map<String, Double> fees;

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumberApartment() {
        return numberApartment;
    }

    public void setNumberApartment(int numberApartment) {
        this.numberApartment = numberApartment;
    }

    public Map<String, Double> getFees() {
        return fees;
    }

    public void setFees(Map<String, Double> fees) {
        this.fees = fees;
    }
}
