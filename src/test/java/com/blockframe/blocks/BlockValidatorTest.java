package com.blockframe.blocks;

import com.blockframe.mining.Miner;
import com.blockframe.transactions.Transaction;
import com.blockframe.utils.ObjectProvider;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class BlockValidatorTest {

    @Test
    public void testValidateBlock() {
        ObjectProvider.transactionPool.submitTransaction(new Transaction("test"));
        Block block = ObjectProvider.blockMaker.createUnminedBlock();
        Miner.mineBlock(block);
        assertTrue(BlockValidator.validateBlock(block));
    }

}
