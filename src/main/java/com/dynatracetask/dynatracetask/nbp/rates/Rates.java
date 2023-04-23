package com.dynatracetask.dynatracetask.nbp.rates;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Rates {
    private double mid;
    private String effectiveDate;

    private double bid;
    private double ask;
    private double majorDifference;
}
