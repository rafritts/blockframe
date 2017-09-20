package com.blockframe.blocks;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BlockHeaderTest {

    @Test
    public void testBlockHeader() {
        BlockHeader blockHeader = new BlockHeader();
        blockHeader.setBlockId("1");
        blockHeader.setDifficultyTarget(1);
        blockHeader.setMerkleRoot("MerkleRoot");
        blockHeader.setMinedHash("MinedHash");
        blockHeader.setPreviousBlockHash("PreviousBlockHash");
        blockHeader.setNonce(100);
        blockHeader.setMiningTimeInSeconds(1);
        blockHeader.setTimeStamp(100);
        blockHeader.setVersion("1.0.0");
        Block block = new Block();
        block.setBlockHeader(blockHeader);
        assertEquals("1", block.getBlockHeader().getBlockId());
        assertEquals(1, block.getBlockHeader().getDifficultyTarget());
        assertEquals("MerkleRoot", block.getBlockHeader().getMerkleRoot());
        assertEquals("MinedHash", block.getBlockHeader().getMinedHash());
        assertEquals("PreviousBlockHash", block.getBlockHeader().getPreviousBlockHash());
        assertEquals(100, block.getBlockHeader().getNonce());
        assertEquals(1.0, block.getBlockHeader().getMiningTimeInSeconds(), 0.1);
        assertEquals(100, block.getBlockHeader().getTimeStamp());
        assertEquals("1.0.0", block.getBlockHeader().getVersion());
    }

}
