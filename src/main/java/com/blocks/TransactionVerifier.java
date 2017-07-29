package com.blocks;

public class TransactionVerifier {

    public boolean verify(Transaction transaction) {
        return !transaction.getDetails().isEmpty();
    }

}
