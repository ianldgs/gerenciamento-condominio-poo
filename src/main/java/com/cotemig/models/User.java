package com.cotemig.models;

import javax.persistence.*;

/**
 * Created by Matheus on 22/10/2016.
 */

@Entity
@Table(name = "users")
@SequenceGenerator(name="USER_SEQUENCE", sequenceName="USER_ID_SEQUENCE", allocationSize=1)
public class User {
    //region attributes

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="USER_SEQUENCE")
    private int id;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    //endregion

    //region getters/setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    //endregion
}
