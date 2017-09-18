package com.blockframe;

import com.blockframe.blocks.Block;
import com.blockframe.mining.Miner;
import com.blockframe.utils.ObjectProvider;

import java.util.concurrent.TimeUnit;

public class Blockframe {

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
        if (block.hasTransactionsToMine()) {
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
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
