package com.example.sprint4.controller;

import com.example.sprint4.dto.CartDTO;
import com.example.sprint4.model.Cart;
import com.example.sprint4.model.User;
import com.example.sprint4.service.CartService;
import com.example.sprint4.service.CategoryService;
import com.example.sprint4.service.GoodsService;
import com.example.sprint4.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.*;

@RestController
@CrossOrigin
@RequestMapping("session")
public class SessionController {
    @Autowired
    JavaMailSender javaMailSender;
    @Autowired
    CartService cartService;
    @Autowired
    CategoryService categoryService;
    @Autowired
    UserService userService;
    @Autowired
    GoodsService goodsService;
    public static Map<Long, CartDTO> cartItems = new HashMap<>();
    @PostMapping("add")
    public ResponseEntity<Void> addCart(@RequestBody CartDTO cartDTO) {
        if (cartDTO != null) {
            if (cartItems.containsKey(cartDTO.getIdGood())) {
                CartDTO item = cartItems.get(cartDTO.getIdGood());
//                System.err.println(cartItems.get(cartDTO.getIdGood()).getQuantity());
                item.setQuantity(String.valueOf(Integer.parseInt(cartItems.get(cartDTO.getIdGood()).getQuantity())+1));
                cartItems.put(cartDTO.getIdGood(), item);
            } else {
                cartItems.put(cartDTO.getIdGood(), cartDTO);
            }
//            System.err.println(String.valueOf(Integer.parseInt(cartItems.get(cartDTO.getIdGood()).getQuantity())+1));
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PostMapping("update")
    public ResponseEntity<Void> updateCart(@RequestBody CartDTO cartDTO) {
        if (cartDTO != null) {
            if (cartItems.containsKey(cartDTO.getIdGood())) {
                CartDTO item = cartItems.get(cartDTO.getIdGood());
//                System.err.println(cartItems.get(cartDTO.getIdGood()).getQuantity());
                item.setQuantity(cartDTO.getQuantity());
                cartItems.put(cartDTO.getIdGood(), item);
            } else {
                cartItems.put(cartDTO.getIdGood(), cartDTO);
            }
//            System.err.println(String.valueOf(Integer.parseInt(cartItems.get(cartDTO.getIdGood()).getQuantity())+1));
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @GetMapping("delete/{idGood}")
    public ResponseEntity<Void> deleteCart(@PathVariable Long idGood) {
//        if (cartDTO != null) {
//            if (cartItems.containsKey(cartDTO.getIdGood())) {
//                CartDTO item = cartItems.get(cartDTO.getIdGood());
////                System.err.println(cartItems.get(cartDTO.getIdGood()).getQuantity());
//                item.setQuantity(cartDTO.getQuantity());
//                cartItems.put(cartDTO.getIdGood(), item);
//            } else {
//                cartItems.put(cartDTO.getIdGood(), cartDTO);
//            }
////            System.err.println(String.valueOf(Integer.parseInt(cartItems.get(cartDTO.getIdGood()).getQuantity())+1));
//        }
        cartItems.remove(idGood);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @GetMapping
    public ResponseEntity<List<CartDTO>> getCartPage(HttpSession session){
        List<CartDTO> list = new ArrayList<>();
        for (Long key: cartItems.keySet()){
            list.add(cartItems.get(key));
        }
        if(list.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(list,HttpStatus.OK);
    }
}
