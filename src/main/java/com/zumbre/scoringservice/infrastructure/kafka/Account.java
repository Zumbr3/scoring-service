package com.zumbre.scoringservice.infrastructure.kafka;

import java.time.Instant;

public record Account(String Name, String accountNumber, Instant createdAt) {}
