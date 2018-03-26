package com.thoughtworks.step.bank;

import java.util.Date;

public class CreditTransaction extends Transaction {
  protected CreditTransaction(Date date, double amount, AccountNumber from) {
    super(date, amount, from);
  }
  
  public CreditTransaction(double amount, AccountNumber from) {
    this(new Date(),amount,from);
  }
}
