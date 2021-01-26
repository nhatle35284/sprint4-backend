package com.example.sprint4.service;

import com.example.sprint4.model.Bill;

import java.util.List;

public interface BillService {
    List<Bill> findAll();

//    List<Bill> findAllByStatusTrue();

    Bill findById(Long id);

    void save(Bill bill);

    void deleteById(Long id);
}
