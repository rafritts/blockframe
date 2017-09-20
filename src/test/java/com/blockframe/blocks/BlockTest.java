package com.blockframe.blocks;

import com.blockframe.transactions.Transaction;
import com.blockframe.utils.ObjectProvider;
import org.junit.Assert;
import org.junit.Test;

import java.util.LinkedList;

public class BlockTest {

    @Test
    public void testBlockFields() {
        Block block = new Block();
        block.setBlockHeader(new BlockHeader());
        block.getBlockHeader().setMinedHash("minedPayloadHash");
        block.getBlockHeader().setPreviousBlockHash("previousPayloadHash");
        block.getBlockHeader().setNonce(1234);
        block.setPayloadAsJson("payload");
        Assert.assertEquals("minedPayloadHash", block.getBlockHeader().getMinedHash());
        Assert.assertEquals("previousPayloadHash", block.getBlockHeader().getPreviousBlockHash());
        Assert.assertEquals(1234, block.getBlockHeader().getNonce());
        Assert.assertEquals("payload", block.getPayloadAsJson());
    }

    @Test
    public void testAssignBlockId() {
        Block block = new Block();
        BlockHeader blockHeader = new BlockHeader();
        block.setBlockHeader(blockHeader);
        ObjectProvider.blockchain.addBlock(block);
        block.assignBlockId();
        Assert.assertEquals("1", block.getBlockHeader().getBlockId());
    }

    @Test
    public void testHasTransactionsToMine() {
        Block block = new Block();
        LinkedList<Transaction> transactionsList = new LinkedList<>();
        block.setListOfVerifiedTransactions(transactionsList);
        Assert.assertFalse(block.hasTransactionsToMine());
        transactionsList.add(new Transaction("test"));
        Assert.assertNotNull(block.getListOfVerifiedTransactions());
        Assert.assertTrue(block.hasTransactionsToMine());
    }


}
