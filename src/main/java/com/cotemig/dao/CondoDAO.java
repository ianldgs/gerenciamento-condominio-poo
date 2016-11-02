package com.cotemig.dao;

import com.cotemig.model.Condo;

import java.util.List;

/**
 * Created by matheus.elias on 11/2/16.
 */
public interface CondoDAO {
    public void addCondo(Condo condo);
    public void updateCondo(Condo condo);
    public List<Condo> listCondos();
    public Condo getCondoById(int id);
    public void removeCondo(int id);
}
