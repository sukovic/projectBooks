package com.zadatak.shopservice.controller;

import com.zadatak.shopservice.dto.BestSellerDto;
import com.zadatak.shopservice.dto.BookDto;
import com.zadatak.shopservice.service.BasketService;
import com.zadatak.shopservice.service.PurchaseOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/shop")
public class ShopController {
    final BookServiceApi bookServiceApi;
    final PurchaseOrderService purchaseOrderService;
    final BasketService basketService;


    @Autowired
    public ShopController(BookServiceApi bookServiceApi, PurchaseOrderService purchaseOrderService, BasketService basketService) {
        this.bookServiceApi = bookServiceApi;
        this.purchaseOrderService = purchaseOrderService;
        this.basketService = basketService;
    }

    @GetMapping(value = "/top10")
    public ResponseEntity<List<BestSellerDto>> getTop10() {
        ResponseEntity<List<BookDto>> all = bookServiceApi.getAll();
        List<BestSellerDto> top10 = purchaseOrderService.getTop10(all.getBody());
        return new ResponseEntity<>(top10, HttpStatus.OK);
    }

    @GetMapping(value = "/allGenres")
    public ResponseEntity<List<String>> allGenres() {
        ResponseEntity<List<BookDto>> all = bookServiceApi.getAll();
        List<String> genres = purchaseOrderService.allGenres(all.getBody());
        return new ResponseEntity<>(genres, HttpStatus.OK);
    }

    @GetMapping(value = "/search/{title}/{author}")
    public ResponseEntity<List<BookDto>> searchTitleAuthor(@PathVariable("title") String title, @PathVariable("author") String author) {
        ResponseEntity<List<BookDto>> all = bookServiceApi.getAll();
        List<BookDto> found = purchaseOrderService.searchTitleAuthor(all.getBody(), title, author);
        return new ResponseEntity<>(found, HttpStatus.OK);
    }

    @GetMapping(value = "/search/{genre}")
    public ResponseEntity<List<BookDto>> searchGenre(@PathVariable("genre") String genre) {
        ResponseEntity<List<BookDto>> all = bookServiceApi.getAll();
        List<BookDto> found = purchaseOrderService.searchGenre(all.getBody(), genre);
        return new ResponseEntity<>(found, HttpStatus.OK);
    }

    @DeleteMapping(value = "basket/{id}")
    public ResponseEntity deleteBasket(@PathVariable("id") Long id) {
        basketService.deleteBasket(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @PutMapping(value = "remove-book-from-basket/{basket-id}/{book-id}")
    public ResponseEntity deleteFromBasket(@PathVariable("basket-id") Long basketId, @PathVariable("book-id") Long bookId) {
        boolean res = basketService.removeFromBasket(basketId, bookId);
        if (!res) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @PutMapping(value = "new-quantity-basket/{basket-id}/{book-id}/{quantity}")
    public ResponseEntity newQuantity(@PathVariable("basket-id") Long basketId, @PathVariable("book-id") Long bookId, @PathVariable("quantity") Long quantity) {
        ResponseEntity<BookDto> byId = bookServiceApi.getById(bookId);
        BookDto book = byId.getBody();
        if (book == null) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        // not enough books for shopping
        if (book.getQuantity() < quantity) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        boolean res = basketService.changeQuantity(basketId, bookId, quantity);
        if (!res) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping(value = "purchase-order/{basket-id}/{username}")
    public ResponseEntity purchaseOrder(@PathVariable("basket-id") Long basketId, @PathVariable("username") String username) {
        boolean res = purchaseOrderService.purchaseOrder(basketId, username);
        if (!res) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(HttpStatus.OK);
    }

}
