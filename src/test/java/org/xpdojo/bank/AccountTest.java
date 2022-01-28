package org.xpdojo.bank;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.assertj.core.api.Assertions.fail;

public class AccountTest {

    @Test
    void shouldNotAllowToDepositNegativeAmount() {
        Account account = new Account(Mockito.mock(IAccountBalanceChangeListener.class));
        try {
            account.deposit(-10);
            fail("exception expected");
        } catch (Exception e) {
            // expected
        }
    }

    @Test
    void shouldNotAllowToWithdrawNegativeAmount() {
        Account account = new Account(Mockito.mock(IAccountBalanceChangeListener.class));
        try {
            account.withdraw(-10);
            fail("exception expected");
        } catch (Exception e) {
            // expected
        }
    }

    @Test
    void shouldAddToBalanceOnDeposit() {
        final IAccountBalanceChangeListener changeListener = Mockito.mock(IAccountBalanceChangeListener.class);
        final Account account = new Account(changeListener);
        account.deposit(10);
        Mockito.verify(changeListener).onBalanceChanged(Mockito.anyLong(), Mockito.eq(10), Mockito.eq(10));
        account.deposit(20);
        Mockito.verify(changeListener).onBalanceChanged(Mockito.anyLong(), Mockito.eq(20), Mockito.eq(30));
    }

    @Test
    void shouldLowerBalanceOnWithdraw() {
        final IAccountBalanceChangeListener changeListener = Mockito.mock(IAccountBalanceChangeListener.class);
        final Account account = new Account(changeListener);
        account.deposit(10);
        account.withdraw(5);
        Mockito.verify(changeListener).onBalanceChanged(Mockito.anyLong(), Mockito.eq(-5), Mockito.eq(5));
    }

    @Test
    void shouldNotAllowToWithdrawMoreThanExists() {
        Account account = new Account(Mockito.mock(IAccountBalanceChangeListener.class));
        try {
            account.withdraw(10);
            fail("exception expected");
        } catch (Exception e) {
            // expected
        }
    }

    @Test
    void shouldTransfer() {
        IAccountBalanceChangeListener l1 = Mockito.mock(IAccountBalanceChangeListener.class);
        IAccountBalanceChangeListener l2 = Mockito.mock(IAccountBalanceChangeListener.class);

        Account a1 = new Account(l1);
        Account a2 = new Account(l2);

        a1.deposit(10);
        a1.transferTo(a2, 5);

        Mockito.verify(l1).onBalanceChanged(Mockito.anyLong(), Mockito.eq(-5), Mockito.eq(5));
        Mockito.verify(l2).onBalanceChanged(Mockito.anyLong(), Mockito.eq(5), Mockito.eq(5));
    }
}
