package com.blockframe;

import com.blockframe.blockchain.Blockchain;
import com.blockframe.blocks.Block;
import com.blockframe.blocks.BlockMaker;
import com.blockframe.blocks.BlockPool;
import com.blockframe.mining.Miner;
import com.blockframe.restfulservices.WebServiceManager;
import com.blockframe.transactions.TransactionPool;

public class BlockChainApp {

    private static final int TIME_DELAY_SECONDS = 1;
    private static final int ONE_SECOND = 1000;
    private static final String VERSION = "1.0.0";
    private static int DIFFICULTY_TARGET = 4;

    private TransactionPool transactionPool = new TransactionPool();
    private Blockchain blockchain = new Blockchain();
    private BlockMaker blockMaker = new BlockMaker(transactionPool, blockchain);
    private BlockPool blockPool = new BlockPool(blockchain);
    private WebServiceManager webServiceManager = new WebServiceManager(transactionPool, blockchain);


    public void run() {
        webServiceManager.startWebServices();
        generateAndMineBlocks();
    }

    private void generateAndMineBlocks() {
        while (true) {
            processTransactions();
            sleepForXSeconds();
        }
    }

    private void processTransactions() {
        Block block = blockMaker.createUnminedBlock(VERSION, DIFFICULTY_TARGET);
        if (hasTransactionsToMine(block)) {
            mineBlock(block);
            blockPool.cleanBlockPool();
            transactionPool.cleanTransactionPool();
        } else {
            System.out.print("\r" + "Waiting for transactions to mine...");
        }
    }

    private void mineBlock(Block block) {
        blockPool.addBlock(block);
        Miner.mineBlock(blockPool.getFirstUnminedBlock(), DIFFICULTY_TARGET);
    }

    private void sleepForXSeconds() {
        try {
            Thread.sleep(TIME_DELAY_SECONDS * ONE_SECOND);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private boolean hasTransactionsToMine(Block block) {
        return block.getListOfVerifiedTransactions().size() != 0;
    }

    public static int getDifficultyTarget() {
        return DIFFICULTY_TARGET;
    }

    public static void setDifficultyTarget(int difficultyTarget) {
        DIFFICULTY_TARGET = difficultyTarget;
    }
}
