package com.blocks.resources;

import com.blocks.models.Transaction;

import java.util.LinkedList;

public class TransactionPool {

    LinkedList<Transaction> transactionList = new LinkedList<>();

    public void submitTransaction(Transaction transaction) {
        transactionList.add(transaction);
    }

}
