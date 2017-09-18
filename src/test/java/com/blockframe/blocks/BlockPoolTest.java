package com.blockframe.blocks;

import com.blockframe.blockchain.Blockchain;
import com.blockframe.utils.ObjectProvider;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class BlockPoolTest {

    @Before
    public void init() {
        ObjectProvider.blockchain = new Blockchain();
    }

    @Test
    public void testBlockPoolTest() {
        Block block1 = new Block();
        block1.setBlockHeader(new BlockHeader());
        block1.getBlockHeader().setMinedHash(null);
        block1.setPayloadAsJson("test payload 1");
        ObjectProvider.blockPool.addBlock(block1);
        assertEquals("test payload 1", ObjectProvider.blockPool.getFirstUnminedBlock().getPayloadAsJson());
        block1.getBlockHeader().setMinedHash("Mined Hash");
        assertNull(ObjectProvider.blockPool.getFirstUnminedBlock());
    }

    @Test
    public void testCleanBlockPool() {
        Block block1 = new Block();
        block1.setBlockHeader(new BlockHeader());
        block1.getBlockHeader().setMinedHash(null);
        ObjectProvider.blockPool.addBlock(block1);
        assertEquals(block1, ObjectProvider.blockPool.getFirstUnminedBlock());
        block1.getBlockHeader().setMinedHash("MinedHash");
        ObjectProvider.blockPool.cleanBlockPool();
        assertEquals(null, ObjectProvider.blockPool.getFirstUnminedBlock());
    }

    @Test
    public void testMoveMinedBlocksToBlockChain() {
        Block block1 = new Block();
        block1.setBlockHeader(new BlockHeader());
        block1.getBlockHeader().setMinedHash(null);
        ObjectProvider.blockPool.addBlock(block1);
        ObjectProvider.blockPool.moveMinedBlocksToBlockChain();
        assertEquals(0, ObjectProvider.blockchain.getBlockchainLength());
        block1.getBlockHeader().setMinedHash("MinedHash");
        ObjectProvider.blockPool.moveMinedBlocksToBlockChain();
        assertEquals(1, ObjectProvider.blockchain.getBlockchainLength());
    }


}
