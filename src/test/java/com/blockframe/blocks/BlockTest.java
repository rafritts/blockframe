package com.blockframe.blocks;

import com.blockframe.transactions.Transaction;
import org.junit.Assert;
import org.junit.Test;

import java.util.LinkedList;

public class BlockTest {
    @Test
    public void testBlockFields() {
        Block block = new Block();
        block.setBlockHeader(new BlockHeader());
        block.getBlockHeader().setMinedHash("minedPayloadHash");
        block.getBlockHeader().setNonce(1234);
        block.setPayloadAsJson("payload");
        block.getBlockHeader().setPreviousBlockHash("previousPayloadHash");
        block.setListOfVerifiedTransactions(new LinkedList<>());
        Assert.assertEquals("minedPayloadHash", block.getBlockHeader().getMinedHash());
        Assert.assertEquals("payload", block.getPayloadAsJson());
        Assert.assertEquals("previousPayloadHash", block.getBlockHeader().getPreviousBlockHash());
        Assert.assertEquals(1234, block.getBlockHeader().getNonce());
        Assert.assertNotNull(block.getListOfVerifiedTransactions());
    }

    @Test
    public void testTransactionFields() {
        Transaction transaction = new Transaction("test transaction");
        transaction.setDetails("test details");
        transaction.setVerified(true);
        Assert.assertEquals("test details", transaction.getDetails());
        Assert.assertEquals(true, transaction.isVerified());
    }
}
