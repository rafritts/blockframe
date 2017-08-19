package com.blocks.models;

import java.util.LinkedList;

public class Block {

    private String payload;
    private String minedPayloadHash;
    private String previousPayloadHash;
    private boolean mined = false;
    private double miningTimeInSeconds = 0.0;
    private long nonce = 0;
    private LinkedList<Transaction> listOfVerifiedTransactions = new LinkedList<>();

    public boolean isMined() {
        return mined;
    }

    public void setMined(boolean mined) {
        this.mined = mined;
    }

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

    public String getMinedPayloadHash() {
        return minedPayloadHash;
    }

    public void setMinedPayloadHash(String minedPayloadHash) {
        this.minedPayloadHash = minedPayloadHash;
    }

    public long getNonce() {
        return nonce;
    }

    public void setNonce(long nonce) {
        this.nonce = nonce;
    }

    public double getMiningTimeInSeconds() {
        return miningTimeInSeconds;
    }

    public void setMiningTimeInSeconds(double miningTimeInSeconds) {
        this.miningTimeInSeconds = miningTimeInSeconds;
    }

    public void addVerifiedTransaction(Transaction transaction) {
        listOfVerifiedTransactions.add(transaction);
    }

    public LinkedList<Transaction> getListOfVerifiedTransactions() {
        return listOfVerifiedTransactions;
    }

    public void setListOfVerifiedTransactions(LinkedList<Transaction> listOfVerifiedTransactions) {
        this.listOfVerifiedTransactions = listOfVerifiedTransactions;
    }

}
