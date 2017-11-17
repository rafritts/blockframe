package com.blockframe.webservices.services;

import com.blockframe.blocks.BlockValidator;
import com.blockframe.webservices.requests.BlockValidationRequest;
import com.blockframe.webservices.responses.BlockValidationResponse;
import com.google.gson.Gson;

import static spark.Spark.post;


public class BlockValidatorWebService implements Runnable {

    private Gson gson = new Gson();

    @Override
    public void run() {
        post("/blockValidator","application/json", (request, response) -> {
            BlockValidationRequest blockValidationRequest = gson.fromJson(request.body(), BlockValidationRequest.class);
            BlockValidationResponse blockValidationResponse = new BlockValidationResponse();
            blockValidationResponse.isBlockValid = BlockValidator.validateBlock(blockValidationRequest.getBlock());
            return gson.toJson(blockValidationResponse);
        } );
    }
}
