# CryproCurrencyWatcher

This is a simple REST service to view cryptocurrency quotes.

Tech stack:
* Java 17
* Spring Boot, MVC, Data JPA
* Hibernate
* Lombok
* PostgreSQL
* fasterxml.jackson, JSON
* Maven

Currencies are predetermined (BTC, ETH, SOL). Data is parsed from CoinLore.

This is a RESTful service, endpoints are:

`http://localhost:8080/all`
Returns a list of all cryptocurrencies, contains id and current price.

`http://localhost:8080/currency?id=48543`
Returns info about cryptocurrency with chosen id.

`http://localhost:8080/notify?username=anya&symbol=BTC`
Registrates user to be notified about the price change of a certain currency.
