package com.blocks.blocks;

public class BlockHeader {

    private String version;
    private String previousBlockHash;
    private String merkleRoot;
    private String minedHash;
    private long timeStamp;
    private int difficultyTarget;
    private long nonce;
    private double miningTimeInSeconds = 0.0;

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getPreviousBlockHash() {
        return previousBlockHash;
    }

    public void setPreviousBlockHash(String previousBlockHash) {
        this.previousBlockHash = previousBlockHash;
    }

    public String getMerkleRoot() {
        return merkleRoot;
    }

    public void setMerkleRoot(String merkleRoot) {
        this.merkleRoot = merkleRoot;
    }

    public String getMinedHash() {
        return minedHash;
    }

    public void setMinedHash(String minedHash) {
        this.minedHash = minedHash;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }

    public int getDifficultyTarget() {
        return difficultyTarget;
    }

    public void setDifficultyTarget(int difficultyTarget) {
        this.difficultyTarget = difficultyTarget;
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
}
