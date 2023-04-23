package com.dynatracetask.dynatracetask.nbp;

import com.dynatracetask.dynatracetask.nbp.rates.Rates;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ExchangeRate {
    private String currency;
    private String code;
    private List<Rates> rates;

}
