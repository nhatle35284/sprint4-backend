package com.example.sprint4.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Collection;

@Entity
public class Goods {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idGood;
    private String goodName;
    private String price;
    private String quantity;
    private String trademark;
    private String saleOff;
    private String image;
    private String priceForSaleOff;

    @OneToMany(mappedBy = "good", cascade = CascadeType.ALL)
    @JsonIgnoreProperties("good")
    private Collection<BillGoods> billGoodsCollection;

    @OneToMany(mappedBy = "goods", cascade = CascadeType.ALL)
    @JsonIgnoreProperties("goods")
    private Collection<Cart> cartCollection;
    @ManyToOne
    @JoinColumn(name = "idCategory")
    @JsonIgnoreProperties("goodsCollection")
    private Category category;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Collection<BillGoods> getBillGoodsCollection() {
        return billGoodsCollection;
    }

    public void setBillGoodsCollection(Collection<BillGoods> billGoodsCollection) {
        this.billGoodsCollection = billGoodsCollection;
    }

    public Collection<Cart> getCartCollection() {
        return cartCollection;
    }

    public void setCartCollection(Collection<Cart> cartCollection) {
        this.cartCollection = cartCollection;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Long getIdGood() {
        return idGood;
    }

    public void setIdGood(Long idGood) {
        this.idGood = idGood;
    }

    public String getGoodName() {
        return goodName;
    }

    public void setGoodName(String goodName) {
        this.goodName = goodName;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getTrademark() {
        return trademark;
    }

    public void setTrademark(String trademark) {
        this.trademark = trademark;
    }

    public String getSaleOff() {
        return saleOff;
    }

    public void setSaleOff(String saleOff) {
        this.saleOff = saleOff;
    }

    public String getPriceForSaleOff() {
        return priceForSaleOff;
    }

    public void setPriceForSaleOff(String priceForSaleOff) {
        this.priceForSaleOff = priceForSaleOff;
    }
}
