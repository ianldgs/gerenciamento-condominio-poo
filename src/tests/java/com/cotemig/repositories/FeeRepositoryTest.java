package com.cotemig.repositories;

import com.cotemig.TestsConfig;
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
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Ian Luca on 19/11/2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestsConfig.class)
public class FeeRepositoryTest {
    @Autowired
    FeeRepository feeRepository;

    @Autowired
    CondoRepository condoRepository;

    @Autowired
    ResidentRepository residentRepository;

    @Before
    public void setUp() throws Exception {
        feeRepository.deleteAll();
        residentRepository.deleteAll();
        condoRepository.deleteAll();
    }

    @Test
    public void shitIsWorking() throws Exception {
        assertNotNull(feeRepository);
        assertNotNull(feeRepository.findAll());
        assertEquals(0, feeRepository.findAll().size());
    }

    @Test
    public void count() throws Exception {
        saveWithRelations();

        assertEquals(1, feeRepository.count());
    }

    @Test
    public void findOne() throws Exception {
        Fee fee = feeRepository.findOne(saveWithRelations());

        assertNotNull(fee);

        assertNotNull(fee.getDueDate());
        assertEquals(500, fee.getValue(), .1);
    }

    public int saveWithRelations() throws Exception {
        Condo condo = new Condo();
        condo.setName("Residencial Canadá");
        condo.setCnpj("123");

        assertEquals(0, condo.getId());

        condoRepository.saveAndFlush(condo);

        assertNotEquals(0, condo.getId());

        Resident resident = new Resident();
        resident.setCondo(condo);
        resident.setCpf("12312312387");
        resident.setApartmentNumber(205);
        resident.setName("Ian");

        assertEquals(0, resident.getId());

        residentRepository.saveAndFlush(resident);

        assertNotEquals(0, resident.getId());

        Fee fee = new Fee();
        fee.setCondo(condo);
        fee.setResident(resident);
        fee.setValue(500);
        fee.setDueDate(new Date());

        assertEquals(0, fee.getId());

        feeRepository.saveAndFlush(fee);

        assertNotEquals(0, fee.getId());

        assertEquals(1, feeRepository.count());

        return fee.getId();
    }

    @Test
    public void findByCnpjAndCpfAndDateWithFormat() throws Exception {
        saveWithRelations();

        Fee fee = feeRepository.findByCnpjAndCpfAndDateWithFormat("123", "12312312387", "11/2016", "MM/YYYY");

        assertNotNull(fee);
    }

    @Test
    public void findByDateWithFormat() throws Exception {
        saveWithRelations();

        List<Fee> fees = feeRepository.findByDateWithFormat("11/2016", "MM/YYYY");

        assertNotNull(fees);
        assertEquals(1, fees.size());
    }
}