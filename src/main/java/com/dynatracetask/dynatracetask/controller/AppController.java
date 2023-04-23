package com.dynatracetask.dynatracetask.controller;


import com.dynatracetask.dynatracetask.client.NbpClient;
import com.dynatracetask.dynatracetask.nbp.ExchangeRate;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/exchanges")
@RequiredArgsConstructor
public class AppController {

    private final NbpClient nbpClient;

    @RequestMapping(value = "average/{currencyCode}/{date}")
    public ExchangeRate getAverageExchangeRate(@PathVariable(value = "currencyCode") String currencyCode,
                                               @PathVariable(value = "date") String date) {
        return nbpClient.getAverageExchangeRate(currencyCode, date);
    }

    @RequestMapping(value = "/majordifference/{currencyCode}/{N}")
    public ExchangeRate getMajorDifference(@PathVariable(value = "currencyCode") String currencyCode,
                                                  @PathVariable(value = "N") String n) {
        return nbpClient.getMajorDifference(currencyCode, n);
    }

    @RequestMapping(value = "maxmin/{currencyCode}/{N}")
    public ExchangeRate getMaxMinAverageValue(@PathVariable(value = "currencyCode") String currencyCode,
                                                     @PathVariable(value = "N") String n) {
        return nbpClient.getMaxMinAverageValue(currencyCode, n);
    }

}

