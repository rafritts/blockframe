package com.blocks;

import com.blocks.models.Transaction;
import com.blocks.resources.BlockPool;
import com.blocks.resources.TransactionPool;
import com.blocks.services.BlockMaker;
import com.blocks.services.Miner;

public class BlockChainApp {

    private static final int ONE_SECOND = 1000;

    private TransactionPool transactionPool = new TransactionPool();
    private BlockMaker blockMaker = new BlockMaker(transactionPool);
    private BlockPool blockPool = new BlockPool();
    private Transaction transaction1 = new Transaction("transaction1");
    private Transaction transaction2 = new Transaction("transaction2");
    private Transaction transaction3 = new Transaction("transaction3");
    private Transaction transaction4 = new Transaction("transaction4");
    private Transaction transaction5 = new Transaction("transaction5");

    public void run() {
        while (true) {
            transactionPool.submitTransaction(transaction1);
            transactionPool.submitTransaction(transaction2);
            transactionPool.submitTransaction(transaction3);
            transactionPool.submitTransaction(transaction4);
            transactionPool.submitTransaction(transaction5);
            processTransactions();
            sleepForTenSeconds();
        }
    }

    private void processTransactions() {
        blockPool.addBlock(blockMaker.createBlock());
        Miner.mineBlock(blockPool.getFirstUnminedBlock(), 6);
    }

    private void sleepForTenSeconds() {
        try {
            System.out.println("Sleeping for 10 seconds");
            Thread.sleep(ONE_SECOND * 10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}
