package com.thoughtworks.step.bank;

import java.util.Date;
import java.util.Objects;

public abstract class Transaction {
  private final Date date;
  private final double amount;
  private final AccountNumber to;
  private final String type;
  
  public Transaction(Date date, double amount, AccountNumber to, String type) {
    this.date = date;
    this.amount = amount;
    this.to = to;
    this.type = type;
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
            Objects.equals(date.toString(), that.date.toString()) &&
            Objects.equals(to, that.to) &&
            Objects.equals(type, that.type);
  }
  
  @Override
  public int hashCode() {
    
    return Objects.hash(date.toString(), amount, to, type);
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
            ", type='" + type + '\'' +
            '}';
  }
  
  public AccountNumber getSource() {
    return to;
  }
  
  public String getType() {
    return type;
  }
}
