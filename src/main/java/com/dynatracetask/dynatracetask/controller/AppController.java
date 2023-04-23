package com.dynatracetask.dynatracetask.controller;

import com.dynatracetask.dynatracetask.client.NbpClient;
import com.dynatracetask.dynatracetask.exception.ClientException;
import com.dynatracetask.dynatracetask.nbp.ExchangeRateTask12;
import com.dynatracetask.dynatracetask.nbp.ExchangeRateTask3;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/exchanges")
@RequiredArgsConstructor
public class AppController {

    private final NbpClient nbpClient;

    @RequestMapping(value = "average/{currencyCode}/{date}")
    public ExchangeRateTask12 getAverageExchangeRate(@PathVariable(value = "currencyCode") String currencyCode,
                                                     @PathVariable(value = "date") String date) {
        return nbpClient.getAverageExchangeRate(currencyCode, date);
    }

    @RequestMapping(value = "maxmin/{currencyCode}/{N}")
    public ExchangeRateTask12 getMaxMinAverageValue(@PathVariable(value = "currencyCode") String currencyCode,
                                                    @PathVariable(value = "N") String n) {
        return nbpClient.getMaxMinAverageValue(currencyCode, n);
    }

    @RequestMapping(value = "/majordifference/{currencyCode}/{N}")
    public ExchangeRateTask3 getMajorDifference(@PathVariable(value = "currencyCode") String currencyCode,
                                                @PathVariable(value = "N") String n) {
        ExchangeRateTask3 result = nbpClient.getMajorDifference(currencyCode, n);
        return nbpClient.getMajorDifference(currencyCode, n);
    }

    @ExceptionHandler
    public String handle(ClientException cl){
        return cl.getMessage();
    }
}

