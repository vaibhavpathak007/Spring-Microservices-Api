# Microservices with Spring Boot and Spring Cloud


## Ports

|     Application       |     Port          |
| ------------- | ------------- |
| Limits Service | 8080, 8081, ... |
| Spring Cloud Config Server | 8888 |
|  |  |
| Currency Exchange Service | 8000, 8001, 8002, ..  |
| Currency Conversion Service | 8100, 8101, 8102, ... |
| Netflix Eureka Naming Server | 8761 |
| Netflix Zuul API Gateway Server | 8765 |
| Zipkin Distributed Tracing Server | 9411 |


## URLs

|     Application       |     URL          |
| ------------- | ------------- |
| Limits Service | http://localhost:8080/limits POST -> http://localhost:8080/actuator/refresh|
|Spring Cloud Config Server| http://localhost:8888/limits-service/default http://localhost:8888/limits-service/dev |
|  Currency Converter Service - Direct Call| http://localhost:8100/currency-converter/from/USD/to/INR/quantity/10|
|  Currency Converter Service - Feign| http://localhost:8100/currency-converter-feign/from/EUR/to/INR/quantity/10000|
| Currency Exchange Service | http://localhost:8000/currency-exchange/from/EUR/to/INR http://localhost:8001/currency-exchange/from/USD/to/INR|
| Eureka | http://localhost:8761/|
| Zuul - Currency Exchange & Exchange Services | http://localhost:8765/currency-exchange-service/currency-exchange/from/EUR/to/INR http://localhost:8765/currency-conversion-service/currency-converter-feign/from/USD/to/INR/quantity/10|
| Zipkin | http://localhost:9411/zipkin/ |
| Spring Cloud Bus Refresh | http://localhost:8080/bus/refresh |

## Zipkin Installation

Before Rabbit MQ install Erlang
- https://www.erlang.org/downloads

Then download and install Rabbit MQ for Windows
- https://www.rabbitmq.com/install-windows.html 

It automatially starts RabbitMQ in background (we can stop it from start menue)


Downloading Zipkin Server Jar 
- https://search.maven.org/remote_content?g=io.zipkin.java&a=zipkin-server&v=LATEST&c=exec

Running zipkin server alone (To check installation is done or not)
```
java -jar zipkin-serve.jar
```

For Running Rabbit-MQ with zipkin server run these two diffrent command.
```
SET RABBIT_URI=amqp://localhost
java -jar zipkin-serve.jar
```

## VM Argument

-Dserver.port=8001

## Starting Application Order: 
1) Naming Server
2) Zipkin Server
3) Currency Exchange Service
4) Currency Conversion Service
5) Zuul API Gateway