package com.blocks.transactions;

import java.util.LinkedList;

public class TransactionPool {

    private LinkedList<Transaction> transactionList = new LinkedList<>();

    public void submitTransaction(Transaction transaction) {
        transactionList.add(transaction);
    }

    public LinkedList<Transaction> getAllUnverifiedTransactions() {
        LinkedList<Transaction> list = new LinkedList<>();
        for (Transaction transaction : transactionList) {
            if (!transaction.isVerified()) {
                list.add(transaction);
            }
        }
        return list;
    }

    public void cleanTransactionPool() {
        LinkedList<Transaction> verifiedTransactions = getAllMinedTransactions();
        transactionList.removeAll(verifiedTransactions);
    }

    private LinkedList<Transaction> getAllMinedTransactions() {
        LinkedList<Transaction> verifiedTransactions = new LinkedList<>();
        for (Transaction transaction : transactionList) {
            if (transaction.isMined()) {
                verifiedTransactions.add(transaction);
            }
        }
        return verifiedTransactions;
    }

}
