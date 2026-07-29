package com.zumbre.scoringservice.infrastructure.kafka;

import java.time.Instant;

public record AccountDetailsData(String name, String account, Instant createdAt) {}
