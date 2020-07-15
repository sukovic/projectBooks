package com.zadatak.shopservice.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class PurchaseOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String username;

    @Column
    private double total;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "purchaseOrder_id")
    private List<BoughtBook> boughtBooks = new ArrayList<>();


    public PurchaseOrder() {
    }

    public PurchaseOrder(String username, double total, List<BoughtBook> boughtBooks) {
        this.username = username;
        this.total = total;
        this.boughtBooks = boughtBooks;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public List<BoughtBook> getBoughtBooks() {
        return boughtBooks;
    }

    public void setBoughtBooks(List<BoughtBook> boughtBooks) {
        this.boughtBooks = boughtBooks;
    }
}
