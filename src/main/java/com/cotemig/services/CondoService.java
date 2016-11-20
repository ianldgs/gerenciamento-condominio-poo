package com.cotemig.services;

import com.cotemig.models.Condo;
import com.cotemig.repositories.CondoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by matheus.elias on 11/19/16.
 */

@Service
public class CondoService {
    @Autowired(required = true)
    private CondoRepository condoRepository;

    @Transactional
    public void insert(Condo condo) {
        try{
            condoRepository.saveAndFlush(condo);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
