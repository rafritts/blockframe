package com.blocks.services;

import com.blocks.models.Block;
import com.blocks.models.Transaction;
import com.blocks.resources.TransactionPool;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BlockMakerTest {

    private TransactionPool transactionPool = new TransactionPool();
    private BlockMaker blockMaker = new BlockMaker(transactionPool);

    @Test
    public void testBlockMaker() {
        transactionPool.submitTransaction(new Transaction("test transaction 1"));
        Block block = blockMaker.createBlock();
        assertEquals(false, block.isMined());
        assertEquals("test transaction 1", block.getListOfVerifiedTransactions().getFirst().getDetails());
    }


}
