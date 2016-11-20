package com.cotemig.models;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Created by matheus.elias on 10/23/16.
 */
@Entity
@Table(name = "residents")
@SequenceGenerator(name="RESIDENT_SEQUENCE", sequenceName="RESIDENT_SEQUENCE", allocationSize=1, initialValue=0)
public class Resident {
    //region attributes

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="RESIDENT_SEQUENCE")
    private int id;

    @Column(nullable = false, unique = true)
    @NotNull(message = "CPF Inválido")
    @Length(min = 11, max = 11, message = "CPF Inválido")
    private String cpf;

    @Column(nullable = false)
    @NotNull(message = "O nome é obrigatório")
    @Length(min = 3, max = 255)
    private String name;

    @Column(nullable = false)
    private boolean syndic = false;

    @Column
    @NotNull(message = "O número do apartamento é obrigatório")
    @Min(1)
    private int apartmentNumber;

    @ManyToOne(targetEntity = Condo.class, optional = true)
    private Condo condo;

    @OneToMany(targetEntity = Fee.class)
    private List<Fee> fees;

    //endregion

    //region getters/setters

    public int getId() {
        return id;
    }

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

    public boolean isSyndic() {
        return syndic;
    }

    public void setSyndic(boolean syndic) {
        this.syndic = syndic;
    }

    public int getApartmentNumber() {
        return apartmentNumber;
    }

    public void setApartmentNumber(int apartmentNumber) {
        this.apartmentNumber = apartmentNumber;
    }

    public List<Fee> getFees() {
        return fees;
    }

    public void setFees(List<Fee> fees) {
        this.fees = fees;
    }

    public Condo getCondo() {
        return condo;
    }

    public void setCondo(Condo condo) {
        this.condo = condo;
    }

    //endregion
}