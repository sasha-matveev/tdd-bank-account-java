package org.xpdojo.bank;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Transactions implements IAccountBalanceChangeListener {
    private final List<Transaction> transactions = new ArrayList<>();

    @Override
    public void onBalanceChanged(long timestamp, int change, int balance) {
        this.transactions.add(new Transaction(timestamp, change, balance));
    }

    public Transaction lastTransaction() {
        return transactions.get(transactions.size() - 1);
    }

    public Transactions deposits() {
        return forType(TransactionType.Deposit);
    }
    public Transactions withdraws() {
        return forType(TransactionType.Withdraw);
    }

    private Transactions forType(TransactionType type) {
        Transactions deposits = new Transactions();
        transactions.stream()
                .filter(t -> t.type == type)
                .forEach(t -> deposits.transactions.add(t));
        return deposits;
    }

    public Transactions fromTime(long startTime) {
        Transactions deposits = new Transactions();
        transactions.stream()
                .filter(t -> t.time > startTime)
                .forEach(t -> deposits.transactions.add(t));
        return deposits;
    }

    @Override
    public String toString() {
        return transactions.stream().map(t -> t.toString()).collect(Collectors.joining("\n"));
    }

    public enum TransactionType {
        Deposit, Withdraw
    }

    public final static class Transaction {
        private final long time;
        private final TransactionType type;
        private final int amount;
        private final int balance;

        private Transaction(long time, int change, int balance) {
            this.time = time;
            this.type = change > 0 ? TransactionType.Deposit : TransactionType.Withdraw;
            this.amount = Math.abs(change);
            this.balance = balance;
        }

        @Override
        public String toString() {
            return time + " : " + type + " : " + amount + " : " + balance;
        }
    }
}
