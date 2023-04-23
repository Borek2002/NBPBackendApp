package com.dynatracetask.dynatracetask.nbp;

import com.dynatracetask.dynatracetask.nbp.rates.RatesTask3;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ExchangeRateTask3 {

    private String currency;
    private String code;
    private List<RatesTask3> rates;
}