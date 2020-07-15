package com.zadatak.shopservice.controller;

import com.zadatak.shopservice.dto.BookDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "book-service", url = "http://localhost:8081/")
public interface BookServiceApi {

    @GetMapping("/books")
    public ResponseEntity<List<BookDto>> getAll();

    @GetMapping(value = "/books/{id}")
    public ResponseEntity<BookDto> getById(@PathVariable("id") Long id);
}
