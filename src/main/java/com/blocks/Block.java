package com.blocks;

public class Block {

    private String payload;
    private String currentPayloadHash;
    private String previousPayloadHash;
    private long nonce = 0;

    public String getPreviousPayloadHash() {
        return previousPayloadHash;
    }

    public void setPreviousPayloadHash(String previousPayloadHash) {
        this.previousPayloadHash = previousPayloadHash;
    }

    public String getPayload() {
        return payload;
    }

    public void setPayload(String payload) {
        this.payload = payload;
    }

    public String getCurrentPayloadHash() {
        return currentPayloadHash;
    }

    public void setCurrentPayloadHash(String currentPayloadHash) {
        this.currentPayloadHash = currentPayloadHash;
    }

    public long getNonce() {
        return nonce;
    }

    public void setNonce(long nonce) {
        this.nonce = nonce;
    }
}
