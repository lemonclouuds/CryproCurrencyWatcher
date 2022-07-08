package com.IDF.CryptoCurrencyWatcher.repository;

import com.IDF.CryptoCurrencyWatcher.domain.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {
    Optional<Subscription> findByUsernameAndCryptocurrency_Symbol(String username, String symbol);
}
