package com.zumbre.scoringservice.domain.rules;

import com.zumbre.scoringservice.domain.AccountDetails;
import com.zumbre.scoringservice.domain.Transaction;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Date;

public class SevenDaysRule {
    private Transaction transaction;
    private BigDecimal ammountLimit;

    public SevenDaysRule(Transaction transaction, BigDecimal ammountLimit){
        this.transaction = transaction;
        this.ammountLimit = ammountLimit;
    }

    public boolean validateSevenDaysRule(){
        AccountDetails account = this.transaction.account();
        Date accountCreationDate = account.createdAt();
        Instant now = Instant.now();
        Duration age = Duration.between(accountCreationDate.toInstant(), now);
        if(age.compareTo(Duration.ofDays(7)) > 0 && this.transaction.ammount().compareTo(this.ammountLimit) > 0){
            return true;
        }

        return false;

    }
}
