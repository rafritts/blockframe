package com.blocks.transactions;

import com.blocks.transactions.Transaction;
import com.blocks.transactions.TransactionVerifier;
import org.junit.Test;

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
}
