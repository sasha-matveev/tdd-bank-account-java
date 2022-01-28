package org.xpdojo.bank;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class AccountTest {

    @Test
	public void shouldHasZeroBalanceOnNewAccount() {
        final Account account = new Account();
        assertThat(account.balance()).isEqualTo(0);
    }

    @Test
    void shouldDeposit() {
        final Account account = new Account();
        account.deposit(10);
        assertThat(account.balance()).isEqualTo(10);
        account.deposit(20);
        assertThat(account.balance()).isEqualTo(30);
    }
}
