package com.blocks.services;

import com.blocks.mining.Miner;
import com.blocks.models.Block;
import com.blocks.models.Transaction;
import com.blocks.models.Blockchain;
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
        Block block = blockMaker.createBlock("1.0.0", 1);
        Miner.mineBlock(block, 1);
        assertEquals("[{\"verified\":true,\"mined\":false,\"details\":\"test transaction 1\"}]", block.getPayloadAsJson());
        assertEquals(49, block.getBlockHeader().getNonce());
        assertEquals("0cf3ffaaafa3726122d5526d68f4d41e50c39b88f045c77958335e455fbe77f8", block.getBlockHeader().getMinedHash());
    }


}
