package com.cotemig.repositories;

import com.cotemig.ApplicationConfig;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

/**
 * Created by Ian Luca on 19/11/2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ApplicationConfig.class)
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