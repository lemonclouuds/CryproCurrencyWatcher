package com.IDF.CryptoCurrencyWatcher.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "subscriptions")
public class Subscription {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "previous_price", nullable = false)
    private Double previousPrice;

    @ManyToOne
    private Cryptocurrency cryptocurrency;

    public Subscription(String username, Double previousPrice, Cryptocurrency cryptocurrency) {
        this.username = username;
        this.previousPrice = previousPrice;
        this.cryptocurrency = cryptocurrency;
    }
}
