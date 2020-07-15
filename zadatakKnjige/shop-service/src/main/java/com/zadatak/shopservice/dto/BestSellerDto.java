package com.zadatak.shopservice.dto;

public class BestSellerDto {

    private String nameOfBook;
    private int timesSold;

    public BestSellerDto() {
    }

    public BestSellerDto(String nameOfBook, int timesSold) {
        this.nameOfBook = nameOfBook;
        this.timesSold = timesSold;
    }

    public String getNameOfBook() {
        return nameOfBook;
    }

    public void setNameOfBook(String nameOfBook) {
        this.nameOfBook = nameOfBook;
    }

    public int getTimesSold() {
        return timesSold;
    }

    public void setTimesSold(int timesSold) {
        this.timesSold = timesSold;
    }
}
