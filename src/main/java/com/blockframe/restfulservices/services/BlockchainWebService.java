package com.blockframe.restfulservices.services;

import com.blockframe.blocks.Block;
import com.blockframe.restfulservices.responses.BlockchainResponse;
import com.blockframe.utils.ObjectProvider;
import com.google.gson.Gson;

import static spark.Spark.get;

public class BlockchainWebService implements Runnable {

    private Gson gson = new Gson();

    @Override
    public void run() {
        get("/blockchain", (request, response) -> gson.toJson(ObjectProvider.blockchain));
        get("/blockchain/length", (request, response) -> {
                BlockchainResponse blockchainResponse = new BlockchainResponse();
                blockchainResponse.setLength(ObjectProvider.blockchain.getBlockchainLength());
                return gson.toJson(blockchainResponse);
        });
        get("/blockchain/averageNonce", (request, response) -> {
                long totalNonceValue = 0;
                for (Block block : ObjectProvider.blockchain.getBlockchain()) {
                    totalNonceValue += block.getBlockHeader().getNonce();
                }
                BlockchainResponse blockchainResponse = new BlockchainResponse();
                blockchainResponse.setAverageNonce(totalNonceValue / ObjectProvider.blockchain.getBlockchainLength());
                return gson.toJson(blockchainResponse);
        });
        get("/blockchain/block/:id", (request, response) ->
                gson.toJson(ObjectProvider.blockchain.getBlockchain().get(Integer.valueOf(request.params(":id")) - 1))
        );
    }

}
