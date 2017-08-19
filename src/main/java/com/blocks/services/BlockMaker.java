package com.blocks.services;

import com.blocks.models.Block;
import com.blocks.models.Transaction;
import com.blocks.resources.Blockchain;
import com.blocks.resources.TransactionPool;
import com.google.gson.Gson;

import java.util.LinkedList;
import java.util.NoSuchElementException;

public class BlockMaker {

    private TransactionPool transactionPool;
    private Blockchain blockchain;

    public BlockMaker(TransactionPool pool, Blockchain blockchain) {
        this.transactionPool = pool;
        this.blockchain = blockchain;
    }

    public Block createBlock() {
        Block block = new Block();
        LinkedList<Transaction> listOfTransactions = transactionPool.getAllUnverifiedTransactions();
        verifyTransactions(listOfTransactions);
        addAllTransactionsToBlock(listOfTransactions, block);
        block.setPayload(new Gson().toJson(block.getListOfVerifiedTransactions()));
        setPreviousBlockHash(block);
        return block;
    }

    private void setPreviousBlockHash(Block block) {
        try {
            block.setPreviousPayloadHash(blockchain.getBlockchain().getLast().getMinedPayloadHash());
        } catch (NoSuchElementException exp) {
            block.setPreviousPayloadHash("0000000000000000000000000000000000000000000000000000000000000000");
        }
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
