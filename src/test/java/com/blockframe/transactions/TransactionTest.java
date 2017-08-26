package com.blockframe.transactions;

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


}
