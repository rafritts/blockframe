package com.blockframe.restfulservices.responses;

public class BlockchainResponse {

    private int length;

    public BlockchainResponse(int length) {
        this.length = length;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }
}
