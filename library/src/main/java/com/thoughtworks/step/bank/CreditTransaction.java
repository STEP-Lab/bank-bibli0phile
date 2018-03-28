package com.thoughtworks.step.bank;

import java.util.Date;

public class CreditTransaction extends Transaction {
  protected CreditTransaction(Date date, double amount, AccountNumber from,double balance) {
    super(date, amount, from,balance);
  }
  
  public CreditTransaction(double amount, AccountNumber from,double balance) {
    this(new Date(),amount,from,balance);
  }
}
