package com.blocks.blocks;

import com.blocks.blockchain.Blockchain;
import com.blocks.transactions.Transaction;
import com.blocks.transactions.TransactionPool;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BlockMakerTest {

    private TransactionPool transactionPool = new TransactionPool();
    private Blockchain blockchain = new Blockchain();
    private BlockMaker blockMaker = new BlockMaker(transactionPool, blockchain);

    @Test
    public void testBlockMaker() {
        transactionPool.submitTransaction(new Transaction("test transaction 1"));
        Block block = blockMaker.createUnminedBlock("1.0.0", 1);
        assertEquals(true, block.getBlockHeader().getMinedHash() == null);
        assertEquals("test transaction 1", block.getListOfVerifiedTransactions().getFirst().getDetails());
    }

    @Test
    public void testGetPreviousBlockHash() {
        transactionPool.submitTransaction(new Transaction("test transaction 1"));
        Block block1 = blockMaker.createUnminedBlock("1.0.0", 1);
        block1.getBlockHeader().setMinedHash("MinedHashBlock1");
        blockchain.addBlock(block1);
        transactionPool.submitTransaction(new Transaction("test transaction 1"));
        Block block2 = blockMaker.createUnminedBlock("1.0.0", 1);
        assertEquals("MinedHashBlock1", block2.getBlockHeader().getPreviousBlockHash());
    }

    @Test
    public void testMerkleRoot() {
        Block block = blockMaker.createUnminedBlock("1.0.0.", 1);
        assertEquals("", block.getBlockHeader().getMerkleRoot());
    }
}
