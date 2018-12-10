# Event Driven GiftCard Application

This project demonstrate the use of [Event Sourcing](https://martinfowler.com/eaaDev/EventSourcing.html) and [CQRS](https://martinfowler.com/bliki/CQRS.html) in the context of a [Microservices](https://martinfowler.com/articles/microservices.html)-based system. 

This application composed by two services: Command and Query. 

- [Issue/Redeem a Giftcard - Command Service]
- [View the Cards - Query Service]

This application is also resilient, even when the query service is down but still you can save the state and once the query service is up and running, it pulls all the data from the event store. 

# Tech Stack

- Java 8
- Spring (Boot, Data)
- [Axon](http://axonframework.org)
- [Axon Server](https://axoniq.io/download)

# Running

Ensure you have a recent version of Java, Maven, and Axon server 4.x version.

Download the axon server, and run the below command 
$ java -jar axonserver-4.0.jar

```
$ cd giftcard-es-axon-microservices
$ mvn clean install
$ mvn -Dserver.port=8081 spring-boot:run -Pcommand
$ cd giftcard-es-axon-microservices
$ mvn -Dserver.port=8082 spring-boot:run -Pquery
```

```
$ Issue GiftCard :
localhost:8081/card/1/issue/100/command
$ Redeem GiftCard :
localhost:8081/card/1/redeem/50/command
$ List all the cards
localhost:8082/cards
```

## Simulate resiliency 

```
Stop the Query Service server

$ Issue GiftCard without query service
localhost:8081/card/2/issue/100/command

Now Start the query service
$ mvn -Dserver.port=8082 spring-boot:run -Pquery

$ List all the cards ( Now you will find the updated data)
localhost:8082/cards

```

## To run in Monolithic
```
[Note: This project can also be run as Monolithic application without Axon Server, 
Just exclude this dependency "axon-server-connector" from "axon-spring-boot-starter"]

$ cd giftcard-es-axon-microservices
$ mvn clean install
$ mvn -Dserver.port=8080 spring-boot:run
```
