package com.blockframe.restfulservices;

import com.blockframe.blockchain.Blockchain;
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
        get("/blockchain/size", (request, response) -> gson.toJson(blockchain.getBlockchainLength()));
    }

}
