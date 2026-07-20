package com.zumbre.scoringservice.infrastructure.kafka;

import java.time.Instant;
import java.util.UUID;

public record TransactionReceivedEvent(UUID eventId, String eventType, int eventVersion, Instant occurredAt, String producer, TransactionData data) {}
