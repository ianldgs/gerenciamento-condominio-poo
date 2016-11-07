package com.cotemig.services;

import com.cotemig.models.Fee;
import org.junit.Before;
import org.junit.Test;

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
}