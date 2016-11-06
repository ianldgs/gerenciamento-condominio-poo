package com.cotemig.dao;

import com.cotemig.model.Fee;

import java.util.List;

/**
 * Created by matheus.elias on 11/2/16.
 */
public interface FeeDAO {
    public void addFee(String code);
    public void updateFee(Fee fee);
    public List<Fee> listFee();
    public Fee getFeeById(int id);
    public void removeFee(int id);
}
