package com.cotemig.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by Matheus on 22/10/2016.
 */

@Entity
@Table(name="user")
public class User {
    private String cpf;
    private String name;
    private String password;
    private String idCondo;

    @Id
    @Column(name="id")
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
