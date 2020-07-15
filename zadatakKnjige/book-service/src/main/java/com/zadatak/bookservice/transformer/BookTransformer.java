package com.zadatak.bookservice.transformer;

import com.zadatak.bookservice.dto.BookDto;
import com.zadatak.bookservice.model.Book;
import org.springframework.stereotype.Component;

@Component
public class BookTransformer {

    public BookDto transformBookToBookDTO(Book book) {
        return new BookDto(
                book.getId(),
                book.getTitle(),
                book.getAuthor(),
                book.getGenre(),
                book.getPrice(),
                book.getQuantity(),
                book.isDeleted());
    }

    public Book transformBookDTOtoBook(BookDto bookDto) {
        return new Book(
                bookDto.getTitle(),
                bookDto.getAuthor(),
                bookDto.getGenre(),
                bookDto.getPrice(),
                bookDto.getQuantity(),
                bookDto.isDeleted());
    }
}
