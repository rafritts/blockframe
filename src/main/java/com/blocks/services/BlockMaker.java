package com.blocks.services;

import com.blocks.models.Block;
import com.blocks.models.Transaction;
import com.blocks.resources.TransactionPool;
import com.blocks.utils.BlockUtil;

import java.util.LinkedList;

public class BlockMaker {

    private TransactionPool transactionPool;

    public BlockMaker(TransactionPool pool) {
        this.transactionPool = pool;
    }

    public Block createBlock() {
        Block block = new Block();
        LinkedList<Transaction> listOfTransactions = transactionPool.getAllUnverifiedTransactions();
        verifyTransactions(listOfTransactions);
        addAllTransactionsToBlock(listOfTransactions, block);
        BlockUtil.setPayloadAsTransactionList(block);
        return block;
    }

    private void verifyTransactions(LinkedList<Transaction> listOfUnverifiedTransactions) {
        TransactionVerifier transactionVerifier = new TransactionVerifier();
        for (Transaction transaction : listOfUnverifiedTransactions) {
            transactionVerifier.verify(transaction);
        }
    }

    private void addAllTransactionsToBlock(LinkedList<Transaction> listOfVerifiedTransactions, Block block) {
        for (Transaction transaction : listOfVerifiedTransactions) {
            if (transaction.isVerified()) {
                block.addVerifiedTransaction(transaction);
            }
        }
    }


}
