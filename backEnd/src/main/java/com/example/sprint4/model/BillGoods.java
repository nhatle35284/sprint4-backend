package com.example.sprint4.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Collection;

@Entity
public class BillGoods {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idBillGoods;
    private String quantity;

    @ManyToOne
    @JoinColumn(name = "idGood")
    @JsonIgnoreProperties("billGoodsCollection")
    private Goods good;


    @ManyToOne
    @JoinColumn(name = "idBill")
    @JsonIgnoreProperties("billGoodsCollection")
    private Bill bill;

    public Long getIdBillGoods() {
        return idBillGoods;
    }

    public void setIdBillGoods(Long idBillGoods) {
        this.idBillGoods = idBillGoods;
    }

    public Goods getGood() {
        return good;
    }

    public void setGood(Goods good) {
        this.good = good;
    }

    public Bill getBill() {
        return bill;
    }

    public void setBill(Bill bill) {
        this.bill = bill;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }
}
