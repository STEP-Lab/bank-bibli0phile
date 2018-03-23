package com.thoughtworks.step.bank;

import java.util.Date;

public class DebitTransaction extends Transaction {
  protected DebitTransaction(Date date, double amount, String to) {
    super(date, amount, to);
  }
  
  public DebitTransaction(double amount, String to) {
    this(new Date(),amount,to);
  }
}
