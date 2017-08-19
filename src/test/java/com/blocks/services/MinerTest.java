package com.blocks.services;

import com.blocks.models.Block;
import com.blocks.models.Transaction;
import com.blocks.resources.Blockchain;
import com.blocks.resources.TransactionPool;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MinerTest {

    @Test
    public void testMiner() {
        TransactionPool transactionPool = new TransactionPool();
        Blockchain blockchain = new Blockchain();
        BlockMaker blockMaker = new BlockMaker(transactionPool, blockchain);
        transactionPool.submitTransaction(new Transaction("test transaction 1"));
        Block block = blockMaker.createBlock();
        Miner.mineBlock(block, 1);
        assertEquals("[{\"verified\":true,\"details\":\"test transaction 1\"}]", block.getPayload());
        assertEquals(17, block.getNonce());
        assertEquals("01813de5d375477e029b12022601f39cc0cc140219401fbed026f6aee57058c8", block.getMinedPayloadHash());
    }


}
