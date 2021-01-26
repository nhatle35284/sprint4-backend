package com.example.sprint4.controller;

import com.example.sprint4.dto.CartDTO;
import com.example.sprint4.model.Cart;
import com.example.sprint4.model.Category;
import com.example.sprint4.model.Goods;
import com.example.sprint4.model.User;
import com.example.sprint4.service.CartService;
import com.example.sprint4.service.CategoryService;
import com.example.sprint4.service.GoodsService;
import com.example.sprint4.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@CrossOrigin
@RequestMapping("cart")
public class CartController {
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

    @GetMapping("list")
    public ResponseEntity<List<CartDTO>> getListCart() {
        List<Cart> cartList = cartService.findAll();
        List<CartDTO> cartDTOS = new ArrayList<>();
        for (Cart cart : cartList) {
            CartDTO cartDTO = new CartDTO();
            cartDTO.setIdCart(cart.getIdCart());
            cartDTO.setQuantity(cart.getQuantity());
            cartDTO.setIdUser(cart.getUser().getIdUser());
            cartDTO.setUserName(cart.getUser().getUsername());
            cartDTO.setIdGood(cart.getGoods().getIdGood());
            cartDTO.setGoodsName(cart.getGoods().getGoodName());
            cartDTO.setImage(cart.getGoods().getImage());
            cartDTO.setPrice(cart.getGoods().getPrice());
            cartDTOS.add(cartDTO);
        }
        return new ResponseEntity<>(cartDTOS, HttpStatus.OK);
    }

    @GetMapping("list/{idUser}")
    public ResponseEntity<List<CartDTO>> getListCart(@PathVariable Long idUser) {
        List<Cart> cartList = cartService.findAllByUser_IdUser(idUser);
        List<CartDTO> cartDTOS = new ArrayList<>();
        for (Cart cart : cartList) {
            CartDTO cartDTO = new CartDTO();
            cartDTO.setIdCart(cart.getIdCart());
            cartDTO.setQuantity(cart.getQuantity());
            cartDTO.setIdUser(cart.getUser().getIdUser());
            cartDTO.setUserName(cart.getUser().getUsername());
            cartDTO.setIdGood(cart.getGoods().getIdGood());
            cartDTO.setGoodsName(cart.getGoods().getGoodName());
            cartDTO.setImage(cart.getGoods().getImage());
            cartDTO.setPrice(cart.getGoods().getPrice());
            cartDTOS.add(cartDTO);
        }
        return new ResponseEntity<>(cartDTOS, HttpStatus.OK);
    }

    @GetMapping("{idCart}")
    public ResponseEntity<CartDTO> getCart(@PathVariable Long idCart) {
        Cart cart = cartService.findById(idCart);
        CartDTO cartDTO = new CartDTO();
        cartDTO.setIdCart(cart.getIdCart());
        cartDTO.setQuantity(cart.getQuantity());
        cartDTO.setIdUser(cart.getUser().getIdUser());
        cartDTO.setUserName(cart.getUser().getUsername());
        cartDTO.setIdGood(cart.getGoods().getIdGood());
        cartDTO.setGoodsName(cart.getGoods().getGoodName());
        cartDTO.setImage(cart.getGoods().getImage());
        cartDTO.setPrice(cart.getGoods().getPrice());
//        cartList.setQuantity(cart.getQuantity()+1);
        return new ResponseEntity<>(cartDTO, HttpStatus.OK);
    }

