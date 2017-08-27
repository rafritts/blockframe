package com.blockframe.restfulservices;

import com.google.gson.Gson;

import static spark.Spark.*;

public class EchoWebService implements Runnable {

    Gson gson = new Gson();
    private String message = "Hello from Blockframe!";

    @Override
    public void run() {
        enableCORS();
        get("/", (request, response) -> gson.toJson(message));
    }

    // Enables CORS on requests. This method is an initialization method and should be called once.
    private static void enableCORS() {

        options("/*",
                (request, response) -> {

                    String accessControlRequestHeaders = request
                            .headers("Access-Control-Request-Headers");
                    if (accessControlRequestHeaders != null) {
                        response.header("Access-Control-Allow-Headers",
                                accessControlRequestHeaders);
                    }

                    String accessControlRequestMethod = request
                            .headers("Access-Control-Request-Method");
                    if (accessControlRequestMethod != null) {
                        response.header("Access-Control-Allow-Methods",
                                accessControlRequestMethod);
                    }

                    return "OK";
                });

        before((request, response) -> response.header("Access-Control-Allow-Origin", "*"));
    }
}
