package com.blockframe.mining;

import com.blockframe.blockchain.Blockchain;
import com.blockframe.blocks.Block;
import com.blockframe.blocks.BlockMaker;
import com.blockframe.transactions.Transaction;
import com.blockframe.transactions.TransactionPool;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MinerTest {

    @Test
    public void testMinerWithOneTransaction() {
        TransactionPool transactionPool = new TransactionPool();
        Blockchain blockchain = new Blockchain();
        BlockMaker blockMaker = new BlockMaker(transactionPool, blockchain);
        transactionPool.submitTransaction(new Transaction("test transaction 1"));
        Block block = blockMaker.createUnminedBlock("1.0.0", 1);
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
        Block block = blockMaker.createUnminedBlock("1.0.0", 1);
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
        Block block = blockMaker.createUnminedBlock("1.0.0", 1);
        Miner.mineBlock(block, 1);
        assertEquals(3, block.getListOfVerifiedTransactions().size());
        assertEquals(27, block.getBlockHeader().getNonce());
        assertEquals("0c3df8f1879fb6443bfaf76ec48ddb778645efc32c6e174aafa25bc5b242815f", block.getBlockHeader().getMinedHash());
        assertEquals("eb28b2097df1af437d4f3baf20a60d17ed3955d6146af8c1ee8a92906f3a49dd", block.getBlockHeader().getMerkleRoot());
    }

    @Test
    public void testMinerWithFiveTransactions() {
        TransactionPool transactionPool = new TransactionPool();
        Blockchain blockchain = new Blockchain();
        BlockMaker blockMaker = new BlockMaker(transactionPool, blockchain);
        transactionPool.submitTransaction(new Transaction("test transaction 1"));
        transactionPool.submitTransaction(new Transaction("test transaction 2"));
        transactionPool.submitTransaction(new Transaction("test transaction 3"));
        transactionPool.submitTransaction(new Transaction("test transaction 4"));
        transactionPool.submitTransaction(new Transaction("test transaction 5"));
        Block block = blockMaker.createUnminedBlock("1.0.0", 1);
        Miner.mineBlock(block, 1);
        assertEquals(5, block.getListOfVerifiedTransactions().size());
        assertEquals(4, block.getBlockHeader().getNonce());
        assertEquals("0694b758ddb47895891d2197bdb019c33608a94ed26bf9718c5a88bedbf0aea6", block.getBlockHeader().getMinedHash());
        assertEquals("db568fa96730542f7cc380be9e57bc6f0f6aed8ae34e5d63345c09df1cd2bca7", block.getBlockHeader().getMerkleRoot());
    }

    @Test
    public void testMinerPrintHashInfo() {
        TransactionPool transactionPool = new TransactionPool();
        Blockchain blockchain = new Blockchain();
        BlockMaker blockMaker = new BlockMaker(transactionPool, blockchain);
        transactionPool.submitTransaction(new Transaction("test transaction 1"));
        Block block = blockMaker.createUnminedBlock("1.0.0", 1);
        Miner.mineBlock(block, 5);
        assertEquals(1, block.getListOfVerifiedTransactions().size());
        assertEquals(2709455, block.getBlockHeader().getNonce());
        assertEquals("0000065d96787c139b75e7a6353c7318605d973c4b9a4ee7696ab648e11e4745", block.getBlockHeader().getMinedHash());
        assertEquals("1f4c5af37ee8e3b361796b6d17c9ab3cbd63f2cbf0e9efc83fd3292a454ee285", block.getBlockHeader().getMerkleRoot());
    }
}
