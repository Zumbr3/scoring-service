package com.zumbre.scoringservice.domain;

import java.math.BigDecimal;
import java.time.Instant;

public record Transaction(
    String id,
    GeoLocation geoLocation,
    AccountDetails account,
    BigDecimal amount,
    Instant transactionDate ) {}
