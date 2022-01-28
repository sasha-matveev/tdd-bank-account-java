package org.xpdojo.bank;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class TransactionsTest {

    @Test
    void shouldPrintLatestTransaction() {
        Transactions t = new Transactions();
        t.onBalanceChanged(100L, 10, 20);
        assertThat(t.lastTransaction()).hasToString("100 : Deposit : 10 : 20");
    }

    @Test
    void shouldPrintAllTransactions() {
        Transactions t = new Transactions();
        t.onBalanceChanged(100L, 10, 20);
        t.onBalanceChanged(200L, -30, 40);
        assertThat(t).hasToString(("100 : Deposit : 10 : 20\n200 : Withdraw : 30 : 40"));
    }

    @Test
    void shouldPrintDepositsOnly() {
        Transactions t = new Transactions();
        t.onBalanceChanged(100L, 10, 20);
        t.onBalanceChanged(200L, -30, 40);
        assertThat(t.deposits()).hasToString(("100 : Deposit : 10 : 20"));
    }

    @Test
    void shouldPrintFromTimeOnly() {
        Transactions t = new Transactions();
        t.onBalanceChanged(100L, 10, 20);
        t.onBalanceChanged(200L, -30, 40);
        assertThat(t.fromTime(150L)).hasToString(("200 : Withdraw : 30 : 40"));
    }
}