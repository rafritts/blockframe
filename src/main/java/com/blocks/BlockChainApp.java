package com.blocks;

import com.blocks.models.Transaction;
import com.blocks.resources.BlockPool;
import com.blocks.resources.TransactionPool;
import com.blocks.services.BlockMaker;
import com.blocks.services.Miner;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

public class BlockChainApp {

    private TransactionPool transactionPool = new TransactionPool();
    private BlockMaker blockMaker = new BlockMaker(transactionPool);
    private BlockPool blockPool = new BlockPool();
    private Transaction transaction1 = new Transaction("transaction1");
    private Transaction transaction2 = new Transaction("transaction2");
    private Transaction transaction3 = new Transaction("transaction3");
    private Transaction transaction4 = new Transaction("transaction4");
    private Transaction transaction5 = new Transaction("transaction5");

    public void run() throws UnsupportedEncodingException, NoSuchAlgorithmException {
        transactionPool.submitTransaction(transaction1);
        processTransactions();
        transactionPool.submitTransaction(transaction2);
        processTransactions();
        transactionPool.submitTransaction(transaction3);
        processTransactions();
        transactionPool.submitTransaction(transaction4);
        processTransactions();
        transactionPool.submitTransaction(transaction5);
        processTransactions();
    }

    private void processTransactions() throws UnsupportedEncodingException, NoSuchAlgorithmException {
        blockPool.addBlock(blockMaker.createBlock());
        Miner.mineBlock(blockPool.getFirstUnminedBlock());
    }
}
