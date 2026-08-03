package com.zumbre.scoringservice.infrastructure.kafka.outbound;

import java.time.Instant;
import java.util.UUID;

public record TransactionApprovedEvent(
    UUID eventId,
    String eventType,
    int eventVersion,
    Instant occurredAt,
    String producer,
    ApprovedData data) {}
