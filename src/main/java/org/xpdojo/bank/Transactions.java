package org.xpdojo.bank;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Transactions implements IAccountBalanceChangeListener, IPrintable {
    private final List<Transaction> transactions = new ArrayList<>();

    @Override
    public void onBalanceChanged(long timestamp, int change, int balance) {
        this.transactions.add(new Transaction(timestamp, change, balance));
    }

    public Transaction lastTransaction() {
        return transactions.get(transactions.size() - 1);
    }

    @Override
    public String print() {
        return transactions.stream().map(t -> t.toString()).collect(Collectors.joining("\n"));
    }

    public final static class Transaction implements IPrintable {
        private final long time;
        private final String type;
        private final int amount;
        private final int balance;

        private Transaction(long time, int change, int balance) {
            this.time = time;
            this.type = change > 0 ? "Deposit" : "Withdraw";
            this.amount = Math.abs(change);
            this.balance = balance;
        }

        @Override
        public String toString() {
            return time + " : " + type + " : " + amount + " : " + balance;
        }

        @Override
        public String print() {
            return toString();
        }
    }
}
