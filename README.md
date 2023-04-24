# NBPBackendApp

## Description

A simple runnable local server project, that exposes some endpoints which take arguments and return plain simple data after performing certain internal operations. The goal is to query data from the Narodowy Bank Polski's public APIs and return relevant information from them.

## Launching server
To start the server, you should open terminal, go to this project folder and run this command:
```bash
mvn spring-boot:run
```

## Usage
We can run 3 operations:
- Average exchange rate for the day
```bash
 curl http://localhost:8080/exchanges/average/{currencyCode}/{YYYY-MM-DD}
```
- Min and max average value for the last N quotations 
  (first rate in output is min Value and the second is max value)
```bash
 curl http://localhost:8080/exchanges/maxmin/{currencyCode}/{N}
```
- The major difference between the buy and ask rate for the last N quotations
```bash
 curl http://localhost:8080/exchanges/majordifference/{currencyCode}/{N}  
```
The result of these operations is in JSON format

## Examples
```bash
 curl http://localhost:8080/exchanges/average/eur/2023-01-12
```
Output:
![image](https://user-images.githubusercontent.com/105941239/233969942-36225737-ffb4-4838-8db2-c696fbe24ca8.png)

```bash
 curl http://localhost:8080/exchanges/maxmin/eur/255
```
![image](https://user-images.githubusercontent.com/105941239/233969804-d1bb20b4-8d7e-4cf8-9556-ab1ed44f876b.png)

```bash
 curl http://localhost:8080/exchanges/majordifference/eur/4
```
![image](https://user-images.githubusercontent.com/105941239/233970445-dfe659c8-8e70-4925-b5b4-f1c23b3c2ba7.png)
