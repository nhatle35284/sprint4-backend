package com.example.sprint4.controller;

import com.example.sprint4.dto.BillDTO;
import com.example.sprint4.dto.BillGoodsDTO;
import com.example.sprint4.dto.CartDTO;
import com.example.sprint4.model.Bill;
import com.example.sprint4.model.BillGoods;
import com.example.sprint4.model.Cart;
import com.example.sprint4.model.Goods;
import com.example.sprint4.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("bill")
public class BillController {
    @Autowired
    BillService billService;
    @Autowired
    BillGoodsService billGoodsService;
    @Autowired
    CartService cartService;
    @Autowired
    UserService userService;
    @Autowired
    GoodsService goodsService;
    @GetMapping("list")
    public ResponseEntity<List<BillGoodsDTO>> listBill() {
        List<BillGoods> billGoods = billGoodsService.findAll();
        List<BillGoodsDTO> billGoodsDTOS = new ArrayList<>();
        for (int i = 0;i<billGoods.size();i++){
            BillGoodsDTO billGoodsDTO = new BillGoodsDTO();
            billGoodsDTO.setIdBill(billGoods.get(i).getBill().getIdBill());

            billGoodsDTO.setCreatedDate(billGoods.get(i).getBill().getCreatedDate());
            billGoodsDTO.setQuantity(billGoods.get(i).getQuantity());
            billGoodsDTO.setIdUser(billGoods.get(i).getBill().getUser().getIdUser());
            billGoodsDTO.setFullName(billGoods.get(i).getBill().getUser().getFullName());
            billGoodsDTO.setAddress(billGoods.get(i).getBill().getUser().getAddress());
            billGoodsDTO.setNumberPhone(billGoods.get(i).getBill().getUser().getNumberPhone());
            billGoodsDTO.setEmail(billGoods.get(i).getBill().getUser().getEmail());
            billGoodsDTO.setGoodName(billGoods.get(i).getGood().getGoodName());
            billGoodsDTO.setStatus(false);
            billGoodsDTOS.add(billGoodsDTO);
        }
//        bill.set
//        cart1.setUser(userService.findById(cart.getIdUser()));
//        cart1.setGoods(goodsService.findById(cart.getIdGood()));
//        cart1.setQuantity("1");
//        cartService.save(cart1);
        return new ResponseEntity<>(billGoodsDTOS,HttpStatus.OK);
    }
@GetMapping("create/{idUser}")
    public ResponseEntity<Void> createBill(@PathVariable Long idUser) {
        List<Cart> carts = cartService.findAllByUser_IdUser(idUser);

        Bill bill = new Bill();
        bill.setCreatedDate(String.valueOf(LocalDateTime.now()));
        bill.setUser(userService.findById(idUser));
        billService.save(bill);


        BillGoods billGoods;
        for (int i = 0;i<carts.size();i++){
            billGoods = new BillGoods();
            billGoods.setBill(billService.findById(bill.getIdBill()));
            billGoods.setGood(goodsService.findById(carts.get(i).getGoods().getIdGood()));
            billGoods.setQuantity(carts.get(i).getQuantity());
            Goods goods = goodsService.findById(carts.get(i).getGoods().getIdGood());
            goods.setQuantity(String.valueOf(Integer.parseInt(goods.getQuantity())-Integer.parseInt(carts.get(i).getQuantity())));
            billGoodsService.save(billGoods);
        }
//        bill.set
//        cart1.setUser(userService.findById(cart.getIdUser()));
//        cart1.setGoods(goodsService.findById(cart.getIdGood()));
//        cart1.setQuantity("1");
//        cartService.save(cart1);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
