package com.IDF.CryptoCurrencyWatcher.service;

import com.IDF.CryptoCurrencyWatcher.domain.Cryptocurrency;
import com.IDF.CryptoCurrencyWatcher.repository.CryptocurrencyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CryptocurrencyService {

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
                () -> new EntityNotFoundException(String.format("Cryptocurrency \"%s\" not found", symbol))
        );
    }

    public Cryptocurrency update(Cryptocurrency cryptocurrency) {
        return cryptocurrencyRepository.save(cryptocurrency);
    }
}
