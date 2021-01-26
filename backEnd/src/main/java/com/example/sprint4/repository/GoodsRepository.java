package com.example.sprint4.repository;

import com.example.sprint4.model.Goods;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GoodsRepository extends JpaRepository<Goods,Long> {
    List<Goods> findAllByGoodNameContaining(String key);
}
