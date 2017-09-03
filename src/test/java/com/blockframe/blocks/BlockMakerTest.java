package com.blockframe.blocks;

import com.blockframe.blockchain.Blockchain;
import com.blockframe.transactions.Transaction;
import com.blockframe.transactions.TransactionPool;
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
    public void testMerkleRootEmpty() {
        Block block = blockMaker.createUnminedBlock("1.0.0.", 1);
        assertEquals("", block.getBlockHeader().getMerkleRoot());
    }

    @Test
    public void testMerkleRootFull() {
        transactionPool.submitTransaction(new Transaction("test transaction 1"));
        transactionPool.submitTransaction(new Transaction("test transaction 2"));
        transactionPool.submitTransaction(new Transaction("test transaction 3"));
        transactionPool.submitTransaction(new Transaction("test transaction 4"));
        transactionPool.submitTransaction(new Transaction("test transaction 5"));
        Block block = blockMaker.createUnminedBlock("1.0.0.", 1);
        assertEquals("db568fa96730542f7cc380be9e57bc6f0f6aed8ae34e5d63345c09df1cd2bca7",
                block.getBlockHeader().getMerkleRoot());
    }


}
