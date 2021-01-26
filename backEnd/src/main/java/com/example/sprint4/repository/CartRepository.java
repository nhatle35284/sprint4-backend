package com.example.sprint4.repository;

import com.example.sprint4.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartRepository extends JpaRepository< Cart,Long> {
    Cart findByGoods_IdGoodAndUser_IdUser(Long idGoods,Long idUser);
    Cart findByStatusFalseAndGoods_IdGoodAndUser_IdUser(Long idGoods,Long idUser);
    List<Cart> findAllByUser_IdUser(Long idUser);
    List<Cart> findAllByStatusFalseAndUser_IdUser(Long idUser);
}
