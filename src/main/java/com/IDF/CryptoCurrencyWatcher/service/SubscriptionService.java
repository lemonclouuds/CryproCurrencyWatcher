package com.IDF.CryptoCurrencyWatcher.service;

import com.IDF.CryptoCurrencyWatcher.domain.Subscription;
import com.IDF.CryptoCurrencyWatcher.repository.SubscriptionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SubscriptionService {
    private final SubscriptionRepository subscriptionRepository;

    public Subscription save(Subscription subscription) {
        subscription.setPreviousPrice(subscription.getCryptocurrency().getPrice());
        return subscriptionRepository.save(subscription);
    }

    public List<Subscription> findAll() {
        return subscriptionRepository.findAll();
    }
}
