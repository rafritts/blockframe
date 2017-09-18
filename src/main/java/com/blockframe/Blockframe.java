package com.blockframe;

import com.blockframe.blocks.Block;
import com.blockframe.mining.Miner;
import com.blockframe.utils.ObjectProvider;

public class Blockframe {

    private static final int TIME_DELAY_SECONDS = 1;
    private static final int ONE_SECOND = 1000;

    public void run() {
        ObjectProvider.webServiceManager.startWebServices();
        generateAndMineBlocks();
    }

    private void generateAndMineBlocks() {
        while (true) {
            processTransactions();
            sleepForXSeconds();
        }
    }

    private void processTransactions() {
        Block block = ObjectProvider.blockMaker.createUnminedBlock();
        if (hasTransactionsToMine(block)) {
            mineBlock(block);
            ObjectProvider.blockPool.moveMinedBlocksToBlockChain();
            block.assignBlockId(ObjectProvider.blockchain);
            ObjectProvider.storageManager.storeBlock(block);
            ObjectProvider.blockPool.cleanBlockPool();
            ObjectProvider.transactionPool.cleanTransactionPool();
        } else {
            System.out.print("\r" + "Waiting for transactions to mine...");
        }
    }

    private void mineBlock(Block block) {
        ObjectProvider.blockPool.addBlock(block);
        Miner.mineBlock(ObjectProvider.blockPool.getFirstUnminedBlock());
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
}
