package com.zumbre.scoringservice.domain.rules;

import com.zumbre.scoringservice.domain.AccountDetails;
import com.zumbre.scoringservice.domain.GeoLocation;
import com.zumbre.scoringservice.domain.Transaction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.temporal.ChronoUnit;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class NewAccountBigAmountRuleTest {
  private NewAccountBigAmountRule newAccountBigAmountRule;

  @BeforeEach
  void configure() {

    this.newAccountBigAmountRule = new NewAccountBigAmountRule(new BigDecimal("4000.00"), 7);
  }

  @Test
  void newAccountWithHighAmount_triggers () {
    Instant now = Instant.now();

    Instant lessFiveDays = now.minus(5, ChronoUnit.DAYS);
    GeoLocation geoLocation = new GeoLocation(1.1, 1.2);
    AccountDetails accountDetails = new AccountDetails("1", lessFiveDays, "1", now);
    Transaction transaction =
        new Transaction("1", geoLocation, accountDetails, new BigDecimal("5000.00"), now);

    boolean result = this.newAccountBigAmountRule.evaluate(transaction);

    assertTrue(result);
  }
}
