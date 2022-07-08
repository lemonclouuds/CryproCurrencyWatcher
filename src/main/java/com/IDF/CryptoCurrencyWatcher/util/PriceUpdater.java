package com.IDF.CryptoCurrencyWatcher.util;

import com.IDF.CryptoCurrencyWatcher.service.CryptocurrencyService;
import com.IDF.CryptoCurrencyWatcher.service.SubscriptionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RequiredArgsConstructor
@Slf4j
@EnableAsync
@EnableScheduling
public class PriceUpdater {
    private final SubscriptionService subscriptionService;
    private final CryptocurrencyService cryptocurrencyService;

    private final String COINLORE_URL = "https://api.coinlore.net/api/ticker/?id=%d";

    public void checkPriceChange() {
        subscriptionService.findAll()
                .forEach(subscription -> {
                    double percent;
                    percent = Math.abs((subscription.getCryptocurrency().getPrice() - subscription.getPreviousPrice())
                            / subscription.getPreviousPrice() * 100);
                    if (percent > 1) {
                        log.warn(String.format("\nUser: %s\n Ticker: %s\n Change: %f%%",
                                subscription.getUsername(), subscription.getCryptocurrency().getSymbol(), percent));
                    }
                });
    }

    public void updatePrices() {
        cryptocurrencyService.findAll()
                .forEach(cryptocurrency -> {
                    try {
                        JSONObject jsonObject = JsonParser.readJsonFromUrl(
                                String.format(COINLORE_URL, cryptocurrency.getId()));
                        cryptocurrency.setPrice(Double.parseDouble(jsonObject.get("price_usd").toString()));
                        cryptocurrencyService.update(cryptocurrency);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
    }

    @Async
    @Scheduled(fixedRate = 60000)
    public void update() {
        updatePrices();
        checkPriceChange();
    }
}
