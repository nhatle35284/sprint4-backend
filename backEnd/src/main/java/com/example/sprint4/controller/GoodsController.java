package com.example.sprint4.controller;

import com.example.sprint4.dto.CategoryDTO;
import com.example.sprint4.dto.GoodsDTO;
import com.example.sprint4.model.Category;
import com.example.sprint4.model.Goods;
import com.example.sprint4.service.CategoryService;
import com.example.sprint4.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("goods")
public class GoodsController {
    @Autowired
    GoodsService goodsService;
    @Autowired
    CategoryService categoryService;

    @GetMapping("list")
    public ResponseEntity<List<GoodsDTO>> getListGoods() {
        List<Goods> goodsList = goodsService.findAll();
        List<GoodsDTO> goodsDTOS = new ArrayList<>();
        for (Goods goods : goodsList) {
            GoodsDTO goodsDTO = new GoodsDTO();
            goodsDTO.setIdGood(goods.getIdGood());
            goodsDTO.setGoodName(goods.getGoodName());
            goodsDTO.setPrice(goods.getPrice());
            goodsDTO.setQuantity(goods.getQuantity());
            goodsDTO.setTrademark(goods.getTrademark());
            goodsDTO.setSaleOff(goods.getSaleOff());
            goodsDTO.setImage(goods.getImage());
            goodsDTO.setPriceForSaleOff(goods.getPriceForSaleOff());
            goodsDTO.setIdCategory(goods.getCategory().getIdCategory());
            goodsDTO.setCategoryName(goods.getCategory().getCategoryName());
            goodsDTOS.add(goodsDTO);
        }
        return new ResponseEntity<>(goodsDTOS, HttpStatus.OK);
    }

    @GetMapping("listCategory")
    public ResponseEntity<List<CategoryDTO>> getListCategory() {
        List<Category> categories = categoryService.findAll();
        List<CategoryDTO> categoryDTOS = new ArrayList<>();
        for (Category category : categories){
            CategoryDTO categoryDTO = new CategoryDTO();
            categoryDTO.setIdCategory(category.getIdCategory());
            categoryDTO.setCategoryName(category.getCategoryName());
            categoryDTOS.add(categoryDTO);
        }

            return new ResponseEntity<>(categoryDTOS, HttpStatus.OK);
    }

    @GetMapping("{idGood}")
    public ResponseEntity<GoodsDTO> getGoods(@PathVariable Long idGood) {
        Goods goods = goodsService.findById(idGood);
        GoodsDTO goodsDTO = new GoodsDTO();
        goodsDTO.setIdGood(goods.getIdGood());
        goodsDTO.setGoodName(goods.getGoodName());
        goodsDTO.setPrice(goods.getPrice());
        goodsDTO.setQuantity(goods.getQuantity());
        goodsDTO.setTrademark(goods.getTrademark());
        goodsDTO.setSaleOff(goods.getSaleOff());
        goodsDTO.setImage(goods.getImage());
        goodsDTO.setPriceForSaleOff(goods.getPriceForSaleOff());
        goodsDTO.setIdCategory(goods.getCategory().getIdCategory());
        goodsDTO.setCategoryName(goods.getCategory().getCategoryName());
        return new ResponseEntity<>(goodsDTO, HttpStatus.OK);
    }

    @GetMapping("search")
    public ResponseEntity<List<GoodsDTO>> getListSearchGoods(@RequestParam("value1") String keySearch) {
        List<Goods> goodsList;
        List<GoodsDTO> goodsDTOS = new ArrayList<>();
        if ("".equals(keySearch)) {
            goodsList = goodsService.findAll();
            for (Goods goods : goodsList) {
                GoodsDTO goodsDTO = new GoodsDTO();
                goodsDTO.setIdGood(goods.getIdGood());
                goodsDTO.setGoodName(goods.getGoodName());
                goodsDTO.setPrice(goods.getPrice());
                goodsDTO.setQuantity(goods.getQuantity());
                goodsDTO.setTrademark(goods.getTrademark());
                goodsDTO.setSaleOff(goods.getSaleOff());
                goodsDTO.setImage(goods.getImage());
                goodsDTO.setPriceForSaleOff(goods.getPriceForSaleOff());
                goodsDTO.setIdCategory(goods.getCategory().getIdCategory());
                goodsDTO.setCategoryName(goods.getCategory().getCategoryName());
                goodsDTOS.add(goodsDTO);
            }
        } else {
            goodsList = goodsService.findAllByGoodNameContaining(keySearch);
            for (Goods goods : goodsList) {
                GoodsDTO goodsDTO = new GoodsDTO();
                goodsDTO.setIdGood(goods.getIdGood());
                goodsDTO.setGoodName(goods.getGoodName());
                goodsDTO.setPrice(goods.getPrice());
                goodsDTO.setQuantity(goods.getQuantity());
                goodsDTO.setTrademark(goods.getTrademark());
                goodsDTO.setSaleOff(goods.getSaleOff());
                goodsDTO.setImage(goods.getImage());
                goodsDTO.setPriceForSaleOff(goods.getPriceForSaleOff());
                goodsDTO.setIdCategory(goods.getCategory().getIdCategory());
                goodsDTO.setCategoryName(goods.getCategory().getCategoryName());
                goodsDTOS.add(goodsDTO);
            }
        }
        return new ResponseEntity<>(goodsDTOS, HttpStatus.OK);
    }

    @DeleteMapping("delete/{idGood}")
    public ResponseEntity<Void> deleteGoods(@PathVariable Long idGood) {
        if (idGood == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        goodsService.deleteById(idGood);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("edit/{idGood}")
    public ResponseEntity<Void> editGoods(@PathVariable Long idGood, @RequestBody GoodsDTO goodsDTO) {
        ArrayList<Integer> arrListString = new ArrayList<>();
        arrListString.remove(1);
        if (idGood == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        Goods goods = goodsService.findById(idGood);
//        goods.setIdGood(goodsDTO.getIdGood());
        goods.setGoodName(goodsDTO.getGoodName());
        goods.setPrice(goodsDTO.getPrice());
        goods.setQuantity(goodsDTO.getQuantity());
        goods.setTrademark(goodsDTO.getTrademark());
        goods.setSaleOff(goodsDTO.getSaleOff());
        goods.setImage(goodsDTO.getImage());
        goods.setPriceForSaleOff(goodsDTO.getPriceForSaleOff());
        goods.setCategory(categoryService.findById(goodsDTO.getIdCategory()));
        goodsService.save(goods);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PostMapping("create")
    public ResponseEntity<Void> deleteGoods(@RequestBody GoodsDTO goodsDTO) {
        Goods goods = new Goods();
//        goods.setIdGood(goodsDTO.getIdGood());
        goods.setGoodName(goodsDTO.getGoodName());
        goods.setPrice(goodsDTO.getPrice());
        goods.setQuantity(goodsDTO.getQuantity());
        goods.setTrademark(goodsDTO.getTrademark());
        goods.setSaleOff(goodsDTO.getSaleOff());
        goods.setImage(goodsDTO.getImage());
        goods.setPriceForSaleOff(goodsDTO.getPriceForSaleOff());
        goods.setCategory(categoryService.findById(goodsDTO.getIdCategory()));
        goodsService.save(goods);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
