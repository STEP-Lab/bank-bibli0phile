package com.thoughtworks.step.bank;

import java.util.Date;

public class CreditTransaction extends Transaction {
  protected CreditTransaction(Date date, double amount, AccountNumber from,String type,double balance) {
    super(date, amount, from, type,balance);
  }
  
  public CreditTransaction(double amount, AccountNumber from, String type,double balance) {
    this(new Date(),amount,from,type,balance);
  }
}
