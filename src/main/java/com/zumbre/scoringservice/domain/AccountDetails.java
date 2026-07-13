package com.zumbre.scoringservice.domain;

import java.time.Instant;

public record AccountDetails(String accountId, Instant createdAt, String kycStatus, Instant updatedAt) {}
