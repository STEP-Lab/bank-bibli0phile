package com.thoughtworks.step.bank;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class AccountNumberTest {
  @Test
  public void checkAccountNumber() throws InvalidAccountNumberException {
    assertThat(AccountNumber.createAccountNumber("1234-5678").getAccountNumber(),is("1234-5678"));
  }
  @Test (expected =  InvalidAccountNumberException.class)
  public void checkInvalidAccountNumber() throws InvalidAccountNumberException {
    AccountNumber.createAccountNumber("12-5678");
  }
}
