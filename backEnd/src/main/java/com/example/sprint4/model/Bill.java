package com.example.sprint4.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Collection;

@Entity
public class Bill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idBill;
    private String createdDate;
    private String quantity;
    private String billType;
    private boolean status;


    @OneToMany(mappedBy = "bill", cascade = CascadeType.ALL)
    @JsonIgnoreProperties("bill")
    private Collection<BillGoods> billGoodsCollection;

    @ManyToOne
    @JoinColumn(name = "idUser")
    @JsonIgnoreProperties("billCollection")
    private User user;

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Collection<BillGoods> getBillGoodsCollection() {
        return billGoodsCollection;
    }

    public void setBillGoodsCollection(Collection<BillGoods> billGoodsCollection) {
        this.billGoodsCollection = billGoodsCollection;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getIdBill() {
        return idBill;
    }

    public void setIdBill(Long idBill) {
        this.idBill = idBill;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getBillType() {
        return billType;
    }

    public void setBillType(String billType) {
        this.billType = billType;
    }
}
