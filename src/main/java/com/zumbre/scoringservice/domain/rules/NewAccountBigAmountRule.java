package com.zumbre.scoringservice.domain.rules;

import com.zumbre.scoringservice.domain.AccountDetails;
import com.zumbre.scoringservice.domain.Transaction;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.Instant;

public class NewAccountBigAmountRule {
  private Transaction transaction;
  private BigDecimal amountLimit;

  public NewAccountBigAmountRule(Transaction transaction, BigDecimal amountLimit) {
    this.transaction = transaction;
    this.amountLimit = amountLimit;
  }

  public boolean evaluateNewAccountRule() {
    AccountDetails account = this.transaction.account();
    Instant accountCreationDate = account.createdAt();
    Instant transactionOccurredAt = this.transaction.occuredAt();

    Duration age = Duration.between(accountCreationDate, transactionOccurredAt);

    return age.compareTo(Duration.ofDays(7)) < 0
        && this.transaction.amount().compareTo(this.amountLimit) > 0;
  }
}
