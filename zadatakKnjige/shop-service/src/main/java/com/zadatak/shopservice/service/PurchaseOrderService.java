package com.zadatak.shopservice.service;

import com.zadatak.shopservice.dto.BestSellerDto;
import com.zadatak.shopservice.dto.BookDto;

import java.util.List;

public interface PurchaseOrderService {
    List<BestSellerDto> getTop10(List<BookDto> allBooks);

    List<String> allGenres(List<BookDto> allBooks);

    List<BookDto> searchTitleAuthor(List<BookDto> allBooks, String title, String author);

    List<BookDto> searchGenre(List<BookDto> allBooks, String genre);

    boolean purchaseOrder(Long basketId, String username);
}
