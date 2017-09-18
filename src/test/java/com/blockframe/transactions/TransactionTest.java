package com.blockframe.transactions;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TransactionTest {

    @Test
    public void testTransaction() {
        Transaction transaction = new Transaction("Test Transaction");
        transaction.setDetails("More Test Details");
        transaction.setMined(true);
        transaction.setVerified(true);
        assertTrue(transaction.isMined());
        assertTrue(transaction.isVerified());
        assertEquals("More Test Details", transaction.getDetails());
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
