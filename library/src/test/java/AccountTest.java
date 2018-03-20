import com.thoughtworks.step.bank.Account;
import com.thoughtworks.step.bank.MinimumBalanceException;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class AccountTest {
  
  private Account account;
  
  @Before
  public void setUp() throws MinimumBalanceException {
    account = new Account("1234", 3000.00);
  
  }
  
  @Test
  public void checkBalance(){
    assertThat(account.getBalance(),is(3000.00));
  }
  
  @Test
  public void checkAccountNumber() {
    assertThat(account.getAccountNumber(),is("1234"));
  }
  
  @Test (expected = MinimumBalanceException.class)
  public void checkMinimumBalance() throws MinimumBalanceException {
    new Account("1245",200);
  }
  
  @Test
  public void checkWithdrawBalance() {
    account.debit(500);
    assertThat(account.getBalance(),is(2500.0));
  }
}
