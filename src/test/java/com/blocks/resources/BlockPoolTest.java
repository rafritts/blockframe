package com.blocks.resources;

import com.blocks.models.Block;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

import static org.junit.Assert.assertNull;

public class BlockPoolTest {

    private BlockPool blockPool = new BlockPool();

    @Test
    public void testBlockPoolTest() {
        Block block1 = new Block();
        block1.setMined(false);
        block1.setPayload("test payload 1");
        blockPool.addBlock(block1);
        assertEquals("test payload 1", blockPool.getFirstUnminedBlock().getPayload());
        block1.setMined(true);
        assertNull(blockPool.getFirstUnminedBlock());
    }

}
