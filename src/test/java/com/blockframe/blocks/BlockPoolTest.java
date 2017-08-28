package com.blockframe.blocks;

import com.blockframe.blockchain.Blockchain;
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

    @Test
    public void testCleanBlockPool() {
        Block block1 = new Block();
        block1.setBlockHeader(new BlockHeader());
        block1.getBlockHeader().setMinedHash(null);
        blockPool.addBlock(block1);
        assertEquals(block1, blockPool.getFirstUnminedBlock());
        block1.getBlockHeader().setMinedHash("MinedHash");
        blockPool.cleanBlockPool();
        assertEquals(null, blockPool.getFirstUnminedBlock());
    }

    @Test
    public void testMoveMinedBlocksToBlockChain() {
        Block block1 = new Block();
        block1.setBlockHeader(new BlockHeader());
        block1.getBlockHeader().setMinedHash(null);
        blockPool.addBlock(block1);
        blockPool.moveMinedBlocksToBlockChain();
        assertEquals(0, testBlockchain.getBlockchainLength());
        block1.getBlockHeader().setMinedHash("MinedHash");
        blockPool.moveMinedBlocksToBlockChain();
        assertEquals(1, testBlockchain.getBlockchainLength());
    }


}
