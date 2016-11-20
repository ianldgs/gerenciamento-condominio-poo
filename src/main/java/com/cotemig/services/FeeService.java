package com.cotemig.services;

import com.cotemig.models.Condo;
import com.cotemig.models.Fee;
import com.cotemig.models.Resident;
import com.cotemig.repositories.FeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ian Luca on 06/11/2016.
 */
@Service
public class FeeService {

    @Autowired
    private FeeRepository repository;

    public void create(Condo condo, double totalAmount) {
        List<Resident> residentsList = condo.getResidents();

        double amountPerResident = totalAmount / residentsList.size();
        DecimalFormat formatAmountPerResident = new DecimalFormat("#.00");
        amountPerResident = Double.valueOf(formatAmountPerResident.format(amountPerResident));

        for (Resident resident : residentsList) {
            Fee fee = new Fee();
            fee.setCondo(condo);
            fee.setResident(resident);
            fee.setValue(amountPerResident);
        }

//        FeeRepository repository
    }

    public Fee parseLine(String line) {
        String cnpj = line.substring(0, 14);
        String cpf = line.substring(14, 25);
        String month = line.substring(25, 27);
        String year = line.substring(27, 31);
        double value = Double.parseDouble(line.substring(31, 39));

        Fee fee = repository.findByCnpjAndCpfAndDateWithFormat(cnpj, cpf, month + "/" + year, "MM/YYYY");

        fee.setPaid(value);

        repository.saveAndFlush(fee);

        return fee;
    }

    public List<Fee> parseFile(File file) throws IOException {
        List<Fee> fees = new ArrayList<>();

        BufferedReader bufferedReader = new BufferedReader(
            new InputStreamReader(
                new FileInputStream(file)
            )
        );

        String line;
        while ((line = bufferedReader.readLine()) != null) {
            fees.add(parseLine(line));
        }

        return fees;
    }
}
