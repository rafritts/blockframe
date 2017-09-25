package com.blockframe.webservices.services;

import com.blockframe.webservices.requests.DifficultyTargetRequest;
import com.blockframe.webservices.responses.DifficultyTargetResponse;
import com.blockframe.utils.ObjectProvider;
import com.google.gson.Gson;

import static spark.Spark.get;
import static spark.Spark.put;

public class DifficultyTargetWebService implements Runnable {

    private Gson gson = new Gson();

    @Override
    public void run() {
        get("/difficultyTarget", (request, response) -> {
            return gson.toJson(new DifficultyTargetResponse());
        });
        put("/difficultyTarget", "application/json", (request, response) -> {
            DifficultyTargetRequest requestBody = gson.fromJson(request.body(), DifficultyTargetRequest.class);
            ObjectProvider.DIFFICULTY_TARGET = requestBody.getDifficultyTarget();
            return gson.toJson(new DifficultyTargetResponse());
        });
    }

}
