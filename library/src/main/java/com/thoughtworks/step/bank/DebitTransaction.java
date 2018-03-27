package com.thoughtworks.step.bank;

import java.util.Date;

public class DebitTransaction extends Transaction {
  protected DebitTransaction(Date date, double amount, AccountNumber to,String type,double balance) {
    super(date, amount, to, type, balance);
  }
  
  public DebitTransaction(double amount, AccountNumber to, String type,double balance) {
    this(new Date(),amount,to,type,balance);
  }
}
