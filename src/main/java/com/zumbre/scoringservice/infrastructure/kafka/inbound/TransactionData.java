package com.zumbre.scoringservice.infrastructure.kafka.inbound;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

public record TransactionData(
    UUID transactionId,
    UUID accountId,
    String currency,
    String type,
    String counterpartyAccount,
    UUID deviceId,
    String ipAddress,
    GeoLocationData geoLocation,
    Instant transactionDate,
    String channel,
    BigDecimal amount,
    AccountDetailsData accountDetails) {}
