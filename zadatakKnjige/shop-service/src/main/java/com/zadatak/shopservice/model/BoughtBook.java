package com.zadatak.shopservice.model;

import javax.persistence.*;

@Entity
public class BoughtBook {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Long bookId;

    @Column
    private int quantity;

    @Column
    private double boughtPrice;

    public BoughtBook() {
    }

    public BoughtBook(Long bookId, int quantity, double boughtPrice) {
        this.bookId = bookId;
        this.quantity = quantity;
        this.boughtPrice = boughtPrice;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getBoughtPrice() {
        return boughtPrice;
    }

    public void setBoughtPrice(double boughtPrice) {
        this.boughtPrice = boughtPrice;
    }
}
