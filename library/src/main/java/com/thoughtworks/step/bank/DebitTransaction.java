package com.thoughtworks.step.bank;

import java.util.Date;

public class DebitTransaction extends Transaction {
  protected DebitTransaction(Date date, double amount, AccountNumber to,String type) {
    super(date, amount, to, type );
  }
  
  public DebitTransaction(double amount, AccountNumber to, String type) {
    this(new Date(),amount,to,type);
  }
}
