package com.zumbre.scoringservice.domain;

import java.math.BigDecimal;

public record Transaction(
    String id, GeoLocation geoLocation, AccountDetails account, BigDecimal ammount) {}
