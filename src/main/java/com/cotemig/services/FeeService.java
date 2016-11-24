package com.cotemig.services;

import com.cotemig.models.Condo;
import com.cotemig.models.Fee;
import com.cotemig.models.Resident;
import com.cotemig.repositories.FeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Ian Luca on 06/11/2016.
 */
@Service
public class FeeService {

    @Autowired
    private FeeRepository repository;

    @Transactional
    public void divideTotalByResident(Condo condo, double totalAmount, int year, int month) {
        List<Resident> residentsList = condo.getResidents();

        double amountPerResident = totalAmount / residentsList.size();
        DecimalFormat formatAmountPerResident = new DecimalFormat("#.00");
        amountPerResident = Double.parseDouble(formatAmountPerResident.format(amountPerResident));

        SimpleDateFormat sdf = new SimpleDateFormat("MM/YYYY");
        Date monthAndYearNow = new Date();

        if (month <= 12 && month > 0 && year > 1900) {
            try {
                monthAndYearNow = sdf.parse(month + "/" + year);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        for (Resident resident : residentsList) {
            Fee fee = new Fee();
            fee.setCondo(condo);
            fee.setResident(resident);
            fee.setValue(amountPerResident);
            fee.setDueDate(monthAndYearNow);

            boolean feeExists = repository.findByCnpjAndCpfAndDateWithFormat(condo.getCnpj(), resident.getCpf(), sdf.format(fee.getDueDate()), "MM/YYYY") != null;

            if (!feeExists) {
                repository.save(fee);
            }
        }
    }

    public Fee parseLine(String line) {
        String cnpj = line.substring(0, 14);
        String cpf = line.substring(14, 25);
        String month = line.substring(25, 27);
        String year = line.substring(27, 31);
        double value = Double.parseDouble(line.substring(31, 39));

        Fee fee = repository.findByCnpjAndCpfAndDateWithFormat(cnpj, cpf, month + "/" + year, "MM/YYYY");

        fee.setPaid(value);

        repository.save(fee);

        return fee;
    }

    @Transactional
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

    public List<Fee> parseFile(MultipartFile mFile) throws IOException {
        File file = new File(mFile.getOriginalFilename());
        mFile.transferTo(file);

        return parseFile(file);
    }
}
