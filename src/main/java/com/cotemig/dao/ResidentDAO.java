package com.cotemig.dao;

import com.cotemig.models.Resident;

import java.util.List;

/**
 * Created by matheus.elias on 11/2/16.
 */
public interface ResidentDAO {
    public void addResident(Resident resident);
    public void updateResident(Resident resident);
    public List<Resident> listResident();
    public Resident getResidentByCpf(String cpf);
    public void removeResident(String cpf);
}
