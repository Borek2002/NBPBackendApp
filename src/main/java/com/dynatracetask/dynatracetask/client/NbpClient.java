package com.dynatracetask.dynatracetask.client;

import com.dynatracetask.dynatracetask.nbp.ExchangeRateTask12;
import com.dynatracetask.dynatracetask.nbp.ExchangeRateTask3;
import com.dynatracetask.dynatracetask.nbp.rates.RatesTask12;
import com.dynatracetask.dynatracetask.nbp.rates.RatesTask3;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.List;

@Component
@RequiredArgsConstructor
public class NbpClient {
    private static final String NBP_URL = "http://api.nbp.pl/api/exchangerates/";
    private static final Logger LOGGER = LoggerFactory.getLogger(NbpClient.class);
    private final RestTemplate restTemplate;

    public ExchangeRateTask12 getAverageExchangeRate(String currencyCode, String date) {
        String url = NBP_URL + "rates/a/" + currencyCode + "/" + date + "/?format=json";
        try {
            HttpHeaders headers = new HttpHeaders();
            HttpEntity<String> entity = new HttpEntity<>(headers);
            ResponseEntity<ExchangeRateTask12> response = restTemplate.exchange(url, HttpMethod.GET, entity, ExchangeRateTask12.class);
            return response.getBody();
        } catch (RestClientException e) {
            LOGGER.error(e.getMessage(), e);
            return null;
        }
    }

    public ExchangeRateTask12 getMaxMinAverageValue(String currencyCode, String n) {
        String url = NBP_URL + "rates/a/" + currencyCode + "/last/" + n + "/?format=json";
        try {
            HttpHeaders headers = new HttpHeaders();
            HttpEntity<String> entity = new HttpEntity<>(headers);
            ResponseEntity<ExchangeRateTask12> response = restTemplate.exchange(url, HttpMethod.GET, entity, ExchangeRateTask12.class);
            response.getBody().setRates(setMinMaxValue(response.getBody().getRates()));
            return response.getBody();
        } catch (RestClientException e) {
            LOGGER.error(e.getMessage(), e);
            return null;
        }
    }

    public ExchangeRateTask3 getMajorDifference(String currencyCode, String n) {
        String url = NBP_URL + "rates/c/" + currencyCode + "/last/" + n + "/?format=json";
        try {
            HttpHeaders headers = new HttpHeaders();
            HttpEntity<String> entity = new HttpEntity<>(headers);
            ResponseEntity<ExchangeRateTask3> response = restTemplate.exchange(url, HttpMethod.GET, entity, ExchangeRateTask3.class);
            response.getBody().setRates(setMajorDifference(response.getBody().getRates()));
            return response.getBody();
        } catch (RestClientException e) {
            LOGGER.error(e.getMessage(), e);
            return null;
        }
    }

    private List<RatesTask12> setMinMaxValue(List<RatesTask12> rates) {
        RatesTask12 minValue = rates.get(0);
        RatesTask12 maxValue = rates.get(0);
        for (RatesTask12 rate : rates) {
            if (rate.getMid().compareTo(maxValue.getMid()) == 1) {
                maxValue = rate;
            }
            if (rate.getMid().compareTo(minValue.getMid()) == -1) {
                minValue = rate;
            }
        }
        rates.removeAll(rates);
        rates.add(minValue);
        rates.add(maxValue);
        return rates;
    }

    private List<RatesTask3> setMajorDifference(List<RatesTask3> rates) {
        BigDecimal majorDifferenceValue = rates.get(0).getAsk().subtract(rates.get(0).getBid());
        RatesTask3 majorDifference = rates.get(0);
        for (RatesTask3 rate : rates) {
            BigDecimal difference = rate.getAsk().subtract(rate.getBid());
            if (difference.compareTo(majorDifferenceValue) == 1) {
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