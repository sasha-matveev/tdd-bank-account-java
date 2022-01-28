package org.xpdojo.bank;

public interface IAccountBalanceChangeListener {
    void onBalanceChanged(long timestamp, int change, int balance);
}
