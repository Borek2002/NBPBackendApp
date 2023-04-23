package com.dynatracetask.dynatracetask.client;

import com.dynatracetask.dynatracetask.nbp.ExchangeRate;
import com.dynatracetask.dynatracetask.nbp.rates.Rates;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
@RequiredArgsConstructor
public class NbpClient {
    private static final String NBP_URL = "http://api.nbp.pl/api/exchangerates/";

    private final RestTemplate restTemplate;

    public ExchangeRate getAverageExchangeRate(String currencyCode, String date) {
        String url = NBP_URL + "rates/a/" + currencyCode + "/" + date + "/?format=json";
        try {
            HttpHeaders headers = new HttpHeaders();
            HttpEntity<String> entity = new HttpEntity<String>(headers);
            ResponseEntity<ExchangeRate> response = restTemplate.exchange(url, HttpMethod.GET, entity,
                    ExchangeRate.class);
            return response.getBody();
        } catch (RestClientException e) {

        }
        return null;
    }

    public ExchangeRate getMaxMinAverageValue(String currencyCode, String n) {
        String url = NBP_URL + "rates/a/" + currencyCode + "/last/" + n + "/?format=json";
        try {
            HttpHeaders headers = new HttpHeaders();
            HttpEntity<String> entity = new HttpEntity<String>(headers);
            ResponseEntity<ExchangeRate> response = restTemplate.exchange(url, HttpMethod.GET, entity,
                    ExchangeRate.class);
            response.getBody().setRates(setMinMaxValue(response.getBody().getRates()));
            return response.getBody();
        } catch (RestClientException e) {

        }
        return null;
    }


    public ExchangeRate getMajorDifference(String currencyCode, String n) {
        String url = NBP_URL + "rates/c/" + currencyCode + "/last/" + n + "/?format=json";
        try {
            HttpHeaders headers = new HttpHeaders();
            HttpEntity<String> entity = new HttpEntity<>(headers);
            ResponseEntity<ExchangeRate> response = restTemplate.exchange(url, HttpMethod.GET, entity,
                    ExchangeRate.class);
            response.getBody().setRates(setMajorDifference(response.getBody().getRates()));
            return response.getBody();
        } catch (RestClientException e) {

        }
        return null;
    }

    private List<Rates> setMinMaxValue(List<Rates> rates) {
        Rates minValue = rates.get(0);
        Rates maxValue = rates.get(0);
        for (Rates rate : rates) {
            if (rate.getMid() > maxValue.getMid()) {
                maxValue = rate;
            }
            if (rate.getMid() < minValue.getMid()) {
                minValue = rate;
            }
        }
        rates.removeAll(rates);
        rates.add(minValue);
        rates.add(maxValue);
        return rates;
    }

    private List<Rates> setMajorDifference(List<Rates> rates) {
        double majorDifferenceValue = rates.get(0).getAsk() - rates.get(0).getBid();
        Rates majorDifference = rates.get(0);
        for (Rates rate : rates) {
            double difference = rate.getAsk() - rate.getBid();
            if (difference > majorDifferenceValue) {
                majorDifferenceValue = difference;
                majorDifference = rate;
            }
        }
        rates.removeAll(rates);
        majorDifference.setMajorDifference(majorDifferenceValue);
        rates.add(majorDifference);
        return rates;
    }
}