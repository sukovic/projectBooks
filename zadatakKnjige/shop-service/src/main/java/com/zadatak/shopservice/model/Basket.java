package com.zadatak.shopservice.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Basket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String username;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "basket_id")
    private List<BoughtBook> boughtBooks = new ArrayList<>();

    public Basket() {
    }

    public Basket(String username, List<BoughtBook> boughtBooks) {
        this.username = username;
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

    public List<BoughtBook> getBoughtBooks() {
        return boughtBooks;
    }

    public void setBoughtBooks(List<BoughtBook> boughtBooks) {
        this.boughtBooks = boughtBooks;
    }
}
