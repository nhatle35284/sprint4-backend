package com.example.sprint4.service.impl;

import com.example.sprint4.model.Bill;
import com.example.sprint4.repository.BillRepository;
import com.example.sprint4.service.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BillServiceImpl implements BillService {
    @Autowired
    BillRepository billRepository;
    @Override
    public List<Bill> findAll() {
        return billRepository.findAll();
    }

    @Override
    public Bill findById(Long id) {
        return billRepository.findById(id).orElse(null);
    }

    @Override
    public void save(Bill bill) {
        billRepository.save(bill);
    }

    @Override
    public void deleteById(Long id) {
        billRepository.deleteById(id);
    }
}
