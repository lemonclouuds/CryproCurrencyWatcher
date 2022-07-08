package com.IDF.CryptoCurrencyWatcher.repository;

import com.IDF.CryptoCurrencyWatcher.domain.Cryptocurrency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CryptocurrencyRepository extends JpaRepository<Cryptocurrency, Long> {
    Optional<Cryptocurrency> findBySymbol(String symbol);
}
