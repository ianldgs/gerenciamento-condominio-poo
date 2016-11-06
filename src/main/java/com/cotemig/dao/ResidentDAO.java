package com.cotemig.dao;

import com.cotemig.model.Resident;

import java.util.List;

/**
 * Created by matheus.elias on 11/2/16.
 */
public interface ResidentDAO {
    public void addResident(Resident resident);
    public void updateResident(Resident resident);
    public List<Resident> listResident();
    public Resident getResidentById(int id);
    public void removeResident(int id);
}
