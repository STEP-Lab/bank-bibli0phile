package com.thoughtworks.step.bank;

import java.util.Date;

public class DebitTransaction extends Transaction {
  protected DebitTransaction(Date date, double amount, AccountNumber to) {
    super(date, amount, to);
  }
  
  public DebitTransaction(double amount, AccountNumber to) {
    this(new Date(),amount,to);
  }
}
