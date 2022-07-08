package com.IDF.CryptoCurrencyWatcher.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "cryptocurrencies")
public class Cryptocurrency {

    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "symbol", nullable = false)
    private String symbol;

    @Column(name = "price", nullable = false)
    private Double price;
}
