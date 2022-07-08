package com.IDF.CryptoCurrencyWatcher.controller;

import com.IDF.CryptoCurrencyWatcher.domain.Cryptocurrency;
import com.IDF.CryptoCurrencyWatcher.service.CryptocurrencyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CryptocurrencyController {

    private final CryptocurrencyService cryptocurrencyService;

    @GetMapping("/all")
    public ResponseEntity<List<Cryptocurrency>> findAll() {
        return ResponseEntity.ok(cryptocurrencyService.findAll());
    }

    @GetMapping("/cryptocurrency/{id}")
    public ResponseEntity<Cryptocurrency> findById(@PathVariable Long id) {
        return ResponseEntity.ok(cryptocurrencyService.findById(id));
    }
}
