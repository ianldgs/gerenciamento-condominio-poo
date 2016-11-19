package com.cotemig.jpa;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Created by Ian Luca on 19/11/2016.
 */
public class GenerateTables {
    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("Condominio");

        factory.close();
    }
}
