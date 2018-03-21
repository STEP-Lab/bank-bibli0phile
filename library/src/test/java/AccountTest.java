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
  public void checkAccountNumber() {
    assertThat(account.getAccountNumber(),is("1234-4567"));
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
  public void checkWithdrawBalance() throws MinimumBalanceException {
    assertThat(account.debit(500),is(2500.0));
    account.debit(2000);
    assertThat(account.debit(500),is(2000.0));
  }
  
  @Test (expected =  InvalidAccountNumberException.class)
  public void checkInvalidAccountNumber() throws MinimumBalanceException, InvalidAccountNumberException {
    new Account("ketan","12-5678",78943.7);
  }
  
  @Test (expected = MinimumBalanceException.class)
  public void checkDepositBalance() throws MinimumBalanceException {
    assertThat(account.credit(1000),is(4000.0));
    account.credit(-1000);
  }
}
