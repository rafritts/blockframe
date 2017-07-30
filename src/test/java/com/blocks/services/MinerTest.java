package com.blocks.services;

import com.blocks.models.Block;
import com.blocks.models.Transaction;
import com.blocks.resources.TransactionPool;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MinerTest {

    @Test
    public void testMiner() {
        TransactionPool transactionPool = new TransactionPool();
        BlockMaker blockMaker = new BlockMaker(transactionPool);
        transactionPool.submitTransaction(new Transaction("test transaction 1"));
        Block block = blockMaker.createBlock();
        Miner.mineBlock(block);
        assertEquals("[{\"verified\":true,\"details\":\"test transaction 1\"}]", block.getPayload());
        assertEquals(615805, block.getNonce());
        assertEquals("000005a0d9fb42abcdf59f37aa55cab5a2713c2a701ba5659595f67ff7ede9d6", block.getMinedPayloadHash());
    }


}
