package com.example.sprint4.service.impl;

import com.example.sprint4.model.Goods;
import com.example.sprint4.repository.GoodsRepository;
import com.example.sprint4.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GoodsServiceImpl implements GoodsService {
    @Autowired
    GoodsRepository goodsRepository;
    @Override
    public List<Goods> findAll() {
        return goodsRepository.findAll();
    }

    @Override
    public Goods findById(Long id) {
        return goodsRepository.findById(id).orElse(null);
    }

    @Override
    public void save(Goods goods) {
        goodsRepository.save(goods);
    }

    @Override
    public void deleteById(Long id) {
        goodsRepository.deleteById(id);
    }

    @Override
    public List<Goods> findAllByGoodNameContaining(String key) {
        return goodsRepository.findAllByGoodNameContaining(key);
    }
}
