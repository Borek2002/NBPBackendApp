package com.dynatracetask.dynatracetask.nbp.rates;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class RatesTask3 {

    private String effectiveDate;
    private BigDecimal bid;
    private BigDecimal ask;
    private BigDecimal majorDifference;
}