package com.blockframe.restfulservices.services;

import com.blockframe.restfulservices.requests.TransactionRequest;
import com.blockframe.restfulservices.responses.TransactionResponse;
import com.blockframe.transactions.Transaction;
import com.blockframe.transactions.TransactionPool;
import com.google.gson.Gson;

import static spark.Spark.get;
import static spark.Spark.post;

public class TransactionWebService implements Runnable {

    private Gson gson = new Gson();
    private TransactionPool transactionPool;

    public TransactionWebService(TransactionPool transactionPool) {
        this.transactionPool = transactionPool;
    }

    @Override
    public void run() {
        get("/transaction/transactionList", (request, response) -> gson.toJson(transactionPool.getAllUnverifiedTransactions()));
        post("/transaction/submitTransaction", (request, response) -> {
            TransactionRequest transactionRequest = gson.fromJson(request.body(), TransactionRequest.class);
            transactionPool.submitTransaction(new Transaction(transactionRequest.getDetails()));
            return gson.toJson(new TransactionResponse(transactionRequest.getDetails()));
        });
    }
}
