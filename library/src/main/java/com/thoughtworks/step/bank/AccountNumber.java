package com.thoughtworks.step.bank;


public class AccountNumber {
  private final String accountNumber;
  public AccountNumber(String accountNumber) throws InvalidAccountNumberException {
    if (!accountNumber.matches("^\\d{4}-\\d{4}$")) {
      throw new InvalidAccountNumberException();
    }
    this.accountNumber = accountNumber;
  }
  
  public String getAccountNumber() {
    return accountNumber;
  }
  
  
}
