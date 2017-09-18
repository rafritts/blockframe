package com.blockframe.blocks;

import com.blockframe.transactions.Transaction;
import com.blockframe.utils.ObjectProvider;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BlockMakerTest {

    @Test
    public void testBlockMaker() {
        ObjectProvider.transactionPool.submitTransaction(new Transaction("test transaction 1"));
        Block block = ObjectProvider.blockMaker.createUnminedBlock();
        assertEquals(true, block.getBlockHeader().getMinedHash() == null);
        assertEquals("test transaction 1", block.getListOfVerifiedTransactions().getFirst().getDetails());
    }

    @Test
    public void testGetPreviousBlockHash() {
        ObjectProvider.transactionPool.submitTransaction(new Transaction("test transaction 1"));
        Block block1 = ObjectProvider.blockMaker.createUnminedBlock();
        block1.getBlockHeader().setMinedHash("MinedHashBlock1");
        ObjectProvider.blockchain.addBlock(block1);
        ObjectProvider.transactionPool.submitTransaction(new Transaction("test transaction 1"));
        Block block2 = ObjectProvider.blockMaker.createUnminedBlock();
        assertEquals("MinedHashBlock1", block2.getBlockHeader().getPreviousBlockHash());
    }

    @Test
    public void testMerkleRootEmpty() {
        Block block = ObjectProvider.blockMaker.createUnminedBlock();
        assertEquals("", block.getBlockHeader().getMerkleRoot());
    }

    @Test
    public void testMerkleRootFull() {
        ObjectProvider.transactionPool.submitTransaction(new Transaction("test transaction 1"));
        ObjectProvider.transactionPool.submitTransaction(new Transaction("test transaction 2"));
        ObjectProvider.transactionPool.submitTransaction(new Transaction("test transaction 3"));
        ObjectProvider.transactionPool.submitTransaction(new Transaction("test transaction 4"));
        ObjectProvider.transactionPool.submitTransaction(new Transaction("test transaction 5"));
        Block block = ObjectProvider.blockMaker.createUnminedBlock();
        assertEquals("db568fa96730542f7cc380be9e57bc6f0f6aed8ae34e5d63345c09df1cd2bca7",
                block.getBlockHeader().getMerkleRoot());
    }


}
