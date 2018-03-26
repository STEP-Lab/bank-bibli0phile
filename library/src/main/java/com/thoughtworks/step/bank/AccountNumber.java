package com.thoughtworks.step.bank;


public class AccountNumber {
  private final String accountNumber;
  private AccountNumber(String accountNumber){
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
  
  
}
