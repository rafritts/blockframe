package com.blocks;

public class Transaction {

    private boolean verified = false;
    private String details = "This is a transaction";

    public boolean isVerified() {
        return verified;
    }

    public void setVerified(boolean verified) {
        this.verified = verified;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}
