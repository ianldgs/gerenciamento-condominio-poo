package com.cotemig.repositories;

import com.cotemig.models.Condo;
import org.springframework.data.repository.Repository;

import java.util.List;

/**
 * Created by matheus.elias on 11/2/16.
 */
public interface CondoRepository extends Repository<Condo, Long> {
    public void addCondo(Condo condo);
    public void updateCondo(Condo condo);
    public List<Condo> listCondos();
    public Condo getCondoByCnpj(String Cnpj);
    public void removeCondo(String Cnpj);
}
