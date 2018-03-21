import com.thoughtworks.step.bank.Account;
import com.thoughtworks.step.bank.InvalidAccountNumberException;
import com.thoughtworks.step.bank.MinimumBalanceException;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class AccountTest {
  
  private Account account;
  
  @Before
  public void setUp() throws MinimumBalanceException, InvalidAccountNumberException {
    account = new Account("ketan","1234-4567", 3000.00);
  
  }
  
  @Test
  public void checkBalance(){
    assertThat(account.getBalance(),is(3000.00));
  }
  
  @Test
  public void checkAccountHolder() {
    assertThat(account.getAccountHolder(),is("ketan"));
  }
  
  @Test (expected = MinimumBalanceException.class)
  public void checkMinimumBalance() throws MinimumBalanceException, InvalidAccountNumberException {
    new Account("ketan","1245-7865",200);
  }
  
  @Test (expected = MinimumBalanceException.class)
  public void checkIfWithdrawIsAllowedIfBalanceIsGreaterThanMinimum() throws MinimumBalanceException {
    assertThat(account.debit(500),is(2500.0));
    account.debit(2000);
    assertThat(account.debit(500),is(2000.0));
  }
  
  @Test (expected = MinimumBalanceException.class)
  public void checkDepositBalance() throws MinimumBalanceException {
    assertThat(account.credit(1000),is(4000.0));
    account.credit(-1000);
  }
  
  @Test
  public void checkSummary() {
    assertThat(account.getSummary(),is("Account{accountNumber='1234-4567', accountHolder='ketan', balance=3000.0}"));
  }
}
