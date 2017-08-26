package com.blocks.transactions;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class TransactionVerifierTest {

    private TransactionVerifier transactionVerifier = new TransactionVerifier();

    @Test
    public void testTransactionVerifier() {
        Transaction transaction = new Transaction("test transaction 1");
        transaction.setVerified(false);
        transactionVerifier.verify(transaction);
        assertTrue(transaction.isVerified());
    }

    @Test
    public void testVerifyPopulatedTransaction() {
        Transaction transaction = new Transaction("Test Transaction");
        assertFalse(transaction.isVerified());
        TransactionVerifier transactionVerifier = new TransactionVerifier();
        transactionVerifier.verify(transaction);
        assertTrue(transaction.isVerified());
    }

    @Test
    public void testVerifyEmptyTransaction() {
        Transaction transaction = new Transaction("");
        assertFalse(transaction.isVerified());
        TransactionVerifier transactionVerifier = new TransactionVerifier();
        transactionVerifier.verify(transaction);
        assertFalse(transaction.isVerified());
    }


}
