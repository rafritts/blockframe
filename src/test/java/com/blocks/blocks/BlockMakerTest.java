package com.blocks.blocks;

import com.blocks.blocks.Block;
import com.blocks.blocks.BlockMaker;
import com.blocks.transactions.Transaction;
import com.blocks.blockchain.Blockchain;
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
        Block block = blockMaker.createBlock("1.0.0", 1);
        assertEquals(true, block.getBlockHeader().getMinedHash() == null);
        assertEquals("test transaction 1", block.getListOfVerifiedTransactions().getFirst().getDetails());
    }


}
