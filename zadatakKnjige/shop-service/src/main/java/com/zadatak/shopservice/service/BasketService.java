package com.zadatak.shopservice.service;

public interface BasketService {
    void deleteBasket(Long id);

    boolean removeFromBasket(Long basketId, Long bookId);

    boolean changeQuantity(Long basketId, Long bookId, Long newQuantity);
}
