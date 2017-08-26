package com.blocks;

import com.blocks.blockchain.Blockchain;
import com.blocks.blocks.Block;
import com.blocks.blocks.BlockMaker;
import com.blocks.blocks.BlockPool;
import com.blocks.mining.Miner;
import com.blocks.restfulservices.BlockchainWebService;
import com.blocks.restfulservices.TransactionWebService;
import com.blocks.transactions.TransactionPool;

public class BlockChainApp {

    private static final int TIME_DELAY_SECONDS = 5;
    private static final int ONE_SECOND = 1000;
    private static final String VERSION = "1.0.0";
    private static final int LEADING_ZEROS = 5;

    private TransactionPool transactionPool = new TransactionPool();
    private Blockchain blockchain = new Blockchain();
    private BlockMaker blockMaker = new BlockMaker(transactionPool, blockchain);
    private BlockPool blockPool = new BlockPool(blockchain);
    private TransactionWebService transactionWebService = new TransactionWebService(transactionPool);
    private BlockchainWebService blockchainWebService = new BlockchainWebService(blockchain);

    public void run() {
        // if you want to test this code, you'll need to submit an http post to the transactionWebService
        transactionWebService.run();
        blockchainWebService.run();
        generateAndMineBlocks();
    }

    private void generateAndMineBlocks() {
        while (true) {
            processTransactions();
            sleepForXSeconds();
        }
    }

    private void processTransactions() {
        Block block = blockMaker.createUnminedBlock(VERSION, LEADING_ZEROS);
        if (hasTransactionsToMine(block)) {
            mineBlock(block);
        } else {
            System.out.println("No transactions found to mine");
        }
    }

    private void mineBlock(Block block) {
        blockPool.addBlock(block);
        Miner.mineBlock(blockPool.getFirstUnminedBlock(), LEADING_ZEROS);
        blockPool.cleanBlockPool();
    }

    private void sleepForXSeconds() {
        try {
            System.out.println("Sleeping for " + TIME_DELAY_SECONDS + " seconds");
            System.out.println();
            Thread.sleep(TIME_DELAY_SECONDS * ONE_SECOND);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    private boolean hasTransactionsToMine(Block block) {
        return block.getListOfVerifiedTransactions().size() != 0;
    }

    public TransactionPool getTransactionPool() {
        return this.transactionPool;
    }
}
