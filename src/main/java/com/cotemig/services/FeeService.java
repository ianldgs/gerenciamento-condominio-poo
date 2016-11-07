package com.cotemig.services;

import com.cotemig.models.Condo;
import com.cotemig.models.Fee;
import com.cotemig.models.Resident;

/**
 * Created by Ian Luca on 06/11/2016.
 */
public class FeeService { //TODO: tornar essa classe injetável, pra ser usada nos controllers
    public Fee find(String cnpj, String cpf, int year, int month) {
        //TODO: DAO/Repository? Procurar uma Fee no banco (onde cpf = cpf, cnpj = cnpj, year = year e month = month)

        Condo condo = new Condo();
        condo.setCnpj(cnpj);

        Resident resident = new Resident();
        resident.setCondo(condo);
        resident.setCpf(cpf);

        Fee fee = new Fee();
        fee.setValue(99.99);
        fee.setCondo(condo);
        fee.setResident(resident);

        return fee;
    }

    public Fee parseLine(String line) {
        String cnpj = line.substring(0, 14);
        String cpf = line.substring(14, 25);
        int month = Integer.parseInt(line.substring(25, 27));
        int year = Integer.parseInt(line.substring(27, 31));
        double value = Double.parseDouble(line.substring(31, 39));

        Fee fee = find(cnpj, cpf, year, month);

        fee.setPaid(value);

        return fee;
    }
}
