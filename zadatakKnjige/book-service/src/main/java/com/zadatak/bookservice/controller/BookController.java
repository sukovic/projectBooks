package com.zadatak.bookservice.controller;

import com.zadatak.bookservice.dto.BookDto;
import com.zadatak.bookservice.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
// http://localhost:8080/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config#/book-controller
@RestController
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public ResponseEntity<List<BookDto>> getAll() {
        return new ResponseEntity<>(bookService.getAll(), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<BookDto> getById(@PathVariable("id") Long id) {
        BookDto bookDto = bookService.getById(id);
        if (bookDto == null) {
            return new ResponseEntity<>(bookDto, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(bookDto, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<BookDto> save(@RequestBody BookDto bookDto) {
        return new ResponseEntity<>(bookService.save(bookDto), HttpStatus.CREATED);
    }


    @PutMapping(value = "/{id}")
    public ResponseEntity<BookDto> update(@PathVariable("id") Long id, @RequestBody BookDto bookDto) {
        BookDto updated = bookService.update(id, bookDto);
        if (updated == null) {
            return new ResponseEntity<>(updated, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(updated, HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity delete(@PathVariable("id") Long id) {
        bookService.delete(id);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
