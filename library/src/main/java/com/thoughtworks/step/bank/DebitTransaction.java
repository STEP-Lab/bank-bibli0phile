package com.thoughtworks.step.bank;

import java.util.Date;

public class DebitTransaction extends Transaction {
  protected DebitTransaction(Date date, double amount, AccountNumber to,double balance) {
    super(date, amount, to, balance);
  }
  
  public DebitTransaction(double amount, AccountNumber to,double balance) {
    this(new Date(),amount,to,balance);
  }
}
