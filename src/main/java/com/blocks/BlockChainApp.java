package com.blocks;

import com.blocks.mining.Miner;
import com.blocks.models.Block;
import com.blocks.models.Blockchain;
import com.blocks.resources.BlockPool;
import com.blocks.resources.TransactionPool;
import com.blocks.services.BlockMaker;
import com.blocks.services.BlockchainService;
import com.blocks.services.TransactionService;

public class BlockChainApp {

    private static final int TIME_DELAY_SECONDS = 5;
    private static final int ONE_SECOND = 1000;
    private static final int LEADING_ZEROS = 6;

    private TransactionPool transactionPool = new TransactionPool();
    private Blockchain blockchain = new Blockchain();
    private BlockMaker blockMaker = new BlockMaker(transactionPool, blockchain);
    private BlockPool blockPool = new BlockPool(blockchain);
    private TransactionService transactionService = new TransactionService(transactionPool);
    private BlockchainService blockchainService = new BlockchainService(blockchain);
    public void run() {
        transactionService.run();
        blockchainService.run();
        generateAndMineBlocks();
    }

    private void generateAndMineBlocks() {
        while (true) {
            processTransactions();
            sleepForXSeconds();
        }
    }

    private void processTransactions() {
        Block block = blockMaker.createBlock();
        if (hasTransactionsToMine(block)) {
            mineBlock(block);
        } else {
            System.out.println("No transactions found to testMine");
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

}
