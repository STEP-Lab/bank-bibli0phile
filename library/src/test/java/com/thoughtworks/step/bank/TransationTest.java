package com.thoughtworks.step.bank;

import org.junit.Test;

import java.util.Date;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class TransationTest {
  @Test
  public void checkDateOfTransaction() {
    Date date = new Date();
    Transaction debitTransaction=new DebitTransaction(new Date(),100,new AccountNumber("1234-5678"));
    assertThat(debitTransaction.getDate(),is(date));
  }
}
