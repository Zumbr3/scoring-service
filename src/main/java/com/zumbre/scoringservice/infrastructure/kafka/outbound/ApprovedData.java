package com.zumbre.scoringservice.infrastructure.kafka.outbound;

import java.math.BigDecimal;
import java.util.UUID;

public record ApprovedData(UUID transactionId, BigDecimal score) {}
