package com.IDF.CryptoCurrencyWatcher.controller;

import com.IDF.CryptoCurrencyWatcher.domain.Cryptocurrency;
import com.IDF.CryptoCurrencyWatcher.domain.Subscription;
import com.IDF.CryptoCurrencyWatcher.service.CryptocurrencyService;
import com.IDF.CryptoCurrencyWatcher.service.SubscriptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CryptocurrencyController {

    private final CryptocurrencyService cryptocurrencyService;
    private final SubscriptionService subscriptionService;

    @GetMapping("/all")
    public ResponseEntity<List<Cryptocurrency>> findAll() {
        return ResponseEntity.ok(cryptocurrencyService.findAll());
    }

    @GetMapping("/currency")
    public ResponseEntity<Cryptocurrency> findById(@RequestParam Long id) {
        return ResponseEntity.ok(cryptocurrencyService.findById(id));
    }

    @GetMapping("/notify")
    public ResponseEntity<?> notify(@RequestParam String username, @RequestParam String symbol) {
        Cryptocurrency found = cryptocurrencyService.findBySymbol(symbol);
        subscriptionService.save(new Subscription(username, found.getPrice(), found));
        return ResponseEntity.ok("Subscription submitted");
    }



}
