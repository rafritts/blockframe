package com.blockframe.restfulservices.responses;

public class EchoResponse {

    String greeting = "Hello from Blockframe!";

    public String getGreeting() {
        return greeting;
    }

    public void setGreeting(String greeting) {
        this.greeting = greeting;
    }
}
