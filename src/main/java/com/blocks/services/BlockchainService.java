package com.blocks.services;

import com.blocks.models.Blockchain;
import com.google.gson.Gson;

import static spark.Spark.get;

public class BlockchainService implements Runnable {

    private Gson gson = new Gson();
    private Blockchain blockchain;

    public BlockchainService (Blockchain blockchain) {
        this.blockchain = blockchain;
    }

    @Override
    public void run() {
        get("/blockchain", (request, response) -> gson.toJson(blockchain.getBlockchain()));
    }
}
