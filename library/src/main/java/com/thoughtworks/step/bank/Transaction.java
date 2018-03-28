package com.thoughtworks.step.bank;

import java.util.Date;
import java.util.Objects;

public abstract class Transaction {
  private final Date date;
  private final double amount;
  private final AccountNumber to;
  private final double balance;
  
  
  public Transaction(Date date, double amount, AccountNumber to, double balance) {
    this.date = date;
    this.amount = amount;
    this.to = to;
    this.balance = balance;
  }
  
  public Date getDate() {
    return date;
  }
  
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Transaction that = (Transaction) o;
    return Double.compare(that.amount, amount) == 0 &&
            Double.compare(that.balance, balance) == 0 &&
            Objects.equals(date.toString(), that.date.toString()) &&
            Objects.equals(to, that.to);
  }
  
  @Override
  public int hashCode() {
    
    return Objects.hash(date, amount, to, balance);
  }
  
  public double getAmount() {
    return amount;
  }
  
  @Override
  public String toString() {
    return "Transaction{" +
            "date=" + date +
            ", amount=" + amount +
            ", to=" + to +
            ", balance=" + balance +
            '}';
  }
  
  public AccountNumber getSource() {
    return to;
  }
  
  public double getBalance() {
    return balance;
  }
}
