package com.thoughtworks.step.bank;

import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsCollectionContaining.hasItem;
import static org.junit.Assert.assertThat;

public class AccountTest {
  
  private Account account;
  
  @Before
  public void setUp() throws MinimumBalanceException{
    account = Account.createAccount("ketan",new AccountNumber("1234-4567"), 3000.00);
  
  }
  
  @Test
  public void checkBalance(){
    assertThat(account.getBalance(),is(3000.00));
  }
  
  @Test
  public void checkAccountHolder() {
    assertThat(account.getAccountHolder(),is("ketan"));
  }
  
  @Test (expected = MinimumBalanceException.class)
  public void checkMinimumBalance() throws MinimumBalanceException{
    Account.createAccount("ketan",new AccountNumber("1245-7865"),200);
  }
  
  @Test (expected = MinimumBalanceException.class)
  public void checkIfWithdrawIsAllowedIfBalanceIsGreaterThanMinimum() throws MinimumBalanceException {
    assertThat(account.debit(500),is(2500.0));
    account.debit(2000);
    assertThat(account.debit(500),is(2000.0));
  }
  
  @Test (expected = MinimumBalanceException.class)
  public void checkDepositBalance() throws MinimumBalanceException {
    assertThat(account.credit(1000),is(4000.0));
    account.credit(-1000);
  }
  
  @Test
  public void checkCreditTransaction() throws MinimumBalanceException {
    account.credit(10000);
    assertThat(account.getAllTransactions(),hasItem(new CreditTransaction(new Date(),10000,new AccountNumber("1234-4567"),"credit")));
  }
  
  @Test
  public void checkDebitTransaction() throws MinimumBalanceException {
    account.debit(1000);
    assertThat(account.getAllTransactions(),hasItem(new DebitTransaction(new Date(),1000,new AccountNumber("1234-4567"),"debit")));
  }
}
