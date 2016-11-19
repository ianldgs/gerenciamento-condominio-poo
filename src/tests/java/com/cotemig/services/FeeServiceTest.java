package com.cotemig.services;

import com.cotemig.models.Fee;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Ian Luca on 06/11/2016.
 */
public class FeeServiceTest {
    FeeService feeService;

    @Before
    public void setUp() throws Exception {
        feeService = new FeeService();
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
}