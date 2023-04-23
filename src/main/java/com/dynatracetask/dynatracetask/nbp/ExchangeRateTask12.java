package com.dynatracetask.dynatracetask.nbp;

import com.dynatracetask.dynatracetask.nbp.rates.RatesTask12;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ExchangeRateTask12 {

    private String currency;
    private String code;
    private List<RatesTask12> rates;
}
