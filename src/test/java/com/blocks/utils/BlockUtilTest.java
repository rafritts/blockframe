package com.blocks.utils;

import com.blocks.models.Block;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BlockUtilTest {

    @Test
    public void testSetPayloadAsTransactionList() {
        Block block = new Block();
        BlockUtil.setPayloadAsTransactionList(block);
        assertEquals("[]", block.getPayload());
    }

}
