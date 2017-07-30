package com.blocks.models;

import org.junit.Assert;
import org.junit.Test;

import java.util.LinkedList;

public class ModelTest {
    @Test
    public void testBlockFields() {
        Block block = new Block();
        block.setMined(true);
        block.setMinedPayloadHash("minedPayloadHash");
        block.setNonce(1234);
        block.setPayload("payload");
        block.setPreviousPayloadHash("previousPayloadHash");
        block.setListOfVerifiedTransactions(new LinkedList<>());
        Assert.assertEquals(true, block.isMined());
        Assert.assertEquals("minedPayloadHash", block.getMinedPayloadHash());
        Assert.assertEquals("payload", block.getPayload());
        Assert.assertEquals("previousPayloadHash", block.getPreviousPayloadHash());
        Assert.assertEquals(1234, block.getNonce());
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
