package com.thoughtworks.step.bank;

public class InvalidAccountNumberException extends Throwable {
  public InvalidAccountNumberException(){
    super("Invalid account number");
  }
}
