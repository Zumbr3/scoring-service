package com.zumbre.scoringservice.infrastructure.kafka;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

public record Transaction(
    UUID transactionId,
    UUID accountId,
    CurrencyEnum currency,
    TransactionTypeEnum type,
    String counterpartyAccount,
    UUID deviceId,
    String ipAddress,
    GeoLocation geoLocation,
    Instant transactionDate,
    String channel,
    BigDecimal amount,
    Account accountDetails) {}
