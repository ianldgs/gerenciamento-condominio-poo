package com.cotemig.repositories;

import com.cotemig.models.Fee;

import java.util.List;

/**
 * Created by matheus.elias on 11/2/16.
 */
public interface FeeRepository {
    public void addFee(String code);
    public void updateFee(Fee fee);
    public List<Fee> listFee();
    public Fee getFeeById(int id);
    public void removeFee(int id);
}
