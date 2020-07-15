package com.zadatak.shopservice.service;

import com.zadatak.shopservice.dto.BestSellerDto;
import com.zadatak.shopservice.dto.BookDto;
import com.zadatak.shopservice.model.Basket;
import com.zadatak.shopservice.model.BoughtBook;
import com.zadatak.shopservice.model.PurchaseOrder;
import com.zadatak.shopservice.repository.BasketRepository;
import com.zadatak.shopservice.repository.PurchaseOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PurchaseOrderServiceImpl implements PurchaseOrderService {

    final PurchaseOrderRepository purchaseOrderRepository;
    final BasketRepository basketRepository;

    @Autowired
    public PurchaseOrderServiceImpl(PurchaseOrderRepository purchaseOrderRepository, BasketRepository basketRepository) {
        this.purchaseOrderRepository = purchaseOrderRepository;
        this.basketRepository = basketRepository;
    }

    @Override
    public List<BestSellerDto> getTop10(List<BookDto> allBooks) {
        List<PurchaseOrder> all = purchaseOrderRepository.findAll();
        Map<BookDto, Integer> boughtMap = new HashMap<>();
        for (BookDto book : allBooks) {
            int howMany = 0;
            for (PurchaseOrder po : all) {
                for (BoughtBook bb : po.getBoughtBooks()) {
                    if (bb.getBookId().equals(book.getId()))
                        howMany += bb.getQuantity();
                }
            }
            boughtMap.put(book, howMany);
        }
        Map<BookDto, Integer> sorted = sortByValue(boughtMap);

        List<BestSellerDto> result = new ArrayList<>();
        int counter = 0;
        for (Map.Entry<BookDto, Integer> entry : sorted.entrySet()) {
            if (entry.getValue() > 0) {
                result.add(new BestSellerDto(entry.getKey().getTitle(), entry.getValue()));
                counter += 1;
            }
            if (counter == 10)
                break;
        }
        Collections.reverse(result);
        return result;
    }

    @Override
    public List<String> allGenres(List<BookDto> allBooks) {
        List<String> genres = new ArrayList<>();
        for (BookDto book : allBooks) {
            if (!genres.contains(book.getGenre())) {
                genres.add(book.getGenre());
            }
        }
        return genres;
    }

    @Override
    public List<BookDto> searchTitleAuthor(List<BookDto> allBooks, String title, String author) {
        List<BookDto> found = new ArrayList<>();
        for (BookDto book : allBooks) {
            if (book.getTitle().equals(title) && book.getAuthor().equals(author)) {
                found.add(book);
            }
        }
        return found;
    }

    @Override
    public List<BookDto> searchGenre(List<BookDto> allBooks, String genre) {
        List<BookDto> found = new ArrayList<>();
        for (BookDto book : allBooks) {
            if (book.getGenre().equals(genre)) {
                found.add(book);
            }
        }
        return found;
    }

    @Override
    public boolean purchaseOrder(Long basketId, String username) {
        Optional<Basket> byId = basketRepository.findById(basketId);
        if (!byId.isPresent()) {
            return false;
        }
        Basket basket = byId.get();
        double total = 0;
        for (BoughtBook bb : basket.getBoughtBooks()) {
            total += bb.getBoughtPrice();
        }
        PurchaseOrder purchaseOrder = new PurchaseOrder(username, total, basket.getBoughtBooks());
        basketRepository.deleteById(basketId);
        for (BoughtBook x : basket.getBoughtBooks()) {
            x.setId(null);
        }
        purchaseOrderRepository.save(purchaseOrder);

        return true;
    }

    // sort map by values
    // https://stackoverflow.com/a/2581754
    public static <K, V extends Comparable<? super V>> Map<K, V> sortByValue(Map<K, V> map) {
        List<Map.Entry<K, V>> list = new ArrayList<>(map.entrySet());
        list.sort(Map.Entry.comparingByValue());

        Map<K, V> result = new LinkedHashMap<>();
        for (Map.Entry<K, V> entry : list) {
            result.put(entry.getKey(), entry.getValue());
        }

        return result;
    }
}
