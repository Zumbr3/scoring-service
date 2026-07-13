package com.zumbre.scoringservice.domain;

import java.math.BigDecimal;
import java.util.Date;

public record Transaction(
        String id, GeoLocation geoLocation, AccountDetails account, BigDecimal ammount, Date occuredAt) {}
