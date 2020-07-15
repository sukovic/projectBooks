package com.zadatak.shopservice.controller;

import com.zadatak.shopservice.dto.BookDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TestController {
    final BookServiceApi bookServiceApi;

    @Autowired
    public TestController(BookServiceApi bookServiceApi) {
        this.bookServiceApi = bookServiceApi;
    }

    @GetMapping
    public ResponseEntity<List<BookDto>> test() {
        return bookServiceApi.getAll();
    }


}

/*
* login X
* registracija X
*
* getTop10 - prebrojim koliko se puta neka knjiga nalazi u porudzbenicama (saberem sve quantity-je) X
* getZanr - daj sve knjige i onda sve zanrove kao set X
* pretraga po naslovu i autoru X
* po zanru daj sve knjige X
* obrisi korpu X
* izbaciti knjigu iz korpe X
* promeniti kolicinu u korpi X
* placanje tako sto snimim porudzbenicu na osnovu korpe
* */
