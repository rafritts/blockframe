package com.blockframe.webservices.services;

import com.blockframe.webservices.requests.TransactionRequest;
import com.blockframe.webservices.responses.TransactionResponse;
import com.blockframe.transactions.Transaction;
import com.blockframe.utils.ObjectProvider;
import com.google.gson.Gson;

import static spark.Spark.get;
import static spark.Spark.post;

public class TransactionWebService implements Runnable {

    private Gson gson = new Gson();

    @Override
    public void run() {
        get("/transaction/transactionList", (request, response) -> gson.toJson(ObjectProvider.transactionPool.getAllUnverifiedTransactions()));
        post("/transaction/submitTransaction", (request, response) -> {
            TransactionRequest transactionRequest = gson.fromJson(request.body(), TransactionRequest.class);
            ObjectProvider.transactionPool.submitTransaction(new Transaction(transactionRequest.getDetails()));
            return gson.toJson(new TransactionResponse(transactionRequest.getDetails()));
        });
    }
}