    @DeleteMapping("delete/{idCart}")
    public ResponseEntity<Void> deleteCart(@PathVariable Long idCart) {
        if (idCart == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        cartService.deleteById(idCart);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("idGood/{idGood}&&{idUser}")
    public ResponseEntity<CartDTO> getCartByGoods(@PathVariable Long idGood, @PathVariable Long idUser) {
        Cart cart = cartService.findByGoods_IdGood(idGood, idUser);
        CartDTO cartDTO = new CartDTO();
        cartDTO.setIdCart(cart.getIdCart());
        cartDTO.setQuantity(cart.getQuantity());
        cartDTO.setIdUser(cart.getUser().getIdUser());
        cartDTO.setUserName(cart.getUser().getUsername());
        cartDTO.setIdGood(cart.getGoods().getIdGood());
        cartDTO.setGoodsName(cart.getGoods().getGoodName());
        cartDTO.setImage(cart.getGoods().getImage());
        cartDTO.setPrice(cart.getGoods().getPrice());
        cartDTO.setStatus(cart.getStatus());
        return new ResponseEntity<>(cartDTO, HttpStatus.OK);
    }

    @PostMapping("update/{idCart}")
    public ResponseEntity<Void> updateCart(@PathVariable Long idCart, @RequestBody CartDTO cartNew) {
        Cart cart = cartService.findById(idCart);
        cart.setQuantity(cartNew.getQuantity());
        cartService.save(cart);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("updateBill/{idUser}")
    public ResponseEntity<Void> updateCartBill(@PathVariable Long idUser) {
        List<Cart> carts = cartService.findAllByUser_IdUser(idUser);
        for (Cart cart : carts) {
            Cart cart1 = cartService.findById(cart.getIdCart());
            cart1.setStatus(true);
            cartService.save(cart1);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("create")
    public ResponseEntity<Void> createCart(@RequestBody CartDTO cart) {
//        Cart cart = cartService.findById(idCart);
        Cart cart1 = new Cart();
        cart1.setUser(userService.findById(cart.getIdUser()));
        cart1.setGoods(goodsService.findById(cart.getIdGood()));
        cart1.setQuantity("1");
        cart1.setStatus(false);
        cartService.save(cart1);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("anonymous/{products}")
    public ResponseEntity<Void> anonymous(@PathVariable ArrayList<Integer> products) {
        int count;
        Set<Integer> set = new HashSet<Integer>();
        for (int i = 0; i < products.size(); i++) {
            set.add(products.get(i));
        }
        for (int i = 0; i < set.size(); i++) {
            count = 0;
            for (int j = 0; j < products.size(); j++) {
                if (products.get(i).equals(products.get(j))){
                   count++;
                }
            }
            System.err.println(count);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping("send")
    public void sendMail() throws MessagingException {
        List<Cart> carts = cartService.findAllByUser_IdUser((long) 1);
        User user = userService.findById((long) 1);
        MimeMessage msg = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(msg, true, "utf-8");
//        SimpleMailMessage message = new SimpleMailMessage();
        helper.setTo(user.getEmail());
        helper.setSubject("ESTORE đã nhận đơn hàng");
//        StringBuilder stringBuilder = new StringBuilder("<!DOCTYPE html>\n" +
//                "<html lang=\"en\">\n" +
//                "<head>\n" +
//                "    <meta charset=\"UTF-8\">\n" +
//                "    <title>Title</title>\n" +
//                "</head>\n" +
//                "<body>\n" +
//                "<table style=\"border-style: groove\">\n" +
//                "    <tr>\n" +
//                "        <th>a</th>\n" +
//                "        <th>b</th>\n" +
//                "        <th>c</th>\n" +
//                "    </tr>\n" +
//                "    <tr>\n" +
//                "        <td>a</td>\n" +
//                "        <td>b</td>\n" +
//                "        <td>c</td>\n" +
//                "    </tr>\n" +
//                "</table>\n" +
//                "</body>\n" +
//                "</html>");
        StringBuilder stringBuilder = new StringBuilder("<!DOCTYPE html>\n" +
                "<!--\n" +
                "  Invoice template by invoicebus.com\n" +
                "  To customize this template consider following this guide https://invoicebus.com/how-to-create-invoice-template/\n" +
                "  This template is under Invoicebus Template License, see https://invoicebus.com/templates/license/\n" +
                "-->\n" +
                "<html lang=\"en\">\n" +
                "  <head>\n" +
                "    <meta charset=\"utf-8\">\n" +
                "    <title>Cobardia (firebrick)</title>\n" +
                "    \n" +
                "    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge,chrome=1\">\n" +
                "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n" +
                "    \n" +
                "    <meta name=\"description\" content=\"Invoicebus Invoice Template\">\n" +
                "    <meta name=\"author\" content=\"Invoicebus\">\n" +
                "\n" +
                "    <meta name=\"template-hash\" content=\"baadb45704803c2d1a1ac3e569b757d5\">\n" +
                "\n" +
                "<!--    <link rel=\"stylesheet\" href=\"css/template.css\">-->\n" +
                "    <style>\n" +
                "      /*! Invoice Templates @author: Invoicebus @email: info@invoicebus.com @web: https://invoicebus.com @version: 1.0.0 @updated: 2015-02-27 16:02:34 @license: Invoicebus */\n" +
                "      /* Reset styles */\n" +
                "      @import url(\"https://fonts.googleapis.com/css?family=Open+Sans:400,700&subset=cyrillic,cyrillic-ext,latin,greek-ext,greek,latin-ext,vietnamese\");\n" +
                "      html, body, div, span, applet, object, iframe,\n" +
                "      h1, h2, h3, h4, h5, h6, p, blockquote, pre,\n" +
                "      a, abbr, acronym, address, big, cite, code,\n" +
                "      del, dfn, em, img, ins, kbd, q, s, samp,\n" +
                "      small, strike, strong, sub, sup, tt, var,\n" +
                "      b, u, i, center,\n" +
                "      dl, dt, dd, ol, ul, li,\n" +
                "      fieldset, form, label, legend,\n" +
                "      table, caption, tbody, tfoot, thead, tr, th, td,\n" +
                "      article, aside, canvas, details, embed,\n" +
                "      figure, figcaption, footer, header, hgroup,\n" +
                "      menu, nav, output, ruby, section, summary,\n" +
                "      time, mark, audio, video {\n" +
                "        margin: 0;\n" +
                "        padding: 0;\n" +
                "        border: 0;\n" +
                "        font: inherit;\n" +
                "        font-size: 100%;\n" +
                "        vertical-align: baseline;\n" +
                "      }\n" +
                "\n" +
                "      html {\n" +
                "        line-height: 1;\n" +
                "      }\n" +
                "\n" +
                "      ol, ul {\n" +
                "        list-style: none;\n" +
                "      }\n" +
                "\n" +
                "      table {\n" +
                "        border-collapse: collapse;\n" +
                "        border-spacing: 0;\n" +
                "      }\n" +
                "\n" +
                "      caption, th, td {\n" +
                "        text-align: left;\n" +
                "        font-weight: normal;\n" +
                "        vertical-align: middle;\n" +
                "      }\n" +
                "\n" +
                "      q, blockquote {\n" +
                "        quotes: none;\n" +
                "      }\n" +
                "      q:before, q:after, blockquote:before, blockquote:after {\n" +
                "        content: \"\";\n" +
                "        content: none;\n" +
                "      }\n" +
                "\n" +
                "      a img {\n" +
                "        border: none;\n" +
                "      }\n" +
                "\n" +
                "      article, aside, details, figcaption, figure, footer, header, hgroup, main, menu, nav, section, summary {\n" +
                "        display: block;\n" +
                "      }\n" +
                "\n" +
                "      /* Invoice styles */\n" +
                "      /**\n" +
                "       * DON'T override any styles for the <html> and <body> tags, as this may break the layout.\n" +
                "       * Instead wrap everything in one main <div id=\"container\"> element where you may change\n" +
                "       * something like the font or the background of the invoice\n" +
                "       */\n" +
                "      html, body {\n" +
                "        /* MOVE ALONG, NOTHING TO CHANGE HERE! */\n" +
                "      }\n" +
                "\n" +
                "      /**\n" +
                "       * IMPORTANT NOTICE: DON'T USE '!important' otherwise this may lead to broken print layout.\n" +
                "       * Some browsers may require '!important' in oder to work properly but be careful with it.\n" +
                "       */\n" +
                "      .clearfix {\n" +
                "        display: block;\n" +
                "        clear: both;\n" +
                "      }\n" +
                "\n" +
                "      .hidden {\n" +
                "        display: none;\n" +
                "      }\n" +
                "\n" +
                "      b, strong, .bold {\n" +
                "        font-weight: bold;\n" +
                "      }\n" +
                "\n" +
                "      #container {\n" +
                "        font: normal 13px/1.4em 'Open Sans', Sans-serif;\n" +
                "        margin: 0 auto;\n" +
                "        min-height: 1158px;\n" +
                "        background: #F7EDEB url(\"../img/bg.png\") 0 0 no-repeat;\n" +
                "        background-size: 100% auto;\n" +
                "        color: #5B6165;\n" +
                "        position: relative;\n" +
                "      }\n" +
                "\n" +
                "      #memo {\n" +
                "        padding-top: 50px;\n" +
                "        margin: 0 110px 0 60px;\n" +
                "        border-bottom: 1px solid #ddd;\n" +
                "        height: 115px;\n" +
                "      }\n" +
                "      #memo .logo {\n" +
                "        float: left;\n" +
                "        margin-right: 20px;\n" +
                "      }\n" +
                "      #memo .logo img {\n" +
                "        width: 150px;\n" +
                "        height: 100px;\n" +
                "      }\n" +
                "      #memo .company-info {\n" +
                "        float: right;\n" +
                "        text-align: right;\n" +
                "      }\n" +
                "      #memo .company-info > div:first-child {\n" +
                "        line-height: 1em;\n" +
                "        font-weight: bold;\n" +
                "        font-size: 22px;\n" +
                "        color: #B32C39;\n" +
                "      }\n" +
                "      #memo .company-info span {\n" +
                "        font-size: 11px;\n" +
                "        display: inline-block;\n" +
                "        min-width: 20px;\n" +
                "      }\n" +
                "      #memo:after {\n" +
                "        content: '';\n" +
                "        display: block;\n" +
                "        clear: both;\n" +
                "      }\n" +
                "\n" +
                "      #invoice-title-number {\n" +
                "        font-weight: bold;\n" +
                "        margin: 30px 0;\n" +
                "      }\n" +
                "      #invoice-title-number span {\n" +
                "        line-height: 0.88em;\n" +
                "        display: inline-block;\n" +
                "        min-width: 20px;\n" +
                "      }\n" +
                "      #invoice-title-number #title {\n" +
                "        text-transform: uppercase;\n" +
                "        padding: 0px 2px 0px 60px;\n" +
                "        font-size: 50px;\n" +
                "        background: #F4846F;\n" +
                "        color: white;\n" +
                "      }\n" +
                "      #invoice-title-number #number {\n" +
                "        margin-left: 10px;\n" +
                "        font-size: 35px;\n" +
                "        position: relative;\n" +
                "        top: -5px;\n" +
                "      }\n" +
                "\n" +
                "      #client-info {\n" +
                "        float: left;\n" +
                "        margin-left: 60px;\n" +
                "        min-width: 220px;\n" +
                "      }\n" +
                "      #client-info > div {\n" +
                "        margin-bottom: 3px;\n" +
                "        min-width: 20px;\n" +
                "      }\n" +
                "      #client-info span {\n" +
                "        display: block;\n" +
                "        min-width: 20px;\n" +
                "      }\n" +
                "      #client-info > span {\n" +
                "        text-transform: uppercase;\n" +
                "      }\n" +
                "\n" +
                "      table {\n" +
                "        table-layout: fixed;\n" +
                "      }\n" +
                "      table th, table td {\n" +
                "        vertical-align: top;\n" +
                "        word-break: keep-all;\n" +
                "        word-wrap: break-word;\n" +
                "      }\n" +
                "\n" +
                "      #items {\n" +
                "        margin: 35px 30px 0 30px;\n" +
                "      }\n" +
                "      #items .first-cell, #items table th:first-child, #items table td:first-child {\n" +
                "        width: 40px !important;\n" +
                "        padding-left: 0 !important;\n" +
                "        padding-right: 0 !important;\n" +
                "        text-align: right;\n" +
                "      }\n" +
                "      #items table {\n" +
                "        border-collapse: separate;\n" +
                "        width: 100%;\n" +
                "      }\n" +
                "      #items table th {\n" +
                "        font-weight: bold;\n" +
                "        padding: 5px 8px;\n" +
                "        text-align: right;\n" +
                "        background: #B32C39;\n" +
                "        color: white;\n" +
                "        text-transform: uppercase;\n" +
                "      }\n" +
                "      #items table th:nth-child(2) {\n" +
                "        width: 30%;\n" +
                "        text-align: left;\n" +
                "      }\n" +
                "      #items table th:last-child {\n" +
                "        text-align: right;\n" +
                "      }\n" +
                "      #items table td {\n" +
                "        padding: 9px 8px;\n" +
                "        text-align: right;\n" +
                "        border-bottom: 1px solid #ddd;\n" +
                "      }\n" +
                "      #items table td:nth-child(2) {\n" +
                "        text-align: left;\n" +
                "      }\n" +
                "\n" +
                "      #sums {\n" +
                "        margin: 25px 30px 0 0;\n" +
                "        background: url(\"../img/total-stripe-firebrick.png\") right bottom no-repeat;\n" +
                "      }\n" +
                "      #sums table {\n" +
                "        float: right;\n" +
                "      }\n" +
                "      #sums table tr th, #sums table tr td {\n" +
                "        min-width: 100px;\n" +
                "        padding: 9px 8px;\n" +
                "        text-align: right;\n" +
                "      }\n" +
                "      #sums table tr th {\n" +
                "        font-weight: bold;\n" +
                "        text-align: left;\n" +
                "        padding-right: 35px;\n" +
                "      }\n" +
                "      #sums table tr td.last {\n" +
                "        min-width: 0 !important;\n" +
                "        max-width: 0 !important;\n" +
                "        width: 0 !important;\n" +
                "        padding: 0 !important;\n" +
                "        border: none !important;\n" +
                "      }\n" +
                "      #sums table tr.amount-total th {\n" +
                "        text-transform: uppercase;\n" +
                "      }\n" +
                "      #sums table tr.amount-total th, #sums table tr.amount-total td {\n" +
                "        font-size: 15px;\n" +
                "        font-weight: bold;\n" +
                "      }\n" +
                "      #sums table tr:last-child th {\n" +
                "        text-transform: uppercase;\n" +
                "      }\n" +
                "      #sums table tr:last-child th, #sums table tr:last-child td {\n" +
                "        font-size: 15px;\n" +
                "        font-weight: bold;\n" +
                "        color: white;\n" +
                "      }\n" +
                "\n" +
                "      #invoice-info {\n" +
                "        float: left;\n" +
                "        margin: 50px 40px 0 60px;\n" +
                "      }\n" +
                "      #invoice-info > div > span {\n" +
                "        display: inline-block;\n" +
                "        min-width: 20px;\n" +
                "        min-height: 18px;\n" +
                "        margin-bottom: 3px;\n" +
                "      }\n" +
                "      #invoice-info > div > span:first-child {\n" +
                "        color: black;\n" +
                "      }\n" +
                "      #invoice-info > div > span:last-child {\n" +
                "        color: #aaa;\n" +
                "      }\n" +
                "      #invoice-info:after {\n" +
                "        content: '';\n" +
                "        display: block;\n" +
                "        clear: both;\n" +
                "      }\n" +
                "\n" +
                "      #terms {\n" +
                "        float: left;\n" +
                "        margin-top: 50px;\n" +
                "      }\n" +
                "      #terms .notes {\n" +
                "        min-height: 30px;\n" +
                "        min-width: 50px;\n" +
                "        color: #B32C39;\n" +
                "      }\n" +
                "      #terms .payment-info div {\n" +
                "        margin-bottom: 3px;\n" +
                "        min-width: 20px;\n" +
                "      }\n" +
                "\n" +
                "      .thank-you {\n" +
                "        margin: 10px 0 30px 0;\n" +
                "        display: inline-block;\n" +
                "        min-width: 20px;\n" +
                "        text-transform: uppercase;\n" +
                "        font-weight: bold;\n" +
                "        line-height: 0.88em;\n" +
                "        float: right;\n" +
                "        padding: 0px 30px 0px 2px;\n" +
                "        font-size: 50px;\n" +
                "        background: #F4846F;\n" +
                "        color: white;\n" +
                "      }\n" +
                "\n" +
                "      .ib_bottom_row_commands {\n" +
                "        margin-left: 30px !important;\n" +
                "      }\n" +
                "\n" +
                "      /**\n" +
                "       * If the printed invoice is not looking as expected you may tune up\n" +
                "       * the print styles (you can use !important to override styles)\n" +
                "       */\n" +
                "      @media print {\n" +
                "        /* Here goes your print styles */\n" +
                "      }\n" +
                "\n" +
                "    </style>\n" +
                "  </head>\n" +
                "  <body>\n" +
                "    <div id=\"container\">\n" +
                "      <section id=\"memo\">\n" +
                "        <div class=\"logo\">\n" +
                "          <img\n" +
                "  src=\"https://lh3.googleusercontent.com/y2yzZg9cyMtWuRNOXSRpbx_eb4SkIksCoNZh2X_0hvU28U2kxRpbEGY1-IEbHKwyQhKCA7TRw2w4TG5oSkqHggZnRK5sWJI_r9dePIFUGEaA9T0mPTD1WrwvoEnFcrxsH9h2AGq_8IZ5Skx9-B31F1rj7bgheuHrWbD3onpAKbVX3IT4THJTTHVcbIxh-J7288N0wdu0V5yR9hq73skbtqApHfv-z3M93yf1N48W-o_pVam0W16IwUscag4-1h7dDQFUeDzfOorpXOOutGf-UE1Kq1-iW7D6h_9-0GJj1XI2enRYoAgYMXBMa8O5vsTKAQ-bHFyMnbCS2nQDo68WOzE6oguBtG_GTMytsaiQX3RII-awn3c4tH8NWO6OcJM1GDXHauzmbSRf948OlKxmET1syMnVy-MQb3n6hCzwv801gf2SXywxuTmhmqmZNdHnb5lsQiIx5pnWU4ISn2nxg7IGGH8fmd2si_Iuyi5kfWh_cMqoH6EIdjUJRnBMLgniiqjHfwTe2rt1GAxFyA--Nnu8WWoBvKl7qEgsf2pG_qqwKVz89GfOMeh_XTd5P-urORFyFQcR_AXL5mPUfDhuaT7PlgZ8G-8bWDxw9BWrqFgf14pbN5O9k25WvlGKzeJ3brI-6mxBz44ngrSljuhMZ2swCMlfyAnq68bQWQ_xWztgGuO2OHVf2aZvmbV4=w89-h23-no?authuser=1\"\n" +
                "  style=\"width: 100px;height:50px\"/>" +
                "        </div>\n" +
                "        \n" +
                "        <div class=\"company-info\">\n" +
                "          <div>ESTORE.</div>\n" +
                "\n" +
                "          <br />\n" +
                "          \n" +
                "          <span>295 Nguyen Tat Thanh,Hai Chau,Da Nang</span>\n" +
                "\n" +
                "          <br />\n" +
                "          \n" +
                "          <span>+84 338 579 681</span>\n" +
                "          <span>leenhat12a1nh@gmail.com</span>\n" +
                "        </div>\n" +
                "\n" +
                "      </section>\n" +
                "\n" +
                "      <section id=\"invoice-title-number\">\n" +
                "      \n" +
                "        <span id=\"title\">INVOICE</span>\n" +
                "        <span id=\"number\">#0028354</span>\n" +
                "        \n" +
                "      </section>\n" +
                "      \n" +
                "      <div class=\"clearfix\"></div>\n" +
                "      \n" +
                "      <section id=\"client-info\">\n" +
                "        <span>To</span>\n" +
                "        <div>\n");
        stringBuilder.append("<span class=\"bold\">");
        stringBuilder.append(user.getFullName());
        stringBuilder.append("</span>\n");
        stringBuilder.append("</div>\n" +
                "        \n" +
                "        <div>\n");
        stringBuilder.append("<span>");
        stringBuilder.append(user.getAddress());
        stringBuilder.append("</span>\n" +
                "        </div>\n" +
                "        \n" +
                "        <div>\n" +
                "          <span>");
        stringBuilder.append(user.getNumberPhone());
        stringBuilder.append("</span>\n" +
                "        </div>\n" +
                "        \n" +
                "        <div>\n" +
                "          <span>");
        stringBuilder.append(user.getEmail());
        stringBuilder.append("</span>\n" +
                " </div>\n");
        stringBuilder.append(
                "</section>\n" +
                        "      \n" +
                        "      <div class=\"clearfix\"></div>\n" +
                        "      \n" +
                        "      <section id=\"items\">\n" +
                        "        \n" +
                        "        <table cellpadding=\"0\" cellspacing=\"0\">\n" +
                        "        \n" +
                        "          <tr>\n" +
                        "            <th>STT</th> <!-- Dummy cell for the row number and row commands -->\n" +
                        "            <th>Good Name</th>\n" +
                        "            <th>Price</th>\n" +
                        "            <th>Quantity</th>\n" +
                        "            <th>Total Money</th>\n" +
                        "          </tr>\n" +
                        "          \n");
        Double total = 0d;
        for (int i = 0; i < carts.size(); i++) {
//            priceSale = Integer.parseInt(goodsCart.getPrice())*Integer.parseInt(goodsCart.getQuantityCart())-( (Integer.parseInt(goodsCart.getPrice())*Integer.parseInt(goodsCart.getQuantityCart()) * Integer.parseInt(goodsCart.getSaleOff()))/100);
//            totalMoney += priceSale;
            stringBuilder.append("<tr data-iterate=\"item\">\n");
            stringBuilder.append("<td>");
            stringBuilder.append(i + 1);
            stringBuilder.append("</td>\n");
            stringBuilder.append("<td>");
            stringBuilder.append(carts.get(i).getGoods().getGoodName());
            stringBuilder.append("</td>\n");
            stringBuilder.append("<td>");
//            stringBuilder.append(String.format("%,.3f",Double.parseDouble((goodsCart.getPrice()))/1000));
            stringBuilder.append(carts.get(i).getGoods().getPrice());
            stringBuilder.append(" VNĐ");
            stringBuilder.append("</td>\n");
            stringBuilder.append("<td>");
            stringBuilder.append(carts.get(i).getQuantity());
            stringBuilder.append("</td>\n");
            stringBuilder.append("<td>");
            stringBuilder.append(String.valueOf(Double.parseDouble(carts.get(i).getQuantity()) * Double.parseDouble(carts.get(i).getGoods().getPrice())));
            stringBuilder.append(" VNĐ");
            stringBuilder.append("</td>\n");
            stringBuilder.append("</tr>\n");
            total += Double.parseDouble(carts.get(i).getQuantity()) * Double.parseDouble(carts.get(i).getGoods().getPrice());
        }
        stringBuilder.append(
                "        </table>\n" +
                        "        \n" +
                        "      </section>\n" +
                        "      \n" +
                        "      <section id=\"sums\">\n" +
                        "      \n" +
                        "        <table cellpadding=\"0\" cellspacing=\"0\">\n" +
                        "          <tr>\n" +
                        "            <th>Subtotal</th>\n" +
                        "            <td>");
        stringBuilder.append(String.valueOf(total));
        stringBuilder.append("VNĐ");
        stringBuilder.append("</td>\n" +
                "          </tr>\n" +
                "          \n" +
                "          <tr data-iterate=\"tax\">\n" +
                "            <th>Shipping</th>\n" +
                "            <td>2 VNĐ</td>\n" +
                "          </tr>\n" +
                "          \n" +
                "          <!-- You can use attribute data-hide-on-quote=\"true\" to hide specific information on quotes.\n" +
                "               For example Invoicebus doesn't need amount paid and amount due on quotes  -->\n" +
                "          <tr data-hide-on-quote=\"true\">\n" +
                "            <th>Total</th>\n" +
                "            <td>");
        stringBuilder.append(String.valueOf(total + 2));
        stringBuilder.append("VNĐ");
        stringBuilder.append(
                "</td>\n" +
                        "          </tr>\n" +
                        "          \n" +
                        "          <tr data-hide-on-quote=\"true\">\n" +
                        "            <th>Paid</th>\n" +
                        "            <td>0 VNĐ</td>\n" +
                        "          </tr>\n" +
                        "          \n" +
                        "          </tr>\n" +
                        "          \n" +
                        "          <tr data-hide-on-quote=\"true\">\n" +
                        "            <th>AMOUNT DUE</th>\n" +
                        "            <td>");
        stringBuilder.append(total + 2);
        stringBuilder.append("VNĐ");
        stringBuilder.append(
                "</td>\n" +
                        "          </tr>\n" +
                        "          \n" +
                        "        </table>\n" +
                        "\n" +
                        "        <div class=\"clearfix\"></div>\n" +
                        "        \n" +
                        "      </section>\n" +
                        "      \n" +
                        "      <div class=\"clearfix\"></div>\n" +
                        "\n" +
                        "      <section id=\"invoice-info\">\n" +
                        "        <div>\n" +
                        "          <span>Issue Date</span> <span>");
        stringBuilder.append(String.valueOf(LocalDate.now()));
        stringBuilder.append("</span>\n" +
                "        </div>\n" +
                "        <div>\n" +
                "          <span>Due Date</span> <span>2020/01/30</span>\n" +
                "        </div>\n" +
                "\n" +
                "        <br />\n" +
                "\n" +
                "        <div>\n" +
                "          <span>Currency:</span> <span>USD</span>\n" +
                "        </div>\n" +
                "        <div>\n" +
                "          <span>P.O</span> <span>#1/2/4</span>\n" +
                "        </div>\n" +
                "        <div>\n" +
                "          <span>Net</span> <span>21</span>\n" +
                "        </div>\n" +
                "      </section>\n" +
                "      \n" +
                "      <section id=\"terms\">\n" +
                "\n" +
                "        <div class=\"notes\">Fred, thank you very much. We really appreciate your business.\n" +
                "                    Please send payments before the due date</div>\n" +
                "\n" +
                "        <br />\n" +
                "\n" +
                "        <div class=\"payment-info\">\n" +
                "          <div> Payment details:</div>\n" +
                "          <div> ● ACC 123006705</div>\n" +
                "          <div>   ● IBAN US100000060345</div>\n" +
                "          <div>● SWIFT BOA447</div>\n" +
                "        </div>\n" +
                "        \n" +
                "      </section>\n" +
                "\n" +
                "      <div class=\"clearfix\"></div>\n" +
                "\n" +
                "      <div class=\"thank-you\">THANKS!</div>\n" +
                "\n" +
                "      <div class=\"clearfix\"></div>\n" +
                "    </div>\n" +
                "\n" +
                "    <script src=\"http://cdn.invoicebus.com/generator/generator.min.js?data=data.js\"></script>\n" +
                "  </body>\n" +
                "</html>\n");
        helper.setText(String.valueOf(stringBuilder), true);
        javaMailSender.send(msg);
//        javaMailSender.send(message);
    }

}
