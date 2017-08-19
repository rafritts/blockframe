package com.blocks.models;

public class Transaction {

    private boolean verified = false;
    private boolean mined = false;
    private String details;

    public Transaction(String details) {
        this.details = details;
    }

    public boolean isVerified() {
        return verified;
    }

    public void setVerified(boolean verified) {
        this.verified = verified;
    }

    public boolean isMined() {
        return mined;
    }

    public void setMined(boolean mined) {
        this.mined = mined;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}
