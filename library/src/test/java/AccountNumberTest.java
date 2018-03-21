import com.thoughtworks.step.bank.AccountNumber;
import com.thoughtworks.step.bank.InvalidAccountNumberException;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class AccountNumberTest {
  @Test
  public void checkAccountNumber() throws InvalidAccountNumberException {
    assertThat(new AccountNumber("1234-5678").getAccountNumber(),is("1234-5678"));
  }
  @Test (expected =  InvalidAccountNumberException.class)
  public void checkInvalidAccountNumber() throws InvalidAccountNumberException {
    new AccountNumber("12-5678");
  }
}
