package com.thoughtworks.step.bank;

import org.junit.Test;

import java.util.Date;

import static org.hamcrest.core.IsCollectionContaining.hasItem;
import static org.junit.Assert.assertThat;

public class TransactionsTest {
  @Test
  public void checkTrasaction() {
    Transactions transactions = new Transactions();
    transactions.debit(1000,new AccountNumber("1234-5678"));
    Transaction debitTransaction  = new DebitTransaction(new Date(),1000,new AccountNumber("1234-5678"));
    assertThat(transactions.allTransactions,hasItem(debitTransaction));
    transactions.credit(1000,new AccountNumber("1234-1234"));
    Transaction creditTransaction = new CreditTransaction(new Date(),1000,new AccountNumber("1234-1234"));
    assertThat(transactions.allTransactions,hasItem(creditTransaction));
  }
}
