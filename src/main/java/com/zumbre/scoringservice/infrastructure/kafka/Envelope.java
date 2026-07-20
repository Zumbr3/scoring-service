package com.zumbre.scoringservice.infrastructure.kafka;

import java.time.Instant;
import java.util.UUID;

public record Envelope(UUID eventId, String eventType, int eventVersion, Instant occuredAt, String producer, Transaction data) {}
