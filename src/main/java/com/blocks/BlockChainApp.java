package com.blocks;

import com.blocks.models.Transaction;
import com.blocks.resources.TransactionPool;
import com.blocks.services.BlockMaker;

public class BlockChainApp {

    private BlockMaker blockMaker = new BlockMaker();
    private TransactionPool transactionPool = new TransactionPool();
    private Transaction transaction1 = new Transaction("transaction1");
    private Transaction transaction2 = new Transaction("transaction2");
    private Transaction transaction3 = new Transaction("transaction3");
    private Transaction transaction4 = new Transaction("transaction4");
    private Transaction transaction5 = new Transaction("transaction5");

    public void run() {
        transactionPool.submitTransaction(transaction1);



    }
}
