package com.zumbre.scoringservice.domain.rules;

import com.zumbre.scoringservice.domain.AccountDetails;
import com.zumbre.scoringservice.domain.Transaction;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.Instant;

public class NewAccountBigAmountRule {
  private final BigDecimal amountLimit;
  private final int daysForRule;

  public NewAccountBigAmountRule(BigDecimal amountLimit, int daysForRule) {
    this.amountLimit = amountLimit;
    this.daysForRule = daysForRule;
  }

  public boolean evaluate(Transaction transaction) {
    AccountDetails account = transaction.account();
    Instant accountCreationDate = account.createdAt();
    Instant transactionOccurredAt = transaction.transactionDate();

    Duration age = Duration.between(accountCreationDate, transactionOccurredAt);

    return age.compareTo(Duration.ofDays(this.daysForRule)) < 0
        && transaction.amount().compareTo(this.amountLimit) > 0;
  }
}
