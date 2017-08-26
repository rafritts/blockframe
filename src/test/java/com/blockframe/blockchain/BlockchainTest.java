package com.blockframe.blockchain;

import com.blockframe.blocks.Block;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BlockchainTest {

    @Test
    public void testBlockchain() {
        Blockchain blockchain = new Blockchain();
        blockchain.addBlock(new Block());
        assertEquals(1, blockchain.getBlockchainLength());
    }


}
