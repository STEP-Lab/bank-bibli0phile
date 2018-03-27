package com.thoughtworks.step.bank;

import java.util.Date;

public class CreditTransaction extends Transaction {
  protected CreditTransaction(Date date, double amount, AccountNumber from,String type) {
    super(date, amount, from, type );
  }
  
  public CreditTransaction(double amount, AccountNumber from, String type) {
    this(new Date(),amount,from,type);
  }
}
