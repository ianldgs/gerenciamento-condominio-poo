package com.cotemig.repositories;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

/**
 * Created by Ian Luca on 19/11/2016.
 */
public class FeeRepositoryTest {
    @Autowired
    FeeRepository repository;

    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void shitIsWorking() throws Exception {
        assertNotNull(repository);
        assertNotNull(repository.findAll());
        assertEquals(0, repository.findAll().size());
    }
}