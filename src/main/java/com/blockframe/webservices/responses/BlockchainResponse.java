package com.blockframe.webservices.responses;

public class BlockchainResponse {

    private int length;
    private long averageNonce;

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public long getAverageNonce() {
        return averageNonce;
    }

    public void setAverageNonce(long averageNonce) {
        this.averageNonce = averageNonce;
    }
}
