package org.xpdojo.bank;

public class Account {

    private final IAccountBalanceChangeListener listener;

    private int balance = 0;

    public Account(IAccountBalanceChangeListener listener) {
        this.listener = listener;
    }

    public void deposit(int amount) {
        this.balance += amount;
        listener.onBalanceChanged(System.currentTimeMillis(), amount, balance);
    }

    public void withdraw(int amount) {
        if (this.balance < amount) {
            throw new IllegalArgumentException("Not enough on account to withdraw");
        }
        this.balance -= amount;
        listener.onBalanceChanged(System.currentTimeMillis(), -amount, balance);
    }

    public void transferTo(Account account, int amount) {
        this.withdraw(amount);
        account.deposit(amount);
    }
}
