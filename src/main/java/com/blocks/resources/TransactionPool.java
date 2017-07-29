package com.blocks.resources;

import com.blocks.models.Transaction;

import java.util.LinkedList;

public class TransactionPool {

    LinkedList<Transaction> transactionList = new LinkedList<>();

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

}
