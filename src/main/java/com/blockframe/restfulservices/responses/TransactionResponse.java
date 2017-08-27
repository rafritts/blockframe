package com.blockframe.restfulservices.responses;

public class TransactionResponse {

    private String message;

    public TransactionResponse(String transactionId) {
        this.message = "Transaction submitted: " + transactionId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
