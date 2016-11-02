package com.cotemig.dao;

import com.cotemig.model.Dweller;

import java.util.List;

/**
 * Created by matheus.elias on 11/2/16.
 */
public interface DwellerDAO {
    public void addDweller(Dweller dweller);
    public void updateDweller(Dweller dweller);
    public List<Dweller> listDweller();
    public Dweller getDwellerById(int id);
    public void removeDweller(int id);
}
