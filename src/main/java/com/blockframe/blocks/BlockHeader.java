package com.blockframe.blocks;

public class BlockHeader {

    private String version;
    private String blockId;
    private int difficultyTarget;
    private String previousBlockHash;
    private String minedHash;
    private String merkleRoot;
    private long timeStamp;
    private long nonce;
    private long miningTimeInSeconds = 0;

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

    public String getBlockId() {
        return blockId;
    }

    public void setBlockId(String blockId) {
        this.blockId = blockId;
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

    public long getMiningTimeInSeconds() {
        return miningTimeInSeconds;
    }

    public void setMiningTimeInSeconds(long miningTimeInSeconds) {
        this.miningTimeInSeconds = miningTimeInSeconds;
    }
}
