package com.thoughtworks.step.bank;


import java.util.Objects;

public class AccountNumber {
  private final String accountNumber;
  public AccountNumber(String accountNumber){
    this.accountNumber = accountNumber;
  }
  
  public static AccountNumber createAccountNumber(String accountNumber) throws InvalidAccountNumberException {
    if (!accountNumber.matches("^\\d{4}-\\d{4}$")) {
      throw new InvalidAccountNumberException();
    }
    return new AccountNumber(accountNumber);
  }
  
  public String getAccountNumber() {
    return accountNumber;
  }
  
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    AccountNumber that = (AccountNumber) o;
    return Objects.equals(accountNumber, that.accountNumber);
  }
  
  @Override
  public int hashCode() {
    return Objects.hash(accountNumber);
  }
}
