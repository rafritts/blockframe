package com.blocks.services;

import com.blocks.models.Transaction;

public class TransactionVerifier {

    public boolean verify(Transaction transaction) {
        if (!transaction.getDetails().isEmpty()) {
            transaction.setVerified(true);
        } else {
            transaction.setVerified(false);
        }
        return transaction.isVerified();
    }

}
