package com.blocks.blocks;

import com.blocks.blocks.Block;
import com.blocks.blocks.BlockHeader;
import com.blocks.blockchain.Blockchain;
import com.blocks.blocks.BlockPool;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

import static org.junit.Assert.assertNull;

public class BlockPoolTest {

    private Blockchain testBlockchain = new Blockchain();
    private BlockPool blockPool = new BlockPool(testBlockchain);

    @Test
    public void testBlockPoolTest() {
        Block block1 = new Block();
        block1.setBlockHeader(new BlockHeader());
        block1.getBlockHeader().setMinedHash(null);
        block1.setPayloadAsJson("test payload 1");
        blockPool.addBlock(block1);
        assertEquals("test payload 1", blockPool.getFirstUnminedBlock().getPayloadAsJson());
        block1.getBlockHeader().setMinedHash("Mined Hash");
        assertNull(blockPool.getFirstUnminedBlock());
    }

}
