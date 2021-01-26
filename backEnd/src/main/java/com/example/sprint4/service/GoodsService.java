package com.example.sprint4.service;

import com.example.sprint4.model.Category;
import com.example.sprint4.model.Goods;

import java.util.List;

public interface GoodsService {
    List<Goods> findAll();

//    List<Goods> findAllByStatusTrue();

    Goods findById(Long id);

    void save(Goods goods);

    void deleteById(Long id);

    List<Goods> findAllByGoodNameContaining(String key);
}
