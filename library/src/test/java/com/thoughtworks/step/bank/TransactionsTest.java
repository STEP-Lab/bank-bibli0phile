package com.thoughtworks.step.bank;

import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;

import static org.hamcrest.core.IsCollectionContaining.hasItem;
import static org.hamcrest.core.IsCollectionContaining.hasItems;
import static org.junit.Assert.assertThat;

public class TransactionsTest {
  Transactions transactions;
  @Before
  public void setUp() throws Exception {
    transactions = new Transactions();
  }
  
  @Test
  public void checkTransaction() {
    transactions.debit(1000,new AccountNumber("1234-5678"));
    Transaction debitTransaction  = new DebitTransaction(new Date(),1000,new AccountNumber("1234-5678"));
    transactions.credit(1000,new AccountNumber("1234-1234"));
    Transaction creditTransaction = new CreditTransaction(new Date(),1000,new AccountNumber("1234-1234"));
    assertThat(transactions.getAllTransactions(),hasItems(debitTransaction,creditTransaction));
  }
  
  @Test
  public void printTransactions() throws FileNotFoundException, UnsupportedEncodingException {
    ArrayList<String> result = new ArrayList<>();
    transactions.credit(1000,new AccountNumber("1234-1234"));
    CreditTransaction creditTransaction = new CreditTransaction(1000, new AccountNumber("1234-1234"));
    PrintWriter printWriter = new PrintWriter("transactions.txt", "UTF-8"){
      @Override
      public void println(String x) {
        result.add(x);
        System.out.println(x);
      }
    };
    transactions.print(printWriter);
    printWriter.close();
    assertThat(result,hasItem(creditTransaction.toString()));
  }
  
  @Test
  public void filterTransactionAmount() {
    transactions.credit(1000,new AccountNumber("1234-1234"));
    transactions.credit(1500,new AccountNumber("1234-1234"));
    transactions.credit(500,new AccountNumber("1234-1234"));
    Transactions filterTransactions = this.transactions.filterByAmountGreaterThan(1000);
    assertThat(filterTransactions.getAllTransactions(),hasItems(new CreditTransaction(1000,new AccountNumber("1234-1234")),new CreditTransaction(1500,new AccountNumber("1234-1234"))));
  }
}
