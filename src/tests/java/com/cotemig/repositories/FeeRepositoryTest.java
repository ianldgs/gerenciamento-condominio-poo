package com.cotemig.repositories;

import com.cotemig.ApplicationConfig;
import com.cotemig.models.Fee;
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
        repository.deleteAll();
    }

    @Test
    public void shitIsWorking() throws Exception {
        assertNotNull(repository);
        assertNotNull(repository.findAll());
        assertEquals(0, repository.findAll().size());
    }

    @Test
    public void save() throws Exception {
        Fee fee = new Fee();

        fee.setValue(500);

        repository.saveAndFlush(fee);

        assertEquals(1, fee.getId());
        assertEquals(1, repository.count());
    }

    @Test
    public void count() throws Exception {
        save();

        assertEquals(1, repository.count());
    }

    @Test
    public void find() throws Exception {
        save();

        Fee fee = repository.findOne(1);

        assertNotNull(fee);

        assertNotNull(fee.getDate());
        assertEquals(500, fee.getValue(), .1);
    }
}