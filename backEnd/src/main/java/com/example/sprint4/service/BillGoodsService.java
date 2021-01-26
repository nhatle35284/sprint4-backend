package com.example.sprint4.service;

import com.example.sprint4.model.BillGoods;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BillGoodsService {
    List<BillGoods> findAll();

//    List<BillGoods> findAllByStatusTrue();

    BillGoods findById(Long id);

    void save(BillGoods billGoods);

    void deleteById(Long id);
}
