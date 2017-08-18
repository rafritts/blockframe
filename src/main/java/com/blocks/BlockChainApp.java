package com.blocks;

import com.blocks.models.Block;
import com.blocks.models.Transaction;
import com.blocks.resources.BlockPool;
import com.blocks.resources.Blockchain;
import com.blocks.resources.TransactionPool;
import com.blocks.services.BlockMaker;
import com.blocks.services.Miner;
import com.blocks.services.TransactionListener;

public class BlockChainApp implements Runnable{

    private static final int TIME_DELAY_SECONDS = 30;
    private static final int ONE_SECOND = 1000;
    private static final int LEADING_ZEROS = 5;

    private TransactionPool transactionPool = new TransactionPool();
    private Blockchain blockchain = new Blockchain();
    private BlockMaker blockMaker = new BlockMaker(transactionPool);
    private BlockPool blockPool = new BlockPool(blockchain);
    private TransactionListener transactionListener = new TransactionListener(transactionPool);

    public void run() {
        transactionListener.run();
        generateAndMineBlocks();
    }

    private void generateAndMineBlocks() {
        int transactionNumber = 1;
        while (true) {
            //transactionNumber = generateFakeTransactions(transactionNumber);
            processTransactions();
            sleepForTenSeconds();
        }
    }

    private int generateFakeTransactions(int transactionNumber) {
        for (int j = 0; j < 5; j++) {
            transactionPool.submitTransaction(new Transaction("transaction" + transactionNumber++));
        }
        return transactionNumber;
    }

    private void processTransactions() {
        Block block = blockMaker.createBlock();
        if (block.getListOfVerifiedTransactions().size() != 0) {
            blockPool.addBlock(block);
            Miner.mineBlock(blockPool.getFirstUnminedBlock(), LEADING_ZEROS);
            blockPool.cleanBlockpool();
            System.out.println("Current blockchain size: " + blockchain.getBlockchainLength());
        } else {
            System.out.println("No transactions found to mine");
        }
    }

    private void sleepForTenSeconds() {
        try {
            System.out.println("Sleeping for 10 seconds");
            Thread.sleep(TIME_DELAY_SECONDS * ONE_SECOND);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}
