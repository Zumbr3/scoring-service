package com.zumbre.scoringservice.domain;

import java.util.Date;

public record AccountDetails(String accountId, Date createdAt, String kycStatus, Date updatedAt) {}
