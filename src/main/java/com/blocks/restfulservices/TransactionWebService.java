package com.blocks.restfulservices;

import com.blocks.transactions.Transaction;
import com.blocks.transactions.TransactionPool;
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
        get("/transactionList", (request, response) -> gson.toJson(transactionPool.getAllUnverifiedTransactions()));
        post("/submitTransaction", (request, response) -> {
            transactionPool.submitTransaction(new Transaction(request.body()));
            response.body("Transaction Submitted");
            return response.body();
        });
    }
}
