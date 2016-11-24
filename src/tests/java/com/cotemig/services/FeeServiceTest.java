package com.cotemig.services;

import com.cotemig.TestsConfig;
import com.cotemig.models.Condo;
import com.cotemig.models.Fee;
import com.cotemig.models.Resident;
import com.cotemig.repositories.CondoRepository;
import com.cotemig.repositories.FeeRepository;
import com.cotemig.repositories.ResidentRepository;
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
@ContextConfiguration(classes = TestsConfig.class)
public class FeeServiceTest {
    @Autowired
    FeeService feeService;

    @Autowired
    FeeRepository feeRepository;

    @Autowired
    CondoRepository condoRepository;

    @Autowired
    ResidentRepository residentRepository;

    @Before
    public void setUp() throws Exception {
        //mocks
        registerFees();
        divideTotalFeeByResident();
    }

    public void registerFees() throws Exception {
        Condo condo1 = new Condo();
        condo1.setCnpj("08322257000171");
        condo1.setName("teste1");

        condoRepository.saveAndFlush(condo1);

        Resident resident1 = new Resident();
        resident1.setCondo(condo1);
        resident1.setCpf("88360130663");
        resident1.setName("teste1");
        resident1.setSyndic(true);
        resident1.setApartmentNumber(1);

        residentRepository.saveAndFlush(resident1);

        Resident resident2 = new Resident();
        resident2.setCondo(condo1);
        resident2.setCpf("57842567859");
        resident2.setName("teste2");
        resident2.setSyndic(false);
        resident2.setApartmentNumber(2);

        residentRepository.saveAndFlush(resident2);

        Condo condo2 = new Condo();
        condo2.setCnpj("54887826000143");
        condo2.setName("teste2");

        condoRepository.saveAndFlush(condo2);

        Resident resident3 = new Resident();
        resident3.setCondo(condo2);
        resident3.setCpf("41496532406");
        resident3.setName("teste3");
        resident3.setSyndic(true);
        resident3.setApartmentNumber(1);

        residentRepository.saveAndFlush(resident3);

        Resident resident4 = new Resident();
        resident4.setCondo(condo2);
        resident4.setCpf("03397790719");
        resident4.setName("teste4");
        resident4.setSyndic(true);
        resident4.setApartmentNumber(1);

        residentRepository.saveAndFlush(resident4);
    }

    public void divideTotalFeeByResident() throws Exception {
        Condo condo1 = condoRepository.findByCnpj("08322257000171");
        assertNotNull(condo1);

        assertNull(feeRepository.findByCnpjAndCpfAndDateWithFormat("08322257000171", "88360130663", "2017/01", "YYYY/MM"));
        assertNull(feeRepository.findByCnpjAndCpfAndDateWithFormat("08322257000171", "57842567859", "2017/01", "YYYY/MM"));

        feeService.divideTotalByResident(condo1, 1000, 2017, 1);
        assertNotNull(feeRepository.findByCnpjAndCpfAndDateWithFormat("08322257000171", "88360130663", "2017/01", "YYYY/MM"));
        assertNotNull(feeRepository.findByCnpjAndCpfAndDateWithFormat("08322257000171", "57842567859", "2017/01", "YYYY/MM"));

        Condo condo2 = condoRepository.findByCnpj("54887826000143");
        assertNotNull(condo2);

        assertNull(feeRepository.findByCnpjAndCpfAndDateWithFormat("08322257000171", "41496532406", "2016/12", "YYYY/MM"));
        assertNull(feeRepository.findByCnpjAndCpfAndDateWithFormat("08322257000171", "03397790719", "2016/12", "YYYY/MM"));

        feeService.divideTotalByResident(condo2, 1000, 2016, 12);
        assertNotNull(feeRepository.findByCnpjAndCpfAndDateWithFormat("08322257000171", "41496532406", "2016/12", "YYYY/MM"));
        assertNotNull(feeRepository.findByCnpjAndCpfAndDateWithFormat("08322257000171", "03397790719", "2016/12", "YYYY/MM"));
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
        String cnpj = "08322257000171";
        String cpf = "88360130663";
        String month = "01";
        String year = "2017";
        String paid = "0001000";

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