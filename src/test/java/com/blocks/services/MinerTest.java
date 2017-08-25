package com.blocks.services;

import com.blocks.mining.Miner;
import com.blocks.models.Block;
import com.blocks.models.Transaction;
import com.blocks.models.Blockchain;
import com.blocks.resources.TransactionPool;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MinerTest {

    @Test
    public void testMinerWithOneTransaction() {
        TransactionPool transactionPool = new TransactionPool();
        Blockchain blockchain = new Blockchain();
        BlockMaker blockMaker = new BlockMaker(transactionPool, blockchain);
        transactionPool.submitTransaction(new Transaction("test transaction 1"));
        Block block = blockMaker.createBlock("1.0.0", 1);
        Miner.mineBlock(block, 1);
        assertEquals(1, block.getListOfVerifiedTransactions().size());
        assertEquals(45, block.getBlockHeader().getNonce());
        assertEquals("093a99fe4dccfcc5ac498274de2e86f25289773c2940748133a37f44484ce335", block.getBlockHeader().getMinedHash());
        assertEquals("1f4c5af37ee8e3b361796b6d17c9ab3cbd63f2cbf0e9efc83fd3292a454ee285", block.getBlockHeader().getMerkleRoot());
    }

    @Test
    public void testMinerWithTwoTransactions() {
        TransactionPool transactionPool = new TransactionPool();
        Blockchain blockchain = new Blockchain();
        BlockMaker blockMaker = new BlockMaker(transactionPool, blockchain);
        transactionPool.submitTransaction(new Transaction("test transaction 1"));
        transactionPool.submitTransaction(new Transaction("test transaction 2"));
        Block block = blockMaker.createBlock("1.0.0", 1);
        Miner.mineBlock(block, 1);
        assertEquals(2, block.getListOfVerifiedTransactions().size());
        assertEquals(18, block.getBlockHeader().getNonce());
        assertEquals("005899271d04870900a4806cda4fd4ca398d15cc204cf63301b7d141c0e24e22", block.getBlockHeader().getMinedHash());
        assertEquals("077267ed685a5c72c85a434bb975dbfff38d0fb696b23e110e7cc6d2c2db093c", block.getBlockHeader().getMerkleRoot());
    }

    @Test
    public void testMinerWithThreeTransactions() {
        TransactionPool transactionPool = new TransactionPool();
        Blockchain blockchain = new Blockchain();
        BlockMaker blockMaker = new BlockMaker(transactionPool, blockchain);
        transactionPool.submitTransaction(new Transaction("test transaction 1"));
        transactionPool.submitTransaction(new Transaction("test transaction 2"));
        transactionPool.submitTransaction(new Transaction("test transaction 3"));
        Block block = blockMaker.createBlock("1.0.0", 1);
        Miner.mineBlock(block, 1);
        assertEquals(3, block.getListOfVerifiedTransactions().size());
        assertEquals(27, block.getBlockHeader().getNonce());
        assertEquals("0c3df8f1879fb6443bfaf76ec48ddb778645efc32c6e174aafa25bc5b242815f", block.getBlockHeader().getMinedHash());
        assertEquals("eb28b2097df1af437d4f3baf20a60d17ed3955d6146af8c1ee8a92906f3a49dd", block.getBlockHeader().getMerkleRoot());
    }
}
