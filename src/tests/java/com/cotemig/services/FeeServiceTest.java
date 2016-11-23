package com.cotemig.services;

import com.cotemig.ApplicationConfig;
import com.cotemig.models.Condo;
import com.cotemig.models.Fee;
import com.cotemig.repositories.CondoRepository;
import com.cotemig.repositories.FeeRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.File;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Ian Luca on 06/11/2016.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ApplicationConfig.class)
public class FeeServiceTest {
    @Autowired
    FeeService feeService;

    @Autowired
    FeeRepository feeRepository;

    @Autowired
    CondoRepository condoRepository;

    @Before
    public void setUp() throws Exception {
        feeService = new FeeService();

        //TODO: criar aqui, o mock do banco referente ao arquivo mocks/fees.txt, para os finds funcionarem corretamente
    }

    @Test
    public void a() throws Exception {
        assertEquals("2016/01", String.format("%1d/%02d", 2016, 1));
        assertEquals("2016/10", String.format("%1d/%02d", 2016, 10));
        assertEquals("0216/03", String.format("%04d/%02d", 216, 3));
        assertEquals("0016/03", String.format("%04d/%02d", 16, 3));
        assertEquals("0006/03", String.format("%04d/%02d", 6, 3));
    }

    @Test
    public void parseLine() throws Exception {
        String cnpj = "74713443000166";
        String cpf = "88360130663";
        String month = "11";
        String year = "2014";
        String paid = "10030059";

        Fee fee = feeService.parseLine(cnpj + cpf + month + year + paid);

        assertNotNull(fee);
        assertEquals(Double.parseDouble(paid), fee.getPaid(), .01);

        assertNotNull(fee.getCondo());
        assertEquals(cnpj, fee.getCondo().getCnpj());

        assertNotNull(fee.getResident());
        assertEquals(cpf, fee.getResident().getCpf());
    }

    @Test
    public void parseFile() throws Exception {
        File file = new File(System.getProperty("user.dir") + "/src/tests/mocks/fees.txt");
        List<Fee> fees = feeService.parseFile(file);

        Fee fee;

        fee = fees.get(0);

        assertNotNull(fee);
        assertEquals(500.0, fee.getPaid(), .01);

        assertNotNull(fee.getCondo());
        assertEquals("08322257000171", fee.getCondo().getCnpj());

        assertNotNull(fee.getResident());
        assertEquals("88360130663", fee.getResident().getCpf());

        fee = fees.get(1);

        assertNotNull(fee);
        assertEquals(500.0, fee.getPaid(), .01);

        assertNotNull(fee.getCondo());
        assertEquals("08322257000171", fee.getCondo().getCnpj());

        assertNotNull(fee.getResident());
        assertEquals("57842567859", fee.getResident().getCpf());

        fee = fees.get(2);

        //////

        assertNotNull(fee);
        assertEquals(1000.0, fee.getPaid(), .01);

        assertNotNull(fee.getCondo());
        assertEquals("54887826000143", fee.getCondo().getCnpj());

        assertNotNull(fee.getResident());
        assertEquals("41496532406", fee.getResident().getCpf());

        fee = fees.get(3);

        assertNotNull(fee);
        assertEquals(1000.0, fee.getPaid(), .01);

        assertNotNull(fee.getCondo());
        assertEquals("54887826000143", fee.getCondo().getCnpj());

        assertNotNull(fee.getResident());
        assertEquals("03397790719", fee.getResident().getCpf());
    }

    @Test
    public void testDivideTotalFeeByResident() throws Exception {
        String cnpj = "24985875000158";
        float totalAmount = 1000;

        Condo condo = condoRepository.findByCnpj(cnpj);

        assertNotNull(condo);

        int previousTotalFeeRegistered = feeRepository.findAll().size();

        feeService.divideTotalByResident(condo, totalAmount, 1, 2015);

        int nowTotalFeeRegistered = feeRepository.findAll().size();

        assertNotEquals(previousTotalFeeRegistered, nowTotalFeeRegistered);
    }
}