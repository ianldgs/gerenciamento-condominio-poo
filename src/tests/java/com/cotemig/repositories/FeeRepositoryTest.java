package com.cotemig.repositories;

import com.cotemig.ApplicationConfig;
import com.cotemig.models.Condo;
import com.cotemig.models.Fee;
import com.cotemig.models.Resident;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;

import static org.junit.Assert.*;

/**
 * Created by Ian Luca on 19/11/2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ApplicationConfig.class)
public class FeeRepositoryTest {
    @Autowired
    FeeRepository feeRepository;

    @Autowired
    CondoRepository condoRepository;

    @Autowired
    ResidentRepository residentRepository;

    @Before
    public void setUp() throws Exception {
//        feeRepository.deleteAll();
    }

    @Test
    public void shitIsWorking() throws Exception {
        assertNotNull(feeRepository);
        assertNotNull(feeRepository.findAll());
        assertEquals(0, feeRepository.findAll().size());
    }

    @Test
    public void save() throws Exception {
        Fee fee = new Fee();

        fee.setValue(500);

        assertEquals(0, fee.getId());

        feeRepository.saveAndFlush(fee);

        assertNotEquals(0, fee.getId());
        assertEquals(1, feeRepository.count());
    }

    @Test
    public void count() throws Exception {
        save();

        assertEquals(1, feeRepository.count());
    }

    @Test
    public void findOne() throws Exception {
        save();

        Fee fee = feeRepository.findOne(1);

        assertNotNull(fee);

        assertNotNull(fee.getDueDate());
        assertEquals(500, fee.getValue(), .1);
    }

    @Test
    public void saveWithRelations() throws Exception {
        Condo condo = new Condo();
        condo.setName("Residencial Canadá");
        condo.setCnpj("123");

        assertEquals(0, condo.getId());

        condoRepository.saveAndFlush(condo);

        assertNotEquals(0, condo.getId());

        Resident resident = new Resident();
        resident.setCondo(condo);
        resident.setCpf("321");
        resident.setApartmentNumber(205);
        resident.setName("Ian");

        assertEquals(0, resident.getId());

        residentRepository.saveAndFlush(resident);

        assertNotEquals(0, resident.getId());

        Fee fee = new Fee();
        fee.setCondo(condo);
        fee.setResident(resident);
        fee.setValue(500);
        fee.setDueDate(new Date(116, 8, 19));

        assertEquals(0, fee.getId());

        feeRepository.saveAndFlush(fee);

        assertNotEquals(0, fee.getId());

        assertEquals(1, feeRepository.count());
    }

    @Test
    public void findByCnpjAndCpfAndDateWithFormat() throws Exception {
        saveWithRelations();

        Fee fee = feeRepository.findByCnpjAndCpfAndDateWithFormat("123", "321", "09/2016", "MM/YYYY");

        assertNotNull(fee);
    }
}