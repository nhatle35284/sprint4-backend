package com.example.sprint4.service;

import com.example.sprint4.model.Bill;
import com.example.sprint4.model.Cart;

import java.util.List;

public interface CartService {
    List<Cart> findAll();

//    List<Cart> findAllByStatusTrue();

    Cart findById(Long id);

    void save(Cart cart);

    void deleteById(Long id);

    Cart findByGoods_IdGood(Long idGoods,Long idUser);

    List<Cart> findAllByUser_IdUser(Long idUser);

//    List<Cart> findAllByUser_IdUser(Long idUser);
}
