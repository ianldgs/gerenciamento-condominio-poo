package com.cotemig.services;

import com.cotemig.models.Condo;
import com.cotemig.repositories.CondoRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by matheus.elias on 11/19/16.
 */

@Service
public class CondoService {
    @Autowired(required = true)
    private CondoRepository condoRepository;

    @Transactional
    public void save(Condo condo) {
        try{
            Condo existentCondo = condoRepository.findByCnpj(condo.getCnpj());

            BeanUtils.copyProperties(condo, existentCondo);

            condoRepository.saveAndFlush(existentCondo);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    @Transactional
    public void remove(int id) {
        try{
            condoRepository.delete(id);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    @Transactional
    public List<Condo> find(){
        List<Condo> condoList = null;

        try {
            condoList = condoRepository.findAll();
        }
        catch (Exception e){
            e.printStackTrace();
        }

        return condoList;
    }
}
