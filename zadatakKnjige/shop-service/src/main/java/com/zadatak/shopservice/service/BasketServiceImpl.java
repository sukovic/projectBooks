package com.zadatak.shopservice.service;

import com.zadatak.shopservice.model.Basket;
import com.zadatak.shopservice.model.BoughtBook;
import com.zadatak.shopservice.repository.BasketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.Optional;

@Service
public class BasketServiceImpl implements BasketService {

    final BasketRepository basketRepository;

    @Autowired
    public BasketServiceImpl(BasketRepository basketRepository) {
        this.basketRepository = basketRepository;
    }

    @Override
    public void deleteBasket(Long id) {
        basketRepository.deleteById(id);
    }

    @Override
    public boolean removeFromBasket(Long basketId, Long bookId) {
        Optional<Basket> byId = basketRepository.findById(basketId);
        if (!byId.isPresent()) {
            return false;
        }
        boolean found = false;
        Basket basket = byId.get();
        for (BoughtBook bb : basket.getBoughtBooks()) {
            if (bb.getBookId().equals(bookId)) {
                basket.getBoughtBooks().remove(bb);
                found = true;
                break;
            }
        }
        // there is more item beside this book
        if (found && basket.getBoughtBooks().size() > 1) {
            basketRepository.save(basket);
        }
        // this is only item delete whole basket
        else if (found) {
            deleteBasket(basketId);
        }
        return found;
    }

    @Override
    public boolean changeQuantity(Long basketId, Long bookId, Long newQuantity) {
        Optional<Basket> byId = basketRepository.findById(basketId);
        if (!byId.isPresent()) {
            return false;
        }
        Basket basket = byId.get();
        boolean found = false;
        for (BoughtBook bb : basket.getBoughtBooks()) {
            if (bb.getBookId().equals(bookId)) {
                found = true;
                double price = bb.getBoughtPrice() / bb.getQuantity();
                bb.setBoughtPrice(price * newQuantity.intValue());
                bb.setQuantity(newQuantity.intValue());
            }
        }
        if (found) {
            basketRepository.save(basket);
        }

        return found;
    }
}
