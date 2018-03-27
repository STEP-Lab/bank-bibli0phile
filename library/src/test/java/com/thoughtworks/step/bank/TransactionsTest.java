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
  public void setUp(){
    transactions = new Transactions();
  }
  
  @Test
  public void checkTransaction() {
    transactions.debit(1000,new AccountNumber("1234-5678"),"debit",1000);
    Transaction debitTransaction  = new DebitTransaction(new Date(),1000,new AccountNumber("1234-5678"),"debit",1000);
    transactions.credit(1000,new AccountNumber("1234-1234"),"credit",1000);
    Transaction creditTransaction = new CreditTransaction(new Date(),1000,new AccountNumber("1234-1234"),"credit",1000);
    assertThat(transactions.getAllTransactions(),hasItems(debitTransaction,creditTransaction));
  }
  
  @Test
  public void printTransactions() throws FileNotFoundException, UnsupportedEncodingException {
    ArrayList<String> result = new ArrayList<>();
    transactions.credit(1000,new AccountNumber("1234-1234"),"credit",1000);
    CreditTransaction creditTransaction = new CreditTransaction(1000, new AccountNumber("1234-1234"),"credit",1000);
    PrintWriter printWriter = new PrintWriter("transactions.txt", "UTF-8"){
      @Override
      public void println(String x) {
        result.add(x);
      }
    };
    transactions.print(printWriter);
    printWriter.close();
    assertThat(result,hasItem(creditTransaction.toString()));
  }
  
  @Test
  public void filterTransactionAmount() {
    transactions.credit(1000,new AccountNumber("1234-1234"),"credit",1000);
    transactions.credit(1500,new AccountNumber("1234-1234"),"credit",2500);
    transactions.credit(500,new AccountNumber("1234-1234"),"credit",200);
    Transactions filterTransactions = this.transactions.filterByAmountGreaterThan(1000);
    assertThat(filterTransactions.getAllTransactions(),hasItems(new CreditTransaction(1000,new AccountNumber("1234-1234"),"credit",1000),new CreditTransaction(1500,new AccountNumber("1234-1234"),"credit",2500)));
  }
  
  @Test
  public void filterByAmountLesserThan() {
    transactions.credit(1000,new AccountNumber("1234-1234"),"credit",1000);
    transactions.credit(500,new AccountNumber("1234-1234"),"credit",1500);
    Transactions filterTransactions = this.transactions.filterByAmountLesserThan(1000);
    assertThat(filterTransactions.getAllTransactions(),hasItems(new CreditTransaction(500,new AccountNumber("1234-1234"),"credit",1500)));
  }
  
  @Test
  public void filterAllCreditTransactions()  {
    transactions.credit(1000,new AccountNumber("1234-1234"),"credit",1000);
    transactions.credit(1500,new AccountNumber("1234-1234"),"credit",2500);
    transactions.debit(500,new AccountNumber("1234-1234"),"debit",2000);
    Transactions filterTransactions = this.transactions.filterAllCreditTransactions();
    assertThat(filterTransactions.getAllTransactions(),hasItems(new CreditTransaction(1000,new AccountNumber("1234-1234"),"credit",1000),new CreditTransaction(1500,new AccountNumber("1234-1234"),"credit",2500)));
  }
  
  @Test
  public void filterAllDebitTransactions() {
    transactions.credit(1000,new AccountNumber("1234-1234"),"credit",1000);
    transactions.credit(1500,new AccountNumber("1234-1234"),"credit",1500);
    transactions.debit(500,new AccountNumber("1234-1234"),"debit",500);
    Transactions filterTransactions = this.transactions.filterAllDebitTransactions();
    assertThat(filterTransactions.getAllTransactions(),hasItems(new DebitTransaction(500,new AccountNumber("1234-1234"),"debit",500)));
  }
  
  @Test
  public void checkWriteInCSV() throws IOException {
    transactions.credit(1000,new AccountNumber("1234-1234"),"credit",1000);
    ArrayList<String> result = new ArrayList<>();
    String headers = "date,type,amount,source";
    CreditTransaction credit = new CreditTransaction(1000, new AccountNumber("1234-1234"), "credit",1000);
    CSVPrinter csvPrinter;
    try (FileWriter fileWriter = new FileWriter("foo.csv") {
      @Override
      public Writer append(CharSequence csq) {
        result.add((String) csq);
        return this;
      }
    }) {
      csvPrinter = new CSVPrinter(fileWriter, headers);
    }
    csvPrinter.writeHeaders();
    transactions.writeOnTransactions(csvPrinter);
    assertThat(result,hasItems(headers,String.valueOf(credit.getAmount()),credit.getType(),String.valueOf(credit.getBalance())));
  }
  
}
