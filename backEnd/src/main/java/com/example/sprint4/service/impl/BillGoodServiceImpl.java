package com.example.sprint4.service.impl;

import com.example.sprint4.model.BillGoods;
import com.example.sprint4.repository.BillGoodsRepository;
import com.example.sprint4.service.BillGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BillGoodServiceImpl implements BillGoodsService {
    @Autowired
    BillGoodsRepository billGoodsRepository;
    @Override
    public List<BillGoods> findAll() {
        return billGoodsRepository.findAll();
    }

    @Override
    public BillGoods findById(Long id) {
        return billGoodsRepository.findById(id).orElse(null);
    }

    @Override
    public void save(BillGoods billGoods) {
        billGoodsRepository.save(billGoods);
    }

    @Override
    public void deleteById(Long id) {
        billGoodsRepository.deleteById(id);
    }
}
