package com.zadatak.bookservice.service;

import com.zadatak.bookservice.dto.BookDto;
import com.zadatak.bookservice.model.Book;
import com.zadatak.bookservice.repository.BookRepository;
import com.zadatak.bookservice.transformer.BookTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final BookTransformer bookTransformer;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository, BookTransformer bookTransformer) {
        this.bookRepository = bookRepository;
        this.bookTransformer = bookTransformer;
    }


    @Override
    public List<BookDto> getAll() {
        List<Book> allBooks = bookRepository.findAll();
        List<BookDto> toReturn = new ArrayList<>();
        for (Book b : allBooks) {
            if (!b.isDeleted()) {
                toReturn.add(bookTransformer.transformBookToBookDTO(b));
            }
        }
        return toReturn;
    }

    @Override
    public BookDto getById(Long id) {
        Optional<Book> bookOpt = bookRepository.findById(id);
        if (!bookOpt.isPresent()) {
            return null;
        }
        Book book = bookOpt.get();
        if (book.isDeleted()) {
            return null;
        }
        return bookTransformer.transformBookToBookDTO(book);
    }

    @Override
    public BookDto save(BookDto bookDto) {
        return bookTransformer.transformBookToBookDTO(bookRepository.save(bookTransformer.transformBookDTOtoBook(bookDto)));
    }

    @Override
    public BookDto update(Long id, BookDto bookDto) {
        Optional<Book> bookOpt = bookRepository.findById(id);
        if (!bookOpt.isPresent()) {
            return null;
        }
        Book book = bookOpt.get();
        book.setAuthor(bookDto.getAuthor());
        book.setGenre(bookDto.getGenre());
        book.setQuantity(bookDto.getQuantity());
        book.setPrice(bookDto.getPrice());
        book.setDeleted(bookDto.isDeleted());
        book.setTitle(bookDto.getTitle());
        return bookTransformer.transformBookToBookDTO(bookRepository.save(book));
    }

    @Override
    public void delete(Long id) {
        bookRepository.deleteById(id);
    }
}
