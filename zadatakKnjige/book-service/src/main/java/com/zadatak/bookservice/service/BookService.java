package com.zadatak.bookservice.service;

import com.zadatak.bookservice.dto.BookDto;
import com.zadatak.bookservice.model.Book;

import java.util.List;

public interface BookService {
    List<BookDto> getAll();

    BookDto getById(Long id);

    BookDto save(BookDto bookDto);

    BookDto update(Long id, BookDto bookDto);

    void delete(Long id);
}
