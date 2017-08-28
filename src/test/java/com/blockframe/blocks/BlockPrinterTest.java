package com.blockframe.blocks;

import org.junit.Test;

public class BlockPrinterTest {

    @Test
    public void testBlockPrinter() {
        Block block = new Block();
        BlockHeader blockHeader = new BlockHeader();
        block.setBlockHeader(blockHeader);
        BlockPrinter.printMinedBlock(block);
    }

}
