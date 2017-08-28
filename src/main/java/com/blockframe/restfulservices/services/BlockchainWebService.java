package com.blockframe.restfulservices.services;

import com.blockframe.blockchain.Blockchain;
import com.blockframe.restfulservices.responses.BlockchainResponse;
import com.google.gson.Gson;

import static spark.Spark.get;

public class BlockchainWebService implements Runnable {

    private Gson gson = new Gson();
    private Blockchain blockchain;

    public BlockchainWebService(Blockchain blockchain) {
        this.blockchain = blockchain;
    }

    @Override
    public void run() {
        get("/blockchain", (request, response) -> gson.toJson(blockchain));
        get("/blockchain/length", (request, response) ->
                gson.toJson(new BlockchainResponse(blockchain.getBlockchainLength())));
        get("/blockchain/block/:id", (request, response) ->
                gson.toJson(blockchain.getBlockchain().get(Integer.valueOf(request.params(":id")) - 1))
        );
    }

}
