package com.example.sprint4.service.impl;

import com.example.sprint4.model.Bill;
import com.example.sprint4.model.Cart;
import com.example.sprint4.repository.CartRepository;
import com.example.sprint4.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartServiceImpl implements CartService {
    @Autowired
    CartRepository cartRepository;
    @Override
    public List<Cart> findAll() {
        return cartRepository.findAll();
    }

    @Override
    public Cart findById(Long id) {
        return cartRepository.findById(id).orElse(null);
    }

    @Override
    public void save(Cart cart) {
        cartRepository.save(cart);
    }

    @Override
    public void deleteById(Long id) {
        cartRepository.deleteById(id);
    }

    @Override
    public Cart findByGoods_IdGood(Long idGoods, Long idUser) {
        return cartRepository.findByStatusFalseAndGoods_IdGoodAndUser_IdUser(idGoods, idUser);
    }

    @Override
    public List<Cart> findAllByUser_IdUser(Long idUser) {
        return cartRepository.findAllByStatusFalseAndUser_IdUser(idUser);
    }
}
