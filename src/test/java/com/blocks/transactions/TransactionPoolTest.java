package com.blocks.transactions;

import com.blocks.transactions.Transaction;
import com.blocks.transactions.TransactionPool;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TransactionPoolTest {

    private TransactionPool transactionPool = new TransactionPool();

    @Test
    public void testTransactionPool() {
        Transaction transaction = new Transaction("test transaction 1");
        transactionPool.submitTransaction(transaction);
        assertEquals("test transaction 1", transactionPool.getAllUnverifiedTransactions().getFirst().getDetails());
        transaction.setVerified(true);
        assertTrue(transactionPool.getAllUnverifiedTransactions().isEmpty());
    }

}
