package com.IDF.CryptoCurrencyWatcher.service;

import com.IDF.CryptoCurrencyWatcher.domain.Cryptocurrency;
import com.IDF.CryptoCurrencyWatcher.repository.CryptocurrencyRepository;
import com.IDF.CryptoCurrencyWatcher.util.JsonParser;
import lombok.RequiredArgsConstructor;
import org.json.JSONObject;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
@EnableAsync
@EnableScheduling
public class CryptocurrencyService {

    private final String COINLORE_URL = "https://api.coinlore.net/api/ticker/?id=%d";

    private final CryptocurrencyRepository cryptocurrencyRepository;

    public List<Cryptocurrency> findAll() {
        return cryptocurrencyRepository.findAll();
    }

    public Cryptocurrency findById(Long id) {
        return cryptocurrencyRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(String.format("Cryptocurrency[%d] not found", id))
        );
    }

    public Cryptocurrency findBySymbol(String symbol) {
        return cryptocurrencyRepository.findBySymbol(symbol).orElseThrow(
                () -> new EntityNotFoundException(String.format("Cryptocurrency[%s] not found", symbol))
        );
    }

    public Cryptocurrency update(Cryptocurrency cryptocurrency) {
        return cryptocurrencyRepository.save(cryptocurrency);
    }

    @Async
    @Scheduled(fixedRate = 6000)
    public void updatePrices() {
        findAll()
                .forEach(cryptocurrency -> {
                    try {
                        JSONObject jsonObject = JsonParser.readJsonFromUrl(
                                String.format(COINLORE_URL, cryptocurrency.getId()));
                        cryptocurrency.setPrice(Double.parseDouble(jsonObject.get("price_usd").toString()));
                        update(cryptocurrency);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
    }
}
